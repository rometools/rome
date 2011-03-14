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
package org.rometools.test;

import org.rometools.fetcher.impl.HttpClientFeedFetcher;
import org.rometools.fetcher.impl.FeedFetcherCache;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;

import org.rometools.fetcher.FeedFetcher;

/**
 * @author Nick Lothian
 */
public class HttpClientFeedFetcherTest extends AbstractJettyTest {

	public HttpClientFeedFetcherTest(String s) {
		super(s);
	}
	
	/**
	 * @see com.sun.syndication.fetcher.impl.AbstractJettyTest#getFeedFetcher()
	 */
	protected FeedFetcher getFeedFetcher() {		
		return new HttpClientFeedFetcher();
	}
	
	protected FeedFetcher getFeedFetcher(FeedFetcherCache cache) {
		return new HttpClientFeedFetcher(cache);
	}

    /**
     * @see com.sun.syndication.fetcher.impl.AbstractJettyTest#getAuthenticatedFeedFetcher()
     */
    public FeedFetcher getAuthenticatedFeedFetcher() {
        return new HttpClientFeedFetcher(null, new HttpClientFeedFetcher.CredentialSupplier() {
            public Credentials getCredentials(String realm, String host) {
                if ("localhost".equals(host)) {
                	return new UsernamePasswordCredentials("username", "password");
                } else {
                    return null;
                }                
            }            
        }); 
    }
}
