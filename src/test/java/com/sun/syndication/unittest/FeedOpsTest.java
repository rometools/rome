package com.sun.syndication.unittest;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 *
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public abstract class FeedOpsTest extends FeedTest {

    protected FeedOpsTest(String feedType) {
        super(feedType+".xml");
        System.out.println("Testing "+feedType+".xml");
    }

    //1.2a
    public void testWireFeedEquals() throws Exception {
        WireFeed feed1 = getCachedWireFeed();
        WireFeed feed2 = getWireFeed();
        assertTrue(feed1.equals(feed2));
    }

    //1.2b
    public void testWireFeedNotEqual() throws Exception {
        WireFeed feed1 = getCachedWireFeed();
        WireFeed feed2 = getWireFeed();
        feed2.setFeedType("dummy");
        assertFalse(feed1.equals(feed2));
    }

    //1.3
    public void testWireFeedCloning() throws Exception {
        WireFeed feed1 = getCachedWireFeed();
        WireFeed feed2 = (WireFeed) feed1.clone();;
        assertTrue(feed1.equals(feed2));
    }

    // 1.4
    public void testWireFeedSerialization() throws Exception {
        WireFeed feed1 = getCachedWireFeed();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(feed1);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        WireFeed feed2 = (WireFeed) ois.readObject();
        ois.close();

        assertTrue(feed1.equals(feed2));
    }

    // 1.6
    public void testWireFeedSyndFeedConversion() throws Exception {
        SyndFeed sFeed1 = getCachedSyndFeed();
        WireFeed wFeed1 = sFeed1.createWireFeed();        
        SyndFeed sFeed2 = new SyndFeedImpl(wFeed1);

        assertTrue(sFeed1.equals(sFeed2));
    }

    //1.7a
    public void testSyndFeedEquals() throws Exception {
        SyndFeed feed1 = getCachedSyndFeed();
        SyndFeed feed2 = getSyndFeed(false);
        assertTrue(feed1.equals(feed2));
    }

    //1.7b
    public void testSyndFeedNotEqual() throws Exception {
        SyndFeed feed1 = getCachedSyndFeed();
        SyndFeed feed2 = getSyndFeed(false);
        feed2.setFeedType("dummy");
        assertFalse(feed1.equals(feed2));
    }

    //1.8
    public void testSyndFeedCloning() throws Exception {
        SyndFeed feed1 = getCachedSyndFeed();
        SyndFeed feed2 = (SyndFeed) feed1.clone();;
        assertTrue(feed1.equals(feed2));
    }

    //1.9
    public void testSyndFeedSerialization() throws Exception {
        SyndFeed feed1 = getCachedSyndFeed();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(feed1);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        SyndFeed feed2 = (SyndFeed) ois.readObject();
        ois.close();

        assertTrue(feed1.equals(feed2));
    }

}
