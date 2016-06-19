/*
 * Copyright 2004 Sun Microsystems, Inc.
 * Copyright 2011 The ROME Team
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
package com.rometools.rome.feed.rss;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.jdom2.Element;

import com.rometools.rome.feed.impl.ObjectBean;
import com.rometools.rome.feed.module.Extendable;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.module.impl.ModuleUtils;
import com.rometools.utils.Dates;
import com.rometools.utils.Lists;

/**
 * Bean for items of RSS feeds.
 * <p>
 * It handles all RSS versions without loosing information.
 * <p>
 * For RSS1.0 it supports Dublin Core and Syndication modules. Note that those modules currently
 * support simple syntax format only.
 */
public class Item implements Cloneable, Serializable, Extendable {

    private static final long serialVersionUID = 1L;

    private final ObjectBean objBean;

    private String title;
    private String link;
    private String uri;
    private Description description;
    private Content content;
    private Source source;
    private List<Enclosure> enclosures;
    private List<Category> categories;
    private Guid guid;
    private String comments;
    private String author;
    private Date pubDate;
    private Date expirationDate;
    private List<Module> modules;
    private List<Element> foreignMarkup;

    public Item() {
        objBean = new ObjectBean(this.getClass(), this);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
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
     * <p>
     *
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    @Override
    public boolean equals(final Object other) {
        if (other == null || !(other instanceof Item)) {
            return false;
        }
        // can't use foreign markup in equals, due to JDOM equals impl
        final List<Element> fm = getForeignMarkup();
        setForeignMarkup(((Item) other).getForeignMarkup());
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
     * Returns the item title.
     * <p>
     *
     * @return the item title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the item title.
     * <p>
     *
     * @param title the item title to set, <b>null</b> if none.
     *
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Returns the item link.
     * <p>
     *
     * @return the item link, <b>null</b> if none.
     *
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the item link.
     * <p>
     *
     * @param link the item link to set, <b>null</b> if none.
     *
     */
    public void setLink(final String link) {
        this.link = link;
    }

    /**
     * Returns the item uri.
     * <p>
     *
     * @return the item uri, <b>null</b> if none.
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the item uri.
     * <p>
     *
     * @param uri the item uri to set, <b>null</b> if none.
     */
    public void setUri(final String uri) {
        this.uri = uri;
    }

    /**
     * Returns the item description.
     * <p>
     *
     * @return the item description, <b>null</b> if none.
     *
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Sets the item description.
     * <p>
     *
     * @param description the item description to set, <b>null</b> if none.
     *
     */
    public void setDescription(final Description description) {
        this.description = description;
    }

    /**
     * Returns the item content.
     * <p>
     *
     * @return the item content, <b>null</b> if none.
     *
     */
    public Content getContent() {
        return content;
    }

    /**
     * Sets the item content.
     * <p>
     *
     * @param content the item content to set, <b>null</b> if none.
     *
     */
    public void setContent(final Content content) {
        this.content = content;
    }

    /**
     * Returns the item source.
     * <p>
     *
     * @return the item source, <b>null</b> if none.
     *
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets the item source.
     * <p>
     *
     * @param source the item source to set, <b>null</b> if none.
     *
     */
    public void setSource(final Source source) {
        this.source = source;
    }

    /**
     * Returns the item enclosures.
     * <p>
     *
     * @return a list of Enclosure elements with the item enclosures, an empty list if none.
     *
     */
    public List<Enclosure> getEnclosures() {
        return enclosures = Lists.createWhenNull(enclosures);
    }

    /**
     * Sets the item enclosures.
     * <p>
     *
     * @param enclosures the list of Enclosure elements with the item enclosures to set, an empty
     *            list or <b>null</b> if none.
     *
     */
    public void setEnclosures(final List<Enclosure> enclosures) {
        this.enclosures = enclosures;
    }

    /**
     * Returns the item categories.
     * <p>
     *
     * @return a list of Category elements with the item categories, an empty list if none.
     *
     */
    public List<Category> getCategories() {
        return categories = Lists.createWhenNull(categories);
    }

    /**
     * Sets the item categories.
     * <p>
     *
     * @param categories the list of Categories elements with the item categories to set, an empty
     *            list or <b>null</b> if none.
     *
     */
    public void setCategories(final List<Category> categories) {
        this.categories = categories;
    }

    /**
     * Returns the item GUID.
     * <p>
     *
     * @return the item GUID, <b>null</b> if none.
     *
     */
    public Guid getGuid() {
        return guid;
    }

    /**
     * Sets the item GUID.
     * <p>
     *
     * @param guid the item GUID to set, <b>null</b> if none.
     *
     */
    public void setGuid(final Guid guid) {
        this.guid = guid;
    }

    /**
     * Returns the item comments.
     * <p>
     *
     * @return the item comments, <b>null</b> if none.
     *
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the item comments.
     * <p>
     *
     * @param comments the item comments to set, <b>null</b> if none.
     *
     */
    public void setComments(final String comments) {
        this.comments = comments;
    }

    /**
     * Returns the item author.
     * <p>
     *
     * @return the item author, <b>null</b> if none.
     *
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the item author.
     * <p>
     *
     * @param author the item author to set, <b>null</b> if none.
     *
     */
    public void setAuthor(final String author) {
        this.author = author;
    }

    /**
     * Returns the item modules.
     * <p>
     *
     * @return a list of ModuleImpl elements with the item modules, an empty list if none.
     *
     */
    @Override
    public List<Module> getModules() {
        return modules = Lists.createWhenNull(modules);
    }

    /**
     * Sets the item modules.
     * <p>
     *
     * @param modules the list of ModuleImpl elements with the item modules to set, an empty list or
     *            <b>null</b> if none.
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
     * Returns the item publishing date.
     * <p>
     *
     * @return the item publishing date, <b>null</b> if none.
     *
     */
    public Date getPubDate() {
        return Dates.copy(pubDate);
    }

    /**
     * Sets the item publishing date.
     * <p>
     *
     * @param pubDate the item publishing date to set, <b>null</b> if none.
     *
     */
    public void setPubDate(final Date pubDate) {
        this.pubDate = Dates.copy(pubDate);
    }

    /**
     * Returns the item expiration date.
     * <p>
     *
     * @return the item expiration date, <b>null</b> if none.
     *
     */
    public Date getExpirationDate() {
        return Dates.copy(expirationDate);
    }

    /**
     * Sets the item expiration date.
     * <p>
     *
     * @param expirationDate the item expiration date to set, <b>null</b> if none.
     *
     */
    public void setExpirationDate(final Date expirationDate) {
        this.expirationDate = Dates.copy(expirationDate);
    }

    /**
     * Returns foreign markup found at item level.
     * <p>
     *
     * @return Opaque object to discourage use
     *
     */
    public List<Element> getForeignMarkup() {
        return foreignMarkup = Lists.createWhenNull(foreignMarkup);
    }

    /**
     * Sets foreign markup found at item level.
     * <p>
     *
     * @param foreignMarkup Opaque object to discourage use
     *
     */
    public void setForeignMarkup(final List<Element> foreignMarkup) {
        this.foreignMarkup = foreignMarkup;
    }

}
