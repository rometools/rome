package com.rometools.rome.unittest.issues;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.unittest.FeedTest;

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
