/*
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
package com.rometools.rome.unittest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;

public abstract class FeedOpsTest extends FeedTest {

    protected FeedOpsTest(final String feedType) {
        super(feedType + ".xml");
    }
    
    protected FeedOpsTest(final String feedType, boolean allowDoctypes) {
        super(feedType + ".xml", allowDoctypes);
    }

    // 1.2a
    public void testWireFeedEquals() throws Exception {
        final WireFeed feed1 = getCachedWireFeed();
        final WireFeed feed2 = getWireFeed();
        assertTrue(feed1.equals(feed2));
    }

    // 1.2b
    public void testWireFeedNotEqual() throws Exception {
        final WireFeed feed1 = getCachedWireFeed();
        final WireFeed feed2 = getWireFeed();
        feed2.setFeedType("dummy");
        assertFalse(feed1.equals(feed2));
    }

    // 1.3
    public void testWireFeedCloning() throws Exception {
        final WireFeed feed1 = getCachedWireFeed();
        final WireFeed feed2 = (WireFeed) feed1.clone();
        ;
        assertTrue(feed1.equals(feed2));
    }

    // 1.4
    public void testWireFeedSerialization() throws Exception {
        final WireFeed feed1 = getCachedWireFeed();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(feed1);
        oos.close();

        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(bais);
        final WireFeed feed2 = (WireFeed) ois.readObject();
        ois.close();

        assertTrue(feed1.equals(feed2));
    }

    // 1.6
    public void testWireFeedSyndFeedConversion() throws Exception {
        final SyndFeed sFeed1 = this.getCachedSyndFeed();
        final WireFeed wFeed1 = sFeed1.createWireFeed();
        final SyndFeed sFeed2 = new SyndFeedImpl(wFeed1);

        assertTrue(sFeed1.equals(sFeed2));
    }

    // 1.7a
    public void testSyndFeedEquals() throws Exception {
        final SyndFeed feed1 = this.getCachedSyndFeed();
        final SyndFeed feed2 = getSyndFeed(false);
        assertTrue(feed1.equals(feed2));
    }

    // 1.7b
    public void testSyndFeedNotEqual() throws Exception {
        final SyndFeed feed1 = this.getCachedSyndFeed();
        final SyndFeed feed2 = getSyndFeed(false);
        feed2.setFeedType("dummy");
        assertFalse(feed1.equals(feed2));
    }

    // 1.8
    public void testSyndFeedCloning() throws Exception {
        final SyndFeed feed1 = this.getCachedSyndFeed();
        final SyndFeed feed2 = (SyndFeed) feed1.clone();
        ;
        assertTrue(feed1.equals(feed2));
    }

    // 1.9
    public void testSyndFeedSerialization() throws Exception {
        final SyndFeed feed1 = this.getCachedSyndFeed();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(feed1);
        oos.close();

        final ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(bais);
        final SyndFeed feed2 = (SyndFeed) ois.readObject();
        ois.close();

        assertTrue(feed1.equals(feed2));
    }

}
