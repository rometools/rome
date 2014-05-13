package com.sun.syndication.unittest;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import junit.framework.TestCase;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.impl.ConfigurableClassLoader;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.WireFeedInput;

/**
 * @author pat, tucu
 *
 */
public abstract class FeedTest extends TestCase {
    private final String feedFileName;
    private Document jDomDoc = null;
    private WireFeed wireFeed = null;
    private SyndFeed syndFeed = null;

    private boolean preservingWireFeed = false;

    protected FeedTest(final String feedFileName) {
        this.feedFileName = feedFileName;
    }

    protected String getFeedFileName() {
        return feedFileName;
    }

    protected Reader getFeedReader() throws Exception {
        final ClassLoader ClassLoader = ConfigurableClassLoader.INSTANCE.getClassLoader();
        final InputStream resource = ClassLoader.getResourceAsStream(getFeedFileName());
        assertNotNull("Could not find resource " + getFeedFileName(), resource);
        return new InputStreamReader(resource);
    }

    protected Document getJDomDoc() throws Exception {
        final SAXBuilder saxBuilder = new SAXBuilder(XMLReaders.NONVALIDATING);
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
        if (jDomDoc == null) {
            jDomDoc = getJDomDoc();
        }
        return jDomDoc;
    }

    protected WireFeed getCachedWireFeed() throws Exception {
        if (wireFeed == null) {
            wireFeed = getWireFeed();
        }
        return wireFeed;
    }

    protected SyndFeed getCachedSyndFeed(final boolean preserveWireFeed) throws Exception {

        if (syndFeed == null || preservingWireFeed != preserveWireFeed) {
            syndFeed = getSyndFeed(preserveWireFeed);
            preservingWireFeed = preserveWireFeed;
        }
        return syndFeed;

    }

    protected SyndFeed getCachedSyndFeed() throws Exception {
        return this.getCachedSyndFeed(false);
    }

}
