/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.Type;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @version $Revision$ $Date$
 */
public class MethodTest extends Assert {

    @Test
    public void test() throws Exception {

        final Method unexpected = new Method(MethodTest.class.getMethod("test"));

        for (java.lang.reflect.Method method : Colors.class.getMethods()) {
            final Method a = new Method(method);

            final Method b = Method.fromDescriptor(method.getName(), Type.getMethodDescriptor(method), "");
            final String toStringValue = method.toString();
            System.out.println(toStringValue);
            final Method c = Method.fromToString(toStringValue);
            final Method d = Method.fromToString(c.toString());

            assertEquals(a, b);
            assertEquals(a, c);
            assertEquals(a, d);
            assertEquals(b, c);
            assertEquals(d, c);

            // Negative tests
            assertNotEquals(unexpected, a);
            assertNotEquals(unexpected, b);
            assertNotEquals(unexpected, c);
        }
    }

    @Test
    public void testImperfectInput() throws Exception {
        final Method orange = new Method(Colors.class.getMethod("orange", String.class, Long.class, Date.class));

        assertEquals(orange, Method.fromToString("public abstract java.lang.Boolean org.tomitribe.snitch.MethodTest$Colors.orange(java.lang.String,java.lang.Long,java.util.Date) throws java.io.IOException"));
        assertEquals(orange, Method.fromToString("abstract java.lang.Boolean org.tomitribe.snitch.MethodTest$Colors.orange(java.lang.String,java.lang.Long,java.util.Date) throws java.io.IOException"));
        assertEquals(orange, Method.fromToString(" org.tomitribe.snitch.MethodTest$Colors.orange(java.lang.String,java.lang.Long,java.util.Date) throws java.io.IOException"));
        assertEquals(orange, Method.fromToString("public abstract java.lang.Boolean org.tomitribe.snitch.MethodTest$Colors.orange(java.lang.String,java.lang.Long,java.util.Date) throws "));
        assertEquals(orange, Method.fromToString("org.tomitribe.snitch.MethodTest$Colors.orange(java.lang.String,java.lang.Long,java.util.Date)"));
        assertEquals(orange, Method.fromToString(" org.tomitribe.snitch.MethodTest$Colors.orange( java.lang.String , java.lang.Long , java.util.Date ) "));

        final Method yellow = new Method(Colors.class.getMethod("yellow"));
        assertEquals(yellow, Method.fromToString("public abstract void org.tomitribe.snitch.MethodTest$Colors.yellow() throws java.io.IOException"));

    }


    public static interface Colors {

        public Boolean orange(String s, Long l, Date d) throws IOException;

        public void green(int s, boolean b) throws IOException;

        public void red(String[] s, Long[][] l, Date[][][] d) throws IOException;

        public int blue(int s, boolean b) throws IOException;

        public int[][] purple(String[] s, long[][] l, Date[][][] d) throws IOException;

        public <T extends Map> T white(List<T> t) throws IOException;

        public void yellow() throws IOException;

    }
}
