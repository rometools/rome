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

package org.rometools.certiorem.hub;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.rometools.certiorem.HttpStatusCodeException;
import org.rometools.certiorem.hub.Notifier.SubscriptionSummaryCallback;
import org.rometools.certiorem.hub.Verifier.VerificationCallback;
import org.rometools.certiorem.hub.data.HubDAO;
import org.rometools.certiorem.hub.data.Subscriber;
import org.rometools.certiorem.hub.data.SubscriptionSummary;
import org.rometools.fetcher.FeedFetcher;

import com.sun.syndication.feed.synd.SyndFeed;

/**
 * The basic business logic controller for the Hub implementation. It is intended to be usable under a very thin servlet wrapper, or other, non-HTTP
 * notification methods you might want to use.
 * 
 * @author robert.cooper
 */
public class Hub {
    private static final HashSet<String> STANDARD_SCHEMES = new HashSet<String>();

    static {
        STANDARD_SCHEMES.add("http");
        STANDARD_SCHEMES.add("https");
    }

    private final FeedFetcher fetcher;
    private final HubDAO dao;
    private final Notifier notifier;
    private final Set<Integer> validPorts;
    private final Set<String> validSchemes;
    private final Set<String> validTopics;
    private final Verifier verifier;

    /**
     * Constructs a new Hub instance
     * 
     * @param dao The persistence HubDAO to use
     * @param verifier The verification strategy to use.
     */
    public Hub(final HubDAO dao, final Verifier verifier, final Notifier notifier, final FeedFetcher fetcher) {
        this.dao = dao;
        this.verifier = verifier;
        this.notifier = notifier;
        this.fetcher = fetcher;
        validSchemes = STANDARD_SCHEMES;
        validPorts = Collections.EMPTY_SET;
        validTopics = Collections.EMPTY_SET;
    }

    /**
     * Constructs a new Hub instance.
     * 
     * @param dao The persistence HubDAO to use
     * @param verifier The verification strategy to use
     * @param validSchemes A list of valid URI schemes for callbacks (default: http, https)
     * @param validPorts A list of valid port numbers for callbacks (default: any)
     * @param validTopics A set of valid topic URIs which can be subscribed to (default: any)
     */
    public Hub(final HubDAO dao, final Verifier verifier, final Notifier notifier, final FeedFetcher fetcher, final Set<String> validSchemes,
            final Set<Integer> validPorts, final Set<String> validTopics) {
        this.dao = dao;
        this.verifier = verifier;
        this.notifier = notifier;
        this.fetcher = fetcher;

        final Set<String> readOnlySchemes = Collections.unmodifiableSet(validSchemes);
        this.validSchemes = readOnlySchemes == null ? STANDARD_SCHEMES : readOnlySchemes;

        final Set<Integer> readOnlyPorts = Collections.unmodifiableSet(validPorts);
        this.validPorts = readOnlyPorts == null ? Collections.EMPTY_SET : readOnlyPorts;

        final Set<String> readOnlyTopics = Collections.unmodifiableSet(validTopics);
        this.validTopics = readOnlyTopics == null ? Collections.EMPTY_SET : readOnlyTopics;
    }

    /**
     * Sends a notification to the subscribers
     * 
     * @param requestHost the host name the hub is running on. (Used for the user agent)
     * @param topic the URL of the topic that was updated.
     * @throws HttpStatusCodeException a wrapper exception with a recommended status code for the request.
     */
    public void sendNotification(final String requestHost, final String topic) {
        assert validTopics.isEmpty() || validTopics.contains(topic) : "That topic is not supported by this hub. " + topic;
        Logger.getLogger(Hub.class.getName()).log(Level.FINE, "Sending notification for {0}", topic);
        try {
            final List<? extends Subscriber> subscribers = dao.subscribersForTopic(topic);

            if (subscribers.isEmpty()) {
                Logger.getLogger(Hub.class.getName()).log(Level.FINE, "No subscribers to notify for {0}", topic);
                return;
            }

            final List<SubscriptionSummary> summaries = (List<SubscriptionSummary>) dao.summariesForTopic(topic);
            int total = 0;
            final StringBuilder hosts = new StringBuilder();

            for (final SubscriptionSummary s : summaries) {
                if (s.getSubscribers() > 0) {
                    total += s.getSubscribers();
                    hosts.append(" (").append(s.getHost()).append("; ").append(s.getSubscribers()).append(" subscribers)");
                }
            }

            final StringBuilder userAgent = new StringBuilder("ROME-Certiorem (+http://").append(requestHost).append("; ").append(total)
                    .append(" subscribers)").append(hosts);
            final SyndFeed feed = fetcher.retrieveFeed(userAgent.toString(), new URL(topic));
            Logger.getLogger(Hub.class.getName()).log(Level.FINE, "Got feed for {0}  Sending to {1} subscribers.", new Object[] { topic, subscribers.size() });
            notifier.notifySubscribers((List<Subscriber>) subscribers, feed, new SubscriptionSummaryCallback() {
                @Override
                public void onSummaryInfo(final SubscriptionSummary summary) {
                    dao.handleSummary(topic, summary);
                }
            });
        } catch (final Exception ex) {
            Logger.getLogger(Hub.class.getName()).log(Level.SEVERE, "Exception getting " + topic, ex);
            throw new HttpStatusCodeException(500, ex.getMessage(), ex);
        }
    }

