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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;

/**
 * @version $Revision$ $Date$
 */
public class BlueTest extends Assert {

    @Test
    public void test() throws Exception {
        final byte[] bytes = Bytecode.readClassFile(BlueBefore.class);

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
        final byte[] expectedBytes = Bytecode.readClassFile(BlueAfter.class);

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
