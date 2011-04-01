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

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.io.WireFeedInput;
import com.sun.syndication.propono.utils.ProponoException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;


/** 
 * Enables iteration over entries in Atom protocol collection.
 */
public class EntryIterator implements Iterator {
    static final Log logger = LogFactory.getLog(EntryIterator.class);
    private final ClientCollection collection;

    int      maxEntries = 20;
    int      offset = 0;
    Iterator members = null;
    Feed     col = null;
    String   collectionURI;
    String   nextURI;
    
    EntryIterator(ClientCollection collection) throws ProponoException {
        this.collection = collection;
        collectionURI = collection.getHrefResolved();
        nextURI = collectionURI;
        getNextEntries();
    }
    
    /**
     * Returns true if more entries are available. 
     */
    public boolean hasNext() {
        if (!members.hasNext()) {
            try { 
                getNextEntries(); 
            } catch (Exception ignored) {
                logger.error("ERROR getting next entries", ignored);
            }
        }
        return members.hasNext();
    }
    
    /**
     * Get next entry in collection.
     */
    public Object next() {
        if (hasNext()) {
            Entry romeEntry = (Entry)members.next();
            try {
                if (!romeEntry.isMediaEntry()) {
                    return new ClientEntry(null, collection, romeEntry, true);
                } else { 
                    return new ClientMediaEntry(null, collection, romeEntry, true);
                }
            } catch (ProponoException e) {
                throw new RuntimeException("Unexpected exception creating ClientEntry or ClientMedia", e);
            }
        }
        throw new NoSuchElementException();
    }
    
    /**
     * Remove entry is not implemented.
     */
    public void remove() {
        // optional method, not implemented
    }
    
    private void getNextEntries() throws ProponoException {  
        if (nextURI == null) return;
        GetMethod colGet = new GetMethod( collection.getHrefResolved(nextURI) );
        collection.addAuthentication(colGet);
        try {
            collection.getHttpClient().executeMethod(colGet);
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(colGet.getResponseBodyAsStream());
            WireFeedInput feedInput = new WireFeedInput();
            col = (Feed) feedInput.build(doc);
        }  catch (Exception e) {
            throw new ProponoException("ERROR: fetching or parsing next entries, HTTP code: " + (colGet != null ? colGet.getStatusCode() : -1), e);
        } finally {
            colGet.releaseConnection();
        }
        members = col.getEntries().iterator();
        offset += col.getEntries().size();

        nextURI = null;
        List altLinks = col.getOtherLinks();
        if (altLinks != null) {
            Iterator iter = altLinks.iterator();
            while (iter.hasNext()) {
                Link link = (Link)iter.next();
                if ("next".equals(link.getRel())) {
                    nextURI = link.getHref();
                }
            }
        }
    }
}
