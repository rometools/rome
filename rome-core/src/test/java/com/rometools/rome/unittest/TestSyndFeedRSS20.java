/*
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
 * Created on Jun 24, 2004
 *
 */
package com.rometools.rome.unittest;

import java.util.List;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.synd.SyndEntry;

public class TestSyndFeedRSS20 extends TestSyndFeedRSS094 {

    public TestSyndFeedRSS20() {
        super("rss_2.0");
    }

    protected TestSyndFeedRSS20(final String type) {
        super(type);
    }

    protected TestSyndFeedRSS20(final String feedType, final String feedFileName) {
        super(feedType, feedFileName);
    }

    @Override
    protected void testItem(final int i) throws Exception {
        super.testItem(i);
        final List<SyndEntry> items = this.getCachedSyndFeed().getEntries();
        final SyndEntry entry = items.get(i);
        assertProperty(entry.getContents().get(0).getValue(), "channel.item[" + i + "].content");
    }

    /**
     * Test we can get to RSS attributes which aren't exposed in the SyndEntry object
     *
     * @throws Exception
     */
    public void testPreservedWireItems() throws Exception {
        final SyndEntry syndEntry1 = this.getCachedSyndFeed(true).getEntries().get(0);
        final Object o = syndEntry1.getWireEntry();
        assertNotNull(o);
        assertTrue(o instanceof Item);
        if (o instanceof Item) {
            final Item item = (Item) o;
            assertEquals("rss_2.0.channel.item[0].comments", item.getComments());
        }
    }

    public void testPreserveWireFeedComments() throws Exception {
        final WireFeed wf = this.getCachedSyndFeed(true).originalWireFeed();
        assertNotNull(wf);
        assertTrue(wf instanceof Channel);
        if (wf instanceof Channel) {
            final Channel channel = (Channel) wf;
            assertEquals("rss_2.0.channel.item[0].comments", channel.getItems().get(0).getComments());
            assertEquals("rss_2.0.channel.item[1].comments", channel.getItems().get(1).getComments());
        }
    }

}
