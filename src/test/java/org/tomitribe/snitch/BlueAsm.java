/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @version $Revision$ $Date$
 */
public class BlueAsm implements Opcodes {

    public void doit(ClassWriter cw) {
        MethodVisitor mv;

        {
            mv = cw.visitMethod(
                    ACC_PUBLIC,
                    "voidMethod1",
                    "(Lorg/tomitribe/snitch/Blue$Arg0;)V",
                    null,
                    new String[]{"java/lang/IllegalStateException"}
            );
            mv.visitCode(

            );
            Label l0 = new Label(

            );
            Label l1 = new Label(

            );
            Label l2 = new Label(

            );
            mv.visitTryCatchBlock(
                    l0,
                    l1,
                    l2,
                    null
            );
            Label l3 = new Label(

            );
            mv.visitTryCatchBlock(
                    l2,
                    l3,
                    l2,
                    null
            );
            mv.visitMethodInsn(
                    INVOKESTATIC,
                    "java/lang/System",
                    "nanoTime",
                    "()J"
            );
            mv.visitVarInsn(
                    LSTORE,
                    2
            );
            mv.visitLabel(
                    l0
            );
            mv.visitVarInsn(
                    ALOAD,
                    0
            );
            mv.visitVarInsn(
                    ALOAD,
                    1
            );
            mv.visitMethodInsn(
                    INVOKEVIRTUAL,
                    "org/tomitribe/snitch/Blue",
                    "track$voidMethod1",
                    "(Lorg/tomitribe/snitch/Blue$Arg0;)V"
            );
            mv.visitLabel(
                    l1
            );
            mv.visitLdcInsn(
                    "theTag"
            );
            mv.visitVarInsn(
                    LLOAD,
                    2
            );
            mv.visitMethodInsn(
                    INVOKESTATIC,
                    "org/tomitribe/snitch/Tracker",
                    "track",
                    "(Ljava/lang/String;J)V"
            );
            Label l4 = new Label(

            );
            mv.visitJumpInsn(
                    GOTO,
                    l4
            );
            mv.visitLabel(
                    l2
            );
            mv.visitFrame(
                    Opcodes.F_FULL,
                    3,
                    new Object[]{"org/tomitribe/snitch/Blue",
                            "org/tomitribe/snitch/Blue$Arg0",
                            Opcodes.LONG},
                    1,
                    new Object[]{"java/lang/Throwable"}
            );
            mv.visitVarInsn(
                    ASTORE,
                    4
            );
            mv.visitLabel(
                    l3
            );
            mv.visitLdcInsn(
                    "theTag"
            );
            mv.visitVarInsn(
                    LLOAD,
                    2
            );
            mv.visitMethodInsn(
                    INVOKESTATIC,
                    "org/tomitribe/snitch/Tracker",
                    "track",
                    "(Ljava/lang/String;J)V"
            );
            mv.visitVarInsn(
                    ALOAD,
                    4
            );
            mv.visitInsn(
                    ATHROW
            );
            mv.visitLabel(
                    l4
            );
            mv.visitFrame(
                    Opcodes.F_SAME,
                    0,
                    null,
                    0,
                    null
            );
            mv.visitInsn(
                    RETURN
            );
            mv.visitMaxs(
                    3,
                    5
            );
            mv.visitEnd(

            );
        }
        {
            mv = cw.visitMethod(
                    ACC_PUBLIC,
                    "booleanMethod1",
                    "(Lorg/tomitribe/snitch/Blue$Arg0;)Z",
                    null,
                    new String[]{"java/lang/IllegalStateException"}
            );
            mv.visitCode(

            );
            Label l0 = new Label(

            );
            Label l1 = new Label(

            );
            Label l2 = new Label(

            );
            mv.visitTryCatchBlock(
                    l0,
                    l1,
                    l2,
                    null
            );
            Label l3 = new Label(

            );
            mv.visitTryCatchBlock(
                    l2,
                    l3,
                    l2,
                    null
            );
            mv.visitMethodInsn(
                    INVOKESTATIC,
                    "java/lang/System",
                    "nanoTime",
                    "()J"
            );
            mv.visitVarInsn(
                    LSTORE,
                    2
            );
            mv.visitLabel(
                    l0
            );
            mv.visitVarInsn(
                    ALOAD,
                    0
            );
            mv.visitVarInsn(
                    ALOAD,
                    1
            );
            mv.visitMethodInsn(
                    INVOKEVIRTUAL,
                    "org/tomitribe/snitch/Blue",
                    "track$booleanMethod1",
                    "(Lorg/tomitribe/snitch/Blue$Arg0;)Z"
            );
            mv.visitVarInsn(
                    ISTORE,
                    4
            );
            mv.visitLabel(
                    l1
            );
            mv.visitLdcInsn(
                    "theTag"
            );
            mv.visitVarInsn(
                    LLOAD,
                    2
            );
            mv.visitMethodInsn(
                    INVOKESTATIC,
                    "org/tomitribe/snitch/Tracker",
                    "track",
                    "(Ljava/lang/String;J)V"
            );
            mv.visitVarInsn(
                    ILOAD,
                    4
            );
            mv.visitInsn(
                    IRETURN
            );
            mv.visitLabel(
                    l2
            );
            mv.visitFrame(
                    Opcodes.F_FULL,
                    3,
                    new Object[]{"org/tomitribe/snitch/Blue",
                            "org/tomitribe/snitch/Blue$Arg0",
                            Opcodes.LONG},
                    1,
                    new Object[]{"java/lang/Throwable"}
            );
            mv.visitVarInsn(
                    ASTORE,
                    5
            );
            mv.visitLabel(
                    l3
            );
            mv.visitLdcInsn(
                    "theTag"
            );
            mv.visitVarInsn(
                    LLOAD,
                    2
            );
            mv.visitMethodInsn(
                    INVOKESTATIC,
                    "org/tomitribe/snitch/Tracker",
                    "track",
                    "(Ljava/lang/String;J)V"
            );
            mv.visitVarInsn(
                    ALOAD,
                    5
            );
            mv.visitInsn(
                    ATHROW
            );
            mv.visitMaxs(
                    3,
                    6
            );
            mv.visitEnd(

            );
        }

    }
}
