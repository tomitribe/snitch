/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import java.util.HashMap;
import java.util.Map;

/**
 * @version $Revision$ $Date$
 */
public class Clazz {

    private final String name;
    private final Map<Method, Monitor> time = new HashMap<Method, Monitor>();
    private final Map<Method, Monitor> track = new HashMap<Method, Monitor>();

    public Clazz(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<Method, Monitor> getTime() {
        return new HashMap<Method, Monitor>(time);
    }

    public Map<Method, Monitor> getTrack() {
        return new HashMap<Method, Monitor>(track);
    }

    public Monitor time(Method method) {
        return time.get(method);
    }

    public Monitor time(Method method, String monitor) {
        return time.put(method, new Monitor(monitor, method));
    }

    public Monitor track(Method method) {
        return track.get(method);
    }

    public Monitor track(Method method, String monitor) {
        return track.put(method, new Monitor(monitor, method));
    }

    public boolean shouldTrack() {
        return track.size() > 0;
    }

    public boolean shouldTime() {
        return time.size() > 0;
    }
}
