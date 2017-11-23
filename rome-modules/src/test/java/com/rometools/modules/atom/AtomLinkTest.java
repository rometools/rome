/*
 * SSEParserTest.java
 * JUnit based test
 *
 * Created on August 2, 2005, 1:30 PM
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
package com.rometools.modules.atom;

import java.io.File;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.atom.io.AtomModuleGenerator;
import com.rometools.modules.atom.modules.AtomLinkModule;
import com.rometools.modules.atom.modules.AtomLinkModuleImpl;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import junit.framework.Test;
import junit.framework.TestSuite;

import java.io.File;
import java.util.List;

/**
 * Test to verify correctness of SSE subproject.
 */
public class AtomLinkTest extends AbstractTestCase {
    public static final String[] href = {"http://test.com", "http://test.com/alt"};
    public static final String[] type = {"application/rss+xml", "application/rss+xml"};
    public static final String[] rel = {"self", "alternate"};

    public AtomLinkTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        return new TestSuite(AtomLinkTest.class);
    }

    /**
     * Test of getNamespaceUri method, of class com.rometools.rome.feed.module.sse.SSE091
     */
    public void testGetNamespaceUri() {
        assertEquals("Namespace", AtomLinkModule.URI, new AtomModuleGenerator().getNamespaceUri());
    }

    public void test() throws Exception {

        final File testdata = new File(super.getTestFile("atom/atom.xml"));
        final SyndFeed feed = new SyndFeedInput().build(testdata);

        final AtomLinkModule atomLinkModule = (AtomLinkModule) feed.getModule(AtomLinkModule.URI);
        List<Link> links = atomLinkModule.getLinks();
        for (int i = 0; i < links.size(); i++) {
            Link link = links.get(i);
            assertEquals(href[i], link.getHref());
            assertEquals(rel[i], link.getRel());
            assertEquals(type[i], link.getType());
        }

    }
    
    public void testSetLinkShouldWork() {
        final AtomLinkModuleImpl atomLinkModule = new AtomLinkModuleImpl();
        final Link link = new Link();
        atomLinkModule.setLink(link);
        assertEquals(link, atomLinkModule.getLink());
    }
}

