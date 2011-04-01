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
package org.rometools.propono.atom.server.impl;

import org.rometools.propono.atom.server.AtomMediaResource;
import org.apache.commons.codec.binary.Base64;
import org.rometools.propono.atom.server.AtomHandler;
import org.rometools.propono.atom.server.AtomException;
import org.rometools.propono.atom.server.AtomServlet;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import org.rometools.propono.atom.common.AtomService;
import org.rometools.propono.atom.common.Categories;
import org.rometools.propono.atom.server.AtomRequest;
import java.io.File;
import java.util.StringTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;


/**
 * File-based {@link com.sun.syndication.propono.atom.server.AtomHandler}
 * implementation that stores entries and media-entries to disk. Implemented 
 * using {@link com.sun.syndication.propono.atom.server.impl.FileBasedAtomService}.
 */
public class FileBasedAtomHandler implements AtomHandler {

    private static Log log =
            LogFactory.getFactory().getInstance(FileBasedAtomHandler.class);
    
    private static String fileStoreDir = null;

    private String userName = null;
    private String atomProtocolURL = null;
    private String contextURI = null;    
    private String uploadurl = null;
    
    private FileBasedAtomService service = null;
    
    /**
     * Construct handler to handle one request.
     * @param req Request to be handled.
     */
    public FileBasedAtomHandler( HttpServletRequest req ) {     
        this(req, AtomServlet.getContextDirPath());
    } 
    
    /**
     * Contruct handler for one request, using specified file storage directory.
     * @param req       Request to be handled.
     * @param uploaddir File storage upload dir.
     */
    public FileBasedAtomHandler(HttpServletRequest req, String uploaddir) {
        log.debug("ctor");
        
        userName = authenticateBASIC(req);        
        
        atomProtocolURL = req.getScheme() + "://" + req.getServerName() + ":" 
                + req.getServerPort()  + req.getContextPath() + req.getServletPath();
        
        contextURI = req.getScheme() + "://" + req.getServerName() + ":" 
                + req.getServerPort()  + req.getContextPath();
    
        try {
            service = new FileBasedAtomService(userName, uploaddir, 
                    contextURI, req.getContextPath(), req.getServletPath());            
        } catch (Throwable t) {
            throw new RuntimeException("ERROR creating FileBasedAtomService", t);
        }
    }
    
    /** 
     * Method used for validating user. Developers can overwrite this method 
     * and use credentials stored in Database or LDAP to confirm if the user is 
     * allowed to access this service.
     * @param login    user submitted login id
     * @param password user submitted password 
     */
    public boolean validateUser(String login, String password) {
        return true;
    }
    
    /**
     * Get username of authenticated user
     * @return User name.
     */
    public String getAuthenticatedUsername() {
        // For now return userName as the login id entered for authorization
        return userName;
    }
    
    /**
     * Get base URI of Atom protocol implementation.
     * @return Base URI of Atom protocol implemenation.
     */
    public String getAtomProtocolURL( ) {
        if ( atomProtocolURL == null ) {
            return "app";
        } else {
            return  atomProtocolURL;
        }
    }
    
    /**
     * Return introspection document
     * @throws com.sun.syndication.propono.atom.server.AtomException Unexpected exception.
     * @return AtomService object with workspaces and collections.
     */
    public AtomService getAtomService(AtomRequest areq) throws AtomException {               
        return service;
    }
    
    /**
     * Returns null because we use in-line categories.
     * @throws com.sun.syndication.propono.atom.server.AtomException Unexpected exception.
     * @return Categories object
     */
    public Categories getCategories(AtomRequest areq) throws AtomException {    
        log.debug("getCollection");
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        String handle = pathInfo[0];
        String collection = pathInfo[1];
        FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        return (Categories)col.getCategories(true).get(0); 
    }
    
    /**
     * Get collection specified by pathinfo.
     * @param areq Details of HTTP request
     * @return ROME feed representing collection.
     * @throws com.sun.syndication.propono.atom.server.AtomException Invalid collection or other exception.
     */
    public Feed getCollection(AtomRequest areq) throws AtomException {
        log.debug("getCollection");
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        String handle = pathInfo[0];
        String collection = pathInfo[1];
        FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        return col.getFeedDocument();        
    }
    
