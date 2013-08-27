/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.tomitribe.snitch.util.AsmModifiers;

import java.util.Map;

/**
 * @version $Revision$ $Date$
 */
public class TrackEnhancer extends ClassVisitor implements Opcodes {

    private String classInternalName;
    private final Map<Method,Monitor> methods;

    public TrackEnhancer(ClassVisitor visitor, Clazz clazz) {
        super(Opcodes.ASM4, visitor);
        methods = clazz.getTrack();
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] originalInterfaces) {
        this.classInternalName = name;
        super.visit(version, access, name, signature, superName, originalInterfaces);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();

        for (Map.Entry<Method, Monitor> unused : methods.entrySet()) {
            System.err.printf("SNITCH: No Such Method: %s = %s%n", unused.getValue().getName(), unused.getKey());
        }
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        final Monitor monitor = methods.remove(Method.fromDescriptor(name, desc, ""));

        if (monitor != null) {
            enhanceMethod(monitor, access, name, desc, signature, exceptions);
            return super.visitMethod(access, target(name), desc, signature, exceptions);
        } else {
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
    }

    private void enhanceMethod(Monitor monitor, int access, String name, String desc, String signature, String[] exceptions) {
        // Remove Synchronization from wrapper method so we
        if (AsmModifiers.isSynchronized(access)) {
            access -= Opcodes.ACC_SYNCHRONIZED;
        }

        final String monitorName = monitor.getName();
        final Type[] args = Type.getArgumentTypes(desc);

        if (!desc.contains(")V")) {

            final MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
            mv.visitCode();
            final Label l0 = new Label();
            final Label l1 = new Label();
            final Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            final Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "start", "()V");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 1 + args.length);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);

            for (int i = 0; i < args.length; i++) {
                mv.visitVarInsn(ALOAD, i + 1);
            }

            mv.visitMethodInsn(INVOKEVIRTUAL, classInternalName, target(name), desc);
            mv.visitVarInsn(ASTORE, 4);
            mv.visitLabel(l1);
            mv.visitLdcInsn(monitorName);
            mv.visitVarInsn(LLOAD, 1 + args.length);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "stop", "()V");
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 3, new Object[]{classInternalName, "[Ljava/lang/String;", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 5);
            mv.visitLabel(l3);
            mv.visitLdcInsn(monitorName);
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "stop", "()V");
            mv.visitVarInsn(ALOAD, 5);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(3, 6);
            mv.visitEnd();
        } else {
            final MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
            mv.visitCode();
            final Label l0 = new Label();
            final Label l1 = new Label();
            final Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            final Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "start", "()V");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 2);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, classInternalName, target(name), desc);
            mv.visitLabel(l1);
            mv.visitLdcInsn(monitorName);
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "stop", "()V");
            final Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 3, new Object[]{classInternalName, "[Ljava/lang/String;", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 4);
            mv.visitLabel(l3);
            mv.visitLdcInsn(monitorName);
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "stop", "()V");
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 5);
            mv.visitEnd();
        }
    }

    private String target(String methodName) {
        return "track$" + methodName;
    }
}
