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
public class TrackEnhancer extends ClassVisitor implements Opcodes {

    private String classInternalName;
    private final Map<Method, Monitor> methods;
    private int version;

    public TrackEnhancer(ClassVisitor visitor, Clazz clazz) {
        super(Opcodes.ASM4, visitor);
        methods = clazz.getTrack();
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] originalInterfaces) {
        this.classInternalName = name;
        this.version = version;
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
        try {
            final Monitor monitor = methods.remove(Method.fromDescriptor(name, desc, ""));
            if (monitor != null) {
                Enhance.enhance(cv, monitor.getName(), classInternalName, version, access, name, desc, signature, exceptions, true);
                return super.visitMethod(access, Enhance.target(name), desc, signature, exceptions);
            } else {
                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        } catch (RuntimeException e) {
            System.err.println(name + " " + desc);
            throw e;
        }
    }
}
