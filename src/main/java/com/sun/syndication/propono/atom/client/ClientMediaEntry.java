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

import com.sun.syndication.feed.atom.Content;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.impl.Atom10Generator;
import com.sun.syndication.io.impl.Atom10Parser;
import com.sun.syndication.propono.utils.ProponoException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import com.sun.syndication.propono.utils.Utilities;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.JDOMException;

/**
 * Client implementation of Atom media-link entry, an Atom entry that provides
 * meta-data for a media file (e.g. uploaded image or audio file). 
 */
public class ClientMediaEntry extends ClientEntry {
    private static final Log  logger = LogFactory.getLog(ClientMediaEntry.class);  
    
    private String slug = null;
    private byte[] bytes;
    private InputStream inputStream;
    
    /**
     * Create ClientMedieEntry for service and collection.
     */
    public ClientMediaEntry(ClientAtomService service, ClientCollection collection) {
        super(service, collection);
    }

    public ClientMediaEntry(ClientAtomService service, ClientCollection collection, 
            Entry entry, boolean partial) throws ProponoException { 
        super(service, collection, entry, partial);
    }  
    
    public ClientMediaEntry(ClientAtomService service, ClientCollection collection, 
            String title, String slug, String contentType, InputStream is) {
        this(service, collection); 
        this.inputStream = is;
        setTitle(title);
        setSlug(slug);
        Content content = new Content();
        content.setType(contentType);
        List contents = new ArrayList();
        contents.add(content);
        setContents(contents);
    } 
        
    public ClientMediaEntry(ClientAtomService service, ClientCollection collection, 
            String title, String slug, String contentType, byte[] bytes) {
        this(service, collection); 
        this.bytes = bytes;
        setTitle(title);
        setSlug(slug);
        Content content = new Content();
        content.setType(contentType);
        List contents = new ArrayList();
        contents.add(content);
        setContents(contents);
    } 
        
    /** 
     * Get bytes of media resource associated with entry.
     * @return Bytes or null if none available or if entry uses an InputStream instead.
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Set media resource data as a byte array, don't try this if you have already
     * set the data as an InputStream. 
     */
    public void setBytes(byte[] bytes) {
        if (inputStream != null) {
            throw new IllegalStateException("ERROR: already has inputStream, cannot set both inputStream and bytes"); 
        }
        this.bytes = bytes;
    }

    /**
     * Get input stream for media resource associated with this entry.
     * @return InputStream or null if none available or if entry uses bytes instead.
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Set media resource data as an input stream, don't try this if you have already
     * set the data as a byte array.
     */
    public void setInputStream(InputStream inputStream) {
        if (bytes != null) {
            throw new IllegalStateException("ERROR: already has bytes, cannot set both bytes and inputStream");
        }
        this.inputStream = inputStream;
    }

    /**
     * Get media link URI for editing the media resource associated with this
     * entry via HTTP PUT or DELETE.
     */
    public String getMediaLinkURI() {
        for (int i=0; i<getOtherLinks().size(); i++) {
            Link link = (Link)getOtherLinks().get(i);
            if (link.getRel() != null && link.getRel().equals("edit-media")) {
                return link.getHrefResolved();
            }
        }
        return null;
    }
       
    /**
     * Get media resource as an InputStream, should work regardless of whether
     * you set the media resource data as an InputStream or as a byte array.
     */
    public InputStream getAsStream() throws ProponoException {
        if (getContents() != null && getContents().size() > 0) {
            Content c = (Content)getContents().get(0);
            if (c.getSrc() != null) {
                return getResourceAsStream();
            } else if (inputStream != null) {
                return inputStream;
            } else if (bytes != null) {
                return new ByteArrayInputStream(bytes);
            } else {
                throw new ProponoException("ERROR: no src URI or binary data to return");
            } 
        }
        else {
            throw new ProponoException("ERROR: no content found in entry");
        }
    }
    
    private InputStream getResourceAsStream() throws ProponoException {
        if (getEditURI() == null) {
            throw new ProponoException("ERROR: not yet saved to server");
        }
        GetMethod method = new GetMethod(((Content)getContents()).getSrc());
        try {
            getCollection().getHttpClient().executeMethod(method);
            if (method.getStatusCode() != 200) {
                throw new ProponoException("ERROR HTTP status=" + method.getStatusCode());
            }
            return method.getResponseBodyAsStream();
        } catch (IOException e) {
            throw new ProponoException("ERROR: getting media entry", e);
        }
    } 
    
