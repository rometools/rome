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
package org.rometools.propono.blogclient;

import org.rometools.propono.blogclient.BlogConnection;
import org.rometools.propono.blogclient.BlogResource;
import org.rometools.propono.blogclient.Blog;
import org.rometools.propono.blogclient.BlogConnectionFactory;
import org.rometools.propono.blogclient.BlogEntry;
import com.sun.syndication.io.impl.Atom10Parser;
import org.rometools.propono.utils.Utilities;
import java.io.File;
import java.util.Iterator;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



/**
 * Tests Atom and MetaWeblog API CRUD via BlogClient.
 * Exclude this from automated tests because it requires a live blog server.
 */
public class SimpleBlogClientTest extends TestCase {
    
    private String metaweblogEndpoint = "http://localhost:8080/roller/roller-services/xmlrpc";
    //private String atomEndpoint       = "http://localhost:8080/roller/roller-services/app";
    private String atomEndpoint       = "http://localhost:8080/sample-atomserver/app";
    
    private String endpoint = "http://localhost:8080/atom-fileserver/app";
    private String username = "admin";
    private String password = "admin";
    
    public SimpleBlogClientTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }
    
    public void testBlogClientAtom() throws Exception {
        testBlogClient("atom", atomEndpoint);
    }
    
    public void testBlogClientMetaWeblog() throws Exception{
        testBlogClient("metaweblog", metaweblogEndpoint);
    }
    
    public void testBlogClient(String type, String endpoint) throws Exception {
        BlogConnection conn = BlogConnectionFactory
                .getBlogConnection(type, endpoint, username, password);
        
        int blogCount = 0;
        for (Iterator it = conn.getBlogs().iterator(); it.hasNext();) {
            Blog blog = (Blog) it.next();
            System.out.println(blog.getName());
            blogCount++;
        }
        assertTrue(blogCount > 0); 
    }

    public void testPostAndDeleteAtom() throws Exception {
        testPostAndDelete("atom", atomEndpoint);
    }
    
    public void testPostAndDeleteMetaWeblog() throws Exception {
        testPostAndDelete("metaweblog", metaweblogEndpoint);
    }
    
    public void testMediaPostAtom() throws Exception {
        testMediaPost("atom", atomEndpoint);
    }
    
    public void testMediaPostMetaWeblog() throws Exception {
        testMediaPost("metaweblog", metaweblogEndpoint);
    }
    
    public void testPostAndDelete(String type, String endpoint) throws Exception {
        BlogConnection conn = BlogConnectionFactory
                .getBlogConnection(type, endpoint, username, password);
        assertNotNull(conn);
        
        String title1 = "Test content";
        String content1 = "Test content";
        
        Blog blog = (Blog)conn.getBlogs().get(0);
        BlogEntry entry = blog.newEntry();
        entry.setTitle(title1);
        entry.setContent(new BlogEntry.Content(content1));
        entry.save();
        String token = entry.getToken();
        assertNotNull(token);
        
        entry = blog.getEntry(token);
        
        assertEquals(title1, entry.getTitle());
        assertEquals(content1, entry.getContent().getValue());
        
        assertNotNull(entry);
        entry.delete();
        entry = null;
        
        boolean notFound = false;
        try {
            entry = blog.getEntry(token);
        } catch (Exception e) {
            notFound = true;
        }
        assertTrue(notFound);
    }

    /**
     * Post media entry to every media colletion avialable on server, then cleanup.
     */
    public void testMediaPost(String type, String endpoint) throws Exception {
        BlogConnection conn = BlogConnectionFactory
                .getBlogConnection(type, endpoint, username, password);
        assertNotNull(conn);
        
        assertTrue(conn.getBlogs().size() > 0);        
        int count = 0;
        for (Iterator it = conn.getBlogs().iterator(); it.hasNext();) {
            Blog blog = (Blog) it.next();
            assertNotNull(blog.getName());
            
            for (Iterator colit = blog.getCollections().iterator(); colit.hasNext();) {
                Blog.Collection col = (Blog.Collection) colit.next();
                if (col.accepts("image/gif")) {
                    
                    // we found a collection that accepts GIF, so post one
                    BlogResource m1 = col.newResource("duke"+count, "image/gif", 
                        Utilities.getBytesFromFile(new File("test/testdata/duke-wave-shadow.gif")));
                    col.saveResource(m1);
                    
                    if ("atom".equals(type)) { // additional tests for Atom

                        // entry should now exist on server
                        BlogResource m2 = (BlogResource)blog.getEntry(m1.getToken());
                        assertNotNull(m2);

                        // remove entry
                        m2.delete();

                        // fetching entry now should result in exception
                        boolean failed = false;
                        try {
                            blog.getEntry(m1.getToken());                    
                        } catch (Exception e) {
                            failed = true;
                        }
                        assertTrue(failed);
                    }
                    count++;
                }
            }
        }   
        assertTrue(count > 0);
    }

    
    public void testEntryIterationAtom() throws Exception {
        testEntryIteration("atom", atomEndpoint);
    }
    
    public void testEntryIterationMetaWeblog() throws Exception {
        testEntryIteration("metaweblog", metaweblogEndpoint);
    }

    public void testEntryIteration(String type, String endpoint) throws Exception {
        BlogConnection conn = BlogConnectionFactory
                .getBlogConnection(type, endpoint, username, password);
        assertNotNull(conn);
        
        String title1 = "Test content";
        String content1 = "Test content";
        
        Blog blog = (Blog)conn.getBlogs().get(0);
        
        for (int i=0; i<10; i++) {
            BlogEntry entry = blog.newEntry();
            entry.setTitle(title1);
            entry.setContent(new BlogEntry.Content(content1));
            entry.save();
            String token = entry.getToken();
            assertTrue(Atom10Parser.isAbsoluteURI(token));
            assertNotNull(token);
        }

        for (Iterator it = blog.getEntries(); it.hasNext();) {
            BlogEntry blogEntry = (BlogEntry)it.next();
            assertTrue(Atom10Parser.isAbsoluteURI(blogEntry.getToken()));
            blogEntry.delete();
        }       
    }
    
    
    public static Test suite() {
        TestSuite suite = new TestSuite(SimpleBlogClientTest.class);        
        return suite;
    }
}



