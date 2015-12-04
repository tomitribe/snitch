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

package com.tomitribe.snitch.track;

import com.tomitribe.snitch.util.Join;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @version $Revision$ $Date$
 */
public class Tracker {

    private static final Logger log = Logger.getLogger(Tracker.class.getName());

    private final Map<String, Operation> stats = new LinkedHashMap<String, Tracker.Operation>();

    private final static ThreadLocal<Tracker> TRACKER_THREAD_LOCAL = new ThreadLocal<Tracker>();

    public static void start() {
        TRACKER_THREAD_LOCAL.set(new Tracker());
    }

    public static void stop() {
        end();
    }

    public static Tracker end() {
        final Tracker tracker = TRACKER_THREAD_LOCAL.get();
        TRACKER_THREAD_LOCAL.remove();
        tracker.report();
        return tracker;
    }

    public static void track(final String name, final long start) {
        final Tracker tracker = TRACKER_THREAD_LOCAL.get();
        if (tracker == null) {
            return;
        }

        tracker.operation(name).time(start);
    }

    public Collection<Operation> operations() {
        return stats.values();
    }

    public Operation operation(final String name) {
        {
            final Operation operation = stats.get(name);
            if (operation != null) {
                return operation;
            }
        }

        final Operation operation = new Operation(name);
        stats.put(name, operation);
        return operation;
    }

    private void report() {
        if (stats.size() > 0) {
            log.info("TRACK: " + Join.join(" - ", stats.values()));
        }
    }

    public final class Operation {

        private final String name;
        private long count;
        private long time;

        private Operation(final String name) {
            this.name = name;
        }

        public void time(final long start) {
            count++;
            time += System.nanoTime() - start;
        }

        public String getName() {
            return name;
        }

        public long getCount() {
            return count;
        }

        public long getTime() {
            return time;
        }

        @Override
        public String toString() {
            return name + "{" +
                "count=" + count +
                ", time=" + time +
                '}';
        }
    }
}
