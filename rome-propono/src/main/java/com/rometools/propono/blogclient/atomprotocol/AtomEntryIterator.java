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
package com.rometools.propono.blogclient.atomprotocol;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.propono.atom.client.ClientEntry;
import com.rometools.propono.atom.client.ClientMediaEntry;
import com.rometools.propono.blogclient.BlogClientException;
import com.rometools.propono.blogclient.BlogEntry;

/**
 * Atom protocol implementation of BlogClient entry iterator.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class AtomEntryIterator implements Iterator<BlogEntry> {

    private static final Logger LOG = LoggerFactory.getLogger(AtomEntryIterator.class);

    private Iterator<ClientEntry> iterator = null;
    private AtomCollection collection = null;

    AtomEntryIterator(final AtomCollection collection) throws BlogClientException {
        try {
            this.collection = collection;
            iterator = collection.getClientCollection().getEntries();
        } catch (final Exception e) {
            throw new BlogClientException("ERROR fetching collection", e);
        }
    }

    /**
     * True if more entries are available.
     */
    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    /**
     * Get next entry.
     */
    @Override
    public BlogEntry next() {
        try {
            final ClientEntry entry = iterator.next();
            if (entry instanceof ClientMediaEntry) {
                return new AtomResource(collection, (ClientMediaEntry) entry);
            } else {
                return new AtomEntry(collection, entry);
            }
        } catch (final Exception e) {
            LOG.error("An error occured while fetching entry", e);
        }
        return null;
    }

    /**
     * Remove is not supported.
     */
    @Override
    public void remove() {
        // optional method, not implemented
    }
}
