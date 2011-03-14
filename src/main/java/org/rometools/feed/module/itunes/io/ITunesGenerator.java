/*
 * ITunesGenerator.java
 *
 * Created on August 1, 2005, 10:44 PM
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
 */
package org.rometools.feed.module.itunes.io;

import com.sun.syndication.feed.module.Module;
import org.rometools.feed.module.itunes.AbstractITunesObject;
import org.rometools.feed.module.itunes.EntryInformationImpl;
import org.rometools.feed.module.itunes.FeedInformationImpl;
import org.rometools.feed.module.itunes.types.Category;
import com.sun.syndication.io.ModuleGenerator;

import org.jdom.Element;
import org.jdom.Namespace;

import java.util.HashSet;
import java.util.Iterator;

/**
 * @version $Revision: 1.3 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class ITunesGenerator implements ModuleGenerator {
    private static final HashSet SET = new HashSet();
    private static final Namespace NS = Namespace.getNamespace(AbstractITunesObject.PREFIX, AbstractITunesObject.URI);

    static {
        SET.add(NS);
    }

    /** Creates a new instance of ITunesGenerator */
    public ITunesGenerator() {
    }

    public void generate(Module module, Element element) {
        Element root = element;

        while ((root.getParent() != null) && root.getParent() instanceof Element) {
            root = (Element) root.getParent();
        }

        root.addNamespaceDeclaration(NS);

        if (!(module instanceof AbstractITunesObject)) {
            return;
        }

        AbstractITunesObject itunes = (AbstractITunesObject) module;

        if (itunes instanceof FeedInformationImpl) {
            //Do Channel Specific Stuff.
            FeedInformationImpl info = (FeedInformationImpl) itunes;
            Element owner = this.generateSimpleElement("owner", "");
            Element email = this.generateSimpleElement("email", info.getOwnerEmailAddress());
            owner.addContent(email);

            Element name = this.generateSimpleElement("name", info.getOwnerName());
            owner.addContent(name);
            element.addContent(owner);

            if (info.getImage() != null) {
                Element image = this.generateSimpleElement("image", "");
                image.setAttribute("href", info.getImage().toExternalForm());
                element.addContent(image);
            }

            for (Iterator it = info.getCategories().iterator(); it.hasNext();) {
        	Category cat = (Category) it.next();
                Element category = this.generateSimpleElement("category", "");
                category.setAttribute("text", cat.getName());

                if (cat.getSubcategory() != null) {
                    Element subcat = this.generateSimpleElement("category", "");
                    subcat.setAttribute("text", cat.getSubcategory().getName());
                    category.addContent(subcat);
                }

                element.addContent(category);
            }
        } else if (itunes instanceof EntryInformationImpl) {
            EntryInformationImpl info = (EntryInformationImpl) itunes;

            if (info.getDuration() != null) {
                element.addContent(this.generateSimpleElement("duration", info.getDuration().toString()));
            }
        }

        if (itunes.getAuthor() != null) {
            element.addContent(this.generateSimpleElement("author", itunes.getAuthor()));
        }

        if (itunes.getBlock()) {
            element.addContent(this.generateSimpleElement("block", ""));
        }

        if (itunes.getExplicit()) {
            element.addContent(this.generateSimpleElement("explicit", "yes"));
        } else {
            element.addContent(this.generateSimpleElement("explicit", "no"));
        }

        if (itunes.getKeywords() != null) {
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < itunes.getKeywords().length; i++) {
                if (i != 0) {
                    sb.append(", ");
                }

                sb.append(itunes.getKeywords()[i]);
            }

            element.addContent(this.generateSimpleElement("keywords", sb.toString()));
        }

        if (itunes.getSubtitle() != null) {
            element.addContent(this.generateSimpleElement("subtitle", itunes.getSubtitle()));
        }

        if (itunes.getSummary() != null) {
            element.addContent(this.generateSimpleElement("summary", itunes.getSummary()));
        }
    }

    /** Returns the list of namespaces this module uses.
     * @return set of Namespace objects.
     */
    public java.util.Set getNamespaces() {
        return SET;
    }

    /** Returns the namespace URI this module handles.
     * @return Returns the namespace URI this module handles.
     */
    public String getNamespaceUri() {
        return AbstractITunesObject.URI;
    }

    protected Element generateSimpleElement(String name, String value) {
        Element element = new Element(name, NS);
        element.addContent(value);

        return element;
    }
}
