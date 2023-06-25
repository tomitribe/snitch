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

import com.tomitribe.snitch.annotate.Config;

@Config(afloat = 4.5F, adouble = 4.6D, ashort = 123, achar = 'X')
public interface GreenInterfaceAfter<T> {
    void foo() throws Exception;

    T bar(T t) throws Exception;
}
