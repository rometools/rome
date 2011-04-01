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
package com.sun.syndication.propono.atom.client;

import com.sun.syndication.feed.atom.Category;
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.propono.utils.ProponoException;
import com.sun.syndication.propono.atom.common.Categories;
import com.sun.syndication.propono.atom.common.Collection;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Simple APP test designed to run against a live Atom server.
 */
public class AtomClientTest extends TestCase {
    
    private static Log log =
            LogFactory.getFactory().getInstance(AtomClientTest.class);
    
    private static ClientAtomService service = null;

    // Basic Auth example
    private static String endpoint = "http://localhost:8080/sample-atomserver/app";
    private static String username = "admin";
    private static String password = "admin";
    static {
        try {
            service = AtomClientFactory.getAtomService(endpoint,
                new BasicAuthStrategy(username, password));
        } catch (Exception e) {
            log.error("ERROR creating service", e);
        }
    }

    /*
    // Roller OAuth example
    private static String endpoint = "http://macsnoopdave:8080/roller-services/app";
    private static String consumerKey    = "55132608a2fb68816bcd3d1caeafc933";
    private static String consumerSecret = "bb420783-fdea-4270-ab83-36445c18c307";
    private static String requestUri     = "http://macsnoopdave:8080/roller-services/oauth/requestToken";
    private static String authorizeUri   = "http://macsnoopdave:8080/roller-services/oauth/authorize?userId=roller&oauth_callback=none";
    private static String accessUri      = "http://macsnoopdave:8080/roller-services/oauth/accessToken";
    private static String username       = "roller";
    private static String password       = "n/a";
    static {
        try {
            service = AtomClientFactory.getAtomService(endpoint,
                new OAuthStrategy(
                    username, consumerKey, consumerSecret, "HMAC-SHA1",
                    requestUri, authorizeUri, accessUri));
        } catch (Exception e) {
            log.error("ERROR creating service", e);
        }
    }
    */

    // GData Blogger API
    /*
    private static String endpoint = "http://www.blogger.com/feeds/default/blogs?alt=atom-service";
    private static String email = "EMAIL";
    private static String password = "PASSWORD";
    private static String serviceName = "blogger";
    static {
        try {
            service = AtomClientFactory.getAtomService(endpoint,
                new GDataAuthStrategy(email, password, serviceName));
        } catch (Exception e) {
            log.error("ERROR creating service", e);
        }
    }  
    */


    private int maxPagingEntries = 10;
    
    
    public AtomClientTest(String testName) {
        super(testName);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(AtomClientTest.class);        
        return suite;
    }

    /**
     * Tests that server has introspection doc with at least one workspace.
     */
    public void testGetAtomService() throws Exception {        
        assertNotNull(service);
        assertTrue(service.getWorkspaces().size() > 0); 
        for (Iterator it = service.getWorkspaces().iterator(); it.hasNext();) {
            ClientWorkspace space = (ClientWorkspace) it.next();
            assertNotNull(space.getTitle());            
            log.debug("Workspace: " + space.getTitle());
            for (Iterator colit = space.getCollections().iterator(); colit.hasNext();) {
                ClientCollection col = (ClientCollection) colit.next();
                log.debug("   Collection: " + col.getTitle() + " Accepts: " + col.getAccepts());
                log.debug("      href: " + col.getHrefResolved());
                assertNotNull(col.getTitle());
            }
        }
    }
    
