/*
 * Copyright 2004 Sun Microsystems, Inc.
 * Copyright 2011 ROME Team
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
package com.rometools.rome.feed;

import java.io.Serializable;
import java.util.List;

import org.jdom2.Element;

import com.rometools.rome.feed.impl.ObjectBean;
import com.rometools.rome.feed.module.Extendable;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.module.impl.ModuleUtils;
import com.rometools.utils.Lists;

/**
 * Parent class of the RSS (Channel) and Atom (Feed) feed beans.
 * <p>
 * NOTE: We don't like this class at this package level but the alternative would have been a
 * proliferation of packages (one more level to hold atom and rss package with this class just in
 * that package).
 * <p>
 * The format of the 'type' property must be [FEEDNAME]_[FEEDVERSION] with the FEEDNAME in lower
 * case, for example: rss_0.9, rss_0.93, atom_0.3
 */
public abstract class WireFeed implements Cloneable, Serializable, Extendable {

    private static final long serialVersionUID = 1L;

    private final ObjectBean objBean;

    private String feedType;
    private String encoding;
    private String styleSheet;
    private List<Module> modules;
    private List<Element> foreignMarkup;

    /**
     * Default constructor, for bean cloning purposes only.
     */
    protected WireFeed() {
        objBean = new ObjectBean(this.getClass(), this);
    }

    /**
     * Creates a feed for a given type.
     *
     * @param type of the feed to create.
     *
     */
    protected WireFeed(final String type) {
        this();
        feedType = type;
    }

    /**
     * Creates a deep 'bean' clone of the object.
     *
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals()
     * method.
     *
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    @Override
    public boolean equals(final Object other) {

        if (other == null) {
            return false;
        }

        if (!(other instanceof WireFeed)) {
            return false;
        }

        // can't use foreign markup in equals, due to JDOM equals impl
        final List<Element> fm = getForeignMarkup();
        setForeignMarkup(((WireFeed) other).getForeignMarkup());
        final boolean ret = objBean.equals(other);
        // restore foreign markup
        setForeignMarkup(fm);
        return ret;

    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     *
     * @return the hashcode of the bean object.
     *
     */
    @Override
    public int hashCode() {
        return objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     *
     * @return String representation for the object.
     *
     */
    @Override
    public String toString() {
        return objBean.toString();
    }

    /**
     * Sets the feedType of a the feed. <b>Do not use</b>, for bean cloning purposes only.
     * <p>
     *
     * @param feedType the feedType of the feed.
     *
     */
    public void setFeedType(final String feedType) {
        this.feedType = feedType;
    }

    /**
     * Returns the type of the feed.
     *
     * @return the type of the feed.
     */
    public String getFeedType() {
        return feedType;
    }

    /**
     * Returns the charset encoding of a the feed.
     * <p>
     * This property is not set by feed parsers. But it is used by feed generators to set the
     * encoding in the XML prolog.
     * <p>
     *
     * @return the charset encoding of the feed.
     *
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Sets the charset encoding of a the feed.
     * <p>
     * This property is not set by feed parsers. But it is used by feed generators to set the
     * encoding in the XML prolog.
     * <p>
     *
     * @param encoding the charset encoding of the feed.
     *
     */
    public void setEncoding(final String encoding) {
        this.encoding = encoding;
    }

    /**
     * Returns the channel modules.
     * <p>
     *
     * @return a list of ModuleImpl elements with the channel modules, an empty list if none.
     *
     */
    @Override
    public List<Module> getModules() {
        return modules = Lists.createWhenNull(modules);
    }

    /**
     * Sets the channel modules.
     * <p>
     *
     * @param modules the list of ModuleImpl elements with the channel modules to set, an empty list
     *            or <b>null</b> if none.
     *
     */
    @Override
    public void setModules(final List<Module> modules) {
        this.modules = modules;
    }

    /**
     * Returns the module identified by a given URI.
     * <p>
     *
     * @param uri the URI of the ModuleImpl.
     * @return The module with the given URI, <b>null</b> if none.
     */
    @Override
    public Module getModule(final String uri) {
        return ModuleUtils.getModule(modules, uri);
    }

    /**
     * Returns foreign markup found at channel level.
     * <p>
     *
     * @return Opaque object to discourage use
     *
     */
    public List<Element> getForeignMarkup() {
        return foreignMarkup = Lists.createWhenNull(foreignMarkup);
    }

    /**
     * Sets foreign markup found at channel level.
     * <p>
     *
     * @param foreignMarkup Opaque object to discourage use
     *
     */
    public void setForeignMarkup(final List<Element> foreignMarkup) {
        this.foreignMarkup = foreignMarkup;
    }

    /**
     * URL of XSL-Stylesheet.
     *
     * @since 2.0.0
     * @return styleSheet URL or {@code null}
     */
    public String getStyleSheet() {
        return styleSheet;
    }

    /**
     * URL of XSL-Stylesheet.
     *
     * @since 2.0.0
     * @param styleSheet URL or {@code null}
     */
    public void setStyleSheet(final String styleSheet) {
        this.styleSheet = styleSheet;
    }

}
