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

package com.rometools.propono.atom.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;

/**
 * Represents a media link entry.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class AtomMediaResource {
    private String contentType = null;
    private long contentLength = 0;
    private InputStream inputStream = null;
    private Date lastModified = null;
    private static FileTypeMap map = null;

    static {
        // TODO: figure out why PNG is missing from Java MIME types
        map = FileTypeMap.getDefaultFileTypeMap();
        if (map instanceof MimetypesFileTypeMap) {
            try {
                ((MimetypesFileTypeMap) map).addMimeTypes("image/png png PNG");
            } catch (final Exception ignored) {
            }
        }
    }

    public AtomMediaResource(final File resource) throws FileNotFoundException {
        contentType = map.getContentType(resource.getName());
        contentLength = resource.length();
        lastModified = new Date(resource.lastModified());
        inputStream = new FileInputStream(resource);
    }

    public AtomMediaResource(final String name, final long length, final Date lastModified, final InputStream is) throws FileNotFoundException {
        contentType = map.getContentType(name);
        contentLength = length;
        this.lastModified = lastModified;
        inputStream = is;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(final long contentLength) {
        this.contentLength = contentLength;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(final Date lastModified) {
        this.lastModified = lastModified;
    }

}
