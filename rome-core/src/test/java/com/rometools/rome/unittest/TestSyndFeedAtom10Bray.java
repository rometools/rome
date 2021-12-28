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
package com.rometools.rome.unittest;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.impl.Atom10Parser;

public class TestSyndFeedAtom10Bray extends FeedTest {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Atom10Parser.setResolveURIs(true);
    }

    @Override
    protected void tearDown() throws Exception {
        Atom10Parser.setResolveURIs(false);
        super.tearDown();
    }

    public TestSyndFeedAtom10Bray() {
        super("atom_1.0_bray.xml");
    }

    public void testFeedURI() throws Exception {
        final SyndFeed feed = getSyndFeed(false);
        assertEquals("Bad URL: " + feed.getUri(), "http://www.example.com/blog", feed.getUri());
    }

    public void testEntry1URI() throws Exception {
        final SyndFeed feed = getSyndFeed(false);
        final SyndEntry entry = feed.getEntries().get(0);
        assertEquals("Bad URL: " + entry.getLink(), "http://www.example.com/blog/2006-11-05/entry1", entry.getLink());
    }

    public void testEntry2URI() throws Exception {
        final SyndFeed feed = getSyndFeed(false);
        final SyndEntry entry = feed.getEntries().get(1);
        assertEquals("Bad URL: " + entry.getLink(), "http://www.example.com/blog/2006-11-02/entry2", entry.getLink());
    }
}
