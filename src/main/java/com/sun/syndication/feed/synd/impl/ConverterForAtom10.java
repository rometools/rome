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
package com.sun.syndication.feed.synd.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.atom.Category;
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.atom.Person;
import com.sun.syndication.feed.module.impl.ModuleUtils;
import com.sun.syndication.feed.synd.Converter;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEnclosureImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.feed.synd.SyndLink;
import com.sun.syndication.feed.synd.SyndLinkImpl;
import com.sun.syndication.feed.synd.SyndPerson;

/**
 */
public class ConverterForAtom10 implements Converter {
    private final String type;

    public ConverterForAtom10() {
        this("atom_1.0");
    }

    protected ConverterForAtom10(final String type) {
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

        syndFeed.setUri(aFeed.getId());

        final Content aTitle = aFeed.getTitleEx();
        if (aTitle != null) {
            final SyndContent c = new SyndContentImpl();
            c.setType(aTitle.getType());
            c.setValue(aTitle.getValue());
            syndFeed.setTitleEx(c);
        }

        final Content aSubtitle = aFeed.getSubtitle();
        if (aSubtitle != null) {
            final SyndContent c = new SyndContentImpl();
            c.setType(aSubtitle.getType());
            c.setValue(aSubtitle.getValue());
            syndFeed.setDescriptionEx(c);
        }

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

        final List<Entry> aEntries = aFeed.getEntries();
        if (aEntries != null) {
            syndFeed.setEntries(createSyndEntries(aFeed, aEntries, syndFeed.isPreservingWireFeed()));
        }

        // Core Atom language/author/copyright/modified elements have precedence
        // over DC equivalent info.

        final List<Person> authors = aFeed.getAuthors();
        if (authors != null && authors.size() > 0) {
            syndFeed.setAuthors(ConverterForAtom03.createSyndPersons(authors));
        }

        final List<Person> contributors = aFeed.getContributors();
        if (contributors != null && contributors.size() > 0) {
            syndFeed.setContributors(ConverterForAtom03.createSyndPersons(contributors));
        }

        final String rights = aFeed.getRights();
        if (rights != null) {
            syndFeed.setCopyright(rights);
        }

        final Date date = aFeed.getUpdated();
        if (date != null) {
            syndFeed.setPublishedDate(date);
        }

    }

    protected List<SyndLink> createSyndLinks(final List<Link> aLinks) {
        final ArrayList<SyndLink> sLinks = new ArrayList<SyndLink>();
        for (final Link link2 : aLinks) {
            final Link link = link2;
            final SyndLink sLink = createSyndLink(link);
            sLinks.add(sLink);
        }
        return sLinks;
    }

    protected List<SyndEntry> createSyndEntries(final Feed feed, final List<Entry> atomEntries, final boolean preserveWireItems) {
        final List<SyndEntry> syndEntries = new ArrayList<SyndEntry>();
        for (int i = 0; i < atomEntries.size(); i++) {
            syndEntries.add(createSyndEntry(feed, atomEntries.get(i), preserveWireItems));
        }
        return syndEntries;
    }

