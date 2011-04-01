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

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.io.Serializable;

/**
 * Provides deep <b>Bean</b> toString support.
 * <p>
 * It works on all read/write properties, recursively. It support all primitive types, Strings, Collections,
 * ToString objects and multi-dimensional arrays of any of them.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class ToStringBean implements Serializable {
    private static final ThreadLocal PREFIX_TL = new ThreadLocal() {
        public Object get() {
            Object o = super.get();
            if (o==null) {
                o = new Stack();
                set(o);
            }
            return o;
        }
    };

    private static final Object[] NO_PARAMS = new Object[0];

    private Class _beanClass;
    private Object _obj;

    /**
     * Default constructor.
     * <p>
     * To be used by classes extending ToStringBean only.
     * <p>
     * @param beanClass indicates the class to scan for properties, normally an interface class.
     *
     */
    protected ToStringBean(Class beanClass) {
        _beanClass = beanClass;
        _obj = this;
    }

    /**
     * Creates a ToStringBean to be used in a delegation pattern.
     * <p>
     * For example:
     * <p>
     * <code>
     *   public class Foo implements ToString {
     *
     *       public String toString(String prefix) {
     *           ToStringBean tsb = new ToStringBean(this);
     *           return tsb.toString(prefix);
     *       }
     *
     *       public String toString() {
     *           return toString("Foo");
     *       }
     *
     *   }
     * </code>
     * <p>
     * @param beanClass indicates the class to scan for properties, normally an interface class.
     * @param obj object bean to create String representation.
     *
     */
    public ToStringBean(Class beanClass,Object obj) {
        _beanClass = beanClass;
        _obj = obj;
    }

    /**
     * Returns the String representation of the bean given in the constructor.
     * <p>
     * It uses the Class name as the prefix.
     * <p>
     * @return bean object String representation.
     *
     */
    public String toString() {
        Stack stack = (Stack) PREFIX_TL.get();
        String[] tsInfo = (String[]) ((stack.isEmpty()) ? null : stack.peek());
        String prefix;
        if (tsInfo==null) {
            String className = _obj.getClass().getName();
            prefix = className.substring(className.lastIndexOf(".")+1);
        }
        else {
            prefix = tsInfo[0];
            tsInfo[1] = prefix;
        }
        return toString(prefix);
    }

    /**
     * Returns the String representation of the bean given in the constructor.
     * <p>
     * @param prefix to use for bean properties.
     * @return bean object String representation.
     *
     */
    private String toString(String prefix) {
        StringBuffer sb = new StringBuffer(128);
        try {
            PropertyDescriptor[] pds = BeanIntrospector.getPropertyDescriptors(_beanClass);
            if (pds!=null) {
                for (int i=0;i<pds.length;i++) {
                    String pName = pds[i].getName();
                    Method pReadMethod = pds[i].getReadMethod();
                    if (pReadMethod!=null &&                             // ensure it has a getter method
                        pReadMethod.getDeclaringClass()!=Object.class && // filter Object.class getter methods
                        pReadMethod.getParameterTypes().length==0) {     // filter getter methods that take parameters
                        Object value = pReadMethod.invoke(_obj,NO_PARAMS);
                        printProperty(sb,prefix+"."+pName,value);
                    }
                }
            }
        }
        catch (Exception ex) {
            sb.append("\n\nEXCEPTION: Could not complete "+_obj.getClass()+".toString(): "+ex.getMessage()+"\n");
        }
        return sb.toString();
    }

    private void printProperty(StringBuffer sb,String prefix,Object value) {
        if (value==null) {
            sb.append(prefix).append("=null\n");
        }
        else
        if (value.getClass().isArray()) {
            printArrayProperty(sb,prefix,value);
        }
        else
        if (value instanceof Map) {
            Map map = (Map) value;
            Iterator i = map.entrySet().iterator();
            if (i.hasNext()) {
                while (i.hasNext()) {
                    Map.Entry me = (Map.Entry) i.next();
                    String ePrefix = prefix+"["+me.getKey()+"]";
                    Object eValue = me.getValue();

                    //NEW
                    String[] tsInfo = new String[2];
                    tsInfo[0] = ePrefix;
                    Stack stack = (Stack) PREFIX_TL.get();
                    stack.push(tsInfo);
                    String s = (eValue!=null) ? eValue.toString() : "null";
                    stack.pop();
                    if (tsInfo[1]==null) {
                        sb.append(ePrefix).append("=").append(s).append("\n");
                    }
                    else {
                        sb.append(s);
                    }
                }
            }
            else {
                sb.append(prefix).append("=[]\n");
            }
        }
        else
        if (value instanceof Collection) {
            Collection collection = (Collection) value;
            Iterator i = collection.iterator();
            if (i.hasNext()) {
                int c = 0;
                while (i.hasNext()) {
                    String cPrefix = prefix+"["+(c++)+"]";
                    Object cValue = i.next();

                    //NEW
                    String[] tsInfo = new String[2];
                    tsInfo[0] = cPrefix;
                    Stack stack = (Stack) PREFIX_TL.get();
                    stack.push(tsInfo);
                    String s = (cValue!=null) ? cValue.toString() : "null";
                    stack.pop();
                    if (tsInfo[1]==null) {
                        sb.append(cPrefix).append("=").append(s).append("\n");
                    }
                    else {
                        sb.append(s);
                    }
                }
            }
            else {
                sb.append(prefix).append("=[]\n");
            }
        }
        else {
            String[] tsInfo = new String[2];
            tsInfo[0] = prefix;
            Stack stack = (Stack) PREFIX_TL.get();
            stack.push(tsInfo);
            String s = value.toString();
            stack.pop();
            if (tsInfo[1]==null) {
                sb.append(prefix).append("=").append(s).append("\n");
            }
            else {
                sb.append(s);
            }
        }
    }

    private void printArrayProperty(StringBuffer sb, String prefix,Object array) {
        int length = Array.getLength(array);
        for (int i=0;i<length;i++) {
            Object obj = Array.get(array,i);
            printProperty(sb,prefix+"["+i+"]",obj);
        }
    }

}

