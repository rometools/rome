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

import com.sun.syndication.feed.CopyFrom;

/**
 * Bean interface for images of SyndFeedImpl feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public interface SyndImage extends Cloneable,CopyFrom {
    /**
     * Returns the image title.
     * <p>
     * @return the image title, <b>null</b> if none.
     *
     */
    String getTitle();

    /**
     * Sets the image title.
     * <p>
     * @param title the image title to set, <b>null</b> if none.
     *
     */
    void setTitle(String title);

    /**
     * Returns the image URL.
     * <p>
     * @return the image URL, <b>null</b> if none.
     *
     */
    String getUrl();

    /**
     * Sets the image URL.
     * <p>
     * @param url the image URL to set, <b>null</b> if none.
     *
     */
    void setUrl(String url);

    /**
     * Returns the image link.
     * <p>
     * @return the image link, <b>null</b> if none.
     *
     */
    String getLink();

    /**
     * Sets the image link.
     * <p>
     * @param link the image link to set, <b>null</b> if none.
     *
     */
    void setLink(String link);

    /**
     * Returns the image description.
     * <p>
     * @return the image description, <b>null</b> if none.
     *
     */
    String getDescription();

    /**
     * Sets the image description.
     * <p>
     * @param description the image description to set, <b>null</b> if none.
     *
     */
    void setDescription(String description);

    /**
     * Creates a deep clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException;

}
