/*
 * SlashModuleGeneratorTest.java
 * JUnit based test
 *
 * Created on November 19, 2005, 10:13 PM
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

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.yahooweather.YWeatherModule;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;

public class WeatherGeneratorTest extends AbstractTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherGeneratorTest.class);

    /**
     * @param testName name of the test
     */
    public WeatherGeneratorTest(final String testName) {
        super(testName);
    }

    /**
     * @return test suite
     */
    public static Test suite() {
        return new TestSuite(WeatherGeneratorTest.class);
    }

    /**
     * Test of generate method, of class com.totsp.xml.syndication.base.io.SlashGenerator.
     *
     * @throws Exception on error
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
            LOG.debug("processing" + testFiles[h]);
            final SyndFeed feed = input.build(testFiles[h]);
            output.output(feed, new File("target/" + testFiles[h].getName()));
            final SyndFeed feed2 = input.build(new File("target/" + testFiles[h].getName()));
            final YWeatherModule weather = (YWeatherModule) feed.getModule(YWeatherModule.URI);
            final YWeatherModule weather2 = (YWeatherModule) feed2.getModule(YWeatherModule.URI);
            Assert.assertEquals(testFiles[h].getName(), weather, weather2);
            for (int i = 0; i < feed.getEntries().size(); i++) {
                final SyndEntry entry = feed.getEntries().get(i);
                final SyndEntry entry2 = feed2.getEntries().get(i);
                final YWeatherModule weatherEntry = (YWeatherModule) entry.getModule(YWeatherModule.URI);
                final YWeatherModule weatherEntry2 = (YWeatherModule) entry2.getModule(YWeatherModule.URI);
                assertEquals(testFiles[h].getName(), weatherEntry, weatherEntry2);
            }
        }

    }

}
