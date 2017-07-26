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
package com.rometools.propono.blogclient;

import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * Represents a blog, which has collections of entries and resources. You can access the collections
 * using the getCollections() and getCollection(String name) methods, which return Blog.Collection
 * objects, which you can use to create, retrieve update or delete entries within a collection.
 * </p>
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public interface Blog {

    /**
     * Token can be used to fetch this blog again from getBlog() method.
     *
     * @return Blog object specified by token.
     */
    public String getToken();

    /**
     * Name of this blog.
     *
     * @return Display name of this blog.
     */
    public String getName();

    /**
     * Get a single BlogEntry (or BlogResource) by token.
     *
     * @param token Token from blog entry's getToken() method.
     * @throws com.rometools.rome.propono.blogclient.BlogClientException On error fetching the blog
     *             entry.
     * @return Blog entry specified by token.
     */
    public BlogEntry getEntry(String token) throws BlogClientException;

    /**
     * Gets listing of entry and resource collections available in the blog, including the primary
     * collections.
     *
     * @throws BlogClientException On error fetching collections.
     * @return List of Blog.Collection objects.
     */
    public List<Collection> getCollections() throws BlogClientException;

    /**
     * Get collection by token.
     *
     * @param token Token from a collection's getToken() method.
     * @throws BlogClientException On error fetching collection.
     * @return Blog.Collection object.
     */
    public Collection getCollection(String token) throws BlogClientException;

    /**
     * Represents an entry or resource collection on a blog server.
     */
    public interface Collection {

        /**
         * Get blog that contains this collection.
         *
         * @return Blog that contains this collection.
         */
        public Blog getBlog();

        /**
         * Title of collection.
         *
         * @return Title of collecton.
         */
        public String getTitle();

        /**
         * Token that can be used to fetch collection.
         *
         * @return Token that can be used to fetch collection.
         */
        public String getToken();

        /**
         * Content-types accepted by collection.
         *
         * @return Comma-separated list of content-types accepted.
         */
        public List<String> getAccepts();

        /**
         * Determines if collection will accept a content-type.
         *
         * @param contentType Content-type to be considered.
         * @return True of content type will be accepted, false otherwise.
         */
        public boolean accepts(String contentType);

        /**
         * Return categories allowed by colletion.
         *
         * @throws BlogClientException On error fetching categories.
         * @return List of BlogEntry.Category objects for this collection.
         */
        public List<BlogEntry.Category> getCategories() throws BlogClientException;

        /**
         * Create but do not save new entry in collection. To save entry, call its save() method.
         *
         * @throws BlogClientException On error creating entry.
         * @return New BlogEntry object.
         */
        public BlogEntry newEntry() throws BlogClientException;

        /**
         * Create but do not save new resource in collection. To save resource, call its save()
         * method.
         *
         * @param name Name of new resource.
         * @param contentType MIME content-type of new resource.
         * @param bytes Data for new resource.
         * @throws BlogClientException On error creating entry.
         * @return New BlogResource object,
         */
        public BlogResource newResource(String name, String contentType, byte[] bytes) throws BlogClientException;

        /**
         * Get iterator over entries/resources in this collection.
         *
         * @return List of BlogEntry objects, some may be BlogResources.
         * @throws BlogClientException On error fetching entries/resources.
         */
        public Iterator<BlogEntry> getEntries() throws BlogClientException;

        /**
         * Save or update a BlogEntry in this collection by adding it to this collection and then
         * calling it's entry.save() method.
         *
         * @param entry BlogEntry to be saved.
         * @throws BlogClientException On error saving entry.
         * @return URI of entry.
         */
        public String saveEntry(BlogEntry entry) throws BlogClientException;

        /**
         * Save or update resource in this collection
         *
         * @param resource BlogResource to be saved.
         * @throws BlogClientException On error saving resource.
         * @return URI of resource.
         */
        public String saveResource(BlogResource resource) throws BlogClientException;
    }

    // Deprecated primary collection methods, instead use collections directly.

    /**
     * Get iterator over entries in primary entries collection (the first collection that accepts
     * entries). Note that entries may be partial, so don't try to update and save them: to update
     * and entry, first fetch it with getEntry(), change fields, then call entry.save();
     *
     * @return To iterate over all entries in collection.
     * @throws BlogClientException On failure or if there is no primary entries collection.
     *
     * @deprecated Instead use collections directly.
     */
    @Deprecated
    public Iterator<BlogEntry> getEntries() throws BlogClientException;

    /**
     * Get entries in primary resources collection (the first collection that accepts anything other
     * than entries).
     *
     * @throws BlogClientException On failure or if there is no primary resources collection.
     * @return To iterate over all resojrces in collection.
     *
     * @deprecated Instead use collections directly.
     */
    @Deprecated
    public Iterator<BlogEntry> getResources() throws BlogClientException;

    /**
     * Create but do not save it to server new BlogEntry in primary entries collection (the first
     * collection found that accepts entries). To save the entry to the server to a collection, use
     * the entry's save() method.
     *
     * @throws BlogClientException On error or if there is no primary entries collection.
     * @return Unsaved BlogEntry in primary entries collection.
     *
     * @deprecated Instead use collections directly.
     */
    @Deprecated
    public BlogEntry newEntry() throws BlogClientException;

    /**
     * Create but do not save it to server new BlogResource in primary resources collection (the
     * first collection found that accepts resources). To save the resource to the server to a
     * collection, use the resource's save() method.
     *
     * @param name Name of resource to be saved.
     * @param type MIME content type of resource data.
     * @param bytes Bytes of resource data.
     * @throws BlogClientException On error or if there is no primary respurces collection.
     * @return Unsaved BlogEntry in primary resources collection.
     *
     * @deprecated Instead use collections directly.
     */
    @Deprecated
    public BlogResource newResource(String name, String type, byte[] bytes) throws BlogClientException;

    /**
     * Returns list of available BlogEntry.Category in primary entries collection.
     *
     * @throws BlogClientException On error or if there is no primary entries collection.
     * @return List of BlogEntry.Category objects.
     *
     * @deprecated Instead use collections directly.
     */
    @Deprecated
    public List<BlogEntry.Category> getCategories() throws BlogClientException;
}
