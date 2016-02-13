/*
 * GeneratorTest.java
 * JUnit based test
 *
 * Created on April 16, 2006, 5:58 PM
 */

package com.rometools.modules.photocast.io;

import java.io.File;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.photocast.PhotocastModule;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;

/**
 *
 * @author cooper
 */
public class GeneratorTest extends AbstractTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(GeneratorTest.class);

    public GeneratorTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(GeneratorTest.class);

        return suite;
    }

    /**
     * Test of generate method, of class com.rometools.rome.feed.module.photocast.io.Generator.
     */
    public void testGenerate() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();

        final SyndFeed feed = input.build(new File(super.getTestFile("index.rss")));
        final List<SyndEntry> entries = feed.getEntries();
        for (int i = 0; i < entries.size(); i++) {
            LOG.debug("{}", entries.get(i).getModule(PhotocastModule.URI));
        }
        final SyndFeedOutput output = new SyndFeedOutput();
        output.output(feed, new File("target/index.rss"));
        final SyndFeed feed2 = input.build(new File("target/index.rss"));
        final List<SyndEntry> entries2 = feed2.getEntries();
        for (int i = 0; i < entries.size(); i++) {
            assertEquals("Module test", entries.get(i).getModule(PhotocastModule.URI), entries2.get(i).getModule(PhotocastModule.URI));
        }
    }

    /**
     * Test of getNamespaces method, of class
     * com.rometools.rome.feed.module.photocast.io.Generator.
     */
    public void testGetNamespaces() {
        // TODO add your test code.
    }

    /**
     * Test of getNamespaceUri method, of class
     * com.rometools.rome.feed.module.photocast.io.Generator.
     */
    public void testGetNamespaceUri() {
        // TODO add your test code.
    }

}
