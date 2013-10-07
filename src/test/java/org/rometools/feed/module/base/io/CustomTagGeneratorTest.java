/*
 * CustomTagGeneratorTest.java
 * JUnit based test
 *
 * Created on February 6, 2006, 1:58 AM
 */

package org.rometools.feed.module.base.io;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.rometools.feed.module.AbstractTestCase;
import org.rometools.feed.module.base.CustomTag;
import org.rometools.feed.module.base.CustomTags;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;

/**
 * 
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class CustomTagGeneratorTest extends AbstractTestCase {

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

        final List entries = feed.getEntries();
        final SyndEntry entry = (SyndEntry) entries.get(0);
        final CustomTags customTags = (CustomTags) entry.getModule(CustomTags.URI);

        final List entries2 = feed2.getEntries();
        final SyndEntry entry2 = (SyndEntry) entries2.get(0);
        final CustomTags customTags2 = (CustomTags) entry2.getModule(CustomTags.URI);

        final Iterator it = customTags.getValues().iterator();
        final Iterator it2 = customTags2.getValues().iterator();
        while (it.hasNext()) {
            final CustomTag tag = (CustomTag) it.next();
            final CustomTag tag2 = (CustomTag) it2.next();
            System.out.println("tag1:" + tag);
            System.out.println("tag2:" + tag2);
            Assert.assertEquals(tag, tag2);
        }
    }

}
