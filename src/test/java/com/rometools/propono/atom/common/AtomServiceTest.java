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
 */
package com.rometools.propono.atom.common;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 * Tests reading and writing of service document, no server needed.
 */
public class AtomServiceTest extends TestCase {

    public AtomServiceTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(AtomServiceTest.class);

        return suite;
    }

    /**
     * Test of documentToService method, of class AtomService.
     */
    public void testDocumentToService() {
        try {
            // Load service document from disk
            final SAXBuilder builder = new SAXBuilder();
            final Document document = builder.build(this.getClass().getResourceAsStream("/servicedoc1.xml"));
            assertNotNull(document);
            final AtomService service = AtomService.documentToService(document);

            int workspaceCount = 0;

            // Verify that service contains expected workspaces, collections and categories
            for (final Object element : service.getWorkspaces()) {
                final Workspace space = (Workspace) element;
                assertNotNull(space.getTitle());
                workspaceCount++;
                for (final Object element2 : space.getCollections()) {
                    final Collection col = (Collection) element2;
                    assertNotNull(col.getTitle());
                    assertNotNull(col.getHrefResolved());
                    int catCount = 0;
                    if (!col.getCategories().isEmpty()) {
                        for (final Object element3 : col.getCategories()) {
                            final Categories cats = (Categories) element3;
                            catCount += cats.getCategories().size();
                            assertTrue(catCount > 0);
                        }
                    }
                }
            }

            assertTrue(workspaceCount > 0);

        } catch (final Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /**
     * Test of documentToService method, of class AtomService.
     */
    public void testServiceToDocument() {
        try {
            // Create service with workspace and collections
            final AtomService service = new AtomService();

            final Workspace workspace1 = new Workspace("workspace1", null);
            final Workspace workspace2 = new Workspace("workspace1", null);
            service.addWorkspace(workspace1);
            service.addWorkspace(workspace2);

            final Collection collection11 = new Collection("collection11", null, "http://example.com/app/col11");
            final Collection collection12 = new Collection("collection12", null, "http://example.com/app/col12");
            workspace1.addCollection(collection11);
            workspace1.addCollection(collection12);

            final Collection collection21 = new Collection("collection21", null, "http://example.com/app/col21");
            final Collection collection22 = new Collection("collection22", null, "http://example.com/app/col22");
            workspace2.addCollection(collection21);
            workspace2.addCollection(collection22);

            // TODO: add categories at collection level

            // Convert to JDOM document
            final Document document = service.serviceToDocument();

            // verify that JDOM document contains service, workspace and collection
            assertEquals("service", document.getRootElement().getName());
            int workspaceCount = 0;
            for (final Object element : document.getRootElement().getChildren()) {
                final Element elem = (Element) element;
                if ("workspace".equals(elem.getName())) {
                    workspaceCount++;
                }
                boolean workspaceTitle = false;
                int collectionCount = 0;
                for (final Object element2 : elem.getChildren()) {
                    final Element colelem = (Element) element2;
                    if ("title".equals(colelem.getName())) {
                        workspaceTitle = true;
                    } else if ("collection".equals(colelem.getName())) {
                        collectionCount++;
                    }

                    // TODO: test for categories at the collection level
                }
                assertTrue(workspaceTitle);
                assertTrue(collectionCount > 0);
            }
            assertTrue(workspaceCount > 0);

        } catch (final Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
