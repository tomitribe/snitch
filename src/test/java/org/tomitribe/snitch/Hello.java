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
public class Hello {

    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println(Hello.class.getMethod("main", String[].class).toString());
        hello();
        hello();
    }

    private static void hello() {
        System.out.println("Hello");
    }


}
