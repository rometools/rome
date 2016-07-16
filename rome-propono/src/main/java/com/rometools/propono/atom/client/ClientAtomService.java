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
package com.rometools.propono.atom.client;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.propono.atom.common.AtomService;
import com.rometools.propono.utils.ProponoException;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.io.impl.Atom10Parser;

/**
 * This class models an Atom Publising Protocol Service Document. It extends the common
 * {@link com.rometools.rome.propono.atom.common.Collection} class to add a <code>getEntry()</code>
 * method and to return {@link com.rometools.rome.propono.atom.client.ClientWorkspace} objects
 * instead of common {@link com.rometools.rome.propono.atom.common.Workspace}s.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class ClientAtomService extends AtomService {

    private static final Logger LOG = LoggerFactory.getLogger(ClientAtomService.class);

    private String uri = null;
    private HttpClient httpClient = null;
    private AuthStrategy authStrategy = null;

    /**
     * Create Atom blog service instance for specified URL and user account.
     *
     * @param url End-point URL of Atom service
     */
    ClientAtomService(final String uri, final AuthStrategy authStrategy) throws ProponoException {
        this.uri = uri;
        this.authStrategy = authStrategy;
        final Document doc = getAtomServiceDocument();
        parseAtomServiceDocument(doc);
    }

    /**
     * Get full entry from service by entry edit URI.
     */
    public ClientEntry getEntry(final String uri) throws ProponoException {
        final GetMethod method = new GetMethod(uri);
        authStrategy.addAuthentication(httpClient, method);
        try {
            httpClient.executeMethod(method);
            if (method.getStatusCode() != 200) {
                throw new ProponoException("ERROR HTTP status code=" + method.getStatusCode());
            }
            final Entry romeEntry = Atom10Parser.parseEntry(new InputStreamReader(method.getResponseBodyAsStream()), uri, Locale.US);
            if (!romeEntry.isMediaEntry()) {
                return new ClientEntry(this, null, romeEntry, false);
            } else {
                return new ClientMediaEntry(this, null, romeEntry, false);
            }
        } catch (final Exception e) {
            throw new ProponoException("ERROR: getting or parsing entry/media", e);
        } finally {
            method.releaseConnection();
        }
    }

    void addAuthentication(final HttpMethodBase method) throws ProponoException {
        authStrategy.addAuthentication(httpClient, method);
    }

    AuthStrategy getAuthStrategy() {
        return authStrategy;
    }

    private Document getAtomServiceDocument() throws ProponoException {
        GetMethod method = null;
        final int code = -1;
        try {
            httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
            // TODO: make connection timeout configurable
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);

            method = new GetMethod(uri);
            authStrategy.addAuthentication(httpClient, method);
            httpClient.executeMethod(method);

            final SAXBuilder builder = new SAXBuilder();
            final String doc = method.getResponseBodyAsString();
            LOG.debug(doc);
            return builder.build(method.getResponseBodyAsStream());

        } catch (final Throwable t) {
            final String msg = "ERROR retrieving Atom Service Document, code: " + code;
            LOG.debug(msg, t);
            throw new ProponoException(msg, t);
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }
    }

    /** Deserialize an Atom service XML document into an object */
    private void parseAtomServiceDocument(final Document document) throws ProponoException {
        final Element root = document.getRootElement();
        final List<Element> spaces = root.getChildren("workspace", AtomService.ATOM_PROTOCOL);
        for (final Element e : spaces) {
            addWorkspace(new ClientWorkspace(e, this, uri));
        }
    }

    /**
     * Package access to httpClient.
     */
    HttpClient getHttpClient() {
        return httpClient;
    }

}
