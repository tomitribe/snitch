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

import com.tomitribe.snitch.Filter;
import com.tomitribe.snitch.Method;
import com.tomitribe.snitch.track.Enhance;
import com.tomitribe.snitch.util.AsmModifiers;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version $Revision$ $Date$
 */
public class InsertListenerEnhancer extends ClassVisitor implements Opcodes {

    private final Filter<Type> filter;
    private String classInternalName;

    public InsertListenerEnhancer(ClassVisitor classVisitor, final Filter<Type> filter) {
        super(Opcodes.ASM4, classVisitor);
        this.filter = filter;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.classInternalName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitEnd() {
        filter.end();
        super.visitEnd();
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        final MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);

        final Method method = Method.fromDescriptor(name, desc, classInternalName);
        final Type listener = filter.accept(method);

        if (listener == null) {

            return methodVisitor;

        } else {

            return new MethodVisitor(Opcodes.ASM4, methodVisitor) {
                @Override
                public void visitCode() {

                    final boolean isStatic = AsmModifiers.isStatic(access);

                    final Type[] argumentTypes = getArgumentTypes();
                    final List<Type> variables = new ArrayList<Type>(Arrays.asList(argumentTypes));

                    if (isStatic) {
                        /**
                         * When the method being watched is static, pass a null instead of
                         * a "this" reference.
                         */
                        variables.remove(0);
                        this.visitInsn(ACONST_NULL);
                    }

                    Enhance.load(variables, this);

                    final String methodDescriptor = Type.getMethodDescriptor(Type.VOID_TYPE, argumentTypes);

                    this.visitMethodInsn(INVOKESTATIC, listener.getInternalName(), name, methodDescriptor);
                    super.visitCode();
                }

                /**
                 * Listener method signature will be identical to the method being intercepted,
                 * but with the object instance prefixed to the arguments.
                 *
                 * For example, Blue has a method being watched and AnyListener is doing the watching.
                 *
                 * Blue.class:
                 *
                 *  - public void doSomething(int, boolean, URI);
                 *
                 * AnyListener.class:
                 *
                 *  - public static void doSomething(Blue, int, boolean, URI);
                 *
                 */
                private Type[] getArgumentTypes() {
                    final Type[] original = Type.getArgumentTypes(desc);
                    final Type[] expanded = new Type[original.length + 1];

                    expanded[0] = Type.getObjectType(classInternalName);
                    System.arraycopy(original, 0, expanded, 1, original.length);

                    return expanded;
                }
            };
        }
    }
}
