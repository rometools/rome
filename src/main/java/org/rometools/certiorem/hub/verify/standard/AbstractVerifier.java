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

package org.rometools.certiorem.hub.verify.standard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.rometools.certiorem.hub.Verifier;
import org.rometools.certiorem.hub.data.Subscriber;

/**
 * An abstract verifier based on the java.net HTTP classes. This implements only
 * synchronous operations, and expects a child class to do Async ops.
 *
 * @author robert.cooper
 */
public abstract class AbstractVerifier implements Verifier {
    @Override
    public boolean verifySubcribeSyncronously(final Subscriber subscriber) {
        return doOp(Verifier.MODE_SUBSCRIBE, subscriber);
    }

    @Override
    public boolean verifyUnsubcribeSyncronously(final Subscriber subscriber) {
        return doOp(Verifier.MODE_UNSUBSCRIBE, subscriber);
    }

    private boolean doOp(final String mode, final Subscriber subscriber) {
        try {
            final String challenge = UUID.randomUUID().toString();
            final StringBuilder queryString = new StringBuilder();
            queryString.append("hub.mode=").append(mode).append("&hub.topic=").append(URLEncoder.encode(subscriber.getTopic(), "UTF-8"))
                    .append("&hub.challenge=").append(challenge);

            if (subscriber.getLeaseSeconds() != -1) {
                queryString.append("&hub.lease_seconds=").append(subscriber.getLeaseSeconds());
            }

            if (subscriber.getVertifyToken() != null) {
                queryString.append("&hub.verify_token=").append(URLEncoder.encode(subscriber.getVertifyToken(), "UTF-8"));
            }

            final URL url = new URL(subscriber.getCallback() + "?" + queryString.toString());
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            // connection.setRequestProperty("Host", url.getHost());
            // connection.setRequestProperty("Port",
            // Integer.toString(url.getPort()));
            connection.setRequestProperty("User-Agent", "ROME-Certiorem");
            connection.connect();
            final int rc = connection.getResponseCode();
            final InputStream is = connection.getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            final String result = reader.readLine();
            reader.close();
            connection.disconnect();
            if (rc != 200 || !challenge.equals(result.trim())) {
                return false;
            } else {
                return true;
            }
        } catch (final UnsupportedEncodingException ex) {
            Logger.getLogger(AbstractVerifier.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Should not happen. UTF-8 threw unsupported encoding", ex);
        } catch (final IOException ex) {
            Logger.getLogger(AbstractVerifier.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }
}
