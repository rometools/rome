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
package org.rometools.propono.blogclient.atomprotocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.rometools.propono.atom.client.AtomClientFactory;
import org.rometools.propono.atom.client.BasicAuthStrategy;
import org.rometools.propono.atom.client.ClientAtomService;
import org.rometools.propono.atom.client.ClientWorkspace;
import org.rometools.propono.blogclient.Blog;
import org.rometools.propono.blogclient.BlogClientException;
import org.rometools.propono.blogclient.BlogConnection;

/**
 * Atom protocol of BlogConnection. Connects to Atom server, creates AtomBlog object for each Atom workspace found and within each blog a collection for each
 * Atom collection found.
 */
public class AtomConnection implements BlogConnection {
    private static Log logger = LogFactory.getLog(AtomConnection.class);
    private final HttpClient httpClient = null;
    private final Map blogs = new HashMap();

    /**
     * Create Atom blog client instance for specified URL and user account.
     * 
     * @param uri End-point URL of Atom service
     * @param username Username of account
     * @param password Password of account
     */
    public AtomConnection(final String uri, final String username, final String password) throws BlogClientException {

        final Document doc = null;
        try {
            final ClientAtomService service = AtomClientFactory.getAtomService(uri, new BasicAuthStrategy(username, password));
            final Iterator iter = service.getWorkspaces().iterator();
            final int count = 0;
            while (iter.hasNext()) {
                final ClientWorkspace workspace = (ClientWorkspace) iter.next();
                final Blog blog = new AtomBlog(service, workspace);
                blogs.put(blog.getToken(), blog);
            }
        } catch (final Throwable t) {
            throw new BlogClientException("Error connecting to blog server", t);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List getBlogs() {
        return new ArrayList(blogs.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Blog getBlog(final String token) {
        return (AtomBlog) blogs.get(token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAppkey(final String appkey) {
    }
}
