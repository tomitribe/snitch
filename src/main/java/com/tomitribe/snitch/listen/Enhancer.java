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
package com.tomitribe.snitch.listen;

import com.tomitribe.snitch.Filter;
import com.tomitribe.snitch.Method;
import com.tomitribe.snitch.track.Bytecode;
import com.tomitribe.snitch.track.Log;
import com.tomitribe.snitch.util.IO;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @version $Revision$ $Date$
 */
public class Enhancer {

    private final Map<String, Clazz> classes = new HashMap<String, Clazz>();

    public static Enhancer create(final File file) throws IOException {
        final Properties properties = IO.readProperties(file);

        return create(properties);
    }

    public Clazz getClazz(String name) {
        name = normalize(name);
        return classes.get(name);
    }

    public static Enhancer create(final Properties properties) {
        final Enhancer configuration = new Enhancer();

        for (final Map.Entry<Object, Object> entry : properties.entrySet()) {

            final String key = (String) entry.getKey();
            final String value = (String) entry.getValue();

            final Method method = Method.fromToString(key);
            final Type type = Type.getObjectType(normalize(value));

            configuration.clazz(method.getClassName()).setListener(method, type);
        }

        return configuration;
    }

    private Clazz clazz(String name) {
        name = normalize(name);
        {
            final Clazz clazz = classes.get(name);
            if (clazz != null) {
                return clazz;
            }
        }

        {
            final Clazz clazz = new Clazz(name);
            classes.put(clazz.getInternalName(), clazz);
            return clazz;
        }
    }

    private static String normalize(String name) {
        if (name.contains(".")) {
            name = name.replace('.', '/');
        }
        return name;
    }

    public byte[] enhance(final String className, final byte[] bytecode) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        final Clazz clazz = getClazz(className);
        if (clazz == null) {
            return bytecode;
        }

        if (!clazz.shouldListen()) {
            return bytecode;
        }

        Log.log("Listen %s", className);

        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        final MethodFilter methodFilter = new MethodFilter(clazz.getListeners());
        final ClassVisitor classAdapter = new InsertListenerVisitor(cw, methodFilter);

        Bytecode.read(bytecode, classAdapter);

        return cw.toByteArray();

    }


    public static class MethodFilter implements Filter<Type> {

        private final Map<Method, Type> methods;

        public MethodFilter(final Map<Method, Type> methods) {
            this.methods = methods;
        }

        @Override
        public Type accept(final Method method) {
            return methods.remove(method);
        }

        @Override
        public void end() {
            for (final Map.Entry<Method, Type> unused : methods.entrySet()) {
                Log.err("No Such Method: %s for listener %s", unused.getKey(), unused.getValue().getInternalName());
            }
        }
    }
}
