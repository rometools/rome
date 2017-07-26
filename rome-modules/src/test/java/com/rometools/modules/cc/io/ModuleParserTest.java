/*
 * ModuleParserRSS1Test.java
 * JUnit based test
 *
 * Created on November 20, 2005, 7:05 PM
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

package com.rometools.modules.cc.io;

import java.io.File;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.cc.CreativeCommons;
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

    public void testNull() {
        return;
    }

    public void atestParse() throws Exception {

        final SyndFeedInput input = new SyndFeedInput();
        final File testDir = new File(super.getTestFile("test/xml"));
        final File[] testFiles = testDir.listFiles();
        for (int h = 0; h < testFiles.length; h++) {
            if (!testFiles[h].getName().endsWith(".xml")) {
                continue;
            }
            LOG.debug(testFiles[h].getName());
            final SyndFeed feed = input.build(testFiles[h]);
            final List<SyndEntry> entries = feed.getEntries();
            final CreativeCommons fMod = (CreativeCommons) feed.getModule(CreativeCommons.URI);
            LOG.debug("{}", fMod);
            for (int i = 0; i < entries.size(); i++) {
                final SyndEntry entry = entries.get(i);
                final CreativeCommons eMod = (CreativeCommons) entry.getModule(CreativeCommons.URI);
                LOG.debug("\nEntry:");
                LOG.debug("{}", eMod);
            }
        }
    }

}
