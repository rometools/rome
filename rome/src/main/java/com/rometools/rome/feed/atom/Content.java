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
import java.util.HashSet;
import java.util.Set;

import com.rometools.rome.feed.impl.ObjectBean;
import com.rometools.utils.Strings;

/**
 * Bean for content elements of Atom feeds.
 */
public class Content implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    private final ObjectBean objBean;

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

    public Content() {
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
        if (!(other instanceof Content)) {
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
     * Returns the content type.
     * <p>
     * The type indicates how the value was/will-be encoded in the XML feed.
     * </p>
     *
     * @since Atom 1.0
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the content type.
     * <p>
     * The type indicates how the value was/will-be encoded in the XML feed.
     * </p>
     *
     * @since Atom 1.0
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Returns the content mode (Atom 0.3 only).
     * <p>
     * The mode indicates how the value was/will-be encoded in the XML feed.
     * <p>
     *
     * @return the content mode, <b>null</b> if none.
     */
    public String getMode() {
        return mode;
    }

    /**
     * Sets the content mode (Atom 0.3 only).
     * <p>
     * The mode indicates how the value was/will-be encoded in the XML feed.
     * <p>
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
     * <p>
     * The return value should be decoded.
     * <p>
     *
     * @return the content value, <b>null</b> if none.
     *
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the content value.
     * <p>
     * The value being set should be decoded.
     * <p>
     *
     * @param value the content value, <b>null</b> if none.
     *
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * Returns the src
     * <p>
     *
     * @return Returns the src.
     * @since Atom 1.0
     */
    public String getSrc() {
        return src;
    }

    /**
     * Set the src
     * <p>
     *
     * @param src The src to set.
     * @since Atom 1.0
     */
    public void setSrc(final String src) {
        this.src = src;
    }
}
