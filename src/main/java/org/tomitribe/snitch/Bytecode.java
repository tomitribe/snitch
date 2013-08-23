/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;


import org.objectweb.asm.ClassAdapter;
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

    public static Class modify(URLClassLoader classLoader, final String className, final Class<? extends ClassAdapter> adapterClass) throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        final byte[] originalBytes = readClassFile(classLoader, className);

        final byte[] modifiedBytes = modify(originalBytes, adapterClass);

        return defineClass(modifiedBytes, className, classLoader);
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

    public static byte[] modify(byte[] bytes, Class<? extends ClassAdapter>... adapterClasses) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        for (Class<? extends ClassAdapter> adapterClass : adapterClasses) {
            bytes = modify(bytes, adapterClass);
        }
        return bytes;
    }

    public static byte[] modify(byte[] originalBytes, Class<? extends ClassAdapter> adapterClass) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        final ClassReader cr = new ClassReader(originalBytes);
        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        final Constructor<? extends ClassAdapter> constructor = adapterClass.getConstructor(ClassVisitor.class);
        final ClassAdapter classAdapter = constructor.newInstance(cw);
        cr.accept(classAdapter, ClassReader.EXPAND_FRAMES);

        return cw.toByteArray();
    }

    public static void modifyAndDefine(ClassLoader loader, String className, final Class<? extends ClassAdapter>... classes) throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        byte[] bytes = readClassFile(loader, className);
        bytes = modify(bytes, classes);
        defineClass(bytes, className, loader);
    }
}
