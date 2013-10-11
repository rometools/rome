package org.rometools.fetcher.impl;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>An implementation of the {@link org.rometools.fetcher.impl.FeedFetcherCache} interface.</p>
 * 
 * <p>Unlike the HashMapFeedInfoCache this implementation will not grow unbound</p>
 * 
 * @author Javier Kohen
 * @author Nick Lothian
 *
 */
public class LinkedHashMapFeedInfoCache extends HashMapFeedInfoCache {
	private final class CacheImpl extends LinkedHashMap {
		private static final long serialVersionUID = -6977191330127794920L;

		public CacheImpl() {
			super(16, 0.75F, true);
		}
		
		protected boolean removeEldestEntry(Map.Entry eldest) {
			return size() > getMaxEntries();
		}
	}
	
	private static final int DEFAULT_MAX_ENTRIES = 20;

	private static final long serialVersionUID = 1694228973357997417L;

	private int maxEntries = DEFAULT_MAX_ENTRIES;

	private final static LinkedHashMapFeedInfoCache _instance = new LinkedHashMapFeedInfoCache();	
	
	
	/**
	 * Get the global instance of the cache
	 * @return an implementation of FeedFetcherCache
	 */
	public static final FeedFetcherCache getInstance() {
		return _instance;
	}

	/**
	 * <p>Constructor for HashMapFeedInfoCache</p>
	 * 
	 * <p>Only use this if you want multiple instances of the cache. 
	 * Usually {@link #getInstance()} is more appropriate.</p>
	 *
	 * @see #getInstance()
	 */
	public LinkedHashMapFeedInfoCache() {
		super();
	}

	protected Map createInfoCache() {
		return Collections.synchronizedMap(new CacheImpl());
	}

	public synchronized final int getMaxEntries() {
		return maxEntries;
	}

	public synchronized final void setMaxEntries(int maxEntries) {
		this.maxEntries = maxEntries;
	}	
	
}
