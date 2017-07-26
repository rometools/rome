/*
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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * An implementation of the {@link com.rometools.fetcher.impl.FeedFetcherCache} interface.
 * </p>
 *
 * <p>
 * Unlike the HashMapFeedInfoCache this implementation will not grow unbound
 * </p>
 *
 * @author Javier Kohen
 * @author Nick Lothian
 *
 * @deprecated ROME Fetcher will be dropped in the next major version of ROME (version 2). For more information and some migration hints, 
 * please have a look at our <a href="https://github.com/rometools/rome/issues/276">detailed explanation</a>.
 */
@Deprecated
public class LinkedHashMapFeedInfoCache extends HashMapFeedInfoCache {

    private final class CacheImpl extends LinkedHashMap<String, SyndFeedInfo> {

        private static final long serialVersionUID = 1L;

        public CacheImpl() {
            super(16, 0.75F, true);
        }

        @Override
        protected boolean removeEldestEntry(final Map.Entry<String, SyndFeedInfo> eldest) {
            return size() > getMaxEntries();
        }

    }

    private static final int DEFAULT_MAX_ENTRIES = 20;
    private static final long serialVersionUID = 1L;
    private static final LinkedHashMapFeedInfoCache _instance = new LinkedHashMapFeedInfoCache();

    private int maxEntries = DEFAULT_MAX_ENTRIES;

    /**
     * Get the global instance of the cache
     *
     * @return an implementation of FeedFetcherCache
     */
    public static final FeedFetcherCache getInstance() {
        return _instance;
    }

    /**
     * <p>
     * Constructor for HashMapFeedInfoCache
     * </p>
     *
     * <p>
     * Only use this if you want multiple instances of the cache. Usually {@link #getInstance()} is
     * more appropriate.
     * </p>
     *
     * @see #getInstance()
     */
    public LinkedHashMapFeedInfoCache() {
        super();
    }

    @Override
    protected Map<String, SyndFeedInfo> createInfoCache() {
        return Collections.synchronizedMap(new CacheImpl());
    }

    public synchronized final int getMaxEntries() {
        return maxEntries;
    }

    public synchronized final void setMaxEntries(final int maxEntries) {
        this.maxEntries = maxEntries;
    }

}
