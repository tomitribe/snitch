/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.tomitribe.snitch.gen.BlueAfter;
import org.tomitribe.snitch.gen.BlueBefore;
import org.tomitribe.snitch.gen.GreenAfter;
import org.tomitribe.snitch.gen.GreenBefore;
import org.tomitribe.snitch.gen.OrangeAfter;
import org.tomitribe.snitch.gen.OrangeBefore;
import org.tomitribe.snitch.gen.RedAfter;
import org.tomitribe.snitch.gen.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @version $Revision$ $Date$
 */
public class GeneratorTest extends Assert {

    @Test
    public void testBlue() throws Exception {
        assertBytecode(BlueBefore.class, BlueAfter.class);
    }

    @Test
    public void testGreen() throws Exception {
        assertBytecode(GreenBefore.class, GreenAfter.class);
    }

    @Test
    public void testRed() throws Exception {
        assertBytecode(RedBefore.class, RedAfter.class);
    }

    @Test
    public void testOrange() throws Exception {
        assertBytecode(OrangeBefore.class, OrangeAfter.class);
    }

    @Test
    public void testYellow() throws Exception {
        assertBytecode(YellowBefore.class, YellowAfter.class);
    }

    @Test
    public void testPurple() throws Exception {
        assertBytecode(PurpleBefore.class, PurpleAfter.class);
    }

    @Test
    public void testPink() throws Exception {
        assertBytecode(PinkBefore.class, PinkAfter.class);
    }

    @Test
    public void testMagenta() throws Exception {
        assertBytecode(MagentaBefore.class, MagentaAfter.class);
    }

    private void assertBytecode(Class<?> beforeClass, Class<?> afterClass) throws IOException {
        final byte[] bytes = Bytecode.readClassFile(beforeClass);

        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        final ClassVisitor classAdapter = new GenericEnhancer(cw, new Filter() {
            @Override
            public String accept(Method method) {
                if (method.getMethodName().contains("<")) return null;
                return "theTag";
            }
        });

        Bytecode.read(bytes, classAdapter);

        final byte[] actualBytes = cw.toByteArray();
        final byte[] expectedBytes = Bytecode.readClassFile(afterClass);

        final String actual = asmify(actualBytes).replaceAll("Before", "");
        final String expected = asmify(expectedBytes).replaceAll("After", "");

        assertEquals(expected, actual);
    }

    private String asmify(byte[] actualBytes) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Asmifier.write(new ClassReader(actualBytes), byteArrayOutputStream);
        return new String(byteArrayOutputStream.toByteArray());
    }
}
