package com.sun.syndication.unittest.issues;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.unittest.FeedTest;

/**
 * Test for #134: Incorrect handling of CDATA sections.
 * 
 * @author Martin Kurz
 * 
 */
public class Issue134Test extends FeedTest {

    public Issue134Test() {
        super("CDATATestFeed.xml");
    }

    public void testCDataLinks() throws Exception {
        final SyndFeed feed = this.getCachedSyndFeed();
        assertEquals("links differ", feed.getEntries().get(0).getLink(), feed.getEntries().get(1).getLink());
    }

}
