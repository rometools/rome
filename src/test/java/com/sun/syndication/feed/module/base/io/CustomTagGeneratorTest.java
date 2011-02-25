/*
 * CustomTagGeneratorTest.java
 * JUnit based test
 *
 * Created on February 6, 2006, 1:58 AM
 */

package com.sun.syndication.feed.module.base.io;

import com.sun.syndication.feed.module.AbstractTestCase;
import com.sun.syndication.feed.module.base.CustomTag;
import junit.framework.*;
import com.sun.syndication.feed.module.base.CustomTags;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class CustomTagGeneratorTest extends AbstractTestCase {
    
    public CustomTagGeneratorTest(String testName) {
	super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(CustomTagGeneratorTest.class);
        
        return suite;
    }

    

    public void testGenerate() throws Exception {
        SyndFeedInput input = new SyndFeedInput();
	
	SyndFeed feed = input.build(  new File(super.getTestFile( "xml/custom-tags-example.xml")) );
	SyndFeedOutput output = new SyndFeedOutput();
	output.output( feed, new File( "target/custom-tags-example.xml") );
	SyndFeed feed2 = input.build( new File("target/custom-tags-example.xml") );
	    
	List entries = feed.getEntries();
	SyndEntry entry = (SyndEntry) entries.get(0);
	CustomTags customTags = (CustomTags) entry.getModule( CustomTags.URI );
	
	List entries2 = feed2.getEntries();
	SyndEntry entry2 = (SyndEntry) entries2.get(0);
	CustomTags customTags2 = (CustomTags) entry2.getModule( CustomTags.URI );
	
	Iterator it = customTags.getValues().iterator();
	Iterator it2 = customTags2.getValues().iterator();
	while( it.hasNext() ){
	    CustomTag tag = (CustomTag) it.next();
	    CustomTag tag2 = (CustomTag) it2.next();
	    System.out.println( "tag1:" + tag );
	    System.out.println( "tag2:" + tag2 );
	    this.assertEquals( tag, tag2);
	}
    }
    
}
