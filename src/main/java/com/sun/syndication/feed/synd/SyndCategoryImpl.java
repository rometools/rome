/*
 * Copyright 2004 Sun Microsystems, Inc.
 * Copyright 2011 ROME Team
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

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.module.DCSubject;
import com.sun.syndication.feed.module.DCSubjectImpl;

/**
 * Bean for categories of SyndFeedImpl feeds and entries.
 * <p>
 * 
 * @author Alejandro Abdelnur
 * 
 */
public class SyndCategoryImpl implements Serializable, SyndCategory {
    private static final long serialVersionUID = -2151815243404151131L;
    private final ObjectBean objBean;
    private final DCSubject subject;

    /**
     * For implementations extending SyndContentImpl to be able to use the
     * ObjectBean functionality with extended interfaces.
     * <p>
     * 
     * @param subject the DC subject to wrap.
     */
    SyndCategoryImpl(final DCSubject subject) {
        objBean = new ObjectBean(SyndCategory.class, this);
        this.subject = subject;
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
        return objBean.clone();
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
        if (!(other instanceof SyndCategoryImpl)) {
            return false;
        }
        return objBean.equals(other);
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
     * Package private constructor, used by SyndCategoryListFacade.
     * <p>
     * 
     * @return the DC subject being wrapped.
     * 
     */
    DCSubject getSubject() {
        return subject;
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
     * 
     * @return the category name, <b>null</b> if none.
     * 
     */
    @Override
    public String getName() {
        return subject.getValue();
    }

    /**
     * Sets the category name.
     * <p>
     * 
     * @param name the category name to set, <b>null</b> if none.
     * 
     */
    @Override
    public void setName(final String name) {
        subject.setValue(name);
    }

    /**
     * Returns the category taxonomy URI.
     * <p>
     * 
     * @return the category taxonomy URI, <b>null</b> if none.
     * 
     */
    @Override
    public String getTaxonomyUri() {
        return subject.getTaxonomyUri();
    }

    /**
     * Sets the category taxonomy URI.
     * <p>
     * 
     * @param taxonomyUri the category taxonomy URI to set, <b>null</b> if none.
     * 
     */
    @Override
    public void setTaxonomyUri(final String taxonomyUri) {
        subject.setTaxonomyUri(taxonomyUri);
    }

}

/**
 * List implementation for SyndCategoryImpl elements. To be directly used by the
 * SyndFeedImpl and SyndEntryImpl classes only.
 * <p>
 * It acts as a facade on top of the DCSubjectImpl elements of the underlying
 * list and remains in synch with it. It is possible to work on either list, the
 * categories one or the subjects one and they remain in synch.
 * <p>
 * This is necessary because the SyndFeedImpl categories are just a convenience
 * to access the DublinCore subjects.
 * <P>
 * All this mess to avoid making DCSubjectImpl implement SyndCategory (which it
 * would be odd).
 * <p>
 * 
 * @author Alejandro Abdelnur
 * 
 */
class SyndCategoryListFacade extends AbstractList<SyndCategory> {
    private final List<DCSubject> subjects;

    /**
     * Default constructor. Creates and empty list.
     */
    public SyndCategoryListFacade() {
        this(new ArrayList<DCSubject>());
    }

    /**
     * Creates a facade list of categories on top the given subject list.
     * <P>
     * 
     * @param subjects the list of subjects to create the facade.
     * 
     */
    public SyndCategoryListFacade(final List<DCSubject> subjects) {
        this.subjects = subjects;
    }

    /**
     * Gets the category by index.
     * <p>
     * 
     * @param index the index position to retrieve the category.
     * @return the SyndCategoryImpl in position index, <b>null</b> if none.
     * 
     */
    @Override
    public SyndCategory get(final int index) {
        return new SyndCategoryImpl(subjects.get(index));
    }

    /**
     * Returns the size of the list.
     * <p>
     * 
     * @return the size of the list.
     * 
     */
    @Override
    public int size() {
        return subjects.size();
    }

    /**
     * Sets a category in an existing position in the list.
     * <p>
     * 
     * @param index position to set the category.
     * @param obj the SyndCategoryImpl object to set.
     * @return the SyndCategoryImpl object that is being replaced, <b>null</b>
     *         if none.
     * 
     */
    @Override
    public SyndCategory set(final int index, final SyndCategory obj) {
        final SyndCategoryImpl sCat = (SyndCategoryImpl) obj;
        DCSubject subject;
        if (sCat != null) {
            subject = sCat.getSubject();
        } else {
            subject = null;
        }
        subject = subjects.set(index, subject);
        if (subject != null) {
            return new SyndCategoryImpl(subject);
        } else {
            return null;
        }
    }

    /**
     * Adds a category to the list.
     * <p>
     * 
     * @param index position to add the category.
     * @param obj the SyndCategoryImpl object to add.
     * 
     */
    @Override
    public void add(final int index, final SyndCategory obj) {
        final SyndCategoryImpl sCat = (SyndCategoryImpl) obj;
        DCSubject subject;
        if (sCat != null) {
            subject = sCat.getSubject();
        } else {
            subject = null;
        }
        subjects.add(index, subject);
    }

    /**
     * Removes a category element from a specific position.
     * <p>
     * 
     * @param index position to remove the category from.
     * @return the SyndCategoryImpl being removed from position index,
     *         <b>null</b> if none.
     * 
     */
    @Override
    public SyndCategory remove(final int index) {
        final DCSubject subject = subjects.remove(index);
        if (subject != null) {
            return new SyndCategoryImpl(subject);
        } else {
            return null;
        }
    }

    /**
     * Returns a list with the DCSubject elements of the SyndCategoryImpl list
     * facade. To be used by the SyndFeedImpl class only.
     * <p>
     * 
     * @param cList the list with SyndCategoryImpl elements to convert to
     *            subject list.
     * @return a list with DCSubject elements corresponding to the categories in
     *         the given list.
     * 
     */
    public static List<DCSubject> convertElementsSyndCategoryToSubject(final List<SyndCategory> cList) {
        List<DCSubject> sList = null;
        if (cList != null) {
            sList = new ArrayList<DCSubject>();
            for (int i = 0; i < cList.size(); i++) {
                final SyndCategoryImpl sCat = (SyndCategoryImpl) cList.get(i);
                DCSubject subject = null;
                if (sCat != null) {
                    subject = sCat.getSubject();
                }
                sList.add(subject);
            }
        }
        return sList;
    }

}
