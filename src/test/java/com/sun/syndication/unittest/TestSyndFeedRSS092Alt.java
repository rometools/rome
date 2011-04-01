
package com.sun.syndication.unittest;

import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEnclosure;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class TestSyndFeedRSS092Alt extends TestSyndFeedRSS092 {

    public TestSyndFeedRSS092Alt() {
        this("rss_0.92","rss_0.92_alt.xml");
    }

    protected TestSyndFeedRSS092Alt(String feedType,String feedFileName) {
        super(feedType,feedFileName);
    }

    protected void _testEnclosures(List encs,String prefix) throws Exception {
        Set s1 = new HashSet();
        Set s2 = new HashSet();
        for (int i=0;i<encs.size();i++) {
            SyndEnclosure enc = (SyndEnclosure) encs.get(i);
            s1.add(enc.getUrl()+" "+enc.getType()+" "+enc.getLength());
            s2.add(getPrefix() + "." +prefix+".enclousure["+i+"]^url"+" "+
                   getPrefix() + "." +prefix+".enclousure["+i+"]^type"+" "+
                   "0");
        }
        assertTrue(s1.equals(s2));
    }
}
