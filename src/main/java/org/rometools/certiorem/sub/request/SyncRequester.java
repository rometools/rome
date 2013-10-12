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
package org.rometools.certiorem.sub.request;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.rometools.certiorem.sub.data.Subscription;

/**
 * A simple requester implementation that always makes requests as Async.
 * 
 * @author Farrukh Najmi
 */
public class SyncRequester extends AbstractRequester {
    @Override
    public void sendSubscribeRequest(final String hubUrl, final Subscription subscription, final String verifySync, final long leaseSeconds,
            final String secret, final String callbackUrl, final RequestCallback callback) {
        Logger.getLogger(SyncRequester.class.getName()).log(Level.INFO, "Sending subscribe request to {0} for {1} to {2}",
                new Object[] { hubUrl, subscription.getSourceUrl(), callbackUrl });
        try {
            sendRequest(hubUrl, "subscribe", subscription, verifySync, leaseSeconds, secret, callbackUrl, callback);
            callback.onSuccess();
        } catch (final Exception ex) {
            Logger.getLogger(SyncRequester.class.getName()).log(Level.SEVERE, null, ex);
            callback.onFailure(ex);
        }
    }

    @Override
    public void sendUnsubscribeRequest(final String hubUrl, final Subscription subscription, final String verifySync, final String secret,
            final String callbackUrl, final RequestCallback callback) {
        Logger.getLogger(SyncRequester.class.getName()).log(Level.INFO, "Sending unsubscribe request to {0} for {1} to {2}",
                new Object[] { hubUrl, subscription.getSourceUrl(), callbackUrl });
        try {
            sendRequest(hubUrl, "unsubscribe", subscription, verifySync, -1, secret, callbackUrl, callback);
            callback.onSuccess();
        } catch (final IOException ex) {
            Logger.getLogger(SyncRequester.class.getName()).log(Level.SEVERE, null, ex);
            callback.onFailure(ex);
        }
    }
}
