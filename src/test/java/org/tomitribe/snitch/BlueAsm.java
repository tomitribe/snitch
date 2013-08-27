/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.objectweb.asm.*;

/**
 * @version $Revision$ $Date$
 */
public class BlueAsm implements Opcodes{

    public void doit(ClassWriter cw) {
        MethodVisitor mv;

        {
        mv = cw.visitMethod(ACC_PUBLIC, "voidMethod0", "()V", null, new String[] { "java/lang/IllegalStateException" });
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
        mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$voidMethod0", "()V");
        mv.visitLabel(l1);
        mv.visitLdcInsn("theTag");
        mv.visitVarInsn(LLOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
        Label l4 = new Label();
        mv.visitJumpInsn(GOTO, l4);
        mv.visitLabel(l2);
        mv.visitFrame(Opcodes.F_FULL, 2, new Object[] {"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[] {"java/lang/Throwable"});
        mv.visitVarInsn(ASTORE, 3);
        mv.visitLabel(l3);
        mv.visitLdcInsn("theTag");
        mv.visitVarInsn(LLOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
        mv.visitVarInsn(ALOAD, 3);
        mv.visitInsn(ATHROW);
        mv.visitLabel(l4);
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        mv.visitInsn(RETURN);
        mv.visitMaxs(3, 4);
        mv.visitEnd();
        }
        {
        mv = cw.visitMethod(ACC_PUBLIC, "booleanMethod0", "()Z", null, new String[] { "java/lang/IllegalStateException" });
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
        mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$booleanMethod0", "()Z");
        mv.visitVarInsn(ISTORE, 3);
        mv.visitLabel(l1);
        mv.visitLdcInsn("theTag");
        mv.visitVarInsn(LLOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
        mv.visitVarInsn(ILOAD, 3);
        mv.visitInsn(IRETURN);
        mv.visitLabel(l2);
        mv.visitFrame(Opcodes.F_FULL, 2, new Object[] {"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[] {"java/lang/Throwable"});
        mv.visitVarInsn(ASTORE, 4);
        mv.visitLabel(l3);
        mv.visitLdcInsn("theTag");
        mv.visitVarInsn(LLOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
        mv.visitVarInsn(ALOAD, 4);
        mv.visitInsn(ATHROW);
        mv.visitMaxs(3, 5);
        mv.visitEnd();
        }

    }
}
