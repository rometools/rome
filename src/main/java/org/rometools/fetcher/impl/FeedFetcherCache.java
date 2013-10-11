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

import java.net.URL;

/**
 * <p>An interface to allow caching of feed details. Implementing this allows the
 * {@link org.rometools.fetcher.io.HttpURLFeedFetcher} class to
 * enable conditional gets</p> 
 * 
 * @author Nick Lothian
 *
 */
public interface FeedFetcherCache {
	/**
	 * Get a SyndFeedInfo object from the cache.
	 * 
	 * @param feedUrl The url of the feed
	 * @return A SyndFeedInfo or null if it is not in the cache
	 */
	public SyndFeedInfo getFeedInfo(URL feedUrl);
	
	/**
	 * Add a SyndFeedInfo object to the cache
	 * 
	 * @param feedUrl  The url of the feed
	 * @param syndFeedInfo A SyndFeedInfo for the feed
	 */
	public void setFeedInfo(URL feedUrl, SyndFeedInfo syndFeedInfo);
	
	/**
	 * Removes all items from the cache.
	 */
	public void clear();
	
	/**
	 * Removes the SyndFeedInfo identified by the url from the cache.
	 * @return The removed SyndFeedInfo
	 */
	public SyndFeedInfo remove( URL feedUrl );
}
