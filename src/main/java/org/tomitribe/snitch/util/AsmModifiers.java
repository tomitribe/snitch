/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch.util;

import org.objectweb.asm.Opcodes;

/**
 * @version $Revision$ $Date$
 */
public class AsmModifiers implements Opcodes {

    public static boolean isPublic(int mod) {
        return (mod & ACC_PUBLIC) != 0;
    }

    public static boolean isPrivate(int mod) {
        return (mod & ACC_PRIVATE) != 0;
    }

    public static boolean isProtected(int mod) {
        return (mod & ACC_PROTECTED) != 0;
    }

    public static boolean isStatic(int mod) {
        return (mod & ACC_STATIC) != 0;
    }

    public static boolean isFinal(int mod) {
        return (mod & ACC_FINAL) != 0;
    }

    public static boolean isSynchronized(int mod) {
        return (mod & ACC_SYNCHRONIZED) != 0;
    }

    public static boolean isVolatile(int mod) {
        return (mod & ACC_VOLATILE) != 0;
    }

    public static boolean isTransient(int mod) {
        return (mod & ACC_TRANSIENT) != 0;
    }

    public static boolean isNative(int mod) {
        return (mod & ACC_NATIVE) != 0;
    }

    public static boolean isInterface(int mod) {
        return (mod & ACC_INTERFACE) != 0;
    }

    public static boolean isAbstract(int mod) {
        return (mod & ACC_ABSTRACT) != 0;
    }
}