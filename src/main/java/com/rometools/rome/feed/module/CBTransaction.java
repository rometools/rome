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
 * Transaction of the Central Bank ModuleImpl.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public interface CBTransaction extends Cloneable, CopyFrom {
    /**
     * Returns the CB Transaction Observation.
     * <p>
     *
     * @return the CB Transaction Observation, <b>null</b> if none.
     *
     */
	CBObservation getObservation();

    /**
     * Sets the CB Transaction Observation.
     * <p>
     *
     * @param observation the CB Transaction Observation to set, <b>null</b> if none.
     *
     */
    void setObservation(CBObservation observation);
    
    /**
     * Returns the CB Transaction transactionName.
     * <p>
     *
     * @return the CB Transaction transactionName, <b>null</b> if none.
     *
     */
    String getTransactionName();

    /**
     * Sets the CB Transaction transactionName.
     * <p>
     *
     * @param transactionName the CB Transaction transactionName to set, <b>null</b> if none.
     *
     */
    void setTransactionName(String transactionName);
    
    /**
     * Returns the CB Transaction transactionType.
     * <p>
     *
     * @return the CB Transaction transactionType, <b>null</b> if none.
     *
     */
    String getTransactionType();

    /**
     * Sets the CB Transaction transactionType.
     * <p>
     *
     * @param transactionType the CB Transaction transactionType to set, <b>null</b> if none.
     *
     */
    void setTransactionType(String transactionType);
    
    /**
     * Returns the CB Transaction ObservationPeriod.
     * <p>
     *
     * @return the CB Transaction ObservationPeriod, <b>null</b> if none.
     *
     */
    CBObservationPeriod getObservationPeriod();

    /**
     * Sets the CB Transaction ObservationPeriod.
     * <p>
     *
     * @param observationPeriod the CB Transaction ObservationPeriod to set, <b>null</b> if none.
     *
     */
    void setObservationPeriod(CBObservationPeriod observationPeriod);
    
    /**
     * Returns the CB Transaction transactionTerm.
     * <p>
     *
     * @return the CB Transaction transactionTerm, <b>null</b> if none.
     *
     */
    String getTransactionTerm();

    /**
     * Sets the CB Transaction transactionTerm.
     * <p>
     *
     * @param transactionTerm the CB Transaction transactionTerm to set, <b>null</b> if none.
     *
     */
    void setTransactionTerm(String transactionTerm);
}
