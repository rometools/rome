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

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.atom.*;
import com.sun.syndication.io.FeedException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;

import java.util.*;

/**
 */
public class Atom03Parser extends BaseWireFeedParser {
    private static final String ATOM_03_URI = "http://purl.org/atom/ns#";
    private static final Namespace ATOM_03_NS = Namespace.getNamespace(ATOM_03_URI);

    public Atom03Parser() {
        this("atom_0.3", ATOM_03_NS);
    }

    protected Atom03Parser(String type, Namespace ns) {
        super(type, ns);
    }

    protected Namespace getAtomNamespace() {
        return ATOM_03_NS;
    }

    public boolean isMyType(Document document) {
        Element rssRoot = document.getRootElement();
        Namespace defaultNS = rssRoot.getNamespace();
        return (defaultNS!=null) && defaultNS.equals(getAtomNamespace());
    }

    public WireFeed parse(Document document, boolean validate) throws IllegalArgumentException,FeedException {
        if (validate) {
            validateFeed(document);
        }
        Element rssRoot = document.getRootElement();
        return parseFeed(rssRoot);
    }

    protected void validateFeed(Document document) throws FeedException {
        // TBD
        // here we have to validate the Feed against a schema or whatever
        // not sure how to do it
        // one posibility would be to produce an ouput and attempt to parse it again
        // with validation turned on.
        // otherwise will have to check the document elements by hand.
    }

    protected WireFeed parseFeed(Element eFeed) {

        com.sun.syndication.feed.atom.Feed feed = new com.sun.syndication.feed.atom.Feed(getType());

        Element e = eFeed.getChild("title",getAtomNamespace());
        if (e!=null) {
            feed.setTitleEx(parseContent(e));
        }

        List eList = eFeed.getChildren("link",getAtomNamespace());
        feed.setAlternateLinks(parseAlternateLinks(eList));
        feed.setOtherLinks(parseOtherLinks(eList));

        e = eFeed.getChild("author",getAtomNamespace());
        if (e!=null) {
            List authors = new ArrayList();
            authors.add(parsePerson(e));
            feed.setAuthors(authors);
        }

        eList = eFeed.getChildren("contributor",getAtomNamespace());
        if (eList.size()>0) {
            feed.setContributors(parsePersons(eList));
        }

        e = eFeed.getChild("tagline",getAtomNamespace());
        if (e!=null) {
            feed.setTagline(parseContent(e));
        }

        e = eFeed.getChild("id",getAtomNamespace());
        if (e!=null) {
            feed.setId(e.getText());
        }

        e = eFeed.getChild("generator",getAtomNamespace());
        if (e!=null) {
            Generator gen = new Generator();
            gen.setValue(e.getText());
            String att = getAttributeValue(e, "url");
            if (att!=null) {
                gen.setUrl(att);
            }
            att = getAttributeValue(e, "version");
            if (att!=null) {
                gen.setVersion(att);
            }
            feed.setGenerator(gen);
        }

        e = eFeed.getChild("copyright",getAtomNamespace());
        if (e!=null) {
            feed.setCopyright(e.getText());
        }

        e = eFeed.getChild("info",getAtomNamespace());
        if (e!=null) {
            feed.setInfo(parseContent(e));
        }

        e = eFeed.getChild("modified",getAtomNamespace());
        if (e!=null) {
            feed.setModified(DateParser.parseDate(e.getText()));
        }

        feed.setModules(parseFeedModules(eFeed));
        
        eList = eFeed.getChildren("entry",getAtomNamespace());
        if (eList.size()>0) {
            feed.setEntries(parseEntries(eList));
        }

        List foreignMarkup = 
            extractForeignMarkup(eFeed, feed, getAtomNamespace());
        if (foreignMarkup.size() > 0) {
            feed.setForeignMarkup(foreignMarkup);
        } 
        return feed;
    }

    private Link parseLink(Element eLink) {
        Link link = new Link();
        String att = getAttributeValue(eLink, "rel");
        if (att!=null) {
            link.setRel(att);
        }
        att = getAttributeValue(eLink, "type");
        if (att!=null) {
            link.setType(att);
        }
        att = getAttributeValue(eLink, "href");
        if (att!=null) {
            link.setHref(att);
        }
        return link;
    }

    // List(Elements) -> List(Link)
    private List parseLinks(List eLinks,boolean alternate) {
        List links = new ArrayList();
        for (int i=0;i<eLinks.size();i++) {
            Element eLink = (Element) eLinks.get(i);
            String rel = getAttributeValue(eLink, "rel");
            if (alternate) {
                if ("alternate".equals(rel)) {
                    links.add(parseLink(eLink));
                }
            }
            else {
                if (!("alternate".equals(rel))) {
                    links.add(parseLink(eLink));
                }
            }
        }
        return (links.size()>0) ? links : null;
    }

