/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch.util;

import org.tomitribe.snitch.Asmifier;
import org.tomitribe.snitch.Bytecode;
import org.tomitribe.snitch.Enhancer;
import org.tomitribe.snitch.Tracker;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @version $Revision$ $Date$
 */
public class AsmTest {

    @org.junit.Test
    public void testIsPublic() throws Exception {
        Asmifier.asmify(Orange.class, "unmodified");
        Asmifier.asmify(Orange2.class, "modified");

        final URLClassLoader loader = new URLClassLoader(new URL[]{});
        Bytecode.modifyAndDefine(loader, Orange.class.getName(), Enhancer.class);

        final Class<?> clazz = loader.loadClass(Orange.class.getName());
        final Object orange = clazz.newInstance();

        final Method doit2 = clazz.getMethod("doit2");

        final Orange2<Object> orange2 = new Orange2<Object>();

        Tracker.start();

        doit2.invoke(orange);
        doit2.invoke(orange);
        doit2.invoke(orange);
        doit2.invoke(orange);
        doit2.invoke(orange);
        doit2.invoke(orange);
        doit2.invoke(orange);
        doit2.invoke(orange);
        doit2.invoke(orange);
        orange2.doit();
        orange2.doit();
        orange2.doit();

        Tracker.stop();
    }

    public static class Orange<T> {
        public synchronized T doit2() {
            return null;
        }
    }


    public static class Orange2<T> {

        public synchronized T $doit() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
            return null;
        }

        public T doit() {
            final long doit$start = System.nanoTime();
            try {
                return $doit();
            } finally {
                Tracker.track("doit", doit$start);
            }
        }
    }
}
