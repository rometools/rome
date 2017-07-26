/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rometools.rome.osgi;

import static org.junit.Assert.assertEquals;

import com.rometools.fetcher.FeedFetcher;
import com.rometools.fetcher.impl.FeedFetcherCache;
import com.rometools.fetcher.impl.HashMapFeedInfoCache;
import com.rometools.fetcher.impl.HttpURLFeedFetcher;
import com.rometools.rome.feed.synd.SyndFeed;

import org.junit.Test;

import java.net.URL;

public class FetcherIT {

    @Test
    public void test() throws Exception {
        FeedFetcherCache cache = HashMapFeedInfoCache.getInstance();
        FeedFetcher fetcher = new HttpURLFeedFetcher(cache);
        SyndFeed feed = fetcher.retrieveFeed(new URL("https://www.w3.org/blog/news/feed/atom"));

        assertEquals("W3C News", feed.getTitle());
    }
}
