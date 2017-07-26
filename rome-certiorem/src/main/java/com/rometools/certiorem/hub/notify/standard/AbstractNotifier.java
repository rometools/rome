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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.certiorem.hub.Notifier;
import com.rometools.certiorem.hub.data.Subscriber;
import com.rometools.certiorem.hub.data.SubscriptionSummary;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedOutput;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public abstract class AbstractNotifier implements Notifier {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractNotifier.class);

    /**
     * This method will serialize the synd feed and build Notifications for the implementation class
     * to handle.
     *
     * @see enqueueNotification
     *
     * @param subscribers List of subscribers to notify
     * @param value The SyndFeed object to send
     * @param callback A callback that will be invoked each time a subscriber is notified.
     *
     */
    @Override
    public void notifySubscribers(final List<? extends Subscriber> subscribers, final SyndFeed value, final SubscriptionSummaryCallback callback) {

        String mimeType = null;

        if (value.getFeedType().startsWith("rss")) {
            mimeType = "application/rss+xml";
        } else {
            mimeType = "application/atom+xml";
        }

        final SyndFeedOutput output = new SyndFeedOutput();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            output.output(value, new OutputStreamWriter(baos));
            baos.close();
        } catch (final IOException ex) {
            LOG.error("Unable to output the feed", ex);
            throw new RuntimeException("Unable to output the feed.", ex);
        } catch (final FeedException ex) {
            LOG.error("Unable to output the feed", ex);
            throw new RuntimeException("Unable to output the feed.", ex);
        }

        final byte[] payload = baos.toByteArray();

        for (final Subscriber s : subscribers) {
            final Notification not = new Notification();
            not.callback = callback;
            not.lastRun = 0;
            not.mimeType = mimeType;
            not.payload = payload;
            not.retryCount = 0;
            not.subscriber = s;
            enqueueNotification(not);
        }
    }

    /**
     * Implementation method that queues/sends a notification
     *
     * @param not notification to send.
     */
    protected abstract void enqueueNotification(Notification not);

    /**
     * POSTs the payload to the subscriber's callback and returns a SubscriptionSummary with
     * subscriber counts (where possible) and the success state of the notification.
     *
     * @param subscriber subscriber data.
     * @param mimeType MIME type for the request
     * @param payload payload of the feed to send
     * @return SubscriptionSummary with the returned data.
     */
    protected SubscriptionSummary postNotification(final Subscriber subscriber, final String mimeType, final byte[] payload) {
        final SubscriptionSummary result = new SubscriptionSummary();

        try {
            final URL target = new URL(subscriber.getCallback());
            LOG.info("Posting notification to subscriber {}", subscriber.getCallback());
            result.setHost(target.getHost());

            final HttpURLConnection connection = (HttpURLConnection) target.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", mimeType);
            connection.setDoOutput(true);
            connection.connect();

            final OutputStream os = connection.getOutputStream();
            os.write(payload);
            os.close();

            final int responseCode = connection.getResponseCode();
            final String subscribers = connection.getHeaderField("X-Hub-On-Behalf-Of");
            connection.disconnect();

            if (responseCode != 200) {
                LOG.warn("Got code {} from {}", responseCode, target);
                result.setLastPublishSuccessful(false);
                return result;
            }

            if (subscribers != null) {
                try {
                    result.setSubscribers(Integer.parseInt(subscribers));
                } catch (final NumberFormatException nfe) {
                    LOG.warn("Invalid subscriber value " + subscribers + " " + target, nfe);
                    result.setSubscribers(-1);
                }
            } else {
                result.setSubscribers(-1);
            }
        } catch (final MalformedURLException ex) {
            LOG.warn(null, ex);
            result.setLastPublishSuccessful(false);
        } catch (final IOException ex) {
            LOG.error(null, ex);
            result.setLastPublishSuccessful(false);
        }

        return result;
    }
}
