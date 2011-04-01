/*
 * Created on Jun 24, 2004
 *
 */
package com.sun.syndication.unittest;

import java.util.List;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;

/**
 * @author pat
 *
 */
public class TestSyndFeedRSS20 extends TestSyndFeedRSS094 {

	public TestSyndFeedRSS20() {
		super("rss_2.0");
	}

    protected TestSyndFeedRSS20(String type) {
        super(type);
    }

    protected TestSyndFeedRSS20(String feedType,String feedFileName) {
        super(feedType,feedFileName);
    }
    
    protected void _testItem(int i) throws Exception {
        super._testItem(i);
        List items = getCachedSyndFeed().getEntries();
        SyndEntry entry = (SyndEntry) items.get(i);
        assertProperty(((SyndContent)entry.getContents().get(0)).getValue(), "channel.item["+i+"].content");
    }
    
    /**
     * Test we can get to RSS attributes which aren't exposed in the SyndEntry object
     * @throws Exception
     */
    public void testPreservedWireItems() throws Exception {
    	SyndEntry syndEntry1 = (SyndEntry) getCachedSyndFeed(true).getEntries().get(0);
    	Object o = syndEntry1.getWireEntry();
    	assertNotNull(o);
    	assertTrue(o instanceof Item);
    	if (o instanceof Item) {
			Item item = (Item) o;
			assertEquals("rss_2.0.channel.item[0].comments", item.getComments());
		}    	
    }
    
    public void testPreserveWireFeedComments() throws Exception {
    	WireFeed wf = getCachedSyndFeed(true).originalWireFeed();
    	assertNotNull(wf);
    	assertTrue(wf instanceof Channel);
    	if (wf instanceof Channel) {
			Channel channel = (Channel) wf;
			assertEquals("rss_2.0.channel.item[0].comments", ((Item)channel.getItems().get(0)).getComments());
			assertEquals("rss_2.0.channel.item[1].comments", ((Item)channel.getItems().get(1)).getComments());
		}
    }

}
