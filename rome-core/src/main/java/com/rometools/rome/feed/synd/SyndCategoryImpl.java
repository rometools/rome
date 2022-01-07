/*
 * Copyright 2004 Sun Microsystems, Inc.
 * Copyright 2011 ROME Team
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
package com.rometools.rome.feed.synd;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CloneableBean;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.rometools.rome.feed.module.DCSubject;
import com.rometools.rome.feed.module.DCSubjectImpl;

/**
 * Bean for categories of SyndFeedImpl feeds and entries.
 */
public class SyndCategoryImpl implements Serializable, SyndCategory {

    private static final long serialVersionUID = 1L;

    private static final CopyFromHelper COPY_FROM_HELPER;

    private final DCSubject subject;
    private String label;

    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("name", String.class);
        basePropInterfaceMap.put("taxonomyUri", String.class);
        basePropInterfaceMap.put("label", String.class);
        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = Collections.emptyMap();
        COPY_FROM_HELPER = new CopyFromHelper(SyndCategory.class, basePropInterfaceMap, basePropClassImplMap);
    }

    /**
     * For implementations extending SyndContentImpl to be able to use the ObjectBean functionality
     * with extended interfaces.
     *
     * @param subject the DC subject to wrap.
     */
    SyndCategoryImpl(final DCSubject subject) {
        this.subject = subject;
    }

    /**
     * Creates a deep 'bean' clone of the object.
     *
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return CloneableBean.beanClone(this, Collections.<String>emptySet());
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals()
     * method.
     *
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof SyndCategoryImpl)) {
            return false;
        }
        return EqualsBean.beanEquals(SyndCategory.class, this, other);
    }

    /**
     * Returns a hashcode value for the object.
     * It follows the contract defined by the Object hashCode() method.
     *
     * @return the hashcode of the bean object.
     *
     */
    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }

    /**
     * Returns the String representation for the object.
     *
     * @return String representation for the object.
     *
     */
    @Override
    public String toString() {
        return ToStringBean.toString(SyndCategory.class, this);
    }

    /**
     * Package private constructor, used by SyndCategoryListFacade.
     *
     * @return the DC subject being wrapped.
     *
     */
    DCSubject getSubject() {
        return subject;
    }

    /**
     * Public constructor.
     */
    public SyndCategoryImpl() {
        this(new DCSubjectImpl());
    }

    /**
     * Returns the category name.
     *
     * @return the category name, <b>null</b> if none.
     *
     */
    @Override
    public String getName() {
        return subject.getValue();
    }

    /**
     * Sets the category name.
     *
     * @param name the category name to set, <b>null</b> if none.
     *
     */
    @Override
    public void setName(final String name) {
        subject.setValue(name);
    }

    /**
     * Sets the category Label.
     * @param label the category label to set, <b>null</b> if none
     *
     */
    @Override
    public void setLabel(final String label) {
        this.label = label;
    }

    /**
     * Returns the label
     * 
     * @return the label.
     */
    @Override
    public String getLabel() { return label; }

    /**
     * Returns the category taxonomy URI.
     *
     * @return the category taxonomy URI, <b>null</b> if none.
     *
     */
    @Override
    public String getTaxonomyUri() {
        return subject.getTaxonomyUri();
    }

    /**
     * Sets the category taxonomy URI.
     *
     * @param taxonomyUri the category taxonomy URI to set, <b>null</b> if none.
     *
     */
    @Override
    public void setTaxonomyUri(final String taxonomyUri) {
        subject.setTaxonomyUri(taxonomyUri);
    }

    /**
     * Returns the Class interface
     * 
     * @return the class interface.
     */
    @Override
    public Class<? extends CopyFrom> getInterface() {
        return SyndCategory.class;
    }

    /**
     * Copy execution using CopyFrom helper class.
     * 
     * @param obj the source origin object.
     */
    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

}
