/*
 * ModuleParserRSS1Test.java
 * JUnit based test
 *
 * Created on November 20, 2005, 7:05 PM
 */

package com.sun.syndication.feed.module.cc.io;

import com.sun.syndication.feed.module.AbstractTestCase;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import junit.framework.*;
import com.sun.syndication.feed.module.cc.CreativeCommons;
import java.io.File;
import java.util.List;

/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class ModuleParserTest extends AbstractTestCase {
    
    public ModuleParserTest(String testName) {
	super(testName);
    }
    
    public static Test suite() {
	TestSuite suite = new TestSuite(ModuleParserTest.class);
	
	return suite;
    }
    public void testNull(){
	return;
    }
    public void atestParse() throws Exception {
	
	
	SyndFeedInput input = new SyndFeedInput();
	File testDir = new File(super.getTestFile( "test/xml"));
	File[] testFiles = testDir.listFiles();
	for( int h=0; h < testFiles.length; h++){
	    if( !testFiles[h].getName().endsWith(".xml"))
		continue;
	    System.out.println(testFiles[h].getName());
	    SyndFeed feed = input.build(  testFiles[h] );
	    List entries = feed.getEntries();
	    CreativeCommons fMod = (CreativeCommons) feed.getModule( CreativeCommons.URI );
	    System.out.println( fMod );
	    for( int i=0 ; i< entries.size(); i++ ){
		SyndEntry entry = (SyndEntry) entries.get(i);
		CreativeCommons eMod = (CreativeCommons) entry.getModule( CreativeCommons.URI );
		System.out.println("\nEntry:");
		System.out.println( eMod );
	    }
	}
    }

}
