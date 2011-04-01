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

import java.io.InputStream;

import org.rometools.propono.blogclient.BlogClientException;
import org.rometools.propono.blogclient.BlogEntry;
import org.rometools.propono.blogclient.BlogResource;
import com.sun.syndication.feed.atom.Link;
import org.rometools.propono.atom.client.ClientAtomService;
import org.rometools.propono.atom.client.ClientCollection;
import org.rometools.propono.atom.client.ClientEntry;
import org.rometools.propono.atom.client.ClientMediaEntry;
import java.util.Iterator;
import java.util.List;

/**
 * Atom protocol implementation of BlogResource.
 */
public class AtomResource extends AtomEntry implements BlogResource {
    private AtomCollection collection;
    private byte[] bytes;

    AtomResource(AtomCollection collection, String name, String contentType, byte[] bytes) 
            throws BlogClientException {
        super((AtomBlog)collection.getBlog(), collection);             
        this.collection = collection;
        this.bytes = bytes;
        BlogEntry.Content rcontent = new BlogEntry.Content();
        rcontent.setType(contentType);
        setContent(rcontent);
    } 
    
    AtomResource(AtomCollection collection, ClientMediaEntry entry) 
            throws BlogClientException {
        super(collection, entry);
    }  
    
    AtomResource(AtomBlog blog, ClientMediaEntry entry)  throws BlogClientException {   
        super(blog, entry);
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return getTitle();
    }
    
    byte[] getBytes() {
        return bytes;
    }
    
    /**
     * {@inheritDoc}
     */
    public InputStream getAsStream() throws BlogClientException {
        try {
            return null; //((ClientMediaEntry)clientEntry).getAsStream();
        } catch (Exception e) {
            throw new BlogClientException("Error creating entry", e);
        }
    }
        
    /**
     * {@inheritDoc}
     */
    public void save() throws BlogClientException {
        try {
            if (getToken() == null) {
                ClientAtomService clientService = ((AtomBlog)getBlog()).getService();
                ClientCollection clientCollection = collection.getClientCollection();
                
                ClientMediaEntry clientEntry = 
                    new ClientMediaEntry(clientService, clientCollection, getTitle(), 
                        "", getContent().getType(), getBytes());
                
                copyToRomeEntry(clientEntry);
                collection.getClientCollection().addEntry(clientEntry); 
                this.editURI = clientEntry.getEditURI();
                
            } else {
                ClientAtomService clientService = ((AtomBlog)getBlog()).getService();
                ClientMediaEntry clientEntry = (ClientMediaEntry)clientService.getEntry(editURI);
                clientEntry.update(); 
            }
        } catch (Exception e) {
            throw new BlogClientException("Error creating entry", e);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void update(byte[] newBytes) throws BlogClientException {
        try {
            //((ClientMediaEntry)clientEntry).setBytes(newBytes);
            //clientEntry.update();
        } catch (Exception e) {
            throw new BlogClientException("Error creating entry", e);
        }
    }
        
    void copyFromRomeEntry(ClientEntry entry) {
        super.copyFromRomeEntry(entry);
        
        List links = entry.getOtherLinks();
        if (links != null) {
            for (Iterator iter = links.iterator(); iter.hasNext();) {
                Link link = (Link)iter.next();
                if ("edit-media".equals(link.getRel())) {
                    id = link.getHrefResolved();
                    break;
                }
            }
        }
        
        
    }
}
