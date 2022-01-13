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
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.module.DCModule;
import com.rometools.rome.feed.module.DCSubject;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;

/**
 * Feed Generator for DublinCore Module.
 * <p/>
 */
public class DCModuleGenerator implements ModuleGenerator {

    private static final String DC_URI = "http://purl.org/dc/elements/1.1/";
    private static final String TAXO_URI = "http://purl.org/rss/1.0/modules/taxonomy/";
    private static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    private static final Namespace DC_NS = Namespace.getNamespace("dc", DC_URI);
    private static final Namespace TAXO_NS = Namespace.getNamespace("taxo", TAXO_URI);
    private static final Namespace RDF_NS = Namespace.getNamespace("rdf", RDF_URI);

    private static final Set<Namespace> NAMESPACES;

    static {
        final Set<Namespace> nss = new HashSet<Namespace>();
        nss.add(DC_NS);
        nss.add(TAXO_NS);
        nss.add(RDF_NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    @Override
    public final String getNamespaceUri() {
        return DC_URI;
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
     * Returns a set with all the URIs (JDOM Namespace elements) this module generator uses.
     * <p/>
     * It is used by the the feed generators to add their namespace definition in the root element
     * of the generated document (forward-missing of Java 5.0 Generics).
     * <p/>
     *
     * @return a set with all the URIs this module generator uses.
     */
    @Override
    public final Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    /**
     * Populate an element tree with elements for a module.
     * <p>
     *
     * @param module the module to populate from.
     * @param element the root element to attach child elements to.
     */
    @Override
    public final void generate(final Module module, final Element element) {

        final DCModule dcModule = (DCModule) module;

        final String title = dcModule.getTitle();
        if (title != null) {
            element.addContent(generateSimpleElementList("title", dcModule.getTitles()));
        }

        final String creator = dcModule.getCreator();
        if (creator != null) {
            element.addContent(generateSimpleElementList("creator", dcModule.getCreators()));
        }

        final List<DCSubject> subjects = dcModule.getSubjects();
        for (final DCSubject dcSubject : subjects) {
            element.addContent(generateSubjectElement(dcSubject));
        }

        final String description = dcModule.getDescription();
        if (description != null) {
            element.addContent(generateSimpleElementList("description", dcModule.getDescriptions()));
        }

        final String publisher = dcModule.getPublisher();
        if (publisher != null) {
            element.addContent(generateSimpleElementList("publisher", dcModule.getPublishers()));
        }

        final List<String> contributors = dcModule.getContributors();
        if (contributors != null) {
            element.addContent(generateSimpleElementList("contributor", contributors));
        }

        final Date dcDate = dcModule.getDate();
        if (dcDate != null) {
            for (final Date date : dcModule.getDates()) {
                element.addContent(generateSimpleElement("date", DateParser.formatW3CDateTime(date, Locale.US)));
            }
        }

        final String type = dcModule.getType();
        if (type != null) {
            element.addContent(generateSimpleElementList("type", dcModule.getTypes()));
        }

        final String format = dcModule.getFormat();
        if (format != null) {
            element.addContent(generateSimpleElementList("format", dcModule.getFormats()));
        }

        final String identifier = dcModule.getIdentifier();
        if (identifier != null) {
            element.addContent(generateSimpleElementList("identifier", dcModule.getIdentifiers()));
        }

        final String source = dcModule.getSource();
        if (source != null) {
            element.addContent(generateSimpleElementList("source", dcModule.getSources()));
        }

        final String language = dcModule.getLanguage();
        if (language != null) {
            element.addContent(generateSimpleElementList("language", dcModule.getLanguages()));
        }

        final String relation = dcModule.getRelation();
        if (relation != null) {
            element.addContent(generateSimpleElementList("relation", dcModule.getRelations()));
        }

        final String coverage = dcModule.getCoverage();
        if (coverage != null) {
            element.addContent(generateSimpleElementList("coverage", dcModule.getCoverages()));
        }

        final String rights = dcModule.getRights();
        if (rights != null) {
            element.addContent(generateSimpleElementList("rights", dcModule.getRightsList()));
        }

    }

    /**
     * Utility method to generate an element for a subject.
     * <p>
     *
     * @param subject the subject to generate an element for.
     * @return the element for the subject.
     */
    protected final Element generateSubjectElement(final DCSubject subject) {

        final Element subjectElement = new Element("subject", getDCNamespace());

        final String taxonomyUri = subject.getTaxonomyUri();
        final String value = subject.getValue();

        if (taxonomyUri != null) {

            final Attribute resourceAttribute = new Attribute("resource", taxonomyUri, getRDFNamespace());

            final Element topicElement = new Element("topic", getTaxonomyNamespace());
            topicElement.setAttribute(resourceAttribute);

            final Element descriptionElement = new Element("Description", getRDFNamespace());
            descriptionElement.addContent(topicElement);

            if (value != null) {
                final Element valueElement = new Element("value", getRDFNamespace());
                valueElement.addContent(value);
                descriptionElement.addContent(valueElement);
            }

            subjectElement.addContent(descriptionElement);

        } else {
            subjectElement.addContent(value);
        }

        return subjectElement;
    }

    /**
     * Utility method to generate a single element containing a string.
     * <p>
     *
     * @param name the name of the elment to generate.
     * @param value the value of the text in the element.
     * @return the element generated.
     */
    protected final Element generateSimpleElement(final String name, final String value) {
        final Element element = new Element(name, getDCNamespace());
        element.addContent(value);
        return element;
    }

    /**
     * Utility method to generate a list of simple elements.
     * <p>
     *
     * @param name the name of the element list to generate.
     * @param values the list of values for the elements.
     * @return a list of Elements created.
     */
    protected final List<Element> generateSimpleElementList(final String name, final List<String> values) {
        final List<Element> elements = new ArrayList<Element>();
        for (final String value : values) {
            elements.add(generateSimpleElement(name, value));
        }
        return elements;
    }
}
