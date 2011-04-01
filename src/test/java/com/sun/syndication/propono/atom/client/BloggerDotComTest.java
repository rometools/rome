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

import com.sun.syndication.feed.atom.Content;
import java.util.Iterator;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Simple APP test designed to run against Blogger.com.
 */
public class BloggerDotComTest extends TestCase {
    
    private String collectionURI = "http://www.blogger.com/feeds/BLOGID/posts/default";
    private String atomServiceURI= "http://www.blogger.com/feeds/default/blogs?alt=atom-service";
    private String email         = "EMAIL";
    private String password      = "PASSWORD";
    
    public BloggerDotComTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(BloggerDotComTest.class);        
        return suite;
    }

    /**
     * Verify that server returns service document containing workspaces containing collections.
     */
    public void testGetEntries() throws Exception {  
                
        // no auth necessary for iterating through entries
        ClientCollection col = AtomClientFactory.getCollection(collectionURI, 
            new GDataAuthStrategy(email, password, "blogger"));
        assertNotNull(col);
        int count = 0;
        for (Iterator it = col.getEntries(); it.hasNext();) {
            ClientEntry entry = (ClientEntry) it.next();
            assertNotNull(entry);
            count++;
        }
        assertTrue(count > 0);

        col = AtomClientFactory.getCollection(collectionURI, 
            new GDataAuthStrategy(email, password, "blogger"));
        ClientEntry p1 = col.createEntry();
        p1.setTitle("Propono post");
        Content c = new Content();
        c.setValue("This is content from ROME Propono");
        p1.setContent(c);
        col.addEntry(p1);
        
        ClientEntry p2 = col.getEntry(p1.getEditURI());
        assertNotNull(p2);
        
        
        ClientAtomService atomService = AtomClientFactory.getAtomService(
            collectionURI, new GDataAuthStrategy(email, password, "blogger"));
        assertNotNull(atomService);
        
    }
}


