/*
 * SlashModuleGeneratorTest.java
 * JUnit based test
 *
 * Created on November 19, 2005, 10:13 PM
 */

package org.rometools.feed.module.yahooweather.io;

import org.rometools.feed.module.AbstractTestCase;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import junit.framework.*;
import org.rometools.feed.module.yahooweather.YWeatherModule;
import java.io.File;

/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class WeahterGeneratorTest extends AbstractTestCase {
    
    public WeahterGeneratorTest(String testName) {
	super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(WeahterGeneratorTest.class);
        
        return suite;
    }

        /**
     * Test of generate method, of class com.totsp.xml.syndication.base.io.SlashGenerator.
     */
    public void testGenerate() throws Exception {
	System.out.println("testGenerate");
	SyndFeedInput input = new SyndFeedInput();
	SyndFeedOutput output = new SyndFeedOutput();
	File testDir = new File(super.getTestFile( "xml"));
	File[] testFiles = testDir.listFiles();
	for( int h=0; h < testFiles.length; h++){
	    if( !testFiles[h].getName().endsWith(".xml"))
		continue;
	    System.out.println("processing"+ testFiles[h]);
	    SyndFeed feed = input.build(  testFiles[h] );	    
	    output.output( feed, new File( "target/"+testFiles[h].getName())  );
	    SyndFeed feed2 = input.build( new File("target/"+testFiles[h].getName()) );
            {
                YWeatherModule weather = (YWeatherModule) feed.getModule( YWeatherModule.URI );
		YWeatherModule weather2 = (YWeatherModule) feed2.getModule( YWeatherModule.URI );
		this.assertEquals( testFiles[h].getName(), weather, weather2 );
            
            }
	    for( int i= 0; i < feed.getEntries().size() ; i++ ){
		SyndEntry entry = (SyndEntry) feed.getEntries().get(i);
		SyndEntry entry2 = (SyndEntry) feed2.getEntries().get(i);
		YWeatherModule weather = (YWeatherModule) entry.getModule( YWeatherModule.URI );
		YWeatherModule weather2 = (YWeatherModule) entry2.getModule( YWeatherModule.URI );
		this.assertEquals( testFiles[h].getName(), weather, weather2 );
	    }
	}	
	
    }

   
    
}
