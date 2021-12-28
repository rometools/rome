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

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Image;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.rss.TextInput;
import com.rometools.rome.io.FeedException;

/**
 * Feed Generator for RSS 0.90
 * <p/>
 */
public class RSS090Generator extends BaseWireFeedGenerator {

    private static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    private static final String RSS_URI = "http://my.netscape.com/rdf/simple/0.9/";
    private static final String CONTENT_URI = "http://purl.org/rss/1.0/modules/content/";

    private static final Namespace RDF_NS = Namespace.getNamespace("rdf", RDF_URI);
    private static final Namespace RSS_NS = Namespace.getNamespace(RSS_URI);
    private static final Namespace CONTENT_NS = Namespace.getNamespace("content", CONTENT_URI);

    public RSS090Generator() {
        this("rss_0.9");
    }

    protected RSS090Generator(final String type) {
        super(type);
    }

    @Override
    public Document generate(final WireFeed feed) throws FeedException {
        final Channel channel = (Channel) feed;
        final Element root = createRootElement(channel);
        populateFeed(channel, root);
        purgeUnusedNamespaceDeclarations(root);
        return createDocument(root);
    }

    protected Namespace getFeedNamespace() {
        return RSS_NS;
    }

    protected Namespace getRDFNamespace() {
        return RDF_NS;
    }

    protected Namespace getContentNamespace() {
        return CONTENT_NS;
    }

    protected Document createDocument(final Element root) {
        return new Document(root);
    }

    protected Element createRootElement(final Channel channel) {
        final Element root = new Element("RDF", getRDFNamespace());
        root.addNamespaceDeclaration(getFeedNamespace());
        root.addNamespaceDeclaration(getRDFNamespace());
        root.addNamespaceDeclaration(getContentNamespace());
        generateModuleNamespaceDefs(root);
        return root;
    }

    protected void populateFeed(final Channel channel, final Element parent) throws FeedException {
        addChannel(channel, parent);
        addImage(channel, parent);
        addTextInput(channel, parent);
        addItems(channel, parent);
        generateForeignMarkup(parent, channel.getForeignMarkup());
    }

    protected void addChannel(final Channel channel, final Element parent) throws FeedException {
        final Element eChannel = new Element("channel", getFeedNamespace());
        populateChannel(channel, eChannel);
        checkChannelConstraints(eChannel);
        parent.addContent(eChannel);
        generateFeedModules(channel.getModules(), eChannel);
    }

    /**
     * Populates the given channel with parsed data from the ROME element that holds the channel
     * data.
     *
     * @param channel the channel into which parsed data will be added.
     * @param eChannel the XML element that holds the data for the channel.
     */
    protected void populateChannel(final Channel channel, final Element eChannel) {
        final String title = channel.getTitle();
        if (title != null) {
            eChannel.addContent(generateSimpleElement("title", title));
        }
        final String link = channel.getLink();
        if (link != null) {
            eChannel.addContent(generateSimpleElement("link", link));
        }
        final String description = channel.getDescription();
        if (description != null) {
            eChannel.addContent(generateSimpleElement("description", description));
        }
    }

    // maxLen == -1 means unlimited.
    protected void checkNotNullAndLength(final Element parent, final String childName, final int minLen, final int maxLen) throws FeedException {
        final Element child = parent.getChild(childName, getFeedNamespace());
        if (child == null) {
            throw new FeedException("Invalid " + getType() + " feed, missing " + parent.getName() + " " + childName);
        }
        checkLength(parent, childName, minLen, maxLen);
    }

    // maxLen == -1 means unlimited.
    protected void checkLength(final Element parent, final String childName, final int minLen, final int maxLen) throws FeedException {
        final Element child = parent.getChild(childName, getFeedNamespace());
        if (child != null) {
            if (minLen > 0 && child.getText().length() < minLen) {
                throw new FeedException("Invalid " + getType() + " feed, " + parent.getName() + " " + childName + "short of " + minLen + " length");
            }
            if (maxLen > -1 && child.getText().length() > maxLen) {
                throw new FeedException("Invalid " + getType() + " feed, " + parent.getName() + " " + childName + "exceeds " + maxLen + " length");
            }
        }
    }

