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

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides deep <b>Bean</b> clonning support.
 * <p>
 * It works on all read/write properties, recursively. It support all primitive types, Strings,
 * Collections, Cloneable objects and multi-dimensional arrays of any of them.
 */
public class CloneableBean implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(CloneableBean.class);

    private static final Set<Class<?>> BASIC_TYPES = new HashSet<Class<?>>();
    private static final Class<?>[] NO_PARAMS_DEF = new Class[0];
    private static final Object[] NO_PARAMS = new Object[0];

    private final Object obj;
    private Set<String> ignoreProperties;

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

    /**
     * Default constructor.
     * <p>
     * To be used by classes extending CloneableBean only.
     * <p>
     *
     */
    protected CloneableBean() {
        obj = this;
    }

    /**
     * Creates a CloneableBean to be used in a delegation pattern.
     * <p>
     * For example:
     * <p>
     * <code>
     *   public class Foo implements Cloneable {
     *       private CloneableBean cloneableBean;
     * 
     *       public Foo() {
     *           cloneableBean = new CloneableBean(this);
     *       }
     * 
     *       public Object clone() throws CloneNotSupportedException {
     *           return cloneableBean.beanClone();
     *       }
     * 
     *   }
     * </code>
     * <p>
     *
     * @param obj object bean to clone.
     *
     */
    public CloneableBean(final Object obj) {
        this(obj, null);
    }

    /**
     * Creates a CloneableBean to be used in a delegation pattern.
     * <p>
     * The property names in the ignoreProperties Set will not be copied into the cloned instance.
     * This is useful for cases where the Bean has convenience properties (properties that are
     * actually references to other properties or properties of properties). For example SyndFeed
     * and SyndEntry beans have convenience properties, publishedDate, author, copyright and
     * categories all of them mapped to properties in the DC Module.
     * <p>
     *
     * @param obj object bean to clone.
     * @param ignoreProperties properties to ignore when cloning.
     *
     */
    public CloneableBean(final Object obj, final Set<String> ignoreProperties) {
        this.obj = obj;
        if (ignoreProperties == null) {
            this.ignoreProperties = Collections.<String> emptySet();
        } else {
            this.ignoreProperties = ignoreProperties;
        }
    }

    /**
     * Makes a deep bean clone of the object.
     * <p>
     * To be used by classes extending CloneableBean. Although it works also for classes using
     * CloneableBean in a delegation pattern, for correctness those classes should use the
     *
     * @see #beanClone() beanClone method.
     *      <p>
     * @return a clone of the object bean.
     * @throws CloneNotSupportedException thrown if the object bean could not be cloned.
     *
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return beanClone();
    }

    /**
     * Makes a deep bean clone of the object passed in the constructor.
     * <p>
     * To be used by classes using CloneableBean in a delegation pattern,
     *
     * @see #CloneableBean(Object) constructor.
     *
     * @return a clone of the object bean.
     * @throws CloneNotSupportedException thrown if the object bean could not be cloned.
     *
     */
    public Object beanClone() throws CloneNotSupportedException {

        final Class<? extends Object> clazz = obj.getClass();

        try {

            final Object clonedBean = clazz.newInstance();

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
    private <T extends Object> T doClone(T value) throws Exception {
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

    private <T> T cloneArray(final T array) throws Exception {
        final Class<?> elementClass = array.getClass().getComponentType();
        final int length = Array.getLength(array);
        @SuppressWarnings("unchecked")
        final T newArray = (T) Array.newInstance(elementClass, length);
        for (int i = 0; i < length; i++) {
            Array.set(newArray, i, doClone(Array.get(array, i)));
        }
        return newArray;
    }

    private <T> Collection<T> cloneCollection(final Collection<T> collection) throws Exception {
        @SuppressWarnings("unchecked")
        final Collection<T> newCollection = collection.getClass().newInstance();
        for (final T item : collection) {
            newCollection.add(doClone(item));
        }
        return newCollection;
    }

    private <K, V> Map<K, V> cloneMap(final Map<K, V> map) throws Exception {
        @SuppressWarnings("unchecked")
        final Map<K, V> newMap = map.getClass().newInstance();
        for (final Entry<K, V> entry : map.entrySet()) {
            final K clonedKey = doClone(entry.getKey());
            final V clonedValue = doClone(entry.getValue());
            newMap.put(clonedKey, clonedValue);
        }
        return newMap;
    }

    private boolean isBasicType(final Class<?> vClass) {
        return BASIC_TYPES.contains(vClass);
    }

}
