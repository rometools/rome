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

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import com.rometools.propono.atom.common.AtomService;
import com.rometools.propono.utils.Utilities;

/**
 * File based Atom service. Supports one workspace per user. Collections in workspace are defined in
 * /propono.properties, for example:
 *
 * <pre>
 *    # Define list of collections to be offered
 *    propono.atomserver.filebased.collections=entries,gifimages
 * 
 *    # Defines 'entries' collection, accepts entries
 *    propono.atomserver.filebased.collection.entries.title=Entries
 *    propono.atomserver.filebased.collection.entries.singular=entry
 *    propono.atomserver.filebased.collection.entries.plural=entries
 *    propono.atomserver.filebased.collection.entries.accept=application/atom+xml;type=entry
 *    propono.atomserver.filebased.collection.entries.categories=general,category1,category2
 * 
 *    # Defines 'gifimages' collection, accepts only GIF files
 *    propono.atomserver.filebased.collection.gifimages.title=GIF Images
 *    propono.atomserver.filebased.collection.gifimages.singular=gif
 *    propono.atomserver.filebased.collection.gifimages.plural=gifs
 *    propono.atomserver.filebased.collection.gifimages.accept=image/gif
 *    propono.atomserver.filebased.collection.gifimages.categories=general,category1,category2
 * </pre>
 *
 * If no such properties are found, then service will fall back to two collections: 'entries' for
 * Atom entries and 'resources' for any content-type.
 *
 *
 * <p>
 * <b>URI structure used for accessing collections and entries</b>
 * </p>
 *
 * <p>
 * Collection feed (URI allows GET to get collection, POST to add to it)<br/>
 * <code>[servlet-context-uri]/app/[workspace-handle]/[collection-plural] </code>
 * </p>
 *
 * <p>
 * Collection entry (URI allows GET, PUT and DELETE)<br/>
 * <code>[servlet-context-uri]/app/[workspace-handle]/[collection-singular]/[entryid] </code>
 * </p>
 *
 * <p>
 * Collection entry media (URI allows GET, PUT and DELETE)<br/>
 * <code>[servlet-context-uri]/app/[workspace-handle]/[collection-singular]/media/[entryid]</code>
 * </p>
 *
 * <p>
 * Categories URI if not using inline categories (URI allows GET)<br/>
 * <code>[servlet-context-uri]/app/[workspace-handle]/[collection-plural]/categories</code>
 * </p>
 *
 *
 * <p>
 * <b>Directory structure used to store collections and entries</b>
 * </p>
 *
 * <p>
 * Collection feed (kept constantly up to date)<br/>
 * <code>[servlet-context-dir]/[workspace-handle]/[collection-plural]/feed.xml</code>
 * </p>
 *
 * <p>
 * Collection entry (individual entries also stored as entry.xml files)<br/>
 * <code>[servlet-context-dir]/[workspace-handle]/[collection-plural]/id/entry.xml</code>
 * </p>
 *
 * <p>
 * Collection entry media (media file stored under entry directory)<br/>
 * <code>[servlet-context-dir]/[workspace-handle]/[collection-plural]/id/media/id</code>
 * </p>
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class FileBasedAtomService extends AtomService {

    private final Map<String, FileBasedWorkspace> workspaceMap = new TreeMap<String, FileBasedWorkspace>();
    private final Map<String, FileBasedCollection> collectionMap = new TreeMap<String, FileBasedCollection>();
    private static Properties cacheProps = new Properties();
    private boolean firstTime = true;

    public FileBasedAtomService(final String userName, final String baseDir, final String contextURI, final String contextPath, final String servletPath)
            throws Exception {
        final String workspaceHandle = userName;

        // One workspace per user
        final FileBasedWorkspace workspace = new FileBasedWorkspace(workspaceHandle, baseDir);
        workspaceMap.put(userName, workspace);

        if (firstTime) {
            synchronized (cacheProps) {
                final InputStream is = getClass().getResourceAsStream("/propono.properties");
                if (is != null) {
                    cacheProps.load(is);
                }
                firstTime = false;
            }
        }
        // can't find propono.properties, so use system props instead
        if (cacheProps == null) {
            cacheProps = System.getProperties();
        }

        final String relativeURIsString = cacheProps.getProperty("propono.atomserver.filebased.relativeURIs");
        final boolean relativeURIs = "true".equals(relativeURIsString);

        final String inlineCategoriesString = cacheProps.getProperty("propono.atomserver.filebased.inlineCategories");
        final boolean inlineCategories = "true".equals(inlineCategoriesString);

        final String colnames = cacheProps.getProperty("propono.atomserver.filebased.collections");
        if (colnames != null) {

            // collections specified in propono.properties, use those

            final String[] colarray = Utilities.stringToStringArray(colnames, ",");
            for (final String element : colarray) {
                final String prefix = "propono.atomserver.filebased.collection." + element + ".";
                final String collectionTitle = cacheProps.getProperty(prefix + "title");
                final String collectionSingular = cacheProps.getProperty(prefix + "singular");
                final String collectionPlural = cacheProps.getProperty(prefix + "plural");
                final String collectionAccept = cacheProps.getProperty(prefix + "accept");

                final String catNamesString = cacheProps.getProperty(prefix + "categories");
                final String[] catNames = Utilities.stringToStringArray(catNamesString, ",");

                final FileBasedCollection entries = new FileBasedCollection(collectionTitle, workspaceHandle, collectionPlural, collectionSingular,
                        collectionAccept, inlineCategories, catNames, relativeURIs, contextURI, contextPath, servletPath, baseDir);
                workspace.addCollection(entries);
                // want to be able to look up collection by singular and plural names
                collectionMap.put(workspaceHandle + "|" + collectionSingular, entries);
                collectionMap.put(workspaceHandle + "|" + collectionPlural, entries);
            }
        } else {

            // Fallback to two collections. One collection for accepting entries
            // and other collection for other ( resources/uploaded images etc.)

            final String[] catNames = new String[] { "general", "category1", "category2" };

            final FileBasedCollection entries = new FileBasedCollection("Entries", workspaceHandle, "entries", "entry", "application/atom+xml;type=entry",
                    inlineCategories, catNames, relativeURIs, contextURI, contextPath, servletPath, baseDir);
            workspace.addCollection(entries);
            // want to be able to look up collection by singular and plural names
            collectionMap.put(workspaceHandle + "|entry", entries);
            collectionMap.put(workspaceHandle + "|entries", entries);

            final FileBasedCollection resources = new FileBasedCollection("Resources", workspaceHandle, "resources", "resource", "*/*", inlineCategories,
                    catNames, relativeURIs, contextURI, contextPath, servletPath, baseDir);
            // want to be able to look up collection by singular and plural names
            workspace.addCollection(resources);
            collectionMap.put(workspaceHandle + "|resource", resources);
            collectionMap.put(workspaceHandle + "|resources", resources);
        }

        getWorkspaces().add(workspace);
    }

    /**
     * Find workspace by handle, returns null of not found.
     */
    FileBasedWorkspace findWorkspaceByHandle(final String handle) {
        return workspaceMap.get(handle);
    }

    FileBasedCollection findCollectionByHandle(final String handle, final String collection) {
        return collectionMap.get(handle + "|" + collection);
    }
}
