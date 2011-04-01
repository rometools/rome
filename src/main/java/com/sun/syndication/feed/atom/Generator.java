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
import com.sun.syndication.feed.impl.ObjectBean;

import java.io.Serializable;

/**
 * Bean for the generator element of Atom feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class Generator implements Cloneable,Serializable {
    private ObjectBean _objBean;
    private String _url;
    private String _version;
    private String _value;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Generator() {
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
      * Returns the generator URL.
      * <p>
      * @return the generator URL, <b>null</b> if none.
      *
      */
    public String getUrl() {
        return _url;
    }

    /**
      * Sets the generator URL.
      * <p>
      * @param url the generator URL, <b>null</b> if none.
      *
      */
    public void setUrl(String url) {
        _url = url;
    }

    /**
     * Returns the generator version.
     * <p>
     * @return the generator version, <b>null</b> if none.
     *
     */
    public String getVersion() {
        return _version;
    }

    /**
     * Sets the generator version.
     * <p>
     * @param version the generator version, <b>null</b> if none.
     *
     */
    public void setVersion(String version) {
        _version = version;
    }

    /**
     * Returns the generator value.
     * <p>
     * @return the generator value, <b>null</b> if none.
     *
     */
    public String getValue() {
        return _value;
    }

    /**
     * Sets the generator value.
     * <p>
     * @param value the generator value, <b>null</b> if none.
     *
     */
    public void setValue(String value) {
        _value = value;
    }

}
