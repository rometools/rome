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
package com.rometools.propono.blogclient.metaweblog;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.rometools.propono.blogclient.Blog;
import com.rometools.propono.blogclient.BlogClientException;
import com.rometools.propono.blogclient.BlogEntry;
import com.rometools.propono.blogclient.BlogEntry.Category;
import com.rometools.propono.blogclient.BlogResource;

/**
 * Blog implementation that uses a mix of Blogger and MetaWeblog API methods.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class MetaWeblogBlog implements Blog {

    private final String blogid;
    private final String name;
    private final URL url;
    private final String userName;
    private final String password;
    private final Map<String, MetaWeblogBlogCollection> collections;

    private String appkey = "dummy";
    private XmlRpcClient xmlRpcClient = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getToken() {
        return blogid;
    }

    /**
     * String representation of blog, returns the name.
     */
    @Override
    public String toString() {
        return getName();
    }

    private XmlRpcClient getXmlRpcClient() {

        if (xmlRpcClient == null) {
            final XmlRpcClientConfigImpl xmlrpcConfig = new XmlRpcClientConfigImpl();
            xmlrpcConfig.setServerURL(url);
            xmlRpcClient = new XmlRpcClient();
            xmlRpcClient.setConfig(xmlrpcConfig);
        }
        return xmlRpcClient;
    }

    MetaWeblogBlog(final String blogid, final String name, final URL url, final String userName, final String password) {
        this.blogid = blogid;
        this.name = name;
        this.url = url;
        this.userName = userName;
        this.password = password;
        collections = new TreeMap<String, MetaWeblogBlogCollection>();
        collections.put("entries", new MetaWeblogBlogCollection(this, "entries", "Entries", "entry"));
        collections.put("resources", new MetaWeblogBlogCollection(this, "resources", "Resources", "*"));
    }

    MetaWeblogBlog(final String blogId, final String name, final URL url, final String userName, final String password, final String appkey) {
        this(blogId, name, url, userName, password);
        this.appkey = appkey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlogEntry newEntry() {
        return new MetaWeblogEntry(this, new HashMap<String, Object>());
    }

    String saveEntry(final BlogEntry entry) throws BlogClientException {
        final Blog.Collection col = collections.get("entries");
        return col.saveEntry(entry);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlogEntry getEntry(final String id) throws BlogClientException {
        try {
            final Object[] params = new Object[] { id, userName, password };
            final Object response = getXmlRpcClient().execute("metaWeblog.getPost", params);
            @SuppressWarnings("unchecked")
            final Map<String, Object> result = (Map<String, Object>) response;
            return new MetaWeblogEntry(this, result);
        } catch (final Exception e) {
            throw new BlogClientException("ERROR: XML-RPC error getting entry", e);
        }
    }

    void deleteEntry(final String id) throws BlogClientException {
        try {
            getXmlRpcClient().execute("blogger.deletePost", new Object[] { appkey, id, userName, password, Boolean.FALSE });
        } catch (final Exception e) {
            throw new BlogClientException("ERROR: XML-RPC error getting entry", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<BlogEntry> getEntries() throws BlogClientException {
        return new EntryIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlogResource newResource(final String name, final String contentType, final byte[] bytes) throws BlogClientException {
        return new MetaWeblogResource(this, name, contentType, bytes);
    }

    String saveResource(final MetaWeblogResource resource) throws BlogClientException {
        final Blog.Collection col = collections.get("resources");
        return col.saveResource(resource);
    }

    BlogResource getResource(final String token) throws BlogClientException {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NoOpIterator<BlogEntry> getResources() throws BlogClientException {
        return new NoOpIterator<BlogEntry>();
    }

    void deleteResource(final BlogResource resource) throws BlogClientException {
        // no-op
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Category> getCategories() throws BlogClientException {

        final ArrayList<Category> ret = new ArrayList<Category>();

        try {

            final Object result = getXmlRpcClient().execute("metaWeblog.getCategories", new Object[] { blogid, userName, password });

            if (result != null && result instanceof HashMap) {

                // Standard MetaWeblog API style: struct of struts
                @SuppressWarnings("unchecked")
                final Map<String, Object> catsmap = (Map<String, Object>) result;

                final Set<String> keys = catsmap.keySet();
                for (final String key : keys) {
                    @SuppressWarnings("unchecked")
                    final Map<String, Object> catmap = (Map<String, Object>) catsmap.get(key);
                    final BlogEntry.Category category = new BlogEntry.Category(key);
                    final String description = (String) catmap.get("description");
                    category.setName(description);
                    // catmap.get("htmlUrl");
                    // catmap.get("rssUrl");
                    ret.add(category);
                }

            } else if (result != null && result instanceof Object[]) {
                // Wordpress style: array of structs
                final Object[] array = (Object[]) result;
                for (final Object map : array) {
                    @SuppressWarnings("unchecked")
                    final Map<String, Object> catmap = (Map<String, Object>) map;
                    final String categoryId = (String) catmap.get("categoryId");
                    final String categoryName = (String) catmap.get("categoryName");
                    final BlogEntry.Category category = new BlogEntry.Category(categoryId);
                    category.setName(categoryName);
                    ret.add(category);
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return ret;

    }

    private Map<String, Object> createPostStructure(final BlogEntry entry) {
        return ((MetaWeblogEntry) entry).toPostStructure();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Collection> getCollections() throws BlogClientException {
        return new ArrayList<Collection>(collections.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Blog.Collection getCollection(final String token) throws BlogClientException {
        return collections.get(token);
    }

    // -------------------------------------------------------------------------

    /** MetaWeblog API impplementation of Blog.Collection */
    public class MetaWeblogBlogCollection implements Blog.Collection {
        private String accept = null;
        private String title = null;
        private String token = null;
        private Blog blog = null;

        /**
         * @param token Identifier for collection, unique within blog
         * @param title Title of collection
         * @param accept Content types accepted, either "entry" or "*"
         */
        public MetaWeblogBlogCollection(final Blog blog, final String token, final String title, final String accept) {
            this.blog = blog;
            this.accept = accept;
            this.title = title;
            this.token = token;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getTitle() {
            return title;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getToken() {
            return token;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public List<String> getAccepts() {
            return Collections.singletonList(accept);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BlogResource newResource(final String name, final String contentType, final byte[] bytes) throws BlogClientException {
            return blog.newResource(name, contentType, bytes);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BlogEntry newEntry() throws BlogClientException {
            return blog.newEntry();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean accepts(final String ct) {
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
        @Override
        public Iterator<BlogEntry> getEntries() throws BlogClientException {
            Iterator<BlogEntry> ret = null;
            if (accept.equals("entry")) {
                ret = MetaWeblogBlog.this.getEntries();
            } else {
                ret = getResources();
            }
            return ret;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String saveEntry(final BlogEntry entry) throws BlogClientException {
            String ret = entry.getId();
            if (entry.getId() == null) {
                try {
                    ret = (String) getXmlRpcClient().execute("metaWeblog.newPost",
                            new Object[] { blogid, userName, password, createPostStructure(entry), new Boolean(!entry.getDraft()) });
                } catch (final Exception e) {
                    throw new BlogClientException("ERROR: XML-RPC error saving new entry", e);
                }
            } else {
                try {
                    getXmlRpcClient().execute("metaWeblog.editPost",
                            new Object[] { entry.getId(), userName, password, createPostStructure(entry), new Boolean(!entry.getDraft()) });
                } catch (final Exception e) {
                    throw new BlogClientException("ERROR: XML-RPC error updating entry", e);
                }
            }
            return ret;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String saveResource(final BlogResource res) throws BlogClientException {
            final MetaWeblogResource resource = (MetaWeblogResource) res;
            try {
                final HashMap<String, Object> resmap = new HashMap<String, Object>();
                resmap.put("name", resource.getName());
                resmap.put("type", resource.getContent().getType());
                resmap.put("bits", resource.getBytes());
                final Object[] params = new Object[] { blogid, userName, password, resmap };
                final Object response = getXmlRpcClient().execute("metaWeblog.newMediaObject", params);
                @SuppressWarnings("unchecked")
                final Map<String, Object> result = (Map<String, Object>) response;
                final String url = (String) result.get("url");
                res.getContent().setSrc(url);
                return url;
            } catch (final Exception e) {
                throw new BlogClientException("ERROR: loading or uploading file", e);
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public List<Category> getCategories() throws BlogClientException {
            return MetaWeblogBlog.this.getCategories();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Blog getBlog() {
            return blog;
        }
    }

    // -------------------------------------------------------------------------
    /**
     * Iterates over MetaWeblog API entries.
     */
    public class EntryIterator implements Iterator<BlogEntry> {

        private int pos = 0;
        private boolean eod = false;
        private static final int BUFSIZE = 30;
        private List<Map<String, Object>> results = null;

        /**
         * Iterator for looping over MetaWeblog API entries.
         */
        public EntryIterator() throws BlogClientException {
            getNextEntries();
        }

        /**
         * Returns true if more entries are avialable.
         */
        @Override
        public boolean hasNext() {
            if (pos == results.size() && !eod) {
                try {
                    getNextEntries();
                } catch (final Exception ignored) {
                }
            }
            return pos < results.size();
        }

        /**
         * Get next entry.
         */
        @Override
        public BlogEntry next() {
            return new MetaWeblogEntry(MetaWeblogBlog.this, results.get(pos++));
        }

        /**
         * Remove is not implemented.
         */
        @Override
        public void remove() {
        }

        private void getNextEntries() throws BlogClientException {
            final int requestSize = pos + BUFSIZE;
            try {
                final Object[] params = new Object[] { blogid, userName, password, new Integer(requestSize) };
                final Object response = getXmlRpcClient().execute("metaWeblog.getRecentPosts", params);
                @SuppressWarnings("unchecked")
                final Map<String, Object>[] resultsArray = (Map<String, Object>[]) response;
                results = Arrays.asList(resultsArray);
            } catch (final Exception e) {
                throw new BlogClientException("ERROR: XML-RPC error getting entry", e);
            }
            if (results.size() < requestSize) {
                eod = true;
            }
        }
    }

}
