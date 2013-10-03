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

import org.jdom2.Element;

import com.sun.syndication.feed.rss.Item;

/**
 */
public class RSS093Parser extends RSS092Parser {

    public RSS093Parser() {
        this("rss_0.93");
    }

    protected RSS093Parser(final String type) {
        super(type);
    }

    @Override
    protected String getRSSVersion() {
        return "0.93";
    }

    @Override
    protected Item parseItem(final Element rssRoot, final Element eItem) {
        final Item item = super.parseItem(rssRoot, eItem);
        Element e = eItem.getChild("pubDate", getRSSNamespace());
        if (e != null) {
            item.setPubDate(DateParser.parseDate(e.getText()));
        }
        e = eItem.getChild("expirationDate", getRSSNamespace());
        if (e != null) {
            item.setExpirationDate(DateParser.parseDate(e.getText()));
        }
        e = eItem.getChild("description", getRSSNamespace());
        if (e != null) {
            final String type = e.getAttributeValue("type");
            if (type != null) {
                item.getDescription().setType(type);
            }
        }
        return item;
    }

}
