/*
 * TestOpsOPML10.java
 *
 * Created on April 25, 2006, 4:26 PM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.rometools.opml;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.rometools.opml.test.NullWriter;
import com.rometools.opml.test.TestUtil;
import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.WireFeedInput;
import com.rometools.rome.io.WireFeedOutput;

public class TestOpsOPML10links extends FeedOpsTest {

    public TestOpsOPML10links() {
        super("opml_1.0_links");
    }

    // 1.6
    @Override
    public void testWireFeedSyndFeedConversion() throws Exception {
        final SyndFeed sFeed1 = getCachedSyndFeed();
        final WireFeed wFeed1 = sFeed1.createWireFeed();
        final SyndFeed sFeed2 = new SyndFeedImpl(wFeed1);
        PrintWriter w = new PrintWriter(new FileOutputStream("target/test-reports/1"));
        w.println(sFeed1.toString());
        w.close();
        w = new PrintWriter(new FileOutputStream("target/test-reports/2"));
        w.println(sFeed2.toString());
        w.close();

        assertEquals(sFeed2.createWireFeed(), sFeed1.createWireFeed());
    }

    public void testTemp() throws Exception {
        final WireFeedInput input = new WireFeedInput();
        final WireFeed wf = input.build(TestUtil.loadFile("/opml_1.0_links.xml"));
        final WireFeedOutput output = new WireFeedOutput();

        final SyndFeedImpl sf = new SyndFeedImpl(wf);
        sf.setFeedType("rss_2.0");
        sf.setDescription("");
        sf.setLink("http://foo.com");
        sf.setFeedType("opml_1.0");
        output.output(sf.createWireFeed(), new NullWriter());
    }

}
