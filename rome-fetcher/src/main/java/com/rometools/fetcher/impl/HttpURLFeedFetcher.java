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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import com.rometools.fetcher.FetcherEvent;
import com.rometools.fetcher.FetcherException;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.rometools.utils.IO;

/**
 * <p>
 * Class to retrieve syndication files via HTTP.
 * </p>
 *
 * <p>
 * If passed a {@link com.rometools.fetcher.impl.FeedFetcherCache} in the constructor it will use
 * conditional gets to only retrieve modified content.
 * </p>
 *
 * <p>
 * The class uses the Accept-Encoding: gzip header to retrieve gzipped feeds where supported by the
 * server.
 * </p>
 *
 * <p>
 * Simple usage:
 *
 * <pre>
 * // create the cache
 * FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.getFeedInfoCache();
 * // retrieve the feed the first time
 * // any subsequent request will use conditional gets and only
 * // retrieve the resource if it has changed
 * SyndFeed feed = new HttpURLFeedFetcher(feedInfoCache).retrieveFeed(feedUrl);
 * </pre>
 *
 * </p>
 *
 * @see <a
 *      href="http://fishbowl.pastiche.org/2002/10/21/http_conditional_get_for_rss_hackers">http://fishbowl.pastiche.org/2002/10/21/http_conditional_get_for_rss_hackers</a>
 * @see <a
 *      href="http://diveintomark.org/archives/2003/07/21/atom_aggregator_behavior_http_level">http://diveintomark.org/archives/2003/07/21/atom_aggregator_behavior_http_level</a>
 * @see <a
 *      href="http://bobwyman.pubsub.com/main/2004/09/using_rfc3229_w.html">http://bobwyman.pubsub.com/main/2004/09/using_rfc3229_w.html</a>
 * @author Nick Lothian
 * 
 * @deprecated ROME Fetcher will be dropped in the next major version of ROME (version 2). For more information and some migration hints, 
 * please have a look at our <a href="https://github.com/rometools/rome/issues/276">detailed explanation</a>.
 */
@Deprecated
public class HttpURLFeedFetcher extends AbstractFeedFetcher {

    private volatile int connectTimeout = -1;

    static final int POLL_EVENT = 1;
    static final int RETRIEVE_EVENT = 2;
    static final int UNCHANGED_EVENT = 3;

    private FeedFetcherCache feedInfoCache;

    /**
     * Constructor to use HttpURLFeedFetcher without caching of feeds
     *
     */
    public HttpURLFeedFetcher() {
        this(null);
    }

    /**
     * Constructor to enable HttpURLFeedFetcher to cache feeds
     *
     * @param feedInfoCache - an instance of the FeedFetcherCache interface
     */
    public HttpURLFeedFetcher(final FeedFetcherCache feedInfoCache) {
        setFeedInfoCache(feedInfoCache);
    }

    @Override
    public SyndFeed retrieveFeed(final URL feedUrl) throws IllegalArgumentException, IOException, FeedException, FetcherException {
        return this.retrieveFeed(getUserAgent(), feedUrl);
    }

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
    @Override
    public SyndFeed retrieveFeed(final String userAgent, final URL feedUrl) throws IllegalArgumentException, IOException, FeedException, FetcherException {
        if (feedUrl == null) {
            throw new IllegalArgumentException("null is not a valid URL");
        }

        final URLConnection connection = feedUrl.openConnection();
        if (!(connection instanceof HttpURLConnection)) {
            throw new IllegalArgumentException(feedUrl.toExternalForm() + " is not a valid HTTP Url");
        }
        final HttpURLConnection httpConnection = (HttpURLConnection) connection;
        if (connectTimeout >= 0) {
            httpConnection.setConnectTimeout(connectTimeout);
        }
        // httpConnection.setInstanceFollowRedirects(true); // this is true by default, but can be
        // changed on a claswide basis

        final FeedFetcherCache cache = getFeedInfoCache();
        if (cache != null) {
            SyndFeedInfo syndFeedInfo = cache.getFeedInfo(feedUrl);
            setRequestHeaders(connection, syndFeedInfo, userAgent);
            httpConnection.connect();
            try {
                fireEvent(FetcherEvent.EVENT_TYPE_FEED_POLLED, connection);

                if (syndFeedInfo == null) {
                    // this is a feed that hasn't been retrieved
                    syndFeedInfo = new SyndFeedInfo();
                    retrieveAndCacheFeed(feedUrl, syndFeedInfo, httpConnection);
                } else {
                    // check the response code
                    final int responseCode = httpConnection.getResponseCode();
                    if (responseCode != HttpURLConnection.HTTP_NOT_MODIFIED) {
                        // the response code is not 304 NOT MODIFIED
                        // This is either because the feed server
                        // does not support condition gets
                        // or because the feed hasn't changed
                        retrieveAndCacheFeed(feedUrl, syndFeedInfo, httpConnection);
                    } else {
                        // the feed does not need retrieving
                        fireEvent(FetcherEvent.EVENT_TYPE_FEED_UNCHANGED, connection);
                    }
                }

                return syndFeedInfo.getSyndFeed();
            } finally {
                httpConnection.disconnect();
            }
        } else {
            fireEvent(FetcherEvent.EVENT_TYPE_FEED_POLLED, connection);
            InputStream inputStream = null;
            setRequestHeaders(connection, null, userAgent);

            httpConnection.connect();
            try {
                inputStream = httpConnection.getInputStream();
                return getSyndFeedFromStream(inputStream, connection);
            } catch (final java.io.IOException e) {
                handleErrorCodes(((HttpURLConnection) connection).getResponseCode());
            } finally {
                IO.close(inputStream);
                httpConnection.disconnect();
            }
            // we will never actually get to this line
            return null;
        }
    }

