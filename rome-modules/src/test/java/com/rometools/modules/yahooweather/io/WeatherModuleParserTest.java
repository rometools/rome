/*
 * SlashModuleParserTest.java
 * JUnit based test
 *
 * Created on November 19, 2005, 9:45 PM
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

package com.rometools.modules.yahooweather.io;

import java.io.File;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.cc.io.CCModuleGenerator;
import com.rometools.modules.yahooweather.YWeatherEntryModule;
import com.rometools.modules.yahooweather.YWeatherModule;
import com.rometools.modules.yahooweather.YWeatherModuleImpl;
import com.rometools.modules.yahooweather.io.WeatherModuleParser;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;

public class WeatherModuleParserTest extends AbstractTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(CCModuleGenerator.class);

    public WeatherModuleParserTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(WeatherModuleParserTest.class);

        return suite;
    }

    public void testGetNamespaceUri() {
        final WeatherModuleParser instance = new WeatherModuleParser();
        final String result = instance.getNamespaceUri();
        assertEquals(YWeatherModule.URI, result);
    }

    public void testQuickParse() throws Exception {
        LOG.debug("testParse");
        final SyndFeedInput input = new SyndFeedInput();
        final File testDir = new File(super.getTestFile("xml"));
        final File[] testFiles = testDir.listFiles();
        for (int h = 0; h < testFiles.length; h++) {
            if (!testFiles[h].getName().endsWith(".xml")) {
                continue;
            }

            final SyndFeed feed = input.build(testFiles[h]);
            final List<SyndEntry> entries = feed.getEntries();
            for (int i = 0; i < entries.size(); i++) {
                final SyndEntry entry = entries.get(i);
                LOG.debug("{}", entry.getModules().size());
                for (int j = 0; j < entry.getModules().size(); j++) {
                    LOG.debug("{}", entry.getModules().get(j).getClass());
                    if (entry.getModules().get(j) instanceof YWeatherModule) {
                        final YWeatherModule base = (YWeatherModule) entry.getModules().get(j);
                        assertTrue(((YWeatherEntryModule) base).getForecasts().length > 0);
                        LOG.debug(testFiles[h].getName());

                        final YWeatherEntryModule module2 = new YWeatherModuleImpl();
                        module2.copyFrom(base);
                        assertEquals(((YWeatherEntryModule) base).getForecasts().length, module2.getForecasts().length);

                    }
                }
            }
        }
    }

}
