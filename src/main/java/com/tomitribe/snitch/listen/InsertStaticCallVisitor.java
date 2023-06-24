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

import com.tomitribe.snitch.Method;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @version $Revision$ $Date$
 */
public class InsertStaticCallVisitor extends ClassVisitor implements Opcodes {
    private String className;
    private final Method find;
    private final Method insert;

    public InsertStaticCallVisitor(final ClassWriter classVisitor, final Method find, final Method insert) {
        super(Opcodes.ASM5, classVisitor);
        this.find = find;
        this.insert = insert;
    }

    @Override
    public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces) {
        this.className = name.replace("/", ".");
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        final MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);

        final Method method = Method.fromDescriptor(name, desc, className);

        if (!method.equals(find)) {
            return methodVisitor;
        }

        return new MethodVisitor(Opcodes.ASM5, methodVisitor) {
            @Override
            public void visitCode() {
                final String internalName = insert.getClassName().replace('.', '/');
                this.visitMethodInsn(INVOKESTATIC, internalName, insert.getMethodName(), "()V");
                super.visitCode();
            }
        };
    }
}
