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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;

import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;

import com.rometools.propono.atom.common.Categories;
import com.rometools.propono.atom.common.Collection;
import com.rometools.propono.atom.common.rome.AppModule;
import com.rometools.propono.atom.common.rome.AppModuleImpl;
import com.rometools.propono.atom.server.AtomException;
import com.rometools.propono.atom.server.AtomMediaResource;
import com.rometools.propono.atom.server.AtomNotFoundException;
import com.rometools.propono.utils.Utilities;
import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.atom.Category;
import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.WireFeedInput;
import com.rometools.rome.io.WireFeedOutput;
import com.rometools.rome.io.impl.Atom10Generator;
import com.rometools.rome.io.impl.Atom10Parser;

/**
 * File based Atom collection implementation. This is the heart of the file-based Atom service
 * implementation. It provides methods for adding, getting updating and deleting Atom entries and
 * media entries.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class FileBasedCollection extends Collection {

    private String handle = null;
    private String singular = null;
    private String collection = null;

    private boolean inlineCats = false;
    private String[] catNames = null;

    private boolean relativeURIs = false;
    private String contextURI = null;
    private String servletPath = null;
    private String baseDir = null;

    private static final String FEED_TYPE = "atom_1.0";

    /**
     * Construct by providing title (plain text, no HTML), a workspace handle, a plural collection
     * name (e.g. entries), a singular collection name (e.g. entry), the base directory for file
     * storage, the content-type range accepted by the collection and the root Atom protocol URI for
     * the service.
     *
     * @param title Title of collection (plain text, no HTML)
     * @param handle Workspace handle
     * @param collection Collection handle, plural
     * @param singular Collection handle, singular
     * @param accept Content type range accepted by collection
     * @param inlineCats True for inline categories
     * @param catNames Category names for this workspace
     * @param baseDir Base directory for file storage
     * @param relativeURIs True for relative URIs
     * @param contextURI Absolute URI of context that hosts APP service
     * @param contextPath Context path of APP service (e.g. "/sample-atomserver")
     * @param servletPath Servlet path of APP service (e.g. "/app")
     */
    public FileBasedCollection(final String title, final String handle, final String collection, final String singular, final String accept,
            final boolean inlineCats, final String[] catNames, final boolean relativeURIs, final String contextURI, final String contextPath,
            final String servletPath, final String baseDir) {
        super(title, "text", relativeURIs ? servletPath.substring(1) + "/" + handle + "/" + collection : contextURI + servletPath + "/" + handle + "/"
                + collection);

        this.handle = handle;
        this.collection = collection;
        this.singular = singular;
        this.inlineCats = inlineCats;
        this.catNames = catNames;
        this.baseDir = baseDir;
        this.relativeURIs = relativeURIs;
        this.contextURI = contextURI;
        this.servletPath = servletPath;

        addAccept(accept);
    }

    /**
     * Get feed document representing collection.
     *
     * @throws com.rometools.rome.propono.atom.server.AtomException On error retrieving feed file.
     * @return Atom Feed representing collection.
     */
    public Feed getFeedDocument() throws AtomException {
        InputStream in = null;
        synchronized (FileStore.getFileStore()) {
            in = FileStore.getFileStore().getFileInputStream(getFeedPath());
            if (in == null) {
                in = createDefaultFeedDocument(contextURI + servletPath + "/" + handle + "/" + collection);
            }
        }
        try {
            final WireFeedInput input = new WireFeedInput();
            final WireFeed wireFeed = input.build(new InputStreamReader(in, "UTF-8"));
            return (Feed) wireFeed;
        } catch (final Exception ex) {
            throw new AtomException(ex);
        }
    }

    /**
     * Get list of one Categories object containing categories allowed by collection.
     *
     * @param inline True if Categories object should contain collection of in-line Categories
     *            objects or false if it should set the Href for out-of-line categories.
     */
    public List<Categories> getCategories(final boolean inline) {
        final Categories cats = new Categories();
        cats.setFixed(true);
        cats.setScheme(contextURI + "/" + handle + "/" + singular);
        if (inline) {
            for (final String catName : catNames) {
                final Category cat = new Category();
                cat.setTerm(catName);
                cats.addCategory(cat);
            }
        } else {
            cats.setHref(getCategoriesURI());
        }
        return Collections.singletonList(cats);
    }

    /**
     * Get list of one Categories object containing categories allowed by collection, returns
     * in-line categories if collection set to use in-line categories.
     */
    @Override
    public List<Categories> getCategories() {
        return getCategories(inlineCats);
    }

    /**
     * Add entry to collection.
     *
     * @param entry Entry to be added to collection. Entry will be saved to disk in a directory
     *            under the collection's directory and the path will follow the pattern
     *            [collection-plural]/[entryid]/entry.xml. The entry will be added to the
     *            collection's feed in [collection-plural]/feed.xml.
     * @throws java.lang.Exception On error.
     * @return Entry as it exists on the server.
     */
    public Entry addEntry(final Entry entry) throws Exception {
        synchronized (FileStore.getFileStore()) {
            final Feed f = getFeedDocument();

            final String fsid = FileStore.getFileStore().getNextId();
            updateTimestamps(entry);

            // Save entry to file
            final String entryPath = getEntryPath(fsid);

            final OutputStream os = FileStore.getFileStore().getFileOutputStream(entryPath);
            updateEntryAppLinks(entry, fsid, true);
            Atom10Generator.serializeEntry(entry, new OutputStreamWriter(os, "UTF-8"));
            os.flush();
            os.close();

            // Update feed file
            updateEntryAppLinks(entry, fsid, false);
            updateFeedDocumentWithNewEntry(f, entry);

            return entry;
        }
    }

    /**
     * Add media entry to collection. Accepts a media file to be added to collection. The file will
     * be saved to disk in a directory under the collection's directory and the path will follow the
     * pattern <code>[collection-plural]/[entryid]/media/[entryid]</code>. An Atom entry will be
     * created to store metadata for the entry and it will exist at the path
     * <code>[collection-plural]/[entryid]/entry.xml</code>. The entry will be added to the
     * collection's feed in [collection-plural]/feed.xml.
     *
     * @param entry Entry object
     * @param slug String to be used in file-name
     * @param is Source of media data
     * @throws java.lang.Exception On Error
     * @return Location URI of entry
     */
    public String addMediaEntry(final Entry entry, final String slug, final InputStream is) throws Exception {
        synchronized (FileStore.getFileStore()) {

            // Save media file temp file
            final Content content = entry.getContents().get(0);
            if (entry.getTitle() == null) {
                entry.setTitle(slug);
            }
            final String fileName = createFileName(slug != null ? slug : entry.getTitle(), content.getType());
            final File tempFile = File.createTempFile(fileName, "tmp");
            final FileOutputStream fos = new FileOutputStream(tempFile);
            Utilities.copyInputToOutput(is, fos);
            fos.close();

            // Save media file
            final FileInputStream fis = new FileInputStream(tempFile);
            saveMediaFile(fileName, content.getType(), tempFile.length(), fis);
            fis.close();
            final File resourceFile = new File(getEntryMediaPath(fileName));

            // Create media-link entry
            updateTimestamps(entry);

            // Save media-link entry
            final String entryPath = getEntryPath(fileName);
            final OutputStream os = FileStore.getFileStore().getFileOutputStream(entryPath);
            updateMediaEntryAppLinks(entry, resourceFile.getName(), true);
            Atom10Generator.serializeEntry(entry, new OutputStreamWriter(os, "UTF-8"));
            os.flush();
            os.close();

            // Update feed with new entry
            final Feed f = getFeedDocument();
            updateMediaEntryAppLinks(entry, resourceFile.getName(), false);
            updateFeedDocumentWithNewEntry(f, entry);

            return getEntryEditURI(fileName, false, true);
        }
    }

    /**
     * Get an entry from the collection.
     *
     * @param fsid Internal ID of entry to be returned
     * @throws java.lang.Exception On error
     * @return Entry specified by fileName/ID
     */
    public Entry getEntry(String fsid) throws Exception {
        if (fsid.endsWith(".media-link")) {
            fsid = fsid.substring(0, fsid.length() - ".media-link".length());
        }

        final String entryPath = getEntryPath(fsid);

        checkExistence(entryPath);
        final InputStream in = FileStore.getFileStore().getFileInputStream(entryPath);

        final Entry entry;
        final File resource = new File(fsid);
        if (resource.exists()) {
            entry = loadAtomResourceEntry(in, resource);
            updateMediaEntryAppLinks(entry, fsid, true);
        } else {
            entry = loadAtomEntry(in);
            updateEntryAppLinks(entry, fsid, true);
        }
        return entry;
    }

    /**
     * Get media resource wrapping a file.
     */
    public AtomMediaResource getMediaResource(final String fileName) throws Exception {
        final String filePath = getEntryMediaPath(fileName);
        final File resource = new File(filePath);
        return new AtomMediaResource(resource);
    }

    /**
     * Update an entry in the collection.
     *
     * @param entry Updated entry to be stored
     * @param fsid Internal ID of entry
     * @throws java.lang.Exception On error
     */
    public void updateEntry(final Entry entry, String fsid) throws Exception {
        synchronized (FileStore.getFileStore()) {

            final Feed f = getFeedDocument();

            if (fsid.endsWith(".media-link")) {
                fsid = fsid.substring(0, fsid.length() - ".media-link".length());
            }

            updateTimestamps(entry);

            updateEntryAppLinks(entry, fsid, false);
            updateFeedDocumentWithExistingEntry(f, entry);

            final String entryPath = getEntryPath(fsid);
            final OutputStream os = FileStore.getFileStore().getFileOutputStream(entryPath);
            updateEntryAppLinks(entry, fsid, true);
            Atom10Generator.serializeEntry(entry, new OutputStreamWriter(os, "UTF-8"));
            os.flush();
            os.close();
        }
    }

    /**
     * Update media associated with a media-link entry.
     *
     * @param fileName Internal ID of entry being updated
     * @param contentType Content type of data
     * @param is Source of updated data
     * @throws java.lang.Exception On error
     * @return Updated Entry as it exists on server
     */
    public Entry updateMediaEntry(final String fileName, final String contentType, final InputStream is) throws Exception {
        synchronized (FileStore.getFileStore()) {

            final File tempFile = File.createTempFile(fileName, "tmp");
            final FileOutputStream fos = new FileOutputStream(tempFile);
            Utilities.copyInputToOutput(is, fos);
            fos.close();

            // Update media file
            final FileInputStream fis = new FileInputStream(tempFile);
            saveMediaFile(fileName, contentType, tempFile.length(), fis);
            fis.close();
            final File resourceFile = new File(getEntryMediaPath(fileName));

            // Load media-link entry to return
            final String entryPath = getEntryPath(fileName);
            final InputStream in = FileStore.getFileStore().getFileInputStream(entryPath);
            final Entry atomEntry = loadAtomResourceEntry(in, resourceFile);

            updateTimestamps(atomEntry);
            updateMediaEntryAppLinks(atomEntry, fileName, false);

            // Update feed with new entry
            final Feed f = getFeedDocument();
            updateFeedDocumentWithExistingEntry(f, atomEntry);

            // Save updated media-link entry
            final OutputStream os = FileStore.getFileStore().getFileOutputStream(entryPath);
            updateMediaEntryAppLinks(atomEntry, fileName, true);
            Atom10Generator.serializeEntry(atomEntry, new OutputStreamWriter(os, "UTF-8"));
            os.flush();
            os.close();

            return atomEntry;
        }
    }

    /**
     * Delete an entry and any associated media file.
     *
     * @param fsid Internal ID of entry
     * @throws java.lang.Exception On error
     */
    public void deleteEntry(final String fsid) throws Exception {
        synchronized (FileStore.getFileStore()) {

            // Remove entry from Feed
            final Feed feed = getFeedDocument();
            updateFeedDocumentRemovingEntry(feed, fsid);

            final String entryFilePath = getEntryPath(fsid);
            FileStore.getFileStore().deleteFile(entryFilePath);

            final String entryMediaPath = getEntryMediaPath(fsid);
            if (entryMediaPath != null) {
                FileStore.getFileStore().deleteFile(entryMediaPath);
            }

            final String entryDirPath = getEntryDirPath(fsid);
            FileStore.getFileStore().deleteDirectory(entryDirPath);

            try {
                Thread.sleep(500L);
            } catch (final Exception ignored) {
            }
        }
    }

    private void updateFeedDocumentWithNewEntry(final Feed f, final Entry e) throws AtomException {
        boolean inserted = false;
        for (int i = 0; i < f.getEntries().size(); i++) {
            final Entry entry = f.getEntries().get(i);
            final AppModule mod = (AppModule) entry.getModule(AppModule.URI);
            final AppModule newMod = (AppModule) e.getModule(AppModule.URI);
            if (newMod.getEdited().before(mod.getEdited())) {
                f.getEntries().add(i, e);
                inserted = true;
                break;
            }
        }
        if (!inserted) {
            f.getEntries().add(0, e);
        }
        updateFeedDocument(f);
    }

    private void updateFeedDocumentRemovingEntry(final Feed f, final String id) throws AtomException {
        final Entry e = findEntry("urn:uuid:" + id, f);
        f.getEntries().remove(e);
        updateFeedDocument(f);
    }

    private void updateFeedDocumentWithExistingEntry(final Feed f, final Entry e) throws AtomException {
        final Entry old = findEntry(e.getId(), f);
        f.getEntries().remove(old);

        boolean inserted = false;
        for (int i = 0; i < f.getEntries().size(); i++) {
            final Entry entry = f.getEntries().get(i);
            final AppModule entryAppModule = (AppModule) entry.getModule(AppModule.URI);
            final AppModule eAppModule = (AppModule) entry.getModule(AppModule.URI);
            if (eAppModule.getEdited().before(entryAppModule.getEdited())) {
                f.getEntries().add(i, e);
                inserted = true;
                break;
            }
        }
        if (!inserted) {
            f.getEntries().add(0, e);
        }
        updateFeedDocument(f);
    }

    private Entry findEntry(final String id, final Feed feed) {
        for (final Entry entry : feed.getEntries()) {
            if (id.equals(entry.getId())) {
                return entry;
            }
        }
        return null;
    }

    private void updateFeedDocument(final Feed f) throws AtomException {
        try {
            synchronized (FileStore.getFileStore()) {
                final WireFeedOutput wireFeedOutput = new WireFeedOutput();
                final Document feedDoc = wireFeedOutput.outputJDom(f);
                final XMLOutputter outputter = new XMLOutputter();
                // outputter.setFormat(Format.getPrettyFormat());
                final OutputStream fos = FileStore.getFileStore().getFileOutputStream(getFeedPath());
                outputter.output(feedDoc, new OutputStreamWriter(fos, "UTF-8"));
            }
        } catch (final FeedException fex) {
            throw new AtomException(fex);
        } catch (final IOException ex) {
            throw new AtomException(ex);
        }
    }

    private InputStream createDefaultFeedDocument(final String uri) throws AtomException {

        final Feed f = new Feed();
        f.setTitle("Feed");
        f.setId(uri);
        f.setFeedType(FEED_TYPE);

        final Link selfLink = new Link();
        selfLink.setRel("self");
        selfLink.setHref(uri);
        f.getOtherLinks().add(selfLink);

        try {
            final WireFeedOutput wireFeedOutput = new WireFeedOutput();
            final Document feedDoc = wireFeedOutput.outputJDom(f);
            final XMLOutputter outputter = new XMLOutputter();
            // outputter.setFormat(Format.getCompactFormat());
            final OutputStream fos = FileStore.getFileStore().getFileOutputStream(getFeedPath());
            outputter.output(feedDoc, new OutputStreamWriter(fos, "UTF-8"));

        } catch (final FeedException ex) {
            throw new AtomException(ex);
        } catch (final IOException ex) {
            throw new AtomException(ex);
        } catch (final Exception e) {

            e.printStackTrace();
        }
        return FileStore.getFileStore().getFileInputStream(getFeedPath());
    }

    private Entry loadAtomResourceEntry(final InputStream in, final File file) {
        try {
            final Entry entry = Atom10Parser.parseEntry(new BufferedReader(new InputStreamReader(in)), null, Locale.US);
            updateMediaEntryAppLinks(entry, file.getName(), true);
            return entry;

        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void updateEntryAppLinks(final Entry entry, final String fsid, final boolean singleEntry) {

        entry.setId("urn:uuid:" + fsid);

        // Look for existing alt links and the alt link
        Link altLink = null;
        List<Link> altLinks = entry.getAlternateLinks();
        if (altLinks != null) {
            for (final Link link : altLinks) {
                if (link.getRel() == null || "alternate".equals(link.getRel())) {
                    altLink = link;
                    break;
                }
            }
        } else {
            // No alt links found, so add them now
            altLinks = new ArrayList<Link>();
            entry.setAlternateLinks(altLinks);
        }
        // The alt link not found, so add it now
        if (altLink == null) {
            altLink = new Link();
            altLinks.add(altLink);
        }
        // Set correct value for the alt link
        altLink.setRel("alternate");
        altLink.setHref(getEntryViewURI(fsid));

        // Look for existing other links and the edit link
        Link editLink = null;
        List<Link> otherLinks = entry.getOtherLinks();
        if (otherLinks != null) {
            for (final Link link : otherLinks) {
                if ("edit".equals(link.getRel())) {
                    editLink = link;
                    break;
                }
            }
        } else {
            // No other links found, so add them now
            otherLinks = new ArrayList<Link>();
            entry.setOtherLinks(otherLinks);
        }
        // The edit link not found, so add it now
        if (editLink == null) {
            editLink = new Link();
            otherLinks.add(editLink);
        }
        // Set correct value for the edit link
        editLink.setRel("edit");
        editLink.setHref(getEntryEditURI(fsid, relativeURIs, singleEntry));
    }

    private void updateMediaEntryAppLinks(final Entry entry, final String fileName, final boolean singleEntry) {

        // TODO: figure out why PNG is missing from Java MIME types
        final FileTypeMap map = FileTypeMap.getDefaultFileTypeMap();
        if (map instanceof MimetypesFileTypeMap) {
            try {
                ((MimetypesFileTypeMap) map).addMimeTypes("image/png png PNG");
            } catch (final Exception ignored) {
            }
        }
        entry.setId(getEntryMediaViewURI(fileName));
        entry.setTitle(fileName);
        entry.setUpdated(new Date());

        final List<Link> otherlinks = new ArrayList<Link>();
        entry.setOtherLinks(otherlinks);

        final Link editlink = new Link();
        editlink.setRel("edit");
        editlink.setHref(getEntryEditURI(fileName, relativeURIs, singleEntry));
        otherlinks.add(editlink);

        final Link editMedialink = new Link();
        editMedialink.setRel("edit-media");
        editMedialink.setHref(getEntryMediaEditURI(fileName, relativeURIs, singleEntry));
        otherlinks.add(editMedialink);

        final Content content = entry.getContents().get(0);
        content.setSrc(getEntryMediaViewURI(fileName));
        final List<Content> contents = new ArrayList<Content>();
        contents.add(content);
        entry.setContents(contents);
    }

    /**
     * Create a Rome Atom entry based on a Roller entry. Content is escaped. Link is stored as
     * rel=alternate link.
     */
    private Entry loadAtomEntry(final InputStream in) {
        try {
            return Atom10Parser.parseEntry(new BufferedReader(new InputStreamReader(in, "UTF-8")), null, Locale.US);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Update existing or add new app:edited.
     */
    private void updateTimestamps(final Entry entry) {
        // We're not differenting between an update and an edit (yet)
        entry.setUpdated(new Date());

        AppModule appModule = (AppModule) entry.getModule(AppModule.URI);
        if (appModule == null) {
            appModule = new AppModuleImpl();
            final List<Module> modules = entry.getModules() == null ? new ArrayList<Module>() : entry.getModules();
            modules.add(appModule);
            entry.setModules(modules);
        }
        appModule.setEdited(entry.getUpdated());
    }

    /**
     * Save file to website's resource directory.
     *
     * @param handle Weblog handle to save to
     * @param name Name of file to save
     * @param size Size of file to be saved
     * @param is Read file from input stream
     */
    private void saveMediaFile(final String name, final String contentType, final long size, final InputStream is) throws AtomException {

        final byte[] buffer = new byte[8192];
        int bytesRead = 0;

        final File dirPath = new File(getEntryMediaPath(name));
        if (!dirPath.getParentFile().exists()) {
            dirPath.getParentFile().mkdirs();
        }
        OutputStream bos = null;
        try {
            bos = new FileOutputStream(dirPath.getAbsolutePath());
            while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        } catch (final Exception e) {
            throw new AtomException("ERROR uploading file", e);
        } finally {
            try {
                bos.flush();
                bos.close();
            } catch (final Exception ignored) {
            }
        }

    }

    /**
     * Creates a file name for a file based on a weblog handle, title string and a content-type.
     *
     * @param handle Weblog handle
     * @param title Title to be used as basis for file name (or null)
     * @param contentType Content type of file (must not be null)
     *
     *            If a title is specified, the method will apply the same create-anchor logic we use
     *            for weblog entries to create a file name based on the title.
     *
     *            If title is null, the base file name will be the weblog handle plus a YYYYMMDDHHSS
     *            timestamp.
     *
     *            The extension will be formed by using the part of content type that comes after he
     *            slash.
     *
     *            For example: weblog.handle = "daveblog" title = "Port Antonio" content-type =
     *            "image/jpg" Would result in port_antonio.jpg
     *
     *            Another example: weblog.handle = "daveblog" title = null content-type =
     *            "image/jpg" Might result in daveblog-200608201034.jpg
     */
    private String createFileName(final String title, final String contentType) {

        if (handle == null) {
            throw new IllegalArgumentException("weblog handle cannot be null");
        }
        if (contentType == null) {
            throw new IllegalArgumentException("contentType cannot be null");
        }

        String fileName = null;

        final SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyyMMddHHssSSS");

        // Determine the extension based on the contentType. This is a hack.
        // The info we need to map from contentType to file extension is in
        // JRE/lib/content-type.properties, but Java Activation doesn't provide
        // a way to do a reverse mapping or to get at the data.
        final String[] typeTokens = contentType.split("/");
        final String ext = typeTokens[1];

        if (title != null && !title.trim().equals("")) {
            // We've got a title, so use it to build file name
            final String base = Utilities.replaceNonAlphanumeric(title, ' ');
            final StringTokenizer toker = new StringTokenizer(base);
            String tmp = null;
            int count = 0;
            while (toker.hasMoreTokens() && count < 5) {
                String s = toker.nextToken();
                s = s.toLowerCase();
                tmp = tmp == null ? s : tmp + "_" + s;
                count++;
            }
            fileName = tmp + "-" + sdf.format(new Date()) + "." + ext;

        } else {
            // No title or text, so instead we'll use the item's date
            // in YYYYMMDD format to form the file name
            fileName = handle + "-" + sdf.format(new Date()) + "." + ext;
        }

        return fileName;
    }

    // ------------------------------------------------------------ URI methods

    private String getEntryEditURI(final String fsid, final boolean relative, final boolean singleEntry) {
        String entryURI = null;
        if (relative) {
            if (singleEntry) {
                entryURI = fsid;
            } else {
                entryURI = singular + "/" + fsid;
            }
        } else {
            entryURI = contextURI + servletPath + "/" + handle + "/" + singular + "/" + fsid;
        }
        return entryURI;
    }

    private String getEntryViewURI(final String fsid) {
        return contextURI + "/" + handle + "/" + collection + "/" + fsid + "/entry.xml";
    }

    private String getEntryMediaEditURI(final String fsid, final boolean relative, final boolean singleEntry) {
        String entryURI = null;
        if (relative) {
            if (singleEntry) {
                entryURI = "media/" + fsid;
            } else {
                entryURI = singular + "/media/" + fsid;
            }
        } else {
            entryURI = contextURI + servletPath + "/" + handle + "/" + singular + "/media/" + fsid;
        }
        return entryURI;

    }

    private String getEntryMediaViewURI(final String fsid) {
        return contextURI + "/" + handle + "/" + collection + "/" + fsid + "/media/" + fsid;
    }

    private String getCategoriesURI() {
        if (!relativeURIs) {
            return contextURI + servletPath + "/" + handle + "/" + singular + "/categories";
        } else {
            return servletPath + "/" + handle + "/" + singular + "/categories";
        }
    }

    // ------------------------------------------------------- File path methods

    private String getBaseDir() {
        return baseDir;
    }

    private String getFeedPath() {
        return getBaseDir() + handle + File.separator + collection + File.separator + "feed.xml";
    }

    private String getEntryDirPath(final String id) {
        return getBaseDir() + handle + File.separator + collection + File.separator + id;
    }

    private String getEntryPath(final String id) {
        return getEntryDirPath(id) + File.separator + "entry.xml";
    }

    private String getEntryMediaPath(final String id) {
        return getEntryDirPath(id) + File.separator + "media" + File.separator + id;
    }

    private static void checkExistence(final String path) throws AtomNotFoundException {
        if (!FileStore.getFileStore().exists(path)) {
            throw new AtomNotFoundException("Entry does not exist");
        }
    }

}
