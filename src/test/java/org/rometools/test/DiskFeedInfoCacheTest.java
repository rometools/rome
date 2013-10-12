package org.rometools.test;

import java.io.File;
import java.net.URL;

import junit.framework.TestCase;

import org.rometools.fetcher.impl.DiskFeedInfoCache;
import org.rometools.fetcher.impl.SyndFeedInfo;

public class DiskFeedInfoCacheTest extends TestCase {

    public void testClear() throws Exception {
        final File cacheDir = new File("test-cache");
        cacheDir.mkdir();
        cacheDir.deleteOnExit();

        final DiskFeedInfoCache cache = new DiskFeedInfoCache(cacheDir.getCanonicalPath());
        final SyndFeedInfo info = new SyndFeedInfo();
        final URL url = new URL("http://nowhere.com");
        cache.setFeedInfo(url, info);

        cache.clear();
        final Object returned = cache.getFeedInfo(url);
        assertTrue(returned == null);
    }

    public void testRemove() throws Exception {
        final File cacheDir = new File("test-cache");
        cacheDir.mkdir();
        cacheDir.deleteOnExit();

        final DiskFeedInfoCache cache = new DiskFeedInfoCache(cacheDir.getCanonicalPath());
        final SyndFeedInfo info = new SyndFeedInfo();
        final URL url = new URL("http://nowhere.com");
        cache.setFeedInfo(url, info);

        final SyndFeedInfo removedInfo = cache.remove(url);
        assertTrue(removedInfo.equals(info));
        final SyndFeedInfo shouldBeNull = cache.remove(url);
        assertTrue(null == shouldBeNull);
    }

}
