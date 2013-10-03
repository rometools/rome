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

import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.module.impl.ModuleUtils;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Image;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.Converter;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndImage;
import com.sun.syndication.feed.synd.SyndImageImpl;

/**
 */
public class ConverterForRSS090 implements Converter {
    private final String type;

    public ConverterForRSS090() {
        this("rss_0.9");
    }

    protected ConverterForRSS090(final String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void copyInto(final WireFeed feed, final SyndFeed syndFeed) {
        syndFeed.setModules(ModuleUtils.cloneModules(feed.getModules()));
        if (feed.getForeignMarkup().size() > 0) {
            syndFeed.setForeignMarkup(feed.getForeignMarkup());
        }
        syndFeed.setEncoding(feed.getEncoding());
        final Channel channel = (Channel) feed;
        syndFeed.setTitle(channel.getTitle());
        syndFeed.setLink(channel.getLink());
        syndFeed.setDescription(channel.getDescription());

        final Image image = channel.getImage();
        if (image != null) {
            syndFeed.setImage(createSyndImage(image));
        }

        final List<Item> items = channel.getItems();
        if (items != null) {
            syndFeed.setEntries(createSyndEntries(items, syndFeed.isPreservingWireFeed()));
        }
    }

    protected SyndImage createSyndImage(final Image rssImage) {
        final SyndImage syndImage = new SyndImageImpl();
        syndImage.setTitle(rssImage.getTitle());
        syndImage.setUrl(rssImage.getUrl());
        syndImage.setLink(rssImage.getLink());
        return syndImage;
    }

    protected List<SyndEntry> createSyndEntries(final List<Item> rssItems, final boolean preserveWireItems) {
        final List<SyndEntry> syndEntries = new ArrayList<SyndEntry>();
        for (int i = 0; i < rssItems.size(); i++) {
            syndEntries.add(createSyndEntry(rssItems.get(i), preserveWireItems));
        }
        return syndEntries;
    }

    protected SyndEntry createSyndEntry(final Item item, final boolean preserveWireItem) {
        final SyndEntryImpl syndEntry = new SyndEntryImpl();
        if (preserveWireItem) {
            syndEntry.setWireEntry(item);
        }

        syndEntry.setModules(ModuleUtils.cloneModules(item.getModules()));

        if (item.getForeignMarkup().size() > 0) {
            syndEntry.setForeignMarkup(item.getForeignMarkup());
        }

        syndEntry.setUri(item.getUri());
        syndEntry.setLink(item.getLink());
        syndEntry.setTitle(item.getTitle());
        syndEntry.setLink(item.getLink());
        return syndEntry;
    }

    @Override
    public WireFeed createRealFeed(final SyndFeed syndFeed) {
        return this.createRealFeed(getType(), syndFeed);
    }

    protected WireFeed createRealFeed(final String type, final SyndFeed syndFeed) {
        final Channel channel = new Channel(type);
        channel.setModules(ModuleUtils.cloneModules(syndFeed.getModules()));

        channel.setEncoding(syndFeed.getEncoding());

        channel.setTitle(syndFeed.getTitle());
        if (syndFeed.getLink() != null) {
            channel.setLink(syndFeed.getLink());
        } else if (syndFeed.getLinks().size() > 0) {
            channel.setLink(syndFeed.getLinks().get(0).getHref());
        }
        channel.setDescription(syndFeed.getDescription());
        final SyndImage sImage = syndFeed.getImage();
        if (sImage != null) {
            channel.setImage(createRSSImage(sImage));
        }

        final List<SyndEntry> sEntries = syndFeed.getEntries();
        if (sEntries != null) {
            channel.setItems(createRSSItems(sEntries));
        }

        if (syndFeed.getForeignMarkup().size() > 0) {
            channel.setForeignMarkup(syndFeed.getForeignMarkup());
        }
        return channel;
    }

    protected Image createRSSImage(final SyndImage sImage) {
        final Image image = new Image();
        image.setTitle(sImage.getTitle());
        image.setUrl(sImage.getUrl());
        image.setLink(sImage.getLink());
        return image;
    }

    protected List<Item> createRSSItems(final List<SyndEntry> sEntries) {
        final List<Item> list = new ArrayList<Item>();
        for (int i = 0; i < sEntries.size(); i++) {
            list.add(createRSSItem(sEntries.get(i)));
        }
        return list;
    }

    protected Item createRSSItem(final SyndEntry sEntry) {
        final Item item = new Item();
        item.setModules(ModuleUtils.cloneModules(sEntry.getModules()));
        item.setTitle(sEntry.getTitle());
        item.setLink(sEntry.getLink());
        if (sEntry.getForeignMarkup().size() > 0) {
            item.setForeignMarkup(sEntry.getForeignMarkup());
        }

        final String uri = sEntry.getUri();
        if (uri != null) {
            item.setUri(uri);
        }

        return item;
    }

}
