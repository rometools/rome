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

import java.io.InputStream;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.rometools.propono.blogclient.BlogClientException;
import com.rometools.propono.blogclient.BlogResource;

/**
 * MetaWeblog API implementation of an resource entry.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class MetaWeblogResource extends MetaWeblogEntry implements BlogResource {
    private final MetaWeblogBlog blog;
    private final String name;
    private final String contentType;
    private byte[] bytes;

    MetaWeblogResource(final MetaWeblogBlog blog, final String name, final String contentType, final byte[] bytes) {
        super(blog, new HashMap<String, Object>());
        this.blog = blog;
        this.name = name;
        this.contentType = contentType;
        this.bytes = bytes;
        content = new Content();
        content.setType(contentType);
    }

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
    @Override
    public InputStream getAsStream() throws BlogClientException {
        final HttpClient httpClient = new HttpClient();
        final GetMethod method = new GetMethod(permalink);
        try {
            httpClient.executeMethod(method);
        } catch (final Exception e) {
            throw new BlogClientException("ERROR: error reading file", e);
        }
        if (method.getStatusCode() != 200) {
            throw new BlogClientException("ERROR HTTP status=" + method.getStatusCode());
        }
        try {
            return method.getResponseBodyAsStream();
        } catch (final Exception e) {
            throw new BlogClientException("ERROR: error reading file", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save() throws BlogClientException {
        blog.saveResource(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final byte[] bytes) throws BlogClientException {
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
    @Override
    public void delete() throws BlogClientException {
    }
}
