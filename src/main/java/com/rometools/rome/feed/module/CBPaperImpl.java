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
 * Paper of the Central Bank ModuleImpl, default implementation.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB Paper</a>.
 * @author Munavar Basha <munavar.basha@metricstream.com>
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public class CBPaperImpl implements Cloneable, Serializable, CBPaper{

    private static final long serialVersionUID = 1L;
    private static final CopyFromHelper COPY_FROM_HELPER;
    
    private final ObjectBean objBean;
    private String simpleTitle;
    private String occurenceDate;
    private String institutionAbbrev;
    private List<String> keyword;
    private CBResource resource;
    private CBPerson person;
    private String byline;
    private String publicationDate;
    private String publication;
    private String issue;
    private String JELCode;
    
    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("simpleTitle", String.class);
        basePropInterfaceMap.put("occurenceDate", String.class);
        basePropInterfaceMap.put("institutionAbbrev", String.class);
        basePropInterfaceMap.put("keyword", List.class);
        basePropInterfaceMap.put("byline", String.class);
        basePropInterfaceMap.put("publicationDate", String.class);
        basePropInterfaceMap.put("publication", String.class);
        basePropInterfaceMap.put("issue", String.class);
        basePropInterfaceMap.put("JELCode", String.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();
        basePropClassImplMap.put(CBResource.class, CBResourceImpl.class);
        basePropClassImplMap.put(CBPerson.class, CBPersonImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(CBPaper.class, basePropInterfaceMap, basePropClassImplMap);
    }
    
    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public CBPaperImpl() {
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
        if (!(other instanceof CBPaperImpl)) {
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
    public Class<CBPaper> getInterface() {
        return CBPaper.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    /**
     * Returns the CB paper simpleTitle.
     * <p>
     *
     * @return the CB paper simpleTitle, <b>null</b> if none.
     *
     */
	@Override
	public String getSimpleTitle() {
		return simpleTitle;
	}
	
	
    /**
     * Sets the CB paper simpleTitle.
     * <p>
     *
     * @param simpleTitle the CB paper simpleTitle to set, <b>null</b> if none.
     *
     */
	@Override
	public void setSimpleTitle(String simpleTitle) {
		this.simpleTitle = simpleTitle;
		
	}

	/**
     * Returns the CB paper occurenceDate.
     * <p>
     *
     * @return the CB paper occurenceDate, <b>null</b> if none.
     *
     */
	@Override
	public String getOccurrenceDate() {
		return occurenceDate;
	}
	
    /**
     * Sets the CB paper occurenceDate.
     * <p>
     *
     * @param occurenceDate the CB paper occurenceDate to set, <b>null</b> if none.
     *
     */
	@Override
	public void setOccurrenceDate(String occurenceDate) {
		this.occurenceDate = occurenceDate;
		
	}

	/**
     * Returns the CB paper institutionAbbrev.
     * <p>
     *
     * @return the CB paper institutionAbbrev, <b>null</b> if none.
     *
     */
	@Override
	public String getInstitutionAbbrev() {
		return institutionAbbrev;
	}

    /**
     * Sets the CB paper institutionAbbrev.
     * <p>
     *
     * @param institutionAbbrev the CB paper institutionAbbrev to set, <b>null</b> if none.
     *
     */
	@Override
	public void setInstitutionAbbrev(String institutionAbbrev) {
		this.institutionAbbrev = institutionAbbrev;
		
	}

	/**
     * Returns the CB paper keyword.
     * <p>
     *
     * @return the CB paper keyword, <b>null</b> if none.
     *
     */
	@Override
    public List<String> getKeywords() {
        if (this.keyword == null) {
            this.keyword = new ArrayList<String>();
        }
        return this.keyword;
    }
	
	/**
     * Sets the CB paper keyword.
     * <p>
     *
     * @param keyList the CB paper keyword to set, <b>null</b> if none.
     *
     */
	@Override
    public void setKeywords(List<String> keyList) {
        this.keyword = keyList;
    }
	
	/**
     * Returns the CB paper resource.
     * <p>
     *
     * @return the CB paper resource, <b>null</b> if none.
     *
     */
	@Override
	public CBResource getResource() {
		return resource;
	}
	
    /**
     * Sets the CB paper resource.
     * <p>
     *
     * @param resource the CB paper resource to set, <b>null</b> if none.
     *
     */
	@Override
    public void setResource(CBResource resource) {
		this.resource = resource;
	}

	/**
     * Returns the CB paper person.
     * <p>
     *
     * @return the CB paper person, <b>null</b> if none.
     *
     */
	@Override
	public CBPerson getPerson() {
		return person;
	}
	
    /**
     * Sets the CB paper person.
     * <p>
     *
     * @param person the CB paper person to set, <b>null</b> if none.
     *
     */
	@Override
    public void setPerson(CBPerson person) {
		this.person = person;
	}

	/**
     * Returns the CB paper byline.
     * <p>
     *
     * @return the CB paper byline, <b>null</b> if none.
     *
     */
	@Override
	public String getByline() {
		return byline;
	}

    /**
     * Sets the CB paper byline.
     * <p>
     *
     * @param person the CB paper byline to set, <b>null</b> if none.
     *
     */
	@Override
	public void setByline(String byline) {
		this.byline = byline;
		
	}

	/**
     * Returns the CB paper publicationDate.
     * <p>
     *
     * @return the CB paper publicationDate, <b>null</b> if none.
     *
     */
	@Override
	public String getPublicationDate() {
		return publicationDate;
	}

    /**
     * Sets the CB paper publicationDate.
     * <p>
     *
     * @param person the CB paper publicationDate to set, <b>null</b> if none.
     *
     */
	@Override
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
		
	}

	/**
     * Returns the CB paper publication.
     * <p>
     *
     * @return the CB paper publication, <b>null</b> if none.
     *
     */
	@Override
	public String getPublication() {
		return publication;
	}
	
    /**
     * Sets the CB paper publication.
     * <p>
     *
     * @param person the CB paper publication to set, <b>null</b> if none.
     *
     */
	@Override
	public void setPublication(String publication) {
		this.publication = publication;
	}

    /**
     * Gets the value of the issue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@Override
	public String getIssue(){
		return issue;
	}

    /**
     * Sets the value of the issue property.
     * 
     * @param issue
     *     allowed object is
     *     {@link String }
     *     
     */
	@Override
	public void setIssue(String issue){
		this.issue = issue;
	}
    
    /**
     * Gets the value of the JELCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@Override
	public String getJELCode(){
		return JELCode;
	}

    /**
     * Sets the value of the JELCode property.
     * 
     * @param JELCode
     *     allowed object is
     *     {@link String }
     *     
     */
	@Override
	public void setJELCode(String JELCode){
		this.JELCode = JELCode;
	}
}
