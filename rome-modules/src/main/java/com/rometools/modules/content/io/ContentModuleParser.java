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
import java.util.List;
import java.util.Locale;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.XMLOutputter;

import com.rometools.modules.content.ContentItem;
import com.rometools.modules.content.ContentModule;
import com.rometools.modules.content.ContentModuleImpl;

public class ContentModuleParser implements com.rometools.rome.io.ModuleParser {
    private static final Namespace CONTENT_NS = Namespace.getNamespace("content", ContentModule.URI);
    private static final Namespace RDF_NS = Namespace.getNamespace("rdf", ContentModule.RDF_URI);

    public ContentModuleParser() {
    }

    @Override
    public String getNamespaceUri() {
        return ContentModule.URI;
    }

    @Override
    public com.rometools.rome.feed.module.Module parse(final Element element, final Locale locale) {
        boolean foundSomething = false;
        final ContentModule cm = new ContentModuleImpl();
        final List<Element> encodeds = element.getChildren("encoded", CONTENT_NS);
        final ArrayList<String> contentStrings = new ArrayList<String>();
        final ArrayList<String> encodedStrings = new ArrayList<String>();

        if (!encodeds.isEmpty()) {
            foundSomething = true;

            for (int i = 0; i < encodeds.size(); i++) {
                final Element encodedElement = encodeds.get(i);
                encodedStrings.add(encodedElement.getText());
                contentStrings.add(encodedElement.getText());
            }
        }

        final ArrayList<ContentItem> contentItems = new ArrayList<ContentItem>();
        final List<Element> items = element.getChildren("items", CONTENT_NS);

        for (int i = 0; i < items.size(); i++) {
            foundSomething = true;

            final List<Element> lis = items.get(i).getChild("Bag", RDF_NS).getChildren("li", RDF_NS);

            for (int j = 0; j < lis.size(); j++) {
                final ContentItem ci = new ContentItem();
                final Element li = lis.get(j);
                final Element item = li.getChild("item", CONTENT_NS);
                final Element format = item.getChild("format", CONTENT_NS);
                final Element encoding = item.getChild("encoding", CONTENT_NS);
                final Element value = item.getChild("value", RDF_NS);

                if (value != null) {
                    if (value.getAttributeValue("parseType", RDF_NS) != null) {
                        ci.setContentValueParseType(value.getAttributeValue("parseType", RDF_NS));
                    }

                    if (ci.getContentValueParseType() != null && ci.getContentValueParseType().equals("Literal")) {
                        ci.setContentValue(getXmlInnerText(value));
                        contentStrings.add(getXmlInnerText(value));
                        ci.setContentValueNamespaces(value.getAdditionalNamespaces());
                    } else {
                        ci.setContentValue(value.getText());
                        contentStrings.add(value.getText());
                    }

                    ci.setContentValueDOM(value.clone().getContent());
                }

                if (format != null) {
                    ci.setContentFormat(format.getAttribute("resource", RDF_NS).getValue());
                }

                if (encoding != null) {
                    ci.setContentEncoding(encoding.getAttribute("resource", RDF_NS).getValue());
                }

                if (item != null) {
                    final Attribute about = item.getAttribute("about", RDF_NS);

                    if (about != null) {
                        ci.setContentAbout(about.getValue());
                    }
                }

                contentItems.add(ci);
            }
        }

        cm.setEncodeds(encodedStrings);
        cm.setContentItems(contentItems);
        cm.setContents(contentStrings);

        return foundSomething ? cm : null;
    }

    protected String getXmlInnerText(final Element e) {
        final StringBuffer sb = new StringBuffer();
        final XMLOutputter xo = new XMLOutputter();
        final List<Content> children = e.getContent();
        sb.append(xo.outputString(children));

        return sb.toString();
    }
}
