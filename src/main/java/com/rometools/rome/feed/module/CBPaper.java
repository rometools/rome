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

/**
 * Subject of the Dublin Core ModuleImpl.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Munavar Basha <munavar.basha@metricstream.com>
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public interface CBPaper extends Cloneable, CopyFrom{
	
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
    public void setSimpleTitle(String value);

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
    public void setOccurrenceDate(String value);

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
    public void setInstitutionAbbrev(String value);

    /**
     * Gets the value of the keyword property.
 	 *  @param keyword the CB news keyword to set, <b>null</b> if none.
     * 
     */
    public List<String> getKeyword();
    
    /**
     * Sets the CB paper keyword.
     * <p>
     *
     * @param strkey the CB paper keyword to set, <b>null</b> if none.
     *
     */
    public void setKeyword(List<String> strkey);

    /**
     * Gets the value of the resource property.
     * 
     * @return the CB news resource, <b>null</b> if none.
     * 
     */
    public CBResource getResource();
    
    /**
     * Sets the CB paper resource.
     * <p>
     *
     * @param resource the CB paper resource to set, <b>null</b> if none.
     *
     */
    public void setResource(CBResource resource);

    /**
     * Gets the value of the person property.
     * @return the CB paper person, <b>null</b> if none.
     * 
     * 
     */
    public CBPerson getPerson();
    
    /**
     * Sets the CB paper person.
     * <p>
     *
     * @param person the CB paper person to set, <b>null</b> if none.
     *
     */
    public void setPerson(CBPerson person);

    /**
     * Gets the value of the byline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getByline();

    /**
     * Sets the value of the byline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setByline(String value);

    /**
     * Gets the value of the publicationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublicationDate();

    /**
     * Sets the value of the publicationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicationDate(String value);

    /**
     * Gets the value of the publication property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublication();
    
    /**
     * Sets the CB paper publication.
     * <p>
     *
     * @param person the CB paper publication to set, <b>null</b> if none.
     *
     */
	public void setPublication(String publication);

    /**
     * Gets the value of the issue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssue();

    /**
     * Sets the value of the issue property.
     * 
     * @param issue
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssue(String issue);
    
    /**
     * Gets the value of the JELCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJELCode();

    /**
     * Sets the value of the JELCode property.
     * 
     * @param JELCode
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJELCode(String JELCode);
}
