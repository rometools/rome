/*
 * Copyright 2004 Sun Microsystems, Inc.
 * Copyright 2011 ROME Team
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
package com.sun.syndication.feed.atom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Element;

import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.module.Extendable;
import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.impl.ModuleUtils;

/**
 * Bean for entry elements of Atom feeds.
 * <p>
 * 
 * @author Alejandro Abdelnur
 * @author Dave Johnson (updated for Atom 1.0)
 */
public class Entry implements Cloneable, Serializable, Extendable {
    private Content _summary;
    private Content _title;
    private Date _created; // Atom 0.3 only
    private Date _published; // AKA issued
    private Date _updated; // AKA modified
    private Feed _source;
    private List<Link> _alternateLinks;
    private List<Person> _authors;
    private List<Category> _categories;
    private List<Content> _contents;
    private List<Person> _contributors;
    private List<Element> _foreignMarkup;
    private List<Module> _modules;
    private List<Link> _otherLinks;
    private final ObjectBean _objBean;
    private String _id;
    private String _rights;
    private String _xmlBase;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     * 
     */
    public Entry() {
        this._objBean = new ObjectBean(this.getClass(), this);
    }

    /**
     * Sets the entry alternate links.
     * <p>
     * 
     * @param alternateLinks the list of Link elements with the entry alternate
     *            links to set, an empty list or <b>null</b> if none.
     */
    public void setAlternateLinks(final List<Link> alternateLinks) {
        this._alternateLinks = alternateLinks;
    }

    /**
     * Returns the entry alternate links.
     * <p>
     * 
     * @return a list of Link elements with the entry alternate links, an empty
     *         list if none.
     */
    public List<Link> getAlternateLinks() {
        return this._alternateLinks == null ? (this._alternateLinks = new ArrayList<Link>()) : this._alternateLinks;
    }

    /**
     * Sets the author of the entry.
     * <p>
     * 
     * @param authors the author of the entry, <b>null</b> if none.
     * 
     */
    public void setAuthors(final List<Person> authors) {
        this._authors = authors;
    }

    /**
     * Returns the entry author.
     * <p>
     * 
     * @return the entry author, <b>null</b> if none.
     * 
     */
    public List<Person> getAuthors() {
        return this._authors == null ? (this._authors = new ArrayList<Person>()) : this._authors;
    }

    /**
     * Set the categories
     * <p>
     * 
     * @param categories The categories to set.
     * @since Atom 1.0
     */
    public void setCategories(final List<Category> categories) {
        this._categories = categories;
    }

    /**
     * Returns the categories
     * <p>
     * 
     * @return Returns the categories.
     * @since Atom 1.0
     */
    public List<Category> getCategories() {
        return this._categories == null ? (this._categories = new ArrayList<Category>()) : this._categories;
    }

    /**
     * Sets the entry contents.
     * <p>
     * 
     * @param contents the list of Content elements with the entry contents to
     *            set, an empty list or <b>null</b> if none.
     */
    public void setContents(final List<Content> contents) {
        this._contents = contents;
    }

    /**
     * Returns the entry contents.
     * <p>
     * 
     * @return a list of Content elements with the entry contents, an empty list
     *         if none.
     */
    public List<Content> getContents() {
        return this._contents == null ? (this._contents = new ArrayList<Content>()) : this._contents;
    }

    /**
     * Sets the entry contributors.
     * <p>
     * 
     * @param contributors the list of Person elements with the entry
     *            contributors to set, an empty list or <b>null</b> if none.
     * 
     */
    public void setContributors(final List<Person> contributors) {
        this._contributors = contributors;
    }

    /**
     * Returns the entry contributors.
     * <p>
     * 
     * @return a list of Person elements with the entry contributors, an empty
     *         list if none.
     * 
     */
    public List<Person> getContributors() {
        return this._contributors == null ? (this._contributors = new ArrayList<Person>()) : this._contributors;
    }

    /**
     * Sets the entry created date (Atom 0.3 only)
     * <p>
     * 
     * @param created the entry created date, <b>null</b> if none.
     */
    public void setCreated(final Date created) {
        this._created = new Date(created.getTime());
    }

