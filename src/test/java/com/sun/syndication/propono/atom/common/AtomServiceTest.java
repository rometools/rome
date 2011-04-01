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
package com.sun.syndication.propono.atom.common;

import com.sun.syndication.feed.atom.Category;
import java.io.FileInputStream;
import java.util.Iterator;
import junit.framework.*;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * Tests reading and writing of service document, no server needed.
 */
public class AtomServiceTest extends TestCase {
    
    public AtomServiceTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(AtomServiceTest.class);
        
        return suite;
    }

    /**
     * Test of documentToService method, of class AtomService.
     */
    public void testDocumentToService() {
        try {
            // Load service document from disk
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(new FileInputStream("test/testdata/servicedoc1.xml"));
            assertNotNull(document);
            AtomService service = AtomService.documentToService(document); 
            
            int workspaceCount = 0;
            
            // Verify that service contains expected workspaces, collections and categories
            for (Iterator it = service.getWorkspaces().iterator(); it.hasNext();) {
                Workspace space = (Workspace)it.next();
                assertNotNull(space.getTitle());
                workspaceCount++;
                int collectionCount = 0;

                for (Iterator colit = space.getCollections().iterator(); colit.hasNext();) {
                    Collection col = (Collection)colit.next();
                    assertNotNull(col.getTitle());
                    assertNotNull(col.getHrefResolved());
                    collectionCount++;
                    int catCount = 0;
                    if (col.getCategories().size() > 0) {
                        for (Iterator catsit = col.getCategories().iterator(); catsit.hasNext();) {
                            Categories cats = (Categories) catsit.next();
                            for (Iterator catit = cats.getCategories().iterator(); catit.hasNext();) {
                                Category cat = (Category) catit.next();
                                catCount++;
                            }
                            assertTrue(catCount > 0);
                        }
                    }
                }
            }
            
            assertTrue(workspaceCount > 0);
            
        } catch (Exception e) {
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
            AtomService service = new AtomService();
            
            Workspace workspace1 = new Workspace("workspace1", null);
            Workspace workspace2 = new Workspace("workspace1", null);
            service.addWorkspace(workspace1);
            service.addWorkspace(workspace2);
            
            Collection collection11 = 
                new Collection("collection11", null, "http://example.com/app/col11");            
            Collection collection12 = 
                new Collection("collection12", null, "http://example.com/app/col12");
            workspace1.addCollection(collection11);
            workspace1.addCollection(collection12);
            
            Collection collection21 = 
                new Collection("collection21", null, "http://example.com/app/col21");            
            Collection collection22 = 
                new Collection("collection22", null, "http://example.com/app/col22");
            workspace2.addCollection(collection21);
            workspace2.addCollection(collection22);
            
            // TODO: add categories at collection level
            
            // Convert to JDOM document
            Document document = service.serviceToDocument();

            // verify that JDOM document contains service, workspace and collection
            assertEquals("service", document.getRootElement().getName());
            int workspaceCount = 0;
            for (Iterator spaceit = document.getRootElement().getChildren().iterator(); spaceit.hasNext();) {
                Element elem = (Element) spaceit.next();            
                if ("workspace".equals(elem.getName())) { 
                    workspaceCount++;
                }
                boolean workspaceTitle = false;
                int collectionCount = 0;
                for (Iterator colit = elem.getChildren().iterator(); colit.hasNext();) {
                    Element colelem = (Element) colit.next();
                    if ("title".equals(colelem.getName())) { 
                        workspaceTitle = true;
                    } else if ("collection".equals(colelem.getName())){
                        collectionCount++;             
                    }
                    
                    // TODO: test for categories at the collection level
                }
                assertTrue(workspaceTitle);
                assertTrue(collectionCount > 0);
            }
            assertTrue(workspaceCount > 0);
            
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }  
}
