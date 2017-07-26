/*
 * Copyright 2007 Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package com.rometools.propono.atom.server;

import com.rometools.propono.atom.common.AtomService;
import com.rometools.propono.atom.common.Categories;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;

/**
 * Interface for handling single Atom protocol requests.
 *
 * <p>
 * To create your own Atom protocol implementation you must implement this interface and create a
 * concrete sub-class of {@link com.rometools.rome.propono.atom.server.AtomHandlerFactory} which is
 * capable of instantiating it.
 * </p>
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public interface AtomHandler {
    /**
     * Get username of authenticated user. Return the username of the authenticated user
     */
    public String getAuthenticatedUsername();

    /**
     * Return {@link com.rometools.rome.propono.atom.common.AtomService} object that contains the
     * {@link com.rometools.rome.propono.atom.common.Workspace} objects available to the currently
     * authenticated user and within those the
     * {@link com.rometools.rome.propono.atom.common.Collection} avalaible.
     */
    public AtomService getAtomService(AtomRequest req) throws AtomException;

    /**
     * Get categories, a list of Categories objects
     */
    public Categories getCategories(AtomRequest req) throws AtomException;

    /**
     * Return collection or portion of collection specified by request.
     *
     * @param req Details of HTTP request
     */
    public Feed getCollection(AtomRequest req) throws AtomException;

    /**
     * Store new entry in collection specified by request and return representation of entry as it
     * is stored on server.
     *
     * @param req Details of HTTP request
     * @return Location URL of new entry
     */
    public Entry postEntry(AtomRequest req, Entry entry) throws AtomException;

    /**
     * Get entry specified by request.
     *
     * @param req Details of HTTP request
     */
    public Entry getEntry(AtomRequest req) throws AtomException;

    /**
     * Get media resource specified by request.
     *
     * @param req Details of HTTP request
     */
    public AtomMediaResource getMediaResource(AtomRequest req) throws AtomException;

    /**
     * Update entry specified by request and return new entry as represented on the server.
     *
     * @param req Details of HTTP request
     */
    public void putEntry(AtomRequest req, Entry entry) throws AtomException;

    /**
     * Delete entry specified by request.
     *
     * @param req Details of HTTP request
     */
    public void deleteEntry(AtomRequest req) throws AtomException;

    /**
     * Store media data in collection specified by request, create an Atom media-link entry to store
     * metadata for the new media file and return that entry to the caller.
     *
     * @param req Details of HTTP request
     * @param entry New entry initialzied with only title and content type
     * @return Location URL of new media entry
     */
    public Entry postMedia(AtomRequest req, Entry entry) throws AtomException;

    /**
     * Update the media file part of a media-link entry.
     *
     * @param req Details of HTTP request
     */
    public void putMedia(AtomRequest req) throws AtomException;

    /**
     * Return true if specified request represents URI of a Service Document.
     *
     * @param req Details of HTTP request
     */
    public boolean isAtomServiceURI(AtomRequest req);

    /**
     * Return true if specified request represents URI of a Categories Document.
     *
     * @param req Details of HTTP request
     */
    public boolean isCategoriesURI(AtomRequest req);

    /**
     * Return true if specified request represents URI of a collection.
     *
     * @param req Details of HTTP request
     */
    public boolean isCollectionURI(AtomRequest req);

    /**
     * Return true if specified request represents URI of an Atom entry.
     *
     * @param req Details of HTTP request
     */
    public boolean isEntryURI(AtomRequest req);

    /**
     * Return true if specified patrequesthinfo represents media-edit URI.
     *
     * @param req Details of HTTP request
     */
    public boolean isMediaEditURI(AtomRequest req);
}