    protected void retrieveAndCacheFeed(final URL feedUrl, final SyndFeedInfo syndFeedInfo, final HttpURLConnection connection)
            throws IllegalArgumentException, FeedException, FetcherException, IOException {
        handleErrorCodes(connection.getResponseCode());

        resetFeedInfo(feedUrl, syndFeedInfo, connection);
        final FeedFetcherCache cache = getFeedInfoCache();
        // resetting feed info in the cache
        // could be needed for some implementations
        // of FeedFetcherCache (eg, distributed HashTables)
        if (cache != null) {
            cache.setFeedInfo(feedUrl, syndFeedInfo);
        }
    }

    protected void resetFeedInfo(final URL orignalUrl, final SyndFeedInfo syndFeedInfo, final HttpURLConnection connection) throws IllegalArgumentException,
            IOException, FeedException {
        // need to always set the URL because this may have changed due to 3xx redirects
        syndFeedInfo.setUrl(connection.getURL());

        // the ID is a persistant value that should stay the same even if the URL for the
        // feed changes (eg, by 3xx redirects)
        syndFeedInfo.setId(orignalUrl.toString());

        // This will be 0 if the server doesn't support or isn't setting the last modified header
        syndFeedInfo.setLastModified(connection.getLastModified());

        // This will be null if the server doesn't support or isn't setting the ETag header
        syndFeedInfo.setETag(connection.getHeaderField("ETag"));

        // get the contents
        InputStream inputStream = null;
        try {
            inputStream = connection.getInputStream();
            SyndFeed syndFeed = getSyndFeedFromStream(inputStream, connection);

            final String imHeader = connection.getHeaderField("IM");
            if (isUsingDeltaEncoding() && imHeader != null && imHeader.contains("feed")) {
                final FeedFetcherCache cache = getFeedInfoCache();
                if (cache != null && connection.getResponseCode() == 226) {
                    // client is setup to use http delta encoding and the server supports it and has
                    // returned a delta encoded response
                    // This response only includes new items
                    final SyndFeedInfo cachedInfo = cache.getFeedInfo(orignalUrl);
                    if (cachedInfo != null) {
                        final SyndFeed cachedFeed = cachedInfo.getSyndFeed();

                        // set the new feed to be the orginal feed plus the new items
                        syndFeed = combineFeeds(cachedFeed, syndFeed);
                    }
                }
            }

            syndFeedInfo.setSyndFeed(syndFeed);
        } finally {
            IO.close(inputStream);
        }
    }

    /**
     * <p>
     * Set appropriate HTTP headers, including conditional get and gzip encoding headers
     * </p>
     *
     * @param connection A URLConnection
     * @param syndFeedInfo The SyndFeedInfo for the feed to be retrieved. May be null
     * @param userAgent the name of the user-agent to be placed in HTTP-header.
     */
    protected void setRequestHeaders(final URLConnection connection, final SyndFeedInfo syndFeedInfo, final String userAgent) {
        if (syndFeedInfo != null) {
            // set the headers to get feed only if modified
            // we support the use of both last modified and eTag headers
            if (syndFeedInfo.getLastModified() != null) {
                final Object lastModified = syndFeedInfo.getLastModified();
                if (lastModified instanceof Long) {
                    connection.setIfModifiedSince((Long) syndFeedInfo.getLastModified());
                }
            }
            if (syndFeedInfo.getETag() != null) {
                connection.setRequestProperty("If-None-Match", syndFeedInfo.getETag());
            }

        }
        // header to retrieve feed gzipped
        connection.setRequestProperty("Accept-Encoding", "gzip");
        connection.addRequestProperty("User-Agent", userAgent);

        if (isUsingDeltaEncoding()) {
            connection.addRequestProperty("A-IM", "feed");
        }
    }

    private SyndFeed readSyndFeedFromStream(final InputStream inputStream, final URLConnection connection) throws IOException, IllegalArgumentException,
            FeedException {
        BufferedInputStream is;
        if ("gzip".equalsIgnoreCase(connection.getContentEncoding())) {
            // handle gzip encoded content
            is = new BufferedInputStream(new GZIPInputStream(inputStream));
        } else {
            is = new BufferedInputStream(inputStream);
        }

        final XmlReader reader;
        if (connection.getHeaderField("Content-Type") != null) {
            reader = new XmlReader(is, connection.getHeaderField("Content-Type"), true);
        } else {
            reader = new XmlReader(is, true);
        }

        final SyndFeedInput syndFeedInput = new SyndFeedInput();
        syndFeedInput.setPreserveWireFeed(isPreserveWireFeed());
        syndFeedInput.setAllowDoctypes(isAllowDoctypes());

        return syndFeedInput.build(reader);

    }

    private SyndFeed getSyndFeedFromStream(final InputStream inputStream, final URLConnection connection) throws IOException, IllegalArgumentException,
            FeedException {
        final SyndFeed feed = readSyndFeedFromStream(inputStream, connection);
        fireEvent(FetcherEvent.EVENT_TYPE_FEED_RETRIEVED, connection, feed);
        return feed;
    }

    /**
     * @return The FeedFetcherCache used by this fetcher (Could be null)
     */
    public synchronized FeedFetcherCache getFeedInfoCache() {
        return feedInfoCache;
    }

    /**
     * @param cache The cache to be used by this fetcher (pass null to stop using a cache)
     */
    public synchronized void setFeedInfoCache(final FeedFetcherCache cache) {
        feedInfoCache = cache;
    }

    /**
     * @param timeout see java.net.URLConnection.setConnectTimeout(int timeout)
     */
    public synchronized void setConnectTimeout(final int timeout) {
        connectTimeout = timeout;
    }
}
