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

import com.sun.syndication.feed.rss.Category;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Guid;
import com.sun.syndication.feed.rss.Item;
import org.jdom.Element;

import java.util.List;


/**
 * Feed Generator for RSS 2.0
 * <p/>
 *
 * @author Elaine Chien
 *
 */

public class RSS20Generator extends RSS094Generator {

    public RSS20Generator() {
        this("rss_2.0","2.0");
    }

    protected RSS20Generator(String feedType,String version) {
        super(feedType,version);
    }

    protected void populateChannel(Channel channel,Element eChannel) {
        super.populateChannel(channel,eChannel);

        String generator = channel.getGenerator();
        if (generator != null) {
            eChannel.addContent(generateSimpleElement("generator", generator));
        }

        int ttl = channel.getTtl();
        if (ttl>-1) {
            eChannel.addContent(generateSimpleElement("ttl", String.valueOf(ttl)));
        }

        List categories = channel.getCategories();
        for(int i = 0; i < categories.size(); i++) {
            eChannel.addContent(generateCategoryElement((Category)categories.get(i)));
        }

    }

    public void populateItem(Item item, Element eItem, int index) {
        super.populateItem(item,eItem, index);

        Element eDescription = eItem.getChild("description",getFeedNamespace());
        if (eDescription != null) eDescription.removeAttribute("type");

        String author = item.getAuthor();
        if (author != null) {
            eItem.addContent(generateSimpleElement("author", author));
        }

        String comments = item.getComments();
        if (comments != null) {
            eItem.addContent(generateSimpleElement("comments", comments));
        }

        Guid guid = item.getGuid();
        if (guid != null) {
            Element eGuid = generateSimpleElement("guid",guid.getValue());
            if (!guid.isPermaLink()) {
                eGuid.setAttribute("isPermaLink", "false");
            }
            eItem.addContent(eGuid);
        }
    }

}
