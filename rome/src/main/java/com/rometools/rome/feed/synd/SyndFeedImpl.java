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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom2.Element;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.WireFeed;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;
import com.rometools.rome.feed.module.DCModule;
import com.rometools.rome.feed.module.DCModuleImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.module.SyModule;
import com.rometools.rome.feed.module.SyModuleImpl;
import com.rometools.rome.feed.module.impl.ModuleUtils;
import com.rometools.rome.feed.synd.impl.Converters;
import com.rometools.rome.feed.synd.impl.URINormalizer;
import com.rometools.utils.Lists;

/**
 * Bean for all types of feeds.
 * <p>
 * It handles all RSS versions, Atom 0.3 and Atom 1.0, it normalizes all info, it may lose
 * information.
 */
public class SyndFeedImpl implements Serializable, SyndFeed {

    private static final long serialVersionUID = 1L;

    private static final CopyFromHelper COPY_FROM_HELPER;

    private final ObjectBean objBean;

    private String encoding;
    private String uri;
    private SyndContent title;
    private SyndContent description;
    private String feedType;
    private String link;
    private String webMaster;
    private String managingEditor;
    private String docs;
    private String generator;
    private String styleSheet;
    private List<SyndLink> links;
    private SyndImage image;
    private List<SyndEntry> entries;
    private List<Module> modules;
    private List<SyndPerson> authors;
    private List<SyndPerson> contributors;
    private List<Element> foreignMarkup;

    private WireFeed wireFeed = null;
    private boolean preserveWireFeed = false;

