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

import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.atom.Category;
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Generator;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.synd.SyndPerson;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedOutput;

/**
 * Feed Generator for Atom
 * <p/>
 *
 * @author Elaine Chien
 * @author Dave Johnson (updated for Atom 1.0)
 *
 */

public class Atom10Generator extends BaseWireFeedGenerator {
    private static final String ATOM_10_URI = "http://www.w3.org/2005/Atom";
    private static final Namespace ATOM_NS = Namespace.getNamespace(ATOM_10_URI);

    private final String version;

    public Atom10Generator() {
        this("atom_1.0", "1.0");
    }

    protected Atom10Generator(final String type, final String version) {
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
        // Attribute version = new Attribute("version", getVersion());
        // root.setAttribute(version);
        if (feed.getXmlBase() != null) {
            root.setAttribute("base", feed.getXmlBase(), Namespace.XML_NAMESPACE);
        }
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
        generateForeignMarkup(eFeed, feed.getForeignMarkup());
        checkFeedHeaderConstraints(eFeed);
        generateFeedModules(feed.getModules(), eFeed);
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
        if (entry.getXmlBase() != null) {
            eEntry.setAttribute("base", entry.getXmlBase(), Namespace.XML_NAMESPACE);
        }
        populateEntry(entry, eEntry);
        generateForeignMarkup(eEntry, entry.getForeignMarkup());
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
        if (links != null) {
            for (int i = 0; i < links.size(); i++) {
                eFeed.addContent(generateLinkElement(links.get(i)));
            }
        }
        links = feed.getOtherLinks();
        if (links != null) {
            for (int j = 0; j < links.size(); j++) {
                eFeed.addContent(generateLinkElement(links.get(j)));
            }
        }

        final List<Category> cats = feed.getCategories();
        if (cats != null) {
            for (final Category category : cats) {
                eFeed.addContent(generateCategoryElement(category));
            }
        }

        final List<SyndPerson> authors = feed.getAuthors();
        if (authors != null && !authors.isEmpty()) {
            for (int i = 0; i < authors.size(); i++) {
                final Element authorElement = new Element("author", getFeedNamespace());
                fillPersonElement(authorElement, feed.getAuthors().get(i));
                eFeed.addContent(authorElement);
            }
        }

        final List<SyndPerson> contributors = feed.getContributors();
        if (contributors != null && !contributors.isEmpty()) {
            for (int i = 0; i < contributors.size(); i++) {
                final Element contributorElement = new Element("contributor", getFeedNamespace());
                fillPersonElement(contributorElement, contributors.get(i));
                eFeed.addContent(contributorElement);
            }
        }

        if (feed.getSubtitle() != null) {
            final Element subtitleElement = new Element("subtitle", getFeedNamespace());
            fillContentElement(subtitleElement, feed.getSubtitle());
            eFeed.addContent(subtitleElement);
        }

        if (feed.getId() != null) {
            eFeed.addContent(generateSimpleElement("id", feed.getId()));
        }

        if (feed.getGenerator() != null) {
            eFeed.addContent(generateGeneratorElement(feed.getGenerator()));
        }

        if (feed.getRights() != null) {
            eFeed.addContent(generateSimpleElement("rights", feed.getRights()));
        }

        if (feed.getIcon() != null) {
            eFeed.addContent(generateSimpleElement("icon", feed.getIcon()));
        }

        if (feed.getLogo() != null) {
            eFeed.addContent(generateSimpleElement("logo", feed.getLogo()));
        }

        if (feed.getUpdated() != null) {
            final Element updatedElement = new Element("updated", getFeedNamespace());
            updatedElement.addContent(DateParser.formatW3CDateTime(feed.getUpdated(), Locale.US));
            eFeed.addContent(updatedElement);
        }
    }

    protected void populateEntry(final Entry entry, final Element eEntry) throws FeedException {
        if (entry.getTitleEx() != null) {
            final Element titleElement = new Element("title", getFeedNamespace());
            fillContentElement(titleElement, entry.getTitleEx());
            eEntry.addContent(titleElement);
        }
        List<Link> links = entry.getAlternateLinks();
        if (links != null) {
            for (int i = 0; i < links.size(); i++) {
                eEntry.addContent(generateLinkElement(links.get(i)));
            }
        }
        links = entry.getOtherLinks();
        if (links != null) {
            for (int i = 0; i < links.size(); i++) {
                eEntry.addContent(generateLinkElement(links.get(i)));
            }
        }

        final List<Category> cats = entry.getCategories();
        if (cats != null) {
            for (int i = 0; i < cats.size(); i++) {
                eEntry.addContent(generateCategoryElement(cats.get(i)));
            }
        }

        final List<SyndPerson> authors = entry.getAuthors();
        if (authors != null && !authors.isEmpty()) {
            for (int i = 0; i < authors.size(); i++) {
                final Element authorElement = new Element("author", getFeedNamespace());
                fillPersonElement(authorElement, entry.getAuthors().get(i));
                eEntry.addContent(authorElement);
            }
        }

        final List<SyndPerson> contributors = entry.getContributors();
        if (contributors != null && !contributors.isEmpty()) {
            for (int i = 0; i < contributors.size(); i++) {
                final Element contributorElement = new Element("contributor", getFeedNamespace());
                fillPersonElement(contributorElement, contributors.get(i));
                eEntry.addContent(contributorElement);
            }
        }
        if (entry.getId() != null) {
            eEntry.addContent(generateSimpleElement("id", entry.getId()));
        }

        if (entry.getUpdated() != null) {
            final Element updatedElement = new Element("updated", getFeedNamespace());
            updatedElement.addContent(DateParser.formatW3CDateTime(entry.getUpdated(), Locale.US));
            eEntry.addContent(updatedElement);
        }

        if (entry.getPublished() != null) {
            final Element publishedElement = new Element("published", getFeedNamespace());
            publishedElement.addContent(DateParser.formatW3CDateTime(entry.getPublished(), Locale.US));
            eEntry.addContent(publishedElement);
        }

        if (entry.getContents() != null && !entry.getContents().isEmpty()) {
            final Element contentElement = new Element("content", getFeedNamespace());
            final Content content = entry.getContents().get(0);
            fillContentElement(contentElement, content);
            eEntry.addContent(contentElement);
        }

        if (entry.getSummary() != null) {
            final Element summaryElement = new Element("summary", getFeedNamespace());
            fillContentElement(summaryElement, entry.getSummary());
            eEntry.addContent(summaryElement);
        }

        if (entry.getSource() != null) {
            final Element sourceElement = new Element("source", getFeedNamespace());
            populateFeedHeader(entry.getSource(), sourceElement);
            eEntry.addContent(sourceElement);
        }
    }

