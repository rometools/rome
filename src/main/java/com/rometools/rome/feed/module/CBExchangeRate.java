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

import com.rometools.rome.feed.CopyFrom;

/**
 * Subject of the Dublin Core ExchangeRate.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Munavar Basha <munavar.basha@metricstream.com>
 *
 */
public interface CBExchangeRate extends Cloneable, CopyFrom {

    /**
     * Gets the value of the observation property.
     * 
     * @return
     *     possible object is
     *     {@link CBObservation }
     *     
     */
    public CBObservation getObservation();

    /**
     * Sets the value of the observation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBObservation }
     *     
     */
    public void setObservation(CBObservation observation);

    /**
     * Gets the value of the baseCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseCurrency();

    /**
     * Sets the value of the baseCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseCurrency(String baseCurrency);

    /**
     * Gets the value of the targetCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetCurrency();

    /**
     * Sets the value of the targetCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetCurrency(String targetCurrency);

    /**
     * Gets the value of the rateType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateType();

    /**
     * Sets the value of the rateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateType(String rateType);

    /**
     * Gets the value of the observationPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link CBObservationPeriod }
     *     
     */
    public CBObservationPeriod getObservationPeriod();

    /**
     * Sets the value of the observationPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObservationPeriod }
     *     
     */
    public void setObservationPeriod(CBObservationPeriod observationPeriod);

}
