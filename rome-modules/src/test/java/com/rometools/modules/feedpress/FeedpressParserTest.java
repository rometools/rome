package com.rometools.modules.feedpress;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.feedpress.io.FeedpressParser;
import com.rometools.modules.feedpress.modules.FeedpressModule;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import junit.framework.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FeedpressParserTest extends AbstractTestCase {

    private static final Logger log = LoggerFactory.getLogger(FeedpressParserTest.class);

    public FeedpressParserTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() {
    }

    @Override
    protected void tearDown() {
    }

    public static junit.framework.Test suite() {
        return new TestSuite(FeedpressParserTest.class);
    }

    public void testParseRss() throws Exception {

        log.debug("testParseRss");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("feedpress/rss.xml")).toURI().toURL()));
        final FeedpressModule feedpress = (FeedpressModule) feed.getModule(FeedpressModule.URI);

        assertNotNull(feedpress);
        assertEquals("abc123", feedpress.getNewsletterId());
        assertEquals("en", feedpress.getLocale());
        assertEquals("xyz123", feedpress.getPodcastId());
        assertEquals("http://example.org/style.css", feedpress.getCssFile());
    }

    public void testParseAtom() throws Exception {

        log.debug("testParseAtom");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("feedpress/atom.xml")).toURI().toURL()));
        final FeedpressModule feedpress = (FeedpressModule) feed.getModule(FeedpressModule.URI);

        assertNotNull(feedpress);
        assertEquals("abc123", feedpress.getNewsletterId());
        assertEquals("en", feedpress.getLocale());
        assertEquals("xyz123", feedpress.getPodcastId());
        assertEquals("http://example.org/style.css", feedpress.getCssFile());
    }

    public void testGetNamespaceUri() {
        assertEquals("Namespace", FeedpressModule.URI, new FeedpressParser().getNamespaceUri());
    }

}