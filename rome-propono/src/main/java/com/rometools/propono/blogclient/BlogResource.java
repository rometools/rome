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
package com.rometools.propono.blogclient;

import java.io.InputStream;

/**
 * Represents a file that has been uploaded to a blog.
 * <p />
 * Resources are modeled as a type of BlogEntry, but be aware: not all servers can save resource
 * metadata (i.e. title, category, author, etc.). MetaWeblog based servers can't save metadata at
 * all and Atom protocol servers are not required to preserve uploaded file metadata.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public interface BlogResource extends BlogEntry {

    /** Get resource name (name is required) */
    public String getName();

    /** Get resource as stream, using content.src as URL */
    public InputStream getAsStream() throws BlogClientException;

    /** Update resource by immediately uploading new bytes to server */
    public void update(byte[] newBytes) throws BlogClientException;
}
