package com.rometools.rome.unittest;

/**
 *
 * <p>
 *
 * @author Alejandro Abdelnur
 *
 */
public class TestOpsRSS094 extends FeedOpsTest {

    public static void main(final String[] args) throws Exception {
        final FeedOpsTest test = new TestOpsRSS094();
        test.testWireFeedSyndFeedConversion();
    }

    public TestOpsRSS094() {
        super("rss_0.94");
    }

}
