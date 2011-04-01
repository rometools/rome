/*
 * Created on Jun 24, 2004
 *
 */
package com.sun.syndication.unittest;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.io.impl.DateParser;

import java.util.List;
import java.util.Date;

/**
 * @author pat
 *
 */
public class TestSyndFeedRSS091N extends SyndFeedTest {

	public TestSyndFeedRSS091N() {
		super("rss_0.91N", "rss_0.91N.xml");
	}

    protected TestSyndFeedRSS091N(String type) {
        super(type);
    }

    protected TestSyndFeedRSS091N(String feedType,String feedFileName) {
        super(feedType,feedFileName);
    }

    public void testLanguage() throws Exception {
        assertProperty(getCachedSyndFeed().getLanguage(),"channel.language");
    }

    public void testCopyright() throws Exception {
        assertProperty(getCachedSyndFeed().getCopyright(),"channel.copyright");
    }

    public void testPublishedDate() throws Exception {
        Date d = DateParser.parseRFC822("Mon, 01 Jan 2001 00:00:00 GMT");
        assertEquals(getCachedSyndFeed().getPublishedDate(),d);
    }

    public void testAuthor() throws Exception {
        assertProperty(getCachedSyndFeed().getAuthor(),"channel.managingEditor");
    }

    public void testImageTitle() throws Exception {
        assertProperty(getCachedSyndFeed().getImage().getTitle(),"channel.image.title");
    }

    public void testImageUrl() throws Exception {
        assertProperty(getCachedSyndFeed().getImage().getUrl(),"channel.image.url");
    }

    public void testImageLink() throws Exception {
        assertProperty(getCachedSyndFeed().getImage().getLink(),"channel.image.link");
    }

    public void testImageDescription() throws Exception {
        assertProperty(getCachedSyndFeed().getImage().getDescription(),"channel.image.description");
    }

    protected void _testItem(int i) throws Exception {
        List items = getCachedSyndFeed().getEntries();
        SyndEntry entry = (SyndEntry) items.get(i);
        assertProperty(entry.getTitle(),"channel.item["+i+"].title");
        assertProperty(entry.getLink(),"channel.item["+i+"].link");
        assertProperty(entry.getDescription().getValue(),"channel.item["+i+"].description");
    }

    protected void _testUri(SyndEntry entry,int i) throws Exception {
        assertProperty(entry.getUri(),"channel.item["+i+"].link");
    }



}
