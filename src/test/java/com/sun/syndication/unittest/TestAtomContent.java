package com.sun.syndication.unittest;

import java.io.StringReader;
import java.io.StringWriter;

import junit.framework.TestCase;

import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.io.WireFeedInput;
import com.sun.syndication.io.WireFeedOutput;

public class TestAtomContent extends TestCase {

    private Feed createFeed() {
        final Feed feed = new Feed();
        final Content content = new Content();
        content.setType("application/xml");
        content.setValue("<test>Hello Hello</test>");
        feed.setTitleEx(content);
        feed.setFeedType("atom_1.0");
        return feed;
    }

    public void testReadWrite() throws Exception {
        Feed feed = createFeed();
        final StringWriter sw = new StringWriter();
        final WireFeedOutput output = new WireFeedOutput();
        output.output(feed, sw);
        sw.close();
        final StringReader reader = new StringReader(sw.toString());
        final WireFeedInput input = new WireFeedInput();
        feed = (Feed) input.build(reader);
        reader.close();
        assertEquals("<test>Hello Hello</test>", feed.getTitleEx().getValue().trim());
    }

    public void testXML() throws Exception {
        final Feed feed = createFeed();
        final StringWriter sw = new StringWriter();
        final WireFeedOutput output = new WireFeedOutput();
        output.output(feed, sw);
        sw.close();
        assertTrue(sw.toString().contains("<test xmlns=\"\">Hello Hello</test>"));
    }

}