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
import com.sun.syndication.feed.rss.Guid;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndLink;
import com.sun.syndication.feed.synd.SyndLinkImpl;
import com.sun.syndication.feed.synd.SyndPerson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 */
public class ConverterForRSS094 extends ConverterForRSS093 {

    public ConverterForRSS094() {
        this("rss_0.94");
    }

    protected ConverterForRSS094(String type) {
        super(type);
    }

    @Override
    public void copyInto(WireFeed feed,SyndFeed syndFeed) {
        Channel channel = (Channel) feed;
        super.copyInto(channel,syndFeed);
        List cats = channel.getCategories();    //c
        if (cats.size()>0) {
            Set s = new HashSet();                // using a set to remove duplicates
            s.addAll(createSyndCategories(cats)); // feed native categories (as syndcat)
            s.addAll(syndFeed.getCategories());   // DC subjects (as syndcat)
            syndFeed.setCategories(new ArrayList(s));
        }
    }

    @Override
    protected SyndEntry createSyndEntry(Item item, boolean preserveWireItem) {
        SyndEntry syndEntry = super.createSyndEntry(item, preserveWireItem);

        // adding native feed author to DC creators list
        String author = item.getAuthor();
        if (author!=null) {
            List creators = ((DCModule)syndEntry.getModule(DCModule.URI)).getCreators();
            if (!creators.contains(author)) {
                Set s = new HashSet(); // using a set to remove duplicates
                s.addAll(creators);    // DC creators
                s.add(author);         // feed native author
                creators.clear();
                creators.addAll(s);
            }
        }

        Guid guid = item.getGuid();
        if (guid!=null) {
            syndEntry.setUri(guid.getValue());
            if (item.getLink()==null && guid.isPermaLink()) {
                syndEntry.setLink(guid.getValue());
            }
        }
        else {
            syndEntry.setUri(item.getLink());
        }
        if(item.getComments() != null){
            SyndLinkImpl comments = new SyndLinkImpl();
            comments.setRel("comments");
            comments.setHref(item.getComments());
            comments.setType("text/html");
        }
        return syndEntry;
    }


    @Override
    protected WireFeed createRealFeed(String type,SyndFeed syndFeed) {
        Channel channel = (Channel) super.createRealFeed(type,syndFeed);
        List cats = syndFeed.getCategories();    //c
        if (cats.size()>0) {
            channel.setCategories(createRSSCategories(cats));
        }
        return channel;
    }

    @Override
    protected Item createRSSItem(SyndEntry sEntry) {
        Item item = super.createRSSItem(sEntry);     
        if (sEntry.getAuthors()!=null && sEntry.getAuthors().size() > 0) {
            SyndPerson author = (SyndPerson)sEntry.getAuthors().get(0);
            item.setAuthor(author.getEmail());  
        }  

        Guid guid = null;
        String uri = sEntry.getUri();
        if (uri!=null) {
            guid = new Guid();
            guid.setPermaLink(false);
            guid.setValue(uri);
        }
        else {
            String link = sEntry.getLink();
            if (link!=null) {
                guid = new Guid();
                guid.setPermaLink(true);
                guid.setValue(link);
            }
        }
        item.setGuid(guid);
        SyndLink comments = sEntry.findRelatedLink("comments");
        if(comments != null && (comments.getType() == null || comments.getType().endsWith("html"))){
            item.setComments(comments.getHref());
        }
        return item;
    }

}
