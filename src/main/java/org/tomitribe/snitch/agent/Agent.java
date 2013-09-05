/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch.agent;

import org.tomitribe.snitch.Enhancer;
import org.tomitribe.snitch.Log;

import java.io.File;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @version $Rev$ $Date$
 */
public class Agent {

    private static Instrumentation instrumentation;

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        if (Agent.instrumentation != null) return;

        initialize(agentArgs, instrumentation);

        Agent.instrumentation = instrumentation;
    }

    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        if (Agent.instrumentation != null) return;

        initialize(agentArgs, instrumentation);

        Agent.instrumentation = instrumentation;
    }

    private static void initialize(String agentArgs, Instrumentation instrumentation) {
        try {
            if (agentArgs == null) {
                err("Tracker not installed.  No properties file specified");
                return;
            }

            final File file = new File(agentArgs);

            if (!file.isFile()) {
                err("Tracker not installed.  Configuration file does not exist '%s'", file.getAbsolutePath());
                return;
            }

            if (!file.isFile()) {
                err("Tracker not installed.  Path is not a file '%s'", file.getAbsolutePath());
                return;
            }

            if (!file.canRead()) {
                err("Tracker not installed.  Cannot read properties file '%s'", file.getAbsolutePath());
                return;
            }

            instrumentation.addTransformer(new Tracker(Enhancer.create(file), instrumentation));
            out("Tracker installed.  Configuration '%s'", file.getAbsolutePath());

        } catch (Throwable e) {
            err("Failed %s", e.getMessage());
            e.printStackTrace();
        }
    }

    private static void out(String format, Object... details) {
        Log.log("Agent:: %s%n", String.format(format, details));
    }

    private static void err(String format, Object... details) {
        Log.err("Agent:: %s%n", String.format(format, details));
    }

    public static class Tracker implements ClassFileTransformer {

        private Enhancer enhancer;

        public Tracker(final Enhancer enhancer) {
            this.enhancer = enhancer;
        }

        public Tracker(final Enhancer enhancer, Instrumentation instrumentation) {
            this.enhancer = enhancer;
        }

        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            try {
                return enhancer.enhance(className, classfileBuffer);
            } catch (Throwable e) {
                err("Enhance Failed for '%s' : %s %s", className, e.getClass().getName(), e.getMessage());
                e.printStackTrace();
                return classfileBuffer;
            }
        }

    }
}