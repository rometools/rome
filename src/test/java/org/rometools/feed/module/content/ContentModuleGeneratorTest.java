/*
 * ContentModuleGeneratorTest.java
 * JUnit based test
 *
 * Created on February 2, 2005, 4:15 PM
 */
package org.rometools.feed.module.content;

import java.io.File;
import java.io.StringWriter;

import org.rometools.feed.module.AbstractTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;

/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert Cooper</a>
 */
public class ContentModuleGeneratorTest extends AbstractTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(ContentModuleGeneratorTest.class);

    public ContentModuleGeneratorTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws java.lang.Exception {
    }

    @Override
    protected void tearDown() throws java.lang.Exception {
    }

    public static junit.framework.Test suite() {
        final junit.framework.TestSuite suite = new junit.framework.TestSuite(ContentModuleGeneratorTest.class);

        return suite;
    }

    /**
     * Test of generate method, of class com.totsp.xml.syndication.content.ContentModuleGenerator.
     */
    public void testGenerate() throws Exception {

        LOG.debug("testGenerate");

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("xml/test-rdf.xml")).toURI().toURL()));
        final SyndEntry entry = feed.getEntries().get(0);
        entry.getModule(ContentModule.URI);
        final SyndFeedOutput output = new SyndFeedOutput();
        final StringWriter writer = new StringWriter();
        output.output(feed, writer);

        LOG.debug("{}", writer);

    }
}
