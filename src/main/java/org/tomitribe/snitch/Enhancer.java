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
public class Enhancer extends ClassAdapter implements Opcodes {

    private String classInternalName = "org/tomitribe/snitch/util/AsmTest$Orange2";

    public Enhancer(ClassVisitor classVisitor) {
        super(classVisitor);
    }

    public static byte[] enchance(byte[] original) throws Exception {
        return enchance(original, false);
    }

    public static byte[] enchance(byte[] original, final boolean b) throws Exception {
        return enhance(new ClassReader(original), b);
    }

    public static byte[] enchance(String className) throws Exception {
        return enhance(new ClassReader(className), false);
    }

    public static byte[] enchance(InputStream inputStream) throws Exception {
        return enhance(new ClassReader(inputStream), false);
    }

    public static byte[] enhance(ClassReader cr, final boolean b) {
        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);

        final Enhancer enhancer = new Enhancer(cw);

        cr.accept(enhancer, ClassReader.EXPAND_FRAMES);

        return cw.toByteArray();
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

        if (name.contains("doit2")) {
            enhanceMethod(access, name, desc, signature, exceptions);
            return super.visitMethod(access, target(name), desc, signature, exceptions);
        } else {
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
    }

    private void enhanceMethod(int access, String name, String desc, String signature, String[] exceptions) {
        // Remove Synchronization from wrapper method so we
        if (AsmModifiers.isSynchronized(access)) {
            access -= Opcodes.ACC_SYNCHRONIZED;
        }

        final MethodVisitor mv = this.cv.visitMethod(access, name, desc, signature, exceptions);
        mv.visitCode();
        Label l0 = new Label();
        Label l1 = new Label();
        Label l2 = new Label();
        mv.visitTryCatchBlock(l0, l1, l2, null);
        Label l3 = new Label();
        mv.visitTryCatchBlock(l2, l3, l2, null);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
        mv.visitVarInsn(LSTORE, 1);
        mv.visitLabel(l0);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKEVIRTUAL, classInternalName, target(name), desc);
        mv.visitVarInsn(ASTORE, 3);
        mv.visitLabel(l1);
        mv.visitLdcInsn(name);
        mv.visitVarInsn(LLOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
        mv.visitVarInsn(ALOAD, 3);
        mv.visitInsn(ARETURN);
        mv.visitLabel(l2);
        mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{classInternalName, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
        mv.visitVarInsn(ASTORE, 4);
        mv.visitLabel(l3);
        mv.visitLdcInsn(name);
        mv.visitVarInsn(LLOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
        mv.visitVarInsn(ALOAD, 4);
        mv.visitInsn(ATHROW);
        mv.visitMaxs(-1, -1);
        mv.visitEnd();
    }

    private String target(String methodName) {
        return "track$" + methodName;
    }
}
