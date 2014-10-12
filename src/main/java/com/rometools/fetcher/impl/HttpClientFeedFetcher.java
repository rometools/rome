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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import com.rometools.fetcher.FetcherEvent;
import com.rometools.fetcher.FetcherException;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

/**
 * @author Nick Lothian
 */
public class HttpClientFeedFetcher extends AbstractFeedFetcher {

    private CredentialSupplier credentialSupplier;
    private FeedFetcherCache feedInfoCache;
    private volatile HttpClientMethodCallbackIntf httpClientMethodCallback;
    private volatile HttpClientParams httpClientParams;
    private Map<String,String> customRequestHeaders;

    public HttpClientFeedFetcher() {
        setHttpClientParams(new HttpClientParams());
    }

    /**
     * @param cache
     */
    public HttpClientFeedFetcher(final FeedFetcherCache cache) {
        this();
        setFeedInfoCache(cache);
    }

    public HttpClientFeedFetcher(final FeedFetcherCache cache, final CredentialSupplier credentialSupplier) {
        this(cache);
        setCredentialSupplier(credentialSupplier);
    }

    /**
     * @param timeout Sets the connect timeout for the HttpClient but using the URLConnection method
     *            name. Uses the HttpClientParams method setConnectionManagerTimeout instead of
     *            setConnectTimeout
     *
     */
    public synchronized void setConnectTimeout(final int timeout) {
        httpClientParams.setConnectionManagerTimeout(timeout);
    }

    /**
     * @return The currently used connect timeout for the HttpClient but using the URLConnection
     *         method name. Uses the HttpClientParams method getConnectionManagerTimeout instead of
     *         getConnectTimeout
     *
     */
    public int getConnectTimeout() {
        return (int) getHttpClientParams().getConnectionManagerTimeout();
    }

    /**
     * @param credentialSupplier The credentialSupplier to set.
     */
    public synchronized void setCredentialSupplier(final CredentialSupplier credentialSupplier) {
        this.credentialSupplier = credentialSupplier;
    }

    /**
     * @return Returns the credentialSupplier.
     */
    public synchronized CredentialSupplier getCredentialSupplier() {
        return credentialSupplier;
    }

    /**
     * @param feedInfoCache the feedInfoCache to set
     */
    public synchronized void setFeedInfoCache(final FeedFetcherCache feedInfoCache) {
        this.feedInfoCache = feedInfoCache;
    }

    /**
     * @return the feedInfoCache.
     */
    public synchronized FeedFetcherCache getFeedInfoCache() {
        return feedInfoCache;
    }

    public synchronized void setHttpClientMethodCallback(final HttpClientMethodCallbackIntf httpClientMethodCallback) {
        this.httpClientMethodCallback = httpClientMethodCallback;
    }

    public HttpClientMethodCallbackIntf getHttpClientMethodCallback() {
        return httpClientMethodCallback;
    }

    /**
     * @param httpClientParams The httpClientParams to set.
     */
    public synchronized void setHttpClientParams(final HttpClientParams httpClientParams) {
        this.httpClientParams = httpClientParams;
    }

    /**
     * @return Returns the httpClientParams.
     */
    public synchronized HttpClientParams getHttpClientParams() {
        return httpClientParams;
    }

    /**
     * @return The currently used read timeout for the URLConnection, 0 is unlimited, i.e. no
     *         timeout
     */
    public synchronized void setReadTimeout(final int timeout) {
        httpClientParams.setSoTimeout(timeout);
    }

    /**
     * @return timeout the read timeout for the URLConnection to a specified timeout, in
     *         milliseconds.
     */
    public int getReadTimeout() {
        return getHttpClientParams().getSoTimeout();
    }
    
    /**
     * Apply any request headers to the HTTP method call.
     * @param customRequestHeaders
     */
    public synchronized void setCustomRequestHeaders(final Map<String,String> customRequestHeaders) {
        this.customRequestHeaders = customRequestHeaders;
    }

