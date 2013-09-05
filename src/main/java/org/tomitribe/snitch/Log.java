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
public class Log {

    public static void log(final String format, Object... details) {
        final String message = String.format(format, details);
        System.out.printf("%tF %<tT - SNITCH: %s%n", System.currentTimeMillis(), message);
    }

    public static void err(final String format, Object... details) {
        final String message = String.format(format, details);
        System.err.printf("%tF %<tT - SNITCH: %s%n", System.currentTimeMillis(), message);
    }
}
