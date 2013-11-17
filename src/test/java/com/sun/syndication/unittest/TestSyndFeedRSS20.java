/*
 * Created on Jun 24, 2004
 *
 */
package com.sun.syndication.unittest;

import java.util.List;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndEntry;

/**
 * @author pat
 * 
 */
public class TestSyndFeedRSS20 extends TestSyndFeedRSS094 {

    public TestSyndFeedRSS20() {
        super("rss_2.0");
    }

    protected TestSyndFeedRSS20(final String type) {
        super(type);
    }

    protected TestSyndFeedRSS20(final String feedType, final String feedFileName) {
        super(feedType, feedFileName);
    }

    @Override
    protected void testItem(final int i) throws Exception {
        super.testItem(i);
        final List<SyndEntry> items = this.getCachedSyndFeed().getEntries();
        final SyndEntry entry = items.get(i);
        assertProperty(entry.getContents().get(0).getValue(), "channel.item[" + i + "].content");
    }

    /**
     * Test we can get to RSS attributes which aren't exposed in the SyndEntry object
     * 
     * @throws Exception
     */
    public void testPreservedWireItems() throws Exception {
        final SyndEntry syndEntry1 = this.getCachedSyndFeed(true).getEntries().get(0);
        final Object o = syndEntry1.getWireEntry();
        assertNotNull(o);
        assertTrue(o instanceof Item);
        if (o instanceof Item) {
            final Item item = (Item) o;
            assertEquals("rss_2.0.channel.item[0].comments", item.getComments());
        }
    }

    public void testPreserveWireFeedComments() throws Exception {
        final WireFeed wf = this.getCachedSyndFeed(true).originalWireFeed();
        assertNotNull(wf);
        assertTrue(wf instanceof Channel);
        if (wf instanceof Channel) {
            final Channel channel = (Channel) wf;
            assertEquals("rss_2.0.channel.item[0].comments", channel.getItems().get(0).getComments());
            assertEquals("rss_2.0.channel.item[1].comments", channel.getItems().get(1).getComments());
        }
    }

}
