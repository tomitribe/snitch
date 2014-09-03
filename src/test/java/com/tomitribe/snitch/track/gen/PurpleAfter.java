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

package com.tomitribe.snitch.track.gen;

import com.tomitribe.snitch.track.Tracker;

public class PurpleAfter {
    // void return type

    private void voidMethodTime0() throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime0();
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private void track$voidMethodTime0() throws IllegalStateException {
    }

    // object or primitive return type

    private byte byteMethodTime0() throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$byteMethodTime0();
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private byte track$byteMethodTime0() throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    private byte[] byteArrayMethodTime0() throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$byteArrayMethodTime0();
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private byte[] track$byteArrayMethodTime0() throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    private void voidMethodTime1(final byte a0) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime1(a0);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private void track$voidMethodTime1(final byte a0) throws IllegalStateException {
    }

    // object or primitive return type

    private boolean booleanMethodTime1(final byte a0) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$booleanMethodTime1(a0);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private boolean track$booleanMethodTime1(final byte a0) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    private boolean[] booleanArrayMethodTime1(final byte a0) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$booleanArrayMethodTime1(a0);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private boolean[] track$booleanArrayMethodTime1(final byte a0) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    private void voidMethodTime2(final byte a0, final boolean a1) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime2(a0, a1);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private void track$voidMethodTime2(final byte a0, final boolean a1) throws IllegalStateException {
    }

    // object or primitive return type

    private char charMethodTime2(final byte a0, final boolean a1) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$charMethodTime2(a0, a1);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private char track$charMethodTime2(final byte a0, final boolean a1) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    private char[] charArrayMethodTime2(final byte a0, final boolean a1) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$charArrayMethodTime2(a0, a1);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private char[] track$charArrayMethodTime2(final byte a0, final boolean a1) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    private void voidMethodTime3(final byte a0, final boolean a1, final char a2) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime3(a0, a1, a2);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private void track$voidMethodTime3(final byte a0, final boolean a1, final char a2) throws IllegalStateException {
    }

    // object or primitive return type

    private short shortMethodTime3(final byte a0, final boolean a1, final char a2) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$shortMethodTime3(a0, a1, a2);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private short track$shortMethodTime3(final byte a0, final boolean a1, final char a2) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    private short[] shortArrayMethodTime3(final byte a0, final boolean a1, final char a2) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$shortArrayMethodTime3(a0, a1, a2);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private short[] track$shortArrayMethodTime3(final byte a0, final boolean a1, final char a2) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    private void voidMethodTime4(final byte a0, final boolean a1, final char a2, final short a3) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime4(a0, a1, a2, a3);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private void track$voidMethodTime4(final byte a0, final boolean a1, final char a2, final short a3) throws IllegalStateException {
    }

    // object or primitive return type

    private int intMethodTime4(final byte a0, final boolean a1, final char a2, final short a3) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$intMethodTime4(a0, a1, a2, a3);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private int track$intMethodTime4(final byte a0, final boolean a1, final char a2, final short a3) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    private int[] intArrayMethodTime4(final byte a0, final boolean a1, final char a2, final short a3) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$intArrayMethodTime4(a0, a1, a2, a3);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private int[] track$intArrayMethodTime4(final byte a0, final boolean a1, final char a2, final short a3) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    private void voidMethodTime5(final byte a0, final boolean a1, final char a2, final short a3, final int a4) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime5(a0, a1, a2, a3, a4);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private void track$voidMethodTime5(final byte a0, final boolean a1, final char a2, final short a3, final int a4) throws IllegalStateException {
    }

    // object or primitive return type

    private long longMethodTime5(final byte a0, final boolean a1, final char a2, final short a3, final int a4) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$longMethodTime5(a0, a1, a2, a3, a4);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private long track$longMethodTime5(final byte a0, final boolean a1, final char a2, final short a3, final int a4) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    private long[] longArrayMethodTime5(final byte a0, final boolean a1, final char a2, final short a3, final int a4) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$longArrayMethodTime5(a0, a1, a2, a3, a4);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private long[] track$longArrayMethodTime5(final byte a0, final boolean a1, final char a2, final short a3, final int a4) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    private void voidMethodTime6(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime6(a0, a1, a2, a3, a4, a5);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private void track$voidMethodTime6(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5) throws IllegalStateException {
    }

    // object or primitive return type

    private float floatMethodTime6(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$floatMethodTime6(a0, a1, a2, a3, a4, a5);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private float track$floatMethodTime6(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    private float[] floatArrayMethodTime6(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$floatArrayMethodTime6(a0, a1, a2, a3, a4, a5);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private float[] track$floatArrayMethodTime6(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    private void voidMethodTime7(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime7(a0, a1, a2, a3, a4, a5, a6);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private void track$voidMethodTime7(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6) throws IllegalStateException {
    }

    // object or primitive return type

    private double doubleMethodTime7(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$doubleMethodTime7(a0, a1, a2, a3, a4, a5, a6);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private double track$doubleMethodTime7(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    private double[] doubleArrayMethodTime7(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$doubleArrayMethodTime7(a0, a1, a2, a3, a4, a5, a6);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private double[] track$doubleArrayMethodTime7(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    private void voidMethodTime8(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6, final double a7) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime8(a0, a1, a2, a3, a4, a5, a6, a7);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private void track$voidMethodTime8(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6, final double a7) throws IllegalStateException {
    }

    // object or primitive return type

    private java.util.Date DateMethodTime8(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6, final double a7) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$DateMethodTime8(a0, a1, a2, a3, a4, a5, a6, a7);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private java.util.Date track$DateMethodTime8(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6, final double a7) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    private java.util.Date[] DateArrayMethodTime8(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6, final double a7) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$DateArrayMethodTime8(a0, a1, a2, a3, a4, a5, a6, a7);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private java.util.Date[] track$DateArrayMethodTime8(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6, final double a7) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    private void voidMethodTime9(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6, final double a7, final java.util.Date a8) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime9(a0, a1, a2, a3, a4, a5, a6, a7, a8);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private void track$voidMethodTime9(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6, final double a7, final java.util.Date a8) throws IllegalStateException {
    }

    // object or primitive return type

    private java.net.URI URIMethodTime9(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6, final double a7, final java.util.Date a8) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$URIMethodTime9(a0, a1, a2, a3, a4, a5, a6, a7, a8);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private java.net.URI track$URIMethodTime9(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6, final double a7, final java.util.Date a8) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    private java.net.URI[] URIArrayMethodTime9(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6, final double a7, final java.util.Date a8) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$URIArrayMethodTime9(a0, a1, a2, a3, a4, a5, a6, a7, a8);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    private java.net.URI[] track$URIArrayMethodTime9(final byte a0, final boolean a1, final char a2, final short a3, final int a4, final long a5, final float a6, final double a7, final java.util.Date a8) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

}
