/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.junit.Assert;
import org.junit.Test;
import org.tomitribe.snitch.util.Join;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.tomitribe.snitch.util.Join.join;

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
        Asmifier.asmify(Colors.class, "unmodified");

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
        Asmifier.asmify(clazz.getName(), enhance, "modified");
        Bytecode.defineClass(enhance, clazz.getName(), loader);
    }

    @Test
    public void testGenerate() throws Exception {
        final PrintStream out = System.out;
        out.println("public class Blue {");

        final int max = 12;
        final List<String> args = new ArrayList<String>();

        for (int i = 0; i < max; i++) {

            out.printf("    public void voidMethod%s(%s) throws IllegalStateException {\n" +
//                    "        Tracker.start();\n" +
                    "        final long start = System.nanoTime();\n" +
                    "        try {\n" +
                    "            track$voidMethod%s(%s);\n" +
                    "        } finally {\n" +
                    "            Tracker.track(\"theTag\", start);\n" +
//                    "            Tracker.stop();\n" +
                    "        }\n" +
                    "    }%n", i, join(",", new ParamCallback(), args), i, join(",", new ArgCallback(), args));
            out.printf("    public boolean booleanMethod%s(%s) throws IllegalStateException {\n" +
//                    "            Tracker.start();\n" +
                    "        final long start = System.nanoTime();\n" +
                    "        try {\n" +
                    "            return track$booleanMethod%s(%s);\n" +
                    "        } finally {\n" +
                    "            Tracker.track(\"theTag\", start);\n" +
//                    "            Tracker.stop();\n" +
                    "        }\n" +
                    "    }%n", i, join(",", new ParamCallback(), args), i, join(",", new ArgCallback(), args));

            out.printf("    public boolean track$booleanMethod%s(%s) {return false;}%n", i, join(",", new ParamCallback(), args));
            out.printf("    public void track$voidMethod%s(%s) {}%n", i, join(",", new ParamCallback(), args));
            out.printf("    public static class Arg%s {}%n", i);

            args.add("Arg" + i);
        }

        out.println("}");

        Asmifier.asmify(Blue.class, "modified");
    }

    public static class Colors {

        private final Shapes shapes = new Shapes();

        public Boolean orange(String s, Long l, Date d) throws IOException {
//            Tracker.start();
//            final long start = System.nanoTime();
//            try {
//                return track$orange2(s, l, d);
//            } finally {
//                Tracker.track("tag", start);
//                Tracker.stop();
//            }
//        }
//
//        private Boolean track$orange2(String s, Long l, Date d) throws IOException {
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
            final long start = System.nanoTime();
            try {
                return purple2(s,l,d);
            } finally {
                Tracker.track("theTag", start);
            }
        }
        public int[][] purple2(String[] s, long[][] l, Date[][][] d) throws IOException {

            return null;
        }

        public <T extends Map> T white(List<T> t) throws IOException {
            return null;
        }

        public void yellow() throws IOException {
        }


        private void primitives(byte b, boolean z, char c, short s, int i, long l, float f, double d){
            final long start = System.nanoTime();
            try {
                primitives1(b, z, c, s, i, l, f, d);
            } finally {
                Tracker.track("theTag", start);
            }
        }

        private void primitives(byte[] b, boolean[] z, char[] c, short[] s, int[] i, long[] l, float[] f, double[] d){
            final long start = System.nanoTime();
            try {
                primitives1(b, z, c, s, i, l, f, d);
            } finally {
                Tracker.track("theTag", start);
            }
        }

        private void primitives1(byte b, boolean z, char c, short s, int i, long l, float f, double d){

        }
        private void primitives1(byte[] b, boolean[] z, char[] c, short[] s, int[] i, long[] l, float[] f, double[] d){

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

    private static class ArgCallback implements Join.NameCallback<String> {
        int i;

        @Override
        public String getName(String object) {
            return "a" + (i++);
        }
    }

    private static class ParamCallback implements Join.NameCallback<String> {
        int i;

        @Override
        public String getName(String object) {
            return object + " a" + (i++);
        }
    }
}
