/*
 * SSEParserTest.java
 * JUnit based test
 *
 * Created on August 2, 2005, 1:30 PM
 */
package com.rometools.modules.atom;

import java.io.File;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.atom.io.AtomModuleGenerator;
import com.rometools.modules.atom.modules.AtomLinkModule;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test to verify correctness of SSE subproject.
 */
public class AtomLinkTest extends AbstractTestCase {
    public static final String href = "http://test.com";
    public static final String type = "application/rss+xml";
    public static final String rel = "self";

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
        Link link = atomLinkModule.getLink();

        assertEquals(href, link.getHref());
        assertEquals(rel, link.getRel());
        assertEquals(type, link.getType());

    }
}
