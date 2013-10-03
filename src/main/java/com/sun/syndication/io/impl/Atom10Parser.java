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
import com.sun.syndication.feed.atom.Category;
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Generator;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.atom.Person;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedInput;
import com.sun.syndication.io.WireFeedOutput;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.util.regex.Pattern;
import org.jdom2.Attribute;
import org.jdom2.JDOMException;
import org.jdom2.Parent;
import org.jdom2.input.SAXBuilder;

/**
 * Parser for Atom 1.0
 * @author Dave Johnson
 */
public class Atom10Parser extends BaseWireFeedParser {
    private static final String ATOM_10_URI = "http://www.w3.org/2005/Atom";
    private static final Namespace ATOM_10_NS = Namespace.getNamespace(ATOM_10_URI);

    private static boolean resolveURIs = false;

    public static void setResolveURIs(boolean resolveURIs) {
        Atom10Parser.resolveURIs = resolveURIs;
    }

    public static boolean getResolveURIs() {
        return resolveURIs;
    }

    public Atom10Parser() {
        this("atom_1.0");
    }
    
    protected Atom10Parser(String type) {
        super(type, ATOM_10_NS);
    }
    
    protected Namespace getAtomNamespace() {
        return ATOM_10_NS;
    }
    
    public boolean isMyType(Document document) {
        Element rssRoot = document.getRootElement();
        Namespace defaultNS = rssRoot.getNamespace();
        return (defaultNS!=null) && defaultNS.equals(getAtomNamespace());
    }
    
    public WireFeed parse(Document document, boolean validate)
    throws IllegalArgumentException,FeedException {
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
    
    protected WireFeed parseFeed(Element eFeed) throws FeedException {
        
        String baseURI = null;
        try {
            baseURI = findBaseURI(eFeed);
        } catch (Exception e) {
            throw new FeedException("ERROR while finding base URI of feed", e);
        }
        
        Feed feed = parseFeedMetadata(baseURI, eFeed);

        String xmlBase = eFeed.getAttributeValue("base", Namespace.XML_NAMESPACE);
        if (xmlBase != null) {
            feed.setXmlBase(xmlBase);
        }
        
        feed.setModules(parseFeedModules(eFeed));

        List eList = eFeed.getChildren("entry",getAtomNamespace());
        if (eList.size()>0) {
            feed.setEntries(parseEntries(feed, baseURI, eList));
        }

        List foreignMarkup =
            extractForeignMarkup(eFeed, feed, getAtomNamespace());
        if (foreignMarkup.size() > 0) {
            feed.setForeignMarkup(foreignMarkup);
        }
        return feed;
    }

    private Feed parseFeedMetadata(String baseURI, Element eFeed) {
        com.sun.syndication.feed.atom.Feed feed =
            new com.sun.syndication.feed.atom.Feed(getType());

        Element e = eFeed.getChild("title",getAtomNamespace());
        if (e!=null) {
            Content c = new Content();
            c.setValue(parseTextConstructToString(e));
            c.setType(getAttributeValue(e, "type"));
            feed.setTitleEx(c);
        }
        
        List eList = eFeed.getChildren("link",getAtomNamespace());
        feed.setAlternateLinks(parseAlternateLinks(feed, null, baseURI, eList));
        feed.setOtherLinks(parseOtherLinks(feed, null, baseURI, eList));
        
        List cList = eFeed.getChildren("category",getAtomNamespace());
        feed.setCategories(parseCategories(baseURI, cList));
        
        eList = eFeed.getChildren("author", getAtomNamespace());
        if (eList.size()>0) {
            feed.setAuthors(parsePersons(baseURI, eList));
        }
        
        eList = eFeed.getChildren("contributor",getAtomNamespace());
        if (eList.size()>0) {
            feed.setContributors(parsePersons(baseURI, eList));
        }
        
        e = eFeed.getChild("subtitle",getAtomNamespace());
        if (e!=null) {
            Content subtitle = new Content();
            subtitle.setValue(parseTextConstructToString(e));
            subtitle.setType(getAttributeValue(e, "type"));
            feed.setSubtitle(subtitle);
        }
        
        e = eFeed.getChild("id",getAtomNamespace());
        if (e!=null) {
            feed.setId(e.getText());
        }
        
        e = eFeed.getChild("generator",getAtomNamespace());
        if (e!=null) {
            Generator gen = new Generator();
            gen.setValue(e.getText());
            String att = getAttributeValue(e, "uri");
            if (att!=null) {
                gen.setUrl(att);
            }
            att = getAttributeValue(e, "version");
            if (att!=null) {
                gen.setVersion(att);
            }
            feed.setGenerator(gen);
        }
        
        e = eFeed.getChild("rights",getAtomNamespace());
        if (e!=null) {
            feed.setRights(parseTextConstructToString(e));
        }
        
        e = eFeed.getChild("icon",getAtomNamespace());
        if (e!=null) {
            feed.setIcon(e.getText());
        }
        
        e = eFeed.getChild("logo",getAtomNamespace());
        if (e!=null) {
            feed.setLogo(e.getText());
        }
        
        e = eFeed.getChild("updated",getAtomNamespace());
        if (e!=null) {
            feed.setUpdated(DateParser.parseDate(e.getText()));
        }
        
        return feed;
    }

    private Link parseLink(Feed feed , Entry entry, String baseURI, Element eLink) {
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
            if (isRelativeURI(att)) {
                link.setHrefResolved(resolveURI(baseURI, eLink, att));
            } 
        }
        att = getAttributeValue(eLink, "title");
        if (att!=null) {
            link.setTitle(att);
        }
        att = getAttributeValue(eLink, "hreflang");
        if (att!=null) {
            link.setHreflang(att);
        }
        att = getAttributeValue(eLink, "length");
        if (att!=null) {
        	Long val = NumberParser.parseLong(att);
        	if (val != null) {
        		link.setLength(val.longValue());
        	}            
        }
        return link;
    }
    
