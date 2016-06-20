/*
 * ConverterForOPML10.java
 *
 * Created on April 25, 2006, 1:26 AM
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
 */
package com.rometools.opml.feed.synd.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.opml.feed.opml.Attribute;
import com.rometools.opml.feed.opml.Opml;
import com.rometools.opml.feed.opml.Outline;
import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.synd.Converter;
import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndCategoryImpl;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndLink;
import com.rometools.rome.feed.synd.SyndLinkImpl;
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.rome.feed.synd.SyndPersonImpl;

public class ConverterForOPML10 implements Converter {

    private static final Logger LOG = LoggerFactory.getLogger(ConverterForOPML10.class);

    public static final String URI_TREE = "urn:rome.tree";
    public static final String URI_ATTRIBUTE = "urn:rome.attribute#";

    protected void addOwner(final Opml opml, final SyndFeed syndFeed) {
        if (opml.getOwnerEmail() != null || opml.getOwnerName() != null) {
            final List<SyndPerson> authors = new ArrayList<SyndPerson>();
            final SyndPerson person = new SyndPersonImpl();
            person.setEmail(opml.getOwnerEmail());
            person.setName(opml.getOwnerName());
            authors.add(person);
            syndFeed.setAuthors(authors);
        }
    }

    /**
     * Makes a deep copy/conversion of the values of a real feed into a SyndFeedImpl.
     * <p>
     * It assumes the given SyndFeedImpl has no properties set.
     * <p>
     *
     * @param feed real feed to copy/convert.
     * @param syndFeed the SyndFeedImpl that will contain the copied/converted values of the real feed.
     */
    @Override
    public void copyInto(final WireFeed feed, final SyndFeed syndFeed) {
        final Opml opml = (Opml) feed;
        syndFeed.setTitle(opml.getTitle());
        addOwner(opml, syndFeed);
        syndFeed.setPublishedDate(opml.getModified() != null ? opml.getModified() : opml.getCreated());
        syndFeed.setFeedType(opml.getFeedType());
        syndFeed.setModules(opml.getModules());
        syndFeed.setFeedType(getType());

        createEntries(new Stack<Integer>(), syndFeed.getEntries(), opml.getOutlines());
    }

    protected void createEntries(final Stack<Integer> context, final List<SyndEntry> allEntries, final List<Outline> outlines) {
        final List<Outline> so = Collections.synchronizedList(outlines);

        for (int i = 0; i < so.size(); i++) {
            createEntry(context, allEntries, so.get(i));
        }
    }

    protected SyndEntry createEntry(final Stack<Integer> context, final List<SyndEntry> allEntries, final Outline outline) {
        final SyndEntry entry = new SyndEntryImpl();

        if (outline.getType() != null && outline.getType().equals("rss")) {
            entry.setLink(outline.getHtmlUrl() != null ? outline.getHtmlUrl() : outline.getXmlUrl());
        } else if (outline.getType() != null && outline.getType().equals("link")) {
            entry.setLink(outline.getUrl());
        }

        if (outline.getHtmlUrl() != null) {
            final SyndLink link = new SyndLinkImpl();
            link.setRel("alternate");
            link.setType("text/html");
            link.setHref(outline.getHtmlUrl());
            entry.getLinks().add(link);
            entry.setLink(outline.getHtmlUrl());
        }

        if (outline.getXmlUrl() != null && outline.getType() != null && outline.getType().equalsIgnoreCase("rss")) {
            final SyndLink link = new SyndLinkImpl();
            link.setRel("alternate");
            link.setType("application/rss+xml");
            link.setHref(outline.getXmlUrl());
            entry.getLinks().add(link);

            if (entry.getLink() == null) {
                entry.setLink(outline.getXmlUrl());
            }
        }

        if (outline.getXmlUrl() != null && outline.getType() != null && outline.getType().equalsIgnoreCase("atom")) {
            final SyndLink link = new SyndLinkImpl();
            link.setRel("alternate");
            link.setType("application/atom+xml");
            link.setHref(outline.getXmlUrl());
            entry.getLinks().add(link);

            if (entry.getLink() == null) {
                entry.setLink(outline.getXmlUrl());
            }
        }

        if (outline.getType() != null && outline.getType().equals("rss")) {
            entry.setTitle(outline.getTitle());
        } else {
            entry.setTitle(outline.getText());
        }

        if (outline.getText() == null && entry.getTitle() != null) {
            final SyndContent c = new SyndContentImpl();
            c.setValue(outline.getText());
            entry.setDescription(c);
        }

        entry.setPublishedDate(outline.getCreated());

        final String nodeName = "node." + outline.hashCode();

        final SyndCategory cat = new TreeCategoryImpl();
        cat.setTaxonomyUri(URI_TREE);
        cat.setName(nodeName);
        entry.getCategories().add(cat);

        if (!context.isEmpty()) {
            final Integer parent = context.peek();
            final SyndCategory pcat = new TreeCategoryImpl();
            pcat.setTaxonomyUri(URI_TREE);
            pcat.setName("parent." + parent);
            entry.getCategories().add(pcat);
        }

        final List<Attribute> attributes = Collections.synchronizedList(outline.getAttributes());

        for (int i = 0; i < attributes.size(); i++) {
            final Attribute a = attributes.get(i);
            final SyndCategory acat = new SyndCategoryImpl();
            acat.setName(a.getValue());
            acat.setTaxonomyUri(URI_ATTRIBUTE + a.getName());
            entry.getCategories().add(acat);
        }

        entry.setModules(outline.getModules());
        allEntries.add(entry);
        context.push(new Integer(outline.hashCode()));
        createEntries(context, allEntries, outline.getChildren());
        context.pop();

        return entry;
    }

