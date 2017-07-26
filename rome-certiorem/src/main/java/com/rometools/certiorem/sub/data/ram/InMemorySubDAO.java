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

package com.rometools.certiorem.sub.data.ram;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.certiorem.sub.data.SubDAO;
import com.rometools.certiorem.sub.data.Subscription;

/**
 *
 * @author robert.cooper
 * @deprecated Certiorem will be removed in Rome 2.
 */
@Deprecated
public class InMemorySubDAO implements SubDAO {

    private static final Logger LOG = LoggerFactory.getLogger(InMemorySubDAO.class);

    private final ConcurrentHashMap<String, Subscription> subscriptions = new ConcurrentHashMap<String, Subscription>();

    @Override
    public Subscription findById(final String id) {
        final Subscription s = subscriptions.get(id);
        if (s == null) {
            return null;
        }
        if (s.getExpirationTime() > 0 && s.getExpirationTime() <= System.currentTimeMillis()) {
            LOG.debug("Subscription {} expired at {}", s.getSourceUrl(), new Date(s.getExpirationTime()));
            subscriptions.remove(id);

            return null;
        }
        return s;
    }

    @Override
    public Subscription addSubscription(final Subscription s) {
        subscriptions.put(s.getId(), s);
        LOG.debug("Stored subscription {} {}", s.getSourceUrl(), s.getId());
        return s;
    }

    @Override
    public Subscription updateSubscription(final Subscription s) {
        subscriptions.put(s.getId(), s);
        return s;
    }

    @Override
    public void removeSubscription(final Subscription s) {
        subscriptions.remove(s.getId());
    }

}
