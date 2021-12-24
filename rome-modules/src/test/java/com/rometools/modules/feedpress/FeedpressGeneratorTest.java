package com.rometools.modules.feedpress;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.feedpress.io.FeedpressGenerator;
import com.rometools.modules.feedpress.modules.FeedpressModule;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;
import junit.framework.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;

public class FeedpressGeneratorTest extends AbstractTestCase {

    private static final Logger log = LoggerFactory.getLogger(FeedpressGeneratorTest.class);

    public FeedpressGeneratorTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() {
    }

    @Override
    protected void tearDown() {
    }

    public static junit.framework.Test suite() {
        return new TestSuite(FeedpressGeneratorTest.class);
    }

    public void testGenerateRss() throws Exception {

        log.debug("testGenerateRss");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("feedpress/rss.xml")).toURI().toURL()));
        feed.getModule(FeedpressModule.URI);
        final SyndFeedOutput output = new SyndFeedOutput();
        final StringWriter writer = new StringWriter();
        output.output(feed, writer);

        final String xml = writer.toString();
        assertTrue(xml.contains("xmlns:feedpress=\"https://feed.press/xmlns\""));
        assertTrue(xml.contains("<feedpress:newsletterId>abc123</feedpress:newsletterId>"));
        assertTrue(xml.contains("<feedpress:locale>en</feedpress:locale>"));
        assertTrue(xml.contains("<feedpress:podcastId>xyz123</feedpress:podcastId>"));
        assertTrue(xml.contains("<feedpress:cssFile>http://example.org/style.css</feedpress:cssFile>"));

        log.debug("{}", writer);

    }

    public void testGenerateAtom() throws Exception {

        log.debug("testGenerateAtom");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("feedpress/atom.xml")).toURI().toURL()));
        feed.getModule(FeedpressModule.URI);
        final SyndFeedOutput output = new SyndFeedOutput();
        final StringWriter writer = new StringWriter();
        output.output(feed, writer);

        final String xml = writer.toString();
        assertTrue(xml.contains("xmlns:feedpress=\"https://feed.press/xmlns\""));
        assertTrue(xml.contains("<feedpress:newsletterId>abc123</feedpress:newsletterId>"));
        assertTrue(xml.contains("<feedpress:locale>en</feedpress:locale>"));
        assertTrue(xml.contains("<feedpress:podcastId>xyz123</feedpress:podcastId>"));
        assertTrue(xml.contains("<feedpress:cssFile>http://example.org/style.css</feedpress:cssFile>"));

        log.debug("{}", writer);

    }

    public void testGetNamespaceUri() {
        assertEquals("Namespace", FeedpressModule.URI, new FeedpressGenerator().getNamespaceUri());
    }

}