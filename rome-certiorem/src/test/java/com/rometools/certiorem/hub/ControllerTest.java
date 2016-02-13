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

package com.rometools.certiorem.hub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.certiorem.HttpStatusCodeException;
import com.rometools.certiorem.hub.Hub;
import com.rometools.certiorem.hub.Notifier;
import com.rometools.certiorem.hub.data.HubDAO;
import com.rometools.certiorem.hub.data.ram.InMemoryHubDAO;
import com.rometools.fetcher.FeedFetcher;
import com.rometools.fetcher.impl.HashMapFeedInfoCache;
import com.rometools.fetcher.impl.HttpURLFeedFetcher;

/**
 *
 * @author robert.cooper
 */
public class ControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerTest.class);

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

        LOG.info("subscribe");

        final String callback = "http://localhost/doNothing";
        final String topic = "http://feeds.feedburner.com/screaming-penguin";
        final long lease_seconds = -1;
        final String secret = null;
        final String verify_token = "MyVoiceIsMyPassport";
        final HubDAO dao = new InMemoryHubDAO();
        final Notifier notifier = null;
        final FeedFetcher fetcher = new HttpURLFeedFetcher(HashMapFeedInfoCache.getInstance());
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
        } catch (final HttpStatusCodeException e) {
            assertEquals(400, e.getStatus());
            LOG.info(e.getMessage());
        }

        try {
            instance.subscribe(callback, null, "async", lease_seconds, secret, verify_token);
            fail();
        } catch (final HttpStatusCodeException e) {
            assertEquals(400, e.getStatus());
            LOG.info(e.getMessage());
        }

        try {
            instance.subscribe(callback, topic, "foo", lease_seconds, secret, verify_token);
            fail();
        } catch (final HttpStatusCodeException e) {
            assertEquals(400, e.getStatus());
            LOG.info(e.getMessage());
        }

        // test general exception
        instance = new Hub(dao, new ExceptionVerifier(), notifier, fetcher);

        try {
            result = instance.subscribe(callback, topic, "sync", lease_seconds, secret, verify_token);
            fail();
        } catch (final HttpStatusCodeException e) {
            assertEquals(500, e.getStatus());
        }
    }
}
