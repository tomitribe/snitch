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
import org.objectweb.asm.Type;

import java.util.HashMap;
import java.util.Map;

/**
 * @version $Revision$ $Date$
 */
public class Clazz {

    private final String name;
    private final Map<Method, Type> listeners = new HashMap<Method, Type>();

    public Clazz(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getInternalName() {
        return name.replace('.', '/');
    }

    public Map<Method, Type> getListeners() {
        return new HashMap<Method, Type>(listeners);
    }

    public Type getListener(Method method) {
        return listeners.get(method);
    }

    public Type setListener(Method method, Type listener) {
        return listeners.put(method, listener);
    }

    public boolean shouldListen() {
        return listeners.size() > 0;
    }
}
