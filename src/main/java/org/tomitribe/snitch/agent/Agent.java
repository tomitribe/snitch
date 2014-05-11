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
package org.tomitribe.snitch.agent;

import org.tomitribe.snitch.Enhancer;
import org.tomitribe.snitch.Log;
import org.tomitribe.snitch.util.IO;
import org.tomitribe.snitch.util.Join;

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

    private Agent() {
        // no-op
    }

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        if (Agent.instrumentation != null) { return; }

        initialize(agentArgs, instrumentation);

        Agent.instrumentation = instrumentation;
    }

    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        if (Agent.instrumentation != null) { return; }

        initialize(agentArgs, instrumentation);

        Agent.instrumentation = instrumentation;
    }

    private static void initialize(String agentArgs, Instrumentation instrumentation) {
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

            for (String path : split) {
                final File file = new File(path);

                if (!file.isFile()) {
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

            for (File file : files) {
                IO.readProperties(file, properties);
            }

            instrumentation.addTransformer(new Tracker(Enhancer.create(properties), instrumentation));
            out("Tracker installed.  Configuration files '%s'", Join.join(",", new Join.NameCallback<File>() {
                @Override
                public String getName(File object) {
                    return object.getAbsolutePath();
                }
            }, files));

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

        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                                byte[] classfileBuffer) throws IllegalClassFormatException
        {
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