    @Override
    public SyndFeed retrieveFeed(final URL url) throws IllegalArgumentException, IOException, FeedException, FetcherException {
        return this.retrieveFeed(getUserAgent(), url);
    }

    /**
     * @see com.rometools.fetcher.FeedFetcher#retrieveFeed(java.net.URL)
     */
    @Override
    public SyndFeed retrieveFeed(final String userAgent, final URL feedUrl) throws IllegalArgumentException, IOException, FeedException, FetcherException {

        if (feedUrl == null) {
            throw new IllegalArgumentException("null is not a valid URL");
        }

        final HttpClient client = new HttpClient(httpClientParams);

        if (getCredentialSupplier() != null) {

            client.getParams().setAuthenticationPreemptive(true);

            final String host = feedUrl.getHost();
            final Credentials credentials = getCredentialSupplier().getCredentials(null, host);
            if (credentials != null) {
                final AuthScope authScope = new AuthScope(host, -1);
                client.getState().setCredentials(authScope, credentials);
            }

        }

        System.setProperty("httpclient.useragent", userAgent);

        final String urlStr = feedUrl.toString();

        final HttpMethod method = new GetMethod(urlStr);
        if (customRequestHeaders == null) {
        	method.addRequestHeader("Accept-Encoding", "gzip");
            method.addRequestHeader("User-Agent", userAgent);
        	
        } else {
        	for (final Map.Entry<String,String> entry : customRequestHeaders.entrySet()) {
        		method.addRequestHeader(entry.getKey(), entry.getValue());
        	}
        	if (!customRequestHeaders.containsKey("Accept-Encoding")) method.addRequestHeader("Accept-Encoding", "gzip");
        	if (!customRequestHeaders.containsKey("User-Agent")) method.addRequestHeader("User-Agent", userAgent);
        }
        
        method.setFollowRedirects(true);

        if (httpClientMethodCallback != null) {
            synchronized (httpClientMethodCallback) {
                httpClientMethodCallback.afterHttpClientMethodCreate(method);
            }
        }

        final FeedFetcherCache cache = getFeedInfoCache();

        if (cache != null) {
            // retrieve feed
            try {
                if (isUsingDeltaEncoding()) {
                    method.setRequestHeader("A-IM", "feed");
                }

                // get the feed info from the cache
                // Note that syndFeedInfo will be null if it is not in the cache
                SyndFeedInfo syndFeedInfo = cache.getFeedInfo(feedUrl);

                if (syndFeedInfo != null) {
                    method.setRequestHeader("If-None-Match", syndFeedInfo.getETag());

                    if (syndFeedInfo.getLastModified() instanceof String) {
                        method.setRequestHeader("If-Modified-Since", (String) syndFeedInfo.getLastModified());
                    }
                }

                final int statusCode = client.executeMethod(method);
                fireEvent(FetcherEvent.EVENT_TYPE_FEED_POLLED, urlStr);
                handleErrorCodes(statusCode);

                SyndFeed feed = getFeed(syndFeedInfo, urlStr, method, statusCode);

                syndFeedInfo = buildSyndFeedInfo(feedUrl, urlStr, method, feed, statusCode);

                cache.setFeedInfo(new URL(urlStr), syndFeedInfo);

                // the feed may have been modified to pick up cached values
                // (eg - for delta encoding)
                feed = syndFeedInfo.getSyndFeed();

                return feed;
            } finally {
                method.releaseConnection();
            }
        } else {
            // cache is not in use
            try {
                final int statusCode = client.executeMethod(method);
                fireEvent(FetcherEvent.EVENT_TYPE_FEED_POLLED, urlStr);
                handleErrorCodes(statusCode);

                return getFeed(null, urlStr, method, statusCode);
            } finally {
                method.releaseConnection();
            }
        }
    }

