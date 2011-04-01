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

import org.rometools.propono.utils.ProponoException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.rometools.propono.blogclient.Blog;
import org.rometools.propono.blogclient.BlogClientException;
import org.rometools.propono.blogclient.BlogEntry;
import org.rometools.propono.blogclient.BlogResource;
import org.rometools.propono.atom.client.ClientAtomService;
import org.rometools.propono.atom.client.ClientCollection;
import org.rometools.propono.atom.client.ClientEntry;
import org.rometools.propono.atom.client.ClientMediaEntry;
import org.rometools.propono.atom.client.ClientWorkspace;
import java.util.Map;
import java.util.TreeMap;

/**
 * Atom protocol implementation of the BlogClient Blog interface.
 */
public class AtomBlog implements Blog {
    static final Log logger = LogFactory.getLog(AtomBlog.class);
    private HttpClient        httpClient = null;
    private String            name = null;
    private ClientAtomService service;
    private ClientWorkspace   workspace = null;
    private AtomCollection    entriesCollection = null;
    private AtomCollection    resourcesCollection = null;
    private Map               collections = new TreeMap();
    
    /**
     * Create AtomBlog using specified HTTPClient, user account and workspace, 
     * called by AtomConnection. Fetches Atom Service document and creates 
     * an AtomCollection object for each collection found. The first entry 
     * collection is considered the primary entry collection. And the first 
     * resource collection is considered the primary resource collection.
     */
    AtomBlog(ClientAtomService service, ClientWorkspace workspace) {
        this.setService(service);
        this.setWorkspace(workspace);
        this.name = workspace.getTitle();
        Iterator members = workspace.getCollections().iterator();
        
        while (members.hasNext()) {
            ClientCollection col = (ClientCollection) members.next();
            if (col.accepts("entry") && entriesCollection == null) {
                // first entry collection is primary entry collection 
                entriesCollection = new AtomCollection(this, col);
            }
            else if (!col.accepts("entry") && resourcesCollection == null) {
                // first non-entry collection is primary resource collection
                resourcesCollection = new AtomCollection(this, col);
            } 
            collections.put(col.getHrefResolved(), new AtomCollection(this, col));
        }    
    }  
    
    /**
     * {@inheritDoc} 
     */
    public String getName() { return name; }

    /**
     * String display of blog, returns name.
     */
    public String toString() { return getName(); }
    
    /**
     * {@inheritDoc} 
     */
    public String getToken() { return entriesCollection.getToken(); }

    /**
     * {@inheritDoc} 
     */
    public BlogEntry newEntry() throws BlogClientException { 
        if (entriesCollection == null) throw new BlogClientException("No entry collection");
        return entriesCollection.newEntry(); 
    }  
    
    /**
     * {@inheritDoc}
     */
    public BlogEntry getEntry(String token) throws BlogClientException {      
        ClientEntry clientEntry = null;
        AtomEntry atomEntry = null;
        try {
            clientEntry = getService().getEntry(token);         
        } catch (ProponoException ex) {
            throw new BlogClientException("ERROR: fetching entry", ex);
        }
        if (clientEntry != null && clientEntry instanceof ClientMediaEntry) {
            return new AtomResource(this, (ClientMediaEntry)clientEntry);
        } else if (clientEntry != null && clientEntry instanceof ClientEntry) {
            return new AtomEntry(this, clientEntry);
        } else {
            throw new BlogClientException("ERROR: unknown object type returned");
        }
    }
    
    /**
     * {@inheritDoc} 
     */
    public Iterator getEntries() throws BlogClientException {
        if (entriesCollection == null) throw new BlogClientException("No primary entry collection");
        return new AtomEntryIterator(entriesCollection);
    }   
    
    /**
     * {@inheritDoc} 
     */
    public Iterator getResources() throws BlogClientException {
        if (resourcesCollection == null) throw new BlogClientException("No primary entry collection");
        return new AtomEntryIterator(resourcesCollection);
    }   
    
    String saveEntry(BlogEntry entry) throws BlogClientException {
        if (entriesCollection == null) throw new BlogClientException("No primary entry collection");
        return entriesCollection.saveEntry(entry);
    } 
    
    void deleteEntry(BlogEntry entry) throws BlogClientException {
        if (entriesCollection == null) throw new BlogClientException("No primary entry collection");
        entriesCollection.deleteEntry(entry);        
    }

    /**
     * {@inheritDoc}
     */
    public List getCategories() throws BlogClientException {
        if (entriesCollection == null) throw new BlogClientException("No primary entry collection");
        return entriesCollection.getCategories();
    }
    
    /**
     * {@inheritDoc}
     */
    public BlogResource newResource(
        String name, String contentType, byte[] bytes) throws BlogClientException {
        if (resourcesCollection == null) { 
            throw new BlogClientException("No resource collection");
        }
        return resourcesCollection.newResource(name, contentType, bytes);
    }


    String saveResource(BlogResource res) throws BlogClientException {
        if (resourcesCollection == null) throw new BlogClientException("No primary resource collection");
        return resourcesCollection.saveResource(res);
    }
         
    void deleteResource(BlogResource resource) throws BlogClientException {
        deleteEntry((BlogEntry)resource);
    }   
    
    /**
     * {@inheritDoc}
     */
    public List getCollections() throws BlogClientException {
        return new ArrayList(collections.values());
    }

    /**
     * {@inheritDoc}
     */
    public Blog.Collection getCollection(String token) throws BlogClientException {
        return (Blog.Collection)collections.get(token);
    } 
    
    ClientAtomService getService() {
        return service;
    }

    void setService(ClientAtomService service) {
        this.service = service;
    }

    ClientWorkspace getWorkspace() {
        return workspace;
    }

    void setWorkspace(ClientWorkspace workspace) {
        this.workspace = workspace;
    }

}
