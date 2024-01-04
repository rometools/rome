/*
 * Copyright 2004 Sun Microsystems, Inc.
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
package com.rometools.rome.feed.atom;

import java.time.LocalDateTime;
import java.util.List;

import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.module.impl.ModuleUtils;
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.utils.Lists;

/**
 * Bean for Atom feeds.
 */
public class Feed extends WireFeed {

    private static final long serialVersionUID = 1L;

    private String xmlBase;
    private List<Category> categories;
    private List<SyndPerson> authors;
    private List<SyndPerson> contributors;
    private Generator generator;
    private String icon;
    private String id;
    private String logo;
    private String rights; // AKA copyright
    private Content subtitle; // AKA tagline
    private Content title;
    private LocalDateTime updated; // AKA modified
    private List<Link> alternateLinks;
    private List<Link> otherLinks;
    private List<Entry> entries;

    private List<Module> modules;

    private Content info; // Atom 0.3 only
    private String language; // Atom 0.3 only

    /**
     * Default constructor, for bean cloning purposes only.
     *
     */
    public Feed() {
    }

    /**
     * Feed Constructor. All properties, except the type, are set to <b>null</b>.
     * <p>
     *
     * @param type the type of the Atom feed.
     *
     */
    public Feed(final String type) {
        super(type);
    }

    /**
     * Returns the feed language (Atom 0.3 only)
     * <p>
     *
     * @return the feed language, <b>null</b> if none.
     *
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the feed language (Atom 0.3 only)
     * <p>
     *
     * @param language the feed language to set, <b>null</b> if none.
     *
     */
    public void setLanguage(final String language) {
        this.language = language;
    }

    /**
     * Returns the feed title.
     * <p>
     *
     * @return the feed title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        if (title != null) {
            return title.getValue();
        }
        return null;
    }

    /**
     * Sets the feed title.
     * <p>
     *
     * @param title the feed title to set, <b>null</b> if none.
     *
     */
    public void setTitle(final String title) {
        if (this.title == null) {
            this.title = new Content();
        }
        this.title.setValue(title);
    }

    /**
     * Returns the feed title.
     * <p>
     *
     * @return the feed title, <b>null</b> if none.
     *
     */
    public Content getTitleEx() {
        return title;
    }

    /**
     * Sets the feed title.
     * <p>
     *
     * @param title the feed title to set, <b>null</b> if none.
     *
     */
    public void setTitleEx(final Content title) {
        this.title = title;
    }

    /**
     * Returns the feed alternate links.
     * <p>
     *
     * @return a list of Link elements with the feed alternate links, an empty list if none.
     */
    public List<Link> getAlternateLinks() {
        return alternateLinks = Lists.createWhenNull(alternateLinks);
    }

    /**
     * Sets the feed alternate links.
     * <p>
     *
     * @param alternateLinks the list of Link elements with the feed alternate links to set, an
     *            empty list or <b>null</b> if none.
     */
    public void setAlternateLinks(final List<Link> alternateLinks) {
        this.alternateLinks = alternateLinks;
    }

    /**
     * Returns the feed other links (non-alternate ones).
     * <p>
     *
     * @return a list of Link elements with the feed other links (non-alternate ones), an empty list
     *         if none.
     */
    public List<Link> getOtherLinks() {
        return otherLinks = Lists.createWhenNull(otherLinks);
    }

    /**
     * Sets the feed other links (non-alternate ones).
     * <p>
     *
     * @param otherLinks the list of Link elements with the feed other links (non-alternate ones) to
     *            set, an empty list or <b>null</b> if none.
     */
    public void setOtherLinks(final List<Link> otherLinks) {
        this.otherLinks = otherLinks;
    }

    /**
     * Returns the feed author.
     * <p>
     *
     * @return the feed author, <b>null</b> if none.
     *
     */
    public List<SyndPerson> getAuthors() {
        return authors = Lists.createWhenNull(authors);
    }

    /**
     * Sets the feed author.
     * <p>
     *
     * @param authors the feed author to set, <b>null</b> if none.
     *
     */
    public void setAuthors(final List<SyndPerson> authors) {
        this.authors = authors;
    }

    /**
     * Returns the feed contributors.
     * <p>
     *
     * @return a list of Person elements with the feed contributors, an empty list if none.
     *
     */
    public List<SyndPerson> getContributors() {
        return contributors = Lists.createWhenNull(contributors);
    }

    /**
     * Sets the feed contributors.
     * <p>
     *
     * @param contributors the list of Person elements with the feed contributors to set, an empty
     *            list or <b>null</b> if none.
     *
     */
    public void setContributors(final List<SyndPerson> contributors) {
        this.contributors = contributors;
    }

    /**
     * Returns the feed tag line (Atom 0.3, maps to {@link #getSubtitle()}).
     * <p>
     *
     * @return the feed tag line, <b>null</b> if none.
     */
    public Content getTagline() {
        return subtitle;
    }

    /**
     * Sets the feed tagline (Atom 0.3, maps to
     * {@link #setSubtitle(com.rometools.rome.feed.atom.Content)}).
     * <p>
     *
     * @param tagline the feed tagline to set, <b>null</b> if none.
     */
    public void setTagline(final Content tagline) {
        subtitle = tagline;
    }

