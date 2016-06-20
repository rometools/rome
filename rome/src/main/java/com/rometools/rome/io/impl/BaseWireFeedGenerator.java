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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.Parent;

import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.WireFeedGenerator;

public abstract class BaseWireFeedGenerator implements WireFeedGenerator {

    /**
     * [TYPE].feed.ModuleParser.classes= [className] ...
     */
    private static final String FEED_MODULE_GENERATORS_POSFIX_KEY = ".feed.ModuleGenerator.classes";

    /**
     * [TYPE].item.ModuleParser.classes= [className] ...
     */
    private static final String ITEM_MODULE_GENERATORS_POSFIX_KEY = ".item.ModuleGenerator.classes";

    /**
     * [TYPE].person.ModuleParser.classes= [className] ...
     */
    private static final String PERSON_MODULE_GENERATORS_POSFIX_KEY = ".person.ModuleGenerator.classes";

    private final String type;
    private final ModuleGenerators feedModuleGenerators;
    private final ModuleGenerators itemModuleGenerators;
    private final ModuleGenerators personModuleGenerators;
    private final Namespace[] allModuleNamespaces;

    protected BaseWireFeedGenerator(final String type) {

        this.type = type;

        feedModuleGenerators = new ModuleGenerators(type + FEED_MODULE_GENERATORS_POSFIX_KEY, this);
        itemModuleGenerators = new ModuleGenerators(type + ITEM_MODULE_GENERATORS_POSFIX_KEY, this);
        personModuleGenerators = new ModuleGenerators(type + PERSON_MODULE_GENERATORS_POSFIX_KEY, this);

        final Set<Namespace> allModuleNamespaces = new HashSet<Namespace>();

        for (final Namespace namespace : feedModuleGenerators.getAllNamespaces()) {
            allModuleNamespaces.add(namespace);
        }

        for (final Namespace namespace : itemModuleGenerators.getAllNamespaces()) {
            allModuleNamespaces.add(namespace);
        }

        for (final Namespace namespace : personModuleGenerators.getAllNamespaces()) {
            allModuleNamespaces.add(namespace);
        }

        this.allModuleNamespaces = new Namespace[allModuleNamespaces.size()];

        allModuleNamespaces.toArray(this.allModuleNamespaces);

    }

    @Override
    public String getType() {
        return type;
    }

    protected void generateModuleNamespaceDefs(final Element root) {
        for (final Namespace allModuleNamespace : allModuleNamespaces) {
            root.addNamespaceDeclaration(allModuleNamespace);
        }
    }

    protected void generateFeedModules(final List<Module> modules, final Element feed) {
        feedModuleGenerators.generateModules(modules, feed);
    }

    public void generateItemModules(final List<Module> modules, final Element item) {
        itemModuleGenerators.generateModules(modules, item);
    }

    public void generatePersonModules(final List<Module> modules, final Element person) {
        personModuleGenerators.generateModules(modules, person);
    }

    protected void generateForeignMarkup(final Element element, final List<Element> foreignElements) {
        if (foreignElements != null) {
            for (final Element foreignElement : foreignElements) {
                final Parent parent = foreignElement.getParent();
                if (parent != null) {
                    parent.removeContent(foreignElement);
                }
                element.addContent(foreignElement);
            }
        }
    }

    /**
     * Purging unused declarations is less optimal, performance-wise, than never adding them in the
     * first place. So, we should still ask the ROME guys to fix their code (not adding dozens of
     * unnecessary module declarations). Having said that: purging them here, before XML generation,
     * is more efficient than parsing and re-molding the XML after ROME generates it.
     * <p/>
     * Note that the calling app could still add declarations/modules to the Feed tree after this.
     * Which is fine. But those modules are then responsible for crawling to the root of the tree,
     * at generate() time, to make sure their namespace declarations are present.
     */
    protected static void purgeUnusedNamespaceDeclarations(final Element root) {

        final Set<String> usedPrefixes = new HashSet<String>();
        collectUsedPrefixes(root, usedPrefixes);

        final List<Namespace> list = root.getAdditionalNamespaces();
        final List<Namespace> additionalNamespaces = new ArrayList<Namespace>();
        additionalNamespaces.addAll(list); // the duplication will prevent a
        // ConcurrentModificationException
        // below

        for (final Namespace ns : additionalNamespaces) {
            final String prefix = ns.getPrefix();
            if (prefix != null && prefix.length() > 0 && !usedPrefixes.contains(prefix)) {
                root.removeNamespaceDeclaration(ns);
            }
        }

    }

    private static void collectUsedPrefixes(final Element el, final Set<String> collector) {

        final String prefix = el.getNamespacePrefix();
        if (prefix != null && prefix.length() > 0 && !collector.contains(prefix)) {
            collector.add(prefix);
        }

        final List<Element> kids = el.getChildren();
        for (final Element kid : kids) {
            // recursion- worth it
            collectUsedPrefixes(kid, collector);
        }

    }

}
