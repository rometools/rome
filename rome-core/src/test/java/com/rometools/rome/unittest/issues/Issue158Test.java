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
package com.rometools.rome.unittest.issues;

import com.rometools.rome.unittest.FeedTest;

/**
 * Test for #161: No source element in RSS 2.0 items.
 */
public class Issue158Test extends FeedTest {

    public Issue158Test() {
        super("rss_1.0-ns-on-channel.xml");
    }

    public void testSyndFeed() throws Exception {
        assertEquals("rss_1.0", this.getCachedSyndFeed().getFeedType());
    }

}
