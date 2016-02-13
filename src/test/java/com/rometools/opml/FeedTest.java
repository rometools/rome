package com.rometools.opml;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import junit.framework.TestCase;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.WireFeedInput;

/**
 * @author pat, tucu
 *
 */
public abstract class FeedTest extends TestCase {

    private final String fileName;
    private Document jDomDoc = null;
    private WireFeed wireFeed = null;
    private SyndFeed syndFeed = null;

    protected FeedTest(final String feedFileName) {
        fileName = feedFileName;
    }

    protected String getFeedFileName() {
        return fileName;
    }

    protected Reader getFeedReader() throws Exception {
        final InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(getFeedFileName());
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

    protected SyndFeed getSyndFeed() throws Exception {
        final SyndFeedInput in = new SyndFeedInput();
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

    protected SyndFeed getCachedSyndFeed() throws Exception {
        if (syndFeed == null) {
            syndFeed = getSyndFeed();
        }
        return syndFeed;
    }

}
