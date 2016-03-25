package com.rometools.fetcher.impl;

import java.net.URL;

import junit.framework.TestCase;

import com.rometools.fetcher.impl.HashMapFeedInfoCache;
import com.rometools.fetcher.impl.SyndFeedInfo;

public class HashMapFeedInfoCacheTest extends TestCase {

    public void testRemove() throws Exception {
        final HashMapFeedInfoCache cache = new HashMapFeedInfoCache();
        assertNotNull(cache);

        final URL url = new URL("http://foo.com");
        final SyndFeedInfo syndFeedInfo = new SyndFeedInfo();
        syndFeedInfo.setUrl(url);
        cache.setFeedInfo(url, syndFeedInfo);

        final SyndFeedInfo returned = cache.remove(url);
        assertTrue(returned.equals(syndFeedInfo));
        assertTrue(url.equals(returned.getUrl()));
    }

    public void testClear() throws Exception {
        final HashMapFeedInfoCache cache = new HashMapFeedInfoCache();
        assertNotNull(cache);

        final URL url = new URL("http://foo.com");
        final SyndFeedInfo syndFeedInfo = new SyndFeedInfo();
        syndFeedInfo.setUrl(url);
        cache.setFeedInfo(url, syndFeedInfo);

        // clear it
        cache.clear();

        // we should not get a result back
        final Object returned = cache.getFeedInfo(url);
        assertTrue(returned == null);
    }
}
