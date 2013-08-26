/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.tomitribe.snitch;

import org.objectweb.asm.Type;

import java.util.Arrays;

/**
 * @version $Revision$ $Date$
 */
public class Method {
    private final String methodName;
    private final Type[] arguments;

    Method(java.lang.reflect.Method method) {
        this.methodName = method.getName();
        this.arguments = Type.getArgumentTypes(method);
    }

    public Method(String methodName, Type[] arguments) {
        this.methodName = methodName;
        this.arguments = arguments;
    }

    public static Method fromDescriptor(String name, String desc) {
        return new Method(name, Type.getArgumentTypes(desc));
    }

    public static Method fromToString(String toStringValue) {
        String rawName = null;
        String rawArgs = null;

        final String[] split = toStringValue.split("[()]");

        if (split.length < 1) throw new IllegalArgumentException("Invalid toString format: " + toStringValue);

        if (toStringValue.contains("()") || toStringValue.length() == 1) {
            rawName = split[0];
            rawArgs = "";
        } else {
            rawName = split[0];
            rawArgs = split[1];
        }

        // string prefixes like "public static void "
        rawName = rawName.trim();
        rawName = rawName.replaceAll(".* ", "");

        // strip white space a user may have added to the arguments list
        rawArgs = rawArgs.replaceAll("\\s", "");

        final String className = rawName.substring(0, rawName.lastIndexOf('.'));
        final String methodName = rawName.substring(rawName.lastIndexOf('.') + 1, rawName.length());
        final String[] args = (rawArgs.length() == 0) ? new String[0] : rawArgs.split(",");

        final Type[] types = new Type[args.length];

        for (int i = 0; i < args.length; i++) {

            final String[] array = args[i].split("\\[");
            String type = type(array[0]);

            for (int j = 1; j < array.length; j++) {
                type = "[" + type;
            }

            types[i] = Type.getType(type);
        }

        return new Method(methodName, types);
    }

    private static String type(String raw) {
        if ("byte".equals(raw)) return "B";
        if ("boolean".equals(raw)) return "Z";
        if ("char".equals(raw)) return "C";
        if ("short".equals(raw)) return "S";
        if ("int".equals(raw)) return "I";
        if ("long".equals(raw)) return "J";
        if ("float".equals(raw)) return "F";
        if ("double".equals(raw)) return "D";
        return "L" + raw.replace('.', '/') + ";";
    }

    @Override
    public String toString() {
        return "Method{" +
                "name='" + methodName + '\'' +
                ", arguments=" + (arguments == null ? null : Arrays.asList(arguments)) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Method method = (Method) o;

        if (!Arrays.equals(arguments, method.arguments)) return false;
        if (!methodName.equals(method.methodName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = methodName.hashCode();
        result = 31 * result + Arrays.hashCode(arguments);
        return result;
    }
}
