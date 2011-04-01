/*
 * Created on Jun 24, 2004
 *
 */
package com.sun.syndication.unittest;

import com.sun.syndication.io.impl.DateParser;
import com.sun.syndication.feed.synd.SyndEntry;

import java.util.Date;
import java.util.List;

/**
 * @author pat
 *
 */
public class TestSyndFeedRSS093 extends TestSyndFeedRSS092 {

	public TestSyndFeedRSS093() {
		super("rss_0.93");
	}

    protected TestSyndFeedRSS093(String type) {
        super(type);
    }

    protected TestSyndFeedRSS093(String feedType,String feedFileName) {
        super(feedType,feedFileName);
    }

    protected void _testItem(int i) throws Exception {
        super._testItem(i);
        List items = getCachedSyndFeed().getEntries();
        SyndEntry entry = (SyndEntry) items.get(i);
        Date d = DateParser.parseRFC822("Mon, 0"+(i+1)+" Jan 2001 00:00:00 GMT");
        assertEquals(entry.getPublishedDate(),d);
        _testDescriptionType(entry,i);
    }

    protected void _testDescriptionType(SyndEntry entry,int i) throws Exception {
        assertProperty(entry.getDescription().getType(),"channel.item["+i+"].description^type");
    }

	public void testEntryPublishedDate() throws Exception {
		assertEquals(DateParser.parseRFC822("Mon, 01 Jan 2001 00:00:00 GMT"), getEntryPublishedDate(getCachedSyndFeed().getEntries().get(0)));
		assertEquals(DateParser.parseRFC822("Tue, 02 Jan 2001 00:00:00 GMT"), getEntryPublishedDate(getCachedSyndFeed().getEntries().get(1)));
	}    
    
}
