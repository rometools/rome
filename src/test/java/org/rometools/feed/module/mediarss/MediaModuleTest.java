/*
 * MediaModuleTest.java
 * JUnit based test
 *
 * Created on March 29, 2006, 11:49 PM
 */

package org.rometools.feed.module.mediarss;

import org.rometools.feed.module.mediarss.MediaModule;
import org.rometools.feed.module.mediarss.MediaEntryModule;
import org.rometools.feed.module.AbstractTestCase;
import junit.framework.*;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.SyndFeedOutput;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author cooper
 */
public class MediaModuleTest extends AbstractTestCase {
    
    public MediaModuleTest(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(MediaModuleTest.class);
        
        return suite;
    }
    
    public void testGoogleVideo() throws Exception {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build( new InputStreamReader( new URL( "http://video.google.com/videofeed?type=top100new&num=20&output=rss").openStream()));
        for( Iterator it = feed.getEntries().iterator(); it.hasNext();  ){
            SyndEntry entry = (SyndEntry) it.next();
            MediaEntryModule m = (MediaEntryModule) entry.getModule( MediaEntryModule.URI );
            System.out.print( m );
        }
    }
    
    public void testParse() throws Exception {
        SyndFeedInput input = new SyndFeedInput();
        File test = new File( super.getTestFile( "xml") );
        File[] files = test.listFiles();
        for( int j=0; j < files.length; j++ ){
            if( !files[j].getName().endsWith(".xml") )
                continue;
            SyndFeed feed =  input.build( files[j] );
            //System.out.println( feed );
            List entries = feed.getEntries();
            for( int i = 0; i < entries.size(); i++ ){
                //System.out.println( ((SyndEntry) entries.get(i)).getModule( MediaModule.URI ) );
            }
            SyndFeedOutput output = new SyndFeedOutput();
            output.output( feed, new File( "target/"+j+".xml") );
            SyndFeed feed2 = input.build( new File("target/"+j+".xml")  );
            for( int i=0; i < entries.size(); i++ ){
                BufferedWriter b = new BufferedWriter( new FileWriter( "target/"+j+"a.txt") );
                b.write( ""+((SyndEntry) entries.get(i)).getModule( MediaModule.URI ) );
                b.close();
                b = new BufferedWriter( new FileWriter( "target/"+j+"b.txt") );
                b.write( ""+ ((SyndEntry) feed2.getEntries().get(i)).getModule( MediaModule.URI ) );
                b.close();
                assertEquals( ((SyndEntry) entries.get(i)).getModule( MediaModule.URI ), ((SyndEntry) feed2.getEntries().get(i)).getModule( MediaModule.URI ));
            }
        }
    }
    
    
    /** This code represents the original MediaModuleTest main class from openvision.tv
     */
    public void xtestOriginal() throws Exception{
        //
        /*
        //You can test with blip tv or broadcast machine
         
        String rss = "http://blip.tv/?1=1&&skin=rss";//
        //	String rss = "http://openvision.tv/bm/rss.php?i=4";
        //	String rss = "http://api.search.yahoo.com/VideoSearchService/rss/videoSearch.xml?appid=yahoosearchvideorss&query=surfing&adult_ok=0";
         
         
        URLConnection uc = new URL(rss).openConnection();
        String contentType = uc.getContentEncoding();
         
        WireFeedInput input = new WireFeedInput();
         
        XmlReader xmlReader = new XmlReader(uc.getInputStream(), contentType, true);
         
        Channel chnl = (Channel)input.build(xmlReader);
         
        String feedTitle = chnl.getTitle();
        String feedDesc = chnl.getDescription();
         
         
        List items = chnl.getItems();
         
        ListIterator li = items.listIterator();
        Item item = null;
        Enclosure enc = null;
        MediaModule mModule = null;
         
        while (li.hasNext()) {
         
            item = (Item)li.next();
            enc = (Enclosure)item.getEnclosures().get(0);
         
            mModule = (MediaModule)item.getModule(MediaModule.URI);
         
            List modules = item.getModules();
            System.out.println("title: " + item.getTitle());
            System.out.println("module count: " + modules.size());
         
            if (mModule != null) {
                Thumbnail[] mThumbs = mModule.getMediaThumbnails();
         
                if (mThumbs != null) {
                    for (int i = 0; i < mThumbs.length; i++) {
                        String imgUrl = mThumbs[i].getUrl();
                        System.out.println("got MediaModule img " + i + ": " + imgUrl);
                    }
                }
         
                System.out.println("MediaModule title: " + mModule.getTitle());
                System.out.println("MediaModule isAdult: " + mModule.isAdult());
                /*
                if (mModule.getMediaContent() != null) {
                    for (int i = 0; i < mModule.getMediaContent().length; i++) {
                        MediaContent mc = mModule.getMediaContent()[i];
                        mThumbs = mc.getMediaThumbnails();
                        if (mThumbs != null) {
                            for (int n = 0; n < mThumbs.length; n++) {
                                String imgUrl = 	mThumbs[n].getUrl();
                                System.out.println("got MediaContentImage " + n + " img: " + imgUrl);
                            }
                        }
                        System.out.println("MediaContent title:" + mc.getTitle());
                        System.out.println("MediaContent text:" + mc.getText());
         
                    }
         
                }
         
            } else {
                System.out.println("no MediaModule!");
            }
        }
         */
    }
}

