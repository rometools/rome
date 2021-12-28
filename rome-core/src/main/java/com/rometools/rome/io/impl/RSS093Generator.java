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
package com.rometools.rome.io.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jdom2.Element;

import com.rometools.rome.feed.rss.Enclosure;
import com.rometools.rome.feed.rss.Item;

/**
 * Feed Generator for RSS 0.93
 * <p/>
 */
public class RSS093Generator extends RSS092Generator {

    public RSS093Generator() {
        this("rss_0.93", "0.93");
    }

    protected RSS093Generator(final String feedType, final String version) {
        super(feedType, version);
    }

    @Override
    protected void populateItem(final Item item, final Element eItem, final int index) {
        super.populateItem(item, eItem, index);

        final Date pubDate = item.getPubDate();
        if (pubDate != null) {
            eItem.addContent(generateSimpleElement("pubDate", DateParser.formatRFC822(pubDate, Locale.US)));
        }

        final Date expirationDate = item.getExpirationDate();
        if (expirationDate != null) {
            eItem.addContent(generateSimpleElement("expirationDate", DateParser.formatRFC822(expirationDate, Locale.US)));
        }
    }

    // Another one to thanks DW for
    @Override
    protected int getNumberOfEnclosures(final List<Enclosure> enclosures) {
        return enclosures.size();
    }

}
