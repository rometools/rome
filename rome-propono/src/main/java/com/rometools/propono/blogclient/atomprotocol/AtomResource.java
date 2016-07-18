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

import java.io.InputStream;
import java.util.List;

import com.rometools.propono.atom.client.ClientAtomService;
import com.rometools.propono.atom.client.ClientCollection;
import com.rometools.propono.atom.client.ClientEntry;
import com.rometools.propono.atom.client.ClientMediaEntry;
import com.rometools.propono.blogclient.BlogClientException;
import com.rometools.propono.blogclient.BlogEntry;
import com.rometools.propono.blogclient.BlogResource;
import com.rometools.rome.feed.atom.Link;

/**
 * Atom protocol implementation of BlogResource.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class AtomResource extends AtomEntry implements BlogResource {

    private AtomCollection collection;
    private byte[] bytes;

    AtomResource(final AtomCollection collection, final String name, final String contentType, final byte[] bytes) throws BlogClientException {
        super((AtomBlog) collection.getBlog(), collection);
        this.collection = collection;
        this.bytes = bytes;
        final BlogEntry.Content rcontent = new BlogEntry.Content();
        rcontent.setType(contentType);
        setContent(rcontent);
    }

    AtomResource(final AtomCollection collection, final ClientMediaEntry entry) throws BlogClientException {
        super(collection, entry);
    }

    AtomResource(final AtomBlog blog, final ClientMediaEntry entry) throws BlogClientException {
        super(blog, entry);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return getTitle();
    }

    byte[] getBytes() {
        return bytes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getAsStream() throws BlogClientException {
        try {
            return null; // ((ClientMediaEntry)clientEntry).getAsStream();
        } catch (final Exception e) {
            throw new BlogClientException("Error creating entry", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save() throws BlogClientException {
        try {
            if (getToken() == null) {
                final ClientAtomService clientService = ((AtomBlog) getBlog()).getService();
                final ClientCollection clientCollection = collection.getClientCollection();

                final ClientMediaEntry clientEntry = new ClientMediaEntry(clientService, clientCollection, getTitle(), "", getContent().getType(), getBytes());

                copyToRomeEntry(clientEntry);
                collection.getClientCollection().addEntry(clientEntry);
                editURI = clientEntry.getEditURI();

            } else {
                final ClientAtomService clientService = ((AtomBlog) getBlog()).getService();
                final ClientMediaEntry clientEntry = (ClientMediaEntry) clientService.getEntry(editURI);
                clientEntry.update();
            }
        } catch (final Exception e) {
            throw new BlogClientException("Error creating entry", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final byte[] newBytes) throws BlogClientException {
        try {
            // ((ClientMediaEntry)clientEntry).setBytes(newBytes);
            // clientEntry.update();
        } catch (final Exception e) {
            throw new BlogClientException("Error creating entry", e);
        }
    }

    @Override
    void copyFromRomeEntry(final ClientEntry entry) {
        super.copyFromRomeEntry(entry);
        final List<Link> links = entry.getOtherLinks();
        if (links != null) {
            for (final Link link : links) {
                if ("edit-media".equals(link.getRel())) {
                    id = link.getHrefResolved();
                    break;
                }
            }
        }
    }

}