    /**
     * Creates real feed with a deep copy/conversion of the values of a SyndFeedImpl.
     * <p>
     *
     * @param syndFeed SyndFeedImpl to copy/convert value from.
     * @return a real feed with copied/converted values of the SyndFeedImpl.
     */
    @Override
    public WireFeed createRealFeed(final SyndFeed syndFeed) {

        final List<SyndEntry> entries = Collections.synchronizedList(syndFeed.getEntries());

        final HashMap<String, Outline> entriesByNode = new HashMap<String, Outline>();

        // this will hold entries that we can't parent the first time.
        final ArrayList<OutlineHolder> doAfterPass = new ArrayList<OutlineHolder>();

        // this holds root level outlines;
        final ArrayList<Outline> root = new ArrayList<Outline>();

        for (int i = 0; i < entries.size(); i++) {
            final SyndEntry entry = entries.get(i);
            final Outline o = new Outline();

            final List<SyndCategory> cats = Collections.synchronizedList(entry.getCategories());
            boolean parentFound = false;
            final StringBuffer category = new StringBuffer();

            for (int j = 0; j < cats.size(); j++) {
                final SyndCategory cat = cats.get(j);

                if (cat.getTaxonomyUri() != null && cat.getTaxonomyUri().equals(URI_TREE)) {
                    final String nodeVal = cat.getName().substring(cat.getName().lastIndexOf("."), cat.getName().length());

                    if (cat.getName().startsWith("node.")) {
                        entriesByNode.put(nodeVal, o);
                    } else if (cat.getName().startsWith("parent.")) {
                        parentFound = true;

                        final Outline parent = entriesByNode.get(nodeVal);

                        if (parent != null) {
                            parent.getChildren().add(o);
                        } else {
                            doAfterPass.add(new OutlineHolder(o, nodeVal));
                        }
                    }
                } else if (cat.getTaxonomyUri() != null && cat.getTaxonomyUri().startsWith(URI_ATTRIBUTE)) {
                    final String name = cat.getTaxonomyUri().substring(cat.getTaxonomyUri().indexOf("#") + 1, cat.getTaxonomyUri().length());
                    o.getAttributes().add(new Attribute(name, cat.getName()));
                } else {
                    if (category.length() > 0) {
                        category.append(", ");
                    }

                    category.append(cat.getName());
                }
            }

            if (!parentFound) {
                root.add(o);
            }

            if (category.length() > 0) {
                o.getAttributes().add(new Attribute("category", category.toString()));
            }

            final List<SyndLink> links = Collections.synchronizedList(entry.getLinks());
            // final String entryLink = entry.getLink();

            for (int j = 0; j < links.size(); j++) {
                final SyndLink link = links.get(j);

                // if(link.getHref().equals(entryLink)) {
                if (link.getType() != null && link.getRel() != null && link.getRel().equals("alternate")
                        && (link.getType().equals("application/rss+xml") || link.getType().equals("application/atom+xml"))) {
                    o.setType("rss");

                    if (o.getXmlUrl() == null) {
                        o.getAttributes().add(new Attribute("xmlUrl", link.getHref()));
                    }
                } else if (link.getType() != null && link.getType().equals("text/html")) {
                    if (o.getHtmlUrl() == null) {
                        o.getAttributes().add(new Attribute("htmlUrl", link.getHref()));
                    }
                } else {
                    o.setType(link.getType());
                }

                // }
            }

            if (o.getType() == null || o.getType().equals("link")) {
                o.setText(entry.getTitle());

            } else {
                o.setTitle(entry.getTitle());
            }

            if (o.getText() == null && entry.getDescription() != null) {
                o.setText(entry.getDescription().getValue());
            }
        }

        // Do back and parenting for things we missed.
        for (int i = 0; i < doAfterPass.size(); i++) {
            final OutlineHolder o = doAfterPass.get(i);
            final Outline parent = entriesByNode.get(o.parent);

            if (parent == null) {
                root.add(o.outline);
                LOG.warn("Unable to find parent node: {}", o.parent);
            } else {
                parent.getChildren().add(o.outline);
            }
        }

        final Opml opml = new Opml();
        opml.setFeedType(getType());
        opml.setCreated(syndFeed.getPublishedDate());
        opml.setTitle(syndFeed.getTitle());

        final List<SyndPerson> authors = Collections.synchronizedList(syndFeed.getAuthors());

        for (int i = 0; i < authors.size(); i++) {
            final SyndPerson p = authors.get(i);

            if (syndFeed.getAuthor() == null || syndFeed.getAuthor().equals(p.getName())) {
                opml.setOwnerName(p.getName());
                opml.setOwnerEmail(p.getEmail());
                opml.setOwnerId(p.getUri());
            }
        }

        opml.setOutlines(root);

        return opml;
    }

    /**
     * Returns the type (version) of the real feed this converter handles.
     *
     * @return the real feed type.
     * @see WireFeed for details on the format of this string.
     */
    @Override
    public String getType() {
        return "opml_1.0";
    }

    private static class OutlineHolder {

        private final Outline outline;
        private final String parent;

        public OutlineHolder(final Outline outline, final String parent) {
            this.outline = outline;
            this.parent = parent;
        }

    }

}
