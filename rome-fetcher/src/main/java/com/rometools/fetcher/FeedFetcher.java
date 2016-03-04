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
package com.rometools.fetcher;

import java.io.IOException;
import java.net.URL;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;

/**
 * @deprecated ROME Fetcher will be dropped in the next major version of ROME (version 2). For more information and some migration hints, 
 * please have a look at our <a href="https://github.com/rometools/rome/issues/276">detailed explanation</a>.
 */
@Deprecated
public interface FeedFetcher {
    /**
     * <p>
     * The default user agent. It is not marked final so buggy java compiler will not write this
     * string into all classes that reference it.
     * </p>
     *
     * <p>
     * http://tinyurl.com/64t5n points to https://rome.dev.java.net Some servers ban user agents
     * with "Java" in the name.
     * </p>
     *
     */
    public static String DEFAULT_USER_AGENT = "Rome Client (http://tinyurl.com/64t5n)";

    /**
     * @param string The User-Agent to sent to servers
     */
    public abstract void setUserAgent(String string);

    /**
     * @return the User-Agent currently being sent to servers
     */
    public abstract String getUserAgent();

    /**
     * <p>
     * Turn on or off rfc3229 delta encoding
     * </p>
     *
     * <p>
     * See http://www.ietf.org/rfc/rfc3229.txt and
     * http://bobwyman.pubsub.com/main/2004/09/using_rfc3229_w.html
     * </p>
     *
     * <p>
     * NOTE: This is experimental and feedback is welcome!
     * </p>
     *
     * @param useDeltaEncoding
     */
    public abstract void setUsingDeltaEncoding(boolean useDeltaEncoding);

    /**
     * <p>
     * Is this fetcher using rfc3229 delta encoding?
     * </p>
     *
     * @return
     */
    public abstract boolean isUsingDeltaEncoding();

    /**
     * <p>
     * Add a FetcherListener.
     * </p>
     *
     * <p>
     * The FetcherListener will receive an FetcherEvent when a Fetcher event (feed polled,
     * retrieved, etc) occurs
     * </p>
     *
     * @param listener The FetcherListener to recieve the event
     */
    public abstract void addFetcherEventListener(FetcherListener listener);

    /**
     * <p>
     * Remove a FetcherListener
     * </p>
     *
     * @param listener The FetcherListener to remove
     */
    public abstract void removeFetcherEventListener(FetcherListener listener);

    /**
     * Retrieve a feed over HTTP
     *
     * @param feedUrl A non-null URL of a RSS/Atom feed to retrieve
     * @return A {@link com.rometools.rome.feed.synd.SyndFeed} object
     * @throws IllegalArgumentException if the URL is null;
     * @throws IOException if a TCP error occurs
     * @throws FeedException if the feed is not valid
     * @throws FetcherException if a HTTP error occurred
     */
    public abstract SyndFeed retrieveFeed(URL feedUrl) throws IllegalArgumentException, IOException, FeedException, FetcherException;

    public SyndFeed retrieveFeed(String userAgent, URL url) throws IllegalArgumentException, IOException, FeedException, FetcherException;

    /**
     * If set to true, the WireFeed will be made accessible from the SyndFeed object returned from
     * the Fetcher via the originalWireFeed() method. Each Entry in the feed will have the
     * corresponding wireEntry property set.
     */
    void setPreserveWireFeed(boolean preserveWireFeed);
    
    /**
     * In ROME 1.5.1 we fixed a security vulnerability by disallowing Doctype declarations by default. 
     * This change breaks the compatibility with at least RSS 0.91N because it requires a Doctype declaration. 
     * You are able to allow Doctype declarations again with this property. You should only activate it 
     * when the feeds that you process are absolutely trustful. 
     *  
     * @param allowDoctypes true when Doctype declarations should be allowed again, false otherwise
     */
    void setAllowDoctypes(boolean allowDoctypes);

}
