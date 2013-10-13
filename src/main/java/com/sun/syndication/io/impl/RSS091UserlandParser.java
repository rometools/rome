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
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Image;
import com.sun.syndication.feed.rss.Item;

/**
 */
public class RSS091UserlandParser extends RSS090Parser {

    public RSS091UserlandParser() {
        this("rss_0.91U");
    }

    protected RSS091UserlandParser(final String type) {
        super(type, null);
    }

    @Override
    public boolean isMyType(final Document document) {
        boolean ok;
        final Element rssRoot = document.getRootElement();
        ok = rssRoot.getName().equals("rss");
        if (ok) {
            ok = false;
            final Attribute version = rssRoot.getAttribute("version");
            if (version != null) {
                ok = version.getValue().equals(getRSSVersion());
            }
        }
        return ok;
    }

    protected String getRSSVersion() {
        return "0.91";
    }

    @Override
    protected Namespace getRSSNamespace() {
        return Namespace.getNamespace("");
    }

    /**
     * To be overriden by RSS 0.91 Netscape and RSS 0.94
     */
    protected boolean isHourFormat24(final Element rssRoot) {
        return true;
    }

    /**
     * Parses the root element of an RSS document into a Channel bean.
     * <p/>
     * It first invokes super.parseChannel and then parses and injects the
     * following properties if present: language, pubDate, rating and copyright.
     * <p/>
     * 
     * @param rssRoot the root element of the RSS document to parse.
     * @return the parsed Channel bean.
     */
    @Override
    protected WireFeed parseChannel(final Element rssRoot, final Locale locale) {
        final Channel channel = (Channel) super.parseChannel(rssRoot, locale);

        final Element eChannel = rssRoot.getChild("channel", getRSSNamespace());

        Element e = eChannel.getChild("language", getRSSNamespace());
        if (e != null) {
            channel.setLanguage(e.getText());
        }
        e = eChannel.getChild("rating", getRSSNamespace());
        if (e != null) {
            channel.setRating(e.getText());
        }
        e = eChannel.getChild("copyright", getRSSNamespace());
        if (e != null) {
            channel.setCopyright(e.getText());
        }
        e = eChannel.getChild("pubDate", getRSSNamespace());
        if (e != null) {
            channel.setPubDate(DateParser.parseDate(e.getText(), locale));
        }
        e = eChannel.getChild("lastBuildDate", getRSSNamespace());
        if (e != null) {
            channel.setLastBuildDate(DateParser.parseDate(e.getText(), locale));
        }
        e = eChannel.getChild("docs", getRSSNamespace());
        if (e != null) {
            channel.setDocs(e.getText());
        }
        e = eChannel.getChild("generator", getRSSNamespace());
        if (e != null) {
            channel.setGenerator(e.getText());
        }
        e = eChannel.getChild("managingEditor", getRSSNamespace());
        if (e != null) {
            channel.setManagingEditor(e.getText());
        }
        e = eChannel.getChild("webMaster", getRSSNamespace());
        if (e != null) {
            channel.setWebMaster(e.getText());
        }
        e = eChannel.getChild("skipHours");
        if (e != null) {
            final List<Integer> skipHours = new ArrayList<Integer>();
            final List<Element> eHours = e.getChildren("hour", getRSSNamespace());
            for (int i = 0; i < eHours.size(); i++) {
                final Element eHour = eHours.get(i);
                skipHours.add(new Integer(eHour.getText().trim()));
            }
            channel.setSkipHours(skipHours);
        }

        e = eChannel.getChild("skipDays");
        if (e != null) {
            final List<String> skipDays = new ArrayList<String>();
            final List<Element> eDays = e.getChildren("day", getRSSNamespace());
            for (int i = 0; i < eDays.size(); i++) {
                final Element eDay = eDays.get(i);
                skipDays.add(eDay.getText().trim());
            }
            channel.setSkipDays(skipDays);
        }
        return channel;
    }

    /**
     * Parses the root element of an RSS document looking for image information.
     * <p/>
     * It first invokes super.parseImage and then parses and injects the
     * following properties if present: url, link, width, height and
     * description.
     * <p/>
     * 
     * @param rssRoot the root element of the RSS document to parse for image
     *            information.
     * @return the parsed RSSImage bean.
     */
    @Override
    protected Image parseImage(final Element rssRoot) {
        final Image image = super.parseImage(rssRoot);
        if (image != null) {
            final Element eImage = getImage(rssRoot);
            Element e = eImage.getChild("width", getRSSNamespace());
            if (e != null) {
                final Integer val = NumberParser.parseInt(e.getText());
                if (val != null) {
                    image.setWidth(val.intValue());
                }
            }
            e = eImage.getChild("height", getRSSNamespace());
            if (e != null) {
                final Integer val = NumberParser.parseInt(e.getText());
                if (val != null) {
                    image.setHeight(val.intValue());
                }
            }
            e = eImage.getChild("description", getRSSNamespace());
            if (e != null) {
                image.setDescription(e.getText());
            }
        }
        return image;
    }

    /**
     * It looks for the 'item' elements under the 'channel' elemment.
     */
    @Override
    protected List<Element> getItems(final Element rssRoot) {
        final Element eChannel = rssRoot.getChild("channel", getRSSNamespace());
        final List<Element> emptyList = Collections.emptyList();
        if (eChannel != null) {
            return eChannel.getChildren("item", getRSSNamespace());
        } else {
            return emptyList;
        }
    }

    /**
     * It looks for the 'image' elements under the 'channel' elemment.
     */
    @Override
    protected Element getImage(final Element rssRoot) {
        final Element eChannel = rssRoot.getChild("channel", getRSSNamespace());
        if (eChannel != null) {
            return eChannel.getChild("image", getRSSNamespace());
        } else {
            return null;
        }
    }

    /**
     * To be overriden by RSS 0.91 Netscape parser
     */
    protected String getTextInputLabel() {
        return "textInput";
    }

    /**
     * It looks for the 'textinput' elements under the 'channel' elemment.
     */
    @Override
    protected Element getTextInput(final Element rssRoot) {
        final String elementName = getTextInputLabel();
        final Element eChannel = rssRoot.getChild("channel", getRSSNamespace());
        if (eChannel != null) {
            return eChannel.getChild(elementName, getRSSNamespace());
        } else {
            return null;
        }
    }

    /**
     * Parses an item element of an RSS document looking for item information.
     * <p/>
     * It first invokes super.parseItem and then parses and injects the
     * description property if present.
     * <p/>
     * 
     * @param rssRoot the root element of the RSS document in case it's needed
     *            for context.
     * @param eItem the item element to parse.
     * @return the parsed RSSItem bean.
     */
    @Override
    protected Item parseItem(final Element rssRoot, final Element eItem, final Locale locale) {
        final Item item = super.parseItem(rssRoot, eItem, locale);
        final Element e = eItem.getChild("description", getRSSNamespace());
        if (e != null) {
            item.setDescription(parseItemDescription(rssRoot, e));
        }
        final Element ce = eItem.getChild("encoded", getContentNamespace());
        if (ce != null) {
            final Content content = new Content();
            content.setType(Content.HTML);
            content.setValue(ce.getText());
            item.setContent(content);
        }
        return item;
    }

    protected Description parseItemDescription(final Element rssRoot, final Element eDesc) {
        final Description desc = new Description();
        desc.setType("text/plain");
        desc.setValue(eDesc.getText());
        return desc;
    }

}
