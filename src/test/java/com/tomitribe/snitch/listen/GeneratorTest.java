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
import com.tomitribe.snitch.Filter;
import com.tomitribe.snitch.Method;
import com.tomitribe.snitch.listen.gen.BlueAfter;
import com.tomitribe.snitch.listen.gen.BlueBefore;
import com.tomitribe.snitch.listen.gen.BlueListener;
import com.tomitribe.snitch.track.Bytecode;
import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;

public class GeneratorTest extends Assert {


    @Test
    public void testBlue() throws Exception {
        assertBytecode(BlueBefore.class, BlueAfter.class);
    }


    public static void assertBytecode(final Class<?> beforeClass, final Class<?> afterClass) throws Exception {
        final String tag = "idea";
        Asmifier.asmify(beforeClass, "before." + tag);
        Asmifier.asmify(afterClass, "after." + tag);
        final byte[] bytes = Bytecode.readClassFile(beforeClass);

        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        final ClassVisitor classAdapter = new InsertListenerVisitor(cw, new Filter<Type>() {
            @Override
            public Type accept(final Method method) {
                if (method.getMethodName().startsWith("doIt")) {
                    return Type.getType(BlueListener.class);
                } else {
                    return null;
                }
            }

            @Override
            public void end() {
            }
        });

        Bytecode.read(bytes, classAdapter);

        final byte[] actualBytes = cw.toByteArray();
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
