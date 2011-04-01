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

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.impl.ModuleUtils;
import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.module.Extendable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import java.util.Iterator;

/**
 * Bean for entry elements of Atom feeds.
 * <p>
 * @author Alejandro Abdelnur
 * @author Dave Johnson (updated for Atom 1.0)
 */
public class Entry implements Cloneable, Serializable, Extendable {
    
    private ObjectBean _objBean;
    
    private String  _xmlBase;
    private List    _authors;
    private List    _contributors;
    private List    _categories;   
    private List    _contents;       
    private String  _id;
    private Date    _published;      // AKA issued  
    private String  _rights;        
    private Feed    _source;     
    private Content _summary;
    private Content _title;
    private Date    _updated;        // AKA modified
    private List    _alternateLinks; 
    private List    _otherLinks;   
    private List    _foreignMarkup;
    
    private List    _modules;
    
    private Date    _created;        // Atom 0.3 only


    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public Entry() {
        _objBean = new ObjectBean(this.getClass(),this);
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
        setForeignMarkup(((Entry)other).getForeignMarkup());       
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
     * @param title the entry title, <b>null</b> if none.
     *
     */
    public void setTitle(String title) {
        if (_title == null) _title = new Content();
        _title.setValue(title);
    }

    /**
     * Returns the entry title as a text construct.
     * <p>
     * @return the entry title, <b>null</b> if none.
     *
     */
    public Content getTitleEx() {
        return _title;
    }

    /**
     * Sets the entry title as a text construct.
     * <p>
     * @param title the entry title, <b>null</b> if none.
     *
     */
    public void setTitleEx(Content title) {
        _title = title;
    }

    /**
     * Returns the entry alternate links.
     * <p>
     * @return a list of Link elements with the entry alternate links, an empty list if none.
     */
    public List getAlternateLinks() {
        return (_alternateLinks==null) ? (_alternateLinks=new ArrayList()) : _alternateLinks;
    }

    /**
     * Sets the entry alternate links.
     * <p>
     * @param alternateLinks the list of Link elements with the entry alternate links to set,
     *        an empty list or <b>null</b> if none.
     */
    public void setAlternateLinks(List alternateLinks) {
        _alternateLinks = alternateLinks;
    }

    /**
     * Returns the entry non-alternate links.
     * <p>
     * @return the list of Link elements with the entry non-alternate links to set,
     *         an empty list if none.
     */
    public List getOtherLinks() {
        return (_otherLinks==null) ? (_otherLinks=new ArrayList()) : _otherLinks;
    }

    /**
     * Sets the entry non-alternate links.
     * <p>
     * @param otherLinks the list Link elements with the entry non-alternate links to set,
     *        an empty list or <b>null</b> if none.
     */
    public void setOtherLinks(List otherLinks) {
        _otherLinks = otherLinks;
    }

    /**
     * Returns the entry author.
     * <p>
     * @return the entry author, <b>null</b> if none.
     *
     */
    public List getAuthors() {
        return (_authors==null) ? (_authors=new ArrayList()) : _authors;
    }

    /**
     * Sets the author of the entry.
     * <p>
     * @param authors the author of the entry, <b>null</b> if none.
     *
     */
    public void setAuthors(List authors) {
        _authors = authors;
    }

    /**
     * Returns the entry contributors.
     * <p>
     * @return a list of Person elements with the entry contributors,
     *         an empty list if none.
     *
     */
    public List getContributors() {
        return (_contributors==null) ? (_contributors=new ArrayList()) : _contributors;
    }

    /**
     * Sets the entry contributors.
     * <p>
     * @param contributors the list of Person elements with the entry contributors to set,
     *        an empty list or <b>null</b> if none.
     *
     */
    public void setContributors(List contributors) {
        _contributors = contributors;
    }

    /**
     * Returns the entry ID.
     * <p>
     * @return the entry ID, <b>null</b> if none.
     *
     */
    public String getId() {
        return _id;
    }

    /**
     * Sets the entry ID.
     * <p>
     * @param id the entry ID, <b>null</b> if none.
     *
     */
    public void setId(String id) {
        _id = id;
    }

    /**
     * Returns the entry modified date (Atom 0.3, maps to {@link #getUpdated()}).
     * <p>
     * @return the entry modified date, <b>null</b> if none.
     */
    public Date getModified() {
        return _updated;
    }

    /**
     * Sets the entry modified date (Atom 0.3, maps to {@link #setUpdated(java.util.Date)}).
     * <p>
     * @param modified the entry modified date, <b>null</b> if none.
     */
    public void setModified(Date modified) {
        _updated = modified;
    }

    /**
     * Returns the entry issued date (Atom 0.3, maps to {@link #getPublished()}).
     * <p>
     * @return the entry issued date, <b>null</b> if none.
     */
    public Date getIssued() {
        return _published;
    }

    /**
     * Sets the entry issued date (Atom 0.3, maps to {@link #setPublished(java.util.Date)}).
     * <p>
     * @param issued the entry issued date, <b>null</b> if none.
     */
    public void setIssued(Date issued) {
        _published = issued;
    }

    /**
     * Returns the entry created date (Atom 0.3 only)
     * <p>
     * @return the entry created date, <b>null</b> if none.
     */
    public Date getCreated() {
        return _created;
    }

    /**
     * Sets the entry created date (Atom 0.3 only)
     * <p>
     * @param created the entry created date, <b>null</b> if none.
     */
    public void setCreated(Date created) {
        _created = created;
    }

    /**
     * Returns the entry summary.
     * <p>
     * @return  the entry summary, <b>null</b> if none.
     *
     */
    public Content getSummary() {
        return _summary;
    }

    /**
     * Sets the entry summary.
     * <p>
     * @param summary the entry summary, <b>null</b> if none.
     *
     */
    public void setSummary(Content summary) {
        _summary = summary;
    }

    /**
     * Returns the entry contents.
     * <p>
     * @return a list of Content elements with the entry contents,
     *         an empty list if none.
     */
    public List getContents() {
        return (_contents==null) ? (_contents=new ArrayList()) : _contents;
    }

    /**
     * Sets the entry contents.
     * <p>
     * @param contents the list of Content elements with the entry contents to set,
     *        an empty list or <b>null</b> if none.
     */
    public void setContents(List contents) {
        _contents = contents;
    }

    /**
     * Returns the entry modules.
     * <p>
     * @return a list of ModuleImpl elements with the entry modules,
     *         an emtpy list if none.
     *
     */
    public List getModules() {
        return (_modules==null) ? (_modules=new ArrayList()) : _modules;
    }

    /**
     * Sets the entry modules.
     * <p>
     * @param modules the list of ModuleImpl elements with the entry modules to set,
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
        return ModuleUtils.getModule(_modules,uri);
    }
        
    /**
     * Returns the published
     * <p>
     * @return Returns the published.
     * @since Atom 1.0
     */
    public Date getPublished() {
        return _published;
    }
    
    /**
     * Set the published
     * <p>
     * @param published The published to set.
     * @since Atom 1.0
     */
    public void setPublished(Date published) {
        _published = published;
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
     * Returns the source
     * <p>
     * @return Returns the source.
     */
    public Feed getSource() {
        return _source;
    }
    
    /**
     * Set the source
     * <p>
     * @param source The source to set.
     */
    public void setSource(Feed source) {
        _source = source;
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
    public void setCategories(List categories) {
        _categories = categories;
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
    
    
    /**
     * Returns foreign markup found at entry level.
     * <p>
     * @return list of Opaque object to discourage use
     *
     */
    public Object getForeignMarkup() {
        return (_foreignMarkup==null) ? (_foreignMarkup=new ArrayList()) : _foreignMarkup;
    }

    /**
     * Sets foreign markup found at entry level.
     * <p>
     * @param foreignMarkup Opaque object to discourage use
     *
     */
    public void setForeignMarkup(Object foreignMarkup) {
        _foreignMarkup = (List)foreignMarkup;
    }
    
    /**
     * Returns true if entry is a media entry, i.e. has rel="edit-media".
     *
     * @return true if entry is a media entry
     */
    public boolean isMediaEntry() {
        boolean mediaEntry = false;
        List links = getOtherLinks();
        for (Iterator it = links.iterator(); it.hasNext();) {
            Link link = (Link) it.next();
            if ("edit-media".equals(link.getRel())) {
                mediaEntry = true;
                break;
            }
        }
        return mediaEntry;
    } 

}
