/*
 * Copyright 2004 Sun Microsystems, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.rometools.fetcher;

import java.net.URL;

import junit.framework.TestCase;

import org.mortbay.http.BasicAuthenticator;
import org.mortbay.http.HashUserRealm;
import org.mortbay.http.HttpContext;
import org.mortbay.http.HttpServer;
import org.mortbay.http.SecurityConstraint;
import org.mortbay.http.SocketListener;
import org.mortbay.http.UserRealm;
import org.mortbay.http.handler.SecurityHandler;
import org.mortbay.jetty.servlet.ServletHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.fetcher.impl.FeedFetcherCache;
import com.rometools.fetcher.impl.HashMapFeedInfoCache;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

/**
 * @author nl
 */
public abstract class AbstractJettyTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractJettyTest.class);

    private HttpServer server;
    private final int testPort = 8283;

    /**
     * @param s
     */
    public AbstractJettyTest(final String s) {
        super(s);
    }

    protected HttpServer getServer() {
        return server;
    }

    protected abstract FeedFetcher getFeedFetcher();

    protected abstract FeedFetcher getFeedFetcher(FeedFetcherCache cache);

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        setupServer();

        final HttpContext context = createContext();

        final ServletHandler servlets = createServletHandler();
        context.addHandler(servlets);

        server.addContext(context);

        server.start();
    }

    /**
     * @throws InterruptedException
     */
    private void setupServer() throws InterruptedException {

        // Create the server
        if (server != null) {
            server.stop();
            server = null;
        }
        server = new HttpServer();

        // Create a port listener
        final SocketListener listener = new SocketListener();
        listener.setPort(testPort);
        server.addListener(listener);
    }

    /**
     * @return
     */
    private ServletHandler createServletHandler() {
        final ServletHandler servlets = new ServletHandler();
        servlets.addServlet("FetcherTestServlet", FetcherTestServlet.SERVLET_MAPPING, "com.rometools.fetcher.FetcherTestServlet");
        servlets.addServlet("FetcherTestServlet", FetcherTestServlet.SERVLET_MAPPING2, "com.rometools.fetcher.FetcherTestServlet");
        return servlets;
    }

    /**
     * @return
     */
    private HttpContext createContext() {
        final HttpContext context = new HttpContext();
        context.setContextPath("/rome/*");
        return context;
    }

    /**
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        if (server != null) {
            server.stop();
            server.destroy();
            server = null;
        }
    }

    class FetcherEventListenerImpl implements FetcherListener {
        boolean polled = false;
        boolean retrieved = false;
        boolean unchanged = false;

        public void reset() {
            polled = false;
            retrieved = false;
            unchanged = false;
        }

        /**
         * @see com.rometools.rome.fetcher.FetcherListener#fetcherEvent(com.rometools.rome.fetcher.FetcherEvent)
         */
        @Override
        public void fetcherEvent(final FetcherEvent event) {
            final String eventType = event.getEventType();
            if (FetcherEvent.EVENT_TYPE_FEED_POLLED.equals(eventType)) {
                LOG.debug("\tEVENT: Feed Polled. URL = " + event.getUrlString());
                polled = true;
            } else if (FetcherEvent.EVENT_TYPE_FEED_RETRIEVED.equals(eventType)) {
                LOG.debug("\tEVENT: Feed Retrieved. URL = " + event.getUrlString());
                retrieved = true;
            } else if (FetcherEvent.EVENT_TYPE_FEED_UNCHANGED.equals(eventType)) {
                LOG.debug("\tEVENT: Feed Unchanged. URL = " + event.getUrlString());
                unchanged = true;
            }
        }
    }

    public void testRetrieveFeed() {
        final FeedFetcher feedFetcher = getFeedFetcher();
        try {
            final SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort + "/rome/FetcherTestServlet/"));
            assertNotNull(feed);
            assertEquals("atom_1.0.feed.title", feed.getTitle());
        } catch (final Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    public void testBasicAuthentication() {
        try {
            setupServer();


            final URL url = this.getClass().getClassLoader().getResource("testuser.properties");
            final UserRealm userRealm = new HashUserRealm("test", url.toString());

            final BasicAuthenticator basicauthenticator = new BasicAuthenticator();

            final SecurityHandler securityHandler = new SecurityHandler();

            final SecurityConstraint securityConstraint = new SecurityConstraint();
            securityConstraint.setName("test");
            securityConstraint.addRole("*");
            securityConstraint.setAuthenticate(true);

            final ServletHandler servletHandler = createServletHandler();

            final HttpContext context = createContext();
            context.setRealm(userRealm);
            context.setAuthenticator(basicauthenticator);
            context.addHandler(securityHandler);
            context.addSecurityConstraint("/", securityConstraint);
            context.addHandler(servletHandler);

            server.addContext(context);
            server.start();

            final FeedFetcher feedFetcher = getAuthenticatedFeedFetcher();
            final SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort + "/rome/FetcherTestServlet/"));
            assertNotNull(feed);
            assertEquals("atom_1.0.feed.title", feed.getTitle());

        } catch (final Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    public abstract FeedFetcher getAuthenticatedFeedFetcher();

    /**
     * Test getting a feed via a http 301 redirect
     *
     */
    public void testRetrieveRedirectedFeed() {
        final FeedFetcher feedFetcher = getFeedFetcher();
        try {
            final SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort + "/rome/FetcherTestServlet?redirect=TRUE"));
            assertNotNull(feed);
            assertEquals("atom_1.0.feed.title", feed.getTitle());
        } catch (final Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test error handling
     *
     */
    public void testErrorHandling() {
        final FeedFetcher feedFetcher = getFeedFetcher();
        try {
            feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort + "/rome/FetcherTestServlet?error=404"));
            fail("4xx error handling did not work correctly");
        } catch (final FetcherException e) {
            // expect this exception
            assertEquals(404, e.getResponseCode());
        } catch (final Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        try {
            feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort + "/rome/FetcherTestServlet?error=500"));
            fail("5xx error handling did not work correctly");
        } catch (final FetcherException e) {
            // expect this exception
            assertEquals(500, e.getResponseCode());
        } catch (final Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    public void testUserAgent() {
        final FeedFetcher feedFetcher = getFeedFetcher();
        // LOG.debug(feedFetcher.getUserAgent());
        // LOG.debug(System.getProperty("rome.fetcher.version", "UNKNOWN"));
        assertEquals("Rome Client (http://tinyurl.com/64t5n) Ver: " + System.getProperty("rome.fetcher.version", "UNKNOWN"), feedFetcher.getUserAgent());
    }

    /**
     * Test events fired when there is no cache in use
     *
     */
    public void testFetchEvents() {
        final FeedFetcher feedFetcher = getFeedFetcher();
        final FetcherEventListenerImpl listener = new FetcherEventListenerImpl();
        feedFetcher.addFetcherEventListener(listener);
        try {
            SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort + "/rome/FetcherTestServlet/"));
            assertNotNull(feed);
            assertTrue(listener.polled);
            assertTrue(listener.retrieved);
            assertFalse(listener.unchanged);
            listener.reset();

            // since there is no cache, the events fired should be exactly the same if
            // we re-retrieve the feed
            feed = feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort + "/rome/FetcherTestServlet/"));
            assertNotNull(feed);
            assertTrue(listener.polled);
            assertTrue(listener.retrieved);
            assertFalse(listener.unchanged);
            listener.reset();
        } catch (final Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test events fired when there is a cache in use
     *
     */
    public void testFetchEventsWithCache() {
        final FeedFetcherCache feedInfoCache = new HashMapFeedInfoCache();
        final FeedFetcher feedFetcher = getFeedFetcher(feedInfoCache);
        final FetcherEventListenerImpl listener = new FetcherEventListenerImpl();
        feedFetcher.addFetcherEventListener(listener);
        try {
            SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort + "/rome/FetcherTestServlet/"));
            assertNotNull(feed);
            assertTrue(listener.polled);
            assertTrue(listener.retrieved);
            assertFalse(listener.unchanged);
            listener.reset();

            // Since the feed is cached, the second request should not
            // actually retrieve the feed
            feed = feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort + "/rome/FetcherTestServlet/"));
            assertNotNull(feed);
            assertTrue(listener.polled);
            assertFalse(listener.retrieved);
            assertTrue(listener.unchanged);
            listener.reset();

            // now simulate getting the feed after it has changed
            feed = feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort + "/rome/FetcherTestServlet?refreshfeed=TRUE"));
            assertNotNull(feed);
            assertTrue(listener.polled);
            assertTrue(listener.retrieved);
            assertFalse(listener.unchanged);
            listener.reset();
        } catch (final Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    /**
     * Test handling of GZipped feed
     *
     */
    public void testGZippedFeed() {
        final FeedFetcher feedFetcher = getFeedFetcher();
        try {
            final SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort + "/rome/FetcherTestServlet?gzipfeed=TRUE"));
            assertNotNull(feed);
            assertEquals("atom_1.0.feed.title", feed.getTitle());
        } catch (final Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    public void testPreserveWireFeed() throws Exception {
        final FeedFetcher feedFetcher = getFeedFetcher();

        // first check we the WireFeed is not preserved by default
        SyndFeed feed = feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort + "/rome/FetcherTestServlet/"));
        assertNotNull(feed);
        assertEquals("atom_1.0.feed.title", feed.getTitle());
        assertNull(feed.originalWireFeed());

        SyndEntry syndEntry = feed.getEntries().get(0);
        assertNotNull(syndEntry);
        assertNull(syndEntry.getWireEntry());

        // now turn on WireFeed preservation
        feedFetcher.setPreserveWireFeed(true);
        try {
            feed = feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort + "/rome/FetcherTestServlet/"));
            assertNotNull(feed);
            assertEquals("atom_1.0.feed.title", feed.getTitle());
            assertNotNull(feed.originalWireFeed());

            syndEntry = feed.getEntries().get(0);
            assertNotNull(syndEntry);
            assertNotNull(syndEntry.getWireEntry());

            final Entry entry = (Entry) syndEntry.getWireEntry();
            assertEquals("atom_1.0.feed.entry[0].rights", entry.getRights());

        } finally {
            feedFetcher.setPreserveWireFeed(false); // reset
        }

    }

    public void testDeltaEncoding() {
        final FeedFetcherCache feedInfoCache = new HashMapFeedInfoCache();
        final FeedFetcher feedFetcher = getFeedFetcher(feedInfoCache);
        try {
            feedFetcher.setUsingDeltaEncoding(true);

            // first retrieval should just grab the default feed
            final SyndFeed feed1 = feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort
                    + "/rome/FetcherTestServlet?deltaencode=TRUE&refreshfeed=TRUE"));
            assertNotNull(feed1);
            assertEquals("atom_1.0.feed.title", feed1.getTitle());
            assertEquals(2, feed1.getEntries().size());
            SyndEntry entry1 = feed1.getEntries().get(0);
            assertEquals("atom_1.0.feed.entry[0].title", entry1.getTitle());

            // second retrieval should get only the new item
            /*
             * This is breaking with Rome 0.5 ??
             */
            final SyndFeed feed2 = feedFetcher.retrieveFeed(new URL("http://localhost:" + testPort
                    + "/rome/FetcherTestServlet?deltaencode=TRUE&refreshfeed=TRUE"));
            assertNotNull(feed2);
            assertEquals(FetcherTestServlet.DELTA_FEED_TITLE, feed2.getTitle());
            assertEquals(3, feed2.getEntries().size());
            entry1 = feed2.getEntries().get(0);
            assertEquals(FetcherTestServlet.DELTA_FEED_ENTRY_TITLE, entry1.getTitle());

            final SyndEntry entry2 = feed2.getEntries().get(1);
            assertEquals("atom_1.0.feed.entry[0].title", entry2.getTitle());

        } catch (final Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

}
