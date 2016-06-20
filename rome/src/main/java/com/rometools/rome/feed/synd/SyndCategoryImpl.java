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
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;
import com.rometools.rome.feed.module.DCSubject;
import com.rometools.rome.feed.module.DCSubjectImpl;

/**
 * Bean for categories of SyndFeedImpl feeds and entries.
 */
public class SyndCategoryImpl implements Serializable, SyndCategory {

    private static final long serialVersionUID = 1L;

    private static final CopyFromHelper COPY_FROM_HELPER;

    private final ObjectBean objBean;
    private final DCSubject subject;

    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("name", String.class);
        basePropInterfaceMap.put("taxonomyUri", String.class);
        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = Collections.emptyMap();
        COPY_FROM_HELPER = new CopyFromHelper(SyndCategory.class, basePropInterfaceMap, basePropClassImplMap);
    }

    /**
     * For implementations extending SyndContentImpl to be able to use the ObjectBean functionality
     * with extended interfaces.
     * <p>
     *
     * @param subject the DC subject to wrap.
     */
    SyndCategoryImpl(final DCSubject subject) {
        objBean = new ObjectBean(SyndCategory.class, this);
        this.subject = subject;
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
        if (!(other instanceof SyndCategoryImpl)) {
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
     * Package private constructor, used by SyndCategoryListFacade.
     * <p>
     *
     * @return the DC subject being wrapped.
     *
     */
    DCSubject getSubject() {
        return subject;
    }

    public SyndCategoryImpl() {
        this(new DCSubjectImpl());
    }

    /**
     * Returns the category name.
     * <p>
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
     * <p>
     *
     * @param name the category name to set, <b>null</b> if none.
     *
     */
    @Override
    public void setName(final String name) {
        subject.setValue(name);
    }

    /**
     * Returns the category taxonomy URI.
     * <p>
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
     * <p>
     *
     * @param taxonomyUri the category taxonomy URI to set, <b>null</b> if none.
     *
     */
    @Override
    public void setTaxonomyUri(final String taxonomyUri) {
        subject.setTaxonomyUri(taxonomyUri);
    }

    @Override
    public Class<? extends CopyFrom> getInterface() {
        return SyndCategory.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

}
