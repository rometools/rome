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

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Image;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.io.FeedException;

/**
 * Feed Generator for RSS 0.91
 * <p/>
 */
public class RSS091UserlandGenerator extends RSS090Generator {

    private final String version;

    public RSS091UserlandGenerator() {
        this("rss_0.91U", "0.91");
    }

    protected RSS091UserlandGenerator(final String type, final String version) {
        super(type);
        this.version = version;
    }

    @Override
    protected Namespace getFeedNamespace() {
        return Namespace.NO_NAMESPACE;
    }

    /**
     * To be overriden by RSS 0.91 Netscape and RSS 0.94
     */
    protected boolean isHourFormat24() {
        return true;
    }

    protected String getVersion() {
        return version;
    }

    @Override
    protected void addChannel(final Channel channel, final Element parent) throws FeedException {

        super.addChannel(channel, parent);

        final Element eChannel = parent.getChild("channel", getFeedNamespace());

        addImage(channel, eChannel);
        addTextInput(channel, eChannel);
        addItems(channel, eChannel);

    }

    @Override
    protected void checkChannelConstraints(final Element eChannel) throws FeedException {

        checkNotNullAndLength(eChannel, "title", 1, 100);
        checkNotNullAndLength(eChannel, "description", 1, 500);
        checkNotNullAndLength(eChannel, "link", 1, 500);
        checkNotNullAndLength(eChannel, "language", 2, 5);
        checkLength(eChannel, "rating", 20, 500);
        checkLength(eChannel, "copyright", 1, 100);
        checkLength(eChannel, "pubDate", 1, 100);
        checkLength(eChannel, "lastBuildDate", 1, 100);
        checkLength(eChannel, "docs", 1, 500);
        checkLength(eChannel, "managingEditor", 1, 100);
        checkLength(eChannel, "webMaster", 1, 100);

        final Element skipHours = eChannel.getChild("skipHours");

        if (skipHours != null) {

            final List<Element> hours = skipHours.getChildren();
            for (final Element hour : hours) {

                final int value = Integer.parseInt(hour.getText().trim());

                if (isHourFormat24()) {
                    if (value < 1 || value > 24) {
                        throw new FeedException("Invalid hour value " + value + ", it must be between 1 and 24");
                    }
                } else {
                    if (value < 0 || value > 23) {
                        throw new FeedException("Invalid hour value " + value + ", it must be between 0 and 23");
                    }
                }

            }

        }

    }

    @Override
    protected void checkImageConstraints(final Element eImage) throws FeedException {
        checkNotNullAndLength(eImage, "title", 1, 100);
        checkNotNullAndLength(eImage, "url", 1, 500);
        checkLength(eImage, "link", 1, 500);
        checkLength(eImage, "width", 1, 3);
        checkLength(eImage, "width", 1, 3);
        checkLength(eImage, "description", 1, 100);
    }

    @Override
    protected void checkItemConstraints(final Element eItem) throws FeedException {
        checkNotNullAndLength(eItem, "title", 1, 100);
        checkNotNullAndLength(eItem, "link", 1, 500);
        checkLength(eItem, "description", 1, 500);
    }

    @Override
    protected void checkTextInputConstraints(final Element eTextInput) throws FeedException {
        checkNotNullAndLength(eTextInput, "title", 1, 100);
        checkNotNullAndLength(eTextInput, "description", 1, 500);
        checkNotNullAndLength(eTextInput, "name", 1, 20);
        checkNotNullAndLength(eTextInput, "link", 1, 500);
    }

    @Override
    protected Document createDocument(final Element root) {
        return new Document(root);
    }

    @Override
    protected Element createRootElement(final Channel channel) {
        final Element root = new Element("rss", getFeedNamespace());
        final Attribute version = new Attribute("version", getVersion());
        root.setAttribute(version);
        root.addNamespaceDeclaration(getContentNamespace());
        generateModuleNamespaceDefs(root);
        return root;
    }

    protected Element generateSkipDaysElement(final List<String> days) {
        final Element skipDaysElement = new Element("skipDays");
        for (final String day : days) {
            skipDaysElement.addContent(generateSimpleElement("day", day.toString()));
        }
        return skipDaysElement;
    }

    protected Element generateSkipHoursElement(final List<Integer> hours) {
        final Element skipHoursElement = new Element("skipHours", getFeedNamespace());
        for (final Integer hour : hours) {
            skipHoursElement.addContent(generateSimpleElement("hour", hour.toString()));
        }
        return skipHoursElement;
    }

    @Override
    protected void populateChannel(final Channel channel, final Element eChannel) {

        super.populateChannel(channel, eChannel);

        final String language = channel.getLanguage();
        if (language != null) {
            eChannel.addContent(generateSimpleElement("language", language));
        }

        final String rating = channel.getRating();
        if (rating != null) {
            eChannel.addContent(generateSimpleElement("rating", rating));
        }

        final String copyright = channel.getCopyright();
        if (copyright != null) {
            eChannel.addContent(generateSimpleElement("copyright", copyright));
        }

        final Date pubDate = channel.getPubDate();
        if (pubDate != null) {
            eChannel.addContent(generateSimpleElement("pubDate", DateParser.formatRFC822(pubDate, Locale.US)));
        }

        final Date lastBuildDate = channel.getLastBuildDate();
        if (lastBuildDate != null) {
            eChannel.addContent(generateSimpleElement("lastBuildDate", DateParser.formatRFC822(lastBuildDate, Locale.US)));
        }

        final String docs = channel.getDocs();
        if (docs != null) {
            eChannel.addContent(generateSimpleElement("docs", docs));
        }

        final String managingEditor = channel.getManagingEditor();
        if (managingEditor != null) {
            eChannel.addContent(generateSimpleElement("managingEditor", managingEditor));
        }

        final String webMaster = channel.getWebMaster();
        if (webMaster != null) {
            eChannel.addContent(generateSimpleElement("webMaster", webMaster));
        }

        final List<Integer> skipHours = channel.getSkipHours();
        if (skipHours != null && !skipHours.isEmpty()) {
            eChannel.addContent(generateSkipHoursElement(skipHours));
        }

        final List<String> skipDays = channel.getSkipDays();
        if (skipDays != null && !skipDays.isEmpty()) {
            eChannel.addContent(generateSkipDaysElement(skipDays));
        }

    }

    @Override
    protected void populateFeed(final Channel channel, final Element parent) throws FeedException {
        addChannel(channel, parent);
    }

    @Override
    protected void populateImage(final Image image, final Element eImage) {

        super.populateImage(image, eImage);

        final Integer width = image.getWidth();
        if (width != null) {
            eImage.addContent(generateSimpleElement("width", String.valueOf(width)));
        }

        final Integer height = image.getHeight();
        if (height != null) {
            eImage.addContent(generateSimpleElement("height", String.valueOf(height)));
        }

        final String description = image.getDescription();
        if (description != null) {
            eImage.addContent(generateSimpleElement("description", description));
        }

    }

    @Override
    protected void populateItem(final Item item, final Element eItem, final int index) {

        super.populateItem(item, eItem, index);

        final Description description = item.getDescription();
        if (description != null) {
            eItem.addContent(generateSimpleElement("description", description.getValue()));
        }

        final Namespace contentNamespace = getContentNamespace();
        final Content content = item.getContent();
        if (item.getModule(contentNamespace.getURI()) == null && content != null) {
            final Element elem = new Element("encoded", contentNamespace);
            elem.addContent(content.getValue());
            eItem.addContent(elem);
        }

    }

}
