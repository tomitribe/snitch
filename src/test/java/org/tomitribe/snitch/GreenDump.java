package org.tomitribe.snitch;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

public class GreenDump implements Opcodes {

    public static void main(String[] args) throws Exception {
        final byte[] dump = dump();
        final Class clazz = Bytecode.defineClass(dump, "org.tomitribe.snitch.Green", new URLClassLoader(new URL[0]));
        final Object blue = clazz.newInstance();

        Tracker.start();

        for (java.lang.reflect.Method method : clazz.getDeclaredMethods()) {
            try {
                final Class<?>[] types = method.getParameterTypes();
                final Object[] objects = new Object[types.length];
                for (int i = 0; i < types.length; i++) {
                    final Class<?> type = types[i];
                    if (!type.isPrimitive()) continue;

                    if (Byte.TYPE.equals(type)) {
                        objects[i] = (byte) 1;
                    } else if (Short.TYPE.equals(type)) {
                        objects[i] = (short) 1;
                    } else if (Integer.TYPE.equals(type)) {
                        objects[i] = 1;
                    } else if (Long.TYPE.equals(type)) {
                        objects[i] = 1l;
                    } else if (Float.TYPE.equals(type)) {
                        objects[i] = 1f;
                    } else if (Double.TYPE.equals(type)) {
                        objects[i] = 1d;
                    } else if (Character.TYPE.equals(type)) {
                        objects[i] = '1';
                    } else if (Boolean.TYPE.equals(type)) {
                        objects[i] = true;
                    } else {
                        throw new IllegalStateException("Unknown primitive type " + type.getName());
                    }

                }
                method.invoke(blue, objects);
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

        cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "org/tomitribe/snitch/Green", null, "java/lang/Object", null);

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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$voidMethodTime0", "()V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Green", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime0", "()V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "byteMethodTime0", "()B", null, new String[]{"java/lang/IllegalStateException"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$byteMethodTime0", "()B");
            mv.visitVarInsn(ISTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ILOAD, 3);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Green", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$byteArrayMethodTime0", "()[B");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 3);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/tomitribe/snitch/Green", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
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
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime1", "(B)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 2);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$voidMethodTime1", "(B)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 3, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 4);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime1", "(B)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "booleanMethodTime1", "(B)Z", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 2);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$booleanMethodTime1", "(B)Z");
            mv.visitVarInsn(ISTORE, 4);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ILOAD, 4);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 3, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 5);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 5);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(3, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$booleanMethodTime1", "(B)Z", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "booleanArrayMethodTime1", "(B)[Z", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 2);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$booleanArrayMethodTime1", "(B)[Z");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 3, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 5);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 2);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 5);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(3, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$booleanArrayMethodTime1", "(B)[Z", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime2", "(BZ)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 3);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$voidMethodTime2", "(BZ)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 3);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 4, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 5);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 3);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 5);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime2", "(BZ)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "charMethodTime2", "(BZ)C", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 3);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$charMethodTime2", "(BZ)C");
            mv.visitVarInsn(ISTORE, 5);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 3);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ILOAD, 5);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 4, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 6);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 3);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 6);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(3, 7);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$charMethodTime2", "(BZ)C", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "charArrayMethodTime2", "(BZ)[C", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 3);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$charArrayMethodTime2", "(BZ)[C");
            mv.visitVarInsn(ASTORE, 5);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 3);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 5);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 4, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 6);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 3);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 6);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(3, 7);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$charArrayMethodTime2", "(BZ)[C", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime3", "(BZC)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 4);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$voidMethodTime3", "(BZC)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 4);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 5, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 6);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 4);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 6);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(4, 7);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime3", "(BZC)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "shortMethodTime3", "(BZC)S", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 4);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$shortMethodTime3", "(BZC)S");
            mv.visitVarInsn(ISTORE, 6);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 4);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ILOAD, 6);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 5, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 7);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 4);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 7);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(4, 8);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$shortMethodTime3", "(BZC)S", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "shortArrayMethodTime3", "(BZC)[S", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 4);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$shortArrayMethodTime3", "(BZC)[S");
            mv.visitVarInsn(ASTORE, 6);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 4);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 6);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 5, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 7);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 4);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 7);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(4, 8);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$shortArrayMethodTime3", "(BZC)[S", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 4);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime4", "(BZCS)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 5);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$voidMethodTime4", "(BZCS)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 5);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 6, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 7);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 5);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 7);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(5, 8);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime4", "(BZCS)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "intMethodTime4", "(BZCS)I", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 5);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$intMethodTime4", "(BZCS)I");
            mv.visitVarInsn(ISTORE, 7);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 5);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ILOAD, 7);
            mv.visitInsn(IRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 6, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 8);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 5);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 8);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(5, 9);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$intMethodTime4", "(BZCS)I", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "intArrayMethodTime4", "(BZCS)[I", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 5);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$intArrayMethodTime4", "(BZCS)[I");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 5);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 7);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 6, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 8);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 5);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 8);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(5, 9);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$intArrayMethodTime4", "(BZCS)[I", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime5", "(BZCSI)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 6);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$voidMethodTime5", "(BZCSI)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 7, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 8);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 8);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(6, 9);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime5", "(BZCSI)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "longMethodTime5", "(BZCSI)J", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 6);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$longMethodTime5", "(BZCSI)J");
            mv.visitVarInsn(LSTORE, 8);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(LLOAD, 8);
            mv.visitInsn(LRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 7, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 10);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 10);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(6, 11);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$longMethodTime5", "(BZCSI)J", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "longArrayMethodTime5", "(BZCSI)[J", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 6);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$longArrayMethodTime5", "(BZCSI)[J");
            mv.visitVarInsn(ASTORE, 8);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 8);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 7, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 9);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 9);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(6, 10);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$longArrayMethodTime5", "(BZCSI)[J", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 6);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime6", "(BZCSIJ)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 8);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$voidMethodTime6", "(BZCSIJ)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 8);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 8, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 10);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 8);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 10);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(8, 11);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime6", "(BZCSIJ)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 8);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "floatMethodTime6", "(BZCSIJ)F", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 8);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$floatMethodTime6", "(BZCSIJ)F");
            mv.visitVarInsn(FSTORE, 10);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 8);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(FLOAD, 10);
            mv.visitInsn(FRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 8, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 11);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 8);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 11);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(8, 12);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$floatMethodTime6", "(BZCSIJ)F", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 8);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "floatArrayMethodTime6", "(BZCSIJ)[F", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 8);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitVarInsn(LLOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$floatArrayMethodTime6", "(BZCSIJ)[F");
            mv.visitVarInsn(ASTORE, 10);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 8);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 10);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 8, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 11);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 8);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 11);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(8, 12);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$floatArrayMethodTime6", "(BZCSIJ)[F", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 8);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime7", "(BZCSIJF)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 9);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitVarInsn(LLOAD, 6);
            mv.visitVarInsn(FLOAD, 8);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$voidMethodTime7", "(BZCSIJF)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 9);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 9, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG, Opcodes.FLOAT, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 11);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 9);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 11);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(9, 12);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime7", "(BZCSIJF)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 9);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "doubleMethodTime7", "(BZCSIJF)D", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 9);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitVarInsn(LLOAD, 6);
            mv.visitVarInsn(FLOAD, 8);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$doubleMethodTime7", "(BZCSIJF)D");
            mv.visitVarInsn(DSTORE, 11);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 9);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(DLOAD, 11);
            mv.visitInsn(DRETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 9, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG, Opcodes.FLOAT, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 13);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 9);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 13);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(9, 14);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$doubleMethodTime7", "(BZCSIJF)D", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 9);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "doubleArrayMethodTime7", "(BZCSIJF)[D", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 9);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitVarInsn(LLOAD, 6);
            mv.visitVarInsn(FLOAD, 8);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$doubleArrayMethodTime7", "(BZCSIJF)[D");
            mv.visitVarInsn(ASTORE, 11);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 9);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 11);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 9, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG, Opcodes.FLOAT, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 12);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 9);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 12);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(9, 13);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$doubleArrayMethodTime7", "(BZCSIJF)[D", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 9);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "voidMethodTime8", "(BZCSIJFD)V", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 11);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitVarInsn(LLOAD, 6);
            mv.visitVarInsn(FLOAD, 8);
            mv.visitVarInsn(DLOAD, 9);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$voidMethodTime8", "(BZCSIJFD)V");
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 11);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            Label l4 = new Label();
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 10, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG, Opcodes.FLOAT, Opcodes.DOUBLE, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 13);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 11);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 13);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(11, 14);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime8", "(BZCSIJFD)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 11);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "DateMethodTime8", "(BZCSIJFD)Ljava/util/Date;", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 11);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitVarInsn(LLOAD, 6);
            mv.visitVarInsn(FLOAD, 8);
            mv.visitVarInsn(DLOAD, 9);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$DateMethodTime8", "(BZCSIJFD)Ljava/util/Date;");
            mv.visitVarInsn(ASTORE, 13);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 11);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 13);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 10, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG, Opcodes.FLOAT, Opcodes.DOUBLE, Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 14);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 11);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 14);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(11, 15);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$DateMethodTime8", "(BZCSIJFD)Ljava/util/Date;", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 11);
            mv.visitEnd();
        }
        {
            final int access = ACC_PUBLIC;
            final String name = "DateArrayMethodTime8";
            final String desc = "(BZCSIJFD)[Ljava/util/Date;";
            final String signature = null;
            final String[] exceptions = {"java/lang/IllegalStateException"};
            Enhance.enhance(cw, "dateArrayMethod8", "org/tomitribe/snitch/Green", access, name, desc, signature, exceptions);
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$DateArrayMethodTime8", "(BZCSIJFD)[Ljava/util/Date;", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 11);
            mv.visitEnd();
        }
        {
            final int access = ACC_PUBLIC;
            final String name = "voidMethodTime9";
            final String desc = "(BZCSIJFDLjava/util/Date;)V";
            final String signature = null;
            final String monitorName = "time9";
            final String[] exceptions = {"java/lang/IllegalStateException"};

            Enhance.enhance(cw, monitorName, "org/tomitribe/snitch/Green", access, name, desc, signature, exceptions);
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$voidMethodTime9", "(BZCSIJFDLjava/util/Date;)V", null, null);
            mv.visitCode();
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 12);
            mv.visitEnd();
        }
        {
            final String desc = "(BZCSIJFDLjava/util/Date;)Ljava/net/URI;";
            final int access = ACC_PUBLIC;
            final String[] exceptions = {"java/lang/IllegalStateException"};
            final String name = "URIMethodTime9";
            final String signature = null;
            final String monitorName = "urimethod9";

            Enhance.enhance(cw, monitorName, "org/tomitribe/snitch/Green", access, name, desc, signature, exceptions);
        }
        {
            final String desc = "(BZCSIJFDLjava/util/Date;)Ljava/net/URI;";
            mv = cw.visitMethod(ACC_PUBLIC, "track$URIMethodTime9", desc, null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 12);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "URIArrayMethodTime9", "(BZCSIJFDLjava/util/Date;)[Ljava/net/URI;", null, new String[]{"java/lang/IllegalStateException"});
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
            mv.visitVarInsn(LSTORE, 12);
            mv.visitLabel(l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitVarInsn(ILOAD, 2);
            mv.visitVarInsn(ILOAD, 3);
            mv.visitVarInsn(ILOAD, 4);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitVarInsn(LLOAD, 6);
            mv.visitVarInsn(FLOAD, 8);
            mv.visitVarInsn(DLOAD, 9);
            mv.visitVarInsn(ALOAD, 11);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/tomitribe/snitch/Green", "track$URIArrayMethodTime9", "(BZCSIJFDLjava/util/Date;)[Ljava/net/URI;");
            mv.visitVarInsn(ASTORE, 14);
            mv.visitLabel(l1);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 12);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 14);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_FULL, 11, new Object[]{"org/tomitribe/snitch/Green", Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.LONG, Opcodes.FLOAT, Opcodes.DOUBLE, "java/util/Date", Opcodes.LONG}, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 15);
            mv.visitLabel(l3);
            mv.visitLdcInsn("theTag");
            mv.visitVarInsn(LLOAD, 12);
            mv.visitMethodInsn(INVOKESTATIC, "org/tomitribe/snitch/Tracker", "track", "(Ljava/lang/String;J)V");
            mv.visitVarInsn(ALOAD, 15);
            mv.visitInsn(ATHROW);
            mv.visitMaxs(12, 16);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "track$URIArrayMethodTime9", "(BZCSIJFDLjava/util/Date;)[Ljava/net/URI;", null, null);
            mv.visitCode();
            mv.visitTypeInsn(NEW, "java/lang/UnsupportedOperationException");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V");
            mv.visitInsn(ATHROW);
            mv.visitMaxs(2, 12);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }

}
