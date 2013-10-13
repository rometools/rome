/*
 * Created on Jun 24, 2004
 *
 */
package com.sun.syndication.unittest;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.io.impl.DateParser;

/**
 * @author pat
 * 
 */
public class TestSyndFeedRSS091N extends SyndFeedTest {

    public TestSyndFeedRSS091N() {
        super("rss_0.91N", "rss_0.91N.xml");
    }

    protected TestSyndFeedRSS091N(final String type) {
        super(type);
    }

    protected TestSyndFeedRSS091N(final String feedType, final String feedFileName) {
        super(feedType, feedFileName);
    }

    @Override
    public void testLanguage() throws Exception {
        assertProperty(this.getCachedSyndFeed().getLanguage(), "channel.language");
    }

    public void testCopyright() throws Exception {
        assertProperty(this.getCachedSyndFeed().getCopyright(), "channel.copyright");
    }

    @Override
    public void testPublishedDate() throws Exception {
        final Date d = DateParser.parseRFC822("Mon, 01 Jan 2001 00:00:00 GMT", Locale.US);
        assertEquals(this.getCachedSyndFeed().getPublishedDate(), d);
    }

    public void testAuthor() throws Exception {
        assertProperty(this.getCachedSyndFeed().getAuthor(), "channel.managingEditor");
    }

    public void testImageTitle() throws Exception {
        assertProperty(this.getCachedSyndFeed().getImage().getTitle(), "channel.image.title");
    }

    public void testImageUrl() throws Exception {
        assertProperty(this.getCachedSyndFeed().getImage().getUrl(), "channel.image.url");
    }

    public void testImageLink() throws Exception {
        assertProperty(this.getCachedSyndFeed().getImage().getLink(), "channel.image.link");
    }

    public void testImageDescription() throws Exception {
        assertProperty(this.getCachedSyndFeed().getImage().getDescription(), "channel.image.description");
    }

    protected void testItem(final int i) throws Exception {
        final List<SyndEntry> items = this.getCachedSyndFeed().getEntries();
        final SyndEntry entry = items.get(i);
        assertProperty(entry.getTitle(), "channel.item[" + i + "].title");
        assertProperty(entry.getLink(), "channel.item[" + i + "].link");
        assertProperty(entry.getDescription().getValue(), "channel.item[" + i + "].description");
    }

    protected void testUri(final SyndEntry entry, final int i) throws Exception {
        assertProperty(entry.getUri(), "channel.item[" + i + "].link");
    }

}