    /**
     * Tests that entries can be posted and removed in all collections that 
     * accept entries. Fails if no collections found that accept entries.
     */
    public void testSimpleEntryPostAndRemove() throws Exception {
        assertNotNull(service);
        assertTrue(service.getWorkspaces().size() > 0);    
        int count = 0;
        for (Iterator it = service.getWorkspaces().iterator(); it.hasNext();) {
            ClientWorkspace space = (ClientWorkspace) it.next();
            assertNotNull(space.getTitle());
            
            for (Iterator colit = space.getCollections().iterator(); colit.hasNext();) {
                ClientCollection col = (ClientCollection) colit.next();
                if (col.accepts(Collection.ENTRY_TYPE)) {
                    
                    // we found a collection that accepts entries, so post one
                    ClientEntry m1 = col.createEntry();
                    m1.setTitle("Test post");
                    Content c = new Content();
                    c.setValue("This is a test post");
                    c.setType("html");
                    m1.setContent(c);
                    
                    col.addEntry(m1);
                    
                    // entry should now exist on server
                    ClientEntry m2 = col.getEntry(m1.getEditURI());
                    assertNotNull(m2);
                                        
                    // remove entry
                    m2.remove();
                    
                    // fetching entry now should result in exception
                    boolean failed = false;
                    try {
                        col.getEntry(m1.getEditURI());                    
                    } catch (ProponoException e) {
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
     * Tests that entries can be posted, updated and removed in all collections that 
     * accept entries. Fails if no collections found that accept entries.
     */
    public void testSimpleEntryPostUpdateAndRemove() throws Exception {
        assertNotNull(service);
        assertTrue(service.getWorkspaces().size() > 0);    
        int count = 0;
        for (Iterator it = service.getWorkspaces().iterator(); it.hasNext();) {
            ClientWorkspace space = (ClientWorkspace) it.next();
            assertNotNull(space.getTitle());
            
            for (Iterator colit = space.getCollections().iterator(); colit.hasNext();) {
                ClientCollection col = (ClientCollection) colit.next();
                if (col.accepts(Collection.ENTRY_TYPE)) {
                    
                    // we found a collection that accepts entries, so post one
                    ClientEntry m1 = col.createEntry();
                    m1.setTitle(col.getTitle() + ": Test post");
                    Content c = new Content();
                    c.setValue("This is a test post");
                    c.setType("html");
                    m1.setContent(c);
                    
                    col.addEntry(m1);
                    
                    // entry should now exist on server
                    ClientEntry m2 = (ClientEntry)col.getEntry(m1.getEditURI());
                    assertNotNull(m2);
                    
                    m2.setTitle(col.getTitle() + ": Updated title");
                    m2.update();
                                        
                    // entry should now be updated on server
                    ClientEntry m3 = (ClientEntry)col.getEntry(m1.getEditURI());
                    assertEquals(col.getTitle() + ": Updated title", m3.getTitle());

                    // remove entry
                    m3.remove();
                    
                    // fetching entry now should result in exception
                    boolean failed = false;
                    try {
                        col.getEntry(m1.getEditURI());                    
                    } catch (ProponoException e) {
                        failed = true;
                    }
                    assertTrue(failed);                   
                    count++;
                }
            }
        }  
        assertTrue(count > 0);
    } 
    
    public void testFindWorkspace() throws Exception {
        assertNotNull(service);
        ClientWorkspace ws = (ClientWorkspace)service.findWorkspace("adminblog");
        if (ws != null) {
            ClientCollection col = (ClientCollection)ws.findCollection(null, "entry");            
            ClientEntry entry = col.createEntry();
            entry.setTitle("NPE on submitting order query");
            entry.setContent("This is a <b>bad</b> one!", Content.HTML);
            col.addEntry(entry);
            
            // entry should now exist on server
            ClientEntry saved = (ClientEntry)col.getEntry(entry.getEditURI());
            assertNotNull(saved);

            // remove entry
            saved.remove();
            
            // fetching entry now should result in exception
            boolean failed = false;
            try {
                col.getEntry(saved.getEditURI());                    
            } catch (ProponoException e) {
                failed = true;
            }
            assertTrue(failed);   
        }
    }
    
    /**
     * Test posting an entry to every available collection with a fixed and 
     * an unfixed category if server support allows, then cleanup.
     */
    public void testEntryPostWithCategories() throws Exception {
        assertNotNull(service);
        assertTrue(service.getWorkspaces().size() > 0);    
        int count = 0;
        for (Iterator it = service.getWorkspaces().iterator(); it.hasNext();) {
            ClientWorkspace space = (ClientWorkspace) it.next();
            assertNotNull(space.getTitle());
            
            for (Iterator colit = space.getCollections().iterator(); colit.hasNext();) {
                ClientCollection col = (ClientCollection) colit.next();
                if (col.accepts(Collection.ENTRY_TYPE)) {
                    
                    // we found a collection that accepts GIF, so post one
                    ClientEntry m1 = col.createEntry();
                    m1.setTitle("Test post");
                    Content c = new Content();
                    c.setValue("This is a test post");
                    c.setType("html");
                    m1.setContent(c);
                    
                    // if possible, pick one fixed an un unfixed category
                    Category fixedCat = null;
                    Category unfixedCat = null;
                    List entryCats = new ArrayList();
                    for (int i=0; i<col.getCategories().size(); i++) {
                        Categories cats = (Categories)col.getCategories().get(i);
                        if (cats.isFixed() && fixedCat == null) {
                            String scheme = cats.getScheme();
                            fixedCat = (Category)cats.getCategories().get(0);
                            if (fixedCat.getScheme() == null) fixedCat.setScheme(scheme);
                            entryCats.add(fixedCat);
                        } else if (!cats.isFixed() && unfixedCat == null) {
                            String scheme = cats.getScheme();
                            unfixedCat = new Category();
                            unfixedCat.setScheme(scheme);
                            unfixedCat.setTerm("tagster");
                            entryCats.add(unfixedCat);
                        }
                    }
                    m1.setCategories(entryCats);
                    col.addEntry(m1);
                    
                    // entry should now exist on server
                    ClientEntry m2 = (ClientEntry)col.getEntry(m1.getEditURI());
                    assertNotNull(m2);
                    
                    if (fixedCat != null) {
                        // we added a fixed category, let's make sure it's there
                        boolean foundCat = false;
                        for (Iterator catit = m2.getCategories().iterator(); catit.hasNext();) {
                            Category cat = (Category) catit.next();
                            if (   cat.getTerm().equals(  fixedCat.getTerm())) {
                                foundCat = true;
                            }
                        }
                        assertTrue(foundCat);
                    }
                    
                    if (unfixedCat != null) {
                        // we added an unfixed category, let's make sure it's there
                        boolean foundCat = false;
                        for (Iterator catit = m2.getCategories().iterator(); catit.hasNext();) {
                            Category cat = (Category) catit.next();
                            if (cat.getTerm().equals(  unfixedCat.getTerm())) {
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
                    } catch (ProponoException e) {
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
        assertTrue(service.getWorkspaces().size() > 0);        
        int count = 0;
        for (Iterator it = service.getWorkspaces().iterator(); it.hasNext();) {
            ClientWorkspace space = (ClientWorkspace) it.next();
            assertNotNull(space.getTitle());
            
            for (Iterator colit = space.getCollections().iterator(); colit.hasNext();) {
                ClientCollection col = (ClientCollection) colit.next();
                if (col.accepts("image/gif")) {
                    
                    // we found a collection that accepts GIF, so post one
                    ClientMediaEntry m1 = col.createMediaEntry("duke"+count, "duke"+count, "image/gif", 
                        new FileInputStream("test/testdata/duke-wave-shadow.gif"));
                    col.addEntry(m1);
                    
                    // entry should now exist on server
                    ClientMediaEntry m2 = (ClientMediaEntry)col.getEntry(m1.getEditURI());
                    assertNotNull(m2);
                    
                    // remove entry
                    m2.remove();
                    
                    // fetching entry now should result in exception
                    boolean failed = false;
                    try {
                        col.getEntry(m1.getEditURI());                    
                    } catch (ProponoException e) {
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
     * Post X media entries each media collection found, test paging, then cleanup.
     *
    public void testMediaPaging() throws Exception {
        ClientAtomService service = getClientAtomService();
        assertNotNull(service);
        assertTrue(service.getWorkspaces().size() > 0);        
        int count = 0;
        for (Iterator it = service.getWorkspaces().iterator(); it.hasNext();) {
            ClientWorkspace space = (ClientWorkspace) it.next();
            assertNotNull(space.getTitle());
            
            for (Iterator colit = space.getCollections().iterator(); colit.hasNext();) {
                ClientCollection col = (ClientCollection) colit.next();
                if (col.accepts("image/gif")) {
                    
                    // we found a collection that accepts GIF, so post 100 of them
                    List posted = new ArrayList();
                    for (int i=0; i<maxPagingEntries; i++) {
                        ClientMediaEntry m1 = col.createMediaEntry("duke"+count, "duke"+count, "image/gif", 
                            new FileInputStream("test/testdata/duke-wave-shadow.gif"));
                        col.addEntry(m1);
                        posted.add(m1);
                    }
                    int entryCount = 0;
                    for (Iterator iter = col.getEntries(); iter.hasNext();) {
                        ClientMediaEntry entry = (ClientMediaEntry) iter.next();
                        entryCount++;
                    }
                    for (Iterator delit = posted.iterator(); delit.hasNext();) {
                        ClientEntry entry = (ClientEntry) delit.next();
                        entry.remove();
                    }
                    assertTrue(entryCount >= maxPagingEntries);
                    count++;
                    break;
                }
            }
        }   
        assertTrue(count > 0);
    }*/  
}


