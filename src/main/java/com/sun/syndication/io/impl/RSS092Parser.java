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

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.rss.Category;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Cloud;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Enclosure;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.rss.Source;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class RSS092Parser extends RSS091UserlandParser {

    public RSS092Parser() {
        this("rss_0.92");
    }

    protected RSS092Parser(String type) {
        super(type);
    }

    protected String getRSSVersion() {
            return "0.92";
    }

    protected WireFeed parseChannel(Element rssRoot)  {
        Channel channel = (Channel) super.parseChannel(rssRoot);

        Element eChannel = rssRoot.getChild("channel",getRSSNamespace());
        Element eCloud = eChannel.getChild("cloud",getRSSNamespace());
        if (eCloud!=null) {
            Cloud cloud = new Cloud();
            String att = eCloud.getAttributeValue("domain");//getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
            if (att!=null) {
                cloud.setDomain(att);
            }
            att = eCloud.getAttributeValue("port");//getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
            if (att!=null) {
                cloud.setPort(Integer.parseInt(att.trim()));
            }
            att = eCloud.getAttributeValue("path");//getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
            if (att!=null) {
                cloud.setPath(att);
            }
            att = eCloud.getAttributeValue("registerProcedure");//getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
            if (att!=null) {
                cloud.setRegisterProcedure(att);
            }
            att = eCloud.getAttributeValue("protocol");//getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
            if (att!=null) {
                cloud.setProtocol(att);
            }
            channel.setCloud(cloud);
        }
        return channel;
    }

    protected Item parseItem(Element rssRoot,Element eItem) {
        Item item = super.parseItem(rssRoot,eItem);

        Element e = eItem.getChild("source",getRSSNamespace());
        if (e!=null) {
            Source source = new Source();
            String url = e.getAttributeValue("url");//getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
            source.setUrl(url);
            source.setValue(e.getText());
            item.setSource(source);
        }

        // 0.92 allows one enclosure occurrence, 0.93 multiple
        // just saving to write some code.
        List eEnclosures = eItem.getChildren("enclosure");//getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
        if (eEnclosures.size()>0) {
            List enclosures = new ArrayList();
            for (int i=0;i<eEnclosures.size();i++) {
                e = (Element) eEnclosures.get(i);

                Enclosure enclosure = new Enclosure();
                String att = e.getAttributeValue("url");//getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
                if (att!=null) {
                    enclosure.setUrl(att);
                }
                att = e.getAttributeValue("length");//getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
                enclosure.setLength(NumberParser.parseLong(att,0L));

                att = e.getAttributeValue("type");//getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
                if (att!=null) {
                    enclosure.setType(att);
                }
                enclosures.add(enclosure);
            }
            item.setEnclosures(enclosures);
        }

        List eCats = eItem.getChildren("category");//getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
        item.setCategories(parseCategories(eCats));

        return item;
    }

    protected List parseCategories(List eCats) {
        List cats = null;
        if (eCats.size()>0) {
            cats = new ArrayList();
            for (int i=0;i<eCats.size();i++) {
                Category cat = new Category();
                Element e = (Element) eCats.get(i);
                String att = e.getAttributeValue("domain");//getRSSNamespace()); DONT KNOW WHY DOESN'T WORK
                if (att!=null) {
                    cat.setDomain(att);
                }
                cat.setValue(e.getText());
                cats.add(cat);
            }
        }
        return cats;
    }

    protected Description parseItemDescription(Element rssRoot,Element eDesc) {
        Description desc = super.parseItemDescription(rssRoot,eDesc);
        desc.setType("text/html");
        return desc;
    }

}
