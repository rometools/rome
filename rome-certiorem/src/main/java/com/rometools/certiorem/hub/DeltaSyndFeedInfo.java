/*
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
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rometools.certiorem.hub;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rometools.fetcher.impl.SyndFeedInfo;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

/**
 * Extends SyndFeedInfo to also track etags for individual entries. This may be used with
 * DeltaFeedInfoCache to only return feed with a subset of entries that have changed since last
 * fetch.
 *
 * @author najmi
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public class DeltaSyndFeedInfo extends SyndFeedInfo {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Map<String, String> entryTagsMap = new HashMap<String, String>();
    Map<String, Boolean> changedMap = new HashMap<String, Boolean>();

    public DeltaSyndFeedInfo(final SyndFeedInfo backingFeedInfo) {
        setETag(backingFeedInfo.getETag());
        setId(backingFeedInfo.getId());
        setLastModified(backingFeedInfo.getLastModified());
        setSyndFeed(backingFeedInfo.getSyndFeed());
    }

    /**
     * Gets a filtered version of the SyndFeed that only has entries that were changed in the last
     * setSyndFeed() call.
     *
     * @return
     */
    @Override
    public synchronized SyndFeed getSyndFeed() {
        try {
            final SyndFeed feed = (SyndFeed) super.getSyndFeed().clone();

            final List<SyndEntry> changedEntries = new ArrayList<SyndEntry>();

            final List<SyndEntry> entries = feed.getEntries();
            for (final SyndEntry entry : entries) {
                if (changedMap.containsKey(entry.getUri())) {
                    changedEntries.add(entry);
                }
            }

            feed.setEntries(changedEntries);

            return feed;
        } catch (final CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Overrides super class method to update changedMap and entryTagsMap for tracking changed
     * entries.
     *
     * @param feed
     */
    @Override
    public final synchronized void setSyndFeed(final SyndFeed feed) {
        super.setSyndFeed(feed);

        changedMap.clear();
        final List<SyndEntry> entries = feed.getEntries();
        for (final SyndEntry entry : entries) {
            final String currentEntryTag = computeEntryTag(entry);
            final String previousEntryTag = entryTagsMap.get(entry.getUri());

            if (previousEntryTag == null || !currentEntryTag.equals(previousEntryTag)) {
                // Entry has changed
                changedMap.put(entry.getUri(), Boolean.TRUE);
            }
            entryTagsMap.put(entry.getUri(), currentEntryTag);
        }
    }

    private String computeEntryTag(final SyndEntry entry) {

        // Following hash algorithm suggested by Robert Cooper needs to be
        // evaluated in future.
        // int hash = ( entry.getUri() != null ? entry.getUri().hashCode() :
        // entry.getLink().hashCode() ) ^
        // (entry.getUpdatedDate() != null ? entry.getUpdatedDate().hashCode() :
        // entry.getPublishedDate().hashCode()) ^
        // entry.getTitle().hashCode() ^
        // entry.getDescription().hashCode();

        final String id = entry.getUri();
        Date updateDate = entry.getUpdatedDate();
        final Date publishedDate = entry.getPublishedDate();
        if (updateDate == null) {
            if (publishedDate != null) {
                updateDate = publishedDate;
            } else {
                // For misbehaving feeds that do not set updateDate or
                // publishedDate we use current tiem which pretty mucg assures
                // that it will be viewed as
                // changed even when it is not
                updateDate = new Date();
            }
        }
        final String key = id + ":" + entry.getUpdatedDate();
        return computeDigest(key);
    }

    private String computeDigest(final String content) {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA");
            final byte[] digest = md.digest(content.getBytes());
            final BigInteger bi = new BigInteger(digest);
            return bi.toString(16);
        } catch (final Exception e) {
            return "";
        }
    }
}
