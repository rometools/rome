package com.sun.syndication.unittest;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.impl.Atom10Parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestSyndFeedAtom10Bray extends FeedTest {

    protected void setUp() throws Exception {
        super.setUp();
        Atom10Parser.setResolveURIs(true);
    }

    protected void tearDown() throws Exception {
        Atom10Parser.setResolveURIs(false);
        super.tearDown();
    }
    
    public TestSyndFeedAtom10Bray() {
		super("atom_1.0_bray.xml");
	}

	public void testFeedURI() throws Exception {
        SyndFeed feed = getSyndFeed(false);
		assertEquals("Bad URL: "+feed.getUri(), "http://www.example.com/blog", feed.getUri());
	}
	public void testEntry1URI() throws Exception {
        SyndFeed feed = getSyndFeed(false);
        SyndEntry entry = (SyndEntry)feed.getEntries().get(0);
        assertEquals("Bad URL: "+entry.getLink(), "http://www.example.com/blog/2006-11-05/entry1", entry.getLink());
	}
	public void testEntry2URI() throws Exception {
        SyndFeed feed = getSyndFeed(false);
        SyndEntry entry = (SyndEntry)feed.getEntries().get(1);
        assertEquals("Bad URL: "+entry.getLink(), "http://www.example.com/blog/2006-11-02/entry2", entry.getLink());
	}
}
