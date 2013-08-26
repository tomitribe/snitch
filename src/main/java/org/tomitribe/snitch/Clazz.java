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
    private final Map<Method, Monitor> methods = new HashMap<Method, Monitor>();

    public Clazz(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Monitor get(Method method) {
        return methods.get(method);
    }

    public Monitor put(Method method, Monitor monitor) {
        return methods.put(method, monitor);
    }

    public Monitor put(Method method, String monitor) {
        return methods.put(method, new Monitor(monitor, method));
    }
}
