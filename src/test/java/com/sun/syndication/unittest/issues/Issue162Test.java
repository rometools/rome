package com.sun.syndication.unittest.issues;

import java.util.List;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.unittest.TestSyndFeedRSS20;

/**
 * Test for #161: No source element in RSS 2.0 items.
 * 
 * @author Martin Kurz
 * 
 */
public class Issue162Test extends TestSyndFeedRSS20 {

    public Issue162Test() {
        super("rss_2.0");
    }

    public void testWireFeed() throws Exception {
        final Channel channel = (Channel) getCachedWireFeed();
        assertProperty(channel.getDocs(), "channel.docs");
        assertProperty(channel.getGenerator(), "channel.generator");
        assertProperty(channel.getManagingEditor(), "channel.managingEditor");
        assertProperty(channel.getWebMaster(), "channel.webMaster");
    }

    public void testWireFeedItems() throws Exception {
        final int count = ((Channel) getCachedWireFeed()).getItems().size();
        for (int i = 0; i < count; i++) {
            testItem(i);
        }
    }

    protected void testWireFeedItem(final int i) throws Exception {
        final List<Item> items = ((Channel) getCachedWireFeed()).getItems();
        final Item entry = items.get(i);
        assertProperty(entry.getComments(), "channel.item[" + i + "].comments");
    }

    public void testSyndFeed() throws Exception {
        final SyndFeed feed = this.getCachedSyndFeed();
        assertProperty(feed.getDocs(), "channel.docs");
        assertProperty(feed.getGenerator(), "channel.generator");
        assertProperty(feed.getManagingEditor(), "channel.managingEditor");
        assertProperty(feed.getWebMaster(), "channel.webMaster");
    }

    public void testSyndFeedItems() throws Exception {
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
        assertProperty(entry.getComments(), "channel.item[" + i + "].comments");
    }

}
