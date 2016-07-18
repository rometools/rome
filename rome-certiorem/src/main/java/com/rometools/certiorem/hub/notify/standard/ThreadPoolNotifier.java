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

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.rometools.certiorem.hub.data.SubscriptionSummary;

/**
 * A notifier implementation that uses a thread pool to deliver notifications to subscribers
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public class ThreadPoolNotifier extends AbstractNotifier {
    private static final long TWO_MINUTES = 2 * 60 * 1000;
    protected final ThreadPoolExecutor exeuctor;
    private final Timer timer = new Timer();
    private final ConcurrentSkipListSet<String> pendings = new ConcurrentSkipListSet<String>();

    public ThreadPoolNotifier() {
        this(2, 5, 5);
    }

    public ThreadPoolNotifier(final int startPoolSize, final int maxPoolSize, final int queueSize) {
        exeuctor = new ThreadPoolExecutor(startPoolSize, maxPoolSize, 300, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(queueSize));
    }

    protected ThreadPoolNotifier(final ThreadPoolExecutor executor) {
        exeuctor = executor;
    }

    /**
     * Enqueues a notification to run. If the notification fails, it will be retried every two
     * minutes until 5 attempts are completed. Notifications to the same callback should be
     * delivered successfully in order.
     *
     * @param not
     */
    @Override
    protected void enqueueNotification(final Notification not) {
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                not.lastRun = System.currentTimeMillis();

                final SubscriptionSummary summary = postNotification(not.subscriber, not.mimeType, not.payload);

                if (!summary.isLastPublishSuccessful()) {
                    not.retryCount++;

                    if (not.retryCount <= 5) {
                        retry(not);
                    }
                }

                not.callback.onSummaryInfo(summary);
            }
        };

        exeuctor.execute(r);
    }

    /**
     * Schedules a notification to retry in two minutes.
     *
     * @param not Notification to retry
     */
    protected void retry(final Notification not) {
        if (!pendings.contains(not.subscriber.getCallback())) {
            // We don't have a current retry for this callback pending, so we
            // will schedule the retry
            pendings.add(not.subscriber.getCallback());
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    pendings.remove(not.subscriber.getCallback());
                    enqueueNotification(not);
                }
            }, TWO_MINUTES);
        } else {
            // There is a retry in front of this one, so we will just schedule
            // it to retry again in a bit
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    retry(not);
                }
            }, TWO_MINUTES);
        }
    }
}
