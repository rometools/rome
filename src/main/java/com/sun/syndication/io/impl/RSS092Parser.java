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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jdom2.Content;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.rss.Category;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Cloud;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Enclosure;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.rss.Source;

/**
 */
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
            String att = eCloud.getAttributeValue("domain");// getRSSNamespace());
                                                            // DONT KNOW WHY
                                                            // DOESN'T WORK
            if (att != null) {
                cloud.setDomain(att);
            }
            att = eCloud.getAttributeValue("port");// getRSSNamespace()); DONT
                                                   // KNOW WHY DOESN'T WORK
            if (att != null) {
                cloud.setPort(Integer.parseInt(att.trim()));
            }
            att = eCloud.getAttributeValue("path");// getRSSNamespace()); DONT
                                                   // KNOW WHY DOESN'T WORK
            if (att != null) {
                cloud.setPath(att);
            }
            att = eCloud.getAttributeValue("registerProcedure");// getRSSNamespace());
                                                                // DONT KNOW WHY
                                                                // DOESN'T WORK
            if (att != null) {
                cloud.setRegisterProcedure(att);
            }
            att = eCloud.getAttributeValue("protocol");// getRSSNamespace());
                                                       // DONT KNOW WHY DOESN'T
                                                       // WORK
            if (att != null) {
                cloud.setProtocol(att);
            }
            channel.setCloud(cloud);
        }
        return channel;
    }

    @Override
    protected Item parseItem(final Element rssRoot, final Element eItem, final Locale locale) {
        final Item item = super.parseItem(rssRoot, eItem, locale);

        Element e = eItem.getChild("source", getRSSNamespace());
        if (e != null) {
            final Source source = new Source();
            final String url = e.getAttributeValue("url");// getRSSNamespace());
                                                          // DONT
            // KNOW WHY DOESN'T WORK
            source.setUrl(url);
            source.setValue(e.getText());
            item.setSource(source);
        }

        // 0.92 allows one enclosure occurrence, 0.93 multiple
        // just saving to write some code.
        final List<Element> eEnclosures = eItem.getChildren("enclosure");// getRSSNamespace());
        // DONT KNOW
        // WHY
        // DOESN'T
        // WORK
        if (eEnclosures.size() > 0) {
            final List<Enclosure> enclosures = new ArrayList<Enclosure>();
            for (int i = 0; i < eEnclosures.size(); i++) {
                e = eEnclosures.get(i);

                final Enclosure enclosure = new Enclosure();
                String att = e.getAttributeValue("url");// getRSSNamespace());
                                                        // DONT KNOW WHY DOESN'T
                                                        // WORK
                if (att != null) {
                    enclosure.setUrl(att);
                }
                att = e.getAttributeValue("length");// getRSSNamespace()); DONT
                                                    // KNOW WHY DOESN'T WORK
                enclosure.setLength(NumberParser.parseLong(att, 0L));

                att = e.getAttributeValue("type");// getRSSNamespace()); DONT
                                                  // KNOW WHY DOESN'T WORK
                if (att != null) {
                    enclosure.setType(att);
                }
                enclosures.add(enclosure);
            }
            item.setEnclosures(enclosures);
        }

        final List<Element> eCats = eItem.getChildren("category");// getRSSNamespace());
        // DONT KNOW WHY
        // DOESN'T WORK
        item.setCategories(parseCategories(eCats));

        return item;
    }

    protected List<Category> parseCategories(final List<Element> eCats) {
        List<Category> cats = null;
        if (eCats.size() > 0) {
            cats = new ArrayList<Category>();
            for (int i = 0; i < eCats.size(); i++) {
                final Category cat = new Category();
                final Element e = eCats.get(i);
                final String att = e.getAttributeValue("domain");// getRSSNamespace());
                // DONT KNOW WHY
                // DOESN'T WORK
                if (att != null) {
                    cat.setDomain(att);
                }
                cat.setValue(e.getText());
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
