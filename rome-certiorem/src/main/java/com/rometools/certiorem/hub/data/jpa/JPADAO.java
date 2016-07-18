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

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.rometools.certiorem.hub.data.HubDAO;
import com.rometools.certiorem.hub.data.Subscriber;
import com.rometools.certiorem.hub.data.SubscriptionSummary;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public class JPADAO implements HubDAO {

    private final EntityManagerFactory factory;
    private final boolean purgeExpired;

    public JPADAO(final EntityManagerFactory factory, final boolean purgeExpired) {
        this.factory = factory;
        this.purgeExpired = purgeExpired;
    }

    @Override
    public List<? extends Subscriber> subscribersForTopic(final String topic) {
        final LinkedList<JPASubscriber> result = new LinkedList<JPASubscriber>();
        final EntityManager em = factory.createEntityManager();
        final EntityTransaction tx = em.getTransaction();
        tx.begin();

        final Query query = em.createNamedQuery("Subcriber.forTopic");
        query.setParameter("topic", topic);

        try {
            @SuppressWarnings("unchecked")
            final List<JPASubscriber> subscribers = query.getResultList();
            for (final JPASubscriber subscriber : subscribers) {
                if (subscriber.getLeaseSeconds() == -1) {
                    result.add(subscriber);
                    continue;
                }

                if (subscriber.getSubscribedAt().getTime() < System.currentTimeMillis() - 1000 * subscriber.getLeaseSeconds()) {
                    subscriber.setExpired(true);
                } else {
                    result.add(subscriber);
                }

                if (subscriber.isExpired() && purgeExpired) {
                    em.remove(subscriber);
                }
            }
        } catch (final NoResultException e) {
            tx.rollback();
            em.close();

            return result;
        }

        if (!tx.getRollbackOnly()) {
            tx.commit();
        } else {
            tx.rollback();
        }

        em.close();

        return result;
    }

    @Override
    public Subscriber addSubscriber(final Subscriber subscriber) {
        // FIXME assert should not be used for validation because it can be disabled
        assert subscriber != null : "Attempt to store a null subscriber";
        final EntityManager em = factory.createEntityManager();
        final EntityTransaction tx = em.getTransaction();
        tx.begin();
        final JPASubscriber data = new JPASubscriber();
        data.copyFrom(subscriber);
        data.setId(UUID.randomUUID().toString());
        em.persist(data);
        tx.commit();
        em.close();
        return data;
    }

    @Override
    public Subscriber findSubscriber(final String topic, final String callbackUrl) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void handleSummary(final String topic, final SubscriptionSummary summary) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<? extends SubscriptionSummary> summariesForTopic(final String topic) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeSubscriber(final String topic, final String callback) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
