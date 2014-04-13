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
package org.rometools.propono.atom.client;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.rometools.propono.utils.ProponoException;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.io.WireFeedInput;

/**
 * Enables iteration over entries in Atom protocol collection.
 */
public class EntryIterator implements Iterator<ClientEntry> {

    private static final Log LOGGER = LogFactory.getLog(EntryIterator.class);
    private final ClientCollection collection;

    private Iterator<Entry> members = null;
    private Feed col = null;
    private final String collectionURI;
    private String nextURI;

    EntryIterator(final ClientCollection collection) throws ProponoException {
        this.collection = collection;
        collectionURI = collection.getHrefResolved();
        nextURI = collectionURI;
        getNextEntries();
    }

    /**
     * Returns true if more entries are available.
     */
    @Override
    public boolean hasNext() {
        if (!members.hasNext()) {
            try {
                getNextEntries();
            } catch (final Exception ignored) {
                LOGGER.error("ERROR getting next entries", ignored);
            }
        }
        return members.hasNext();
    }

    /**
     * Get next entry in collection.
     */
    @Override
    public ClientEntry next() {
        if (hasNext()) {
            final Entry romeEntry = members.next();
            try {
                if (!romeEntry.isMediaEntry()) {
                    return new ClientEntry(null, collection, romeEntry, true);
                } else {
                    return new ClientMediaEntry(null, collection, romeEntry, true);
                }
            } catch (final ProponoException e) {
                throw new RuntimeException("Unexpected exception creating ClientEntry or ClientMedia", e);
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Remove entry is not implemented.
     */
    @Override
    public void remove() {
        // optional method, not implemented
    }

    private void getNextEntries() throws ProponoException {
        if (nextURI == null) {
            return;
        }
        final GetMethod colGet = new GetMethod(collection.getHrefResolved(nextURI));
        collection.addAuthentication(colGet);
        try {
            collection.getHttpClient().executeMethod(colGet);
            final SAXBuilder builder = new SAXBuilder();
            final Document doc = builder.build(colGet.getResponseBodyAsStream());
            final WireFeedInput feedInput = new WireFeedInput();
            col = (Feed) feedInput.build(doc);
        } catch (final Exception e) {
            throw new ProponoException("ERROR: fetching or parsing next entries, HTTP code: " + (colGet != null ? colGet.getStatusCode() : -1), e);
        } finally {
            colGet.releaseConnection();
        }
        members = col.getEntries().iterator();
        col.getEntries().size();

        nextURI = null;
        final List<Link> altLinks = col.getOtherLinks();
        if (altLinks != null) {
            for (final Link link : altLinks) {
                if ("next".equals(link.getRel())) {
                    nextURI = link.getHref();
                }
            }
        }
    }

}
