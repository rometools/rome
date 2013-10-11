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
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rometools.propono.atom.client.ClientAtomService;
import org.rometools.propono.atom.client.ClientCollection;
import org.rometools.propono.atom.client.ClientEntry;
import org.rometools.propono.atom.common.Categories;
import org.rometools.propono.blogclient.Blog;
import org.rometools.propono.blogclient.BlogClientException;
import org.rometools.propono.blogclient.BlogEntry;
import org.rometools.propono.blogclient.BlogResource;

import com.sun.syndication.feed.atom.Category;

/**
 * Atom protocol implementation of BlogClient Blog.Collection.
 */
public class AtomCollection implements Blog.Collection {
    static final Log logger = LogFactory.getLog(AtomCollection.class);

    private Blog blog = null;
    private List categories = new ArrayList();

    private ClientCollection clientCollection = null;

    AtomCollection(final AtomBlog blog, final ClientCollection col) {
        this.blog = blog;
        clientCollection = col;
        for (final Iterator catsIter = col.getCategories().iterator(); catsIter.hasNext();) {
            final Categories cats = (Categories) catsIter.next();
            for (final Iterator catIter = cats.getCategories().iterator(); catIter.hasNext();) {
                final Category cat = (Category) catIter.next();
                final BlogEntry.Category blogCat = new BlogEntry.Category(cat.getTerm());
                blogCat.setName(cat.getLabel());
                blogCat.setUrl(cat.getScheme());
                getCategories().add(blogCat);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return getClientCollection().getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getToken() {
        return getClientCollection().getHrefResolved();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List getAccepts() {
        return getClientCollection().getAccepts();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean accepts(final String ct) {
        return getClientCollection().accepts(ct);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator getEntries() throws BlogClientException {
        return new AtomEntryIterator(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlogEntry newEntry() throws BlogClientException {
        final AtomBlog atomBlog = (AtomBlog) getBlog();
        final BlogEntry entry = new AtomEntry(atomBlog, this);
        return entry;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlogResource newResource(final String name, final String contentType, final byte[] bytes) throws BlogClientException {
        return new AtomResource(this, name, contentType, bytes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveResource(final BlogResource res) throws BlogClientException {
        ((AtomResource) res).setCollection(this);
        res.save();
        return res.getContent().getSrc();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveEntry(final BlogEntry entry) throws BlogClientException {
        ((AtomEntry) entry).setCollection(this);
        entry.save();
        return entry.getPermalink();
    }

    void deleteEntry(final BlogEntry entry) throws BlogClientException {
        try {
            final ClientAtomService service = ((AtomBlog) getBlog()).getService();
            final ClientEntry clientEntry = service.getEntry(entry.getToken());
            clientEntry.remove();

        } catch (final Exception e) {
            throw new BlogClientException("ERROR deleting entry", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Blog getBlog() {
        return blog;
    }

    void setBlog(final AtomBlog blog) {
        this.blog = blog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List getCategories() {
        return categories;
    }

    void setCategories(final List categories) {
        this.categories = categories;
    }

    ClientCollection getClientCollection() {
        return clientCollection;
    }
}