    /**
     * Subscribes to a topic.
     * 
     * @param callback Callback URI
     * @param topic Topic URI
     * @param verify Verification Type
     * @param lease_seconds Duration of the lease
     * @param secret Secret value
     * @param verify_token verify_token;
     * @return Boolean.TRUE if the subscription succeeded synchronously, Boolean.FALSE if the subscription failed synchronously, or null if the request is
     *         asynchronous.
     * @throws HttpStatusCodeException a wrapper exception with a recommended status code for the request.
     */
    public Boolean subscribe(final String callback, final String topic, final String verify, final long lease_seconds, final String secret,
            final String verify_token) {
        Logger.getLogger(Hub.class.getName()).log(Level.FINE, "{0} wants to subscribe to {1}", new Object[] { callback, topic });
        try {
            try {
                assert callback != null : "Callback URL is required.";
                assert topic != null : "Topic URL is required.";

                final URI uri = new URI(callback);
                assert validSchemes.contains(uri.getScheme()) : "Not a valid protocol " + uri.getScheme();
                assert validPorts.isEmpty() || validPorts.contains(uri.getPort()) : "Not a valid port " + uri.getPort();
                assert validTopics.isEmpty() || validTopics.contains(topic) : "Not a supported topic " + topic;
            } catch (final URISyntaxException ex) {
                assert false : "Not a valid URI " + callback;
            }
            assert verify != null && (verify.equals(Subscriber.VERIFY_ASYNC) || verify.equals(Subscriber.VERIFY_SYNC)) : "Unexpected verify value " + verify;

            final Subscriber subscriber = new Subscriber();
            subscriber.setCallback(callback);
            subscriber.setLeaseSeconds(lease_seconds);
            subscriber.setSecret(secret);
            subscriber.setTopic(topic);
            subscriber.setVerify(verify);
            subscriber.setVertifyToken(verify_token);

            final VerificationCallback verified = new VerificationCallback() {
                @Override
                public void onVerify(final boolean verified) {
                    if (verified) {
                        Logger.getLogger(Hub.class.getName()).log(Level.FINE, "Verified {0} subscribed to {1}",
                                new Object[] { subscriber.getCallback(), subscriber.getTopic() });
                        dao.addSubscriber(subscriber);
                    }
                }
            };

            if (Subscriber.VERIFY_SYNC.equals(subscriber.getVerify())) {
                final boolean result = verifier.verifySubcribeSyncronously(subscriber);
                verified.onVerify(result);

                return result;
            } else {
                verifier.verifySubscribeAsyncronously(subscriber, verified);

                return null;
            }
        } catch (final AssertionError ae) {
            throw new HttpStatusCodeException(400, ae.getMessage(), ae);
        } catch (final Exception e) {
            throw new HttpStatusCodeException(500, e.getMessage(), e);
        }
    }

    public Boolean unsubscribe(final String callback, final String topic, final String verify, final String secret, final String verifyToken) {
        final Subscriber subscriber = dao.findSubscriber(topic, callback);
        if (subscriber == null) {
            throw new HttpStatusCodeException(400, "Not a valid subscription.", null);
        }
        subscriber.setVertifyToken(verifyToken);
        subscriber.setSecret(secret);
        if (Subscriber.VERIFY_SYNC.equals(verify)) {

            final boolean ret = verifier.verifyUnsubcribeSyncronously(subscriber);
            if (ret) {
                dao.removeSubscriber(topic, callback);
            }
        } else {
            verifier.verifyUnsubscribeAsyncronously(subscriber, new VerificationCallback() {

                @Override
                public void onVerify(final boolean verified) {
                    Logger.getLogger(Hub.class.getName()).log(Level.FINE, "Unsubscribe for {0} at {1} verified {2}",
                            new Object[] { subscriber.getTopic(), subscriber.getCallback(), verified });
                    if (verified) {
                        dao.removeSubscriber(topic, callback);
                    }
                }

            });
        }
        return null;
    }
}
