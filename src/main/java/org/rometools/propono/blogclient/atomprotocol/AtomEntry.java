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
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rometools.propono.atom.client.ClientEntry;
import org.rometools.propono.atom.common.rome.AppModule;
import org.rometools.propono.atom.common.rome.AppModuleImpl;
import org.rometools.propono.blogclient.BaseBlogEntry;
import org.rometools.propono.blogclient.BlogClientException;
import org.rometools.propono.blogclient.BlogEntry;
import org.rometools.propono.utils.ProponoException;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.synd.SyndPerson;

/**
 * Atom protocol implementation of BlogEntry.
 */
public class AtomEntry extends BaseBlogEntry implements BlogEntry {
    static final Log logger = LogFactory.getLog(AtomCollection.class);

    String editURI = null;
    AtomCollection collection = null;

    AtomEntry(final AtomBlog blog, final AtomCollection collection) throws BlogClientException {
        super(blog);
        this.collection = collection;
    }

    AtomEntry(final AtomCollection collection, final ClientEntry entry) throws BlogClientException {
        this((AtomBlog) collection.getBlog(), collection);
        // clientEntry = entry;
        copyFromRomeEntry(entry);
    }

    AtomEntry(final AtomBlog blog, final ClientEntry entry) throws BlogClientException {
        super(blog);
        // clientEntry = entry;
        copyFromRomeEntry(entry);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getToken() {
        return editURI;
    }

    AtomCollection getCollection() {
        return collection;
    }

    void setCollection(final AtomCollection collection) {
        this.collection = collection;
    }

    /**
     * True if entry's token's are equal.
     */
    @Override
    public boolean equals(final Object o) {
        if (o instanceof AtomEntry) {
            final AtomEntry other = (AtomEntry) o;
            if (other.getToken() != null && getToken() != null) {
                return other.getToken().equals(getToken());
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save() throws BlogClientException {
        final boolean create = getToken() == null;
        if (create && getCollection() == null) {
            throw new BlogClientException("Cannot save entry, no collection");
        } else if (create) {
            try {
                final ClientEntry clientEntry = collection.getClientCollection().createEntry();
                copyToRomeEntry(clientEntry);
                collection.getClientCollection().addEntry(clientEntry);
                copyFromRomeEntry(clientEntry);
            } catch (final ProponoException ex) {
                throw new BlogClientException("Error saving entry", ex);
            }
        } else {
            try {
                final ClientEntry clientEntry = ((AtomBlog) getBlog()).getService().getEntry(getToken());
                copyToRomeEntry(clientEntry);
                clientEntry.update();
                copyFromRomeEntry(clientEntry);
            } catch (final ProponoException ex) {
                throw new BlogClientException("Error updating entry", ex);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete() throws BlogClientException {
        if (getToken() == null) {
            throw new BlogClientException("Cannot delete unsaved entry");
        }
        try {
            final ClientEntry clientEntry = ((AtomBlog) getBlog()).getService().getEntry(editURI);
            clientEntry.remove();
        } catch (final ProponoException ex) {
            throw new BlogClientException("Error removing entry", ex);
        }
    }

    void copyFromRomeEntry(final ClientEntry entry) {
        id = entry.getId();
        title = entry.getTitle();
        editURI = entry.getEditURI();
        final List<Link> altlinks = entry.getAlternateLinks();
        if (altlinks != null) {
            for (final Link link : altlinks) {
                if ("alternate".equals(link.getRel()) || link.getRel() == null) {
                    permalink = link.getHrefResolved();
                    break;
                }
            }
        }
        final List<com.sun.syndication.feed.atom.Content> contents = entry.getContents();
        com.sun.syndication.feed.atom.Content romeContent = null;
        if (contents != null && contents.size() > 0) {
            romeContent = contents.get(0);
        }
        if (romeContent != null) {
            content = new BlogEntry.Content(romeContent.getValue());
            content.setType(romeContent.getType());
            content.setSrc(romeContent.getSrc());
        }
        if (entry.getCategories() != null) {
            final List<Category> cats = new ArrayList<Category>();
            final List<com.sun.syndication.feed.atom.Category> romeCats = entry.getCategories();
            for (final com.sun.syndication.feed.atom.Category romeCat : romeCats) {
                final BlogEntry.Category cat = new BlogEntry.Category();
                cat.setId(romeCat.getTerm());
                cat.setUrl(romeCat.getScheme());
                cat.setName(romeCat.getLabel());
                cats.add(cat);
            }
            categories = cats;
        }
        final List<SyndPerson> authors = entry.getAuthors();
        if (authors != null && authors.size() > 0) {
            final com.sun.syndication.feed.atom.Person romeAuthor = (com.sun.syndication.feed.atom.Person) authors.get(0);
            if (romeAuthor != null) {
                author = new Person();
                author.setName(romeAuthor.getName());
                author.setEmail(romeAuthor.getEmail());
                author.setUrl(romeAuthor.getUrl());
            }
        }
        publicationDate = entry.getPublished();
        modificationDate = entry.getModified();

        final AppModule control = (AppModule) entry.getModule(AppModule.URI);
        if (control != null && control.getDraft() != null) {
            draft = control.getDraft().booleanValue();
        } else {
            draft = false;
        }
    }

    Entry copyToRomeEntry(final ClientEntry entry) {
        if (id != null) {
            entry.setId(id);
        }
        entry.setTitle(title);
        if (author != null) {
            final com.sun.syndication.feed.atom.Person person = new com.sun.syndication.feed.atom.Person();
            person.setName(author.getName());
            person.setEmail(author.getEmail());
            person.setUrl(author.getUrl());
            final List<SyndPerson> authors = new ArrayList<SyndPerson>();
            authors.add(person);
            entry.setAuthors(authors);
        }
        if (content != null) {
            final com.sun.syndication.feed.atom.Content romeContent = new com.sun.syndication.feed.atom.Content();
            romeContent.setValue(content.getValue());
            romeContent.setType(content.getType());
            final List<com.sun.syndication.feed.atom.Content> contents = new ArrayList<com.sun.syndication.feed.atom.Content>();
            contents.add(romeContent);
            entry.setContents(contents);
        }
        if (categories != null) {
            final List<com.sun.syndication.feed.atom.Category> romeCats = new ArrayList<com.sun.syndication.feed.atom.Category>();
            for (final Iterator<Category> iter = categories.iterator(); iter.hasNext();) {
                final BlogEntry.Category cat = iter.next();
                final com.sun.syndication.feed.atom.Category romeCategory = new com.sun.syndication.feed.atom.Category();
                romeCategory.setTerm(cat.getId());
                romeCategory.setScheme(cat.getUrl());
                romeCategory.setLabel(cat.getName());
                romeCats.add(romeCategory);
            }
            entry.setCategories(romeCats);
        }
        entry.setPublished(publicationDate == null ? new Date() : publicationDate);
        entry.setModified(modificationDate == null ? new Date() : modificationDate);

        final List<Module> modules = new ArrayList<Module>();
        final AppModule control = new AppModuleImpl();
        control.setDraft(new Boolean(draft));
        modules.add(control);
        entry.setModules(modules);

        return entry;
    }

}
