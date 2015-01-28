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
 * Observation of the Central Bank ModuleImpl, default implementation.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public class CBObservationImpl implements Cloneable, Serializable, CBObservation {

    private static final long serialVersionUID = 1L;
    private static final CopyFromHelper COPY_FROM_HELPER;

    private final ObjectBean objBean;
    private String value;
    private String unit;
    private String unit_mult;
    private String decimals;


    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("value", String.class);
        basePropInterfaceMap.put("unit", String.class);
        basePropInterfaceMap.put("unit_mult", String.class);
        basePropInterfaceMap.put("decimals", String.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();

        COPY_FROM_HELPER = new CopyFromHelper(CBObservation.class, basePropInterfaceMap, basePropClassImplMap);
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public CBObservationImpl() {
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
        if (!(other instanceof CBObservationImpl)) {
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
    public Class<CBObservation> getInterface() {
        return CBObservation.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    /**
     * Returns the CB observation value.
     * <p>
     *
     * @return the CB observation value, <b>null</b> if none.
     *
     */
    @Override
    public String getValue() {
    	return value;
    }

    /**
     * Sets the CB observation value.
     * <p>
     *
     * @param value the CB observation value to set, <b>null</b> if none.
     *
     */
    @Override
    public void setValue(String value) {
    	this.value = value;
    }

    /**
     * Returns the CB Observation unit.
     * <p>
     *
     * @return the CB Observation unit, <b>null</b> if none.
     *
     */
    @Override
    public String getUnit(){
    	return unit;
    }

    /**
     * Sets the CB Observation unit.
     * <p>
     *
     * @param unit the CB Observation unit to set, <b>null</b> if none.
     *
     */
    public void setUnit(String unit){
    	this.unit = unit;
    }

    /**
     * Returns the CB Observation unit_mult.
     * <p>
     *
     * @return the CB Observation unit_mult, <b>null</b> if none.
     *
     */
    public String getUnitMult(){
    	return unit_mult;
    }

    /**
     * Sets the CB Observation unit_mult.
     * <p>
     *
     * @param unitMult the CB Observation unit_mult to set, <b>null</b> if none.
     *
     */
    public void setUnitMult(String unitMult){
    	this.unit_mult = unitMult;
    }
    
    /**
     * Returns the CB Observation decimals.
     * <p>
     *
     * @return the CB Observation decimals, <b>null</b> if none.
     *
     */
    public String getDecimal(){
    	return decimals;
    }

    /**
     * Sets the CB Observation decimal.
     * <p>
     *
     * @param decimal the CB Observation decimal to set, <b>null</b> if none.
     *
     */
    public void setDecimal(String decimals){
    	this.decimals = decimals;
    }

}
