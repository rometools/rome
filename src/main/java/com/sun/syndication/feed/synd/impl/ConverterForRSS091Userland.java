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
package com.sun.syndication.feed.synd.impl;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.module.DCModule;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Image;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndImage;
import com.sun.syndication.feed.synd.SyndPerson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 */
public class ConverterForRSS091Userland extends ConverterForRSS090 {
    public ConverterForRSS091Userland() {
        this("rss_0.91U");
    }

    protected ConverterForRSS091Userland(String type) {
        super(type);
    }

    @Override
    public void copyInto(WireFeed feed, SyndFeed syndFeed) {
        Channel channel = (Channel) feed;
        super.copyInto(channel, syndFeed);
        syndFeed.setLanguage(channel.getLanguage()); //c
        syndFeed.setCopyright(channel.getCopyright()); //c

        Date pubDate = channel.getPubDate();

        if (pubDate != null) {
            syndFeed.setPublishedDate(pubDate); //c
        } else if (channel.getLastBuildDate() != null) {
            syndFeed.setPublishedDate(channel.getLastBuildDate()); //c
        }

        String author = channel.getManagingEditor();

        if (author != null) {
            List creators = ((DCModule) syndFeed.getModule(DCModule.URI)).getCreators();

            if (!creators.contains(author)) {
                Set s = new HashSet(); // using a set to remove duplicates
                s.addAll(creators); // DC creators
                s.add(author); // feed native author
                creators.clear();
                creators.addAll(s);
            }
        }
    }

    protected Description createItemDescription(SyndContent sContent) {
        Description desc = new Description();
        desc.setValue(sContent.getValue());
        desc.setType(sContent.getType());

        return desc;
    }

    @Override
    protected Image createRSSImage(SyndImage sImage) {
        Image image = super.createRSSImage(sImage);
        image.setDescription(sImage.getDescription());

        return image;
    }

    // for synd -> rss
    // synd.content -> rss.content
    // synd.description -> rss.description
    @Override
    protected Item createRSSItem(SyndEntry sEntry) {
        Item item = super.createRSSItem(sEntry);

        SyndContent sContent = sEntry.getDescription();

        if (sContent != null) {
            item.setDescription(createItemDescription(sContent));
        }

        List contents = sEntry.getContents();

        if ((contents != null) && (contents.size() > 0)) {
            SyndContent syndContent = (SyndContent) contents.get(0);
            Content cont = new Content();
            cont.setValue(syndContent.getValue());
            cont.setType(syndContent.getType());
            item.setContent(cont);
        }

        return item;
    }

    @Override
    protected WireFeed createRealFeed(String type, SyndFeed syndFeed) {
        Channel channel = (Channel) super.createRealFeed(type, syndFeed);
        channel.setLanguage(syndFeed.getLanguage()); //c
        channel.setCopyright(syndFeed.getCopyright()); //c
        channel.setPubDate(syndFeed.getPublishedDate()); //c        

        if ((syndFeed.getAuthors() != null) && (syndFeed.getAuthors()
                                                            .size() > 0)) {
            SyndPerson author = (SyndPerson) syndFeed.getAuthors()
                                                     .get(0);
            channel.setManagingEditor(author.getName());
        }

        return channel;
    }

    // for rss -> synd
    // rss.content -> synd.content
    // rss.description -> synd.description
    @Override
    protected SyndEntry createSyndEntry(Item item, boolean preserveWireItem) {
        SyndEntry syndEntry = super.createSyndEntry(item, preserveWireItem);
        Description desc = item.getDescription();

        if (desc != null) {
            SyndContent descContent = new SyndContentImpl();
            descContent.setType(desc.getType());
            descContent.setValue(desc.getValue());
            syndEntry.setDescription(descContent);
        }

        Content cont = item.getContent();

        if (cont != null) {
            SyndContent content = new SyndContentImpl();
            content.setType(cont.getType());
            content.setValue(cont.getValue());

            List syndContents = new ArrayList();
            syndContents.add(content);
            syndEntry.setContents(syndContents);
        }

        return syndEntry;
    }

    @Override
    protected SyndImage createSyndImage(Image rssImage) {
        SyndImage syndImage = super.createSyndImage(rssImage);
        syndImage.setDescription(rssImage.getDescription());

        return syndImage;
    }
}
