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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * A very simple implementation of the {@link com.rometools.fetcher.impl.FeedFetcherCache}
 * interface.
 * </p>
 *
 * <p>
 * This implementation uses a HashMap to cache retrieved feeds. This implementation is most suitible
 * for sort term (client aggregator?) use, as the memory usage will increase over time as the number
 * of feeds in the cache increases.
 * </p>
 *
 * @author Nick Lothian
 * 
 * @deprecated ROME Fetcher will be dropped in the next major version of ROME (version 2). For more information and some migration hints, 
 * please have a look at our <a href="https://github.com/rometools/rome/issues/276">detailed explanation</a>.
 */
@Deprecated
public class HashMapFeedInfoCache implements FeedFetcherCache, Serializable {

    private static final long serialVersionUID = 1L;

    static HashMapFeedInfoCache instance;

    private Map<String, SyndFeedInfo> infoCache;

    /**
     * <p>
     * Constructor for HashMapFeedInfoCache
     * </p>
     *
     * <p>
     * Only use this if you want multiple instances of the cache. Usually getInstance() is more
     * appropriate.
     * </p>
     *
     */
    public HashMapFeedInfoCache() {
        setInfoCache(createInfoCache());
    }

    /**
     * Get the global instance of the cache
     *
     * @return an implementation of FeedFetcherCache
     */
    public static synchronized FeedFetcherCache getInstance() {
        if (instance == null) {
            instance = new HashMapFeedInfoCache();
        }
        return instance;
    }

    protected Map<String, SyndFeedInfo> createInfoCache() {
        return Collections.synchronizedMap(new HashMap<String, SyndFeedInfo>());
    }

    protected Object get(final Object key) {
        return getInfoCache().get(key);
    }

    /**
     * @see extensions.io.FeedFetcherCache#getFeedInfo(java.net.URL)
     */
    @Override
    public SyndFeedInfo getFeedInfo(final URL feedUrl) {
        return (SyndFeedInfo) get(feedUrl.toString());
    }

    protected void put(final String key, final SyndFeedInfo value) {
        getInfoCache().put(key, value);
    }

    /**
     * @see extensions.io.FeedFetcherCache#setFeedInfo(java.net.URL, extensions.io.SyndFeedInfo)
     */
    @Override
    public void setFeedInfo(final URL feedUrl, final SyndFeedInfo syndFeedInfo) {
        put(feedUrl.toString(), syndFeedInfo);
    }

    protected synchronized final Map<String, SyndFeedInfo> getInfoCache() {
        return infoCache;
    }

    /**
     * The API of this class indicates that map must thread safe. In other words, be sure to wrap it
     * in a synchronized map unless you know what you are doing.
     *
     * @param map the map to use as the info cache.
     */
    protected synchronized final void setInfoCache(final Map<String, SyndFeedInfo> map) {
        infoCache = map;
    }

    /**
     * @see com.rometools.rome.fetcher.impl.FeedFetcherCache#clear()
     */
    @Override
    public void clear() {
        synchronized (infoCache) {
            infoCache.clear();
        }
    }

    /**
     * @see com.rometools.rome.fetcher.impl.FeedFetcherCache#remove(java.net.URL)
     */
    @Override
    public SyndFeedInfo remove(final URL url) {
        if (url == null) {
            return null;
        }

        return infoCache.remove(url.toString());
    }

}
