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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class which helps in handling File persistence related operations.
 */
class FileStore {

    private static Log log = LogFactory.getFactory().getInstance(FileStore.class);

    private static final FileStore fileStore = new FileStore();

    public String getNextId() {
        // return UUID.randomUUID().toString(); // requires JDK 1.5
        return RandomStringUtils.randomAlphanumeric(20);
    }

    public boolean exists(final String path) {
        final File f = new File(path);
        return f.exists();
    }

    public InputStream getFileInputStream(final String path) {
        log.debug("getFileContents path: " + path);
        try {
            return new BufferedInputStream(new FileInputStream(path));
        } catch (final FileNotFoundException e) {
            log.debug("   File not found: " + path);
            return null;
        }
    }

    public OutputStream getFileOutputStream(final String path) {
        log.debug("getFileOutputStream path: " + path);
        try {
            final File f = new File(path);
            f.getParentFile().mkdirs();
            return new BufferedOutputStream(new FileOutputStream(f));
        } catch (final FileNotFoundException e) {
            log.debug("   File not found: " + path);
            return null;
        }
    }

    public void createOrUpdateFile(final String path, final InputStream content) throws FileNotFoundException, IOException {
        log.debug("createOrUpdateFile path: " + path);
        final File f = new File(path);
        f.mkdirs();
        final FileOutputStream out = new FileOutputStream(f);

        final byte[] buffer = new byte[2048];
        int read;
        while ((read = content.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    public void deleteFile(final String path) {
        log.debug("deleteFile path: " + path);
        final File f = new File(path);
        if (!f.delete()) {
            log.debug("   Failed to delete: " + f.getAbsolutePath());
        }
    }

    public static FileStore getFileStore() {
        return fileStore;
    }

    public boolean deleteDirectory(final String path) {
        return deleteDirectory(new File(path));
    }

    public boolean deleteDirectory(final File path) {
        log.debug("deleteDirectory path: " + path);
        if (path.exists()) {
            final File[] files = path.listFiles();
            for (final File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        return path.delete();
    }
}
