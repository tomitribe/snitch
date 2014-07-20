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

import com.tomitribe.snitch.util.AsmModifiers;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @version $Revision$ $Date$
 */
public class InsertListenerEnhancer extends ClassVisitor implements Opcodes {
    private String classInternalName;

    public InsertListenerEnhancer(ClassVisitor classVisitor) {
        super(Opcodes.ASM4, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.classInternalName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        final MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);

        if ("doIt".equals(name) || "doItStatic".equals(name)) {
            return new MethodVisitor(Opcodes.ASM4, methodVisitor) {
                @Override
                public void visitCode() {

                    final boolean isStatic = AsmModifiers.isStatic(access);

                    if (isStatic) {
                        this.visitInsn(ACONST_NULL);
                        this.visitVarInsn(ALOAD, 0);
                        this.visitVarInsn(ALOAD, 1);
                        this.visitMethodInsn(INVOKESTATIC, "com/tomitribe/snitch/listen/gen/BlueListener", name, getDescriptor());
                    } else {
                        this.visitVarInsn(ALOAD, 0);
                        this.visitVarInsn(ALOAD, 1);
                        this.visitVarInsn(ALOAD, 2);
                        this.visitMethodInsn(INVOKESTATIC, "com/tomitribe/snitch/listen/gen/BlueListener", name, getDescriptor());
                    }
                    super.visitCode();
                }

                private String getDescriptor() {
                    final List<Type> args = new ArrayList<Type>();
                    args.add(Type.getObjectType(classInternalName));
                    args.addAll(Arrays.asList(Type.getArgumentTypes(desc)));

                    return Type.getMethodDescriptor(Type.VOID_TYPE, args.toArray(new Type[args.size()]));
                }
            };
        } else {
            return methodVisitor;
        }
    }
}
