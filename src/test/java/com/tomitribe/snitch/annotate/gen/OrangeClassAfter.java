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

import com.tomitribe.snitch.annotate.Color;
import com.tomitribe.snitch.annotate.Copyright;
import com.tomitribe.snitch.annotate.Shape;

@Copyright(owner = "Tomitribe Corporation", build = "9.0.76-TT.2", licensee = "Unreliable, Inc")
@Color(r = 215, g = 119, b = 58)
@Shape("circle")
public class OrangeClassAfter {

    private final String string;

    public OrangeClassAfter(final String string) {
        this.string = string;
    }

    public static void foo() {
        System.out.println();
    }

}
