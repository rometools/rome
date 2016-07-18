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

import com.rometools.rome.feed.atom.Category;
import com.rometools.rome.io.impl.Atom10Parser;

/**
 * Models an Atom protocol Categories element, which may contain ROME Atom
 * {@link com.rometools.rome.feed.atom.Category} elements.
 *
 * @deprecated Propono will be removed in Rome 2.
 */
@Deprecated
public class Categories {

    private final List<Category> categories = new ArrayList<Category>();
    private String baseURI = null;
    private Element categoriesElement = null;
    private String href = null;
    private String scheme = null;
    private boolean fixed = false;

    public Categories() {
    }

    /** Load select from XML element */
    public Categories(final Element e, final String baseURI) {
        categoriesElement = e;
        this.baseURI = baseURI;
        parseCategoriesElement(e);
    }

    /** Add category list of those specified */
    public void addCategory(final Category cat) {
        categories.add(cat);
    }

    /**
     * Iterate over Category objects
     *
     * @return List of ROME Atom {@link com.rometools.rome.feed.atom.Category}
     */
    public List<Category> getCategories() {
        return categories;
    }

    /** True if clients MUST use one of the categories specified */
    public boolean isFixed() {
        return fixed;
    }

    /** True if clients MUST use one of the categories specified */
    public void setFixed(final boolean fixed) {
        this.fixed = fixed;
    }

    /** Category URI scheme to use for Categories without a scheme */
    public String getScheme() {
        return scheme;
    }

    /** Category URI scheme to use for Categories without a scheme */
    public void setScheme(final String scheme) {
        this.scheme = scheme;
    }

    /** URI of out-of-line categories */
    public String getHref() {
        return href;
    }

    /** URI of out-of-line categories */
    public void setHref(final String href) {
        this.href = href;
    }

    /** Get unresolved URI of the collection, or null if impossible to determine */
    public String getHrefResolved() {
        if (Atom10Parser.isAbsoluteURI(href)) {
            return href;
        } else if (baseURI != null && categoriesElement != null) {
            return Atom10Parser.resolveURI(baseURI, categoriesElement, href);
        }
        return null;
    }

    public Element categoriesToElement() {
        final Categories cats = this;
        final Element catsElem = new Element("categories", AtomService.ATOM_PROTOCOL);
        catsElem.setAttribute("fixed", cats.isFixed() ? "yes" : "no", AtomService.ATOM_PROTOCOL);
        if (cats.getScheme() != null) {
            catsElem.setAttribute("scheme", cats.getScheme(), AtomService.ATOM_PROTOCOL);
        }
        if (cats.getHref() != null) {
            catsElem.setAttribute("href", cats.getHref(), AtomService.ATOM_PROTOCOL);
        } else {
            // Loop to create <atom:category> elements
            for (final Object element : cats.getCategories()) {
                final Category cat = (Category) element;
                final Element catElem = new Element("category", AtomService.ATOM_FORMAT);
                catElem.setAttribute("term", cat.getTerm(), AtomService.ATOM_FORMAT);
                if (cat.getScheme() != null) { // optional
                    catElem.setAttribute("scheme", cat.getScheme(), AtomService.ATOM_FORMAT);
                }
                if (cat.getLabel() != null) { // optional
                    catElem.setAttribute("label", cat.getLabel(), AtomService.ATOM_FORMAT);
                }
                catsElem.addContent(catElem);
            }
        }
        return catsElem;
    }

    protected void parseCategoriesElement(final Element catsElem) {

        if (catsElem.getAttribute("href", AtomService.ATOM_PROTOCOL) != null) {
            setHref(catsElem.getAttribute("href", AtomService.ATOM_PROTOCOL).getValue());
        }

        if (catsElem.getAttribute("fixed", AtomService.ATOM_PROTOCOL) != null) {
            if ("yes".equals(catsElem.getAttribute("fixed", AtomService.ATOM_PROTOCOL).getValue())) {
                setFixed(true);
            }
        }

        if (catsElem.getAttribute("scheme", AtomService.ATOM_PROTOCOL) != null) {
            setScheme(catsElem.getAttribute("scheme", AtomService.ATOM_PROTOCOL).getValue());
        }

        // Loop to parse <atom:category> elemenents to Category objects
        final List<Element> catElems = catsElem.getChildren("category", AtomService.ATOM_FORMAT);
        for (final Element catElem : catElems) {
            final Category cat = new Category();
            cat.setTerm(catElem.getAttributeValue("term", AtomService.ATOM_FORMAT));
            cat.setLabel(catElem.getAttributeValue("label", AtomService.ATOM_FORMAT));
            cat.setScheme(catElem.getAttributeValue("scheme", AtomService.ATOM_FORMAT));
            addCategory(cat);
        }
    }

}
