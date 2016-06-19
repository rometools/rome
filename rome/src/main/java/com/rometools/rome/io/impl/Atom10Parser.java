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

import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.Parent;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.atom.Category;
import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.atom.Generator;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.atom.Person;
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.WireFeedInput;
import com.rometools.rome.io.WireFeedOutput;
import com.rometools.utils.Lists;

/**
 * Parser for Atom 1.0
 */
public class Atom10Parser extends BaseWireFeedParser {

    private static final String ATOM_10_URI = "http://www.w3.org/2005/Atom";
    private static final Namespace ATOM_10_NS = Namespace.getNamespace(ATOM_10_URI);

    private static boolean resolveURIs = false;

    public static void setResolveURIs(final boolean resolveURIs) {
        Atom10Parser.resolveURIs = resolveURIs;
    }

    public static boolean getResolveURIs() {
        return resolveURIs;
    }

    public Atom10Parser() {
        this("atom_1.0");
    }

    protected Atom10Parser(final String type) {
        super(type, ATOM_10_NS);
    }

    protected Namespace getAtomNamespace() {
        return ATOM_10_NS;
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
        // TBD here we have to validate the Feed against a schema or whatever not sure how to do it
        // one posibility would be to produce an ouput and attempt to parse it again with validation
        // turned on. otherwise will have to check the document elements by hand.
    }

    protected WireFeed parseFeed(final Element eFeed, final Locale locale) throws FeedException {

        String baseURI = null;
        try {
            baseURI = findBaseURI(eFeed);
        } catch (final Exception e) {
            throw new FeedException("ERROR while finding base URI of feed", e);
        }

        final Feed feed = parseFeedMetadata(baseURI, eFeed, locale);
        feed.setStyleSheet(getStyleSheet(eFeed.getDocument()));

        final String xmlBase = eFeed.getAttributeValue("base", Namespace.XML_NAMESPACE);
        if (xmlBase != null) {
            feed.setXmlBase(xmlBase);
        }

        feed.setModules(parseFeedModules(eFeed, locale));

        final List<Element> eList = eFeed.getChildren("entry", getAtomNamespace());
        if (!eList.isEmpty()) {
            feed.setEntries(parseEntries(feed, baseURI, eList, locale));
        }

        final List<Element> foreignMarkup = extractForeignMarkup(eFeed, feed, getAtomNamespace());
        if (!foreignMarkup.isEmpty()) {
            feed.setForeignMarkup(foreignMarkup);
        }
        return feed;
    }

    private Feed parseFeedMetadata(final String baseURI, final Element eFeed, final Locale locale) {

        final com.rometools.rome.feed.atom.Feed feed = new com.rometools.rome.feed.atom.Feed(getType());

        final Element title = eFeed.getChild("title", getAtomNamespace());
        if (title != null) {
            final Content c = new Content();
            c.setValue(parseTextConstructToString(title));
            c.setType(getAttributeValue(title, "type"));
            feed.setTitleEx(c);
        }

        final List<Element> links = eFeed.getChildren("link", getAtomNamespace());
        feed.setAlternateLinks(parseAlternateLinks(feed, null, baseURI, links));
        feed.setOtherLinks(parseOtherLinks(feed, null, baseURI, links));

        final List<Element> categories = eFeed.getChildren("category", getAtomNamespace());
        feed.setCategories(parseCategories(baseURI, categories));

        final List<Element> authors = eFeed.getChildren("author", getAtomNamespace());
        if (!authors.isEmpty()) {
            feed.setAuthors(parsePersons(baseURI, authors, locale));
        }

        final List<Element> contributors = eFeed.getChildren("contributor", getAtomNamespace());
        if (!contributors.isEmpty()) {
            feed.setContributors(parsePersons(baseURI, contributors, locale));
        }

        final Element subtitle = eFeed.getChild("subtitle", getAtomNamespace());
        if (subtitle != null) {
            final Content content = new Content();
            content.setValue(parseTextConstructToString(subtitle));
            content.setType(getAttributeValue(subtitle, "type"));
            feed.setSubtitle(content);
        }

        final Element id = eFeed.getChild("id", getAtomNamespace());
        if (id != null) {
            feed.setId(id.getText());
        }

        final Element generator = eFeed.getChild("generator", getAtomNamespace());
        if (generator != null) {

            final Generator gen = new Generator();
            gen.setValue(generator.getText());

            final String uri = getAttributeValue(generator, "uri");
            if (uri != null) {
                gen.setUrl(uri);
            }

            final String version = getAttributeValue(generator, "version");
            if (version != null) {
                gen.setVersion(version);
            }

            feed.setGenerator(gen);

        }

        final Element rights = eFeed.getChild("rights", getAtomNamespace());
        if (rights != null) {
            feed.setRights(parseTextConstructToString(rights));
        }

        final Element icon = eFeed.getChild("icon", getAtomNamespace());
        if (icon != null) {
            feed.setIcon(icon.getText());
        }

        final Element logo = eFeed.getChild("logo", getAtomNamespace());
        if (logo != null) {
            feed.setLogo(logo.getText());
        }

        final Element updated = eFeed.getChild("updated", getAtomNamespace());
        if (updated != null) {
            feed.setUpdated(DateParser.parseDate(updated.getText(), locale));
        }

        return feed;

    }

