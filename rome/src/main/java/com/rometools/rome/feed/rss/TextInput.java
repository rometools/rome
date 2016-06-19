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
package com.rometools.rome.feed.rss;

import java.io.Serializable;

import com.rometools.rome.feed.impl.ObjectBean;

/**
 * Bean for text input of RSS feeds.
 */
public class TextInput implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    private final ObjectBean objBean;
    private String title;
    private String description;
    private String name;
    private String link;

    public TextInput() {
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
        if (!(other instanceof TextInput)) {
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
     * Returns the text input title.
     * <p>
     *
     * @return the text input title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the text input title.
     * <p>
     *
     * @param title the text input title to set, <b>null</b> if none.
     *
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Returns the text input description.
     * <p>
     *
     * @return the text input description, <b>null</b> if none.
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the text input description.
     * <p>
     *
     * @param description the text input description to set, <b>null</b> if none.
     *
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns the text input name.
     * <p>
     *
     * @return the text input name, <b>null</b> if none.
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the text input name.
     * <p>
     *
     * @param name the text input name to set, <b>null</b> if none.
     *
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns the text input link.
     * <p>
     *
     * @return the text input link, <b>null</b> if none.
     *
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the text input link.
     * <p>
     *
     * @param link the text input link to set, <b>null</b> if none.
     *
     */
    public void setLink(final String link) {
        this.link = link;
    }

}
