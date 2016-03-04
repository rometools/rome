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
package com.rometools.fetcher.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import com.rometools.utils.IO;

/**
 * Disk based feed cache.
 * 
 * @deprecated ROME Fetcher will be dropped in the next major version of ROME (version 2). For more information and some migration hints, 
 * please have a look at our <a href="https://github.com/rometools/rome/issues/276">detailed explanation</a>.
 */
@Deprecated
public class DiskFeedInfoCache implements FeedFetcherCache {

    protected String cachePath = null;

    public DiskFeedInfoCache(final String cachePath) {
        this.cachePath = cachePath;
    }

    @Override
    public SyndFeedInfo getFeedInfo(final URL url) {
        final String fileName = generateFilename(url);
        return getFeedInfo(fileName);
    }

    @Override
    public void setFeedInfo(final URL url, final SyndFeedInfo feedInfo) {

        final String fileName = generateFilename(url);

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {

            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(feedInfo);
            fos.flush();

        } catch (final FileNotFoundException e) {

            throw new RuntimeException("Error while writing to cache", e);

        } catch (final IOException e) {

            throw new RuntimeException("Error while writing to cache", e);

        } finally {

            IO.closeQuietly(fos);
            IO.closeQuietly(oos);

        }

    }

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
        final String fileName = generateFilename(url);
        final SyndFeedInfo info = getFeedInfo(fileName);
        if (info != null) {
            final File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
        }
        return info;
    }

    private SyndFeedInfo getFeedInfo(final String fileName) {

        SyndFeedInfo info = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {

            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            info = (SyndFeedInfo) ois.readObject();

        } catch (final FileNotFoundException e) {

            // feed is not cached yet

        } catch (final ClassNotFoundException e) {

            throw new RuntimeException("Unable to read from cache", e);

        } catch (final IOException e) {

            throw new RuntimeException("Unable to read from cache", e);

        } finally {

            IO.closeQuietly(fis);
            IO.closeQuietly(ois);

        }

        return info;

    }

    private static String replaceNonAlphanumeric(final String string, final char character) {
        final StringBuffer buffer = new StringBuffer(string.length());
        for (final char singleChar : string.toCharArray()) {
            if (Character.isLetterOrDigit(singleChar)) {
                buffer.append(singleChar);
            } else {
                buffer.append(character);
            }
        }
        return buffer.toString();
    }

    private String generateFilename(final URL url) {
        return cachePath + File.separator + "feed_" + replaceNonAlphanumeric(url.toString(), '_').trim();
    }

}
