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
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Image;
import com.sun.syndication.feed.rss.Item;
import com.sun.syndication.feed.rss.TextInput;
import com.sun.syndication.io.FeedException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 */
public class RSS090Parser extends BaseWireFeedParser {

    private static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    private static final String RSS_URI = "http://my.netscape.com/rdf/simple/0.9/";
    private static final String CONTENT_URI = "http://purl.org/rss/1.0/modules/content/";
    
    private static final Namespace RDF_NS = Namespace.getNamespace(RDF_URI);
    private static final Namespace RSS_NS = Namespace.getNamespace(RSS_URI);
    private static final Namespace CONTENT_NS = Namespace.getNamespace(CONTENT_URI);


    public RSS090Parser() {
        this("rss_0.9", RSS_NS);
    }

    protected RSS090Parser(String type, Namespace ns) {
        super(type, ns);
    }

    public boolean isMyType(Document document) {
        boolean ok = false;

        Element rssRoot = document.getRootElement();
        Namespace defaultNS = rssRoot.getNamespace();
        List additionalNSs = rssRoot.getAdditionalNamespaces();

        ok = defaultNS!=null && defaultNS.equals(getRDFNamespace());
        if (ok) {
            if (additionalNSs==null) {
                ok = false;
            }
            else {
                ok = false;
                for (int i=0;!ok && i<additionalNSs.size();i++) {
                    ok = getRSSNamespace().equals(additionalNSs.get(i));
                }
            }
        }
        return ok;
    }

    public WireFeed parse(Document document, boolean validate) throws IllegalArgumentException,FeedException {
        if (validate) {
            validateFeed(document);
        }
        Element rssRoot = document.getRootElement();
        return parseChannel(rssRoot);
    }

    protected void validateFeed(Document document) throws FeedException {
        // TBD
        // here we have to validate the Feed against a schema or whatever
        // not sure how to do it
        // one posibility would be to inject our own schema for the feed (they don't exist out there)
        // to the document, produce an ouput and attempt to parse it again with validation turned on.
        // otherwise will have to check the document elements by hand.
    }

    /**
     * Returns the namespace used by RSS elements in document of the RSS version the parser supports.
     * <P>
     * This implementation returns the EMTPY namespace.
     * <p>
     *
     * @return returns the EMPTY namespace.
     */
    protected Namespace getRSSNamespace() {
        return RSS_NS;
    }

    /**
     * Returns the namespace used by RDF elements in document of the RSS version the parser supports.
     * <P>
     * This implementation returns the EMTPY namespace.
     * <p>
     *
     * @return returns the EMPTY namespace.
     */
    protected Namespace getRDFNamespace() {
        return RDF_NS;
    }

    /**
     * Returns the namespace used by Content Module elements in document.
     * <P>
     * This implementation returns the EMTPY namespace.
     * <p>
     *
     * @return returns the EMPTY namespace.
     */
    protected Namespace getContentNamespace() {
        return CONTENT_NS;
    }

    /**
     * Parses the root element of an RSS document into a Channel bean.
     * <p/>
     * It reads title, link and description and delegates to parseImage, parseItems
     * and parseTextInput. This delegation always passes the root element of the RSS
     * document as different RSS version may have this information in different parts
     * of the XML tree (no assumptions made thanks to the specs variaty)
     * <p/>
     *
     * @param rssRoot the root element of the RSS document to parse.
     * @return the parsed Channel bean.
     */
    protected WireFeed parseChannel(Element rssRoot) {
        Element eChannel = rssRoot.getChild("channel", getRSSNamespace());

        Channel channel = new Channel(getType());

        Element e = eChannel.getChild("title",getRSSNamespace());
        if (e!=null) {
            channel.setTitle(e.getText());
        }
        e = eChannel.getChild("link",getRSSNamespace());
        if (e!=null) {
            channel.setLink(e.getText());
        }
        e = eChannel.getChild("description",getRSSNamespace());
        if (e!=null) {
            channel.setDescription(e.getText());
        }

        channel.setImage(parseImage(rssRoot));

        channel.setTextInput(parseTextInput(rssRoot));

        // Unfortunately Microsoft's SSE extension has a special case of 
        // effectively putting the sharing channel module inside the RSS tag 
        // and not inside the channel itself. So we also need to look for 
        // channel modules from the root RSS element.
        List allFeedModules = new ArrayList();
        List rootModules = parseFeedModules(rssRoot);
        List channelModules = parseFeedModules(eChannel); 
        if (rootModules != null) {
            allFeedModules.addAll(rootModules);
        }
        if (channelModules != null) {
            allFeedModules.addAll(channelModules);
        }
        channel.setModules(allFeedModules);
        channel.setItems(parseItems(rssRoot));

        List foreignMarkup = 
            extractForeignMarkup(eChannel, channel, getRSSNamespace());
        if (foreignMarkup.size() > 0) {
            channel.setForeignMarkup(foreignMarkup);
        }          
        return channel;
    }


