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
package com.rometools.rome.feed.synd;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CloneableBean;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

/**
 * Bean for images of SyndFeedImpl feeds.
 */
public class SyndImageImpl implements Serializable, SyndImage {

    private static final long serialVersionUID = 1L;

    private static final CopyFromHelper COPY_FROM_HELPER;

    private String title;
    private String url;
    private Integer width;
    private Integer height;
    private String link;
    private String description;

    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("title", String.class);
        basePropInterfaceMap.put("url", String.class);
        basePropInterfaceMap.put("link", String.class);
        basePropInterfaceMap.put("width", Integer.class);
        basePropInterfaceMap.put("height", Integer.class);
        basePropInterfaceMap.put("description", String.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = Collections.<Class<? extends CopyFrom>, Class<?>> emptyMap();

        COPY_FROM_HELPER = new CopyFromHelper(SyndImage.class, basePropInterfaceMap, basePropClassImplMap);
    }

    /**
     * Empty public constructor.
     */
    public SyndImageImpl() { }

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
        return EqualsBean.beanEquals(SyndImage.class, this, other);
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
        return ToStringBean.toString(SyndImage.class, this);
    }

    /**
     * Returns the image title.

     *
     * @return the image title, <b>null</b> if none.
     *
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Sets the image title.

     *
     * @param title the image title to set, <b>null</b> if none.
     *
     */
    @Override
    public void setTitle(final String title) {
        this.title = title;
    }


    /**
     * Returns the image URL.

     *
     * @return the image URL, <b>null</b> if none.
     *
     */
    @Override
    public String getUrl() {
        return url;
    }

    /**
     * Sets the image URL.

     *
     * @param url the image URL to set, <b>null</b> if none.
     *
     */
    @Override
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Returns the image width.

     *
     * @return the image width, <b>null</b> if none.
     *
     */
    @Override
    public Integer getWidth() {
        return width;
    }

    /**
     * Sets the image width.

     *
     * @param width the image width to set, <b>null</b> if none.
     *
     */
    @Override
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * Returns the image height.

     *
     * @return the image height, <b>null</b> if none.
     *
     */
    @Override
    public Integer getHeight() {
        return height;
    }

    /**
     * Sets the image height.

     *
     * @param height the image height to set, <b>null</b> if none.
     *
     */
    @Override
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * Returns the image link.

     *
     * @return the image link, <b>null</b> if none.
     *
     */
    @Override
    public String getLink() {
        return link;
    }

    /**
     * Sets the image link.

     *
     * @param link the image link to set, <b>null</b> if none.
     *
     */
    @Override
    public void setLink(final String link) {
        this.link = link;
    }

    /**
     * Returns the image description.

     *
     * @return the image description, <b>null</b> if none.
     *
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Sets the image description.

     *
     * @param description the image description to set, <b>null</b> if none.
     *
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * The Class interface representation.
     * 
     * @return the class interface.
     */
    @Override
    public Class<SyndImage> getInterface() {
        return SyndImage.class;
    }

    /**
     * Copy execution using CopyFrom helper class.
     * 
     * @param syndImage the source origin object.
     */
    @Override
    public void copyFrom(final CopyFrom syndImage) {
        COPY_FROM_HELPER.copy(this, syndImage);
    }

}
