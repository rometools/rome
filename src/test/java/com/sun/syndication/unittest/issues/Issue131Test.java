package com.sun.syndication.unittest.issues;

import java.util.List;

import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.unittest.FeedTest;

/**
 * Test for #131: SyndFeedImpl copyFrom method does not copy Entry Categories.
 * 
 * @author Martin Kurz
 * 
 */
public class Issue131Test extends FeedTest {

    public Issue131Test() {
        super("issue131-rss.xml");
    }

    public void testOriginalCategories() throws Exception {
        checkFeed(getCachedSyndFeed());
    }

    public void testCopiedFeedCategories() throws Exception {
        final SyndFeed copiedFeed = new SyndFeedImpl();
        copiedFeed.copyFrom(getCachedSyndFeed());
        checkFeed(copiedFeed);
    }

    public void testCopiedFeedEntryCategories() throws Exception {
        for (final SyndEntry entry : getCachedSyndFeed().getEntries()) {
            final SyndEntry copiedEntry = new SyndEntryImpl();
            copiedEntry.copyFrom(entry);
            checkEntryCategories(copiedEntry.getCategories());
        }
    }

    private void checkFeed(final SyndFeed feed) {
        checkFeedCategories(feed.getCategories());
        for (final SyndEntry entry : feed.getEntries()) {
            checkEntryCategories(entry.getCategories());
        }
    }

    private void checkFeedCategories(final List<SyndCategory> categories) {
        assertNotNull(categories);
        assertEquals(2, categories.size());
        checkCategory(categories.get(0), "Channel Category 1");
        checkCategory(categories.get(1), "Channel Category 2");
    }

    private void checkEntryCategories(final List<SyndCategory> categories) {
        assertNotNull(categories);
        assertEquals(2, categories.size());
        checkCategory(categories.get(0), "Entry Category 1");
        checkCategory(categories.get(1), "Entry Category 2");
    }

    private void checkCategory(final SyndCategory category, final String expectedName) {
        assertNotNull(category);
        assertEquals("http://somewhere.org/taxonomy", category.getTaxonomyUri());
        assertEquals(expectedName, category.getName());
    }
}
