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
package com.sun.syndication.feed.atom;

import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.impl.ModuleUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Bean for Atom feeds.
 * <p>
 * It handles Atom feeds version 0.3 without loosing any feed information.
 * <p>
 * @author Alejandro Abdelnur
 * @author Dave Johnson (updated for Atom 1.0)
 */
public class Feed extends WireFeed {
    
    private String    _xmlBase;
    private List<Category> _categories;
    private List<Person>     _authors;
    private List<Person>     _contributors;
    private Generator _generator;
    private String    _icon;       
    private String    _id;
    private String    _logo;
    private String    _rights;         // AKA copyright
    private Content   _subtitle;       // AKA tagline   
    private Content   _title;
    private Date      _updated;        // AKA modified
    private List<Link>      _alternateLinks;
    private List<Link>      _otherLinks;
    private List<Entry>      _entries;
    
    private List<Module>      _modules;
   
    private Content   _info;           // Atom 0.3 only
    private String    _language;       // Atom 0.3 only

    /**
     * Default constructor, for bean cloning purposes only.
     *
     */
    public Feed() {
    }

    /**
     * Feed Constructor. All properties, except the type, are set to <b>null</b>.
     * <p>
     * @param type the type of the Atom feed.
     *
     */
    public Feed(String type) {
        super(type);
    }

    /**
     * Returns the feed language (Atom 0.3 only)
     * <p>
     * @return the feed language, <b>null</b> if none.
     *
     */
    public String getLanguage() {
        return _language;
    }

    /**
     * Sets the feed language (Atom 0.3 only)
     * <p>
     * @param language the feed language to set, <b>null</b> if none.
     *
     */
    public void setLanguage(String language) {
        _language = language;
    }

    /**
     * Returns the feed title.
     * <p>
     * @return the feed title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        if (_title != null) return _title.getValue();
        return null;
    }

    /**
     * Sets the feed title.
     * <p>
     * @param title the feed title to set, <b>null</b> if none.
     *
     */
    public void setTitle(String title) {
        if (_title == null) _title = new Content();
        _title.setValue(title);
    }
    
    /**
     * Returns the feed title.
     * <p>
     * @return the feed title, <b>null</b> if none.
     *
     */
    public Content getTitleEx() {
        return _title;
    }

    /**
     * Sets the feed title.
     * <p>
     * @param title the feed title to set, <b>null</b> if none.
     *
     */
    public void setTitleEx(Content title) {
        _title = title;
    }

    /**
     * Returns the feed alternate links.
     * <p>
     * @return a list of Link elements with the feed alternate links,
     *         an empty list if none.
     */
    public List<Link> getAlternateLinks() {
        return (_alternateLinks==null) ? (_alternateLinks=new ArrayList<Link>()) : _alternateLinks;
    }

    /**
     * Sets the feed alternate links.
     * <p>
     * @param alternateLinks the list of Link elements with the feed alternate links to set,
     *        an empty list or <b>null</b> if none.
     */
    public void setAlternateLinks(List<Link> alternateLinks) {
        _alternateLinks = alternateLinks;
    }

    /**
     * Returns the feed other links (non-alternate ones).
     * <p>
     * @return a list of Link elements with the feed other links (non-alternate ones),
     *         an empty list if none.
     */
    public List<Link> getOtherLinks() {
        return (_otherLinks==null) ? (_otherLinks=new ArrayList()) : _otherLinks;
    }

    /**
     * Sets the feed other links (non-alternate ones).
     * <p>
     * @param otherLinks the list of Link elements with the feed other links (non-alternate ones) to set,
     *        an empty list or <b>null</b> if none.
     */
    public void setOtherLinks(List<Link> otherLinks) {
        _otherLinks = otherLinks;
    }

    /**
     * Returns the feed author.
     * <p>
     * @return the feed author, <b>null</b> if none.
     * 
     */
    public List<Person> getAuthors() {
        return (_authors==null) ? (_authors=new ArrayList<Person>()) : _authors;
    }

    /**
     * Sets the feed author.
     * <p>
     * @param authors the feed author to set, <b>null</b> if none.
     * 
     */
    public void setAuthors(List<Person> authors) {
        _authors = authors;
    }

    /**
     * Returns the feed contributors.
     * <p>
     * @return a list of Person elements with the feed contributors,
     *         an empty list if none.
     *
     */
    public List<Person> getContributors() {
        return (_contributors==null) ? (_contributors=new ArrayList<Person>()) : _contributors;
    }

    /**
     * Sets the feed contributors.
     * <p>
     * @param contributors the list of Person elements with the feed contributors to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setContributors(List<Person> contributors) {
        _contributors = contributors;
    }

    /**
     * Returns the feed tag line (Atom 0.3, maps to {@link #getSubtitle()}).
     * <p>
     * @return the feed tag line, <b>null</b> if none.
     */
    public Content getTagline() {
        return _subtitle;
    }

    /**
     * Sets the feed tagline (Atom 0.3, maps to {@link #setSubtitle(com.sun.syndication.feed.atom.Content)}).
     * <p>
     * @param tagline the feed tagline to set, <b>null</b> if none.
     */
    public void setTagline(Content tagline) {
        _subtitle = tagline;
    }

    /**
     * Returns the feed ID.
     * <p>
     * @return the feed ID, <b>null</b> if none.
     *
     */
    public String getId() {
        return _id;
    }

    /**
     * Sets the feed ID.
     * <p>
     * @param id the feed ID to set, <b>null</b> if none.
     *
     */
    public void setId(String id) {
        _id = id;
    }

