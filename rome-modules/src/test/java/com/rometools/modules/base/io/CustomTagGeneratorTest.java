/*
 * Copyright 2006 Robert Cooper, Temple of the Screaming Penguin
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
 */

package com.rometools.modules.base.io;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.base.CustomTag;
import com.rometools.modules.base.CustomTags;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;

public class CustomTagGeneratorTest extends AbstractTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(CustomTagGeneratorTest.class);

    public CustomTagGeneratorTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(CustomTagGeneratorTest.class);

        return suite;
    }

    public void testGenerate() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();

        final SyndFeed feed = input.build(new File(super.getTestFile("xml/custom-tags-example.xml")));
        final SyndFeedOutput output = new SyndFeedOutput();
        output.output(feed, new File("target/custom-tags-example.xml"));
        final SyndFeed feed2 = input.build(new File("target/custom-tags-example.xml"));

        final List<SyndEntry> entries = feed.getEntries();
        final SyndEntry entry = entries.get(0);
        final CustomTags customTags = (CustomTags) entry.getModule(CustomTags.URI);

        final List<SyndEntry> entries2 = feed2.getEntries();
        final SyndEntry entry2 = entries2.get(0);
        final CustomTags customTags2 = (CustomTags) entry2.getModule(CustomTags.URI);

        final Iterator<CustomTag> it = customTags.getValues().iterator();
        final Iterator<CustomTag> it2 = customTags2.getValues().iterator();
        while (it.hasNext()) {
            final CustomTag tag = it.next();
            final CustomTag tag2 = it2.next();
            LOG.debug("tag1: {}", tag);
            LOG.debug("tag2: {}", tag2);
            Assert.assertEquals(tag, tag2);
        }
    }

}
