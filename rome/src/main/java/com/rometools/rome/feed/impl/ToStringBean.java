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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides deep <b>Bean</b> toString support.
 * <p>
 * It works on all read/write properties, recursively. It support all primitive types, Strings,
 * Collections, ToString objects and multi-dimensional arrays of any of them.
 */
public class ToStringBean {

    private static final Logger LOG = LoggerFactory.getLogger(ToStringBean.class);

    private static final ThreadLocal<Stack<String[]>> PREFIX_TL = new ThreadLocal<Stack<String[]>>();

    private static final Object[] NO_PARAMS = new Object[0];

    private ToStringBean() {
    }

    /**
     * Returns the String representation of the bean given in the constructor.
     * <p>
     * It uses the Class name as the prefix.
     * <p>
     *
     * @return bean object String representation.
     *
     */
    public static String toString(Class<?> beanClass, Object obj) {
        Stack<String[]> stack = PREFIX_TL.get();
        boolean needStackCleanup = false;

        if (stack == null) {
            stack = new Stack<String[]>();
            PREFIX_TL.set(stack);
            needStackCleanup = true;
        }

        final String[] tsInfo;
        if (stack.isEmpty()) {
            tsInfo = null;
        } else {
            tsInfo = stack.peek();
        }
        final String prefix;
        if (tsInfo == null) {
            final String className = obj.getClass().getName();
            prefix = className.substring(className.lastIndexOf(".") + 1);
        } else {
            prefix = tsInfo[0];
            tsInfo[1] = prefix;
        }

        final String result = toString(beanClass, obj, prefix);

        if (needStackCleanup) {
          PREFIX_TL.remove();
        }

        return result;
    }

    /**
     * Returns the String representation of the bean given in the constructor.
     * <p>
     *
     * @param prefix to use for bean properties.
     * @return bean object String representation.
     *
     */
    private static String toString(final Class<?> beanClass, final Object obj, final String prefix) {

        final StringBuffer sb = new StringBuffer(128);

        try {

            final List<PropertyDescriptor> propertyDescriptors = BeanIntrospector.getPropertyDescriptorsWithGetters(beanClass);
            for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {

                final String propertyName = propertyDescriptor.getName();
                final Method getter = propertyDescriptor.getReadMethod();

                final Object value = getter.invoke(obj, NO_PARAMS);
                printProperty(sb, prefix + "." + propertyName, value);

            }

        } catch (final Exception e) {
            LOG.error("Error while generating toString", e);
            final Class<?> clazz = obj.getClass();
            final String errorMessage = e.getMessage();
            sb.append(String.format("\n\nEXCEPTION: Could not complete %s.toString(): %s\n", clazz, errorMessage));
        }

        return sb.toString();
    }

    private static void printProperty(final StringBuffer sb, final String prefix, final Object value) {

        if (value == null) {

            sb.append(prefix).append("=null\n");

        } else if (value.getClass().isArray()) {

            printArrayProperty(sb, prefix, value);

        } else if (value instanceof Map) {

            @SuppressWarnings("unchecked")
            final Map<Object, Object> map = (Map<Object, Object>) value;
            final Set<Entry<Object, Object>> entries = map.entrySet();

            if (entries.isEmpty()) {

                sb.append(prefix).append("=[]\n");

            } else {

                for (final Entry<Object, Object> entry : entries) {

                    final Object eKey = entry.getKey();
                    final Object eValue = entry.getValue();
                    final String ePrefix = String.format("%s[%s]", prefix, eKey);

                    final String[] tsInfo = new String[2];
                    tsInfo[0] = ePrefix;
                    final Stack<String[]> stack = PREFIX_TL.get();
                    stack.push(tsInfo);
                    final String s;
                    if (eValue == null) {
                        s = "null";
                    } else {
                        s = eValue.toString();
                    }
                    stack.pop();
                    if (tsInfo[1] == null) {
                        sb.append(ePrefix).append("=").append(s).append("\n");
                    } else {
                        sb.append(s);
                    }

                }

            }

        } else if (value instanceof Collection) {

            @SuppressWarnings("unchecked")
            final Collection<Object> collection = (Collection<Object>) value;
            if (collection.isEmpty()) {

                sb.append(prefix).append("=[]\n");

            } else {

                int c = 0;

                for (final Object cValue : collection) {

                    final String cPrefix = String.format("%s[%s]", prefix, c++);

                    final String[] tsInfo = new String[2];
                    tsInfo[0] = cPrefix;
                    final Stack<String[]> stack = PREFIX_TL.get();
                    stack.push(tsInfo);
                    final String s;
                    if (cValue == null) {
                        s = "null";
                    } else {
                        s = cValue.toString();
                    }
                    stack.pop();
                    if (tsInfo[1] == null) {
                        sb.append(cPrefix).append("=").append(s).append("\n");
                    } else {
                        sb.append(s);
                    }
                }
            }

        } else {

            final String[] tsInfo = new String[2];
            tsInfo[0] = prefix;
            final Stack<String[]> stack = PREFIX_TL.get();
            stack.push(tsInfo);
            final String s = value.toString();
            stack.pop();
            if (tsInfo[1] == null) {
                sb.append(prefix).append("=").append(s).append("\n");
            } else {
                sb.append(s);
            }

        }
    }

    private static void printArrayProperty(final StringBuffer sb, final String prefix, final Object array) {
        final int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            final Object obj = Array.get(array, i);
            printProperty(sb, String.format("%s[%s]", prefix, i), obj);
        }
    }

}
