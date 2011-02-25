/*
 * ITunesGeneratorTest.java
 * JUnit based test
 *
 * Created on August 2, 2005, 2:31 PM
 */
package com.sun.syndication.feed.module;

import com.sun.syndication.feed.module.itunes.ITunes;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;
import com.sun.syndication.feed.module.itunes.AbstractITunesObject;
import junit.framework.*;

import java.io.File;

import java.util.List;


/**
 *
 * @author cooper
 */
public class ITunesGeneratorTest extends AbstractTestCase {
    

    public ITunesGeneratorTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ITunesGeneratorTest.class);

        return suite;
    }

    /**
     * Test of generate method, of class com.totsp.xml.syndication.itunes.ITunesGenerator.
     */
    public void testEndToEnd() throws Exception {
        System.out.println("testEndToEnd");
        testFile("xml/leshow.xml");

        //testFile( "/test/xml/apple.xml" );
        //testFile( "/test/xml/lr.xml" );
    }

    private void testFile(String filename) throws Exception {
        File feed = new File(this.getTestFile(filename));
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed syndfeed = input.build(new XmlReader(feed.toURL()));

        SyndFeedOutput output = new SyndFeedOutput();
        File outfeed = new File("target/"+ feed.getName());
        output.output(syndfeed, outfeed);

        SyndFeed syndCheck = input.build(new XmlReader(outfeed.toURL()));
        System.out.println(syndCheck.getModule(AbstractITunesObject.URI).toString());
        assertEquals("Feed Level: ", syndfeed.getModule(AbstractITunesObject.URI).toString(), syndCheck.getModule(AbstractITunesObject.URI).toString());

        List syndEntries = syndfeed.getEntries();
        List syndChecks = syndCheck.getEntries();

        for (int i = 0; i < syndEntries.size(); i++) {
            SyndEntry entry = (SyndEntry) syndEntries.get(i);
            SyndEntry check = (SyndEntry) syndChecks.get(i);
            System.out.println("Original: " + entry.getModule(AbstractITunesObject.URI));
            System.out.println("Check:    " + check.getModule(AbstractITunesObject.URI));
            assertEquals("Entry Level: ", entry.getModule(AbstractITunesObject.URI).toString(), check.getModule(AbstractITunesObject.URI).toString());
        }
    }
}
