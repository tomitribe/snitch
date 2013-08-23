package org.tomitribe.snitch;

import org.tomitribe.snitch.util.Join;

import java.util.HashMap;
import java.util.Map;

/**
 * @version $Revision$ $Date$
 */
public class Tracker {

    private final Map<String, Operation> stats = new HashMap<String, Operation>();

    private final static ThreadLocal<Tracker> trackers = new ThreadLocal<Tracker>();

    public static void start() {
        trackers.set(new Tracker());
    }

    public static void stop() {
        final Tracker tracker = trackers.get();
        trackers.remove();
        tracker.report();
    }

    public static void track(String name, long start) {
        final Tracker tracker = trackers.get();
        if (tracker == null) return;

        tracker.operation(name).time(start);
    }

    public Operation operation(String name) {
        {
            final Operation operation = stats.get(name);
            if (operation != null) return operation;
        }

        final Operation operation = new Operation(name);
        stats.put(name, operation);
        return operation;
    }

    private void report() {
        System.out.printf("SNITCH: %s%n", Join.join(" - ", stats.values()));
    }

    private final class Operation {

        private final String name;
        private long count;
        private long time;

        private Operation(String name) {
            this.name = name;
        }

        public void time(long start) {
            count++;
            time += System.nanoTime() - start;
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
