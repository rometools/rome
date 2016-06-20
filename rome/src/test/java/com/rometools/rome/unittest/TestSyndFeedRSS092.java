/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created on Jun 24, 2004
 *
 */
package com.rometools.rome.unittest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;

public class TestSyndFeedRSS092 extends TestSyndFeedRSS091N {

    public TestSyndFeedRSS092() {
        super("rss_0.92");
    }

    protected TestSyndFeedRSS092(final String type) {
        super(type);
    }

    protected TestSyndFeedRSS092(final String feedType, final String feedFileName) {
        super(feedType, feedFileName);
    }

    @Override
    protected void testItem(final int i) throws Exception {
        super.testItem(i);
        final List<SyndEntry> items = this.getCachedSyndFeed().getEntries();
        final SyndEntry entry = items.get(i);

        assertProperty(entry.getTitle(), "channel.item[" + i + "].title");
        assertProperty(entry.getLink(), "channel.item[" + i + "].link");
        assertProperty(entry.getDescription().getValue(), "channel.item[" + i + "].description");
        testCategories(entry.getCategories(), "channel.item[" + i + "]");
        testEnclosures(entry.getEnclosures(), "channel.item[" + i + "]");
    }

    protected void testCategories(final List<SyndCategory> cats, final String prefix) throws Exception {
        final Set<String> s1 = new HashSet<String>();
        final Set<String> s2 = new HashSet<String>();
        for (int i = 0; i < cats.size(); i++) {
            final SyndCategory cat = cats.get(i);
            s1.add(cat.getTaxonomyUri() + " " + cat.getName());
            s2.add(getPrefix() + "." + prefix + ".category[" + i + "]^domain" + " " + getPrefix() + "." + prefix + ".category[" + i + "]");
        }
        assertTrue(s1.equals(s2));
    }

    protected void testEnclosures(final List<SyndEnclosure> encs, final String prefix) throws Exception {
        final Set<String> s1 = new HashSet<String>();
        final Set<String> s2 = new HashSet<String>();
        for (int i = 0; i < encs.size(); i++) {
            final SyndEnclosure enc = encs.get(i);
            s1.add(enc.getUrl() + " " + enc.getType() + " " + enc.getLength());
            s2.add(getPrefix() + "." + prefix + ".enclousure[" + i + "]^url" + " " + getPrefix() + "." + prefix + ".enclousure[" + i + "]^type" + " " + "100");
        }
        assertTrue(s1.equals(s2));
    }
}
