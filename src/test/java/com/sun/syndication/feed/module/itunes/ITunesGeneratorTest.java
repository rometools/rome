/*
 * ITunesGeneratorTest.java
 * JUnit based test
 *
 * Created on August 2, 2005, 2:31 PM
 */
package com.sun.syndication.feed.module.itunes;

import com.sun.syndication.feed.module.AbstractTestCase;
import com.sun.syndication.feed.module.itunes.types.Category;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;

import junit.framework.*;

import java.io.File;
import java.io.OutputStreamWriter;

import java.util.List;


/**
 *
 * @author cooper
 */
public class ITunesGeneratorTest extends AbstractTestCase {
    static final String URI = AbstractITunesObject.URI;
    
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
        testFile( "xml/lr.xml" );
    }
    
    private void testFile(String filename) throws Exception {
        File feed = new File(this.getTestFile(filename));
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed syndfeed = input.build(new XmlReader(feed.toURL()));
        
        SyndFeedOutput output = new SyndFeedOutput();
        File outfeed = new File(feed.getAbsolutePath() + ".output");
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
            System.out.println( entry.getModule(AbstractITunesObject.URI).toString() );
            System.out.println( "-----------------------------------------");
            System.out.println(  check.getModule(AbstractITunesObject.URI).toString());
            assertEquals("Entry Level: ", entry.getModule(AbstractITunesObject.URI).toString(), check.getModule(AbstractITunesObject.URI).toString());
        }
    }
    
    public void testCreate() throws Exception {
        SyndFeed feed = new SyndFeedImpl();
        String feedType = "rss_2.0";
        feed.setFeedType(feedType);
        feed.setLanguage("en-us");
        feed.setTitle("sales.com on the Radio!");
        feed.setDescription("sales.com radio shows in MP3 format");
        feed.setLink("http://foo/rss/podcasts.rss");
        
        FeedInformation fi = new FeedInformationImpl();
        fi.setOwnerName("sales.com");
        fi.getCategories().add(new Category("Shopping"));
        fi.setOwnerEmailAddress("patti@sales.com");
        feed.getModules().add( fi );
        
        SyndFeedOutput output = new SyndFeedOutput();
        output.output( feed, new OutputStreamWriter( System.out) );
    }
}
