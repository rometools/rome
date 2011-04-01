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
package com.sun.syndication.propono.blogclient.metaweblog;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

import com.sun.syndication.propono.blogclient.BlogConnection;
import com.sun.syndication.propono.blogclient.Blog;
import com.sun.syndication.propono.blogclient.BlogClientException;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 * BlogClient implementation that uses a mix of Blogger and MetaWeblog API methods.
 */
public class MetaWeblogConnection implements BlogConnection {
    private URL url = null;
    private String userName = null;
    private String password = null;
    private String appkey = "null";
    private Map blogs = null;
    
    private XmlRpcClient xmlRpcClient = null;
        
    public MetaWeblogConnection(String url, String userName, String password) 
            throws BlogClientException {
        this.userName = userName;
        this.password = password;
        try {
            this.url = new URL(url);
            blogs = createBlogMap();
        } catch (Throwable t) {
            throw new BlogClientException("ERROR connecting to server", t);
        }
    }
    
    private XmlRpcClient getXmlRpcClient() {         
        if (xmlRpcClient == null) {
            XmlRpcClientConfigImpl xmlrpcConfig = new XmlRpcClientConfigImpl();
            xmlrpcConfig.setServerURL(url);
            xmlRpcClient = new XmlRpcClient();
            xmlRpcClient.setConfig(xmlrpcConfig);
        }
        return xmlRpcClient; 
    }
    
    /**
     * {@inheritDoc}
     */
    public List getBlogs() {
        return new ArrayList(blogs.values());
    }
    
    /**
     * {@inheritDoc}
     */
    private Map createBlogMap() throws XmlRpcException, IOException {
        Map blogMap = new HashMap();
        Object[] results = (Object[])getXmlRpcClient().execute("blogger.getUsersBlogs", 
            new Object[] {appkey, userName, password});
        for (int i = 0; i < results.length; i++) {
            Map blog = (Map)results[i];
            String blogid = (String)blog.get("blogid");
            String name = (String)blog.get("blogName");
            blogMap.put(blogid, new MetaWeblogBlog(blogid, name, url, userName, password));
        }
        return blogMap;
    }
    
     /**
     * {@inheritDoc}
     */
    public Blog getBlog(String token) {
        return (Blog)blogs.get(token);
    }
    
    /**
     * {@inheritDoc}
     */
    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
}

