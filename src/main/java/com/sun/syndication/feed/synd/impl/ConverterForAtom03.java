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

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.atom.Person;
import com.sun.syndication.feed.module.impl.ModuleUtils;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.Converter;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEnclosureImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndLink;
import com.sun.syndication.feed.synd.SyndLinkImpl;
import com.sun.syndication.feed.synd.SyndPerson;
import com.sun.syndication.feed.synd.SyndPersonImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

/**
 */
public class ConverterForAtom03 implements Converter {
    private String _type;

    public ConverterForAtom03() {
        this("atom_0.3");
    }

    protected ConverterForAtom03(String type) {
        _type = type;
    }

    public String getType() {
        return _type;
    }

    public void copyInto(WireFeed feed,SyndFeed syndFeed) {
        Feed aFeed = (Feed) feed;

        syndFeed.setModules(ModuleUtils.cloneModules(aFeed.getModules()));

        if (((List)feed.getForeignMarkup()).size() > 0) {
            syndFeed.setForeignMarkup(feed.getForeignMarkup());
        }
        
        syndFeed.setEncoding(aFeed.getEncoding());

        syndFeed.setUri(aFeed.getId());

        syndFeed.setTitle(aFeed.getTitle());

        // use first alternate links as THE link
        if (aFeed.getAlternateLinks() != null
                && aFeed.getAlternateLinks().size() > 0) {
            Link theLink = (Link)aFeed.getAlternateLinks().get(0);
            syndFeed.setLink(theLink.getHrefResolved());
        }
        // lump alternate and other links together
        List syndLinks = new ArrayList();
        if (aFeed.getAlternateLinks() != null
                && aFeed.getAlternateLinks().size() > 0) {
            syndLinks.addAll(createSyndLinks(aFeed.getAlternateLinks()));
        }
        if (aFeed.getOtherLinks() != null
                && aFeed.getOtherLinks().size() > 0) {
            syndLinks.addAll(createSyndLinks(aFeed.getOtherLinks()));
        }
        syndFeed.setLinks(syndLinks);

        Content tagline = aFeed.getTagline();
        if (tagline!=null) {
            syndFeed.setDescription(tagline.getValue());
        }


        List aEntries = aFeed.getEntries();
        if (aEntries!=null) {
            syndFeed.setEntries(createSyndEntries(aEntries, syndFeed.isPreservingWireFeed()));
        }

        // Core Atom language/author/copyright/modified elements have precedence
        // over DC equivalent info.

        String language = aFeed.getLanguage();
        if (language!=null) {
            syndFeed.setLanguage(language);
        }

        List authors = aFeed.getAuthors();
        if (authors!=null && authors.size() > 0) {
            syndFeed.setAuthors(createSyndPersons(authors));
        }

        String copyright = aFeed.getCopyright();
        if (copyright!=null) {
            syndFeed.setCopyright(copyright);
        }

        Date date = aFeed.getModified();
        if (date!=null) {
            syndFeed.setPublishedDate(date);
        }

    }

    protected List createSyndLinks(List aLinks) {
        ArrayList sLinks = new ArrayList();
        for (Iterator iter = aLinks.iterator(); iter.hasNext();) {
            Link link = (Link)iter.next();
            if (!link.getRel().equals("enclosure")) {
                SyndLink sLink = createSyndLink(link);
                sLinks.add(sLink);
            }
        }
        return sLinks;
    }

    public SyndLink createSyndLink(Link link) {
        SyndLink syndLink = new SyndLinkImpl();
        syndLink.setRel(     link.getRel());
        syndLink.setType(    link.getType());
        syndLink.setHref(    link.getHrefResolved());
        syndLink.setTitle(   link.getTitle());
        return syndLink;
    }

    protected List createSyndEntries(List atomEntries, boolean preserveWireItems) {
        List syndEntries = new ArrayList();
        for (int i=0;i<atomEntries.size();i++) {
            syndEntries.add(createSyndEntry((Entry) atomEntries.get(i), preserveWireItems));
        }
        return syndEntries;
    }

