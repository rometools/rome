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

 
package org.rometools.certiorem.hub.notify.standard;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

import org.rometools.certiorem.hub.Notifier;
import org.rometools.certiorem.hub.data.Subscriber;
import org.rometools.certiorem.hub.data.SubscriptionSummary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rometools.certiorem.sub.data.ram.InMemorySubDAO;


/**
 *
 * @author robert.cooper
 */
public abstract class AbstractNotifier implements Notifier {
    /**
     * This method will serialize the synd feed and build Notifications for the
     * implementation class to handle.
     * @see  enqueueNotification
     *
     * @param subscribers List of subscribers to notify
     * @param value The SyndFeed object to send
     * @param callback A callback that will be invoked each time a subscriber is notified.
     *
     */
    @Override
    public void notifySubscribers(List<Subscriber> subscribers, SyndFeed value, SubscriptionSummaryCallback callback) {
        String mimeType = null;

        if (value.getFeedType()
                     .startsWith("rss")) {
            mimeType = "application/rss+xml";
        } else {
            mimeType = "application/atom+xml";
        }

        SyndFeedOutput output = new SyndFeedOutput();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            output.output(value, new OutputStreamWriter(baos));
            baos.close();
        } catch (IOException ex) {
            Logger.getLogger(AbstractNotifier.class.getName())
                  .log(Level.SEVERE, null, ex);
            throw new RuntimeException("Unable to output the feed.", ex);
        } catch (FeedException ex) {
            Logger.getLogger(AbstractNotifier.class.getName())
                  .log(Level.SEVERE, null, ex);
            throw new RuntimeException("Unable to output the feed.", ex);
        }

        byte[] payload = baos.toByteArray();

        for (Subscriber s : subscribers) {
            Notification not = new Notification();
            not.callback = callback;
            not.lastRun = 0;
            not.mimeType = mimeType;
            not.payload = payload;
            not.retryCount = 0;
            not.subscriber = s;
            this.enqueueNotification(not);
        }
    }
    /** Implementation method that queues/sends a notification
     *
     * @param not notification to send.
     */
    protected abstract void enqueueNotification(Notification not);

    /**
     * POSTs the payload to the subscriber's callback and returns a
     * SubscriptionSummary with subscriber counts (where possible) and the
     * success state of the notification.
     * @param subscriber subscriber data.
     * @param mimeType MIME type for the request
     * @param payload payload of the feed to send
     * @return SubscriptionSummary with the returned data.
     */
    protected SubscriptionSummary postNotification(Subscriber subscriber, String mimeType, byte[] payload) {
        SubscriptionSummary result = new SubscriptionSummary();

        try {
            URL target = new URL(subscriber.getCallback());
            Logger.getLogger(AbstractNotifier.class.getName()).log(Level.INFO, "Posting notification to subscriber {0}", subscriber.getCallback());
            result.setHost(target.getHost());

            HttpURLConnection connection = (HttpURLConnection) target.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", mimeType);
            connection.setDoOutput(true);
            connection.connect();

            OutputStream os = connection.getOutputStream();
            os.write(payload);
            os.close();

            int responseCode = connection.getResponseCode();
            String subscribers = connection.getHeaderField("X-Hub-On-Behalf-Of");
            connection.disconnect();
            
            if (responseCode != 200) {
                Logger.getLogger(AbstractNotifier.class.getName())
                      .log(Level.WARNING, "Got code " + responseCode + " from " + target);
                result.setLastPublishSuccessful(false);

                return result;
            }

            

            if (subscribers != null) {
                try {
                    result.setSubscribers(Integer.parseInt(subscribers));
                } catch (NumberFormatException nfe) {
                    Logger.getLogger(AbstractNotifier.class.getName())
                          .log(Level.WARNING, "Invalid subscriber value " + subscribers + " " + target, nfe);
                    result.setSubscribers(-1);
                }
            } else {
                result.setSubscribers(-1);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(AbstractNotifier.class.getName())
                  .log(Level.WARNING, null, ex);
            result.setLastPublishSuccessful(false);
        } catch (IOException ex) {
            Logger.getLogger(AbstractNotifier.class.getName())
                  .log(Level.SEVERE, null, ex);
            result.setLastPublishSuccessful(false);
        }

        return result;
    }
}
