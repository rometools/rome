/*
 * ITunesGeneratorTest.java
 * JUnit based test
 *
 * Created on August 2, 2005, 2:31 PM
 */
package org.rometools.feed.module.itunes;

import java.io.File;
import java.io.OutputStreamWriter;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.rometools.feed.module.AbstractTestCase;
import org.rometools.feed.module.itunes.types.Category;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;

/**
 *
 * @author cooper
 */
public class ITunesGeneratorTest extends AbstractTestCase {
    static final String URI = AbstractITunesObject.URI;

    public ITunesGeneratorTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(ITunesGeneratorTest.class);

        return suite;
    }

    /**
     * Test of generate method, of class com.totsp.xml.syndication.itunes.ITunesGenerator.
     */
    public void testEndToEnd() throws Exception {
        System.out.println("testEndToEnd");
        testFile("xml/leshow.xml");

        // testFile( "/test/xml/apple.xml" );
        testFile("xml/lr.xml");
    }

    private void testFile(final String filename) throws Exception {
        final File feed = new File(getTestFile(filename));
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed syndfeed = input.build(new XmlReader(feed.toURL()));

        final SyndFeedOutput output = new SyndFeedOutput();
        final File outfeed = new File(feed.getAbsolutePath() + ".output");
        output.output(syndfeed, outfeed);

        final SyndFeed syndCheck = input.build(new XmlReader(outfeed.toURL()));
        System.out.println(syndCheck.getModule(AbstractITunesObject.URI).toString());
        assertEquals("Feed Level: ", syndfeed.getModule(AbstractITunesObject.URI).toString(), syndCheck.getModule(AbstractITunesObject.URI).toString());

        final List syndEntries = syndfeed.getEntries();
        final List syndChecks = syndCheck.getEntries();

        for (int i = 0; i < syndEntries.size(); i++) {
            final SyndEntry entry = (SyndEntry) syndEntries.get(i);
            final SyndEntry check = (SyndEntry) syndChecks.get(i);
            System.out.println("Original: " + entry.getModule(AbstractITunesObject.URI));
            System.out.println("Check:    " + check.getModule(AbstractITunesObject.URI));
            System.out.println(entry.getModule(AbstractITunesObject.URI).toString());
            System.out.println("-----------------------------------------");
            System.out.println(check.getModule(AbstractITunesObject.URI).toString());
            assertEquals("Entry Level: ", entry.getModule(AbstractITunesObject.URI).toString(), check.getModule(AbstractITunesObject.URI).toString());
        }
    }

    public void testCreate() throws Exception {
        final SyndFeed feed = new SyndFeedImpl();
        final String feedType = "rss_2.0";
        feed.setFeedType(feedType);
        feed.setLanguage("en-us");
        feed.setTitle("sales.com on the Radio!");
        feed.setDescription("sales.com radio shows in MP3 format");
        feed.setLink("http://foo/rss/podcasts.rss");

        final FeedInformation fi = new FeedInformationImpl();
        fi.setOwnerName("sales.com");
        fi.getCategories().add(new Category("Shopping"));
        fi.setOwnerEmailAddress("patti@sales.com");
        feed.getModules().add(fi);

        final SyndFeedOutput output = new SyndFeedOutput();
        output.output(feed, new OutputStreamWriter(System.out));
    }
}
