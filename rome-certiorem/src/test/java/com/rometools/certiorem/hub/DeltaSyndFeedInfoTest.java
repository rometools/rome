/*
 * Copyright 2004 Sun Microsystems, Inc.
 * Copyright 2011 ROME Team
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
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.rometools.certiorem.hub;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.List;

import org.junit.Test;

import com.rometools.fetcher.impl.HashMapFeedInfoCache;
import com.rometools.fetcher.impl.HttpURLFeedFetcher;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

public class DeltaSyndFeedInfoTest {

    @Test
    public void testGetSyndFeed() throws Exception {

        final URL url = new URL("https://news.google.com/news?pz=1&cf=all&ned=us&hl=en&output=rss");

        final DeltaFeedInfoCache feedInfoCache = new DeltaFeedInfoCache(new HashMapFeedInfoCache());
        final HttpURLFeedFetcher feedFetcher = new HttpURLFeedFetcher(feedInfoCache);

        // the first time the feed should not be empty
        final SyndFeed firstFeed = feedFetcher.retrieveFeed(url);
        final List<SyndEntry> firstEntries = firstFeed.getEntries();
        assertFalse(firstEntries.isEmpty());

        // fetch once again and this time the entries should be empty because nothing has changed
        final SyndFeed secondFeed = feedFetcher.retrieveFeed(url);
        final List<SyndEntry> secondEntries = secondFeed.getEntries();
        assertTrue(secondEntries.isEmpty());

    }

}
