/*
 * ModuleParserRSS1Test.java
 * JUnit based test
 *
 * Created on November 20, 2005, 7:05 PM
 */

package org.rometools.feed.module.cc.io;

import java.io.File;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.rometools.feed.module.AbstractTestCase;
import org.rometools.feed.module.cc.CreativeCommons;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class ModuleParserTest extends AbstractTestCase {

    public ModuleParserTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(ModuleParserTest.class);

        return suite;
    }

    public void testNull() {
        return;
    }

    public void atestParse() throws Exception {

        final SyndFeedInput input = new SyndFeedInput();
        final File testDir = new File(super.getTestFile("test/xml"));
        final File[] testFiles = testDir.listFiles();
        for (int h = 0; h < testFiles.length; h++) {
            if (!testFiles[h].getName().endsWith(".xml")) {
                continue;
            }
            System.out.println(testFiles[h].getName());
            final SyndFeed feed = input.build(testFiles[h]);
            final List entries = feed.getEntries();
            final CreativeCommons fMod = (CreativeCommons) feed.getModule(CreativeCommons.URI);
            System.out.println(fMod);
            for (int i = 0; i < entries.size(); i++) {
                final SyndEntry entry = (SyndEntry) entries.get(i);
                final CreativeCommons eMod = (CreativeCommons) entry.getModule(CreativeCommons.URI);
                System.out.println("\nEntry:");
                System.out.println(eMod);
            }
        }
    }

}
