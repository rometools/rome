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

import java.io.StringReader;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Generator;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.atom.Person;
import com.sun.syndication.io.FeedException;

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

    private final String version;

    public Atom03Generator() {
        this("atom_0.3", "0.3");
    }

    protected Atom03Generator(final String type, final String version) {
        super(type);
        this.version = version;
    }

    protected String getVersion() {
        return version;
    }

    protected Namespace getFeedNamespace() {
        return ATOM_NS;
    }

    @Override
    public Document generate(final WireFeed wFeed) throws FeedException {
        final Feed feed = (Feed) wFeed;
        final Element root = createRootElement(feed);
        populateFeed(feed, root);
        purgeUnusedNamespaceDeclarations(root);
        return createDocument(root);
    }

    protected Document createDocument(final Element root) {
        return new Document(root);
    }

    protected Element createRootElement(final Feed feed) {
        final Element root = new Element("feed", getFeedNamespace());
        root.addNamespaceDeclaration(getFeedNamespace());
        final Attribute version = new Attribute("version", getVersion());
        root.setAttribute(version);
        generateModuleNamespaceDefs(root);
        return root;
    }

    protected void populateFeed(final Feed feed, final Element parent) throws FeedException {
        addFeed(feed, parent);
        addEntries(feed, parent);
    }

    protected void addFeed(final Feed feed, final Element parent) throws FeedException {
        final Element eFeed = parent;
        populateFeedHeader(feed, eFeed);
        checkFeedHeaderConstraints(eFeed);
        generateFeedModules(feed.getModules(), eFeed);
        generateForeignMarkup(eFeed, feed.getForeignMarkup());
    }

    protected void addEntries(final Feed feed, final Element parent) throws FeedException {
        final List<Entry> items = feed.getEntries();
        for (int i = 0; i < items.size(); i++) {
            addEntry(items.get(i), parent);
        }
        checkEntriesConstraints(parent);
    }

    protected void addEntry(final Entry entry, final Element parent) throws FeedException {
        final Element eEntry = new Element("entry", getFeedNamespace());
        populateEntry(entry, eEntry);
        checkEntryConstraints(eEntry);
        generateItemModules(entry.getModules(), eEntry);
        parent.addContent(eEntry);
    }

    protected void populateFeedHeader(final Feed feed, final Element eFeed) throws FeedException {
        if (feed.getTitleEx() != null) {
            final Element titleElement = new Element("title", getFeedNamespace());
            fillContentElement(titleElement, feed.getTitleEx());
            eFeed.addContent(titleElement);
        }

        List<Link> links = feed.getAlternateLinks();
        for (int i = 0; i < links.size(); i++) {
            eFeed.addContent(generateLinkElement(links.get(i)));
        }

        links = feed.getOtherLinks();
        for (int i = 0; i < links.size(); i++) {
            eFeed.addContent(generateLinkElement(links.get(i)));
        }
        if (feed.getAuthors() != null && feed.getAuthors().size() > 0) {
            final Element authorElement = new Element("author", getFeedNamespace());
            fillPersonElement(authorElement, feed.getAuthors().get(0));
            eFeed.addContent(authorElement);
        }

        final List<Person> contributors = feed.getContributors();
        for (int i = 0; i < contributors.size(); i++) {
            final Element contributorElement = new Element("contributor", getFeedNamespace());
            fillPersonElement(contributorElement, contributors.get(i));
            eFeed.addContent(contributorElement);
        }

        if (feed.getTagline() != null) {
            final Element taglineElement = new Element("tagline", getFeedNamespace());
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
            final Element infoElement = new Element("info", getFeedNamespace());
            fillContentElement(infoElement, feed.getInfo());
            eFeed.addContent(infoElement);
        }

        if (feed.getModified() != null) {
            final Element modifiedElement = new Element("modified", getFeedNamespace());
            modifiedElement.addContent(DateParser.formatW3CDateTime(feed.getModified()));
            eFeed.addContent(modifiedElement);
        }
    }

    protected void populateEntry(final Entry entry, final Element eEntry) throws FeedException {
        if (entry.getTitleEx() != null) {
            final Element titleElement = new Element("title", getFeedNamespace());
            fillContentElement(titleElement, entry.getTitleEx());
            eEntry.addContent(titleElement);
        }
        List<Link> links = entry.getAlternateLinks();
        for (int i = 0; i < links.size(); i++) {
            eEntry.addContent(generateLinkElement(links.get(i)));
        }

        links = entry.getOtherLinks();
        for (int i = 0; i < links.size(); i++) {
            eEntry.addContent(generateLinkElement(links.get(i)));
        }

        if (entry.getAuthors() != null && entry.getAuthors().size() > 0) {
            final Element authorElement = new Element("author", getFeedNamespace());
            fillPersonElement(authorElement, entry.getAuthors().get(0));
            eEntry.addContent(authorElement);
        }

        final List<Person> contributors = entry.getContributors();
        for (int i = 0; i < contributors.size(); i++) {
            final Element contributorElement = new Element("contributor", getFeedNamespace());
            fillPersonElement(contributorElement, contributors.get(i));
            eEntry.addContent(contributorElement);
        }
        if (entry.getId() != null) {
            eEntry.addContent(generateSimpleElement("id", entry.getId()));
        }

        if (entry.getModified() != null) {
            final Element modifiedElement = new Element("modified", getFeedNamespace());
            modifiedElement.addContent(DateParser.formatW3CDateTime(entry.getModified()));
            eEntry.addContent(modifiedElement);
        }

        if (entry.getIssued() != null) {
            final Element issuedElement = new Element("issued", getFeedNamespace());
            issuedElement.addContent(DateParser.formatW3CDateTime(entry.getIssued()));
            eEntry.addContent(issuedElement);
        }

        if (entry.getCreated() != null) {
            final Element createdElement = new Element("created", getFeedNamespace());
            createdElement.addContent(DateParser.formatW3CDateTime(entry.getCreated()));
            eEntry.addContent(createdElement);
        }

        if (entry.getSummary() != null) {
            final Element summaryElement = new Element("summary", getFeedNamespace());
            fillContentElement(summaryElement, entry.getSummary());
            eEntry.addContent(summaryElement);
        }

        final List<Content> contents = entry.getContents();
        for (int i = 0; i < contents.size(); i++) {
            final Element contentElement = new Element("content", getFeedNamespace());
            fillContentElement(contentElement, contents.get(i));
            eEntry.addContent(contentElement);
        }

        generateForeignMarkup(eEntry, entry.getForeignMarkup());
    }

    protected void checkFeedHeaderConstraints(final Element eFeed) throws FeedException {
    }

    protected void checkEntriesConstraints(final Element parent) throws FeedException {
    }

    protected void checkEntryConstraints(final Element eEntry) throws FeedException {
    }

    protected Element generateLinkElement(final Link link) {
        final Element linkElement = new Element("link", getFeedNamespace());

        if (link.getRel() != null) {
            final Attribute relAttribute = new Attribute("rel", link.getRel().toString());
            linkElement.setAttribute(relAttribute);
        }

        if (link.getType() != null) {
            final Attribute typeAttribute = new Attribute("type", link.getType());
            linkElement.setAttribute(typeAttribute);
        }

        if (link.getHref() != null) {
            final Attribute hrefAttribute = new Attribute("href", link.getHref());
            linkElement.setAttribute(hrefAttribute);
        }
        return linkElement;
    }

    protected void fillPersonElement(final Element element, final Person person) {
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

    protected Element generateTagLineElement(final Content tagline) {
        final Element taglineElement = new Element("tagline", getFeedNamespace());

        if (tagline.getType() != null) {
            final Attribute typeAttribute = new Attribute("type", tagline.getType());
            taglineElement.setAttribute(typeAttribute);
        }

        if (tagline.getValue() != null) {
            taglineElement.addContent(tagline.getValue());
        }
        return taglineElement;
    }

    protected void fillContentElement(final Element contentElement, final Content content) throws FeedException {

        if (content.getType() != null) {
            final Attribute typeAttribute = new Attribute("type", content.getType());
            contentElement.setAttribute(typeAttribute);
        }

        final String mode = content.getMode();
        if (mode != null) {
            final Attribute modeAttribute = new Attribute("mode", content.getMode().toString());
            contentElement.setAttribute(modeAttribute);
        }

        if (content.getValue() != null) {

            if (mode == null || mode.equals(Content.ESCAPED)) {
                contentElement.addContent(content.getValue());
            } else if (mode.equals(Content.BASE64)) {
                contentElement.addContent(Base64.encode(content.getValue()));
            } else if (mode.equals(Content.XML)) {

                final StringBuffer tmpDocString = new StringBuffer("<tmpdoc>");
                tmpDocString.append(content.getValue());
                tmpDocString.append("</tmpdoc>");
                final StringReader tmpDocReader = new StringReader(tmpDocString.toString());
                Document tmpDoc;

                try {
                    final SAXBuilder saxBuilder = new SAXBuilder();
                    tmpDoc = saxBuilder.build(tmpDocReader);
                } catch (final Exception ex) {
                    throw new FeedException("Invalid XML", ex);
                }

                final List<org.jdom2.Content> children = tmpDoc.getRootElement().removeContent();
                contentElement.addContent(children);
            }
        }
    }

    protected Element generateGeneratorElement(final Generator generator) {
        final Element generatorElement = new Element("generator", getFeedNamespace());

        if (generator.getUrl() != null) {
            final Attribute urlAttribute = new Attribute("url", generator.getUrl());
            generatorElement.setAttribute(urlAttribute);
        }

        if (generator.getVersion() != null) {
            final Attribute versionAttribute = new Attribute("version", generator.getVersion());
            generatorElement.setAttribute(versionAttribute);
        }

        if (generator.getValue() != null) {
            generatorElement.addContent(generator.getValue());
        }

        return generatorElement;

    }

    protected Element generateSimpleElement(final String name, final String value) {
        final Element element = new Element(name, getFeedNamespace());
        element.addContent(value);
        return element;
    }

}
