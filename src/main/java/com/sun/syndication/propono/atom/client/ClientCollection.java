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
package com.sun.syndication.propono.atom.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List; 
import java.io.InputStreamReader;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.io.impl.Atom10Parser;
import com.sun.syndication.propono.atom.common.AtomService;
import com.sun.syndication.propono.atom.common.Categories;
import com.sun.syndication.propono.utils.ProponoException;
import com.sun.syndication.propono.atom.common.Collection;
import com.sun.syndication.propono.atom.common.Workspace;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jdom.Element;

/**
 * Models an Atom collection, extends Collection and adds methods for adding,
 * retrieving, updateing and deleting entries.
 */
public class ClientCollection extends Collection {
    static final Log logger = LogFactory.getLog(ClientCollection.class);

    private List         categories = new ArrayList();
    private HttpClient   httpClient = null;
    private AuthStrategy authStrategy = null;
    private boolean      writable = true;

    private ClientWorkspace workspace = null;
    private ClientAtomService service = null;

    ClientCollection(Element e, ClientWorkspace workspace, String baseURI)  throws ProponoException {
        super(e, baseURI); 
        this.workspace = workspace;
        this.service = workspace.getAtomService();
        this.httpClient = workspace.getAtomService().getHttpClient();
        this.authStrategy = workspace.getAtomService().getAuthStrategy();
        parseCollectionElement(e);
    }

    ClientCollection(String href, AuthStrategy authStrategy)  throws ProponoException {
        super("Standalone connection", "text", href);
        this.authStrategy = authStrategy;
        try {
            httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
            // TODO: make connection timeout configurable
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000); 
        } catch (Throwable t) {
            throw new ProponoException("ERROR creating HTTPClient", t);
        }
    }

    void addAuthentication(HttpMethodBase method) throws ProponoException {
        authStrategy.addAuthentication(httpClient, method);
    }
    
    /**
     * Package access to httpClient to allow use by ClientEntry and ClientMediaEntry.
     */
    HttpClient getHttpClient() {
        return httpClient;
    }

    /** 
     * Get iterator over entries in this collection. Entries returned are 
     * considered to be partial entries cannot be saved/updated.
     */
    public Iterator getEntries() throws ProponoException {
        return new EntryIterator(this); 
    }
    
    /**
     * Get full entry specified by entry edit URI. 
     * Note that entry may or may not be associated with this collection.
     * @return ClientEntry or ClientMediaEntry specified by URI.
     */
    public ClientEntry getEntry(String uri) throws ProponoException {      
        GetMethod method = new GetMethod(uri);
        authStrategy.addAuthentication(httpClient, method);
        try {
            httpClient.executeMethod(method);         
            if (method.getStatusCode() != 200) {
                throw new ProponoException("ERROR HTTP status code=" + method.getStatusCode());
            }
            Entry romeEntry = Atom10Parser.parseEntry(
                    new InputStreamReader(method.getResponseBodyAsStream()), uri);
            if (!romeEntry.isMediaEntry()) {
                return new ClientEntry(service, this, romeEntry, false);
            } else {
                return new ClientMediaEntry(service, this, romeEntry, false);
            }
        } catch (Exception e) {
            throw new ProponoException("ERROR: getting or parsing entry/media, HTTP code: ", e);
        } finally {
            method.releaseConnection();
        }
    }
    
    /**
     * Get workspace or null if collection is not associated with a workspace.
     */
    public Workspace getWorkspace() {
        return workspace;
    }
    
    /**
     * Determines if collection is writable.
     */
    public boolean isWritable() {
        return writable;
    }
    
    /** 
     * Create new entry associated with collection, but do not save to server.
     * @throws ProponoException if collecton is not writable.
     */
    public ClientEntry createEntry() throws ProponoException {
        if (!isWritable()) throw new ProponoException("Collection is not writable");
        return new ClientEntry(service, this);
    }

    /**
     * Create new media entry assocaited with collection, but do not save.
     * server. Depending on the Atom server, you may or may not be able to 
     * persist the properties of the entry that is returned.
     * @param title       Title to used for uploaded file.
     * @param slug        String to be used in file-name of stored file
     * @param contentType MIME content-type of file.
     * @param bytes       Data to be uploaded as byte array.
     * @throws ProponoException if collecton is not writable
     */
    public ClientMediaEntry createMediaEntry(
            String title, String slug, String contentType, byte[] bytes) 
            throws ProponoException {
        if (!isWritable()) throw new ProponoException("Collection is not writable");
        return new ClientMediaEntry(service, this, title, slug, contentType, bytes);
    }
    
    /**
     * Create new media entry assocaited with collection, but do not save.
     * server. Depending on the Atom server, you may or may not be able to. 
     * persist the properties of the entry that is returned.
     * @param title       Title to used for uploaded file.
     * @param slug        String to be used in file-name of stored file
     * @param contentType MIME content-type of file.
     * @param is          Data to be uploaded as InputStream.
     * @throws ProponoException if collecton is not writable
     */
    public ClientMediaEntry createMediaEntry(
            String title, String slug, String contentType, InputStream is) 
            throws ProponoException {
        if (!isWritable()) throw new ProponoException("Collection is not writable");
        return new ClientMediaEntry(service, this, title, slug, contentType, is);
    }
    
    /**
     * Save to collection a new entry that was created by a createEntry() 
     * or createMediaEntry() and save it to the server.
     * @param entry Entry to be saved.
     * @throws ProponoException on error, if collection is not writable or if entry is partial.
     */
    public void addEntry(ClientEntry entry) throws ProponoException {
        if (!isWritable()) throw new ProponoException("Collection is not writable");
        entry.addToCollection(this);
    }

    protected void parseCollectionElement(Element element) throws ProponoException {
        if (workspace == null) return;
        
        setHref(element.getAttribute("href").getValue());
        
        Element titleElem = element.getChild("title", AtomService.ATOM_FORMAT);
        if (titleElem != null) {
            setTitle(titleElem.getText());
            if (titleElem.getAttribute("type", AtomService.ATOM_FORMAT) != null) {
                setTitleType(titleElem.getAttribute("type", AtomService.ATOM_FORMAT).getValue());
            }
        }
                
        List acceptElems = element.getChildren("accept",  AtomService.ATOM_PROTOCOL);
        if (acceptElems != null && acceptElems.size() > 0) {
            for (Iterator it = acceptElems.iterator(); it.hasNext();) {
                Element acceptElem = (Element)it.next();
                addAccept(acceptElem.getTextTrim());
            }
        }
        
        // Loop to parse <app:categories> element to Categories objects
        List catsElems = element.getChildren("categories", AtomService.ATOM_PROTOCOL);
        for (Iterator catsIter = catsElems.iterator(); catsIter.hasNext();) {
            Element catsElem = (Element) catsIter.next();  
            Categories cats = new ClientCategories(catsElem, this);
            addCategories(cats);
        }
    }
}
