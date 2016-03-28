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

import java.io.File;
import java.net.URL;

import junit.framework.TestCase;

import com.rometools.fetcher.impl.DiskFeedInfoCache;
import com.rometools.fetcher.impl.SyndFeedInfo;

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
