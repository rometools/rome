/*
 * SSEParserTest.java
 * JUnit based test
 *
 * Created on August 2, 2005, 1:30 PM
 */
package org.rometools.feed.module.sse;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.rometools.feed.module.AbstractTestCase;
import org.rometools.feed.module.sse.modules.Conflict;
import org.rometools.feed.module.sse.modules.History;
import org.rometools.feed.module.sse.modules.SSEModule;
import org.rometools.feed.module.sse.modules.Sync;

import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import com.sun.syndication.io.XmlReader;
import com.sun.syndication.io.impl.DateParser;

/**
 * Test to verify correctness of SSE subproject.
 */
public class SSEParserTest extends AbstractTestCase {
    public SSEParserTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        return new TestSuite(SSEParserTest.class);
    }

    /**
     * Test of getNamespaceUri method, of class com.sun.syndication.feed.module.sse.SSE091
     */
    public void testGetNamespaceUri() {
        assertEquals("Namespace", SSEModule.SSE_SCHEMA_URI, new SSE091Generator().getNamespaceUri());
    }

    public void xtestParseGenerateV5() throws Exception {
        final URL feedURL = new File(getTestFile("xml/v/v5.xml")).toURL();
        // parse the document for comparison
        final SAXBuilder builder = new SAXBuilder();
        final Document directlyBuilt = builder.build(feedURL);

        // generate the feed back into a document
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed inputFeed = input.build(new XmlReader(feedURL));

        final SyndFeedOutput output = new SyndFeedOutput();
        final Document parsedAndGenerated = output.outputJDom(inputFeed);

        // XMLOutputter outputter = new XMLOutputter();
        // outputter.setFormat(Format.getPrettyFormat());
        // outputter.output(directlyBuilt, new FileOutputStream("c:\\cygwin\\tmp\\sync-direct.xml"));
        // outputter.output(parsedAndGenerated, new FileOutputStream("c:\\cygwin\\tmp\\sync-pg.xml"));

        assertDocumentsEqual(directlyBuilt, parsedAndGenerated);
    }

    // TODO: probably should rip this out and use xunit instead
    private void assertDocumentsEqual(final Document one, final Document two) {
        assertEqualElements(one.getRootElement(), two.getRootElement());
    }

    private void assertEqualElements(final Element one, final Element two) {
        if (one == two || bothNull(one, two)) {
            return;
        }

        assertNullEqual("elements not null equal", one, two);
        assertEqualAttributes(one, two);
        asserEqualContent(one, two);
    }

    private void assertNullEqual(final String mesg, final Object one, final Object two) {
        assertTrue(mesg, nullEqual(one, two));
    }

    private boolean nullEqual(final Object one, final Object two) {
        return one == null && two == null || one != null && two != null;
    }

    private boolean bothNull(final Object one, final Object two) {
        return one == null && two == null;
    }

    private void assertEqualAttributes(final Element one, final Element two) {
        assertTrue(equalAttributes(one, two, true));
    }

    private boolean equalAttributes(final Element one, final Element two, final boolean doAssert) {
        final List attrs1 = one.getAttributes();
        final List attrs2 = two.getAttributes();

        boolean equal = nullEqual(attrs1, attrs2);
        if (doAssert) {
            assertTrue("not null equal", equal);
        }

        if (bothNull(attrs1, attrs2)) {
            return true;
        }

        if (equal) {
            for (final Iterator oneIter = attrs1.iterator(); oneIter.hasNext();) {
                // compare the attributes in an order insensitive way
                final Attribute a1 = (Attribute) oneIter.next();
                final Attribute a2 = findAttribute(a1.getName(), attrs2);

                equal = a2 != null;
                if (!equal) {
                    if (doAssert) {
                        assertNotNull("no matching attribute for: " + one.getName() + "." + a1.getName() + "=" + a1.getValue(), a2);
                    }
                    break;
                }

                Object av1 = a1.getValue();
                Object av2 = a2.getValue();

                equal = nullEqual(av1, av2);
                if (!equal && doAssert) {
                    assertNullEqual("attribute values not null equal: " + av1 + " != " + av2, av1, av2);
                }

                if (!bothNull(av1, av2)) {
                    final String a1Name = a1.getName();

                    // this test is brittle, but its comprehensive
                    if ("until".equals(a1Name) || "since".equals(a1Name) || "when".equals(a1Name)) {
                        av1 = DateParser.parseRFC822((String) av1, Locale.US);
                        av2 = DateParser.parseRFC822((String) av2, Locale.US);
                    }

                    assertTrue("unequal attributes:" + one.getName() + "." + a1.getName() + ": " + av1 + " != " + av2, av1.equals(av2));
                }
            }
        }
        return equal;
    }

    private Attribute findAttribute(final String name, final List attrs) {
        for (final Iterator attrIter = attrs.iterator(); attrIter.hasNext();) {
            final Attribute a = (Attribute) attrIter.next();
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    private void asserEqualContent(final Element one, final Element two) {
        final List oneContent = one.getContent();
        final List twoContent = two.getContent();
        if (bothNull(oneContent, twoContent)) {
            return;
        }

        assertNullEqual("missing compare content", oneContent, twoContent);
        assertEqualAttributes(one, two);

        // scan through the content to make sure each element is equal
        for (final Iterator oneIter = oneContent.iterator(); oneIter.hasNext();) {
            final Object content1 = oneIter.next();
            if (content1 instanceof Element) {
                final Element e1 = (Element) content1;

                boolean foundEqual = false;
                final List messages = new ArrayList();
                for (final Iterator twoIter = twoContent.iterator(); twoIter.hasNext();) {
                    final Object o = twoIter.next();
                    if (o instanceof Element) {
                        final Element e2 = (Element) o;

                        try {
                            // have to check all elements to be order insensitive
                            if (e1.getName().equals(e2.getName()) && equalAttributes(e1, e2, false)) {
                                assertEqualElements(e1, e2);
                                foundEqual = true;
                                messages.clear();
                                break;
                            }
                        } catch (final Error e) {
                            messages.add(e.getMessage());
                        }
                    }
                }

                // if (!foundEqual) {
                // // show accumulated error messages
                // for (Iterator mesgIter = messages.iterator(); mesgIter.hasNext();) {
                // System.out.println((String) mesgIter.next());
                // }
                // }

                // look for the content in the other tree
                assertTrue("could not find matching element for: " + one.getName(), foundEqual);
            }
        }
    }

    /**
     * Assure v5 file parsed correctly.
     * 
     * @throws Exception
     */
    public void xtestV5() throws Exception {
        final File feed = new File(getTestFile("xml/v/v5.xml"));
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed syndfeed = input.build(new XmlReader(feed.toURL()));

        final List entries = syndfeed.getEntries();
        final Iterator it = entries.iterator();

        for (int id = 101; it.hasNext() && id <= 113; id++) {
            final SyndEntry entry = (SyndEntry) it.next();
            final Sync sync = (Sync) entry.getModule(SSEModule.SSE_SCHEMA_URI);
            assertEquals(String.valueOf(id), sync.getId());

            final History history = sync.getHistory();
            assertNotNull(history);

            final Date when = history.getWhen();
            assertNotNull(when);
            final Date testDate = DateParser.parseRFC822("Fri, 6 Jan 2006 19:24:09 GMT", Locale.US);
            assertEquals(testDate, when);
        }

        for (int ep = 1; ep <= 2; ep++) {
            for (int i = 100; i < 102; i++) {
                final SyndEntry entry = (SyndEntry) it.next();
                final Sync sync = (Sync) entry.getModule(SSEModule.SSE_SCHEMA_URI);
                final String id = sync.getId();
                assertEquals("ep" + ep + "." + i, id);

                if (id.equals("ep1.100")) {
                    final List conflicts = sync.getConflicts();
                    assertNotNull(conflicts);

                    final Conflict conflict = (Conflict) conflicts.get(0);
                    final Item conflictItem = conflict.getItem();

                    assertEquals(conflictItem.getTitle(), "Phish - Coventry Live (the last *good* concert)");
                    assertEquals(conflictItem.getDescription().getValue(), "All songs");
                }
            }
        }
    }
}