    /**
     * Create a new entry specified by pathInfo and posted entry. We save the 
     * submitted Atom entry verbatim, but we do set the id and reset the update
     * time.  
     *
     * @param entry Entry to be added to collection.
     * @param areq Details of HTTP request
     * @throws com.sun.syndication.propono.atom.server.AtomException On invalid collection or other error.
     * @return Entry as represented on server.
     */
    public Entry postEntry(AtomRequest areq, Entry entry) throws AtomException {
        log.debug("postEntry");
        
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        String handle = pathInfo[0];
        String collection = pathInfo[1];
        FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        try {
            return col.addEntry(entry); 
            
        } catch (Exception fe) {
            fe.printStackTrace();
            throw new AtomException( fe );
        }
    }
    
    /**
     * Get entry specified by pathInfo.
     * @param areq Details of HTTP request
     * @throws com.sun.syndication.propono.atom.server.AtomException On invalid pathinfo or other error.
     * @return ROME Entry object.
     */
    public Entry getEntry(AtomRequest areq) throws AtomException {
        log.debug("getEntry");
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        String handle = pathInfo[0];
        String collection = pathInfo[1];
        String fileName = pathInfo[2];
        FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        try {              
            return col.getEntry(fileName);
                
        } catch (Exception re) {
            if (re instanceof AtomException) throw (AtomException)re;
            throw new AtomException("ERROR: getting entry", re);
        }
    }
    
    /**
     * Update entry specified by pathInfo and posted entry.
     * 
     * @param entry 
     * @param areq Details of HTTP request
     * @throws com.sun.syndication.propono.atom.server.AtomException 
     */
    public void putEntry(AtomRequest areq, Entry entry) throws AtomException {
        log.debug("putEntry");
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        String handle = pathInfo[0];
        String collection = pathInfo[1];
        String fileName = pathInfo[2];
        FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        try {            
            col.updateEntry(entry, fileName);
            
        } catch ( Exception fe ) {
            throw new AtomException( fe );
        }
    }
    
    
    /**
     * Delete entry specified by pathInfo.
     * @param areq Details of HTTP request
     */
    public void deleteEntry(AtomRequest areq) throws AtomException {
        log.debug("deleteEntry");
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        String handle = pathInfo[0];
        String collection = pathInfo[1];
        String fileName = pathInfo[2];
        FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        try {
            col.deleteEntry(fileName); 

        } catch (Exception e) {
            String msg = "ERROR in atom.deleteResource";
            log.error(msg,e);
            throw new AtomException(msg);
        }
    }
    

    /**
     * Store media data in collection specified by pathInfo, create an Atom
     * media-link entry to store metadata for the new media file and return 
     * that entry to the caller.
     * @param areq Details of HTTP request
     * @param entry    New entry initialzied with only title and content type
     * @return Location URL of new media entry
     */
    public Entry postMedia(AtomRequest areq, Entry entry) throws AtomException {
        
        // get incoming slug from HTTP header
        String slug = areq.getHeader("Slug");
        
        if (log.isDebugEnabled()) {
            log.debug("postMedia - title: "+entry.getTitle()+" slug:"+slug);
        }       
                        
        try {
            File tempFile = null;
            String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
            String handle = pathInfo[0];
            String collection = pathInfo[1];
            FileBasedCollection col = service.findCollectionByHandle(handle, collection);
            try {
                col.addMediaEntry(entry, slug, areq.getInputStream());

            } catch (Exception e) {
                e.printStackTrace();
                String msg = "ERROR reading posted file";
                log.error(msg,e);
                throw new AtomException(msg, e);
            } finally {
                if (tempFile != null) tempFile.delete();
            }
            
        } catch (Exception re) {
            throw new AtomException("ERROR: posting media");
        }
        return entry;
   }
    
