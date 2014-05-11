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
package org.tomitribe.snitch;

import org.tomitribe.snitch.util.IO;

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

    public static Enhancer create(File file) throws IOException {
        final Properties properties = IO.readProperties(file);

        return create(properties);
    }

    public Clazz getClazz(String name) {
        name = normalize(name);
        return classes.get(name);
    }

    public static Enhancer create(Properties properties) {
        final Enhancer configuration = new Enhancer();

        for (Map.Entry<Object, Object> entry : properties.entrySet()) {

            final String key = (String) entry.getKey();
            final String value = (String) entry.getValue();

            final Method method = Method.fromToString(value);

            if (key.startsWith("@")) {
                configuration.clazz(method.getClassName()).track(method, key.substring(1));
            } else {
                configuration.clazz(method.getClassName()).time(method, key);
            }
        }

        return configuration;
    }

    private Clazz clazz(String name) {
        name = normalize(name);
        {
            final Clazz clazz = classes.get(name);
            if (clazz != null) { return clazz; }
        }

        {
            final Clazz clazz = new Clazz(name);
            classes.put(clazz.getInternalName(), clazz);
            return clazz;
        }
    }

    private String normalize(String name) {
        if (name.contains(".")) {
            name = name.replace('.', '/');
        }
        return name;
    }

    public byte[] enhance(String className, byte[] bytecode) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        final Clazz clazz = getClazz(className);
        if (clazz == null) { return bytecode; }

        Log.log("Tracking %s", className);

        if (clazz.shouldTrack()) {
            bytecode = Bytecode.modify(bytecode, TrackEnhancer.class, clazz);
        }

        if (clazz.shouldTime()) {
            bytecode = Bytecode.modify(bytecode, TimingEnhancer.class, clazz);
        }

        return bytecode;
    }

}
