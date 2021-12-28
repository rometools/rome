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
import java.lang.reflect.Method;
import java.util.List;

/**
 * Provides deep <b>Bean</b> equals() and hashCode() functionality for Java Beans.
 * <p>
 * It works on all read/write properties, recursively. It support all primitive types, Strings,
 * Collections, bean-like objects and multi-dimensional arrays of any of them.
 * <p>
 * The hashcode is calculated by getting the hashcode of the Bean String representation.
 */
public class EqualsBean {

    private static final Object[] NO_PARAMS = new Object[0];

    private EqualsBean() {
    }

    /**
     * Indicates whether some other object is "equal to" the object passed in the constructor, as
     * defined by the Object equals() method.
     * <p>
     * To be used by classes using EqualsBean in a delegation pattern,
     *
     * @param obj1 The reference object with which to compare.
     * @param obj2 The object to which to compare.
     * @return <b>true</b> if the object passed in the constructor is equal to the 'obj' object.
     *
     */
    public static boolean beanEquals(Class<?> beanClass, final Object obj1, final Object obj2) {
        boolean eq;

        if (obj1 == null && obj2 == null) { // both are null
            eq = true;
        } else if (obj1 == null || obj2 == null) { // one of the objects is null
            eq = false;
        } else if (!beanClass.isInstance(obj2)) { // not of the same type
            eq = false;
        } else {
            eq = true;
            try {

                final List<PropertyDescriptor> propertyDescriptors = BeanIntrospector.getPropertyDescriptorsWithGetters(beanClass);
                for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {

                    final Method getter = propertyDescriptor.getReadMethod();

                    final Object value1 = getter.invoke(obj1, NO_PARAMS);
                    final Object value2 = getter.invoke(obj2, NO_PARAMS);

                    eq = doEquals(value1, value2);

                    if (!eq) {
                        break;
                    }

                }

            } catch (final Exception ex) {
                throw new RuntimeException("Could not execute equals()", ex);
            }

        }
        return eq;
    }

    /**
     * Returns the hashcode for the object passed in the constructor.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     * The hashcode is calculated by getting the hashcode of the Bean String representation.
     * <p>
     * To be used by classes using EqualsBean in a delegation pattern,
     *
     * @return the hashcode of the bean object.
     *
     */
    public static int beanHashCode(Object obj) {
        return obj.toString().hashCode();
    }

    private static boolean doEquals(final Object obj1, final Object obj2) {
        boolean eq = obj1 == obj2;
        if (!eq && obj1 != null && obj2 != null) {
            final Class<?> classObj1 = obj1.getClass();
            final Class<?> classObj2 = obj2.getClass();
            if (classObj1.isArray() && classObj2.isArray()) {
                eq = equalsArray(obj1, obj2);
            } else {
                eq = obj1.equals(obj2);
            }
        }
        return eq;
    }

    private static boolean equalsArray(final Object array1, final Object array2) {
        boolean eq;
        final int length1 = Array.getLength(array1);
        final int length2 = Array.getLength(array2);
        if (length1 == length2) {
            eq = true;
            for (int i = 0; eq && i < length1; i++) {
                final Object e1 = Array.get(array1, i);
                final Object e2 = Array.get(array2, i);
                eq = doEquals(e1, e2);
            }
        } else {
            eq = false;
        }
        return eq;
    }

}
