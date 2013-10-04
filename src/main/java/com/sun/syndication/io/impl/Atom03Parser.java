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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.XMLOutputter;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Generator;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.atom.Person;
import com.sun.syndication.io.FeedException;

/**
 */
public class Atom03Parser extends BaseWireFeedParser {
    private static final String ATOM_03_URI = "http://purl.org/atom/ns#";
    private static final Namespace ATOM_03_NS = Namespace.getNamespace(ATOM_03_URI);

    public Atom03Parser() {
        this("atom_0.3", ATOM_03_NS);
    }

    protected Atom03Parser(final String type, final Namespace ns) {
        super(type, ns);
    }

    protected Namespace getAtomNamespace() {
        return ATOM_03_NS;
    }

    @Override
    public boolean isMyType(final Document document) {
        final Element rssRoot = document.getRootElement();
        final Namespace defaultNS = rssRoot.getNamespace();
        return defaultNS != null && defaultNS.equals(getAtomNamespace());
    }

    @Override
    public WireFeed parse(final Document document, final boolean validate) throws IllegalArgumentException, FeedException {
        if (validate) {
            validateFeed(document);
        }
        final Element rssRoot = document.getRootElement();
        return parseFeed(rssRoot);
    }

    protected void validateFeed(final Document document) throws FeedException {
        // TBD
        // here we have to validate the Feed against a schema or whatever
        // not sure how to do it
        // one posibility would be to produce an ouput and attempt to parse it
        // again
        // with validation turned on.
        // otherwise will have to check the document elements by hand.
    }

    protected WireFeed parseFeed(final Element eFeed) {

        final com.sun.syndication.feed.atom.Feed feed = new com.sun.syndication.feed.atom.Feed(getType());

        Element e = eFeed.getChild("title", getAtomNamespace());
        if (e != null) {
            feed.setTitleEx(parseContent(e));
        }

        List<Element> eList = eFeed.getChildren("link", getAtomNamespace());
        feed.setAlternateLinks(parseAlternateLinks(eList));
        feed.setOtherLinks(parseOtherLinks(eList));

        e = eFeed.getChild("author", getAtomNamespace());
        if (e != null) {
            final List<Person> authors = new ArrayList<Person>();
            authors.add(parsePerson(e));
            feed.setAuthors(authors);
        }

        eList = eFeed.getChildren("contributor", getAtomNamespace());
        if (eList.size() > 0) {
            feed.setContributors(parsePersons(eList));
        }

        e = eFeed.getChild("tagline", getAtomNamespace());
        if (e != null) {
            feed.setTagline(parseContent(e));
        }

        e = eFeed.getChild("id", getAtomNamespace());
        if (e != null) {
            feed.setId(e.getText());
        }

        e = eFeed.getChild("generator", getAtomNamespace());
        if (e != null) {
            final Generator gen = new Generator();
            gen.setValue(e.getText());
            String att = getAttributeValue(e, "url");
            if (att != null) {
                gen.setUrl(att);
            }
            att = getAttributeValue(e, "version");
            if (att != null) {
                gen.setVersion(att);
            }
            feed.setGenerator(gen);
        }

        e = eFeed.getChild("copyright", getAtomNamespace());
        if (e != null) {
            feed.setCopyright(e.getText());
        }

        e = eFeed.getChild("info", getAtomNamespace());
        if (e != null) {
            feed.setInfo(parseContent(e));
        }

        e = eFeed.getChild("modified", getAtomNamespace());
        if (e != null) {
            feed.setModified(DateParser.parseDate(e.getText()));
        }

        feed.setModules(parseFeedModules(eFeed));

        eList = eFeed.getChildren("entry", getAtomNamespace());
        if (eList.size() > 0) {
            feed.setEntries(parseEntries(eList));
        }

        final List<Element> foreignMarkup = extractForeignMarkup(eFeed, feed, getAtomNamespace());
        if (foreignMarkup.size() > 0) {
            feed.setForeignMarkup(foreignMarkup);
        }
        return feed;
    }

    private Link parseLink(final Element eLink) {
        final Link link = new Link();
        String att = getAttributeValue(eLink, "rel");
        if (att != null) {
            link.setRel(att);
        }
        att = getAttributeValue(eLink, "type");
        if (att != null) {
            link.setType(att);
        }
        att = getAttributeValue(eLink, "href");
        if (att != null) {
            link.setHref(att);
        }
        return link;
    }

    // List(Elements) -> List(Link)
    private List<Link> parseLinks(final List<Element> eLinks, final boolean alternate) {
        final List<Link> links = new ArrayList<Link>();
        for (int i = 0; i < eLinks.size(); i++) {
            final Element eLink = eLinks.get(i);
            final String rel = getAttributeValue(eLink, "rel");
            if (alternate) {
                if ("alternate".equals(rel)) {
                    links.add(parseLink(eLink));
                }
            } else {
                if (!"alternate".equals(rel)) {
                    links.add(parseLink(eLink));
                }
            }
        }
        if (links.size() > 0) {
            return links;
        } else {
            return null;
        }
    }

