package com.rometools.rome.unittest;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.rometools.rome.feed.module.DCModule;
import com.rometools.rome.feed.module.CBModule;
import com.rometools.rome.feed.module.CBNews;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.impl.DateParser;

/**
 * @author Norbert Kiesel <nkiesel@metricstream.com>
 */
public class TestSyndFeedRSS10DCCBModules extends FeedTest {

    public TestSyndFeedRSS10DCCBModules() {
        super("rss_1.0_DC_CB.xml");
    }

    protected void testCBModule(final CBModule cb, final String prefix, int idx) throws Exception {
        assertNotNull(cb);
		final CBNews news = cb.getNews();
        assertNotNull(news);
		switch (idx) {
		case 0:
			assertEquals("2014-11-24 12:08 PM", news.getOccurenceDate());
			assertNull(news.getSimpleTitle());
			break;
		case 1:
			assertEquals("2014-10-28 12:10 PM", news.getOccurenceDate());
			assertEquals("Macroeconomic Review October 2014", news.getSimpleTitle());
			break;
		default:
			break;
		}
    }

    public void testItemsDCModule() throws Exception {
        testItemCBModule(0);
        testItemCBModule(1);
    }

    protected void testItemCBModule(final int i) throws Exception {
        final List<SyndEntry> entries = this.getCachedSyndFeed().getEntries();
        final SyndEntry entry = entries.get(i);
        final CBModule cb = (CBModule) entry.getModule(CBModule.URI);
        testCBModule(cb, "item[" + i + "].", i);

    }

}
