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
package com.rometools.propono.blogclient.metaweblog;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rometools.propono.blogclient.BaseBlogEntry;
import com.rometools.propono.blogclient.BlogClientException;
import com.rometools.propono.blogclient.BlogEntry;

/**
 * MetaWeblog API implementation of an entry.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class MetaWeblogEntry extends BaseBlogEntry {

    MetaWeblogEntry(final MetaWeblogBlog blog, final Map<String, Object> entryMap) {
        super(blog);
        id = (String) entryMap.get("postid");

        content = new Content((String) entryMap.get("description"));

        // let's pretend MetaWeblog API has a content-type
        content.setType("application/metaweblog+xml");

        // no way to tell if entry is draft or not
        draft = false;

        title = (String) entryMap.get("title");
        publicationDate = (Date) entryMap.get("dateCreated");
        permalink = (String) entryMap.get("permaLink");

        // AlexisMP: fix to get the author value populated.
        author.setName((String) entryMap.get("userid"));
        author.setEmail((String) entryMap.get("author"));

        categories = new ArrayList<Category>();
        final Object[] catArray = (Object[]) entryMap.get("categories");
        if (catArray != null) {
            for (final Object element : catArray) {
                final Category cat = new Category((String) element);
                categories.add(cat);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getToken() {
        return id;
    }

    /**
     * True if tokens are equal
     */
    @Override
    public boolean equals(final Object o) {
        if (o instanceof MetaWeblogEntry) {
            final MetaWeblogEntry other = (MetaWeblogEntry) o;
            if (other.id != null && id != null) {
                return other.id.equals(id);
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save() throws BlogClientException {
        id = ((MetaWeblogBlog) getBlog()).saveEntry(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete() throws BlogClientException {
        ((MetaWeblogBlog) getBlog()).deleteEntry(id);
    }

    Map<String, Object> toPostStructure() {
        final Map<String, Object> struct = new HashMap<String, Object>();
        if (getTitle() != null) {
            struct.put("title", getTitle());
        }
        if (getContent() != null && getContent().getValue() != null) {
            struct.put("description", getContent().getValue());
        }
        if (getCategories() != null && !getCategories().isEmpty()) {
            final List<String> catArray = new ArrayList<String>();
            final List<Category> cats = getCategories();
            for (int i = 0; i < cats.size(); i++) {
                final BlogEntry.Category cat = cats.get(i);
                catArray.add(cat.getName());
            }
            struct.put("categories", catArray);
        }
        if (getPublicationDate() != null) {
            struct.put("dateCreated", getPublicationDate());
        }
        if (getId() != null) {
            struct.put("postid", getId());
        }
        return struct;
    }
}
