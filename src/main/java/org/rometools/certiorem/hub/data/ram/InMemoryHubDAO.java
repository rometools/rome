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

package org.rometools.certiorem.hub.data.ram;

import org.rometools.certiorem.hub.data.HubDAO;
import org.rometools.certiorem.hub.data.Subscriber;
import org.rometools.certiorem.hub.data.SubscriptionSummary;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * A Simple In-Memory HubDAO for subscribers.
 *
 * @author robert.cooper
 */
public class InMemoryHubDAO implements HubDAO {
    private ConcurrentHashMap<String, List<Subscriber>> subscribers = new ConcurrentHashMap<String, List<Subscriber>>();
    private ConcurrentHashMap<String, ConcurrentHashMap<String, SubscriptionSummary>> summaries = new ConcurrentHashMap<String, ConcurrentHashMap<String, SubscriptionSummary>>();

    @Override
    public Subscriber addSubscriber(Subscriber subscriber) {
        assert subscriber != null : "Attempt to store a null subscriber";

        List<Subscriber> subList = this.subscribers.get(subscriber.getTopic());

        if (subList == null) {
            synchronized (this) {
                subList = new CopyOnWriteArrayList<Subscriber>();
                this.subscribers.put(subscriber.getTopic(), subList);
            }
        }

        subList.add(subscriber);

        return subscriber;
    }

    @Override
    public void handleSummary(String topic, SubscriptionSummary summary) {
        ConcurrentHashMap<String, SubscriptionSummary> hostsToSummaries = this.summaries.get(topic);

        if (hostsToSummaries == null) {
            synchronized (this) {
                hostsToSummaries = new ConcurrentHashMap<String, SubscriptionSummary>();
                this.summaries.put(topic, hostsToSummaries);
            }
        }

        hostsToSummaries.put(summary.getHost(), summary);
    }

    @Override
    public List<?extends Subscriber> subscribersForTopic(String topic) {
        if (subscribers.containsKey(topic)) {
            List<Subscriber> result = new LinkedList<Subscriber>();
            LinkedList<Subscriber> expired = new LinkedList<Subscriber>();

            for (Subscriber s : subscribers.get(topic)) {
                if ((s.getLeaseSeconds() > 0) &&
                        (System.currentTimeMillis() >= (s.getCreationTime() + (s.getLeaseSeconds() * 1000L)))) {
                    expired.add(s);
                } else {
                    result.add(s);
                }
            }

            if (!expired.isEmpty()) {
                subscribers.get(topic)
                           .removeAll(expired);
            }

            return result;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<?extends SubscriptionSummary> summariesForTopic(String topic) {
        LinkedList<SubscriptionSummary> result = new LinkedList<SubscriptionSummary>();

        if (this.summaries.containsKey(topic)) {
            result.addAll(this.summaries.get(topic).values());
        }

        return result;
    }

    @Override
    public Subscriber findSubscriber(String topic, String callbackUrl) {

        for (Subscriber s : this.subscribersForTopic(topic)) {
            if (callbackUrl.equals(s.getCallback())) {
               return s;
            }
        }
        return null;
    }

    @Override
    public void removeSubscriber(String topic, String callback) {
        List<Subscriber> subs = this.subscribers.get(topic);
        if(subs == null){
            return;
        }
        Subscriber match = null;
        for(Subscriber s: subs){
            if(s.getCallback().equals(callback)){
                match = s;
                break;
            }
        }
        if(match != null){
            subs.remove(match);
        }
    }
}
