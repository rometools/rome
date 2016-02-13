/*
 * SSEParserTest.java
 * JUnit based test
 *
 * Created on August 2, 2005, 1:30 PM
 */
package com.rometools.modules.atom;

import com.rometools.modules.AbstractTestCase;
import com.rometools.modules.atom.io.AtomModuleGenerator;
import com.rometools.modules.atom.modules.AtomLinkModule;
import com.rometools.modules.sle.SimpleListExtension;
import com.rometools.modules.sse.SSE091Generator;
import com.rometools.modules.sse.modules.Conflict;
import com.rometools.modules.sse.modules.History;
import com.rometools.modules.sse.modules.SSEModule;
import com.rometools.modules.sse.modules.Sync;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;
import com.rometools.rome.io.impl.DateParser;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.net.URL;
import java.util.*;

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