    // List(Elements) -> List(Link)
    private List parseAlternateLinks(Feed feed, Entry entry, String baseURI, List eLinks) {
        List links = new ArrayList();
        for (int i=0;i<eLinks.size();i++) {
            Element eLink = (Element) eLinks.get(i);
            Link link = parseLink(feed, entry, baseURI, eLink);
            if (link.getRel() == null
                    || "".equals(link.getRel().trim())
                    || "alternate".equals(link.getRel())) {
                links.add(link);
            }
        }
        return (links.size()>0) ? links : null;
    }
    
    private List parseOtherLinks(Feed feed, Entry entry, String baseURI, List eLinks) {
        List links = new ArrayList();
        for (int i=0;i<eLinks.size();i++) {
            Element eLink = (Element) eLinks.get(i);
            Link link = parseLink(feed, entry, baseURI, eLink);
            if (!"alternate".equals(link.getRel())) {
                links.add(link);
            }
        }
        return (links.size()>0) ? links : null;
    }
    
    private Person parsePerson(String baseURI, Element ePerson) {
        Person person = new Person();
        Element e = ePerson.getChild("name",getAtomNamespace());
        if (e!=null) {
            person.setName(e.getText());
        }
        e = ePerson.getChild("uri",getAtomNamespace());
        if (e!=null) {
            person.setUri(e.getText());
            if (isRelativeURI(e.getText())) {
               person.setUriResolved(resolveURI(baseURI, ePerson, e.getText())); 
            }
        }
        e = ePerson.getChild("email",getAtomNamespace());
        if (e!=null) {
            person.setEmail(e.getText());
        }
        person.setModules(parsePersonModules(ePerson));        
        return person;
    }
    
    // List(Elements) -> List(Persons)
    private List parsePersons(String baseURI, List ePersons) {
        List persons = new ArrayList();
        for (int i=0;i<ePersons.size();i++) {
            persons.add(parsePerson(baseURI, (Element)ePersons.get(i)));
        }
        return (persons.size()>0) ? persons : null;
    }
    