    /**
     * Update entry on server. 
     */
    public void update() throws ProponoException {
        if (partial) throw new ProponoException("ERROR: attempt to update partial entry");
        EntityEnclosingMethod method = null;
        Content updateContent = (Content)getContents().get(0);
        try {
            if (getMediaLinkURI() != null && getBytes() != null) {
                // existing media entry and new file, so PUT file to edit-media URI
                method = new PutMethod(getMediaLinkURI());
                if (inputStream != null) {
                    method.setRequestEntity(new InputStreamRequestEntity(inputStream));
                } else {
                    method.setRequestEntity(new InputStreamRequestEntity(new ByteArrayInputStream(getBytes())));
                }
                
                method.setRequestHeader("Content-type", updateContent.getType());
            } 
            else if (getEditURI() != null) {
                // existing media entry and NO new file, so PUT entry to edit URI
                method = new PutMethod(getEditURI());
                StringWriter sw = new StringWriter();
                Atom10Generator.serializeEntry(this, sw);
                method.setRequestEntity(new StringRequestEntity(sw.toString()));
                method.setRequestHeader(
                    "Content-type", "application/atom+xml; charset=utf8"); 
            } else {
                throw new ProponoException("ERROR: media entry has no edit URI or media-link URI");
            }
            this.getCollection().addAuthentication(method);
            method.addRequestHeader("Title", getTitle());
            getCollection().getHttpClient().executeMethod(method); 
            if (inputStream != null) inputStream.close();
            InputStream is = method.getResponseBodyAsStream();
            if (method.getStatusCode() != 200 && method.getStatusCode() != 201) {
                throw new ProponoException(
                    "ERROR HTTP status=" + method.getStatusCode() + " : " + Utilities.streamToString(is));
            }
            
        } catch (Exception e) {
            throw new ProponoException("ERROR: saving media entry");
        }
        if (method.getStatusCode() != 201) { 
            throw new ProponoException("ERROR HTTP status=" + method.getStatusCode());
        }
    }
            
    /** Package access, to be called by DefaultClientCollection */
    void addToCollection(ClientCollection col) throws ProponoException {
        setCollection(col);
        EntityEnclosingMethod method = new PostMethod(col.getHrefResolved());
        getCollection().addAuthentication(method);
        StringWriter sw = new StringWriter();
        boolean error = false;
        try {
            Content c = (Content)getContents().get(0);
            if (inputStream != null) {
                method.setRequestEntity(new InputStreamRequestEntity(inputStream));
            } else {
                method.setRequestEntity(new InputStreamRequestEntity(new ByteArrayInputStream(getBytes())));
            }
            method.setRequestHeader("Content-type", c.getType()); 
            method.setRequestHeader("Title", getTitle());
            method.setRequestHeader("Slug", getSlug());
            getCollection().getHttpClient().executeMethod(method);
            if (inputStream != null) inputStream.close();
            InputStream is = method.getResponseBodyAsStream();
            if (method.getStatusCode() == 200 || method.getStatusCode() == 201) {
                Entry romeEntry = Atom10Parser.parseEntry(
                   new InputStreamReader(is), col.getHrefResolved());
                BeanUtils.copyProperties(this, romeEntry);                        
                
            } else {
                throw new ProponoException(
                    "ERROR HTTP status-code=" + method.getStatusCode() 
                 + " status-line: " + method.getStatusLine()); 
            }
        } catch (IOException ie) {
            throw new ProponoException("ERROR: saving media entry", ie);
        } catch (JDOMException je) {
            throw new ProponoException("ERROR: saving media entry", je);
        } catch (FeedException fe) {
            throw new ProponoException("ERROR: saving media entry", fe);
        } catch (IllegalAccessException ae) {
            throw new ProponoException("ERROR: saving media entry", ae);
        } catch (InvocationTargetException te) {
            throw new ProponoException("ERROR: saving media entry", te);
        }
        Header locationHeader = method.getResponseHeader("Location");
        if (locationHeader == null) {
            logger.warn("WARNING added entry, but no location header returned");
        } else if (getEditURI() == null) {
            List links = getOtherLinks();
            Link link = new Link();
            link.setHref(locationHeader.getValue());
            link.setRel("edit");
            links.add(link);
            setOtherLinks(links);
        }
    }

    /** Set string to be used in file name of new media resource on server. */
    public String getSlug() {
        return slug;
    }

    /** Get string to be used in file name of new media resource on server. */
    public void setSlug(String slug) {
        this.slug = slug;
    }

}

























