/*
 * SlashModuleParserTest.java
 * JUnit based test
 *
 * Created on November 19, 2005, 9:45 PM
 */

package com.sun.syndication.feed.module.yahooweather.io;
import com.sun.syndication.feed.module.AbstractTestCase;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import junit.framework.*;
import com.sun.syndication.feed.module.yahooweather.YWeatherModule;
import com.sun.syndication.feed.module.yahooweather.YWeatherEntryModule;
import com.sun.syndication.feed.module.yahooweather.YWeatherModuleImpl;
import java.io.File;
import java.util.List;
/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class WeatherModuleParserTest extends AbstractTestCase {
    
    public WeatherModuleParserTest(String testName) {
	super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(WeatherModuleParserTest.class);
        
        return suite;
    }

    public void testGetNamespaceUri() {
        WeatherModuleParser instance = new WeatherModuleParser();        
        String result = instance.getNamespaceUri();
        assertEquals(YWeatherModule.URI, result);
    }

    public void testQuickParse() throws Exception {
        System.out.println("testParse");
	    SyndFeedInput input = new SyndFeedInput();
	    File testDir = new File(super.getTestFile( "xml"));
	    File[] testFiles = testDir.listFiles();
	    for( int h=0; h < testFiles.length; h++){
		if( !testFiles[h].getName().endsWith(".xml"))
		    continue;
		
		SyndFeed feed = input.build(  testFiles[h] );
		List entries = feed.getEntries();
		for( int i=0 ; i< entries.size(); i++ ){
		    SyndEntry entry = (SyndEntry) entries.get(i);
		    System.out.println( entry.getModules().size() );
		    for( int j=0; j < entry.getModules().size() ; j++ ){
			System.out.println( entry.getModules().get(j).getClass() );
			if( entry.getModules().get(j) instanceof YWeatherModule ){
			    YWeatherModule base = (YWeatherModule) entry.getModules().get(j);
                            assertTrue( ((YWeatherEntryModule) base).getForecasts().length > 0);
			    System.out.println( testFiles[h].getName());
			    System.out.println( super.beanToString( base , false ));
                            
                            YWeatherEntryModule module2 = new YWeatherModuleImpl();
                            module2.copyFrom( base );
                            assertEquals( ((YWeatherEntryModule) base).getForecasts().length, 
                                    module2.getForecasts().length );
                            
			}
		    }
		}
	    }
    }
    
    
	
}
