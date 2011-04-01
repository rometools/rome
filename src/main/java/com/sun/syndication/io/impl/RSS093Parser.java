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

/**
 */
public class RSS093Parser extends RSS092Parser {

    public RSS093Parser() {
        this("rss_0.93");
    }

    protected RSS093Parser(String type) {
        super(type);
    }

    protected String getRSSVersion() {
            return "0.93";
    }

    protected Item parseItem(Element rssRoot,Element eItem) {
        Item item = super.parseItem(rssRoot,eItem);
        Element e = eItem.getChild("pubDate",getRSSNamespace());
        if (e!=null) {
            item.setPubDate(DateParser.parseDate(e.getText()));
        }
        e = eItem.getChild("expirationDate",getRSSNamespace());
        if (e!=null) {
            item.setExpirationDate(DateParser.parseDate(e.getText()));
        }
        e = eItem.getChild("description",getRSSNamespace());
        if (e!=null) {
            String type = e.getAttributeValue("type");
            if (type!=null) {
                item.getDescription().setType(type);
            }
        }
        return item;
    }

}
