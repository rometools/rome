/*
 * Copyright 2004 Sun Microsystems, Inc.
 * Copyright 2011 The ROME Team
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
package com.rometools.rome.feed.rss;

import java.io.Serializable;

import com.rometools.rome.feed.impl.ObjectBean;

/**
 * Bean for categories of RSS feeds.
 */
public class Category implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    private final ObjectBean objBean;
    private String domain;
    private String value;

    public Category() {
        objBean = new ObjectBean(this.getClass(), this);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     *
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals()
     * method.
     * <p>
     *
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Category)) {
            return false;
        }
        return objBean.equals(other);
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     *
     * @return the hashcode of the bean object.
     *
     */
    @Override
    public int hashCode() {
        return objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     *
     * @return String representation for the object.
     *
     */
    @Override
    public String toString() {
        return objBean.toString();
    }

    /**
     * Returns the category domain.
     * <p>
     *
     * @return the category domain, <b>null</b> if none.
     *
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the category domain.
     * <p>
     *
     * @param domain the category domain to set, <b>null</b> if none.
     *
     */
    public void setDomain(final String domain) {
        this.domain = domain;
    }

    /**
     * Returns the category value.
     * <p>
     *
     * @return the category value, <b>null</b> if none.
     *
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the category value.
     * <p>
     *
     * @param value the category value to set, <b>null</b> if none.
     *
     */
    public void setValue(final String value) {
        this.value = value;
    }

}