    private Content parseContent(Element e) {
        String value = parseTextConstructToString(e);
        String src = getAttributeValue(e, "src");
        String type = getAttributeValue(e, "type");
        Content content = new Content();
        content.setSrc(src);
        content.setType(type);
        content.setValue(value);
        return content;
    }
    
    private String parseTextConstructToString(Element e) {
        String value = null;
        String type = getAttributeValue(e, "type");
        type = (type!=null) ? type : Content.TEXT;
        if (type.equals(Content.XHTML) || (type.indexOf("/xml")) != -1 || (type.indexOf("+xml")) != -1) {
            // XHTML content needs special handling
            XMLOutputter outputter = new XMLOutputter();
            List eContent = e.getContent();
            Iterator i = eContent.iterator();
            while (i.hasNext()) {
                org.jdom2.Content c = (org.jdom2.Content) i.next();
                if (c instanceof Element) {
                    Element eC = (Element) c;
                    if (eC.getNamespace().equals(getAtomNamespace())) {
                        ((Element)c).setNamespace(Namespace.NO_NAMESPACE);
                    }
                }
            }
            value = outputter.outputString(eContent);
        } else {
            // Everything else comes in verbatim
            value = e.getText();
        }
        return value;
    }
    
    // List(Elements) -> List(Entries)
    protected List parseEntries(Feed feed, String baseURI, List eEntries) {
        List entries = new ArrayList();
        for (int i=0;i<eEntries.size();i++) {
            entries.add(parseEntry(feed, (Element)eEntries.get(i), baseURI));
        }
        return (entries.size()>0) ? entries : null;
    }
    
    protected Entry parseEntry(Feed feed, Element eEntry, String baseURI) {
        Entry entry = new Entry();
        
        String xmlBase = eEntry.getAttributeValue("base", Namespace.XML_NAMESPACE);
        if (xmlBase != null) {
            entry.setXmlBase(xmlBase);
        }
        
        Element e = eEntry.getChild("title",getAtomNamespace());
        if (e!=null) {
            Content c = new Content();
            c.setValue(parseTextConstructToString(e));
            c.setType(getAttributeValue(e, "type"));
            entry.setTitleEx(c);
        }
        
        List eList = eEntry.getChildren("link",getAtomNamespace());
        entry.setAlternateLinks(parseAlternateLinks(feed, entry, baseURI, eList));
        entry.setOtherLinks(parseOtherLinks(feed, entry, baseURI, eList));
        
        eList = eEntry.getChildren("author", getAtomNamespace());
        if (eList.size()>0) {
            entry.setAuthors(parsePersons(baseURI, eList));
        }
        
        eList = eEntry.getChildren("contributor",getAtomNamespace());
        if (eList.size()>0) {
            entry.setContributors(parsePersons(baseURI, eList));
        }
        
        e = eEntry.getChild("id",getAtomNamespace());
        if (e!=null) {
            entry.setId(e.getText());
        }
        
        e = eEntry.getChild("updated",getAtomNamespace());
        if (e!=null) {
            entry.setUpdated(DateParser.parseDate(e.getText()));
        }
        
        e = eEntry.getChild("published",getAtomNamespace());
        if (e!=null) {
            entry.setPublished(DateParser.parseDate(e.getText()));
        }
        
        e = eEntry.getChild("summary",getAtomNamespace());
        if (e!=null) {
            entry.setSummary(parseContent(e));
        }
        
        e = eEntry.getChild("content",getAtomNamespace());
        if (e!=null) {
            List contents = new ArrayList();
            contents.add(parseContent(e));
            entry.setContents(contents);
        }
        
        e = eEntry.getChild("rights",getAtomNamespace());
        if (e!=null) {
            entry.setRights(e.getText());
        }
        
        List cList = eEntry.getChildren("category",getAtomNamespace());
        entry.setCategories(parseCategories(baseURI, cList));
        
        // TODO: SHOULD handle Atom entry source element
        e = eEntry.getChild("source", getAtomNamespace());
        if (e!=null) {
            entry.setSource(parseFeedMetadata(baseURI, e));
        }
        
        entry.setModules(parseItemModules(eEntry));
        
        List foreignMarkup =
                extractForeignMarkup(eEntry, entry, getAtomNamespace());
        if (foreignMarkup.size() > 0) {
            entry.setForeignMarkup(foreignMarkup);
        }
        return entry;
    }
    
