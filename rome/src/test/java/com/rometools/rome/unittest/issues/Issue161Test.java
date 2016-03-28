package com.rometools.rome.unittest.issues;

import java.util.List;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.unittest.TestSyndFeedRSS20;

/**
 * Test for #161: No source element in RSS 2.0 items.
 *
 * @author Martin Kurz
 *
 */
public class Issue161Test extends TestSyndFeedRSS20 {

    public Issue161Test() {
        super("rss_2.0");
    }

    public void testItems() throws Exception {
        final int count = this.getCachedSyndFeed().getEntries().size();
        for (int i = 0; i < count; i++) {
            testItem(i);
        }
    }

    @Override
    protected void testItem(final int i) throws Exception {
        super.testItem(i);
        final List<SyndEntry> items = this.getCachedSyndFeed().getEntries();
        final SyndEntry entry = items.get(i);
        assertProperty(entry.getSource().getLink(), "channel.item[" + i + "].source^url");
        assertProperty(entry.getSource().getTitle(), "channel.item[" + i + "].source");
    }

}
