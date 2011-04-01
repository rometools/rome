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
package com.sun.syndication.feed.atom;

import com.sun.syndication.feed.impl.ObjectBean;

import java.io.Serializable;

/**
 * Bean for category elements of Atom feeds.
 * <p>
 * @author Dave Johnson (added for Atom 1.0)
 */
public class Category implements Cloneable, Serializable  {
    
    private ObjectBean _objBean;
    
    private String _term;
    private String _scheme;  
    private String _schemeResolved;  
    private String _label;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Category() {
        _objBean = new ObjectBean(this.getClass(),this);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    @Override
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
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Category)){
            return false;
        }
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
    @Override
    public int hashCode() {
        return _objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     * @return String representation for the object.
     *
     */
    @Override
    public String toString() {
        return _objBean.toString();
    }
    
    /** 
     * Get label for category.
     * <p>
     * @return Label for category.
     */
    public String getLabel() {
        return _label;
    }
    
    /**
     * Set label for category.
     * <p>
     * @param label Label for category.
     */
    public void setLabel(String label) {
        this._label = label;
    }
    
    /**
     * Get Scheme URI for category.
     * <p>
     * @return Scheme URI for category.
     */
    public String getScheme() {
        return _scheme;
    }
    
    /**
     * Set scheme URI for category.
     * <p>
     * @param scheme Scheme URI for category.
     */
    public void setScheme(String scheme) {
        this._scheme = scheme;
    }
    
    public void setSchemeResolved(String schemeResolved) {
        _schemeResolved = schemeResolved;
    }

    public String getSchemeResolved() {
        return _schemeResolved != null ? _schemeResolved : _scheme;
    }

    /**
     * Return term for category.
     * <p>
     * @return Term for category.
     */
    public String getTerm() {
        return _term;
    }
    
    /**
     * Set term for category.
     * <p>
     * @param term Term for category.
     */
    public void setTerm(String term) {
        this._term = term;
    }
}
