/*
 * SlashModuleParserTest.java
 * JUnit based test
 *
 * Created on November 19, 2005, 9:45 PM
 */

package org.rometools.feed.module.yahooweather.io;

import java.io.File;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.rometools.feed.module.AbstractTestCase;
import org.rometools.feed.module.yahooweather.YWeatherEntryModule;
import org.rometools.feed.module.yahooweather.YWeatherModule;
import org.rometools.feed.module.yahooweather.YWeatherModuleImpl;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class WeatherModuleParserTest extends AbstractTestCase {

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
        System.out.println("testParse");
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
                System.out.println(entry.getModules().size());
                for (int j = 0; j < entry.getModules().size(); j++) {
                    System.out.println(entry.getModules().get(j).getClass());
                    if (entry.getModules().get(j) instanceof YWeatherModule) {
                        final YWeatherModule base = (YWeatherModule) entry.getModules().get(j);
                        assertTrue(((YWeatherEntryModule) base).getForecasts().length > 0);
                        System.out.println(testFiles[h].getName());
                        System.out.println(super.beanToString(base, false));

                        final YWeatherEntryModule module2 = new YWeatherModuleImpl();
                        module2.copyFrom(base);
                        assertEquals(((YWeatherEntryModule) base).getForecasts().length, module2.getForecasts().length);

                    }
                }
            }
        }
    }

}