    private Link parseLink(final Feed feed, final Entry entry, final String baseURI, final Element eLink) {

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
            if (isRelativeURI(href)) {
                link.setHrefResolved(resolveURI(baseURI, eLink, href));
            }
        }

        final String title = getAttributeValue(eLink, "title");
        if (title != null) {
            link.setTitle(title);
        }

        final String hrefLang = getAttributeValue(eLink, "hreflang");
        if (hrefLang != null) {
            link.setHreflang(hrefLang);
        }

        final String length = getAttributeValue(eLink, "length");
        if (length != null) {
            final Long val = NumberParser.parseLong(length);
            if (val != null) {
                link.setLength(val.longValue());
            }
        }

        return link;

    }

    // List(Elements) -> List(Link)
    private List<Link> parseAlternateLinks(final Feed feed, final Entry entry, final String baseURI, final List<Element> eLinks) {

        final List<Link> links = new ArrayList<Link>();
        for (final Element eLink : eLinks) {
            final Link link = parseLink(feed, entry, baseURI, eLink);
            if (link.getRel() == null || "".equals(link.getRel().trim()) || "alternate".equals(link.getRel())) {
                links.add(link);
            }
        }

        return Lists.emptyToNull(links);

    }

    private List<Link> parseOtherLinks(final Feed feed, final Entry entry, final String baseURI, final List<Element> eLinks) {

        final List<Link> links = new ArrayList<Link>();
        for (final Element eLink : eLinks) {
            final Link link = parseLink(feed, entry, baseURI, eLink);
            if (!"alternate".equals(link.getRel())) {
                links.add(link);
            }
        }

        return Lists.emptyToNull(links);

    }

    private Person parsePerson(final String baseURI, final Element ePerson, final Locale locale) {

        final Person person = new Person();

        final Element name = ePerson.getChild("name", getAtomNamespace());
        if (name != null) {
            person.setName(name.getText());
        }

        final Element uri = ePerson.getChild("uri", getAtomNamespace());
        if (uri != null) {
            person.setUri(uri.getText());
            if (isRelativeURI(uri.getText())) {
                person.setUriResolved(resolveURI(baseURI, ePerson, uri.getText()));
            }
        }

        final Element email = ePerson.getChild("email", getAtomNamespace());
        if (email != null) {
            person.setEmail(email.getText());
        }

        person.setModules(parsePersonModules(ePerson, locale));

        return person;
    }

    // List(Elements) -> List(Persons)
    private List<SyndPerson> parsePersons(final String baseURI, final List<Element> ePersons, final Locale locale) {

        final List<SyndPerson> persons = new ArrayList<SyndPerson>();
        for (final Element ePerson : ePersons) {
            persons.add(parsePerson(baseURI, ePerson, locale));
        }

        return Lists.emptyToNull(persons);

    }

    private Content parseContent(final Element e) {

        final String value = parseTextConstructToString(e);
        final String src = getAttributeValue(e, "src");
        final String type = getAttributeValue(e, "type");

        final Content content = new Content();
        content.setSrc(src);
        content.setType(type);
        content.setValue(value);
        return content;

    }

    private String parseTextConstructToString(final Element e) {

        String type = getAttributeValue(e, "type");
        if (type == null) {
            type = Content.TEXT;
        }

        String value = null;
        if (type.equals(Content.XHTML) || type.indexOf("/xml") != -1 || type.indexOf("+xml") != -1) {
            // XHTML content needs special handling
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
        } else {
            // Everything else comes in verbatim
            value = e.getText();
        }

        return value;

    }

    // List(Elements) -> List(Entries)
    protected List<Entry> parseEntries(final Feed feed, final String baseURI, final List<Element> eEntries, final Locale locale) {

        final List<Entry> entries = new ArrayList<Entry>();
        for (final Element entry : eEntries) {
            entries.add(this.parseEntry(feed, entry, baseURI, locale));
        }

        return Lists.emptyToNull(entries);

    }

    protected Entry parseEntry(final Feed feed, final Element eEntry, final String baseURI, final Locale locale) {

        final Entry entry = new Entry();

        final String xmlBase = eEntry.getAttributeValue("base", Namespace.XML_NAMESPACE);
        if (xmlBase != null) {
            entry.setXmlBase(xmlBase);
        }

        final Element title = eEntry.getChild("title", getAtomNamespace());
        if (title != null) {
            final Content c = new Content();
            c.setValue(parseTextConstructToString(title));
            c.setType(getAttributeValue(title, "type"));
            entry.setTitleEx(c);
        }

        final List<Element> links = eEntry.getChildren("link", getAtomNamespace());
        entry.setAlternateLinks(parseAlternateLinks(feed, entry, baseURI, links));
        entry.setOtherLinks(parseOtherLinks(feed, entry, baseURI, links));

        final List<Element> authors = eEntry.getChildren("author", getAtomNamespace());
        if (!authors.isEmpty()) {
            entry.setAuthors(parsePersons(baseURI, authors, locale));
        }

        final List<Element> contributors = eEntry.getChildren("contributor", getAtomNamespace());
        if (!contributors.isEmpty()) {
            entry.setContributors(parsePersons(baseURI, contributors, locale));
        }

        final Element id = eEntry.getChild("id", getAtomNamespace());
        if (id != null) {
            entry.setId(id.getText());
        }

        final Element updated = eEntry.getChild("updated", getAtomNamespace());
        if (updated != null) {
            entry.setUpdated(DateParser.parseDate(updated.getText(), locale));
        }

        final Element published = eEntry.getChild("published", getAtomNamespace());
        if (published != null) {
            entry.setPublished(DateParser.parseDate(published.getText(), locale));
        }

        final Element summary = eEntry.getChild("summary", getAtomNamespace());
        if (summary != null) {
            entry.setSummary(parseContent(summary));
        }

        final Element content = eEntry.getChild("content", getAtomNamespace());
        if (content != null) {
            final List<Content> contents = new ArrayList<Content>();
            contents.add(parseContent(content));
            entry.setContents(contents);
        }

        final Element rights = eEntry.getChild("rights", getAtomNamespace());
        if (rights != null) {
            entry.setRights(rights.getText());
        }

        final List<Element> categories = eEntry.getChildren("category", getAtomNamespace());
        entry.setCategories(parseCategories(baseURI, categories));

        // TODO: SHOULD handle Atom entry source element
        final Element source = eEntry.getChild("source", getAtomNamespace());
        if (source != null) {
            entry.setSource(parseFeedMetadata(baseURI, source, locale));
        }

        entry.setModules(parseItemModules(eEntry, locale));

        final List<Element> foreignMarkup = extractForeignMarkup(eEntry, entry, getAtomNamespace());
        if (!foreignMarkup.isEmpty()) {
            entry.setForeignMarkup(foreignMarkup);
        }

        return entry;
    }

    private List<Category> parseCategories(final String baseURI, final List<Element> eCategories) {

        final List<Category> cats = new ArrayList<Category>();
        for (final Element eCategory : eCategories) {
            cats.add(parseCategory(baseURI, eCategory));
        }

        return Lists.emptyToNull(cats);

    }

    private Category parseCategory(final String baseURI, final Element eCategory) {

        final Category category = new Category();

        final String term = getAttributeValue(eCategory, "term");
        if (term != null) {
            category.setTerm(term);
        }

        final String scheme = getAttributeValue(eCategory, "scheme");
        if (scheme != null) {
            category.setScheme(scheme);
            if (isRelativeURI(scheme)) {
                category.setSchemeResolved(resolveURI(baseURI, eCategory, scheme));
            }
        }

        final String label = getAttributeValue(eCategory, "label");
        if (label != null) {
            category.setLabel(label);
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

    public static boolean isAbsoluteURI(final String uri) {
        return absoluteURIPattern.matcher(uri).find();
    }

    /** Returns true if URI is relative. */
    public static boolean isRelativeURI(final String uri) {
        return !isAbsoluteURI(uri);
    }

    /**
     * Resolve URI via base URL and parent element. Resolve URI based considering xml:base and
     * baseURI.
     *
     * @param baseURI Base URI used to fetch the XML document
     * @param parent Parent element from which to consider xml:base
     * @param url URL to be resolved
     */
    public static String resolveURI(final String baseURI, final Parent parent, String url) {

        if (!resolveURIs) {
            return url;
        }

        if (isRelativeURI(url)) {

            if (".".equals(url) || "./".equals(url)) {
                url = "";
            }

            if (url.startsWith("/") && baseURI != null) {
                String base = null;
                final int slashslash = baseURI.indexOf("//");
                final int nextslash = baseURI.indexOf("/", slashslash + 2);
                if (nextslash != -1) {
                    base = baseURI.substring(0, nextslash);
                }
                return formURI(base, url);
            }

            // Relative URI with parent
            if (parent != null && parent instanceof Element) {

                // Do we have an xml:base?
                String xmlbase = ((Element) parent).getAttributeValue("base", Namespace.XML_NAMESPACE);
                if (xmlbase != null && xmlbase.trim().length() > 0) {
                    if (isAbsoluteURI(xmlbase)) {
                        // Absolute xml:base, so form URI right now
                        if (url.startsWith("/")) {
                            // Host relative URI
                            final int slashslash = xmlbase.indexOf("//");
                            final int nextslash = xmlbase.indexOf("/", slashslash + 2);
                            if (nextslash != -1) {
                                xmlbase = xmlbase.substring(0, nextslash);
                            }
                            return formURI(xmlbase, url);
                        }
                        if (!xmlbase.endsWith("/")) {
                            // Base URI is filename, strip it off
                            xmlbase = xmlbase.substring(0, xmlbase.lastIndexOf("/"));
                        }
                        return formURI(xmlbase, url);
                    } else {
                        // Relative xml:base, so walk up tree
                        return resolveURI(baseURI, parent.getParent(), stripTrailingSlash(xmlbase) + "/" + stripStartingSlash(url));
                    }
                }
                // No xml:base so walk up tree
                return resolveURI(baseURI, parent.getParent(), url);

                // Relative URI with no parent (i.e. top of tree), so form URI
                // right now
            } else if (parent == null || parent instanceof Document) {
                return formURI(baseURI, url);
            }
        }

        return url;

    }

    /**
     * Find base URI of feed considering relative URIs.
     *
     * @param root Root element of feed.
     */
    private String findBaseURI(final Element root) throws MalformedURLException {
        String ret = null;
        if (findAtomLink(root, "self") != null) {
            ret = findAtomLink(root, "self");
            if (".".equals(ret) || "./".equals(ret)) {
                ret = "";
            }
            if (ret.indexOf("/") != -1) {
                ret = ret.substring(0, ret.lastIndexOf("/"));
            }
            ret = resolveURI(null, root, ret);
        }
        return ret;
    }

    /**
     * Return URL string of Atom link element under parent element. Link with no rel attribute is
     * considered to be rel="alternate"
     *
     * @param parent Consider only children of this parent element
     * @param rel Consider only links with this relationship
     */
    private String findAtomLink(final Element parent, final String rel) {
        String ret = null;
        final List<Element> linksList = parent.getChildren("link", ATOM_10_NS);
        if (linksList != null) {
            for (final Element element : linksList) {
                final Element link = element;
                final Attribute relAtt = getAttribute(link, "rel");
                final Attribute hrefAtt = getAttribute(link, "href");
                if (relAtt == null && "alternate".equals(rel) || relAtt != null && relAtt.getValue().equals(rel)) {
                    ret = hrefAtt.getValue();
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * Form URI by combining base with append portion and giving special consideration to append
     * portions that begin with ".."
     *
     * @param base Base of URI, may end with trailing slash
     * @param append String to append, may begin with slash or ".."
     */
    private static String formURI(String base, String append) {
        base = stripTrailingSlash(base);
        append = stripStartingSlash(append);
        if (append.startsWith("..")) {
            final String[] parts = append.split("/");
            for (final String part : parts) {
                if ("..".equals(part)) {
                    final int last = base.lastIndexOf("/");
                    if (last != -1) {
                        base = base.substring(0, last);
                        append = append.substring(3, append.length());
                    } else {
                        break;
                    }
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
    public static Entry parseEntry(final Reader rd, final String baseURI, final Locale locale) throws JDOMException, IOException, IllegalArgumentException,
            FeedException {

        // Parse entry into JDOM tree
        final SAXBuilder builder = new SAXBuilder();
        final Document entryDoc = builder.build(rd);
        final Element fetchedEntryElement = entryDoc.getRootElement();
        fetchedEntryElement.detach();

        // Put entry into a JDOM document with 'feed' root so that Rome can
        // handle it
        final Feed feed = new Feed();
        feed.setFeedType("atom_1.0");
        final WireFeedOutput wireFeedOutput = new WireFeedOutput();
        final Document feedDoc = wireFeedOutput.outputJDom(feed);
        feedDoc.getRootElement().addContent(fetchedEntryElement);

        if (baseURI != null) {
            feedDoc.getRootElement().setAttribute("base", baseURI, Namespace.XML_NAMESPACE);
        }

        final WireFeedInput input = new WireFeedInput(false, locale);
        final Feed parsedFeed = (Feed) input.build(feedDoc);
        return parsedFeed.getEntries().get(0);
    }

}
