/*
 *  Copyright 2011 robert.cooper.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package com.rometools.rome.unittest.issues;

import java.util.List;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.unittest.TestSyndFeedRSS094;

public class Issue2Test extends TestSyndFeedRSS094 {

    @Override
    protected void testItem(final int i) throws Exception {
        super.testItem(i);
        final List<SyndEntry> items = this.getCachedSyndFeed().getEntries();
        final SyndEntry entry = items.get(i);
        testComments(entry, i);
    }

    protected void testComments(final SyndEntry entry, final int i) throws Exception {
        assertProperty(entry.findRelatedLink("comments").getHref(), "rss_0.94.channel.item[" + i + "].comments");
    }

}
