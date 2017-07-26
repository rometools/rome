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
package com.rometools.rome.feed.synd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom2.Element;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;
import com.rometools.rome.feed.module.DCModule;
import com.rometools.rome.feed.module.DCModuleImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.module.SyModule;
import com.rometools.rome.feed.module.SyModuleImpl;
import com.rometools.rome.feed.module.impl.ModuleUtils;
import com.rometools.rome.feed.synd.impl.URINormalizer;
import com.rometools.utils.Dates;
import com.rometools.utils.Lists;
import com.rometools.utils.Strings;

/**
 * Bean for entries of SyndFeedImpl feeds.
 */
public class SyndEntryImpl implements Serializable, SyndEntry {

    private static final long serialVersionUID = 1L;

    private static final CopyFromHelper COPY_FROM_HELPER;

    private final ObjectBean objBean;

    private String uri;
    private String link;
    private String comments;
    private Date updatedDate;
    private SyndContent title;
    private SyndContent description;
    private List<SyndLink> links;
    private List<SyndContent> contents; // deprecated by Atom 1.0
    private List<Module> modules;
    private List<SyndEnclosure> enclosures;
    private List<SyndPerson> authors;
    private List<SyndPerson> contributors;
    private SyndFeed source;
    private List<Element> foreignMarkup;

    // com.rometools.rome.feed.atom.Entry or com.rometools.rome.feed.rss.Item
    private Object wireEntry;

    // ISSUE: some converters assume this is never null
    private List<SyndCategory> categories = new ArrayList<SyndCategory>();

    private static final Set<String> IGNORE_PROPERTIES = new HashSet<String>();

    /**
     * Unmodifiable Set containing the convenience properties of this class.
     * <p>
     * Convenience properties are mapped to Modules, for cloning the convenience properties can be
     * ignored as the will be copied as part of the module cloning.
     */
    public static final Set<String> CONVENIENCE_PROPERTIES = Collections.unmodifiableSet(IGNORE_PROPERTIES);

