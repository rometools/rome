package com.sun.syndication.unittest;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import junit.framework.TestCase;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.WireFeedInput;

/**
 * @author pat, tucu
 * 
 */
public abstract class FeedTest extends TestCase {
    private final String _feedFileName;
    private Document _jDomDoc = null;
    private WireFeed _wireFeed = null;
    private SyndFeed _syndFeed = null;

    private boolean preservingWireFeed = false;

    protected FeedTest(final String feedFileName) {
        this._feedFileName = feedFileName;
    }

    protected String getFeedFileName() {
        return this._feedFileName;
    }

    protected Reader getFeedReader() throws Exception {
        final InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(getFeedFileName());
        assertNotNull("Could not find resource " + getFeedFileName(), resource);
        return new InputStreamReader(resource);
    }

    protected Document getJDomDoc() throws Exception {
        final SAXBuilder saxBuilder = new SAXBuilder(false);
        return saxBuilder.build(getFeedReader());
    }

    protected WireFeed getWireFeed() throws Exception {
        final WireFeedInput in = new WireFeedInput();
        return in.build(getFeedReader());
    }

    protected SyndFeed getSyndFeed(final boolean preserveWireFeed) throws Exception {
        final SyndFeedInput in = new SyndFeedInput();
        in.setPreserveWireFeed(preserveWireFeed);
        return in.build(getFeedReader());
    }

    protected Document getCachedJDomDoc() throws Exception {
        if (this._jDomDoc == null) {
            this._jDomDoc = getJDomDoc();
        }
        return this._jDomDoc;
    }

    protected WireFeed getCachedWireFeed() throws Exception {
        if (this._wireFeed == null) {
            this._wireFeed = getWireFeed();
        }
        return this._wireFeed;
    }

    protected SyndFeed getCachedSyndFeed(final boolean preserveWireFeed) throws Exception {

        if (this._syndFeed == null || this.preservingWireFeed != preserveWireFeed) {
            this._syndFeed = getSyndFeed(preserveWireFeed);
            this.preservingWireFeed = preserveWireFeed;
        }
        return this._syndFeed;

    }

    protected SyndFeed getCachedSyndFeed() throws Exception {
        return this.getCachedSyndFeed(false);
    }

}
