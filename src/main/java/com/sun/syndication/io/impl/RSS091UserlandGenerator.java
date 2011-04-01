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

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Image;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.io.FeedException;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.List;
import java.util.Date;

/**
 * Feed Generator for RSS 0.91
 * <p/>
 *
 * @author Elaine Chien
 *
 */
public class RSS091UserlandGenerator extends RSS090Generator {
    private String _version;

    public RSS091UserlandGenerator() {
        this("rss_0.91U","0.91");
    }

    protected RSS091UserlandGenerator(String type,String version) {
        super(type);
        _version = version;
    }

    protected String getVersion() {
        return _version;
    }

    protected Namespace getFeedNamespace() {
        return Namespace.NO_NAMESPACE;
    }

    protected Document createDocument(Element root) {
        return new Document(root);
    }

    protected Element createRootElement(Channel channel) {
        Element root = new Element("rss",getFeedNamespace());
        Attribute version = new Attribute("version", getVersion());
        root.setAttribute(version);
        root.addNamespaceDeclaration(getContentNamespace());
        generateModuleNamespaceDefs(root);
        return root;
    }

    protected void populateFeed(Channel channel,Element parent) throws FeedException  {
        addChannel(channel,parent);
    }

    protected void addChannel(Channel channel,Element parent) throws FeedException {
        super.addChannel(channel,parent);
        Element eChannel = parent.getChild("channel",getFeedNamespace());

        addImage(channel,eChannel);
        addTextInput(channel,eChannel);
        addItems(channel,eChannel);
    }

    protected void populateChannel(Channel channel,Element eChannel) {
        super.populateChannel(channel,eChannel);
        String language = channel.getLanguage();
        if (language != null) {
            eChannel.addContent(generateSimpleElement("language", language));
        }

        String rating = channel.getRating();
        if (rating != null) {
            eChannel.addContent(generateSimpleElement("rating", rating));
        }

        String copyright = channel.getCopyright();
        if (copyright != null) {
            eChannel.addContent(generateSimpleElement("copyright", copyright));
        }

        Date pubDate = channel.getPubDate();
        if (pubDate != null) {
            eChannel.addContent(generateSimpleElement("pubDate", DateParser.formatRFC822(pubDate)));
        }

        Date lastBuildDate = channel.getLastBuildDate();
        if (lastBuildDate != null) {
            eChannel.addContent(generateSimpleElement("lastBuildDate", DateParser.formatRFC822(lastBuildDate)));
        }

        String docs = channel.getDocs();
        if (docs != null) {
            eChannel.addContent(generateSimpleElement("docs", docs));
        }

        String managingEditor = channel.getManagingEditor();
        if (managingEditor != null) {
            eChannel.addContent(generateSimpleElement("managingEditor", managingEditor));
        }

        String webMaster = channel.getWebMaster();
        if (webMaster != null) {
            eChannel.addContent(generateSimpleElement("webMaster", webMaster));
        }

        List skipHours = channel.getSkipHours();
        if (skipHours != null && skipHours.size()>0) {
            eChannel.addContent(generateSkipHoursElement(skipHours));
        }

        List skipDays = channel.getSkipDays();
        if (skipDays != null && skipDays.size()>0) {
            eChannel.addContent(generateSkipDaysElement(skipDays));
        }
    }

    protected Element generateSkipHoursElement(List hours) {
        Element skipHoursElement = new Element("skipHours",getFeedNamespace());
        for (int i = 0; i < hours.size(); i++) {
            skipHoursElement.addContent(generateSimpleElement("hour", hours.get(i).toString()));
        }
        return skipHoursElement;
    }

    protected Element generateSkipDaysElement(List days) {
        Element skipDaysElement = new Element("skipDays");
        for (int i = 0; i < days.size(); i++) {
            skipDaysElement.addContent(generateSimpleElement("day", days.get(i).toString()));
        }
        return skipDaysElement;
    }

    protected void populateImage(Image image,Element eImage) {
        super.populateImage(image,eImage);

        int width = image.getWidth();
        if (width>-1) {
            eImage.addContent(generateSimpleElement("width",String.valueOf(width)));
        }
        int height = image.getHeight();
        if (height>-1) {
            eImage.addContent(generateSimpleElement("height",String.valueOf(height)));
        }

        String description = image.getDescription();
        if (description!=null) {
            eImage.addContent(generateSimpleElement("description",description));
        }
    }

    protected void populateItem(Item item, Element eItem, int index) {
        super.populateItem(item,eItem, index);
        Description description = item.getDescription();
        if (description!=null) {
            eItem.addContent(generateSimpleElement("description",description.getValue()));
        }
        if (item.getModule(getContentNamespace().getURI()) == null && item.getContent() != null) {
            Element elem = new Element("encoded", getContentNamespace());
            elem.addContent(item.getContent().getValue());
            eItem.addContent(elem);
        }
    }

    /**
     * To be overriden by RSS 0.91 Netscape and RSS 0.94
     */
    protected boolean isHourFormat24() {
        return true;
    }

    protected void checkChannelConstraints(Element eChannel) throws FeedException {
        checkNotNullAndLength(eChannel,"title", 1, 100);
        checkNotNullAndLength(eChannel,"description", 1, 500);
        checkNotNullAndLength(eChannel,"link", 1, 500);
        checkNotNullAndLength(eChannel,"language", 2, 5);

        checkLength(eChannel,"rating", 20, 500);
        checkLength(eChannel,"copyright", 1, 100);
        checkLength(eChannel,"pubDate", 1, 100);
        checkLength(eChannel,"lastBuildDate", 1, 100);
        checkLength(eChannel,"docs", 1, 500);
        checkLength(eChannel,"managingEditor", 1, 100);
        checkLength(eChannel,"webMaster", 1, 100);

        Element skipHours = eChannel.getChild("skipHours");
        if (skipHours!=null) {
            List hours = skipHours.getChildren();
            for (int i=0;i<hours.size();i++) {
                Element hour = (Element) hours.get(i);
                int value = Integer.parseInt(hour.getText().trim());
                if (isHourFormat24()) {
                    if (value<1 || value>24) {
                        throw new FeedException("Invalid hour value "+value+", it must be between 1 and 24");
                    }
                }
                else {
                    if (value<0 || value>23) {
                        throw new FeedException("Invalid hour value "+value+", it must be between 0 and 23");
                    }
                }
            }
        }
    }

    protected void checkImageConstraints(Element eImage) throws FeedException {
        checkNotNullAndLength(eImage,"title", 1, 100);
        checkNotNullAndLength(eImage,"url", 1, 500);

        checkLength(eImage,"link", 1, 500);
        checkLength(eImage,"width", 1, 3);
        checkLength(eImage,"width", 1, 3);
        checkLength(eImage,"description", 1, 100);
    }


    protected void checkTextInputConstraints(Element eTextInput) throws FeedException {
        checkNotNullAndLength(eTextInput,"title", 1, 100);
        checkNotNullAndLength(eTextInput,"description", 1, 500);
        checkNotNullAndLength(eTextInput,"name", 1, 20);
        checkNotNullAndLength(eTextInput,"link", 1, 500);
    }

    protected void checkItemConstraints(Element eItem) throws FeedException {
        checkNotNullAndLength(eItem,"title", 1, 100);
        checkNotNullAndLength(eItem,"link", 1, 500);

        checkLength(eItem,"description", 1, 500);
    }

}
