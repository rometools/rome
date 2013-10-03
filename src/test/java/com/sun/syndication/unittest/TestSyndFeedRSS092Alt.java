package com.sun.syndication.unittest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sun.syndication.feed.synd.SyndEnclosure;

public class TestSyndFeedRSS092Alt extends TestSyndFeedRSS092 {

    public TestSyndFeedRSS092Alt() {
        this("rss_0.92", "rss_0.92_alt.xml");
    }

    protected TestSyndFeedRSS092Alt(final String feedType, final String feedFileName) {
        super(feedType, feedFileName);
    }

    @Override
    protected void _testEnclosures(final List<SyndEnclosure> encs, final String prefix) throws Exception {
        final Set<String> s1 = new HashSet<String>();
        final Set<String> s2 = new HashSet<String>();
        for (int i = 0; i < encs.size(); i++) {
            final SyndEnclosure enc = encs.get(i);
            s1.add(enc.getUrl() + " " + enc.getType() + " " + enc.getLength());
            s2.add(getPrefix() + "." + prefix + ".enclousure[" + i + "]^url" + " " + getPrefix() + "." + prefix + ".enclousure[" + i + "]^type" + " " + "0");
        }
        assertTrue(s1.equals(s2));
    }
}
