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
import java.util.HashMap;
import java.util.Map;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;
import com.rometools.rome.io.ISO3166CountyCode;

/**
 * Subject of the CBStatisticsImpl, default implementation.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB Statistics</a>.
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public class CBStatisticsImpl implements Cloneable, Serializable, CBStatistics{

    private static final long serialVersionUID = 1L;
    private static final CopyFromHelper COPY_FROM_HELPER;
    
    private final ObjectBean objBean;
    private ISO3166CountyCode country;
    private String institutionAbbrev;
    private CBExchangeRate exchangeRate;
    private CBInterestRate interestRate;
    private CBTransaction transaction;
    private CBOtherStatistics otherStatistic;
    
    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("country", String.class);
        basePropInterfaceMap.put("institutionAbbrev", String.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();
        basePropClassImplMap.put(CBExchangeRate.class, CBExchangeRateImpl.class);
        basePropClassImplMap.put(CBInterestRate.class, CBInterestRateImpl.class);
        basePropClassImplMap.put(CBTransaction.class, CBTransactionImpl.class);
        basePropClassImplMap.put(CBOtherStatistics.class, CBOtherStatisticsImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(CBStatistics.class, basePropInterfaceMap, basePropClassImplMap);
    }
    
    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public CBStatisticsImpl() {
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
        if (!(other instanceof CBStatisticsImpl)) {
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
    public Class<CBStatistics> getInterface() {
        return CBStatistics.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
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
    public String getInstitutionAbbrev(){
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
    public void setInstitutionAbbrev(String institutionAbbrev){
    	this.institutionAbbrev = institutionAbbrev;
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
    public ISO3166CountyCode getLocationCountry(){
    	return country;
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
    public void setLocationCountry(ISO3166CountyCode locationCountry){
    	this.country = locationCountry;
    }

    /**
     * Gets the value of the exchangeRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Override
    public CBExchangeRate getExchangeRate(){
    	return exchangeRate;
    }

    /**
     * Sets the value of the exchangeRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Override
    public void setExchangeRate(CBExchangeRate exchangeRate){
    	this.exchangeRate = exchangeRate;
    }
    
    /**
     * Gets the value of the interestRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Override
    public CBInterestRate getInterestRate(){
    	return interestRate;
    }

    /**
     * Sets the value of the interestRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Override
    public void setInterestRate(CBInterestRate interestRate){
    	this.interestRate = interestRate;
    }

    /**
     * Gets the value of the transaction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Override
    public CBTransaction getTransaction(){
    	return transaction;
    }

    /**
     * Sets the value of the transaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Override
    public void setTransaction(CBTransaction transaction){
    	this.transaction = transaction;
    }
    
    /**
     * Gets the value of the otherStatistic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Override
    public CBOtherStatistics getOtherStatistic(){
    	return otherStatistic;
    }

    /**
     * Sets the value of the otherStatistic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Override
    public void setOtherStatistic(CBOtherStatistics otherStatistic){
    	this.otherStatistic = otherStatistic;
    }
}