    // List(Elements) -> List(Link)
    private List parseAlternateLinks(List eLinks) {
        return parseLinks(eLinks,true);
    }

    // List(Elements) -> List(Link)
    private List parseOtherLinks(List eLinks) {
        return parseLinks(eLinks,false);
    }

    private Person parsePerson(Element ePerson) {
        Person person = new Person();
        Element e = ePerson.getChild("name",getAtomNamespace());
        if (e!=null) {
            person.setName(e.getText());
        }
        e = ePerson.getChild("url",getAtomNamespace());
        if (e!=null) {
            person.setUrl(e.getText());
        }
        e = ePerson.getChild("email",getAtomNamespace());
        if (e!=null) {
            person.setEmail(e.getText());
        }
        return person;
    }

    // List(Elements) -> List(Persons)
    private List parsePersons(List ePersons) {
        List persons = new ArrayList();
        for (int i=0;i<ePersons.size();i++) {
            persons.add(parsePerson((Element)ePersons.get(i)));
        }
        return (persons.size()>0) ? persons : null;
    }

    private Content parseContent(Element e) {
        String value = null;
        String type = getAttributeValue(e, "type");
        type = (type!=null) ? type : "text/plain";
        String mode = getAttributeValue(e, "mode");
        if (mode == null) {
            mode = Content.XML; // default to xml content
        }
        if (mode.equals(Content.ESCAPED)) {
            // do nothing XML Parser took care of this
            value = e.getText();
        }
        else
        if (mode.equals(Content.BASE64)) {
                value = Base64.decode(e.getText());
        }
        else
        if (mode.equals(Content.XML)) {
            XMLOutputter outputter = new XMLOutputter();
            List eContent = e.getContent();
            Iterator i = eContent.iterator();
            while (i.hasNext()) {
                org.jdom.Content c = (org.jdom.Content) i.next();
                if (c instanceof Element) {
                    Element eC = (Element) c;
                    if (eC.getNamespace().equals(getAtomNamespace())) {
                        ((Element)c).setNamespace(Namespace.NO_NAMESPACE);
                    }
                }
            }
            value = outputter.outputString(eContent);
        }

        Content content = new Content();
        content.setType(type);
        content.setMode(mode);
        content.setValue(value);
        return content;
    }

    // List(Elements) -> List(Entries)
    private List parseEntries(List eEntries) {
        List entries = new ArrayList();
        for (int i=0;i<eEntries.size();i++) {
            entries.add(parseEntry((Element)eEntries.get(i)));
        }
        return (entries.size()>0) ? entries : null;
    }

    private Entry parseEntry(Element eEntry) {
        Entry entry = new Entry();

        Element e = eEntry.getChild("title",getAtomNamespace());
        if (e!=null) {
            entry.setTitleEx(parseContent(e));
        }

        List eList = eEntry.getChildren("link",getAtomNamespace());
        entry.setAlternateLinks(parseAlternateLinks(eList));
        entry.setOtherLinks(parseOtherLinks(eList));

        e = eEntry.getChild("author",getAtomNamespace());
        if (e!=null) {
            List authors = new ArrayList();
            authors.add(parsePerson(e));
            entry.setAuthors(authors);
        }

        eList = eEntry.getChildren("contributor",getAtomNamespace());
        if (eList.size()>0) {
            entry.setContributors(parsePersons(eList));
        }

        e = eEntry.getChild("id",getAtomNamespace());
        if (e!=null) {
            entry.setId(e.getText());
        }

        e = eEntry.getChild("modified",getAtomNamespace());
        if (e!=null) {
            entry.setModified(DateParser.parseDate(e.getText()));
        }

        e = eEntry.getChild("issued",getAtomNamespace());
        if (e!=null) {
            entry.setIssued(DateParser.parseDate(e.getText()));
        }

        e = eEntry.getChild("created",getAtomNamespace());
        if (e!=null) {
            entry.setCreated(DateParser.parseDate(e.getText()));
        }

        e = eEntry.getChild("summary",getAtomNamespace());
        if (e!=null) {
            entry.setSummary(parseContent(e));
        }

        eList = eEntry.getChildren("content",getAtomNamespace());
        if (eList.size()>0) {
            List content = new ArrayList();
            for (int i=0;i<eList.size();i++) {
                content.add(parseContent((Element)eList.get(i)));
            }
            entry.setContents(content);
        }

        entry.setModules(parseItemModules(eEntry));

        List foreignMarkup = 
            extractForeignMarkup(eEntry, entry, getAtomNamespace());
        if (foreignMarkup.size() > 0) {
            entry.setForeignMarkup(foreignMarkup);
        } 
        return entry;
    }


}
