package com.rometools.modules.psc;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.psc.io.PodloveSimpleChapterParser;
import com.rometools.modules.psc.modules.PodloveSimpleChapterModule;
import com.rometools.modules.psc.types.SimpleChapter;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import junit.framework.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class PodloveSimpleChapterParserTest extends AbstractTestCase {

    private static final Logger log = LoggerFactory.getLogger(PodloveSimpleChapterParserTest.class);

    public PodloveSimpleChapterParserTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() {
    }

    @Override
    protected void tearDown() {
    }

    public static junit.framework.Test suite() {
        return new TestSuite(PodloveSimpleChapterParserTest.class);
    }

    public void testParseRss() throws Exception {

        log.debug("testParseRss");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("psc/rss.xml")).toURI().toURL()));
        final SyndEntry entry = feed.getEntries().get(0);
        final PodloveSimpleChapterModule simpleChapters = (PodloveSimpleChapterModule) entry.getModule(PodloveSimpleChapterModule.URI);

        assertNotNull(simpleChapters);
        for (SimpleChapter c : simpleChapters.getChapters()) {
            assertEquals("00:00:00.000", c.getStart());
            assertEquals("Lorem Ipsum", c.getTitle());
            assertEquals("http://example.org", c.getHref());
            assertEquals("http://example.org/cover", c.getImage());
        }
    }

    public void testParseAtom() throws Exception {

        log.debug("testParseAtom");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("psc/atom.xml")).toURI().toURL()));
        final SyndEntry entry = feed.getEntries().get(0);
        final PodloveSimpleChapterModule simpleChapters = (PodloveSimpleChapterModule) entry.getModule(PodloveSimpleChapterModule.URI);

        assertNotNull(simpleChapters);
        for (SimpleChapter c : simpleChapters.getChapters()) {
            assertEquals("00:00:00.000", c.getStart());
            assertEquals("Lorem Ipsum", c.getTitle());
            assertEquals("http://example.org", c.getHref());
            assertEquals("http://example.org/cover", c.getImage());
        }
    }

    public void testGetNamespaceUri() {
        assertEquals("Namespace", PodloveSimpleChapterModule.URI, new PodloveSimpleChapterParser().getNamespaceUri());
    }

}
