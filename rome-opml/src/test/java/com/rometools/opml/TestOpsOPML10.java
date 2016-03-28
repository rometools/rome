/*
 * TestOpsOPML10.java
 *
 * Created on April 25, 2006, 4:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rometools.opml;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;

/**
 *
 * @author cooper
 */
public class TestOpsOPML10 extends FeedOpsTest {

    /** Creates a new instance of TestOpsOPML10 */
    public TestOpsOPML10() {
        super("opml_1.0");
    }

    // 1.6
    @Override
    public void testWireFeedSyndFeedConversion() throws Exception {
        final SyndFeed sFeed1 = getCachedSyndFeed();
        final WireFeed wFeed1 = sFeed1.createWireFeed();
        final SyndFeed sFeed2 = new SyndFeedImpl(wFeed1);
        PrintWriter w = new PrintWriter(new FileOutputStream("target/test-reports/1"));
        w.println(sFeed1.toString());
        w.close();
        w = new PrintWriter(new FileOutputStream("target/test-reports/2"));
        w.println(sFeed2.toString());
        w.close();

        assertEquals(sFeed1, sFeed2);
    }

}
