/*
 * Copyright 2015 MetricStream, Inc.
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
package com.rometools.rome.feed.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;

/**
 * News of the Central Bank ModuleImpl, default implementation.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Norbert Kiesel <nkiesel@metricstream.com>
 *
 */
public class CBNewsImpl implements Cloneable, Serializable, CBNews {

    private static final long serialVersionUID = 1L;
    private static final CopyFromHelper COPY_FROM_HELPER;

    private final ObjectBean objBean;
    private String simpleTitle;
    private String occurenceDate;
    private String institutionAbbrev;
    private List<String> keyword;
    private CBResource resource;
    private CBPerson person;

    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("simpleTitle", String.class);
        basePropInterfaceMap.put("occurenceDate", String.class);
        basePropInterfaceMap.put("institutionAbbrev", String.class);
        basePropInterfaceMap.put("keyword", List.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();
        basePropClassImplMap.put(CBResource.class, CBResourceImpl.class);
        basePropClassImplMap.put(CBPerson.class, CBPersonImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(CBNews.class, basePropInterfaceMap, basePropClassImplMap);
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public CBNewsImpl() {
        objBean = new ObjectBean(this.getClass(), this);
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
        if (!(other instanceof CBNewsImpl)) {
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

    @Override
    public Class<CBNews> getInterface() {
        return CBNews.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    /**
     * Returns the CB news simpleTitle.
     * <p>
     *
     * @return the CB news simpleTitle, <b>null</b> if none.
     *
     */
	@Override
    public String getSimpleTitle() {
		return simpleTitle;
	}

    /**
     * Sets the CB news simpleTitle.
     * <p>
     *
     * @param simpleTitle the CB news simpleTitle to set, <b>null</b> if none.
     *
     */
	@Override
    public void setSimpleTitle(String simpleTitle) {
		this.simpleTitle = simpleTitle;
	}

	/**
     * Returns the CB news occurenceDate.
     * <p>
     *
     * @return the CB news occurenceDate, <b>null</b> if none.
     *
     */
	@Override
    public String getOccurenceDate() {
		return occurenceDate;
	}

    /**
     * Sets the CB news occurenceDate.
     * <p>
     *
     * @param occurenceDate the CB news occurenceDate to set, <b>null</b> if none.
     *
     */
	@Override
    public void setOccurenceDate(String occurenceDate) {
		this.occurenceDate = occurenceDate;
	}

	/**
     * Returns the CB news institutionAbbrev.
     * <p>
     *
     * @return the CB news institutionAbbrev, <b>null</b> if none.
     *
     */
	@Override
    public String getInstitutionAbbrev() {
		return institutionAbbrev;
	}

    /**
     * Sets the CB news institutionAbbrev.
     * <p>
     *
     * @param institutionAbbrev the CB news institutionAbbrev to set, <b>null</b> if none.
     *
     */
	@Override
    public void setInstitutionAbbrev(String institutionAbbrev) {
		this.institutionAbbrev = institutionAbbrev;
	}

	/**
     * Returns the CB news keyword list.
     * <p>
     *
     * @return the CB news keyword list, <b>null</b> if none.
     *
     */
	@Override
    public List<String> getKeywords() {
        if (keyword == null) {
            keyword = new ArrayList<String>();
        }
        return this.keyword;
    }

	/**
     * Sets the CB news keyword.
     * <p>
     *
     * @param keyList the CB news keyword to set, <b>null</b> if none.
     *
     */
	@Override
    public void setKeywords(List<String> keyList) {
        this.keyword = keyList;
    }
	
	/**
     * Returns the CB news resource.
     * <p>
     *
     * @return the CB news resource, <b>null</b> if none.
     *
     */
	@Override
    public CBResource getResource() {
		return resource;
	}

    /**
     * Sets the CB news resource.
     * <p>
     *
     * @param resource the CB news resource to set, <b>null</b> if none.
     *
     */
	@Override
    public void setResource(CBResource resource) {
		this.resource = resource;
	}

	/**
     * Returns the CB news person.
     * <p>
     *
     * @return the CB news person, <b>null</b> if none.
     *
     */
	@Override
    public CBPerson getPerson() {
		return person;
	}

    /**
     * Sets the CB news person.
     * <p>
     *
     * @param person the CB news person to set, <b>null</b> if none.
     *
     */
	@Override
    public void setPerson(CBPerson person) {
		this.person = person;
	}

}
