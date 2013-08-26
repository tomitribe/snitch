/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

/**
 * @version $Revision$ $Date$
 */
public class Monitor {

    private final String name;
    private final Method method;

    public Monitor(String name, Method method) {
        this.name = name;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public Method getMethod() {
        return method;
    }
}
