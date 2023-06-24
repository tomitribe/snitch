/*
 * Tomitribe Confidential
 *
 * Copyright Tomitribe Corporation. 2023
 *
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 */
package com.tomitribe.snitch.listen;

import com.tomitribe.snitch.Method;
import com.tomitribe.snitch.track.Bytecode;
import com.tomitribe.snitch.util.Join;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.util.Objects;
import java.util.function.Function;

public class StaticNoArgCallback implements Function<byte[], byte[]> {

    private final Method find;
    private final Method insert;

    private StaticNoArgCallback(final Method find, final Method insert) {
        Objects.requireNonNull(find);
        Objects.requireNonNull(insert);

        if (insert.getArguments().length != 0) {
            throw new IllegalArgumentException("Insert method must have no arguments.  Found: " + Join.join(", ", (Object[]) insert.getArguments()));
        }

        this.find = find;
        this.insert = insert;
    }

    @Override
    public byte[] apply(final byte[] bytes) {
        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        final ClassVisitor classAdapter = new InsertStaticCallVisitor(cw, find, insert);
        Bytecode.read(bytes, classAdapter);
        return cw.toByteArray();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        Method find;
        Method insert;

        public Builder find(final Method method) {
            this.find = method;
            return this;
        }

        public Builder find(final String methodToString) {
            this.find = Method.fromToString(methodToString);
            return this;
        }

        public Builder find(final java.lang.reflect.Method method) {
            this.find = new Method(method);
            return this;
        }

        public Builder insert(final Class<?> clazz, final String method) {
            this.insert = Method.fromDescriptor(method, "()V", clazz.getName());
            return this;
        }

        public Builder insert(final String methodToString) {
            this.insert = Method.fromToString(methodToString);
            return this;
        }

        public Builder insert(final java.lang.reflect.Method method) {
            this.insert = new Method(method);
            return this;
        }

        public Function<byte[], byte[]> build() {
            return new StaticNoArgCallback(find, insert);
        }
    }
}
