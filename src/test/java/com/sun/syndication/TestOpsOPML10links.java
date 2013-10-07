/*
 * TestOpsOPML10.java
 *
 * Created on April 25, 2006, 4:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.sun.syndication;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import test.NullWriter;
import test.TestUtil;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.WireFeedInput;
import com.sun.syndication.io.WireFeedOutput;

/**
 * 
 * @author cooper
 */
public class TestOpsOPML10links extends FeedOpsTest {

    /** Creates a new instance of TestOpsOPML10 */
    public TestOpsOPML10links() {
        super("opml_1.0_links");
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

        assertEquals(sFeed2.createWireFeed(), sFeed1.createWireFeed());
    }

    public void testTemp() throws Exception {
        final WireFeedInput input = new WireFeedInput();
        final WireFeed wf = input.build(TestUtil.loadFile("/opml_1.0_links.xml"));
        final WireFeedOutput output = new WireFeedOutput();

        final SyndFeedImpl sf = new SyndFeedImpl(wf);
        sf.setFeedType("rss_2.0");
        sf.setDescription("");
        sf.setLink("http://foo.com");
        sf.setFeedType("opml_1.0");
        output.output(sf.createWireFeed(), new NullWriter());
    }

}
