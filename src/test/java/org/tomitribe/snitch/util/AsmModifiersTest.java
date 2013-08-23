/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch.util;

import junit.framework.TestCase;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.EmptyVisitor;

/**
 * @version $Revision$ $Date$
 */
public class AsmModifiersTest extends TestCase {

    public void testIsPublic() throws Exception {
        final ClassReader classReader = new ClassReader(IsPublic.class.getName());
        classReader.accept(new EmptyVisitor() {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertTrue(AsmModifiers.isPublic(access));
                assertFalse(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));
                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isAbstract(access));
                assertFalse(AsmModifiers.isNative(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);
    }

    public void testIsPrivate() throws Exception {
        final ClassReader classReader = new ClassReader(IsPrivate.class.getName());
        classReader.accept(new EmptyVisitor() {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertFalse(AsmModifiers.isPublic(access));
                assertTrue(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));
                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isAbstract(access));
                assertFalse(AsmModifiers.isNative(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);
    }

    public void testIsProtected() throws Exception {
        final ClassReader classReader = new ClassReader(IsProtected.class.getName());
        classReader.accept(new EmptyVisitor() {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertFalse(AsmModifiers.isPublic(access));
                assertFalse(AsmModifiers.isPrivate(access));
                assertTrue(AsmModifiers.isProtected(access));
                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isAbstract(access));
                assertFalse(AsmModifiers.isNative(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);

    }

    public void testIsStatic() throws Exception {
        final ClassReader classReader = new ClassReader(IsStatic.class.getName());
        classReader.accept(new EmptyVisitor() {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertTrue(AsmModifiers.isPublic(access));
                assertFalse(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));
                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isAbstract(access));
                assertFalse(AsmModifiers.isNative(access));
                assertTrue(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);

    }

    public void testIsFinal() throws Exception {
        final ClassReader classReader = new ClassReader(IsFinal.class.getName());
        classReader.accept(new EmptyVisitor() {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertTrue(AsmModifiers.isPublic(access));
                assertFalse(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));
                assertTrue(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isAbstract(access));
                assertFalse(AsmModifiers.isNative(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);
    }

    public void testIsSynchronized() throws Exception {
        final ClassReader classReader = new ClassReader(IsSynchronized.class.getName());
        classReader.accept(new EmptyVisitor() {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertTrue(AsmModifiers.isPublic(access));
                assertFalse(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));
                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isAbstract(access));
                assertFalse(AsmModifiers.isNative(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertTrue(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);
    }

    public void testIsVolatile() throws Exception {
        final ClassReader classReader = new ClassReader(IsVolatile.class.getName());
        classReader.accept(new EmptyVisitor() {
            @Override
            public FieldVisitor visitField(int access, String s, String s1, String s2, Object o) {

                assertFalse(AsmModifiers.isPublic(access));
                assertTrue(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));

                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isTransient(access));
                assertTrue(AsmModifiers.isVolatile(access));

                return super.visitField(access, s, s1, s2, o);
            }
        }, 0);
    }

    public void testIsTransient() throws Exception {
        final ClassReader classReader = new ClassReader(IsTransient.class.getName());
        classReader.accept(new EmptyVisitor() {
            @Override
            public FieldVisitor visitField(int access, String s, String s1, String s2, Object o) {

                assertFalse(AsmModifiers.isPublic(access));
                assertTrue(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));

                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertTrue(AsmModifiers.isTransient(access));
                assertFalse(AsmModifiers.isVolatile(access));

                return super.visitField(access, s, s1, s2, o);
            }
        }, 0);
    }

    public void testIsNative() throws Exception {
        final ClassReader classReader = new ClassReader(IsNative.class.getName());
        classReader.accept(new EmptyVisitor() {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertTrue(AsmModifiers.isPublic(access));
                assertFalse(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));
                assertFalse(AsmModifiers.isFinal(access));
                assertFalse(AsmModifiers.isAbstract(access));
                assertTrue(AsmModifiers.isNative(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);
    }

    public void testIsAbstract() throws Exception {
        final ClassReader classReader = new ClassReader(IsAbstract.class.getName());
        classReader.accept(new EmptyVisitor() {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                if (name.equals("<init>")) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                }

                assertTrue(AsmModifiers.isPublic(access));
                assertFalse(AsmModifiers.isPrivate(access));
                assertFalse(AsmModifiers.isProtected(access));
                assertFalse(AsmModifiers.isFinal(access));
                assertTrue(AsmModifiers.isAbstract(access));
                assertFalse(AsmModifiers.isNative(access));
                assertFalse(AsmModifiers.isStatic(access));
                assertFalse(AsmModifiers.isSynchronized(access));

                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        }, 0);
    }

    public static class IsPublic {
        public void method() {
        }
    }

    public static class IsPrivate {
        private void method() {
        }
    }

    public static class IsProtected {
        protected void method() {
        }
    }

    public static class IsFinal {
        public final void method() {
        }
    }

    public static class IsStatic {
        public static void method() {
        }
    }

    public static class IsSynchronized {
        public synchronized void method() {
        }
    }

    public static class IsNative {
        public native void method();
    }

    public abstract static class IsAbstract {
        public abstract void method();
    }

    public static class IsTransient {
        private transient Object field;
    }

    public static class IsVolatile {
        private volatile Object field;
    }
}
