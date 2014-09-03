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

public class BlueListener {

    public static <T> void doIt(final BlueAfter blue, final URI uri, final List<T> list) {
        System.out.println("Listen doIt: " + blue);
    }

    public static void doItStatic(final BlueAfter blue, final URL uri, final Set list) {
        System.out.println("Listen doItStatic: " + blue);
    }
}
