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
package com.rometools.rome.feed.synd;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;

/**
 * Bean for content of SyndFeedImpl entries.
 */
public class SyndContentImpl implements Serializable, SyndContent {

    private static final long serialVersionUID = 1L;

    private static final CopyFromHelper COPY_FROM_HELPER;

    private final ObjectBean objBean;

    private String type;
    private String value;
    private String mode;

    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("type", String.class);
        basePropInterfaceMap.put("value", String.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = Collections.<Class<? extends CopyFrom>, Class<?>> emptyMap();

        COPY_FROM_HELPER = new CopyFromHelper(SyndContent.class, basePropInterfaceMap, basePropClassImplMap);
    }

    public SyndContentImpl() {
        objBean = new ObjectBean(SyndContent.class, this);
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
     * Returns the content type.
     * <p>
     * When used for the description of an entry, if <b>null</b> 'text/plain' must be assumed.
     * <p>
     *
     * @return the content type, <b>null</b> if none.
     *
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Sets the content type.
     * <p>
     * When used for the description of an entry, if <b>null</b> 'text/plain' must be assumed.
     * <p>
     *
     * @param type the content type to set, <b>null</b> if none.
     *
     */
    @Override
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Returns the content mode.
     *
     * @return the content mode, <b>null</b> if none.
     *
     */
    @Override
    public String getMode() {
        return mode;
    }

    /**
     * Sets the content mode.
     *
     * @param mode the content mode to set, <b>null</b> if none.
     *
     */
    @Override
    public void setMode(final String mode) {
        this.mode = mode;
    }

    /**
     * Returns the content value.
     * <p>
     *
     * @return the content value, <b>null</b> if none.
     *
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * Sets the content value.
     * <p>
     *
     * @param value the content value to set, <b>null</b> if none.
     *
     */
    @Override
    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public Class<SyndContent> getInterface() {
        return SyndContent.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

}
