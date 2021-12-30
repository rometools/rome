/*
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
import java.util.List;
import java.util.Locale;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.ProcessingInstruction;
import org.jdom2.filter.ContentFilter;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.module.Extendable;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.WireFeedParser;

public abstract class BaseWireFeedParser implements WireFeedParser {
    /**
     * [TYPE].feed.ModuleParser.classes= [className] ...
     *
     */
    private static final String FEED_MODULE_PARSERS_POSFIX_KEY = ".feed.ModuleParser.classes";

    /**
     * [TYPE].item.ModuleParser.classes= [className] ...
     *
     */
    private static final String ITEM_MODULE_PARSERS_POSFIX_KEY = ".item.ModuleParser.classes";

    /**
     * [TYPE].person.ModuleParser.classes= [className] ...
     *
     */
    private static final String PERSON_MODULE_PARSERS_POSFIX_KEY = ".person.ModuleParser.classes";

    private final String type;
    private final ModuleParsers feedModuleParsers;
    private final ModuleParsers itemModuleParsers;
    private final ModuleParsers personModuleParsers;
    private final Namespace namespace;

    protected BaseWireFeedParser(final String type, final Namespace namespace) {
        this.type = type;
        this.namespace = namespace;
        feedModuleParsers = new ModuleParsers(type + FEED_MODULE_PARSERS_POSFIX_KEY, this);
        itemModuleParsers = new ModuleParsers(type + ITEM_MODULE_PARSERS_POSFIX_KEY, this);
        personModuleParsers = new ModuleParsers(type + PERSON_MODULE_PARSERS_POSFIX_KEY, this);
    }

    /**
     * Returns the type of feed the parser handles.
     * <p>
     *
     * @see WireFeed for details on the format of this string.
     *      <p>
     * @return the type of feed the parser handles.
     *
     */
    @Override
    public String getType() {
        return type;
    }

    protected List<Module> parseFeedModules(final Element feedElement, final Locale locale) {
        return feedModuleParsers.parseModules(feedElement, locale);
    }

    protected List<Module> parseItemModules(final Element itemElement, final Locale locale) {
        return itemModuleParsers.parseModules(itemElement, locale);
    }

    protected List<Module> parsePersonModules(final Element itemElement, final Locale locale) {
        return personModuleParsers.parseModules(itemElement, locale);
    }

    protected List<Element> extractForeignMarkup(final Element e, final Extendable ext, final Namespace namespace) {

        final ArrayList<Element> foreignElements = new ArrayList<Element>();

        for (final Element element : e.getChildren()) {
            if (!namespace.equals(element.getNamespace()) && ext.getModule(element.getNamespaceURI()) == null) {
                // if element not in the RSS namespace and elem was not handled by a module save it
                // as foreign markup but we can't detach it while we're iterating
                foreignElements.add(element.clone());
            }
        }

        // now we can detach the foreign markup elements
        for (final Element foreignElement : foreignElements) {
            foreignElement.detach();
        }

        return foreignElements;

    }

    protected Attribute getAttribute(final Element e, final String attributeName) {
        Attribute attribute = e.getAttribute(attributeName);
        if (attribute == null) {
            attribute = e.getAttribute(attributeName, namespace);
        }
        return attribute;
    }

    protected String getAttributeValue(final Element e, final String attributeName) {
        final Attribute attr = getAttribute(e, attributeName);
        if (attr != null) {
            return attr.getValue();
        } else {
            return null;
        }
    }

    protected String getStyleSheet(final Document doc) {
        String styleSheet = null;
        for (final Content c : doc.getContent(new ContentFilter(ContentFilter.PI))) {
            final ProcessingInstruction pi = (ProcessingInstruction) c;
            if ("text/xsl".equals(pi.getPseudoAttributeValue("type"))) {
                styleSheet = pi.getPseudoAttributeValue("href");
                break;
            }
        }
        return styleSheet;
    }
}
