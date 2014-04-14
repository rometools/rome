/*
 * MediaModuleTest.java
 * JUnit based test
 *
 * Created on March 29, 2006, 11:49 PM
 */

package org.rometools.feed.module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.rometools.feed.module.mediarss.MediaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;

/**
 *
 * @author cooper
 */
public class MediaModuleTest extends AbstractTestCase {

    private static final Logger LOG = LoggerFactory.getLogger(MediaModuleTest.class);

    public MediaModuleTest(final String testName) {
        super(testName);
    }

    public static Test suite() {
        final TestSuite suite = new TestSuite(MediaModuleTest.class);

        return suite;
    }

    public void testParse() throws Exception {
        final SyndFeedInput input = new SyndFeedInput();
        final File test = new File(super.getTestFile("xml"));
        final File[] files = test.listFiles();
        for (int j = 0; j < files.length; j++) {
            if (!files[j].getName().endsWith(".xml")) {
                continue;
            }
            final SyndFeed feed = input.build(files[j]);
            final List<SyndEntry> entries = feed.getEntries();
            for (int i = 0; i < entries.size(); i++) {
                LOG.debug("{}", entries.get(i).getModule(MediaModule.URI));
            }
            final SyndFeedOutput output = new SyndFeedOutput();
            output.output(feed, new File("target/" + j + "media.xml"));
            final SyndFeed feed2 = input.build(new File("target/" + j + "media.xml"));
            for (int i = 0; i < entries.size(); i++) {
                BufferedWriter b = new BufferedWriter(new FileWriter("target/" + j + "a.txt"));
                b.write("" + entries.get(i).getModule(MediaModule.URI));
                b.close();
                b = new BufferedWriter(new FileWriter("target/" + j + "b.txt"));
                b.write("" + feed2.getEntries().get(i).getModule(MediaModule.URI));
                b.close();
                assertEquals(entries.get(i).getModule(MediaModule.URI), feed2.getEntries().get(i).getModule(MediaModule.URI));
            }
        }
    }

    /**
     * This code represents the original MediaModuleTest main class from openvision.tv
     */
    public void xtestOriginal() throws Exception {
        //
        /*
         * //You can test with blip tv or broadcast machine String rss =
         * "http://blip.tv/?1=1&&skin=rss";// // String rss = "http://openvision.tv/bm/rss.php?i=4";
         * // String rss =
         * "http://api.search.yahoo.com/VideoSearchService/rss/videoSearch.xml?appid=yahoosearchvideorss&query=surfing&adult_ok=0"
         * ; URLConnection uc = new URL(rss).openConnection(); String contentType =
         * uc.getContentEncoding(); WireFeedInput input = new WireFeedInput(); XmlReader xmlReader =
         * new XmlReader(uc.getInputStream(), contentType, true); Channel chnl =
         * (Channel)input.build(xmlReader); String feedTitle = chnl.getTitle(); String feedDesc =
         * chnl.getDescription(); List items = chnl.getItems(); ListIterator li =
         * items.listIterator(); Item item = null; Enclosure enc = null; MediaModule mModule = null;
         * while (li.hasNext()) { item = (Item)li.next(); enc =
         * (Enclosure)item.getEnclosures().get(0); mModule =
         * (MediaModule)item.getModule(MediaModule.URI); List modules = item.getModules();
         * LOG.debug("title: " + item.getTitle()); LOG.debug("module count: " + modules.size()); if
         * (mModule != null) { Thumbnail[] mThumbs = mModule.getMediaThumbnails(); if (mThumbs !=
         * null) { for (int i = 0; i < mThumbs.length; i++) { String imgUrl = mThumbs[i].getUrl();
         * LOG.debug("got MediaModule img " + i + ": " + imgUrl); } }
         * LOG.debug("MediaModule title: " + mModule.getTitle()); LOG.debug("MediaModule isAdult: "
         * + mModule.isAdult()); /* if (mModule.getMediaContent() != null) { for (int i = 0; i <
         * mModule.getMediaContent().length; i++) { MediaContent mc = mModule.getMediaContent()[i];
         * mThumbs = mc.getMediaThumbnails(); if (mThumbs != null) { for (int n = 0; n <
         * mThumbs.length; n++) { String imgUrl = mThumbs[n].getUrl();
         * LOG.debug("got MediaContentImage " + n + " img: " + imgUrl); } }
         * LOG.debug("MediaContent title:" + mc.getTitle()); LOG.debug("MediaContent text:" +
         * mc.getText()); } } } else { LOG.debug("no MediaModule!"); } }
         */
    }
}
