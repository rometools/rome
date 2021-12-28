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

import java.util.List;
import java.util.Locale;

import org.jdom2.Element;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Guid;
import com.rometools.rome.feed.rss.Item;

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

        final List<Element> categories = eChannel.getChildren("category", getRSSNamespace());
        channel.setCategories(parseCategories(categories));

        final Element ttl = eChannel.getChild("ttl", getRSSNamespace());
        if (ttl != null && ttl.getText() != null) {
            final Integer ttlValue = NumberParser.parseInt(ttl.getText());
            if (ttlValue != null) {
                channel.setTtl(ttlValue);
            }
        }

        return channel;
    }

    @Override
    public Item parseItem(final Element rssRoot, final Element eItem, final Locale locale) {

        final Item item = super.parseItem(rssRoot, eItem, locale);

        item.setExpirationDate(null);

        final Element author = eItem.getChild("author", getRSSNamespace());
        if (author != null) {
            item.setAuthor(author.getText());
        }

        final Element eGuid = eItem.getChild("guid", getRSSNamespace());
        if (eGuid != null) {

            final Guid guid = new Guid();

            // getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
            final String att = eGuid.getAttributeValue("isPermaLink");
            if (att != null) {
                guid.setPermaLink(att.equalsIgnoreCase("true"));
            }

            guid.setValue(eGuid.getText());

            item.setGuid(guid);

        }

        final Element comments = eItem.getChild("comments", getRSSNamespace());
        if (comments != null) {
            item.setComments(comments.getText());
        }

        return item;

    }

}
