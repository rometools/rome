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
package org.rometools.fetcher.impl;

import java.io.Serializable;
import java.net.URL;

import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.synd.SyndFeed;

/**
 * <p>A class to represent a {@link com.sun.syndication.feed.synd.SyndFeed}
 * and some useful information about it.</p>
 * 
 * <p>This class is thread safe, as expected by the different feed fetcher
 * implementations.</p>
 *
 * @author Nick Lothian
 */
public class SyndFeedInfo implements Cloneable, Serializable {
	private static final long serialVersionUID = -1874786860901426015L;
	
	private final ObjectBean _objBean;
	private String id;
	private URL url;
	private Object lastModified;
	private String eTag;
	private SyndFeed syndFeed;

    public SyndFeedInfo() {
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

	public synchronized void setETag(String string) {
		eTag = string;
	}

	public synchronized void setLastModified(Object o) {
		lastModified = o;
	}

	public synchronized void setUrl(URL url) {
		this.url = url;
	}

	public synchronized SyndFeed getSyndFeed() {
		return syndFeed;
	}

	public synchronized void setSyndFeed(SyndFeed feed) {
		syndFeed = feed;
	}

	/**
	 * @return A unique ID to identify the feed
	 */
	public synchronized String getId() {
		return id;
	}

	/**
	 * @param string A unique ID to identify the feed. Note that if the URL of the feed
	 * changes this will remain the same
	 */
	public synchronized void setId(String string) {
		id = string;
	}

}
