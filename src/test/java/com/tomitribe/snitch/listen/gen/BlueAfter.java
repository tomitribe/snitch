/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2014
 *
 * The source code for this program is not published or otherwise divested 
 * of its trade secrets, irrespective of what has been deposited with the 
 * U.S. Copyright Office.
 */
package com.tomitribe.snitch.listen.gen;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Set;

public class BlueAfter<T> {

    public void doIt(final URI uri, final List<T> list) {
        BlueListener.doIt(this, uri, list);

        System.out.println("DoIt: Printing list");

        for (final T t : list) {
            System.out.println(" - " + t);
        }
    }

    public static void doItStatic(final URL uri, final Set list) {

        BlueListener.doItStatic(null, uri, list);

        System.out.println("DoItStatic: Printing list");

        for (final Object t : list) {
            System.out.println(" - " + t);
        }
    }
}
