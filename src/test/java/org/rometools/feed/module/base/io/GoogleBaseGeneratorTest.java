/*
 * GoogleBaseGeneratorTest.java
 * JUnit based test
 *
 * Created on November 17, 2005, 3:40 PM
 */
package org.rometools.feed.module.base.io;

import org.rometools.feed.module.AbstractTestCase;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import org.rometools.feed.module.base.GoogleBase;
import org.rometools.feed.module.base.GoogleBaseImpl;
import org.rometools.feed.module.base.Product;
import org.rometools.feed.module.base.Vehicle;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import junit.framework.*;

/**
 *
 * @author rcooper
 */
public class GoogleBaseGeneratorTest extends AbstractTestCase {

    public GoogleBaseGeneratorTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(GoogleBaseGeneratorTest.class);

        return suite;
    }

    /**
     * Test of generate method, of class com.totsp.xml.syndication.base.io.GoogleBaseGenerator.
     */
    public void testGenerate() throws Exception {
        System.out.println("testGenerate");
        SyndFeedInput input = new SyndFeedInput();
        SyndFeedOutput output = new SyndFeedOutput();
        File testDir = new File(super.getTestFile("xml"));
        File[] testFiles = testDir.listFiles();
        for (int h = 0; h < testFiles.length; h++) {
            if (!testFiles[h].getName().endsWith(".xml")) {
                continue;
            }
            System.out.println(testFiles[h].getName());
            SyndFeed feed = input.build(testFiles[h]);
            try {
                output.output(feed, new File("target/" + testFiles[h].getName()));
            } catch (Exception e) {
                throw new RuntimeException(testFiles[h].getAbsolutePath(), e);
            }
            SyndFeed feed2 = input.build(new File("target/" + testFiles[h].getName()));
            for (int i = 0; i < feed.getEntries().size(); i++) {
                SyndEntry entry = (SyndEntry) feed.getEntries().get(i);
                SyndEntry entry2 = (SyndEntry) feed2.getEntries().get(i);
                GoogleBase base = (GoogleBase) entry.getModule(GoogleBase.URI);
                GoogleBase base2 = (GoogleBase) entry2.getModule(GoogleBase.URI);
                this.assertEquals(testFiles[h].getName(), base, base2);
            }
        }

    }

    public void testMehta() throws Exception {
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("atom_1.0");

        feed.setTitle("Sample Feed (created with Rome)");
        feed.setLink("http://rome.dev.java.net");
        feed.setDescription("This feed has been created using Rome (Java syndication utilities");

        List entries = new ArrayList();
        SyndEntry entry;
        SyndContent description;

        entry = new SyndEntryImpl();
        entry.setTitle("Rome v1.0");
        entry.setLink("http://wiki.java.net/bin/view/Javawsxml/Rome01 ");
        description = new SyndContentImpl();
        description.setType("text/plain");
        description.setValue("Initial release of Rome");
        entry.setDescription(description);
        Vehicle vehicle = new GoogleBaseImpl();
        vehicle.setMake("Honda");
        vehicle.setModel("Insight");

        Product product = new GoogleBaseImpl();
        product.setCondition("New");
        product.setDeliveryNotes("Insight");

        List modules = new ArrayList();
        modules.add(vehicle);
        modules.add(product);


        entry.setModules(modules);


        entries.add(entry);

        feed.setEntries(entries);

        SyndFeedOutput output = new SyndFeedOutput();
        System.out.println(output.outputString(feed));



    }
}
