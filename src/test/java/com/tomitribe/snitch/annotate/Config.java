/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2023
 *
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 */
package com.tomitribe.snitch.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Config {
    String astring() default "";

    Class<?> aclass() default Object.class;

    boolean aboolean() default false;

    byte abyte() default 42;

    char achar() default 'D';

    short ashort() default 256;

    int anint() default 1234567;

    long along() default 1234567890000L;

    float afloat() default 12.34567F;

    double adouble() default 1234.567890D;
}
