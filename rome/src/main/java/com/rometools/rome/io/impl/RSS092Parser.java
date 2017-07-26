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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jdom2.Content;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.rss.Category;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Cloud;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Enclosure;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.rss.Source;

public class RSS092Parser extends RSS091UserlandParser {

    private static final Logger LOG = LoggerFactory.getLogger(RSS092Parser.class);

    public RSS092Parser() {
        this("rss_0.92");
    }

    protected RSS092Parser(final String type) {
        super(type);
    }

    @Override
    protected String getRSSVersion() {
        return "0.92";
    }

    @Override
    protected WireFeed parseChannel(final Element rssRoot, final Locale locale) {

        final Channel channel = (Channel) super.parseChannel(rssRoot, locale);

        final Element eChannel = rssRoot.getChild("channel", getRSSNamespace());

        final Element eCloud = eChannel.getChild("cloud", getRSSNamespace());

        if (eCloud != null) {
            final Cloud cloud = new Cloud();

            final String domain = eCloud.getAttributeValue("domain");
            if (domain != null) {
                cloud.setDomain(domain);
            }

            // getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
            final String port = eCloud.getAttributeValue("port");
            if (port != null) {
                cloud.setPort(Integer.parseInt(port.trim()));
            }

            // getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
            final String path = eCloud.getAttributeValue("path");
            if (path != null) {
                cloud.setPath(path);
            }

            // getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
            final String registerProcedure = eCloud.getAttributeValue("registerProcedure");
            if (registerProcedure != null) {
                cloud.setRegisterProcedure(registerProcedure);
            }

            // getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
            final String protocol = eCloud.getAttributeValue("protocol");
            if (protocol != null) {
                cloud.setProtocol(protocol);
            }

            channel.setCloud(cloud);

        }
        return channel;
    }

    @Override
    protected Item parseItem(final Element rssRoot, final Element eItem, final Locale locale) {
        final Item item = super.parseItem(rssRoot, eItem, locale);

        final Element eSource = eItem.getChild("source", getRSSNamespace());
        if (eSource != null) {
            final Source source = new Source();
            // getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
            final String url = eSource.getAttributeValue("url");
            source.setUrl(url);
            source.setValue(eSource.getText());
            item.setSource(source);
        }

        // 0.92 allows one enclosure occurrence, 0.93 multiple just saving to write some code.
        // getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
        final List<Element> eEnclosures = eItem.getChildren("enclosure");

        if (!eEnclosures.isEmpty()) {

            final List<Enclosure> enclosures = new ArrayList<Enclosure>();

            for (final Element eEnclosure : eEnclosures) {

                final Enclosure enclosure = new Enclosure();
                // getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
                final String url = eEnclosure.getAttributeValue("url");
                if (url != null) {
                    enclosure.setUrl(url);
                }

                // getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
                final String length = eEnclosure.getAttributeValue("length");
                enclosure.setLength(NumberParser.parseLong(length, 0L));

                // getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
                final String type = eEnclosure.getAttributeValue("type");
                if (type != null) {
                    enclosure.setType(type);
                }

                enclosures.add(enclosure);

            }

            item.setEnclosures(enclosures);
        }

        // getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
        final List<Element> categories = eItem.getChildren("category");
        item.setCategories(parseCategories(categories));

        return item;
    }

    protected List<Category> parseCategories(final List<Element> eCats) {

        List<Category> cats = null;

        if (!eCats.isEmpty()) {

            cats = new ArrayList<Category>();
            for (final Element eCat : eCats) {

                final Category cat = new Category();

                // getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
                final String domain = eCat.getAttributeValue("domain");
                if (domain != null) {
                    cat.setDomain(domain);
                }

                cat.setValue(eCat.getText());

                cats.add(cat);

            }
        }

        return cats;

    }

    @Override
    protected Description parseItemDescription(final Element rssRoot, final Element eDesc) {
        final Description desc = new Description();
        final StringBuilder sb = new StringBuilder();
        final XMLOutputter xmlOut = new XMLOutputter();
        for (final Content c : eDesc.getContent()) {
            switch (c.getCType()) {
                case Text:
                case CDATA:
                    sb.append(c.getValue());
                    break;
                case EntityRef:
                    LOG.debug("Entity: {}", c.getValue());
                    sb.append(c.getValue());
                    break;
                case Element:
                    sb.append(xmlOut.outputString((Element) c));
                    break;
                default:
                    // ignore
                    break;
            }
        }
        desc.setValue(sb.toString());
        String att = eDesc.getAttributeValue("type");
        if (att == null) {
            att = "text/html";
        }
        desc.setType(att);
        return desc;
    }

}