    // List(Elements) -> List(Link)
    private List<Link> parseAlternateLinks(final List<Element> eLinks) {
        return parseLinks(eLinks, true);
    }

    // List(Elements) -> List(Link)
    private List<Link> parseOtherLinks(final List<Element> eLinks) {
        return parseLinks(eLinks, false);
    }

    private Person parsePerson(final Element ePerson) {
        final Person person = new Person();
        Element e = ePerson.getChild("name", getAtomNamespace());
        if (e != null) {
            person.setName(e.getText());
        }
        e = ePerson.getChild("url", getAtomNamespace());
        if (e != null) {
            person.setUrl(e.getText());
        }
        e = ePerson.getChild("email", getAtomNamespace());
        if (e != null) {
            person.setEmail(e.getText());
        }
        return person;
    }

    // List(Elements) -> List(Persons)
    private List<Person> parsePersons(final List<Element> ePersons) {
        final List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < ePersons.size(); i++) {
            persons.add(parsePerson(ePersons.get(i)));
        }
        if (persons.size() > 0) {
            return persons;
        } else {
            return null;
        }
    }

    private Content parseContent(final Element e) {
        String value = null;
        String type = getAttributeValue(e, "type");
        if (type == null) {
            type = "text/plain";
        }
        String mode = getAttributeValue(e, "mode");
        if (mode == null) {
            mode = Content.XML; // default to xml content
        }
        if (mode.equals(Content.ESCAPED)) {
            // do nothing XML Parser took care of this
            value = e.getText();
        } else if (mode.equals(Content.BASE64)) {
            value = Base64.decode(e.getText());
        } else if (mode.equals(Content.XML)) {
            final XMLOutputter outputter = new XMLOutputter();
            final List<org.jdom2.Content> eContent = e.getContent();
            final Iterator<org.jdom2.Content> i = eContent.iterator();
            while (i.hasNext()) {
                final org.jdom2.Content c = i.next();
                if (c instanceof Element) {
                    final Element eC = (Element) c;
                    if (eC.getNamespace().equals(getAtomNamespace())) {
                        ((Element) c).setNamespace(Namespace.NO_NAMESPACE);
                    }
                }
            }
            value = outputter.outputString(eContent);
        }

        final Content content = new Content();
        content.setType(type);
        content.setMode(mode);
        content.setValue(value);
        return content;
    }

    // List(Elements) -> List(Entries)
    private List<Entry> parseEntries(final List<Element> eEntries) {
        final List<Entry> entries = new ArrayList<Entry>();
        for (int i = 0; i < eEntries.size(); i++) {
            entries.add(parseEntry(eEntries.get(i)));
        }
        if (entries.size() > 0) {
            return entries;
        } else {
            return null;
        }
    }

    private Entry parseEntry(final Element eEntry) {
        final Entry entry = new Entry();

        Element e = eEntry.getChild("title", getAtomNamespace());
        if (e != null) {
            entry.setTitleEx(parseContent(e));
        }

        List<Element> eList = eEntry.getChildren("link", getAtomNamespace());
        entry.setAlternateLinks(parseAlternateLinks(eList));
        entry.setOtherLinks(parseOtherLinks(eList));

        e = eEntry.getChild("author", getAtomNamespace());
        if (e != null) {
            final List<Person> authors = new ArrayList<Person>();
            authors.add(parsePerson(e));
            entry.setAuthors(authors);
        }

        eList = eEntry.getChildren("contributor", getAtomNamespace());
        if (eList.size() > 0) {
            entry.setContributors(parsePersons(eList));
        }

        e = eEntry.getChild("id", getAtomNamespace());
        if (e != null) {
            entry.setId(e.getText());
        }

        e = eEntry.getChild("modified", getAtomNamespace());
        if (e != null) {
            entry.setModified(DateParser.parseDate(e.getText()));
        }

        e = eEntry.getChild("issued", getAtomNamespace());
        if (e != null) {
            entry.setIssued(DateParser.parseDate(e.getText()));
        }

        e = eEntry.getChild("created", getAtomNamespace());
        if (e != null) {
            entry.setCreated(DateParser.parseDate(e.getText()));
        }

        e = eEntry.getChild("summary", getAtomNamespace());
        if (e != null) {
            entry.setSummary(parseContent(e));
        }

        eList = eEntry.getChildren("content", getAtomNamespace());
        if (eList.size() > 0) {
            final List<Content> content = new ArrayList<Content>();
            for (int i = 0; i < eList.size(); i++) {
                content.add(parseContent(eList.get(i)));
            }
            entry.setContents(content);
        }

        entry.setModules(parseItemModules(eEntry));

        final List<Element> foreignMarkup = extractForeignMarkup(eEntry, entry, getAtomNamespace());
        if (foreignMarkup.size() > 0) {
            entry.setForeignMarkup(foreignMarkup);
        }
        return entry;
    }

}
