/*
 * ContentModuleParserTest.java
 * JUnit based test
 *
 * Created on February 2, 2005, 3:35 PM
 */
package org.rometools.feed.module.content;

import java.io.File;
import java.util.List;

import org.rometools.feed.module.AbstractTestCase;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert Cooper</a>
 */
public class ContentModuleParserTest extends AbstractTestCase {
    public ContentModuleParserTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws java.lang.Exception {
    }

    @Override
    protected void tearDown() throws java.lang.Exception {
    }

    public static junit.framework.Test suite() {
        final junit.framework.TestSuite suite = new junit.framework.TestSuite(ContentModuleParserTest.class);

        return suite;
    }

    /**
     * Test of parse method, of class com.sun.syndication.feed.module.content.ContentModuleParser. It will test through the whole ROME framework.
     */
    public void testParse() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("xml/test-rdf.xml")).toURL()));
        final SyndEntry entry = feed.getEntries().get(0);
        final ContentModule module = (ContentModule) entry.getModule(ContentModule.URI);
        final List items = module.getContentItems();

        for (int i = 0; i < items.size(); i++) {
            final ContentItem item = (ContentItem) ContentModuleImplTest.contentItems.get(i);
            // TODO fix this.
            // assertEquals (item , items.get(i));
        }
    }

}
