/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Map;

/**
 * @version $Revision$ $Date$
 */
public class GenericEnhancer extends ClassVisitor implements Opcodes {

    private final Filter filter;

    private String classInternalName;
    private int version;
    private boolean track;

    public GenericEnhancer(ClassVisitor classVisitor, Filter filter) {
        this(classVisitor, filter, false);
    }

    public GenericEnhancer(ClassVisitor classVisitor, Filter filter, boolean track) {
        super(Opcodes.ASM4, classVisitor);
        this.filter = filter;
        this.track= track;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.classInternalName = name;
        this.version = version;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitEnd() {
        filter.end();
        super.visitEnd();
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        try {
            final Method method = Method.fromDescriptor(name, desc, classInternalName);
            final String monitor = filter.accept(method);
            if (monitor != null) {
                Enhance.enhance(cv, monitor, classInternalName, version, access, name, desc, signature, exceptions, track);
                return super.visitMethod(access, Enhance.target(name), desc, signature, exceptions);
            } else {
                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        } catch (RuntimeException e) {
            Log.err("Enhance failed %s %s %s", classInternalName, name, desc);
            throw e;
        }
    }

    public static class MethodFilter implements Filter {

        private final Map<Method, Monitor> methods;

        public MethodFilter(Map<Method, Monitor> methods) {
            this.methods = methods;
        }

        @Override
        public String accept(Method method) {
            final Monitor monitor = methods.remove(method);
            if (monitor != null) return monitor.getName();
            return null;
        }

        @Override
        public void end() {
            for (Map.Entry<Method, Monitor> unused : methods.entrySet()) {
                Log.err("No Such Method: %s = %s", unused.getValue().getName(), unused.getKey());
            }
        }
    }
}
