package com.rometools.modules.commentapi;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import com.rometools.modules.AbstractTestCase;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import junit.framework.TestSuite;

public class CommentAPIParserTest extends AbstractTestCase {

    public CommentAPIParserTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() {
    }

    @Override
    protected void tearDown() {
    }

    public static junit.framework.Test suite() {
        return new TestSuite(CommentAPIParserTest.class);
    }

    public void testParseRss() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();
        final SyndFeed feed = input.build(new XmlReader(new File(getTestFile("commentapi/rss.xml"))));
        List<SyndEntry> entries = feed.getEntries();
        int i = 0;
        for (Iterator<SyndEntry> it = entries.iterator(); it.hasNext(); ++i) {
            final SyndEntry entry = it.next();
            final CommentAPI entryInfo = (CommentAPI) entry.getModule(CommentAPI.URI);
            assertNotNull(entryInfo);
            assertEquals(String.format("http://www.example.com/feed/rss/5432%d/comments", i), entryInfo.getComment());
            assertEquals(String.format("http://www.example.com/feed/rss/5432%d/commentsRss", i), entryInfo.getCommentRss());
        }
    }

}
