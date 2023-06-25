/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2023
 *
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 */
package com.tomitribe.snitch.annotate.gen;

import com.tomitribe.snitch.annotate.Copyright;

@Copyright(owner = "Tomitribe Corporation", build = "9.0.76-TT.2", licensee = "Unreliable, Inc")
public class GreenClassAfter {

    private final String string;

    public GreenClassAfter(final String string) {
        this.string = string;
    }

    public static void foo() {
        System.out.println();
    }

}
