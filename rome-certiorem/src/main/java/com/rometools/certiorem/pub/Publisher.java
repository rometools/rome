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

package com.rometools.certiorem.pub;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndLink;

/**
 * A class for sending update notifications to a hub.
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public class Publisher {

    private static final Logger LOG = LoggerFactory.getLogger(Publisher.class);

    private ThreadPoolExecutor executor;

    /**
     * Constructs a new publisher. This publisher will spawn a new thread for each async send.
     */
    public Publisher() {
    }

    /**
     * Constructs a new publisher with an optional ThreadPoolExector for sending updates.
     */
    public Publisher(final ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    /**
     * Sends the HUB url a notification of a change in topic
     *
     * @param hub URL of the hub to notify.
     * @param topic The Topic that has changed
     * @throws NotificationException Any failure
     */
    public void sendUpdateNotification(final String hub, final String topic) throws NotificationException {
        try {
            final StringBuilder sb = new StringBuilder("hub.mode=publish&hub.url=").append(URLEncoder.encode(topic, "UTF-8"));
            final URL hubUrl = new URL(hub);
            final HttpURLConnection connection = (HttpURLConnection) hubUrl.openConnection();
            // connection.setRequestProperty("Host", hubUrl.getHost());
            connection.setRequestProperty("User-Agent", "ROME-Certiorem");
            connection.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);
            connection.connect();

            final OutputStream os = connection.getOutputStream();
            os.write(sb.toString().getBytes("UTF-8"));
            os.close();

            final int rc = connection.getResponseCode();
            connection.disconnect();

            if (rc != 204) {
                throw new NotificationException("Server returned an unexcepted response code: " + rc + " " + connection.getResponseMessage());
            }

        } catch (final UnsupportedEncodingException ex) {
            LOG.error("Could not encode URL", ex);
            throw new NotificationException("Could not encode URL", ex);
        } catch (final IOException ex) {
            LOG.error("Communication error", ex);
            throw new NotificationException("Unable to communicate with " + hub, ex);
        }
    }

    /**
     * Sends a notification for a feed located at "topic". The feed MUST contain rel="hub".
     *
     * @param topic URL for the feed
     * @param feed The feed itself
     * @throws NotificationException Any failure
     */
    public void sendUpdateNotification(final String topic, final SyndFeed feed) throws NotificationException {
        for (final SyndLink link : feed.getLinks()) {
            if ("hub".equals(link.getRel())) {
                sendUpdateNotification(link.getRel(), topic);

                return;
            }
        }
        throw new NotificationException("Hub link not found.");
    }

    /**
     * Sends a notification for a feed. The feed MUST contain rel="hub" and rel="self" links.
     *
     * @param feed The feed to notify
     * @throws NotificationException Any failure
     */
    public void sendUpdateNotification(final SyndFeed feed) throws NotificationException {
        SyndLink hub = null;
        SyndLink self = null;

        for (final SyndLink link : feed.getLinks()) {
            if ("hub".equals(link.getRel())) {
                hub = link;
            }

            if ("self".equals(link.getRel())) {
                self = link;
            }

            if (hub != null && self != null) {
                break;
            }
        }

        if (hub == null) {
            throw new NotificationException("A link rel='hub' was not found in the feed.");
        }

        if (self == null) {
            throw new NotificationException("A link rel='self' was not found in the feed.");
        }

        sendUpdateNotification(hub.getRel(), self.getHref());
    }

    /**
     * Sends the HUB url a notification of a change in topic asynchronously
     *
     * @param hub URL of the hub to notify.
     * @param topic The Topic that has changed
     * @param callback A callback invoked when the notification completes.
     * @throws NotificationException Any failure
     */
    public void sendUpdateNotificationAsyncronously(final String hub, final String topic, final AsyncNotificationCallback callback) {
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    sendUpdateNotification(hub, topic);
                    callback.onSuccess();
                } catch (final Throwable t) {
                    callback.onFailure(t);
                }
            }
        };

        if (executor != null) {
            executor.execute(r);
        } else {
            new Thread(r).start();
        }
    }

    /**
     * Asynchronously sends a notification for a feed located at "topic". The feed MUST contain
     * rel="hub".
     *
     * @param topic URL for the feed
     * @param feed The feed itself
     * @param callback A callback invoked when the notification completes.
     * @throws NotificationException Any failure
     */
    public void sendUpdateNotificationAsyncronously(final String topic, final SyndFeed feed, final AsyncNotificationCallback callback) {
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    sendUpdateNotification(topic, feed);
                    callback.onSuccess();
                } catch (final Throwable t) {
                    callback.onFailure(t);
                }
            }
        };

        if (executor != null) {
            executor.execute(r);
        } else {
            new Thread(r).start();
        }
    }

    /**
     * Asyncronously sends a notification for a feed. The feed MUST contain rel="hub" and rel="self"
     * links.
     *
     * @param feed The feed to notify
     * @param callback A callback invoked when the notification completes.
     * @throws NotificationException Any failure
     */
    public void sendUpdateNotificationAsyncronously(final SyndFeed feed, final AsyncNotificationCallback callback) {
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    sendUpdateNotification(feed);
                    callback.onSuccess();
                } catch (final Throwable t) {
                    callback.onFailure(t);
                }
            }
        };

        if (executor != null) {
            executor.execute(r);
        } else {
            new Thread(r).start();
        }
    }

    /**
     * A callback interface for asynchronous notifications.
     */
    public static interface AsyncNotificationCallback {
        /**
         * Called when a notification fails
         *
         * @param thrown Whatever was thrown during the failure
         */
        public void onFailure(Throwable thrown);

        /**
         * Invoked with the asyncronous notification completes successfully.
         */
        public void onSuccess();
    }
}
