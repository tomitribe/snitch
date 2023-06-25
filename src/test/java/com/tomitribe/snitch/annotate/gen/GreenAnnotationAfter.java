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

import com.tomitribe.snitch.annotate.Monitor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URI;

@Monitor(licensee = true, implementation = URI.class, max = 234567890L, min = -123)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GreenAnnotationAfter {
    String foo();

    int bar();
}
