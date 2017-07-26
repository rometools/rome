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
package com.rometools.propono.blogclient.atomprotocol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.rometools.propono.atom.client.ClientAtomService;
import com.rometools.propono.atom.client.ClientCollection;
import com.rometools.propono.atom.client.ClientEntry;
import com.rometools.propono.atom.client.ClientMediaEntry;
import com.rometools.propono.atom.client.ClientWorkspace;
import com.rometools.propono.blogclient.Blog;
import com.rometools.propono.blogclient.BlogClientException;
import com.rometools.propono.blogclient.BlogEntry;
import com.rometools.propono.blogclient.BlogResource;
import com.rometools.propono.utils.ProponoException;

/**
 * Atom protocol implementation of the BlogClient Blog interface.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class AtomBlog implements Blog {

    private String name = null;
    private ClientAtomService service;
    private ClientWorkspace workspace = null;
    private AtomCollection entriesCollection = null;
    private AtomCollection resourcesCollection = null;
    private final Map<String, AtomCollection> collections = new TreeMap<String, AtomCollection>();

    /**
     * Create AtomBlog using specified HTTPClient, user account and workspace, called by
     * AtomConnection. Fetches Atom Service document and creates an AtomCollection object for each
     * collection found. The first entry collection is considered the primary entry collection. And
     * the first resource collection is considered the primary resource collection.
     */
    AtomBlog(final ClientAtomService service, final ClientWorkspace workspace) {

        setService(service);
        setWorkspace(workspace);
        name = workspace.getTitle();
        final List<com.rometools.propono.atom.common.Collection> collect = workspace.getCollections();
        final Iterator<com.rometools.propono.atom.common.Collection> members = collect.iterator();

        while (members.hasNext()) {
            final ClientCollection col = (ClientCollection) members.next();
            if (col.accepts("entry") && entriesCollection == null) {
                // first entry collection is primary entry collection
                entriesCollection = new AtomCollection(this, col);
            } else if (!col.accepts("entry") && resourcesCollection == null) {
                // first non-entry collection is primary resource collection
                resourcesCollection = new AtomCollection(this, col);
            }
            collections.put(col.getHrefResolved(), new AtomCollection(this, col));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * String display of blog, returns name.
     */
    @Override
    public String toString() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getToken() {
        return entriesCollection.getToken();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlogEntry newEntry() throws BlogClientException {
        if (entriesCollection == null) {
            throw new BlogClientException("No entry collection");
        }
        return entriesCollection.newEntry();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlogEntry getEntry(final String token) throws BlogClientException {
        ClientEntry clientEntry = null;
        try {
            clientEntry = getService().getEntry(token);
        } catch (final ProponoException ex) {
            throw new BlogClientException("ERROR: fetching entry", ex);
        }
        if (clientEntry != null && clientEntry instanceof ClientMediaEntry) {
            return new AtomResource(this, (ClientMediaEntry) clientEntry);
        } else if (clientEntry != null && clientEntry instanceof ClientEntry) {
            return new AtomEntry(this, clientEntry);
        } else {
            throw new BlogClientException("ERROR: unknown object type returned");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<BlogEntry> getEntries() throws BlogClientException {
        if (entriesCollection == null) {
            throw new BlogClientException("No primary entry collection");
        }
        return new AtomEntryIterator(entriesCollection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<BlogEntry> getResources() throws BlogClientException {
        if (resourcesCollection == null) {
            throw new BlogClientException("No primary entry collection");
        }
        return new AtomEntryIterator(resourcesCollection);
    }

    String saveEntry(final BlogEntry entry) throws BlogClientException {
        if (entriesCollection == null) {
            throw new BlogClientException("No primary entry collection");
        }
        return entriesCollection.saveEntry(entry);
    }

    void deleteEntry(final BlogEntry entry) throws BlogClientException {
        if (entriesCollection == null) {
            throw new BlogClientException("No primary entry collection");
        }
        entriesCollection.deleteEntry(entry);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BlogEntry.Category> getCategories() throws BlogClientException {
        if (entriesCollection == null) {
            throw new BlogClientException("No primary entry collection");
        }
        return entriesCollection.getCategories();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlogResource newResource(final String name, final String contentType, final byte[] bytes) throws BlogClientException {
        if (resourcesCollection == null) {
            throw new BlogClientException("No resource collection");
        }
        return resourcesCollection.newResource(name, contentType, bytes);
    }

    String saveResource(final BlogResource res) throws BlogClientException {
        if (resourcesCollection == null) {
            throw new BlogClientException("No primary resource collection");
        }
        return resourcesCollection.saveResource(res);
    }

    void deleteResource(final BlogResource resource) throws BlogClientException {
        deleteEntry(resource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Collection> getCollections() throws BlogClientException {
        return new ArrayList<Collection>(collections.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Blog.Collection getCollection(final String token) throws BlogClientException {
        return collections.get(token);
    }

    ClientAtomService getService() {
        return service;
    }

    void setService(final ClientAtomService service) {
        this.service = service;
    }

    ClientWorkspace getWorkspace() {
        return workspace;
    }

    void setWorkspace(final ClientWorkspace workspace) {
        this.workspace = workspace;
    }

}
