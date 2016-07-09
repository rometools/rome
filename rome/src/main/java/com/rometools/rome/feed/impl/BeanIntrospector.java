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
package com.rometools.rome.feed.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Obtains all property descriptors from a bean (interface or implementation).
 * <p>
 * The java.beans.Introspector does not process the interfaces hierarchy chain, this one does.
 */
public class BeanIntrospector {

    private static final Map<Class<?>, PropertyDescriptor[]> introspected = new HashMap<Class<?>, PropertyDescriptor[]>();
    private static final String SETTER = "set";
    private static final String GETTER = "get";
    private static final String BOOLEAN_GETTER = "is";

    private BeanIntrospector() {
    }

    /**
     * Extract all {@link PropertyDescriptor}s for properties with getters and setters for the given
     * class.
     *
     * @param clazz The class to extract the desired {@link PropertyDescriptor}s from
     * @return All {@link PropertyDescriptor} for properties with getters and setters for the given
     *         class.
     */
    private static synchronized PropertyDescriptor[] getPropertyDescriptors(final Class<?> clazz) {
        PropertyDescriptor[] descriptors = introspected.get(clazz);
        if (descriptors == null) {
            descriptors = getPDs(clazz);
            introspected.put(clazz, descriptors);
        }
        return descriptors;
    }

    /**
     * Extract all {@link PropertyDescriptor}s for properties with a getter that does not come from
     * {@link Object} and does not accept parameters.
     *
     * @param clazz The class to extract the desired {@link PropertyDescriptor}s from
     * @return All {@link PropertyDescriptor}s for properties with a getter that does not come from
     *         {@link Object} and does not accept parameters.
     */
    public static List<PropertyDescriptor> getPropertyDescriptorsWithGetters(final Class<?> clazz) {

        final List<PropertyDescriptor> relevantDescriptors = new ArrayList<PropertyDescriptor>();

        final PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
        if (propertyDescriptors != null) {
            for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {

                final Method getter = propertyDescriptor.getReadMethod();
                final boolean getterExists = getter != null;

                if (getterExists) {

                    final boolean getterFromObject = getter.getDeclaringClass() == Object.class;
                    final boolean getterWithoutParams = getter.getParameterTypes().length == 0;

                    if (!getterFromObject && getterWithoutParams) {
                        relevantDescriptors.add(propertyDescriptor);
                    }

                }

            }
        }

        return relevantDescriptors;

    }

    /**
     * Extract all {@link PropertyDescriptor}s for properties with a getter (that does not come from
     * {@link Object} and does not accept parameters) and a setter.
     *
     * @param clazz The class to extract the desired {@link PropertyDescriptor}s from
     * @return All {@link PropertyDescriptor}s for properties with a getter (that does not come from
     *         {@link Object} and does not accept parameters) and a setter.
     */
    public static List<PropertyDescriptor> getPropertyDescriptorsWithGettersAndSetters(final Class<?> clazz) {

        final List<PropertyDescriptor> relevantDescriptors = new ArrayList<PropertyDescriptor>();

        final List<PropertyDescriptor> propertyDescriptors = getPropertyDescriptorsWithGetters(clazz);
        for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {

            final Method setter = propertyDescriptor.getWriteMethod();
            final boolean setterExists = setter != null;

            if (setterExists) {
                relevantDescriptors.add(propertyDescriptor);
            }

        }

        return relevantDescriptors;

    }

    private static PropertyDescriptor[] getPDs(final Class<?> clazz) {
        final Method[] methods = clazz.getMethods();
        final Map<String, PropertyDescriptor> getters = getPDs(methods, false);
        final Map<String, PropertyDescriptor> setters = getPDs(methods, true);
        final List<PropertyDescriptor> propertyDescriptors = merge(getters, setters);
        return propertyDescriptors.toArray(new PropertyDescriptor[propertyDescriptors.size()]);
    }

    private static Map<String, PropertyDescriptor> getPDs(final Method[] methods, final boolean setters) {

        final Map<String, PropertyDescriptor> pds = new HashMap<String, PropertyDescriptor>();

        for (final Method method : methods) {

            String propertyName = null;
            PropertyDescriptor propertyDescriptor = null;

            final int modifiers = method.getModifiers();
            if ((modifiers & Modifier.PUBLIC) != 0) {

                final String methodName = method.getName();
                final Class<?> returnType = method.getReturnType();
                final int nrOfParameters = method.getParameterTypes().length;

                if (setters) {
                    if (methodName.startsWith(SETTER) && returnType == void.class && nrOfParameters == 1) {
                        propertyName = decapitalize(methodName.substring(3));
                        propertyDescriptor = new PropertyDescriptor(propertyName, null, method);
                    }
                } else {
                    if (methodName.startsWith(GETTER) && returnType != void.class && nrOfParameters == 0) {
                        propertyName = decapitalize(methodName.substring(3));
                        propertyDescriptor = new PropertyDescriptor(propertyName, method, null);
                    } else if (methodName.startsWith(BOOLEAN_GETTER) && returnType == boolean.class && nrOfParameters == 0) {
                        propertyName = decapitalize(methodName.substring(2));
                        propertyDescriptor = new PropertyDescriptor(propertyName, method, null);
                    }
                }
            }

            if (propertyName != null) {
                pds.put(propertyName, propertyDescriptor);
            }

        }

        return pds;

    }

    private static List<PropertyDescriptor> merge(final Map<String, PropertyDescriptor> getters, final Map<String, PropertyDescriptor> setters) {

        final List<PropertyDescriptor> props = new ArrayList<PropertyDescriptor>();
        final Set<String> processedProps = new HashSet<String>();

        for (final String propertyName : getters.keySet()) {
            final PropertyDescriptor getter = getters.get(propertyName);
            final PropertyDescriptor setter = setters.get(propertyName);
            if (setter != null) {
                processedProps.add(propertyName);
                final PropertyDescriptor prop = new PropertyDescriptor(propertyName, getter.getReadMethod(), setter.getWriteMethod());
                props.add(prop);
            } else {
                props.add(getter);
            }
        }

        final Set<String> writeOnlyProperties = new HashSet<String>();
        writeOnlyProperties.removeAll(processedProps);

        for (final String propertyName : writeOnlyProperties) {
            final PropertyDescriptor setter = setters.get(propertyName);
            props.add(setter);
        }

        return props;
    }

    /**
     * Make first character lower case unless the second character is upper case.
     */
    private static String decapitalize(String name) {
        if (name.isEmpty() || (name.length() > 1 && Character.isUpperCase(name.charAt(1)))) {
            return name;
        }

        char[] chars = name.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }
}
