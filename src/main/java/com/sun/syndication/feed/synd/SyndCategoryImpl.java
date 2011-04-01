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

import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.module.DCSubjectImpl;
import com.sun.syndication.feed.module.DCSubject;

import java.util.AbstractList;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Bean for categories of SyndFeedImpl feeds and entries.
 * <p>
 * @author Alejandro Abdelnur
 *
 */
public class SyndCategoryImpl implements Serializable,SyndCategory {
    private ObjectBean _objBean;
    private DCSubject _subject;

    /**
     * For implementations extending SyndContentImpl to be able to use the ObjectBean functionality
     * with extended interfaces.
     * <p>
     * @param subject the DC subject to wrap.
     */
    SyndCategoryImpl(DCSubject subject) {
        _objBean = new ObjectBean(SyndCategory.class,this);
        _subject = subject;
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
        return _objBean.equals(other);
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
     * Package private constructor, used by SyndCategoryListFacade.
     * <p>
     * @return the DC subject being wrapped.
     *
     */
    DCSubject getSubject() {
        return _subject;
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public SyndCategoryImpl() {
        this(new DCSubjectImpl());
    }

    /**
     * Returns the category name.
     * <p>
     * @return the category name, <b>null</b> if none.
     *
     */
    public String getName() {
        return _subject.getValue();
    }

    /**
     * Sets the category name.
     * <p>
     * @param name the category name to set, <b>null</b> if none.
     *
     */
    public void setName(String name) {
        _subject.setValue(name);
    }

    /**
     * Returns the category taxonomy URI.
     * <p>
     * @return the category taxonomy URI, <b>null</b> if none.
     *
     */
    public String getTaxonomyUri() {
        return _subject.getTaxonomyUri();
    }

    /**
     * Sets the category taxonomy URI.
     * <p>
     * @param taxonomyUri the category taxonomy URI to set, <b>null</b> if none.
     *
     */
    public void setTaxonomyUri(String taxonomyUri) {
        _subject.setTaxonomyUri(taxonomyUri);
    }

}


/**
 * List implementation for SyndCategoryImpl elements. To be directly used by the SyndFeedImpl
 * and SyndEntryImpl classes only.
 * <p>
 * It acts as a facade on top of the DCSubjectImpl elements of the underlying list
 * and remains in synch with it. It is possible to work on either list, the categories
 * one or the subjects one and they remain in synch.
 * <p>
 * This is necessary because the SyndFeedImpl categories are just a convenience to access
 * the DublinCore subjects.
 * <P>
 * All this mess to avoid making DCSubjectImpl implement SyndCategory (which it would be odd).
 * <p>
 * @author Alejandro Abdelnur
 *
 */
class SyndCategoryListFacade extends AbstractList {
    private List _subjects;

    /**
     * Default constructor. Creates and empty list.
     */
    public SyndCategoryListFacade() {
        this(new ArrayList());
    }

    /**
     * Creates a facade list of categories on top the given subject list.
     * <P>
     * @param subjects the list of subjects to create the facade.
     *
     */
    public SyndCategoryListFacade(List subjects) {
        _subjects = subjects;
    }

    /**
     * Gets the category by index.
     * <p>
     * @param index the index position to retrieve the category.
     * @return the SyndCategoryImpl in position index, <b>null</b> if none.
     *
     */
    public Object get(int index) {
        return new SyndCategoryImpl((DCSubject) _subjects.get(index));
    }

    /**
     * Returns the size of the list.
     * <p>
     * @return the size of the list.
     *
     */
    public int size() {
        return _subjects.size();
    }

    /**
     * Sets a category in an existing position in the list.
     * <p>
     * @param index position to set the category.
     * @param obj the SyndCategoryImpl object to set.
     * @return the SyndCategoryImpl object that is being replaced, <b>null</b> if none.
     *
     */
    public Object set(int index,Object obj) {
        SyndCategoryImpl sCat = (SyndCategoryImpl) obj;
        DCSubject subject = (sCat!=null) ? sCat.getSubject() : null;
        subject = (DCSubject) _subjects.set(index,subject);
        return (subject!=null) ? new SyndCategoryImpl(subject) : null;
    }

    /**
    * Adds a category to the list.
    * <p>
    * @param index position to add the category.
    * @param obj the SyndCategoryImpl object to add.
     *
     */
    public void add(int index,Object obj) {
        SyndCategoryImpl sCat = (SyndCategoryImpl) obj;
        DCSubject subject = (sCat!=null) ? sCat.getSubject() : null;
        _subjects.add(index,subject);
    }

    /**
     * Removes a category element from a specific position.
     * <p>
     * @param index position to remove the category from.
     * @return the SyndCategoryImpl being removed from position index, <b>null</b> if none.
     *
     */
    public Object remove(int index) {
        DCSubject subject = (DCSubject) _subjects.remove(index);
        return (subject!=null) ? new SyndCategoryImpl(subject) : null;
    }

    /**
     * Returns a list with the DCSubject elements of the SyndCategoryImpl list facade.
     * To be used by the SyndFeedImpl class only.
     * <p>
     * @param cList the list with SyndCategoryImpl elements to convert to subject list.
     * @return a list with DCSubject elements corresponding to the categories in the given list.
     *
     */
    public static List convertElementsSyndCategoryToSubject(List cList) {
        List sList = null;
        if (cList!=null) {
            sList = new ArrayList();
            for (int i=0;i<cList.size();i++) {
                SyndCategoryImpl sCat = (SyndCategoryImpl) cList.get(i);
                DCSubject subject = null;
                if (sCat!=null) {
                    subject = sCat.getSubject();
                }
                sList.add(subject);
            }
        }
        return sList;
    }

}
