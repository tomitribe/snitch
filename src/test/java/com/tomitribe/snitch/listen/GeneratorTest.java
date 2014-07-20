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

import com.tomitribe.snitch.listen.gen.BlueAfter;
import com.tomitribe.snitch.listen.gen.BlueBefore;
import com.tomitribe.snitch.track.Asmifier;
import com.tomitribe.snitch.track.Bytecode;
import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GeneratorTest extends Assert {


    @Test
    public void testBlue() throws Exception {
        assertBytecode(BlueBefore.class, BlueAfter.class);
    }


    public static void assertBytecode(Class<?> beforeClass, Class<?> afterClass) throws Exception {
        final String tag = "idea";
        Asmifier.asmify(beforeClass, "before." + tag);
        Asmifier.asmify(afterClass, "after." + tag);
        final byte[] bytes = Bytecode.readClassFile(beforeClass);

        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        final ClassVisitor classAdapter = new InsertListenerEnhancer(cw);

        Bytecode.read(bytes, classAdapter);

        final byte[] actualBytes = cw.toByteArray();
        final byte[] expectedBytes = Bytecode.readClassFile(afterClass);

        final String expected;
        final String actual;
        try {
            expected = asmify(expectedBytes).replaceAll("After", "");
            actual = asmify(actualBytes).replaceAll("Before", "");
        } catch (Exception e) {
            e.printStackTrace();
            assertArrayEquals(expectedBytes, actualBytes);
            throw e;
        }


        assertEquals(expected, actual);
    }

    public static String asmify(byte[] actualBytes) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Asmifier.write(new ClassReader(actualBytes), byteArrayOutputStream);
        return new String(byteArrayOutputStream.toByteArray());
    }

}