    /**
     * Returns the entry created date (Atom 0.3 only)
     * <p>
     * 
     * @return the entry created date, <b>null</b> if none.
     */
    public Date getCreated() {
        return this._created == null ? null : new Date(this._created.getTime());
    }

    /**
     * Sets foreign markup found at entry level.
     * <p>
     * 
     * @param foreignMarkup Opaque object to discourage use
     * 
     */
    public void setForeignMarkup(final List<Element> foreignMarkup) {
        this._foreignMarkup = foreignMarkup;
    }

    /**
     * Returns foreign markup found at entry level.
     * <p>
     * 
     * @return list of Opaque object to discourage use
     * 
     */
    public List<Element> getForeignMarkup() {
        return this._foreignMarkup == null ? (this._foreignMarkup = new ArrayList<Element>()) : this._foreignMarkup;
    }

    /**
     * Sets the entry ID.
     * <p>
     * 
     * @param id the entry ID, <b>null</b> if none.
     * 
     */
    public void setId(final String id) {
        this._id = id;
    }

    /**
     * Returns the entry ID.
     * <p>
     * 
     * @return the entry ID, <b>null</b> if none.
     * 
     */
    public String getId() {
        return this._id;
    }

    /**
     * Sets the entry issued date (Atom 0.3, maps to
     * {@link #setPublished(java.util.Date)}).
     * <p>
     * 
     * @param issued the entry issued date, <b>null</b> if none.
     */
    public void setIssued(final Date issued) {
        this._published = issued == null ? null : new Date(issued.getTime());
    }

    /**
     * Returns the entry issued date (Atom 0.3, maps to {@link #getPublished()}
     * ).
     * <p>
     * 
     * @return the entry issued date, <b>null</b> if none.
     */
    public Date getIssued() {
        return this._published == null ? null : new Date(this._published.getTime());
    }

    /**
     * Returns true if entry is a media entry, i.e. has rel="edit-media".
     * 
     * @return true if entry is a media entry
     */
    public boolean isMediaEntry() {
        boolean mediaEntry = false;
        final List<Link> links = getOtherLinks();

        for (final Link link : links) {
            if ("edit-media".equals(link.getRel())) {
                mediaEntry = true;

                break;
            }
        }

        return mediaEntry;
    }

    /**
     * Sets the entry modified date (Atom 0.3, maps to
     * {@link #setUpdated(java.util.Date)}).
     * <p>
     * 
     * @param modified the entry modified date, <b>null</b> if none.
     */
    public void setModified(final Date modified) {
        this._updated = modified == null ? null : new Date(modified.getTime());
    }

    /**
     * Returns the entry modified date (Atom 0.3, maps to {@link #getUpdated()}
     * ).
     * <p>
     * 
     * @return the entry modified date, <b>null</b> if none.
     */
    public Date getModified() {
        return this._updated == null ? null : new Date(this._updated.getTime());
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
        return ModuleUtils.getModule(this._modules, uri);
    }

    /**
     * Sets the entry modules.
     * <p>
     * 
     * @param modules the list of ModuleImpl elements with the entry modules to
     *            set, an empty list or <b>null</b> if none.
     * 
     */
    @Override
    public void setModules(final List<Module> modules) {
        this._modules = modules;
    }

    /**
     * Returns the entry modules.
     * <p>
     * 
     * @return a list of ModuleImpl elements with the entry modules, an emtpy
     *         list if none.
     * 
     */
    @Override
    public List<Module> getModules() {
        return this._modules == null ? (this._modules = new ArrayList<Module>()) : this._modules;
    }

    /**
     * Sets the entry non-alternate links.
     * <p>
     * 
     * @param otherLinks the list Link elements with the entry non-alternate
     *            links to set, an empty list or <b>null</b> if none.
     */
    public void setOtherLinks(final List<Link> otherLinks) {
        this._otherLinks = otherLinks;
    }

    /**
     * Returns the entry non-alternate links.
     * <p>
     * 
     * @return the list of Link elements with the entry non-alternate links to
     *         set, an empty list if none.
     */
    public List<Link> getOtherLinks() {
        return this._otherLinks == null ? (this._otherLinks = new ArrayList<Link>()) : this._otherLinks;
    }

