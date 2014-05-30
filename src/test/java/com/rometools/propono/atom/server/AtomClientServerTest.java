/*
 * Copyright 2007 Sun Microsystems, Inc.
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
package com.rometools.propono.atom.server;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.http.HttpContext;
import org.mortbay.http.HttpServer;
import org.mortbay.http.SocketListener;
import org.mortbay.jetty.servlet.ServletHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.propono.atom.client.AtomClientFactory;
import com.rometools.propono.atom.client.BasicAuthStrategy;
import com.rometools.propono.atom.client.ClientAtomService;
import com.rometools.propono.atom.client.ClientCollection;
import com.rometools.propono.atom.client.ClientEntry;
import com.rometools.propono.atom.client.ClientMediaEntry;
import com.rometools.propono.atom.client.ClientWorkspace;
import com.rometools.propono.atom.common.Categories;
import com.rometools.propono.atom.common.Collection;
import com.rometools.propono.atom.common.Workspace;
import com.rometools.propono.utils.ProponoException;
import com.rometools.rome.feed.atom.Category;
import com.rometools.rome.feed.atom.Content;

/**
 * Test Propono Atom Client against Atom Server via Jetty. Extends <code>AtomClientTest</code> to
 * start Jetty server, run tests and then stop the Jetty server.
 */
public class AtomClientServerTest {

    private static final Logger LOG = LoggerFactory.getLogger(AtomClientServerTest.class);

    private HttpServer server = null;
    public static final int TESTPORT = 8283;
    public static final String ENDPOINT = "http://localhost:" + TESTPORT + "/rome/app";
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "admin";

    private static ClientAtomService service = null;

    public String getEndpoint() {
        return ENDPOINT;
    }

    public String getUsername() {
        return USERNAME;
    }

    public String getPassword() {
        return PASSWORD;
    }

    protected HttpServer getServer() {
        return server;
    }

    @Before
    public void setUpClass() throws Exception {

        LOG.info("---------------------------------------------");
        LOG.info("Starting Jetty");
        LOG.info("---------------------------------------------");

        setupServer();
        final HttpContext context = createContext();
        final ServletHandler servlets = createServletHandler();
        context.addHandler(servlets);
        server.addContext(context);
        server.start();

        service = AtomClientFactory.getAtomService(getEndpoint(), new BasicAuthStrategy(getUsername(), getPassword()));
    }

    @After
    public void tearDownClass() throws Exception {
        if (server != null) {
            LOG.info("Stoping Jetty");
            server.stop();
            server.destroy();
            server = null;
        }
    }

    private void setupServer() throws InterruptedException {
        // Create the server
        if (server != null) {
            server.stop();
            server = null;
        }
        server = new HttpServer();

        // Create a port listener
        final SocketListener listener = new SocketListener();
        listener.setPort(TESTPORT);
        server.addListener(listener);
    }

    private ServletHandler createServletHandler() {
        System.setProperty("com.rometools.propono.atom.server.AtomHandlerFactory", "com.rometools.propono.atom.server.TestAtomHandlerFactory");
        final ServletHandler servlets = new ServletHandler();
        servlets.addServlet("app", "/app/*", "com.rometools.propono.atom.server.AtomServlet");
        return servlets;
    }

    private HttpContext createContext() {
        final HttpContext context = new HttpContext();
        context.setContextPath("/rome/*");
        return context;
    }

    /**
     * Tests that server has introspection doc with at least one workspace.
     */
    @Test
    public void testGetAtomService() throws Exception {
        assertNotNull(service);
        assertTrue(!service.getWorkspaces().isEmpty());
        for (final Workspace workspace : service.getWorkspaces()) {
            final ClientWorkspace space = (ClientWorkspace) workspace;
            assertNotNull(space.getTitle());
            LOG.debug("Workspace: {}", space.getTitle());
            for (final Object element : space.getCollections()) {
                final ClientCollection col = (ClientCollection) element;
                LOG.debug("   Collection: {} Accepts: {}", col.getTitle(), col.getAccepts());
                LOG.debug("      href: {}", col.getHrefResolved());
                assertNotNull(col.getTitle());
            }
        }
    }

