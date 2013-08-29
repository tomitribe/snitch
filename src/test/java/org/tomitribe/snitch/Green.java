package org.tomitribe.snitch;

import org.tomitribe.snitch.Tracker;

public class Green {
    // void return type

    public void voidMethodTime0() throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime0();
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public synchronized void track$voidMethodTime0() {
    }

    // object or primitive return type

    public byte byteMethodTime0() throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$byteMethodTime0();
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public byte track$byteMethodTime0() {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    public byte[] byteArrayMethodTime0() throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$byteArrayMethodTime0();
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public byte[] track$byteArrayMethodTime0() {
        throw new UnsupportedOperationException();
    }

    // void return type

    public void voidMethodTime1(byte a0) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime1(a0);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public void track$voidMethodTime1(byte a0) {
    }

    // object or primitive return type

    public boolean booleanMethodTime1(byte a0) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$booleanMethodTime1(a0);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public boolean track$booleanMethodTime1(byte a0) {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    public boolean[] booleanArrayMethodTime1(byte a0) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$booleanArrayMethodTime1(a0);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public boolean[] track$booleanArrayMethodTime1(byte a0) {
        throw new UnsupportedOperationException();
    }

    // void return type

    public void voidMethodTime2(byte a0,boolean a1) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime2(a0,a1);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public void track$voidMethodTime2(byte a0,boolean a1) {
    }

    // object or primitive return type

    public char charMethodTime2(byte a0,boolean a1) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$charMethodTime2(a0,a1);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public char track$charMethodTime2(byte a0,boolean a1) {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    public char[] charArrayMethodTime2(byte a0,boolean a1) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$charArrayMethodTime2(a0,a1);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public char[] track$charArrayMethodTime2(byte a0,boolean a1) {
        throw new UnsupportedOperationException();
    }

    // void return type

    public void voidMethodTime3(byte a0,boolean a1,char a2) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime3(a0,a1,a2);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public void track$voidMethodTime3(byte a0,boolean a1,char a2) {
    }

    // object or primitive return type

    public short shortMethodTime3(byte a0,boolean a1,char a2) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$shortMethodTime3(a0,a1,a2);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public short track$shortMethodTime3(byte a0,boolean a1,char a2) {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    public short[] shortArrayMethodTime3(byte a0,boolean a1,char a2) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$shortArrayMethodTime3(a0,a1,a2);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public short[] track$shortArrayMethodTime3(byte a0,boolean a1,char a2) {
        throw new UnsupportedOperationException();
    }

    // void return type

    public void voidMethodTime4(byte a0,boolean a1,char a2,short a3) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime4(a0,a1,a2,a3);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public void track$voidMethodTime4(byte a0,boolean a1,char a2,short a3) {
    }

    // object or primitive return type

    public int intMethodTime4(byte a0,boolean a1,char a2,short a3) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$intMethodTime4(a0,a1,a2,a3);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public int track$intMethodTime4(byte a0,boolean a1,char a2,short a3) {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    public int[] intArrayMethodTime4(byte a0,boolean a1,char a2,short a3) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$intArrayMethodTime4(a0,a1,a2,a3);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public int[] track$intArrayMethodTime4(byte a0,boolean a1,char a2,short a3) {
        throw new UnsupportedOperationException();
    }

    // void return type

    public void voidMethodTime5(byte a0,boolean a1,char a2,short a3,int a4) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime5(a0,a1,a2,a3,a4);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public void track$voidMethodTime5(byte a0,boolean a1,char a2,short a3,int a4) {
    }

    // object or primitive return type

    public long longMethodTime5(byte a0,boolean a1,char a2,short a3,int a4) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$longMethodTime5(a0,a1,a2,a3,a4);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public long track$longMethodTime5(byte a0,boolean a1,char a2,short a3,int a4) {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    public long[] longArrayMethodTime5(byte a0,boolean a1,char a2,short a3,int a4) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$longArrayMethodTime5(a0,a1,a2,a3,a4);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public long[] track$longArrayMethodTime5(byte a0,boolean a1,char a2,short a3,int a4) {
        throw new UnsupportedOperationException();
    }

    // void return type

    public void voidMethodTime6(byte a0,boolean a1,char a2,short a3,int a4,long a5) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime6(a0,a1,a2,a3,a4,a5);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public void track$voidMethodTime6(byte a0,boolean a1,char a2,short a3,int a4,long a5) {
    }

    // object or primitive return type

    public float floatMethodTime6(byte a0,boolean a1,char a2,short a3,int a4,long a5) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$floatMethodTime6(a0,a1,a2,a3,a4,a5);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public float track$floatMethodTime6(byte a0,boolean a1,char a2,short a3,int a4,long a5) {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    public float[] floatArrayMethodTime6(byte a0,boolean a1,char a2,short a3,int a4,long a5) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$floatArrayMethodTime6(a0,a1,a2,a3,a4,a5);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public float[] track$floatArrayMethodTime6(byte a0,boolean a1,char a2,short a3,int a4,long a5) {
        throw new UnsupportedOperationException();
    }

    // void return type

    public void voidMethodTime7(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime7(a0,a1,a2,a3,a4,a5,a6);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public void track$voidMethodTime7(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6) {
    }

    // object or primitive return type

    public double doubleMethodTime7(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$doubleMethodTime7(a0,a1,a2,a3,a4,a5,a6);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public double track$doubleMethodTime7(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6) {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    public double[] doubleArrayMethodTime7(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$doubleArrayMethodTime7(a0,a1,a2,a3,a4,a5,a6);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public double[] track$doubleArrayMethodTime7(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6) {
        throw new UnsupportedOperationException();
    }

    // void return type

    public void voidMethodTime8(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6,double a7) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime8(a0,a1,a2,a3,a4,a5,a6,a7);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public void track$voidMethodTime8(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6,double a7) {
    }

    // object or primitive return type

    public java.util.Date DateMethodTime8(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6,double a7) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$DateMethodTime8(a0,a1,a2,a3,a4,a5,a6,a7);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public java.util.Date track$DateMethodTime8(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6,double a7) {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    public java.util.Date[] DateArrayMethodTime8(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6,double a7) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$DateArrayMethodTime8(a0,a1,a2,a3,a4,a5,a6,a7);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public java.util.Date[] track$DateArrayMethodTime8(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6,double a7) {
        throw new UnsupportedOperationException();
    }

    // void return type

    public void voidMethodTime9(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6,double a7,java.util.Date a8) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            track$voidMethodTime9(a0,a1,a2,a3,a4,a5,a6,a7,a8);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public void track$voidMethodTime9(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6,double a7,java.util.Date a8) {
    }

    // object or primitive return type

    public java.net.URI URIMethodTime9(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6,double a7,java.util.Date a8) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$URIMethodTime9(a0,a1,a2,a3,a4,a5,a6,a7,a8);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public java.net.URI track$URIMethodTime9(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6,double a7,java.util.Date a8) {
        throw new UnsupportedOperationException();
    }

    // array object or primitive return type

    public java.net.URI[] URIArrayMethodTime9(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6,double a7,java.util.Date a8) throws IllegalStateException {
        final long start = System.nanoTime();
        try {
            return track$URIArrayMethodTime9(a0,a1,a2,a3,a4,a5,a6,a7,a8);
        } finally {
            Tracker.track("theTag", start);
        }
    }

    public java.net.URI[] track$URIArrayMethodTime9(byte a0,boolean a1,char a2,short a3,int a4,long a5,float a6,double a7,java.util.Date a8) {
        throw new UnsupportedOperationException();
    }

}
