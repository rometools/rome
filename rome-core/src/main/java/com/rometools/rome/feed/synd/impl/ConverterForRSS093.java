/*
 * Copyright 2004 Sun Microsystems, Inc.
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
package com.rometools.rome.feed.synd.impl;

import java.util.Date;

import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.synd.SyndEntry;

public class ConverterForRSS093 extends ConverterForRSS092 {

    public ConverterForRSS093() {
        this("rss_0.93");
    }

    protected ConverterForRSS093(final String type) {
        super(type);
    }

    @Override
    protected SyndEntry createSyndEntry(final Item item, final boolean preserveWireItem) {

        final SyndEntry syndEntry = super.createSyndEntry(item, preserveWireItem);

        final Date pubDate = item.getPubDate();
        final Date publishedDate = syndEntry.getPublishedDate();
        if (pubDate != null && publishedDate == null) {
            syndEntry.setPublishedDate(pubDate); // c
        }

        return syndEntry;
    }

    @Override
    protected Item createRSSItem(final SyndEntry sEntry) {
        final Item item = super.createRSSItem(sEntry);
        item.setPubDate(sEntry.getPublishedDate()); // c
        return item;
    }

}
