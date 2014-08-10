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

import com.tomitribe.snitch.Asmifier;
import com.tomitribe.snitch.Method;
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

import static com.tomitribe.snitch.track.GeneratorTest.asmify;

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

    private void modifyAndCompare(Enhancer enhancer, ClassLoader loader, final Class<?> clazz, Class<?> afterClass) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException, IOException {
        final byte[] actualBytes = enhancer.enhance(clazz.getName(), Bytecode.readClassFile(clazz));
        Bytecode.defineClass(actualBytes, clazz.getName(), loader);

        final byte[] expectedBytes = Bytecode.readClassFile(afterClass);

        final String expected = asmify(expectedBytes).replaceAll(afterClass.getSimpleName(), clazz.getSimpleName());
        final String actual = asmify(actualBytes);

        assertEquals(expected, actual);
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
                return purple2(s, l, d);
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


        private void primitives(byte b, boolean z, char c, short s, int i, long l, float f, double d) {
            final long start = System.nanoTime();
            try {
                primitives1(b, z, c, s, i, l, f, d);
            } finally {
                Tracker.track("theTag", start);
            }
        }

        private void primitives(byte[] b, boolean[] z, char[] c, short[] s, int[] i, long[] l, float[] f, double[] d) {
            final long start = System.nanoTime();
            try {
                primitives1(b, z, c, s, i, l, f, d);
            } finally {
                Tracker.track("theTag", start);
            }
        }

        private void primitives1(byte b, boolean z, char c, short s, int i, long l, float f, double d) {

        }

        private void primitives1(byte[] b, boolean[] z, char[] c, short[] s, int[] i, long[] l, float[] f, double[] d) {

        }

        public void voidMethodTime0() throws IllegalStateException {
        }

        // object or primitive return type

        public byte byteMethodTime0() throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // array object or primitive return type

        public byte[] byteArrayMethodTime0() throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // void return type

        public void voidMethodTime1(byte a0) throws IllegalStateException {
        }

        // object or primitive return type

        public boolean booleanMethodTime1(byte a0) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // array object or primitive return type

        public boolean[] booleanArrayMethodTime1(byte a0) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // void return type

        public void voidMethodTime2(byte a0, boolean a1) throws IllegalStateException {
        }

        // object or primitive return type

        public char charMethodTime2(byte a0, boolean a1) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // array object or primitive return type

        public char[] charArrayMethodTime2(byte a0, boolean a1) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // void return type

        public void voidMethodTime3(byte a0, boolean a1, char a2) throws IllegalStateException {
        }

        // object or primitive return type

        public short shortMethodTime3(byte a0, boolean a1, char a2) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // array object or primitive return type

        public short[] shortArrayMethodTime3(byte a0, boolean a1, char a2) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // void return type

        public void voidMethodTime4(byte a0, boolean a1, char a2, short a3) throws IllegalStateException {
        }

        // object or primitive return type

        public int intMethodTime4(byte a0, boolean a1, char a2, short a3) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // array object or primitive return type

        public int[] intArrayMethodTime4(byte a0, boolean a1, char a2, short a3) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // void return type

        public void voidMethodTime5(byte a0, boolean a1, char a2, short a3, int a4) throws IllegalStateException {
        }

        // object or primitive return type

        public long longMethodTime5(byte a0, boolean a1, char a2, short a3, int a4) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // array object or primitive return type

        public long[] longArrayMethodTime5(byte a0, boolean a1, char a2, short a3, int a4) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // void return type

        public void voidMethodTime6(byte a0, boolean a1, char a2, short a3, int a4, long a5) throws IllegalStateException {
        }

        // object or primitive return type

        public float floatMethodTime6(byte a0, boolean a1, char a2, short a3, int a4, long a5) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // array object or primitive return type

        public float[] floatArrayMethodTime6(byte a0, boolean a1, char a2, short a3, int a4, long a5) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // void return type

        public void voidMethodTime7(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6) throws IllegalStateException {
        }

        // object or primitive return type

        public double doubleMethodTime7(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // array object or primitive return type

        public double[] doubleArrayMethodTime7(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // void return type

        public void voidMethodTime8(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7) throws IllegalStateException {
        }

        // object or primitive return type

        public java.util.Date DateMethodTime8(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // array object or primitive return type

        public java.util.Date[] DateArrayMethodTime8(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // void return type

        public void voidMethodTime9(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7, java.util.Date a8) throws IllegalStateException {
        }

        // object or primitive return type

        public java.net.URI URIMethodTime9(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7, java.util.Date a8) throws IllegalStateException {
            throw new UnsupportedOperationException();
        }

        // array object or primitive return type

        public java.net.URI[] URIArrayMethodTime9(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7, java.util.Date a8) throws IllegalStateException {
            throw new UnsupportedOperationException();
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
