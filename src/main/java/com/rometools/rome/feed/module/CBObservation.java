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
 * Observation of the Central Bank ModuleImpl.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public interface CBObservation extends Cloneable, CopyFrom {
    /**
     * Returns the CB Observation value.
     * <p>
     *
     * @return the CB Observation value, <b>null</b> if none.
     *
     */
    String getValue();

    /**
     * Sets the CB Observation value.
     * <p>
     *
     * @param value the CB Observation value to set, <b>null</b> if none.
     *
     */
    void setValue(String value);
    
    /**
     * Returns the CB Observation unit.
     * <p>
     *
     * @return the CB Observation unit, <b>null</b> if none.
     *
     */
    String getUnit();

    /**
     * Sets the CB Observation unit.
     * <p>
     *
     * @param unit the CB Observation unit to set, <b>null</b> if none.
     *
     */
    void setUnit(String unit);

    /**
     * Returns the CB Observation unit_mult.
     * <p>
     *
     * @return the CB Observation unit_mult, <b>null</b> if none.
     *
     */
    String getUnitMult();

    /**
     * Sets the CB Observation unit_mult.
     * <p>
     *
     * @param unitMult the CB Observation unit_mult to set, <b>null</b> if none.
     *
     */
    void setUnitMult(String unitMult);
    
    /**
     * Returns the CB Observation decimals.
     * <p>
     *
     * @return the CB Observation decimals, <b>null</b> if none.
     *
     */
    String getDecimal();

    /**
     * Sets the CB Observation decimal.
     * <p>
     *
     * @param decimal the CB Observation decimal to set, <b>null</b> if none.
     *
     */
    void setDecimal(String decimal);
}
