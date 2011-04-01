/*
 * Created on Jun 24, 2004
 *
 */
package com.sun.syndication.unittest;

import com.sun.syndication.feed.synd.SyndEntry;

import java.util.List;


/**
 * @author pat
 *
 */
public class TestSyndFeedRSS094 extends TestSyndFeedRSS093 {
    public TestSyndFeedRSS094() {
        super("rss_0.94");
    }

    protected TestSyndFeedRSS094(String type) {
        super(type);
    }

    protected TestSyndFeedRSS094(String feedType, String feedFileName) {
        super(feedType, feedFileName);
    }

    public void testCategories() throws Exception {
        _testCategories(getCachedSyndFeed().getCategories(), "channel");
    }

    

    @Override
    protected void _testDescriptionType(SyndEntry entry, int i)
        throws Exception {
    }

    @Override
    protected void _testItem(int i) throws Exception {
        super._testItem(i);

        List items = getCachedSyndFeed()
                         .getEntries();
        SyndEntry entry = (SyndEntry) items.get(i);

        assertProperty(entry.getAuthor(), "channel.item[" + i + "].author");
       
    }

    @Override
    protected void _testUri(SyndEntry entry, int i) throws Exception {
        assertProperty(entry.getUri(), "channel.item[" + i + "].guid");
    }
}
