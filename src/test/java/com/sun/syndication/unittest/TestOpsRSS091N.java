package com.sun.syndication.unittest;

/**
 *
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class TestOpsRSS091N extends FeedOpsTest {

    public static void main(String[] args) throws Exception {
        FeedOpsTest test = new TestOpsRSS091N();
        test.testWireFeedSyndFeedConversion();
    }

    public TestOpsRSS091N() {
        super("rss_0.91N");
    }

}
