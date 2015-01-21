package com.rometools.rome.unittest;

import java.util.List;

import com.rometools.rome.feed.module.CBModule;
import com.rometools.rome.feed.module.CBNews;
import com.rometools.rome.feed.module.CBPaper;
import com.rometools.rome.feed.module.CBPerson;
import com.rometools.rome.feed.module.CBResource;
import com.rometools.rome.feed.module.CBRole;
import com.rometools.rome.feed.module.CBSpeech;
import com.rometools.rome.feed.module.CBStatistics;
import com.rometools.rome.feed.synd.SyndEntry;

/**
 * @author Norbert Kiesel <nkiesel@metricstream.com>
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 */
public class TestSyndFeedRSS10DCCBModules extends FeedTest {

    public TestSyndFeedRSS10DCCBModules() {
       super("rss_1.0_DC_CB.xml");
    }

    protected void testCBModule(final CBModule cb, final String prefix, int idx) throws Exception {
        assertNotNull(cb);
		final CBNews news = cb.getNews();
        assertNotNull(news);
        
        final CBPaper paper = cb.getPaper();
        assertNotNull(paper);
        
        final CBSpeech speech = cb.getSpeech();
        assertNotNull(speech);
        
        final CBStatistics stat = cb.getStatistics();
        assertNotNull(stat);
        
		switch (idx) {
		case 0:
			assertEquals("2014-11-24 12:08 PM", news.getOccurenceDate());
			assertNull(news.getSimpleTitle());
			
			//cb:paper
			assertEquals("2015-01-16T11:00:00Z", paper.getOccurrenceDate());
			assertEquals("Secular stagnation, debt overhang and other rationales for sluggish growth, six years on", paper.getSimpleTitle());
			assertEquals("BIS", paper.getInstitutionAbbrev());
			List<String> keyList = paper.getKeyword();
			assertEquals("3", Integer.toString(keyList.size()));
			for(String key : keyList){
				assertNotNull(key);
			}
			
			//cb:speech
			assertEquals("Benoît Cœuré: Interview in Libération", speech.getSimpleTitle());
			assertEquals("2015-01-19T11:09:00Z", speech.getOccurrenceDate());
			assertEquals("BIS", speech.getInstitutionAbbrev());
			
			//cb:stat
			assertEquals("BIS", stat.getInstitutionAbbrev());
			
			break;
		case 1:
			assertEquals("2014-10-28 12:10 PM", news.getOccurenceDate());
			assertEquals("Macroeconomic Review October 2014", news.getSimpleTitle());
			
			//cb:resource
			CBResource res = paper.getResource();
			assertEquals("PDF version", res.getTitle());
			assertEquals("http://www.bis.org/publ/work482.pdf", res.getLink());
			assertEquals("Test description", res.getDescription());
			
			//cb:person
			CBPerson person = paper.getPerson();
			assertNotNull(person);
			assertEquals("TJ", person.getGivenName());
			assertEquals("Thomas Jordan", person.getNameAsWritten());
			assertEquals("Mr", person.getPersonalTitle());
			assertEquals("Jordan", person.getSurname());
			
			//cb:role
			CBRole role = person.getRole();
			assertEquals("Chairman of the Governing Board", role.getJobTitle());
			assertEquals("Swiss National Bank", role.getAffiliation());
			
			//cb:speech
			assertEquals("Bengaluru", speech.getLocationAsWritten());
			assertEquals("Bangalore", speech.getLocationCity());
			assertEquals("Karnataka", speech.getLocationState());
			
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
