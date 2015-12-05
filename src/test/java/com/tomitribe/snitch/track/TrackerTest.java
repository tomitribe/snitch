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

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @version $Revision$ $Date$
 */
public class TrackerTest extends Assert {

    @Test
    public void test() throws Exception {

        Tracker.start();

        Tracker.track("three", System.nanoTime() - SECONDS.toNanos(3));
        Tracker.track("three", System.nanoTime() - SECONDS.toNanos(3));
        Tracker.track("three", System.nanoTime() - SECONDS.toNanos(3));

        Tracker.track("two", System.nanoTime() - SECONDS.toNanos(2));
        Tracker.track("two", System.nanoTime() - SECONDS.toNanos(2));

        Tracker.track("one", System.nanoTime() - SECONDS.toNanos(1));

        final Tracker tracker = Tracker.end();

        final Collection<Tracker.Operation> operations = tracker.operations();
        final Map<String, Tracker.Operation> operationMap = new HashMap<String, Tracker.Operation>();
        for (Tracker.Operation operation : operations) {
            operationMap.put(operation.getName(), operation);
        }

        assertTrue(operationMap.containsKey("one"));
        assertTrue(operationMap.containsKey("two"));
        assertTrue(operationMap.containsKey("three"));

        final Tracker.Operation one = tracker.operation("one");
        final Tracker.Operation two = tracker.operation("two");
        final Tracker.Operation three = tracker.operation("three");

        assertEquals(one, operationMap.get("one"));
        assertEquals(two, operationMap.get("two"));
        assertEquals(three, operationMap.get("three"));

        assertEquals(1, one.getCount());
        assertEquals(1, NANOSECONDS.toSeconds(one.getTime()));

        assertEquals(2, two.getCount());
        assertEquals(4, NANOSECONDS.toSeconds(two.getTime()));

        assertEquals(3, three.getCount());
        assertEquals(9, NANOSECONDS.toSeconds(three.getTime()));

    }
}
