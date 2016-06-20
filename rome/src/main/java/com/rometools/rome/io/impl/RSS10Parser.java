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

import java.util.Locale;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Item;

public class RSS10Parser extends RSS090Parser {

    private static final String RSS_URI = "http://purl.org/rss/1.0/";
    private static final Namespace RSS_NS = Namespace.getNamespace(RSS_URI);

    public RSS10Parser() {
        this("rss_1.0", RSS_NS);
    }

    protected RSS10Parser(final String type, final Namespace ns) {
        super(type, ns);
    }

    /**
     * Indicates if a JDom document is an RSS instance that can be parsed with the parser.
     * <p/>
     * It checks for RDF ("http://www.w3.org/1999/02/22-rdf-syntax-ns#") namespace being defined in
     * the root element and for the RSS 1.0 ("http://purl.org/rss/1.0/") namespace in the channel
     * element.
     *
     * @param document document to check if it can be parsed with this parser implementation.
     * @return <b>true</b> if the document is RSS1., <b>false</b> otherwise.
     */
    @Override
    public boolean isMyType(final Document document) {
        final Element rssRoot = document.getRootElement();
        final Namespace defaultNS = rssRoot.getNamespace();
        return defaultNS != null && defaultNS.equals(getRDFNamespace()) && rssRoot.getChild("channel", getRSSNamespace()) != null;
    }

    /**
     * Returns the namespace used by RSS elements in document of the RSS 1.0
     * <P>
     *
     * @return returns "http://purl.org/rss/1.0/".
     */
    @Override
    protected Namespace getRSSNamespace() {
        return Namespace.getNamespace(RSS_URI);
    }

    /**
     * Parses an item element of an RSS document looking for item information.
     * <p/>
     * It first invokes super.parseItem and then parses and injects the description property if
     * present.
     * <p/>
     *
     * @param rssRoot the root element of the RSS document in case it's needed for context.
     * @param eItem the item element to parse.
     * @return the parsed RSSItem bean.
     */
    @Override
    protected Item parseItem(final Element rssRoot, final Element eItem, final Locale locale) {

        final Item item = super.parseItem(rssRoot, eItem, locale);

        final Element description = eItem.getChild("description", getRSSNamespace());
        if (description != null) {
            item.setDescription(parseItemDescription(rssRoot, description));
        }

        final Element encoded = eItem.getChild("encoded", getContentNamespace());
        if (encoded != null) {
            final Content content = new Content();
            content.setType(Content.HTML);
            content.setValue(encoded.getText());
            item.setContent(content);
        }

        final String about = eItem.getAttributeValue("about", getRDFNamespace());
        if (about != null) {
            item.setUri(about);
        }

        return item;
    }

    @Override
    protected WireFeed parseChannel(final Element rssRoot, final Locale locale) {

        final Channel channel = (Channel) super.parseChannel(rssRoot, locale);

        final Element eChannel = rssRoot.getChild("channel", getRSSNamespace());
        final String uri = eChannel.getAttributeValue("about", getRDFNamespace());
        if (uri != null) {
            channel.setUri(uri);
        }

        return channel;
    }

    protected Description parseItemDescription(final Element rssRoot, final Element eDesc) {
        final Description desc = new Description();
        desc.setType("text/plain");
        desc.setValue(eDesc.getText());
        return desc;
    }

}
