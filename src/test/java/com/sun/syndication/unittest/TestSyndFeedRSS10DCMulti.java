package com.sun.syndication.unittest;

import com.sun.syndication.feed.module.DCModule;
import com.sun.syndication.feed.module.DCSubject;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.io.impl.DateParser;

import java.util.List;
import java.util.Date;


/**
 * Test case for the multi-valued DublinCore module elements.
 * <p>
 * @author Paul Dlug
 */
public class TestSyndFeedRSS10DCMulti extends TestSyndFeedRSS10 {

    public TestSyndFeedRSS10DCMulti() {
        super("rss_1.0", "rss_1.0_DC_multi.xml");
    }

    protected TestSyndFeedRSS10DCMulti(String type) {
        super(type);
    }

    protected TestSyndFeedRSS10DCMulti(String feedType, String feedFileName) {
        super(feedType, feedFileName);
    }

    public void testChannelDCModule() throws Exception {
        DCModule dc = (DCModule) getCachedSyndFeed().getModule(DCModule.URI);
        _testDCModule(dc, "channel.");
    }

    protected void _testDCModule(DCModule dc,String prefix) throws Exception {
        assertNotNull(dc);
        
        assertProperty((String)dc.getTitles().get(0), prefix + "dc:title[0]");
        assertProperty((String)dc.getTitles().get(1), prefix + "dc:title[1]");
        
        assertProperty((String)dc.getCreators().get(0), prefix + "dc:creator[0]");
        assertProperty((String)dc.getCreators().get(1), prefix + "dc:creator[1]");
        
        assertProperty(((DCSubject)dc.getSubjects().get(0)).getValue(), prefix + "dc:subject[0]");
        String taxo0 = ((DCSubject)dc.getSubjects().get(0)).getTaxonomyUri();
        if (taxo0 != null) {
            assertProperty(taxo0, prefix + "dc:subject[0].taxo:topic^resource");
        }
        assertProperty(((DCSubject)dc.getSubjects().get(1)).getValue(), prefix + "dc:subject[1]");
        String taxo1 = ((DCSubject)dc.getSubjects().get(1)).getTaxonomyUri();
        if (taxo1 != null) {
            assertProperty(taxo1, prefix + "dc:subject[1].taxo:topic^resource");
        }
        
        assertProperty((String)dc.getDescriptions().get(0), prefix + "dc:description[0]");
        assertProperty((String)dc.getDescriptions().get(1), prefix + "dc:description[1]");
        
        assertProperty((String)dc.getPublishers().get(0), prefix + "dc:publisher[0]");
        assertProperty((String)dc.getPublishers().get(1), prefix + "dc:publisher[1]");
        
        assertProperty((String)dc.getContributors().get(0),prefix + "dc:contributor[0]");
        assertProperty((String)dc.getContributors().get(1),prefix + "dc:contributor[1]");
        Date date = DateParser.parseW3CDateTime("2001-01-01T00:00+00:00");
        assertEquals((Date)dc.getDates().get(0), date);
        assertEquals((Date)dc.getDates().get(1), date);
        
        assertProperty((String)dc.getTypes().get(0), prefix + "dc:type[0]");
        assertProperty((String)dc.getTypes().get(1), prefix +"dc:type[1]");
        
        assertProperty((String)dc.getFormats().get(0), prefix + "dc:format[0]");
        assertProperty((String)dc.getFormats().get(1), prefix + "dc:format[1]");
        
        assertProperty((String)dc.getIdentifiers().get(0), prefix + "dc:identifier[0]");
        assertProperty((String)dc.getIdentifiers().get(1), prefix + "dc:identifier[1]");
        
        assertProperty((String)dc.getSources().get(0), prefix + "dc:source[0]");
        assertProperty((String)dc.getSources().get(1), prefix + "dc:source[1]");
        
        assertProperty((String)dc.getLanguages().get(0), prefix + "dc:language[0]");
        assertProperty((String)dc.getLanguages().get(1), prefix + "dc:language[1]");
        
        assertProperty((String)dc.getRelations().get(0), prefix + "dc:relation[0]");
        assertProperty((String)dc.getRelations().get(1), prefix + "dc:relation[1]");
        
        assertProperty((String)dc.getCoverages().get(0), prefix + "dc:coverage[0]");
        assertProperty((String)dc.getCoverages().get(1), prefix + "dc:coverage[1]");
        
        assertProperty((String)dc.getRightsList().get(0), prefix + "dc:rights[0]");
        assertProperty((String)dc.getRightsList().get(1), prefix + "dc:rights[1]");
    }

    public void testItemsDCModule() throws Exception {
        _testItemDCModule(0);
        _testItemDCModule(1);
    }

    protected void _testItemDCModule(int i) throws Exception {
        List entries = getCachedSyndFeed().getEntries();
        SyndEntry entry = (SyndEntry) entries.get(i);
        DCModule dc = (DCModule) entry.getModule(DCModule.URI);
        _testDCModule(dc, "item[" + i + "].");
    }
}