    private List parseCategories(String baseURI, List eCategories) {
        List cats = new ArrayList();
        for (int i=0;i<eCategories.size();i++) {
            Element eCategory = (Element) eCategories.get(i);
            cats.add(parseCategory(baseURI, eCategory));
        }
        return (cats.size()>0) ? cats : null;
    }
    
    private Category parseCategory(String baseURI, Element eCategory) {
        Category category = new Category();
        String att = getAttributeValue(eCategory, "term");
        if (att!=null) {
            category.setTerm(att);
        }
        att = getAttributeValue(eCategory, "scheme");
        if (att!=null) {
            category.setScheme(att);
            if (isRelativeURI(att)) {
                category.setSchemeResolved(resolveURI(baseURI, eCategory, att));
            }
        }
        att = getAttributeValue(eCategory, "label");
        if (att!=null) {
            category.setLabel(att);
        }
        return category;
        
    }
    
    // Once following relative URI methods are made public in the ROME 
    // Atom10Parser, then use them instead and delete these.
    
    
    // Fix for issue #34 "valid IRI href attributes are stripped for atom:link"
    // URI's that didn't start with http were being treated as relative URIs.
    // So now consider an absolute URI to be any alpha-numeric string followed
    // by a colon, followed by anything -- specified by this regex:
    static Pattern absoluteURIPattern = Pattern.compile("^[a-z0-9]*:.*$");
    
    public static boolean isAbsoluteURI(String uri) {
        return absoluteURIPattern.matcher(uri).find();
    }
    
    /** Returns true if URI is relative. */
    public static boolean isRelativeURI(String uri) {
        return !isAbsoluteURI(uri);
    }
        
    /**
     * Resolve URI via base URL and parent element.
     * Resolve URI based considering xml:base and baseURI.
     * @param baseURI Base URI used to fetch the XML document
     * @param parent  Parent element from which to consider xml:base
     * @param url     URL to be resolved
     */
    public static String resolveURI(String baseURI, Parent parent, String url) {
        if (!resolveURIs) {
            return url;
        }
        if (isRelativeURI(url)) {
            url = (!".".equals(url) && !"./".equals(url)) ? url : "";
            
            if (url.startsWith("/") && baseURI != null) {
                String base = null;
                int slashslash = baseURI.indexOf("//");
                int nextslash = baseURI.indexOf("/", slashslash + 2);
                if (nextslash != -1) base = baseURI.substring(0, nextslash);
                return formURI(base, url);               
            } 

            // Relative URI with parent
            if (parent != null && parent instanceof Element) {

                // Do we have an xml:base?         
                String xmlbase = ((Element)parent).getAttributeValue(
                    "base", Namespace.XML_NAMESPACE);
                if (xmlbase != null && xmlbase.trim().length() > 0) {
                    if (isAbsoluteURI(xmlbase)) {
                        // Absolute xml:base, so form URI right now 
                        if (url.startsWith("/")) { 
                            // Host relative URI
                            int slashslash = xmlbase.indexOf("//");
                            int nextslash = xmlbase.indexOf("/", slashslash + 2);
                            if (nextslash != -1) xmlbase = xmlbase.substring(0, nextslash);
                            return formURI(xmlbase, url); 
                        }
                        if (!xmlbase.endsWith("/")) {
                            // Base URI is filename, strip it off 
                            xmlbase = xmlbase.substring(0, xmlbase.lastIndexOf("/"));
                        }
                        return formURI(xmlbase, url);
                    } else {
                        // Relative xml:base, so walk up tree
                        return resolveURI(baseURI, parent.getParent(), 
                            stripTrailingSlash(xmlbase) + "/"+ stripStartingSlash(url));
                    }
                }
                // No xml:base so walk up tree
                return resolveURI(baseURI, parent.getParent(), url);

            // Relative URI with no parent (i.e. top of tree), so form URI right now
            } else if (parent == null || parent instanceof Document) {
                return formURI(baseURI, url);        
            } 
        }                
        return url;
    }
        
