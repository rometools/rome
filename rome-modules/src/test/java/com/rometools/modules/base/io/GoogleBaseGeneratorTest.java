/*
 * GoogleBaseGeneratorTest.java
 * JUnit based test
 *
 * Created on November 17, 2005, 3:40 PM
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
package com.rometools.modules.base.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.base.GoogleBase;
import com.rometools.modules.base.GoogleBaseImpl;
import com.rometools.modules.base.Product;
import com.rometools.modules.base.Vehicle;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;

public class GoogleBaseGeneratorTest extends AbstractTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(GoogleBaseGeneratorTest.class);

    public GoogleBaseGeneratorTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(GoogleBaseGeneratorTest.class);

        return suite;
    }

    /**
     * Test of generate method, of class com.totsp.xml.syndication.base.io.GoogleBaseGenerator.
     */
    public void testGenerate() throws Exception {
        LOG.debug("testGenerate");
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeedOutput output = new SyndFeedOutput();
        final File testDir = new File(super.getTestFile("xml"));
        final File[] testFiles = testDir.listFiles();
        for (int h = 0; h < testFiles.length; h++) {
            if (!testFiles[h].getName().endsWith(".xml")) {
                continue;
            }
            LOG.debug(testFiles[h].getName());
            final SyndFeed feed = input.build(testFiles[h]);
            try {
                output.output(feed, new File("target/" + testFiles[h].getName()));
            } catch (final Exception e) {
                throw new RuntimeException(testFiles[h].getAbsolutePath(), e);
            }
            final SyndFeed feed2 = input.build(new File("target/" + testFiles[h].getName()));
            for (int i = 0; i < feed.getEntries().size(); i++) {
                final SyndEntry entry = feed.getEntries().get(i);
                final SyndEntry entry2 = feed2.getEntries().get(i);
                final GoogleBase base = (GoogleBase) entry.getModule(GoogleBase.URI);
                final GoogleBase base2 = (GoogleBase) entry2.getModule(GoogleBase.URI);
                Assert.assertEquals(testFiles[h].getName(), base, base2);
            }
        }

    }

    public void testMehta() throws Exception {
        final SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("atom_1.0");

        feed.setTitle("Sample Feed (created with Rome)");
        feed.setLink("http://rome.dev.java.net");
        feed.setDescription("This feed has been created using Rome (Java syndication utilities");

        final List<SyndEntry> entries = new ArrayList<SyndEntry>();
        SyndEntry entry;
        SyndContent description;

        entry = new SyndEntryImpl();
        entry.setTitle("Rome v1.0");
        entry.setLink("http://wiki.java.net/bin/view/Javawsxml/Rome01 ");
        description = new SyndContentImpl();
        description.setType("text/plain");
        description.setValue("Initial release of Rome");
        entry.setDescription(description);
        final Vehicle vehicle = new GoogleBaseImpl();
        vehicle.setMake("Honda");
        vehicle.setModel("Insight");

        final Product product = new GoogleBaseImpl();
        product.setCondition("New");
        product.setDeliveryNotes("Insight");

        entries.add(entry);

        feed.setEntries(entries);

        final SyndFeedOutput output = new SyndFeedOutput();
        LOG.debug(output.outputString(feed));

    }
}
