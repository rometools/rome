package com.sun.syndication.unittest;

/**
 *
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class TestOpsRSS094 extends FeedOpsTest {

    public static void main(String[] args) throws Exception {
        FeedOpsTest test = new TestOpsRSS094();
        test.testWireFeedSyndFeedConversion();
    }

    public TestOpsRSS094() {
        super("rss_0.94");
    }

}
