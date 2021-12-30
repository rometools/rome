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
 */
package com.rometools.rome.unittest;

import java.io.StringReader;
import java.io.StringWriter;

import junit.framework.TestCase;

import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.io.WireFeedInput;
import com.rometools.rome.io.WireFeedOutput;

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
