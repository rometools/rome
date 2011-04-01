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
import com.sun.syndication.feed.atom.*;
import com.sun.syndication.io.FeedException;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

import java.io.StringReader;
import java.util.List;

/**
 * Feed Generator for Atom
 * <p/>
 *
 * @author Elaine Chien
 *
 */

public class Atom03Generator extends BaseWireFeedGenerator {
    private static final String ATOM_03_URI = "http://purl.org/atom/ns#";
    private static final Namespace ATOM_NS = Namespace.getNamespace(ATOM_03_URI);

    private String _version;

    public Atom03Generator() {
        this("atom_0.3","0.3");
    }

    protected Atom03Generator(String type,String version) {
        super(type);
        _version = version;
    }

    protected String getVersion() {
        return _version;
    }

    protected Namespace getFeedNamespace() {
        return ATOM_NS;
    }

    public Document generate(WireFeed wFeed) throws FeedException {
        Feed feed = (Feed) wFeed;
        Element root = createRootElement(feed);
        populateFeed(feed,root);
        purgeUnusedNamespaceDeclarations(root); 
        return createDocument(root);
    }

    protected Document createDocument(Element root) {
        return new Document(root);
    }

    protected Element createRootElement(Feed feed) {
        Element root = new Element("feed",getFeedNamespace());
        root.addNamespaceDeclaration(getFeedNamespace());
        Attribute version = new Attribute("version", getVersion());
        root.setAttribute(version);
        generateModuleNamespaceDefs(root);
        return root;
    }

    protected void populateFeed(Feed feed,Element parent) throws FeedException  {
        addFeed(feed,parent);
        addEntries(feed,parent);
    }

    protected void addFeed(Feed feed, Element parent) throws FeedException {
        Element eFeed = parent;
        populateFeedHeader(feed,eFeed);
        checkFeedHeaderConstraints(eFeed);
        generateFeedModules(feed.getModules(),eFeed);
        generateForeignMarkup(eFeed, (List)feed.getForeignMarkup()); 
    }

    protected void addEntries(Feed feed,Element parent) throws FeedException {
        List items = feed.getEntries();
        for (int i=0;i<items.size();i++) {
            addEntry((Entry)items.get(i),parent);
        }
        checkEntriesConstraints(parent);
    }

    protected void addEntry(Entry entry,Element parent) throws FeedException {
        Element eEntry = new Element("entry", getFeedNamespace());
        populateEntry(entry,eEntry);
        checkEntryConstraints(eEntry);
        generateItemModules(entry.getModules(),eEntry);
        parent.addContent(eEntry);
    }

    protected void populateFeedHeader(Feed feed, Element eFeed) throws FeedException {
        if (feed.getTitleEx() != null) {
            Element titleElement = new Element("title", getFeedNamespace());
            fillContentElement(titleElement, feed.getTitleEx());
            eFeed.addContent(titleElement);
        }

        List links = feed.getAlternateLinks();
        for (int i = 0; i < links.size(); i++) {
            eFeed.addContent(generateLinkElement((Link)links.get(i)));
        }

        links = feed.getOtherLinks();
        for (int i = 0; i < links.size(); i++) {
            eFeed.addContent(generateLinkElement((Link)links.get(i)));
        }
        if (feed.getAuthors()!=null && feed.getAuthors().size() > 0) {
            Element authorElement = new Element("author", getFeedNamespace());
            fillPersonElement(authorElement, (Person)feed.getAuthors().get(0));
            eFeed.addContent(authorElement);
        }

        List contributors = feed.getContributors();
        for (int i = 0; i < contributors.size(); i++) {
            Element contributorElement = new Element("contributor", getFeedNamespace());
            fillPersonElement(contributorElement, (Person)contributors.get(i));
            eFeed.addContent(contributorElement);
        }

        if (feed.getTagline() != null) {
            Element taglineElement = new Element("tagline", getFeedNamespace());
            fillContentElement(taglineElement, feed.getTagline());
            eFeed.addContent(taglineElement);
        }

        if (feed.getId() != null) {
            eFeed.addContent(generateSimpleElement("id", feed.getId()));
        }

        if (feed.getGenerator() != null) {
            eFeed.addContent(generateGeneratorElement(feed.getGenerator()));
        }

        if (feed.getCopyright() != null) {
            eFeed.addContent(generateSimpleElement("copyright", feed.getCopyright()));
        }

        if (feed.getInfo() != null) {
            Element infoElement = new Element("info", getFeedNamespace());
            fillContentElement(infoElement, feed.getInfo());
            eFeed.addContent(infoElement);
        }

        if (feed.getModified() != null) {
            Element modifiedElement = new Element("modified", getFeedNamespace());
            modifiedElement.addContent(DateParser.formatW3CDateTime(feed.getModified()));
            eFeed.addContent(modifiedElement);
        }
    }

