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
    protected void _testItem(final int i) throws Exception {
        super._testItem(i);
        final List<SyndEntry> items = this.getCachedSyndFeed().getEntries();
        final SyndEntry entry = items.get(i);
        assertProperty(entry.getDescription().getValue(), "item[" + i + "].description");
        assertProperty(entry.getContents().get(0).getValue(), "item[" + i + "].content");
    }

    @Override
    protected void _testUri(final SyndEntry entry, final int i) throws Exception {
        assertProperty(entry.getUri(), "channel.items[" + i + "]^rdf:resource");
    }
}
