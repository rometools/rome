package com.sun.syndication.unittest;

import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.impl.Atom10Parser;

public class TestSyndFeedAtom10b extends FeedTest {

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

    public TestSyndFeedAtom10b() {
        super("atom_1.0_b.xml");
    }

    public void testXmlBaseConformance() throws Exception {
        final SyndFeed feed = getSyndFeed(false);
        final List<SyndEntry> entries = feed.getEntries();
        for (int index = 0; index < entries.size(); index++) {
            final SyndEntry entry = entries.get(index);
            assertEquals("Incorrect URI: " + entry.getLink() + " in entry [" + entry.getTitle() + "]", "http://example.org/tests/base/result.html",
                    entry.getLink());
        }
    }
}
