/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2023
 *
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 */
package com.tomitribe.snitch.annotate;

import com.tomitribe.snitch.ASM;
import com.tomitribe.snitch.track.Bytecode;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class AnnotateType implements Function<byte[], byte[]> {

    private final String annotationClassName;
    private final Map<String, Object> attributes = new LinkedHashMap<>();
    private final boolean visible;

    public AnnotateType(final String annotationClassName, final Map<String, Object> attributes, final boolean visible) {
        this.annotationClassName = annotationClassName;
        this.attributes.putAll(attributes);
        this.visible = visible;
    }

    @Override
    public byte[] apply(final byte[] bytes) {
        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        final ClassVisitor classAdapter = new AnnotateTypeVisitor(cw);
        Bytecode.read(bytes, classAdapter);
        return cw.toByteArray();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String annotationClassName;
        private final Map<String, Object> attributes = new LinkedHashMap<>();

        /**
         * Is this annotation visible at runtime?
         */
        private boolean visible;

        public Builder annotation(final String className) {
            this.annotationClassName = className;
            return this;
        }

        public Builder annotation(final Class<?> clazz) {
            this.annotationClassName = clazz.getName();
            return this;
        }

        public Builder visible(final boolean visible) {
            this.visible = visible;
            return this;
        }


        public Builder set(final String name, final String value) {
            return put(name, value);
        }

        public Builder set(final String name, final Class<?> value) {
            return put(name, value);
        }

        public Builder set(final String name, final Boolean value) {
            return put(name, value);
        }

        public Builder set(final String name, final Byte value) {
            return put(name, value);
        }

        public Builder set(final String name, final Character value) {
            return put(name, value);
        }

        public Builder set(final String name, final Short value) {
            return put(name, value);
        }

        public Builder set(final String name, final Integer value) {
            return put(name, value);
        }

        public Builder set(final String name, final Long value) {
            return put(name, value);
        }

        public Builder set(final String name, final Float value) {
            return put(name, value);
        }

        public Builder set(final String name, final Double value) {
            return put(name, value);
        }

        private Builder put(final String name, final Object value) {
            this.attributes.put(name, value);
            return this;
        }

        public AnnotateType build() {
            return new AnnotateType(annotationClassName, attributes, visible);
        }
    }

    /**
     * @version $Revision$ $Date$
     */
    class AnnotateTypeVisitor extends ClassVisitor implements Opcodes {

        public AnnotateTypeVisitor(final ClassWriter classVisitor) {
            super(ASM.VERSION, classVisitor);
        }

        @Override
        public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces) {
            super.visit(version, access, name, signature, superName, interfaces);

            final String s = annotationClassName.replace('.', '/');
            final String typeDescriptor = "L" + s + ";";
            final AnnotationVisitor annotationVisitor = this.visitAnnotation(typeDescriptor, visible);

            for (final Map.Entry<String, Object> entry : attributes.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof Class) {
                    final Class<?> aClass = (Class<?>) value;
                    value = Type.getType(aClass);
                }
                annotationVisitor.visit(entry.getKey(), value);
            }
            annotationVisitor.visitEnd();
        }
    }
}
