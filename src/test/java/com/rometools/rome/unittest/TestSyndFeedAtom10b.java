package com.rometools.rome.unittest;

import java.util.List;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.impl.Atom10Parser;

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
