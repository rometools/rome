/*
 * Copyright 2007 Sun Microsystems, Inc.
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
package com.rometools.propono.atom.server.impl;

import java.io.File;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.propono.atom.common.AtomService;
import com.rometools.propono.atom.common.Categories;
import com.rometools.propono.atom.server.AtomException;
import com.rometools.propono.atom.server.AtomHandler;
import com.rometools.propono.atom.server.AtomMediaResource;
import com.rometools.propono.atom.server.AtomRequest;
import com.rometools.propono.atom.server.AtomServlet;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;

/**
 * File-based {@link com.rometools.rome.propono.atom.server.AtomHandler} implementation that stores
 * entries and media-entries to disk. Implemented using
 * {@link com.rometools.rome.propono.atom.server.impl.FileBasedAtomService}.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class FileBasedAtomHandler implements AtomHandler {

    private static final Logger LOG = LoggerFactory.getLogger(FileBasedAtomHandler.class);

    private String userName = null;
    private String atomProtocolURL = null;
    private String contextURI = null;
    private FileBasedAtomService service = null;

    /**
     * Construct handler to handle one request.
     *
     * @param req Request to be handled.
     */
    public FileBasedAtomHandler(final HttpServletRequest req) {
        this(req, AtomServlet.getContextDirPath());
    }

    /**
     * Contruct handler for one request, using specified file storage directory.
     *
     * @param req Request to be handled.
     * @param uploaddir File storage upload dir.
     */
    public FileBasedAtomHandler(final HttpServletRequest req, final String uploaddir) {
        LOG.debug("ctor");

        userName = authenticateBASIC(req);

        atomProtocolURL = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + req.getServletPath();

        contextURI = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();

        try {
            service = new FileBasedAtomService(userName, uploaddir, contextURI, req.getContextPath(), req.getServletPath());
        } catch (final Throwable t) {
            throw new RuntimeException("ERROR creating FileBasedAtomService", t);
        }
    }

    /**
     * Method used for validating user. Developers can overwrite this method and use credentials
     * stored in Database or LDAP to confirm if the user is allowed to access this service.
     *
     * @param login user submitted login id
     * @param password user submitted password
     */
    public boolean validateUser(final String login, final String password) {
        return true;
    }

    /**
     * Get username of authenticated user
     *
     * @return User name.
     */
    @Override
    public String getAuthenticatedUsername() {
        // For now return userName as the login id entered for authorization
        return userName;
    }

    /**
     * Get base URI of Atom protocol implementation.
     *
     * @return Base URI of Atom protocol implemenation.
     */
    public String getAtomProtocolURL() {
        if (atomProtocolURL == null) {
            return "app";
        } else {
            return atomProtocolURL;
        }
    }

    /**
     * Return introspection document
     *
     * @throws com.rometools.rome.propono.atom.server.AtomException Unexpected exception.
     * @return AtomService object with workspaces and collections.
     */
    @Override
    public AtomService getAtomService(final AtomRequest areq) throws AtomException {
        return service;
    }

    /**
     * Returns null because we use in-line categories.
     *
     * @throws com.rometools.rome.propono.atom.server.AtomException Unexpected exception.
     * @return Categories object
     */
    @Override
    public Categories getCategories(final AtomRequest areq) throws AtomException {
        LOG.debug("getCollection");
        final String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
        final String handle = pathInfo[0];
        final String collection = pathInfo[1];
        final FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        return col.getCategories(true).get(0);
    }

    /**
     * Get collection specified by pathinfo.
     *
     * @param areq Details of HTTP request
     * @return ROME feed representing collection.
     * @throws com.rometools.rome.propono.atom.server.AtomException Invalid collection or other
     *             exception.
     */
    @Override
    public Feed getCollection(final AtomRequest areq) throws AtomException {
        LOG.debug("getCollection");
        final String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
        final String handle = pathInfo[0];
        final String collection = pathInfo[1];
        final FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        return col.getFeedDocument();
    }

    /**
     * Create a new entry specified by pathInfo and posted entry. We save the submitted Atom entry
     * verbatim, but we do set the id and reset the update time.
     *
     * @param entry Entry to be added to collection.
     * @param areq Details of HTTP request
     * @throws com.rometools.rome.propono.atom.server.AtomException On invalid collection or other
     *             error.
     * @return Entry as represented on server.
     */
    @Override
    public Entry postEntry(final AtomRequest areq, final Entry entry) throws AtomException {
        LOG.debug("postEntry");

        final String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
        final String handle = pathInfo[0];
        final String collection = pathInfo[1];
        final FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        try {
            return col.addEntry(entry);

        } catch (final Exception fe) {
            fe.printStackTrace();
            throw new AtomException(fe);
        }
    }

    /**
     * Get entry specified by pathInfo.
     *
     * @param areq Details of HTTP request
     * @throws com.rometools.rome.propono.atom.server.AtomException On invalid pathinfo or other
     *             error.
     * @return ROME Entry object.
     */
    @Override
    public Entry getEntry(final AtomRequest areq) throws AtomException {
        LOG.debug("getEntry");
        final String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
        final String handle = pathInfo[0];
        final String collection = pathInfo[1];
        final String fileName = pathInfo[2];
        final FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        try {
            return col.getEntry(fileName);

        } catch (final Exception re) {
            if (re instanceof AtomException) {
                throw (AtomException) re;
            }
            throw new AtomException("ERROR: getting entry", re);
        }
    }

    /**
     * Update entry specified by pathInfo and posted entry.
     *
     * @param entry
     * @param areq Details of HTTP request
     * @throws com.rometools.rome.propono.atom.server.AtomException
     */
    @Override
    public void putEntry(final AtomRequest areq, final Entry entry) throws AtomException {
        LOG.debug("putEntry");
        final String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
        final String handle = pathInfo[0];
        final String collection = pathInfo[1];
        final String fileName = pathInfo[2];
        final FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        try {
            col.updateEntry(entry, fileName);

        } catch (final Exception fe) {
            throw new AtomException(fe);
        }
    }

    /**
     * Delete entry specified by pathInfo.
     *
     * @param areq Details of HTTP request
     */
    @Override
    public void deleteEntry(final AtomRequest areq) throws AtomException {
        LOG.debug("deleteEntry");
        final String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
        final String handle = pathInfo[0];
        final String collection = pathInfo[1];
        final String fileName = pathInfo[2];
        final FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        try {
            col.deleteEntry(fileName);

        } catch (final Exception e) {
            final String msg = "ERROR in atom.deleteResource";
            LOG.error(msg, e);
            throw new AtomException(msg);
        }
    }

    /**
     * Store media data in collection specified by pathInfo, create an Atom media-link entry to
     * store metadata for the new media file and return that entry to the caller.
     *
     * @param areq Details of HTTP request
     * @param entry New entry initialzied with only title and content type
     * @return Location URL of new media entry
     */
    @Override
    public Entry postMedia(final AtomRequest areq, final Entry entry) throws AtomException {

        // get incoming slug from HTTP header
        final String slug = areq.getHeader("Slug");

        if (LOG.isDebugEnabled()) {
            LOG.debug("postMedia - title: " + entry.getTitle() + " slug:" + slug);
        }

        try {
            final File tempFile = null;
            final String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
            final String handle = pathInfo[0];
            final String collection = pathInfo[1];
            final FileBasedCollection col = service.findCollectionByHandle(handle, collection);
            try {
                col.addMediaEntry(entry, slug, areq.getInputStream());

            } catch (final Exception e) {
                e.printStackTrace();
                final String msg = "ERROR reading posted file";
                LOG.error(msg, e);
                throw new AtomException(msg, e);
            } finally {
                if (tempFile != null) {
                    tempFile.delete();
                }
            }

        } catch (final Exception re) {
            throw new AtomException("ERROR: posting media");
        }
        return entry;
    }

    /**
     * Update the media file part of a media-link entry.
     *
     * @param areq Details of HTTP request Assuming pathInfo of form /user-name/resource/name
     */
    @Override
    public void putMedia(final AtomRequest areq) throws AtomException {

        LOG.debug("putMedia");
        final String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
        final String handle = pathInfo[0];
        final String collection = pathInfo[1];
        final String fileName = pathInfo[3];
        final FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        try {
            col.updateMediaEntry(fileName, areq.getContentType(), areq.getInputStream());

        } catch (final Exception re) {
            throw new AtomException("ERROR: posting media");
        }
    }

    @Override
    public AtomMediaResource getMediaResource(final AtomRequest areq) throws AtomException {
        LOG.debug("putMedia");
        final String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
        final String handle = pathInfo[0];
        final String collection = pathInfo[1];
        final String fileName = pathInfo[3];
        final FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        try {
            return col.getMediaResource(fileName);

        } catch (final Exception re) {
            throw new AtomException("ERROR: posting media");
        }
    }

    /**
     * Return true if specified pathinfo represents URI of service doc.
     */
    @Override
    public boolean isAtomServiceURI(final AtomRequest areq) {
        final String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
        if (pathInfo.length == 0) {
            return true;
        }
        return false;
    }

    /**
     * Return true if specified pathinfo represents URI of category doc.
     */
    @Override
    public boolean isCategoriesURI(final AtomRequest areq) {
        LOG.debug("isCategoriesDocumentURI");
        final String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
        if (pathInfo.length == 3 && "categories".equals(pathInfo[2])) {
            return true;
        }
        return false;
    }

    /**
     * Return true if specified pathinfo represents URI of a collection.
     */
    @Override
    public boolean isCollectionURI(final AtomRequest areq) {
        LOG.debug("isCollectionURI");
        // workspace/collection-plural
        // if length is 2 and points to a valid collection then YES
        final String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
        if (pathInfo.length == 2) {
            final String handle = pathInfo[0];
            final String collection = pathInfo[1];
            if (service.findCollectionByHandle(handle, collection) != null) {
                return true;
            }
        }
        return false;

    }

    /**
     * Return true if specified pathinfo represents URI of an Atom entry.
     */
    @Override
    public boolean isEntryURI(final AtomRequest areq) {
        LOG.debug("isEntryURI");
        // workspace/collection-singular/fsid
        // if length is 3 and points to a valid collection then YES
        final String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
        if (pathInfo.length == 3) {
            final String handle = pathInfo[0];
            final String collection = pathInfo[1];
            if (service.findCollectionByHandle(handle, collection) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if specified pathinfo represents media-edit URI.
     */
    @Override
    public boolean isMediaEditURI(final AtomRequest areq) {
        LOG.debug("isMediaEditURI");
        // workspace/collection-singular/fsid/media/fsid
        // if length is 4, points to a valid collection and fsid is mentioned
        // twice then YES
        final String[] pathInfo = StringUtils.split(areq.getPathInfo(), "/");
        if (pathInfo.length == 4) {
            final String handle = pathInfo[0];
            final String collection = pathInfo[1];
            final String media = pathInfo[2];
            // final String fsid = pathInfo[3];
            if (service.findCollectionByHandle(handle, collection) != null && media.equals("media")) {
                return true;
            }
        }
        return false;

    }

    /**
     * BASIC authentication.
     */
    public String authenticateBASIC(final HttpServletRequest request) {
        LOG.debug("authenticateBASIC");
        boolean valid = false;
        String userID = null;
        String password = null;
        try {
            final String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                final StringTokenizer st = new StringTokenizer(authHeader);
                if (st.hasMoreTokens()) {
                    final String basic = st.nextToken();
                    if (basic.equalsIgnoreCase("Basic")) {
                        final String credentials = st.nextToken();
                        final String userPass = new String(Base64.decodeBase64(credentials.getBytes()));
                        final int p = userPass.indexOf(":");
                        if (p != -1) {
                            userID = userPass.substring(0, p);
                            password = userPass.substring(p + 1);

                            // Validate the User.
                            valid = validateUser(userID, password);
                        }
                    }
                }
            }
        } catch (final Exception e) {
            LOG.debug("An error occured while processing Basic authentication", e);
        }
        if (valid) {
            // For now assume userID as userName
            return userID;
        }
        return null;
    }
}
