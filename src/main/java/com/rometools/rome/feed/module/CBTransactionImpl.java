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
 * Transaction of the Central Bank ModuleImpl, default implementation.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public class CBTransactionImpl implements Cloneable, Serializable, CBTransaction {

    private static final long serialVersionUID = 1L;
    private static final CopyFromHelper COPY_FROM_HELPER;

    private final ObjectBean objBean;
    private CBObservation observation;
    private String transactionName;
    private String transactionType;
    private CBObservationPeriod observationPeriod;
    private String transactionTerm;

    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("transactionName", String.class);
        basePropInterfaceMap.put("transactionType", String.class);
        basePropInterfaceMap.put("transactionTerm", String.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();
        basePropClassImplMap.put(CBObservation.class, CBObservationImpl.class);
        basePropClassImplMap.put(CBObservationPeriod.class, CBObservationPeriodImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(CBTransaction.class, basePropInterfaceMap, basePropClassImplMap);
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public CBTransactionImpl() {
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
        if (!(other instanceof CBTransactionImpl)) {
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
    public Class<CBTransaction> getInterface() {
        return CBTransaction.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    /**
     * Returns the CB Transaction Observation.
     * <p>
     *
     * @return the CB Transaction Observation, <b>null</b> if none.
     *
     */
	public CBObservation getObservation(){
		return observation;
	}

    /**
     * Sets the CB Transaction Observation.
     * <p>
     *
     * @param observation the CB Transaction Observation to set, <b>null</b> if none.
     *
     */
    public void setObservation(CBObservation observation){
    	this.observation = observation;
    }
    
    /**
     * Returns the CB Transaction transactionName.
     * <p>
     *
     * @return the CB Transaction transactionName, <b>null</b> if none.
     *
     */
    public String getTransactionName(){
    	return transactionName;
    }

    /**
     * Sets the CB Transaction transactionName.
     * <p>
     *
     * @param transactionName the CB Transaction transactionName to set, <b>null</b> if none.
     *
     */
    public void setTransactionName(String transactionName){
    	this.transactionName = transactionName;
    }
    
    /**
     * Returns the CB Transaction transactionType.
     * <p>
     *
     * @return the CB Transaction transactionType, <b>null</b> if none.
     *
     */
    public String getTransactionType(){
    	return transactionType;
    }

    /**
     * Sets the CB Transaction transactionType.
     * <p>
     *
     * @param transactionType the CB Transaction transactionType to set, <b>null</b> if none.
     *
     */
    public void setTransactionType(String transactionType){
    	this.transactionType = transactionType;
    }
    
    /**
     * Returns the CB Transaction ObservationPeriod.
     * <p>
     *
     * @return the CB Transaction ObservationPeriod, <b>null</b> if none.
     *
     */
    public CBObservationPeriod getObservationPeriod(){
    	return observationPeriod;
    }

    /**
     * Sets the CB Transaction ObservationPeriod.
     * <p>
     *
     * @param observationPeriod the CB Transaction ObservationPeriod to set, <b>null</b> if none.
     *
     */
    public void setObservationPeriod(CBObservationPeriod observationPeriod){
    	this.observationPeriod = observationPeriod;
    }
    
    /**
     * Returns the CB Transaction transactionTerm.
     * <p>
     *
     * @return the CB Transaction transactionTerm, <b>null</b> if none.
     *
     */
    public String getTransactionTerm(){
    	return transactionTerm;
    }

    /**
     * Sets the CB Transaction transactionTerm.
     * <p>
     *
     * @param transactionTerm the CB Transaction transactionTerm to set, <b>null</b> if none.
     *
     */
    public void setTransactionTerm(String transactionTerm){
    	this.transactionTerm = transactionTerm;
    }

}
