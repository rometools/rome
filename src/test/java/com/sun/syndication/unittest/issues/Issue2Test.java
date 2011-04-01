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

package com.sun.syndication.unittest.issues;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.unittest.TestSyndFeedRSS094;
import java.util.List;

/**
 *
 * @author robert.cooper
 */
public class Issue2Test extends TestSyndFeedRSS094 {

    @Override
    protected void _testItem(int i) throws Exception {
        super._testItem(i);
        List<SyndEntry> items = getCachedSyndFeed()
                         .getEntries();
        SyndEntry entry = items.get(i);
        _testComments(entry, i);
    }

    protected void _testComments(SyndEntry entry, int i)
        throws Exception {
        assertProperty(entry.findRelatedLink("comments").getHref(), "rss_0.94.channel.item[" + i + "].comments");
    }

}
