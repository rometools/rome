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
public class SubscriptionSummary implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String host;
    private boolean lastPublishSuccessful = true;
    private int subscribers = 0;

    /**
     * Set the value of host
     *
     * @param newhost new value of host
     */
    public void setHost(final String newhost) {
        host = newhost;
    }

    /**
     * Get the value of host
     *
     * @return the value of host
     */
    public String getHost() {
        return host;
    }

    /**
     * Set the value of lastPublishSuccessful
     *
     * @param newlastPublishSuccessful new value of lastPublishSuccessful
     */
    public void setLastPublishSuccessful(final boolean newlastPublishSuccessful) {
        lastPublishSuccessful = newlastPublishSuccessful;
    }

    /**
     * Get the value of lastPublishSuccessful
     *
     * @return the value of lastPublishSuccessful
     */
    public boolean isLastPublishSuccessful() {
        return lastPublishSuccessful;
    }

    /**
     * Set the value of subscribers
     *
     * @param newsubscribers new value of subscribers
     */
    public void setSubscribers(final int newsubscribers) {
        subscribers = newsubscribers;
    }

    /**
     * Get the value of subscribers
     *
     * @return the value of subscribers
     */
    public int getSubscribers() {
        return subscribers;
    }
}
