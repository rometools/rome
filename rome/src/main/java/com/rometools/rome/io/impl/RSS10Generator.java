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

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.io.FeedException;

/**
 * Feed Generator for RSS 1.0
 * <p/>
 */

public class RSS10Generator extends RSS090Generator {

    private static final String RSS_URI = "http://purl.org/rss/1.0/";
    private static final Namespace RSS_NS = Namespace.getNamespace(RSS_URI);

    public RSS10Generator() {
        super("rss_1.0");
    }

    protected RSS10Generator(final String feedType) {
        super(feedType);
    }

    @Override
    protected Namespace getFeedNamespace() {
        return RSS_NS;
    }

    @Override
    protected void populateChannel(final Channel channel, final Element eChannel) {

        super.populateChannel(channel, eChannel);

        final String channelUri = channel.getUri();
        if (channelUri != null) {
            eChannel.setAttribute("about", channelUri, getRDFNamespace());
        }

        final List<Item> items = channel.getItems();
        if (!items.isEmpty()) {
            final Element eItems = new Element("items", getFeedNamespace());
            final Element eSeq = new Element("Seq", getRDFNamespace());
            for (final Item item : items) {
                final Element lis = new Element("li", getRDFNamespace());
                final String uri = item.getUri();
                if (uri != null) {
                    lis.setAttribute("resource", uri, getRDFNamespace());
                }
                eSeq.addContent(lis);
            }
            eItems.addContent(eSeq);
            eChannel.addContent(eItems);
        }
    }

    @Override
    protected void populateItem(final Item item, final Element eItem, final int index) {

        super.populateItem(item, eItem, index);

        final String link = item.getLink();
        final String uri = item.getUri();
        if (uri != null) {
            eItem.setAttribute("about", uri, getRDFNamespace());
        } else if (link != null) {
            eItem.setAttribute("about", link, getRDFNamespace());
        }

        final Description description = item.getDescription();
        if (description != null) {
            eItem.addContent(generateSimpleElement("description", description.getValue()));
        }

        if (item.getModule(getContentNamespace().getURI()) == null && item.getContent() != null) {
            final Element elem = new Element("encoded", getContentNamespace());
            elem.addContent(item.getContent().getValue());
            eItem.addContent(elem);
        }

    }

    @Override
    protected void checkChannelConstraints(final Element eChannel) throws FeedException {
        checkNotNullAndLength(eChannel, "title", 0, -1);
        checkNotNullAndLength(eChannel, "description", 0, -1);
        checkNotNullAndLength(eChannel, "link", 0, -1);
    }

    @Override
    protected void checkImageConstraints(final Element eImage) throws FeedException {
        checkNotNullAndLength(eImage, "title", 0, -1);
        checkNotNullAndLength(eImage, "url", 0, -1);
        checkNotNullAndLength(eImage, "link", 0, -1);
    }

    @Override
    protected void checkTextInputConstraints(final Element eTextInput) throws FeedException {
        checkNotNullAndLength(eTextInput, "title", 0, -1);
        checkNotNullAndLength(eTextInput, "description", 0, -1);
        checkNotNullAndLength(eTextInput, "name", 0, -1);
        checkNotNullAndLength(eTextInput, "link", 0, -1);
    }

    @Override
    protected void checkItemsConstraints(final Element parent) throws FeedException {
    }

    @Override
    protected void checkItemConstraints(final Element eItem) throws FeedException {
        checkNotNullAndLength(eItem, "title", 0, -1);
        checkNotNullAndLength(eItem, "link", 0, -1);
    }

}
