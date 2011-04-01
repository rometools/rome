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
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Content;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class ConverterForRSS10 extends ConverterForRSS090 {

    public ConverterForRSS10() {
        this("rss_1.0");
    }

    protected ConverterForRSS10(String type) {
        super(type);
    }

    @Override
    public void copyInto(WireFeed feed,SyndFeed syndFeed) {
        Channel channel = (Channel) feed;
        super.copyInto(channel,syndFeed);
        if (channel.getUri() != null) {
        	syndFeed.setUri(channel.getUri());
        } else {
        	// if URI is not set use the value for link
        	syndFeed.setUri(channel.getLink());
        }
    }
    
    // for rss -> synd
    // rss.content -> synd.content
    // rss.description -> synd.description
    
    @Override
    protected SyndEntry createSyndEntry(Item item, boolean preserveWireItem) {
        SyndEntry syndEntry = super.createSyndEntry(item, preserveWireItem);

        Description desc = item.getDescription();
        if (desc!=null) {
            SyndContent descContent = new SyndContentImpl();
            descContent.setType(desc.getType());
            descContent.setValue(desc.getValue());
            syndEntry.setDescription(descContent);
        }
        Content cont = item.getContent();
        if (cont!=null) {
            SyndContent contContent = new SyndContentImpl();
            contContent.setType(cont.getType());
            contContent.setValue(cont.getValue());
            List contents = new ArrayList();
            contents.add(contContent);
            syndEntry.setContents(contents);
        }
                
        return syndEntry;
    }

    @Override
    protected WireFeed createRealFeed(String type,SyndFeed syndFeed) {
        Channel channel = (Channel) super.createRealFeed(type,syndFeed);        
        if (syndFeed.getUri() != null) {
        	channel.setUri(syndFeed.getUri());
        } else {
        	// if URI is not set use the value for link
        	channel.setUri(syndFeed.getLink());
        }
        
        return channel;
    }
    
    // for synd -> rss
    // synd.content -> rss.content
    // synd.description -> rss.description
    
    @Override
    protected Item createRSSItem(SyndEntry sEntry) {
        Item item = super.createRSSItem(sEntry);

        SyndContent desc = sEntry.getDescription();
        if (desc!=null) {
            item.setDescription(createItemDescription(desc));
        }
        List contents = sEntry.getContents();
        if (contents!=null && contents.size() > 0) {
            item.setContent(createItemContent((SyndContent)contents.get(0)));
        }
        
        String uri = sEntry.getUri();
        if (uri != null) {
            item.setUri(uri);
        }
        
        return item;
    }

    protected Description createItemDescription(SyndContent sContent) {
        Description desc = new Description();
        desc.setValue(sContent.getValue());
        desc.setType(sContent.getType());
        return desc;
    }

    protected Content createItemContent(SyndContent sContent) {
        Content cont = new Content();
        cont.setValue(sContent.getValue());
        cont.setType(sContent.getType());
        return cont;
    }

}
