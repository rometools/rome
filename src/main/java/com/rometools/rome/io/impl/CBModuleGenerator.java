/*
 * Copyright 2015 MetricStream, Inc.
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.rome.feed.module.CBModule;
import com.rometools.rome.feed.module.CBSpeech;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;

/**
 * Feed Generator for CB Module.
 * <p/>
 *
 * @author Manish SV Kumar
 *
 */
public class CBModuleGenerator implements ModuleGenerator {

    private static final String CB_URI = "http://www.cbwiki.net/wiki/index.php/Specification_1.1";

    private static final Namespace CB_NS = Namespace.getNamespace("cb", CB_URI);

    private static final Set<Namespace> NAMESPACES;

    static {
        final Set<Namespace> nss = new HashSet<Namespace>();
        nss.add(CB_NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    @Override
    public final String getNamespaceUri() {
        return CB_URI;
    }

    private final Namespace getCBNamespace() {
        return CB_NS;
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

        final CBModule cbModule = (CBModule) module;

        final CBSpeech speech = cbModule.getSpeech();
        	element.addContent(generateSpeechElement(speech));
    }

    /**
     * Utility method to generate an element for a speech.
     * <p>
     *
     * @param speech the speech to generate an element for.
     * @return the element for the speech.
     */
    protected final Element generateSpeechElement(final CBSpeech speech) {

        final Element speechElement = new Element("speech", getCBNamespace());
        final String simpleTitle = speech.getSimpleTitle();

        if (simpleTitle != null) {
        	final Element simpleTitleElement = new Element("simpleTitle", getCBNamespace());
        	simpleTitleElement.addContent(simpleTitle);
        	speechElement.addContent(simpleTitleElement);
        }
        return speechElement;
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
        final Element element = new Element(name, getCBNamespace());
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
