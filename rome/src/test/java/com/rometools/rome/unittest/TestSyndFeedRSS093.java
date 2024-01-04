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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.impl.DateParser;

public class TestSyndFeedRSS093 extends TestSyndFeedRSS092 {

    public TestSyndFeedRSS093() {
        super("rss_0.93");
    }

    protected TestSyndFeedRSS093(final String type) {
        super(type);
    }

    protected TestSyndFeedRSS093(final String feedType, final String feedFileName) {
        super(feedType, feedFileName);
    }

    @Override
    protected void testItem(final int i) throws Exception {
        super.testItem(i);
        final List<SyndEntry> items = this.getCachedSyndFeed().getEntries();
        final SyndEntry entry = items.get(i);
        final String date = "0" + (i + 1) + " Jan 2001 00:00:00 GMT";
        final LocalDateTime d = DateParser.parseRFC822(date, Locale.US);
        assertEquals(entry.getPublishedDate(), d);
        testDescriptionType(entry, i);
    }

    protected void testDescriptionType(final SyndEntry entry, final int i) throws Exception {
        assertProperty(entry.getDescription().getType(), "channel.item[" + i + "].description^type");
    }

    @Override
    public void testEntryPublishedDate() throws Exception {
        assertEquals(
                DateParser.parseRFC822("Mon, 01 Jan 2001 00:00:00 GMT", Locale.US),
                getEntryPublishedDate(this.getCachedSyndFeed().getEntries().get(0))
        );
        assertEquals(
                DateParser.parseRFC822("Tue, 02 Jan 2001 00:00:00 GMT", Locale.US),
                getEntryPublishedDate(this.getCachedSyndFeed().getEntries().get(1))
        );
    }

}