    /**
     * Find base URI of feed considering relative URIs.
     * @param root Root element of feed.
     */
    private String findBaseURI(Element root) throws MalformedURLException {
        String ret = null;
        if (findAtomLink(root, "self") != null) {
            ret = findAtomLink(root, "self");
            if (".".equals(ret) || "./".equals(ret)) ret = "";
            if (ret.indexOf("/") != -1) ret = ret.substring(0, ret.lastIndexOf("/"));
            ret = resolveURI(null, root, ret);
        }
        return ret;
    }
    
    /** 
     * Return URL string of Atom link element under parent element.
     * Link with no rel attribute is considered to be rel="alternate"
     * @param parent Consider only children of this parent element
     * @param rel    Consider only links with this relationship
     */
    private  String findAtomLink(Element parent, String rel) {
        String ret = null;
        List linksList = parent.getChildren("link", ATOM_10_NS);
        if (linksList != null) {
            for (Iterator links = linksList.iterator(); links.hasNext(); ) {
                Element link = (Element)links.next();
                Attribute relAtt = getAttribute(link, "rel");
                Attribute hrefAtt = getAttribute(link, "href");
                if (   (relAtt == null && "alternate".equals(rel)) 
                    || (relAtt != null && relAtt.getValue().equals(rel))) {
                    ret = hrefAtt.getValue();
                    break;
                }
            }
        }
        return ret;
    }
        
    /** 
     * Form URI by combining base with append portion and giving 
     * special consideration to append portions that begin with ".."
     * @param base   Base of URI, may end with trailing slash
     * @param append String to append, may begin with slash or ".."
     */
    private static String formURI(String base, String append) {
        base = stripTrailingSlash(base);
        append = stripStartingSlash(append);
        if (append.startsWith("..")) {
            String ret = null;
            String[] parts = append.split("/");
            for (int i=0; i<parts.length; i++) {
                if ("..".equals(parts[i])) {
                    int last = base.lastIndexOf("/");
                    if (last != -1) {
                        base = base.substring(0, last);
                        append = append.substring(3, append.length());
                    }
                    else break;
                }
            }
        }
        return base + "/" + append;
    }
    
    /** 
     * Strip starting slash from beginning of string.
     */
    private static String stripStartingSlash(String s) {
        if (s != null && s.startsWith("/")) {
            s = s.substring(1, s.length());
        }
        return s;
    }
    
    /** 
     * Strip trailing slash from end of string.
     */
    private static String stripTrailingSlash(String s) {
        if (s != null && s.endsWith("/")) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }    

    
    /**
     * Parse entry from reader.
     */
    public static Entry parseEntry(Reader rd, String baseURI)
        throws JDOMException, IOException, IllegalArgumentException, FeedException {
        // Parse entry into JDOM tree
        SAXBuilder builder = new SAXBuilder();
        Document entryDoc = builder.build(rd);
        Element fetchedEntryElement = entryDoc.getRootElement();
        fetchedEntryElement.detach();

        // Put entry into a JDOM document with 'feed' root so that Rome can handle it
        Feed feed = new Feed();
        feed.setFeedType("atom_1.0");
        WireFeedOutput wireFeedOutput = new WireFeedOutput();
        Document feedDoc = wireFeedOutput.outputJDom(feed);
        feedDoc.getRootElement().addContent(fetchedEntryElement);
        
        if (baseURI != null) {
            feedDoc.getRootElement().setAttribute("base", baseURI, Namespace.XML_NAMESPACE);
        }
        
        WireFeedInput input = new WireFeedInput();
        Feed parsedFeed = (Feed)input.build(feedDoc);
        return (Entry)parsedFeed.getEntries().get(0);
    } 
}


