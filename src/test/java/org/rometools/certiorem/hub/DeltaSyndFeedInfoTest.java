/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rometools.certiorem.hub;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.rometools.fetcher.impl.HashMapFeedInfoCache;
import org.rometools.fetcher.impl.HttpURLFeedFetcher;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

/**
 *
 * @author najmi
 */
public class DeltaSyndFeedInfoTest {

    private DeltaFeedInfoCache feedInfoCache;
    private HttpURLFeedFetcher feedFetcher;
    private SyndFeed feed;

    @Before
    public void setUp() {
        feedInfoCache = new DeltaFeedInfoCache(new HashMapFeedInfoCache());
        feedFetcher = new HttpURLFeedFetcher(feedInfoCache);
    }

    /**
     * Test of getSyndFeed method, of class DeltaSyndFeedInfo.
     */
    @Test
    public void testGetSyndFeed() throws Exception {

        feed = feedFetcher.retrieveFeed(getFeedUrl());
        List<SyndEntry> entries = feed.getEntries();
        assertTrue(!entries.isEmpty());

        // Fetch again and this time the entries should be empty as none have
        // changed.
        feed = feedFetcher.retrieveFeed(getFeedUrl());
        entries = feed.getEntries();
        assertTrue(entries.isEmpty());
    }

    private URL getFeedUrl() throws IOException {
        final URL feedUrl = new URL("http://news.google.com/news?pz=1&cf=all&ned=us&hl=en&output=rss");
        // URL feedUrl = new
        // URL("http://newsrss.bbc.co.uk/rss/newsonline_world_edition/front_page/rss.xml");
        return feedUrl;
    }

}
