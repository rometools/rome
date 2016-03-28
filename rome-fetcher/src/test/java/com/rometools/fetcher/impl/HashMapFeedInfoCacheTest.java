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
