/*
 * ModuleParserTest.java
 * JUnit based test
 *
 * Created on April 29, 2006, 7:00 PM
 */

package org.rometools.feed.module.sle.io;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.jdom2.Namespace;
import org.rometools.feed.module.AbstractTestCase;
import org.rometools.feed.module.sle.SimpleListExtension;
import org.rometools.feed.module.sle.SleEntry;
import org.rometools.feed.module.sle.types.Group;
import org.rometools.feed.module.sle.types.Sort;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

/**
 * 
 * @author cooper
 */
public class ModuleParserTest extends AbstractTestCase {

    public ModuleParserTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(ModuleParserTest.class);

        return suite;
    }

    /**
     * Test of parse method, of class com.sun.syndication.feed.module.sle.io.ModuleParser.
     */
    public void testParse() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new File(super.getTestFile("data/bookexample.xml")));
        final SimpleListExtension sle = (SimpleListExtension) feed.getModule(SimpleListExtension.URI);
        // System.out.println( sle );
        assertEquals("list", sle.getTreatAs());
        final Group[] groups = sle.getGroupFields();
        assertEquals("genre", groups[0].getElement());
        assertEquals("Genre", groups[0].getLabel());
        final Sort[] sorts = sle.getSortFields();
        assertEquals("Relevance", sorts[0].getLabel());
        assertTrue(sorts[0].getDefaultOrder());
        assertEquals(sorts[1].getNamespace(), Namespace.getNamespace("http://www.example.com/book"));
        assertEquals(sorts[1].getDataType(), Sort.DATE_TYPE);
        assertEquals(sorts[1].getElement(), "firstedition");
        final SyndEntry entry = feed.getEntries().get(0);
        final SleEntry sleEntry = (SleEntry) entry.getModule(SleEntry.URI);
        System.out.println(sleEntry);
        System.out.println("getGroupByElement");
        System.out.println(sleEntry.getGroupByElement(groups[0]));
        System.out.println("getSortByElement");
        System.out.println(sleEntry.getSortByElement(sorts[0]));
    }

}
