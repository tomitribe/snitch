/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2023
 *
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 */
package com.tomitribe.snitch.annotate;

import com.tomitribe.snitch.Asmifier;
import com.tomitribe.snitch.annotate.gen.GreenAnnotationAfter;
import com.tomitribe.snitch.annotate.gen.GreenAnnotationBefore;
import com.tomitribe.snitch.annotate.gen.GreenClassAfter;
import com.tomitribe.snitch.annotate.gen.GreenClassBefore;
import com.tomitribe.snitch.annotate.gen.GreenEnumAfter;
import com.tomitribe.snitch.annotate.gen.GreenEnumBefore;
import com.tomitribe.snitch.annotate.gen.GreenInterfaceAfter;
import com.tomitribe.snitch.annotate.gen.GreenInterfaceBefore;
import com.tomitribe.snitch.annotate.gen.OrangeAnnotationAfter;
import com.tomitribe.snitch.annotate.gen.OrangeAnnotationBefore;
import com.tomitribe.snitch.annotate.gen.OrangeClassAfter;
import com.tomitribe.snitch.annotate.gen.OrangeClassBefore;
import com.tomitribe.snitch.annotate.gen.OrangeEnumAfter;
import com.tomitribe.snitch.annotate.gen.OrangeEnumBefore;
import com.tomitribe.snitch.annotate.gen.OrangeInterfaceAfter;
import com.tomitribe.snitch.annotate.gen.OrangeInterfaceBefore;
import com.tomitribe.snitch.track.Bytecode;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.util.function.Function;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AnnotateTypeTest {

    /**
     * Original class does not have annotations
     */
    @Test
    public void classWithNoAnnotations() throws IOException {
        final AnnotateType annotateType = AnnotateType.builder()
                .annotation(Copyright.class)
                .set("owner", "Tomitribe Corporation")
                .set("build", "9.0.76-TT.2")
                .set("licensee", "Unreliable, Inc")
                .build();

        assertEnhancement(annotateType, GreenClassBefore.class, GreenClassAfter.class);
    }

    /**
     * Original class has two annotations
     */
    @Test
    public void classWithAnnotations() throws IOException {
        final AnnotateType annotateType = AnnotateType.builder()
                .annotation(Copyright.class)
                .set("owner", "Tomitribe Corporation")
                .set("build", "9.0.76-TT.2")
                .set("licensee", "Unreliable, Inc")
                .build();

        assertEnhancement(annotateType, OrangeClassBefore.class, OrangeClassAfter.class);
    }

    /**
     * Original enum does not have annotations
     * annotationVisitor0.visit("licensee", Boolean.TRUE);
     * annotationVisitor0.visit("implementation", Type.getType("Ljava/net/URI;"));
     * annotationVisitor0.visit("max", new Long(234567890L));
     * annotationVisitor0.visit("min", new Integer(-123));
     */
    @Test
    public void enumWithNoAnnotations() throws IOException {
        final AnnotateType annotateType = AnnotateType.builder()
                .annotation(Copyright.class)
                .set("owner", "The Tribe")
                .set("build", "Apache Tomcat 9.0.76-TT.2")
                .set("licensee", "Acme, Co")
                .build();

        assertEnhancement(annotateType, GreenEnumBefore.class, GreenEnumAfter.class);
    }

    /**
     * Original enum has two annotations
     */
    @Test
    public void enumWithAnnotations() throws IOException {
        final AnnotateType annotateType = AnnotateType.builder()
                .annotation(Copyright.class)
                .set("owner", "The Tribe")
                .set("build", "Apache Tomcat 9.0.76-TT.2")
                .set("licensee", "Acme, Co")
                .build();

        assertEnhancement(annotateType, OrangeEnumBefore.class, OrangeEnumAfter.class);
    }

    /**
     * Original annotation does not have annotations
     */
    @Test
    public void annotationWithNoAnnotations() throws IOException {
        final AnnotateType annotateType = AnnotateType.builder()
                .annotation(Monitor.class)
                .set("licensee", Boolean.TRUE)
                .set("implementation", URI.class)
                .set("max", 234567890L)
                .set("min", -123)
                .build();

        assertEnhancement(annotateType, GreenAnnotationBefore.class, GreenAnnotationAfter.class);
    }

    /**
     * Original annotation has two annotations
     */
    @Test
    public void annotationWithAnnotations() throws IOException {
        final AnnotateType annotateType = AnnotateType.builder()
                .annotation(Monitor.class)
                .set("licensee", Boolean.TRUE)
                .set("implementation", URI.class)
                .set("max", 234567890L)
                .set("min", -123)
                .build();

        assertEnhancement(annotateType, OrangeAnnotationBefore.class, OrangeAnnotationAfter.class);
    }

    @Test
    public void interfaceWithNoAnnotations() throws IOException {
        final AnnotateType annotateType = AnnotateType.builder()
                .annotation(Config.class)
                .visible(true)
                .set("afloat", 4.5F)
                .set("adouble", 4.6D)
                .set("ashort", (short) 123)
                .set("achar", (char) 88)
                .build();

        assertEnhancement(annotateType, GreenInterfaceBefore.class, GreenInterfaceAfter.class);
    }

    /**
     * Original interface has two annotations
     */
    @Test
    public void interfaceWithAnnotations() throws IOException {
        final AnnotateType annotateType = AnnotateType.builder()
                .annotation(Config.class)
                .visible(true)
                .set("afloat", 4.5F)
                .set("adouble", 4.6D)
                .set("ashort", (short) 123)
                .set("achar", (char) 88)
                .build();

        assertEnhancement(annotateType, OrangeInterfaceBefore.class, OrangeInterfaceAfter.class);
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