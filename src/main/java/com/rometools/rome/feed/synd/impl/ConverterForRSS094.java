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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.module.DCModule;
import com.rometools.rome.feed.rss.Category;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Guid;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndLink;
import com.rometools.rome.feed.synd.SyndLinkImpl;
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.utils.Lists;

public class ConverterForRSS094 extends ConverterForRSS093 {

    public ConverterForRSS094() {
        this("rss_0.94");
    }

    protected ConverterForRSS094(final String type) {
        super(type);
    }

    @Override
    public void copyInto(final WireFeed feed, final SyndFeed syndFeed) {

        final Channel channel = (Channel) feed;

        super.copyInto(channel, syndFeed);

        final List<Category> cats = channel.getCategories(); // c
        if (!cats.isEmpty()) {

            // using a set to remove duplicates
            final Set<SyndCategory> s = new LinkedHashSet<SyndCategory>();

            // feed native categories (as syndcat)
            s.addAll(createSyndCategories(cats));

            // DC subjects (as syndcat)
            s.addAll(syndFeed.getCategories());

            syndFeed.setCategories(new ArrayList<SyndCategory>(s));

        }

    }

    @Override
    protected SyndEntry createSyndEntry(final Item item, final boolean preserveWireItem) {

        final SyndEntry syndEntry = super.createSyndEntry(item, preserveWireItem);

        // adding native feed author to DC creators list
        final String author = item.getAuthor();
        if (author != null) {
            final List<String> creators = ((DCModule) syndEntry.getModule(DCModule.URI)).getCreators();
            if (!creators.contains(author)) {

                // using a set to remove duplicates
                final Set<String> s = new LinkedHashSet<String>();

                // DC creators
                s.addAll(creators);

                // feed native author
                s.add(author);

                creators.clear();
                creators.addAll(s);
            }
        }

        final Guid guid = item.getGuid();
        final String itemLink = item.getLink();
        if (guid != null) {
            final String guidValue = guid.getValue();
            syndEntry.setUri(guidValue);
            if (itemLink == null && guid.isPermaLink()) {
                syndEntry.setLink(guidValue);
            }
        } else {
            syndEntry.setUri(itemLink);
        }

        if (item.getComments() != null) {
            final SyndLinkImpl comments = new SyndLinkImpl();
            comments.setRel("comments");
            comments.setHref(item.getComments());
            comments.setType("text/html");
        }

        return syndEntry;

    }

    @Override
    protected WireFeed createRealFeed(final String type, final SyndFeed syndFeed) {
        final Channel channel = (Channel) super.createRealFeed(type, syndFeed);
        final List<SyndCategory> cats = syndFeed.getCategories(); // c
        if (!cats.isEmpty()) {
            channel.setCategories(createRSSCategories(cats));
        }
        return channel;
    }

    @Override
    protected Item createRSSItem(final SyndEntry sEntry) {

        final Item item = super.createRSSItem(sEntry);

        final List<SyndPerson> authors = sEntry.getAuthors();
        if (Lists.isNotEmpty(authors)) {
            final SyndPerson author = authors.get(0);
            item.setAuthor(author.getEmail());
        }

        Guid guid = null;
        final String uri = sEntry.getUri();
        final String link = sEntry.getLink();
        if (uri != null) {
            guid = new Guid();
            guid.setPermaLink(false);
            guid.setValue(uri);
        } else if (link != null) {
            guid = new Guid();
            guid.setPermaLink(true);
            guid.setValue(link);

        }
        item.setGuid(guid);

        final SyndLink comments = sEntry.findRelatedLink("comments");
        if (comments != null && (comments.getType() == null || comments.getType().endsWith("html"))) {
            item.setComments(comments.getHref());
        }
        return item;

    }

}
