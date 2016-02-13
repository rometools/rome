/*
 * MediaModuleTest.java
 * JUnit based test
 *
 * Created on March 29, 2006, 11:49 PM
 */

package com.rometools.modules.mediarss;

import java.io.InputStreamReader;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.mediarss.MediaEntryModule;
import com.rometools.modules.mediarss.MediaModule;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;

/**
 *
 * @author cooper
 */
public class GoogleTest extends AbstractTestCase {

    public GoogleTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(MediaModuleTest.class);

        return suite;
    }

    public static void testGoogleVideo() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new InputStreamReader(new URL("http://video.google.com/videofeed?type=top100new&num=20&output=rss").openStream()));
        for (final Object element : feed.getEntries()) {
            final SyndEntry entry = (SyndEntry) element;
            final MediaEntryModule m = (MediaEntryModule) entry.getModule(MediaModule.URI);
            System.out.print(m);
        }
    }

}
