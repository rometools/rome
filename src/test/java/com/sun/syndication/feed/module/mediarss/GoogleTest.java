/*
 * MediaModuleTest.java
 * JUnit based test
 *
 * Created on March 29, 2006, 11:49 PM
 */

package com.sun.syndication.feed.module.mediarss;

import com.sun.syndication.feed.module.AbstractTestCase;
import junit.framework.*;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;

/**
 *
 * @author cooper
 */
public class GoogleTest extends AbstractTestCase {
    
    public GoogleTest(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(MediaModuleTest.class);
        
        return suite;
    }
    
    public static void testGoogleVideo() throws Exception {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build( new InputStreamReader( new URL( "http://video.google.com/videofeed?type=top100new&num=20&output=rss").openStream()));
        for( Iterator it = feed.getEntries().iterator(); it.hasNext();  ){
            SyndEntry entry = (SyndEntry) it.next();
            MediaEntryModule m = (MediaEntryModule) entry.getModule( MediaEntryModule.URI );
            System.out.print( m );
        }
    }
    
    
}

