package com.rometools.modules.fyyd;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.fyyd.io.FyydParser;
import com.rometools.modules.fyyd.modules.FyydModule;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import junit.framework.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FyydParserTest extends AbstractTestCase {

    private static final Logger log = LoggerFactory.getLogger(FyydParserTest.class);

    public FyydParserTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() {
    }

    @Override
    protected void tearDown() {
    }

    public static junit.framework.Test suite() {
        return new TestSuite(FyydParserTest.class);
    }

    public void testParseRss() throws Exception {

        log.debug("testParseRss");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("fyyd/rss.xml")).toURI().toURL()));
        final FyydModule fyyd = (FyydModule) feed.getModule(FyydModule.URI);

        assertNotNull(fyyd);
        assertEquals("abcdefg", fyyd.getVerify());
    }

    public void testParseAtom() throws Exception {

        log.debug("testParseAtom");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("fyyd/atom.xml")).toURI().toURL()));
        final FyydModule fyyd = (FyydModule) feed.getModule(FyydModule.URI);

        assertNotNull(fyyd);
        assertEquals("abcdefg", fyyd.getVerify());
    }

    public void testGetNamespaceUri() {
        assertEquals("Namespace", FyydModule.URI, new FyydParser().getNamespaceUri());
    }

}
