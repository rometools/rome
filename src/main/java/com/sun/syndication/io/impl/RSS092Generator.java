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

import com.sun.syndication.feed.rss.*;
import com.sun.syndication.io.FeedException;
import org.jdom.Attribute;
import org.jdom.Element;

import java.util.List;


/**
 * Feed Generator for RSS 0.92
 * <p/>
 *
 * @author Elaine Chien
 *
 */

public class RSS092Generator extends RSS091UserlandGenerator {

    public RSS092Generator() {
        this("rss_0.92","0.92");
    }

    protected RSS092Generator(String type,String version) {
        super(type,version);
    }

    protected void populateChannel(Channel channel,Element eChannel) {
        super.populateChannel(channel,eChannel);

        Cloud cloud = channel.getCloud();
        if (cloud!=null) {
            eChannel.addContent(generateCloud(cloud));
        }
    }

    protected Element generateCloud(Cloud cloud) {
        Element eCloud = new Element("cloud",getFeedNamespace());

        if (cloud.getDomain() != null) {
            eCloud.setAttribute(new Attribute("domain", cloud.getDomain()));
        }

        if (cloud.getPort() != 0) {
            eCloud.setAttribute(new Attribute("port", String.valueOf(cloud.getPort())));
        }

        if (cloud.getPath() != null) {
            eCloud.setAttribute(new Attribute("path", cloud.getPath()));
        }

        if (cloud.getRegisterProcedure() != null) {
            eCloud.setAttribute(new Attribute("registerProcedure", cloud.getRegisterProcedure()));
        }

        if (cloud.getProtocol() != null) {
            eCloud.setAttribute(new Attribute("protocol", cloud.getProtocol()));
        }
        return eCloud;
    }

    // Another one to thanks DW for
    protected int getNumberOfEnclosures(List enclosures) {
        return (enclosures.size()>0) ? 1 : 0;
    }

    protected void populateItem(Item item, Element eItem, int index) {
        super.populateItem(item,eItem, index);

        Source source =item.getSource();
        if (source != null) {
            eItem.addContent(generateSourceElement(source));
        }

        List enclosures = item.getEnclosures();
        for(int i = 0; i < getNumberOfEnclosures(enclosures); i++) {
            eItem.addContent(generateEnclosure((Enclosure)enclosures.get(i)));
        }

        List categories = item.getCategories();
        for(int i = 0; i < categories.size(); i++) {
            eItem.addContent(generateCategoryElement((Category)categories.get(i)));
        }
    }

    protected Element generateSourceElement(Source source) {
        Element sourceElement = new Element("source",getFeedNamespace());
        if (source.getUrl() != null) {
            sourceElement.setAttribute(new Attribute("url", source.getUrl()));
        }
        sourceElement.addContent(source.getValue());
        return sourceElement;
    }

    protected Element generateEnclosure(Enclosure enclosure) {
        Element enclosureElement = new Element("enclosure",getFeedNamespace());
        if (enclosure.getUrl() != null) {
            enclosureElement.setAttribute("url", enclosure.getUrl());
        }
        if (enclosure.getLength() != 0) {
            enclosureElement.setAttribute("length", String.valueOf(enclosure.getLength()));
        }
        if (enclosure.getType() != null) {
            enclosureElement.setAttribute("type", enclosure.getType());
        }
        return enclosureElement;
    }

    protected Element generateCategoryElement(Category category) {
        Element categoryElement = new Element("category",getFeedNamespace());
        if (category.getDomain() != null) {
            categoryElement.setAttribute("domain", category.getDomain());
        }
        categoryElement.addContent(category.getValue());
        return categoryElement;
    }


    protected void checkChannelConstraints(Element eChannel) throws FeedException {
        checkNotNullAndLength(eChannel,"title", 0, -1);
        checkNotNullAndLength(eChannel,"description", 0, -1);
        checkNotNullAndLength(eChannel,"link", 0, -1);
    }

    protected void checkImageConstraints(Element eImage) throws FeedException {
        checkNotNullAndLength(eImage,"title", 0, -1);
        checkNotNullAndLength(eImage,"url", 0, -1);
    }

    protected void checkTextInputConstraints(Element eTextInput) throws FeedException {
        checkNotNullAndLength(eTextInput,"title", 0, -1);
        checkNotNullAndLength(eTextInput,"description", 0, -1);
        checkNotNullAndLength(eTextInput,"name", 0, -1);
        checkNotNullAndLength(eTextInput,"link", 0, -1);
    }

    protected void checkItemsConstraints(Element parent) throws FeedException {
    }

    protected void checkItemConstraints(Element eItem) throws FeedException {
    }

}
