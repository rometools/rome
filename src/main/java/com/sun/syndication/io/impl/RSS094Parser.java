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

import java.util.List;
import java.util.Locale;

import org.jdom2.Element;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Guid;
import com.sun.syndication.feed.rss.Item;

/**
 */
public class RSS094Parser extends RSS093Parser {

    public RSS094Parser() {
        this("rss_0.94");
    }

    protected RSS094Parser(final String type) {
        super(type);
    }

    @Override
    protected String getRSSVersion() {
        return "0.94";
    }

    @Override
    protected WireFeed parseChannel(final Element rssRoot, final Locale locale) {
        final Channel channel = (Channel) super.parseChannel(rssRoot, locale);
        final Element eChannel = rssRoot.getChild("channel", getRSSNamespace());

        final List<Element> eCats = eChannel.getChildren("category", getRSSNamespace());
        channel.setCategories(parseCategories(eCats));

        final Element eTtl = eChannel.getChild("ttl", getRSSNamespace());
        if (eTtl != null && eTtl.getText() != null) {
            Integer ttlValue = null;
            try {
                ttlValue = new Integer(eTtl.getText());
            } catch (final NumberFormatException nfe) {
                ; // let it go by
            }
            if (ttlValue != null) {
                channel.setTtl(ttlValue.intValue());
            }
        }

        return channel;
    }

    @Override
    public Item parseItem(final Element rssRoot, final Element eItem, final Locale locale) {
        final Item item = super.parseItem(rssRoot, eItem, locale);
        item.setExpirationDate(null);

        Element e = eItem.getChild("author", getRSSNamespace());
        if (e != null) {
            item.setAuthor(e.getText());
        }

        e = eItem.getChild("guid", getRSSNamespace());
        if (e != null) {
            final Guid guid = new Guid();
            final String att = e.getAttributeValue("isPermaLink");// getRSSNamespace());
            // DONT KNOW WHY
            // DOESN'T WORK
            if (att != null) {
                guid.setPermaLink(att.equalsIgnoreCase("true"));
            }
            guid.setValue(e.getText());
            item.setGuid(guid);
        }

        e = eItem.getChild("comments", getRSSNamespace());
        if (e != null) {
            item.setComments(e.getText());
        }

        return item;
    }

}
