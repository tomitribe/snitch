/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tomitribe.snitch.track;

import org.objectweb.asm.Type;
import com.tomitribe.snitch.util.Join;

import java.util.Arrays;

/**
 * @version $Revision$ $Date$
 */
public class Method {

    private final String className;
    private final String methodName;
    private final Type[] arguments;

    public Method(java.lang.reflect.Method method) {
        this.className = method.getDeclaringClass().getName();
        this.methodName = method.getName();
        this.arguments = Type.getArgumentTypes(method);
    }

    public Method(String className, String methodName, Type[] arguments) {
        this.className = className;
        this.methodName = methodName;
        this.arguments = arguments;
    }

    public static Method fromDescriptor(String name, String desc, final String className) {
        return new Method(className, name, Type.getArgumentTypes(desc));
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public Type[] getArguments() {
        return arguments;
    }

    public static Method fromToString(String toStringValue) {
        String rawName = null;
        String rawArgs = null;

        final String[] split = toStringValue.split("[()]");

        if (split.length < 1) { throw new IllegalArgumentException("Invalid toString format: " + toStringValue); }

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

        return new Method(className, methodName, types);
    }

    private static String type(String raw) {
        if ("byte".equals(raw)) { return "B"; }
        if ("boolean".equals(raw)) { return "Z"; }
        if ("char".equals(raw)) { return "C"; }
        if ("short".equals(raw)) { return "S"; }
        if ("int".equals(raw)) { return "I"; }
        if ("long".equals(raw)) { return "J"; }
        if ("float".equals(raw)) { return "F"; }
        if ("double".equals(raw)) { return "D"; }
        return "L" + raw.replace('.', '/') + ";";
    }

    public static String type(Type type) {
        if (Type.BYTE_TYPE.equals(type)) { return "byte"; }
        if (Type.BOOLEAN_TYPE.equals(type)) { return "boolean"; }
        if (Type.CHAR_TYPE.equals(type)) { return "char"; }
        if (Type.SHORT_TYPE.equals(type)) { return "short"; }
        if (Type.INT_TYPE.equals(type)) { return "int"; }
        if (Type.LONG_TYPE.equals(type)) { return "long"; }
        if (Type.FLOAT_TYPE.equals(type)) { return "float"; }
        if (Type.DOUBLE_TYPE.equals(type)) { return "double"; }
        return type.getClassName();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(className).append(".");
        sb.append(methodName).append("(");
        sb.append(Join.join(",", new Join.NameCallback<Type>() {
            @Override
            public String getName(Type type) {
                return type(type);
            }
        }, arguments));
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Method method = (Method) o;

        if (!Arrays.equals(arguments, method.arguments)) { return false; }
        if (!methodName.equals(method.methodName)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = methodName.hashCode();
        result = 31 * result + Arrays.hashCode(arguments);
        return result;
    }


}
