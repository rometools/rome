/*
 * ContentItemTest.java
 * JUnit based test
 *
 * Created on February 2, 2005, 2:50 PM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.rometools.modules.content;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.content.ContentItem;

/**
 * This is all standard property storage testing.
 */
public class ContentItemTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(ContentItemTest.class);

    private final ContentItem item = new ContentItem();

    public ContentItemTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws java.lang.Exception {
    }

    @Override
    protected void tearDown() throws java.lang.Exception {
    }

    public static junit.framework.Test suite() {
        final junit.framework.TestSuite suite = new junit.framework.TestSuite(ContentItemTest.class);

        return suite;
    }

    /**
     * Test of ContentFormat method, of class com.totsp.xml.syndication.content.ContentItem.
     */
    public void testContentFormat() {
        LOG.debug("testContentFormat");
        final String test = "application/xhtml";
        item.setContentFormat(test);
        assertTrue(item.getContentFormat().equals(test));
    }

    /**
     * Test of ContentEncoding method, of class com.totsp.xml.syndication.content.ContentItem.
     */
    public void testContentEncoding() {
        LOG.debug("testContentEncoding");
        final String test = "http://www.w3.org/TR/REC-xml#dt-wellformed";

        item.setContentFormat(test);
        assertTrue(item.getContentFormat().equals(test));
    }

    /**
     * Test of ContentValue method, of class com.totsp.xml.syndication.content.ContentItem.
     */
    public void testContentValue() {
        LOG.debug("testContentValue");
        final String test = "<em>This is<strong>very</em> cool</strong>.";

        item.setContentFormat(test);
        assertTrue(item.getContentFormat().equals(test));
    }

    /**
     * Test of ContentAbout method, of class com.totsp.xml.syndication.content.ContentItem.
     */
    public void testContentAbout() {
        LOG.debug("testContentAbout");
        final String test = "http://example.org/item/content.svg";

        item.setContentFormat(test);
        assertTrue(item.getContentFormat().equals(test));
    }

    /**
     * Test of ContentValueParseType method, of class com.totsp.xml.syndication.content.ContentItem.
     */
    public void testContentValueParseType() {
        LOG.debug("testContentValueParseType");
        final String test = "Literal";

        item.setContentFormat(test);
        assertTrue(item.getContentFormat().equals(test));
    }

    /**
     * Test of ContentValueNamespace method, of class com.totsp.xml.syndication.content.ContentItem.
     */
    public void testContentValueNamespace() {
        LOG.debug("testContentValueNamespace");
        final String test = "http://www.w3.org/1999/xhtml";

        item.setContentFormat(test);
        assertTrue(item.getContentFormat().equals(test));
    }

    /**
     * Test of ContentResource method, of class com.totsp.xml.syndication.content.ContentItem.
     */
    public void testContentResource() {
        LOG.debug("testContentResource");
        final String test = "http://www.w3.org/2000/svg";

        item.setContentResource(test);
        assertTrue(item.getContentResource().equals(test));
    }

    /**
     * this is a stupid test.
     *
     * @todo make better Test of ContentResource method, of class
     *       com.totsp.xml.syndication.content.ContentItem.
     */
    public void testEquals() {
        LOG.debug("testEquals");
        final String test = "http://www.w3.org/2000/svg";

        assertTrue(test.equals(test));
        final ContentItem test2 = ContentModuleImplTest.contentItems.get(0);
        assertTrue(!test.equals(test2));
        final ContentItem test3 = new ContentItem();
        test3.setContentFormat(new String("http://www.w3.org/1999/xhtml"));
        test3.setContentEncoding(new String("http://www.w3.org/TR/REC-xml#dt-wellformed"));
        test3.setContentEncoding(new String("Literal"));
        // test3.setContentValueNamespace(new String("http://www.w3.org/1999/xhtml"));
        test3.setContentValue(new String("<em>This is <strong>very</strong></em> <strong>cool</strong>."));
        // assertEquals( test2, test3 );
    }

}
