/*
 * OpenSearchModuleTest.java
 * JUnit based test
 *
 * Created on April 25, 2006, 8:56 PM
 */

package com.sun.syndication.feed.module.opensearch;

import com.sun.syndication.feed.module.AbstractTestCase;
import junit.framework.*;
import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import java.io.File;

/**
 *
 * @author cooper
 */
public class OpenSearchModuleTest extends AbstractTestCase {
    
    public OpenSearchModuleTest(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(OpenSearchModuleTest.class);
        
        return suite;
    }
    
    public void testEndToEnd() throws Exception {
        SyndFeedInput input = new SyndFeedInput();
        File test = new File( super.getTestFile( "os") );
        File[] files = test.listFiles();
        for( int j=0; j < files.length; j++ ){
            if( !files[j].getName().endsWith(".xml") )
                continue;
            SyndFeed feed =  input.build( files[j] );
            Module m = feed.getModule( OpenSearchModule.URI );
            SyndFeedOutput output = new SyndFeedOutput();
            File outfile = new File( "target/" + files[j].getName() ) ;
            output.output( feed, outfile );
            SyndFeed feed2 = input.build( outfile );
            assertEquals( m, feed2.getModule(OpenSearchModule.URI));
        }
    }
    
}
