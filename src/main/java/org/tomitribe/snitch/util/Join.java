/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch.util;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @version $Rev: 1291185 $ $Date: 2012-02-20 11:35:46 +0100 (Mon, 20 Feb 2012) $
 */
public class Join {

    public static final MethodCallback METHOD_CALLBACK = new MethodCallback();

    public static final ClassCallback CLASS_CALLBACK = new ClassCallback();

    public static String join(String delimiter, Collection collection) {
        if (collection.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : collection) {
            sb.append(obj).append(delimiter);
        }
        return sb.substring(0, sb.length() - delimiter.length());
    }

    public static String join(String delimiter, Object... collection) {
        if (collection.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : collection) {
            sb.append(obj).append(delimiter);
        }
        return sb.substring(0, sb.length() - delimiter.length());
    }

    public static <T> String join(String delimiter, NameCallback<T> nameCallback, T... collection) {
        if (collection.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T obj : collection) {
            sb.append(nameCallback.getName(obj)).append(delimiter);
        }
        return sb.substring(0, sb.length() - delimiter.length());
    }

    public static <T> String join(String delimiter, NameCallback<T> nameCallback, Collection<T> collection) {
        if (collection.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T obj : collection) {
            sb.append(nameCallback.getName(obj)).append(delimiter);
        }
        return sb.substring(0, sb.length() - delimiter.length());
    }

    public static <T> List<String> strings(Collection<T> collection, NameCallback<T> callback) {
        final List<String> list = new ArrayList<String>();

        for (T t : collection) {
            final String name = callback.getName(t);
            list.add(name);
        }

        return list;
    }

    public static interface NameCallback<T> {

        public String getName(T object);
    }

    public static class FileCallback implements NameCallback<File> {

        public String getName(File file) {
            return file.getName();
        }
    }

    public static class MethodCallback implements NameCallback<Method> {

        public String getName(Method method) {
            return method.getName();
        }
    }

    public static class ClassCallback implements NameCallback<Class<?>> {

        public String getName(Class<?> cls) {
            return cls.getName();
        }
    }
}
