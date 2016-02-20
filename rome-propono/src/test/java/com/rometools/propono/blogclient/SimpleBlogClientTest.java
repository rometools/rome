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
package com.rometools.propono.blogclient;

import java.io.File;
import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.propono.blogclient.Blog.Collection;
import com.rometools.propono.utils.Utilities;
import com.rometools.rome.io.impl.Atom10Parser;

/**
 * Tests Atom and MetaWeblog API CRUD via BlogClient. Exclude this from automated tests because it
 * requires a live blog server.
 */
@Ignore
public class SimpleBlogClientTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleBlogClientTest.class);

    private final String metaweblogEndpoint = "http://localhost:8080/roller/roller-services/xmlrpc";
    // private String atomEndpoint = "http://localhost:8080/roller/roller-services/app";
    private final String atomEndpoint = "http://localhost:8080/sample-atomserver/app";

    // private final String endpoint = "http://localhost:8080/atom-fileserver/app";
    private final String username = "admin";
    private final String password = "admin";

    public SimpleBlogClientTest(final String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    public void testBlogClientAtom() throws Exception {
        testBlogClient("atom", atomEndpoint);
    }

    public void testBlogClientMetaWeblog() throws Exception {
        testBlogClient("metaweblog", metaweblogEndpoint);
    }

    public void testBlogClient(final String type, final String endpoint) throws Exception {
        final BlogConnection conn = BlogConnectionFactory.getBlogConnection(type, endpoint, username, password);

        int blogCount = 0;
        for (final Blog blog : conn.getBlogs()) {
            LOG.debug(blog.getName());
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

    public void testPostAndDelete(final String type, final String endpoint) throws Exception {
        final BlogConnection conn = BlogConnectionFactory.getBlogConnection(type, endpoint, username, password);
        assertNotNull(conn);

        final String title1 = "Test content";
        final String content1 = "Test content";

        final Blog blog = conn.getBlogs().get(0);
        BlogEntry entry = blog.newEntry();
        entry.setTitle(title1);
        entry.setContent(new BlogEntry.Content(content1));
        entry.save();
        final String token = entry.getToken();
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
        } catch (final Exception e) {
            notFound = true;
        }
        assertTrue(notFound);
    }

    /**
     * Post media entry to every media colletion avialable on server, then cleanup.
     */
    public void testMediaPost(final String type, final String endpoint) throws Exception {
        final BlogConnection conn = BlogConnectionFactory.getBlogConnection(type, endpoint, username, password);
        assertNotNull(conn);

        assertTrue(!conn.getBlogs().isEmpty());
        int count = 0;
        for (final Blog blog2 : conn.getBlogs()) {
            final Blog blog = blog2;
            assertNotNull(blog.getName());

            for (final Collection collection : blog.getCollections()) {
                final Blog.Collection col = collection;
                if (col.accepts("image/gif")) {

                    // we found a collection that accepts GIF, so post one
                    final BlogResource m1 = col.newResource("duke" + count, "image/gif",
                            Utilities.getBytesFromFile(new File("test/testdata/duke-wave-shadow.gif")));
                    col.saveResource(m1);

                    if ("atom".equals(type)) { // additional tests for Atom

                        // entry should now exist on server
                        final BlogResource m2 = (BlogResource) blog.getEntry(m1.getToken());
                        assertNotNull(m2);

                        // remove entry
                        m2.delete();

                        // fetching entry now should result in exception
                        boolean failed = false;
                        try {
                            blog.getEntry(m1.getToken());
                        } catch (final Exception e) {
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

    public void testEntryIteration(final String type, final String endpoint) throws Exception {
        final BlogConnection conn = BlogConnectionFactory.getBlogConnection(type, endpoint, username, password);
        assertNotNull(conn);

        final String title1 = "Test content";
        final String content1 = "Test content";

        final Blog blog = conn.getBlogs().get(0);

        for (int i = 0; i < 10; i++) {
            final BlogEntry entry = blog.newEntry();
            entry.setTitle(title1);
            entry.setContent(new BlogEntry.Content(content1));
            entry.save();
            final String token = entry.getToken();
            assertTrue(Atom10Parser.isAbsoluteURI(token));
            assertNotNull(token);
        }

        for (final Iterator<BlogEntry> it = blog.getEntries(); it.hasNext();) {
            final BlogEntry blogEntry = it.next();
            assertTrue(Atom10Parser.isAbsoluteURI(blogEntry.getToken()));
            blogEntry.delete();
        }
    }

    public static Test suite() {
        return new TestSuite(SimpleBlogClientTest.class);
    }

}
