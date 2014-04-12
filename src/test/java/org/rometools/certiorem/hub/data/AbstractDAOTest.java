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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

/**
 *
 * @author robert.cooper
 */
public abstract class AbstractDAOTest {

    private static final Logger LOGGER = Logger.getLogger(AbstractDAOTest.class.getName());

    protected abstract HubDAO get();

    @Test
    public void testSubscribe() {
        final HubDAO instance = get();
        LOGGER.log(Level.INFO, "{0} testSubscribe", instance.getClass().getName());
        final Subscriber subscriber = new Subscriber();
        subscriber.setCallback("http://localhost:9797/noop");
        subscriber.setTopic("http://feeds.feedburner.com/screaming-penguin");
        subscriber.setLeaseSeconds(-1);
        subscriber.setVerify("VerifyMe");

        final Subscriber result = instance.addSubscriber(subscriber);

        assert result.equals(subscriber) : "Subscriber not equal.";

        final List<? extends Subscriber> subscribers = instance.subscribersForTopic(subscriber.getTopic());

        assert subscribers.contains(result) : "Subscriber not in result.";
    }

    @Test
    public void testLeaseExpire() throws InterruptedException {
        final HubDAO instance = get();
        LOGGER.log(Level.INFO, "{0} testLeaseExpire", instance.getClass().getName());
        final Subscriber subscriber = new Subscriber();
        subscriber.setCallback("http://localhost:9797/noop");
        subscriber.setTopic("http://feeds.feedburner.com/screaming-penguin");
        subscriber.setLeaseSeconds(1);
        subscriber.setVerify("VerifyMe");

        final Subscriber result = instance.addSubscriber(subscriber);

        assert subscriber.equals(result) : "Subscriber not equal.";
        // quick test for store.
        List<? extends Subscriber> subscribers = instance.subscribersForTopic(subscriber.getTopic());
        assert subscribers.contains(result) : "Subscriber not in result.";
        // sleep past expiration
        Thread.sleep(1100);
        subscribers = instance.subscribersForTopic(subscriber.getTopic());
        assert !subscribers.contains(result) : "Subscriber should have expired";
    }

    @Test
    public void testUnsubscribe() throws InterruptedException {
        final HubDAO instance = get();
        LOGGER.log(Level.INFO, "{0} testUnsubscribe", instance.getClass().getName());
        final Subscriber subscriber = new Subscriber();
        subscriber.setCallback("http://localhost:9797/noop");
        subscriber.setTopic("http://feeds.feedburner.com/screaming-penguin");
        subscriber.setLeaseSeconds(1);
        subscriber.setVerify("VerifyMe");

        // TODO
        // final Subscriber result = instance.addSubscriber(subscriber);

    }
}