    /**
     * Set the published
     * <p>
     * 
     * @param published The published to set.
     * @since Atom 1.0
     */
    public void setPublished(final Date published) {
        this._published = published == null ? null : new Date(published.getTime());
    }

    /**
     * Returns the published
     * <p>
     * 
     * @return Returns the published.
     * @since Atom 1.0
     */
    public Date getPublished() {
        return this._published == null ? null : new Date(this._published.getTime());
    }

    /**
     * Set the rights
     * <p>
     * 
     * @param rights The rights to set.
     * @since Atom 1.0
     */
    public void setRights(final String rights) {
        this._rights = rights;
    }

    /**
     * Returns the rights
     * <p>
     * 
     * @return Returns the rights.
     * @since Atom 1.0
     */
    public String getRights() {
        return this._rights;
    }

    /**
     * Set the source
     * <p>
     * 
     * @param source The source to set.
     */
    public void setSource(final Feed source) {
        this._source = source;
    }

    /**
     * Returns the source
     * <p>
     * 
     * @return Returns the source.
     */
    public Feed getSource() {
        return this._source;
    }

    /**
     * Sets the entry summary.
     * <p>
     * 
     * @param summary the entry summary, <b>null</b> if none.
     * 
     */
    public void setSummary(final Content summary) {
        this._summary = summary;
    }

    /**
     * Returns the entry summary.
     * <p>
     * 
     * @return the entry summary, <b>null</b> if none.
     * 
     */
    public Content getSummary() {
        return this._summary;
    }

    /**
     * Sets the entry title.
     * <p>
     * 
     * @param title the entry title, <b>null</b> if none.
     * 
     */
    public void setTitle(final String title) {
        if (this._title == null) {
            this._title = new Content();
        }

        this._title.setValue(title);
    }

    /**
     * Returns the entry title.
     * <p>
     * 
     * @return the entry title, <b>null</b> if none.
     * 
     */
    public String getTitle() {
        if (this._title != null) {
            return this._title.getValue();
        }

        return null;
    }

    /**
     * Sets the entry title as a text construct.
     * <p>
     * 
     * @param title the entry title, <b>null</b> if none.
     * 
     */
    public void setTitleEx(final Content title) {
        this._title = title;
    }

    /**
     * Returns the entry title as a text construct.
     * <p>
     * 
     * @return the entry title, <b>null</b> if none.
     * 
     */
    public Content getTitleEx() {
        return this._title;
    }

    /**
     * Set the updated
     * <p>
     * 
     * @param updated The updated to set.
     * @since Atom 1.0
     */
    public void setUpdated(final Date updated) {
        this._updated = updated == null ? null : new Date(updated.getTime());
    }

    /**
     * Returns the updated
     * <p>
     * 
     * @return Returns the updated.
     * @since Atom 1.0
     */
    public Date getUpdated() {
        return this._updated == null ? null : new Date(this._updated.getTime());
    }

    /**
     * Set the xmlBase
     * <p>
     * 
     * @param xmlBase The xmlBase to set.
     * @since Atom 1.0
     */
    public void setXmlBase(final String xmlBase) {
        this._xmlBase = xmlBase;
    }

    /**
     * Returns the xmlBase
     * <p>
     * 
     * @return Returns the xmlBase.
     * @since Atom 1.0
     */
    public String getXmlBase() {
        return this._xmlBase;
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     * 
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object
     *             cannot be cloned.
     * 
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return this._objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by
     * the Object equals() method.
     * <p>
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
        if (!(other instanceof Entry)) {
            return false;
        }
        // can't use foreign markup in equals, due to JDOM equals impl
        final List<Element> fm = getForeignMarkup();
        setForeignMarkup(((Entry) other).getForeignMarkup());

        final boolean ret = this._objBean.equals(other);
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
        return this._objBean.hashCode();
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
        return this._objBean.toString();
    }

    public Link findRelatedLink(final String relation) {
        for (final Link l : this._otherLinks) {
            if (relation.equals(l.getRel())) {
                return l;
            }
        }
        return null;
    }
}
