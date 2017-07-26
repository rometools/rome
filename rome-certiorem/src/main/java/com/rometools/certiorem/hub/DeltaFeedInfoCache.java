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

import java.net.URL;

import com.rometools.fetcher.impl.FeedFetcherCache;
import com.rometools.fetcher.impl.SyndFeedInfo;

/**
 * Wrapper FeedFetcherCache that wraps a backing FeedFetcherCache and makes sure that any
 * SyndFeedInfo used within it are replaced with a DeltaSyndFeedInfo which is capable of tracking
 * changes to entries in the underlying feed.
 *
 * @author najmi
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public class DeltaFeedInfoCache implements FeedFetcherCache {

    FeedFetcherCache backingCache;

    public DeltaFeedInfoCache(final FeedFetcherCache backingCache) {
        this.backingCache = backingCache;
    }

    @Override
    public SyndFeedInfo getFeedInfo(final URL feedUrl) {
        return backingCache.getFeedInfo(feedUrl);
    }

    @Override
    public void setFeedInfo(final URL feedUrl, SyndFeedInfo syndFeedInfo) {
        // Make sure that syndFeedInfo is an instance of DeltaSyndFeedInfo
        if (!(syndFeedInfo instanceof DeltaSyndFeedInfo)) {
            syndFeedInfo = new DeltaSyndFeedInfo(syndFeedInfo);
        }
        backingCache.setFeedInfo(feedUrl, syndFeedInfo);
    }

    @Override
    public void clear() {
        backingCache.clear();
    }

    @Override
    public SyndFeedInfo remove(final URL feedUrl) {
        return backingCache.remove(feedUrl);
    }

}
