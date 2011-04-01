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
import com.sun.syndication.feed.module.*;
import com.sun.syndication.feed.module.impl.ModuleUtils;
import com.sun.syndication.feed.synd.impl.URINormalizer;
import com.sun.syndication.feed.impl.CopyFromHelper;

import java.util.*;
import java.io.Serializable; 

/**
 * Bean for entries of SyndFeedImpl feeds.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class SyndEntryImpl implements Serializable,SyndEntry {
    private ObjectBean _objBean;
    private String _uri;
    private String _link;
    private Date _updatedDate;
    private SyndContent _title;       
    private SyndContent _description;
    private List<SyndLink> _links;
    private List<SyndContent> _contents; // deprecated by Atom 1.0
    private List<Module> _modules;
    private List<SyndEnclosure> _enclosures;
    private List<SyndPerson> _authors;
    private List<SyndPerson> _contributors;
    private SyndFeed _source;
    private List _foreignMarkup;
    private Object wireEntry; // com.sun.syndication.feed.atom.Entry or com.sun.syndication.feed.rss.Item
    
    // ISSUE: some converters assume this is never null
    private List _categories = new ArrayList(); 

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
    }

    /**
     * For implementations extending SyndEntryImpl to be able to use the ObjectBean functionality
     * with extended interfaces.
     * <p>
     * @param beanClass
     * @param convenienceProperties set containing the convenience properties of the SyndEntryImpl
     * (the are ignored during cloning, check CloneableBean for details).
     *
     */
    protected SyndEntryImpl(Class beanClass,Set convenienceProperties) {
        _objBean = new ObjectBean(beanClass,this,convenienceProperties);
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public SyndEntryImpl() {
        this(SyndEntry.class,IGNORE_PROPERTIES);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    @Override
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
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        // while ObjectBean does this check this method does a cast to obtain the foreign markup
        // so we need to check before doing so.
        if (!(other instanceof SyndEntryImpl)) {
            return false;
        }
        // can't use foreign markup in equals, due to JDOM equals impl
        Object fm = getForeignMarkup();
        setForeignMarkup(((SyndEntryImpl)other).getForeignMarkup());              
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
    @Override
    public int hashCode() {
        return _objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     * @return String representation for the object.
     *
     */
    @Override
    public String toString() {
        return _objBean.toString();
    }


    /**
     * Returns the entry URI.
     * <p>
     * How the entry URI maps to a concrete feed type (RSS or Atom) depends on
     * the concrete feed type. This is explained in detail in Rome documentation,
     * <a href="http://wiki.java.net/bin/edit/Javawsxml/Rome04URIMapping">Feed and entry URI mapping</a>.
     * <p>
     * The returned URI is a normalized URI as specified in RFC 2396bis.
     * <p>
     * @return the entry URI, <b>null</b> if none.
     *
     */
    public String getUri() {
        return _uri;
    }

    /**
     * Sets the entry URI.
     * <p>
     * How the entry URI maps to a concrete feed type (RSS or Atom) depends on
     * the concrete feed type. This is explained in detail in Rome documentation,
     * <a href="http://wiki.java.net/bin/edit/Javawsxml/Rome04URIMapping">Feed and entry URI mapping</a>.
     * <p>
     * @param uri the entry URI to set, <b>null</b> if none.
     *
     */
    public void setUri(String uri) {
        _uri = URINormalizer.normalize(uri);
    }

    /**
     * Returns the entry title.
     * <p>
     * @return the entry title, <b>null</b> if none.
     *
     */
    public String getTitle() {
        if (_title != null) return _title.getValue();
        return null;
    }

    /**
     * Sets the entry title.
     * <p>
     * @param title the entry title to set, <b>null</b> if none.
     *
     */
    public void setTitle(String title) {
        if (_title == null) _title = new SyndContentImpl();
        _title.setValue(title);
    }

    /**
     * Returns the entry title as a text construct.
     * <p>
     * @return the entry title, <b>null</b> if none.
     *
     */
    public SyndContent getTitleEx() {
        return _title;
    }

    /**
     * Sets the entry title as a text construct.
     * <p>
     * @param title the entry title to set, <b>null</b> if none.
     *
     */
    public void setTitleEx(SyndContent title) {
        _title = title;
    }

    /**
     * Returns the entry link.
     * <p>
     * @return the entry link, <b>null</b> if none.
     *
     */
    public String getLink() {
        return _link;
    }

    /**
     * Sets the entry link.
     * <p>
     * @param link the entry link to set, <b>null</b> if none.
     *
     */
    public void setLink(String link) {
        _link = link;
    }

    /**
     * Returns the entry description.
     * <p>
     * @return the entry description, <b>null</b> if none.
     *
     */
    public SyndContent getDescription() {
        return _description;
    }

    /**
     * Sets the entry description.
     * <p>
     * @param description the entry description to set, <b>null</b> if none.
     *
     */
    public void setDescription(SyndContent description) {
        _description = description;
    }

    /**
     * Returns the entry contents.
     * <p>
     * @return a list of SyndContentImpl elements with the entry contents,
     *         an empty list if none.
     *
     */
    public List<SyndContent> getContents() {
        return (_contents==null) ? (_contents=new ArrayList<SyndContent>()) : _contents;
    }

    /**
     * Sets the entry contents.
     * <p>
     * @param contents the list of SyndContentImpl elements with the entry contents to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setContents(List<SyndContent> contents) {
        _contents = contents;
    }

    /**
     * Returns the entry enclosures.
     * <p>
     * @return a list of SyndEnclosure elements with the entry enclosures,
     *         an empty list if none.
     *
     */
    public List<SyndEnclosure> getEnclosures() {
        return (_enclosures==null) ? (_enclosures=new ArrayList<SyndEnclosure>()) : _enclosures;
    }

    /**
     * Sets the entry enclosures.
     * <p>
     * @param enclosures the list of SyndEnclosure elements with the entry enclosures to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setEnclosures(List<SyndEnclosure> enclosures) {
        _enclosures = enclosures;
    }


    /**
     * Returns the entry published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     * @return the entry published date, <b>null</b> if none.
     *
     */
    public Date getPublishedDate() {
        return getDCModule().getDate();
    }

    /**
     * Sets the entry published date.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module date.
     * <p>
     * @param publishedDate the entry published date to set, <b>null</b> if none.
     *
     */
    public void setPublishedDate(Date publishedDate) {
        getDCModule().setDate(publishedDate);
    }

    /**
     * Returns the entry categories.
     * <p>
     * @return a list of SyndCategoryImpl elements with the entry categories,
     *         an empty list if none.
     *
     */
    public List<SyndCategory> getCategories() {
       return _categories;
    }

    /**
     * Sets the entry categories.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module subjects.
     * <p>
     * @param categories the list of SyndCategoryImpl elements with the entry categories to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setCategories(List<SyndCategory> categories) {
        _categories = categories;
    }

    /**
     * Returns the entry modules.
     * <p>
     * @return a list of ModuleImpl elements with the entry modules,
     *         an empty list if none.
     *
     */
    public List<Module> getModules() {
        if  (_modules==null) {
            _modules=new ArrayList<Module>();
        }
        if (ModuleUtils.getModule(_modules,DCModule.URI)==null) {
            _modules.add(new DCModuleImpl());
        }
        return _modules;
    }

    /**
     * Sets the entry modules.
     * <p>
     * @param modules the list of ModuleImpl elements with the entry modules to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setModules(List<Module> modules) {
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
        return SyndEntry.class;
    }

    public void copyFrom(CopyFrom obj) {
        COPY_FROM_HELPER.copy(this,obj);
    }

    private static final CopyFromHelper COPY_FROM_HELPER;

    static {
        Map basePropInterfaceMap = new HashMap();
        basePropInterfaceMap.put("uri",String.class);
        basePropInterfaceMap.put("title",String.class);
        basePropInterfaceMap.put("link",String.class);
        basePropInterfaceMap.put("uri",String.class);
        basePropInterfaceMap.put("description",SyndContent.class);
        basePropInterfaceMap.put("contents",SyndContent.class);
        basePropInterfaceMap.put("enclosures",SyndEnclosure.class);
        basePropInterfaceMap.put("modules",Module.class);

        Map basePropClassImplMap = new HashMap();
        basePropClassImplMap.put(SyndContent.class,SyndContentImpl.class);
        basePropClassImplMap.put(SyndEnclosure.class,SyndEnclosureImpl.class);
        basePropClassImplMap.put(DCModule.class,DCModuleImpl.class);
        basePropClassImplMap.put(SyModule.class,SyModuleImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(SyndEntry.class,basePropInterfaceMap,basePropClassImplMap);
    }

    /**
     * Returns the links
     * <p>
     * @return Returns the links.
     */
    public List<SyndLink> getLinks() {
        return (_links==null) ? (_links=new ArrayList<SyndLink>()) : _links;
    }
    
    /**
     * Set the links
     * <p>
     * @param links The links to set.
     */
    public void setLinks(List links) {
        _links = links;
    }    
    
    /**
     * Returns the updatedDate
     * <p>
     * @return Returns the updatedDate.
     */
    public Date getUpdatedDate() {
        return _updatedDate == null ? null : new Date(_updatedDate.getTime());
    }
    
    /**
     * Set the updatedDate
     * <p>
     * @param updatedDate The updatedDate to set.
     */
    public void setUpdatedDate(Date updatedDate) {
        _updatedDate = new Date(updatedDate.getTime());
    }

    public List getAuthors() {
        return (_authors==null) ? (_authors=new ArrayList()) : _authors;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.feed.synd.SyndEntry#setAuthors(java.util.List)
     */
    public void setAuthors(List authors) {
        _authors = authors;
    }

    /**
     * Returns the entry author.
     * <p>
     * This method is a convenience method, it maps to the Dublin Core module creator.
     * <p>
     * @return the entry author, <b>null</b> if none.
     *
     */
    public String getAuthor() {
        String author;
        
        // Start out looking for one or more authors in _authors. For non-Atom
        // feeds, _authors may actually be null.
        if ((_authors != null) && (_authors.size() > 0)) {
            author = ((SyndPerson)_authors.get(0)).getName();
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
     * @param author the entry author to set, <b>null</b> if none.
     *
     */
    public void setAuthor(String author) {
        // Get the DCModule so that we can check to see if "creator" is already
        // set.
        DCModule dcModule = getDCModule();
        String currentValue = dcModule.getCreator();
        
        if ((currentValue == null) || (currentValue.length() == 0)) {
            getDCModule().setCreator(author);
        }
    }
    
    public List getContributors() {
        return (_contributors==null) ? (_contributors=new ArrayList()) : _contributors;
    }

    /* (non-Javadoc)
     * @see com.sun.syndication.feed.synd.SyndEntry#setContributors(java.util.List)
     */
    public void setContributors(List contributors) {
        _contributors = contributors;
    }
    
    public SyndFeed getSource() {
    	return _source;
    }
    
    public void setSource(SyndFeed source) {
    	_source = source;
    }
    
    /**
     * Returns foreign markup found at channel level.
     * <p>
     * @return list of JDOM nodes containing channel-level foreign markup,
     *         an empty list if none.
     *
     */
    public Object getForeignMarkup() {
        return (_foreignMarkup==null) ? (_foreignMarkup=new ArrayList()) : _foreignMarkup;
    }

    /**
     * Sets foreign markup found at channel level.
     * <p>
     * @param foreignMarkup list of JDOM nodes containing channel-level foreign markup,
     *         an empty list if none.
     *
     */
    public void setForeignMarkup(Object foreignMarkup) {
        _foreignMarkup = (List)foreignMarkup;
    }

	public Object getWireEntry() {
		return wireEntry;
	}    
	
	public void setWireEntry(Object wireEntry) {
		this.wireEntry = wireEntry;
	}

    public SyndLink findRelatedLink(String relation) {
        for(SyndLink l : this.getLinks()){
            if(relation.equals(l.getRel())){
                return l;
            }
        }
        return null;
    }
}
