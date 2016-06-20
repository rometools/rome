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
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Image;
import com.rometools.rome.feed.rss.Item;

public class RSS091UserlandParser extends RSS090Parser {

    public RSS091UserlandParser() {
        this("rss_0.91U");
    }

    protected RSS091UserlandParser(final String type) {
        super(type, null);
    }

    @Override
    public boolean isMyType(final Document document) {
        final Element rssRoot = document.getRootElement();
        final Attribute version = rssRoot.getAttribute("version");
        return rssRoot.getName().equals("rss") && version != null && version.getValue().equals(getRSSVersion());
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
     * It first invokes super.parseChannel and then parses and injects the following properties if
     * present: language, pubDate, rating and copyright.
     * <p/>
     *
     * @param rssRoot the root element of the RSS document to parse.
     * @return the parsed Channel bean.
     */
    @Override
    protected WireFeed parseChannel(final Element rssRoot, final Locale locale) {

        final Channel channel = (Channel) super.parseChannel(rssRoot, locale);

        final Element eChannel = rssRoot.getChild("channel", getRSSNamespace());

        final Element language = eChannel.getChild("language", getRSSNamespace());
        if (language != null) {
            channel.setLanguage(language.getText());
        }

        final Element atinge = eChannel.getChild("rating", getRSSNamespace());
        if (atinge != null) {
            channel.setRating(atinge.getText());
        }

        final Element copyright = eChannel.getChild("copyright", getRSSNamespace());
        if (copyright != null) {
            channel.setCopyright(copyright.getText());
        }

        final Element pubDate = eChannel.getChild("pubDate", getRSSNamespace());
        if (pubDate != null) {
            channel.setPubDate(DateParser.parseDate(pubDate.getText(), locale));
        }

        final Element lastBuildDate = eChannel.getChild("lastBuildDate", getRSSNamespace());
        if (lastBuildDate != null) {
            channel.setLastBuildDate(DateParser.parseDate(lastBuildDate.getText(), locale));
        }

        final Element docs = eChannel.getChild("docs", getRSSNamespace());
        if (docs != null) {
            channel.setDocs(docs.getText());
        }

        final Element generator = eChannel.getChild("generator", getRSSNamespace());
        if (generator != null) {
            channel.setGenerator(generator.getText());
        }

        final Element managingEditor = eChannel.getChild("managingEditor", getRSSNamespace());
        if (managingEditor != null) {
            channel.setManagingEditor(managingEditor.getText());
        }

        final Element webMaster = eChannel.getChild("webMaster", getRSSNamespace());
        if (webMaster != null) {
            channel.setWebMaster(webMaster.getText());
        }

        final Element eSkipHours = eChannel.getChild("skipHours");
        if (eSkipHours != null) {
            final List<Integer> skipHours = new ArrayList<Integer>();
            final List<Element> eHours = eSkipHours.getChildren("hour", getRSSNamespace());
            for (final Element eHour : eHours) {
                skipHours.add(new Integer(eHour.getText().trim()));
            }
            channel.setSkipHours(skipHours);
        }

        final Element eSkipDays = eChannel.getChild("skipDays");
        if (eSkipDays != null) {
            final List<String> skipDays = new ArrayList<String>();
            final List<Element> eDays = eSkipDays.getChildren("day", getRSSNamespace());
            for (final Element eDay : eDays) {
                skipDays.add(eDay.getText().trim());
            }
            channel.setSkipDays(skipDays);
        }

        return channel;
    }

    /**
     * Parses the root element of an RSS document looking for image information.
     * <p/>
     * It first invokes super.parseImage and then parses and injects the following properties if
     * present: url, link, width, height and description.
     * <p/>
     *
     * @param rssRoot the root element of the RSS document to parse for image information.
     * @return the parsed RSSImage bean.
     */
    @Override
    protected Image parseImage(final Element rssRoot) {

        final Image image = super.parseImage(rssRoot);
        if (image != null) {

            final Element eImage = getImage(rssRoot);

            final Element width = eImage.getChild("width", getRSSNamespace());
            if (width != null) {
                final Integer val = NumberParser.parseInt(width.getText());
                if (val != null) {
                    image.setWidth(val);
                }
            }

            final Element height = eImage.getChild("height", getRSSNamespace());
            if (height != null) {
                final Integer val = NumberParser.parseInt(height.getText());
                if (val != null) {
                    image.setHeight(val);
                }
            }

            final Element description = eImage.getChild("description", getRSSNamespace());
            if (description != null) {
                image.setDescription(description.getText());
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

        if (eChannel != null) {
            return eChannel.getChildren("item", getRSSNamespace());
        } else {
            return Collections.emptyList();
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

        return item;

    }

    protected Description parseItemDescription(final Element rssRoot, final Element eDesc) {
        final Description desc = new Description();
        desc.setType("text/plain");
        desc.setValue(eDesc.getText());
        return desc;
    }

}
