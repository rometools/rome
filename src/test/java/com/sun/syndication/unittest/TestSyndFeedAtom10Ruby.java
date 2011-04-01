package com.sun.syndication.unittest;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.impl.Atom10Parser;

public class TestSyndFeedAtom10Ruby extends FeedTest {

    protected void setUp() throws Exception {
        super.setUp();
        Atom10Parser.setResolveURIs(true);
    }

    protected void tearDown() throws Exception {
        Atom10Parser.setResolveURIs(false);
        super.tearDown();
    }
    
    public TestSyndFeedAtom10Ruby() {
		super("atom_1.0_ruby.xml");
	}

	public void testFeedURI() throws Exception {
        SyndFeed feed = getSyndFeed(false);
		assertEquals("http://www.example.com/blog", feed.getUri());
	}
	public void testEntry1URI() throws Exception {
        SyndFeed feed = getSyndFeed(false);
        SyndEntry entry = (SyndEntry)feed.getEntries().get(0);
        assertEquals("http://www.example.com/blog/bloggy-blog", entry.getLink());
	}
	public void testEntry2URI() throws Exception {
        SyndFeed feed = getSyndFeed(false);
        SyndEntry entry = (SyndEntry)feed.getEntries().get(1);
        assertEquals("http://www.example.com/frog/froggy-frog", entry.getLink());
	}
}
