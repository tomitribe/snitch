/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.TraceClassVisitor;
import org.tomitribe.snitch.util.IO;

import javax.annotation.security.RolesAllowed;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.*;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @version $Revision$ $Date$
 */
public class Asmifier {

    public static void main(String[] args) throws IOException {
        Asmifier.print(Asmifier.class.getClassLoader(), "org.tomitribe.snitch.Blue");
        Asmifier.print(Asmifier.class.getClassLoader(), "org.tomitribe.snitch.Green");
        Asmifier.print(Asmifier.class.getClassLoader(), "org.tomitribe.snitch.Red");
    }

    public static void print(ClassLoader classLoader, final String className) throws IOException {
        final String internalName = className.replace('.', '/') + ".class";
        final URL resource = classLoader.getResource(internalName);
        final org.objectweb.asm.ClassReader reader = new org.objectweb.asm.ClassReader(resource.openStream());


        final File file = new File("/tmp/" + className);

        write(reader, file);
    }

    private static void write(ClassReader reader, File file) throws IOException {
        final OutputStream write = IO.write(file);
        write(reader, write);
    }

    public static void write(ClassReader reader, OutputStream write) throws IOException {
        final TraceClassVisitor visitor = new TraceClassVisitor(null, new ASMifier(), new PrintWriter(write));
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
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

        write(reader, file);
    }
}
