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

import java.util.HashMap;
import java.util.Map;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;

/**
 * Exchange Rate of the Central Bank ModuleImpl, default implementation.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Munavar Basha <munavar.basha@metricstream.com>
 *
 */
public class CBExchangeRateImpl implements CBExchangeRate {
	
	
    private static final long serialVersionUID = 1L;
    private static final CopyFromHelper COPY_FROM_HELPER;
    private final ObjectBean objBean;
	
	private String baseCurrency;
	private String targetCurrency;
	private String rateType;
	
	private CBObservationPeriod observationPeriod;
	private CBObservation observation;
	
   static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("baseCurrency", String.class);
        basePropInterfaceMap.put("targetCurrency", String.class);
        basePropInterfaceMap.put("rateType", String.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();
        basePropClassImplMap.put(CBObservationPeriod.class, CBObservationPeriodImpl.class);
        basePropClassImplMap.put(CBObservation.class, CBObservationImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(CBSpeech.class, basePropInterfaceMap, basePropClassImplMap);
    }
    
    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public CBExchangeRateImpl() {
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
        if (!(other instanceof CBExchangeRateImpl)) {
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
    public Class<CBExchangeRate> getInterface() {
        return CBExchangeRate.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }


    /**
     * Gets the value of the observation property.
     * 
     * @return
     *     possible object is
     *     {@link CBObservation }
     *     
     */
	@Override
	public CBObservation getObservation() {
		return observation;
	}

    /**
     * Sets the value of the observation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CBObservation }
     *     
     */
	@Override
	public void setObservation(CBObservation observation) {
		this.observation = observation;

	}

    /**
     * Gets the value of the baseCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@Override
	public String getBaseCurrency() {
		return baseCurrency;
	}

    /**
     * Sets the value of the baseCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	@Override
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;

	}

    /**
     * Gets the value of the targetCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@Override
	public String getTargetCurrency() {
		return targetCurrency;
	}

    /**
     * Sets the value of the targetCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	@Override
	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;

	}

    /**
     * Gets the value of the rateType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	@Override
	public String getRateType() {
		return rateType;
	}

    /**
     * Sets the value of the rateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	@Override
	public void setRateType(String rateType) {
		this.rateType = rateType;

	}

    /**
     * Gets the value of the observationPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link CBObservationPeriod }
     *     
     */
	@Override
	public CBObservationPeriod getObservationPeriod() {
		return observationPeriod;
	}

    /**
     * Sets the value of the observationPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObservationPeriod }
     *     
     */
	@Override
	public void setObservationPeriod(CBObservationPeriod observationPeriod) {
		this.observationPeriod = observationPeriod;

	}

}
