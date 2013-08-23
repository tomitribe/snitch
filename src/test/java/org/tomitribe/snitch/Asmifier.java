/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.objectweb.asm.ClassReader;
import org.tomitribe.snitch.util.IO;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @version $Revision$ $Date$
 */
public class Asmifier {

    public static void print(URLClassLoader classLoader, final String className) throws IOException {
        final String internalName = className.replace('.', '/') + ".class";
        final URL resource = classLoader.getResource(internalName);
        final org.objectweb.asm.ClassReader reader = new org.objectweb.asm.ClassReader(resource.openStream());


        final File file = new File("/tmp/" + className);
        final OutputStream write = IO.write(file);
        reader.accept(new org.objectweb.asm.util.ASMifierClassVisitor(new PrintWriter(write)), ClassReader.SKIP_DEBUG);
        write.close();
    }


    public static void asmify(Class clazz, String suffix) throws IOException {
        asmify(clazz.getName(), Bytecode.readClassFile(clazz.getClassLoader(), clazz), suffix);
    }

    public static void asmify(ClassLoader loader, String className, String suffix) throws IOException {
        asmify(className, Bytecode.readClassFile(loader, className), suffix);
    }

    public static void asmify(String className, byte[] bytes, final String suffix) throws IOException {
        final org.objectweb.asm.ClassReader reader = new org.objectweb.asm.ClassReader(bytes);
        final File file = new File("/tmp/" + className + "." + suffix);
        final OutputStream write = IO.write(file);
        reader.accept(new org.objectweb.asm.util.ASMifierClassVisitor(new PrintWriter(write)), ClassReader.SKIP_DEBUG);
        write.close();
    }
}
