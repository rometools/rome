/*
 * MediaModuleTest.java
 * JUnit based test
 *
 * Created on March 29, 2006, 11:49 PM
 */

package com.rometools.modules.mediarss;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.mediarss.MediaEntryModule;
import com.rometools.modules.mediarss.MediaModule;
import com.rometools.modules.mediarss.types.MediaContent;
import com.rometools.modules.mediarss.types.Rating;
import com.rometools.modules.mediarss.types.Thumbnail;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;

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
     * tests parsing thubnails with empty dimensions
     * (https://github.com/rometools/rome-modules/issues/7).
     *
     * @throws IOException if file not found or not accessible
     * @throws FeedException when the feed can't be parsed
     *
     */
    public void testParseThumbnailWithEmptyDimensions() throws FeedException, IOException {

        final SyndFeed feed = getSyndFeed("org/rometools/feed/module/mediarss/issue-07.xml");
        final SyndEntry entry = feed.getEntries().get(0);
        final MediaEntryModule module = (MediaEntryModule) entry.getModule(MediaModule.URI);
        final Thumbnail[] thumbnails = module.getMetadata().getThumbnail();

        assertThat(thumbnails, is(notNullValue()));

    }

    /**
     * tests parsing a decimal duration (https://github.com/rometools/rome-modules/issues/8).
     *
     * @throws IOException if file not found or not accessible
     * @throws FeedException when the feed can't be parsed
     *
     */
    public void testParseDecimalDuration() throws FeedException, IOException {

        final SyndFeed feed = getSyndFeed("org/rometools/feed/module/mediarss/issue-08.xml");
        final SyndEntry entry = feed.getEntries().get(0);
        final MediaEntryModule module = (MediaEntryModule) entry.getModule(MediaModule.URI);
        final Thumbnail[] thumbnails = module.getMetadata().getThumbnail();

        assertThat(thumbnails, is(notNullValue()));

    }

    /**
     * tests parsing rating without scheme (https://github.com/rometools/rome-modules/issues/12).
     *
     * @throws IOException if file not found or not accessible
     * @throws FeedException when the feed can't be parsed
     *
     */
    public void testParseRatingWithoutScheme() throws FeedException, IOException {

        final SyndFeed feed = getSyndFeed("org/rometools/feed/module/mediarss/issue-12.xml");
        final SyndEntry entry = feed.getEntries().get(0);
        final MediaEntryModule module = (MediaEntryModule) entry.getModule(MediaModule.URI);
        final Rating[] ratings = module.getMetadata().getRatings();

        assertThat(ratings, is(notNullValue()));

    }

    /**
     * test url with whitespace in media element
     * (https://github.com/rometools/rome-modules/issues/20).
     *
     * @throws Exception if file not found or not accessible
     */
    public void testParseMediaContentContainingURLWithSpaces() throws Exception {
        final SyndFeed feed = getSyndFeed("org/rometools/feed/module/mediarss/issue-20.xml");
        final SyndEntry entry = feed.getEntries().get(0);
        final MediaEntryModule m = (MediaEntryModule) entry.getModule(MediaModule.URI);
        assertNotNull("missing media entry module", m);
        final MediaContent[] mcs = m.getMediaContents();
        assertNotNull("missing media:content", mcs);
        assertEquals("wrong count of media:content", 1, mcs.length);
        final MediaContent mc = mcs[0];
        assertEquals("http://www.foo.com/path/containing+spaces/trailer.mov", mc.getReference().toString());
    }

    private SyndFeed getSyndFeed(final File file) throws IOException, FeedException {
        return new SyndFeedInput().build(file);
    }

    private SyndFeed getSyndFeed(final String filePath) throws IOException, FeedException {
        final String fullPath = getTestFile(filePath);
        final File file = new File(fullPath);
        return new SyndFeedInput().build(file);
    }

}
