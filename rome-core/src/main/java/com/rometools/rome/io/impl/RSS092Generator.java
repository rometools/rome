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

import org.jdom2.Attribute;
import org.jdom2.Element;

import com.rometools.rome.feed.rss.Category;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Cloud;
import com.rometools.rome.feed.rss.Enclosure;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.rss.Source;
import com.rometools.rome.io.FeedException;

/**
 * Feed Generator for RSS 0.92
 */
public class RSS092Generator extends RSS091UserlandGenerator {

    public RSS092Generator() {
        this("rss_0.92", "0.92");
    }

    protected RSS092Generator(final String type, final String version) {
        super(type, version);
    }

    @Override
    protected void populateChannel(final Channel channel, final Element eChannel) {

        super.populateChannel(channel, eChannel);

        final Cloud cloud = channel.getCloud();
        if (cloud != null) {
            eChannel.addContent(generateCloud(cloud));
        }

    }

    protected Element generateCloud(final Cloud cloud) {

        final Element eCloud = new Element("cloud", getFeedNamespace());

        final String domain = cloud.getDomain();
        if (domain != null) {
            eCloud.setAttribute(new Attribute("domain", domain));
        }

        final int port = cloud.getPort();
        if (port != 0) {
            eCloud.setAttribute(new Attribute("port", String.valueOf(port)));
        }

        final String path = cloud.getPath();
        if (path != null) {
            eCloud.setAttribute(new Attribute("path", path));
        }

        final String registerProcedure = cloud.getRegisterProcedure();
        if (registerProcedure != null) {
            eCloud.setAttribute(new Attribute("registerProcedure", registerProcedure));
        }

        final String protocol = cloud.getProtocol();
        if (protocol != null) {
            eCloud.setAttribute(new Attribute("protocol", protocol));
        }

        return eCloud;

    }

    // Another one to thanks DW for
    protected int getNumberOfEnclosures(final List<Enclosure> enclosures) {
        if (!enclosures.isEmpty()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    protected void populateItem(final Item item, final Element eItem, final int index) {

        super.populateItem(item, eItem, index);

        final Source source = item.getSource();
        if (source != null) {
            eItem.addContent(generateSourceElement(source));
        }

        final List<Enclosure> enclosures = item.getEnclosures();
        for (int i = 0; i < getNumberOfEnclosures(enclosures); i++) {
            eItem.addContent(generateEnclosure(enclosures.get(i)));
        }

        final List<Category> categories = item.getCategories();
        for (final Category category : categories) {
            eItem.addContent(generateCategoryElement(category));
        }

    }

    protected Element generateSourceElement(final Source source) {

        final Element sourceElement = new Element("source", getFeedNamespace());

        final String url = source.getUrl();
        if (url != null) {
            sourceElement.setAttribute(new Attribute("url", url));
        }

        sourceElement.addContent(source.getValue());

        return sourceElement;
    }

    protected Element generateEnclosure(final Enclosure enclosure) {

        final Element enclosureElement = new Element("enclosure", getFeedNamespace());

        final String url = enclosure.getUrl();
        if (url != null) {
            enclosureElement.setAttribute("url", url);
        }

        final long length = enclosure.getLength();
        if (length != 0) {
            enclosureElement.setAttribute("length", String.valueOf(length));
        }

        final String type = enclosure.getType();
        if (type != null) {
            enclosureElement.setAttribute("type", type);
        }

        return enclosureElement;
    }

    protected Element generateCategoryElement(final Category category) {

        final Element categoryElement = new Element("category", getFeedNamespace());

        final String domain = category.getDomain();
        if (domain != null) {
            categoryElement.setAttribute("domain", domain);
        }

        categoryElement.addContent(category.getValue());

        return categoryElement;
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
    }

}
