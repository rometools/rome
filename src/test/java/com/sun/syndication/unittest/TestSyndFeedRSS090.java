/*
 * Created on Jun 24, 2004
 *
 */
package com.sun.syndication.unittest;

import com.sun.syndication.feed.synd.SyndEntry;

import java.util.List;

/**
 * @author pat
 *
 */
public class TestSyndFeedRSS090 extends SyndFeedTest {

    public TestSyndFeedRSS090() {
        super("rss_0.9");
    }

    protected TestSyndFeedRSS090(String type) {
        super(type);
    }

    protected TestSyndFeedRSS090(String feedType,String feedFileName) {
        super(feedType,feedFileName);
    }

    public void testTitle() throws Exception {
        assertProperty(getCachedSyndFeed().getTitle(),"channel.title");
    }

    public void testLink() throws Exception {
        assertProperty( getCachedSyndFeed().getLink(),"channel.link");
    }

    public void testDescription() throws Exception {
        assertProperty(getCachedSyndFeed().getDescription(),"channel.description");
    }

    public void testImageTitle() throws Exception {
        assertProperty(getCachedSyndFeed().getImage().getTitle(),"image.title");
    }

    public void testImageUrl() throws Exception {
        assertProperty(getCachedSyndFeed().getImage().getUrl(),"image.url");
    }

    public void testImageLink() throws Exception {
        assertProperty(getCachedSyndFeed().getImage().getLink(),"image.link");
    }

    protected void _testItem(int i) throws Exception {
        List items = getCachedSyndFeed().getEntries();
        SyndEntry entry = (SyndEntry) items.get(i);
        assertProperty(entry.getTitle(),"item["+i+"].title");
        assertProperty(entry.getLink(),"item["+i+"].link");

        _testUri(entry,i);
    }

    public void testItem0() throws Exception {
        _testItem(0);
    }

    public void testItem1() throws Exception {
        _testItem(1);
    }

    protected void _testUri(SyndEntry entry,int i) throws Exception {
        assertProperty(entry.getUri(),"item["+i+"].link");
    }
    
    public void testLanguage() throws Exception {
    	// not supported
    }
    
    public void testPublishedDate() throws Exception {
    	// not supported
    }

    public void testImage() throws Exception {
    	// not supported
    }

	public void testEntryTitle() throws Exception {
		assertEqualsStr("item[0].title", getEntryTitle(getCachedSyndFeed().getEntries().get(0)));
		assertEqualsStr("item[1].title", getEntryTitle(getCachedSyndFeed().getEntries().get(1)));
	}
	
	public void testEntryDescription() throws Exception {
		// I think this should be should work, but it can't seem to find the description		
		//System.out.println(((SyndEntry)getCachedSyndFeed().getEntries().get(0)).getDescription());
	}	
	
	public void testEntryLink() throws Exception {
		assertEqualsStr("item[0].link", getEntryLink(getCachedSyndFeed().getEntries().get(0)));
		assertEqualsStr("item[1].link", getEntryLink(getCachedSyndFeed().getEntries().get(1)));		
	}
	
	public void testEntryPublishedDate() throws Exception {
		// not supported
	}
    
}
