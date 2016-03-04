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
