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

/**
 * Subject of the CB ModuleImpl, default implementation.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public class CBOtherStatisticsImpl implements Cloneable, Serializable, CBOtherStatistics {

    private static final long serialVersionUID = 1L;
    private static final CopyFromHelper COPY_FROM_HELPER;

    private final ObjectBean objBean;
    private CBObservation observation;
    private String topic;
    private String coverage;
    private CBObservationPeriod observationPeriod;
    private String dataType;

    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("topic", String.class);
        basePropInterfaceMap.put("coverage", String.class);
        basePropInterfaceMap.put("dataType", String.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();
        basePropClassImplMap.put(CBObservation.class, CBObservationImpl.class);
        basePropClassImplMap.put(CBObservationPeriod.class, CBObservationPeriodImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(CBOtherStatistics.class, basePropInterfaceMap, basePropClassImplMap);
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public CBOtherStatisticsImpl() {
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
        if (!(other instanceof CBOtherStatisticsImpl)) {
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
    public Class<CBOtherStatistics> getInterface() {
        return CBOtherStatistics.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    /**
     * Returns the CB OtherStatistics Observation.
     * <p>
     *
     * @return the CB OtherStatistics Observation, <b>null</b> if none.
     *
     */
    public CBObservation getObservation(){
    	return observation;
    }

    /**
     * Sets the CB Transaction Observation.
     * <p>
     *
     * @param observation the CB OtherStatistics Observation to set, <b>null</b> if none.
     *
     */
	public void setObservation(CBObservation observation){
		this.observation = observation;
	}
    
    /**
     * Returns the CB OtherStatistics topic.
     * <p>
     *
     * @return the CB OtherStatistics topic, <b>null</b> if none.
     *
     */
    public String getTopic(){
    	return topic;
    }

    /**
     * Sets the CB OtherStatistics topic.
     * <p>
     *
     * @param topic the CB OtherStatistics topic to set, <b>null</b> if none.
     *
     */
    public void setTopic(String topic){
    	this.topic = topic;
    }
    
    /**
     * Returns the CB OtherStatistics coverage.
     * <p>
     *
     * @return the CB OtherStatistics coverage, <b>null</b> if none.
     *
     */
    public String getCoverage(){
    	return coverage;
    }

    /**
     * Sets the CB OtherStatistics coverage.
     * <p>
     *
     * @param coverage the CB OtherStatistics coverage to set, <b>null</b> if none.
     *
     */
    public void setCoverage(String coverage){
    	this.coverage = coverage;
    }
    
    /**
     * Returns the CB OtherStatistics ObservationPeriod.
     * <p>
     *
     * @return the CB OtherStatistics ObservationPeriod, <b>null</b> if none.
     *
     */
    public CBObservationPeriod getObservationPeriod(){
    	return observationPeriod;
    }

    /**
     * Sets the CB OtherStatistics ObservationPeriod.
     * <p>
     *
     * @param observationPeriod the CB OtherStatistics ObservationPeriod to set, <b>null</b> if none.
     *
     */
    public void setObservationPeriod(CBObservationPeriod observationPeriod){
    	this.observationPeriod = observationPeriod;
    }
    
    /**
     * Returns the CB OtherStatistics dataType.
     * <p>
     *
     * @return the CB OtherStatistics dataType, <b>null</b> if none.
     *
     */
    public String getDataType(){
    	return dataType;
    }

    /**
     * Sets the CB OtherStatistics dataType.
     * <p>
     *
     * @param dataType the CB OtherStatistics dataType to set, <b>null</b> if none.
     *
     */
    public void setDataType(String dataType){
    	this.dataType = dataType;
    }

}
