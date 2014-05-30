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

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.certiorem.hub.data.HubDAO;
import com.rometools.certiorem.hub.data.Subscriber;

/**
 *
 * @author robert.cooper
 */
public abstract class AbstractDAOTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDAOTest.class);

    protected abstract HubDAO get();

    @Test
    public void testSubscribe() {
        final HubDAO instance = get();
        LOG.info("{} testSubscribe", instance.getClass().getName());
        final Subscriber subscriber = new Subscriber();
        subscriber.setCallback("http://localhost:9797/noop");
        subscriber.setTopic("http://feeds.feedburner.com/screaming-penguin");
        subscriber.setLeaseSeconds(-1);
        subscriber.setVerify("VerifyMe");

        final Subscriber result = instance.addSubscriber(subscriber);

        Assert.assertTrue("Subscriber not equal", result.equals(subscriber));

        final List<? extends Subscriber> subscribers = instance.subscribersForTopic(subscriber.getTopic());

        Assert.assertTrue("Subscriber not in result", subscribers.contains(result));

    }

    @Test
    public void testLeaseExpire() throws InterruptedException {
        final HubDAO instance = get();
        LOG.info("{} testLeaseExpire", instance.getClass().getName());
        final Subscriber subscriber = new Subscriber();
        subscriber.setCallback("http://localhost:9797/noop");
        subscriber.setTopic("http://feeds.feedburner.com/screaming-penguin");
        subscriber.setLeaseSeconds(1);
        subscriber.setVerify("VerifyMe");

        final Subscriber result = instance.addSubscriber(subscriber);

        Assert.assertEquals("Subscriber not equal", subscriber, result);
        // quick test for store.
        List<? extends Subscriber> subscribers = instance.subscribersForTopic(subscriber.getTopic());
        Assert.assertTrue("Subscriber not in result", subscribers.contains(result));
        // sleep past expiration
        Thread.sleep(1100);
        subscribers = instance.subscribersForTopic(subscriber.getTopic());
        Assert.assertFalse("Subscriber should have expired", subscribers.contains(result));
    }

    @Test
    public void testUnsubscribe() throws InterruptedException {
        final HubDAO instance = get();
        LOG.info("{} testUnsubscribe", instance.getClass().getName());
        final Subscriber subscriber = new Subscriber();
        subscriber.setCallback("http://localhost:9797/noop");
        subscriber.setTopic("http://feeds.feedburner.com/screaming-penguin");
        subscriber.setLeaseSeconds(1);
        subscriber.setVerify("VerifyMe");

        // TODO
        // final Subscriber result = instance.addSubscriber(subscriber);

    }
}