    /**
     * Tests that entries can be posted and removed in all collections that accept entries. Fails if
     * no collections found that accept entries.
     */
    @Test
    public void testSimpleEntryPostAndRemove() throws Exception {
        assertNotNull(service);
        assertTrue(!service.getWorkspaces().isEmpty());
        int count = 0;
        for (final Object element : service.getWorkspaces()) {
            final ClientWorkspace space = (ClientWorkspace) element;
            assertNotNull(space.getTitle());

            for (final Object element2 : space.getCollections()) {
                final ClientCollection col = (ClientCollection) element2;
                if (col.accepts(Collection.ENTRY_TYPE)) {

                    // we found a collection that accepts entries, so post one
                    final ClientEntry m1 = col.createEntry();
                    m1.setTitle("Test post");
                    final Content c = new Content();
                    c.setValue("This is a test post");
                    c.setType("html");
                    m1.setContent(c);

                    col.addEntry(m1);

                    // entry should now exist on server
                    final ClientEntry m2 = col.getEntry(m1.getEditURI());
                    assertNotNull(m2);

                    // remove entry
                    m2.remove();

                    // fetching entry now should result in exception
                    boolean failed = false;
                    try {
                        col.getEntry(m1.getEditURI());
                    } catch (final ProponoException e) {
                        failed = true;
                    }
                    assertTrue(failed);
                    count++;
                }
            }
        }
        assertTrue(count > 0);
    }

    /**
     * Tests that entries can be posted, updated and removed in all collections that accept entries.
     * Fails if no collections found that accept entries.
     */
    @Test
    public void testSimpleEntryPostUpdateAndRemove() throws Exception {
        assertNotNull(service);
        assertTrue(!service.getWorkspaces().isEmpty());
        int count = 0;
        for (final Object element : service.getWorkspaces()) {
            final ClientWorkspace space = (ClientWorkspace) element;
            assertNotNull(space.getTitle());

            for (final Object element2 : space.getCollections()) {
                final ClientCollection col = (ClientCollection) element2;
                if (col.accepts(Collection.ENTRY_TYPE)) {

                    // we found a collection that accepts entries, so post one
                    final ClientEntry m1 = col.createEntry();
                    m1.setTitle(col.getTitle() + ": Test post");
                    final Content c = new Content();
                    c.setValue("This is a test post");
                    c.setType("html");
                    m1.setContent(c);

                    col.addEntry(m1);

                    // entry should now exist on server
                    final ClientEntry m2 = col.getEntry(m1.getEditURI());
                    assertNotNull(m2);

                    m2.setTitle(col.getTitle() + ": Updated title");
                    m2.update();

                    // entry should now be updated on server
                    final ClientEntry m3 = col.getEntry(m1.getEditURI());
                    assertEquals(col.getTitle() + ": Updated title", m3.getTitle());

                    // remove entry
                    m3.remove();

                    // fetching entry now should result in exception
                    boolean failed = false;
                    try {
                        col.getEntry(m1.getEditURI());
                    } catch (final ProponoException e) {
                        failed = true;
                    }
                    assertTrue(failed);
                    count++;
                }
            }
        }
        assertTrue(count > 0);
    }

    @Test
    public void testFindWorkspace() throws Exception {
        assertNotNull(service);
        final ClientWorkspace ws = (ClientWorkspace) service.findWorkspace("adminblog");
        if (ws != null) {
            final ClientCollection col = (ClientCollection) ws.findCollection(null, "entry");
            final ClientEntry entry = col.createEntry();
            entry.setTitle("NPE on submitting order query");
            entry.setContent("This is a <b>bad</b> one!", Content.HTML);
            col.addEntry(entry);

            // entry should now exist on server
            final ClientEntry saved = col.getEntry(entry.getEditURI());
            assertNotNull(saved);

            // remove entry
            saved.remove();

            // fetching entry now should result in exception
            boolean failed = false;
            try {
                col.getEntry(saved.getEditURI());
            } catch (final ProponoException e) {
                failed = true;
            }
            assertTrue(failed);
        }
    }

