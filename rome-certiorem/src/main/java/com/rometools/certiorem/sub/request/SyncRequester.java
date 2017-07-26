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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rometools.certiorem.sub.request;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.certiorem.sub.data.Subscription;

/**
 * A simple requester implementation that always makes requests as Async.
 *
 * @author Farrukh Najmi
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public class SyncRequester extends AbstractRequester {

    private static final Logger LOG = LoggerFactory.getLogger(SyncRequester.class);

    @Override
    public void sendSubscribeRequest(final String hubUrl, final Subscription subscription, final String verifySync, final long leaseSeconds,
            final String secret, final String callbackUrl, final RequestCallback callback) {
        LOG.info("Sending subscribe request to {} for {} to {}", hubUrl, subscription.getSourceUrl(), callbackUrl);
        try {
            sendRequest(hubUrl, "subscribe", subscription, verifySync, leaseSeconds, secret, callbackUrl, callback);
            callback.onSuccess();
        } catch (final Exception ex) {
            LOG.error(null, ex);
            callback.onFailure(ex);
        }
    }

    @Override
    public void sendUnsubscribeRequest(final String hubUrl, final Subscription subscription, final String verifySync, final String secret,
            final String callbackUrl, final RequestCallback callback) {
        LOG.info("Sending unsubscribe request to {} for {} to {}", hubUrl, subscription.getSourceUrl(), callbackUrl);
        try {
            sendRequest(hubUrl, "unsubscribe", subscription, verifySync, -1, secret, callbackUrl, callback);
            callback.onSuccess();
        } catch (final IOException ex) {
            LOG.error(null, ex);
            callback.onFailure(ex);
        }
    }
}
