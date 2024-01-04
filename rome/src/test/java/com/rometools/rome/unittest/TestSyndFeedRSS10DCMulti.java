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
 */
package com.rometools.rome.unittest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

import com.rometools.rome.feed.module.DCModule;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.impl.DateParser;

/**
 * Test case for the multi-valued DublinCore module elements.
 */
public class TestSyndFeedRSS10DCMulti extends TestSyndFeedRSS10 {

    public TestSyndFeedRSS10DCMulti() {
        super("rss_1.0", "rss_1.0_DC_multi.xml");
    }

    protected TestSyndFeedRSS10DCMulti(final String type) {
        super(type);
    }

    protected TestSyndFeedRSS10DCMulti(final String feedType, final String feedFileName) {
        super(feedType, feedFileName);
    }

    public void testChannelDCModule() throws Exception {
        final DCModule dc = (DCModule) this.getCachedSyndFeed().getModule(DCModule.URI);
        testDCModule(dc, "channel.");
    }

    protected void testDCModule(final DCModule dc, final String prefix) throws Exception {
        assertNotNull(dc);

        assertProperty(dc.getTitles().get(0), prefix + "dc:title[0]");
        assertProperty(dc.getTitles().get(1), prefix + "dc:title[1]");

        assertProperty(dc.getCreators().get(0), prefix + "dc:creator[0]");
        assertProperty(dc.getCreators().get(1), prefix + "dc:creator[1]");

        assertProperty(dc.getSubjects().get(0).getValue(), prefix + "dc:subject[0]");
        final String taxo0 = dc.getSubjects().get(0).getTaxonomyUri();
        if (taxo0 != null) {
            assertProperty(taxo0, prefix + "dc:subject[0].taxo:topic^resource");
        }
        assertProperty(dc.getSubjects().get(1).getValue(), prefix + "dc:subject[1]");
        final String taxo1 = dc.getSubjects().get(1).getTaxonomyUri();
        if (taxo1 != null) {
            assertProperty(taxo1, prefix + "dc:subject[1].taxo:topic^resource");
        }

        assertProperty(dc.getDescriptions().get(0), prefix + "dc:description[0]");
        assertProperty(dc.getDescriptions().get(1), prefix + "dc:description[1]");

        assertProperty(dc.getPublishers().get(0), prefix + "dc:publisher[0]");
        assertProperty(dc.getPublishers().get(1), prefix + "dc:publisher[1]");

        assertProperty(dc.getContributors().get(0), prefix + "dc:contributor[0]");
        assertProperty(dc.getContributors().get(1), prefix + "dc:contributor[1]");
        final LocalDateTime date = DateParser.parseW3CDateTime("2001-01-01T00:00+00:00", Locale.US);
        assertEquals(dc.getDates().get(0), date);
        assertEquals(dc.getDates().get(1), date);

        assertProperty(dc.getTypes().get(0), prefix + "dc:type[0]");
        assertProperty(dc.getTypes().get(1), prefix + "dc:type[1]");

        assertProperty(dc.getFormats().get(0), prefix + "dc:format[0]");
        assertProperty(dc.getFormats().get(1), prefix + "dc:format[1]");

        assertProperty(dc.getIdentifiers().get(0), prefix + "dc:identifier[0]");
        assertProperty(dc.getIdentifiers().get(1), prefix + "dc:identifier[1]");

        assertProperty(dc.getSources().get(0), prefix + "dc:source[0]");
        assertProperty(dc.getSources().get(1), prefix + "dc:source[1]");

        assertProperty(dc.getLanguages().get(0), prefix + "dc:language[0]");
        assertProperty(dc.getLanguages().get(1), prefix + "dc:language[1]");

        assertProperty(dc.getRelations().get(0), prefix + "dc:relation[0]");
        assertProperty(dc.getRelations().get(1), prefix + "dc:relation[1]");

        assertProperty(dc.getCoverages().get(0), prefix + "dc:coverage[0]");
        assertProperty(dc.getCoverages().get(1), prefix + "dc:coverage[1]");

        assertProperty(dc.getRightsList().get(0), prefix + "dc:rights[0]");
        assertProperty(dc.getRightsList().get(1), prefix + "dc:rights[1]");
    }

    public void testItemsDCModule() throws Exception {
        testItemDCModule(0);
        testItemDCModule(1);
    }

    protected void testItemDCModule(final int i) throws Exception {
        final List<SyndEntry> entries = this.getCachedSyndFeed().getEntries();
        final SyndEntry entry = entries.get(i);
        final DCModule dc = (DCModule) entry.getModule(DCModule.URI);
        testDCModule(dc, "item[" + i + "].");
    }
}
