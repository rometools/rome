/*
 * CustomTagParserTest.java
 * JUnit based test
 *
 * Created on February 6, 2006, 1:29 AM
 */

package com.sun.syndication.feed.module.base.io;

import com.sun.syndication.feed.module.AbstractTestCase;
import com.sun.syndication.feed.module.base.CustomTag;
import com.sun.syndication.feed.module.base.CustomTagImpl;
import junit.framework.*;
import com.sun.syndication.feed.module.base.CustomTags;
import com.sun.syndication.feed.module.base.types.DateTimeRange;
import com.sun.syndication.feed.module.base.types.FloatUnit;
import com.sun.syndication.feed.module.base.types.IntUnit;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class CustomTagParserTest extends AbstractTestCase {
    
    public CustomTagParserTest(String testName) {
	super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(CustomTagParserTest.class);
        
        return suite;
    }

    public void testParse() throws Exception{
        SyndFeedInput input = new SyndFeedInput();
	
	SyndFeed feed = input.build(  new File(super.getTestFile( "xml/custom-tags-example.xml")) );
	List entries = feed.getEntries();
	SyndEntry entry = (SyndEntry) entries.get(0);
	CustomTags customTags = (CustomTags) entry.getModule( CustomTags.URI );
	Iterator it = customTags.getValues().iterator();
	while( it.hasNext() ){
	    CustomTag tag = (CustomTag) it.next();
	    System.out.println( tag );
	    if( tag.getName().equals("language_skills") ){
		this.assertEquals( "Fluent in English and German", tag.getValue() );
	    }
	    if( tag.getName().equals( "prior_experience_years") ){
		this.assertEquals( new Integer( 5 ), tag.getValue() );
	    } else if( tag.getName().equals( "start_date") ){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( 0 );
		cal.set( 2005, 10, 15, 0, 0, 0 );
		this.assertEquals( cal.getTime(), tag.getValue() );
	    } else if( tag.getName().equals( "test_url") ){
		this.assertEquals( new URL( "http://www.screaming-penguin.com"), tag.getValue() );
	    } else if( tag.getName().equals( "test_boolean") ){
		this.assertEquals( new Boolean(true), tag.getValue());
	    } else if( tag.getName().equals("test_intUnit") ){
		this.assertEquals( new IntUnit( 25, "horses"), tag.getValue() );
	    } else if( tag.getName().equals("test_floatUnit") ){
		this.assertEquals( new FloatUnit( (float)2.5, "cows"), tag.getValue() );
	    } else if( tag.getName().equals("test_location" ) ){
		this.assertEquals( new CustomTagImpl.Location( "125 Main St, Sometown, GA"), tag.getValue() );
	    } else if( tag.getName().equals("test_dateRange") ){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( 0 );
		cal.set( 2005, 06, 04, 20, 0, 0 );
		Date start = cal.getTime();
		cal.set( 2005, 06, 04, 23, 0, 0 );
		DateTimeRange dtr = new DateTimeRange( start, cal.getTime() );
		this.assertEquals( dtr, tag.getValue() );
	    }
	}
    }

   
    
}
