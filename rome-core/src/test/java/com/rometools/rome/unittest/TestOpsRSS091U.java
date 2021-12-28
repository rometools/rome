package com.rometools.rome.unittest;

import static org.assertj.core.api.Assertions.assertThat;

public class TestOpsRSS091U extends FeedOpsTest {

    public TestOpsRSS091U() {
        super("rss_0.91U");
    }
    
    @Override
    public void testStylesheet() throws Exception {
        assertThat(this.getCachedSyndFeed().getStyleSheet()).isEqualTo("style.xsl");
        assertThat(this.getCachedWireFeed().getStyleSheet()).isEqualTo("style.xsl");
    }

}
