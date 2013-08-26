/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.tomitribe.snitch.util.AsmModifiers;

import java.io.InputStream;

/**
 * @version $Revision$ $Date$
 */
public class TrackEnhancer extends ClassAdapter implements Opcodes {

    private String classInternalName;
    private final Clazz clazz;

    public TrackEnhancer(ClassVisitor visitor, Clazz clazz) {
        super(visitor);
        this.clazz = clazz;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] originalInterfaces) {
        this.classInternalName = name;
        super.visit(version, access, name, signature, superName, originalInterfaces);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        final Monitor monitor = clazz.get(Method.fromDescriptor(name, desc));

        if (monitor != null) {
            enhanceMethod(monitor, access, name, desc, signature, exceptions);
            return super.visitMethod(access, target(name), desc, signature, exceptions);
        } else {
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
    }

    private boolean monitor(String name, String desc) {
        return name.contains("run");
    }

    private void enhanceMethod(Monitor monitor, int access, String name, String desc, String signature, String[] exceptions) {
        // Remove Synchronization from wrapper method so we
        if (AsmModifiers.isSynchronized(access)) {
            access -= Opcodes.ACC_SYNCHRONIZED;
        }

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
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, classInternalName, target(name), desc);
            mv.visitVarInsn(ASTORE, 2);
            mv.visitLabel(l1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "stop", "()V");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l3);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "stop", "()V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 4);
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
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, classInternalName, target(name), "([Ljava/lang/String;)V");
            mv.visitLabel(l1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "stop", "()V");
            final Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 2);
            mv.visitLabel(l3);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "stop", "()V");
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 3);
            mv.visitEnd();
        }
    }

    private String target(String methodName) {
        return "track$" + methodName;
    }
}
