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

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.impl.CopyFromHelper;
import com.sun.syndication.feed.impl.ObjectBean;

/**
 * Subject of the Dublin Core ModuleImpl, default implementation.
 * <p>
 * 
 * @see <a href="http://web.resource.org/rss/1.0/modules/dc/">Dublin Core
 *      module</a>.
 * @author Alejandro Abdelnur
 * 
 */
public class DCSubjectImpl implements Cloneable, Serializable, DCSubject {
    private final ObjectBean _objBean;
    private String _taxonomyUri;
    private String _value;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     * 
     */
    public DCSubjectImpl() {
        this._objBean = new ObjectBean(this.getClass(), this);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     * 
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object
     *             cannot be cloned.
     * 
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return this._objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by
     * the Object equals() method.
     * <p>
     * 
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     * 
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof DCSubjectImpl)) {
            return false;
        }
        return this._objBean.equals(other);
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
        return this._objBean.hashCode();
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
        return this._objBean.toString();
    }

    /**
     * Returns the DublinCore subject taxonomy URI.
     * <p>
     * 
     * @return the DublinCore subject taxonomy URI, <b>null</b> if none.
     * 
     */
    @Override
    public String getTaxonomyUri() {
        return this._taxonomyUri;
    }

    /**
     * Sets the DublinCore subject taxonomy URI.
     * <p>
     * 
     * @param taxonomyUri the DublinCore subject taxonomy URI to set,
     *            <b>null</b> if none.
     * 
     */
    @Override
    public void setTaxonomyUri(final String taxonomyUri) {
        this._taxonomyUri = taxonomyUri;
    }

    /**
     * Returns the DublinCore subject value.
     * <p>
     * 
     * @return the DublinCore subject value, <b>null</b> if none.
     * 
     */
    @Override
    public String getValue() {
        return this._value;
    }

    /**
     * Sets the DublinCore subject value.
     * <p>
     * 
     * @param value the DublinCore subject value to set, <b>null</b> if none.
     * 
     */
    @Override
    public void setValue(final String value) {
        this._value = value;
    }

    @Override
    public Class getInterface() {
        return DCSubject.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    private static final CopyFromHelper COPY_FROM_HELPER;

    static {
        final Map basePropInterfaceMap = new HashMap();
        basePropInterfaceMap.put("taxonomyUri", String.class);
        basePropInterfaceMap.put("value", String.class);

        final Map basePropClassImplMap = Collections.EMPTY_MAP;

        COPY_FROM_HELPER = new CopyFromHelper(DCSubject.class, basePropInterfaceMap, basePropClassImplMap);
    }

}
