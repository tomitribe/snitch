/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.junit.Assert;
import org.junit.Test;

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

        final Tracker.Operation one = tracker.operation("one");
        final Tracker.Operation two = tracker.operation("two");
        final Tracker.Operation three = tracker.operation("three");

        assertEquals(1, one.getCount());
        assertEquals(1, NANOSECONDS.toSeconds(one.getTime()));

        assertEquals(2, two.getCount());
        assertEquals(4, NANOSECONDS.toSeconds(two.getTime()));

        assertEquals(3, three.getCount());
        assertEquals(9, NANOSECONDS.toSeconds(three.getTime()));

    }
}
