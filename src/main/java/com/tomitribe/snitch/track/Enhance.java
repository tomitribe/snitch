/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tomitribe.snitch.track;

import com.tomitribe.snitch.util.AsmModifiers;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.objectweb.asm.Type.LONG_TYPE;
import static org.objectweb.asm.Type.VOID_TYPE;

/**
 * @version $Revision$ $Date$
 */
public class Enhance {

    private Enhance() {
        // no-op
    }

    private static void load(List<Type> invocationStack, MethodVisitor mv) {
        int slot = 0;
        for (Type type : invocationStack) {
            mv.visitVarInsn(type.getOpcode(Opcodes.ILOAD), slot);
            slot += size(type);
        }
    }

    private static Object internalName(Type type) {
        if (Type.BYTE_TYPE.equals(type)) { return Opcodes.INTEGER; }
        if (Type.BOOLEAN_TYPE.equals(type)) { return Opcodes.INTEGER; }
        if (Type.CHAR_TYPE.equals(type)) { return Opcodes.INTEGER; }
        if (Type.SHORT_TYPE.equals(type)) { return Opcodes.INTEGER; }
        if (Type.INT_TYPE.equals(type)) { return Opcodes.INTEGER; }
        if (LONG_TYPE.equals(type)) { return Opcodes.LONG; }
        if (Type.FLOAT_TYPE.equals(type)) { return Opcodes.FLOAT; }
        if (Type.DOUBLE_TYPE.equals(type)) { return Opcodes.DOUBLE; }

        return type.getInternalName();
    }

    public static int size(List<Type> types) {
        int size = 0;
        for (Type type : types) {
            size += size(type);
        }

        return size;
    }

    public static int size(Type[] types) {
        int size = 0;
        for (Type type : types) {
            size += size(type);
        }
        return size;
    }

    public static int size(Type type) {
        if (VOID_TYPE.equals(type)) { return 0; }
        if (LONG_TYPE.equals(type) || Type.DOUBLE_TYPE.equals(type)) { return 2; }
        return 1;
    }

    public static Object[] toInternalNames(List<Type> types) {
        final List<Object> objects = new ArrayList<Object>(types.size());

        for (Type type : types) {
            if (VOID_TYPE.equals(type)) { continue; }
            objects.add(internalName(type));
        }

        return objects.toArray();
    }

    public static String target(String name) {
        return "track$" + name;
    }

    public static void enhance(ClassVisitor cw, String monitorName, final String internalName, final int version, int access,
                               String name, String desc, String signature, String[] exceptions)
    {
        enhance(cw, monitorName, internalName, version, access, name, desc, signature, exceptions, false);
    }

    public static void enhance(ClassVisitor cw, String monitorName, final String internalName, final int version, int access,
                               String name, String desc, String signature, String[] exceptions, final boolean track)
    {
        final MethodVisitor mv = visit(cw, monitorName, internalName, version, access, name, desc, signature, exceptions, track);

        mv.visitEnd();
    }

    public static MethodVisitor visit(ClassVisitor cw, String monitorName, String internalName, int version, int access,
                                      String name, String desc, String signature, String[] exceptions, boolean track)
    {
        // Remove Synchronization from wrapper method so we
        if (AsmModifiers.isSynchronized(access)) {
            access -= Opcodes.ACC_SYNCHRONIZED;
        }

        final boolean enableFrames = isJava6orHigher(version);

        final MethodVisitor mv = cw.visitMethod(access, name, desc, signature, exceptions);
        mv.visitCode();

        final Type thisType = Type.getObjectType(internalName);
        final Type returnType = Type.getReturnType(desc);
        final Type throwableType = Type.getType(Throwable.class);
        final Type[] argumentTypes = Type.getArgumentTypes(desc);

        // local variable stack
        final List<Type> locals = new ArrayList<Type>();
        final List<Type> invocationStack = new ArrayList<Type>();

        final boolean isStatic = AsmModifiers.isStatic(access);
        final boolean isVoid = VOID_TYPE.equals(returnType);

        if (!isStatic) {
            locals.add(thisType);
        }

        locals.addAll(Arrays.asList(argumentTypes));

        invocationStack.addAll(locals);

        final int nanotimeVariable = size(locals);
        locals.add(LONG_TYPE);

        final int returnVariable = size(locals);
        locals.add(returnType);

        final int throwableVariable = size((locals));
        locals.add(throwableType);

        final int variablesSize = size(locals);

        final Label tryBlock = new Label();
        final Label successBlock = new Label();
        final Label failureBlock = new Label();
        final Label finallyBlock = new Label();
        final Label endBlock = new Label();

        mv.visitTryCatchBlock(tryBlock, successBlock, failureBlock, null);
        mv.visitTryCatchBlock(failureBlock, finallyBlock, failureBlock, null);

        if (track) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "start", "()V");
        }
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
        mv.visitVarInsn(Opcodes.LSTORE, nanotimeVariable);

        mv.visitLabel(tryBlock);
        {
            // fill the stack for delegating to the right method
            load(invocationStack, mv);

            mv.visitMethodInsn(invoke(access), thisType.getInternalName(), target(name), desc);

            if (!isVoid) {
                mv.visitVarInsn(returnType.getOpcode(Opcodes.ISTORE), returnVariable);
            }
        }
        mv.visitLabel(successBlock);
        {
            mv.visitLdcInsn(monitorName);
            mv.visitVarInsn(Opcodes.LLOAD, nanotimeVariable);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "track", "(Ljava/lang/String;J)V");
            if (track) {
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "stop", "()V");
            }

            if (isVoid) {
                mv.visitJumpInsn(Opcodes.GOTO, endBlock);
            } else {
                mv.visitVarInsn(returnType.getOpcode(Opcodes.ILOAD), returnVariable);
                mv.visitInsn(returnType.getOpcode(Opcodes.IRETURN));
            }
        }
        mv.visitLabel(failureBlock);
        {
            if (enableFrames) {
                final List<Type> newLocals = new ArrayList<Type>(locals);
                newLocals.remove(newLocals.size() - 1); // remove the throwable
                newLocals.remove(newLocals.size() - 1); // remove the return type
                final Object[] objects = toInternalNames(newLocals);
                mv.visitFrame(Opcodes.F_FULL, objects.length, objects, 1, new Object[]{ "java/lang/Throwable" });
            }
            mv.visitVarInsn(Opcodes.ASTORE, throwableVariable);
        }
        mv.visitLabel(finallyBlock);
        {
            mv.visitLdcInsn(monitorName);
            mv.visitVarInsn(Opcodes.LLOAD, nanotimeVariable);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "track", "(Ljava/lang/String;J)V");
            if (track) {
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "stop", "()V");
            }
            mv.visitVarInsn(Opcodes.ALOAD, throwableVariable);
            mv.visitInsn(Opcodes.ATHROW);
        }
        if (isVoid) {
            mv.visitLabel(endBlock);
            {
                if (enableFrames) {
                    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                }
                mv.visitInsn(Opcodes.RETURN);
            }
        }
        mv.visitMaxs(-1, -1);
        return mv;
    }

    private static String tracker() {
        return Type.getType(Tracker.class).getInternalName();
    }

    private static boolean isJava6orHigher(int version) {
        return version >= Opcodes.V1_6;
    }

    private static int invoke(int access) {
        if (AsmModifiers.isStatic(access)) {
            return Opcodes.INVOKESTATIC;
        }
        if (AsmModifiers.isPrivate(access)) {
            return Opcodes.INVOKESPECIAL;
        }

        return Opcodes.INVOKEVIRTUAL;
    }
}
