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
package com.sun.syndication.io.impl;

import com.sun.syndication.feed.rss.Item;
import org.jdom.Element;

import java.util.Date;
import java.util.List;


/**
 * Feed Generator for RSS 0.93
 * <p/>
 *
 * @author Elaine Chien
 *
 */
public class RSS093Generator extends RSS092Generator {

    public RSS093Generator() {
        this("rss_0.93","0.93");
    }

    protected RSS093Generator(String feedType,String version) {
        super(feedType,version);
    }

    protected void populateItem(Item item, Element eItem, int index) {
        super.populateItem(item,eItem, index);

        Date pubDate = item.getPubDate();
        if (pubDate != null) {
            eItem.addContent(generateSimpleElement("pubDate", DateParser.formatRFC822(pubDate)));
        }

        Date expirationDate = item.getExpirationDate();
        if (expirationDate != null) {
            eItem.addContent(generateSimpleElement("expirationDate", DateParser.formatRFC822(expirationDate)));
        }
    }

    // Another one to thanks DW for
    protected int getNumberOfEnclosures(List enclosures) {
        return enclosures.size();
    }

}
