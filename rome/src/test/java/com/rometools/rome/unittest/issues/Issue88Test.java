package com.rometools.rome.unittest.issues;

import com.rometools.rome.unittest.FeedTest;

/**
 * Test for #134: Incorrect handling of CDATA sections.
 *
 * @author Martin Kurz
 *
 */
public class Issue88Test extends FeedTest {

    public Issue88Test() {
        super("rss_2.0.xml");
    }

    public void testStyleSheet() throws Exception {
        assertEquals("stylesheet in syndfeed missing", "http://test.example/test.xslt", this.getCachedSyndFeed().getStyleSheet());
        assertEquals("stylesheet in wirefeed missing", "http://test.example/test.xslt", getCachedWireFeed().getStyleSheet());
    }

}
