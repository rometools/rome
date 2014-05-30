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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.XMLOutputter;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.atom.Generator;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.atom.Person;
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.rome.io.FeedException;
import com.rometools.utils.Lists;

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
    public WireFeed parse(final Document document, final boolean validate, final Locale locale) throws IllegalArgumentException, FeedException {

        if (validate) {
            validateFeed(document);
        }

        final Element rssRoot = document.getRootElement();

        return parseFeed(rssRoot, locale);

    }

    protected void validateFeed(final Document document) throws FeedException {
        // TODO here we have to validate the Feed against a schema or whatever not sure how to do it
        // one posibility would be to produce an ouput and attempt to parse it again with validation
        // turned on. otherwise will have to check the document elements by hand.
    }

    protected WireFeed parseFeed(final Element eFeed, final Locale locale) {

        final String type = getType();
        final Document document = eFeed.getDocument();
        final String styleSheet = getStyleSheet(document);

        final Feed feed = new Feed(type);
        feed.setStyleSheet(styleSheet);

        final Element title = eFeed.getChild("title", getAtomNamespace());
        if (title != null) {
            feed.setTitleEx(parseContent(title));
        }

        final List<Element> links = eFeed.getChildren("link", getAtomNamespace());
        feed.setAlternateLinks(parseAlternateLinks(links));
        feed.setOtherLinks(parseOtherLinks(links));

        final Element author = eFeed.getChild("author", getAtomNamespace());
        if (author != null) {
            final List<SyndPerson> authors = new ArrayList<SyndPerson>();
            authors.add(parsePerson(author));
            feed.setAuthors(authors);
        }

        final List<Element> contributors = eFeed.getChildren("contributor", getAtomNamespace());
        if (!contributors.isEmpty()) {
            feed.setContributors(parsePersons(contributors));
        }

        final Element tagline = eFeed.getChild("tagline", getAtomNamespace());
        if (tagline != null) {
            feed.setTagline(parseContent(tagline));
        }

        final Element id = eFeed.getChild("id", getAtomNamespace());
        if (id != null) {
            feed.setId(id.getText());
        }

        final Element generator = eFeed.getChild("generator", getAtomNamespace());
        if (generator != null) {
            final Generator gen = new Generator();
            gen.setValue(generator.getText());
            String att = getAttributeValue(generator, "url");
            if (att != null) {
                gen.setUrl(att);
            }
            att = getAttributeValue(generator, "version");
            if (att != null) {
                gen.setVersion(att);
            }
            feed.setGenerator(gen);
        }

        final Element copyright = eFeed.getChild("copyright", getAtomNamespace());
        if (copyright != null) {
            feed.setCopyright(copyright.getText());
        }

        final Element info = eFeed.getChild("info", getAtomNamespace());
        if (info != null) {
            feed.setInfo(parseContent(info));
        }

        final Element modified = eFeed.getChild("modified", getAtomNamespace());
        if (modified != null) {
            feed.setModified(DateParser.parseDate(modified.getText(), locale));
        }

        feed.setModules(parseFeedModules(eFeed, locale));

        final List<Element> entries = eFeed.getChildren("entry", getAtomNamespace());
        if (!entries.isEmpty()) {
            feed.setEntries(parseEntries(entries, locale));
        }

        final List<Element> foreignMarkup = extractForeignMarkup(eFeed, feed, getAtomNamespace());
        if (!foreignMarkup.isEmpty()) {
            feed.setForeignMarkup(foreignMarkup);
        }

        return feed;

    }

    private Link parseLink(final Element eLink) {

        final Link link = new Link();

        final String rel = getAttributeValue(eLink, "rel");
        if (rel != null) {
            link.setRel(rel);
        }

        final String type = getAttributeValue(eLink, "type");
        if (type != null) {
            link.setType(type);
        }

        final String href = getAttributeValue(eLink, "href");
        if (href != null) {
            link.setHref(href);
        }

        return link;

    }

    private List<Link> parseLinks(final List<Element> eLinks, final boolean alternate) {

        final List<Link> links = new ArrayList<Link>();

        for (final Element eLink : eLinks) {
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

        return Lists.emptyToNull(links);

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

        final Element name = ePerson.getChild("name", getAtomNamespace());

        if (name != null) {
            person.setName(name.getText());
        }

        final Element url = ePerson.getChild("url", getAtomNamespace());
        if (url != null) {
            person.setUrl(url.getText());
        }

        final Element email = ePerson.getChild("email", getAtomNamespace());
        if (email != null) {
            person.setEmail(email.getText());
        }

        return person;

    }

    // List(Elements) -> List(Persons)
    private List<SyndPerson> parsePersons(final List<Element> ePersons) {

        final List<SyndPerson> persons = new ArrayList<SyndPerson>();

        for (final Element person : ePersons) {
            persons.add(parsePerson(person));
        }

        return Lists.emptyToNull(persons);

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
            final List<org.jdom2.Content> contents = e.getContent();
            for (final org.jdom2.Content content : contents) {
                if (content instanceof Element) {
                    final Element element = (Element) content;
                    if (element.getNamespace().equals(getAtomNamespace())) {
                        element.setNamespace(Namespace.NO_NAMESPACE);
                    }
                }

            }
            value = outputter.outputString(contents);

        }

        final Content content = new Content();
        content.setType(type);
        content.setMode(mode);
        content.setValue(value);
        return content;
    }

    // List(Elements) -> List(Entries)
    private List<Entry> parseEntries(final List<Element> eEntries, final Locale locale) {

        final List<Entry> entries = new ArrayList<Entry>();
        for (final Element entry : eEntries) {
            entries.add(parseEntry(entry, locale));
        }

        return Lists.emptyToNull(entries);

    }

    private Entry parseEntry(final Element eEntry, final Locale locale) {

        final Entry entry = new Entry();

        final Element title = eEntry.getChild("title", getAtomNamespace());
        if (title != null) {
            entry.setTitleEx(parseContent(title));
        }

        final List<Element> links = eEntry.getChildren("link", getAtomNamespace());
        entry.setAlternateLinks(parseAlternateLinks(links));
        entry.setOtherLinks(parseOtherLinks(links));

        final Element author = eEntry.getChild("author", getAtomNamespace());
        if (author != null) {
            final List<SyndPerson> authors = new ArrayList<SyndPerson>();
            authors.add(parsePerson(author));
            entry.setAuthors(authors);
        }

        final List<Element> contributors = eEntry.getChildren("contributor", getAtomNamespace());
        if (!contributors.isEmpty()) {
            entry.setContributors(parsePersons(contributors));
        }

        final Element id = eEntry.getChild("id", getAtomNamespace());
        if (id != null) {
            entry.setId(id.getText());
        }

        final Element modified = eEntry.getChild("modified", getAtomNamespace());
        if (modified != null) {
            entry.setModified(DateParser.parseDate(modified.getText(), locale));
        }

        final Element issued = eEntry.getChild("issued", getAtomNamespace());
        if (issued != null) {
            entry.setIssued(DateParser.parseDate(issued.getText(), locale));
        }

        final Element created = eEntry.getChild("created", getAtomNamespace());
        if (created != null) {
            entry.setCreated(DateParser.parseDate(created.getText(), locale));
        }

        final Element summary = eEntry.getChild("summary", getAtomNamespace());
        if (summary != null) {
            entry.setSummary(parseContent(summary));
        }

        final List<Element> contents = eEntry.getChildren("content", getAtomNamespace());
        if (!contents.isEmpty()) {
            final List<Content> content = new ArrayList<Content>();
            for (final Element eContent : contents) {
                content.add(parseContent(eContent));
            }
            entry.setContents(content);
        }

        entry.setModules(parseItemModules(eEntry, locale));

        final List<Element> foreignMarkup = extractForeignMarkup(eEntry, entry, getAtomNamespace());
        if (!foreignMarkup.isEmpty()) {
            entry.setForeignMarkup(foreignMarkup);
        }

        return entry;

    }

}
