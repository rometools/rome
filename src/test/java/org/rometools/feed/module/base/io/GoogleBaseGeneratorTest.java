/*
 * GoogleBaseGeneratorTest.java
 * JUnit based test
 *
 * Created on November 17, 2005, 3:40 PM
 */
package org.rometools.feed.module.base.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.Assert;
import org.rometools.feed.module.AbstractTestCase;
import org.rometools.feed.module.base.GoogleBase;
import org.rometools.feed.module.base.GoogleBaseImpl;
import org.rometools.feed.module.base.Product;
import org.rometools.feed.module.base.Vehicle;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;

/**
 *
 * @author rcooper
 */
public class GoogleBaseGeneratorTest extends AbstractTestCase {

    public GoogleBaseGeneratorTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(GoogleBaseGeneratorTest.class);

        return suite;
    }

    /**
     * Test of generate method, of class com.totsp.xml.syndication.base.io.GoogleBaseGenerator.
     */
    public void testGenerate() throws Exception {
        System.out.println("testGenerate");
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeedOutput output = new SyndFeedOutput();
        final File testDir = new File(super.getTestFile("xml"));
        final File[] testFiles = testDir.listFiles();
        for (int h = 0; h < testFiles.length; h++) {
            if (!testFiles[h].getName().endsWith(".xml")) {
                continue;
            }
            System.out.println(testFiles[h].getName());
            final SyndFeed feed = input.build(testFiles[h]);
            try {
                output.output(feed, new File("target/" + testFiles[h].getName()));
            } catch (final Exception e) {
                throw new RuntimeException(testFiles[h].getAbsolutePath(), e);
            }
            final SyndFeed feed2 = input.build(new File("target/" + testFiles[h].getName()));
            for (int i = 0; i < feed.getEntries().size(); i++) {
                final SyndEntry entry = feed.getEntries().get(i);
                final SyndEntry entry2 = feed2.getEntries().get(i);
                final GoogleBase base = (GoogleBase) entry.getModule(GoogleBase.URI);
                final GoogleBase base2 = (GoogleBase) entry2.getModule(GoogleBase.URI);
                Assert.assertEquals(testFiles[h].getName(), base, base2);
            }
        }

    }

    public void testMehta() throws Exception {
        final SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("atom_1.0");

        feed.setTitle("Sample Feed (created with Rome)");
        feed.setLink("http://rome.dev.java.net");
        feed.setDescription("This feed has been created using Rome (Java syndication utilities");

        final List<SyndEntry> entries = new ArrayList<SyndEntry>();
        SyndEntry entry;
        SyndContent description;

        entry = new SyndEntryImpl();
        entry.setTitle("Rome v1.0");
        entry.setLink("http://wiki.java.net/bin/view/Javawsxml/Rome01 ");
        description = new SyndContentImpl();
        description.setType("text/plain");
        description.setValue("Initial release of Rome");
        entry.setDescription(description);
        final Vehicle vehicle = new GoogleBaseImpl();
        vehicle.setMake("Honda");
        vehicle.setModel("Insight");

        final Product product = new GoogleBaseImpl();
        product.setCondition("New");
        product.setDeliveryNotes("Insight");

        // FIXME
        final List modules = new ArrayList();
        modules.add(vehicle);
        modules.add(product);

        entry.setModules(modules);

        entries.add(entry);

        feed.setEntries(entries);

        final SyndFeedOutput output = new SyndFeedOutput();
        System.out.println(output.outputString(feed));

    }
}
