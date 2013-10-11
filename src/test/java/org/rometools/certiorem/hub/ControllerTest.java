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

package org.rometools.certiorem.hub;

import java.util.logging.Logger;
import org.rometools.certiorem.HttpStatusCodeException;
import org.rometools.fetcher.FeedFetcher;
import org.rometools.fetcher.impl.HashMapFeedInfoCache;
import org.rometools.fetcher.impl.HttpURLFeedFetcher;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.rometools.certiorem.hub.data.HubDAO;
import org.rometools.certiorem.hub.data.ram.InMemoryHubDAO;

/**
 *
 * @author robert.cooper
 */
public class ControllerTest {
    public ControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of subscribe method, of class Hub.
     */
    @Test
    public void testSubscribe() {
        Logger.getLogger(ControllerTest.class.getName()).info("subscribe");

        String callback = "http://localhost/doNothing";
        String topic = "http://feeds.feedburner.com/screaming-penguin";
        long lease_seconds = -1;
        String secret = null;
        String verify_token = "MyVoiceIsMyPassport";
        HubDAO dao = new InMemoryHubDAO();
        Notifier notifier = null;
        FeedFetcher fetcher = new HttpURLFeedFetcher(HashMapFeedInfoCache.getInstance());
        Hub instance = new Hub(dao, new AlwaysVerifier(), notifier, fetcher);

        Boolean result = instance.subscribe(callback, topic, "sync", lease_seconds, secret, verify_token);
        assertEquals(true, result);

        instance = new Hub(dao, new NeverVerifier(), notifier, fetcher);
        result = instance.subscribe(callback, topic, "sync", lease_seconds, secret, verify_token);
        assertEquals(false, result);

        result = instance.subscribe(callback, topic, "async", lease_seconds, secret, verify_token);
        assertEquals(null, result);

        // Test internal assertions
        try {
            instance.subscribe(null, topic, "async", lease_seconds, secret, verify_token);
            fail();
        } catch (HttpStatusCodeException e) {
            assertEquals(400, e.getStatus());
            Logger.getLogger(ControllerTest.class.getName()).info(e.getMessage());
        }

        try {
            instance.subscribe(callback, null, "async", lease_seconds, secret, verify_token);
            fail();
        } catch (HttpStatusCodeException e) {
            assertEquals(400, e.getStatus());
            Logger.getLogger(ControllerTest.class.getName()).info(e.getMessage());
        }

        try {
            instance.subscribe(callback, topic, "foo", lease_seconds, secret, verify_token);
            fail();
        } catch (HttpStatusCodeException e) {
            assertEquals(400, e.getStatus());
            Logger.getLogger(ControllerTest.class.getName()).info(e.getMessage());
        }

        // test general exception
        instance = new Hub(dao, new ExceptionVerifier(), notifier, fetcher);

        try {
            result = instance.subscribe(callback, topic, "sync", lease_seconds, secret, verify_token);
            fail();
        } catch (HttpStatusCodeException e) {
            assertEquals(500, e.getStatus());
        }
    }
}
