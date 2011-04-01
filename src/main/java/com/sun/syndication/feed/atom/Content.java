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
import java.util.HashSet;
import java.util.Set;

/**
 * Bean for content elements of Atom feeds.
 * <p>
 * @author Alejandro Abdelnur
 * @author Dave Johnson (updated for Atom 1.0)
 */
public class Content implements Cloneable,Serializable {
    
    private ObjectBean _objBean;
    
    private String _type;
    private String _value;
    private String _src; 
    
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

    private String _mode;  
    private static final Set MODES = new HashSet(); 
    static {
        MODES.add(XML);
        MODES.add(BASE64);
        MODES.add(ESCAPED);
    }


    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Content() {
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
     * Returns the content type.
     * <p>
     * The type indicates how the value was/will-be encoded in the XML feed.
     * </p>
     * @since Atom 1.0
     */
    public String getType() {
        return _type;
    }

    /**
     * Sets the content type.
     * <p>
     * The type indicates how the value was/will-be encoded in the XML feed.
     * </p>
     * @since Atom 1.0
     */
    public void setType(String type) {
        _type = type;
    }

    /**
     * Returns the content mode (Atom 0.3 only).
     * <p>
     * The mode indicates how the value was/will-be encoded in the XML feed.
     * <p>
     * @return the content mode, <b>null</b> if none.
     */
    public String getMode() {
        return _mode;
    }

    /**
     * Sets the content mode (Atom 0.3 only).
     * <p>
     * The mode indicates how the value was/will-be encoded in the XML feed.
     * <p>
     * @param mode the content mode, <b>null</b> if none.
     */
    public void setMode(String mode) {
        mode = (mode!=null) ? mode.toLowerCase() : null;
        if (mode==null || !MODES.contains(mode)) {
            throw new IllegalArgumentException("Invalid mode ["+mode+"]");
        }
        _mode = mode;
    }

    /**
     * Returns the content value.
     * <p>
     * The return value should be decoded.
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
     * The value being set should be decoded.
     * <p>
     * @param value the content value, <b>null</b> if none.
     *
     */
    public void setValue(String value) {
        _value = value;
    }

    /**
     * Returns the src
     * <p>
     * @return Returns the src.
     * @since Atom 1.0
     */
    public String getSrc() {
        return _src;
    }
    
    /**
     * Set the src
     * <p>
     * @param src The src to set.
     * @since Atom 1.0
     */
    public void setSrc(String src) {
        _src = src;
    }
}


