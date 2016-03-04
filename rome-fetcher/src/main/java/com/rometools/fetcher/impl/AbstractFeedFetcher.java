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

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.fetcher.FeedFetcher;
import com.rometools.fetcher.FetcherEvent;
import com.rometools.fetcher.FetcherException;
import com.rometools.fetcher.FetcherListener;
import com.rometools.rome.feed.synd.SyndFeed;

/**
 * @deprecated ROME Fetcher will be dropped in the next major version of ROME (version 2). For more information and some migration hints, 
 * please have a look at our <a href="https://github.com/rometools/rome/issues/276">detailed explanation</a>.
 */
@Deprecated
public abstract class AbstractFeedFetcher implements FeedFetcher {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractFeedFetcher.class);

    private final Set<FetcherListener> listeners;
    private String userAgent;
    private boolean usingDeltaEncoding;
    private boolean preserveWireFeed;
    private boolean allowDoctypes = false;

    public AbstractFeedFetcher() {

        listeners = Collections.synchronizedSet(new HashSet<FetcherListener>());

        final Properties props = new Properties(System.getProperties());
        final String resourceName = "fetcher.properties";

        try {

            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
            if (inputStream == null) {
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
            }

            if (inputStream != null) {
                props.load(inputStream);
                System.getProperties().putAll(props);
                inputStream.close();
            } else {
                LOG.warn("Could not find {} on classpath", resourceName);
            }

        } catch (final IOException e) {
            // do nothing - we don't want to fail just because we could not find the version
            LOG.error("Error reading {} from classpath: {}", resourceName, e.getMessage());
        }

        setUserAgent(DEFAULT_USER_AGENT + " Ver: " + System.getProperty("rome.fetcher.version", "UNKNOWN"));
    }

    /**
     * @return the User-Agent currently being sent to servers
     */
    @Override
    public synchronized String getUserAgent() {
        return userAgent;
    }

    /**
     * @param string The User-Agent to sent to servers
     */
    @Override
    public synchronized void setUserAgent(final String string) {
        userAgent = string;
    }

    /**
     * @param eventType The event type to fire
     * @param connection the current connection
     */
    protected void fireEvent(final String eventType, final URLConnection connection) {
        fireEvent(eventType, connection.getURL().toExternalForm(), null);
    }

    /**
     * @param eventType The event type to fire
     * @param connection the current connection
     * @param feed The feed to pass to the event
     */
    protected void fireEvent(final String eventType, final URLConnection connection, final SyndFeed feed) {
        fireEvent(eventType, connection.getURL().toExternalForm(), feed);
    }

    /**
     * @param eventType The event type to fire
     * @param urlStr the current url as a string
     */
    protected void fireEvent(final String eventType, final String urlStr) {
        fireEvent(eventType, urlStr, null);
    }

    /**
     * @param eventType The event type to fire
     * @param urlStr the current url as a string
     * @param feed The feed to pass to the event
     */
    protected void fireEvent(final String eventType, final String urlStr, final SyndFeed feed) {
        final FetcherEvent fetcherEvent = new FetcherEvent(this, urlStr, eventType, feed);
        synchronized (listeners) {
            for (final FetcherListener fetcherEventListener : listeners) {
                fetcherEventListener.fetcherEvent(fetcherEvent);
            }
        }
    }

    @Override
    public void addFetcherEventListener(final FetcherListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeFetcherEventListener(final FetcherListener listener) {
        if (listener != null) {
            listeners.remove(listener);
        }
    }

    /**
     * @return Returns the useDeltaEncoding.
     */
    @Override
    public synchronized boolean isUsingDeltaEncoding() {
        return usingDeltaEncoding;
    }

    /**
     * @param useDeltaEncoding The useDeltaEncoding to set.
     */
    @Override
    public synchronized void setUsingDeltaEncoding(final boolean useDeltaEncoding) {
        usingDeltaEncoding = useDeltaEncoding;
    }

    /**
     * <p>
     * Handles HTTP error codes.
     * </p>
     *
     * @param responseCode the HTTP response code
     * @throws FetcherException if response code is in the range 400 to 599 inclusive
     */
    protected void handleErrorCodes(final int responseCode) throws FetcherException {
        // Handle 2xx codes as OK, so ignore them here
        // 3xx codes are handled by the HttpURLConnection class
        if (responseCode == 403) {
            // Authentication is required
            throwAuthenticationError(responseCode);
        } else if (responseCode >= 400 && responseCode < 500) {
            throw4XXError(responseCode);
        } else if (responseCode >= 500 && responseCode < 600) {
            throw new FetcherException(responseCode, "The server encounted an error. HTTP Response code was:" + responseCode);
        }
    }

    protected void throw4XXError(final int responseCode) throws FetcherException {
        throw new FetcherException(responseCode, "The requested resource could not be found. HTTP Response code was:" + responseCode);
    }

    protected void throwAuthenticationError(final int responseCode) throws FetcherException {
        throw new FetcherException(responseCode, "Authentication required for that resource. HTTP Response code was:" + responseCode);
    }

    /**
     * <p>
     * Combine the entries in two feeds into a single feed.
     * </p>
     *
     * <p>
     * The returned feed will have the same data as the newFeed parameter, with the entries from
     * originalFeed appended to the end of its entries.
     * </p>
     *
     * @param originalFeed
     * @param newFeed
     * @return
     */
    public static SyndFeed combineFeeds(final SyndFeed originalFeed, final SyndFeed newFeed) {
        try {
            final SyndFeed result = (SyndFeed) newFeed.clone();
            result.getEntries().addAll(result.getEntries().size(), originalFeed.getEntries());
            return result;
        } catch (final CloneNotSupportedException e) {
            final IllegalArgumentException iae = new IllegalArgumentException("Cannot clone feed");
            iae.initCause(e);
            throw iae;
        }
    }

    public boolean isPreserveWireFeed() {
        return preserveWireFeed;
    }

    @Override
    public void setPreserveWireFeed(final boolean preserveWireFeed) {
        this.preserveWireFeed = preserveWireFeed;
    }

    public boolean isAllowDoctypes() {
        return allowDoctypes;
    }

    @Override
    public void setAllowDoctypes(boolean allowDoctypes) {
        this.allowDoctypes = allowDoctypes;
    }
    
}
