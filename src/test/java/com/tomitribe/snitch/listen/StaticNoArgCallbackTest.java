/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2023
 *
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 */
package com.tomitribe.snitch.listen;

import com.tomitribe.snitch.listen.gen.Interceptor;
import com.tomitribe.snitch.listen.gen.ProcessorAfter;
import com.tomitribe.snitch.listen.gen.ProcessorBefore;
import com.tomitribe.snitch.track.Bytecode;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public class StaticNoArgCallbackTest {

    @Test
    public void testWeaveStaticNoArgMethodIntoStartOfAnInstanceMethod() throws Exception {
        final Function<byte[], byte[]> transformer = StaticNoArgCallback.builder()
                .find("void com.tomitribe.snitch.listen.gen.ProcessorBefore.doWork()")
                .insert("void com.tomitribe.snitch.listen.gen.Interceptor.intercept()")
                .check()
                .build();

        StaticCallTest.assertEnhancement(transformer, ProcessorBefore.class, ProcessorAfter.class);
    }

    @Test
    public void testShouldFailToWeaveAsMethodNotFound() throws Exception {
        try {

            final Function<byte[], byte[]> transformer = StaticNoArgCallback.builder()
                    .find("void com.tomitribe.snitch.listen.gen.ProcessorBefore.foo()")
                    .insert("void com.tomitribe.snitch.listen.gen.Interceptor.intercept()")
                    .check()
                    .build();

            final byte[] bytes = Bytecode.readClassFile(ProcessorBefore.class);
            final byte[] woven = transformer.apply(bytes);

            Assert.fail("Expected exception not thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("com.tomitribe.snitch.listen.gen.ProcessorBefore.foo() method was not found", e.getMessage());
        }
    }

    @Test
    public void testShouldFailToWeaveAMethodWithArguments() throws Exception {
        try {

            final Function<byte[], byte[]> transformer = StaticNoArgCallback.builder()
                    .find("void com.tomitribe.snitch.listen.gen.ProcessorBefore.foo()")
                    .insert("void com.tomitribe.snitch.listen.gen.Interceptor.log(java.lang.String)")
                    .check()
                    .build();

            final byte[] bytes = Bytecode.readClassFile(ProcessorBefore.class);
            final byte[] woven = transformer.apply(bytes);

            Assert.fail("Expected exception not thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Insert method must have no arguments.  Found: Ljava/lang/String;", e.getMessage());
        }
    }

    @Test
    public void testShouldFailToWeaveANonStaticMethodWithoutArguments() throws Exception {
        try {
            final Function<byte[], byte[]> transformer = StaticNoArgCallback.builder()
                    .find("void com.tomitribe.snitch.listen.gen.ProcessorBefore.foo()")
                    .insert(Interceptor.class.getDeclaredMethod("doWork"))
                    .check()
                    .build();

            final byte[] bytes = Bytecode.readClassFile(ProcessorBefore.class);
            final byte[] woven = transformer.apply(bytes);

            Assert.fail("Expected exception not thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Insert method must be static.", e.getMessage());
        }
    }
    @Test
    public void testShouldFailToWeaveANonStaticMethodWithArguments() throws Exception {
        try {
            final Function<byte[], byte[]> transformer = StaticNoArgCallback.builder()
                    .find("void com.tomitribe.snitch.listen.gen.ProcessorBefore.foo()")
                    .insert(Interceptor.class.getDeclaredMethod("doWork", String.class))
                    .check()
                    .build();

            final byte[] bytes = Bytecode.readClassFile(ProcessorBefore.class);
            final byte[] woven = transformer.apply(bytes);

            Assert.fail("Expected exception not thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Insert method must be static.", e.getMessage());
        }
    }


}