    static {

        IGNORE_PROPERTIES.add("publishedDate");
        IGNORE_PROPERTIES.add("author");

        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("uri", String.class);
        basePropInterfaceMap.put("title", String.class);
        basePropInterfaceMap.put("link", String.class);
        basePropInterfaceMap.put("uri", String.class);
        basePropInterfaceMap.put("description", SyndContent.class);
        basePropInterfaceMap.put("contents", SyndContent.class);
        basePropInterfaceMap.put("enclosures", SyndEnclosure.class);
        basePropInterfaceMap.put("modules", Module.class);
        basePropInterfaceMap.put("categories", SyndCategory.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();
        basePropClassImplMap.put(SyndContent.class, SyndContentImpl.class);
        basePropClassImplMap.put(SyndEnclosure.class, SyndEnclosureImpl.class);
        basePropClassImplMap.put(SyndCategory.class, SyndCategoryImpl.class);
        basePropClassImplMap.put(DCModule.class, DCModuleImpl.class);
        basePropClassImplMap.put(SyModule.class, SyModuleImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(SyndEntry.class, basePropInterfaceMap, basePropClassImplMap);

    }

    /**
     * For implementations extending SyndEntryImpl to be able to use the ObjectBean functionality
     * with extended interfaces.
     * <p>
     *
     * @param beanClass
     * @param convenienceProperties set containing the convenience properties of the SyndEntryImpl
     *            (the are ignored during cloning, check CloneableBean for details).
     *
     */
    protected SyndEntryImpl(final Class<?> beanClass, final Set<String> convenienceProperties) {
        objBean = new ObjectBean(beanClass, this, convenienceProperties);
    }

    public SyndEntryImpl() {
        this(SyndEntry.class, IGNORE_PROPERTIES);
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
        if (other == null) {
            return false;
        }
        // while ObjectBean does this check this method does a cast to obtain
        // the foreign markup
        // so we need to check before doing so.
        if (!(other instanceof SyndEntryImpl)) {
            return false;
        }
        // can't use foreign markup in equals, due to JDOM equals impl
        final List<Element> fm = getForeignMarkup();
        setForeignMarkup(((SyndEntryImpl) other).getForeignMarkup());
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
     * Returns the entry URI.
     * <p>
     * How the entry URI maps to a concrete feed type (RSS or Atom) depends on the concrete feed
     * type. This is explained in detail in Rome documentation, <a
     * href="http://wiki.java.net/bin/edit/Javawsxml/Rome04URIMapping">Feed and entry URI
     * mapping</a>.
     * <p>
     * The returned URI is a normalized URI as specified in RFC 2396bis.
     * <p>
     *
     * @return the entry URI, <b>null</b> if none.
     *
     */
    @Override
    public String getUri() {
        return uri;
    }

    /**
     * Sets the entry URI.
     * <p>
     * How the entry URI maps to a concrete feed type (RSS or Atom) depends on the concrete feed
     * type. This is explained in detail in Rome documentation, <a
     * href="http://wiki.java.net/bin/edit/Javawsxml/Rome04URIMapping">Feed and entry URI
     * mapping</a>.
     * <p>
     *
     * @param uri the entry URI to set, <b>null</b> if none.
     *
     */
    @Override
    public void setUri(final String uri) {
        this.uri = URINormalizer.normalize(uri);
    }

    /**
     * Returns the entry title.
     * <p>
     *
     * @return the entry title, <b>null</b> if none.
     *
     */
    @Override
    public String getTitle() {
        if (title != null) {
            return title.getValue();
        }
        return null;
    }

    /**
     * Sets the entry title.
     * <p>
     *
     * @param title the entry title to set, <b>null</b> if none.
     *
     */
    @Override
    public void setTitle(final String title) {
        if (this.title == null) {
            this.title = new SyndContentImpl();
        }
        this.title.setValue(title);
    }

    /**
     * Returns the entry title as a text construct.
     * <p>
     *
     * @return the entry title, <b>null</b> if none.
     *
     */
    @Override
    public SyndContent getTitleEx() {
        return title;
    }

    /**
     * Sets the entry title as a text construct.
     * <p>
     *
     * @param title the entry title to set, <b>null</b> if none.
     *
     */
    @Override
    public void setTitleEx(final SyndContent title) {
        this.title = title;
    }

    /**
     * Returns the entry link.
     * <p>
     *
     * @return the entry link, <b>null</b> if none.
     *
     */
    @Override
    public String getLink() {
        return link;
    }

    /**
     * Sets the entry link.
     * <p>
     *
     * @param link the entry link to set, <b>null</b> if none.
     *
     */
    @Override
    public void setLink(final String link) {
        this.link = link;
    }

    /**
     * Returns the entry description.
     * <p>
     *
     * @return the entry description, <b>null</b> if none.
     *
     */
    @Override
    public SyndContent getDescription() {
        return description;
    }

    /**
     * Sets the entry description.
     * <p>
     *
     * @param description the entry description to set, <b>null</b> if none.
     *
     */
    @Override
    public void setDescription(final SyndContent description) {
        this.description = description;
    }

    /**
     * Returns the entry contents.
     * <p>
     *
     * @return a list of SyndContentImpl elements with the entry contents, an empty list if none.
     *
     */
    @Override
    public List<SyndContent> getContents() {
        return contents = Lists.createWhenNull(contents);
    }

    /**
     * Sets the entry contents.
     * <p>
     *
     * @param contents the list of SyndContentImpl elements with the entry contents to set, an empty
     *            list or <b>null</b> if none.
     *
     */
    @Override
    public void setContents(final List<SyndContent> contents) {
        this.contents = contents;
    }

    /**
     * Returns the entry enclosures.
     * <p>
     *
     * @return a list of SyndEnclosure elements with the entry enclosures, an empty list if none.
     *
     */
    @Override
    public List<SyndEnclosure> getEnclosures() {
        return enclosures = Lists.createWhenNull(enclosures);
    }

    /**
     * Sets the entry enclosures.
     * <p>
     *
     * @param enclosures the list of SyndEnclosure elements with the entry enclosures to set, an
     *            empty list or <b>null</b> if none.
     *
     */
    @Override
    public void setEnclosures(final List<SyndEnclosure> enclosures) {
        this.enclosures = enclosures;
    }

    /**
     * Returns the entry published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     *
     * @return the entry published date, <b>null</b> if none.
     *
     */
    @Override
    public Date getPublishedDate() {
        return getDCModule().getDate();
    }

    /**
     * Sets the entry published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     *
     * @param publishedDate the entry published date to set, <b>null</b> if none.
     *
     */
    @Override
    public void setPublishedDate(final Date publishedDate) {
        getDCModule().setDate(publishedDate);
    }

    /**
     * Returns the entry categories.
     * <p>
     *
     * @return a list of SyndCategoryImpl elements with the entry categories, an empty list if none.
     *
     */
    @Override
    public List<SyndCategory> getCategories() {
        return categories;
    }

    /**
     * Sets the entry categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     *
     * @param categories the list of SyndCategoryImpl elements with the entry categories to set, an
     *            empty list or <b>null</b> if none.
     *
     */
    @Override
    public void setCategories(final List<SyndCategory> categories) {
        this.categories = categories;
    }

    /**
     * Returns the entry modules.
     * <p>
     *
     * @return a list of ModuleImpl elements with the entry modules, an empty list if none.
     *
     */
    @Override
    public List<Module> getModules() {
        modules = Lists.createWhenNull(modules);
        if (ModuleUtils.getModule(modules, DCModule.URI) == null) {
            modules.add(new DCModuleImpl());
        }
        return modules;
    }

    /**
     * Sets the entry modules.
     * <p>
     *
     * @param modules the list of ModuleImpl elements with the entry modules to set, an empty list
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
        return ModuleUtils.getModule(getModules(), uri);
    }

    /**
     * Returns the Dublin Core module of the feed.
     *
     * @return the DC module, it's never <b>null</b>
     *
     */
    private DCModule getDCModule() {
        return (DCModule) getModule(DCModule.URI);
    }

    @Override
    public Class<SyndEntry> getInterface() {
        return SyndEntry.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    /**
     * Returns the links
     * <p>
     *
     * @return Returns the links.
     */
    @Override
    public List<SyndLink> getLinks() {
        return links = Lists.createWhenNull(links);
    }

    /**
     * Set the links
     * <p>
     *
     * @param links The links to set.
     */
    @Override
    public void setLinks(final List<SyndLink> links) {
        this.links = links;
    }

    /**
     * Returns the updatedDate
     * <p>
     *
     * @return Returns the updatedDate.
     */
    @Override
    public Date getUpdatedDate() {
        return Dates.copy(updatedDate);
    }

    /**
     * Set the updatedDate
     * <p>
     *
     * @param updatedDate The updatedDate to set.
     */
    @Override
    public void setUpdatedDate(final Date updatedDate) {
        this.updatedDate = new Date(updatedDate.getTime());
    }

    @Override
    public List<SyndPerson> getAuthors() {
        return authors = Lists.createWhenNull(authors);
    }

    @Override
    public void setAuthors(final List<SyndPerson> authors) {
        this.authors = authors;
    }

    /**
     * Returns the entry author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     *
     * @return the entry author, empty String if none.
     *
     */
    @Override
    public String getAuthor() {

        String author;

        // Start out looking for one or more authors in authors. For non-Atom
        // feeds, authors may actually be null.
        if (Lists.isNotEmpty(authors)) {
            author = authors.get(0).getName();
        } else {
            author = getDCModule().getCreator();
        }

        if (author == null) {
            author = "";
        }

        return author;

    }

    /**
     * Sets the entry author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     *
     * @param author the entry author to set, <b>null</b> if none.
     *
     */
    @Override
    public void setAuthor(final String author) {
        // Get the DCModule so that we can check to see if "creator" is already set.
        final DCModule dcModule = getDCModule();
        final String currentValue = dcModule.getCreator();

        if (Strings.isEmpty(currentValue)) {
            getDCModule().setCreator(author);
        }
    }

    @Override
    public List<SyndPerson> getContributors() {
        return contributors = Lists.createWhenNull(contributors);
    }

    @Override
    public void setContributors(final List<SyndPerson> contributors) {
        this.contributors = contributors;
    }

    @Override
    public SyndFeed getSource() {
        return source;
    }

    @Override
    public void setSource(final SyndFeed source) {
        this.source = source;
    }

    /**
     * Returns foreign markup found at channel level.
     * <p>
     *
     * @return list of JDOM nodes containing channel-level foreign markup, an empty list if none.
     *
     */
    @Override
    public List<Element> getForeignMarkup() {
        return foreignMarkup = Lists.createWhenNull(foreignMarkup);
    }

    /**
     * Sets foreign markup found at channel level.
     * <p>
     *
     * @param foreignMarkup list of JDOM nodes containing channel-level foreign markup, an empty
     *            list if none.
     *
     */
    @Override
    public void setForeignMarkup(final List<Element> foreignMarkup) {
        this.foreignMarkup = foreignMarkup;
    }

    @Override
    public String getComments() {
        return comments;
    }

    @Override
    public void setComments(final String comments) {
        this.comments = comments;
    }

    @Override
    public Object getWireEntry() {
        return wireEntry;
    }

    public void setWireEntry(final Object wireEntry) {
        this.wireEntry = wireEntry;
    }

    @Override
    public SyndLink findRelatedLink(final String relation) {
        final List<SyndLink> syndLinks = getLinks();
        for (final SyndLink syndLink : syndLinks) {
            if (relation.equals(syndLink.getRel())) {
                return syndLink;
            }
        }
        return null;
    }

}
