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

import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.io.FeedException;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.List;

/**
 * Feed Generator for RSS 1.0
 * <p/>
 *
 * @author Elaine Chien
 *
 */

public class RSS10Generator extends RSS090Generator {
    
    private static final String RSS_URI = "http://purl.org/rss/1.0/";
    private static final Namespace RSS_NS = Namespace.getNamespace(RSS_URI);
    
    public RSS10Generator() {
        super("rss_1.0");
    }

    protected RSS10Generator(String feedType) {
        super(feedType);
    }

    protected Namespace getFeedNamespace() {
        return RSS_NS;
    }

    protected void populateChannel(Channel channel,Element eChannel) {
        super.populateChannel(channel,eChannel);
        if (channel.getUri() != null) {
            eChannel.setAttribute("about", channel.getUri(), getRDFNamespace());
        }
        List items = channel.getItems();
        if (items.size()>0) {
            Element eItems = new Element("items",getFeedNamespace());
            Element eSeq = new Element("Seq",getRDFNamespace());
            for (int i=0;i<items.size();i++) {
                Item item = (Item) items.get(i);
                Element eLi = new Element("li",getRDFNamespace());
                String uri = item.getUri();
                if (uri!=null) {
                    eLi.setAttribute("resource",uri,getRDFNamespace());
                }
                eSeq.addContent(eLi);
            }
            eItems.addContent(eSeq);
            eChannel.addContent(eItems);
        }
    }
    
    protected void populateItem(Item item, Element eItem, int index) {
        super.populateItem(item,eItem, index);
        String link = item.getLink();
        String uri = item.getUri();
        
        if (uri != null) {
            eItem.setAttribute("about", uri, getRDFNamespace());
        } else if (link != null) {
            eItem.setAttribute("about", link, getRDFNamespace());
        }
        
        Description description = item.getDescription();
        if (description!=null) {
            eItem.addContent(generateSimpleElement("description", description.getValue()));
        }
        if (item.getModule(getContentNamespace().getURI()) == null && item.getContent() != null) {
            Element elem = new Element("encoded", getContentNamespace());
            elem.addContent(item.getContent().getValue());
            eItem.addContent(elem);
        }
    }

    protected void checkChannelConstraints(Element eChannel) throws FeedException {
        checkNotNullAndLength(eChannel,"title", 0, -1);
        checkNotNullAndLength(eChannel,"description", 0, -1);
        checkNotNullAndLength(eChannel,"link", 0, -1);
    }

    protected void checkImageConstraints(Element eImage) throws FeedException {
        checkNotNullAndLength(eImage,"title", 0, -1);
        checkNotNullAndLength(eImage,"url", 0, -1);
        checkNotNullAndLength(eImage,"link", 0, -1);
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
        checkNotNullAndLength(eItem,"title", 0, -1);
        checkNotNullAndLength(eItem,"link", 0, -1);
    }

}

