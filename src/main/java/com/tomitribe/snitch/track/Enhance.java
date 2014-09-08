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

    private static final boolean IS_WIN;

    static {
        IS_WIN = System.getProperty("os.name").toLowerCase().contains("windows");
    }

    private Enhance() {
        // no-op
    }

    public static void load(final List<Type> invocationStack, final MethodVisitor mv) {
        int slot = 0;
        for (final Type type : invocationStack) {
            mv.visitVarInsn(type.getOpcode(Opcodes.ILOAD), slot);
            slot += size(type);
        }
    }

    private static Object internalName(final Type type) {
        if (Type.BYTE_TYPE.equals(type)) {
            return Opcodes.INTEGER;
        }
        if (Type.BOOLEAN_TYPE.equals(type)) {
            return Opcodes.INTEGER;
        }
        if (Type.CHAR_TYPE.equals(type)) {
            return Opcodes.INTEGER;
        }
        if (Type.SHORT_TYPE.equals(type)) {
            return Opcodes.INTEGER;
        }
        if (Type.INT_TYPE.equals(type)) {
            return Opcodes.INTEGER;
        }
        if (LONG_TYPE.equals(type)) {
            return Opcodes.LONG;
        }
        if (Type.FLOAT_TYPE.equals(type)) {
            return Opcodes.FLOAT;
        }
        if (Type.DOUBLE_TYPE.equals(type)) {
            return Opcodes.DOUBLE;
        }

        return type.getInternalName();
    }

    public static int size(final List<Type> types) {
        int size = 0;
        for (final Type type : types) {
            size += size(type);
        }

        return size;
    }

    public static int size(final Type[] types) {
        int size = 0;
        for (final Type type : types) {
            size += size(type);
        }
        return size;
    }

    public static int size(final Type type) {
        if (VOID_TYPE.equals(type)) {
            return 0;
        }
        if (LONG_TYPE.equals(type) || Type.DOUBLE_TYPE.equals(type)) {
            return 2;
        }
        return 1;
    }

    public static Object[] toInternalNames(final List<Type> types) {
        final List<Object> objects = new ArrayList<Object>(types.size());

        for (final Type type : types) {
            if (VOID_TYPE.equals(type)) {
                continue;
            }
            objects.add(internalName(type));
        }

        return objects.toArray();
    }

    public static String target(final String name) {
        return "track$" + name;
    }

    public static void enhance(final ClassVisitor cw, final String monitorName, final String internalName, final int version, final int access,
                               final String name, final String desc, final String signature, final String[] exceptions) {
        enhance(cw, monitorName, internalName, version, access, name, desc, signature, exceptions, false);
    }

    public static void enhance(final ClassVisitor cw, final String monitorName, final String internalName, final int version, final int access,
                               final String name, final String desc, final String signature, final String[] exceptions, final boolean track) {
        final MethodVisitor mv = visit(cw, monitorName, internalName, version, access, name, desc, signature, exceptions, track);

        mv.visitEnd();
    }

    public static MethodVisitor visit(final ClassVisitor cw, final String monitorName, final String internalName, final int version, int access,
                                      final String name, final String desc, final String signature, final String[] exceptions, final boolean track) {
        // Remove Synchronization from wrapper method so we
        if (AsmModifiers.isSynchronized(access)) {
            access -= Opcodes.ACC_SYNCHRONIZED;
        }

        final MethodVisitor mv = cw.visitMethod(access, name, desc, signature, exceptions);
        mv.visitCode();

        final VisitorMetaData vmd = new VisitorMetaData(monitorName, access, name, desc, track, mv, version, internalName);

        if (IS_WIN && vmd.isVoid() && vmd.getArgumentTypes().length < 1) {
            return getMethodVisitorVoid(vmd);
        } else {
            return getMethodVisitor(vmd);
        }
    }

    private static MethodVisitor getMethodVisitor(final VisitorMetaData vmd) {

        final MethodVisitor mv = vmd.getMv();
        final Label l0 = new Label();
        final Label l1 = new Label();
        final Label l2 = new Label();

        mv.visitTryCatchBlock(l0, l1, l2, null);

        final Label l3 = new Label();
        mv.visitTryCatchBlock(l2, l3, l2, null);

        if (vmd.isTrack()) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "start", "()V", vmd.isInterface());
        }
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "nanoTime", "()J", vmd.isInterface());
        mv.visitVarInsn(Opcodes.LSTORE, vmd.getNanotimeVariable());

        mv.visitLabel(l0);

        // fill the stack for delegating to the right method
        load(vmd.getInvocationStack(), mv);
        mv.visitMethodInsn(invoke(vmd.getAccess()), vmd.getThisType().getInternalName(), target(vmd.getName()), vmd.getDesc(), vmd.isInterface());

        if (!vmd.isVoid()) {
            mv.visitVarInsn(vmd.getReturnType().getOpcode(Opcodes.ISTORE), vmd.getReturnVariable());
        }

        mv.visitLabel(l1);
        Label l4 = null;

        mv.visitLdcInsn(vmd.getMonitorName());
        mv.visitVarInsn(Opcodes.LLOAD, vmd.getNanotimeVariable());
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "track", "(Ljava/lang/String;J)V", vmd.isInterface());

        if (vmd.isTrack()) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "stop", "()V", vmd.isInterface());
        }

        if (vmd.isVoid()) {
            l4 = new Label();
            mv.visitJumpInsn(Opcodes.GOTO, l4);
        } else {
            mv.visitVarInsn(vmd.getReturnType().getOpcode(Opcodes.ILOAD), vmd.getReturnVariable());
            mv.visitInsn(vmd.getReturnType().getOpcode(Opcodes.IRETURN));
        }

        mv.visitLabel(l2);

        if (vmd.isEnableFrames()) {
            final List<Type> newLocals = new ArrayList<Type>(vmd.getLocals());
            newLocals.remove(newLocals.size() - 1); // remove the throwable
            newLocals.remove(newLocals.size() - 1); // remove the return type
            final Object[] objects = toInternalNames(newLocals);
            mv.visitFrame(Opcodes.F_FULL, objects.length, objects, 1, new Object[]{"java/lang/Throwable"});
        }

        mv.visitVarInsn(Opcodes.ASTORE, vmd.getThrowableVariable());
        mv.visitLabel(l3);
        mv.visitLdcInsn(vmd.getMonitorName());
        mv.visitVarInsn(Opcodes.LLOAD, vmd.getNanotimeVariable());
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "track", "(Ljava/lang/String;J)V", vmd.isInterface());

        if (vmd.isTrack()) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "stop", "()V", vmd.isInterface());
        }

        mv.visitVarInsn(Opcodes.ALOAD, vmd.getThrowableVariable());
        mv.visitInsn(Opcodes.ATHROW);

        if (vmd.isVoid()) {
            mv.visitLabel(l4);
            {
                if (vmd.isEnableFrames()) {
                    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                }
                mv.visitInsn(Opcodes.RETURN);
            }
        }

        mv.visitMaxs(-1, -1);
        return mv;
    }

    /**
     * For a parameterless void method
     *
     * @param vmd VisitorMetaData
     * @return MethodVisitor
     */
    private static MethodVisitor getMethodVisitorVoid(final VisitorMetaData vmd) {

        final MethodVisitor mv = vmd.getMv();
        final Label l0 = new Label();
        final Label l1 = new Label();
        final Label l2 = new Label();

        mv.visitTryCatchBlock(l0, l1, l2, null);

        if (vmd.isTrack()) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "start", "()V", vmd.isInterface());
        }
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "nanoTime", "()J", vmd.isInterface());
        mv.visitVarInsn(Opcodes.LSTORE, vmd.getNanotimeVariable());

        mv.visitLabel(l0);

        // fill the stack for delegating to the right method
        load(vmd.getInvocationStack(), mv);
        mv.visitMethodInsn(invoke(vmd.getAccess()), vmd.getThisType().getInternalName(), target(vmd.getName()), vmd.getDesc(), vmd.isInterface());
        mv.visitLabel(l1);
        mv.visitLdcInsn(vmd.getMonitorName());
        mv.visitVarInsn(Opcodes.LLOAD, vmd.getNanotimeVariable());
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "track", "(Ljava/lang/String;J)V", vmd.isInterface());

        if (vmd.isTrack()) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "stop", "()V", vmd.isInterface());
        }

        final Label l3 = new Label();
        mv.visitJumpInsn(Opcodes.GOTO, l3);
        mv.visitLabel(l2);

        if (vmd.isEnableFrames()) {
            final List<Type> newLocals = new ArrayList<Type>(vmd.getLocals());
            newLocals.remove(newLocals.size() - 1); // remove the throwable
            newLocals.remove(newLocals.size() - 1); // remove the return type
            final Object[] objects = toInternalNames(newLocals);
            mv.visitFrame(Opcodes.F_FULL, objects.length, objects, 1, new Object[]{"java/lang/Throwable"});
        }

        mv.visitVarInsn(Opcodes.ASTORE, vmd.getThrowableVariable());

        mv.visitLdcInsn(vmd.getMonitorName());
        mv.visitVarInsn(Opcodes.LLOAD, vmd.getNanotimeVariable());
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "track", "(Ljava/lang/String;J)V", vmd.isInterface());

        if (vmd.isTrack()) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, tracker(), "stop", "()V", vmd.isInterface());
        }

        mv.visitVarInsn(Opcodes.ALOAD, vmd.getThrowableVariable());
        mv.visitInsn(Opcodes.ATHROW);
        mv.visitLabel(l3);

        if (vmd.isEnableFrames()) {
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        }

        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(-1, -1);
        return mv;
    }

    private static String tracker() {
        return Type.getType(Tracker.class).getInternalName();
    }

    private static boolean isJava6orHigher(final int version) {
        return version >= Opcodes.V1_6;
    }

    private static int invoke(final int access) {
        if (AsmModifiers.isStatic(access)) {
            return Opcodes.INVOKESTATIC;
        }
        if (AsmModifiers.isPrivate(access)) {
            return Opcodes.INVOKESPECIAL;
        }

        return Opcodes.INVOKEVIRTUAL;
    }

    private static class VisitorMetaData {
        private final String monitorName;
        private final int access;
        private final String name;
        private final String desc;
        private final boolean track;
        private final boolean enableFrames;
        private final MethodVisitor mv;
        private final Type thisType;
        private final Type returnType;
        private final List<Type> locals;
        private final List<Type> invocationStack;
        private final boolean isInterface;
        private final boolean isVoid;
        private final int nanotimeVariable;
        private final int returnVariable;
        private final int throwableVariable;
        private final int variablesSize;
        private final Type throwableType;
        private final Type[] argumentTypes;
        private final boolean isStatic;

        private VisitorMetaData(final String monitorName,
                                final int access,
                                final String name,
                                final String desc,
                                final boolean track,
                                final MethodVisitor mv,
                                final int version,
                                final String internalName) {

            this.monitorName = monitorName;
            this.access = access;
            this.name = name;
            this.desc = desc;
            this.track = track;
            this.mv = mv;

            this.returnType = Type.getReturnType(desc);
            this.isVoid = VOID_TYPE.equals(returnType);
            this.argumentTypes = Type.getArgumentTypes(desc);

            this.enableFrames = isJava6orHigher(version);
            this.thisType = Type.getObjectType(internalName);
            this.throwableType = Type.getType(Throwable.class);

            // local variable stack
            this.locals = new ArrayList<Type>();
            this.invocationStack = new ArrayList<Type>();

            isStatic = AsmModifiers.isStatic(access);
            this.isInterface = AsmModifiers.isInterface(access);


            if (!isStatic) {
                locals.add(thisType);
            }

            locals.addAll(Arrays.asList(argumentTypes));

            invocationStack.addAll(locals);

            this.nanotimeVariable = size(locals);
            locals.add(LONG_TYPE);

            this.returnVariable = size(locals);
            locals.add(returnType);

            this.throwableVariable = size((locals));
            locals.add(throwableType);

            this.variablesSize = size(locals);
        }

        public String getMonitorName() {
            return monitorName;
        }

        public int getAccess() {
            return access;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        public boolean isTrack() {
            return track;
        }

        public boolean isStatic() {
            return isStatic;
        }

        public int getVariablesSize() {
            return variablesSize;
        }

        public Type getThrowableType() {
            return throwableType;
        }

        public Type[] getArgumentTypes() {
            return argumentTypes;
        }

        public boolean isEnableFrames() {
            return enableFrames;
        }

        public MethodVisitor getMv() {
            return mv;
        }

        public Type getThisType() {
            return thisType;
        }

        public Type getReturnType() {
            return returnType;
        }

        public List<Type> getLocals() {
            return locals;
        }

        public List<Type> getInvocationStack() {
            return invocationStack;
        }

        public boolean isInterface() {
            return isInterface;
        }

        public boolean isVoid() {
            return isVoid;
        }

        public int getNanotimeVariable() {
            return nanotimeVariable;
        }

        public int getReturnVariable() {
            return returnVariable;
        }

        public int getThrowableVariable() {
            return throwableVariable;
        }
    }
}
