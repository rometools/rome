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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.module.DCModule;
import com.rometools.rome.feed.module.DCModuleImpl;
import com.rometools.rome.feed.module.DCSubject;
import com.rometools.rome.feed.module.DCSubjectImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;

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
     * @param locale for date/time parsing
     * @return the module parsed from the element tree, <i>null</i> if none.
     */
    @Override
    public Module parse(final Element dcRoot, final Locale locale) {

        boolean foundSomething = false;
        final DCModule dcm = new DCModuleImpl();

        final List<Element> titles = dcRoot.getChildren("title", getDCNamespace());
        if (!titles.isEmpty()) {
            foundSomething = true;
            dcm.setTitles(parseElementList(titles));
        }

        final List<Element> creators = dcRoot.getChildren("creator", getDCNamespace());
        if (!creators.isEmpty()) {
            foundSomething = true;
            dcm.setCreators(parseElementList(creators));
        }

        final List<Element> subjects = dcRoot.getChildren("subject", getDCNamespace());
        if (!subjects.isEmpty()) {
            foundSomething = true;
            dcm.setSubjects(parseSubjects(subjects));
        }

        final List<Element> descriptions = dcRoot.getChildren("description", getDCNamespace());
        if (!descriptions.isEmpty()) {
            foundSomething = true;
            dcm.setDescriptions(parseElementList(descriptions));
        }

        final List<Element> publishers = dcRoot.getChildren("publisher", getDCNamespace());
        if (!publishers.isEmpty()) {
            foundSomething = true;
            dcm.setPublishers(parseElementList(publishers));
        }

        final List<Element> contributors = dcRoot.getChildren("contributor", getDCNamespace());
        if (!contributors.isEmpty()) {
            foundSomething = true;
            dcm.setContributors(parseElementList(contributors));
        }

        final List<Element> dates = dcRoot.getChildren("date", getDCNamespace());
        if (!dates.isEmpty()) {
            foundSomething = true;
            dcm.setDates(parseElementListDate(dates, locale));
        }

        final List<Element> types = dcRoot.getChildren("type", getDCNamespace());
        if (!types.isEmpty()) {
            foundSomething = true;
            dcm.setTypes(parseElementList(types));
        }

        final List<Element> formats = dcRoot.getChildren("format", getDCNamespace());
        if (!formats.isEmpty()) {
            foundSomething = true;
            dcm.setFormats(parseElementList(formats));
        }

        final List<Element> identifiers = dcRoot.getChildren("identifier", getDCNamespace());
        if (!identifiers.isEmpty()) {
            foundSomething = true;
            dcm.setIdentifiers(parseElementList(identifiers));
        }

        final List<Element> sources = dcRoot.getChildren("source", getDCNamespace());
        if (!sources.isEmpty()) {
            foundSomething = true;
            dcm.setSources(parseElementList(sources));
        }

        final List<Element> languages = dcRoot.getChildren("language", getDCNamespace());
        if (!languages.isEmpty()) {
            foundSomething = true;
            dcm.setLanguages(parseElementList(languages));
        }

        final List<Element> relations = dcRoot.getChildren("relation", getDCNamespace());
        if (!relations.isEmpty()) {
            foundSomething = true;
            dcm.setRelations(parseElementList(relations));
        }

        final List<Element> coverages = dcRoot.getChildren("coverage", getDCNamespace());
        if (!coverages.isEmpty()) {
            foundSomething = true;
            dcm.setCoverages(parseElementList(coverages));
        }

        final List<Element> rights = dcRoot.getChildren("rights", getDCNamespace());
        if (!rights.isEmpty()) {
            foundSomething = true;
            dcm.setRightsList(parseElementList(rights));
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
        String taxonomy = null;
        final Element topic = desc.getChild("topic", getTaxonomyNamespace());
        if (topic != null) {
            final Attribute resource = topic.getAttribute("resource", getRDFNamespace());
            if (resource != null) {
                taxonomy = resource.getValue();
            }
        }
        return taxonomy;
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

        for (final Element eSubject : eList) {

            final Element description = eSubject.getChild("Description", getRDFNamespace());

            if (description != null) {

                final String taxonomy = getTaxonomy(description);

                final List<Element> values = description.getChildren("value", getRDFNamespace());
                for (final Element value : values) {

                    final DCSubject subject = new DCSubjectImpl();
                    subject.setTaxonomyUri(taxonomy);
                    subject.setValue(value.getText());
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
     * @param elements the list of elements to parse.
     * @return the list of strings
     */
    protected final List<String> parseElementList(final List<Element> elements) {
        final List<String> values = new ArrayList<String>();
        for (final Element element : elements) {
            values.add(element.getText());
        }
        return values;
    }

    /**
     * Utility method to parse a list of dates out of a list of elements.
     * <p>
     *
     * @param elements the list of elements to parse.
     * @return the list of dates.
     */
    protected final List<Date> parseElementListDate(final List<Element> elements, final Locale locale) {
        final List<Date> values = new ArrayList<Date>();
        for (final Element element : elements) {
            values.add(DateParser.parseDate(element.getText(), locale));
        }
        return values;
    }

}
