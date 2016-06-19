/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
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
 *
 */
package com.rometools.modules.content.io;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdom2.Attribute;
import org.jdom2.CDATA;
import org.jdom2.Content;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.content.ContentItem;
import com.rometools.modules.content.ContentModule;

public class ContentModuleGenerator implements com.rometools.rome.io.ModuleGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(ContentModuleGenerator.class);

    private static final Namespace CONTENT_NS = Namespace.getNamespace("content", ContentModule.URI);
    private static final Namespace RDF_NS = Namespace.getNamespace("rdf", ContentModule.RDF_URI);
    private static final Set<Namespace> NAMESPACES;

    static {
        final Set<Namespace> nss = new HashSet<Namespace>();
        nss.add(CONTENT_NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    public ContentModuleGenerator() {
    }

    @Override
    public void generate(final com.rometools.rome.feed.module.Module module, final org.jdom2.Element element) {
        // this is not necessary, it is done to avoid the namespace definition in every item.
        Element root = element;

        while (root.getParent() != null && root.getParent() instanceof Element) {
            root = (Element) root.getParent();
        }

        root.addNamespaceDeclaration(CONTENT_NS);

        if (!(module instanceof ContentModule)) {
            return;
        }

        final ContentModule cm = (ContentModule) module;

        final List<String> encodeds = cm.getEncodeds();

        if (encodeds != null) {
            LOG.debug("{}", cm.getEncodeds().size());
            for (int i = 0; i < encodeds.size(); i++) {
                element.addContent(generateCDATAElement("encoded", encodeds.get(i).toString()));
            }
        }

        final List<ContentItem> contentItems = cm.getContentItems();

        if (contentItems != null && !contentItems.isEmpty()) {
            final Element items = new Element("items", CONTENT_NS);
            final Element bag = new Element("Bag", RDF_NS);
            items.addContent(bag);

            for (int i = 0; i < contentItems.size(); i++) {
                final ContentItem contentItem = contentItems.get(i);
                final Element li = new Element("li", RDF_NS);
                final Element item = new Element("item", CONTENT_NS);

                if (contentItem.getContentAbout() != null) {
                    final Attribute about = new Attribute("about", contentItem.getContentAbout(), RDF_NS);
                    item.setAttribute(about);
                }

                if (contentItem.getContentFormat() != null) {
                    final Element format = new Element("format", CONTENT_NS);
                    final Attribute formatResource = new Attribute("resource", contentItem.getContentFormat(), RDF_NS);
                    format.setAttribute(formatResource);

                    item.addContent(format);
                }

                if (contentItem.getContentEncoding() != null) {
                    final Element encoding = new Element("encoding", CONTENT_NS);
                    final Attribute encodingResource = new Attribute("resource", contentItem.getContentEncoding(), RDF_NS);
                    encoding.setAttribute(encodingResource);
                    item.addContent(encoding);
                }

                if (contentItem.getContentValue() != null) {
                    final Element value = new Element("value", RDF_NS);

                    if (contentItem.getContentValueParseType() != null) {
                        final Attribute parseType = new Attribute("parseType", contentItem.getContentValueParseType(), RDF_NS);
                        value.setAttribute(parseType);
                    }

                    if (contentItem.getContentValueNamespaces() != null) {
                        final List<Namespace> namespaces = contentItem.getContentValueNamespaces();

                        for (int ni = 0; ni < namespaces.size(); ni++) {
                            value.addNamespaceDeclaration(namespaces.get(ni));
                        }
                    }

                    final List<Content> detached = new ArrayList<Content>();

                    for (int c = 0; c < contentItem.getContentValueDOM().size(); c++) {
                        detached.add(contentItem.getContentValueDOM().get(c).clone().detach());
                    }

                    value.setContent(detached);
                    item.addContent(value);
                } // end value

                li.addContent(item);
                bag.addContent(li);
            } // end contentItems loop

            element.addContent(items);
        }
    }

    protected Element generateSimpleElement(final String name, final String value) {
        final Element element = new Element(name, CONTENT_NS);
        element.addContent(value);

        return element;
    }

    protected Element generateCDATAElement(final String name, final String value) {
        final Element element = new Element(name, CONTENT_NS);
        final CDATA cdata = new CDATA(value);
        element.addContent(cdata);

        return element;
    }

    @Override
    public String getNamespaceUri() {
        return ContentModule.URI;
    }

    @Override
    public Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }
}
