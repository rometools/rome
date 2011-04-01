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

import com.sun.syndication.feed.atom.Category;
import org.rometools.propono.atom.client.ClientAtomService;
import org.rometools.propono.atom.common.Categories;
import org.rometools.propono.atom.client.ClientCollection;
import org.rometools.propono.atom.client.ClientEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rometools.propono.blogclient.Blog;
import org.rometools.propono.blogclient.BlogClientException;
import org.rometools.propono.blogclient.BlogEntry;
import org.rometools.propono.blogclient.BlogResource;

/**
 * Atom protocol implementation of BlogClient Blog.Collection.
 */
public class AtomCollection implements Blog.Collection {
    static final Log logger = LogFactory.getLog(AtomCollection.class);
    
    private Blog blog = null;
    private List categories = new ArrayList();

    private ClientCollection clientCollection = null;


    AtomCollection(AtomBlog blog, ClientCollection col) {
        this.blog = blog; 
        this.clientCollection = col;
        for (Iterator catsIter = col.getCategories().iterator(); catsIter.hasNext();) {
            Categories cats = (Categories)catsIter.next();
            for (Iterator catIter = cats.getCategories().iterator(); catIter.hasNext();) {
                Category cat = (Category)catIter.next();
                BlogEntry.Category blogCat = new BlogEntry.Category(cat.getTerm());
                blogCat.setName(cat.getLabel());
                blogCat.setUrl(cat.getScheme());
                getCategories().add(blogCat);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getTitle() {
        return getClientCollection().getTitle();
    }

    /**
     * {@inheritDoc}
     */
    public String getToken() {
        return getClientCollection().getHrefResolved();
    }

    /**
     * {@inheritDoc}
     */
    public List getAccepts() {
        return getClientCollection().getAccepts();
    }

    /**
     * {@inheritDoc}
     */
    public boolean accepts(String ct) {
        return getClientCollection().accepts(ct);
    }

    /**
     * {@inheritDoc}
     */
    public Iterator getEntries() throws BlogClientException {
        return new AtomEntryIterator(this); 
    }
    
    /**
     * {@inheritDoc}
     */
    public BlogEntry newEntry() throws BlogClientException {
        AtomBlog atomBlog = (AtomBlog)getBlog();
        BlogEntry entry = new AtomEntry(atomBlog, this);
        return entry;        
    }
        
    /**
     * {@inheritDoc}
     */
    public BlogResource newResource(String name, String contentType, byte[] bytes) throws BlogClientException {
        return new AtomResource(this, name, contentType, bytes);
    }

    /**
     * {@inheritDoc}
     */
    public String saveResource(BlogResource res) throws BlogClientException {
        ((AtomResource)res).setCollection(this);
        res.save();
        return res.getContent().getSrc();
    }

    /**
     * {@inheritDoc}
     */
    public String saveEntry(BlogEntry entry) throws BlogClientException {
        ((AtomEntry)entry).setCollection(this);
        entry.save();
        return entry.getPermalink();
    }

    void deleteEntry(BlogEntry entry) throws BlogClientException {
        try {       
            ClientAtomService service = ((AtomBlog)getBlog()).getService();
            ClientEntry clientEntry = service.getEntry(entry.getToken());
            clientEntry.remove();

        } catch (Exception e) {
            throw new BlogClientException("ERROR deleting entry", e);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public Blog getBlog() {
        return blog;
    }

    void setBlog(AtomBlog blog) {
        this.blog = blog;
    }

    /**
     * {@inheritDoc}
     */
    public List getCategories() {
        return categories;
    }

    void setCategories(List categories) {
        this.categories = categories;
    }

    ClientCollection getClientCollection() {
        return clientCollection;
    }
}