    protected SyndEntry createSyndEntry(final Feed feed, final Entry entry, final boolean preserveWireItem) {
        final SyndEntryImpl syndEntry = new SyndEntryImpl();
        if (preserveWireItem) {
            syndEntry.setWireEntry(entry);
        }
        syndEntry.setModules(ModuleUtils.cloneModules(entry.getModules()));

        if (entry.getForeignMarkup().size() > 0) {
            syndEntry.setForeignMarkup(entry.getForeignMarkup());
        }

        final Content eTitle = entry.getTitleEx();
        if (eTitle != null) {
            syndEntry.setTitleEx(createSyndContent(eTitle));
        }

        final Content summary = entry.getSummary();
        if (summary != null) {
            syndEntry.setDescription(createSyndContent(summary));
        }

        final List<Content> contents = entry.getContents();
        if (contents != null && contents.size() > 0) {
            final List<SyndContent> sContents = new ArrayList<SyndContent>();
            for (final Content content2 : contents) {
                final Content content = content2;
                sContents.add(createSyndContent(content));
            }
            syndEntry.setContents(sContents);
        }

        final List<Person> authors = entry.getAuthors();
        if (authors != null && authors.size() > 0) {
            syndEntry.setAuthors(ConverterForAtom03.createSyndPersons(authors));
            final SyndPerson person0 = syndEntry.getAuthors().get(0);
            syndEntry.setAuthor(person0.getName());
        }

        final List<Person> contributors = entry.getContributors();
        if (contributors != null && contributors.size() > 0) {
            syndEntry.setContributors(ConverterForAtom03.createSyndPersons(contributors));
        }

        Date date = entry.getPublished();
        if (date != null) {
            syndEntry.setPublishedDate(date);
        }

        date = entry.getUpdated();
        if (date != null) {
            syndEntry.setUpdatedDate(date);
        }

        final List<Category> categories = entry.getCategories();
        if (categories != null) {
            final List<SyndCategory> syndCategories = new ArrayList<SyndCategory>();
            for (final Category category : categories) {
                final Category c = category;
                final SyndCategory syndCategory = new SyndCategoryImpl();
                syndCategory.setName(c.getTerm());
                syndCategory.setTaxonomyUri(c.getSchemeResolved());
                // TODO: categories MAY have labels
                // syndCategory.setLabel(c.getLabel());
                syndCategories.add(syndCategory);
            }
            syndEntry.setCategories(syndCategories);
        }

        // use first alternate link as THE link
        if (entry.getAlternateLinks() != null && entry.getAlternateLinks().size() > 0) {
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
                    syndEnclosures.add(createSyndEnclosure(feed, entry, thisLink));
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

        // Convert source element Feed into SyndFeed and assign as SyndEntry
        // source
        final Feed source = entry.getSource();
        if (source != null) {
            final SyndFeed syndSource = new SyndFeedImpl(source);
            syndEntry.setSource(syndSource);
        }

        return syndEntry;
    }

    public SyndEnclosure createSyndEnclosure(final Feed feed, final Entry entry, final Link link) {
        final SyndEnclosure syndEncl = new SyndEnclosureImpl();
        syndEncl.setUrl(link.getHrefResolved());
        syndEncl.setType(link.getType());
        syndEncl.setLength(link.getLength());
        return syndEncl;
    }

    public Link createAtomEnclosure(final SyndEnclosure syndEnclosure) {
        final Link link = new Link();
        link.setRel("enclosure");
        link.setType(syndEnclosure.getType());
        link.setHref(syndEnclosure.getUrl());
        link.setLength(syndEnclosure.getLength());
        return link;
    }

    public SyndLink createSyndLink(final Link link) {
        final SyndLink syndLink = new SyndLinkImpl();
        syndLink.setRel(link.getRel());
        syndLink.setType(link.getType());
        syndLink.setHref(link.getHrefResolved());
        syndLink.setHreflang(link.getHreflang());
        syndLink.setLength(link.getLength());
        syndLink.setTitle(link.getTitle());
        return syndLink;
    }

    public Link createAtomLink(final SyndLink syndLink) {
        final Link link = new Link();
        link.setRel(syndLink.getRel());
        link.setType(syndLink.getType());
        link.setHref(syndLink.getHref());
        link.setHreflang(syndLink.getHreflang());
        link.setLength(syndLink.getLength());
        link.setTitle(syndLink.getTitle());
        return link;
    }

    @Override
    public WireFeed createRealFeed(final SyndFeed syndFeed) {
        final Feed aFeed = new Feed(getType());
        aFeed.setModules(ModuleUtils.cloneModules(syndFeed.getModules()));

        aFeed.setEncoding(syndFeed.getEncoding());

        aFeed.setId(syndFeed.getUri());

        final SyndContent sTitle = syndFeed.getTitleEx();
        if (sTitle != null) {
            final Content title = new Content();
            title.setType(sTitle.getType());
            title.setValue(sTitle.getValue());
            aFeed.setTitleEx(title);
        }

        final SyndContent sDesc = syndFeed.getDescriptionEx();
        if (sDesc != null) {
            final Content subtitle = new Content();
            subtitle.setType(sDesc.getType());
            subtitle.setValue(sDesc.getValue());
            aFeed.setSubtitle(subtitle);
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

        final List<SyndCategory> sCats = syndFeed.getCategories();
        final List<Category> aCats = new ArrayList<Category>();
        if (sCats != null) {
            for (final SyndCategory syndCategory : sCats) {
                final SyndCategory sCat = syndCategory;
                final Category aCat = new Category();
                aCat.setTerm(sCat.getName());
                // TODO: aCat.setLabel(sCat.getLabel());
                aCat.setScheme(sCat.getTaxonomyUri());
                aCats.add(aCat);
            }
        }
        if (aCats.size() > 0) {
            aFeed.setCategories(aCats);
        }

        final List<SyndPerson> authors = syndFeed.getAuthors();
        if (authors != null && authors.size() > 0) {
            aFeed.setAuthors(ConverterForAtom03.createAtomPersons(authors));
        }

        final List<SyndPerson> contributors = syndFeed.getContributors();
        if (contributors != null && contributors.size() > 0) {
            aFeed.setContributors(ConverterForAtom03.createAtomPersons(contributors));
        }

        aFeed.setRights(syndFeed.getCopyright());

        aFeed.setUpdated(syndFeed.getPublishedDate());

        final List<SyndEntry> sEntries = syndFeed.getEntries();
        if (sEntries != null) {
            aFeed.setEntries(createAtomEntries(sEntries));
        }

        if (syndFeed.getForeignMarkup().size() > 0) {
            aFeed.setForeignMarkup(syndFeed.getForeignMarkup());
        }
        return aFeed;
    }

    protected SyndContent createSyndContent(final Content content) {
        final SyndContent sContent = new SyndContentImpl();
        sContent.setType(content.getType());
        sContent.setValue(content.getValue());
        return sContent;
    }

    protected List<Entry> createAtomEntries(final List<SyndEntry> syndEntries) {
        final List<Entry> atomEntries = new ArrayList<Entry>();
        for (int i = 0; i < syndEntries.size(); i++) {
            atomEntries.add(createAtomEntry(syndEntries.get(i)));
        }
        return atomEntries;
    }

    protected Content createAtomContent(final SyndContent sContent) {
        final Content content = new Content();
        content.setType(sContent.getType());
        content.setValue(sContent.getValue());
        return content;
    }

    protected List<Content> createAtomContents(final List<SyndContent> syndContents) {
        final List<Content> atomContents = new ArrayList<Content>();
        for (int i = 0; i < syndContents.size(); i++) {
            atomContents.add(createAtomContent(syndContents.get(i)));
        }
        return atomContents;
    }

    protected Entry createAtomEntry(final SyndEntry sEntry) {
        final Entry aEntry = new Entry();
        aEntry.setModules(ModuleUtils.cloneModules(sEntry.getModules()));

        aEntry.setId(sEntry.getUri());

        final SyndContent sTitle = sEntry.getTitleEx();
        if (sTitle != null) {
            final Content title = new Content();
            title.setType(sTitle.getType());
            title.setValue(sTitle.getValue());
            aEntry.setTitleEx(title);
        }

        final SyndContent sDescription = sEntry.getDescription();
        if (sDescription != null) {
            final Content summary = new Content();
            summary.setType(sDescription.getType());
            summary.setValue(sDescription.getValue());
            aEntry.setSummary(summary);
        }

        // separate SyndEntry's links collection into alternate and other links
        final List<Link> alternateLinks = new ArrayList<Link>();
        final List<Link> otherLinks = new ArrayList<Link>();
        final List<SyndLink> slinks = sEntry.getLinks();
        final List<SyndEnclosure> enclosures = sEntry.getEnclosures();
        boolean linkRelEnclosureExists = false;
        if (slinks != null) {
            for (final SyndLink syndLink2 : slinks) {
                final SyndLink syndLink = syndLink2;
                final Link link = createAtomLink(syndLink);
                // Set this flag if there's a link of rel = enclosure so that
                // enclosures won't be duplicated when pulled from
                // SyndEntry.getEnclosures()
                if (syndLink.getRel() != null && "enclosure".equals(syndLink.getRel())) {
                    linkRelEnclosureExists = true;
                }
                if (link.getRel() == null || "".equals(link.getRel().trim()) || "alternate".equals(syndLink.getRel())) {
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
        // add SyndEnclosures as links with rel="enclosure" ONLY if
        // there are no SyndEntry.getLinks() with rel="enclosure"
        if (enclosures != null && linkRelEnclosureExists == false) {
            for (final SyndEnclosure syndEnclosure : enclosures) {
                final SyndEnclosure syndEncl = syndEnclosure;
                final Link link = createAtomEnclosure(syndEncl);
                otherLinks.add(link);
            }
        }
        if (alternateLinks.size() > 0) {
            aEntry.setAlternateLinks(alternateLinks);
        }
        if (otherLinks.size() > 0) {
            aEntry.setOtherLinks(otherLinks);
        }

        final List<SyndCategory> sCats = sEntry.getCategories();
        final List<Category> aCats = new ArrayList<Category>();
        if (sCats != null) {
            for (final SyndCategory syndCategory : sCats) {
                final SyndCategory sCat = syndCategory;
                final Category aCat = new Category();
                aCat.setTerm(sCat.getName());
                // TODO: aCat.setLabel(sCat.getLabel());
                aCat.setScheme(sCat.getTaxonomyUri());
                aCats.add(aCat);
            }
        }
        if (aCats.size() > 0) {
            aEntry.setCategories(aCats);
        }

        final List<SyndContent> syndContents = sEntry.getContents();
        aEntry.setContents(createAtomContents(syndContents));

        List authors = sEntry.getAuthors();
        if (authors != null && authors.size() > 0) {
            aEntry.setAuthors(ConverterForAtom03.createAtomPersons(authors));
        } else if (sEntry.getAuthor() != null) {
            final Person person = new Person();
            person.setName(sEntry.getAuthor());
            authors = new ArrayList();
            authors.add(person);
            aEntry.setAuthors(authors);
        }

        final List<SyndPerson> contributors = sEntry.getContributors();
        if (contributors != null && contributors.size() > 0) {
            aEntry.setContributors(ConverterForAtom03.createAtomPersons(contributors));
        }

        aEntry.setPublished(sEntry.getPublishedDate());

        // Fix for issue #41 "Use updated instead of published"
        // And issue #42 "Atom 1.0 Date (Updated or Published) Not Set"
        // Atom requires an updated date, if it's missing use the published date
        if (sEntry.getUpdatedDate() != null) {
            aEntry.setUpdated(sEntry.getUpdatedDate());
        } else {
            aEntry.setUpdated(sEntry.getPublishedDate());
        }

        if (sEntry.getForeignMarkup().size() > 0) {
            aEntry.setForeignMarkup(sEntry.getForeignMarkup());
        }

        final SyndFeed sSource = sEntry.getSource();
        if (sSource != null) {
            final Feed aSource = (Feed) sSource.createWireFeed(getType());
            aEntry.setSource(aSource);
        }
        return aEntry;
    }

}
