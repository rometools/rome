package com.sun.syndication.unittest;

import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.io.WireFeedOutput;
import com.sun.syndication.io.WireFeedInput;
import junit.framework.TestCase;

import java.io.StringWriter;
import java.io.StringReader;

public class TestAtomContent extends TestCase {

    private Feed createFeed() {
        Feed feed = new Feed();
        Content content = new Content();
        content.setType("application/xml");
        content.setValue("<test>Hello Hello</test>");
        feed.setTitleEx(content);
        feed.setFeedType("atom_1.0");
        return feed;
    }

    public void testReadWrite() throws Exception {
        Feed feed = createFeed();
        StringWriter sw = new StringWriter();
        WireFeedOutput output = new WireFeedOutput();
        output.output(feed, sw);
        sw.close();
        StringReader reader = new StringReader(sw.toString());
        WireFeedInput input = new WireFeedInput();
        feed = (Feed) input.build(reader);
        reader.close();
        assertEquals("<test>Hello Hello</test>", feed.getTitleEx().getValue().trim());
    }

    public void testXML() throws Exception {
        Feed feed = createFeed();
        StringWriter sw = new StringWriter();
        WireFeedOutput output = new WireFeedOutput();
        output.output(feed, sw);
        sw.close();
        assertTrue(sw.toString().contains("<test xmlns=\"\">Hello Hello</test>"));
    }

}