    /**
     * This method exists because RSS0.90 and RSS1.0 have the 'item' elements under the root elemment.
     * And RSS0.91, RSS0.02, RSS0.93, RSS0.94 and RSS2.0 have the item elements under the 'channel' element.
     * <p/>
     */
    protected List getItems(Element rssRoot) {
        return rssRoot.getChildren("item",getRSSNamespace());
    }

    /**
     * This method exists because RSS0.90 and RSS1.0 have the 'image' element under the root elemment.
     * And RSS0.91, RSS0.02, RSS0.93, RSS0.94 and RSS2.0 have it under the 'channel' element.
     * <p/>
     */
    protected Element getImage(Element rssRoot) {
        return rssRoot.getChild("image",getRSSNamespace());
    }

    /**
     * This method exists because RSS0.90 and RSS1.0 have the 'textinput' element under the root elemment.
     * And RSS0.91, RSS0.02, RSS0.93, RSS0.94 and RSS2.0 have it under the 'channel' element.
     * <p/>
     */
    protected Element getTextInput(Element rssRoot) {
        return rssRoot.getChild("textinput",getRSSNamespace());
    }

    /**
     * Parses the root element of an RSS document looking for  image information.
     * <p/>
     * It reads title and url out of the 'image' element.
     * <p/>
     *
     * @param rssRoot the root element of the RSS document to parse for image information.
     * @return the parsed image bean.
     */
    protected Image parseImage(Element rssRoot) {
        Image image = null;
        Element eImage = getImage(rssRoot);
        if (eImage!=null) {
            image = new Image();

            Element e = eImage.getChild("title",getRSSNamespace());
            if (e!=null) {
                image.setTitle(e.getText());
            }
            e = eImage.getChild("url",getRSSNamespace());
            if (e!=null) {
                image.setUrl(e.getText());
            }
            e = eImage.getChild("link",getRSSNamespace());
            if (e!=null) {
                image.setLink(e.getText());
            }
        }
        return image;
    }

    /**
     * Parses the root element of an RSS document looking for all items information.
     * <p/>
     * It iterates through the item elements list, obtained from the getItems() method, and invoke parseItem()
     * for each item element. The resulting RSSItem of each item element is stored in a list.
     * <p/>
     *
     * @param rssRoot the root element of the RSS document to parse for all items information.
     * @return a list with all the parsed RSSItem beans.
     */
    protected List parseItems(Element rssRoot)  {
        Collection eItems = getItems(rssRoot);

        List items = new ArrayList();
        for (Iterator i=eItems.iterator();i.hasNext();) {
            Element eItem = (Element) i.next();
            items.add(parseItem(rssRoot,eItem));
        }
        return items;
    }

    /**
     * Parses an item element of an RSS document looking for item information.
     * <p/>
     * It reads title and link out of the 'item' element.
     * <p/>
     *
     * @param rssRoot the root element of the RSS document in case it's needed for context.
     * @param eItem the item element to parse.
     * @return the parsed RSSItem bean.
     */
    protected Item parseItem(Element rssRoot,Element eItem) {
        Item item = new Item();
        Element e = eItem.getChild("title",getRSSNamespace());
        if (e!=null) {
            item.setTitle(e.getText());
        }
        e = eItem.getChild("link",getRSSNamespace());
        if (e!=null) {
            item.setLink(e.getText());
            item.setUri(e.getText());
        }
        
        item.setModules(parseItemModules(eItem));
                
        List foreignMarkup = 
            extractForeignMarkup(eItem, item, getRSSNamespace());
        //content:encoded elements are treated special, without a module, they have to be removed from the foreign
        //markup to avoid duplication in case of read/write. Note that this fix will break if a content module is
        //used
        Iterator iterator = foreignMarkup.iterator();
        while (iterator.hasNext()) {
            Element ie = (Element)iterator.next();
            if (getContentNamespace().equals(ie.getNamespace()) && ie.getName().equals("encoded")) {
                iterator.remove();
            }
        }
        if (foreignMarkup.size() > 0) {
            item.setForeignMarkup(foreignMarkup);
        }
        return item;
    }


    /**
     * Parses the root element of an RSS document looking for  text-input information.
     * <p/>
     * It reads title, description, name and link out of the 'textinput' or 'textInput' element.
     * <p/>
     *
     * @param rssRoot the root element of the RSS document to parse for text-input information.
     * @return the parsed RSSTextInput bean.
     */
    protected TextInput parseTextInput(Element rssRoot) {
        TextInput textInput = null;
        Element eTextInput = getTextInput(rssRoot);
        if (eTextInput!=null) {
            textInput = new TextInput();
            Element e = eTextInput.getChild("title",getRSSNamespace());
            if (e!=null) {
                textInput.setTitle(e.getText());
            }
            e = eTextInput.getChild("description",getRSSNamespace());
            if (e!=null) {
                textInput.setDescription(e.getText());
            }
            e = eTextInput.getChild("name",getRSSNamespace());
            if (e!=null) {
                textInput.setName(e.getText());
            }
            e = eTextInput.getChild("link",getRSSNamespace());
            if (e!=null) {
                textInput.setLink(e.getText());
            }
        }
        return textInput;
    }


}
