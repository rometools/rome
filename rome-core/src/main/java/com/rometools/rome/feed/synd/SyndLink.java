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

import java.util.List;

import org.jdom2.Attribute;

/**
 * Represents a link or enclosure associated with entry.
 */
public interface SyndLink {
    /**
     * Creates a deep 'bean' clone of the object.
     *
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public abstract Object clone() throws CloneNotSupportedException;

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals()
     * method.
     *
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    @Override
    public abstract boolean equals(Object other);

    /**
     * Returns a hashcode value for the object.
     * It follows the contract defined by the Object hashCode() method.
     *
     * @return the hashcode of the bean object.
     *
     */
    @Override
    public abstract int hashCode();

    /**
     * Returns the String representation for the object.
     *
     * @return String representation for the object.
     *
     */
    @Override
    public abstract String toString();

    /**
     * Returns the link rel.
     *
     * @return the link rel, <b>null</b> if none.
     *
     */
    public abstract String getRel();

    /**
     * Sets the link rel.
     *
     * @param rel the link rel,, <b>null</b> if none.
     *
     */
    public abstract void setRel(String rel);

    /**
     * Returns the link type.
     *
     * @return the link type, <b>null</b> if none.
     *
     */
    public abstract String getType();

    /**
     * Sets the link type.
     *
     * @param type the link type, <b>null</b> if none.
     *
     */
    public abstract void setType(String type);

    /**
     * Returns the link href.
     *
     * @return the link href, <b>null</b> if none.
     *
     */
    public abstract String getHref();

    /**
     * Sets the link href.
     *
     * @param href the link href, <b>null</b> if none.
     *
     */
    public abstract void setHref(String href);

    /**
     * Returns the link title.
     *
     * @return the link title, <b>null</b> if none.
     *
     */
    public abstract String getTitle();

    /**
     * Sets the link title.
     *
     * @param title the link title, <b>null</b> if none.
     *
     */
    public abstract void setTitle(String title);

    /**
     * Returns the hreflang
     *
     * @return Returns the hreflang.
     */
    public abstract String getHreflang();

    /**
     * Set the hreflang
     *
     * @param hreflang The hreflang to set.
     */
    public abstract void setHreflang(String hreflang);

    /**
     * Returns the length
     *
     * @return Returns the length.
     */
    public abstract long getLength();

    /**
     * Set the length
     *
     * @param length The length to set.
     */
    public abstract void setLength(long length);

    /**
     * Returns foreign attributes
     *
     * @return Opaque object to discourage use
     *
     */
    public List<SyndLinkAttribute> getLinkAttributes();

    /**
     * Sets foreign attributes
     *
     * @param attributes Opaque object to discourage use
     *
     */
    public void setLinkAttributes(List<SyndLinkAttribute> attributes);
}
