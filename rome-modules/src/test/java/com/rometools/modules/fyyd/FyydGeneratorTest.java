package com.rometools.modules.fyyd;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.fyyd.io.FyydGenerator;
import com.rometools.modules.fyyd.modules.FyydModule;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;
import junit.framework.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;

public class FyydGeneratorTest extends AbstractTestCase {

    private static final Logger log = LoggerFactory.getLogger(FyydGeneratorTest.class);

    public FyydGeneratorTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() {
    }

    @Override
    protected void tearDown() {
    }

    public static junit.framework.Test suite() {
        return new TestSuite(FyydGeneratorTest.class);
    }

    public void testGenerateRss() throws Exception {

        log.debug("testGenerateRss");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("fyyd/rss.xml")).toURI().toURL()));
        feed.getModule(FyydModule.URI);
        final SyndFeedOutput output = new SyndFeedOutput();
        final StringWriter writer = new StringWriter();
        output.output(feed, writer);

        final String xml = writer.toString();
        assertTrue(xml.contains("xmlns:fyyd=\"https://fyyd.de/fyyd-ns/\""));
        assertTrue(xml.contains("<fyyd:verify>abcdefg</fyyd:verify>"));

        log.debug("{}", writer);

    }

    public void testGenerateAtom() throws Exception {

        log.debug("testGenerateAtom");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("fyyd/atom.xml")).toURI().toURL()));
        feed.getModule(FyydModule.URI);
        final SyndFeedOutput output = new SyndFeedOutput();
        final StringWriter writer = new StringWriter();
        output.output(feed, writer);

        final String xml = writer.toString();
        assertTrue(xml.contains("xmlns:fyyd=\"https://fyyd.de/fyyd-ns/\""));
        assertTrue(xml.contains("<fyyd:verify>abcdefg</fyyd:verify>"));

        log.debug("{}", writer);

    }

    public void testGetNamespaceUri() {
        assertEquals("Namespace", FyydModule.URI, new FyydGenerator().getNamespaceUri());
    }


}
