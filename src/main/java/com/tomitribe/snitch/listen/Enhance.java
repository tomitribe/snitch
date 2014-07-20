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
package com.tomitribe.snitch.listen;

import com.tomitribe.snitch.track.Tracker;
import com.tomitribe.snitch.util.AsmModifiers;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.objectweb.asm.Type.LONG_TYPE;
import static org.objectweb.asm.Type.VOID_TYPE;

/**
 * @version $Revision$ $Date$
 */
public class Enhance {

    private Enhance() {
        // no-op
    }

    private static void load(List<Type> invocationStack, MethodVisitor mv) {
        int slot = 0;
        for (Type type : invocationStack) {
            mv.visitVarInsn(type.getOpcode(Opcodes.ILOAD), slot);
            slot += size(type);
        }
    }

    private static Object internalName(Type type) {
        if (Type.BYTE_TYPE.equals(type)) { return Opcodes.INTEGER; }
        if (Type.BOOLEAN_TYPE.equals(type)) { return Opcodes.INTEGER; }
        if (Type.CHAR_TYPE.equals(type)) { return Opcodes.INTEGER; }
        if (Type.SHORT_TYPE.equals(type)) { return Opcodes.INTEGER; }
        if (Type.INT_TYPE.equals(type)) { return Opcodes.INTEGER; }
        if (LONG_TYPE.equals(type)) { return Opcodes.LONG; }
        if (Type.FLOAT_TYPE.equals(type)) { return Opcodes.FLOAT; }
        if (Type.DOUBLE_TYPE.equals(type)) { return Opcodes.DOUBLE; }

        return type.getInternalName();
    }

    public static int size(List<Type> types) {
        int size = 0;
        for (Type type : types) {
            size += size(type);
        }

        return size;
    }

    public static int size(Type[] types) {
        int size = 0;
        for (Type type : types) {
            size += size(type);
        }
        return size;
    }

    public static int size(Type type) {
        if (VOID_TYPE.equals(type)) { return 0; }
        if (LONG_TYPE.equals(type) || Type.DOUBLE_TYPE.equals(type)) { return 2; }
        return 1;
    }

    public static Object[] toInternalNames(List<Type> types) {
        final List<Object> objects = new ArrayList<Object>(types.size());

        for (Type type : types) {
            if (VOID_TYPE.equals(type)) { continue; }
            objects.add(internalName(type));
        }

        return objects.toArray();
    }

    public static String target(String name) {
        return "track$" + name;
    }

    public static void enhance(ClassVisitor cw, String monitorName, final String internalName, final int version, int access,
                               String name, String desc, String signature, String[] exceptions)
    {
        enhance(cw, monitorName, internalName, version, access, name, desc, signature, exceptions, false);
    }

    public static void enhance(ClassVisitor cw, String monitorName, final String internalName, final int version, int access,
                               String name, String desc, String signature, String[] exceptions, final boolean track)
    {
        final MethodVisitor mv = visit(cw, version, access, name, desc, signature, exceptions);

        mv.visitEnd();
    }

    public static MethodVisitor visit(ClassVisitor cw, int version, int access,
                                      String name, String desc, String signature, String[] exceptions)
    {
        return null;
    }
}
