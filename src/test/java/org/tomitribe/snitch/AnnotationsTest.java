/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.junit.Assert;
import org.junit.Test;

import javax.annotation.PostConstruct;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version $Revision$ $Date$
 */
public class AnnotationsTest extends Assert {

    @Test
    public void test() throws Exception {
        GeneratorTest.assertBytecode(ColorsBefore.class, ColorsAfter.class);
    }

    public static class ColorsBefore {


        @Square(size = 10)
        public void orange() {

        }

        @Square(size = 5)
        public void yellow(@Circle("perfect") String foo) {

        }
    }

    public static class ColorsAfter {

        @Square(size = 10)
        public void orange() {
            final long start = System.nanoTime();
            try {
                track$orange();
            } finally {
                Tracker.track("theTag", start);
            }
        }

        public void track$orange() {
        }


        @Square(size = 5)
        public void yellow(@Circle("perfect") String foo) {
            final long start = System.nanoTime();
            try {
                track$yellow(foo);
            } finally {
                Tracker.track("theTag", start);
            }
        }

        public void track$yellow(String foo) {
        }
    }


    @Target(value = ElementType.METHOD)
    @Retention(value = RetentionPolicy.RUNTIME)
    public static @interface Square {
        int size();
    }

    @Target(value = ElementType.PARAMETER)
    @Retention(value = RetentionPolicy.RUNTIME)
    public static @interface Circle {
        String value();
    }
}
