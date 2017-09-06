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
package com.tomitribe.snitch.agent;

import com.tomitribe.snitch.track.Enhancer;
import com.tomitribe.snitch.track.Log;
import com.tomitribe.snitch.util.IO;
import com.tomitribe.snitch.util.Join;

import java.io.File;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @version $Rev$ $Date$
 */
public class Agent {

    private static Instrumentation instrumentation;
    private static boolean logEnabled = true;

    private Agent() {
        // no-op
    }

    public static void premain(final String agentArgs, final Instrumentation instrumentation) {
        if (Agent.instrumentation != null) {
            return;
        }

        initialize(agentArgs, instrumentation);

        Agent.instrumentation = instrumentation;
    }

    public static void agentmain(final String agentArgs, final Instrumentation instrumentation) {
        if (Agent.instrumentation != null) {
            return;
        }

        initialize(agentArgs, instrumentation);

        Agent.instrumentation = instrumentation;
    }

    private static void initialize(final String agentArgs, final Instrumentation instrumentation) {
        try {
            if (agentArgs == null) {
                err("Tracker not installed.  No properties file specified");
                return;
            }

            final String[] split = agentArgs.trim().split(" *, *");

            if (agentArgs.length() == 0) {
                err("Tracker not installed.  No properties file specified");
                return;
            }

            final List<File> files = new ArrayList<File>();

            for (final String path : split) {
                final File file = new File(path);

                if (!file.exists()) {
                    err("Configuration file does not exist '%s'", file.getAbsolutePath());
                    continue;
                }

                if (!file.isFile()) {
                    err("Path is not a file '%s'", file.getAbsolutePath());
                    continue;
                }

                if (!file.canRead()) {
                    err("Cannot read properties file '%s'", file.getAbsolutePath());
                    continue;
                }

                files.add(file);
            }

            if (files.size() == 0) {
                err("Tracker not installed.  No usable configuration files.");
                return;
            }

            final Properties properties = new Properties();

            for (final File file : files) {
                IO.readProperties(file, properties);
            }

            logEnabled = Boolean.parseBoolean(properties.getProperty("snitch.logging", "true"));
            properties.remove("snitch.logging");

            instrumentation.addTransformer(new Tracker(Enhancer.create(properties), instrumentation));
            out("Tracker installed.  Configuration files '%s'", Join.join(",", new Join.NameCallback<File>() {
                @Override
                public String getName(final File object) {
                    return object.getAbsolutePath();
                }
            }, files));

        } catch (final Throwable e) {
            err("Failed %s", e.getMessage());
            e.printStackTrace();
        }
    }

    private static void out(final String format, final Object... details) {
        Log.log("Agent:: %s%n", String.format(format, details));
    }

    private static void err(final String format, final Object... details) {
        Log.err("Agent:: %s%n", String.format(format, details));
    }

    public static class Tracker implements ClassFileTransformer {

        private final Enhancer enhancer;

        public Tracker(final Enhancer enhancer) {
            this.enhancer = enhancer;
        }

        public Tracker(final Enhancer enhancer, final Instrumentation instrumentation) {
            this.enhancer = enhancer;
        }

        public byte[] transform(final ClassLoader loader, final String className, final Class<?> classBeingRedefined, final ProtectionDomain protectionDomain,
                                final byte[] classfileBuffer) throws IllegalClassFormatException {
            try {
                return enhancer.enhance(className, classfileBuffer);
            } catch (final Throwable e) {
                err("Enhance Failed for '%s' : %s %s", className, e.getClass().getName(), e.getMessage());
                e.printStackTrace();
                return classfileBuffer;
            }
        }

    }

    public static boolean isLogEnabled() {
        return logEnabled;
    }
}
