/*
 * ModuleParserTest.java
 * JUnit based test
 *
 * Created on April 29, 2006, 7:00 PM
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

package com.rometools.modules.sle.io;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.jdom2.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.sle.SimpleListExtension;
import com.rometools.modules.sle.SleEntry;
import com.rometools.modules.sle.types.Group;
import com.rometools.modules.sle.types.Sort;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;

public class ModuleParserTest extends AbstractTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(ModuleParserTest.class);

    public ModuleParserTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(ModuleParserTest.class);

        return suite;
    }

    /**
     * Test of parse method, of class com.rometools.rome.feed.module.sle.io.ModuleParser.
     */
    public void testParse() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new File(super.getTestFile("data/bookexample.xml")));
        final SimpleListExtension sle = (SimpleListExtension) feed.getModule(SimpleListExtension.URI);

        assertEquals("list", sle.getTreatAs());
        final Group[] groups = sle.getGroupFields();
        assertEquals("genre", groups[0].getElement());
        assertEquals("Genre", groups[0].getLabel());
        final Sort[] sorts = sle.getSortFields();
        assertEquals("Relevance", sorts[0].getLabel());
        assertTrue(sorts[0].getDefaultOrder());
        assertEquals(sorts[1].getNamespace(), Namespace.getNamespace("http://www.example.com/book"));
        assertEquals(sorts[1].getDataType(), Sort.DATE_TYPE);
        assertEquals(sorts[1].getElement(), "firstedition");
        final SyndEntry entry = feed.getEntries().get(0);
        final SleEntry sleEntry = (SleEntry) entry.getModule(SleEntry.URI);
        LOG.debug("{}", sleEntry);
        LOG.debug("getGroupByElement");
        LOG.debug("{}", sleEntry.getGroupByElement(groups[0]));
        LOG.debug("getSortByElement");
        LOG.debug("{}", sleEntry.getSortByElement(sorts[0]));
    }

}
