/*
 * ITunesGeneratorTest.java
 * JUnit based test
 *
 * Created on August 2, 2005, 2:31 PM
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
package com.rometools.modules;

import java.io.File;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.itunes.AbstractITunesObject;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;

public class ITunesGeneratorTest extends AbstractTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(ITunesGeneratorTest.class);

    public ITunesGeneratorTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(ITunesGeneratorTest.class);

        return suite;
    }

    /**
     * Test of generate method, of class com.totsp.xml.syndication.itunes.ITunesGenerator.
     */
    public void testEndToEnd() throws Exception {
        LOG.debug("testEndToEnd");
        testFile("xml/leshow.xml");

        // testFile( "/test/xml/apple.xml" );
        // testFile( "/test/xml/lr.xml" );
    }

    private void testFile(final String filename) throws Exception {
        final File feed = new File(getTestFile(filename));
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed syndfeed = input.build(new XmlReader(feed.toURI().toURL()));

        final SyndFeedOutput output = new SyndFeedOutput();
        final File outfeed = new File("target/" + feed.getName());
        output.output(syndfeed, outfeed);

        final SyndFeed syndCheck = input.build(new XmlReader(outfeed.toURI().toURL()));
        LOG.debug(syndCheck.getModule(AbstractITunesObject.URI).toString());
        assertEquals("Feed Level: ", syndfeed.getModule(AbstractITunesObject.URI).toString(), syndCheck.getModule(AbstractITunesObject.URI).toString());

        final List<SyndEntry> syndEntries = syndfeed.getEntries();
        final List<SyndEntry> syndChecks = syndCheck.getEntries();

        for (int i = 0; i < syndEntries.size(); i++) {
            final SyndEntry entry = syndEntries.get(i);
            final SyndEntry check = syndChecks.get(i);
            LOG.debug("Original: {}", entry.getModule(AbstractITunesObject.URI));
            LOG.debug("Check:    {}", check.getModule(AbstractITunesObject.URI));
            assertEquals("Entry Level: ", entry.getModule(AbstractITunesObject.URI).toString(), check.getModule(AbstractITunesObject.URI).toString());
        }
    }
}
