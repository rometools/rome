/*
 * GeneratorTest.java
 * JUnit based test
 *
 * Created on April 16, 2006, 5:58 PM
 */

package org.rometools.feed.module.photocast.io;

import java.io.File;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.rometools.feed.module.AbstractTestCase;
import org.rometools.feed.module.photocast.PhotocastModule;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;

/**
 * 
 * @author cooper
 */
public class GeneratorTest extends AbstractTestCase {

    public GeneratorTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(GeneratorTest.class);

        return suite;
    }

    /**
     * Test of generate method, of class com.sun.syndication.feed.module.photocast.io.Generator.
     */
    public void testGenerate() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();

        final SyndFeed feed = input.build(new File(super.getTestFile("index.rss")));
        final List entries = feed.getEntries();
        for (int i = 0; i < entries.size(); i++) {
            System.out.println(((SyndEntry) entries.get(i)).getModule(PhotocastModule.URI));
        }
        final SyndFeedOutput output = new SyndFeedOutput();
        output.output(feed, new File("target/index.rss"));
        final SyndFeed feed2 = input.build(new File("target/index.rss"));
        final List entries2 = feed2.getEntries();
        for (int i = 0; i < entries.size(); i++) {
            assertEquals("Module test", ((SyndEntry) entries.get(i)).getModule(PhotocastModule.URI),
                    ((SyndEntry) entries2.get(i)).getModule(PhotocastModule.URI));
        }
    }

    /**
     * Test of getNamespaces method, of class com.sun.syndication.feed.module.photocast.io.Generator.
     */
    public void testGetNamespaces() {
        // TODO add your test code.
    }

    /**
     * Test of getNamespaceUri method, of class com.sun.syndication.feed.module.photocast.io.Generator.
     */
    public void testGetNamespaceUri() {
        // TODO add your test code.
    }

}
