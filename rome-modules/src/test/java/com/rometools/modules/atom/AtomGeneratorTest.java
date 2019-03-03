package com.rometools.modules.atom;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.atom.io.AtomModuleGenerator;
import com.rometools.modules.atom.modules.AtomLinkModule;
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

public class AtomGeneratorTest extends AbstractTestCase {

    private static final Logger log = LoggerFactory.getLogger(AtomGeneratorTest.class);

    public AtomGeneratorTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() {
    }

    @Override
    protected void tearDown() {
    }

    public static junit.framework.Test suite() {
        return new TestSuite(AtomGeneratorTest.class);
    }

    public void testGenerate() throws Exception {

        log.debug("testGenerate");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("atom/rss.xml")).toURI().toURL()));
        feed.getModule(AtomLinkModule.URI);

        final SyndEntry entry = feed.getEntries().get(0);
        entry.getModule(AtomLinkModule.URI);

        final SyndFeedOutput output = new SyndFeedOutput();
        final StringWriter writer = new StringWriter();
        output.output(feed, writer);

        final String xml = writer.toString();
        assertTrue(xml.contains("xmlns:atom=\"http://www.w3.org/2005/Atom\""));
        assertTrue(xml.contains("<atom:author>"));
        assertTrue(xml.contains("<atom:contributor>"));
        assertTrue(xml.contains("<atom:name>Lorem Ipsum</atom:name>"));
        assertTrue(xml.contains("<atom:email>test@example.org</atom:email>"));
        assertTrue(xml.contains("<atom:uri>http://example.org</atom:uri>"));

        log.debug("{}", writer);

    }

    public void testGetNamespaceUri() {
        assertEquals("Namespace", AtomLinkModule.URI, new AtomModuleGenerator().getNamespaceUri());
    }

}
