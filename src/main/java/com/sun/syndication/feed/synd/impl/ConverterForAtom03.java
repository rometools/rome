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
package com.sun.syndication.feed.synd.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.atom.Person;
import com.sun.syndication.feed.module.impl.ModuleUtils;
import com.sun.syndication.feed.synd.Converter;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEnclosureImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndImage;
import com.sun.syndication.feed.synd.SyndImageImpl;
import com.sun.syndication.feed.synd.SyndLink;
import com.sun.syndication.feed.synd.SyndLinkImpl;
import com.sun.syndication.feed.synd.SyndPerson;
import com.sun.syndication.feed.synd.SyndPersonImpl;

/**
 */
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

        if (feed.getForeignMarkup().size() > 0) {
            syndFeed.setForeignMarkup(feed.getForeignMarkup());
        }

        syndFeed.setEncoding(aFeed.getEncoding());
        syndFeed.setStyleSheet(aFeed.getStyleSheet());
        
        if (aFeed.getLogo() != null) {
            SyndImage image = new SyndImageImpl();
            image.setUrl(aFeed.getLogo());
            syndFeed.setImage(image);
        } else if (aFeed.getIcon() != null) {
            SyndImage image = new SyndImageImpl();
            image.setUrl(aFeed.getIcon());
            syndFeed.setImage(image);
        }

        syndFeed.setUri(aFeed.getId());

        syndFeed.setTitle(aFeed.getTitle());

        // use first alternate links as THE link
        if (aFeed.getAlternateLinks() != null && aFeed.getAlternateLinks().size() > 0) {
            final Link theLink = aFeed.getAlternateLinks().get(0);
            syndFeed.setLink(theLink.getHrefResolved());
        }
        // lump alternate and other links together
        final List<SyndLink> syndLinks = new ArrayList<SyndLink>();
        if (aFeed.getAlternateLinks() != null && aFeed.getAlternateLinks().size() > 0) {
            syndLinks.addAll(createSyndLinks(aFeed.getAlternateLinks()));
        }
        if (aFeed.getOtherLinks() != null && aFeed.getOtherLinks().size() > 0) {
            syndLinks.addAll(createSyndLinks(aFeed.getOtherLinks()));
        }
        syndFeed.setLinks(syndLinks);

        final Content tagline = aFeed.getTagline();
        if (tagline != null) {
            syndFeed.setDescription(tagline.getValue());
        }

        final List<Entry> aEntries = aFeed.getEntries();
        if (aEntries != null) {
            syndFeed.setEntries(createSyndEntries(aEntries, syndFeed.isPreservingWireFeed()));
        }

        // Core Atom language/author/copyright/modified elements have precedence
        // over DC equivalent info.

        final String language = aFeed.getLanguage();
        if (language != null) {
            syndFeed.setLanguage(language);
        }

        final List<SyndPerson> authors = aFeed.getAuthors();
        if (authors != null && authors.size() > 0) {
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

    protected List<SyndLink> createSyndLinks(final List<Link> aLinks) {
        final ArrayList<SyndLink> sLinks = new ArrayList<SyndLink>();
        for (final Link link2 : aLinks) {
            final Link link = link2;
            if (!link.getRel().equals("enclosure")) {
                final SyndLink sLink = createSyndLink(link);
                sLinks.add(sLink);
            }
        }
        return sLinks;
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
        for (int i = 0; i < atomEntries.size(); i++) {
            syndEntries.add(createSyndEntry(atomEntries.get(i), preserveWireItems));
        }
        return syndEntries;
    }

    protected SyndEntry createSyndEntry(final Entry entry, final boolean preserveWireItem) {
        final SyndEntryImpl syndEntry = new SyndEntryImpl();
        if (preserveWireItem) {
            syndEntry.setWireEntry(entry);
        }

        syndEntry.setModules(ModuleUtils.cloneModules(entry.getModules()));

        if (entry.getForeignMarkup().size() > 0) {
            syndEntry.setForeignMarkup(entry.getForeignMarkup());
        }

        syndEntry.setTitle(entry.getTitle());

        // if there is exactly one alternate link, use that as THE link
        if (entry.getAlternateLinks() != null && entry.getAlternateLinks().size() == 1) {
            final Link theLink = entry.getAlternateLinks().get(0);
            syndEntry.setLink(theLink.getHrefResolved());
        }

        // Create synd enclosures from enclosure links
        final List<SyndEnclosure> syndEnclosures = new ArrayList<SyndEnclosure>();
        if (entry.getOtherLinks() != null && entry.getOtherLinks().size() > 0) {
            final List<Link> oLinks = entry.getOtherLinks();
            for (final Link link : oLinks) {
                final Link thisLink = link;
                if ("enclosure".equals(thisLink.getRel())) {
                    syndEnclosures.add(createSyndEnclosure(entry, thisLink));
                }
            }
        }
        syndEntry.setEnclosures(syndEnclosures);

        // lump alternate and other links together
        final List<SyndLink> syndLinks = new ArrayList<SyndLink>();
        if (entry.getAlternateLinks() != null && entry.getAlternateLinks().size() > 0) {
            syndLinks.addAll(createSyndLinks(entry.getAlternateLinks()));
        }
        if (entry.getOtherLinks() != null && entry.getOtherLinks().size() > 0) {
            syndLinks.addAll(createSyndLinks(entry.getOtherLinks()));
        }
        syndEntry.setLinks(syndLinks);

        final String id = entry.getId();
        if (id != null) {
            syndEntry.setUri(entry.getId());
        } else {
            syndEntry.setUri(syndEntry.getLink());
        }

        Content content = entry.getSummary();
        if (content == null) {
            final List<Content> contents = entry.getContents();
            if (contents != null && contents.size() > 0) {
                content = contents.get(0);
            }
        }
        if (content != null) {
            final SyndContent sContent = new SyndContentImpl();
            sContent.setType(content.getType());
            sContent.setValue(content.getValue());
            syndEntry.setDescription(sContent);
        }

        final List<Content> contents = entry.getContents();
        if (contents.size() > 0) {
            final List<SyndContent> sContents = new ArrayList<SyndContent>();
            for (int i = 0; i < contents.size(); i++) {
                content = contents.get(i);
                final SyndContent sContent = new SyndContentImpl();
                sContent.setType(content.getType());
                sContent.setValue(content.getValue());
                sContent.setMode(content.getMode());
                sContents.add(sContent);
            }
            syndEntry.setContents(sContents);
        }

        final List<SyndPerson> authors = entry.getAuthors();
        if (authors != null && authors.size() > 0) {
            syndEntry.setAuthors(createSyndPersons(authors));
            final SyndPerson person0 = syndEntry.getAuthors().get(0);
            syndEntry.setAuthor(person0.getName());
        }

        Date date = entry.getModified();
        if (date == null) {
            date = entry.getIssued();
            if (date == null) {
                date = entry.getCreated();
            }
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
            if (sTitle.getType() != null) {
                title.setType(sTitle.getType());
            }

            if (sTitle.getMode() != null) {
                title.setMode(sTitle.getMode());
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
                if (link.getRel() == null || "".equals(link.getRel().trim()) || "alternate".equals(link.getRel())) {
                    alternateLinks.add(link);
                } else {
                    otherLinks.add(link);
                }
            }
        }
        // no alternate link? then use THE link if there is one
        if (alternateLinks.isEmpty() && syndFeed.getLink() != null) {
            final Link link = new Link();
            link.setRel("alternate");
            link.setHref(syndFeed.getLink());
            alternateLinks.add(link);
        }

        if (alternateLinks.size() > 0) {
            aFeed.setAlternateLinks(alternateLinks);
        }
        if (otherLinks.size() > 0) {
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
        if (authors != null && authors.size() > 0) {
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
        for (int i = 0; i < syndEntries.size(); i++) {
            atomEntries.add(createAtomEntry(syndEntries.get(i)));
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
            if (sTitle.getType() != null) {
                title.setType(sTitle.getType());
            }

            if (sTitle.getMode() != null) {
                title.setMode(sTitle.getMode());
            }

            title.setValue(sTitle.getValue());
            aEntry.setTitleEx(title);
        }

        // separate SyndEntry's links collection into alternate and other links
        final List<Link> alternateLinks = new ArrayList<Link>();
        final List<Link> otherLinks = new ArrayList<Link>();
        final List<SyndLink> slinks = sEntry.getLinks();
        if (slinks != null) {
            for (final SyndLink syndLink2 : slinks) {
                final SyndLink syndLink = syndLink2;
                final Link link = createAtomLink(syndLink);
                if (link.getRel() == null || "".equals(link.getRel().trim()) || "alternate".equals(link.getRel())) {
                    alternateLinks.add(link);
                } else {
                    otherLinks.add(link);
                }
            }
        }
        // no alternate link? then use THE link if there is one
        if (alternateLinks.isEmpty() && sEntry.getLink() != null) {
            final Link link = new Link();
            link.setRel("alternate");
            link.setHref(sEntry.getLink());
            alternateLinks.add(link);
        }

        final List<SyndEnclosure> sEnclosures = sEntry.getEnclosures();
        if (sEnclosures != null) {
            for (final SyndEnclosure syndEnclosure2 : sEnclosures) {
                final SyndEnclosure syndEnclosure = syndEnclosure2;
                final Link link = createAtomEnclosure(syndEnclosure);
                otherLinks.add(link);
            }
        }

        if (alternateLinks.size() > 0) {
            aEntry.setAlternateLinks(alternateLinks);
        }
        if (otherLinks.size() > 0) {
            aEntry.setOtherLinks(otherLinks);
        }

        SyndContent sContent = sEntry.getDescription();
        if (sContent != null) {
            final Content content = new Content();
            content.setType(sContent.getType());
            content.setValue(sContent.getValue());
            content.setMode(Content.ESCAPED);
            aEntry.setSummary(content);
        }

        final List<SyndContent> contents = sEntry.getContents();
        if (contents.size() > 0) {
            final List<Content> aContents = new ArrayList<Content>();
            for (int i = 0; i < contents.size(); i++) {
                sContent = contents.get(i);
                final Content content = new Content();
                content.setType(sContent.getType());
                content.setValue(sContent.getValue());
                content.setMode(sContent.getMode());
                aContents.add(content);

            }
            aEntry.setContents(aContents);
        }

        final List<SyndPerson> sAuthors = sEntry.getAuthors();
        if (sAuthors != null && sAuthors.size() > 0) {
            aEntry.setAuthors(createAtomPersons(sAuthors));
        } else if (sEntry.getAuthor() != null) {
            final Person person = new Person();
            person.setName(sEntry.getAuthor());
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
