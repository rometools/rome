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

package com.rometools.certiorem.sub.request;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.rometools.certiorem.sub.Requester;
import com.rometools.certiorem.sub.data.Subscription;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public abstract class AbstractRequester implements Requester {

    protected boolean sendRequest(final String hubUrl, final String mode, final Subscription subscription, final String verifySync, final long leaseSeconds,
            final String secret, final String callbackUrl, final RequestCallback callback) throws IOException {
        final StringBuilder sb = new StringBuilder("hub.callback=").append(URLEncoder.encode(callbackUrl, "UTF-8")).append("&hub.topic=")
                .append(URLEncoder.encode(subscription.getSourceUrl(), "UTF-8")).append("&hub.verify=").append(URLEncoder.encode(verifySync, "UTF-8"))
                .append("&hub.mode=").append(URLEncoder.encode(mode, "UTF-8"));

        if (leaseSeconds > 0) {
            sb.append("&hub.lease_seconds=").append(Long.toString(leaseSeconds));
        }

        if (secret != null) {
            sb.append("&hub.secret=").append(URLEncoder.encode(secret, "UTF-8"));
        }

        if (subscription.getVerifyToken() != null) {
            sb.append("&hub.verify_token=").append(URLEncoder.encode(subscription.getVerifyToken(), "UTF-8"));
        }

        final URL url = new URL(hubUrl);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);
        // connection.setRequestProperty("Host", url.getHost());
        connection.setRequestProperty("User-Agent", "ROME-Certiorem");
        connection.connect();
        connection.getOutputStream().write(sb.toString().getBytes("UTF-8"));

        final int rc = connection.getResponseCode();
        connection.disconnect();

        if (rc != 204) {
            throw new RuntimeException("Unexpected repsonse code. " + rc);
        }

        return true;
    }
}
