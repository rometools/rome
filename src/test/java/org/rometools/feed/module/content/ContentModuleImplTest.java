/*
 * ContentModuleImplTest.java
 * JUnit based test
 *
 * Created on February 2, 2005, 2:58 PM
 */

package org.rometools.feed.module.content;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * 
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert Cooper</a>
 */
public class ContentModuleImplTest extends TestCase {

    private final ContentModuleImpl module = new ContentModuleImpl();
    public static ArrayList contentItems = new ArrayList();
    static {
        ContentItem item = new ContentItem();
        item.setContentFormat("http://www.w3.org/1999/xhtml");
        item.setContentEncoding("http://www.w3.org/TR/REC-xml#dt-wellformed");
        // item.setContentValueNamespaces("http://www.w3.org/1999/xhtml");
        item.setContentValue("<em>This is <strong>very</strong></em> <strong>cool</strong>.");
        item.setContentValueParseType("Literal");

        contentItems.add(item);

        item = new ContentItem();
        item.setContentFormat("http://www.w3.org/TR/html4/");
        item.setContentValue("<em>This is<strong>very</em> cool</strong>.");

        contentItems.add(item);

        item = new ContentItem();
        item.setContentAbout("http://example.org/item/content-here.txt");
        item.setContentFormat("http://www.isi.edu/in-notes/iana/assignments/media-types/text/plain");
        item.setContentValue("This is &gt;very cool&lt;.");

        contentItems.add(item);

        item = new ContentItem();
        item.setContentAbout("http://example.org/item/content.svg");
        item.setContentResource("http://www.w3.org/2000/svg");

        contentItems.add(item);
    }

    public ContentModuleImplTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws java.lang.Exception {

    }

    @Override
    protected void tearDown() throws java.lang.Exception {
    }

    public static junit.framework.Test suite() {
        final junit.framework.TestSuite suite = new junit.framework.TestSuite(ContentModuleImplTest.class);

        return suite;
    }

    /**
     * Test of getEncodeds method, of class com.totsp.xml.syndication.content.ContentModuleImpl.
     */
    public void testEncodeds() {
        final ArrayList encodeds = new ArrayList();
        encodeds.add("Foo");
        encodeds.add("Bar");
        encodeds.add("Baz");
        module.setEncodeds(encodeds);
        final List check = module.getEncodeds();
        assertTrue(check.equals(encodeds));

    }

    /**
     * Test of getInterface method, of class com.totsp.xml.syndication.content.ContentModuleImpl.
     */
    public void testInterface() {
        System.out.println("testInterface");
        assertTrue(module.getInterface().equals(ContentModule.class));
    }

    /**
     * Test of getContentItems method, of class com.totsp.xml.syndication.content.ContentModuleImpl.
     */
    public void testContentItems() {
        System.out.println("testContentItems");
        module.setContentItems(contentItems);
        assertTrue(module.getContentItems().equals(contentItems));
    }

    /**
     * Test of getContents method, of class com.totsp.xml.syndication.content.ContentModuleImpl.
     */
    public void testContents() {
        System.out.println("testContents");
        final ArrayList contents = new ArrayList();
        contents.add("Foo");
        contents.add("Bar");
        contents.add("Baz");
        module.setContents(contents);
        final List check = module.getContents();
        assertTrue(check.equals(contents));
    }

    /**
     * Test of copyFrom method, of class com.totsp.xml.syndication.content.ContentModuleImpl.
     */
    public void testCopyFrom() {
        System.out.println("testCopyFrom");
        final ContentModule test = new ContentModuleImpl();
        test.copyFrom(module);
        assertTrue(test.getContentItems().equals(module.getContentItems()) & test.getContents().equals(module.getContents())
                & test.getEncodeds().equals(module.getEncodeds()));
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}

}
