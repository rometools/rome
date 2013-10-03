package com.sun.syndication.io.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.Parent;

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

    private final String _type;
    private final ModuleGenerators _feedModuleGenerators;
    private final ModuleGenerators _itemModuleGenerators;
    private final ModuleGenerators _personModuleGenerators;
    private final Namespace[] _allModuleNamespaces;

    protected BaseWireFeedGenerator(final String type) {
        this._type = type;
        this._feedModuleGenerators = new ModuleGenerators(type + FEED_MODULE_GENERATORS_POSFIX_KEY, this);
        this._itemModuleGenerators = new ModuleGenerators(type + ITEM_MODULE_GENERATORS_POSFIX_KEY, this);
        this._personModuleGenerators = new ModuleGenerators(type + PERSON_MODULE_GENERATORS_POSFIX_KEY, this);
        final Set allModuleNamespaces = new HashSet();
        Iterator i = this._feedModuleGenerators.getAllNamespaces().iterator();
        while (i.hasNext()) {
            allModuleNamespaces.add(i.next());
        }
        i = this._itemModuleGenerators.getAllNamespaces().iterator();
        while (i.hasNext()) {
            allModuleNamespaces.add(i.next());
        }
        i = this._personModuleGenerators.getAllNamespaces().iterator();
        while (i.hasNext()) {
            allModuleNamespaces.add(i.next());
        }
        this._allModuleNamespaces = new Namespace[allModuleNamespaces.size()];
        allModuleNamespaces.toArray(this._allModuleNamespaces);
    }

    @Override
    public String getType() {
        return this._type;
    }

    protected void generateModuleNamespaceDefs(final Element root) {
        for (final Namespace _allModuleNamespace : this._allModuleNamespaces) {
            root.addNamespaceDeclaration(_allModuleNamespace);
        }
    }

    protected void generateFeedModules(final List modules, final Element feed) {
        this._feedModuleGenerators.generateModules(modules, feed);
    }

    public void generateItemModules(final List modules, final Element item) {
        this._itemModuleGenerators.generateModules(modules, item);
    }

    public void generatePersonModules(final List modules, final Element person) {
        this._personModuleGenerators.generateModules(modules, person);
    }

    protected void generateForeignMarkup(final Element e, final List foreignMarkup) {
        if (foreignMarkup != null) {
            final Iterator elems = foreignMarkup.iterator();
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
        final java.util.Set usedPrefixes = new java.util.HashSet();
        collectUsedPrefixes(root, usedPrefixes);

        final List list = root.getAdditionalNamespaces();
        final List additionalNamespaces = new java.util.ArrayList();
        additionalNamespaces.addAll(list); // the duplication will prevent a
                                           // ConcurrentModificationException
                                           // below

        for (int i = 0; i < additionalNamespaces.size(); i++) {
            final Namespace ns = (Namespace) additionalNamespaces.get(i);
            final String prefix = ns.getPrefix();
            if (prefix != null && prefix.length() > 0 && !usedPrefixes.contains(prefix)) {
                root.removeNamespaceDeclaration(ns);
            }
        }
    }

    private static void collectUsedPrefixes(final Element el, final java.util.Set collector) {
        final String prefix = el.getNamespacePrefix();
        if (prefix != null && prefix.length() > 0 && !collector.contains(prefix)) {
            collector.add(prefix);
        }
        final List kids = el.getChildren();
        for (int i = 0; i < kids.size(); i++) {
            collectUsedPrefixes((Element) kids.get(i), collector); // recursion
                                                                   // - worth it
        }
    }

}
