/*
 * Copyright 2007 Apache Software Foundation
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package org.rometools.propono.atom.server;

import com.sun.syndication.feed.atom.Content;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.io.WireFeedOutput;
import com.sun.syndication.io.impl.Atom10Generator;
import com.sun.syndication.io.impl.Atom10Parser;
import org.rometools.propono.atom.common.AtomService;
import org.rometools.propono.atom.common.Categories;
import org.rometools.propono.utils.Utilities;
import java.io.BufferedReader;
import java.util.Collections;
import java.util.Iterator;
import javax.servlet.ServletConfig;

/**
 * Atom Servlet implements Atom protocol by calling an 
 * {@link com.sun.syndication.propono.atom.server.AtomHandler}
 * implementation. This servlet takes care of parsing incoming XML into ROME
 * Atom {@link com.sun.syndication.feed.atom.Entry} objects, passing those to the handler and serializing
 * to the response the entries and feeds returned by the handler.
 */
public class AtomServlet extends HttpServlet {
   
    /**
     * Get feed type support by Servlet, "atom_1.0"
     */
    public static final String FEED_TYPE = "atom_1.0";
    private static String contextDirPath = null;

    private static Log log =
            LogFactory.getFactory().getInstance(AtomServlet.class);
    
    static {
        Atom10Parser.setResolveURIs(true);
    }
    
    //-----------------------------------------------------------------------------
    /**
     * Create an Atom request handler.
     * TODO: make AtomRequestHandler implementation configurable.
     */
    private AtomHandler createAtomRequestHandler(
            HttpServletRequest request, HttpServletResponse response)
    throws ServletException {
        AtomHandlerFactory ahf = AtomHandlerFactory.newInstance();
        return ahf.newAtomHandler(request, response);
    }

