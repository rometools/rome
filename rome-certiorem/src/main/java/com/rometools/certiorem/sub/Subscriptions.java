/**
 *
 *  Copyright (C) The ROME Team  2011
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.rometools.certiorem.sub;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.certiorem.HttpStatusCodeException;
import com.rometools.certiorem.sub.Requester.RequestCallback;
import com.rometools.certiorem.sub.data.SubDAO;
import com.rometools.certiorem.sub.data.Subscription;
import com.rometools.certiorem.sub.data.SubscriptionCallback;
import com.rometools.fetcher.impl.FeedFetcherCache;
import com.rometools.fetcher.impl.SyndFeedInfo;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndLink;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public class Subscriptions {

    private static final Logger LOG = LoggerFactory.getLogger(Subscriptions.class);

    // TODO unsubscribe.
    private FeedFetcherCache cache;
    private Requester requester;
    private String callbackPrefix;
    private SubDAO dao;

    public Subscriptions() {
    }

    public Subscriptions(final FeedFetcherCache cache, final Requester requester, final String callbackPrefix, final SubDAO dao) {
        this.cache = cache;
        this.requester = requester;
        this.callbackPrefix = callbackPrefix;
        this.dao = dao;
    }

    public void callback(final String callbackPath, final String feed) {
        try {
            this.callback(callbackPath, feed.getBytes("UTF-8"));
        } catch (final UnsupportedEncodingException ex) {
            LOG.error("Unable to parse feed", ex);
            throw new HttpStatusCodeException(400, "Unable to parse feed.", ex);
        }
    }

    public void callback(final String callbackPath, final InputStream feed) {
        final SyndFeedInput input = new SyndFeedInput();

        try {
            this.callback(callbackPath, input.build(new InputStreamReader(feed)));
        } catch (final IllegalArgumentException ex) {
            LOG.error("Unable to parse feed", ex);
            throw new HttpStatusCodeException(500, "Unable to parse feed.", ex);
        } catch (final FeedException ex) {
            LOG.error("Unable to parse feed", ex);
            throw new HttpStatusCodeException(400, "Unable to parse feed.", ex);
        }
    }

    public void callback(final String callbackPath, final byte[] feed) {
        this.callback(callbackPath, new ByteArrayInputStream(feed));
    }

    public void callback(final String callbackPath, final SyndFeed feed) {

        if (!callbackPath.startsWith(callbackPrefix)) {
            throw new HttpStatusCodeException(404, "Not a valid callback prefix.", new Exception(callbackPath + " doesnt start with " + callbackPrefix));
        }

        final String id = callbackPath.substring(callbackPrefix.length());
        LOG.debug("Got callback for {}", id);
        final Subscription s = dao.findById(id);

        if (s == null) {
            throw new HttpStatusCodeException(404, "Not a valid callback.", null);
        }

        validateLink(feed, s.getSourceUrl());

        SyndFeedInfo info = null;
        URL url = null;

        try {
            url = new URL(s.getSourceUrl());
            info = cache.getFeedInfo(url);
        } catch (final MalformedURLException ex) {
            LOG.error("Malformed URL", ex);
        }

        if (info == null) {
            info = new SyndFeedInfo();
            info.setId(s.getId());
            info.setUrl(url);
        }

        info.setLastModified(System.currentTimeMillis());
        info.setSyndFeed(feed);
        cache.setFeedInfo(url, info);

        s.getCallback().onNotify(s, info);
    }

    public void unsubscribe(final Subscription subscription, final String hubUrl, final boolean sync, final String secret, final SubscriptionCallback callback) {

        subscription.setUnsubscribed(true);
        requester.sendUnsubscribeRequest(hubUrl, subscription, sync ? "sync" : "async", secret, callbackPrefix + subscription.getId(), new RequestCallback() {
            @Override
            public void onSuccess() {
                callback.onUnsubscribe(subscription);
            }

            @Override
            public void onFailure(final Exception e) {
                callback.onFailure(e);
            }
        });
    }

    public void subscribe(final String hubUrl, final String topic, final boolean sync, final long leaseSeconds, final String secret,
            final SubscriptionCallback callback) {
        final Subscription s = new Subscription();
        s.setId(UUID.randomUUID().toString());
        s.setVerifyToken(UUID.randomUUID().toString());
        s.setSourceUrl(topic);
        s.setCallback(callback);
        if (leaseSeconds > 0) {
            s.setExpirationTime(System.currentTimeMillis() + leaseSeconds * 1000);
        }

        final Subscription stored = dao.addSubscription(s);

        requester.sendSubscribeRequest(hubUrl, stored, sync ? "sync" : "async", leaseSeconds, secret, callbackPrefix + stored.getId(), new RequestCallback() {
            @Override
            public void onSuccess() {
                callback.onSubscribe(stored);
            }

            @Override
            public void onFailure(final Exception e) {
                callback.onFailure(e);
            }
        });
    }

    public void subscribe(final String topic, final boolean sync, final long leaseSeconds, final String secret, final SubscriptionCallback callback)
            throws IllegalArgumentException, IOException, FeedException {
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new InputStreamReader(new URL(topic).openStream()));
        final String hubUrl = findHubUrl(feed);

        if (hubUrl == null) {
            throw new FeedException("No hub link");
        }

        this.subscribe(hubUrl, topic, sync, leaseSeconds, secret, callback);
    }

    public String validate(final String callbackPath, final String topic, final String mode, final String challenge, final String leaseSeconds,
            final String verifyToken) {
        if (!callbackPath.startsWith(callbackPrefix)) {
            throw new HttpStatusCodeException(404, "Not a valid callback prefix.", new Exception(callbackPath + " doesnt start with " + callbackPrefix));
        }

        final String id = callbackPath.substring(callbackPrefix.length());
        LOG.debug("Handling validation request for id {}", id);
        final Subscription s = dao.findById(id);
        if (s == null) {
            throw new HttpStatusCodeException(404, "Not a valid subscription id", null);
        }
        if (!s.getVerifyToken().equals(verifyToken)) {
            throw new HttpStatusCodeException(403, "Verification Token Mismatch.", null);
        }

        if ("unsubscribe".equals(mode)) {
            if (!s.isUnsubscribed()) {
                throw new HttpStatusCodeException(400, "Unsubscribe not requested.", null);
            }

            dao.removeSubscription(s);
        } else if ("subscribe".equals(mode)) {
            if (s.isValidated()) {
                throw new HttpStatusCodeException(400, "Duplicate validation.", null);
            }

            s.setValidated(true);
            dao.updateSubscription(s);
        } else {
            throw new HttpStatusCodeException(400, "Unsupported mode " + mode, null);
        }
        LOG.debug("Validated. Returning {}", challenge);
        return challenge;
    }

    private String findHubUrl(final SyndFeed feed) {
        for (final SyndLink l : feed.getLinks()) {
            if ("hub".equals(l.getRel())) {
                return l.getHref();
            }
        }

        return null;
    }

    private void validateLink(final SyndFeed feed, final String source) {
        for (final SyndLink l : feed.getLinks()) {
            if ("self".equalsIgnoreCase(l.getRel())) {
                try {
                    final URI u = new URI(l.getHref());
                    final URI t = new URI(source);

                    if (!u.equals(t)) {
                        throw new HttpStatusCodeException(400, "Feed self link does not match the subscribed URI.", null);
                    }

                    break;
                } catch (final URISyntaxException ex) {
                    LOG.error(null, ex);
                }
            }
        }
    }

    /**
     * @param cache the cache to set
     */
    public void setCache(final FeedFetcherCache cache) {
        this.cache = cache;
    }

    /**
     * @param requester the requester to set
     */
    public void setRequester(final Requester requester) {
        this.requester = requester;
    }

    /**
     * @param callbackPrefix the callbackPrefix to set
     */
    public void setCallbackPrefix(final String callbackPrefix) {
        this.callbackPrefix = callbackPrefix;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(final SubDAO dao) {
        this.dao = dao;
    }
}
