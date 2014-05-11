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
