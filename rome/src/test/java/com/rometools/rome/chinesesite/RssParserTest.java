package com.rometools.rome.chinesesite;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.junit.Test;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RssParserTest {
    private final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void parser() throws Exception {
        String[] urls = {
                "https://www.zhihu.com/rss",
                "http://36kr.com/feed",
                "http://news.qq.com/newsgn/rss_newsgn.xml",
                "http://rss.news.sohu.com/rss/pfocus.xml",
                "http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss",
                "http://feed.williamlong.info/",
                "https://www.v2ex.com/index.xml",
                "http://blog.csdn.net/ys743276112/rss/list",
        };
        for (String url : urls) {
            SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
            System.out.printf("%s => %s%n", feed.getTitle(), formatDate(feed.getPublishedDate()));
            for (SyndEntry it : feed.getEntries()) {
                System.out.printf("%s => %s%n", it.getTitle().trim(), formatDate(it.getPublishedDate()));
            }
        }
    }

    private static String formatDate(Date date) {
        if (date == null) return "null";
        return df.format(date);
    }
}
