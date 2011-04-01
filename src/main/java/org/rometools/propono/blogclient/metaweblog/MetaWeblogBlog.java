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
package org.rometools.propono.blogclient.metaweblog;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import org.rometools.propono.blogclient.BlogEntry;
import org.rometools.propono.blogclient.Blog;
import org.rometools.propono.blogclient.BlogClientException;
import org.rometools.propono.blogclient.BlogResource;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 * Blog implementation that uses a mix of Blogger and MetaWeblog API methods.
 */
public class MetaWeblogBlog implements Blog {
    private String blogid;
    private String name;
    private URL    url;
    private String userName;
    private String password;
    private String appkey = "dummy";
    private Map    collections;
    
    private XmlRpcClient xmlRpcClient = null;

    /**
     * {@inheritDoc}
     */
    public String getName() { return name; }
    
    /**
     * {@inheritDoc}
     */
    public String getToken() { return blogid; }
    
    /**
     * String representation of blog, returns the name.
     */
    public String toString() { return getName(); }
    
    private XmlRpcClient getXmlRpcClient() { 
        
        if (xmlRpcClient == null) {
            XmlRpcClientConfigImpl xmlrpcConfig = new XmlRpcClientConfigImpl();
            xmlrpcConfig.setServerURL(url);
            xmlRpcClient = new XmlRpcClient();
            xmlRpcClient.setConfig(xmlrpcConfig);
        }
        return xmlRpcClient; 
    }
    
    MetaWeblogBlog(String blogid, String name, 
            URL url, String userName, String password) {
        this.blogid = blogid;
        this.name = name;
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.collections = new TreeMap();
        collections.put("entries", 
            new MetaWeblogBlogCollection(this, "entries", "Entries", "entry"));
        collections.put("resources", 
            new MetaWeblogBlogCollection(this, "resources", "Resources", "*"));
    }

    MetaWeblogBlog(String blogId, String name, 
            URL url, String userName, String password, String appkey) {
        this(blogId, name, url, userName, password);
        this.appkey = appkey;
    }

    /**
     * {@inheritDoc}
     */
    public BlogEntry newEntry() {
        return new MetaWeblogEntry(this, new HashMap());
    }

    String saveEntry(BlogEntry entry) throws BlogClientException {
        Blog.Collection col = (Blog.Collection)collections.get("entries");
        return col.saveEntry(entry);
    }

    /**
     * {@inheritDoc}
     */
    public BlogEntry getEntry(String id) throws BlogClientException {
        try {
            Map result = (Map)
                getXmlRpcClient().execute("metaWeblog.getPost", new Object[] {id, userName, password});
            return new MetaWeblogEntry(this, result);
        } catch (Exception e) {
            throw new BlogClientException("ERROR: XML-RPC error getting entry", e);
        }
    }

