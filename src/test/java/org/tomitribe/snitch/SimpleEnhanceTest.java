/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @version $Revision$ $Date$
 */
public class SimpleEnhanceTest extends Assert {

    @Test
    public void testEnhance() throws Exception {

        final Properties properties = new Properties();

        properties.put("@Orange", Colors.class.getMethod("orange", String.class, Long.class, Date.class, Object.class).toString());
        properties.put("GREEEEEEN", Colors.class.getMethod("green", int.class, boolean.class).toString());
        properties.put("Square", Shapes.class.getMethod("square", int.class, int.class).toString());

        // This does not exist
        properties.put("Rectangle", Shapes.class.getMethod("square", int.class, int.class).toString().replace("square", "rectangle"));

        final Enhancer enhancer = Enhancer.create(properties);

        final URLClassLoader loader = new URLClassLoader(new URL[]{});

        modify(enhancer, loader, Colors.class);
        modify(enhancer, loader, Shapes.class);

        final Class<?> clazz = loader.loadClass(Colors.class.getName());
        final Object colors = clazz.newInstance();

        final java.lang.reflect.Method orange = clazz.getMethod("orange", String.class, Long.class, Date.class, Object.class);
        orange.invoke(colors, null, null, null, null);
    }

    private void modify(Enhancer enhancer, ClassLoader loader, final Class<?> clazz) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, IOException {
        final byte[] enhance = enhancer.enhance(clazz.getName(), Bytecode.readClassFile(clazz));
        Asmifier.asmify(clazz.getName(), enhance, "modified");
        Bytecode.defineClass(enhance, clazz.getName(), loader);
    }



    public static class Colors {

        private final Shapes shapes = new Shapes();

        public Boolean orange(String s, Long l, Date d, Object foo) throws IOException {
            green(0, false);
            green(0, false);
            green(0, false);
            green(0, false);
            green(0, false);
            red(null, null, null);
            red(null, null, null);
            red(null, null, null);
            blue(3, true);
            yellow();

            shapes.circle(0);
            shapes.triangle(0);
            shapes.square(0, 0);
            return null;
        }

        public void green(int s, boolean b) throws IOException {
        }

        public void red(String s, Long l, Date d) throws IOException {
        }

        public int blue(int s, boolean b) throws IOException {
            return -1;
        }

        public void yellow() throws IOException {
        }
    }

    public static class Shapes {
        public void circle(int i) {

        }

        public void square(int i, int ii) {

        }

        public void triangle(int i) {

        }
    }

}
