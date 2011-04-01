package com.sun.syndication.unittest;

import junit.framework.TestCase;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.WireFeedInput;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.InputStream;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;

/**
 * @author pat, tucu
 *
 */
public abstract class FeedTest extends TestCase {
    private String _feedFileName;
    private Document _jDomDoc  = null;
    private WireFeed _wireFeed = null;
    private SyndFeed _syndFeed = null;
    
    private boolean preservingWireFeed = false;

    protected FeedTest(String feedFileName) {
        _feedFileName = feedFileName;
    }

    protected String getFeedFileName() {
        return _feedFileName;
    }

    protected Reader getFeedReader() throws Exception {
      InputStream resource = Thread.currentThread().
                                          getContextClassLoader().getResourceAsStream(getFeedFileName());
      assertNotNull("Could not find resource " + getFeedFileName(), resource);
      return new InputStreamReader(resource);
    }

    protected Document getJDomDoc() throws Exception {
        SAXBuilder saxBuilder = new SAXBuilder(false);
        return saxBuilder.build(getFeedReader());
    }

    protected WireFeed getWireFeed() throws Exception {
        WireFeedInput in = new WireFeedInput();
        return in.build(getFeedReader());
    }

    protected SyndFeed getSyndFeed(boolean preserveWireFeed) throws Exception {
        SyndFeedInput in = new SyndFeedInput();
        in.setPreserveWireFeed(preserveWireFeed);
        return in.build(getFeedReader());
    }

    protected Document getCachedJDomDoc() throws Exception {
        if (_jDomDoc==null) {
            _jDomDoc = getJDomDoc();
        }
        return _jDomDoc;
    }

    protected WireFeed getCachedWireFeed() throws Exception {
        if (_wireFeed==null) {
            _wireFeed = getWireFeed();
        }
        return _wireFeed;
    }

    protected SyndFeed getCachedSyndFeed(boolean preserveWireFeed) throws Exception {
    
    	if (_syndFeed==null || preservingWireFeed != preserveWireFeed) {
            _syndFeed = getSyndFeed(preserveWireFeed);
            preservingWireFeed = preserveWireFeed;
        }
        return _syndFeed;    	
    	
    }
    
    protected SyndFeed getCachedSyndFeed() throws Exception {
    	return getCachedSyndFeed(false);
    }

}
