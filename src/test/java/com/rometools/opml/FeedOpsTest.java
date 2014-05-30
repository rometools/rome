package com.rometools.opml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;

/**
 *
 * <p>
 *
 * @author Alejandro Abdelnur
 *
 */
public abstract class FeedOpsTest extends FeedTest {

    protected FeedOpsTest(final String feedType) {
        super(feedType + ".xml");
        new File("target/test-reports").mkdirs();
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
        final SyndFeed sFeed1 = getCachedSyndFeed();
        final WireFeed wFeed1 = sFeed1.createWireFeed();
        final SyndFeed sFeed2 = new SyndFeedImpl(wFeed1);

        assertEquals(sFeed1, sFeed2);
    }

    // 1.7a
    public void testSyndFeedEquals() throws Exception {
        final SyndFeed feed1 = getCachedSyndFeed();
        final SyndFeed feed2 = getSyndFeed();
        assertTrue(feed1.equals(feed2));
    }

    // 1.7b
    public void testSyndFeedNotEqual() throws Exception {
        final SyndFeed feed1 = getCachedSyndFeed();
        final SyndFeed feed2 = getSyndFeed();
        feed2.setFeedType("dummy");
        assertFalse(feed1.equals(feed2));
    }

    // 1.8
    public void testSyndFeedCloning() throws Exception {
        final SyndFeed feed1 = getCachedSyndFeed();
        final SyndFeed feed2 = (SyndFeed) feed1.clone();
        ;
        assertTrue(feed1.equals(feed2));
    }

    // 1.9
    public void testSyndFeedSerialization() throws Exception {
        final SyndFeed feed1 = getCachedSyndFeed();

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