    private static final Converters CONVERTERS = new Converters();

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
        IGNORE_PROPERTIES.add("copyright");
        IGNORE_PROPERTIES.add("categories");
        IGNORE_PROPERTIES.add("language");

        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("feedType", String.class);
        basePropInterfaceMap.put("encoding", String.class);
        basePropInterfaceMap.put("uri", String.class);
        basePropInterfaceMap.put("title", String.class);
        basePropInterfaceMap.put("link", String.class);
        basePropInterfaceMap.put("description", String.class);
        basePropInterfaceMap.put("image", SyndImage.class);
        basePropInterfaceMap.put("entries", SyndEntry.class);
        basePropInterfaceMap.put("modules", Module.class);
        basePropInterfaceMap.put("categories", SyndCategory.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();
        basePropClassImplMap.put(SyndEntry.class, SyndEntryImpl.class);
        basePropClassImplMap.put(SyndImage.class, SyndImageImpl.class);
        basePropClassImplMap.put(SyndCategory.class, SyndCategoryImpl.class);
        basePropClassImplMap.put(DCModule.class, DCModuleImpl.class);
        basePropClassImplMap.put(SyModule.class, SyModuleImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(SyndFeed.class, basePropInterfaceMap, basePropClassImplMap);

    }

    /**
     * Returns the real feed types the SyndFeedImpl supports when converting from and to.
     * <p>
     *
     * @return the real feed type supported.
     */
    @Override
    public List<String> getSupportedFeedTypes() {
        return CONVERTERS.getSupportedFeedTypes();
    }

    /**
     * For implementations extending SyndFeedImpl to be able to use the ObjectBean functionality
     * with extended interfaces.
     * <p>
     *
     * @param beanClass
     * @param convenienceProperties set containing the convenience properties of the SyndEntryImpl
     *            (the are ignored during cloning, check CloneableBean for details).
     *
     */
    protected SyndFeedImpl(final Class<?> beanClass, final Set<String> convenienceProperties) {
        objBean = new ObjectBean(beanClass, this, convenienceProperties);
    }

    public SyndFeedImpl() {
        this(null);
    }

    /**
     * Creates a SyndFeedImpl and populates all its properties out of the given RSS Channel or Atom
     * Feed properties.
     * <p>
     *
     * @param feed the RSS Channel or the Atom Feed to populate the properties from.
     *
     */
    public SyndFeedImpl(final WireFeed feed) {
        this(feed, false);
    }

    /**
     * Creates a SyndFeedImpl and populates all its properties out of the given RSS Channel or Atom
     * Feed properties, while optionally preserving the WireFeed for access via the
     * orignalWireFeed() method.
     */
    public SyndFeedImpl(final WireFeed feed, final boolean preserveWireFeed) {
        this(SyndFeed.class, IGNORE_PROPERTIES);

        if (preserveWireFeed) {
            wireFeed = feed;
            this.preserveWireFeed = preserveWireFeed;
        }

        if (feed != null) {
            feedType = feed.getFeedType();
            final Converter converter = CONVERTERS.getConverter(feedType);
            if (converter == null) {
                throw new IllegalArgumentException("Invalid feed type [" + feedType + "]");
            }
            converter.copyInto(feed, this);
        }

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
        if (other == null || !(other instanceof SyndFeedImpl)) {
            return false;
        }
        // can't use foreign markup in equals, due to JDOM equals impl
        final List<Element> fm = getForeignMarkup();
        setForeignMarkup(((SyndFeedImpl) other).getForeignMarkup());
        final boolean ret = objBean.equals(other);
        setForeignMarkup(fm); // restore foreign markup
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
     * Creates a real feed containing the information of the SyndFeedImpl.
     * <p>
     * The feed type of the created WireFeed is taken from the SyndFeedImpl feedType property.
     * <p>
     *
     * @return the real feed.
     *
     */
    @Override
    public WireFeed createWireFeed() {
        return this.createWireFeed(feedType);
    }

    /**
     * Creates a real feed containing the information of the SyndFeedImpl.
     * <p>
     *
     * @param feedType the feed type for the WireFeed to be created.
     * @return the real feed.
     *
     */
    @Override
    public WireFeed createWireFeed(final String feedType) {

        if (feedType == null) {
            throw new IllegalArgumentException("Feed type cannot be null");
        }

        final Converter converter = CONVERTERS.getConverter(feedType);
        if (converter == null) {
            throw new IllegalArgumentException("Invalid feed type [" + feedType + "]");
        }

        return converter.createRealFeed(this);

    }

    /**
     * Returns the WireFeed this SyndFeed was created from. Will return null if the original feed is
     * not stored or if this SyndFeed was not created from a WireFeed. <br />
     * Note: The wire feed returned here will NOT contain any modifications done on this SyndFeed
     * since it was created. That is in contrast to the createWireFeed method, which will reflect
     * the current state of the SyndFeed
     *
     * @return The WireFeed this was created from, or null
     *
     */
    @Override
    public WireFeed originalWireFeed() {
        return wireFeed;
    }

    /**
     * Returns the wire feed type the feed had/will-have when coverted from/to a WireFeed.
     * <p>
     *
     * @return the feed type, <b>null</b> if none.
     *
     */
    @Override
    public String getFeedType() {
        return feedType;
    }

    /**
     * Sets the wire feed type the feed will-have when coverted to a WireFeed.
     * <p>
     *
     * @param feedType the feed type to set, <b>null</b> if none.
     *
     */
    @Override
    public void setFeedType(final String feedType) {
        this.feedType = feedType;
    }

    /**
     * Returns the charset encoding of a the feed. This is not set by Rome parsers.
     * <p>
     *
     * @return the charset encoding of the feed.
     *
     */
    @Override
    public String getEncoding() {
        return encoding;
    }

    /**
     * Sets the charset encoding of a the feed. This is not set by Rome parsers.
     * <p>
     *
     * @param encoding the charset encoding of the feed.
     *
     */
    @Override
    public void setEncoding(final String encoding) {
        this.encoding = encoding;
    }

    /**
     * Returns the feed URI.
     * <p>
     * How the feed URI maps to a concrete feed type (RSS or Atom) depends on the concrete feed
     * type. This is explained in detail in Rome documentation, <a
     * href="http://wiki.java.net/bin/edit/Javawsxml/Rome04URIMapping">Feed and entry URI
     * mapping</a>.
     * <p>
     * The returned URI is a normalized URI as specified in RFC 2396bis.
     * <p>
     * Note: The URI is the unique identifier, in the RSS 2.0/atom case this is the GUID, for RSS
     * 1.0 this is the URI attribute of the item. The Link is the URL that the item is accessible
     * under, the URI is the permanent identifier which the aggregator should use to reference this
     * item. Often the URI will use some standardized identifier scheme such as DOI's so that items
     * can be identified even if they appear in multiple feeds with different "links" (they might be
     * on different hosting platforms but be the same item). Also, though rare, there could be
     * multiple items with the same link but a different URI and associated metadata which need to
     * be treated as distinct entities. In the RSS 1.0 case the URI must be a valid RDF URI
     * reference.
     * <p>
     *
     * @return the feed URI, <b>null</b> if none.
     *
     */
    @Override
    public String getUri() {
        return uri;
    }

    /**
     * Sets the feed URI.
     * <p>
     * How the feed URI maps to a concrete feed type (RSS or Atom) depends on the concrete feed
     * type. This is explained in detail in Rome documentation, <a
     * href="http://wiki.java.net/bin/edit/Javawsxml/Rome04URIMapping">Feed and entry URI
     * mapping</a>.
     * <p>
     * Note: The URI is the unique identifier, in the RSS 2.0/atom case this is the GUID, for RSS
     * 1.0 this is the URI attribute of the item. The Link is the URL that the item is accessible
     * under, the URI is the permanent identifier which the aggregator should use to reference this
     * item. Often the URI will use some standardized identifier scheme such as DOI's so that items
     * can be identified even if they appear in multiple feeds with different "links" (they might be
     * on different hosting platforms but be the same item). Also, though rare, there could be
     * multiple items with the same link but a different URI and associated metadata which need to
     * be treated as distinct entities. In the RSS 1.0 case the URI must be a valid RDF URI
     * reference.
     * <p>
     *
     * @param uri the feed URI to set, <b>null</b> if none.
     *
     */
    @Override
    public void setUri(final String uri) {
        this.uri = URINormalizer.normalize(uri);
    }

    /**
     * Returns the feed title.
     * <p>
     *
     * @return the feed title, <b>null</b> if none.
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
     * Sets the feed title.
     * <p>
     *
     * @param title the feed title to set, <b>null</b> if none.
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
     * Returns the feed title as a text construct.
     * <p>
     *
     * @return the feed title, <b>null</b> if none.
     *
     */
    @Override
    public SyndContent getTitleEx() {
        return title;
    }

    /**
     * Sets the feed title as a text construct.
     * <p>
     *
     * @param title the feed title to set, <b>null</b> if none.
     *
     */
    @Override
    public void setTitleEx(final SyndContent title) {
        this.title = title;
    }

    /**
     * Returns the feed link.
     * <p>
     * Note: The URI is the unique identifier, in the RSS 2.0/atom case this is the GUID, for RSS
     * 1.0 this is the URI attribute of the item. The Link is the URL that the item is accessible
     * under, the URI is the permanent identifier which the aggregator should use to reference this
     * item. Often the URI will use some standardized identifier scheme such as DOI's so that items
     * can be identified even if they appear in multiple feeds with different "links" (they might be
     * on different hosting platforms but be the same item). Also, though rare, there could be
     * multiple items with the same link but a different URI and associated metadata which need to
     * be treated as distinct entities. In the RSS 1.0 case the URI must be a valid RDF URI
     * reference.
     * <p>
     *
     * @return the feed link, <b>null</b> if none.
     *
     */
    @Override
    public String getLink() {
        return link;
    }

    /**
     * Sets the feed link.
     * <p>
     * Note: The URI is the unique identifier, in the RSS 2.0/atom case this is the GUID, for RSS
     * 1.0 this is the URI attribute of the item. The Link is the URL that the item is accessible
     * under, the URI is the permanent identifier which the aggregator should use to reference this
     * item. Often the URI will use some standardized identifier scheme such as DOI's so that items
     * can be identified even if they appear in multiple feeds with different "links" (they might be
     * on different hosting platforms but be the same item). Also, though rare, there could be
     * multiple items with the same link but a different URI and associated metadata which need to
     * be treated as distinct entities. In the RSS 1.0 case the URI must be a valid RDF URI
     * reference.
     * <p>
     *
     * @param link the feed link to set, <b>null</b> if none.
     *
     */
    @Override
    public void setLink(final String link) {
        this.link = link;
    }

    /**
     * Returns the feed description.
     * <p>
     *
     * @return the feed description, <b>null</b> if none.
     *
     */
    @Override
    public String getDescription() {
        if (description != null) {
            return description.getValue();
        }
        return null;
    }

    /**
     * Sets the feed description.
     * <p>
     *
     * @param description the feed description to set, <b>null</b> if none.
     *
     */
    @Override
    public void setDescription(final String description) {
        if (this.description == null) {
            this.description = new SyndContentImpl();
        }
        this.description.setValue(description);
    }

    /**
     * Returns the feed description as a text construct.
     * <p>
     *
     * @return the feed description, <b>null</b> if none.
     *
     */
    @Override
    public SyndContent getDescriptionEx() {
        return description;
    }

    /**
     * Sets the feed description as a text construct.
     * <p>
     *
     * @param description the feed description to set, <b>null</b> if none.
     *
     */
    @Override
    public void setDescriptionEx(final SyndContent description) {
        this.description = description;
    }

    /**
     * Returns the feed published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     *
     * @return the feed published date, <b>null</b> if none.
     *
     */
    @Override
    public Date getPublishedDate() {
        return getDCModule().getDate();
    }

    /**
     * Sets the feed published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     *
     * @param publishedDate the feed published date to set, <b>null</b> if none.
     *
     */
    @Override
    public void setPublishedDate(final Date publishedDate) {
        getDCModule().setDate(publishedDate);
    }

    /**
     * Returns the feed copyright.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module rights.
     * <p>
     *
     * @return the feed copyright, <b>null</b> if none.
     *
     */
    @Override
    public String getCopyright() {
        return getDCModule().getRights();
    }

    /**
     * Sets the feed copyright.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module rights.
     * <p>
     *
     * @param copyright the feed copyright to set, <b>null</b> if none.
     *
     */
    @Override
    public void setCopyright(final String copyright) {
        getDCModule().setRights(copyright);
    }

    /**
     * Returns the feed image.
     * <p>
     *
     * @return the feed image, <b>null</b> if none.
     *
     */
    @Override
    public SyndImage getImage() {
        return image;
    }

    /**
     * Sets the feed image.
     * <p>
     *
     * @param image the feed image to set, <b>null</b> if none.
     *
     */
    @Override
    public void setImage(final SyndImage image) {
        this.image = image;
    }

    /**
     * Returns the feed categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     *
     * @return a list of SyndCategoryImpl elements with the feed categories, an empty list if none.
     *
     */
    @Override
    public List<SyndCategory> getCategories() {
        return new SyndCategoryListFacade(getDCModule().getSubjects());
    }

    /**
     * Sets the feed categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     *
     * @param categories the list of SyndCategoryImpl elements with the feed categories to set, an
     *            empty list or <b>null</b> if none.
     *
     */
    @Override
    public void setCategories(final List<SyndCategory> categories) {
        getDCModule().setSubjects(SyndCategoryListFacade.convertElementsSyndCategoryToSubject(categories));
    }

    /**
     * Returns the feed entries.
     * <p>
     *
     * @return a list of SyndEntryImpl elements with the feed entries, an empty list if none.
     *
     */
    @Override
    public List<SyndEntry> getEntries() {
        return entries = Lists.createWhenNull(entries);
    }

    /**
     * Sets the feed entries.
     * <p>
     *
     * @param entries the list of SyndEntryImpl elements with the feed entries to set, an empty list
     *            or <b>null</b> if none.
     *
     */
    @Override
    public void setEntries(final List<SyndEntry> entries) {
        this.entries = entries;
    }

    /**
     * Returns the feed language.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module language.
     * <p>
     *
     * @return the feed language, <b>null</b> if none.
     *
     */
    @Override
    public String getLanguage() {
        return getDCModule().getLanguage();
    }

    /**
     * Sets the feed language.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module language.
     * <p>
     *
     * @param language the feed language to set, <b>null</b> if none.
     *
     */
    @Override
    public void setLanguage(final String language) {
        getDCModule().setLanguage(language);
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
        modules = Lists.createWhenNull(modules);
        if (ModuleUtils.getModule(modules, DCModule.URI) == null) {
            modules.add(new DCModuleImpl());
        }
        return modules;
    }

    /**
     * Sets the feed modules.
     * <p>
     *
     * @param modules the list of ModuleImpl elements with the feed modules to set, an empty list or
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
    public Class<SyndFeed> getInterface() {
        return SyndFeed.class;
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

    @Override
    public List<SyndPerson> getAuthors() {
        return authors = Lists.createWhenNull(authors);
    }

    @Override
    public void setAuthors(final List<SyndPerson> authors) {
        this.authors = authors;
    }

    /**
     * Returns the feed author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     *
     * @return the feed author, <b>null</b> if none.
     *
     */
    @Override
    public String getAuthor() {
        return getDCModule().getCreator();
    }

    /**
     * Sets the feed author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     *
     * @param author the feed author to set, <b>null</b> if none.
     *
     */
    @Override
    public void setAuthor(final String author) {
        getDCModule().setCreator(author);
    }

    @Override
    public List<SyndPerson> getContributors() {
        return contributors = Lists.createWhenNull(contributors);
    }

    @Override
    public void setContributors(final List<SyndPerson> contributors) {
        this.contributors = contributors;
    }

    /**
     * Returns foreign markup found at channel level.
     * <p>
     *
     * @return Opaque object to discourage use
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
     * @param foreignMarkup Opaque object to discourage use
     *
     */
    @Override
    public void setForeignMarkup(final List<Element> foreignMarkup) {
        this.foreignMarkup = foreignMarkup;
    }

    @Override
    public boolean isPreservingWireFeed() {
        return preserveWireFeed;
    }

    @Override
    public String getDocs() {
        return docs;
    }

    @Override
    public void setDocs(final String docs) {
        this.docs = docs;
    }

    @Override
    public String getGenerator() {
        return generator;
    }

    @Override
    public void setGenerator(final String generator) {
        this.generator = generator;
    }

    @Override
    public String getManagingEditor() {
        return managingEditor;
    }

    @Override
    public void setManagingEditor(final String managingEditor) {
        this.managingEditor = managingEditor;
    }

    @Override
    public String getWebMaster() {
        return webMaster;
    }

    @Override
    public void setWebMaster(final String webMaster) {
        this.webMaster = webMaster;
    }

    @Override
    public String getStyleSheet() {
        return styleSheet;
    }

    @Override
    public void setStyleSheet(final String styleSheet) {
        this.styleSheet = styleSheet;
    }

}
