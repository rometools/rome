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
package com.sun.syndication.propono.blogclient.atomprotocol;

import com.sun.syndication.propono.atom.client.ClientEntry;
import com.sun.syndication.propono.atom.client.ClientMediaEntry;
import java.util.Iterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sun.syndication.propono.blogclient.BlogClientException;

/**
 * Atom protocol implementation of BlogClient entry iterator.
 */
public class AtomEntryIterator implements Iterator {
    static final Log logger = LogFactory.getLog(AtomEntryIterator.class);
    private Iterator iterator = null;
    private AtomCollection collection = null;
    
    AtomEntryIterator(AtomCollection collection) throws BlogClientException {
        try {
            this.collection = collection;
            iterator = collection.getClientCollection().getEntries();
        } catch (Exception e) {
            throw new BlogClientException("ERROR fetching collection", e);
        }
    }
    
    /**
     * True if more entries are available.
     */
    public boolean hasNext() {
        return iterator.hasNext();
    }
    
    /**
     * Get next entry.
     */
    public Object next() {
        try {
            ClientEntry entry = (ClientEntry)iterator.next();
            if (entry instanceof ClientMediaEntry) {
                return new AtomResource(collection, (ClientMediaEntry)entry);
            } else {
                return new AtomEntry(collection, entry);
            }
        } catch (Exception e) {
            logger.error("ERROR fetching entry", e);
        }
        return null;
    }
    
    /**
     * Remove is not supported.
     */
    public void remove() {
        // optional method, not implemented
    }
}
