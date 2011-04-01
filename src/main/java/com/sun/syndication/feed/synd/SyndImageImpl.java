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

import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.impl.CopyFromHelper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

/**
 * Bean for images of SyndFeedImpl feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class SyndImageImpl implements Serializable,SyndImage {
    private ObjectBean _objBean;
    private String _title;
    private String _url;
    private String _link;
    private String _description;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public SyndImageImpl() {
        _objBean = new ObjectBean(SyndImage.class,this);
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
     * Returns the image title.
     * <p>
     * @return the image title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        return _title;
    }

    /**
     * Sets the image title.
     * <p>
     * @param title the image title to set, <b>null</b> if none.
     *
     */
    public void setTitle(String title) {
        _title = title;
    }

    /**
     * Returns the image URL.
     * <p>
     * @return the image URL, <b>null</b> if none.
     *
     */
    public String getUrl() {
        return _url;
    }

    /**
     * Sets the image URL.
     * <p>
     * @param url the image URL to set, <b>null</b> if none.
     *
     */
    public void setUrl(String url) {
        _url = url;
    }

    /**
     * Returns the image link.
     * <p>
     * @return the image link, <b>null</b> if none.
     *
     */
    public String getLink() {
        return _link;
    }

    /**
     * Sets the image link.
     * <p>
     * @param link the image link to set, <b>null</b> if none.
     *
     */
    public void setLink(String link) {
        _link = link;
    }

    /**
     * Returns the image description.
     * <p>
     * @return the image description, <b>null</b> if none.
     *
     */
    public String getDescription() {
        return _description;
    }

    /**
     * Sets the image description.
     * <p>
     * @param description the image description to set, <b>null</b> if none.
     *
     */
    public void setDescription(String description) {
        _description = description;
    }

    public Class getInterface() {
        return SyndImage.class;
    }

    public void copyFrom(Object syndImage) {
        COPY_FROM_HELPER.copy(this,syndImage);
    }

    private static final CopyFromHelper COPY_FROM_HELPER;

    static {
        Map basePropInterfaceMap = new HashMap();
        basePropInterfaceMap.put("title",String.class);
        basePropInterfaceMap.put("url",String.class);
        basePropInterfaceMap.put("link",String.class);
        basePropInterfaceMap.put("description",String.class);

        Map basePropClassImplMap = Collections.EMPTY_MAP;

        COPY_FROM_HELPER = new CopyFromHelper(SyndImage.class,basePropInterfaceMap,basePropClassImplMap);
    }

}
