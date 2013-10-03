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
package com.sun.syndication.feed.rss;

import java.io.Serializable;

import com.sun.syndication.feed.impl.ObjectBean;

/**
 * Bean for item enclosures of RSS feeds.
 * <p>
 * 
 * @author Alejandro Abdelnur
 * 
 */
public class Enclosure implements Cloneable, Serializable {
    private final ObjectBean _objBean;
    private String _url;
    private long _length;
    private String _type;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     * 
     */
    public Enclosure() {
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
        if (!(other instanceof Enclosure)) {
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
     * Returns the enclosure URL.
     * <p>
     * 
     * @return the enclosure URL, <b>null</b> if none.
     * 
     */
    public String getUrl() {
        return this._url;
    }

    /**
     * Sets the enclosure URL.
     * <p>
     * 
     * @param url the enclosure URL to set, <b>null</b> if none.
     * 
     */
    public void setUrl(final String url) {
        this._url = url;
    }

    /**
     * Returns the enclosure length.
     * <p>
     * 
     * @return the enclosure length, <b>0</b> if none.
     * 
     */
    public long getLength() {
        return this._length;
    }

    /**
     * Sets the enclosure length.
     * <p>
     * 
     * @param length the enclosure length to set, <b>0</b> if none.
     * 
     */
    public void setLength(final long length) {
        this._length = length;
    }

    /**
     * Returns the enclosure type.
     * <p>
     * 
     * @return the enclosure type, <b>null</b> if none.
     * 
     */
    public String getType() {
        return this._type;
    }

    /**
     * Sets the enclosure type.
     * <p>
     * 
     * @param type the enclosure type to set, <b>null</b> if none.
     * 
     */
    public void setType(final String type) {
        this._type = type;
    }

}
