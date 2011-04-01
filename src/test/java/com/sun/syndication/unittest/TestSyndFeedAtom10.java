/*
 * Created on Jun 24, 2004
 *
 */
package com.sun.syndication.unittest;

import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndLink;
import com.sun.syndication.io.impl.DateParser;

/**
 * @author pat
 * @author Dave Johnson (modified for Atom 1.0)
 *
 */
public class TestSyndFeedAtom10 extends TestSyndFeedAtom03 {

	public TestSyndFeedAtom10() {
		super("atom_1.0");
	}

    protected TestSyndFeedAtom10(String type) {
        super(type);
    }

    protected TestSyndFeedAtom10(String feedType,String feedFileName) {
        super(feedType,feedFileName);
    }

    public void testTitle() throws Exception {
        assertProperty(getCachedSyndFeed().getTitle(),"feed.title");
        assertProperty(getCachedSyndFeed().getTitleEx().getValue(),"feed.title");
        assertEquals("html", getCachedSyndFeed().getTitleEx().getType());
        
        List altLinks = getCachedSyndFeed().getLinks();
        assertEquals(3, altLinks.size());
        
        assertEquals("http://example.com/blog", ((SyndLink)altLinks.get(0)).getHref());
        assertEquals("text/html", ((SyndLink)altLinks.get(0)).getType());
        
        assertEquals("http://example.com/blog_plain", ((SyndLink)altLinks.get(1)).getHref());
        assertEquals("text/plain", ((SyndLink)altLinks.get(1)).getType());
    }

    public void testLink() throws Exception {
        assertEquals( getCachedSyndFeed().getLink(),"http://example.com/blog");
    }

    public void getAuthor() throws Exception {
        assertProperty(getCachedSyndFeed().getAuthor(),"feed.author.name");
    }

    public void testCopyright() throws Exception {
        assertProperty(getCachedSyndFeed().getCopyright(),"feed.copyright");
    }

    public void testForeignMarkup() throws Exception {
        assertEquals(1, ((List)getCachedSyndFeed().getForeignMarkup()).size());
    }

    public void testPublishedDate() throws Exception {
        Date d = DateParser.parseW3CDateTime("2000-01-01T00:00:00Z");
        assertEquals(getCachedSyndFeed().getPublishedDate(),d);
    }


    protected void _testEntry(int i) throws Exception {
        List items = getCachedSyndFeed().getEntries();
        SyndEntry entry = (SyndEntry) items.get(i);

        assertProperty(entry.getTitle(),"feed.entry["+i+"].title");
        assertProperty(entry.getTitleEx().getValue(),"feed.entry["+i+"].title");
        assertEquals("text",entry.getTitleEx().getType());
        
        assertEquals("http://example.com/blog/entry" + (i + 1), entry.getLink());
        assertEquals(((SyndEnclosure)entry.getEnclosures().get(0)).getUrl(),"http://example.com/blog/enclosure"+(i+1)+".gif");
        assertProperty(entry.getAuthor(),"feed.entry["+i+"].author.name");
        Date d = DateParser.parseW3CDateTime("2000-0"+(i+1)+"-01T01:00:00Z");
        assertEquals(entry.getPublishedDate(),d);
        assertProperty(entry.getDescription().getValue(),"feed.entry["+i+"].summary");
        assertProperty(((SyndContent)entry.getContents().get(0)).getValue(),"feed.entry["+i+"].content[0]");
        assertEquals(1, ((List)entry.getForeignMarkup()).size());
  
        if (i == 0) {
            List links = entry.getLinks();
            assertEquals(4, links.size());

            assertEquals("http://example.com/blog/entry1", ((SyndLink)links.get(0)).getHref());
            assertEquals("text/html", ((SyndLink)links.get(0)).getType());

            assertEquals("http://example.com/blog/entry1_plain", ((SyndLink)links.get(1)).getHref());
            assertEquals("text/plain", ((SyndLink)links.get(1)).getType());
            
            SyndLink slink = (SyndLink)entry.getLinks().get(3);
            assertTrue(slink.getHref().startsWith("tag:"));        
        } else {
            SyndLink slink = (SyndLink)entry.getLinks().get(2);
            assertTrue(slink.getHref().startsWith("tag:"));        
            
        }
    }

    public void testEntry0() throws Exception {
        _testEntry(0);
    }

    public void testEntry1() throws Exception {
        _testEntry(1);
    }

	public void testEntryLink() throws Exception {
		assertEquals("http://example.com/blog/entry1", getEntryLink(getCachedSyndFeed().getEntries().get(0)));
		assertEquals("http://example.com/blog/entry2", getEntryLink(getCachedSyndFeed().getEntries().get(1)));
	}    
	
    public void testPreservedWireItems() throws Exception {
    	SyndEntry syndEntry1 = (SyndEntry) getCachedSyndFeed(true).getEntries().get(0);    	
    	Object o = syndEntry1.getWireEntry();
    	assertNotNull(o);
    	assertTrue(o instanceof Entry);
    	if (o instanceof Entry) {
    		Entry entry = (Entry) o;    		
			assertEquals("atom_1.0.feed.entry[0].rights", entry.getRights());			
		}    	
    }	
    
}
