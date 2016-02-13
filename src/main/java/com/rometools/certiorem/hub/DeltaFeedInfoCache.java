/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rometools.certiorem.hub;

import java.net.URL;

import com.rometools.fetcher.impl.FeedFetcherCache;
import com.rometools.fetcher.impl.SyndFeedInfo;

/**
 * Wrapper FeedFetcherCache that wraps a backing FeedFetcherCache and makes sure that any
 * SyndFeedInfo used within it are replaced with a DeltaSyndFeedInfo which is capable of tracking
 * changes to entries in the underlying feed.
 *
 * @author najmi
 */
public class DeltaFeedInfoCache implements FeedFetcherCache {

    FeedFetcherCache backingCache;

    public DeltaFeedInfoCache(final FeedFetcherCache backingCache) {
        this.backingCache = backingCache;
    }

    @Override
    public SyndFeedInfo getFeedInfo(final URL feedUrl) {
        return backingCache.getFeedInfo(feedUrl);
    }

    @Override
    public void setFeedInfo(final URL feedUrl, SyndFeedInfo syndFeedInfo) {
        // Make sure that syndFeedInfo is an instance of DeltaSyndFeedInfo
        if (!(syndFeedInfo instanceof DeltaSyndFeedInfo)) {
            syndFeedInfo = new DeltaSyndFeedInfo(syndFeedInfo);
        }
        backingCache.setFeedInfo(feedUrl, syndFeedInfo);
    }

    @Override
    public void clear() {
        backingCache.clear();
    }

    @Override
    public SyndFeedInfo remove(final URL feedUrl) {
        return backingCache.remove(feedUrl);
    }

}
