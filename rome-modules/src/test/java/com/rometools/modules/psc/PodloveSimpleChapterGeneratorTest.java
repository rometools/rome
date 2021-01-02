package com.rometools.modules.psc;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.psc.io.PodloveSimpleChapterGenerator;
import com.rometools.modules.psc.modules.PodloveSimpleChapterModule;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;
import junit.framework.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;

public class PodloveSimpleChapterGeneratorTest extends AbstractTestCase {

    private static final Logger log = LoggerFactory.getLogger(PodloveSimpleChapterGeneratorTest.class);

    public PodloveSimpleChapterGeneratorTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() {
    }

    @Override
    protected void tearDown() {
    }

    public static junit.framework.Test suite() {
        return new TestSuite(PodloveSimpleChapterGeneratorTest.class);
    }

    public void testGenerateRss() throws Exception {

        log.debug("testGenerateRss");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("psc/rss.xml")).toURI().toURL()));
        final SyndEntry entry = feed.getEntries().get(0);
        entry.getModule(PodloveSimpleChapterModule.URI);
        final SyndFeedOutput output = new SyndFeedOutput();
        final StringWriter writer = new StringWriter();
        output.output(feed, writer);

        final String xml = writer.toString();
        log.debug("{}", writer);

        assertTrue(xml.contains("xmlns:psc=\"http://podlove.org/simple-chapters\""));
        assertTrue(xml.contains("<psc:chapters version=\"1.2\">"));
        assertTrue(xml.contains("<psc:chapter start=\"00:00:00.000\" title=\"Lorem Ipsum\" href=\"http://example.org\" image=\"http://example.org/cover\" />"));
    }

    public void testGenerateAtom() throws Exception {

        log.debug("testGenerateAtom");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("psc/atom.xml")).toURI().toURL()));
        final SyndEntry entry = feed.getEntries().get(0);
        entry.getModule(PodloveSimpleChapterModule.URI);
        final SyndFeedOutput output = new SyndFeedOutput();
        final StringWriter writer = new StringWriter();
        output.output(feed, writer);

        final String xml = writer.toString();
        log.debug("{}", writer);

        assertTrue(xml.contains("xmlns:psc=\"http://podlove.org/simple-chapters\""));
        assertTrue(xml.contains("<psc:chapters version=\"1.2\">"));
        assertTrue(xml.contains("<psc:chapter start=\"00:00:00.000\" title=\"Lorem Ipsum\" href=\"http://example.org\" image=\"http://example.org/cover\" />"));
    }

    public void testGetNamespaceUri() {
        assertEquals("Namespace", PodloveSimpleChapterModule.URI, new PodloveSimpleChapterGenerator().getNamespaceUri());
    }

}
