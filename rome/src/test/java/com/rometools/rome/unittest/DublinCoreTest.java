/*
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
 */

package com.rometools.rome.unittest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.rometools.rome.feed.module.DCModule;
import com.rometools.rome.feed.synd.SyndEntry;

public class DublinCoreTest extends FeedTest {

    public DublinCoreTest() {
        super("dublin-core-date.xml");
    }

    public void testPubDateDoesNotOverrideDublinCoreDate() throws Exception {
        final SyndEntry entry = this.getCachedSyndFeed().getEntries().get(0);
        final DCModule dublinCore = (DCModule) entry.getModule(DCModule.URI);

        Date expectedDate = new Date(1000);

        LocalDateTime expectedDateTime = expectedDate.toInstant()
                .atZone(ZoneId.of("GMT"))
                .toLocalDateTime();

        assertEquals(
                expectedDateTime,
                dublinCore.getDate()
        );
    }
}
