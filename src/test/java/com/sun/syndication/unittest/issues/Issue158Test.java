package com.sun.syndication.unittest.issues;

import com.sun.syndication.unittest.FeedTest;

/**
 * Test for #161: No source element in RSS 2.0 items.
 * @author Martin Kurz
 *
 */
public class Issue158Test extends FeedTest {

    public Issue158Test() {
        super("rss_1.0-ns-on-channel.xml");
    }

    public void testSyndFeed() throws Exception {
        assertEquals("rss_1.0", this.getCachedSyndFeed().getFeedType());
    }

}
