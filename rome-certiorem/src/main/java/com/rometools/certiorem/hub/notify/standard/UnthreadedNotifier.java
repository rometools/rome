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

package com.rometools.certiorem.hub.notify.standard;

import com.rometools.certiorem.hub.data.SubscriptionSummary;

/**
 * A notifier that does not use threads. All calls are blocking and synchronous.
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public class UnthreadedNotifier extends AbstractNotifier {

    /**
     * A blocking call that performs a notification. If there are pending retries that are older
     * than two minutes old, they will be retried before the method returns.
     *
     * @param not
     */
    @Override
    protected void enqueueNotification(final Notification not) {
        not.lastRun = System.currentTimeMillis();
        final SubscriptionSummary summary = postNotification(not.subscriber, not.mimeType, not.payload);
        not.callback.onSummaryInfo(summary);
    }

}
