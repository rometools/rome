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
 * Observation Period of the Central Bank ModuleImpl, default implementation.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Munavar Basha <munavar.basha@metricstream.com>
 *
 */
public class CBObservationPeriodImpl implements Cloneable, Serializable, CBObservationPeriod {
	
	
    private static final long serialVersionUID = 1L;
    private static final CopyFromHelper COPY_FROM_HELPER;
    private final ObjectBean objBean;

	private String frequency;
	private String period;
	
	
    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("frequency", String.class);
        basePropInterfaceMap.put("period", String.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();


        COPY_FROM_HELPER = new CopyFromHelper(CBSpeech.class, basePropInterfaceMap, basePropClassImplMap);
    }
    
    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public CBObservationPeriodImpl() {
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
        if (!(other instanceof CBObservationPeriodImpl)) {
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
    public Class<CBObservationPeriod> getInterface() {
        return CBObservationPeriod.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }
	
    /**
     * Gets the value of the frequency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	/* (non-Javadoc)
	 * @see com.rometools.rome.feed.module.CBObservationPeriod#getFrequency()
	 */
	@Override
	public String getFrequency() {
		return frequency;
	}

    /**
     * Sets the value of the frequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	/* (non-Javadoc)
	 * @see com.rometools.rome.feed.module.CBObservationPeriod#setFrequency(java.lang.String)
	 */
	@Override
	public void setFrequency(String frequency) {
		this.frequency = frequency;

	}

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	/* (non-Javadoc)
	 * @see com.rometools.rome.feed.module.CBObservationPeriod#getPeriod()
	 */
	@Override
	public String getPeriod() {
		return period;
	}

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	/* (non-Javadoc)
	 * @see com.rometools.rome.feed.module.CBObservationPeriod#setPeriod(java.lang.String)
	 */
	@Override
	public void setPeriod(String period) {
		this.period = period;

	}

}