    protected SyndEntry createSyndEntry(Entry entry, boolean preserveWireItem) {
        SyndEntryImpl syndEntry = new SyndEntryImpl();
        if (preserveWireItem) {
        	syndEntry.setWireEntry(entry);
        }
        
        syndEntry.setModules(ModuleUtils.cloneModules(entry.getModules()));

        if (((List)entry.getForeignMarkup()).size() >  0) {
            syndEntry.setForeignMarkup((List)entry.getForeignMarkup());
        }

        syndEntry.setTitle(entry.getTitle());

        // if there is exactly one alternate link, use that as THE link
        if (entry.getAlternateLinks() != null
				&& entry.getAlternateLinks().size() == 1) {
            Link theLink = (Link)entry.getAlternateLinks().get(0);
            syndEntry.setLink(theLink.getHrefResolved());
        }

        // Create synd enclosures from enclosure links
        List syndEnclosures = new ArrayList();
        if (entry.getOtherLinks() != null && entry.getOtherLinks().size() > 0) {
            List oLinks = entry.getOtherLinks();
            for (Iterator iter = oLinks.iterator(); iter.hasNext(); ) {
                Link thisLink = (Link)iter.next();
                if ("enclosure".equals(thisLink.getRel()))
                    syndEnclosures.add(createSyndEnclosure(entry, thisLink));
            }
        }
        syndEntry.setEnclosures(syndEnclosures);

        // lump alternate and other links together
        List syndLinks = new ArrayList();
        if (entry.getAlternateLinks() != null
                && entry.getAlternateLinks().size() > 0) {
            syndLinks.addAll(createSyndLinks(entry.getAlternateLinks()));
        }
        if (entry.getOtherLinks() != null
                && entry.getOtherLinks().size() > 0) {
            syndLinks.addAll(createSyndLinks(entry.getOtherLinks()));
        }
        syndEntry.setLinks(syndLinks);


        String id = entry.getId();
        if (id!=null) {
            syndEntry.setUri(entry.getId());
        }
        else {
            syndEntry.setUri(syndEntry.getLink());
        }

        Content content = entry.getSummary();
        if (content==null) {
            List contents = entry.getContents();
            if (contents!=null && contents.size()>0) {
                content = (Content) contents.get(0);
            }
        }
        if (content!=null) {
            SyndContent sContent = new SyndContentImpl();
            sContent.setType(content.getType());
            sContent.setValue(content.getValue());
            syndEntry.setDescription(sContent);
        }

        List contents = entry.getContents();
        if (contents.size()>0) {
            List sContents = new ArrayList();
            for (int i=0;i<contents.size();i++) {
                content = (Content) contents.get(i);
                SyndContent sContent = new SyndContentImpl();
                sContent.setType(content.getType());
                sContent.setValue(content.getValue());
                sContent.setMode(content.getMode());
                sContents.add(sContent);
            }
            syndEntry.setContents(sContents);
        }

        List authors = entry.getAuthors();
        if (authors!=null && authors.size() > 0) {
            syndEntry.setAuthors(createSyndPersons(authors));
            SyndPerson person0 = (SyndPerson)syndEntry.getAuthors().get(0);
            syndEntry.setAuthor(person0.getName());
        }

        Date date = entry.getModified();
        if (date==null) {
            date = entry.getIssued();
            if (date==null) {
                date = entry.getCreated();
            }
        }
        if (date!=null) {
            syndEntry.setPublishedDate(date);
        }

        return syndEntry;
    }

    public SyndEnclosure createSyndEnclosure(Entry entry, Link link) {
        SyndEnclosure syndEncl = new SyndEnclosureImpl();
        syndEncl.setUrl(link.getHrefResolved());
        syndEncl.setType(link.getType());
        syndEncl.setLength(link.getLength());
        return syndEncl;
    }

