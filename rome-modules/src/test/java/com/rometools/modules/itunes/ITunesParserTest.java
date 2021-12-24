/*
 * ITunesParserTest.java
 * JUnit based test
 *
 * Created on August 2, 2005, 1:30 PM
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
package com.rometools.modules.itunes;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.itunes.AbstractITunesObject;
import com.rometools.modules.itunes.EntryInformationImpl;
import com.rometools.modules.itunes.FeedInformationImpl;
import com.rometools.modules.itunes.io.ITunesGenerator;
import com.rometools.modules.itunes.types.Duration;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class ITunesParserTest extends AbstractTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(ITunesParserTest.class);

    public ITunesParserTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(ITunesParserTest.class);

        return suite;
    }

    /**
     * Test of getNamespaceUri method, of class com.totsp.xml.syndication.itunes.ITunesParser.
     */
    public void testGetNamespaceUri() {
        LOG.debug("testGetNamespaceUri");

        assertEquals("Namespace", "http://www.itunes.com/dtds/podcast-1.0.dtd", new ITunesGenerator().getNamespaceUri());
    }

    /**
     * Test of parse method, of class com.totsp.xml.syndication.itunes.ITunesParser.
     */
    public void testParse() throws Exception {
        File feed = new File(getTestFile("itunes/leshow.xml"));
        final SyndFeedInput input = new SyndFeedInput();
        SyndFeed syndfeed = input.build(new XmlReader(feed.toURI().toURL()));

        final Module module = syndfeed.getModule(AbstractITunesObject.URI);
        final FeedInformationImpl feedInfo = (FeedInformationImpl) module;

        assertTrue(feedInfo.getBlock());
        assertEquals("owner", "Harry Shearer", feedInfo.getOwnerName());
        assertEquals("email", "", feedInfo.getOwnerEmailAddress());
        assertEquals("image", "http://a1.phobos.apple.com/Music/y2005/m06/d26/h21/mcdrrifv.jpg", feedInfo.getImage().toExternalForm());
        assertEquals("category1", "Comedy", feedInfo.getCategories().get(0).getName());
        assertEquals("category2",
                "Arts & Entertainment",
                feedInfo.getCategories().get(1).getName());
        assertEquals(
                "subCategory",
                "Entertainment",
                feedInfo.getCategories().get(1).getSubcategories().get(0).getName());
        assertEquals(
                "summary",
                "A weekly, hour-long romp through the worlds of media, politics, sports and show business, leavened with an eclectic mix of mysterious music, hosted by Harry Shearer.",
                feedInfo.getSummary());
        assertEquals(true, feedInfo.getComplete());
        assertEquals("http://example.org", feedInfo.getNewFeedUrl());

        List<SyndEntry> entries = syndfeed.getEntries();
        Iterator<SyndEntry> it = entries.iterator();

        while (it.hasNext()) {
            final SyndEntry entry = it.next();
            final EntryInformationImpl entryInfo = (EntryInformationImpl) entry.getModule(AbstractITunesObject.URI);
            LOG.debug("{}", entryInfo);
        }

        feed = new File(getTestFile("xml/rsr.xml"));
        syndfeed = input.build(new XmlReader(feed.toURI().toURL()));
        entries = syndfeed.getEntries();
        it = entries.iterator();

        while (it.hasNext()) {
            final SyndEntry entry = it.next();
            final EntryInformationImpl entryInfo = (EntryInformationImpl) entry.getModule(AbstractITunesObject.URI);
            LOG.debug("{}", entryInfo.getDuration());
        }
    }

    /**
     * Test of parse method, of class com.rometools.modules.itunes.io.ITunesParser.
     */
    public void testParseItem() throws Exception {
        File feed = new File(getTestFile("xml/leshow.xml"));
        final SyndFeedInput input = new SyndFeedInput();
        SyndFeed syndfeed = input.build(new XmlReader(feed.toURI().toURL()));

        SyndEntry entry = syndfeed.getEntries().get(0);

        EntryInformationImpl entryInfo = (EntryInformationImpl) entry.getModule(AbstractITunesObject.URI);
        assertEquals(true, entryInfo.getClosedCaptioned());
        assertEquals(Integer.valueOf(2), entryInfo.getOrder());
        assertEquals("http://example.org/image.png", entryInfo.getImage().toString());
        assertFalse(entryInfo.getExplicit());
        assertEquals("test-itunes-title", entryInfo.getTitle());

        SyndEntry entry1 = syndfeed.getEntries().get(1);
        EntryInformationImpl entryInfo1 = (EntryInformationImpl) entry1.getModule(AbstractITunesObject.URI);
        assertTrue(entryInfo1.getExplicit());

        SyndEntry entry2 = syndfeed.getEntries().get(2);
        EntryInformationImpl entryInfo2 = (EntryInformationImpl) entry2.getModule(AbstractITunesObject.URI);
        assertFalse(entryInfo2.getExplicit());
        assertNull(entryInfo2.getExplicitNullable());
    }

    public void testDuration() throws Exception {
        SyndFeed feed = new SyndFeedInput().build(new XmlReader(getClass().getResource("duration.xml")));
        SyndEntry entry = feed.getEntries().get(0);
        EntryInformationImpl module = (EntryInformationImpl) entry.getModule(AbstractITunesObject.URI);

        assertEquals(1000, module.getDuration().getMilliseconds());
    }

    public void testDurationEmpty() throws Exception {
        SyndFeed feed = new SyndFeedInput().build(new XmlReader(getClass().getResource("duration-empty.xml")));
        SyndEntry entry = feed.getEntries().get(0);
        EntryInformationImpl module = (EntryInformationImpl) entry.getModule(AbstractITunesObject.URI);

        assertNull(module.getDuration());
    }

    public void testDurationBad() throws Exception {
        SyndFeed feed = new SyndFeedInput().build(new XmlReader(getClass().getResource("duration-bad.xml")));
        SyndEntry entry = feed.getEntries().get(0);
        EntryInformationImpl module = (EntryInformationImpl) entry.getModule(AbstractITunesObject.URI);

        assertNull(module.getDuration());
    }

    public void testExplicitnessTrue() throws Exception {
        ArrayList<String> xmlFiles = new ArrayList<String>();
        xmlFiles.add("explicitness-capital-yes.xml");
        xmlFiles.add("explicitness-yes.xml");

        for (String xml : xmlFiles) {
            SyndFeed feed = new SyndFeedInput().build(new XmlReader(getClass().getResource(xml)));
            FeedInformationImpl module = (FeedInformationImpl) feed.getModule(AbstractITunesObject.URI);

            assertTrue(module.getExplicitNullable());
        }
    }

    public void testExplicitnessFalse() throws Exception {
        ArrayList<String> xmlFiles = new ArrayList<String>();
        xmlFiles.add("explicitness-no.xml");
        xmlFiles.add("explicitness-clean.xml");

        for (String xml : xmlFiles) {
            SyndFeed feed = new SyndFeedInput().build(new XmlReader(getClass().getResource(xml)));
            FeedInformationImpl module = (FeedInformationImpl) feed.getModule(AbstractITunesObject.URI);

            assertFalse(module.getExplicitNullable());
        }
    }

    public void testParseNonHttpUris() throws Exception {
        File feed = new File(getTestFile("itunes/no-http-uris.xml"));
        final SyndFeedInput input = new SyndFeedInput();
        SyndFeed syndfeed = input.build(new XmlReader(feed.toURI().toURL()));

        final FeedInformationImpl feedInfo = (FeedInformationImpl) syndfeed.getModule(AbstractITunesObject.URI);

        assertEquals("file://some-location/1.jpg", feedInfo.getImageUri());

        SyndEntry entry = syndfeed.getEntries().get(0);
        EntryInformationImpl module = (EntryInformationImpl) entry.getModule(AbstractITunesObject.URI);

        assertEquals("gs://some-location/whitespaces are allowed/2.jpg", module.getImageUri());
    }
}