    private SyndFeed getFeed(final SyndFeedInfo syndFeedInfo, final String urlStr, final HttpMethod method, final int statusCode) throws IOException,
            HttpException, FetcherException, FeedException {

        if (statusCode == HttpURLConnection.HTTP_NOT_MODIFIED && syndFeedInfo != null) {
            fireEvent(FetcherEvent.EVENT_TYPE_FEED_UNCHANGED, urlStr);
            return syndFeedInfo.getSyndFeed();
        }

        final SyndFeed feed = retrieveFeed(urlStr, method);
        fireEvent(FetcherEvent.EVENT_TYPE_FEED_RETRIEVED, urlStr, feed);

        return feed;
    }

    /**
     * @param feedUrl
     * @param urlStr
     * @param method
     * @param feed
     * @return
     * @throws MalformedURLException
     */
    private SyndFeedInfo buildSyndFeedInfo(final URL feedUrl, final String urlStr, final HttpMethod method, SyndFeed feed, final int statusCode)
            throws MalformedURLException {

        SyndFeedInfo syndFeedInfo;
        syndFeedInfo = new SyndFeedInfo();

        // this may be different to feedURL because of 3XX redirects
        syndFeedInfo.setUrl(new URL(urlStr));
        syndFeedInfo.setId(feedUrl.toString());

        final Header imHeader = method.getResponseHeader("IM");

        if (imHeader != null && imHeader.getValue().indexOf("feed") >= 0 && isUsingDeltaEncoding()) {
            final FeedFetcherCache cache = getFeedInfoCache();

            if (cache != null && statusCode == 226) {
                // client is setup to use http delta encoding and the server supports it and has
                // returned a delta encoded response
                // This response only includes new items
                final SyndFeedInfo cachedInfo = cache.getFeedInfo(feedUrl);

                if (cachedInfo != null) {
                    final SyndFeed cachedFeed = cachedInfo.getSyndFeed();

                    // set the new feed to be the orginal feed plus the new items
                    feed = combineFeeds(cachedFeed, feed);
                }
            }
        }

        final Header lastModifiedHeader = method.getResponseHeader("Last-Modified");

        if (lastModifiedHeader != null) {
            syndFeedInfo.setLastModified(lastModifiedHeader.getValue());
        }

        final Header eTagHeader = method.getResponseHeader("ETag");

        if (eTagHeader != null) {
            syndFeedInfo.setETag(eTagHeader.getValue());
        }

        syndFeedInfo.setSyndFeed(feed);

        return syndFeedInfo;
    }

    /**
     * @param client
     * @param urlStr
     * @param method
     * @return
     * @throws IOException
     * @throws HttpException
     * @throws FetcherException
     * @throws FeedException
     */
    private SyndFeed retrieveFeed(final String urlStr, final HttpMethod method) throws IOException, HttpException, FetcherException, FeedException {

        InputStream stream = null;

        if (method.getResponseHeader("Content-Encoding") != null && "gzip".equalsIgnoreCase(method.getResponseHeader("Content-Encoding").getValue())) {
            stream = new GZIPInputStream(method.getResponseBodyAsStream());
        } else {
            stream = method.getResponseBodyAsStream();
        }

        try {
            XmlReader reader = null;

            if (method.getResponseHeader("Content-Type") != null) {
                reader = new XmlReader(stream, method.getResponseHeader("Content-Type").getValue(), true);
            } else {
                reader = new XmlReader(stream, true);
            }

            final SyndFeedInput syndFeedInput = new SyndFeedInput();
            syndFeedInput.setPreserveWireFeed(isPreserveWireFeed());

            return syndFeedInput.build(reader);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    public interface CredentialSupplier {
        public Credentials getCredentials(String realm, String host);
    }

    public interface HttpClientMethodCallbackIntf {
        /**
         * Allows access to the underlying HttpClient HttpMethod object. Note that in most cases,
         * method.setRequestHeader(String, String) is what you want to do (rather than
         * method.addRequestHeader(String, String))
         *
         * @param method
         */
        public void afterHttpClientMethodCreate(HttpMethod method);
    }

}
