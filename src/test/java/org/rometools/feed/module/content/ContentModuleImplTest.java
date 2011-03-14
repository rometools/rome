/*
 * ContentModuleImplTest.java
 * JUnit based test
 *
 * Created on February 2, 2005, 2:58 PM
 */

package org.rometools.feed.module.content;
import org.rometools.feed.module.content.ContentModule;
import org.rometools.feed.module.content.ContentItem;
import org.rometools.feed.module.content.ContentModuleImpl;
import junit.framework.*;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert Cooper</a>
 */
public class ContentModuleImplTest extends TestCase {
    
    private ContentModuleImpl module = new ContentModuleImpl();
    public static ArrayList contentItems = new ArrayList();
    static{
        ContentItem item = new ContentItem();
        item.setContentFormat("http://www.w3.org/1999/xhtml");
        item.setContentEncoding( "http://www.w3.org/TR/REC-xml#dt-wellformed");
        //item.setContentValueNamespaces("http://www.w3.org/1999/xhtml");
        item.setContentValue("<em>This is <strong>very</strong></em> <strong>cool</strong>.");
        item.setContentValueParseType( "Literal");
        
        contentItems.add( item );
        
        item = new ContentItem();
        item.setContentFormat("http://www.w3.org/TR/html4/");
        item.setContentValue("<em>This is<strong>very</em> cool</strong>.");
        
        contentItems.add( item );
        
        item = new ContentItem();
        item.setContentAbout("http://example.org/item/content-here.txt");
        item.setContentFormat("http://www.isi.edu/in-notes/iana/assignments/media-types/text/plain");
        item.setContentValue("This is &gt;very cool&lt;.");
        
        contentItems.add( item );
        
        item = new ContentItem();
        item.setContentAbout("http://example.org/item/content.svg");
        item.setContentResource("http://www.w3.org/2000/svg");
        
        contentItems.add( item );
    }
    
    public ContentModuleImplTest(String testName) {
        super(testName);
    }

    protected void setUp() throws java.lang.Exception {
        
    }

    protected void tearDown() throws java.lang.Exception {
    }

    public static junit.framework.Test suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite(ContentModuleImplTest.class);
        
        return suite;
    }

    /**
     * Test of getEncodeds method, of class com.totsp.xml.syndication.content.ContentModuleImpl.
     */
    public void testEncodeds() {
        ArrayList encodeds = new ArrayList();
        encodeds.add( "Foo" );
        encodeds.add( "Bar" );
        encodeds.add( "Baz" );
        module.setEncodeds( encodeds );
        List check = module.getEncodeds();
        assertTrue( check.equals( encodeds ));
        
    }
   
    
    /**
     * Test of getInterface method, of class com.totsp.xml.syndication.content.ContentModuleImpl.
     */
    public void testInterface() {
        System.out.println("testInterface");
        assertTrue( module.getInterface().equals( ContentModule.class));
    }

    /**
     * Test of getContentItems method, of class com.totsp.xml.syndication.content.ContentModuleImpl.
     */
    public void testContentItems() {
        System.out.println("testContentItems");
        module.setContentItems( contentItems );
        assertTrue( module.getContentItems().equals( contentItems ) );
    }

 

    /**
     * Test of getContents method, of class com.totsp.xml.syndication.content.ContentModuleImpl.
     */
    public void testContents() {
        System.out.println("testContents");
        ArrayList contents = new ArrayList();
        contents.add( "Foo" );
        contents.add( "Bar" );
        contents.add( "Baz" );
        module.setContents( contents );
        List check = module.getContents();
        assertTrue( check.equals( contents ));
    }

    /**
     * Test of copyFrom method, of class com.totsp.xml.syndication.content.ContentModuleImpl.
     */
    public void testCopyFrom() {
        System.out.println("testCopyFrom");
        ContentModule test = new ContentModuleImpl();
        test.copyFrom( module );
        assertTrue( test.getContentItems().equals( module.getContentItems() ) &
                test.getContents().equals( module.getContents() ) &
                test.getEncodeds().equals( module.getEncodeds() ) );
    }
   
    
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
    
}
