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

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.utils.Dates;

/**
 * Syndication ModuleImpl, default implementation.
 * <p>
 *
 * @see <a href="http://web.resource.org/rss/1.0/modules/syndication/">Syndication module</a>.
 */
public class SyModuleImpl extends ModuleImpl implements SyModule {

    private static final long serialVersionUID = 1L;

    private static final Set<String> PERIODS = new HashSet<String>();
    private static final CopyFromHelper COPY_FROM_HELPER;

    private String updatePeriod;
    private int updateFrequency;
    private Date updateBase;

    static {

        PERIODS.add(HOURLY);
        PERIODS.add(DAILY);
        PERIODS.add(WEEKLY);
        PERIODS.add(MONTHLY);
        PERIODS.add(YEARLY);

        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("updatePeriod", String.class);
        basePropInterfaceMap.put("updateFrequency", Integer.TYPE);
        basePropInterfaceMap.put("updateBase", Date.class);
        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = Collections.<Class<? extends CopyFrom>, Class<?>> emptyMap();
        COPY_FROM_HELPER = new CopyFromHelper(SyModule.class, basePropInterfaceMap, basePropClassImplMap);

    }

    public SyModuleImpl() {
        super(SyModule.class, URI);
    }

    /**
     * Returns the Syndication module update period.
     * <p>
     *
     * @return the Syndication module update period, <b>null</b> if none.
     *
     */
    @Override
    public String getUpdatePeriod() {
        return updatePeriod;
    }

    /**
     * Sets the Syndication module update period.
     * <p>
     *
     * @param updatePeriod the Syndication module update period to set, <b>null</b> if none.
     *
     */
    @Override
    public void setUpdatePeriod(final String updatePeriod) {
        if (!PERIODS.contains(updatePeriod)) {
            throw new IllegalArgumentException("Invalid period [" + updatePeriod + "]");
        }
        this.updatePeriod = updatePeriod;
    }

    /**
     * Returns the Syndication module update frequency.
     * <p>
     *
     * @return the Syndication module update frequency, <b>null</b> if none.
     *
     */
    @Override
    public int getUpdateFrequency() {
        return updateFrequency;
    }

    /**
     * Sets the Syndication module update frequency.
     * <p>
     *
     * @param updateFrequency the Syndication module update frequency to set, <b>null</b> if none.
     *
     */
    @Override
    public void setUpdateFrequency(final int updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    /**
     * Returns the Syndication module update base date.
     * <p>
     *
     * @return the Syndication module update base date, <b>null</b> if none.
     *
     */
    @Override
    public Date getUpdateBase() {
        return Dates.copy(updateBase);
    }

    /**
     * Sets the Syndication module update base date.
     * <p>
     *
     * @param updateBase the Syndication module update base date to set, <b>null</b> if none.
     *
     */
    @Override
    public void setUpdateBase(final Date updateBase) {
        this.updateBase = Dates.copy(updateBase);
    }

    @Override
    public Class<? extends Module> getInterface() {
        return SyModule.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

}
