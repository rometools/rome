/*   
 *  Copyright 2007 Dave Johnson (Blogapps project)
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
package com.sun.syndication.propono.blogclient.atomprotocol;

import com.sun.syndication.propono.atom.client.AtomClientFactory;
import com.sun.syndication.propono.atom.client.BasicAuthStrategy;
import com.sun.syndication.propono.atom.client.ClientAtomService;
import com.sun.syndication.propono.atom.client.ClientWorkspace;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jdom.Document;

import com.sun.syndication.propono.blogclient.BlogConnection;
import com.sun.syndication.propono.blogclient.Blog;
import com.sun.syndication.propono.blogclient.BlogClientException;


/**
 * Atom protocol of BlogConnection. Connects to Atom server, creates AtomBlog
 * object for each Atom workspace found and within each blog a collection for each
 * Atom collection found. 
 */
public class AtomConnection implements BlogConnection {
    private static Log logger = LogFactory.getLog(AtomConnection.class);
    private HttpClient httpClient = null;
    private Map blogs = new HashMap();
    
    /**
     * Create Atom blog client instance for specified URL and user account.
     * @param uri      End-point URL of Atom service
     * @param username Username of account 
     * @param password Password of account
     */
    public AtomConnection(String uri, String username, String password) 
        throws BlogClientException {
        
        Document doc = null;
        try {
            ClientAtomService service = (ClientAtomService)
                AtomClientFactory.getAtomService(uri, new BasicAuthStrategy(username, password));
            Iterator iter = service.getWorkspaces().iterator();
            int count = 0;
            while (iter.hasNext()) {
                ClientWorkspace workspace = (ClientWorkspace)iter.next();
                Blog blog = new AtomBlog(service, workspace);
                blogs.put(blog.getToken(), blog);
            }            
        } catch (Throwable t) {
            throw new BlogClientException("Error connecting to blog server", t);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List getBlogs() {
        return new ArrayList(blogs.values());
    }
    
    /**
     * {@inheritDoc}
     */
    public Blog getBlog(String token) {
        return (AtomBlog)blogs.get(token);
    }

    /**
     * {@inheritDoc}
     */
    public void setAppkey(String appkey) {
    }
}
