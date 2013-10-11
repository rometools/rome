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

 
package org.rometools.certiorem.hub.data.jpa;

import org.rometools.certiorem.hub.data.Subscriber;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author robert.cooper
 */
@Entity
@NamedQueries({@NamedQuery(name = "Subcriber.forTopic", query = "SELECT o FROM JPASubscriber o WHERE o.topic = :topic AND o.expired = false ORDER BY o.subscribedAt")
})
public class JPASubscriber extends Subscriber implements Serializable {
    private Date subscribedAt = new Date();
    private String id;
    private boolean expired = false;

    /**
     * Set the value of expired
     *
     * @param newexpired new value of expired
     */
    public void setExpired(boolean newexpired) {
        this.expired = newexpired;
    }

    /**
     * Get the value of expired
     *
     * @return the value of expired
     */
    public boolean isExpired() {
        return this.expired;
    }

    /**
     * Set the value of id
     *
     * @param newid new value of id
     */
    public void setId(String newid) {
        this.id = newid;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    @Id
    public String getId() {
        return this.id;
    }

    /**
     * Set the value of subscribedAt
     *
     * @param newsubscribedAt new value of subscribedAt
     */
    public void setSubscribedAt(Date newsubscribedAt) {
        this.subscribedAt = newsubscribedAt;
    }

    /**
     * Get the value of subscribedAt
     *
     * @return the value of subscribedAt
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getSubscribedAt() {
        return this.subscribedAt;
    }

    public void copyFrom(Subscriber source) {
        this.setLeaseSeconds(source.getLeaseSeconds());
        this.setSecret(source.getSecret());
        this.setTopic(source.getTopic());
        this.setVerify(source.getVerify());
        this.setVertifyToken(source.getVertifyToken());
    }
}
