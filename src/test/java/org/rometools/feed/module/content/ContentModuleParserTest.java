/*
 * ContentModuleParserTest.java
 * JUnit based test
 *
 * Created on February 2, 2005, 3:35 PM
 */
package org.rometools.feed.module.content;
import org.rometools.feed.module.content.ContentModule;
import org.rometools.feed.module.content.ContentItem;
import org.rometools.feed.module.AbstractTestCase;
import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.File;
import java.util.List;

/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert Cooper</a>
 */
public class ContentModuleParserTest extends AbstractTestCase {
    public ContentModuleParserTest(String testName) {
        super(testName);
    }

    protected void setUp() throws java.lang.Exception {
    }

    protected void tearDown() throws java.lang.Exception {
    }

    public static junit.framework.Test suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite(ContentModuleParserTest.class);

        return suite;
    }

    /**
     * Test of parse method, of class com.sun.syndication.feed.module.content.ContentModuleParser.
     * It will test through the whole ROME framework.
     */
    public void testParse() throws Exception {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(new File( this.getTestFile("xml/test-rdf.xml" ) ).toURL() ));
        SyndEntry entry = (SyndEntry) feed.getEntries().get(0);
        ContentModule module = (ContentModule) entry.getModule(ContentModule.URI);
        List items = module.getContentItems();

        for (int i = 0; i < items.size(); i++) {
            ContentItem item = (ContentItem) ContentModuleImplTest.contentItems.get(i);
            //TODO fix this.
            //assertEquals (item , items.get(i));
        }
    }

}
