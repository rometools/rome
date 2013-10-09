/*
 * MediaModuleTest.java
 * JUnit based test
 *
 * Created on March 29, 2006, 11:49 PM
 */

package org.rometools.feed.module.mediarss;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.rometools.feed.module.AbstractTestCase;

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

    public MediaModuleTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(MediaModuleTest.class);

        return suite;
    }

    public void testGoogleVideo() throws Exception {
        final SyndFeed feed = getSyndFeed(new File(getTestFile("data/YouTube-MostPopular.rss")));
        for (final Object element : feed.getEntries()) {
            final SyndEntry entry = (SyndEntry) element;
            final MediaEntryModule m = (MediaEntryModule) entry.getModule(MediaModule.URI);
            assertNotNull(m);
        }
    }

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
                b.write("" + ((SyndEntry) entries.get(i)).getModule(MediaModule.URI));
                b.close();
                b = new BufferedWriter(new FileWriter("target/" + j + "b.txt"));
                b.write("" + feed2.getEntries().get(i).getModule(MediaModule.URI));
                b.close();
                assertEquals(((SyndEntry) entries.get(i)).getModule(MediaModule.URI), feed2.getEntries().get(i).getModule(MediaModule.URI));
            }
        }
    }

    private SyndFeed getSyndFeed(final File file) throws FileNotFoundException, IllegalArgumentException, IOException, FeedException {
        final SyndFeedInput input = new SyndFeedInput();
        return input.build(file);
    }
}
