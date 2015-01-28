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

/**
 * Interest Rate of the Central Bank ModuleImpl.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public interface CBInterestRate extends Cloneable, CopyFrom {
    /**
     * Returns the CB InterestRate Observation.
     * <p>
     *
     * @return the CB InterestRate Observation, <b>null</b> if none.
     *
     */
	CBObservation getObservation();

    /**
     * Sets the CB InterestRate Observation.
     * <p>
     *
     * @param value the CB InterestRate Observation to set, <b>null</b> if none.
     *
     */
    void setObservation(CBObservation observation);
    
    /**
     * Returns the CB InterestRate rateName.
     * <p>
     *
     * @return the CB InterestRate rateName, <b>null</b> if none.
     *
     */
    String getRateName();

    /**
     * Sets the CB InterestRate rateName.
     * <p>
     *
     * @param rateName the CB InterestRate rateName to set, <b>null</b> if none.
     *
     */
    void setRateName(String rateName);
    
    /**
     * Returns the CB InterestRate rateType.
     * <p>
     *
     * @return the CB InterestRate rateType, <b>null</b> if none.
     *
     */
    String getRateType();

    /**
     * Sets the CB InterestRate rateType.
     * <p>
     *
     * @param rateType the CB InterestRate rateType to set, <b>null</b> if none.
     *
     */
    void setRateType(String rateType);
    
    /**
     * Returns the CB InterestRate ObservationPeriod.
     * <p>
     *
     * @return the CB InterestRate ObservationPeriod, <b>null</b> if none.
     *
     */
    CBObservationPeriod getObservationPeriod();

    /**
     * Sets the CB InterestRate ObservationPeriod.
     * <p>
     *
     * @param observationPeriod the CB InterestRate ObservationPeriod to set, <b>null</b> if none.
     *
     */
    void setObservationPeriod(CBObservationPeriod observationPeriod);
}
