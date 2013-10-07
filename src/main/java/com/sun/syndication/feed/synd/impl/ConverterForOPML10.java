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
package com.sun.syndication.feed.synd.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.opml.Attribute;
import com.sun.syndication.feed.opml.Opml;
import com.sun.syndication.feed.opml.Outline;
import com.sun.syndication.feed.synd.Converter;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndLink;
import com.sun.syndication.feed.synd.SyndLinkImpl;
import com.sun.syndication.feed.synd.SyndPerson;
import com.sun.syndication.feed.synd.SyndPersonImpl;

/**
 * 
 * @author cooper
 */
public class ConverterForOPML10 implements Converter {
    private static final Logger LOG = Logger.getLogger(ConverterForOPML10.class.getName().toString());
    public static final String URI_TREE = "urn:rome.tree";
    public static final String URI_ATTRIBUTE = "urn:rome.attribute#";

    /** Creates a new instance of ConverterForOPML10 */
    public ConverterForOPML10() {
        super();
    }

    protected void addOwner(Opml opml, SyndFeed syndFeed) {
        if ((opml.getOwnerEmail() != null) || (opml.getOwnerName() != null)) {
            List authors = new ArrayList();
            SyndPerson person = new SyndPersonImpl();
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
    public void copyInto(WireFeed feed, SyndFeed syndFeed) {
        Opml opml = (Opml) feed;
        syndFeed.setTitle(opml.getTitle());
        addOwner(opml, syndFeed);
        syndFeed.setPublishedDate((opml.getModified() != null) ? opml.getModified() : opml.getCreated());
        syndFeed.setFeedType(opml.getFeedType());
        syndFeed.setModules(opml.getModules());
        syndFeed.setFeedType(this.getType());

        ArrayList entries = new ArrayList();
        createEntries(new TreeContext(), syndFeed.getEntries(), opml.getOutlines());
    }

    protected void createEntries(TreeContext context, List allEntries, List outlines) {
        List so = Collections.synchronizedList(outlines);

        for (int i = 0; i < so.size(); i++) {
            createEntry(context, allEntries, (Outline) so.get(i));
        }
    }

    protected SyndEntry createEntry(TreeContext context, List allEntries, Outline outline) {
        SyndEntry entry = new SyndEntryImpl();

        if ((outline.getType() != null) && outline.getType().equals("rss")) {
            entry.setLink((outline.getHtmlUrl() != null) ? outline.getHtmlUrl() : outline.getXmlUrl());
        } else if ((outline.getType() != null) && outline.getType().equals("link")) {
            entry.setLink(outline.getUrl());
        }

        if (outline.getHtmlUrl() != null) {
            SyndLink link = new SyndLinkImpl();
            link.setRel("alternate");
            link.setType("text/html");
            link.setHref(outline.getHtmlUrl());
            entry.getLinks().add(link);
            entry.setLink(outline.getHtmlUrl());
        }

        if ((outline.getXmlUrl() != null) && (outline.getType() != null) && outline.getType().equalsIgnoreCase("rss")) {
            SyndLink link = new SyndLinkImpl();
            link.setRel("alternate");
            link.setType("application/rss+xml");
            link.setHref(outline.getXmlUrl());
            entry.getLinks().add(link);

            if (entry.getLink() == null) {
                entry.setLink(outline.getXmlUrl());
            }
        }

        if ((outline.getXmlUrl() != null) && (outline.getType() != null) && outline.getType().equalsIgnoreCase("atom")) {
            SyndLink link = new SyndLinkImpl();
            link.setRel("alternate");
            link.setType("application/atom+xml");
            link.setHref(outline.getXmlUrl());
            entry.getLinks().add(link);

            if (entry.getLink() == null) {
                entry.setLink(outline.getXmlUrl());
            }
        }

        if ((outline.getType() != null) && outline.getType().equals("rss")) {
            entry.setTitle(outline.getTitle());
        } else {
            entry.setTitle(outline.getText());
        }

        if ((outline.getText() == null) && (entry.getTitle() != null)) {
            SyndContent c = new SyndContentImpl();
            c.setValue(outline.getText());
            entry.setDescription(c);
        }

        entry.setPublishedDate(outline.getCreated());

        String nodeName = "node." + outline.hashCode();

        SyndCategory cat = new TreeCategoryImpl();
        cat.setTaxonomyUri(URI_TREE);
        cat.setName(nodeName);
        entry.getCategories().add(cat);

        if (context.size() > 0) {
            Integer parent = (Integer) context.peek();
            SyndCategory pcat = new TreeCategoryImpl();
            pcat.setTaxonomyUri(URI_TREE);
            pcat.setName("parent." + parent);
            entry.getCategories().add(pcat);
        }

        List attributes = Collections.synchronizedList(outline.getAttributes());

        for (int i = 0; i < attributes.size(); i++) {
            Attribute a = (Attribute) attributes.get(i);
            SyndCategory acat = new SyndCategoryImpl();
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
     * 
     */
    @Override
    public WireFeed createRealFeed(SyndFeed syndFeed) {
        List entries = Collections.synchronizedList(syndFeed.getEntries());

        HashMap entriesByNode = new HashMap();
        ArrayList doAfterPass = new ArrayList(); // this will hold entries that we can't parent the first time.
        ArrayList root = new ArrayList(); // this holds root level outlines;

        for (int i = 0; i < entries.size(); i++) {
            SyndEntry entry = (SyndEntry) entries.get(i);
            Outline o = new Outline();

            List cats = Collections.synchronizedList(entry.getCategories());
            boolean parentFound = false;
            StringBuffer category = new StringBuffer();

            for (int j = 0; j < cats.size(); j++) {
                SyndCategory cat = (SyndCategory) cats.get(j);

                if ((cat.getTaxonomyUri() != null) && cat.getTaxonomyUri().equals(URI_TREE)) {
                    String nodeVal = cat.getName().substring(cat.getName().lastIndexOf("."), cat.getName().length());

                    if (cat.getName().startsWith("node.")) {
                        entriesByNode.put(nodeVal, o);
                    } else if (cat.getName().startsWith("parent.")) {
                        parentFound = true;

                        Outline parent = (Outline) entriesByNode.get(nodeVal);

                        if (parent != null) {
                            parent.getChildren().add(o);
                        } else {
                            doAfterPass.add(new OutlineHolder(o, nodeVal));
                        }
                    }
                } else if ((cat.getTaxonomyUri() != null) && cat.getTaxonomyUri().startsWith(URI_ATTRIBUTE)) {
                    String name = cat.getTaxonomyUri().substring(cat.getTaxonomyUri().indexOf("#") + 1, cat.getTaxonomyUri().length());
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

            List links = Collections.synchronizedList(entry.getLinks());
            String entryLink = entry.getLink();

            for (int j = 0; j < links.size(); j++) {
                SyndLink link = (SyndLink) links.get(j);

                // if(link.getHref().equals(entryLink)) {
                if (((link.getType() != null) && (link.getRel() != null) && link.getRel().equals("alternate"))
                        && (link.getType().equals("application/rss+xml") || link.getType().equals("application/atom+xml"))) {
                    o.setType("rss");

                    if (o.getXmlUrl() == null) {
                        o.getAttributes().add(new Attribute("xmlUrl", link.getHref()));
                    }
                } else if ((link.getType() != null) && (link.getType().equals("text/html"))) {
                    if (o.getHtmlUrl() == null) {
                        o.getAttributes().add(new Attribute("htmlUrl", link.getHref()));
                    }
                } else {
                    o.setType(link.getType());
                }

                // }
            }

            if ((o.getType() == null) || o.getType().equals("link")) {
                o.setText(entry.getTitle());

            } else {
                o.setTitle(entry.getTitle());
            }

            if ((o.getText() == null) && (entry.getDescription() != null)) {
                o.setText(entry.getDescription().getValue());
            }
        }

        // Do back and parenting for things we missed.
        for (int i = 0; i < doAfterPass.size(); i++) {
            OutlineHolder o = (OutlineHolder) doAfterPass.get(i);
            Outline parent = (Outline) entriesByNode.get(o.parent);

            if (parent == null) {
                root.add(o.outline);
                LOG.warning("Unable to find parent node :" + o.parent);
            } else {
                parent.getChildren().add(o.outline);
            }
        }

        Opml opml = new Opml();
        opml.setFeedType(this.getType());
        opml.setCreated(syndFeed.getPublishedDate());
        opml.setTitle(syndFeed.getTitle());

        List authors = Collections.synchronizedList(syndFeed.getAuthors());

        for (int i = 0; i < authors.size(); i++) {
            SyndPerson p = (SyndPerson) authors.get(i);

            if ((syndFeed.getAuthor() == null) || syndFeed.getAuthor().equals(p.getName())) {
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
     * <p>
     * 
     * @return the real feed type.
     * @see WireFeed for details on the format of this string.
     *      <p>
     */
    @Override
    public String getType() {
        return "opml_1.0";
    }

    private static class OutlineHolder {
        Outline outline;
        String parent;

        public OutlineHolder(Outline outline, String parent) {
            this.outline = outline;
            this.parent = parent;
        }
    }

    private static class TreeContext extends Stack {
        TreeContext() {
            super();
        }
    }
}
