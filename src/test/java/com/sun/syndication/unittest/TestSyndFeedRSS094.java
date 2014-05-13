/*
 * Created on Jun 24, 2004
 *
 */
package com.sun.syndication.unittest;

import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;

/**
 * @author pat
 *
 */
public class TestSyndFeedRSS094 extends TestSyndFeedRSS093 {
    public TestSyndFeedRSS094() {
        super("rss_0.94");
    }

    protected TestSyndFeedRSS094(final String type) {
        super(type);
    }

    protected TestSyndFeedRSS094(final String feedType, final String feedFileName) {
        super(feedType, feedFileName);
    }

    public void testCategories() throws Exception {
        testCategories(this.getCachedSyndFeed().getCategories(), "channel");
    }

    @Override
    protected void testDescriptionType(final SyndEntry entry, final int i) throws Exception {
    }

    @Override
    protected void testItem(final int i) throws Exception {
        super.testItem(i);

        final List<SyndEntry> items = this.getCachedSyndFeed().getEntries();
        final SyndEntry entry = items.get(i);

        assertProperty(entry.getAuthor(), "channel.item[" + i + "].author");

    }

    @Override
    protected void testUri(final SyndEntry entry, final int i) throws Exception {
        assertProperty(entry.getUri(), "channel.item[" + i + "].guid");
    }
}
