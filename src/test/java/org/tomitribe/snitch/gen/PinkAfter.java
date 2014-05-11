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

package org.tomitribe.snitch.gen;

import org.tomitribe.snitch.Tracker;

public class PinkAfter {
    // void return type

    protected void voidMethodTime0() throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime0();
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected void track$voidMethodTime0() throws IllegalStateException {
    }

    // object or primitive return type

    protected byte byteMethodTime0() throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$byteMethodTime0();
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected byte track$byteMethodTime0()  throws IllegalStateException{
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    protected byte[] byteArrayMethodTime0() throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$byteArrayMethodTime0();
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected byte[] track$byteArrayMethodTime0() throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    protected void voidMethodTime1(byte a0) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime1(a0);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected void track$voidMethodTime1(byte a0) throws IllegalStateException {
    }

    // object or primitive return type

    protected boolean booleanMethodTime1(byte a0) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$booleanMethodTime1(a0);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected boolean track$booleanMethodTime1(byte a0)  throws IllegalStateException{
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    protected boolean[] booleanArrayMethodTime1(byte a0) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$booleanArrayMethodTime1(a0);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected boolean[] track$booleanArrayMethodTime1(byte a0) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    protected void voidMethodTime2(byte a0, boolean a1) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime2(a0, a1);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected void track$voidMethodTime2(byte a0, boolean a1) throws IllegalStateException {
    }

    // object or primitive return type

    protected char charMethodTime2(byte a0, boolean a1) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$charMethodTime2(a0, a1);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected char track$charMethodTime2(byte a0, boolean a1)  throws IllegalStateException{
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    protected char[] charArrayMethodTime2(byte a0, boolean a1) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$charArrayMethodTime2(a0, a1);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected char[] track$charArrayMethodTime2(byte a0, boolean a1) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    protected void voidMethodTime3(byte a0, boolean a1, char a2) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime3(a0, a1, a2);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected void track$voidMethodTime3(byte a0, boolean a1, char a2) throws IllegalStateException {
    }

    // object or primitive return type

    protected short shortMethodTime3(byte a0, boolean a1, char a2) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$shortMethodTime3(a0, a1, a2);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected short track$shortMethodTime3(byte a0, boolean a1, char a2)  throws IllegalStateException{
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    protected short[] shortArrayMethodTime3(byte a0, boolean a1, char a2) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$shortArrayMethodTime3(a0, a1, a2);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected short[] track$shortArrayMethodTime3(byte a0, boolean a1, char a2) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    protected void voidMethodTime4(byte a0, boolean a1, char a2, short a3) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime4(a0, a1, a2, a3);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected void track$voidMethodTime4(byte a0, boolean a1, char a2, short a3) throws IllegalStateException {
    }

    // object or primitive return type

    protected int intMethodTime4(byte a0, boolean a1, char a2, short a3) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$intMethodTime4(a0, a1, a2, a3);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected int track$intMethodTime4(byte a0, boolean a1, char a2, short a3)  throws IllegalStateException{
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    protected int[] intArrayMethodTime4(byte a0, boolean a1, char a2, short a3) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$intArrayMethodTime4(a0, a1, a2, a3);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected int[] track$intArrayMethodTime4(byte a0, boolean a1, char a2, short a3) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    protected void voidMethodTime5(byte a0, boolean a1, char a2, short a3, int a4) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime5(a0, a1, a2, a3, a4);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected void track$voidMethodTime5(byte a0, boolean a1, char a2, short a3, int a4) throws IllegalStateException {
    }

    // object or primitive return type

    protected long longMethodTime5(byte a0, boolean a1, char a2, short a3, int a4) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$longMethodTime5(a0, a1, a2, a3, a4);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected long track$longMethodTime5(byte a0, boolean a1, char a2, short a3, int a4)  throws IllegalStateException{
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    protected long[] longArrayMethodTime5(byte a0, boolean a1, char a2, short a3, int a4) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$longArrayMethodTime5(a0, a1, a2, a3, a4);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected long[] track$longArrayMethodTime5(byte a0, boolean a1, char a2, short a3, int a4) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    protected void voidMethodTime6(byte a0, boolean a1, char a2, short a3, int a4, long a5) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime6(a0, a1, a2, a3, a4, a5);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected void track$voidMethodTime6(byte a0, boolean a1, char a2, short a3, int a4, long a5) throws IllegalStateException {
    }

    // object or primitive return type

    protected float floatMethodTime6(byte a0, boolean a1, char a2, short a3, int a4, long a5) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$floatMethodTime6(a0, a1, a2, a3, a4, a5);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected float track$floatMethodTime6(byte a0, boolean a1, char a2, short a3, int a4, long a5)  throws IllegalStateException{
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    protected float[] floatArrayMethodTime6(byte a0, boolean a1, char a2, short a3, int a4, long a5) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$floatArrayMethodTime6(a0, a1, a2, a3, a4, a5);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected float[] track$floatArrayMethodTime6(byte a0, boolean a1, char a2, short a3, int a4, long a5) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    protected void voidMethodTime7(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime7(a0, a1, a2, a3, a4, a5, a6);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected void track$voidMethodTime7(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6) throws IllegalStateException {
    }

    // object or primitive return type

    protected double doubleMethodTime7(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$doubleMethodTime7(a0, a1, a2, a3, a4, a5, a6);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected double track$doubleMethodTime7(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6)  throws IllegalStateException{
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    protected double[] doubleArrayMethodTime7(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$doubleArrayMethodTime7(a0, a1, a2, a3, a4, a5, a6);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected double[] track$doubleArrayMethodTime7(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    protected void voidMethodTime8(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime8(a0, a1, a2, a3, a4, a5, a6, a7);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected void track$voidMethodTime8(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7) throws IllegalStateException {
    }

    // object or primitive return type

    protected java.util.Date DateMethodTime8(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$DateMethodTime8(a0, a1, a2, a3, a4, a5, a6, a7);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected java.util.Date track$DateMethodTime8(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7)  throws IllegalStateException{
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    protected java.util.Date[] DateArrayMethodTime8(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$DateArrayMethodTime8(a0, a1, a2, a3, a4, a5, a6, a7);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected java.util.Date[] track$DateArrayMethodTime8(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    // void return type

    protected void voidMethodTime9(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7, java.util.Date a8) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime9(a0, a1, a2, a3, a4, a5, a6, a7, a8);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected void track$voidMethodTime9(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7, java.util.Date a8) throws IllegalStateException {
    }

    // object or primitive return type

    protected java.net.URI URIMethodTime9(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7, java.util.Date a8) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$URIMethodTime9(a0, a1, a2, a3, a4, a5, a6, a7, a8);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected java.net.URI track$URIMethodTime9(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7, java.util.Date a8)  throws IllegalStateException{
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    protected java.net.URI[] URIArrayMethodTime9(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7, java.util.Date a8) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$URIArrayMethodTime9(a0, a1, a2, a3, a4, a5, a6, a7, a8);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    protected java.net.URI[] track$URIArrayMethodTime9(byte a0, boolean a1, char a2, short a3, int a4, long a5, float a6, double a7, java.util.Date a8) throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

}
