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
import com.tomitribe.snitch.Filter;
import com.tomitribe.snitch.Method;
import com.tomitribe.snitch.track.gen.BlueAfter;
import com.tomitribe.snitch.track.gen.BlueBefore;
import com.tomitribe.snitch.track.gen.GreenAfter;
import com.tomitribe.snitch.track.gen.GreenBefore;
import com.tomitribe.snitch.track.gen.MagentaAfter;
import com.tomitribe.snitch.track.gen.MagentaBefore;
import com.tomitribe.snitch.track.gen.OrangeAfter;
import com.tomitribe.snitch.track.gen.OrangeBefore;
import com.tomitribe.snitch.track.gen.PinkAfter;
import com.tomitribe.snitch.track.gen.PinkBefore;
import com.tomitribe.snitch.track.gen.PurpleAfter;
import com.tomitribe.snitch.track.gen.PurpleBefore;
import com.tomitribe.snitch.track.gen.RedAfter;
import com.tomitribe.snitch.track.gen.RedBefore;
import com.tomitribe.snitch.track.gen.YellowAfter;
import com.tomitribe.snitch.track.gen.YellowBefore;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

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

    @Ignore
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

    public static void assertBytecode(final Class<?> beforeClass, final Class<?> afterClass) throws Exception {
        final String expected;
        {
            final byte[] expectedBytes = Bytecode.readClassFile(afterClass);
            expected = asmify(expectedBytes).replaceAll("After", "");
        }

        final String actual;
        {
            final byte[] bytes = Bytecode.readClassFile(beforeClass);

            final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

            final ClassVisitor classAdapter = new GenericEnhancer(cw, new Filter<String>() {
                @Override
                public String accept(final Method method) {
                    if (method.getMethodName().contains("<")) return null;
                    return "theTag";
                }

                @Override
                public void end() {
                }
            });

            Bytecode.read(bytes, classAdapter);

            final byte[] actualBytes = cw.toByteArray();

            actual = asmify(actualBytes).replaceAll("Before", "");
        }

        assertEquals(expected, actual);
    }

    public static String asmify(final byte[] actualBytes) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Asmifier.write(new ClassReader(actualBytes), byteArrayOutputStream);
        return new String(byteArrayOutputStream.toByteArray());
    }
}
