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
package org.rometools.fetcher.impl;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import org.rometools.fetcher.FetcherEvent;
import org.rometools.fetcher.FetcherException;

import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.zip.GZIPInputStream;


/**
 * @author Nick Lothian
 */
public class HttpClientFeedFetcher extends AbstractFeedFetcher {
    private CredentialSupplier credentialSupplier;
    private FeedFetcherCache feedInfoCache;
    private volatile HttpClientMethodCallbackIntf httpClientMethodCallback;
    private volatile HttpClientParams httpClientParams;

    public HttpClientFeedFetcher() {
        super();
        setHttpClientParams(new HttpClientParams());
    }

    /**
     * @param cache
     */
    public HttpClientFeedFetcher(FeedFetcherCache cache) {
        this();
        setFeedInfoCache(cache);
    }

    public HttpClientFeedFetcher(FeedFetcherCache cache, CredentialSupplier credentialSupplier) {
        this(cache);
        setCredentialSupplier(credentialSupplier);
    }

    /**
     * @param timeout Sets the connect timeout for the HttpClient but using the URLConnection method name.
         *                   Uses the HttpClientParams method setConnectionManagerTimeout instead of setConnectTimeout
         *
         */
    public synchronized void setConnectTimeout(int timeout) {
        httpClientParams.setConnectionManagerTimeout(timeout);
    }

    /**
     * @return The currently used connect timeout for the HttpClient but using the URLConnection method name.
     *                    Uses the HttpClientParams method getConnectionManagerTimeout instead of getConnectTimeout
     *
     */
    public int getConnectTimeout() {
        return (int) this.getHttpClientParams()
                         .getConnectionManagerTimeout();
    }

