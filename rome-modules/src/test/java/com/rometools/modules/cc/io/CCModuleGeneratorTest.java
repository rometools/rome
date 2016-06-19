/*
 * CCModuleGeneratorTest.java
 * JUnit based test
 *
 * Created on November 20, 2005, 9:48 PM
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

import junit.framework.Test;
import junit.framework.TestSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.cc.CreativeCommons;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;

public class CCModuleGeneratorTest extends AbstractTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(CCModuleGeneratorTest.class);

    public CCModuleGeneratorTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(CCModuleGeneratorTest.class);

        return suite;
    }

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
            // if( !feed.getFeedType().equals("rss_1.0"))
            {
                feed.setFeedType("rss_2.0");
                if (feed.getDescription() == null) {
                    feed.setDescription("test file");
                }
                output.output(feed, new File("target/" + testFiles[h].getName()));
                final SyndFeed feed2 = input.build(new File("target/" + testFiles[h].getName()));
                for (int i = 0; i < feed.getEntries().size(); i++) {
                    // FIXME
                    // final SyndEntry entry = feed.getEntries().get(i);
                    final SyndEntry entry2 = feed2.getEntries().get(i);
                    // / FIXME
                    // final CreativeCommons base = (CreativeCommons)
                    // entry.getModule(CreativeCommons.URI);
                    final CreativeCommons base2 = (CreativeCommons) entry2.getModule(CreativeCommons.URI);
                    LOG.debug("{}", base2);
                    // FIXME
                    // if( base != null)
                    // this.assertEquals( testFiles[h].getName(), base.getLicenses(),
                    // base2.getLicenses() );
                }
            }
        }

    }

}
