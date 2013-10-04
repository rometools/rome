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
import java.util.Date;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import com.sun.syndication.feed.module.DCModule;
import com.sun.syndication.feed.module.DCModuleImpl;
import com.sun.syndication.feed.module.DCSubject;
import com.sun.syndication.feed.module.DCSubjectImpl;
import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.ModuleParser;

/**
 * Parser for the Dublin Core module.
 */
public class DCModuleParser implements ModuleParser {

    private static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    private static final String TAXO_URI = "http://purl.org/rss/1.0/modules/taxonomy/";

    private static final Namespace DC_NS = Namespace.getNamespace(DCModule.URI);
    private static final Namespace RDF_NS = Namespace.getNamespace(RDF_URI);
    private static final Namespace TAXO_NS = Namespace.getNamespace(TAXO_URI);

    @Override
    public final String getNamespaceUri() {
        return DCModule.URI;
    }

    private final Namespace getDCNamespace() {
        return DC_NS;
    }

    private final Namespace getRDFNamespace() {
        return RDF_NS;
    }

    private final Namespace getTaxonomyNamespace() {
        return TAXO_NS;
    }

    /**
     * Parse an element tree and return the module found in it.
     * <p>
     * 
     * @param dcRoot the root element containing the module elements.
     * @return the module parsed from the element tree, <i>null</i> if none.
     */
    @Override
    public Module parse(final Element dcRoot) {
        boolean foundSomething = false;
        final DCModule dcm = new DCModuleImpl();

        List<Element> eList = dcRoot.getChildren("title", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setTitles(parseElementList(eList));
        }
        eList = dcRoot.getChildren("creator", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setCreators(parseElementList(eList));
        }
        eList = dcRoot.getChildren("subject", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setSubjects(parseSubjects(eList));
        }
        eList = dcRoot.getChildren("description", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setDescriptions(parseElementList(eList));
        }
        eList = dcRoot.getChildren("publisher", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setPublishers(parseElementList(eList));
        }
        eList = dcRoot.getChildren("contributor", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setContributors(parseElementList(eList));
        }
        eList = dcRoot.getChildren("date", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setDates(parseElementListDate(eList));
        }
        eList = dcRoot.getChildren("type", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setTypes(parseElementList(eList));
        }
        eList = dcRoot.getChildren("format", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setFormats(parseElementList(eList));
        }
        eList = dcRoot.getChildren("identifier", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setIdentifiers(parseElementList(eList));
        }
        eList = dcRoot.getChildren("source", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setSources(parseElementList(eList));
        }
        eList = dcRoot.getChildren("language", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setLanguages(parseElementList(eList));
        }
        eList = dcRoot.getChildren("relation", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setRelations(parseElementList(eList));
        }
        eList = dcRoot.getChildren("coverage", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setCoverages(parseElementList(eList));
        }
        eList = dcRoot.getChildren("rights", getDCNamespace());
        if (eList.size() > 0) {
            foundSomething = true;
            dcm.setRightsList(parseElementList(eList));
        }
        if (foundSomething) {
            return dcm;
        } else {
            return null;
        }
    }

    /**
     * Utility method to parse a taxonomy from an element.
     * <p>
     * 
     * @param desc the taxonomy description element.
     * @return the string contained in the resource of the element.
     */
    protected final String getTaxonomy(final Element desc) {
        String d = null;
        final Element taxo = desc.getChild("topic", getTaxonomyNamespace());
        if (taxo != null) {
            final Attribute a = taxo.getAttribute("resource", getRDFNamespace());
            if (a != null) {
                d = a.getValue();
            }
        }
        return d;
    }

    /**
     * Utility method to parse a list of subjects out of a list of elements.
     * <p>
     * 
     * @param eList the element list to parse.
     * @return a list of subjects parsed from the elements.
     */
    protected final List<DCSubject> parseSubjects(final List<Element> eList) {
        final List<DCSubject> subjects = new ArrayList<DCSubject>();
        for (final Element element : eList) {
            final Element eSubject = element;
            final Element eDesc = eSubject.getChild("Description", getRDFNamespace());
            if (eDesc != null) {
                final String taxonomy = getTaxonomy(eDesc);
                final List<Element> eValues = eDesc.getChildren("value", getRDFNamespace());
                for (final Element element2 : eValues) {
                    final Element eValue = element2;
                    final DCSubject subject = new DCSubjectImpl();
                    subject.setTaxonomyUri(taxonomy);
                    subject.setValue(eValue.getText());
                    subjects.add(subject);
                }
            } else {
                final DCSubject subject = new DCSubjectImpl();
                subject.setValue(eSubject.getText());
                subjects.add(subject);
            }
        }

        return subjects;
    }

    /**
     * Utility method to parse a list of strings out of a list of elements.
     * <p>
     * 
     * @param eList the list of elements to parse.
     * @return the list of strings
     */
    protected final List<String> parseElementList(final List<Element> eList) {
        final List<String> values = new ArrayList<String>();
        for (final Element element : eList) {
            final Element e = element;
            values.add(e.getText());
        }

        return values;
    }

    /**
     * Utility method to parse a list of dates out of a list of elements.
     * <p>
     * 
     * @param eList the list of elements to parse.
     * @return the list of dates.
     */
    protected final List<Date> parseElementListDate(final List<Element> eList) {
        final List<Date> values = new ArrayList<Date>();
        for (final Element element : eList) {
            final Element e = element;
            values.add(DateParser.parseDate(e.getText()));
        }

        return values;
    }
}
