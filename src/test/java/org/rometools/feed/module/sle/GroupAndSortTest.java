package org.rometools.feed.module.sle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.rometools.feed.module.AbstractTestCase;
import org.rometools.feed.module.sle.types.Sort;

import com.sun.syndication.feed.module.Extendable;
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
        return new TestSuite(GroupAndSortTest.class);
    }

    /**
     * Test of sort method, of class com.sun.syndication.feed.module.sle.GroupAndSort.
     */
    public void testSort() throws Exception {

        final File testdata = new File(super.getTestFile("data/bookexample.xml"));
        final SyndFeed feed = new SyndFeedInput().build(testdata);

        final List<SyndEntry> feedEntries = feed.getEntries();
        final List<Extendable> entries = new ArrayList<Extendable>(feedEntries);

        final SimpleListExtension sle = (SimpleListExtension) feed.getModule(SimpleListExtension.URI);
        final Sort[] sortFields = sle.getSortFields();

        final Sort sortByDate = sortFields[1];
        final Sort sortByTitle = sortFields[2];

        // sort by date ascending
        List<Extendable> sortedEntries = SleUtility.sort(entries, sortByDate, true);
        SyndEntry entry = (SyndEntry) sortedEntries.get(0);
        assertEquals("Great Journeys of the Past", entry.getTitle());

        // sort by date descending
        sortedEntries = SleUtility.sort(entries, sortByDate, false);
        entry = (SyndEntry) sortedEntries.get(0);
        assertEquals("Horror Stories, vol 16", entry.getTitle());

        // sort by date ascending
        sortedEntries = SleUtility.sort(entries, sortByDate, true);

        // update first entry and reinitialize
        entry = (SyndEntry) sortedEntries.get(0);
        entry.setTitle("ZZZZZ");
        SleUtility.initializeForSorting(feed);

        // sort by title desc
        sortedEntries = SleUtility.sort(entries, sortByTitle, false);
        entry = (SyndEntry) sortedEntries.get(0);
        assertEquals("ZZZZZ", entry.getTitle());

        // sort by title asc
        sortedEntries = SleUtility.sort(entries, sortByTitle, true);
        entry = (SyndEntry) sortedEntries.get(0);
        assertEquals("Horror Stories, vol 16", entry.getTitle());

    }

    /**
     * Test of sort method, of class com.sun.syndication.feed.module.sle.GroupAndSort.
     */
    public void testSort2() throws Exception {

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new File(super.getTestFile("data/YahooTopSongs.xml")));
        final SimpleListExtension sle = (SimpleListExtension) feed.getModule(SimpleListExtension.URI);

        final List<Extendable> entries = new ArrayList<Extendable>(feed.getEntries());
        final List<Extendable> sortedEntries = SleUtility.sort(entries, sle.getSortFields()[0], true);
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
    public void testSortAndGroup() throws Exception {

        final File testdata = new File(super.getTestFile("data/bookexample.xml"));
        final SyndFeed feed = new SyndFeedInput().build(testdata);

        final List<SyndEntry> feedEntries = feed.getEntries();
        final List<Extendable> entries = new ArrayList<Extendable>(feedEntries);

        final SimpleListExtension sle = (SimpleListExtension) feed.getModule(SimpleListExtension.URI);
        final Sort[] sortFields = sle.getSortFields();

        final Sort sortByTitle = sortFields[2];

        final List<Extendable> sortedEntries = SleUtility.sortAndGroup(entries, sle.getGroupFields(), sortByTitle, true);
        final SyndEntry entry = (SyndEntry) sortedEntries.get(0);
        assertEquals("Horror Stories, vol 16", entry.getTitle());

    }

}
