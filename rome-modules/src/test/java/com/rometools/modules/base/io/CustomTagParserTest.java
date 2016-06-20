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
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.base.CustomTag;
import com.rometools.modules.base.CustomTagImpl;
import com.rometools.modules.base.CustomTags;
import com.rometools.modules.base.types.DateTimeRange;
import com.rometools.modules.base.types.FloatUnit;
import com.rometools.modules.base.types.IntUnit;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;

public class CustomTagParserTest extends AbstractTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(CustomTagParserTest.class);

    public CustomTagParserTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(CustomTagParserTest.class);

        return suite;
    }

    public void testParse() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();

        final SyndFeed feed = input.build(new File(super.getTestFile("xml/custom-tags-example.xml")));
        final List<SyndEntry> entries = feed.getEntries();
        final SyndEntry entry = entries.get(0);
        final CustomTags customTags = (CustomTags) entry.getModule(CustomTags.URI);
        final Iterator<CustomTag> it = customTags.getValues().iterator();
        while (it.hasNext()) {
            final CustomTag tag = it.next();
            LOG.debug("{}", tag);
            if (tag.getName().equals("language_skills")) {
                Assert.assertEquals("Fluent in English and German", tag.getValue());
            }
            if (tag.getName().equals("prior_experience_years")) {
                Assert.assertEquals(new Integer(5), tag.getValue());
            } else if (tag.getName().equals("start_date")) {
                final Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(0);
                cal.set(2005, 10, 15, 0, 0, 0);
                Assert.assertEquals(cal.getTime(), tag.getValue());
            } else if (tag.getName().equals("test_url")) {
                Assert.assertEquals(new URL("http://www.screaming-penguin.com"), tag.getValue());
            } else if (tag.getName().equals("test_boolean")) {
                Assert.assertEquals(new Boolean(true), tag.getValue());
            } else if (tag.getName().equals("test_intUnit")) {
                Assert.assertEquals(new IntUnit(25, "horses"), tag.getValue());
            } else if (tag.getName().equals("test_floatUnit")) {
                Assert.assertEquals(new FloatUnit((float) 2.5, "cows"), tag.getValue());
            } else if (tag.getName().equals("test_location")) {
                Assert.assertEquals(new CustomTagImpl.Location("125 Main St, Sometown, GA"), tag.getValue());
            } else if (tag.getName().equals("test_dateRange")) {
                final Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(0);
                cal.set(2005, 06, 04, 20, 0, 0);
                final Date start = cal.getTime();
                cal.set(2005, 06, 04, 23, 0, 0);
                final DateTimeRange dtr = new DateTimeRange(start, cal.getTime());
                Assert.assertEquals(dtr, tag.getValue());
            }
        }
    }

}
