package org.rometools.unittest;

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

    protected FeedTest(final String feedFileName) {
        _feedFileName = feedFileName;
    }

    protected String getFeedFileName() {
        return _feedFileName;
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

    protected SyndFeed getSyndFeed() throws Exception {
        final SyndFeedInput in = new SyndFeedInput();
        return in.build(getFeedReader());
    }

    protected Document getCachedJDomDoc() throws Exception {
        if (_jDomDoc == null) {
            _jDomDoc = getJDomDoc();
        }
        return _jDomDoc;
    }

    protected WireFeed getCachedWireFeed() throws Exception {
        if (_wireFeed == null) {
            _wireFeed = getWireFeed();
        }
        return _wireFeed;
    }

    protected SyndFeed getCachedSyndFeed() throws Exception {
        if (_syndFeed == null) {
            _syndFeed = getSyndFeed();
        }
        return _syndFeed;
    }

}
