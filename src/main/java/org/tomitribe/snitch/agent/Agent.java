/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch.agent;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
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
                err("No output directory specified");
                return;
            }

            final File dir = new File(agentArgs);
            if (!dir.exists() && !dir.mkdirs()) {
                err("Cannot create output directory '%s'", dir.getAbsolutePath());
                return;
            }

            if (!dir.isDirectory()) {
                err("Path is not a directory '%s'", dir.getAbsolutePath());
                return;
            }

            if (!dir.canRead()) {
                err("Cannot read output directory '%s'", dir.getAbsolutePath());
                return;
            }

            instrumentation.addTransformer(new Tracker(dir, instrumentation));

            out("Initialized. Logging to '%s'", dir.getAbsolutePath());
        } catch (Throwable e) {
            err("Failed %s", e.getMessage());
            e.printStackTrace();
        }
    }

    private static PrintStream out(String format, Object... details) {
        return System.out.printf("Agent:: %s%n", String.format(format, details));
    }

    private static PrintStream err(String format, Object... details) {
        return System.err.printf("Agent:: %s%n", String.format(format, details));
    }

    public static class Tracker implements ClassFileTransformer {

        private final File output;

        public Tracker(File output) {
            this.output = output;
        }

        public Tracker(File output, Instrumentation instrumentation) {
            this(output);

            for (Class clazz : instrumentation.getAllLoadedClasses()) {
                clazz = unwrap(clazz);
                if (clazz.isPrimitive()) continue;
                track(clazz.getName());
            }
        }

        private static Class unwrap(Class a) {
            if (a.isArray()) return unwrap(a.getComponentType());
            return a;
        }

        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            track(className.replace('/', '.'));
            return classfileBuffer;
        }

        public void track(String className) {
            if (!isInteresting(className)) return;

            try {
                final File file = new File(output, className);

                if (file.exists() || file.createNewFile() || file.exists()) {
                    if (!file.setLastModified(System.currentTimeMillis())) {
                        err("Cannot set last-modified time on class '%s'", className);
                    }
                    return;
                }

                err("Cannot track class '%s'", className);
            } catch (IOException e) {
                err("Cannot track class '%s'", className);
                e.printStackTrace();
            }
        }

        private boolean isInteresting(String className) {
            if (className.startsWith("sun.reflect")) return false;
            if (className.startsWith("sun.nio")) return false;
            if (className.startsWith("$Proxy")) return false;
            if (className.contains("$JaxbAccessor")) return false;
            return true;
        }
    }
}