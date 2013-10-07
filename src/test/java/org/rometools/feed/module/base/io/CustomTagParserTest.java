/*
 * CustomTagParserTest.java
 * JUnit based test
 *
 * Created on February 6, 2006, 1:29 AM
 */

package org.rometools.feed.module.base.io;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.rometools.feed.module.AbstractTestCase;
import org.rometools.feed.module.base.CustomTag;
import org.rometools.feed.module.base.CustomTagImpl;
import org.rometools.feed.module.base.CustomTags;
import org.rometools.feed.module.base.types.DateTimeRange;
import org.rometools.feed.module.base.types.FloatUnit;
import org.rometools.feed.module.base.types.IntUnit;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

/**
 * 
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class CustomTagParserTest extends AbstractTestCase {

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
        final List entries = feed.getEntries();
        final SyndEntry entry = (SyndEntry) entries.get(0);
        final CustomTags customTags = (CustomTags) entry.getModule(CustomTags.URI);
        final Iterator it = customTags.getValues().iterator();
        while (it.hasNext()) {
            final CustomTag tag = (CustomTag) it.next();
            System.out.println(tag);
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
