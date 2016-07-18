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

package com.rometools.certiorem.hub.data;

import java.io.Serializable;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public class Subscriber implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final String VERIFY_SYNC = "sync";
    public static final String VERIFY_ASYNC = "async";
    private String callback;
    private String secret;
    private String topic;
    private String verify;
    private String vertifyToken;
    private long creationTime = System.currentTimeMillis();
    private long leaseSeconds;

    /**
     * Set the value of callback
     *
     * @param newcallback new value of callback
     */
    public void setCallback(final String newcallback) {
        callback = newcallback;
    }

    /**
     * Get the value of callback
     *
     * @return the value of callback
     */
    public String getCallback() {
        return callback;
    }

    /**
     * Set the value of creationTime
     *
     * @param newcreationTime new value of creationTime
     */
    public void setCreationTime(final long newcreationTime) {
        creationTime = newcreationTime;
    }

    /**
     * Get the value of creationTime
     *
     * @return the value of creationTime
     */
    public long getCreationTime() {
        return creationTime;
    }

    /**
     * Set the value of leaseSeconds
     *
     * @param newleaseSeconds new value of leaseSeconds
     */
    public void setLeaseSeconds(final long newleaseSeconds) {
        leaseSeconds = newleaseSeconds;
    }

    /**
     * Get the value of leaseSeconds
     *
     * @return the value of leaseSeconds
     */
    public long getLeaseSeconds() {
        return leaseSeconds;
    }

    /**
     * Set the value of secret
     *
     * @param newsecret new value of secret
     */
    public void setSecret(final String newsecret) {
        secret = newsecret;
    }

    /**
     * Get the value of secret
     *
     * @return the value of secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Set the value of topic
     *
     * @param newtopic new value of topic
     */
    public void setTopic(final String newtopic) {
        topic = newtopic;
    }

    /**
     * Get the value of topic
     *
     * @return the value of topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Set the value of verify
     *
     * @param newverify new value of verify
     */
    public void setVerify(final String newverify) {
        verify = newverify;
    }

    /**
     * Get the value of verify
     *
     * @return the value of verify
     */
    public String getVerify() {
        return verify;
    }

    /**
     * Set the value of vertifyToken
     *
     * @param newvertifyToken new value of vertifyToken
     */
    public void setVertifyToken(final String newvertifyToken) {
        vertifyToken = newvertifyToken;
    }

    /**
     * Get the value of vertifyToken
     *
     * @return the value of vertifyToken
     */
    public String getVertifyToken() {
        return vertifyToken;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Subscriber)) {
            return false;
        }

        final Subscriber other = (Subscriber) obj;

        if (callback == null ? other.callback != null : !callback.equals(other.callback)) {
            return false;
        }

        if (secret == null ? other.secret != null : !secret.equals(other.secret)) {
            return false;
        }

        if (topic == null ? other.topic != null : !topic.equals(other.topic)) {
            return false;
        }

        if (verify == null ? other.verify != null : !verify.equals(other.verify)) {
            return false;
        }

        if (vertifyToken == null ? other.vertifyToken != null : !vertifyToken.equals(other.vertifyToken)) {
            return false;
        }

        if (creationTime != other.creationTime) {
            return false;
        }

        if (leaseSeconds != other.leaseSeconds) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (callback != null ? callback.hashCode() : 0);
        hash = 67 * hash + (secret != null ? secret.hashCode() : 0);
        hash = 67 * hash + (topic != null ? topic.hashCode() : 0);
        hash = 67 * hash + (verify != null ? verify.hashCode() : 0);
        hash = 67 * hash + (vertifyToken != null ? vertifyToken.hashCode() : 0);
        hash = 67 * hash + (int) (creationTime ^ creationTime >>> 32);
        hash = 67 * hash + (int) (leaseSeconds ^ leaseSeconds >>> 32);

        return hash;
    }
}
