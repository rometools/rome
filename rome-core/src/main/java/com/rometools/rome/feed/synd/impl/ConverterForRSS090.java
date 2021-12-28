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
package com.rometools.rome.feed.synd.impl;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.module.impl.ModuleUtils;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Image;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.rss.Source;
import com.rometools.rome.feed.synd.Converter;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.feed.synd.SyndImage;
import com.rometools.rome.feed.synd.SyndImageImpl;
import com.rometools.rome.feed.synd.SyndLink;

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

        final List<Element> foreignMarkup = feed.getForeignMarkup();
        if (!foreignMarkup.isEmpty()) {
            syndFeed.setForeignMarkup(foreignMarkup);
        }

        syndFeed.setStyleSheet(feed.getStyleSheet());

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
        syndImage.setWidth(rssImage.getWidth());
        syndImage.setHeight(rssImage.getHeight());
        return syndImage;
    }

    protected List<SyndEntry> createSyndEntries(final List<Item> rssItems, final boolean preserveWireItems) {
        final List<SyndEntry> syndEntries = new ArrayList<SyndEntry>();
        for (final Item item : rssItems) {
            syndEntries.add(createSyndEntry(item, preserveWireItems));
        }
        return syndEntries;
    }

    protected SyndEntry createSyndEntry(final Item item, final boolean preserveWireItem) {

        final SyndEntryImpl syndEntry = new SyndEntryImpl();

        if (preserveWireItem) {
            syndEntry.setWireEntry(item);
        }

        syndEntry.setModules(ModuleUtils.cloneModules(item.getModules()));

        final List<Element> foreignMarkup = item.getForeignMarkup();
        if (!foreignMarkup.isEmpty()) {
            syndEntry.setForeignMarkup(foreignMarkup);
        }

        syndEntry.setUri(item.getUri());
        syndEntry.setLink(item.getLink());
        syndEntry.setTitle(item.getTitle());
        syndEntry.setLink(item.getLink());
        syndEntry.setSource(createSource(item.getSource()));
        return syndEntry;
    }

    protected SyndFeed createSource(final Source source) {
        SyndFeed feed = null;
        if (source != null) {
            feed = new SyndFeedImpl();
            feed.setLink(source.getUrl());
            feed.setUri(source.getUrl());
            feed.setTitle(source.getValue());
        }
        return feed;
    }

    @Override
    public WireFeed createRealFeed(final SyndFeed syndFeed) {
        return this.createRealFeed(getType(), syndFeed);
    }

    protected WireFeed createRealFeed(final String type, final SyndFeed syndFeed) {
        final Channel channel = new Channel(type);
        channel.setModules(ModuleUtils.cloneModules(syndFeed.getModules()));
        channel.setStyleSheet(syndFeed.getStyleSheet());
        channel.setEncoding(syndFeed.getEncoding());

        channel.setTitle(syndFeed.getTitle());
        final String link = syndFeed.getLink();
        final List<SyndLink> links = syndFeed.getLinks();
        if (link != null) {
            channel.setLink(link);
        } else if (!links.isEmpty()) {
            channel.setLink(links.get(0).getHref());
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

        final List<Element> foreignMarkup = syndFeed.getForeignMarkup();
        if (!foreignMarkup.isEmpty()) {
            channel.setForeignMarkup(foreignMarkup);
        }

        return channel;
    }

    protected Image createRSSImage(final SyndImage sImage) {
        final Image image = new Image();
        image.setTitle(sImage.getTitle());
        image.setUrl(sImage.getUrl());
        image.setLink(sImage.getLink());
        image.setHeight(sImage.getHeight());
        image.setWidth(sImage.getWidth());
        return image;
    }

    protected List<Item> createRSSItems(final List<SyndEntry> sEntries) {
        final List<Item> list = new ArrayList<Item>();
        for (final SyndEntry syndEntry : sEntries) {
            list.add(createRSSItem(syndEntry));
        }
        return list;
    }

    protected Item createRSSItem(final SyndEntry sEntry) {

        final Item item = new Item();

        item.setModules(ModuleUtils.cloneModules(sEntry.getModules()));

        item.setTitle(sEntry.getTitle());

        item.setLink(sEntry.getLink());

        final List<Element> foreignMarkup = sEntry.getForeignMarkup();
        if (!foreignMarkup.isEmpty()) {
            item.setForeignMarkup(foreignMarkup);
        }

        item.setSource(createSource(sEntry.getSource()));

        final String uri = sEntry.getUri();
        if (uri != null) {
            item.setUri(uri);
        }

        return item;
    }

    protected Source createSource(final SyndFeed feed) {
        Source source = null;
        if (feed != null) {
            source = new Source();
            source.setUrl(feed.getUri());
            source.setValue(feed.getTitle());
        }
        return source;
    }

}
