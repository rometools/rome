package com.rometools.modules.commentapi;

import java.io.File;
import java.io.StringWriter;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.commentapi.io.CommentAPIModuleGenerator;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;

import junit.framework.TestSuite;

public class CommentAPIGeneratorTest extends AbstractTestCase {

    public CommentAPIGeneratorTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() {
    }

    @Override
    protected void tearDown() {
    }

    public static junit.framework.Test suite() {
        return new TestSuite(CommentAPIGeneratorTest.class);
    }

    public void testGenerateRss() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("commentapi/rss.xml"))));
        final SyndFeedOutput output = new SyndFeedOutput();
        final StringWriter writer = new StringWriter();
        output.output(feed, writer);
    }

    public void testGetNamespaceUri() {
        assertEquals("Namespace", CommentAPI.URI, new CommentAPIModuleGenerator().getNamespaceUri());
    }
    
}