    protected void checkFeedHeaderConstraints(final Element eFeed) throws FeedException {
    }

    protected void checkEntriesConstraints(final Element parent) throws FeedException {
    }

    protected void checkEntryConstraints(final Element eEntry) throws FeedException {
    }

    protected Element generateCategoryElement(final Category cat) {
        final Element catElement = new Element("category", getFeedNamespace());

        if (cat.getTerm() != null) {
            final Attribute termAttribute = new Attribute("term", cat.getTerm());
            catElement.setAttribute(termAttribute);
        }

        if (cat.getLabel() != null) {
            final Attribute labelAttribute = new Attribute("label", cat.getLabel());
            catElement.setAttribute(labelAttribute);
        }

        if (cat.getScheme() != null) {
            final Attribute schemeAttribute = new Attribute("scheme", cat.getScheme());
            catElement.setAttribute(schemeAttribute);
        }
        return catElement;
    }

    protected Element generateLinkElement(final Link link) {
        final Element linkElement = new Element("link", getFeedNamespace());

        if (link.getRel() != null) {
            final Attribute relAttribute = new Attribute("rel", link.getRel());
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

        if (link.getHreflang() != null) {
            final Attribute hreflangAttribute = new Attribute("hreflang", link.getHreflang());
            linkElement.setAttribute(hreflangAttribute);
        }
        if (link.getTitle() != null) {
            final Attribute title = new Attribute("title", link.getTitle());
            linkElement.setAttribute(title);
        }
        if (link.getLength() != 0) {
            final Attribute lenght = new Attribute("length", Long.toString(link.getLength()));
            linkElement.setAttribute(lenght);
        }
        return linkElement;
    }

    protected void fillPersonElement(final Element element, final SyndPerson person) {
        if (person.getName() != null) {
            element.addContent(generateSimpleElement("name", person.getName()));
        }
        if (person.getUri() != null) {
            element.addContent(generateSimpleElement("uri", person.getUri()));
        }

        if (person.getEmail() != null) {
            element.addContent(generateSimpleElement("email", person.getEmail()));
        }
        generatePersonModules(person.getModules(), element);
    }

    protected Element generateTagLineElement(final Content tagline) {
        final Element taglineElement = new Element("subtitle", getFeedNamespace());

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

        final String type = content.getType();
        String atomType = type;
        if (type != null) {
            // Fix for issue #39 "Atom 1.0 Text Types Not Set Correctly"
            // we're not sure who set this value, so ensure Atom types are used
            if ("text/plain".equals(type)) {
                atomType = Content.TEXT;
            } else if ("text/html".equals(type)) {
                atomType = Content.HTML;
            } else if ("application/xhtml+xml".equals(type)) {
                atomType = Content.XHTML;
            }

            final Attribute typeAttribute = new Attribute("type", atomType);
            contentElement.setAttribute(typeAttribute);
        }
        final String href = content.getSrc();
        if (href != null) {
            final Attribute srcAttribute = new Attribute("src", href);
            contentElement.setAttribute(srcAttribute);
        }
        if (content.getValue() != null) {
            if (atomType != null && (atomType.equals(Content.XHTML) || atomType.indexOf("/xml") != -1 || atomType.indexOf("+xml") != -1)) {

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
            } else {
                // must be type html, text or some other non-XML format
                // JDOM will escape property for XML
                contentElement.addContent(content.getValue());
            }
        }
    }

    protected Element generateGeneratorElement(final Generator generator) {
        final Element generatorElement = new Element("generator", getFeedNamespace());

        if (generator.getUrl() != null) {
            final Attribute urlAttribute = new Attribute("uri", generator.getUrl());
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

    /**
     * Utility method to serialize an entry to writer.
     */
    public static void serializeEntry(final Entry entry, final Writer writer) throws IllegalArgumentException, FeedException, IOException {

        // Build a feed containing only the entry
        final List<Entry> entries = new ArrayList<Entry>();
        entries.add(entry);
        final Feed feed1 = new Feed();
        feed1.setFeedType("atom_1.0");
        feed1.setEntries(entries);

        // Get Rome to output feed as a JDOM document
        final WireFeedOutput wireFeedOutput = new WireFeedOutput();
        final Document feedDoc = wireFeedOutput.outputJDom(feed1);

        // Grab entry element from feed and get JDOM to serialize it
        final Element entryElement = feedDoc.getRootElement().getChildren().get(0);

        final XMLOutputter outputter = new XMLOutputter();
        outputter.output(entryElement, writer);
    }

}
