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
 */
package com.rometools.modules.itunes.io;

import com.rometools.modules.itunes.types.Subcategory;
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

public class ITunesGenerator implements ModuleGenerator {

    private static final HashSet<Namespace> NAMESPACES = new HashSet<Namespace>();
    private static final Namespace NAMESPACE = Namespace.getNamespace(AbstractITunesObject.PREFIX, AbstractITunesObject.URI);

    static {
        NAMESPACES.add(NAMESPACE);
    }

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

            final List<Category> categories = info.getCategories();
            for (final Category cat : categories) {

                final Element category = generateSimpleElement("category", "");
                category.setAttribute("text", cat.getName());

                for (Subcategory subcategory : cat.getSubcategories()) {
                    final Element subcat = generateSimpleElement("category", "");
                    subcat.setAttribute("text", subcategory.getName());
                    category.addContent(subcat);
                }

                element.addContent(category);
            }

            if (info.getType() != null) {
                element.addContent(generateSimpleElement("type",info.getType()));
            }

            if (info.getComplete()) {
                element.addContent(generateSimpleElement("complete", "yes"));
            }

            if (info.getNewFeedUrl() != null) {
                element.addContent(generateSimpleElement("new-feed-url", info.getNewFeedUrl()));
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
            if (info.getEpisodeType() != null) {
                element.addContent(generateSimpleElement("episodeType", info.getEpisodeType()));
            }
            if (info.getSeason() != null && info.getSeason() > 0) {
                element.addContent(generateSimpleElement("season", info.getSeason().toString()));
            }
            if (info.getEpisode() != null && info.getEpisode() > 0) {
                element.addContent(generateSimpleElement("episode", info.getEpisode().toString()));
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

        if (itunes.getImage() != null) {
            final Element image = generateSimpleElement("image", "");
            image.setAttribute("href", itunes.getImage().toExternalForm());
            element.addContent(image);
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