    protected void addImage(final Channel channel, final Element parent) throws FeedException {
        final Image image = channel.getImage();
        if (image != null) {
            final Element eImage = new Element("image", getFeedNamespace());
            populateImage(image, eImage);
            checkImageConstraints(eImage);
            parent.addContent(eImage);
        }
    }

    protected void populateImage(final Image image, final Element eImage) {
        final String title = image.getTitle();
        if (title != null) {
            eImage.addContent(generateSimpleElement("title", title));
        }
        final String url = image.getUrl();
        if (url != null) {
            eImage.addContent(generateSimpleElement("url", url));
        }
        final String link = image.getLink();
        if (link != null) {
            eImage.addContent(generateSimpleElement("link", link));
        }
    }

    // Thxs DW for this one
    protected String getTextInputLabel() {
        return "textInput";
    }

    protected void addTextInput(final Channel channel, final Element parent) throws FeedException {
        final TextInput textInput = channel.getTextInput();
        if (textInput != null) {
            final Element eTextInput = new Element(getTextInputLabel(), getFeedNamespace());
            populateTextInput(textInput, eTextInput);
            checkTextInputConstraints(eTextInput);
            parent.addContent(eTextInput);
        }
    }

    protected void populateTextInput(final TextInput textInput, final Element eTextInput) {
        final String title = textInput.getTitle();
        if (title != null) {
            eTextInput.addContent(generateSimpleElement("title", title));
        }
        final String description = textInput.getDescription();
        if (description != null) {
            eTextInput.addContent(generateSimpleElement("description", description));
        }
        final String name = textInput.getName();
        if (name != null) {
            eTextInput.addContent(generateSimpleElement("name", name));
        }
        final String link = textInput.getLink();
        if (link != null) {
            eTextInput.addContent(generateSimpleElement("link", link));
        }
    }

    protected void addItems(final Channel channel, final Element parent) throws FeedException {
        final List<Item> items = channel.getItems();
        for (int i = 0; i < items.size(); i++) {
            addItem(items.get(i), parent, i);
        }
        checkItemsConstraints(parent);
    }

    protected void addItem(final Item item, final Element parent, final int index) throws FeedException {
        final Element eItem = new Element("item", getFeedNamespace());
        populateItem(item, eItem, index);
        checkItemConstraints(eItem);
        generateItemModules(item.getModules(), eItem);
        parent.addContent(eItem);
    }

    protected void populateItem(final Item item, final Element eItem, final int index) {
        final String title = item.getTitle();
        if (title != null) {
            eItem.addContent(generateSimpleElement("title", title));
        }
        final String link = item.getLink();
        if (link != null) {
            eItem.addContent(generateSimpleElement("link", link));
        }
        generateForeignMarkup(eItem, item.getForeignMarkup());
    }

    protected Element generateSimpleElement(final String name, final String value) {
        final Element element = new Element(name, getFeedNamespace());
        element.addContent(value);
        return element;
    }

    protected void checkChannelConstraints(final Element eChannel) throws FeedException {
        checkNotNullAndLength(eChannel, "title", 0, 40);
        checkNotNullAndLength(eChannel, "description", 0, 500);
        checkNotNullAndLength(eChannel, "link", 0, 500);
    }

    protected void checkImageConstraints(final Element eImage) throws FeedException {
        checkNotNullAndLength(eImage, "title", 0, 40);
        checkNotNullAndLength(eImage, "url", 0, 500);
        checkNotNullAndLength(eImage, "link", 0, 500);
    }

    protected void checkTextInputConstraints(final Element eTextInput) throws FeedException {
        checkNotNullAndLength(eTextInput, "title", 0, 40);
        checkNotNullAndLength(eTextInput, "description", 0, 100);
        checkNotNullAndLength(eTextInput, "name", 0, 500);
        checkNotNullAndLength(eTextInput, "link", 0, 500);
    }

    protected void checkItemsConstraints(final Element parent) throws FeedException {
        final int count = parent.getChildren("item", getFeedNamespace()).size();
        if (count < 1 || count > 15) {
            throw new FeedException("Invalid " + getType() + " feed, item count is " + count + " it must be between 1 an 15");
        }
    }

    protected void checkItemConstraints(final Element eItem) throws FeedException {
        checkNotNullAndLength(eItem, "title", 0, 100);
        checkNotNullAndLength(eItem, "link", 0, 500);
    }

}
