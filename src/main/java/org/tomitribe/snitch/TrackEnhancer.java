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
import org.objectweb.asm.commons.GeneratorAdapter;
import org.tomitribe.snitch.util.AsmModifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @version $Revision$ $Date$
 */
public class TrackEnhancer extends ClassVisitor implements Opcodes {

    private String classInternalName;
    private final Map<Method, Monitor> methods;

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
        final Type[] exceptionTypes = (exceptions == null) ? new Type[0] : new Type[exceptions.length];
        for (int i = 0; i < exceptionTypes.length; i++) {
            exceptionTypes[i] = Type.getObjectType(exceptions[i]);
        }

        final org.objectweb.asm.commons.Method method = new org.objectweb.asm.commons.Method(name, desc);
        final GeneratorAdapter mg = new GeneratorAdapter(access, method, signature, exceptionTypes, cv);

        final List<Object> list = new ArrayList<Object>();
        list.add(classInternalName);
        for (Type arg : args) {
            list.add(arg.getInternalName());
        }
        list.add(Opcodes.LONG);
        final Object[] local = list.toArray();

        final String trackerInternalName = Type.getInternalName(Tracker.class);

        if (!desc.contains(")V")) {
            mg.visitCode();
            Label label0 = mg.newLabel();
            Label label1 = mg.newLabel();
            Label label2 = mg.newLabel();
            mg.visitTryCatchBlock(label0, label1, label2, null);
            Label label3 = mg.newLabel();
            mg.visitTryCatchBlock(label2, label3, label2, null);
            mg.invokeStatic(Type.getObjectType(trackerInternalName), org.objectweb.asm.commons.Method.getMethod("void start()"));
            mg.invokeStatic(Type.getObjectType("java/lang/System"), org.objectweb.asm.commons.Method.getMethod("long nanoTime()"));
            int local0 = mg.newLocal(Type.LONG_TYPE);
            mg.storeLocal(local0);
            mg.mark(label0);
            mg.loadThis();
            for (int i = 0; i < args.length; i++) mg.loadArg(0);

            mg.invokeVirtual(Type.getObjectType(classInternalName), new org.objectweb.asm.commons.Method(target(name), desc));
            mg.mark(label1);
            mg.push(monitorName);
            mg.loadLocal(local0);
            mg.invokeStatic(Type.getObjectType(trackerInternalName), org.objectweb.asm.commons.Method.getMethod("void track(java.lang.String,long)"));
            mg.invokeStatic(Type.getObjectType(trackerInternalName), org.objectweb.asm.commons.Method.getMethod("void stop()"));
            Label label4 = mg.newLabel();
            mg.goTo(label4);
            mg.mark(label2);
            mg.visitFrame(Opcodes.F_NEW
                    , 2 + args.length
                    , local
                    , 1
                    , new Object[]{"java/lang/Throwable"});
            int local1 = mg.newLocal(Type.getObjectType("java/lang/Object"));
            mg.storeLocal(local1);
            mg.mark(label3);
            mg.push(monitorName);
            mg.loadLocal(local0);
            mg.invokeStatic(Type.getObjectType(trackerInternalName), org.objectweb.asm.commons.Method.getMethod("void track(java.lang.String,long)"));
            mg.invokeStatic(Type.getObjectType(trackerInternalName), org.objectweb.asm.commons.Method.getMethod("void stop()"));
            mg.loadLocal(local1);
            mg.throwException();
            mg.mark(label4);
            mg.visitFrame(Opcodes.F_NEW, 0, null, 0, null);
            mg.returnValue();
            mg.endMethod();
            mg.visitEnd();

        } else {
            mg.visitCode();
            Label label0 = mg.newLabel();
            Label label1 = mg.newLabel();
            Label label2 = mg.newLabel();
            mg.visitTryCatchBlock(label0, label1, label2, null);
            Label label3 = mg.newLabel();
            mg.visitTryCatchBlock(label2, label3, label2, null);
            mg.invokeStatic(Type.getObjectType(trackerInternalName), org.objectweb.asm.commons.Method.getMethod("void start()"));
            mg.invokeStatic(Type.getObjectType("java/lang/System"), org.objectweb.asm.commons.Method.getMethod("long nanoTime()"));
            int local0 = mg.newLocal(Type.LONG_TYPE);
            mg.storeLocal(local0);
            mg.mark(label0);
            mg.loadThis();
            for (int i = 0; i < args.length; i++) mg.loadArg(0);

            mg.invokeVirtual(Type.getObjectType(classInternalName), new org.objectweb.asm.commons.Method(target(name), desc));
            mg.mark(label1);
            mg.push(monitorName);
            mg.loadLocal(local0);
            mg.invokeStatic(Type.getObjectType(trackerInternalName), org.objectweb.asm.commons.Method.getMethod("void track(java.lang.String,long)"));
            mg.invokeStatic(Type.getObjectType(trackerInternalName), org.objectweb.asm.commons.Method.getMethod("void stop()"));
            Label label4 = mg.newLabel();
            mg.goTo(label4);
            mg.mark(label2);
            mg.visitFrame(Opcodes.F_NEW
                    , 2 + args.length
                    , local
                    , 1
                    , new Object[]{"java/lang/Throwable"});
            int local1 = mg.newLocal(Type.getObjectType("java/lang/Object"));
            mg.storeLocal(local1);
            mg.mark(label3);
            mg.push(monitorName);
            mg.loadLocal(local0);
            mg.invokeStatic(Type.getObjectType(trackerInternalName), org.objectweb.asm.commons.Method.getMethod("void track(java.lang.String,long)"));
            mg.invokeStatic(Type.getObjectType(trackerInternalName), org.objectweb.asm.commons.Method.getMethod("void stop()"));
            mg.loadLocal(local1);
            mg.throwException();
            mg.mark(label4);
            mg.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mg.returnValue();
            mg.endMethod();
            mg.visitEnd();
        }

    }

    private String target(String methodName) {
        return "track$" + methodName;
    }
}
