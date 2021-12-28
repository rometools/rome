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

public class TestSyndFeedRSS090 extends SyndFeedTest {

    public TestSyndFeedRSS090() {
        super("rss_0.9");
    }

    protected TestSyndFeedRSS090(final String type) {
        super(type);
    }

    protected TestSyndFeedRSS090(final String feedType, final String feedFileName) {
        super(feedType, feedFileName);
    }

    @Override
    public void testTitle() throws Exception {
        assertProperty(this.getCachedSyndFeed().getTitle(), "channel.title");
    }

    @Override
    public void testLink() throws Exception {
        assertProperty(this.getCachedSyndFeed().getLink(), "channel.link");
    }

    @Override
    public void testDescription() throws Exception {
        assertProperty(this.getCachedSyndFeed().getDescription(), "channel.description");
    }

    public void testImageTitle() throws Exception {
        assertProperty(this.getCachedSyndFeed().getImage().getTitle(), "image.title");
    }

    public void testImageUrl() throws Exception {
        assertProperty(this.getCachedSyndFeed().getImage().getUrl(), "image.url");
    }

    public void testImageLink() throws Exception {
        assertProperty(this.getCachedSyndFeed().getImage().getLink(), "image.link");
    }

    protected void testItem(final int i) throws Exception {
        final List<SyndEntry> items = this.getCachedSyndFeed().getEntries();
        final SyndEntry entry = items.get(i);
        assertProperty(entry.getTitle(), "item[" + i + "].title");
        assertProperty(entry.getLink(), "item[" + i + "].link");

        testUri(entry, i);
    }

    public void testItem0() throws Exception {
        testItem(0);
    }

    public void testItem1() throws Exception {
        testItem(1);
    }

    protected void testUri(final SyndEntry entry, final int i) throws Exception {
        assertProperty(entry.getUri(), "item[" + i + "].link");
    }

    @Override
    public void testLanguage() throws Exception {
        // not supported
    }

    @Override
    public void testPublishedDate() throws Exception {
        // not supported
    }

    @Override
    public void testImage() throws Exception {
        // not supported
    }

    @Override
    public void testEntryTitle() throws Exception {
        assertEqualsStr("item[0].title", getEntryTitle(this.getCachedSyndFeed().getEntries().get(0)));
        assertEqualsStr("item[1].title", getEntryTitle(this.getCachedSyndFeed().getEntries().get(1)));
    }

    @Override
    public void testEntryDescription() throws Exception {
        // I think this should be should work, but it can't seem to find the
        // description
        // LOG.debug(((SyndEntry)getCachedSyndFeed().getEntries().get(0)).getDescription());
    }

    @Override
    public void testEntryLink() throws Exception {
        assertEqualsStr("item[0].link", getEntryLink(this.getCachedSyndFeed().getEntries().get(0)));
        assertEqualsStr("item[1].link", getEntryLink(this.getCachedSyndFeed().getEntries().get(1)));
    }

    @Override
    public void testEntryPublishedDate() throws Exception {
        // not supported
    }

}
