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

import com.rometools.rome.feed.impl.ObjectBean;

/**
 * Represents a link or an enclosure.
 */
public class SyndLinkImpl implements Cloneable, Serializable, SyndLink {
    private static final long serialVersionUID = 1L;

    private final ObjectBean objBean;

    private String href;
    private String rel;
    private String type;
    private String hreflang;
    private String title;
    private long length;

    public SyndLinkImpl() {
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
        if (!(other instanceof SyndLinkImpl)) {
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
     * Returns the link rel.
     * <p>
     *
     * @return the link rel, <b>null</b> if none.
     *
     */
    @Override
    public String getRel() {
        return rel;
    }

    /**
     * Sets the link rel.
     * <p>
     *
     * @param rel the link rel,, <b>null</b> if none.
     *
     */
    @Override
    public void setRel(final String rel) {
        // TODO add check, ask P@ about the check
        this.rel = rel;
    }

    /**
     * Returns the link type.
     * <p>
     *
     * @return the link type, <b>null</b> if none.
     *
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Sets the link type.
     * <p>
     *
     * @param type the link type, <b>null</b> if none.
     *
     */
    @Override
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Returns the link href.
     * <p>
     *
     * @return the link href, <b>null</b> if none.
     *
     */
    @Override
    public String getHref() {
        return href;
    }

    /**
     * Sets the link href.
     * <p>
     *
     * @param href the link href, <b>null</b> if none.
     *
     */
    @Override
    public void setHref(final String href) {
        this.href = href;
    }

    /**
     * Returns the link title.
     * <p>
     *
     * @return the link title, <b>null</b> if none.
     *
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Sets the link title.
     * <p>
     *
     * @param title the link title, <b>null</b> if none.
     *
     */
    @Override
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Returns the hreflang
     * <p>
     *
     * @return Returns the hreflang.
     */
    @Override
    public String getHreflang() {
        return hreflang;
    }

    /**
     * Set the hreflang
     * <p>
     *
     * @param hreflang The hreflang to set.
     */
    @Override
    public void setHreflang(final String hreflang) {
        this.hreflang = hreflang;
    }

    /**
     * Returns the length
     * <p>
     *
     * @return Returns the length.
     */
    @Override
    public long getLength() {
        return length;
    }

    /**
     * Set the length
     * <p>
     *
     * @param length The length to set.
     */
    @Override
    public void setLength(final long length) {
        this.length = length;
    }
}
