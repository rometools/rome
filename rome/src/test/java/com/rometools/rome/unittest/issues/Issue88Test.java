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
 * Test for #134: Incorrect handling of CDATA sections.
 */
public class Issue88Test extends FeedTest {

    public Issue88Test() {
        super("rss_2.0.xml");
    }

    public void testStyleSheet() throws Exception {
        assertEquals("stylesheet in syndfeed missing", "http://test.example/test.xslt", this.getCachedSyndFeed().getStyleSheet());
        assertEquals("stylesheet in wirefeed missing", "http://test.example/test.xslt", getCachedWireFeed().getStyleSheet());
    }

}