    /**
     * Returns the feed generator.
     * <p>
     * @return the feed generator, <b>null</b> if none.
     *
     */
    public Generator getGenerator() {
        return _generator;
    }

    /**
     * Sets the feed generator.
     * <p>
     * @param generator the feed generator to set, <b>null</b> if none.
     *
     */
    public void setGenerator(Generator generator) {
        _generator = generator;
    }

    /**
     * Returns the feed copyright (Atom 0.3, maps to {@link #getRights()}).
     * <p>
     * @return the feed copyright, <b>null</b> if none.
     */
    public String getCopyright() {
        return _rights;
    }

    /**
     * Sets the feed copyright (Atom 0.3, maps to {@link #setRights(java.lang.String)}).
     * <p>
     * @param copyright the feed copyright to set, <b>null</b> if none.
     */
    public void setCopyright(String copyright) {
        _rights = copyright;
    }

    /**
     * Returns the feed info (Atom 0.3 only)
     * <p>
     * @return the feed info, <b>null</b> if none.
     */
    public Content getInfo() {
        return _info;
    }

    /**
     * Sets the feed info (Atom 0.3 only)
     * <p>
     * @param info the feed info to set, <b>null</b> if none.
     */
    public void setInfo(Content info) {
        _info = info;
    }

    /**
     * Returns the feed modified date (Atom 0.3, maps to {@link #getUpdated()}).
     * <p>
     * @return the feed modified date, <b>null</b> if none.
     */
    public Date getModified() {
        return _updated;
    }

    /**
     * Sets the feed modified date (Atom 0.3, maps to {@link #setUpdated(java.util.Date)}).
     * <p>
     * @param modified the feed modified date to set, <b>null</b> if none.
     */
    public void setModified(Date modified) {
        _updated = modified;
    }

    /**
     * Returns the feed entries.
     * <p>
     * @return a list of Entry elements with the feed entries,
     *         an empty list if none.
     *
     */
    public List<Entry> getEntries() {
        return (_entries==null) ? (_entries=new ArrayList<Entry>()) : _entries;
    }

    /**
     * Sets the feed entries.
     * <p>
     * @param entries the list of Entry elements with the feed entries to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setEntries(List entries) {
        _entries = entries;
    }

    /**
     * Returns the feed modules.
     * <p>
     * @return a list of ModuleImpl elements with the feed modules,
     *         an empty list if none.
     *
     */
    public List<Module> getModules() {
        return (_modules==null) ? (_modules=new ArrayList<Module>()) : _modules;
    }

    /**
     * Sets the feed moduless.
     * <p>
     * @param modules the list of ModuleImpl elements with the feed moduless to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    @Override
    public void setModules(List<Module> modules) {
        _modules = modules;
    }

    /**
     * Returns the module identified by a given URI.
     * <p>
     * @param uri the URI of the ModuleImpl.
     * @return The module with the given URI, <b>null</b> if none.
     */
    @Override
    public Module getModule(String uri) {
        return ModuleUtils.getModule(_modules,uri);
    }

    /**
     * Returns the categories
     * <p>
     * @return Returns the categories.
     * @since Atom 1.0
     */
    public List getCategories() {
        return (_categories==null) ? (_categories=new ArrayList()) : _categories;
    }
    
    /**
     * Set the categories
     * <p>
     * @param categories The categories to set.
     * @since Atom 1.0
     */
    public void setCategories(List<Category> categories) {
        _categories = categories;
    }
    
    /**
     * Returns the icon
     * <p>
     * @return Returns the icon.
     * @since Atom 1.0
     */
    public String getIcon() {
        return _icon;
    }
    
    /**
     * Set the icon
     * <p>
     * @param icon The icon to set.
     * @since Atom 1.0
     */
    public void setIcon(String icon) {
        _icon = icon;
    }
        
    /**
     * Returns the logo
     * <p>
     * @return Returns the logo.
     * @since Atom 1.0
     */
    public String getLogo() {
        return _logo;
    }
    
    /**
     * Set the logo
     * <p>
     * @param logo The logo to set.
     * @since Atom 1.0
     */
    public void setLogo(String logo) {
        _logo = logo;
    }
    
    /**
     * Returns the rights
     * <p>
     * @return Returns the rights.
     * @since Atom 1.0
     */
    public String getRights() {
        return _rights;
    }
    
    /**
     * Set the rights
     * <p>
     * @param rights The rights to set.
     * @since Atom 1.0
     */
    public void setRights(String rights) {
        _rights = rights;
    }
    
    /**
     * Returns the subtitle
     * <p>
     * @return Returns the subtitle.
     * @since Atom 1.0
     */ 
    public Content getSubtitle() {
        return _subtitle;
    }
    
    /**
     * Set the subtitle
     * <p>
     * @param subtitle The subtitle to set.
     * @since Atom 1.0
     */
    public void setSubtitle(Content subtitle) {
        _subtitle = subtitle;
    }
    
    /**
     * Returns the updated
     * <p>
     * @return Returns the updated.
     * @since Atom 1.0
     */
    public Date getUpdated() {
        return _updated;
    }
    
    /**
     * Set the updated
     * <p>
     * @param updated The updated to set.
     * @since Atom 1.0
     */
    public void setUpdated(Date updated) {
        _updated = updated;
    }

    /**
     * Returns the xmlBase
     * <p>
     * @return Returns the xmlBase.
     * @since Atom 1.0
     */
    public String getXmlBase() {
        return _xmlBase;
    }
    
    /**
     * Set the xmlBase
     * <p>
     * @param xmlBase The xmlBase to set.
     * @since Atom 1.0
     */
    public void setXmlBase(String xmlBase) {
        _xmlBase = xmlBase;
    }
}

