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

public class TestSyndFeedAtom03 extends SyndFeedTest {

    public TestSyndFeedAtom03() {
        super("atom_0.3");
    }

    protected TestSyndFeedAtom03(final String type) {
        super(type);
    }

    protected TestSyndFeedAtom03(final String feedType, final String feedFileName) {
        super(feedType, feedFileName);
    }

    @Override
    public void testTitle() throws Exception {
        assertProperty(this.getCachedSyndFeed().getTitle(), "feed.title");
    }

    @Override
    public void testLink() throws Exception {
        assertProperty(this.getCachedSyndFeed().getLink(), "feed.link^href");
    }

    public void getAuthor() throws Exception {
        assertProperty(this.getCachedSyndFeed().getAuthor(), "feed.author.name");
    }

    public void testCopyright() throws Exception {
        assertProperty(this.getCachedSyndFeed().getCopyright(), "feed.copyright");
    }

    @Override
    public void testPublishedDate() throws Exception {
        final LocalDateTime d = DateParser.parseW3CDateTime("2000-01-01T00:00:00Z", Locale.US);
        assertEquals(this.getCachedSyndFeed().getPublishedDate(), d);
    }

    protected void testEntry(final int i) throws Exception {
        final List<SyndEntry> items = this.getCachedSyndFeed().getEntries();
        final SyndEntry entry = items.get(i);
        assertProperty(entry.getTitle(), "feed.entry[" + i + "].title");
        assertProperty(entry.getLink(), "feed.entry[" + i + "].link^href");
        assertProperty(entry.getAuthor(), "feed.entry[" + i + "].author.name");
        final LocalDateTime d = DateParser.parseW3CDateTime("2000-0" + (i + 1) + "-01T00:00:00Z", Locale.US);
        assertEquals(entry.getPublishedDate(), d);
        assertProperty(entry.getDescription().getValue(), "feed.entry[" + i + "].summary");
        assertProperty(entry.getContents().get(0).getValue(), "feed.entry[" + i + "].content[0]");
        assertProperty(entry.getContents().get(1).getValue(), "feed.entry[" + i + "].content[1]");
    }

    public void testEntry0() throws Exception {
        testEntry(0);
    }

    public void testEntry1() throws Exception {
        testEntry(1);
    }

    @Override
    public void testDescription() throws Exception {
        assertEqualsStr("feed.tagline", this.getCachedSyndFeed().getDescription());
    }

    @Override
    public void testEntryLink() throws Exception {
        assertEqualsStr("feed.entry[0].link^href", getEntryLink(this.getCachedSyndFeed().getEntries().get(0)));
        assertEqualsStr("feed.entry[1].link^href", getEntryLink(this.getCachedSyndFeed().getEntries().get(1)));
    }

    @Override
    public void testLanguage() throws Exception {
        // not supported
    }

    @Override
    public void testImage() throws Exception {
        // not supported
    }

    @Override
    public void testEntryTitle() throws Exception {
        assertEqualsStr("feed.entry[0].title", getEntryTitle(this.getCachedSyndFeed().getEntries().get(0)));
        assertEqualsStr("feed.entry[1].title", getEntryTitle(this.getCachedSyndFeed().getEntries().get(1)));
    }

    @Override
    public void testEntryDescription() throws Exception {
        assertEqualsStr("feed.entry[0].summary", getEntryDescription(this.getCachedSyndFeed().getEntries().get(0)));
        assertEqualsStr("feed.entry[1].summary", getEntryDescription(this.getCachedSyndFeed().getEntries().get(1)));
    }

}