    /**
     * Returns the feed ID.
     * <p>
     *
     * @return the feed ID, <b>null</b> if none.
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the feed ID.
     * <p>
     *
     * @param id the feed ID to set, <b>null</b> if none.
     *
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Returns the feed generator.
     * <p>
     *
     * @return the feed generator, <b>null</b> if none.
     *
     */
    public Generator getGenerator() {
        return generator;
    }

    /**
     * Sets the feed generator.
     * <p>
     *
     * @param generator the feed generator to set, <b>null</b> if none.
     *
     */
    public void setGenerator(final Generator generator) {
        this.generator = generator;
    }

    /**
     * Returns the feed copyright (Atom 0.3, maps to {@link #getRights()}).
     * <p>
     *
     * @return the feed copyright, <b>null</b> if none.
     */
    public String getCopyright() {
        return rights;
    }

    /**
     * Sets the feed copyright (Atom 0.3, maps to {@link #setRights(java.lang.String)}).
     * <p>
     *
     * @param copyright the feed copyright to set, <b>null</b> if none.
     */
    public void setCopyright(final String copyright) {
        rights = copyright;
    }

    /**
     * Returns the feed info (Atom 0.3 only)
     * <p>
     *
     * @return the feed info, <b>null</b> if none.
     */
    public Content getInfo() {
        return info;
    }

    /**
     * Sets the feed info (Atom 0.3 only)
     * <p>
     *
     * @param info the feed info to set, <b>null</b> if none.
     */
    public void setInfo(final Content info) {
        this.info = info;
    }

    /**
     * Returns the feed modified date (Atom 0.3, maps to {@link #getUpdated()}).
     * <p>
     *
     * @return the feed modified date, <b>null</b> if none.
     */
    public LocalDateTime getModified() {
        return updated;
    }

    /**
     * Sets the feed modified date (Atom 0.3, maps to {@link #setUpdated(java.util.Date)}).
     * <p>
     *
     * @param modified the feed modified date to set, <b>null</b> if none.
     */
    public void setModified(final LocalDateTime modified) {
        updated = modified;
    }

    /**
     * Returns the feed entries.
     * <p>
     *
     * @return a list of Entry elements with the feed entries, an empty list if none.
     *
     */
    public List<Entry> getEntries() {
        return entries = Lists.createWhenNull(entries);
    }

    /**
     * Sets the feed entries.
     * <p>
     *
     * @param entries the list of Entry elements with the feed entries to set, an empty list or
     *            <b>null</b> if none.
     *
     */
    public void setEntries(final List<Entry> entries) {
        this.entries = entries;
    }

    /**
     * Returns the feed modules.
     * <p>
     *
     * @return a list of ModuleImpl elements with the feed modules, an empty list if none.
     *
     */
    @Override
    public List<Module> getModules() {
        return modules = Lists.createWhenNull(modules);
    }

    /**
     * Sets the feed moduless.
     * <p>
     *
     * @param modules the list of ModuleImpl elements with the feed moduless to set, an empty list
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
     * Returns the categories
     * <p>
     *
     * @return Returns the categories.
     * @since Atom 1.0
     */
    public List<Category> getCategories() {
        return categories = Lists.createWhenNull(categories);
    }

    /**
     * Set the categories
     * <p>
     *
     * @param categories The categories to set.
     * @since Atom 1.0
     */
    public void setCategories(final List<Category> categories) {
        this.categories = categories;
    }

    /**
     * Returns the icon
     * <p>
     *
     * @return Returns the icon.
     * @since Atom 1.0
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Set the icon
     * <p>
     *
     * @param icon The icon to set.
     * @since Atom 1.0
     */
    public void setIcon(final String icon) {
        this.icon = icon;
    }

    /**
     * Returns the logo
     * <p>
     *
     * @return Returns the logo.
     * @since Atom 1.0
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Set the logo
     * <p>
     *
     * @param logo The logo to set.
     * @since Atom 1.0
     */
    public void setLogo(final String logo) {
        this.logo = logo;
    }

    /**
     * Returns the rights
     * <p>
     *
     * @return Returns the rights.
     * @since Atom 1.0
     */
    public String getRights() {
        return rights;
    }

    /**
     * Set the rights
     * <p>
     *
     * @param rights The rights to set.
     * @since Atom 1.0
     */
    public void setRights(final String rights) {
        this.rights = rights;
    }

    /**
     * Returns the subtitle
     * <p>
     *
     * @return Returns the subtitle.
     * @since Atom 1.0
     */
    public Content getSubtitle() {
        return subtitle;
    }

    /**
     * Set the subtitle
     * <p>
     *
     * @param subtitle The subtitle to set.
     * @since Atom 1.0
     */
    public void setSubtitle(final Content subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * Returns the updated
     * <p>
     *
     * @return Returns the updated.
     * @since Atom 1.0
     */
    public LocalDateTime getUpdated() {
        return updated;
    }

    /**
     * Set the updated
     * <p>
     *
     * @param updated The updated to set.
     * @since Atom 1.0
     */
    public void setUpdated(final LocalDateTime updated) {
        this.updated = updated;
    }

    /**
     * Returns the xmlBase
     * <p>
     *
     * @return Returns the xmlBase.
     * @since Atom 1.0
     */
    public String getXmlBase() {
        return xmlBase;
    }

    /**
     * Set the xmlBase
     * <p>
     *
     * @param xmlBase The xmlBase to set.
     * @since Atom 1.0
     */
    public void setXmlBase(final String xmlBase) {
        this.xmlBase = xmlBase;
    }
}
