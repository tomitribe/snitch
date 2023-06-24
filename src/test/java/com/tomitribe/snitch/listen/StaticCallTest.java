/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2014
 *
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 */
package com.tomitribe.snitch.listen;

import com.tomitribe.snitch.Asmifier;
import com.tomitribe.snitch.Method;
import com.tomitribe.snitch.listen.gen.Circle;
import com.tomitribe.snitch.listen.gen.OrangeAfter;
import com.tomitribe.snitch.listen.gen.OrangeBefore;
import com.tomitribe.snitch.track.Bytecode;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.function.Function;

public class StaticCallTest extends Assert {

    @Test
    public void test() throws Exception {
        final Function<byte[], byte[]> enhancer = StaticNoArgCallback.builder()
                .find(Method.fromDescriptor("init", "()V", OrangeBefore.class.getName()))
                .insert(Circle.class, "draw")
                .build();

        assertEnhancement(enhancer, OrangeBefore.class, OrangeAfter.class);
    }

    @Test
    public void test2() throws Exception {
        final String string = OrangeBefore.class.getMethod("init").toString();
        final Function<byte[], byte[]> enhancer = StaticNoArgCallback.builder()
                .find(Method.fromToString(string))
                .insert(Circle.class, "draw")
                .build();

        assertEnhancement(enhancer, OrangeBefore.class, OrangeAfter.class);
    }

    @Test
    public void methodToString() throws Exception {
        final Function<byte[], byte[]> enhancer = StaticNoArgCallback.builder()
                .find("void com.tomitribe.snitch.listen.gen.OrangeBefore.init()")
                .insert(Circle.class.getMethod("draw").toString())
                .build();

        assertEnhancement(enhancer, OrangeBefore.class, OrangeAfter.class);
    }

    @Test
    public void methods() throws Exception {
        final Function<byte[], byte[]> enhancer = StaticNoArgCallback.builder()
                .find(OrangeBefore.class.getMethod("init"))
                .insert(Circle.class.getMethod("draw"))
                .build();

        assertEnhancement(enhancer, OrangeBefore.class, OrangeAfter.class);
    }

    private static void assertEnhancement(final Function<byte[], byte[]> enhancer, final Class<?> beforeClass, final Class<?> afterClass) throws IOException {
        final byte[] bytes = Bytecode.readClassFile(beforeClass);
        final byte[] actualBytes = enhancer.apply(bytes);
        final byte[] expectedBytes = Bytecode.readClassFile(afterClass);

        final String expected;
        final String actual;
        try {
            expected = Asmifier.asmify(expectedBytes).replaceAll("After", "");
            actual = Asmifier.asmify(actualBytes).replaceAll("Before", "");
        } catch (final Exception e) {
            e.printStackTrace();
            assertArrayEquals(expectedBytes, actualBytes);
            throw e;
        }


        assertEquals(expected, actual);
    }
}
