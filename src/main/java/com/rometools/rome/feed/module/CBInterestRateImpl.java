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
import java.util.HashMap;
import java.util.Map;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;

/**
 * Interest Rate of the Central Bank ModuleImpl, default implementation.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public class CBInterestRateImpl implements Cloneable, Serializable, CBInterestRate {

    private static final long serialVersionUID = 1L;
    private static final CopyFromHelper COPY_FROM_HELPER;

    private final ObjectBean objBean;
    private CBObservation observation;
    private String rateName;
    private String rateType;
    private CBObservationPeriod observationPeriod;


    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("rateName", String.class);
        basePropInterfaceMap.put("rateType", String.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();
        basePropClassImplMap.put(CBObservation.class, CBObservationImpl.class);
        basePropClassImplMap.put(CBObservationPeriod.class, CBObservationPeriodImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(CBInterestRate.class, basePropInterfaceMap, basePropClassImplMap);
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public CBInterestRateImpl() {
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
        if (!(other instanceof CBInterestRateImpl)) {
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
    public Class<CBInterestRate> getInterface() {
        return CBInterestRate.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    /**
     * Returns the CB InterestRate Observation.
     * <p>
     *
     * @return the CB InterestRate Observation, <b>null</b> if none.
     *
     */
	public CBObservation getObservation(){
		return observation;
	}

    /**
     * Sets the CB InterestRate Observation.
     * <p>
     *
     * @param value the CB InterestRate Observation to set, <b>null</b> if none.
     *
     */
    public void setObservation(CBObservation observation){
    	this.observation = observation;
    }
    
    /**
     * Returns the CB InterestRate rateName.
     * <p>
     *
     * @return the CB InterestRate rateName, <b>null</b> if none.
     *
     */
    public String getRateName(){
    	return rateName;
    }

    /**
     * Sets the CB InterestRate rateName.
     * <p>
     *
     * @param rateName the CB InterestRate rateName to set, <b>null</b> if none.
     *
     */
    public void setRateName(String rateName){
    	this.rateName = rateName;
    }
    
    /**
     * Returns the CB InterestRate rateType.
     * <p>
     *
     * @return the CB InterestRate rateType, <b>null</b> if none.
     *
     */
    public String getRateType(){
    	return rateType;
    }

    /**
     * Sets the CB InterestRate rateType.
     * <p>
     *
     * @param rateType the CB InterestRate rateType to set, <b>null</b> if none.
     *
     */
    public void setRateType(String rateType){
    	this.rateType = rateType;
    }
    
    /**
     * Returns the CB InterestRate ObservationPeriod.
     * <p>
     *
     * @return the CB InterestRate ObservationPeriod, <b>null</b> if none.
     *
     */
    public CBObservationPeriod getObservationPeriod(){
    	return observationPeriod;
    }

    /**
     * Sets the CB InterestRate ObservationPeriod.
     * <p>
     *
     * @param observationPeriod the CB InterestRate ObservationPeriod to set, <b>null</b> if none.
     *
     */
    public void setObservationPeriod(CBObservationPeriod observationPeriod){
    	this.observationPeriod = observationPeriod;
    }

}
