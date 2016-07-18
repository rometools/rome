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

import com.rometools.certiorem.hub.data.Subscriber;

/**
 * A strategy interface for verification of subscriptions.
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public interface Verifier {

    /**
     * Value for hub.mode = subscribe
     */
    public static final String MODE_SUBSCRIBE = "subscribe";
    /**
     * Value for hub.mode = unsubscribe
     */
    public static final String MODE_UNSUBSCRIBE = "unsubscribe";

    /**
     * Verifies a subscriber (possibly) asyncronously
     *
     * @param subscriber the Subscriber to verify
     * @param callback a callback with the result of the verification.
     */
    public void verifySubscribeAsyncronously(Subscriber subscriber, VerificationCallback callback);

    /**
     * Verifies a subscriber syncronously
     *
     * @param subscriber The subscriber data
     * @return boolean result;
     */
    public boolean verifySubcribeSyncronously(Subscriber subscriber);

    /**
     * Verifies am unsubscribe (possibly) asyncronously
     *
     * @param subscriber The subscriber data
     * @param callback result
     */
    public void verifyUnsubscribeAsyncronously(Subscriber subscriber, VerificationCallback callback);

    /**
     * Verifies an unsubscribe syncronously
     *
     * @param subscriber The subscriber data
     * @return boolean result;
     */
    public boolean verifyUnsubcribeSyncronously(Subscriber subscriber);

    /**
     * An interface for capturing the result of a verification (subscribe or unsubscribe)
     */
    public static interface VerificationCallback {
        /**
         *
         * @param verified success state of the verification
         */
        public void onVerify(boolean verified);
    }
}
