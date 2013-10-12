/*
 * Copyright 2005 Sun Microsystems, Inc.
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
package org.rometools.fetcher.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

/**
 * Disk based cache.
 */
public class DiskFeedInfoCache implements FeedFetcherCache {

    protected String cachePath = null;

    public DiskFeedInfoCache(final String cachePath) {
        this.cachePath = cachePath;
    }

    @Override
    public SyndFeedInfo getFeedInfo(final URL url) {
        SyndFeedInfo info = null;
        final String fileName = cachePath + File.separator + "feed_" + replaceNonAlphanumeric(url.toString(), '_').trim();
        FileInputStream fis;
        try {
            fis = new FileInputStream(fileName);
            final ObjectInputStream ois = new ObjectInputStream(fis);
            info = (SyndFeedInfo) ois.readObject();
            fis.close();
        } catch (final FileNotFoundException fnfe) {
            // That's OK, we'l return null
        } catch (final ClassNotFoundException cnfe) {
            // Error writing to cache is fatal
            throw new RuntimeException("Attempting to read from cache", cnfe);
        } catch (final IOException fnfe) {
            // Error writing to cache is fatal
            throw new RuntimeException("Attempting to read from cache", fnfe);
        }
        return info;
    }

    @Override
    public void setFeedInfo(final URL url, final SyndFeedInfo feedInfo) {
        final String fileName = cachePath + File.separator + "feed_" + replaceNonAlphanumeric(url.toString(), '_').trim();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            final ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(feedInfo);
            fos.flush();
            fos.close();
        } catch (final Exception e) {
            // Error writing to cache is fatal
            throw new RuntimeException("Attempting to write to cache", e);
        }
    }

    public static String replaceNonAlphanumeric(final String str, final char subst) {
        final StringBuffer ret = new StringBuffer(str.length());
        final char[] testChars = str.toCharArray();
        for (final char testChar : testChars) {
            if (Character.isLetterOrDigit(testChar)) {
                ret.append(testChar);
            } else {
                ret.append(subst);
            }
        }
        return ret.toString();
    }

    /**
     * Clear the cache.
     */
    @Override
    public synchronized void clear() {
        final File file = new File(cachePath);
        // only do the delete if the directory exists
        if (file.exists() && file.canWrite()) {
            // make the directory empty
            final String[] files = file.list();
            final int len = files.length;
            for (int i = 0; i < len; i++) {
                final File deleteMe = new File(cachePath + File.separator + files[i]);
                deleteMe.delete();
            }

            // don't delete the cache directory
        }
    }

    @Override
    public SyndFeedInfo remove(final URL url) {
        SyndFeedInfo info = null;
        final String fileName = cachePath + File.separator + "feed_" + replaceNonAlphanumeric(url.toString(), '_').trim();
        FileInputStream fis;
        try {
            fis = new FileInputStream(fileName);
            final ObjectInputStream ois = new ObjectInputStream(fis);
            info = (SyndFeedInfo) ois.readObject();
            fis.close();

            final File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
        } catch (final FileNotFoundException fnfe) {
            // That's OK, we'l return null
        } catch (final ClassNotFoundException cnfe) {
            // Error writing to cahce is fatal
            throw new RuntimeException("Attempting to read from cache", cnfe);
        } catch (final IOException fnfe) {
            // Error writing to cahce is fatal
            throw new RuntimeException("Attempting to read from cache", fnfe);
        }
        return info;
    }
}
