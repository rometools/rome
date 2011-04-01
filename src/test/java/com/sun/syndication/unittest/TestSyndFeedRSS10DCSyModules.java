/*
 * Created on Jun 25, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.sun.syndication.unittest;

import com.sun.syndication.feed.module.DCModule;
import com.sun.syndication.feed.module.SyModule;
import com.sun.syndication.feed.module.DCSubject;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.io.impl.DateParser;

import java.util.List;
import java.util.Date;


/**
 * @author pat
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class TestSyndFeedRSS10DCSyModules extends TestSyndFeedRSS10 {

    public TestSyndFeedRSS10DCSyModules() {
        super("rss_1.0", "rss_1.0_DC_Sy.xml");
    }

    protected TestSyndFeedRSS10DCSyModules(String type) {
        super(type);
    }

    protected TestSyndFeedRSS10DCSyModules(String feedType,String feedFileName) {
        super(feedType,feedFileName);
    }

    public void testChannelDCModule() throws Exception {
        DCModule dc = (DCModule) getCachedSyndFeed().getModule(DCModule.URI);
        _testDCModule(dc,"channel.");
    }

    protected void _testDCModule(DCModule dc,String prefix) throws Exception {
        assertNotNull(dc);
        assertProperty(dc.getTitle(),prefix+"dc:title");
        assertProperty(dc.getCreator(),prefix+"dc:creator");
        assertProperty(((DCSubject)dc.getSubjects().get(0)).getValue(),prefix+"dc:subject[0]");
        String taxo0 = ((DCSubject)dc.getSubjects().get(0)).getTaxonomyUri();
        if (taxo0!=null) {
            assertProperty(taxo0,prefix+"dc:subject[0].taxo:topic^resource");
        }
        assertProperty(((DCSubject)dc.getSubjects().get(1)).getValue(),prefix+"dc:subject[1]");
        String taxo1 = ((DCSubject)dc.getSubjects().get(1)).getTaxonomyUri();
        if (taxo1!=null) {
            assertProperty(taxo1,prefix+"dc:subject[1].taxo:topic^resource");
        }
        assertProperty(dc.getDescription(),prefix+"dc:description");
        assertProperty(dc.getPublisher(),prefix+"dc:publisher");
        assertProperty((String)dc.getContributors().get(0),prefix+"dc:contributor[0]");
        assertProperty((String)dc.getContributors().get(1),prefix+"dc:contributor[1]");
        Date date = DateParser.parseW3CDateTime("2001-01-01T00:00+00:00");
        assertEquals(dc.getDate(),date);
        assertProperty(dc.getType(),prefix+"dc:type");
        assertProperty(dc.getFormat(),prefix+"dc:format");
        assertProperty(dc.getIdentifier(),prefix+"dc:identifier");
        assertProperty(dc.getSource(),prefix+"dc:source");
        assertProperty(dc.getLanguage(),prefix+"dc:language");
        assertProperty(dc.getRelation(),prefix+"dc:relation");
        assertProperty(dc.getCoverage(),prefix+"dc:coverage");
        assertProperty(dc.getRights(),prefix+"dc:rights");
    }

    public void testChannelSyModule() throws Exception {
        SyModule sy = (SyModule) getCachedSyndFeed().getModule(SyModule.URI);
        assertNotNull(sy);
        assertEquals(sy.getUpdatePeriod(),SyModule.HOURLY);
        assertEquals(sy.getUpdateFrequency(),100);
        Date date = DateParser.parseW3CDateTime("2001-01-01T01:00+00:00");
        assertEquals(sy.getUpdateBase(),date);
    }

    public void testItemsDCModule() throws Exception {
        _testItemDCModule(0);
        _testItemDCModule(1);
    }

    protected void _testItemDCModule(int i) throws Exception {
        List entries = getCachedSyndFeed().getEntries();
        SyndEntry entry = (SyndEntry) entries.get(i);
        DCModule dc = (DCModule) entry.getModule(DCModule.URI);
        _testDCModule(dc,"item["+i+"].");

    }


}
