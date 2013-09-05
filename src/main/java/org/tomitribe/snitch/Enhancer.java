/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
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
            if (clazz != null) return clazz;
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
        if (clazz == null) return bytecode;

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
