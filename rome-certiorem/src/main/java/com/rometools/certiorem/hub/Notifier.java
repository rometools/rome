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

package com.rometools.certiorem.hub;

import java.util.List;

import com.rometools.certiorem.hub.data.Subscriber;
import com.rometools.certiorem.hub.data.SubscriptionSummary;
import com.rometools.rome.feed.synd.SyndFeed;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public interface Notifier {
    /**
     * Instructs the notifier to begin sending notifications to the list of subscribers
     *
     * @param subscribers Subscribers to notify
     * @param value The SyndFeed to send them
     * @param callback A callback that is invoked each time a subscriber is notified.
     */
    public void notifySubscribers(List<? extends Subscriber> subscribers, SyndFeed value, SubscriptionSummaryCallback callback);

    /**
     * A callback that is invoked each time a subscriber is notified.
     */
    public static interface SubscriptionSummaryCallback {
        /**
         *
         * @param summary A summary of the data received from the subscriber
         */
        public void onSummaryInfo(SubscriptionSummary summary);
    }
}
