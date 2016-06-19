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
package com.rometools.rome.feed.atom;

import java.io.Serializable;

import com.rometools.rome.feed.impl.ObjectBean;
import com.rometools.utils.Alternatives;

/**
 * Bean for category elements of Atom feeds.
 */
public class Category implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    private final ObjectBean objBean;

    private String term;
    private String scheme;
    private String schemeResolved;
    private String label;

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
     * Get label for category.
     * <p>
     *
     * @return Label for category.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set label for category.
     * <p>
     *
     * @param label Label for category.
     */
    public void setLabel(final String label) {
        this.label = label;
    }

    /**
     * Get Scheme URI for category.
     * <p>
     *
     * @return Scheme URI for category.
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * Set scheme URI for category.
     * <p>
     *
     * @param scheme Scheme URI for category.
     */
    public void setScheme(final String scheme) {
        this.scheme = scheme;
    }

    public void setSchemeResolved(final String schemeResolved) {
        this.schemeResolved = schemeResolved;
    }

    public String getSchemeResolved() {
        return Alternatives.firstNotNull(schemeResolved, scheme);
    }

    /**
     * Return term for category.
     * <p>
     *
     * @return Term for category.
     */
    public String getTerm() {
        return term;
    }

    /**
     * Set term for category.
     * <p>
     *
     * @param term Term for category.
     */
    public void setTerm(final String term) {
        this.term = term;
    }

}