    //-----------------------------------------------------------------------------
    /**
     * Handles an Atom GET by calling handler and writing results to response.
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException { 
        log.debug("Entering");
        AtomHandler handler = createAtomRequestHandler(req, res);
        String userName = handler.getAuthenticatedUsername();
        if (userName != null) {
            AtomRequest areq = new AtomRequestImpl(req);
            try {
                if (handler.isAtomServiceURI(areq)) {
                    // return an Atom Service document
                    AtomService service = handler.getAtomService(areq);
                    Document doc = service.serviceToDocument(); 
                    res.setContentType("application/atomsvc+xml; charset=utf-8");
                    Writer writer = res.getWriter();
                    XMLOutputter outputter = new XMLOutputter();
                    outputter.setFormat(Format.getPrettyFormat());
                    outputter.output(doc, writer);
                    writer.close();
                    res.setStatus(HttpServletResponse.SC_OK);
                } 
                else if (handler.isCategoriesURI(areq)) {
                    Categories cats = handler.getCategories(areq);
                    res.setContentType("application/xml");
                    Writer writer = res.getWriter();
                    Document catsDoc = new Document();
                    catsDoc.setRootElement(cats.categoriesToElement());
                    XMLOutputter outputter = new XMLOutputter();
                    outputter.output(catsDoc, writer);
                    writer.close();
                    res.setStatus(HttpServletResponse.SC_OK);
                } 
                else if (handler.isCollectionURI(areq)) {
                    // return a collection
                    Feed col = handler.getCollection(areq);
                    col.setFeedType(FEED_TYPE);
                    WireFeedOutput wireFeedOutput = new WireFeedOutput();
                    Document feedDoc = wireFeedOutput.outputJDom(col);
                    res.setContentType("application/atom+xml; charset=utf-8");
                    Writer writer = res.getWriter();
                    XMLOutputter outputter = new XMLOutputter();
                    outputter.setFormat(Format.getPrettyFormat());
                    outputter.output(feedDoc, writer);
                    writer.close();
                    res.setStatus(HttpServletResponse.SC_OK);
                } 
                else if (handler.isEntryURI(areq)) {
                    // return an entry
                    Entry entry = handler.getEntry(areq);
                    if (entry != null) {
                        res.setContentType("application/atom+xml; type=entry; charset=utf-8");
                        Writer writer = res.getWriter();
                        Atom10Generator.serializeEntry(entry, writer);
                        writer.close();
                    } else {
                        res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    }
                }
                else if (handler.isMediaEditURI(areq)) {
                    AtomMediaResource entry = handler.getMediaResource(areq);
                    res.setContentType(entry.getContentType());
                    res.setContentLength((int)entry.getContentLength());
                    Utilities.copyInputToOutput(entry.getInputStream(), res.getOutputStream());
                    res.getOutputStream().flush();
                    res.getOutputStream().close();
                }
                else {
                    res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (AtomException ae) {
                res.sendError(ae.getStatus(), ae.getMessage());
                log.debug("ERROR processing GET", ae);
            } catch (Exception e) {
                res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                log.debug("ERROR processing GET", e);
            }
        } else {
            res.setHeader("WWW-Authenticate", "BASIC realm=\"AtomPub\"");
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        log.debug("Exiting");
    }
    
    //-----------------------------------------------------------------------------
    /**
     * Handles an Atom POST by calling handler to identify URI, reading/parsing
     * data, calling handler and writing results to response.
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        log.debug("Entering");
        AtomHandler handler = createAtomRequestHandler(req, res);
        String userName = handler.getAuthenticatedUsername();
        if (userName != null) {
            AtomRequest areq = new AtomRequestImpl(req);
            try {
                if (handler.isCollectionURI(areq)) {
                    
                    if (req.getContentType().startsWith("application/atom+xml")) {

                        // parse incoming entry
                        Entry entry = Atom10Parser.parseEntry(new BufferedReader(
                            new InputStreamReader(req.getInputStream(), "UTF-8")), null);

                        // call handler to post it
                        Entry newEntry = handler.postEntry(areq, entry);

                        // set Location and Content-Location headers                        
                        for (Iterator it = newEntry.getOtherLinks().iterator(); it.hasNext();) {
                            Link link = (Link) it.next();
                            if ("edit".equals(link.getRel())) {
                                res.addHeader("Location", link.getHrefResolved());
                                break;
                            }
                        }
                        for (Iterator it = newEntry.getAlternateLinks().iterator(); it.hasNext();) {
                            Link link = (Link) it.next();
                            if ("alternate".equals(link.getRel())) {
                                res.addHeader("Content-Location", link.getHrefResolved());
                                break;
                            }
                        }

                        // write entry back out to response
                        res.setStatus(HttpServletResponse.SC_CREATED);
                        res.setContentType("application/atom+xml; type=entry; charset=utf-8");
                
                        Writer writer = res.getWriter();
                        Atom10Generator.serializeEntry(newEntry, writer);
                        writer.close(); 
                    
                    } else if (req.getContentType() != null) {
                                              
                        // get incoming title and slug from HTTP header
                        String title = areq.getHeader("Title");

                        // create new entry for resource, set title and type
                        Entry resource = new Entry();
                        resource.setTitle(title);
                        Content content = new Content();
                        content.setType(areq.getContentType());
                        resource.setContents(Collections.singletonList(content));
        
                        // hand input stream off to hander to post file
                        Entry newEntry = handler.postMedia(areq, resource);

                        // set Location and Content-Location headers
                        for (Iterator it = newEntry.getOtherLinks().iterator(); it.hasNext();) {
                            Link link = (Link) it.next();
                            if ("edit".equals(link.getRel())) {
                                res.addHeader("Location", link.getHrefResolved());
                                break;
                            }
                        }
                        for (Iterator it = newEntry.getAlternateLinks().iterator(); it.hasNext();) {
                            Link link = (Link) it.next();
                            if ("alternate".equals(link.getRel())) {
                                res.addHeader("Content-Location", link.getHrefResolved());
                                break;
                            }
                        }
     
                        res.setStatus(HttpServletResponse.SC_CREATED);                       
                        res.setContentType("application/atom+xml; type=entry; charset=utf-8");
                        
                        Writer writer = res.getWriter();
                        Atom10Generator.serializeEntry(newEntry, writer);
                        writer.close(); 
                        
                    } else {
                        res.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
                                "No content-type specified in request");
                    }
                    
                } else {
                    res.sendError(HttpServletResponse.SC_NOT_FOUND,
                            "Invalid collection specified in request");
                }
            } catch (AtomException ae) {
                res.sendError(ae.getStatus(), ae.getMessage());
                log.debug("ERROR processing POST", ae);
            } catch (Exception e) {
                res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                log.debug("ERROR processing POST", e);
            }
        } else {
            res.setHeader("WWW-Authenticate", "BASIC realm=\"AtomPub\"");
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        log.debug("Exiting");
    }
    
    //-----------------------------------------------------------------------------
    /**
     * Handles an Atom PUT by calling handler to identify URI, reading/parsing
     * data, calling handler and writing results to response.
     */
    protected void doPut(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        log.debug("Entering");
        AtomHandler handler = createAtomRequestHandler(req, res);
        String userName = handler.getAuthenticatedUsername();
        if (userName != null) {
            AtomRequest areq = new AtomRequestImpl(req);
            try {
                if (handler.isEntryURI(areq)) {
                    
                    // parse incoming entry
                    Entry unsavedEntry = Atom10Parser.parseEntry(new BufferedReader(
                        new InputStreamReader(req.getInputStream(), "UTF-8")), null);
                    
                    // call handler to put entry
                    handler.putEntry(areq, unsavedEntry);
                    
                    res.setStatus(HttpServletResponse.SC_OK);
                    
                } else if (handler.isMediaEditURI(areq)) {
                    
                    // hand input stream to handler
                    handler.putMedia(areq);
                                        
                    res.setStatus(HttpServletResponse.SC_OK);
                    
                } else {
                    res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (AtomException ae) {
                res.sendError(ae.getStatus(), ae.getMessage());
                log.debug("ERROR processing PUT", ae);
            } catch (Exception e) {
                res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                log.debug("ERROR processing PUT", e);
            }
        } else {
            res.setHeader("WWW-Authenticate", "BASIC realm=\"AtomPub\"");
            // Wanted to use sendError() here but Tomcat sends 403 forbidden 
            // when I do that, so sticking with setStatus() for time being.
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        log.debug("Exiting");
    }
    
    //-----------------------------------------------------------------------------
    /**
     * Handle Atom DELETE by calling appropriate handler.
     */
    protected void doDelete(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        log.debug("Entering");
        AtomHandler handler = createAtomRequestHandler(req, res);
        String userName = handler.getAuthenticatedUsername();
        if (userName != null) {
            AtomRequest areq = new AtomRequestImpl(req);
            try {
                if (handler.isEntryURI(areq)) {
                    handler.deleteEntry(areq);
                    res.setStatus(HttpServletResponse.SC_OK);
                } 
                else {
                    res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (AtomException ae) {
                res.sendError(ae.getStatus(), ae.getMessage());
                log.debug("ERROR processing DELETE", ae);
            } catch (Exception e) {
                res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                log.debug("ERROR processing DELETE", e);
            }
        } else {
            res.setHeader("WWW-Authenticate", "BASIC realm=\"AtomPub\"");
            // Wanted to use sendError() here but Tomcat sends 403 forbidden 
            // when I do that, so sticking with setStatus() for time being.
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        log.debug("Exiting");
    }
    
    /**
     * Initialize servlet.
     */
    public void init( ServletConfig config ) throws ServletException {
        super.init( config );
        contextDirPath = getServletContext().getRealPath("/");
        
    }
    
    /**
     * Get absolute path to Servlet context directory.
     */
    public static String getContextDirPath() {
        return contextDirPath;
    }
}
