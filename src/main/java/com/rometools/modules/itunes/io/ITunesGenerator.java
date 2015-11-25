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
package com.rometools.modules.itunes.io;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;

import com.rometools.modules.itunes.AbstractITunesObject;
import com.rometools.modules.itunes.EntryInformationImpl;
import com.rometools.modules.itunes.FeedInformationImpl;
import com.rometools.modules.itunes.types.Category;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;

/**
 * @version $Revision: 1.3 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class ITunesGenerator implements ModuleGenerator {

    private static final HashSet<Namespace> NAMESPACES = new HashSet<Namespace>();
    private static final Namespace NAMESPACE = Namespace.getNamespace(AbstractITunesObject.PREFIX, AbstractITunesObject.URI);

    static {
        NAMESPACES.add(NAMESPACE);
    }

    /** Creates a new instance of ITunesGenerator */
    public ITunesGenerator() {
    }

    @Override
    public void generate(final Module module, final Element element) {
        Element root = element;

        while (root.getParent() != null && root.getParent() instanceof Element) {
            root = (Element) root.getParent();
        }

        root.addNamespaceDeclaration(NAMESPACE);

        if (!(module instanceof AbstractITunesObject)) {
            return;
        }

        final AbstractITunesObject itunes = (AbstractITunesObject) module;

        if (itunes instanceof FeedInformationImpl) {
            // Do Channel Specific Stuff.
            final FeedInformationImpl info = (FeedInformationImpl) itunes;
            final Element owner = generateSimpleElement("owner", "");
            final Element email = generateSimpleElement("email", info.getOwnerEmailAddress());
            owner.addContent(email);

            final Element name = generateSimpleElement("name", info.getOwnerName());
            owner.addContent(name);
            element.addContent(owner);

            if (info.getImage() != null) {
                final Element image = generateSimpleElement("image", "");
                image.setAttribute("href", info.getImage().toExternalForm());
                element.addContent(image);
            }

            final List<Category> categories = info.getCategories();
            for (final Category cat : categories) {

                final Element category = generateSimpleElement("category", "");
                category.setAttribute("text", cat.getName());

                if (cat.getSubcategory() != null) {
                    final Element subcat = generateSimpleElement("category", "");
                    subcat.setAttribute("text", cat.getSubcategory().getName());
                    category.addContent(subcat);
                }

                element.addContent(category);
            }
        } else if (itunes instanceof EntryInformationImpl) {
            final EntryInformationImpl info = (EntryInformationImpl) itunes;

            if (info.getDuration() != null) {
                element.addContent(generateSimpleElement("duration", info.getDuration().toString()));
            }
            if (info.getClosedCaptioned()) {
                element.addContent(generateSimpleElement("isClosedCaptioned", "yes"));
            }
            if (info.getOrder() != null) {
                element.addContent(generateSimpleElement("order", info.getOrder().toString()));
            }
        }

        if (itunes.getAuthor() != null) {
            element.addContent(generateSimpleElement("author", itunes.getAuthor()));
        }

        if (itunes.getBlock()) {
            element.addContent(generateSimpleElement("block", ""));
        }

        if (itunes.getExplicit()) {
            element.addContent(generateSimpleElement("explicit", "yes"));
        } else {
            element.addContent(generateSimpleElement("explicit", "no"));
        }

        if (itunes.getKeywords() != null) {
            final StringBuffer sb = new StringBuffer();

            for (int i = 0; i < itunes.getKeywords().length; i++) {
                if (i != 0) {
                    sb.append(", ");
                }

                sb.append(itunes.getKeywords()[i]);
            }

            element.addContent(generateSimpleElement("keywords", sb.toString()));
        }

        if (itunes.getSubtitle() != null) {
            element.addContent(generateSimpleElement("subtitle", itunes.getSubtitle()));
        }

        if (itunes.getSummary() != null) {
            element.addContent(generateSimpleElement("summary", itunes.getSummary()));
        }
    }

    /**
     * Returns the list of namespaces this module uses.
     *
     * @return set of Namespace objects.
     */
    @Override
    public Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    /**
     * Returns the namespace URI this module handles.
     *
     * @return Returns the namespace URI this module handles.
     */
    @Override
    public String getNamespaceUri() {
        return AbstractITunesObject.URI;
    }

    protected Element generateSimpleElement(final String name, final String value) {
        final Element element = new Element(name, NAMESPACE);
        element.addContent(value);

        return element;
    }
}
