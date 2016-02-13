/*
 * Copyright 2004 Sun Microsystems, Inc.
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
 */
package com.rometools.fetcher.impl;

import com.rometools.fetcher.AbstractJettyTest;
import com.rometools.fetcher.FeedFetcher;
import com.rometools.fetcher.TestBasicAuthenticator;
import com.rometools.fetcher.impl.FeedFetcherCache;
import com.rometools.fetcher.impl.HttpURLFeedFetcher;

public class HttpURLFeedFetcherTest extends AbstractJettyTest {

    public HttpURLFeedFetcherTest(final String s) {
        super(s);
    }

    /**
     * @see com.rometools.rome.fetcher.impl.AbstractJettyTest#getFeedFetcher()
     */
    @Override
    protected FeedFetcher getFeedFetcher() {
        return new HttpURLFeedFetcher();
    }

    @Override
    protected FeedFetcher getFeedFetcher(final FeedFetcherCache cache) {
        return new HttpURLFeedFetcher(cache);
    }

    /**
     * @see com.rometools.rome.fetcher.impl.AbstractJettyTest#getAuthenticatedFeedFetcher()
     */
    @Override
    public FeedFetcher getAuthenticatedFeedFetcher() {
        // setup the authenticator
        java.net.Authenticator.setDefault(new TestBasicAuthenticator());

        final FeedFetcher feedFetcher = getFeedFetcher();

        return feedFetcher;
    }

}
