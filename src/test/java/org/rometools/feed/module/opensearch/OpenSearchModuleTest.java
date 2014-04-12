/*
 * OpenSearchModuleTest.java
 * JUnit based test
 *
 * Created on April 25, 2006, 8:56 PM
 */

package org.rometools.feed.module.opensearch;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.rometools.feed.module.AbstractTestCase;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;

/**
 *
 * @author cooper
 */
public class OpenSearchModuleTest extends AbstractTestCase {

    public OpenSearchModuleTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(OpenSearchModuleTest.class);

        return suite;
    }

    public void testEndToEnd() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();
        final File test = new File(super.getTestFile("os"));
        final File[] files = test.listFiles();
        for (int j = 0; j < files.length; j++) {
            if (!files[j].getName().endsWith(".xml")) {
                continue;
            }
            final SyndFeed feed = input.build(files[j]);
            final Module m = feed.getModule(OpenSearchModule.URI);
            final SyndFeedOutput output = new SyndFeedOutput();
            final File outfile = new File("target/" + files[j].getName());
            output.output(feed, outfile);
            final SyndFeed feed2 = input.build(outfile);
            assertEquals(m, feed2.getModule(OpenSearchModule.URI));
        }
    }

}
