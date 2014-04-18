/*
 * MediaModuleTest.java
 * JUnit based test
 *
 * Created on March 29, 2006, 11:49 PM
 */

package org.rometools.feed.module.mediarss;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.rometools.feed.module.AbstractTestCase;
import org.rometools.feed.module.mediarss.types.MediaContent;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;

/**
 * 
 * @author cooper
 */
public class MediaModuleTest extends AbstractTestCase {

    /**
     * @param testName id of test
     */
    public MediaModuleTest(final String testName) {
        super(testName);
    }

    /**
     * @return actual test suite
     */
    public static Test suite() {
        return new TestSuite(MediaModuleTest.class);
    }

    /**
     * @throws Exception if file not found or not accessible
     */
    public void testGoogleVideo() throws Exception {
        final SyndFeed feed = getSyndFeed(new File(getTestFile("data/YouTube-MostPopular.rss")));
        for (final Object element : feed.getEntries()) {
            final SyndEntry entry = (SyndEntry) element;
            final MediaEntryModule m = (MediaEntryModule) entry.getModule(MediaModule.URI);
            assertNotNull(m);
        }
    }

    /**
     * @throws Exception if file not found or not accessible
     */
    public void testParse() throws Exception {
        final File test = new File(super.getTestFile("xml"));
        final File[] files = test.listFiles();
        for (int j = 0; j < files.length; j++) {
            if (!files[j].getName().endsWith(".xml")) {
                continue;
            }
            final SyndFeed feed = getSyndFeed(files[j]);
            final List<SyndEntry> entries = feed.getEntries();
            final SyndFeedOutput output = new SyndFeedOutput();
            output.output(feed, new File("target/" + j + ".xml"));
            final SyndFeed feed2 = getSyndFeed(new File("target/" + j + ".xml"));
            for (int i = 0; i < entries.size(); i++) {
                BufferedWriter b = new BufferedWriter(new FileWriter("target/" + j + "a.txt"));
                b.write("" + entries.get(i).getModule(MediaModule.URI));
                b.close();
                b = new BufferedWriter(new FileWriter("target/" + j + "b.txt"));
                b.write("" + feed2.getEntries().get(i).getModule(MediaModule.URI));
                b.close();
                assertEquals(entries.get(i).getModule(MediaModule.URI), feed2.getEntries().get(i).getModule(MediaModule.URI));
            }
        }
    }

    /**
     * test url with whitespace in media element (https://github.com/rometools/rome-modules/issues/20).
     * 
     * @throws Exception if file not found or not accessible
     */
    public void testParseMediaContentContainingURLWithSpaces() throws Exception {
        final SyndFeed feed = getSyndFeed(new File(getTestFile("org/rometools/feed/module/mediarss/issue-20.xml")));
        final SyndEntry entry = (SyndEntry) feed.getEntries().get(0);
        final MediaEntryModule m = (MediaEntryModule) entry.getModule(MediaEntryModule.URI);
        assertNotNull("missing media entry module", m);
        final MediaContent[] mcs = m.getMediaContents();
        assertNotNull("missing media:content", mcs);
        assertEquals("wrong count of media:content", 1, mcs.length);
        final MediaContent mc = mcs[0];
        assertEquals("http://www.foo.com/path/containing+spaces/trailer.mov", mc.getReference().toString());
    }

    /**
     * @param file to parse
     * @return SyndFeed implementation
     * @throws IllegalArgumentException
     * @throws IOException if file not found or not accessible
     * @throws FeedException if parsing failed
     */
    private SyndFeed getSyndFeed(final File file) throws IOException, FeedException {
        final SyndFeedInput input = new SyndFeedInput();
        return input.build(file);
    }
}
