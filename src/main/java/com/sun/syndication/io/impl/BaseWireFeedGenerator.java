package com.sun.syndication.io.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.Parent;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.WireFeedGenerator;

/**
 * @author Alejandro Abdelnur
 */
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
        Iterator<Namespace> i = feedModuleGenerators.getAllNamespaces().iterator();
        while (i.hasNext()) {
            allModuleNamespaces.add(i.next());
        }
        i = itemModuleGenerators.getAllNamespaces().iterator();
        while (i.hasNext()) {
            allModuleNamespaces.add(i.next());
        }
        i = personModuleGenerators.getAllNamespaces().iterator();
        while (i.hasNext()) {
            allModuleNamespaces.add(i.next());
        }
        this.allModuleNamespaces = new Namespace[allModuleNamespaces.size()];
        allModuleNamespaces.toArray(this.allModuleNamespaces);
    }

    @Override
    public String getType() {
        return type;
    }

    protected void generateModuleNamespaceDefs(final Element root) {
        for (final Namespace _allModuleNamespace : allModuleNamespaces) {
            root.addNamespaceDeclaration(_allModuleNamespace);
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

    protected void generateForeignMarkup(final Element e, final List<Element> foreignMarkup) {
        if (foreignMarkup != null) {
            final Iterator<Element> elems = foreignMarkup.iterator();
            while (elems.hasNext()) {
                final Element elem = (Element) elems.next();
                final Parent parent = elem.getParent();
                if (parent != null) {
                    parent.removeContent(elem);
                }
                e.addContent(elem);
            }
        }
    }

    /**
     * Purging unused declarations is less optimal, performance-wise, than never
     * adding them in the first place. So, we should still ask the ROME guys to
     * fix their code (not adding dozens of unnecessary module declarations).
     * Having said that: purging them here, before XML generation, is more
     * efficient than parsing and re-molding the XML after ROME generates it.
     * <p/>
     * Note that the calling app could still add declarations/modules to the
     * Feed tree after this. Which is fine. But those modules are then
     * responsible for crawling to the root of the tree, at generate() time, to
     * make sure their namespace declarations are present.
     */
    protected static void purgeUnusedNamespaceDeclarations(final Element root) {
        final Set<String> usedPrefixes = new HashSet<String>();
        collectUsedPrefixes(root, usedPrefixes);

        final List<Namespace> list = root.getAdditionalNamespaces();
        final List<Namespace> additionalNamespaces = new ArrayList<Namespace>();
        additionalNamespaces.addAll(list); // the duplication will prevent a
                                           // ConcurrentModificationException
                                           // below

        for (int i = 0; i < additionalNamespaces.size(); i++) {
            final Namespace ns = additionalNamespaces.get(i);
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
        for (int i = 0; i < kids.size(); i++) {
            collectUsedPrefixes((Element) kids.get(i), collector); // recursion
                                                                   // - worth it
        }
    }

}
