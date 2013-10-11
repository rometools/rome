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

package org.rometools.certiorem.hub.data;

import java.io.Serializable;


/**
 *
 * @author robert.cooper
 */
public class Subscriber implements Serializable {
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
    public void setCallback(String newcallback) {
        this.callback = newcallback;
    }

    /**
     * Get the value of callback
     *
     * @return the value of callback
     */
    public String getCallback() {
        return this.callback;
    }

    /**
     * Set the value of creationTime
     *
     * @param newcreationTime new value of creationTime
     */
    public void setCreationTime(long newcreationTime) {
        this.creationTime = newcreationTime;
    }

    /**
     * Get the value of creationTime
     *
     * @return the value of creationTime
     */
    public long getCreationTime() {
        return this.creationTime;
    }

    /**
     * Set the value of leaseSeconds
     *
     * @param newleaseSeconds new value of leaseSeconds
     */
    public void setLeaseSeconds(long newleaseSeconds) {
        this.leaseSeconds = newleaseSeconds;
    }

    /**
     * Get the value of leaseSeconds
     *
     * @return the value of leaseSeconds
     */
    public long getLeaseSeconds() {
        return this.leaseSeconds;
    }

    /**
     * Set the value of secret
     *
     * @param newsecret new value of secret
     */
    public void setSecret(String newsecret) {
        this.secret = newsecret;
    }

    /**
     * Get the value of secret
     *
     * @return the value of secret
     */
    public String getSecret() {
        return this.secret;
    }

    /**
     * Set the value of topic
     *
     * @param newtopic new value of topic
     */
    public void setTopic(String newtopic) {
        this.topic = newtopic;
    }

    /**
     * Get the value of topic
     *
     * @return the value of topic
     */
    public String getTopic() {
        return this.topic;
    }

    /**
     * Set the value of verify
     *
     * @param newverify new value of verify
     */
    public void setVerify(String newverify) {
        this.verify = newverify;
    }

    /**
     * Get the value of verify
     *
     * @return the value of verify
     */
    public String getVerify() {
        return this.verify;
    }

    /**
     * Set the value of vertifyToken
     *
     * @param newvertifyToken new value of vertifyToken
     */
    public void setVertifyToken(String newvertifyToken) {
        this.vertifyToken = newvertifyToken;
    }

    /**
     * Get the value of vertifyToken
     *
     * @return the value of vertifyToken
     */
    public String getVertifyToken() {
        return this.vertifyToken;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Subscriber)) {
            return false;
        }

        final Subscriber other = (Subscriber) obj;

        if ((this.callback == null) ? (other.callback != null) : (!this.callback.equals(other.callback))) {
            return false;
        }

        if ((this.secret == null) ? (other.secret != null) : (!this.secret.equals(other.secret))) {
            return false;
        }

        if ((this.topic == null) ? (other.topic != null) : (!this.topic.equals(other.topic))) {
            return false;
        }

        if ((this.verify == null) ? (other.verify != null) : (!this.verify.equals(other.verify))) {
            return false;
        }

        if ((this.vertifyToken == null) ? (other.vertifyToken != null) : (!this.vertifyToken.equals(other.vertifyToken))) {
            return false;
        }

        if (this.creationTime != other.creationTime) {
            return false;
        }

        if (this.leaseSeconds != other.leaseSeconds) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = (67 * hash) + ((this.callback != null) ? this.callback.hashCode() : 0);
        hash = (67 * hash) + ((this.secret != null) ? this.secret.hashCode() : 0);
        hash = (67 * hash) + ((this.topic != null) ? this.topic.hashCode() : 0);
        hash = (67 * hash) + ((this.verify != null) ? this.verify.hashCode() : 0);
        hash = (67 * hash) + ((this.vertifyToken != null) ? this.vertifyToken.hashCode() : 0);
        hash = (67 * hash) + (int) (this.creationTime ^ (this.creationTime >>> 32));
        hash = (67 * hash) + (int) (this.leaseSeconds ^ (this.leaseSeconds >>> 32));

        return hash;
    }
}
