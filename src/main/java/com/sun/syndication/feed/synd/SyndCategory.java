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




/**
 * Bean interface for categories of SyndFeedImpl feeds and entries.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public interface SyndCategory extends Cloneable {
    /**
     * Returns the category name.
     * <p>
     * @return the category name, <b>null</b> if none.
     *
     */
    String getName();

    /**
     * Sets the category name.
     * <p>
     * @param name the category name to set, <b>null</b> if none.
     *
     */
    void setName(String name);

    /**
     * Returns the category taxonomy URI.
     * <p>
     * @return the category taxonomy URI, <b>null</b> if none.
     *
     */
    String getTaxonomyUri();

    /**
     * Sets the category taxonomy URI.
     * <p>
     * @param taxonomyUri the category taxonomy URI to set, <b>null</b> if none.
     *
     */
    void setTaxonomyUri(String taxonomyUri);

    /**
     * Creates a deep clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException;

}
