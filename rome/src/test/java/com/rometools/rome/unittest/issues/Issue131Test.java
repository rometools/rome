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
package com.rometools.rome.unittest.issues;

import java.util.List;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.unittest.FeedTest;

/**
 * Test for #131: SyndFeedImpl copyFrom method does not copy Entry Categories.
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