    public WireFeed createRealFeed(SyndFeed syndFeed) {
        Feed aFeed = new Feed(getType());
        aFeed.setModules(ModuleUtils.cloneModules(syndFeed.getModules()));

        aFeed.setEncoding(syndFeed.getEncoding());

        aFeed.setId(syndFeed.getUri());

        SyndContent sTitle = syndFeed.getTitleEx();
        if (sTitle != null) {
            Content title = new Content();
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
        List alternateLinks = new ArrayList();
        List otherLinks = new ArrayList();
        List slinks = syndFeed.getLinks();
        if (slinks != null) {
            for (Iterator iter=slinks.iterator(); iter.hasNext();) {
                SyndLink syndLink = (SyndLink)iter.next();
                Link link = createAtomLink(syndLink);
                if (link.getRel() == null ||
                        "".equals(link.getRel().trim()) ||
                        "alternate".equals(link.getRel())) {
                    alternateLinks.add(link);
                } else {
                    otherLinks.add(link);
                }
            }
        }
        // no alternate link? then use THE link if there is one
        if (alternateLinks.isEmpty() && syndFeed.getLink() != null) {
            Link link = new Link();
            link.setRel("alternate");
            link.setHref(syndFeed.getLink());
            alternateLinks.add(link);
        }

        if (alternateLinks.size() > 0) aFeed.setAlternateLinks(alternateLinks);
        if (otherLinks.size() > 0) aFeed.setOtherLinks(otherLinks);

        String sDesc = syndFeed.getDescription();
        if (sDesc!=null) {
            Content tagline = new Content();
            tagline.setValue(sDesc);
            aFeed.setTagline(tagline);
        }

        aFeed.setLanguage(syndFeed.getLanguage());

        List authors = syndFeed.getAuthors();
        if (authors!=null && authors.size() > 0) {
            aFeed.setAuthors(createAtomPersons(authors));
        }

        aFeed.setCopyright(syndFeed.getCopyright());

        aFeed.setModified(syndFeed.getPublishedDate());

        List sEntries = syndFeed.getEntries();
        if (sEntries!=null) {
            aFeed.setEntries(createAtomEntries(sEntries));
        }

        return aFeed;
    }

    protected static List createAtomPersons(List sPersons) {
        List persons = new ArrayList();
        for (Iterator iter = sPersons.iterator(); iter.hasNext(); ) {
            SyndPerson sPerson = (SyndPerson)iter.next();
            Person person = new Person();
            person.setName(sPerson.getName());
            person.setUri(sPerson.getUri());
            person.setEmail(sPerson.getEmail());
            person.setModules(sPerson.getModules());
            persons.add(person);
        }
        return persons;
    }
    
    protected static List createSyndPersons(List aPersons) {
        List persons = new ArrayList();
        for (Iterator iter = aPersons.iterator(); iter.hasNext(); ) {
            Person aPerson = (Person)iter.next();
            SyndPerson person = new SyndPersonImpl();
            person.setName(aPerson.getName());
            person.setUri(aPerson.getUri());
            person.setEmail(aPerson.getEmail());
            person.setModules(aPerson.getModules());
            persons.add(person);
        }
        return persons;
    }
    
    protected List createAtomEntries(List syndEntries) {
        List atomEntries = new ArrayList();
        for (int i=0;i<syndEntries.size();i++) {
            atomEntries.add(createAtomEntry((SyndEntry)syndEntries.get(i)));
        }
        return atomEntries;
    }

    protected Entry createAtomEntry(SyndEntry sEntry) {
        Entry aEntry = new Entry();
        aEntry.setModules(ModuleUtils.cloneModules(sEntry.getModules()));

        aEntry.setId(sEntry.getUri());

        SyndContent sTitle = sEntry.getTitleEx();
        if (sTitle!=null) {
            Content title = new Content();
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
        List alternateLinks = new ArrayList();
        List otherLinks = new ArrayList();
        List slinks = sEntry.getLinks();
        if (slinks != null) {
            for (Iterator iter=slinks.iterator(); iter.hasNext();) {
                SyndLink syndLink = (SyndLink)iter.next();
                Link link = createAtomLink(syndLink);
                if (link.getRel() == null ||
                        "".equals(link.getRel().trim()) ||
                        "alternate".equals(link.getRel())) {
                    alternateLinks.add(link);
                } else {
                    otherLinks.add(link);
                }
            }
        }
        // no alternate link? then use THE link if there is one
        if (alternateLinks.isEmpty() && sEntry.getLink() != null) {
            Link link = new Link();
            link.setRel("alternate");
            link.setHref(sEntry.getLink());
            alternateLinks.add(link);
        }

        List sEnclosures = sEntry.getEnclosures();
        if (sEnclosures != null) {
            for (Iterator iter=sEnclosures.iterator(); iter.hasNext();) {
                SyndEnclosure syndEnclosure = (SyndEnclosure) iter.next();
                Link link = createAtomEnclosure(syndEnclosure);
                otherLinks.add(link);
            }
        }

        if (alternateLinks.size() > 0) aEntry.setAlternateLinks(alternateLinks);
        if (otherLinks.size() > 0) aEntry.setOtherLinks(otherLinks);


        SyndContent sContent = sEntry.getDescription();
        if (sContent!=null) {
            Content content = new Content();
            content.setType(sContent.getType());
            content.setValue(sContent.getValue());
            content.setMode(Content.ESCAPED);
            aEntry.setSummary(content);
        }

        List contents = sEntry.getContents();
        if (contents.size()>0) {
            List aContents = new ArrayList();
            for (int i=0;i<contents.size();i++) {
                sContent = (SyndContentImpl) contents.get(i);
                Content content = new Content();
                content.setType(sContent.getType());
                content.setValue(sContent.getValue());
                content.setMode(sContent.getMode());
                aContents.add(content);

            }
            aEntry.setContents(aContents);
        }

        List sAuthors = sEntry.getAuthors();
        if (sAuthors!=null && sAuthors.size() > 0) {
            aEntry.setAuthors(createAtomPersons(sAuthors));
        } else if (sEntry.getAuthor() != null) {
            Person person = new Person();
            person.setName(sEntry.getAuthor()); 
            List authors = new ArrayList();
            authors.add(person);
            aEntry.setAuthors(authors);
        }

        aEntry.setModified(sEntry.getPublishedDate());
        aEntry.setIssued(sEntry.getPublishedDate());

        return aEntry;
    }

    public Link createAtomLink(SyndLink syndLink) {
        Link link = new Link();
        link.setRel(     syndLink.getRel());
        link.setType(    syndLink.getType());
        link.setHref(    syndLink.getHref());
        link.setTitle(   syndLink.getTitle());
        return link;
    }

    public Link createAtomEnclosure(SyndEnclosure syndEnclosure) {
      Link link = new Link();
      link.setRel(     "enclosure");
      link.setType(    syndEnclosure.getType());
      link.setHref(    syndEnclosure.getUrl());
      link.setLength(  syndEnclosure.getLength());
      return link;
    }

}
