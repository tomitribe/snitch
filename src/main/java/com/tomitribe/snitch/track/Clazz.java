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

import com.tomitribe.snitch.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @version $Revision$ $Date$
 */
public class Clazz {

    private final String name;
    private final Map<Method, Monitor> time = new HashMap<Method, Monitor>();
    private final Map<Method, Monitor> track = new HashMap<Method, Monitor>();

    public Clazz(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getInternalName() {
        return name.replace('.', '/');
    }

    public Map<Method, Monitor> getTime() {
        return new HashMap<Method, Monitor>(time);
    }

    public Map<Method, Monitor> getTrack() {
        return new HashMap<Method, Monitor>(track);
    }

    public Monitor time(final Method method) {
        return time.get(method);
    }

    public Monitor time(final Method method, final String monitor) {
        return time.put(method, new Monitor(monitor, method));
    }

    public Monitor track(final Method method) {
        return track.get(method);
    }

    public Monitor track(final Method method, final String monitor) {
        return track.put(method, new Monitor(monitor, method));
    }

    public boolean shouldTrack() {
        return track.size() > 0;
    }

    public boolean shouldTime() {
        return time.size() > 0;
    }
}
