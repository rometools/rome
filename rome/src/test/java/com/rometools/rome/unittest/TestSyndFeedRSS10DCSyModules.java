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
import com.rometools.rome.feed.module.SyModule;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.impl.DateParser;

public class TestSyndFeedRSS10DCSyModules extends TestSyndFeedRSS10 {

    public TestSyndFeedRSS10DCSyModules() {
        super("rss_1.0", "rss_1.0_DC_Sy.xml");
    }

    protected TestSyndFeedRSS10DCSyModules(final String type) {
        super(type);
    }

    protected TestSyndFeedRSS10DCSyModules(final String feedType, final String feedFileName) {
        super(feedType, feedFileName);
    }

    public void testChannelDCModule() throws Exception {
        final DCModule dc = (DCModule) this.getCachedSyndFeed().getModule(DCModule.URI);
        testDCModule(dc, "channel.");
    }

    protected void testDCModule(final DCModule dc, final String prefix) throws Exception {
        assertNotNull(dc);
        assertProperty(dc.getTitle(), prefix + "dc:title");
        assertProperty(dc.getCreator(), prefix + "dc:creator");
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
        assertProperty(dc.getDescription(), prefix + "dc:description");
        assertProperty(dc.getPublisher(), prefix + "dc:publisher");
        assertProperty(dc.getContributors().get(0), prefix + "dc:contributor[0]");
        assertProperty(dc.getContributors().get(1), prefix + "dc:contributor[1]");
        final LocalDateTime date = DateParser.parseW3CDateTime("2001-01-01T00:00+00:00", Locale.US);
        assertEquals(dc.getDate(), date);
        assertProperty(dc.getType(), prefix + "dc:type");
        assertProperty(dc.getFormat(), prefix + "dc:format");
        assertProperty(dc.getIdentifier(), prefix + "dc:identifier");
        assertProperty(dc.getSource(), prefix + "dc:source");
        assertProperty(dc.getLanguage(), prefix + "dc:language");
        assertProperty(dc.getRelation(), prefix + "dc:relation");
        assertProperty(dc.getCoverage(), prefix + "dc:coverage");
        assertProperty(dc.getRights(), prefix + "dc:rights");
    }

    public void testChannelSyModule() throws Exception {
        final SyModule sy = (SyModule) this.getCachedSyndFeed().getModule(SyModule.URI);
        assertNotNull(sy);
        assertEquals(sy.getUpdatePeriod(), SyModule.HOURLY);
        assertEquals(sy.getUpdateFrequency(), 100);
        final LocalDateTime date = DateParser.parseW3CDateTime("2001-01-01T01:00+00:00", Locale.US);
        assertEquals(sy.getUpdateBase(), date);
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
