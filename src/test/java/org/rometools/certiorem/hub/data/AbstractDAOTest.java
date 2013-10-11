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

    protected abstract HubDAO get();

    @Test
    public void testSubscribe() {
        HubDAO instance = get();
        Logger.getLogger(AbstractDAOTest.class.getName()).log(Level.INFO, "{0} testSubscribe", instance.getClass().getName());
        Subscriber subscriber = new Subscriber();
        subscriber.setCallback("http://localhost:9797/noop");
        subscriber.setTopic("http://feeds.feedburner.com/screaming-penguin");
        subscriber.setLeaseSeconds(-1);
        subscriber.setVerify("VerifyMe");

        Subscriber result = instance.addSubscriber(subscriber);

        assert result.equals(subscriber) : "Subscriber not equal.";

        List<Subscriber> subscribers = (List<Subscriber>) instance.subscribersForTopic(subscriber.getTopic());

        assert subscribers.contains(result) : "Subscriber not in result.";
    }

    @Test
    public void testLeaseExpire() throws InterruptedException {
        HubDAO instance = get();
        Logger.getLogger(AbstractDAOTest.class.getName()).log(Level.INFO, "{0} testLeaseExpire", instance.getClass().getName());
        Subscriber subscriber = new Subscriber();
        subscriber.setCallback("http://localhost:9797/noop");
        subscriber.setTopic("http://feeds.feedburner.com/screaming-penguin");
        subscriber.setLeaseSeconds(1);
        subscriber.setVerify("VerifyMe");

        Subscriber result = instance.addSubscriber(subscriber);

        assert subscriber.equals(result) : "Subscriber not equal.";
        //quick test for store.
        List<Subscriber> subscribers = (List<Subscriber>) instance.subscribersForTopic(subscriber.getTopic());
        assert subscribers.contains(result) : "Subscriber not in result.";
        //sleep past expiration
        Thread.sleep(1100);
        subscribers = (List<Subscriber>) instance.subscribersForTopic(subscriber.getTopic());
        assert !subscribers.contains(result) : "Subscriber should have expired";
    }

    @Test
    public void testUnsubscribe() throws InterruptedException {
        HubDAO instance = get();
        Logger.getLogger(AbstractDAOTest.class.getName()).log(Level.INFO, "{0} testUnsubscribe", instance.getClass().getName());
        Subscriber subscriber = new Subscriber();
        subscriber.setCallback("http://localhost:9797/noop");
        subscriber.setTopic("http://feeds.feedburner.com/screaming-penguin");
        subscriber.setLeaseSeconds(1);
        subscriber.setVerify("VerifyMe");

        Subscriber result = instance.addSubscriber(subscriber);
        // TODO

    }
}
