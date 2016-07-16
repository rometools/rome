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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.rometools.propono.blogclient.Blog;
import com.rometools.propono.blogclient.BlogClientException;
import com.rometools.propono.blogclient.BlogConnection;

/**
 * BlogClient implementation that uses a mix of Blogger and MetaWeblog API methods.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class MetaWeblogConnection implements BlogConnection {

    private URL url = null;
    private String userName = null;
    private String password = null;
    private String appkey = "null";
    private Map<String, MetaWeblogBlog> blogs = null;

    private XmlRpcClient xmlRpcClient = null;

    public MetaWeblogConnection(final String url, final String userName, final String password) throws BlogClientException {
        this.userName = userName;
        this.password = password;
        try {
            this.url = new URL(url);
            blogs = createBlogMap();
        } catch (final Throwable t) {
            throw new BlogClientException("ERROR connecting to server", t);
        }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Blog> getBlogs() {
        return new ArrayList<Blog>(blogs.values());
    }

    /**
     * {@inheritDoc}
     */
    private Map<String, MetaWeblogBlog> createBlogMap() throws XmlRpcException, IOException {
        final Map<String, MetaWeblogBlog> blogMap = new HashMap<String, MetaWeblogBlog>();
        final Object[] results = (Object[]) getXmlRpcClient().execute("blogger.getUsersBlogs", new Object[] { appkey, userName, password });
        for (final Object result : results) {
            @SuppressWarnings("unchecked")
            final Map<String, Object> blog = (Map<String, Object>) result;
            final String blogid = (String) blog.get("blogid");
            final String name = (String) blog.get("blogName");
            blogMap.put(blogid, new MetaWeblogBlog(blogid, name, url, userName, password));
        }
        return blogMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Blog getBlog(final String token) {
        return blogs.get(token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAppkey(final String appkey) {
        this.appkey = appkey;
    }
}
