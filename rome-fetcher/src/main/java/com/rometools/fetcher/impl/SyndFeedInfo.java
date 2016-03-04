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
package com.rometools.fetcher.impl;

import java.io.Serializable;
import java.net.URL;

import com.rometools.rome.feed.impl.ObjectBean;
import com.rometools.rome.feed.synd.SyndFeed;

/**
 * <p>
 * A class to represent a {@link com.rometools.rome.feed.synd.SyndFeed} and some useful information
 * about it.
 * </p>
 *
 * <p>
 * This class is thread safe, as expected by the different feed fetcher implementations.
 * </p>
 *
 * @author Nick Lothian
 * 
 * @deprecated ROME Fetcher will be dropped in the next major version of ROME (version 2). For more information and some migration hints, 
 * please have a look at our <a href="https://github.com/rometools/rome/issues/276">detailed explanation</a>.
 */
@Deprecated
public class SyndFeedInfo implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;

    private final ObjectBean _objBean;
    private String id;
    private URL url;
    private Object lastModified;
    private String eTag;
    private SyndFeed syndFeed;

    public SyndFeedInfo() {
        _objBean = new ObjectBean(this.getClass(), this);
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
        return _objBean.clone();
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
        return _objBean.equals(other);
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
        return _objBean.hashCode();
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
        return _objBean.toString();
    }

    /**
     * @return the ETag the feed was last retrieved with
     */
    public synchronized String getETag() {
        return eTag;
    }

    /**
     * @return the last modified date for the feed
     */
    public synchronized Object getLastModified() {
        return lastModified;
    }

    /**
     * @return the URL the feed was served from
     */
    public synchronized URL getUrl() {
        return url;
    }

    public synchronized void setETag(final String string) {
        eTag = string;
    }

    public synchronized void setLastModified(final Object o) {
        lastModified = o;
    }

    public synchronized void setUrl(final URL url) {
        this.url = url;
    }

    public synchronized SyndFeed getSyndFeed() {
        return syndFeed;
    }

    public synchronized void setSyndFeed(final SyndFeed feed) {
        syndFeed = feed;
    }

    /**
     * @return A unique ID to identify the feed
     */
    public synchronized String getId() {
        return id;
    }

    /**
     * @param string A unique ID to identify the feed. Note that if the URL of the feed changes this
     *            will remain the same
     */
    public synchronized void setId(final String string) {
        id = string;
    }

}
