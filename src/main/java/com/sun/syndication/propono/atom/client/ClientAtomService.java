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
import com.sun.syndication.io.impl.Atom10Parser;
import com.sun.syndication.propono.utils.ProponoException;
import com.sun.syndication.propono.atom.common.AtomService;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


/**
 * This class models an Atom Publising Protocol Service Document.
 * It extends the common 
 * {@link com.sun.syndication.propono.atom.common.Collection} 
 * class to add a <code>getEntry()</code> method and to return 
 * {@link com.sun.syndication.propono.atom.client.ClientWorkspace} 
 * objects instead of common 
 * {@link com.sun.syndication.propono.atom.common.Workspace}s.
 */
public class ClientAtomService extends AtomService {
    private static Log logger = LogFactory.getLog(ClientAtomService.class);
    private String       uri = null;
    private HttpClient   httpClient = null;
    private AuthStrategy authStrategy = null;
    
    /**
     * Create Atom blog service instance for specified URL and user account.
     * @param url      End-point URL of Atom service
     */
    ClientAtomService(String uri, AuthStrategy authStrategy)
        throws ProponoException {
        this.uri = uri;
        this.authStrategy = authStrategy;
        Document doc = getAtomServiceDocument();
        parseAtomServiceDocument(doc);
    }
    
    /**
     * Get full entry from service by entry edit URI.
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
                return new ClientEntry(this, null, romeEntry, false);
            } else {
                return new ClientMediaEntry(this, null, romeEntry, false);
            }
        } catch (Exception e) {
            throw new ProponoException("ERROR: getting or parsing entry/media", e);
        } finally {
            method.releaseConnection();
        }
    }

    void addAuthentication(HttpMethodBase method) throws ProponoException {
        authStrategy.addAuthentication(httpClient, method);
    }

    AuthStrategy getAuthStrategy() {
        return authStrategy;
    }
    
    private Document getAtomServiceDocument() throws ProponoException {
        GetMethod method = null;
        int code = -1;
        try {
            httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
            // TODO: make connection timeout configurable
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);            
            
            method = new GetMethod(uri);
            authStrategy.addAuthentication(httpClient, method);
            httpClient.executeMethod(method);
            
            SAXBuilder builder = new SAXBuilder();
            return builder.build(method.getResponseBodyAsStream());
            
        } catch (Throwable t) {
            String msg = "ERROR retrieving Atom Service Document, code: "+code;
            logger.debug(msg, t);
            throw new ProponoException(msg, t);
        } finally {
            if (method != null) method.releaseConnection();
        }
    }

    /** Deserialize an Atom service XML document into an object */
    private void parseAtomServiceDocument(Document document) throws ProponoException {
        Element root = document.getRootElement();
        List spaces = root.getChildren("workspace", AtomService.ATOM_PROTOCOL);
        Iterator iter = spaces.iterator();
        while (iter.hasNext()) {
            Element e = (Element) iter.next();
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