    /**
     * Test posting an entry to every available collection with a fixed and an unfixed category if
     * server support allows, then cleanup.
     */
    @Test
    public void testEntryPostWithCategories() throws Exception {
        assertNotNull(service);
        assertTrue(!service.getWorkspaces().isEmpty());
        int count = 0;
        for (final Object element2 : service.getWorkspaces()) {
            final ClientWorkspace space = (ClientWorkspace) element2;
            assertNotNull(space.getTitle());

            for (final Object element3 : space.getCollections()) {
                final ClientCollection col = (ClientCollection) element3;
                if (col.accepts(Collection.ENTRY_TYPE)) {

                    // we found a collection that accepts GIF, so post one
                    final ClientEntry m1 = col.createEntry();
                    m1.setTitle("Test post");
                    final Content c = new Content();
                    c.setValue("This is a test post");
                    c.setType("html");
                    m1.setContent(c);

                    // if possible, pick one fixed an un unfixed category
                    Category fixedCat = null;
                    Category unfixedCat = null;
                    final List<Category> entryCats = new ArrayList<Category>();
                    for (int i = 0; i < col.getCategories().size(); i++) {
                        final Categories cats = col.getCategories().get(i);
                        if (cats.isFixed() && fixedCat == null) {
                            final String scheme = cats.getScheme();
                            fixedCat = cats.getCategories().get(0);
                            if (fixedCat.getScheme() == null) {
                                fixedCat.setScheme(scheme);
                            }
                            entryCats.add(fixedCat);
                        } else if (!cats.isFixed() && unfixedCat == null) {
                            final String scheme = cats.getScheme();
                            unfixedCat = new Category();
                            unfixedCat.setScheme(scheme);
                            unfixedCat.setTerm("tagster");
                            entryCats.add(unfixedCat);
                        }
                    }
                    m1.setCategories(entryCats);
                    col.addEntry(m1);

                    // entry should now exist on server
                    final ClientEntry m2 = col.getEntry(m1.getEditURI());
                    assertNotNull(m2);

                    if (fixedCat != null) {
                        // we added a fixed category, let's make sure it's there
                        boolean foundCat = false;
                        for (final Object element : m2.getCategories()) {
                            final Category cat = (Category) element;
                            if (cat.getTerm().equals(fixedCat.getTerm())) {
                                foundCat = true;
                            }
                        }
                        assertTrue(foundCat);
                    }

                    if (unfixedCat != null) {
                        // we added an unfixed category, let's make sure it's there
                        boolean foundCat = false;
                        for (final Object element : m2.getCategories()) {
                            final Category cat = (Category) element;
                            if (cat.getTerm().equals(unfixedCat.getTerm())) {
                                foundCat = true;
                            }
                        }
                        assertTrue(foundCat);
                    }

                    // remove entry
                    m2.remove();

                    // fetching entry now should result in exception
                    boolean failed = false;
                    try {
                        col.getEntry(m1.getEditURI());
                    } catch (final ProponoException e) {
                        failed = true;
                    }
                    assertTrue(failed);
                    count++;
                }
            }
        }
        assertTrue(count > 0);
    }

    /**
     * Post media entry to every media colletion avialable on server, then cleanup.
     */
    public void testMediaPost() throws Exception {
        assertNotNull(service);
        assertTrue(!service.getWorkspaces().isEmpty());
        int count = 0;
        for (final Object element : service.getWorkspaces()) {
            final ClientWorkspace space = (ClientWorkspace) element;
            assertNotNull(space.getTitle());

            for (final Object element2 : space.getCollections()) {
                final ClientCollection col = (ClientCollection) element2;
                if (col.accepts("image/gif")) {

                    // we found a collection that accepts GIF, so post one
                    final ClientMediaEntry m1 = col.createMediaEntry("duke" + count, "duke" + count, "image/gif", new FileInputStream(
                            "test/testdata/duke-wave-shadow.gif"));
                    col.addEntry(m1);

                    // entry should now exist on server
                    final ClientMediaEntry m2 = (ClientMediaEntry) col.getEntry(m1.getEditURI());
                    assertNotNull(m2);

                    // remove entry
                    m2.remove();

                    // fetching entry now should result in exception
                    boolean failed = false;
                    try {
                        col.getEntry(m1.getEditURI());
                    } catch (final ProponoException e) {
                        failed = true;
                    }
                    assertTrue(failed);
                    count++;
                }
            }
        }
        assertTrue(count > 0);
    }

}
