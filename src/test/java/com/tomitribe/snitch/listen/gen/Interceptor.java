/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2023
 *
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 */
package com.tomitribe.snitch.listen.gen;

public class Interceptor {

    public static void intercept() {
        System.out.println("Intercepting");
    }

    public static void log(final String toLog) {
        System.out.println(toLog);
    }

    public void doWork() {
        System.out.println("Doing work");
    }

    public void doWork(final String toLog) {
        System.out.println("Doing work: " + toLog);
    }

}
