/*
 * Copyright 2004 Sun Microsystems, Inc.
 * Copyright 2011 The ROME Team
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
package com.rometools.rome.feed.synd.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Element;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.atom.Person;
import com.rometools.rome.feed.module.impl.ModuleUtils;
import com.rometools.rome.feed.synd.Converter;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEnclosureImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndImage;
import com.rometools.rome.feed.synd.SyndImageImpl;
import com.rometools.rome.feed.synd.SyndLink;
import com.rometools.rome.feed.synd.SyndLinkImpl;
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.rome.feed.synd.SyndPersonImpl;
import com.rometools.utils.Alternatives;
import com.rometools.utils.Lists;
import com.rometools.utils.Strings;

public class ConverterForAtom03 implements Converter {

    private final String type;

    public ConverterForAtom03() {
        this("atom_0.3");
    }

    protected ConverterForAtom03(final String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void copyInto(final WireFeed feed, final SyndFeed syndFeed) {

        final Feed aFeed = (Feed) feed;

        syndFeed.setModules(ModuleUtils.cloneModules(aFeed.getModules()));

        final List<Element> foreignMarkup = feed.getForeignMarkup();
        if (Lists.isNotEmpty(foreignMarkup)) {
            syndFeed.setForeignMarkup(foreignMarkup);
        }

        syndFeed.setEncoding(aFeed.getEncoding());
        syndFeed.setStyleSheet(aFeed.getStyleSheet());

        final String logo = aFeed.getLogo();
        final String icon = aFeed.getIcon();

        if (logo != null) {
            final SyndImage image = new SyndImageImpl();
            image.setUrl(logo);
            syndFeed.setImage(image);
        } else if (icon != null) {
            final SyndImage image = new SyndImageImpl();
            image.setUrl(icon);
            syndFeed.setImage(image);
        }

        syndFeed.setUri(aFeed.getId());

        syndFeed.setTitle(aFeed.getTitle());

        // use first alternate links as THE link
        final List<Link> alternateLinks = aFeed.getAlternateLinks();
        if (Lists.isNotEmpty(alternateLinks)) {
            final Link link = alternateLinks.get(0);
            syndFeed.setLink(link.getHrefResolved());
        }
        // lump alternate and other links together
        final List<SyndLink> syndLinks = new ArrayList<SyndLink>();
        if (Lists.isNotEmpty(alternateLinks)) {
            syndLinks.addAll(createSyndLinks(alternateLinks));
        }
        final List<Link> otherLinks = aFeed.getOtherLinks();
        if (Lists.isNotEmpty(otherLinks)) {
            syndLinks.addAll(createSyndLinks(otherLinks));
        }
        syndFeed.setLinks(syndLinks);

        final Content tagline = aFeed.getTagline();
        if (tagline != null) {
            syndFeed.setDescription(tagline.getValue());
        }

        final List<Entry> aEntries = aFeed.getEntries();
        if (Lists.isNotEmpty(aEntries)) {
            syndFeed.setEntries(createSyndEntries(aEntries, syndFeed.isPreservingWireFeed()));
        }

        // Core Atom language/author/copyright/modified elements have precedence
        // over DC equivalent info.

        final String language = aFeed.getLanguage();
        if (language != null) {
            syndFeed.setLanguage(language);
        }

        final List<SyndPerson> authors = aFeed.getAuthors();
        if (Lists.isNotEmpty(authors)) {
            syndFeed.setAuthors(createSyndPersons(authors));
        }

        final String copyright = aFeed.getCopyright();
        if (copyright != null) {
            syndFeed.setCopyright(copyright);
        }

        final Date date = aFeed.getModified();
        if (date != null) {
            syndFeed.setPublishedDate(date);
        }

    }

    protected List<SyndLink> createSyndLinks(final List<Link> atomLinks) {
        final ArrayList<SyndLink> syndLinks = new ArrayList<SyndLink>();
        for (final Link atomLink : atomLinks) {
            final Link link = atomLink;
            if (!link.getRel().equals("enclosure")) {
                final SyndLink syndLink = createSyndLink(link);
                syndLinks.add(syndLink);
            }
        }
        return syndLinks;
    }

    public SyndLink createSyndLink(final Link link) {
        final SyndLink syndLink = new SyndLinkImpl();
        syndLink.setRel(link.getRel());
        syndLink.setType(link.getType());
        syndLink.setHref(link.getHrefResolved());
        syndLink.setTitle(link.getTitle());
        return syndLink;
    }

    protected List<SyndEntry> createSyndEntries(final List<Entry> atomEntries, final boolean preserveWireItems) {
        final List<SyndEntry> syndEntries = new ArrayList<SyndEntry>();
        for (final Entry atomEntry : atomEntries) {
            syndEntries.add(createSyndEntry(atomEntry, preserveWireItems));
        }
        return syndEntries;
    }

    protected SyndEntry createSyndEntry(final Entry entry, final boolean preserveWireItem) {

        final SyndEntryImpl syndEntry = new SyndEntryImpl();

        if (preserveWireItem) {
            syndEntry.setWireEntry(entry);
        }

        syndEntry.setModules(ModuleUtils.cloneModules(entry.getModules()));

        final List<Element> foreignMarkup = entry.getForeignMarkup();
        if (Lists.isNotEmpty(foreignMarkup)) {
            syndEntry.setForeignMarkup(foreignMarkup);
        }

        syndEntry.setTitle(entry.getTitle());

        // if there is exactly one alternate link, use that as THE link
        final List<Link> alternateLinks = entry.getAlternateLinks();
        if (Lists.sizeIs(alternateLinks, 1)) {
            final Link theLink = alternateLinks.get(0);
            syndEntry.setLink(theLink.getHrefResolved());
        }

        // Create synd enclosures from enclosure links
        final List<SyndEnclosure> syndEnclosures = new ArrayList<SyndEnclosure>();
        final List<Link> otherLinks = entry.getOtherLinks();
        if (Lists.isNotEmpty(otherLinks)) {
            for (final Link otherLink : otherLinks) {
                final Link thisLink = otherLink;
                if ("enclosure".equals(thisLink.getRel())) {
                    syndEnclosures.add(createSyndEnclosure(entry, thisLink));
                }
            }
        }
        syndEntry.setEnclosures(syndEnclosures);

        // lump alternate and other links together
        final List<SyndLink> syndLinks = new ArrayList<SyndLink>();

        if (Lists.isNotEmpty(alternateLinks)) {
            syndLinks.addAll(createSyndLinks(alternateLinks));
        }

        if (Lists.isNotEmpty(otherLinks)) {
            syndLinks.addAll(createSyndLinks(otherLinks));
        }

        syndEntry.setLinks(syndLinks);

        final String id = entry.getId();
        if (id != null) {
            syndEntry.setUri(id);
        } else {
            final String link = syndEntry.getLink();
            syndEntry.setUri(link);
        }

        Content summary = entry.getSummary();
        if (summary == null) {
            final List<Content> contents = entry.getContents();
            if (Lists.isNotEmpty(contents)) {
                summary = contents.get(0);
            }
        } else {
            final SyndContent sContent = new SyndContentImpl();
            sContent.setType(summary.getType());
            sContent.setValue(summary.getValue());
            syndEntry.setDescription(sContent);
        }

        final List<Content> contents = entry.getContents();
        if (Lists.isNotEmpty(contents)) {
            final List<SyndContent> sContents = new ArrayList<SyndContent>();
            for (final Content content : contents) {
                final SyndContent sContent = new SyndContentImpl();
                sContent.setType(content.getType());
                sContent.setValue(content.getValue());
                sContent.setMode(content.getMode());
                sContents.add(sContent);

            }
            syndEntry.setContents(sContents);
        }

        final List<SyndPerson> authors = entry.getAuthors();
        if (Lists.isNotEmpty(authors)) {
            syndEntry.setAuthors(createSyndPersons(authors));
            final SyndPerson firstPerson = syndEntry.getAuthors().get(0);
            syndEntry.setAuthor(firstPerson.getName());
        }

        Date date = entry.getModified();
        if (date == null) {
            date = Alternatives.firstNotNull(entry.getIssued(), entry.getCreated());
        }

        if (date != null) {
            syndEntry.setPublishedDate(date);
        }

        return syndEntry;
    }

    public SyndEnclosure createSyndEnclosure(final Entry entry, final Link link) {
        final SyndEnclosure syndEncl = new SyndEnclosureImpl();
        syndEncl.setUrl(link.getHrefResolved());
        syndEncl.setType(link.getType());
        syndEncl.setLength(link.getLength());
        return syndEncl;
    }

    @Override
    public WireFeed createRealFeed(final SyndFeed syndFeed) {
        final Feed aFeed = new Feed(getType());
        aFeed.setModules(ModuleUtils.cloneModules(syndFeed.getModules()));

        aFeed.setEncoding(syndFeed.getEncoding());
        aFeed.setStyleSheet(syndFeed.getStyleSheet());

        aFeed.setId(syndFeed.getUri());

        final SyndContent sTitle = syndFeed.getTitleEx();
        if (sTitle != null) {

            final Content title = new Content();
            final String type = sTitle.getType();
            if (type != null) {
                title.setType(type);
            }

            final String mode = sTitle.getMode();
            if (mode != null) {
                title.setMode(mode);
            }

            title.setValue(sTitle.getValue());
            aFeed.setTitleEx(title);
        }

        // separate SyndEntry's links collection into alternate and other links
        final List<Link> alternateLinks = new ArrayList<Link>();
        final List<Link> otherLinks = new ArrayList<Link>();

        final List<SyndLink> slinks = syndFeed.getLinks();
        if (slinks != null) {
            for (final SyndLink syndLink2 : slinks) {
                final SyndLink syndLink = syndLink2;
                final Link link = createAtomLink(syndLink);
                final String rel = link.getRel();
                if (Strings.isBlank(rel) || "alternate".equals(rel)) {
                    alternateLinks.add(link);
                } else {
                    otherLinks.add(link);
                }
            }
        }
        // no alternate link? then use THE link if there is one
        final String sLink = syndFeed.getLink();
        if (alternateLinks.isEmpty() && sLink != null) {
            final Link link = new Link();
            link.setRel("alternate");
            link.setHref(sLink);
            alternateLinks.add(link);
        }

        if (!alternateLinks.isEmpty()) {
            aFeed.setAlternateLinks(alternateLinks);
        }
        if (!otherLinks.isEmpty()) {
            aFeed.setOtherLinks(otherLinks);
        }

        final String sDesc = syndFeed.getDescription();
        if (sDesc != null) {
            final Content tagline = new Content();
            tagline.setValue(sDesc);
            aFeed.setTagline(tagline);
        }

        aFeed.setLanguage(syndFeed.getLanguage());

        final List<SyndPerson> authors = syndFeed.getAuthors();
        if (Lists.isNotEmpty(authors)) {
            aFeed.setAuthors(createAtomPersons(authors));
        }

        aFeed.setCopyright(syndFeed.getCopyright());

        aFeed.setModified(syndFeed.getPublishedDate());

        final List<SyndEntry> sEntries = syndFeed.getEntries();
        if (sEntries != null) {
            aFeed.setEntries(createAtomEntries(sEntries));
        }

        return aFeed;
    }

    protected static List<SyndPerson> createAtomPersons(final List<SyndPerson> sPersons) {
        final List<SyndPerson> persons = new ArrayList<SyndPerson>();
        for (final SyndPerson syndPerson : sPersons) {
            final SyndPerson sPerson = syndPerson;
            final Person person = new Person();
            person.setName(sPerson.getName());
            person.setUri(sPerson.getUri());
            person.setEmail(sPerson.getEmail());
            person.setModules(sPerson.getModules());
            persons.add(person);
        }
        return persons;
    }

    protected static List<SyndPerson> createSyndPersons(final List<SyndPerson> aPersons) {
        final List<SyndPerson> persons = new ArrayList<SyndPerson>();
        for (final SyndPerson person2 : aPersons) {
            final SyndPerson person = new SyndPersonImpl();
            person.setName(person2.getName());
            person.setUri(person2.getUri());
            person.setEmail(person2.getEmail());
            person.setModules(person2.getModules());
            persons.add(person);
        }
        return persons;
    }

    protected List<Entry> createAtomEntries(final List<SyndEntry> syndEntries) {
        final List<Entry> atomEntries = new ArrayList<Entry>();
        for (final SyndEntry syndEntry : syndEntries) {
            atomEntries.add(createAtomEntry(syndEntry));
        }
        return atomEntries;
    }

    protected Entry createAtomEntry(final SyndEntry sEntry) {
        final Entry aEntry = new Entry();
        aEntry.setModules(ModuleUtils.cloneModules(sEntry.getModules()));

        aEntry.setId(sEntry.getUri());

        final SyndContent sTitle = sEntry.getTitleEx();
        if (sTitle != null) {
            final Content title = new Content();
            final String type = sTitle.getType();
            if (type != null) {
                title.setType(type);
            }

            final String mode = sTitle.getMode();
            if (mode != null) {
                title.setMode(mode);
            }

            title.setValue(sTitle.getValue());
            aEntry.setTitleEx(title);
        }

        // separate SyndEntry's links collection into alternate and other links
        final List<Link> alternateLinks = new ArrayList<Link>();
        final List<Link> otherLinks = new ArrayList<Link>();
        final List<SyndLink> syndLinks = sEntry.getLinks();

        if (syndLinks != null) {
            for (final SyndLink syndLink : syndLinks) {
                final Link link = createAtomLink(syndLink);
                final String rel = link.getRel();
                if (Strings.isBlank(rel) || "alternate".equals(rel)) {
                    alternateLinks.add(link);
                } else {
                    otherLinks.add(link);
                }
            }
        }
        // no alternate link? then use THE link if there is one
        final String sLink = sEntry.getLink();
        if (alternateLinks.isEmpty() && sLink != null) {
            final Link link = new Link();
            link.setRel("alternate");
            link.setHref(sLink);
            alternateLinks.add(link);
        }

        final List<SyndEnclosure> sEnclosures = sEntry.getEnclosures();
        if (sEnclosures != null) {
            for (final SyndEnclosure syndEnclosure : sEnclosures) {
                final Link link = createAtomEnclosure(syndEnclosure);
                otherLinks.add(link);
            }
        }

        if (!alternateLinks.isEmpty()) {
            aEntry.setAlternateLinks(alternateLinks);
        }
        if (!otherLinks.isEmpty()) {
            aEntry.setOtherLinks(otherLinks);
        }

        final SyndContent sContent = sEntry.getDescription();
        if (sContent != null) {
            final Content content = new Content();
            content.setType(sContent.getType());
            content.setValue(sContent.getValue());
            content.setMode(Content.ESCAPED);
            aEntry.setSummary(content);
        }

        final List<SyndContent> contents = sEntry.getContents();
        if (!contents.isEmpty()) {
            final List<Content> aContents = new ArrayList<Content>();
            for (final SyndContent syndContent : contents) {
                final Content content = new Content();
                content.setType(syndContent.getType());
                content.setValue(syndContent.getValue());
                content.setMode(syndContent.getMode());
                aContents.add(content);
            }
            aEntry.setContents(aContents);
        }

        final List<SyndPerson> sAuthors = sEntry.getAuthors();
        final String author = sEntry.getAuthor();
        if (Lists.isNotEmpty(sAuthors)) {
            aEntry.setAuthors(createAtomPersons(sAuthors));
        } else if (author != null) {
            final Person person = new Person();
            person.setName(author);
            final List<SyndPerson> authors = new ArrayList<SyndPerson>();
            authors.add(person);
            aEntry.setAuthors(authors);
        }

        aEntry.setModified(sEntry.getPublishedDate());
        aEntry.setIssued(sEntry.getPublishedDate());

        return aEntry;
    }

    public Link createAtomLink(final SyndLink syndLink) {
        final Link link = new Link();
        link.setRel(syndLink.getRel());
        link.setType(syndLink.getType());
        link.setHref(syndLink.getHref());
        link.setTitle(syndLink.getTitle());
        return link;
    }

    public Link createAtomEnclosure(final SyndEnclosure syndEnclosure) {
        final Link link = new Link();
        link.setRel("enclosure");
        link.setType(syndEnclosure.getType());
        link.setHref(syndEnclosure.getUrl());
        link.setLength(syndEnclosure.getLength());
        return link;
    }

}