    /**
     * @param credentialSupplier The credentialSupplier to set.
     */
    public synchronized void setCredentialSupplier(CredentialSupplier credentialSupplier) {
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
    public synchronized void setFeedInfoCache(FeedFetcherCache feedInfoCache) {
        this.feedInfoCache = feedInfoCache;
    }

    /**
     * @return the feedInfoCache.
     */
    public synchronized FeedFetcherCache getFeedInfoCache() {
        return feedInfoCache;
    }

    public synchronized void setHttpClientMethodCallback(HttpClientMethodCallbackIntf httpClientMethodCallback) {
        this.httpClientMethodCallback = httpClientMethodCallback;
    }

    public HttpClientMethodCallbackIntf getHttpClientMethodCallback() {
        return httpClientMethodCallback;
    }

    /**
     * @param httpClientParams The httpClientParams to set.
     */
    public synchronized void setHttpClientParams(HttpClientParams httpClientParams) {
        this.httpClientParams = httpClientParams;
    }

    /**
    * @return Returns the httpClientParams.
    */
    public synchronized HttpClientParams getHttpClientParams() {
        return this.httpClientParams;
    }

    /**
     * @return The currently used read timeout for the URLConnection, 0 is unlimited, i.e. no timeout
     */
    public synchronized void setReadTimeout(int timeout) {
        httpClientParams.setSoTimeout(timeout);
    }

    /**
     * @return timeout the read timeout for the URLConnection to a specified timeout, in milliseconds.
     */
    public int getReadTimeout() {
        return (int) this.getHttpClientParams()
                         .getSoTimeout();
    }

    public SyndFeed retrieveFeed(URL url) throws IllegalArgumentException, IOException, FeedException, FetcherException {
        return this.retrieveFeed(this.getUserAgent(), url);
    }

    /**
     * @see org.rometools.fetcher.FeedFetcher#retrieveFeed(java.net.URL)
     */
    public SyndFeed retrieveFeed(String userAgent, URL feedUrl)
        throws IllegalArgumentException, IOException, FeedException, FetcherException {
        if (feedUrl == null) {
            throw new IllegalArgumentException("null is not a valid URL");
        }

        // TODO Fix this
        //System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        HttpClient client = new HttpClient(httpClientParams);

        if (getCredentialSupplier() != null) {
            client.getState()
                  .setAuthenticationPreemptive(true);

            // TODO what should realm be here?
            Credentials credentials = getCredentialSupplier()
                                          .getCredentials(null, feedUrl.getHost());

            if (credentials != null) {
                client.getState()
                      .setCredentials(null, feedUrl.getHost(), credentials);
            }
        }

        System.setProperty("httpclient.useragent", userAgent);

        String urlStr = feedUrl.toString();

        HttpMethod method = new GetMethod(urlStr);
        method.addRequestHeader("Accept-Encoding", "gzip");
        method.addRequestHeader("User-Agent", userAgent);
        method.setFollowRedirects(true);

        if (httpClientMethodCallback != null) {
            synchronized (httpClientMethodCallback) {
                httpClientMethodCallback.afterHttpClientMethodCreate(method);
            }
        }

        FeedFetcherCache cache = getFeedInfoCache();

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

                int statusCode = client.executeMethod(method);
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
                method.recycle();
            }
        } else {
            // cache is not in use		    
            try {
                int statusCode = client.executeMethod(method);
                fireEvent(FetcherEvent.EVENT_TYPE_FEED_POLLED, urlStr);
                handleErrorCodes(statusCode);

                return getFeed(null, urlStr, method, statusCode);
            } finally {
                method.releaseConnection();
                method.recycle();
            }
        }
    }

    private SyndFeed getFeed(SyndFeedInfo syndFeedInfo, String urlStr, HttpMethod method, int statusCode)
        throws IOException, HttpException, FetcherException, FeedException {
        if ((statusCode == HttpURLConnection.HTTP_NOT_MODIFIED) && (syndFeedInfo != null)) {
            fireEvent(FetcherEvent.EVENT_TYPE_FEED_UNCHANGED, urlStr);

            return syndFeedInfo.getSyndFeed();
        }

        SyndFeed feed = retrieveFeed(urlStr, method);
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
    private SyndFeedInfo buildSyndFeedInfo(URL feedUrl, String urlStr, HttpMethod method, SyndFeed feed, int statusCode)
        throws MalformedURLException {
        SyndFeedInfo syndFeedInfo;
        syndFeedInfo = new SyndFeedInfo();

        // this may be different to feedURL because of 3XX redirects
        syndFeedInfo.setUrl(new URL(urlStr));
        syndFeedInfo.setId(feedUrl.toString());

        Header imHeader = method.getResponseHeader("IM");

        if ((imHeader != null) && (imHeader.getValue()
                                               .indexOf("feed") >= 0) && isUsingDeltaEncoding()) {
            FeedFetcherCache cache = getFeedInfoCache();

            if ((cache != null) && (statusCode == 226)) {
                // client is setup to use http delta encoding and the server supports it and has returned a delta encoded response
                // This response only includes new items
                SyndFeedInfo cachedInfo = cache.getFeedInfo(feedUrl);

                if (cachedInfo != null) {
                    SyndFeed cachedFeed = cachedInfo.getSyndFeed();

                    // set the new feed to be the orginal feed plus the new items
                    feed = combineFeeds(cachedFeed, feed);
                }
            }
        }

        Header lastModifiedHeader = method.getResponseHeader("Last-Modified");

        if (lastModifiedHeader != null) {
            syndFeedInfo.setLastModified(lastModifiedHeader.getValue());
        }

        Header eTagHeader = method.getResponseHeader("ETag");

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
    private SyndFeed retrieveFeed(String urlStr, HttpMethod method)
        throws IOException, HttpException, FetcherException, FeedException {
        InputStream stream = null;

        if ((method.getResponseHeader("Content-Encoding") != null) &&
                ("gzip".equalsIgnoreCase(method.getResponseHeader("Content-Encoding").getValue()))) {
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

            SyndFeedInput syndFeedInput = new SyndFeedInput();
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
             * Allows access to the underlying HttpClient HttpMethod object.
             * Note that in most cases, method.setRequestHeader(String, String)
             * is what you want to do (rather than method.addRequestHeader(String, String))
             *
             * @param method
             */
        public void afterHttpClientMethodCreate(HttpMethod method);
    }
}
