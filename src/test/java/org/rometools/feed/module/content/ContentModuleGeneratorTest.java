/*
 * ContentModuleGeneratorTest.java
 * JUnit based test
 *
 * Created on February 2, 2005, 4:15 PM
 */
package org.rometools.feed.module.content;
import org.rometools.feed.module.content.ContentModule;
import org.rometools.feed.module.AbstractTestCase;
import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.*;
import java.io.File;

/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert Cooper</a>
 */
public class ContentModuleGeneratorTest extends AbstractTestCase {
    public ContentModuleGeneratorTest(String testName) {
        super(testName);
    }

    protected void setUp() throws java.lang.Exception {
    }

    protected void tearDown() throws java.lang.Exception {
    }

    public static junit.framework.Test suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite(ContentModuleGeneratorTest.class);

        return suite;
    }

    /**
     * Test of generate method, of class com.totsp.xml.syndication.content.ContentModuleGenerator.
     */
    public void testGenerate() throws Exception {
        System.out.println("testGenerate");

        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader( new File( this.getTestFile("xml/test-rdf.xml" ) ).toURL() ) );
        SyndEntry entry = (SyndEntry) feed.getEntries().get(0);
        ContentModule module = (ContentModule) entry.getModule(ContentModule.URI);
        SyndFeedOutput output = new SyndFeedOutput();
        output.output(feed, new java.io.PrintWriter(System.out));
    }
}
