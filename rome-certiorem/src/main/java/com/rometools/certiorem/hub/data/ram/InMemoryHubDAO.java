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

package com.rometools.certiorem.hub.data.ram;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.rometools.certiorem.hub.data.HubDAO;
import com.rometools.certiorem.hub.data.Subscriber;
import com.rometools.certiorem.hub.data.SubscriptionSummary;

/**
 * A Simple In-Memory HubDAO for subscribers.
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public class InMemoryHubDAO implements HubDAO {
    private final ConcurrentHashMap<String, List<Subscriber>> subscribers = new ConcurrentHashMap<String, List<Subscriber>>();
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, SubscriptionSummary>> summaries = new ConcurrentHashMap<String, ConcurrentHashMap<String, SubscriptionSummary>>();

    @Override
    public Subscriber addSubscriber(final Subscriber subscriber) {
        // FIXME assert should not be used for validation because it can be disabled
        assert subscriber != null : "Attempt to store a null subscriber";

        List<Subscriber> subList = subscribers.get(subscriber.getTopic());

        if (subList == null) {
            synchronized (this) {
                subList = new CopyOnWriteArrayList<Subscriber>();
                subscribers.put(subscriber.getTopic(), subList);
            }
        }

        subList.add(subscriber);

        return subscriber;
    }

    @Override
    public void handleSummary(final String topic, final SubscriptionSummary summary) {
        ConcurrentHashMap<String, SubscriptionSummary> hostsToSummaries = summaries.get(topic);

        if (hostsToSummaries == null) {
            synchronized (this) {
                hostsToSummaries = new ConcurrentHashMap<String, SubscriptionSummary>();
                summaries.put(topic, hostsToSummaries);
            }
        }

        hostsToSummaries.put(summary.getHost(), summary);
    }

    @Override
    public List<? extends Subscriber> subscribersForTopic(final String topic) {
        if (subscribers.containsKey(topic)) {
            final List<Subscriber> result = new LinkedList<Subscriber>();
            final LinkedList<Subscriber> expired = new LinkedList<Subscriber>();

            for (final Subscriber s : subscribers.get(topic)) {
                if (s.getLeaseSeconds() > 0 && System.currentTimeMillis() >= s.getCreationTime() + s.getLeaseSeconds() * 1000L) {
                    expired.add(s);
                } else {
                    result.add(s);
                }
            }

            if (!expired.isEmpty()) {
                subscribers.get(topic).removeAll(expired);
            }

            return result;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<? extends SubscriptionSummary> summariesForTopic(final String topic) {
        final LinkedList<SubscriptionSummary> result = new LinkedList<SubscriptionSummary>();

        if (summaries.containsKey(topic)) {
            result.addAll(summaries.get(topic).values());
        }

        return result;
    }

    @Override
    public Subscriber findSubscriber(final String topic, final String callbackUrl) {

        for (final Subscriber s : subscribersForTopic(topic)) {
            if (callbackUrl.equals(s.getCallback())) {
                return s;
            }
        }
        return null;
    }

    @Override
    public void removeSubscriber(final String topic, final String callback) {
        final List<Subscriber> subs = subscribers.get(topic);
        if (subs == null) {
            return;
        }
        Subscriber match = null;
        for (final Subscriber s : subs) {
            if (s.getCallback().equals(callback)) {
                match = s;
                break;
            }
        }
        if (match != null) {
            subs.remove(match);
        }
    }
}
