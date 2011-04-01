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

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.DCModule;
import com.sun.syndication.feed.module.DCSubject;
import com.sun.syndication.io.ModuleGenerator;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;


/**
 * Feed Generator for DublinCore Module.
 * <p/>
 *
 * @author Elaine Chien
 *
 */
public class DCModuleGenerator implements ModuleGenerator {

    private static final String DC_URI  = "http://purl.org/dc/elements/1.1/";
    private static final String TAXO_URI = "http://purl.org/rss/1.0/modules/taxonomy/";
    private static final String RDF_URI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    private static final Namespace DC_NS  = Namespace.getNamespace("dc", DC_URI);
    private static final Namespace TAXO_NS = Namespace.getNamespace("taxo", TAXO_URI);
    private static final Namespace RDF_NS = Namespace.getNamespace("rdf", RDF_URI);

    private static final Set NAMESPACES;

    static {
        Set nss = new HashSet();
        nss.add(DC_NS);
        nss.add(TAXO_NS);
        nss.add(RDF_NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

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
     * Returns a set with all the URIs (JDOM Namespace elements) this module
     * generator uses.
     * <p/>
     * It is used by the the feed generators to add their namespace definition
     * in the root element of the generated document (forward-missing of
     * Java 5.0 Generics).
     * <p/>
     *
     * @return a set with all the URIs this module generator uses.
     */
    public final Set getNamespaces() {
        return NAMESPACES;
    }

    /**
     * Populate an element tree with elements for a module.
     * <p>
     * @param module the module to populate from.
     * @param element the root element to attach child elements to.
     */
    public final void generate(Module module, Element element) {
        DCModule dcModule = (DCModule) module;

        if (dcModule.getTitle() != null) {
            element.addContent(generateSimpleElementList("title", dcModule.getTitles()));
        }
        if (dcModule.getCreator() != null) {
            element.addContent(generateSimpleElementList("creator", dcModule.getCreators()));
        }
        List subjects = dcModule.getSubjects();
        for (int i = 0; i < subjects.size(); i++) {
            element.addContent(generateSubjectElement((DCSubject) subjects.get(i)));
        }
        if (dcModule.getDescription() != null) {
            element.addContent(generateSimpleElementList("description", dcModule.getDescriptions()));
        }
        if (dcModule.getPublisher() != null) {
            element.addContent(generateSimpleElementList("publisher", dcModule.getPublishers()));
        }
        if (dcModule.getContributors() != null) {
            element.addContent(generateSimpleElementList("contributor", dcModule.getContributors()));
        }
        if (dcModule.getDate() != null) {
            for (Iterator i = dcModule.getDates().iterator(); i.hasNext();) {
                element.addContent(generateSimpleElement("date",
                        DateParser.formatW3CDateTime((Date) i.next())));
            }
        }
        if (dcModule.getType() != null) {
            element.addContent(generateSimpleElementList("type", dcModule.getTypes()));
        }
        if (dcModule.getFormat() != null) {
            element.addContent(generateSimpleElementList("format", dcModule.getFormats()));
        }
        if (dcModule.getIdentifier() != null) {
            element.addContent(generateSimpleElementList("identifier", dcModule.getIdentifiers()));
        }
        if (dcModule.getSource() != null) {
            element.addContent(generateSimpleElementList("source", dcModule.getSources()));
        }
        if (dcModule.getLanguage() != null) {
            element.addContent(generateSimpleElementList("language", dcModule.getLanguages()));
        }
        if (dcModule.getRelation() != null) {
            element.addContent(generateSimpleElementList("relation", dcModule.getRelations()));
        }
        if (dcModule.getCoverage() != null) {
            element.addContent(generateSimpleElementList("coverage", dcModule.getCoverages()));
        }
        if (dcModule.getRights() != null) {
            element.addContent(generateSimpleElementList("rights", dcModule.getRightsList()));
        }
    }

    /**
     * Utility method to generate an element for a subject.
     * <p>
     * @param subject the subject to generate an element for.
     * @return the element for the subject.
     */
    protected final Element generateSubjectElement(DCSubject subject) {
        Element subjectElement = new Element("subject", getDCNamespace());

        if (subject.getTaxonomyUri() != null) {
            Element descriptionElement = new Element("Description", getRDFNamespace());
            Element topicElement = new Element("topic", getTaxonomyNamespace());
            Attribute resourceAttribute = new Attribute("resource", subject.getTaxonomyUri(), getRDFNamespace());
            topicElement.setAttribute(resourceAttribute);
            descriptionElement.addContent(topicElement);

            if (subject.getValue() != null) {
                Element valueElement = new Element("value", getRDFNamespace());
                valueElement.addContent(subject.getValue());
                descriptionElement.addContent(valueElement);
            }
            subjectElement.addContent(descriptionElement);
        } else {
            subjectElement.addContent(subject.getValue());
        }
        return subjectElement;
    }


    /**
     * Utility method to generate a single element containing a string.
     * <p>
     * @param name the name of the elment to generate.
     * @param value the value of the text in the element.
     * @return the element generated.
     */
    protected final Element generateSimpleElement(String name, String value)  {
        Element element = new Element(name, getDCNamespace());
        element.addContent(value);

        return element;
    }

    /**
     * Utility method to generate a list of simple elements.
     * <p>
     * @param name the name of the element list to generate.
     * @param value the list of values for the elements.
     * @return a list of Elements created.
     */
    protected final List generateSimpleElementList(String name, List value) {
        List elements = new ArrayList();
        for (Iterator i = value.iterator(); i.hasNext();) {
            elements.add(generateSimpleElement(name, (String) i.next()));
        }

        return elements;
    }
}
