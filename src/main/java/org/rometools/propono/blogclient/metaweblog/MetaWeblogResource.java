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

import org.rometools.propono.blogclient.BlogClientException;
import java.io.InputStream; 
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.rometools.propono.blogclient.BlogResource;
import java.util.HashMap;

/** 
 * MetaWeblog API implementation of an resource entry.
 */
public class MetaWeblogResource extends MetaWeblogEntry implements BlogResource {
    private MetaWeblogBlog blog;
    private String name;
    private String contentType;
    private byte[] bytes;

    MetaWeblogResource(MetaWeblogBlog blog, 
        String name, String contentType, byte[] bytes) {
        super(blog, new HashMap());
        this.blog = blog;
        this.name = name;
        this.contentType = contentType;
        this.bytes = bytes;
        this.content = new Content();
        this.content.setType(contentType);
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }
    
    /**
     * {@inheritDoc}
     */
    public String getToken() {
        return null;
    }
    /**
     * Get content-type of associated media resource.
     */
    public String getContentType() {
        return contentType;
    }
    /**
     * Get media resource as input stream.
     */
    public InputStream getAsStream() throws BlogClientException {
        HttpClient httpClient = new HttpClient();
        GetMethod method = new GetMethod(permalink);
        try {
            httpClient.executeMethod(method);
        } catch (Exception e) {
            throw new BlogClientException("ERROR: error reading file", e);
        }
        if (method.getStatusCode() != 200) {
            throw new BlogClientException("ERROR HTTP status=" + method.getStatusCode());
        }
        try {
            return method.getResponseBodyAsStream();
        } catch (Exception e) {
            throw new BlogClientException("ERROR: error reading file", e);
        }
    }  
    
    /**
     * {@inheritDoc}
     */
    public void save() throws BlogClientException {
        blog.saveResource(this);
    }

    /**
     * {@inheritDoc}
     */
    public void update(byte[] bytes) throws BlogClientException {
        this.bytes = bytes;
        save();
    }

    /**
     * Get resource data as byte array.
     */
    public byte[] getBytes() {
        return bytes;
    }
    
    /**
     * Not supported by MetaWeblog API
     */
    public void delete() throws BlogClientException {
    }
}
