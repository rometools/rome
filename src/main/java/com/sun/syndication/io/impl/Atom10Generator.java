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
import java.util.Iterator;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.atom.Category;
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Generator;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.atom.Person;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedOutput;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import org.jdom2.output.XMLOutputter;

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

    private String _version;

    public Atom10Generator() {
        this("atom_1.0","1.0");
    }

    protected Atom10Generator(String type,String version) {
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
        //Attribute version = new Attribute("version", getVersion());
        //root.setAttribute(version);
        if (feed.getXmlBase() != null) {
            root.setAttribute("base", feed.getXmlBase(), Namespace.XML_NAMESPACE);
        }
        generateModuleNamespaceDefs(root);
        return root;
    }

    protected void populateFeed(Feed feed,Element parent) throws FeedException  {
        addFeed(feed,parent);
        addEntries(feed,parent);
    }

    protected void addFeed(Feed feed,Element parent) throws FeedException {
        Element eFeed = parent;
        populateFeedHeader(feed,eFeed);
        generateForeignMarkup(eFeed, (List)feed.getForeignMarkup());
        checkFeedHeaderConstraints(eFeed);
        generateFeedModules(feed.getModules(),eFeed);
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
        if (entry.getXmlBase() != null) {
            eEntry.setAttribute("base", entry.getXmlBase(), Namespace.XML_NAMESPACE);
        }
        populateEntry(entry,eEntry);
        generateForeignMarkup(eEntry, (List)entry.getForeignMarkup());
        checkEntryConstraints(eEntry);
        generateItemModules(entry.getModules(),eEntry);
        parent.addContent(eEntry);
    }

    protected void populateFeedHeader(Feed feed,Element eFeed) throws FeedException {
        if (feed.getTitleEx() != null) {
            Element titleElement = new Element("title", getFeedNamespace());
            fillContentElement(titleElement, feed.getTitleEx());
            eFeed.addContent(titleElement);
        }

        List links = feed.getAlternateLinks();
        if (links != null) for (int i = 0; i < links.size(); i++) {
            eFeed.addContent(generateLinkElement((Link)links.get(i)));
        }
        links = feed.getOtherLinks();
        if (links != null) for (int j = 0; j < links.size(); j++) {
            eFeed.addContent(generateLinkElement((Link)links.get(j)));
        }

        List cats = feed.getCategories();
        if (cats != null) for (Iterator iter=cats.iterator(); iter.hasNext();) {
            eFeed.addContent(generateCategoryElement((Category)iter.next()));
        }
            
        List authors = feed.getAuthors();
        if (authors != null && authors.size() > 0) {
            for (int i = 0; i < authors.size(); i++) {
                Element authorElement = new Element("author", getFeedNamespace());
                fillPersonElement(authorElement, (Person)feed.getAuthors().get(i));
                eFeed.addContent(authorElement);
            }
        }

        List contributors = feed.getContributors();
        if (contributors != null && contributors.size() > 0) {
            for (int i = 0; i < contributors.size(); i++) {
                Element contributorElement = new Element("contributor", getFeedNamespace());
                fillPersonElement(contributorElement, (Person)contributors.get(i));
                eFeed.addContent(contributorElement);
            }
        }

        if (feed.getSubtitle() != null) {
          Element subtitleElement = new Element("subtitle", getFeedNamespace());
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
            Element updatedElement = new Element("updated", getFeedNamespace());
            updatedElement.addContent(DateParser.formatW3CDateTime(feed.getUpdated()));
            eFeed.addContent(updatedElement);
        }
    }

    protected void populateEntry(Entry entry, Element eEntry) throws FeedException {
        if (entry.getTitleEx() != null) {
            Element titleElement = new Element("title", getFeedNamespace());
            fillContentElement(titleElement, entry.getTitleEx());
            eEntry.addContent(titleElement);
        }
        List links = entry.getAlternateLinks();
        if (links != null) {
            for (int i = 0; i < links.size(); i++) {
                eEntry.addContent(generateLinkElement((Link)links.get(i)));
            }
        }
        links = entry.getOtherLinks();
        if (links != null) {
            for (int i = 0; i < links.size(); i++) {
                eEntry.addContent(generateLinkElement((Link)links.get(i)));
            }
        }

        List cats = entry.getCategories();
        if (cats != null) {
            for (int i = 0; i < cats.size(); i++) {
                eEntry.addContent(generateCategoryElement((Category)cats.get(i)));
            }
        }
        
        List authors = entry.getAuthors();
        if (authors != null && authors.size() > 0) {
            for (int i = 0; i < authors.size(); i++)  {
                Element authorElement = new Element("author", getFeedNamespace());
                fillPersonElement(authorElement, (Person)entry.getAuthors().get(i));
                eEntry.addContent(authorElement);            
            }
        }

        List contributors = entry.getContributors();
        if (contributors != null && contributors.size() > 0) {
            for (int i = 0; i < contributors.size(); i++) {
                Element contributorElement = new Element("contributor", getFeedNamespace());
                fillPersonElement(contributorElement, (Person)contributors.get(i));
                eEntry.addContent(contributorElement);
            }
        }
        if (entry.getId() != null) {
            eEntry.addContent(generateSimpleElement("id", entry.getId()));
        }

        if (entry.getUpdated() != null) {
            Element updatedElement = new Element("updated", getFeedNamespace());
            updatedElement.addContent(DateParser.formatW3CDateTime(entry.getUpdated()));
            eEntry.addContent(updatedElement);
        }

        if (entry.getPublished() != null) {
            Element publishedElement = new Element("published", getFeedNamespace());
            publishedElement.addContent(DateParser.formatW3CDateTime(entry.getPublished()));
            eEntry.addContent(publishedElement);
        }

        if (entry.getContents() != null && entry.getContents().size() > 0) {
            Element contentElement = new Element("content", getFeedNamespace());
            Content content = (Content)entry.getContents().get(0);
            fillContentElement(contentElement, content);
            eEntry.addContent(contentElement);
        }

        if (entry.getSummary() != null) {
            Element summaryElement = new Element("summary", getFeedNamespace());
            fillContentElement(summaryElement, entry.getSummary());
            eEntry.addContent(summaryElement);
        }

        if (entry.getSource() != null) {
        	Element sourceElement = new Element("source", getFeedNamespace());
            populateFeedHeader(entry.getSource(), sourceElement);
            eEntry.addContent(sourceElement);
        }
    }

    protected void checkFeedHeaderConstraints(Element eFeed) throws FeedException {
    }

    protected void checkEntriesConstraints(Element parent) throws FeedException {
    }

    protected void checkEntryConstraints(Element eEntry) throws FeedException {
    }


    protected Element generateCategoryElement(Category cat) {
        Element catElement = new Element("category", getFeedNamespace());

        if (cat.getTerm() != null) {
            Attribute termAttribute = new Attribute("term", cat.getTerm());
            catElement.setAttribute(termAttribute);
        }

        if (cat.getLabel() != null) {
            Attribute labelAttribute = new Attribute("label", cat.getLabel());
            catElement.setAttribute(labelAttribute);
        }

        if (cat.getScheme() != null) {
            Attribute schemeAttribute = new Attribute("scheme", cat.getScheme());
            catElement.setAttribute(schemeAttribute);
        }
        return catElement;
    }

    protected Element generateLinkElement(Link link) {
        Element linkElement = new Element("link", getFeedNamespace());

        if (link.getRel() != null) {
            Attribute relAttribute = new Attribute("rel", link.getRel());
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
        
        if (link.getHreflang() != null) {
            Attribute hreflangAttribute = new Attribute("hreflang", link.getHreflang());
            linkElement.setAttribute(hreflangAttribute);
        }
        if (link.getTitle() != null) {
            Attribute title = new Attribute("title", link.getTitle());
            linkElement.setAttribute(title);
        }
        if (link.getLength() != 0) {
            Attribute lenght = new Attribute("length", Long.toString(link.getLength()));
            linkElement.setAttribute(lenght);
        }
        return linkElement;
    }


    protected void fillPersonElement(Element element, Person person) {
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

    protected Element generateTagLineElement(Content tagline) {
        Element taglineElement = new Element("subtitle", getFeedNamespace());

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

        String type = content.getType();
            String atomType = type;
        if (type != null) {
            // Fix for issue #39 "Atom 1.0 Text Types Not Set Correctly"
            // we're not sure who set this value, so ensure Atom types are used
            if ("text/plain".equals(type)) atomType = Content.TEXT;
            else if ("text/html".equals(type)) atomType = Content.HTML;
            else if ("application/xhtml+xml".equals(type)) atomType = Content.XHTML;
            
            Attribute typeAttribute = new Attribute("type", atomType);
            contentElement.setAttribute(typeAttribute);
        }
        String href = content.getSrc();
        if (href != null) {
            Attribute srcAttribute = new Attribute("src", href);
            contentElement.setAttribute(srcAttribute);
        }
        if (content.getValue() != null) {
            if (atomType != null && (atomType.equals(Content.XHTML) || (atomType.indexOf("/xml")) != -1 ||
                (atomType.indexOf("+xml")) != -1)) {

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
            } else { 
                // must be type html, text or some other non-XML format
                // JDOM will escape property for XML
                contentElement.addContent(content.getValue());
            }
        }
    }

    protected Element generateGeneratorElement(Generator generator) {
        Element generatorElement = new Element("generator", getFeedNamespace());

        if (generator.getUrl() != null) {
            Attribute urlAttribute = new Attribute("uri", generator.getUrl());
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
    
    /**
     * Utility method to serialize an entry to writer.
     */
    public static void serializeEntry(Entry entry, Writer writer)
        throws IllegalArgumentException, FeedException, IOException {
        
        // Build a feed containing only the entry
        List entries = new ArrayList();
        entries.add(entry);
        Feed feed1 = new Feed();
        feed1.setFeedType("atom_1.0");
        feed1.setEntries(entries);

        // Get Rome to output feed as a JDOM document
        WireFeedOutput wireFeedOutput = new WireFeedOutput();
        Document feedDoc = wireFeedOutput.outputJDom(feed1);

        // Grab entry element from feed and get JDOM to serialize it
        Element entryElement= (Element)feedDoc.getRootElement().getChildren().get(0);

        XMLOutputter outputter = new XMLOutputter();
        outputter.output(entryElement, writer);
    }

}
