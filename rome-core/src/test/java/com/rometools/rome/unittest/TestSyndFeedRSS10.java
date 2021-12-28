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
 * Created on Jun 24, 2004
 *
 */
package com.rometools.rome.unittest;

import java.util.List;

import com.rometools.rome.feed.synd.SyndEntry;

public class TestSyndFeedRSS10 extends TestSyndFeedRSS090 {

    public TestSyndFeedRSS10() {
        super("rss_1.0");
    }

    protected TestSyndFeedRSS10(final String type) {
        super(type);
    }

    protected TestSyndFeedRSS10(final String feedType, final String feedFileName) {
        super(feedType, feedFileName);
    }

    public void testUri() throws Exception {
        assertProperty(this.getCachedSyndFeed().getUri(), "channel.uri");
    }

    @Override
    protected void testItem(final int i) throws Exception {
        super.testItem(i);
        final List<SyndEntry> items = this.getCachedSyndFeed().getEntries();
        final SyndEntry entry = items.get(i);
        assertProperty(entry.getDescription().getValue(), "item[" + i + "].description");
        assertProperty(entry.getContents().get(0).getValue(), "item[" + i + "].content");
    }

    @Override
    protected void testUri(final SyndEntry entry, final int i) throws Exception {
        assertProperty(entry.getUri(), "channel.items[" + i + "]^rdf:resource");
    }
}
