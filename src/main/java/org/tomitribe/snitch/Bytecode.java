/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.tomitribe.snitch.util.IO;
import org.tomitribe.snitch.util.Unsafe;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @version $Revision$ $Date$
 */
public class Bytecode {

    public static Class modify(URLClassLoader classLoader, final Class<? extends ClassVisitor> adapterClass, final Clazz clazz) throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        final byte[] originalBytes = readClassFile(classLoader, clazz.getName());

        final byte[] modifiedBytes = modify(originalBytes, adapterClass, clazz);

        return defineClass(modifiedBytes, clazz.getName(), classLoader);
    }

    public static byte[] readClassFile(Class clazz) throws IOException {
        return readClassFile(clazz.getClassLoader(), clazz);
    }

    public static byte[] readClassFile(ClassLoader classLoader, Class clazz) throws IOException {
        final String internalName = clazz.getName().replace('.', '/') + ".class";
        final URL resource = classLoader.getResource(internalName);
        return IO.readBytes(resource);
    }

    public static byte[] readClassFile(ClassLoader classLoader, String className) throws IOException {
        final String internalName = className.replace('.', '/') + ".class";
        final URL resource = classLoader.getResource(internalName);
        return IO.readBytes(resource);
    }

    public static Class defineClass(byte[] bytes, String className, ClassLoader classLoader) {
        return Unsafe.defineClass(className, bytes, 0, bytes.length, classLoader, null);
    }

    public static byte[] modify(byte[] bytes, final Clazz clazz, Class<? extends ClassVisitor>... adapterClasses) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        for (Class<? extends ClassVisitor> adapterClass : adapterClasses) {
            bytes = modify(bytes, adapterClass, clazz);
        }
        return bytes;
    }

    public static byte[] modify(byte[] originalBytes, Class<? extends ClassVisitor> adapterClass, final Clazz clazz) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        final Constructor<? extends ClassVisitor> constructor = adapterClass.getConstructor(ClassVisitor.class, Clazz.class);
        final ClassVisitor classAdapter = constructor.newInstance(cw, clazz);

        read(originalBytes, classAdapter);

        return cw.toByteArray();
    }

    public static void read(byte[] originalBytes, ClassVisitor classAdapter) {
        final ClassReader cr = new ClassReader(originalBytes);
        cr.accept(classAdapter, ClassReader.EXPAND_FRAMES);
    }

    public static void modifyAndDefine(ClassLoader loader, final Clazz clazz, final Class<? extends ClassVisitor>... classes) throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        byte[] bytes = readClassFile(loader, clazz.getName());
        bytes = modify(bytes, clazz, classes);
        defineClass(bytes, clazz.getName(), loader);
    }
}
