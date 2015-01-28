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



import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.io.ISO3166CountyCode;

/**
 * Statistics of the Central Bank ModuleImpl.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public interface CBStatistics extends Cloneable, CopyFrom  {
	
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
     * Gets the value of the exchangeRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public CBExchangeRate getExchangeRate();

    /**
     * Sets the value of the exchangeRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExchangeRate(CBExchangeRate exchangeRate);
    
    /**
     * Gets the value of the interestRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public CBInterestRate getInterestRate();

    /**
     * Sets the value of the interestRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterestRate(CBInterestRate interestRate);

    /**
     * Gets the value of the transaction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public CBTransaction getTransaction();

    /**
     * Sets the value of the transaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransaction(CBTransaction transaction);
    
    /**
     * Gets the value of the otherStatistic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public CBOtherStatistics getOtherStatistic();

    /**
     * Sets the value of the otherStatistic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherStatistic(CBOtherStatistics otherStatistic);
}
