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
 * Subject of the Dublin Core ModuleImpl.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public interface CBOtherStatistics extends Cloneable, CopyFrom {
    /**
     * Returns the CB OtherStatistics Observation.
     * <p>
     *
     * @return the CB OtherStatistics Observation, <b>null</b> if none.
     *
     */
	CBObservation getObservation();

    /**
     * Sets the CB Transaction Observation.
     * <p>
     *
     * @param observation the CB OtherStatistics Observation to set, <b>null</b> if none.
     *
     */
    void setObservation(CBObservation observation);
    
    /**
     * Returns the CB OtherStatistics topic.
     * <p>
     *
     * @return the CB OtherStatistics topic, <b>null</b> if none.
     *
     */
    String getTopic();

    /**
     * Sets the CB OtherStatistics topic.
     * <p>
     *
     * @param topic the CB OtherStatistics topic to set, <b>null</b> if none.
     *
     */
    void setTopic(String topic);
    
    /**
     * Returns the CB OtherStatistics coverage.
     * <p>
     *
     * @return the CB OtherStatistics coverage, <b>null</b> if none.
     *
     */
    String getCoverage();

    /**
     * Sets the CB OtherStatistics coverage.
     * <p>
     *
     * @param coverage the CB OtherStatistics coverage to set, <b>null</b> if none.
     *
     */
    void setCoverage(String coverage);
    
    /**
     * Returns the CB OtherStatistics ObservationPeriod.
     * <p>
     *
     * @return the CB OtherStatistics ObservationPeriod, <b>null</b> if none.
     *
     */
    CBObservationPeriod getObservationPeriod();

    /**
     * Sets the CB OtherStatistics ObservationPeriod.
     * <p>
     *
     * @param observationPeriod the CB OtherStatistics ObservationPeriod to set, <b>null</b> if none.
     *
     */
    void setObservationPeriod(CBObservationPeriod observationPeriod);
    
    /**
     * Returns the CB OtherStatistics dataType.
     * <p>
     *
     * @return the CB OtherStatistics dataType, <b>null</b> if none.
     *
     */
    String getDataType();

    /**
     * Sets the CB OtherStatistics dataType.
     * <p>
     *
     * @param dataType the CB OtherStatistics dataType to set, <b>null</b> if none.
     *
     */
    void setDataType(String dataType);
}
