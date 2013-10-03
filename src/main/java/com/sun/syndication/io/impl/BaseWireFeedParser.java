package com.sun.syndication.io.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.module.Extendable;
import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.WireFeedParser;

/**
 * @author Alejandro Abdelnur
 */
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

    private final String _type;
    private final ModuleParsers _feedModuleParsers;
    private final ModuleParsers _itemModuleParsers;
    private final ModuleParsers _personModuleParsers;
    private final Namespace _namespace;

    protected BaseWireFeedParser(final String type, final Namespace namespace) {
        this._type = type;
        this._namespace = namespace;
        this._feedModuleParsers = new ModuleParsers(type + FEED_MODULE_PARSERS_POSFIX_KEY, this);
        this._itemModuleParsers = new ModuleParsers(type + ITEM_MODULE_PARSERS_POSFIX_KEY, this);
        this._personModuleParsers = new ModuleParsers(type + PERSON_MODULE_PARSERS_POSFIX_KEY, this);
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
        return this._type;
    }

    protected List<Module> parseFeedModules(final Element feedElement) {
        return this._feedModuleParsers.parseModules(feedElement);
    }

    protected List<Module> parseItemModules(final Element itemElement) {
        return this._itemModuleParsers.parseModules(itemElement);
    }

    protected List<Module> parsePersonModules(final Element itemElement) {
        return this._personModuleParsers.parseModules(itemElement);
    }

    protected List<Element> extractForeignMarkup(final Element e, final Extendable ext, final Namespace basens) {
        final ArrayList<Element> foreignMarkup = new ArrayList<Element>();
        final Iterator<Element> children = e.getChildren().iterator();
        while (children.hasNext()) {
            final Element elem = children.next();
            if (
            // if elemet not in the RSS namespace
            !basens.equals(elem.getNamespace())
            // and elem was not handled by a module
                    && null == ext.getModule(elem.getNamespaceURI())) {

                // save it as foreign markup,
                // but we can't detach it while we're iterating
                foreignMarkup.add(elem.clone());
            }
        }
        // Now we can detach the foreign markup elements
        final Iterator<Element> fm = foreignMarkup.iterator();
        while (fm.hasNext()) {
            final Element elem = fm.next();
            elem.detach();
        }
        return foreignMarkup;
    }

    protected Attribute getAttribute(final Element e, final String attributeName) {
        Attribute attribute = e.getAttribute(attributeName);
        if (attribute == null) {
            attribute = e.getAttribute(attributeName, this._namespace);
        }
        return attribute;
    }

    protected String getAttributeValue(final Element e, final String attributeName) {
        final Attribute attr = getAttribute(e, attributeName);
        return attr != null ? attr.getValue() : null;
    }

}
