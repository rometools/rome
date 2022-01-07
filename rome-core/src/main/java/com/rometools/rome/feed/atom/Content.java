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
package com.rometools.rome.feed.atom;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.rometools.rome.feed.impl.CloneableBean;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.rometools.utils.Strings;

/**
 * Bean for content elements of Atom feeds.
 */
public class Content implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    private String type;
    private String value;
    private String src;

    /** @since Atom 1.0 */
    public static final String TEXT = "text";

    /** @since Atom 1.0 */
    public static final String HTML = "html";

    /** @since Atom 1.0 */
    public static final String XHTML = "xhtml";

    /** Atom 0.3 only */
    public static final String XML = "xml";

    /** Atom 0.3 only */
    public static final String BASE64 = "base64";

    /** Atom 0.3 only */
    public static final String ESCAPED = "escaped";

    private String mode;
    private static final Set<String> MODES = new HashSet<String>();
    static {
        MODES.add(XML);
        MODES.add(BASE64);
        MODES.add(ESCAPED);
    }

    /**
     * Public constructor.
     */
    public Content() { }

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
        if (!(other instanceof Content)) {
            return false;
        }
        return EqualsBean.beanEquals(this.getClass(), this, other);
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
        return ToStringBean.toString(this.getClass(), this);
    }

    /**
     * Returns the content type.
     * The type indicates how the value was/will-be encoded in the XML feed.
     *
     * @return type The type
     * @since Atom 1.0
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the content type.
     * The type indicates how the value was/will-be encoded in the XML feed.
     *
     * @param type The type
     * @since Atom 1.0
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Returns the content mode (Atom 0.3 only).
     * The mode indicates how the value was/will-be encoded in the XML feed.
     *
     * @return the content mode, <b>null</b> if none.
     */
    public String getMode() {
        return mode;
    }

    /**
     * Sets the content mode (Atom 0.3 only).

     * The mode indicates how the value was/will-be encoded in the XML feed.

     *
     * @param mode the content mode, <b>null</b> if none.
     */
    public void setMode(final String mode) {
        this.mode = Strings.toLowerCase(mode);
        if (mode == null || !MODES.contains(mode)) {
            throw new IllegalArgumentException("Invalid mode [" + mode + "]");
        }
    }

    /**
     * Returns the content value.

     * The return value should be decoded.

     *
     * @return the content value, <b>null</b> if none.
     *
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the content value.

     * The value being set should be decoded.

     *
     * @param value the content value, <b>null</b> if none.
     *
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * Returns the src

     *
     * @return Returns the src.
     * @since Atom 1.0
     */
    public String getSrc() {
        return src;
    }

    /**
     * Set the src

     *
     * @param src The src to set.
     * @since Atom 1.0
     */
    public void setSrc(final String src) {
        this.src = src;
    }
}
