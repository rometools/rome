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



import java.util.List;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.io.ISO3166CountyCode;

/**
 * Subject of the Dublin Core ModuleImpl.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Munavar Basha <munavar.basha@metricstream.com>
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public interface CBSpeech extends Cloneable, CopyFrom  {
	
    /**
     * Gets the value of the simpleTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimpleTitle();

    /**
     * Sets the value of the simpleTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimpleTitle(String simpleTitle);

    /**
     * Gets the value of the occurrenceDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOccurrenceDate();

    /**
     * Sets the value of the occurrenceDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOccurrenceDate(String occurrenceDate);

    /**
     * Gets the value of the institutionAbbrev property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstitutionAbbrev();

    /**
     * Sets the value of the institutionAbbrev property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstitutionAbbrev(String institutionAbbrev);

    /**
     * Gets the value of the audience property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAudience();

    /**
     * Sets the value of the audience property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAudience(String audience);

    /**
     * Gets the value of the keyword property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getKeyword();
    
    /**
     * Sets the CB speech keyword.
     * <p>
     *
     * @param strkey the CB speech keyword to set, <b>null</b> if none.
     *
     */
    public void setKeyword(List<String> strkey);
    
    /**
     * Gets the value of the resource property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CBResource }
     * 
     * 
     */
    public CBResource getResource();
    
    /**
     * Sets the CB news resource.
     * <p>
     *
     * @param resource the CB news resource to set, <b>null</b> if none.
     *
     */
    public void setResource(CBResource resource);

    /**
     * Gets the value of the person property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CBPerson }
     * 
     * 
     */
    public CBPerson getPerson();
    
    /**
     * Sets the CB news person.
     * <p>
     *
     * @param person the CB news person to set, <b>null</b> if none.
     *
     */
    public void setPerson(CBPerson person);

    /**
     * Gets the value of the venue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVenue();

    /**
     * Sets the value of the venue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVenue(String venue);

    /**
     * Gets the value of the locationAsWritten property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationAsWritten();

    /**
     * Sets the value of the locationAsWritten property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationAsWritten(String locationAsWritten);

    /**
     * Gets the value of the locationCountry property.
     * 
     * @return
     *     possible object is
     *     {@link ISO3166CountyCode }
     *     
     */
    public ISO3166CountyCode getLocationCountry();

    /**
     * Sets the value of the locationCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO3166CountyCode }
     *     
     */
    public void setLocationCountry(ISO3166CountyCode locationCountry);

    /**
     * Gets the value of the locationState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationState();

    /**
     * Sets the value of the locationState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationState(String locationState);

    /**
     * Gets the value of the locationCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationCity();

    /**
     * Sets the value of the locationCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationCity(String locationCity);

    /**
     * Gets the value of the parseType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParseType();

    /**
     * Sets the value of the parseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParseType(String parseType);

}
