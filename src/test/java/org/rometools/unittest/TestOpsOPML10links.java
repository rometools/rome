/*
 * TestOpsOPML10.java
 *
 * Created on April 25, 2006, 4:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.rometools.unittest;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.WireFeedInput;
import com.sun.syndication.io.WireFeedOutput;   
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author cooper
 */
public class TestOpsOPML10links extends FeedOpsTest{
    
    /** Creates a new instance of TestOpsOPML10 */
    public TestOpsOPML10links() {
        super("opml_1.0_links");
    }
    
    // 1.6
    public void testWireFeedSyndFeedConversion() throws Exception {
        SyndFeed sFeed1 = getCachedSyndFeed();
        WireFeed wFeed1 = sFeed1.createWireFeed();
        //System.out.println( wFeed1 );
        SyndFeed sFeed2 = new SyndFeedImpl(wFeed1);
        PrintWriter w = new PrintWriter( new FileOutputStream( "target/test-reports/1") );
        w.println( sFeed1.toString() );
        w.close();
        w = new PrintWriter( new FileOutputStream( "target/test-reports/2") );
        w.println( sFeed2.toString() );
        w.close();
        
        assertEquals(sFeed2.createWireFeed(), sFeed1.createWireFeed());
    }
 
    
    
    public void testTemp() throws Exception { 
        WireFeedInput input = new WireFeedInput();
        WireFeed wf = input.build( new File( System.getProperty("basedir")+ "/src/test/resources/opml_1.0_links.xml"));
        WireFeedOutput output = new WireFeedOutput();
        //System.out.println( wf );
        
        //System.out.println( "=================================");
        //System.out.println( new SyndFeedImpl( wf) );
        SyndFeedImpl sf = new SyndFeedImpl( wf);
        sf.setFeedType("rss_2.0");
        sf.setDescription("");
        sf.setLink("http://foo.com");
        //output.output(  sf.createWireFeed() , new PrintWriter( System.out ) );
        sf.setFeedType("opml_1.0");
        output.output( sf.createWireFeed() , new File( System.getProperty("basedir")+ "/target/test-reports/1.xml") );
    }
    
}
