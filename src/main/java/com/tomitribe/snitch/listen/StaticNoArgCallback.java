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
import org.objectweb.asm.ClassWriter;

import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.function.Function;

public class StaticNoArgCallback implements Function<byte[], byte[]> {

    private final Method find;
    private final Method insert;
    private final boolean check;

    private StaticNoArgCallback(final Method find, final Method insert) {
        this(find, insert, false);
    }

    private StaticNoArgCallback(final Method find, final Method insert, final boolean check) {
        Objects.requireNonNull(find);
        Objects.requireNonNull(insert);

        if (insert.getArguments().length != 0) {
            throw new IllegalArgumentException("Insert method must have no arguments.  Found: " + Join.join(", ", (Object[]) insert.getArguments()));
        }

        this.find = find;
        this.insert = insert;
        this.check = check;
    }

    @Override
    public byte[] apply(final byte[] bytes) {
        final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        final InsertStaticCallVisitor classAdapter = new InsertStaticCallVisitor(cw, find, insert);
        Bytecode.read(bytes, classAdapter);

        if (check && classAdapter.getFound() == 0) {
            throw new IllegalArgumentException(find.toString() + " method was not found");
        }

        return cw.toByteArray();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        Method find;
        Method insert;

        boolean check;

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
            // As this is a reflection method, we can check that it is suitable for weaving

            if (! Modifier.isStatic(method.getModifiers())) {
                throw new IllegalArgumentException("Insert method must be static.");
            }

            if (Modifier.isPrivate(method.getModifiers())) {
                throw new IllegalArgumentException("Insert method must not be private");
            }

            if (method.getParameters().length > 0) {
                throw new IllegalArgumentException("Insert method must have no arguments.");
            }

            this.insert = new Method(method);
            return this;
        }

        public Builder check() {
            this.check = true;
            return this;
        }

        public Builder check(final boolean check) {
            this.check = check;
            return this;
        }

        public Builder withoutCheck() {
            this.check = false;
            return this;
        }

        public Function<byte[], byte[]> build() {
            return new StaticNoArgCallback(find, insert, check);
        }
    }
}
