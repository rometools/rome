/*
 * ContentModuleGenerator.java
 *
 * Created on January 11, 2005, 1:41 PM
 *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2005  Robert Cooper, Temple of the Screaming Penguin
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package org.rometools.feed.module.content.io;

import org.rometools.feed.module.content.ContentItem;
import org.rometools.feed.module.content.ContentModule;
import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Content;
import org.jdom.Element;
import org.jdom.Namespace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @version $Revision: 1.2 $
 * @author  <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class ContentModuleGenerator implements com.sun.syndication.io.ModuleGenerator {
    private static final Namespace CONTENT_NS = Namespace.getNamespace("content", ContentModule.URI);
    private static final Namespace RDF_NS = Namespace.getNamespace("rdf", ContentModule.RDF_URI);
    private static final Set NAMESPACES;

    static {
        Set nss = new HashSet();
        nss.add(CONTENT_NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    /** Creates a new instance of ContentModuleGenerator */
    public ContentModuleGenerator() {
    }

    public void generate(com.sun.syndication.feed.module.Module module, org.jdom.Element element) {
        // this is not necessary, it is done to avoid the namespace definition in every item.
        Element root = element;

        while ((root.getParent() != null) && root.getParent() instanceof Element) {
            root = (Element) root.getParent();
        }

        root.addNamespaceDeclaration(CONTENT_NS);

        if (!(module instanceof ContentModule)) {
            return;
        }

        ContentModule cm = (ContentModule) module;

        List encodeds = cm.getEncodeds();
        
        //
        if (encodeds != null) {
            System.out.println(cm.getEncodeds().size());
            for (int i = 0; i < encodeds.size(); i++) {
                element.addContent(generateCDATAElement("encoded", encodeds.get(i).toString()));
            }
        }

        List contentItems = cm.getContentItems();

        if ((contentItems != null) && (contentItems.size() > 0)) {
            Element items = new Element("items", CONTENT_NS);
            Element bag = new Element("Bag", RDF_NS);
            items.addContent(bag);

            for (int i = 0; i < contentItems.size(); i++) {
                ContentItem contentItem = (ContentItem) contentItems.get(i);
                Element li = new Element("li", RDF_NS);
                Element item = new Element("item", CONTENT_NS);

                if (contentItem.getContentAbout() != null) {
                    Attribute about = new Attribute("about", contentItem.getContentAbout(), RDF_NS);
                    item.setAttribute(about);
                }

                if (contentItem.getContentFormat() != null) {
                    //System.out.println( "Format");
                    Element format = new Element("format", CONTENT_NS);
                    Attribute formatResource = new Attribute("resource", contentItem.getContentFormat(), RDF_NS);
                    format.setAttribute(formatResource);

                    item.addContent(format);
                }

                if (contentItem.getContentEncoding() != null) {
                    //System.out.println( "Encoding");
                    Element encoding = new Element("encoding", CONTENT_NS);
                    Attribute encodingResource = new Attribute("resource", contentItem.getContentEncoding(), RDF_NS);
                    encoding.setAttribute(encodingResource);
                    item.addContent(encoding);
                }

                if (contentItem.getContentValue() != null) {
                    Element value = new Element("value", RDF_NS);

                    if (contentItem.getContentValueParseType() != null) {
                        Attribute parseType = new Attribute("parseType", contentItem.getContentValueParseType(), RDF_NS);
                        value.setAttribute(parseType);
                    }

                    if (contentItem.getContentValueNamespaces() != null) {
                        List namespaces = contentItem.getContentValueNamespaces();

                        for (int ni = 0; ni < namespaces.size(); ni++) {
                            value.addNamespaceDeclaration((Namespace) namespaces.get(ni));
                        }
                    }

                    List detached = new ArrayList();

                    for (int c = 0;
                            c < contentItem.getContentValueDOM().size(); c++) {
                        detached.add(((Content) ((Content) contentItem.getContentValueDOM().get(c)).clone()).detach());
                    }

                    value.setContent(detached);
                    item.addContent(value);
                } // end value

                li.addContent(item);
                bag.addContent(li);
            } //end contentItems loop

            element.addContent(items);
        }
    }

    protected Element generateSimpleElement(String name, String value) {
        Element element = new Element(name, CONTENT_NS);
        element.addContent(value);

        return element;
    }

    protected Element generateCDATAElement(String name, String value) {
        Element element = new Element(name, CONTENT_NS);
        CDATA cdata = new CDATA(value);
        element.addContent(cdata);

        return element;
    }

    public String getNamespaceUri() {
        return ContentModule.URI;
    }

    public java.util.Set getNamespaces() {
        return NAMESPACES;
    }
}
