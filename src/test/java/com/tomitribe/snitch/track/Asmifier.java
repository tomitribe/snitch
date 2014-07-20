/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tomitribe.snitch.track;

import com.tomitribe.snitch.util.IO;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;

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
