/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch.util;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

/**
 * @version $Revision$ $Date$
 */
public class Unsafe {

    private static final sun.misc.Unsafe unsafe;

    static {
        unsafe = (sun.misc.Unsafe) AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                try {
                    final Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
                    field.setAccessible(true);
                    return field.get(null);
                } catch (Exception e) {
                    throw new IllegalStateException("Cannot get sun.misc.Unsafe", e);
                }
            }
        });
    }

    public static sun.misc.Unsafe get() {
        return unsafe;
    }

    public static void putByteVolatile(Object o, long offset, byte x) {
        unsafe.putByteVolatile(o, offset, x);
    }

    public static void putByte(Object o, long offset, byte x) {
        unsafe.putByte(o, offset, x);
    }

    public static int pageSize() {
        return unsafe.pageSize();
    }

    public static void putOrderedLong(Object o, long offset, long x) {
        unsafe.putOrderedLong(o, offset, x);
    }

    public static void putOrderedObject(Object o, long offset, Object x) {
        unsafe.putOrderedObject(o, offset, x);
    }

    public static char getChar(Object o, long offset) {
        return unsafe.getChar(o, offset);
    }

    public static boolean compareAndSwapInt(Object o, long offset, int expected, int x) {
        return unsafe.compareAndSwapInt(o, offset, expected, x);
    }

    public static long getLong(Object o, long offset) {
        return unsafe.getLong(o, offset);
    }

    public static void putDouble(Object o, long offset, double x) {
        unsafe.putDouble(o, offset, x);
    }

    public static long allocateMemory(long bytes) {
        return unsafe.allocateMemory(bytes);
    }

    public static int getInt(long address) {
        return unsafe.getInt(address);
    }

    public static void putDoubleVolatile(Object o, long offset, double x) {
        unsafe.putDoubleVolatile(o, offset, x);
    }

    public static float getFloat(Object o, long offset) {
        return unsafe.getFloat(o, offset);
    }

    public static boolean compareAndSwapObject(Object o, long offset, Object expected, Object x) {
        return unsafe.compareAndSwapObject(o, offset, expected, x);
    }

    public static void putFloat(Object o, long offset, float x) {
        unsafe.putFloat(o, offset, x);
    }

    public static void putChar(long address, char x) {
        unsafe.putChar(address, x);
    }

    public static int arrayBaseOffset(Class arrayClass) {
        return unsafe.arrayBaseOffset(arrayClass);
    }

    public static char getCharVolatile(Object o, long offset) {
        return unsafe.getCharVolatile(o, offset);
    }

    public static char getChar(long address) {
        return unsafe.getChar(address);
    }

    public static boolean getBoolean(Object o, long offset) {
        return unsafe.getBoolean(o, offset);
    }

    public static void putLong(Object o, long offset, long x) {
        unsafe.putLong(o, offset, x);
    }

    public static void putCharVolatile(Object o, long offset, char x) {
        unsafe.putCharVolatile(o, offset, x);
    }

    public static Class defineClass(String name, byte[] b, int off, int len, ClassLoader loader, ProtectionDomain protectionDomain) {
        return unsafe.defineClass(name, b, off, len, loader, protectionDomain);
    }

    public static long getLong(long address) {
        return unsafe.getLong(address);
    }

    public static void putByte(long address, byte x) {
        unsafe.putByte(address, x);
    }

    public static int getIntVolatile(Object o, long offset) {
        return unsafe.getIntVolatile(o, offset);
    }

    public static void park(boolean isAbsolute, long time) {
        unsafe.park(isAbsolute, time);
    }

    public static int addressSize() {
        return unsafe.addressSize();
    }

    public static void setMemory(long address, long bytes, byte value) {
        unsafe.setMemory(address, bytes, value);
    }

    public static long objectFieldOffset(Field f) {
        return unsafe.objectFieldOffset(f);
    }

    public static void monitorEnter(Object o) {
        unsafe.monitorEnter(o);
    }

    public static void putIntVolatile(Object o, long offset, int x) {
        unsafe.putIntVolatile(o, offset, x);
    }

    public static void putObject(Object o, long offset, Object x) {
        unsafe.putObject(o, offset, x);
    }

    public static void putDouble(long address, double x) {
        unsafe.putDouble(address, x);
    }

    public static short getShort(long address) {
        return unsafe.getShort(address);
    }

    public static void throwException(Throwable ee) {
        unsafe.throwException(ee);
    }

    public static Object staticFieldBase(Field f) {
        return unsafe.staticFieldBase(f);
    }

    public static Object getObject(Object o, long offset) {
        return unsafe.getObject(o, offset);
    }

    public static void putChar(Object o, long offset, char x) {
        unsafe.putChar(o, offset, x);
    }

    public static void putOrderedInt(Object o, long offset, int x) {
        unsafe.putOrderedInt(o, offset, x);
    }

    public static long getAddress(long address) {
        return unsafe.getAddress(address);
    }

    public static void unpark(Object thread) {
        unsafe.unpark(thread);
    }

    public static short getShort(Object o, long offset) {
        return unsafe.getShort(o, offset);
    }

    public static float getFloat(long address) {
        return unsafe.getFloat(address);
    }

    public static void putShortVolatile(Object o, long offset, short x) {
        unsafe.putShortVolatile(o, offset, x);
    }

    public static float getFloatVolatile(Object o, long offset) {
        return unsafe.getFloatVolatile(o, offset);
    }

    public static double getDouble(Object o, long offset) {
        return unsafe.getDouble(o, offset);
    }

    public static boolean tryMonitorEnter(Object o) {
        return unsafe.tryMonitorEnter(o);
    }

    public static void copyMemory(Object o, long l, Object o1, long l1, long l2) {
        unsafe.copyMemory(o, l, o1, l1, l2);
    }

    public static void putShort(long address, short x) {
        unsafe.putShort(address, x);
    }

    public static int getInt(Object o, long offset) {
        return unsafe.getInt(o, offset);
    }

    public static void ensureClassInitialized(Class c) {
        unsafe.ensureClassInitialized(c);
    }

    public static void monitorExit(Object o) {
        unsafe.monitorExit(o);
    }

    public static void putObjectVolatile(Object o, long offset, Object x) {
        unsafe.putObjectVolatile(o, offset, x);
    }

    public static short getShortVolatile(Object o, long offset) {
        return unsafe.getShortVolatile(o, offset);
    }

    public static double getDoubleVolatile(Object o, long offset) {
        return unsafe.getDoubleVolatile(o, offset);
    }

    public static Object allocateInstance(Class cls) throws InstantiationException {
        return unsafe.allocateInstance(cls);
    }

    public static void putFloatVolatile(Object o, long offset, float x) {
        unsafe.putFloatVolatile(o, offset, x);
    }

    public static byte getByte(Object o, long offset) {
        return unsafe.getByte(o, offset);
    }

    public static void putBooleanVolatile(Object o, long offset, boolean x) {
        unsafe.putBooleanVolatile(o, offset, x);
    }

    public static void putInt(long address, int x) {
        unsafe.putInt(address, x);
    }

    public static byte getByte(long address) {
        return unsafe.getByte(address);
    }

    public static void putFloat(long address, float x) {
        unsafe.putFloat(address, x);
    }

    public static int arrayIndexScale(Class arrayClass) {
        return unsafe.arrayIndexScale(arrayClass);
    }

    public static long staticFieldOffset(Field f) {
        return unsafe.staticFieldOffset(f);
    }

    public static void putLongVolatile(Object o, long offset, long x) {
        unsafe.putLongVolatile(o, offset, x);
    }

    public static void putShort(Object o, long offset, short x) {
        unsafe.putShort(o, offset, x);
    }

    public static void putInt(Object o, long offset, int x) {
        unsafe.putInt(o, offset, x);
    }

    public static Object getObjectVolatile(Object o, long offset) {
        return unsafe.getObjectVolatile(o, offset);
    }

    public static void copyMemory(long srcAddress, long destAddress, long bytes) {
        unsafe.copyMemory(srcAddress, destAddress, bytes);
    }

    public static Class defineClass(String name, byte[] b, int off, int len) {
        return unsafe.defineClass(name, b, off, len);
    }

    public static void putLong(long address, long x) {
        unsafe.putLong(address, x);
    }

    public static void putAddress(long address, long x) {
        unsafe.putAddress(address, x);
    }

    public static void putBoolean(Object o, long offset, boolean x) {
        unsafe.putBoolean(o, offset, x);
    }

    public static boolean getBooleanVolatile(Object o, long offset) {
        return unsafe.getBooleanVolatile(o, offset);
    }

    public static void freeMemory(long address) {
        unsafe.freeMemory(address);
    }

    public static boolean compareAndSwapLong(Object o, long offset, long expected, long x) {
        return unsafe.compareAndSwapLong(o, offset, expected, x);
    }

    public static int getLoadAverage(double[] loadavg, int nelems) {
        return unsafe.getLoadAverage(loadavg, nelems);
    }

    public static long reallocateMemory(long address, long bytes) {
        return unsafe.reallocateMemory(address, bytes);
    }

    public static long getLongVolatile(Object o, long offset) {
        return unsafe.getLongVolatile(o, offset);
    }

    public static double getDouble(long address) {
        return unsafe.getDouble(address);
    }

    public static byte getByteVolatile(Object o, long offset) {
        return unsafe.getByteVolatile(o, offset);
    }
}
