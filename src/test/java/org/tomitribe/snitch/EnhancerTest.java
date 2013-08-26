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
public class EnhancerTest extends Assert {

    @Test
    public void testConfiguration() throws Exception {
        final Properties properties = new Properties();

        properties.put("@Orange", Colors.class.getMethod("orange", String.class, Long.class, Date.class).toString());
        properties.put("Purple", Colors.class.getMethod("purple", String[].class, long[][].class, Date[][][].class).toString());
        properties.put("Square", Shapes.class.getMethod("square", int.class, int.class).toString());

        final Enhancer enhancer = Enhancer.create(properties);

        final Clazz colors = enhancer.getClazz(Colors.class.getName());
        final Clazz shapes = enhancer.getClazz(Shapes.class.getName());

        assertNotNull(colors);
        assertNotNull(shapes);


        final Method orange = new Method(Colors.class.getMethod("orange", String.class, Long.class, Date.class));
        final Method purple = new Method(Colors.class.getMethod("purple", String[].class, long[][].class, Date[][][].class));
        final Method yellow = new Method(Colors.class.getMethod("yellow"));
        final Method square = new Method(Shapes.class.getMethod("square", int.class, int.class));
        final Method circle = new Method(Shapes.class.getMethod("circle", int.class));


        assertNull(colors.track(purple));
        assertNull(colors.track(yellow));
        assertNull(colors.track(square));
        assertNull(colors.track(circle));
        assertNotNull(colors.track(orange));

        assertNotNull(colors.time(purple));
        assertNull(colors.time(yellow));
        assertNull(colors.time(square));
        assertNull(colors.time(circle));
        assertNull(colors.time(orange));

        assertNotNull(colors.track(orange));
        assertEquals("Orange", colors.track(orange).getName());

        assertNotNull(colors.time(purple));
        assertEquals("Purple", colors.time(purple).getName());

        assertNull(colors.track(yellow));
        assertNull(colors.time(yellow));

        assertNotNull(shapes.time(square));
        assertEquals("Square", shapes.time(square).getName());

    }

    @Test
    public void testEnhance() throws Exception {
        final Properties properties = new Properties();

        properties.put("@Orange", Colors.class.getMethod("orange", String.class, Long.class, Date.class).toString());
        properties.put("Purple", Colors.class.getMethod("purple", String[].class, long[][].class, Date[][][].class).toString());
        properties.put("Square", Shapes.class.getMethod("square", int.class, int.class).toString());
        properties.put("Rectangle", Shapes.class.getMethod("square", int.class, int.class).toString().replace("square", "rectangle"));

        final Enhancer enhancer = Enhancer.create(properties);

        final URLClassLoader loader = new URLClassLoader(new URL[]{});

        modify(enhancer, loader, Colors.class);
        modify(enhancer, loader, Shapes.class);

        final Class<?> clazz = loader.loadClass(Colors.class.getName());
        final Object colors = clazz.newInstance();

        final java.lang.reflect.Method orange = clazz.getMethod("orange", String.class, Long.class, Date.class);
        orange.invoke(colors, null, null, null);
    }

    private void modify(Enhancer enhancer, ClassLoader loader, final Class<?> clazz) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, IOException {
        final byte[] enhance = enhancer.enhance(clazz.getName(), Bytecode.readClassFile(clazz));
        Bytecode.defineClass(enhance, clazz.getName(), loader);
    }


    public static class Colors {

        private final Shapes shapes = new Shapes();

        public Boolean orange(String s, Long l, Date d) throws IOException {
            green(0, false);
            purple(null, null, null);
            red(null, null, null);
            blue(3, true);
            white(null);

            shapes.circle(0);
            shapes.triangle(0);
            shapes.square(0, 0);
            return null;
        }

        public void green(int s, boolean b) throws IOException {
        }

        public void red(String[] s, Long[][] l, Date[][][] d) throws IOException {
        }

        public int blue(int s, boolean b) throws IOException {
            return -1;
        }

        public int[][] purple(String[] s, long[][] l, Date[][][] d) throws IOException {

            return null;
        }

        public <T extends Map> T white(List<T> t) throws IOException {
            return null;
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
