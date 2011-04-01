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

import com.sun.syndication.propono.utils.ProponoException;
import com.sun.syndication.propono.atom.common.rome.AppModule;
import com.sun.syndication.propono.atom.common.rome.AppModuleImpl;
import com.sun.syndication.propono.blogclient.BlogClientException;
import com.sun.syndication.propono.blogclient.BlogEntry;
import com.sun.syndication.propono.blogclient.BaseBlogEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.propono.atom.client.ClientEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sun.syndication.propono.blogclient.BlogEntry.Person;

/**
 * Atom protocol implementation of BlogEntry.
 */
public class AtomEntry extends BaseBlogEntry implements BlogEntry {
    static final Log logger = LogFactory.getLog(AtomCollection.class);
    
    String editURI = null;
    AtomCollection collection = null;
            
    AtomEntry(AtomBlog blog, AtomCollection collection) throws BlogClientException {
        super(blog);
        this.collection = collection;
    }
    
    AtomEntry(AtomCollection collection, ClientEntry entry)  throws BlogClientException {   
        this((AtomBlog)collection.getBlog(), collection);
        //clientEntry = entry;
        copyFromRomeEntry(entry);
    }
    
    AtomEntry(AtomBlog blog, ClientEntry entry)  throws BlogClientException {   
        super(blog);
        //clientEntry = entry;
        copyFromRomeEntry(entry);
    }
    
    /**
     * {@inheritDoc}
     */
    public String getToken() {
        return editURI;
    }
    
    AtomCollection getCollection() {
        return collection;
    }

    void setCollection(AtomCollection collection) {
        this.collection = collection;
    }

    /**
     * True if entry's token's are equal.
     */
    public boolean equals(Object o) {
        if (o instanceof AtomEntry) {
            AtomEntry other = (AtomEntry)o;
            if (other.getToken() != null && getToken() != null) {
                return other.getToken().equals(getToken());
            }
        }
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    public void save() throws BlogClientException {
        boolean create = (getToken() == null);
        if (create && getCollection() == null) {
            throw new BlogClientException("Cannot save entry, no collection");
        } else if (create) {
            try {
                ClientEntry clientEntry = collection.getClientCollection().createEntry();
                copyToRomeEntry(clientEntry);
                collection.getClientCollection().addEntry(clientEntry);
                copyFromRomeEntry(clientEntry);
            } catch (ProponoException ex) {
                 throw new BlogClientException("Error saving entry", ex);
            }
        } else {
            try {
                ClientEntry clientEntry = ((AtomBlog)getBlog()).getService().getEntry(getToken());
                copyToRomeEntry(clientEntry);
                clientEntry.update();
                copyFromRomeEntry(clientEntry);
            } catch (ProponoException ex) {
                 throw new BlogClientException("Error updating entry", ex);
            }
        }   
    }
    
    /**
     * {@inheritDoc}
     */
    public void delete() throws BlogClientException {
        if (getToken() == null) {
            throw new BlogClientException("Cannot delete unsaved entry");
        }
        try {
            ClientEntry clientEntry = ((AtomBlog)getBlog()).getService().getEntry(editURI);
            clientEntry.remove();
        } catch (ProponoException ex) {
             throw new BlogClientException("Error removing entry", ex);
        }
    }
        
    void copyFromRomeEntry(ClientEntry entry) {
        id = entry.getId();
        title = entry.getTitle();   
        editURI = entry.getEditURI();
        List altlinks = entry.getAlternateLinks();
        if (altlinks != null) {
            for (Iterator iter = altlinks.iterator(); iter.hasNext();) {
                Link link = (Link)iter.next();
                if ("alternate".equals(link.getRel()) || link.getRel()==null) {
                    permalink = link.getHrefResolved();
                    break;
                }
            }
        }
        List contents = entry.getContents();
        com.sun.syndication.feed.atom.Content romeContent = null;
        if (contents != null && contents.size() > 0) {
            romeContent = (com.sun.syndication.feed.atom.Content)contents.get(0);
        }
        if (romeContent != null) {
            content = new BlogEntry.Content(romeContent.getValue());
            content.setType(romeContent.getType());
            content.setSrc(romeContent.getSrc());
        }  
        if (entry.getCategories() != null) {
            List cats = new ArrayList();
            List romeCats = entry.getCategories();
            for (Iterator iter=romeCats.iterator(); iter.hasNext();) {
                com.sun.syndication.feed.atom.Category romeCat = 
                    (com.sun.syndication.feed.atom.Category)iter.next();
                BlogEntry.Category cat = new BlogEntry.Category();
                cat.setId(romeCat.getTerm());
                cat.setUrl(romeCat.getScheme());
                cat.setName(romeCat.getLabel());
                cats.add(cat);
            }
            categories = cats;
        }
        List authors = entry.getAuthors();
        if (authors!=null && authors.size() > 0) {
            com.sun.syndication.feed.atom.Person romeAuthor = 
                (com.sun.syndication.feed.atom.Person)authors.get(0);
            if (romeAuthor != null) {
                author = new Person();
                author.setName(romeAuthor.getName());
                author.setEmail(romeAuthor.getEmail());
                author.setUrl(romeAuthor.getUrl());
            }    
        }    
        publicationDate = entry.getPublished();
        modificationDate = entry.getModified();
        
        AppModule control = (AppModule)entry.getModule(AppModule.URI);
        if (control != null && control.getDraft() != null) { 
            draft = control.getDraft().booleanValue(); 
        } else {
            draft = false;
        }
    }
    Entry copyToRomeEntry(ClientEntry entry) {
        if (id != null) {
            entry.setId(id);
        }
        entry.setTitle(title);        
        if (author != null) {
            com.sun.syndication.feed.atom.Person person = 
                new com.sun.syndication.feed.atom.Person();
            person.setName(author.getName());
            person.setEmail(author.getEmail());
            person.setUrl(author.getUrl());
            List authors = new ArrayList();
            authors.add(person);
            entry.setAuthors(authors);
        }
        if (content != null) {
            com.sun.syndication.feed.atom.Content romeContent = 
                new com.sun.syndication.feed.atom.Content();
            romeContent.setValue(content.getValue());
            romeContent.setType(content.getType());
            List contents = new ArrayList();
            contents.add(romeContent);
            entry.setContents(contents);
        }
        if (categories != null) {
            List romeCats = new ArrayList();
            for (Iterator iter=categories.iterator(); iter.hasNext();) {
                BlogEntry.Category cat = (BlogEntry.Category)iter.next();
                com.sun.syndication.feed.atom.Category romeCategory =
                    new com.sun.syndication.feed.atom.Category();
                romeCategory.setTerm(cat.getId());
                romeCategory.setScheme(cat.getUrl());
                romeCategory.setLabel(cat.getName());
                romeCats.add(romeCategory);
            }
            entry.setCategories(romeCats);
        }
        entry.setPublished((publicationDate == null) ? new Date() : publicationDate);
        entry.setModified((modificationDate == null) ? new Date() : modificationDate);
        
        List modules = new ArrayList();
        AppModule control = new AppModuleImpl();
        control.setDraft(new Boolean(draft));
        modules.add(control);
        entry.setModules(modules);
        
        return entry;
    }

}
