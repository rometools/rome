/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package com.rometools.propono.atom.common;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import com.rometools.propono.utils.ProponoException;
import com.rometools.rome.io.impl.Atom10Parser;

/**
 * Models an Atom workspace collection.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class Collection {

    public static final String ENTRY_TYPE = "application/atom+xml;type=entry";

    private final List<Categories> categories = new ArrayList<Categories>();

    private Element collectionElement = null;
    private String baseURI = null;
    private String title = null;
    private String titleType = null; // may be TEXT, HTML, XHTML
    private List<String> accepts = new ArrayList<String>();
    private String href = null;

    /**
     * Collection MUST have title and href.
     *
     * @param title Title for collection
     * @param titleType Content type of title (null for plain text)
     * @param href Collection URI.
     */
    public Collection(final String title, final String titleType, final String href) {
        this.title = title;
        this.titleType = titleType;
        this.href = href;
    }

    /** Load self from XML element */
    public Collection(final Element e) throws ProponoException {
        collectionElement = e;
        parseCollectionElement(e);
    }

    /** Load self from XML element and base URI for resolving relative URIs */
    public Collection(final Element e, final String baseURI) throws ProponoException {
        collectionElement = e;
        this.baseURI = baseURI;
        parseCollectionElement(e);
    }

    /**
     * List of content-type ranges accepted by collection.
     */
    public List<String> getAccepts() {
        return accepts;
    }

    public void addAccept(final String accept) {
        accepts.add(accept);
    }

    public void setAccepts(final List<String> accepts) {
        this.accepts = accepts;
    }

    /** The URI of the collection */
    public String getHref() {
        return href;
    }

    /**
     * Set URI of collection
     */
    public void setHref(final String href) {
        this.href = href;
    }

    /** Get resolved URI of the collection, or null if impossible to determine */
    public String getHrefResolved() {
        if (Atom10Parser.isAbsoluteURI(href)) {
            return href;
        } else if (baseURI != null && collectionElement != null) {
            final int lastslash = baseURI.lastIndexOf("/");
            return Atom10Parser.resolveURI(baseURI.substring(0, lastslash), collectionElement, href);
        }
        return null;
    }

    /** Get resolved URI using collection's baseURI, or null if impossible to determine */
    public String getHrefResolved(final String relativeUri) {
        if (Atom10Parser.isAbsoluteURI(relativeUri)) {
            return relativeUri;
        } else if (baseURI != null && collectionElement != null) {
            final int lastslash = baseURI.lastIndexOf("/");
            return Atom10Parser.resolveURI(baseURI.substring(0, lastslash), collectionElement, relativeUri);
        }
        return null;
    }

    /** Must have human readable title */
    public String getTitle() {
        return title;
    }

    /**
     * Set title of collection.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Type of title ("text", "html" or "xhtml")
     */
    public String getTitleType() {
        return titleType;
    }

    /**
     * Type of title ("text", "html" or "xhtml")
     */
    public void setTitleType(final String titleType) {
        this.titleType = titleType;
    }

    /** Workspace can have multiple Categories objects */
    public void addCategories(final Categories cats) {
        categories.add(cats);
    }

    /**
     * Get categories allowed by collection.
     *
     * @return Collection of {@link com.rometools.rome.propono.atom.common.Categories} objects.
     */
    public List<Categories> getCategories() {
        return categories;
    }

    /**
     * Returns true if contentType is accepted by collection.
     */
    public boolean accepts(final String ct) {
        for (final Object element : accepts) {
            final String accept = (String) element;
            if (accept != null && accept.trim().equals("*/*")) {
                return true;
            }
            final String entryType = "application/atom+xml";
            final boolean entry = entryType.equals(ct);
            if (entry && null == accept) {
                return true;
            } else if (entry && "entry".equals(accept)) {
                return true;
            } else if (entry && entryType.equals(accept)) {
                return true;
            } else {
                final String[] rules = accepts.toArray(new String[accepts.size()]);
                for (final String rule2 : rules) {
                    String rule = rule2.trim();
                    if (rule.equals(ct)) {
                        return true;
                    }
                    final int slashstar = rule.indexOf("/*");
                    if (slashstar > 0) {
                        rule = rule.substring(0, slashstar + 1);
                        if (ct.startsWith(rule)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Serialize an AtomService.Collection into an XML element
     */
    public Element collectionToElement() {
        final Collection collection = this;
        final Element element = new Element("collection", AtomService.ATOM_PROTOCOL);
        element.setAttribute("href", collection.getHref());

        final Element titleElem = new Element("title", AtomService.ATOM_FORMAT);
        titleElem.setText(collection.getTitle());
        if (collection.getTitleType() != null && !collection.getTitleType().equals("TEXT")) {
            titleElem.setAttribute("type", collection.getTitleType(), AtomService.ATOM_FORMAT);
        }
        element.addContent(titleElem);

        // Loop to create <app:categories> elements
        for (final Object element2 : collection.getCategories()) {
            final Categories cats = (Categories) element2;
            element.addContent(cats.categoriesToElement());
        }

        for (final Object element2 : collection.getAccepts()) {
            final String range = (String) element2;
            final Element acceptElem = new Element("accept", AtomService.ATOM_PROTOCOL);
            acceptElem.setText(range);
            element.addContent(acceptElem);
        }

        return element;
    }

    /** Deserialize an Atom service collection XML element into an object */
    public Collection elementToCollection(final Element element) throws ProponoException {
        return new Collection(element);
    }

    protected void parseCollectionElement(final Element element) throws ProponoException {
        setHref(element.getAttribute("href").getValue());

        final Element titleElem = element.getChild("title", AtomService.ATOM_FORMAT);
        if (titleElem != null) {
            setTitle(titleElem.getText());
            if (titleElem.getAttribute("type", AtomService.ATOM_FORMAT) != null) {
                setTitleType(titleElem.getAttribute("type", AtomService.ATOM_FORMAT).getValue());
            }
        }

        final List<Element> acceptElems = element.getChildren("accept", AtomService.ATOM_PROTOCOL);
        if (acceptElems != null && !acceptElems.isEmpty()) {
            for (final Element acceptElem : acceptElems) {
                addAccept(acceptElem.getTextTrim());
            }
        }

        // Loop to parse <app:categories> element to Categories objects
        final List<Element> catsElems = element.getChildren("categories", AtomService.ATOM_PROTOCOL);
        for (final Element catsElem : catsElems) {
            final Categories cats = new Categories(catsElem, baseURI);
            addCategories(cats);
        }
    }

}
