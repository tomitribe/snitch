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
import com.tomitribe.snitch.annotate.Shape;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Color(r = 215, g = 119, b = 58)
@Shape("square")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OrangeAnnotationBefore {
    String foo();

    int bar();
}
