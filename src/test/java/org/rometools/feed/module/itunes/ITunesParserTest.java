/*
 * ITunesParserTest.java
 * JUnit based test
 *
 * Created on August 2, 2005, 1:30 PM
 */
package org.rometools.feed.module.itunes;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.rometools.feed.module.AbstractTestCase;
import org.rometools.feed.module.itunes.io.ITunesGenerator;
import org.rometools.feed.module.itunes.types.Category;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 *
 * @author cooper
 */
public class ITunesParserTest extends AbstractTestCase {
    public ITunesParserTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(ITunesParserTest.class);

        return suite;
    }

    /**
     * Test of getNamespaceUri method, of class com.totsp.xml.syndication.itunes.ITunesParser.
     */
    public void testGetNamespaceUri() {
        System.out.println("testGetNamespaceUri");

        assertEquals("Namespace", "http://www.itunes.com/dtds/podcast-1.0.dtd", new ITunesGenerator().getNamespaceUri());
    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.itunes.ITunesParser.
     */
    public void testParse() throws Exception {
        File feed = new File(getTestFile("xml/leshow.xml"));
        final SyndFeedInput input = new SyndFeedInput();
        SyndFeed syndfeed = input.build(new XmlReader(feed.toURL()));

        final Module module = syndfeed.getModule(AbstractITunesObject.URI);
        final FeedInformationImpl feedInfo = (FeedInformationImpl) module;

        assertEquals("owner", "Harry Shearer", feedInfo.getOwnerName());
        assertEquals("email", "", feedInfo.getOwnerEmailAddress());
        assertEquals("image", "http://a1.phobos.apple.com/Music/y2005/m06/d26/h21/mcdrrifv.jpg", feedInfo.getImage().toExternalForm());
        assertEquals("category", "Comedy", ((Category) feedInfo.getCategories().get(0)).getName());
        assertEquals(
                "summary",
                "A weekly, hour-long romp through the worlds of media, politics, sports and show business, leavened with an eclectic mix of mysterious music, hosted by Harry Shearer.",
                feedInfo.getSummary());

        List entries = syndfeed.getEntries();
        Iterator it = entries.iterator();

        while (it.hasNext()) {
            final SyndEntry entry = (SyndEntry) it.next();
            final EntryInformationImpl entryInfo = (EntryInformationImpl) entry.getModule(AbstractITunesObject.URI);
            System.out.println(entryInfo);
        }

        feed = new File(getTestFile("xml/rsr.xml"));
        syndfeed = input.build(new XmlReader(feed.toURL()));
        entries = syndfeed.getEntries();
        it = entries.iterator();

        while (it.hasNext()) {
            final SyndEntry entry = (SyndEntry) it.next();
            final EntryInformationImpl entryInfo = (EntryInformationImpl) entry.getModule(AbstractITunesObject.URI);
            System.out.println(entryInfo.getDuration());
        }
    }
}
