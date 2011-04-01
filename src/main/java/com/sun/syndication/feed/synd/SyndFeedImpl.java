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
package com.sun.syndication.feed.synd;

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.impl.CopyFromHelper;
import com.sun.syndication.feed.WireFeed;
import com.sun.syndication.feed.module.*;
import com.sun.syndication.feed.module.impl.ModuleUtils;
import com.sun.syndication.feed.synd.impl.Converters;
import com.sun.syndication.feed.synd.impl.URINormalizer;

import java.util.*;
import java.io.Serializable;

/**
 * Bean for all types of feeds.
 * <p>
 * It handles all RSS versions, Atom 0.3 and Atom 1.0, it normalizes all info, it may lose information.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class SyndFeedImpl implements Serializable, SyndFeed {
    
    private ObjectBean _objBean;
    
    private String    _encoding;
    private String    _uri;
    private SyndContent _title;
    private SyndContent _description;
    private String    _feedType;
    private String    _link;
    private List      _links;
    private SyndImage _image;
    private List      _entries;
    private List      _modules;
    private List      _authors;
    private List      _contributors;
    private List      _foreignMarkup;
    
    private WireFeed wireFeed = null;
    private boolean preserveWireFeed = false;

    private static final Converters CONVERTERS = new Converters();

    private static final Set IGNORE_PROPERTIES = new HashSet();

    /**
     * Unmodifiable Set containing the convenience properties of this class.
     * <p>
     * Convenience properties are mapped to Modules, for cloning the convenience properties
     * can be ignored as the will be copied as part of the module cloning.
     */

    public static final Set CONVENIENCE_PROPERTIES = Collections.unmodifiableSet(IGNORE_PROPERTIES);

    static {
        IGNORE_PROPERTIES.add("publishedDate");
        IGNORE_PROPERTIES.add("author");
        IGNORE_PROPERTIES.add("copyright");
        IGNORE_PROPERTIES.add("categories");
        IGNORE_PROPERTIES.add("language");
    }

    /**
     * Returns the real feed types the SyndFeedImpl supports when converting from and to.
     * <p>
     * @return the real feed type supported.
     */
    public List getSupportedFeedTypes() {
        return CONVERTERS.getSupportedFeedTypes();
    }

    /**
     * For implementations extending SyndFeedImpl to be able to use the ObjectBean functionality
     * with extended interfaces.
     * <p>
     * @param beanClass
     * @param convenienceProperties set containing the convenience properties of the SyndEntryImpl
     * (the are ignored during cloning, check CloneableBean for details).
     *
     */
    protected SyndFeedImpl(Class beanClass,Set convenienceProperties) {
        _objBean = new ObjectBean(beanClass,this,convenienceProperties);
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public SyndFeedImpl() {
        this(null);
    }

    /**
     * Creates a SyndFeedImpl and populates all its properties out of the
     * given RSS Channel or Atom Feed properties.
     * <p>
     * @param feed the RSS Channel or the Atom Feed to populate the properties from.
     *
     */
    public SyndFeedImpl(WireFeed feed) {
        this(feed, false);
    }
    
    /**
     * Creates a SyndFeedImpl and populates all its properties out of the
     * given RSS Channel or Atom Feed properties, while optionally preserving
     * the WireFeed for access via the orignalWireFeed() method.
     * 
     * @param feed
     * @param preserveWireFeed
     */
    public SyndFeedImpl(WireFeed feed, boolean preserveWireFeed) {    	
        this(SyndFeed.class,IGNORE_PROPERTIES);

    	if (preserveWireFeed) {    		
    		this.wireFeed = feed;
    		this.preserveWireFeed = preserveWireFeed;
    	}
                
        if (feed!=null) {
            _feedType = feed.getFeedType();
            Converter converter = CONVERTERS.getConverter(_feedType);
            if (converter==null) {
                throw new IllegalArgumentException("Invalid feed type ["+_feedType+"]");
            }
            converter.copyInto(feed,this);
        }
        
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    public Object clone() throws CloneNotSupportedException {
        return _objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals() method.
     * <p>
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        // can't use foreign markup in equals, due to JDOM equals impl
        Object fm = getForeignMarkup();
        setForeignMarkup(((SyndFeedImpl)other).getForeignMarkup());       
        boolean ret = _objBean.equals(other);
        // restore foreign markup
        setForeignMarkup(fm);
        return ret;
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     * @return the hashcode of the bean object.
     *
     */
    public int hashCode() {
        return _objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     * @return String representation for the object.
     *
     */
    public String toString() {
        return _objBean.toString();
    }

    /**
     * Creates a real feed containing the information of the SyndFeedImpl.
     * <p>
     * The feed type of the created WireFeed is taken from the SyndFeedImpl feedType property.
     * <p>
     * @return the real feed.
     *
     */
    public WireFeed createWireFeed() {
        return createWireFeed(_feedType);
    }

    /**
     * Creates a real feed containing the information of the SyndFeedImpl.
     * <p>
     * @param feedType the feed type for the WireFeed to be created.
     * @return the real feed.
     *
     */
    public WireFeed createWireFeed(String feedType) {
        if (feedType==null) {
            throw new IllegalArgumentException("Feed type cannot be null");
        }
        Converter converter = CONVERTERS.getConverter(feedType);
        if (converter==null) {
            throw new IllegalArgumentException("Invalid feed type ["+feedType+"]");
        }
        return converter.createRealFeed(this);
    }
    
    /**
     * Returns the WireFeed this SyndFeed was created from. 
     * Will return null if the original feed is not stored or if this SyndFeed was not created from a WireFeed.
     * <br />
     * Note: The wire feed returned here will NOT contain any modifications done on this SyndFeed since it was created.
     * That is in contrast to the createWireFeed method, which will reflect the current state of the SyndFeed
     * 
     * @return The WireFeed this was created from, or null
     * 
     */    
    public WireFeed originalWireFeed() {
    	return wireFeed;
    }

    /**
     * Returns the wire feed type the feed had/will-have when coverted from/to a WireFeed.
     * <p>
     * @return the feed type, <b>null</b> if none.
     *
     */
    public String getFeedType() {
        return _feedType;
    }

    /**
     * Sets the wire feed type the feed will-have when coverted to a WireFeed.
     * <p>
     * @param feedType the feed type to set, <b>null</b> if none.
     *
     */
    public void setFeedType(String feedType) {
        _feedType = feedType;
    }

    /**
     * Returns the charset encoding of a the feed. This is not set by Rome parsers.
     * <p>
     * @return the charset encoding of the feed.
     *
     */
    public String getEncoding() {
        return _encoding;
    }

    /**
     * Sets the charset encoding of a the feed. This is not set by Rome parsers.
     * <p>
     * @param encoding the charset encoding of the feed.
     *
     */
    public void setEncoding(String encoding) {
        _encoding = encoding;
    }

    /**
     * Returns the feed URI.
     * <p>
     * How the feed URI maps to a concrete feed type (RSS or Atom) depends on
     * the concrete feed type. This is explained in detail in Rome documentation,
     * <a href="http://wiki.java.net/bin/edit/Javawsxml/Rome04URIMapping">Feed and entry URI mapping</a>.
     * <p>
     * The returned URI is a normalized URI as specified in RFC 2396bis.
     * <p>
     * Note: The URI is the unique identifier, in the RSS 2.0/atom case this is
     * the GUID, for RSS 1.0 this is the URI attribute of the item. The Link
     * is the URL that the item is accessible under, the URI is the
     * permanent identifier which the aggregator should use to reference
     * this item. Often the URI will use some standardized identifier scheme
     * such as DOI's so that items can be identified even if they appear in
     * multiple feeds with different "links" (they might be on different
     * hosting platforms but be the same item). Also, though rare, there
     * could be multiple items with the same link but a different URI and
     * associated metadata which need to be treated as distinct entities.
     * In the RSS 1.0 case the URI must be a valid RDF URI reference.
     * <p>
     * @return the feed URI, <b>null</b> if none.
     *
     */
    public String getUri() {
        return _uri;
    }

    /**
     * Sets the feed URI.
     * <p>
     * How the feed URI maps to a concrete feed type (RSS or Atom) depends on
     * the concrete feed type. This is explained in detail in Rome documentation,
     * <a href="http://wiki.java.net/bin/edit/Javawsxml/Rome04URIMapping">Feed and entry URI mapping</a>.
     * <p>
     * Note: The URI is the unique identifier, in the RSS 2.0/atom case this is
     * the GUID, for RSS 1.0 this is the URI attribute of the item. The Link
     * is the URL that the item is accessible under, the URI is the
     * permanent identifier which the aggregator should use to reference
     * this item. Often the URI will use some standardized identifier scheme
     * such as DOI's so that items can be identified even if they appear in
     * multiple feeds with different "links" (they might be on different
     * hosting platforms but be the same item). Also, though rare, there
     * could be multiple items with the same link but a different URI and
     * associated metadata which need to be treated as distinct entities.
     * In the RSS 1.0 case the URI must be a valid RDF URI reference.
     * <p>
     * @param uri the feed URI to set, <b>null</b> if none.
     *
     */
    public void setUri(String uri) {
        _uri = URINormalizer.normalize(uri);
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
        if (_title == null) _title = new SyndContentImpl();
        _title.setValue(title);
    }

    /**
     * Returns the feed title as a text construct.
     * <p>
     * @return the feed title, <b>null</b> if none.
     *
     */
    public SyndContent getTitleEx() {
        return _title;
    }

    /**
     * Sets the feed title as a text construct.
     * <p>
     * @param title the feed title to set, <b>null</b> if none.
     *
     */
    public void setTitleEx(SyndContent title) {
        _title = title;
    }

    /**
     * Returns the feed link.
     * <p>
     * Note: The URI is the unique identifier, in the RSS 2.0/atom case this is
     * the GUID, for RSS 1.0 this is the URI attribute of the item. The Link
     * is the URL that the item is accessible under, the URI is the
     * permanent identifier which the aggregator should use to reference
     * this item. Often the URI will use some standardized identifier scheme
     * such as DOI's so that items can be identified even if they appear in
     * multiple feeds with different "links" (they might be on different
     * hosting platforms but be the same item). Also, though rare, there
     * could be multiple items with the same link but a different URI and
     * associated metadata which need to be treated as distinct entities.
     * In the RSS 1.0 case the URI must be a valid RDF URI reference.
     * <p>
     * @return the feed link, <b>null</b> if none.
     *
     */
    public String getLink() {
        return _link;
    }

    /**
     * Sets the feed link.
     * <p>
     * Note: The URI is the unique identifier, in the RSS 2.0/atom case this is
     * the GUID, for RSS 1.0 this is the URI attribute of the item. The Link
     * is the URL that the item is accessible under, the URI is the
     * permanent identifier which the aggregator should use to reference
     * this item. Often the URI will use some standardized identifier scheme
     * such as DOI's so that items can be identified even if they appear in
     * multiple feeds with different "links" (they might be on different
     * hosting platforms but be the same item). Also, though rare, there
     * could be multiple items with the same link but a different URI and
     * associated metadata which need to be treated as distinct entities.
     * In the RSS 1.0 case the URI must be a valid RDF URI reference.
     * <p>
     * @param link the feed link to set, <b>null</b> if none.
     *
     */
    public void setLink(String link) {
        _link = link;
    }

    /**
     * Returns the feed description.
     * <p>
     * @return the feed description, <b>null</b> if none.
     *
     */
    public String getDescription() {
        if (_description != null) return _description.getValue();
        return null;
    }

    /**
     * Sets the feed description.
     * <p>
     * @param description the feed description to set, <b>null</b> if none.
     *
     */
    public void setDescription(String description) {
        if (_description == null) _description = new SyndContentImpl();
        _description.setValue(description);
    }
    
    /**
     * Returns the feed description as a text construct.
     * <p>
     * @return the feed description, <b>null</b> if none.
     *
     */
    public SyndContent getDescriptionEx() {
        return _description;
    }

    /**
     * Sets the feed description as a text construct.
     * <p>
     * @param description the feed description to set, <b>null</b> if none.
     *
     */
    public void setDescriptionEx(SyndContent description) {
        _description = description;
    }

    /**
     * Returns the feed published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     * @return the feed published date, <b>null</b> if none.
     *
     */
    public Date getPublishedDate() {
        return getDCModule().getDate();
    }

    /**
     * Sets the feed published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     * @param publishedDate the feed published date to set, <b>null</b> if none.
     *
     */
    public void setPublishedDate(Date publishedDate) {
        getDCModule().setDate(publishedDate);
    }

    /**
     * Returns the feed copyright.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module rights.
     * <p>
     * @return the feed copyright, <b>null</b> if none.
     *
     */
    public String getCopyright() {
        return getDCModule().getRights();
    }

    /**
     * Sets the feed copyright.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module rights.
     * <p>
     * @param copyright the feed copyright to set, <b>null</b> if none.
     *
     */
    public void setCopyright(String copyright) {
        getDCModule().setRights(copyright);
    }

    /**
     * Returns the feed image.
     * <p>
     * @return the feed image, <b>null</b> if none.
     *
     */
    public SyndImage getImage() {
        return _image;
    }

    /**
     * Sets the feed image.
     * <p>
     * @param image the feed image to set, <b>null</b> if none.
     *
     */
    public void setImage(SyndImage image) {
        _image = image;
    }

    /**
     * Returns the feed categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     * @return a list of SyndCategoryImpl elements with the feed categories,
     *         an empty list if none.
     *
     */
    public List getCategories() {
        return new SyndCategoryListFacade(getDCModule().getSubjects());
    }

    /**
     * Sets the feed categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     * @param categories the list of SyndCategoryImpl elements with the feed categories to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setCategories(List categories) {
        getDCModule().setSubjects(SyndCategoryListFacade.convertElementsSyndCategoryToSubject(categories));
    }

    /**
     * Returns the feed entries.
     * <p>
     * @return a list of SyndEntryImpl elements with the feed entries,
     *         an empty list if none.
     *
     */
    public List getEntries() {
        return (_entries==null) ? (_entries=new ArrayList()) : _entries;
    }

    /**
     * Sets the feed entries.
     * <p>
     * @param entries the list of SyndEntryImpl elements with the feed entries to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setEntries(List entries) {
        _entries = entries;
    }

    /**
     * Returns the feed language.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module language.
     * <p>
     * @return the feed language, <b>null</b> if none.
     *
     */
    public String getLanguage() {
        return getDCModule().getLanguage();
    }

    /**
     * Sets the feed language.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module language.
     * <p>
     * @param language the feed language to set, <b>null</b> if none.
     *
     */
    public void setLanguage(String language) {
        getDCModule().setLanguage(language);
    }

    /**
     * Returns the feed modules.
     * <p>
     * @return a list of ModuleImpl elements with the feed modules,
     *         an empty list if none.
     *
     */
    public List getModules() {
        if  (_modules==null) {
            _modules=new ArrayList();
        }
        if (ModuleUtils.getModule(_modules,DCModule.URI)==null) {
            _modules.add(new DCModuleImpl());
        }
        return _modules;
    }


    /**
     * Sets the feed modules.
     * <p>
     * @param modules the list of ModuleImpl elements with the feed modules to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setModules(List modules) {
        _modules = modules;
    }

    /**
     * Returns the module identified by a given URI.
     * <p>
     * @param uri the URI of the ModuleImpl.
     * @return The module with the given URI, <b>null</b> if none.
     */
    public Module getModule(String uri) {
        return ModuleUtils.getModule(getModules(),uri);
    }

    /**
     * Returns the Dublin Core module of the feed.
     * @return the DC module, it's never <b>null</b>
     *
     */
    private DCModule getDCModule() {
        return (DCModule) getModule(DCModule.URI);
    }

    public Class getInterface() {
        return SyndFeed.class;
    }

    public void copyFrom(CopyFrom obj) {
        COPY_FROM_HELPER.copy(this,obj);
    }


    // TODO We need to find out how to refactor this one in a nice reusable way.

    private static final CopyFromHelper COPY_FROM_HELPER;

    static {
        Map basePropInterfaceMap = new HashMap();
        basePropInterfaceMap.put("feedType",String.class);
        basePropInterfaceMap.put("encoding",String.class);
        basePropInterfaceMap.put("uri",String.class);
        basePropInterfaceMap.put("title",String.class);
        basePropInterfaceMap.put("link",String.class);
        basePropInterfaceMap.put("description",String.class);
        basePropInterfaceMap.put("image",SyndImage.class);
        basePropInterfaceMap.put("entries",SyndEntry.class);
        basePropInterfaceMap.put("modules",Module.class);

        Map basePropClassImplMap = new HashMap();
        basePropClassImplMap.put(SyndEntry.class,SyndEntryImpl.class);
        basePropClassImplMap.put(SyndImage.class,SyndImageImpl.class);
        basePropClassImplMap.put(DCModule.class,DCModuleImpl.class);
        basePropClassImplMap.put(SyModule.class,SyModuleImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(SyndFeed.class,basePropInterfaceMap,basePropClassImplMap);
    }

    /**
     * Returns the links
     * <p>
     * @return Returns the links.
     */
    public List getLinks() {
        return (_links==null) ? (_links=new ArrayList()) : _links;
    }
    
    /**
     * Set the links
     * <p>
     * @param links The links to set.
     */
    public void setLinks(List links) {
        _links = links;
    }

    public List getAuthors() {
        return (_authors==null) ? (_authors=new ArrayList()) : _authors;
    }

    public void setAuthors(List authors) {
        this._authors = authors;
    }

    /**
     * Returns the feed author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     * @return the feed author, <b>null</b> if none.
     *
     */
    public String getAuthor() {
        return getDCModule().getCreator();
    }

    /**
     * Sets the feed author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     * @param author the feed author to set, <b>null</b> if none.
     *
     */
    public void setAuthor(String author) {
        getDCModule().setCreator(author);
    }    
    
    public List getContributors() {
        return (_contributors==null) ? (_contributors=new ArrayList()) : _contributors;
    }

    public void setContributors(List contributors) {
        this._contributors = contributors;
    }

    /**
     * Returns foreign markup found at channel level.
     * <p>
     * @return Opaque object to discourage use
     *
     */
    public Object getForeignMarkup() {
        return (_foreignMarkup==null) ? (_foreignMarkup=new ArrayList()) : _foreignMarkup;
    }

    /**
     * Sets foreign markup found at channel level.
     * <p>
     * @param foreignMarkup Opaque object to discourage use
     *
     */
    public void setForeignMarkup(Object foreignMarkup) {
        _foreignMarkup = (List)foreignMarkup;
    }

	public boolean isPreservingWireFeed() {		
		return preserveWireFeed;
	}
}
