/*
 * ModuleParserTest.java
 * JUnit based test
 *
 * Created on April 29, 2006, 7:00 PM
 */

package com.sun.syndication.feed.module.sle.io;

import com.sun.syndication.feed.module.AbstractTestCase;
import com.sun.syndication.feed.module.sle.SimpleListExtension;
import com.sun.syndication.feed.module.sle.SleEntry;
import com.sun.syndication.feed.module.sle.types.Group;
import com.sun.syndication.feed.module.sle.types.Sort;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import java.io.File;
import junit.framework.*;
import org.jdom.Namespace;

/**
 *
 * @author cooper
 */
public class ModuleParserTest extends AbstractTestCase {
    
    public ModuleParserTest(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(ModuleParserTest.class);
        
        return suite;
    }
    
    /**
     * Test of parse method, of class com.sun.syndication.feed.module.sle.io.ModuleParser.
     */
    public void testParse() throws Exception {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new File(super.getTestFile("data/bookexample.xml")));
        SimpleListExtension sle = (SimpleListExtension) feed.getModule( SimpleListExtension.URI );
        //System.out.println( sle );
        assertEquals( "list", sle.getTreatAs() );
        Group[] groups = sle.getGroupFields();
        assertEquals( "genre", groups[0].getElement() );
        assertEquals( "Genre", groups[0].getLabel() );
        Sort[] sorts = sle.getSortFields();
        assertEquals( "Relevance", sorts[0].getLabel() );
        assertTrue( sorts[0].getDefaultOrder() );
        assertEquals ( sorts[1].getNamespace() , Namespace.getNamespace("http://www.example.com/book"));
        assertEquals( sorts[1].getDataType() , Sort.DATE_TYPE );
        assertEquals( sorts[1].getElement() , "firstedition" );
        SyndEntry entry = (SyndEntry) feed.getEntries().get(0);
        SleEntry sleEntry = (SleEntry) entry.getModule( SleEntry.URI );
        System.out.println( sleEntry );
        System.out.println( "getGroupByElement");
        System.out.println( sleEntry.getGroupByElement( groups[0]));
        System.out.println( "getSortByElement" );
        System.out.println( sleEntry.getSortByElement( sorts[0] ) );
    }
    
    
    
}
