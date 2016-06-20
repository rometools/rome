/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.rometools.modules.sle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.sle.SimpleListExtension;
import com.rometools.modules.sle.SleUtility;
import com.rometools.modules.sle.types.Sort;
import com.rometools.rome.feed.module.Extendable;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;

public class GroupAndSortTest extends AbstractTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(GroupAndSortTest.class);

    public GroupAndSortTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(GroupAndSortTest.class);
    }

    /**
     * Test of sort method, of class com.rometools.rome.feed.module.sle.GroupAndSort.
     */
    public void testSort() throws Exception {

        final File testdata = new File(super.getTestFile("data/bookexample.xml"));
        final SyndFeed feed = new SyndFeedInput().build(testdata);

        List<SyndEntry> entries = feed.getEntries();

        final SimpleListExtension extention = (SimpleListExtension) feed.getModule(SimpleListExtension.URI);
        final Sort[] sortFields = extention.getSortFields();

        final Sort sortByDate = sortFields[1];
        final Sort sortByTitle = sortFields[2];

        // sort by date ascending
        List<SyndEntry> sortedEntries = SleUtility.sort(entries, sortByDate, true);
        SyndEntry entry = sortedEntries.get(0);
        assertEquals("Great Journeys of the Past", entry.getTitle());

        // sort by date descending
        sortedEntries = SleUtility.sort(entries, sortByDate, false);
        entry = sortedEntries.get(0);
        assertEquals("Horror Stories, vol 16", entry.getTitle());

        // sort by date ascending
        sortedEntries = SleUtility.sort(entries, sortByDate, true);

        // update first entry and reinitialize
        entry = sortedEntries.get(0);
        entry.setTitle("ZZZZZ");
        SleUtility.initializeForSorting(feed);
        entries = feed.getEntries();

        // sort by title desc
        sortedEntries = SleUtility.sort(entries, sortByTitle, false);
        entry = sortedEntries.get(0);
        assertEquals("ZZZZZ", entry.getTitle());

        // sort by title asc
        sortedEntries = SleUtility.sort(entries, sortByTitle, true);
        entry = sortedEntries.get(0);
        assertEquals("Horror Stories, vol 16", entry.getTitle());

    }

    /**
     * Test of sort method, of class com.rometools.rome.feed.module.sle.GroupAndSort.
     */
    public void testSort2() throws Exception {

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new File(super.getTestFile("data/YahooTopSongs.xml")));
        final SimpleListExtension sle = (SimpleListExtension) feed.getModule(SimpleListExtension.URI);

        final List<Extendable> entries = new ArrayList<Extendable>(feed.getEntries());
        final List<Extendable> sortedEntries = SleUtility.sort(entries, sle.getSortFields()[0], true);
        for (int i = 0; i < sortedEntries.size(); i++) {
            final SyndEntry entry = (SyndEntry) sortedEntries.get(i);
            LOG.debug(entry.getTitle());
        }

    }

    /**
     * Test of group method, of class com.rometools.rome.feed.module.sle.GroupAndSort.
     */
    public void testGroup() {
        // TODO add your test code.
    }

    /**
     * Test of sortAndGroup method, of class com.rometools.rome.feed.module.sle.GroupAndSort.
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
