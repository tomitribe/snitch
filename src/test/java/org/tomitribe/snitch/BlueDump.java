package org.tomitribe.snitch;

import org.objectweb.asm.*;

import java.lang.reflect.*;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;

public class BlueDump implements Opcodes {

    public static void main(String[] args) throws Exception {
        final byte[] dump = dump();
        final Class clazz = Bytecode.defineClass(dump, "org.tomitribe.snitch.Blue", new URLClassLoader(new URL[0]));
        final Object blue = clazz.newInstance();

        Tracker.start();

        for (Method method : clazz.getDeclaredMethods()) {
            try {
                method.invoke(blue);
            } catch (InvocationTargetException e) {
            }
        }

        Tracker.stop();
    }

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "org/tomitribe/snitch/Blue", null, "java/lang/Object", null);

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime0", "()V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label tryBlock = new Label();
            Label successBlock = new Label();
            Label catchBlock = new Label();
            Label failureBlock = new Label();

            Label endBlock = new Label();

            mv.visitTryCatchBlock(tryBlock, successBlock, catchBlock, null);


            mv.visitTryCatchBlock(catchBlock, failureBlock, catchBlock, null);

            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 1);

            mv.visitLabel(tryBlock);
            {
                mv.visitVarInsn(ALOAD, 0);
                mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$voidMethodTime0", "()V");
            }

            mv.visitLabel(successBlock);
            {
                mv.visitLdcInsn("theTag");
                mv.visitVarInsn(LLOAD, 1);
                mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
                mv.visitInsn(RETURN);
            }

            mv.visitLabel(catchBlock);
            {
                mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
                mv.visitVarInsn(ASTORE, 3);
            }

            mv.visitLabel(failureBlock);
            {
                mv.visitLdcInsn("theTag");
                mv.visitVarInsn(LLOAD, 1);
                mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
                mv.visitVarInsn(ALOAD, 3);
                mv.visitInsn(ATHROW);
            }

            mv.visitMaxs(3, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime0", "()V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "byteMethodTime0", "()B", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();

            final org.objectweb.asm.Type returnType = org.objectweb.asm.Type.getReturnType("()B");

            Label tryBlock = new Label();
            Label successBlock = new Label();
            Label catchBlock = new Label();

            mv.visitTryCatchBlock(tryBlock, successBlock, catchBlock, null);

            Label failureBlock = new Label();

            mv.visitTryCatchBlock(catchBlock, failureBlock, catchBlock, null);

            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 1);

            // try
            mv.visitLabel(tryBlock);
            {
                mv.visitVarInsn(ALOAD, 0);  // load "this" onto stack
                mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$byteMethodTime0", "()B");
                mv.visitVarInsn(returnType.getOpcode(ISTORE), 3);
            }
            mv.visitLabel(successBlock);
            {
                mv.visitLdcInsn("theTag");
                mv.visitVarInsn(LLOAD, 1);
                mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
                mv.visitVarInsn(returnType.getOpcode(ILOAD), 3);
                mv.visitInsn(returnType.getOpcode(IRETURN));
            }
            mv.visitLabel(catchBlock);
            {
                mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
                mv.visitVarInsn(ASTORE, 4);
            }
            mv.visitLabel(failureBlock);
            {
                mv.visitLdcInsn("theTag");
                mv.visitVarInsn(LLOAD, 1);
                mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
                mv.visitVarInsn(ALOAD, 4);
                mv.visitInsn(ATHROW);
            }
            mv.visitMaxs(3, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$byteMethodTime0", "()B", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "byteArrayMethodTime0", "()[B", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$byteArrayMethodTime0", "()[B");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$byteArrayMethodTime0", "()[B", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime1", "()V", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$voidMethodTime1", "()V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime1", "()V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "booleanMethodTime1", "()Z", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$booleanMethodTime1", "()Z");
            mv.visitVarInsn(ISTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ILOAD, 3);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$booleanMethodTime1", "()Z", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "booleanArrayMethodTime1", "()[Z", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$booleanArrayMethodTime1", "()[Z");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$booleanArrayMethodTime1", "()[Z", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime2", "()V", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$voidMethodTime2", "()V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime2", "()V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "charMethodTime2", "()C", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$charMethodTime2", "()C");
            mv.visitVarInsn(ISTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ILOAD, 3);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$charMethodTime2", "()C", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "charArrayMethodTime2", "()[C", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$charArrayMethodTime2", "()[C");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$charArrayMethodTime2", "()[C", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime3", "()V", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$voidMethodTime3", "()V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime3", "()V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "shortMethodTime3", "()S", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$shortMethodTime3", "()S");
            mv.visitVarInsn(ISTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ILOAD, 3);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$shortMethodTime3", "()S", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "shortArrayMethodTime3", "()[S", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$shortArrayMethodTime3", "()[S");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$shortArrayMethodTime3", "()[S", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime4", "()V", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$voidMethodTime4", "()V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime4", "()V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "intMethodTime4", "()I", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$intMethodTime4", "()I");
            mv.visitVarInsn(ISTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ILOAD, 3);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$intMethodTime4", "()I", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "intArrayMethodTime4", "()[I", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$intArrayMethodTime4", "()[I");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$intArrayMethodTime4", "()[I", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime5", "()V", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$voidMethodTime5", "()V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime5", "()V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "longMethodTime5", "()J", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$longMethodTime5", "()J");
            mv.visitVarInsn(LSTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(LLOAD, 3);
            mv.visitInsn(LRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 5);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 5);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(3, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$longMethodTime5", "()J", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "longArrayMethodTime5", "()[J", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$longArrayMethodTime5", "()[J");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$longArrayMethodTime5", "()[J", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime6", "()V", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$voidMethodTime6", "()V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime6", "()V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "floatMethodTime6", "()F", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$floatMethodTime6", "()F");
            mv.visitVarInsn(FSTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(FLOAD, 3);
            mv.visitInsn(FRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$floatMethodTime6", "()F", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "floatArrayMethodTime6", "()[F", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$floatArrayMethodTime6", "()[F");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$floatArrayMethodTime6", "()[F", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime7", "()V", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$voidMethodTime7", "()V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime7", "()V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "doubleMethodTime7", "()D", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$doubleMethodTime7", "()D");
            mv.visitVarInsn(DSTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(DLOAD, 3);
            mv.visitInsn(DRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 5);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 5);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(3, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$doubleMethodTime7", "()D", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "doubleArrayMethodTime7", "()[D", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$doubleArrayMethodTime7", "()[D");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$doubleArrayMethodTime7", "()[D", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime8", "()V", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$voidMethodTime8", "()V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime8", "()V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "DateMethodTime8", "()Ljava/util/Date;", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$DateMethodTime8", "()Ljava/util/Date;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$DateMethodTime8", "()Ljava/util/Date;", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "DateArrayMethodTime8", "()[Ljava/util/Date;", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$DateArrayMethodTime8", "()[Ljava/util/Date;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$DateArrayMethodTime8", "()[Ljava/util/Date;", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime9", "()V", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$voidMethodTime9", "()V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime9", "()V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "URIMethodTime9", "()Ljava/net/URI;", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$URIMethodTime9", "()Ljava/net/URI;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$URIMethodTime9", "()Ljava/net/URI;", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "URIArrayMethodTime9", "()[Ljava/net/URI;", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Blue", "track$URIArrayMethodTime9", "()[Ljava/net/URI;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Blue", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$URIArrayMethodTime9", "()[Ljava/net/URI;", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
