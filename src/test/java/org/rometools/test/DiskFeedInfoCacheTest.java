package org.rometools.test;

import org.rometools.fetcher.impl.DiskFeedInfoCache;
import org.rometools.fetcher.impl.SyndFeedInfo;
import java.net.*;
import java.io.File;
import junit.framework.TestCase;

public class DiskFeedInfoCacheTest extends TestCase {

	public void testClear() throws Exception {
		File cacheDir = new File("test-cache");
		cacheDir.mkdir();
		cacheDir.deleteOnExit();
		
		final DiskFeedInfoCache cache = new DiskFeedInfoCache(cacheDir.getCanonicalPath());
		SyndFeedInfo info = new SyndFeedInfo();
		URL url = new URL("http://nowhere.com");
		cache.setFeedInfo(url, info);
		
		cache.clear();
		final Object returned = cache.getFeedInfo(url);
		assertTrue( returned == null );
	}
	
	public void testRemove() throws Exception {
		File cacheDir = new File("test-cache");
		cacheDir.mkdir();
		cacheDir.deleteOnExit();
		
		final DiskFeedInfoCache cache = new DiskFeedInfoCache( cacheDir.getCanonicalPath() );
		SyndFeedInfo info = new SyndFeedInfo();
		URL url = new URL("http://nowhere.com");
		cache.setFeedInfo( url, info );
		
		SyndFeedInfo removedInfo = cache.remove( url );
		assertTrue( removedInfo.equals(info) );
		SyndFeedInfo shouldBeNull = cache.remove( url );
		assertTrue( null == shouldBeNull );	
	}
	
}
