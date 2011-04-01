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
import java.io.Serializable;

/**
 * Provides deep <b>Bean</b> equals() and hashCode() functionality for Java Beans.
 * <p>
 * It works on all read/write properties, recursively. It support all primitive types, Strings, Collections,
 * bean-like objects and multi-dimensional arrays of any of them.
 * <p>
 * The hashcode is calculated by getting the hashcode of the Bean String representation.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class EqualsBean implements Serializable {

    private static final Object[] NO_PARAMS = new Object[0];

    private Class _beanClass;
    private Object _obj;

    /**
     * Default constructor.
     * <p>
     * To be used by classes extending EqualsBean only.
     * <p>
     * @param beanClass the class/interface to be used for property scanning.
     *
     */
    protected EqualsBean(Class beanClass) {
        _beanClass = beanClass;
        _obj = this;
    }

    /**
     * Creates a EqualsBean to be used in a delegation pattern.
     * <p>
     * For example:
     * <p>
     * <code>
     *   public class Foo  implements FooI {
     *       private EqualsBean _equalsBean;
     *
     *       public Foo() {
     *           _equalsBean = new EqualsBean(FooI.class);
     *       }
     *
     *       public boolean equals(Object obj) {
     *           return _equalsBean.beanEquals(obj);
     *       }
     *
     *       public int hashCode() {
     *           return _equalsBean.beanHashCode();
     *       }
     *
     *   }
     * </code>
     * <p>
     * @param beanClass the class/interface to be used for property scanning.
     * @param obj object bean to test equality.
     *
     */
    public EqualsBean(Class beanClass,Object obj) {
        if (!beanClass.isInstance(obj)) {
            throw new IllegalArgumentException(obj.getClass()+" is not instance of "+beanClass);
        }
        _beanClass = beanClass;
        _obj = obj;
    }

    /**
     * Indicates whether some other object is "equal to" this object as defined by the Object equals() method.
     * <p>
     * To be used by classes extending EqualsBean. Although it works also for classes using
     * EqualsBean in a delegation pattern, for correctness those classes should use the
     * @see #beanEquals(Object) beanEquals method.
     * <p>
     * @param obj he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    public boolean equals(Object obj) {
        return beanEquals(obj);
    }

    /**
     * Indicates whether some other object is "equal to" the object passed in the constructor,
     * as defined by the Object equals() method.
     * <p>
     * To be used by classes using EqualsBean in a delegation pattern,
     * @see #EqualsBean(Class,Object) constructor.
     * <p>
     * @param obj he reference object with which to compare.
     * @return <b>true</b> if the object passed in the constructor is equal to the 'obj' object.
     *
     */
    public boolean beanEquals(Object obj) {
        Object bean1 = _obj;
        Object bean2 = obj;
        boolean eq;
        if (bean2==null) {
            eq = false;
        }
        else
        if (bean1==null && bean2==null) {
            eq = true;
        }
        else
            if (bean1==null || bean2==null) {
                eq = false;
            }
            else {
                if (!_beanClass.isInstance(bean2)) {
                    eq = false;
                }
                else {
                    eq = true;
                    try {
                        PropertyDescriptor[] pds = BeanIntrospector.getPropertyDescriptors(_beanClass);
                        if (pds!=null) {
                            for (int i = 0; eq && i<pds.length; i++) {
                                Method pReadMethod = pds[i].getReadMethod();
                                if (pReadMethod!=null && // ensure it has a getter method
                                        pReadMethod.getDeclaringClass()!=Object.class && // filter Object.class getter methods
                                        pReadMethod.getParameterTypes().length==0) {     // filter getter methods that take parameters
                                    Object value1 = pReadMethod.invoke(bean1, NO_PARAMS);
                                    Object value2 = pReadMethod.invoke(bean2, NO_PARAMS);
                                    eq = doEquals(value1, value2);
                                }
                            }
                        }
                    }
                    catch (Exception ex) {
                        throw new RuntimeException("Could not execute equals()", ex);
                    }
                }
            }
        return eq;
    }

    /**
     * Returns the hashcode for this object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     * The hashcode is calculated by getting the hashcode of the Bean String representation.
     * <p>
     * To be used by classes extending EqualsBean. Although it works also for classes using
     * EqualsBean in a delegation pattern, for correctness those classes should use the
     * @see #beanHashCode() beanHashCode method.
     * <p>
     * @return the hashcode of the bean object.
     *
     */
    public int hashCode() {
        return beanHashCode();
    }

    /**
     * Returns the hashcode for the object passed in the constructor.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     * The hashcode is calculated by getting the hashcode of the Bean String representation.
     * <p>
     * To be used by classes using EqualsBean in a delegation pattern,
     * @see #EqualsBean(Class,Object) constructor.
     * <p>
     * @return the hashcode of the bean object.
     *
     */
    public int beanHashCode() {
        return _obj.toString().hashCode();
    }


    private boolean doEquals(Object obj1, Object obj2) {
        boolean eq = obj1==obj2;
        if (!eq && obj1!=null && obj2!=null) {
            Class classObj1 = obj1.getClass();
            Class classObj2 = obj2.getClass();
            if (classObj1.isArray() && classObj2.isArray()) {
                eq = equalsArray(obj1, obj2);
            }
            else {
                eq = obj1.equals(obj2);
            }
        }
        return eq;
    }

    private boolean equalsArray(Object array1, Object array2) {
        boolean eq;
        int length1 = Array.getLength(array1);
        int length2 = Array.getLength(array2);
        if (length1==length2) {
            eq = true;
            for (int i = 0; eq && i<length1; i++) {
                Object e1 = Array.get(array1, i);
                Object e2 = Array.get(array2, i);
                eq = doEquals(e1, e2);
            }
        }
        else {
            eq = false;
        }
        return eq;
    }

}

