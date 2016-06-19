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
package com.rometools.rome.io.impl;

import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.atom.Generator;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.rome.io.FeedException;
import com.rometools.utils.Lists;

/**
 * Feed Generator for Atom
 * <p/>
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
        final List<Entry> entries = feed.getEntries();
        for (final Entry entry : entries) {
            addEntry(entry, parent);
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

        final Content titleEx = feed.getTitleEx();
        if (titleEx != null) {
            final Element titleElement = new Element("title", getFeedNamespace());
            fillContentElement(titleElement, titleEx);
            eFeed.addContent(titleElement);
        }

        List<Link> links = feed.getAlternateLinks();
        for (final Link link : links) {
            eFeed.addContent(generateLinkElement(link));
        }

        links = feed.getOtherLinks();
        for (final Link link : links) {
            eFeed.addContent(generateLinkElement(link));
        }

        final List<SyndPerson> authors = feed.getAuthors();
        if (Lists.isNotEmpty(authors)) {
            final Element authorElement = new Element("author", getFeedNamespace());
            fillPersonElement(authorElement, authors.get(0));
            eFeed.addContent(authorElement);
        }

        final List<SyndPerson> contributors = feed.getContributors();
        for (final SyndPerson contributor : contributors) {
            final Element contributorElement = new Element("contributor", getFeedNamespace());
            fillPersonElement(contributorElement, contributor);
            eFeed.addContent(contributorElement);
        }

        final Content tagline = feed.getTagline();
        if (tagline != null) {
            final Element taglineElement = new Element("tagline", getFeedNamespace());
            fillContentElement(taglineElement, tagline);
            eFeed.addContent(taglineElement);
        }

        final String id = feed.getId();
        if (id != null) {
            eFeed.addContent(generateSimpleElement("id", id));
        }

        final Generator generator = feed.getGenerator();
        if (generator != null) {
            eFeed.addContent(generateGeneratorElement(generator));
        }

        final String copyright = feed.getCopyright();
        if (copyright != null) {
            eFeed.addContent(generateSimpleElement("copyright", copyright));
        }

        final Content info = feed.getInfo();
        if (info != null) {
            final Element infoElement = new Element("info", getFeedNamespace());
            fillContentElement(infoElement, info);
            eFeed.addContent(infoElement);
        }

        final Date modified = feed.getModified();
        if (modified != null) {
            final Element modifiedElement = new Element("modified", getFeedNamespace());
            modifiedElement.addContent(DateParser.formatW3CDateTime(modified, Locale.US));
            eFeed.addContent(modifiedElement);
        }

    }

    protected void populateEntry(final Entry entry, final Element eEntry) throws FeedException {

        final Content titleEx = entry.getTitleEx();
        if (titleEx != null) {
            final Element titleElement = new Element("title", getFeedNamespace());
            fillContentElement(titleElement, titleEx);
            eEntry.addContent(titleElement);
        }

        final List<Link> alternateLinks = entry.getAlternateLinks();
        for (final Link link : alternateLinks) {
            eEntry.addContent(generateLinkElement(link));
        }

        final List<Link> otherLinks = entry.getOtherLinks();
        for (final Link link : otherLinks) {
            eEntry.addContent(generateLinkElement(link));
        }

        final List<SyndPerson> authors = entry.getAuthors();
        if (Lists.isNotEmpty(authors)) {
            final Element authorElement = new Element("author", getFeedNamespace());
            fillPersonElement(authorElement, authors.get(0));
            eEntry.addContent(authorElement);
        }

        final List<SyndPerson> contributors = entry.getContributors();
        for (final SyndPerson contributor : contributors) {
            final Element contributorElement = new Element("contributor", getFeedNamespace());
            fillPersonElement(contributorElement, contributor);
            eEntry.addContent(contributorElement);
        }

        final String id = entry.getId();
        if (id != null) {
            eEntry.addContent(generateSimpleElement("id", id));
        }

        final Date modified = entry.getModified();
        if (modified != null) {
            final Element modifiedElement = new Element("modified", getFeedNamespace());
            modifiedElement.addContent(DateParser.formatW3CDateTime(modified, Locale.US));
            eEntry.addContent(modifiedElement);
        }

        final Date issued = entry.getIssued();
        if (issued != null) {
            final Element issuedElement = new Element("issued", getFeedNamespace());
            issuedElement.addContent(DateParser.formatW3CDateTime(issued, Locale.US));
            eEntry.addContent(issuedElement);
        }

        final Date created = entry.getCreated();
        if (created != null) {
            final Element createdElement = new Element("created", getFeedNamespace());
            createdElement.addContent(DateParser.formatW3CDateTime(created, Locale.US));
            eEntry.addContent(createdElement);
        }

        final Content summary = entry.getSummary();
        if (summary != null) {
            final Element summaryElement = new Element("summary", getFeedNamespace());
            fillContentElement(summaryElement, summary);
            eEntry.addContent(summaryElement);
        }

        final List<Content> contents = entry.getContents();
        for (final Content content : contents) {
            final Element contentElement = new Element("content", getFeedNamespace());
            fillContentElement(contentElement, content);
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

        final String rel = link.getRel();
        if (rel != null) {
            final Attribute relAttribute = new Attribute("rel", rel);
            linkElement.setAttribute(relAttribute);
        }

        final String type = link.getType();
        if (type != null) {
            final Attribute typeAttribute = new Attribute("type", type);
            linkElement.setAttribute(typeAttribute);
        }

        final String href = link.getHref();
        if (href != null) {
            final Attribute hrefAttribute = new Attribute("href", href);
            linkElement.setAttribute(hrefAttribute);
        }

        return linkElement;

    }

    protected void fillPersonElement(final Element element, final SyndPerson person) {

        final String name = person.getName();
        if (name != null) {
            element.addContent(generateSimpleElement("name", name));
        }

        final String uri = person.getUri();
        if (uri != null) {
            element.addContent(generateSimpleElement("url", uri));
        }

        final String email = person.getEmail();
        if (email != null) {
            element.addContent(generateSimpleElement("email", email));
        }

    }

    protected Element generateTagLineElement(final Content tagline) {

        final Element taglineElement = new Element("tagline", getFeedNamespace());

        final String type = tagline.getType();
        if (type != null) {
            final Attribute typeAttribute = new Attribute("type", type);
            taglineElement.setAttribute(typeAttribute);
        }

        final String value = tagline.getValue();
        if (value != null) {
            taglineElement.addContent(value);
        }

        return taglineElement;

    }

    protected void fillContentElement(final Element contentElement, final Content content) throws FeedException {

        final String type = content.getType();
        if (type != null) {
            final Attribute typeAttribute = new Attribute("type", type);
            contentElement.setAttribute(typeAttribute);
        }

        final String mode = content.getMode();
        if (mode != null) {
            final Attribute modeAttribute = new Attribute("mode", mode);
            contentElement.setAttribute(modeAttribute);
        }

        final String value = content.getValue();
        if (value != null) {

            if (mode == null || mode.equals(Content.ESCAPED)) {

                contentElement.addContent(value);

            } else if (mode.equals(Content.BASE64)) {

                contentElement.addContent(Base64.encode(value));

            } else if (mode.equals(Content.XML)) {

                final StringBuffer tmpDocString = new StringBuffer("<tmpdoc>");
                tmpDocString.append(value);
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

        final String url = generator.getUrl();
        if (url != null) {
            final Attribute urlAttribute = new Attribute("url", url);
            generatorElement.setAttribute(urlAttribute);
        }

        final String version = generator.getVersion();
        if (version != null) {
            final Attribute versionAttribute = new Attribute("version", version);
            generatorElement.setAttribute(versionAttribute);
        }

        final String value = generator.getValue();
        if (value != null) {
            generatorElement.addContent(value);
        }

        return generatorElement;

    }

    protected Element generateSimpleElement(final String name, final String value) {
        final Element element = new Element(name, getFeedNamespace());
        element.addContent(value);
        return element;
    }

}
