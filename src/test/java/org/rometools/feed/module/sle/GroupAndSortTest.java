/*
 * GroupAndSortTest.java
 * JUnit based test
 *
 * Created on April 29, 2006, 8:56 PM
 */

package org.rometools.feed.module.sle;

import java.io.File;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.rometools.feed.module.AbstractTestCase;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

/**
 * 
 * @author cooper
 */
public class GroupAndSortTest extends AbstractTestCase {

    public GroupAndSortTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(GroupAndSortTest.class);

        return suite;
    }

    /**
     * Test of sort method, of class com.sun.syndication.feed.module.sle.GroupAndSort.
     */
    public void testSort() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new File(super.getTestFile("data/bookexample.xml")));
        final SimpleListExtension sle = (SimpleListExtension) feed.getModule(SimpleListExtension.URI);

        List sortedEntries = SleUtility.sort(feed.getEntries(), sle.getSortFields()[1], true);
        SyndEntry entry = (SyndEntry) sortedEntries.get(0);
        assertEquals("Great Journeys of the Past", entry.getTitle());
        sortedEntries = SleUtility.sort(feed.getEntries(), sle.getSortFields()[1], false);
        entry = (SyndEntry) sortedEntries.get(0);
        assertEquals("Horror Stories, vol 16", entry.getTitle());

        sortedEntries = SleUtility.sort(feed.getEntries(), sle.getSortFields()[1], true);
        entry = (SyndEntry) sortedEntries.get(0);

        entry.setTitle("ZZZZZ");
        SleUtility.initializeForSorting(feed);
        System.out.println(feed.getEntries().size() + " **Sorting on " + sle.getSortFields()[2]);

        sortedEntries = SleUtility.sort(feed.getEntries(), sle.getSortFields()[2], false);
        System.out.println("Sorted: " + sortedEntries.size());
        for (int i = 0; i < sortedEntries.size(); i++) {
            entry = (SyndEntry) sortedEntries.get(i);
            System.out.println(i + " -- " + entry.getTitle());
            final SleEntry slent = (SleEntry) entry.getModule(SleEntry.URI);
            for (int j = 0; j < slent.getSortValues().length; j++) {
                System.out.println(slent.getSortValues()[j].getElement() + " == " + slent.getSortValues()[j].getValue());
            }

        }
        entry = (SyndEntry) sortedEntries.get(0);
        System.out.println(entry.getTitle());
        assertEquals("ZZZZZ", entry.getTitle());
        entry = (SyndEntry) sortedEntries.get(1);
        System.out.println(entry.getTitle());

        sortedEntries = SleUtility.sort(feed.getEntries(), sle.getSortFields()[2], true);
        entry = (SyndEntry) sortedEntries.get(0);
        System.out.println(entry.getTitle());
        assertEquals("Horror Stories, vol 16", entry.getTitle());
        entry = (SyndEntry) sortedEntries.get(1);
        System.out.println(entry.getTitle());

        sortedEntries = SleUtility.sortAndGroup(feed.getEntries(), sle.getGroupFields(), sle.getSortFields()[2], true);
        entry = (SyndEntry) sortedEntries.get(0);
        System.out.println(entry.getTitle());
        assertEquals("Horror Stories, vol 16", entry.getTitle());

    }

    /**
     * Test of sort method, of class com.sun.syndication.feed.module.sle.GroupAndSort.
     */
    public void testSort2() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new File(super.getTestFile("data/YahooTopSongs.xml")));
        final SimpleListExtension sle = (SimpleListExtension) feed.getModule(SimpleListExtension.URI);
        System.out.println("Sorting on " + sle.getSortFields()[0]);
        final List sortedEntries = SleUtility.sort(feed.getEntries(), sle.getSortFields()[0], true);
        for (int i = 0; i < sortedEntries.size(); i++) {
            final SyndEntry entry = (SyndEntry) sortedEntries.get(i);
            System.out.println(entry.getTitle());
        }
    }

    /**
     * Test of group method, of class com.sun.syndication.feed.module.sle.GroupAndSort.
     */
    public void testGroup() {
        // TODO add your test code.
    }

    /**
     * Test of sortAndGroup method, of class com.sun.syndication.feed.module.sle.GroupAndSort.
     */
    public void testSortAndGroup() {
        // TODO add your test code.
    }

}
