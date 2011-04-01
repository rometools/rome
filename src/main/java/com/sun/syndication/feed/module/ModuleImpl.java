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
package com.sun.syndication.feed.module;

import com.sun.syndication.feed.impl.ObjectBean;

import java.io.Serializable;

/**
 * Base class for modules describing Metadata of feeds, default implementations.
 * Examples of such modules are the Dublin Core and Syndication modules.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public abstract class ModuleImpl implements Cloneable,Serializable,Module {
    private ObjectBean _objBean;
    private String _uri;

    /**
     * Constructor.
     * <p>
     * @param uri URI of the module.
     *
     */
    protected ModuleImpl(Class beanClass,String uri) {
        _objBean = new ObjectBean(beanClass,this);
        _uri = uri;
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException {
        return _objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals() method.
     * <p>
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    public boolean equals(Object other) {
        return _objBean.equals(other);
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
        return _objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     * @return String representation for the object.
     *
     */
    public String toString() {
        return _objBean.toString();
    }

    /**
     * Returns the URI of the module.
     * <p>
     * @return URI of the module.
     *
     */
    public String getUri() {
        return _uri;
    }

}
