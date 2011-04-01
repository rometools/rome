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
package com.sun.syndication.feed.synd;

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.impl.CopyFromHelper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

/**
 * Bean for content of SyndFeedImpl entries.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class SyndContentImpl implements Serializable, SyndContent {
    private ObjectBean _objBean;
    private String _type;
    private String _value;
    private String _mode;


    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public SyndContentImpl() {
        _objBean = new ObjectBean(SyndContent.class,this);
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
     * Returns the content type.
     * <p>
     * When used for the description of an entry, if <b>null</b> 'text/plain' must be assumed.
     * <p>
     * @return the content type, <b>null</b> if none.
     *
     */
    public String getType() {
        return _type;
    }

    /**
     * Sets the content type.
     * <p>
     * When used for the description of an entry, if <b>null</b> 'text/plain' must be assumed.
     * <p>
     * @param type the content type to set, <b>null</b> if none.
     *
     */
    public void setType(String type) {
        _type = type;
    }

    /**
     * Returns the content mode.
     * @return the content mode, <b>null</b> if none.
     *
     */
    public String getMode() {
        return _mode;
    }

    /**
     * Sets the content mode.
     * @param mode the content mode to set, <b>null</b> if none.
     *
     */
    public void setMode(String mode) {
        _mode = mode;
    }

    /**
     * Returns the content value.
     * <p>
     * @return the content value, <b>null</b> if none.
     *
     */
    public String getValue() {
        return _value;
    }

    /**
     * Sets the content value.
     * <p>
     * @param value the content value to set, <b>null</b> if none.
     *
     */
    public void setValue(String value) {
        _value = value;
    }


    public Class getInterface() {
        return SyndContent.class;
    }

    public void copyFrom(CopyFrom obj) {
        COPY_FROM_HELPER.copy(this,obj);
    }

    private static final CopyFromHelper COPY_FROM_HELPER;

    static {
        Map basePropInterfaceMap = new HashMap();
        basePropInterfaceMap.put("type",String.class);
        basePropInterfaceMap.put("value",String.class);

        Map basePropClassImplMap = Collections.EMPTY_MAP;

        COPY_FROM_HELPER = new CopyFromHelper(SyndContent.class,basePropInterfaceMap,basePropClassImplMap);
    }

}
