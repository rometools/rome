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

package com.rometools.certiorem.sub.data;

import java.io.Serializable;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public class Subscription implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String sourceUrl;
    private String verifyToken;
    private boolean unsubscribed;
    private boolean validated;
    private long expirationTime;
    private SubscriptionCallback callback;

    /**
     * Set the value of expirationTime
     *
     * @param newexpirationTime new value of expirationTime
     */
    public void setExpirationTime(final long newexpirationTime) {
        expirationTime = newexpirationTime;
    }

    /**
     * Get the value of expirationTime
     *
     * @return the value of expirationTime
     */
    public long getExpirationTime() {
        return expirationTime;
    }

    /**
     * Set the value of id
     *
     * @param newid new value of id
     */
    public void setId(final String newid) {
        id = newid;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of sourceUrl
     *
     * @param newsourceUrl new value of sourceUrl
     */
    public void setSourceUrl(final String newsourceUrl) {
        sourceUrl = newsourceUrl;
    }

    /**
     * Get the value of sourceUrl
     *
     * @return the value of sourceUrl
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     * Set the value of unsubscribed
     *
     * @param newunsubscribed new value of unsubscribed
     */
    public void setUnsubscribed(final boolean newunsubscribed) {
        unsubscribed = newunsubscribed;
    }

    /**
     * Get the value of unsubscribed
     *
     * @return the value of unsubscribed
     */
    public boolean isUnsubscribed() {
        return unsubscribed;
    }

    /**
     * Set the value of validated
     *
     * @param newvalidated new value of validated
     */
    public void setValidated(final boolean newvalidated) {
        validated = newvalidated;
    }

    /**
     * Get the value of validated
     *
     * @return the value of validated
     */
    public boolean isValidated() {
        return validated;
    }

    /**
     * Set the value of verifyToken
     *
     * @param newverifyToken new value of verifyToken
     */
    public void setVerifyToken(final String newverifyToken) {
        verifyToken = newverifyToken;
    }

    /**
     * Get the value of verifyToken
     *
     * @return the value of verifyToken
     */
    public String getVerifyToken() {
        return verifyToken;
    }

    /**
     * @return the callback
     */
    public SubscriptionCallback getCallback() {
        return callback;
    }

    /**
     * @param callback the callback to set
     */
    public void setCallback(final SubscriptionCallback callback) {
        this.callback = callback;
    }
}
