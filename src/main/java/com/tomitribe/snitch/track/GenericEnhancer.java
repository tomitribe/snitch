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

import com.tomitribe.snitch.Filter;
import com.tomitribe.snitch.Method;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Map;

/**
 * @version $Revision$ $Date$
 */
public class GenericEnhancer extends ClassVisitor implements Opcodes {

    private final Filter<String> filter;

    private String classInternalName;
    private int version;
    private final boolean track;

    public GenericEnhancer(final ClassVisitor classVisitor, final Filter<String> filter) {
        this(classVisitor, filter, false);
    }

    public GenericEnhancer(final ClassVisitor classVisitor, final Filter<String> filter, final boolean track) {
        super(Opcodes.ASM5, classVisitor);
        this.filter = filter;
        this.track = track;
    }

    @Override
    public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces) {
        this.classInternalName = name;
        this.version = version;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitEnd() {
        filter.end();
        super.visitEnd();
    }

    @Override
    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {

        try {
            final Method method = Method.fromDescriptor(name, desc, classInternalName);
            final String monitor = filter.accept(method);
            if (monitor != null) {
                final MethodVisitor newMethod = Enhance.visit(cv, monitor, classInternalName, version, access, name, desc, signature, exceptions, track);
                final MethodVisitor movedMethod = super.visitMethod(access, Enhance.target(name), desc, signature, exceptions);

                return new MoveAnnotationsVisitor(movedMethod, newMethod);
            } else {
                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        } catch (final RuntimeException e) {
            Log.err("Enhance failed %s %s %s", classInternalName, name, desc);
            throw e;
        }
    }

    public static class MoveAnnotationsVisitor extends MethodVisitor {

        private final MethodVisitor newMethod;

        public MoveAnnotationsVisitor(final MethodVisitor movedMethod, final MethodVisitor newMethod) {
            super(Opcodes.ASM5, movedMethod);
            this.newMethod = newMethod;
        }

        @Override
        public AnnotationVisitor visitAnnotation(final String desc, final boolean visible) {
            return newMethod.visitAnnotation(desc, visible);
        }

        @Override
        public AnnotationVisitor visitParameterAnnotation(final int parameter, final String desc, final boolean visible) {
            return newMethod.visitParameterAnnotation(parameter, desc, visible);
        }

        @Override
        public void visitEnd() {
            newMethod.visitEnd();
            super.visitEnd();
        }
    }

    public static class MethodFilter implements Filter<String> {

        private final Map<Method, Monitor> methods;

        public MethodFilter(final Map<Method, Monitor> methods) {
            this.methods = methods;
        }

        @Override
        public String accept(final Method method) {
            final Monitor monitor = methods.remove(method);
            if (monitor != null) {
                return monitor.getName();
            }
            return null;
        }

        @Override
        public void end() {
            for (final Map.Entry<Method, Monitor> unused : methods.entrySet()) {
                Log.err("No Such Method: %s = %s", unused.getValue().getName(), unused.getKey());
            }
        }
    }
}
