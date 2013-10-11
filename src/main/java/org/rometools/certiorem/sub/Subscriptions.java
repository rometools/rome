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

package org.rometools.certiorem.sub;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndLink;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

import org.rometools.certiorem.HttpStatusCodeException;
import org.rometools.certiorem.sub.Requester.RequestCallback;
import org.rometools.certiorem.sub.data.SubDAO;
import org.rometools.certiorem.sub.data.Subscription;

import org.rometools.fetcher.impl.FeedFetcherCache;
import org.rometools.fetcher.impl.SyndFeedInfo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rometools.certiorem.sub.data.SubscriptionCallback;


/**
 *
 * @author robert.cooper
 */
public class Subscriptions {
    //TODO unsubscribe.
    private FeedFetcherCache cache;
    private Requester requester;
    private String callbackPrefix;
    private SubDAO dao;

    public Subscriptions() {        
    }
    
    public Subscriptions(final FeedFetcherCache cache, final Requester requester, final String callbackPrefix,
        final SubDAO dao) {
        this.cache = cache;
        this.requester = requester;
        this.callbackPrefix = callbackPrefix;
        this.dao = dao;
    }

    public void callback(String callbackPath, String feed) {
        try {
            this.callback(callbackPath, feed.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Subscriptions.class.getName())
                  .log(Level.SEVERE, null, ex);
            throw new HttpStatusCodeException(400, "Unable to parse feed.", ex);
        }
    }

    public void callback(String callbackPath, InputStream feed) {
        SyndFeedInput input = new SyndFeedInput();

        try {
            this.callback(callbackPath, input.build(new InputStreamReader(feed)));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Subscriptions.class.getName())
                  .log(Level.SEVERE, null, ex);
            throw new HttpStatusCodeException(500, "Unable to parse feed.", ex);
        } catch (FeedException ex) {
            Logger.getLogger(Subscriptions.class.getName())
                  .log(Level.SEVERE, null, ex);
            throw new HttpStatusCodeException(400, "Unable to parse feed.", ex);
        }
    }

    public void callback(String callbackPath, byte[] feed) {
        this.callback(callbackPath, new ByteArrayInputStream(feed));
    }

    public void callback(String callbackPath, SyndFeed feed) {

        if (!callbackPath.startsWith(callbackPrefix)) {
            throw new HttpStatusCodeException(404, "Not a valid callback prefix.", new Exception(callbackPath+" doesnt start with "+callbackPrefix));
        }

        String id = callbackPath.substring(callbackPrefix.length());
        Logger.getLogger(Subscriptions.class.getName()).log(Level.FINE, "Got callback for {0}", id);
        Subscription s = dao.findById(id);

        if (s == null) {
            throw new HttpStatusCodeException(404, "Not a valid callback.", null);
        }

        this.validateLink(feed, s.getSourceUrl());

        SyndFeedInfo info = null;
        URL url = null;

        try {
            url = new URL(s.getSourceUrl());
            info = cache.getFeedInfo(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Subscriptions.class.getName())
                  .log(Level.SEVERE, null, ex);
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

    public void unsubscribe(final Subscription subscription, String hubUrl, boolean sync, String secret,
        final SubscriptionCallback callback) {                        

        subscription.setUnsubscribed(true);
        this.requester.sendUnsubscribeRequest(hubUrl, subscription, (sync ? "sync" : "async"), secret,
            this.callbackPrefix + subscription.getId(),
            new RequestCallback() {
                @Override
                public void onSuccess() {                    
                    callback.onUnsubscribe(subscription);
                }

                @Override
                public void onFailure(Exception e) {
                    callback.onFailure(e);
                }
            });
    }
    
    public void subscribe(String hubUrl, String topic, boolean sync, long leaseSeconds, String secret,
        final SubscriptionCallback callback) {
        Subscription s = new Subscription();
        s.setId(UUID.randomUUID().toString());
        s.setVerifyToken(UUID.randomUUID().toString());
        s.setSourceUrl(topic);
        s.setCallback(callback);
        if (leaseSeconds > 0) {
            s.setExpirationTime(System.currentTimeMillis() + (leaseSeconds * 1000));
        }

        final Subscription stored = this.dao.addSubscription(s);

        this.requester.sendSubscribeRequest(hubUrl, stored, (sync ? "sync" : "async"), leaseSeconds, secret,
            this.callbackPrefix + stored.getId(),
            new RequestCallback() {
                @Override
                public void onSuccess() {
                    callback.onSubscribe(stored);
                }

                @Override
                public void onFailure(Exception e) {
                    callback.onFailure(e);
                }
            });
    }

    public void subscribe(String topic, boolean sync, long leaseSeconds, String secret,
        final SubscriptionCallback callback) throws IllegalArgumentException, IOException, FeedException {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new InputStreamReader(new URL(topic).openStream()));
        String hubUrl = this.findHubUrl(feed);

        if (hubUrl == null) {
            throw new FeedException("No hub link");
        }

        this.subscribe(hubUrl, topic, sync, leaseSeconds, secret, callback);
    }

    public String validate(String callbackPath, String topic, String mode, String challenge, String leaseSeconds,
        String verifyToken) {
        if (!callbackPath.startsWith(callbackPrefix)) {
            throw new HttpStatusCodeException(404, "Not a valid callback prefix.", new Exception(callbackPath+" doesnt start with "+callbackPrefix));
        }

        String id = callbackPath.substring(callbackPrefix.length());
        Logger.getLogger(Subscriptions.class.getName()).log(Level.FINE, "Handling validation request for id {0}", id);
        Subscription s = dao.findById(id);
        if(s == null){
            throw new HttpStatusCodeException(404, "Not a valid subscription id", null);
        }
        if (!s.getVerifyToken()
                  .equals(verifyToken)) {
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
        Logger.getLogger(Subscriptions.class.getName()).log(Level.FINE, "Validated. Returning {0}", challenge);
        return challenge;
    }

    private String findHubUrl(SyndFeed feed) {
        for (SyndLink l : (List<SyndLink>) feed.getLinks()) {
            if ("hub".equals(l.getRel())) {
                return l.getHref();
            }
        }

        return null;
    }

    private void validateLink(SyndFeed feed, String source) {
        for (SyndLink l : (List<SyndLink>) feed.getLinks()) {
            if ("self".equalsIgnoreCase(l.getRel())) {
                try {
                    URI u = new URI(l.getHref());
                    URI t = new URI(source);

                    if (!u.equals(t)) {
                        throw new HttpStatusCodeException(400, "Feed self link does not match the subscribed URI.", null);
                    }

                    break;
                } catch (URISyntaxException ex) {
                    Logger.getLogger(Subscriptions.class.getName())
                          .log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * @param cache the cache to set
     */
    public void setCache(FeedFetcherCache cache) {
        this.cache = cache;
    }

    /**
     * @param requester the requester to set
     */
    public void setRequester(Requester requester) {
        this.requester = requester;
    }

    /**
     * @param callbackPrefix the callbackPrefix to set
     */
    public void setCallbackPrefix(String callbackPrefix) {
        this.callbackPrefix = callbackPrefix;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(SubDAO dao) {
        this.dao = dao;
    }
}