    protected void populateEntry(Entry entry, Element eEntry) throws FeedException {
        if (entry.getTitleEx() != null) {
            Element titleElement = new Element("title", getFeedNamespace());
            fillContentElement(titleElement, entry.getTitleEx());
            eEntry.addContent(titleElement);
        }
        List links = entry.getAlternateLinks();
        for (int i = 0; i < links.size(); i++) {
            eEntry.addContent(generateLinkElement((Link)links.get(i)));
        }

        links = entry.getOtherLinks();
        for (int i = 0; i < links.size(); i++) {
            eEntry.addContent(generateLinkElement((Link)links.get(i)));
        }

        if (entry.getAuthors()!=null && entry.getAuthors().size() > 0) {
            Element authorElement = new Element("author", getFeedNamespace());
            fillPersonElement(authorElement, (Person)entry.getAuthors().get(0));
            eEntry.addContent(authorElement);
        }

        List contributors = entry.getContributors();
        for (int i = 0; i < contributors.size(); i++) {
            Element contributorElement = new Element("contributor", getFeedNamespace());
            fillPersonElement(contributorElement, (Person)contributors.get(i));
            eEntry.addContent(contributorElement);
        }
        if (entry.getId() != null) {
            eEntry.addContent(generateSimpleElement("id", entry.getId()));
        }

        if (entry.getModified() != null) {
            Element modifiedElement = new Element("modified", getFeedNamespace());
            modifiedElement.addContent(DateParser.formatW3CDateTime(entry.getModified()));
            eEntry.addContent(modifiedElement);
        }

        if (entry.getIssued() != null) {
            Element issuedElement = new Element("issued", getFeedNamespace());
            issuedElement.addContent(DateParser.formatW3CDateTime(entry.getIssued()));
            eEntry.addContent(issuedElement);
        }

        if (entry.getCreated() != null) {
            Element createdElement = new Element("created", getFeedNamespace());
            createdElement.addContent(DateParser.formatW3CDateTime(entry.getCreated()));
            eEntry.addContent(createdElement);
        }

        if (entry.getSummary() != null) {
            Element summaryElement = new Element("summary", getFeedNamespace());
            fillContentElement(summaryElement, entry.getSummary());
            eEntry.addContent(summaryElement);
        }

        List contents = entry.getContents();
        for (int i = 0; i < contents.size(); i++) {
            Element contentElement = new Element("content", getFeedNamespace());
            fillContentElement(contentElement, (Content)contents.get(i));
            eEntry.addContent(contentElement);
        }
        
        generateForeignMarkup(eEntry, (List)entry.getForeignMarkup());
    }

    protected void checkFeedHeaderConstraints(Element eFeed) throws FeedException {
    }

    protected void checkEntriesConstraints(Element parent) throws FeedException {
    }

    protected void checkEntryConstraints(Element eEntry) throws FeedException {
    }


    protected Element generateLinkElement(Link link) {
        Element linkElement = new Element("link", getFeedNamespace());

        if (link.getRel() != null) {
            Attribute relAttribute = new Attribute("rel", link.getRel().toString());
            linkElement.setAttribute(relAttribute);
        }

        if (link.getType() != null) {
            Attribute typeAttribute = new Attribute("type", link.getType());
            linkElement.setAttribute(typeAttribute);
        }

        if (link.getHref() != null) {
            Attribute hrefAttribute = new Attribute("href", link.getHref());
            linkElement.setAttribute(hrefAttribute);
        }
        return linkElement;
    }


    protected void fillPersonElement(Element element, Person person) {
        if (person.getName() != null) {
            element.addContent(generateSimpleElement("name", person.getName()));
        }
        if (person.getUrl() != null) {
            element.addContent(generateSimpleElement("url", person.getUrl()));
        }

        if (person.getEmail() != null) {
            element.addContent(generateSimpleElement("email", person.getEmail()));
        }
    }

    protected Element generateTagLineElement(Content tagline) {
        Element taglineElement = new Element("tagline", getFeedNamespace());

        if (tagline.getType() != null) {
            Attribute typeAttribute = new Attribute("type", tagline.getType());
            taglineElement.setAttribute(typeAttribute);
        }

        if (tagline.getValue() != null) {
            taglineElement.addContent(tagline.getValue());
        }
        return taglineElement;
    }

    protected void fillContentElement(Element contentElement, Content content)
        throws FeedException {

        if (content.getType() != null) {
            Attribute typeAttribute = new Attribute("type", content.getType());
            contentElement.setAttribute(typeAttribute);
        }

        String mode = content.getMode();
        if (mode != null) {
            Attribute modeAttribute = new Attribute("mode", content.getMode().toString());
            contentElement.setAttribute(modeAttribute);
        }

        if (content.getValue() != null) {

            if (mode == null || mode.equals(Content.ESCAPED)) {
                contentElement.addContent(content.getValue());
            } else if (mode.equals(Content.BASE64)) {
                contentElement.addContent(Base64.encode(content.getValue()));
            } else if (mode.equals(Content.XML)) {

                StringBuffer tmpDocString = new StringBuffer("<tmpdoc>");
                tmpDocString.append(content.getValue());
                tmpDocString.append("</tmpdoc>");
                StringReader tmpDocReader = new StringReader(tmpDocString.toString());
                Document tmpDoc;

                try {
                    SAXBuilder saxBuilder = new SAXBuilder();
                    tmpDoc = saxBuilder.build(tmpDocReader);
                }
                catch (Exception ex) {
                    throw new FeedException("Invalid XML",ex);
                }

                List children = tmpDoc.getRootElement().removeContent();
                contentElement.addContent(children);
            }
        }
    }

    protected Element generateGeneratorElement(Generator generator) {
        Element generatorElement = new Element("generator", getFeedNamespace());

        if (generator.getUrl() != null) {
            Attribute urlAttribute = new Attribute("url", generator.getUrl());
            generatorElement.setAttribute(urlAttribute);
        }

        if (generator.getVersion() != null) {
            Attribute versionAttribute = new Attribute("version", generator.getVersion());
            generatorElement.setAttribute(versionAttribute);
        }

        if (generator.getValue() != null) {
            generatorElement.addContent(generator.getValue());
        }

        return generatorElement;

    }

    protected Element generateSimpleElement(String name, String value) {
        Element element = new Element(name, getFeedNamespace());
        element.addContent(value);
        return element;
    }

}