    /**
     * Update the media file part of a media-link entry.
     * @param areq Details of HTTP request
     * Assuming pathInfo of form /user-name/resource/name
     */
    public void putMedia(AtomRequest areq) throws AtomException {
        
        log.debug("putMedia");
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        String handle =     pathInfo[0];
        String collection = pathInfo[1];
        String fileName =   pathInfo[3];
        FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        try {
            col.updateMediaEntry(fileName, areq.getContentType(), areq.getInputStream());
            
        } catch (Exception re) {
            throw new AtomException("ERROR: posting media");
        }
    }
    
    public AtomMediaResource getMediaResource(AtomRequest areq) throws AtomException {
        log.debug("putMedia");
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        String handle =     pathInfo[0];
        String collection = pathInfo[1];
        String fileName =   pathInfo[3];
        FileBasedCollection col = service.findCollectionByHandle(handle, collection);
        try {
            return col.getMediaResource(fileName);
            
        } catch (Exception re) {
            throw new AtomException("ERROR: posting media");
        }
    }

    /**
     * Return true if specified pathinfo represents URI of service doc.
     */
    public boolean isAtomServiceURI(AtomRequest areq) {        
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        if (pathInfo.length==0) return true; 
        return false;
    }
    
    /**
     * Return true if specified pathinfo represents URI of category doc.
     */
    public boolean isCategoriesURI(AtomRequest areq) {        
        log.debug("isCategoriesDocumentURI");
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        if (pathInfo.length == 3 && "categories".equals(pathInfo[2])) {
            return true;
        }
        return false;
    }
    
    /**
     * Return true if specified pathinfo represents URI of a collection.
     */
    public boolean isCollectionURI(AtomRequest areq) {
        log.debug("isCollectionURI");
        // workspace/collection-plural
        // if length is 2 and points to a valid collection then YES
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        if (pathInfo.length == 2) {
            String handle     = pathInfo[0];
            String collection = pathInfo[1];
            if (service.findCollectionByHandle(handle, collection) != null) {
                return true;
            }
        }
        return false;
        
    }
    
    /**
     * Return true if specified pathinfo represents URI of an Atom entry.
     */
    public boolean isEntryURI(AtomRequest areq) {
        log.debug("isEntryURI");
        // workspace/collection-singular/fsid
        // if length is 3 and points to a valid collection then YES
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        if (pathInfo.length == 3) {
            String handle     = pathInfo[0];
            String collection = pathInfo[1];
            if (service.findCollectionByHandle(handle, collection) != null) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Return true if specified pathinfo represents media-edit URI.
     */
    public boolean isMediaEditURI(AtomRequest areq) {
        log.debug("isMediaEditURI");
        // workspace/collection-singular/fsid/media/fsid
        // if length is 4, points to a valid collection and fsid is mentioned twice then YES
        String[] pathInfo = StringUtils.split(areq.getPathInfo(),"/");
        if (pathInfo.length == 4) {
            String handle     = pathInfo[0];
            String collection = pathInfo[1];
            String media      = pathInfo[2];
            String fsid      = pathInfo[3];
            if (service.findCollectionByHandle(handle, collection) != null && media.equals("media")) {
                return true;
            }
        }
        return false;
        
    }
    
    /**
     * BASIC authentication.
     */
    public String authenticateBASIC(HttpServletRequest request) {
        log.debug("authenticateBASIC");
        boolean valid = false;
        String userID = null;
        String password = null;
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null) {
                StringTokenizer st = new StringTokenizer(authHeader);
                if (st.hasMoreTokens()) {
                    String basic = st.nextToken();
                    if (basic.equalsIgnoreCase("Basic")) {
                        String credentials = st.nextToken();
                        String userPass = new String(Base64.decodeBase64(credentials.getBytes()));
                        int p = userPass.indexOf(":");
                        if (p != -1) {
                            userID = userPass.substring(0, p);
                            password = userPass.substring(p+1);
                            
                            //  Validate the User.
                            valid = validateUser( userID, password );
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.debug(e);
        }
        if (valid) {
            //For now assume userID as userName
            return userID;
        }
        return null;
    }
}
