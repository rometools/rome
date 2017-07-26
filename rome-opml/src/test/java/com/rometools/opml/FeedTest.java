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
package com.rometools.opml;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import junit.framework.TestCase;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.WireFeedInput;

public abstract class FeedTest extends TestCase {

    private final String fileName;
    private Document jDomDoc = null;
    private WireFeed wireFeed = null;
    private SyndFeed syndFeed = null;

    protected FeedTest(final String feedFileName) {
        fileName = feedFileName;
    }

    protected String getFeedFileName() {
        return fileName;
    }

    protected Reader getFeedReader() throws Exception {
        final InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(getFeedFileName());
        assertNotNull("Could not find resource " + getFeedFileName(), resource);
        return new InputStreamReader(resource);
    }

    protected Document getJDomDoc() throws Exception {
        final SAXBuilder saxBuilder = new SAXBuilder(XMLReaders.NONVALIDATING);
        return saxBuilder.build(getFeedReader());
    }

    protected WireFeed getWireFeed() throws Exception {
        final WireFeedInput in = new WireFeedInput();
        return in.build(getFeedReader());
    }

    protected SyndFeed getSyndFeed() throws Exception {
        final SyndFeedInput in = new SyndFeedInput();
        return in.build(getFeedReader());
    }

    protected Document getCachedJDomDoc() throws Exception {
        if (jDomDoc == null) {
            jDomDoc = getJDomDoc();
        }
        return jDomDoc;
    }

    protected WireFeed getCachedWireFeed() throws Exception {
        if (wireFeed == null) {
            wireFeed = getWireFeed();
        }
        return wireFeed;
    }

    protected SyndFeed getCachedSyndFeed() throws Exception {
        if (syndFeed == null) {
            syndFeed = getSyndFeed();
        }
        return syndFeed;
    }

}
