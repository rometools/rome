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

import com.sun.syndication.feed.impl.CloneableBean;
import com.sun.syndication.feed.impl.EqualsBean;

import java.io.Serializable;
import java.util.Set;

/**
 * Convenience class providing clone(), toString(), equals() and hashCode() functionality for Java Beans.
 * <p>
 * It works on all read/write properties, recursively.
 * <p>
 * It uses the CloneableBean, EqualsBean and ToStringBean classes in a delegation pattern.
 * <p>
 * <h3>ObjectBean programming conventions</h3>
 * <P>
 * All ObjectBean subclasses having properties that return collections they should never
 * return null if the property has been set to <b>null</b> or if a collection has not been set.
 * They should create and return an empty collection, this empty collection instance should
 * also be set to the corresponding property.
 * <P>
 * All ObjectBean subclasses properties should be live references.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class ObjectBean implements Serializable, Cloneable {
    private EqualsBean _equalsBean;
    private ToStringBean _toStringBean;
    private CloneableBean _cloneableBean;

    /**
     * Constructor.
     * <p>
     * @param beanClass the class/interface to be used for property scanning.
     *
     */
    public ObjectBean(Class beanClass,Object obj) {
        this(beanClass,obj,null);
    }

    /**
     * Constructor.
     * <p>
     * The property names in the ignoreProperties Set will not be copied into
     * the cloned instance. This is useful for cases where the Bean has convenience
     * properties (properties that are actually references to other properties or
     * properties of properties). For example SyndFeed and SyndEntry beans have
     * convenience properties, publishedDate, author, copyright and categories all
     * of them mapped to properties in the DC Module.
     * <p>
     * @param beanClass the class/interface to be used for property scanning.
     * @param ignoreProperties properties to ignore when cloning.
     *
     */
    public ObjectBean(Class beanClass,Object obj,Set ignoreProperties) {
        _equalsBean = new EqualsBean(beanClass,obj);
        _toStringBean = new ToStringBean(beanClass,obj);
        _cloneableBean = new CloneableBean(obj,ignoreProperties);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException {
        return _cloneableBean.beanClone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals() method.
     * <p>
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    public boolean equals(Object other) {
        return _equalsBean.beanEquals(other);
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     * @return the hashcode of the bean object.
     *
     */
    public int hashCode() {
        return _equalsBean.beanHashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     * @return String representation for the object.
     *
     */
    public String toString() {
        return _toStringBean.toString();
    }

}

