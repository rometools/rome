package com.rometools.modules.atom;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.atom.io.AtomModuleParser;
import com.rometools.modules.atom.modules.AtomLinkModule;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import junit.framework.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class AtomParserTest extends AbstractTestCase {

    private static final Logger log = LoggerFactory.getLogger(AtomParserTest.class);

    public AtomParserTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() {
    }

    @Override
    protected void tearDown() {
    }

    public static junit.framework.Test suite() {
        return new TestSuite(AtomParserTest.class);
    }

    public void testParse() throws Exception {

        log.debug("testParse");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("atom/rss.xml")).toURI().toURL()));

        final AtomLinkModule feedAtomModule = (AtomLinkModule) feed.getModule(AtomLinkModule.URI);
        for (SyndPerson author : feedAtomModule.getAuthors()) {
            assertEquals("Lorem Ipsum", author.getName());
            assertEquals("test@example.org", author.getEmail());
            assertEquals("http://example.org", author.getUri());
        }
        for (SyndPerson contributor : feedAtomModule.getContributors()) {
            assertEquals("Lorem Ipsum", contributor.getName());
            assertEquals("test@example.org", contributor.getEmail());
            assertEquals("http://example.org", contributor.getUri());
        }

        final SyndEntry entry = feed.getEntries().get(0);
        final AtomLinkModule entryAtomModule = (AtomLinkModule) entry.getModule(AtomLinkModule.URI);
        for (SyndPerson author : entryAtomModule.getAuthors()) {
            assertEquals("Lorem Ipsum", author.getName());
            assertEquals("test@example.org", author.getEmail());
            assertEquals("http://example.org", author.getUri());
        }
        for (SyndPerson contributor : entryAtomModule.getContributors()) {
            assertEquals("Lorem Ipsum", contributor.getName());
            assertEquals("test@example.org", contributor.getEmail());
            assertEquals("http://example.org", contributor.getUri());
        }
    }

    public void testGetNamespaceUri() {
        assertEquals("Namespace", AtomLinkModule.URI, new AtomModuleParser().getNamespaceUri());
    }

}