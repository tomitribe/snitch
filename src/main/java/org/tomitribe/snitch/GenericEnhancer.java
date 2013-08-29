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

/**
 * @version $Revision$ $Date$
 */
public class GenericEnhancer extends ClassVisitor implements Opcodes {

    private final Filter filter;

    private String classInternalName;

    public GenericEnhancer(ClassVisitor classVisitor, Filter filter) {
        super(Opcodes.ASM4, classVisitor);
        this.filter = filter;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.classInternalName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        try {
            final Method method = Method.fromDescriptor(name, desc, "");
            final String monitor = filter.accept(method);
            if (monitor != null) {
                Enhance.enhance(cv, monitor, classInternalName, access, name, desc, signature, exceptions);
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
