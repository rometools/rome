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
 * Bean for link elements of Atom feeds.
 * <p>
 * @author Alejandro Abdelnur
 * @author Dave Johnson (updated for Atom 1.0)
 */
public class Link implements Cloneable,Serializable {
    
    private ObjectBean _objBean;
    
    private String _href;
    private String _hrefResolved;
    private String _rel = "alternate";
    private String _type;
    private String _hreflang;
    private String _title;
    private long   _length;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Link() {
        _objBean = new ObjectBean(this.getClass(),this);
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
     * Returns the link rel.
     * <p>
     * @return the link rel, <b>null</b> if none.
     *
     */
    public String getRel() {
        return _rel;
    }

    /**
     * Sets the link rel.
     * <p>
     * @param rel the link rel,, <b>null</b> if none.
     *
     */
    public void setRel(String rel) {
        //TODO add check, ask P@ about the check
        _rel = rel;
    }

    /**
     * Returns the link type.
     * <p>
     * @return the link type, <b>null</b> if none.
     *
     */
    public String getType() {
        return _type;
    }

    /**
     * Sets the link type.
     * <p>
     * @param type the link type, <b>null</b> if none.
     *
     */
    public void setType(String type) {
        _type = type;
    }

    /**
     * Returns the link href.
     * <p>
     * @return the link href, <b>null</b> if none.
     *
     */
    public String getHref() {
        return _href;
    }

    /**
     * Sets the link href.
     * <p>
     * @param href the link href, <b>null</b> if none.
     *
     */
    public void setHref(String href) {
        _href = href;
    }

    public void setHrefResolved(String hrefResolved) {
        _hrefResolved = hrefResolved;
    }

    public String getHrefResolved() {
        return _hrefResolved != null ? _hrefResolved : _href;
    }

    /**
     * Returns the link title.
     * <p>
     * @return the link title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        return _title;
    }

    /**
     * Sets the link title.
     * <p>
     * @param title the link title, <b>null</b> if none.
     *
     */
    public void setTitle(String title) {
        _title = title;
    }

    /**
     * Returns the hreflang
     * <p>
     * @return Returns the hreflang.
     * @since Atom 1.0
     */
    public String getHreflang() {
        return _hreflang;
    }
    
    /**
     * Set the hreflang
     * <p>
     * @param hreflang The hreflang to set.
     * @since Atom 1.0
     */
    public void setHreflang(String hreflang) {
        _hreflang = hreflang;
    }
    
    /**
     * Returns the length
     * <p>
     * @return Returns the length.
     */
    public long getLength() {
        return _length;
    }
    
    /**
     * Set the length
     * <p>
     * @param length The length to set.
     */
    public void setLength(long length) {
        _length = length;
    }
}
