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

package com.rometools.certiorem.hub.data.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.rometools.certiorem.hub.data.Subscriber;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
@Entity
@NamedQueries({ @NamedQuery(name = "Subcriber.forTopic", query = "SELECT o FROM JPASubscriber o WHERE o.topic = :topic AND o.expired = false ORDER BY o.subscribedAt") })
public class JPASubscriber extends Subscriber implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Date subscribedAt = new Date();
    private String id;
    private boolean expired = false;

    /**
     * Set the value of expired
     *
     * @param newexpired new value of expired
     */
    public void setExpired(final boolean newexpired) {
        expired = newexpired;
    }

    /**
     * Get the value of expired
     *
     * @return the value of expired
     */
    public boolean isExpired() {
        return expired;
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
    @Id
    public String getId() {
        return id;
    }

    /**
     * Set the value of subscribedAt
     *
     * @param newsubscribedAt new value of subscribedAt
     */
    public void setSubscribedAt(final Date newsubscribedAt) {
        subscribedAt = newsubscribedAt;
    }

    /**
     * Get the value of subscribedAt
     *
     * @return the value of subscribedAt
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getSubscribedAt() {
        return subscribedAt;
    }

    public void copyFrom(final Subscriber source) {
        setLeaseSeconds(source.getLeaseSeconds());
        setSecret(source.getSecret());
        setTopic(source.getTopic());
        setVerify(source.getVerify());
        setVertifyToken(source.getVertifyToken());
    }
}
