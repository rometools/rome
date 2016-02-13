/* ----------------------------------------------------------------------------
 * (c) Mobile IQ Ltd 2009. All Rights Reserved.
 * ----------------------------------------------------------------------------
 */
package com.rometools.rome.unittest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.rometools.rome.feed.module.DCModule;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

public class TestDublinCore extends FeedTest {
    public TestDublinCore() {
        super("dublinCoreTest.xml");
    }

    public void testDublinCoreDateIsPreferredWhenAvailableRatherThanRss20PubDate() throws Exception {
        final SyndFeed feed = this.getCachedSyndFeed();
        final SyndEntry entry = feed.getEntries().get(0);
        final DCModule dc = (DCModule) entry.getModule(DCModule.URI);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final Date expected = sdf.parse("2009-03-03T13:06:20Z");
        assertEquals("<dc:date/> element is returned as date, rather than <pubDate/>.", expected, dc.getDate());
    }
}