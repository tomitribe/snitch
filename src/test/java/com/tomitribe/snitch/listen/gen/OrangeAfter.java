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

public class OrangeAfter {

    public void start() throws Exception {
        try {
            System.out.println("start");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void init() throws Exception {
        Circle.draw();
        System.out.println("init");
        for (final Object value : System.getProperties().values()) {
            System.out.println(value);
        }
    }

    public void pause() throws Exception {
        System.getProperties().values().forEach(System.out::println);
    }

    public void resume() throws Exception {
        final String resume = "resume";
        foo(resume);
    }

    public void stop() throws Exception {
        final String stop = "stop";
        foo(stop);
    }

    public void destroy() throws Exception {
        final String destroy = "destroy";
        foo(destroy);
    }

    public void foo(final Object o){
        System.out.println(o);
    }
}