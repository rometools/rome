/*
 * Copyright 2004 Sun Microsystems, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.sun.syndication.feed.impl;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Obtains all property descriptors from a bean (interface or implementation).
 * <p>
 * The java.beans.Introspector does not process the interfaces hierarchy chain,
 * this one does.
 * <p>
 * 
 * @author Alejandro Abdelnur
 * 
 */
public class BeanIntrospector {

    private static final Map<Class<?>, PropertyDescriptor[]> introspected = new HashMap<Class<?>, PropertyDescriptor[]>();

    public static synchronized PropertyDescriptor[] getPropertyDescriptors(final Class<?> klass) throws IntrospectionException {
        PropertyDescriptor[] descriptors = introspected.get(klass);
        if (descriptors == null) {
            descriptors = getPDs(klass);
            introspected.put(klass, descriptors);
        }
        return descriptors;
    }

    private static PropertyDescriptor[] getPDs(final Class<?> klass) throws IntrospectionException {
        final Method[] methods = klass.getMethods();
        final Map<String, PropertyDescriptor> getters = getPDs(methods, false);
        final Map<String, PropertyDescriptor> setters = getPDs(methods, true);
        final List<PropertyDescriptor> pds = merge(getters, setters);
        final PropertyDescriptor[] array = new PropertyDescriptor[pds.size()];
        pds.toArray(array);
        return array;
    }

    private static final String SETTER = "set";
    private static final String GETTER = "get";
    private static final String BOOLEAN_GETTER = "is";

    private static Map<String, PropertyDescriptor> getPDs(final Method[] methods, final boolean setters) throws IntrospectionException {
        final Map<String, PropertyDescriptor> pds = new HashMap<String, PropertyDescriptor>();
        for (final Method method : methods) {
            String pName = null;
            PropertyDescriptor pDescriptor = null;
            if ((method.getModifiers() & Modifier.PUBLIC) != 0) {
                if (setters) {
                    if (method.getName().startsWith(SETTER) && method.getReturnType() == void.class && method.getParameterTypes().length == 1) {
                        pName = Introspector.decapitalize(method.getName().substring(3));
                        pDescriptor = new PropertyDescriptor(pName, null, method);
                    }
                } else {
                    if (method.getName().startsWith(GETTER) && method.getReturnType() != void.class && method.getParameterTypes().length == 0) {
                        pName = Introspector.decapitalize(method.getName().substring(3));
                        pDescriptor = new PropertyDescriptor(pName, method, null);
                    } else if (method.getName().startsWith(BOOLEAN_GETTER) && method.getReturnType() == boolean.class && method.getParameterTypes().length == 0) {
                        pName = Introspector.decapitalize(method.getName().substring(2));
                        pDescriptor = new PropertyDescriptor(pName, method, null);
                    }
                }
            }
            if (pName != null) {
                pds.put(pName, pDescriptor);
            }
        }
        return pds;
    }

    private static List<PropertyDescriptor> merge(final Map<String, PropertyDescriptor> getters, final Map<String, PropertyDescriptor> setters)
            throws IntrospectionException {
        final List<PropertyDescriptor> props = new ArrayList<PropertyDescriptor>();
        final Set<String> processedProps = new HashSet<String>();
        final Iterator<String> gs = getters.keySet().iterator();
        while (gs.hasNext()) {
            final String name = gs.next();
            final PropertyDescriptor getter = getters.get(name);
            final PropertyDescriptor setter = setters.get(name);
            if (setter != null) {
                processedProps.add(name);
                final PropertyDescriptor prop = new PropertyDescriptor(name, getter.getReadMethod(), setter.getWriteMethod());
                props.add(prop);
            } else {
                props.add(getter);
            }
        }
        final Set<String> writeOnlyProps = new HashSet<String>(setters.keySet());
        writeOnlyProps.removeAll(processedProps);
        final Iterator<String> ss = writeOnlyProps.iterator();
        while (ss.hasNext()) {
            final String name = ss.next();
            final PropertyDescriptor setter = setters.get(name);
            props.add(setter);
        }
        return props;
    }

}
