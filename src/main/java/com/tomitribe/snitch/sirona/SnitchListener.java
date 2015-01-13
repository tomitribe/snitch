package com.tomitribe.snitch.sirona;

import org.apache.sirona.configuration.ioc.AutoSet;
import org.apache.sirona.javaagent.AgentContext;
import org.apache.sirona.javaagent.listener.ConfigurableListener;
import org.apache.sirona.spi.Order;

/**
 * $ cat conf/snitch.properties
 * com.tomitribe.snitch.sirona.SnitchListener.includes=prefix:org.apache.openejb.
 * com.tomitribe.snitch.sirona.SnitchListener.excludes=prefix:org.apache.catalina.
 */
@Order(1)
@AutoSet
public class SnitchListener extends ConfigurableListener {
    private static final Integer KEY = SnitchListener.class.getName().hashCode();

    private boolean activated = true;

    @Override
    public void setIncludes(String includes) {
        super.setIncludes(includes);
    }

    @Override
    public void setExcludes(String excludes) {
        super.setExcludes(excludes);
    }

    @Override
    public boolean accept(final String key, final byte[] buffer) {
        return activated && super.accept(key, buffer);
    }

    @Override
    public void before(final AgentContext context) {
        context.put(KEY, new Tracker(context));
    }

    @Override
    public void after(final AgentContext context, final Object result, final Throwable error) {
        context.get(KEY, Tracker.class).stop();
    }

    // stupid impl but com.tomitribe.snitch.track.Tracker == CounterListener/PathTracking
    // so surely not something to keep here
    private static class Tracker {
        private static final ThreadLocal<StringBuilder> CURRENT = new ThreadLocal<StringBuilder>() {
            @Override
            protected StringBuilder initialValue() {
                return new StringBuilder(32);
            }
        };
        public static final String TAB = "    ";

        private final AgentContext context;

        public Tracker(final AgentContext context) {
            this.context = context;
            start();
        }

        private void start() {
            final StringBuilder c = CURRENT.get();
            System.out.println(c.toString() + "(start) " + context.keyAsMethod());
            c.append(TAB);
        }

        public void stop() {
            final StringBuilder c = CURRENT.get();
            c.setLength(c.length() - TAB.length());
            System.out.println(c.toString() + "(end) " + context.keyAsMethod());
        }
    }
}
