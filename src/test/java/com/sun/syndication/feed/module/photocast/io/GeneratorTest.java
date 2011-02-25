/*
 * GeneratorTest.java
 * JUnit based test
 *
 * Created on April 16, 2006, 5:58 PM
 */

package com.sun.syndication.feed.module.photocast.io;

import com.sun.syndication.feed.module.AbstractTestCase;
import com.sun.syndication.feed.module.photocast.PhotocastModule;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import java.io.File;
import java.util.List;
import junit.framework.*;

/**
 *
 * @author cooper
 */
public class GeneratorTest extends AbstractTestCase {
    
    public GeneratorTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(GeneratorTest.class);
        
        return suite;
    }

    /**
     * Test of generate method, of class com.sun.syndication.feed.module.photocast.io.Generator.
     */
    public void testGenerate() throws Exception{
        SyndFeedInput input = new SyndFeedInput();
	
	SyndFeed feed = input.build(  new File(super.getTestFile( "index.rss" ) ) );
        List entries = feed.getEntries();
        for( int i =0; i < entries.size() ; i++ ){
            System.out.println( ((SyndEntry)entries.get(i)).getModule( PhotocastModule.URI ) );
        }
        SyndFeedOutput output = new SyndFeedOutput();
	output.output( feed, new File( "target/index.rss" ) );
        SyndFeed feed2 =input.build(  new File("target/index.rss" ) );
        List entries2 = feed2.getEntries();
        for( int i=0; i < entries.size(); i++ ){
            assertEquals( "Module test", ((SyndEntry)entries.get(i)).getModule( PhotocastModule.URI ), ((SyndEntry)entries2.get(i)).getModule( PhotocastModule.URI ) );
        }
    }

    /**
     * Test of getNamespaces method, of class com.sun.syndication.feed.module.photocast.io.Generator.
     */
    public void testGetNamespaces() {
        // TODO add your test code.
    }

    /**
     * Test of getNamespaceUri method, of class com.sun.syndication.feed.module.photocast.io.Generator.
     */
    public void testGetNamespaceUri() {
        // TODO add your test code.
    }
    
}
