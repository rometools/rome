/*
 * ContentModuleParserTest.java
 * JUnit based test
 *
 * Created on February 2, 2005, 3:35 PM
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

import java.io.File;
import java.util.List;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.content.ContentItem;
import com.rometools.modules.content.ContentModule;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

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
     * Test of parse method, of class com.rometools.rome.feed.module.content.ContentModuleParser.
     * It will test through the whole ROME framework.
     */
    public void testParse() throws Exception {

        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("xml/test-rdf.xml")).toURI().toURL()));
        final SyndEntry entry = feed.getEntries().get(0);
        final ContentModule module = (ContentModule) entry.getModule(ContentModule.URI);
        final List<ContentItem> items = module.getContentItems();

        for (int i = 0; i < items.size(); i++) {
            // FIXME
            // final ContentItem item = ContentModuleImplTest.contentItems.get(i);
            // assertEquals (item , items.get(i));
        }

    }

}
