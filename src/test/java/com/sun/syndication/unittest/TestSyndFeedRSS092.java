/*
 * Created on Jun 24, 2004
 *
 */
package com.sun.syndication.unittest;

import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEnclosure;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * @author pat
 *
 */
public class TestSyndFeedRSS092 extends TestSyndFeedRSS091N {

    public TestSyndFeedRSS092() {
        super("rss_0.92");
    }

    protected TestSyndFeedRSS092(String type) {
        super(type);
    }

    protected TestSyndFeedRSS092(String feedType,String feedFileName) {
        super(feedType,feedFileName);
    }

    protected void _testItem(int i) throws Exception {
        super._testItem(i);
        List<SyndEntry> items = getCachedSyndFeed().getEntries();
        SyndEntry entry = (SyndEntry) items.get(i);

        assertProperty(entry.getTitle(),"channel.item["+i+"].title");
        assertProperty(entry.getLink(),"channel.item["+i+"].link");
        assertProperty(entry.getDescription().getValue(),"channel.item["+i+"].description");
        _testCategories(entry.getCategories(),"channel.item["+i+"]");
        _testEnclosures(entry.getEnclosures(),"channel.item["+i+"]");
    }

    protected void _testCategories(List<SyndCategory> cats,String prefix) throws Exception {
        Set<String> s1 = new HashSet<String>();
        Set<String> s2 = new HashSet<String>();
        for (int i=0;i<cats.size();i++) {
            SyndCategory cat = (SyndCategory) cats.get(i);
            s1.add(cat.getTaxonomyUri()+" "+cat.getName());
            s2.add(getPrefix() + "." +prefix+".category["+i+"]^domain"+" "+getPrefix() + "." +prefix+".category["+i+"]");
        }
        assertTrue(s1.equals(s2));
    }

    protected void _testEnclosures(List<SyndEnclosure> encs,String prefix) throws Exception {
        Set<String> s1 = new HashSet<String>();
        Set<String> s2 = new HashSet<String>();
        for (int i=0;i<encs.size();i++) {
            SyndEnclosure enc = (SyndEnclosure) encs.get(i);
            s1.add(enc.getUrl()+" "+enc.getType()+" "+enc.getLength());
            s2.add(getPrefix() + "." +prefix+".enclousure["+i+"]^url"+" "+
                   getPrefix() + "." +prefix+".enclousure["+i+"]^type"+" "+
                   "100");
        }
        assertTrue(s1.equals(s2));
    }
}