    void deleteEntry(String id) throws BlogClientException {
        try {
            getXmlRpcClient().execute("blogger.deletePost", 
                new Object[] {appkey, id, userName, password, Boolean.FALSE});
        } catch (Exception e) {
            throw new BlogClientException("ERROR: XML-RPC error getting entry", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Iterator getEntries() throws BlogClientException {
        return new EntryIterator();
    }

    /**
     * {@inheritDoc}
     */
    public BlogResource newResource(String name, String contentType, byte[] bytes) throws BlogClientException {
        return new MetaWeblogResource(this, name, contentType, bytes);
    }
    
    String saveResource(MetaWeblogResource resource) throws BlogClientException {  
        Blog.Collection col = (Blog.Collection)collections.get("resources");
        return col.saveResource(resource);
    }
    
    BlogResource getResource(String token) throws BlogClientException {
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public Iterator getResources() throws BlogClientException {
        return new NoOpIterator();
    }
     
    void deleteResource(BlogResource resource) throws BlogClientException {
        // no-op
    }
    
    /**
     * {@inheritDoc}
     */
    public List getCategories() throws BlogClientException {
 
        ArrayList ret = new ArrayList();        
        try {
            Object result = 
                getXmlRpcClient().execute ("metaWeblog.getCategories", 
                new Object[] {blogid, userName, password});
            if (result != null && result instanceof HashMap) {
                // Standard MetaWeblog API style: struct of struts
                Map catsmap = (Map)result;
                Iterator keys = catsmap.keySet().iterator();
                while (keys.hasNext()) {
                    String key = (String)keys.next();
                    Map catmap = (Map)catsmap.get(key);
                    BlogEntry.Category category = new BlogEntry.Category(key);
                    category.setName((String)catmap.get("description"));
                    // catmap.get("htmlUrl"); 
                    // catmap.get("rssUrl");
                    ret.add(category);
                } 
            } else if (result != null && result instanceof Object[]) {
                // Wordpress style: array of structs
                Object[] resultArray = (Object[])result;
                for (int i=0; i<resultArray.length; i++) {
                    Map catmap = (Map)resultArray[i];
                    String categoryId = (String)catmap.get("categoryId");
                    String categoryName = (String)catmap.get("categoryName");
                    BlogEntry.Category category = new BlogEntry.Category(categoryId);
                    category.setName(categoryName);
                    ret.add(category);   
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    private HashMap createPostStructure(BlogEntry entry) {
        return ((MetaWeblogEntry)entry).toPostStructure();
    }
        
    /**
     * {@inheritDoc}
     */
    public List getCollections() throws BlogClientException {
        return new ArrayList(collections.values());
    }

    /**
     * {@inheritDoc}
     */
    public Blog.Collection getCollection(String token) throws BlogClientException {
        return (Blog.Collection)collections.get(token);
    }
    
    //-------------------------------------------------------------------------
    
    /** MetaWeblog API impplementation of Blog.Collection */
    public class MetaWeblogBlogCollection implements Blog.Collection {
        private String accept = null;
        private String title = null;
        private String token = null;
        private Blog blog = null;
        
        /**
         * @param token  Identifier for collection, unique within blog
         * @param title  Title of collection
         * @param accept Content types accepted, either "entry" or "*"
         */
        public MetaWeblogBlogCollection(Blog blog, String token, String title, String accept) {
            this.blog = blog; 
            this.accept = accept;
            this.title = title;
            this.token = token;
        }
        
        /**
         * {@inheritDoc}
         */
        public String getTitle() {
            return title;
        }

        /**
         * {@inheritDoc}
         */
        public String getToken() {
            return token;
        }

        /**
         * {@inheritDoc}
         */
        public List getAccepts() {
            return Collections.singletonList(accept);
        }
        
        /**
         * {@inheritDoc}
         */
        public BlogResource newResource(String name, String contentType, byte[] bytes) throws BlogClientException {
            return blog.newResource(name, contentType, bytes);
        }
    
        /**
         * {@inheritDoc}
         */
        public BlogEntry newEntry() throws BlogClientException {
            return blog.newEntry();
        }

        /**
         * {@inheritDoc}
         */
        public boolean accepts(String ct) {
            if (accept.equals("*")) {
                // everything accepted
                return true;
            } else if (accept.equals("entry") && ct.equals("application/metaweblog+xml")) {
                // entries only accepted and "application/metaweblog+xml" means entry
                return true;
            }
            return false;
        }

        /**
         * {@inheritDoc}
         */
        public Iterator getEntries() throws BlogClientException {
            Iterator ret = null;
            if (accept.equals("entry")) {
                ret = MetaWeblogBlog.this.getEntries();
            } else {
                ret = MetaWeblogBlog.this.getResources();
            }
            return ret;
        }

        /**
         * {@inheritDoc}
         */
        public String saveEntry(BlogEntry entry) throws BlogClientException {
            String ret = entry.getId();
            if (entry.getId() == null) {
                try {
                    ret = (String)getXmlRpcClient().execute("metaWeblog.newPost", 
                        new Object[] {blogid, userName, password, createPostStructure(entry), new Boolean(!entry.getDraft()) });
                } catch (Exception e) {
                    throw new BlogClientException("ERROR: XML-RPC error saving new entry", e);
                }
            } else {
                try {
                    getXmlRpcClient().execute("metaWeblog.editPost", 
                        new Object[] {entry.getId(),userName,password,createPostStructure(entry),new Boolean(!entry.getDraft())});            
                } catch (Exception e) {
                    throw new BlogClientException("ERROR: XML-RPC error updating entry", e);
                }
            }
            return ret;
        }
        
        /**
         * {@inheritDoc}
         */
        public String saveResource(BlogResource res) throws BlogClientException {
            MetaWeblogResource resource = (MetaWeblogResource)res;
            try {
                HashMap resmap = new HashMap();
                resmap.put("name", resource.getName());
                resmap.put("type", resource.getContent().getType());
                resmap.put("bits", resource.getBytes());                           
                Map result = (Map)
                    getXmlRpcClient().execute("metaWeblog.newMediaObject", 
                    new Object[] {blogid, userName, password, resmap});
                String url = (String)result.get("url");
                res.getContent().setSrc(url);
                return url;
            } catch (Exception e) {
                throw new BlogClientException("ERROR: loading or uploading file", e); 
            }
        }

        /**
         * {@inheritDoc}
         */
        public List getCategories() throws BlogClientException {
            return MetaWeblogBlog.this.getCategories();
        }

        /**
         * {@inheritDoc}
         */
        public Blog getBlog() {
            return blog;
        }
    }  
    
    //-------------------------------------------------------------------------
    /**
     * Iterates over MetaWeblog API entries.
     */
    public class EntryIterator implements Iterator {
        private int pos = 0;
        private boolean eod = false;
        private static final int BUFSIZE = 30;
        private List results = null;
        /**
         * Iterator for looping over MetaWeblog API entries.
         */
        public EntryIterator() throws BlogClientException {
            getNextEntries();
        }
        /**
         * Returns true if more entries are avialable.
         */
        public boolean hasNext() {
            if (pos == results.size() && !eod) {
                try { getNextEntries(); } catch (Exception ignored) {}
            }
            return (pos < results.size());
        }
        /**
         * Get next entry.
         */
        public Object next() {
            Map entryHash = (Map)results.get(pos++);
            return new MetaWeblogEntry(MetaWeblogBlog.this, entryHash);
        }
        /**
         * Remove is not implemented.
         */
        public void remove() {
        }
        private void getNextEntries() throws BlogClientException {
            int requestSize = pos + BUFSIZE;
            try {
                Object[] resultsArray = (Object[])
                    getXmlRpcClient().execute("metaWeblog.getRecentPosts", 
                        new Object[] {blogid, userName, password, new Integer(requestSize)} );
                results = Arrays.asList(resultsArray);
            } catch (Exception e) {
                throw new BlogClientException("ERROR: XML-RPC error getting entry", e);
            }
            if (results.size() < requestSize) eod = true;
        }
    }
    
    //-------------------------------------------------------------------------
    /**
     * No-op iterator.
     */
    public class NoOpIterator implements Iterator {
        /**
         * No-op
         */
        public boolean hasNext() {
            return false;
        }
        /**
         * No-op
         */
        public Object next() {
            return null;
        }        
        /**
         * No-op
         */
        public void remove() {}
    }

}
