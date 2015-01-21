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
package com.rometools.rome.feed.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;
import com.rometools.rome.io.ISO3166CountyCode;

/**
 * Subject of the CB SpeechImpl, default implementation.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Munavar Basha <munavar.basha@metricstream.com>
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public class CBSpeechImpl implements Cloneable, Serializable, CBSpeech {
	
    private static final long serialVersionUID = 1L;
    private static final CopyFromHelper COPY_FROM_HELPER;

    private final ObjectBean objBean;
    private String simpleTitle;
    private String occurrenceDate;
    private String institutionAbbrev;
    private String audience;
    private List<String> keyword;
    private String venue;
    private String locationAsWritten;
    private String locationState;
    private String locationCity;
    private String parseType;
    private CBResource resource;
    private CBPerson person;
    private ISO3166CountyCode locationCountry;

    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("simpleTitle", String.class);
        basePropInterfaceMap.put("occurenceDate", String.class);
        basePropInterfaceMap.put("institutionAbbrev", String.class);
        basePropInterfaceMap.put("audience", String.class);
        basePropInterfaceMap.put("keyword", List.class);
        basePropInterfaceMap.put("venue", String.class);
        basePropInterfaceMap.put("locationAsWritten", String.class);
        basePropInterfaceMap.put("locationCountry", String.class);
        basePropInterfaceMap.put("locationState", String.class);
        basePropInterfaceMap.put("locationCity", String.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();
        basePropClassImplMap.put(CBResource.class, CBResourceImpl.class);
        basePropClassImplMap.put(CBPerson.class, CBPersonImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(CBSpeech.class, basePropInterfaceMap, basePropClassImplMap);
    }
    
    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public CBSpeechImpl() {
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
    public Class<CBSpeech> getInterface() {
        return CBSpeech.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    /**
     * Gets the value of the simpleTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@Override
	public String getSimpleTitle() {
		return simpleTitle;
	}
	
    /**
     * Sets the value of the simpleTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	@Override
	public void setSimpleTitle(String simpleTitle) {
		this.simpleTitle = simpleTitle;
		
	}

    /**
     * Gets the value of the occurrenceDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@Override
	public String getOccurrenceDate() {
		return occurrenceDate;
	}

    /**
     * Sets the value of the occurrenceDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	@Override
	public void setOccurrenceDate(String occurrenceDate) {
		this.occurrenceDate = occurrenceDate;
		
	}

    /**
     * Gets the value of the institutionAbbrev property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@Override
	public String getInstitutionAbbrev() {
		return institutionAbbrev;
	}

    /**
     * Sets the value of the institutionAbbrev property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	@Override
	public void setInstitutionAbbrev(String institutionAbbrev) {
		this.institutionAbbrev = institutionAbbrev;
		
	}
	
    /**
     * Gets the value of the audience property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@Override
	public String getAudience() {
		return audience;
	}

    /**
     * Sets the value of the audience property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	@Override
	public void setAudience(String audience) {
		this.audience = audience;
		
	}

    /**
     * Gets the value of the keyword property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
	@Override
    public List<String> getKeyword() {
        if (keyword == null) {
            keyword = new ArrayList<String>();
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
    public void setKeyword(List<String> keyList) {
        this.keyword = keyList;
    }
	
    /**
     * Gets the value of the resource property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CBResource }
     * 
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
     * Gets the value of the person property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CBPerson }
     * 
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

    /**
     * Gets the value of the venue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@Override
	public String getVenue() {
		return venue;
	}

    /**
     * Sets the value of the venue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	@Override
	public void setVenue(String venue) {
		this.venue = venue;
		
	}

    /**
     * Gets the value of the locationAsWritten property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@Override
	public String getLocationAsWritten() {
		return locationAsWritten;
	}

    /**
     * Sets the value of the locationAsWritten property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	@Override
	public void setLocationAsWritten(String locationAsWritten) {
		this.locationAsWritten = locationAsWritten;
		
	}

    /**
     * Gets the value of the locationCountry property.
     * 
     * @return
     *     possible object is
     *     {@link ISO3166CountyCode }
     *     
     */
	@Override
	public ISO3166CountyCode getLocationCountry() {
		return locationCountry;
	}

    /**
     * Sets the value of the locationCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO3166CountyCode }
     *     
     */
	@Override
	public void setLocationCountry(ISO3166CountyCode locationCountry) {
		this.locationCountry = locationCountry;
		
	}

    /**
     * Gets the value of the locationState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@Override
	public String getLocationState() {
		return locationState;
	}

    /**
     * Sets the value of the locationState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	@Override
	public void setLocationState(String locationState) {
		this.locationState = locationState;
		
	}

    /**
     * Gets the value of the locationCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@Override
	public String getLocationCity() {
		return locationCity;
	}

    /**
     * Sets the value of the locationCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	@Override
	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
		
	}

    /**
     * Gets the value of the parseType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@Override
	public String getParseType() {
		return parseType;
	}

    /**
     * Sets the value of the parseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	@Override
	public void setParseType(String parseType) {
		this.parseType = parseType;
		
	}

}
