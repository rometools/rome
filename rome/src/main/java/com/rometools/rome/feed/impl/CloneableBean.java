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

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides deep <b>Bean</b> clonning support.
 * <p>
 * It works on all read/write properties, recursively. It support all primitive types, Strings,
 * Collections, Cloneable objects and multi-dimensional arrays of any of them.
 */
public class CloneableBean {

    private static final Logger LOG = LoggerFactory.getLogger(CloneableBean.class);

    private static final Set<Class<?>> BASIC_TYPES = new HashSet<Class<?>>();
    private static final Class<?>[] NO_PARAMS_DEF = new Class[0];
    private static final Object[] NO_PARAMS = new Object[0];

    static {
        BASIC_TYPES.add(Boolean.class);
        BASIC_TYPES.add(Byte.class);
        BASIC_TYPES.add(Character.class);
        BASIC_TYPES.add(Double.class);
        BASIC_TYPES.add(Float.class);
        BASIC_TYPES.add(Integer.class);
        BASIC_TYPES.add(Long.class);
        BASIC_TYPES.add(Short.class);
        BASIC_TYPES.add(String.class);
    }

    private CloneableBean() {
    }

    /**
     * Makes a deep bean clone of the object passed in the constructor.
     * <p>
     * To be used by classes using CloneableBean in a delegation pattern,
     *
     * @return a clone of the object bean.
     * @throws CloneNotSupportedException thrown if the object bean could not be cloned.
     *
     */
    public static Object beanClone(Object obj, Set<String> ignoreProperties) throws CloneNotSupportedException {

        final Class<?> clazz = obj.getClass();

        try {

            final Object clonedBean = clazz.getDeclaredConstructor().newInstance();

            final List<PropertyDescriptor> propertyDescriptors = BeanIntrospector.getPropertyDescriptorsWithGettersAndSetters(clazz);
            for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {

                final String propertyName = propertyDescriptor.getName();

                final boolean ignoredProperty = ignoreProperties.contains(propertyName);

                if (!ignoredProperty) {

                    final Method getter = propertyDescriptor.getReadMethod();
                    final Method setter = propertyDescriptor.getWriteMethod();

                    Object value = getter.invoke(obj, NO_PARAMS);
                    if (value != null) {
                        value = doClone(value);
                        setter.invoke(clonedBean, new Object[] { value });
                    }

                }

            }

            return clonedBean;

        } catch (final CloneNotSupportedException e) {
            LOG.error("Error while cloning bean", e);
            throw e;
        } catch (final Exception e) {
            LOG.error("Error while cloning bean", e);
            throw new CloneNotSupportedException("Cannot clone a " + clazz + " object");
        }

    }

    @SuppressWarnings("unchecked")
    private static <T> T doClone(T value) throws Exception {
        if (value != null) {
            final Class<?> vClass = value.getClass();
            if (vClass.isArray()) {
                value = cloneArray(value);
            } else if (value instanceof Collection) {
                value = (T) cloneCollection((Collection<Object>) value);
            } else if (value instanceof Map) {
                value = (T) cloneMap((Map<Object, Object>) value);
            } else if (isBasicType(vClass)) {
                // NOTHING SPECIAL TO DO HERE, THEY ARE INMUTABLE
            } else if (value instanceof LocalDateTime) {
                // NOTHING SPECIAL TO DO HERE, THEY ARE INMUTABLE
            } else if (value instanceof Cloneable) {
                final Method cloneMethod = vClass.getMethod("clone", NO_PARAMS_DEF);
                if (Modifier.isPublic(cloneMethod.getModifiers())) {
                    value = (T) cloneMethod.invoke(value, NO_PARAMS);
                } else {
                    throw new CloneNotSupportedException("Cannot clone a " + value.getClass() + " object, clone() is not public");
                }
            } else {
                throw new CloneNotSupportedException("Cannot clone a " + vClass.getName() + " object");
            }
        }
        return value;
    }

    private static <T> T cloneArray(final T array) throws Exception {
        final Class<?> elementClass = array.getClass().getComponentType();
        final int length = Array.getLength(array);
        @SuppressWarnings("unchecked")
        final T newArray = (T) Array.newInstance(elementClass, length);
        for (int i = 0; i < length; i++) {
            Array.set(newArray, i, doClone(Array.get(array, i)));
        }
        return newArray;
    }

    private static <T> Collection<T> cloneCollection(final Collection<T> collection) throws Exception {
        @SuppressWarnings("unchecked")
        final Collection<T> newCollection = newCollection(collection.getClass());
        for (final T item : collection) {
            newCollection.add(doClone(item));
        }
        return newCollection;
    }

    private static <T extends Collection<E>, E> Collection<E> newCollection(Class<T> type)
        throws InstantiationException, IllegalAccessException, IllegalArgumentException, 
        InvocationTargetException, NoSuchMethodException, SecurityException {
        Collection<E> collection;
        if (SortedSet.class.isAssignableFrom(type)) {
            collection = new TreeSet<E>();
        } else if (Set.class.isAssignableFrom(type)) {
            collection = new HashSet<E>();
        } else if (List.class.isAssignableFrom(type)) {
            collection = new ArrayList<E>();
        } else {
            collection = type.getDeclaredConstructor().newInstance();
        }
        return collection;
    }

    private static <K, V> Map<K, V> cloneMap(final Map<K, V> map) throws Exception {
        @SuppressWarnings("unchecked")
        final Map<K, V> newMap = newMap(map.getClass());
        for (final Entry<K, V> entry : map.entrySet()) {
            final K clonedKey = doClone(entry.getKey());
            final V clonedValue = doClone(entry.getValue());
            newMap.put(clonedKey, clonedValue);
        }
        return newMap;
    }

    private static <T extends Map<K, V>, K, V> Map<K, V> newMap(Class<T> type)
        throws InstantiationException, IllegalAccessException {
        Map<K, V> map;
        if (SortedMap.class.isAssignableFrom(type)) {
            map = new TreeMap<K, V>();
        } else {
            map = new HashMap<K, V>();
        }
        return map;
    }

    private static boolean isBasicType(final Class<?> vClass) {
        return BASIC_TYPES.contains(vClass);
    }

}
