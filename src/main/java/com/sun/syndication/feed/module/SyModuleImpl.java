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
package com.sun.syndication.feed.module;

import com.sun.syndication.feed.impl.CopyFromHelper;

import java.util.*;

/**
 * Syndication ModuleImpl, default implementation.
 * <p>
 * @see <a href="http://web.resource.org/rss/1.0/modules/syndication/">Syndication module</a>.
 * @author Alejandro Abdelnur
 *
 */
public class SyModuleImpl extends ModuleImpl implements SyModule {
    private static final Set PERIODS = new HashSet();

    static {
        PERIODS.add(HOURLY );
        PERIODS.add(DAILY  );
        PERIODS.add(WEEKLY );
        PERIODS.add(MONTHLY);
        PERIODS.add(YEARLY );
    }


    private String _updatePeriod;
    private int _updateFrequency;
    private Date _updateBase;

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public SyModuleImpl() {
        super(SyModule.class,URI);
    }

    /**
     * Returns the Syndication module update period.
     * <p>
     * @return the Syndication module update period, <b>null</b> if none.
     *
     */
    public String getUpdatePeriod() {
        return _updatePeriod;
    }

    /**
     * Sets the Syndication module update period.
     * <p>
     * @param updatePeriod the Syndication module update period to set, <b>null</b> if none.
     *
     */
    public void setUpdatePeriod(String updatePeriod) {
        if (!PERIODS.contains(updatePeriod)) {
            throw new IllegalArgumentException("Invalid period ["+updatePeriod+"]");
        }
        _updatePeriod = updatePeriod;
    }

    /**
     * Returns the Syndication module update frequency.
     * <p>
     * @return the Syndication module update frequency, <b>null</b> if none.
     *
     */
    public int getUpdateFrequency() {
        return _updateFrequency;
    }

    /**
     * Sets the Syndication module update frequency.
     * <p>
     * @param updateFrequency the Syndication module update frequency to set, <b>null</b> if none.
     *
     */
    public void setUpdateFrequency(int updateFrequency) {
        _updateFrequency = updateFrequency;
    }

    /**
     * Returns the Syndication module update base date.
     * <p>
     * @return the Syndication module update base date, <b>null</b> if none.
     *
     */
    public Date getUpdateBase() {
        return _updateBase;
    }

    /**
     * Sets the Syndication module update base date.
     * <p>
     * @param updateBase the Syndication module update base date to set, <b>null</b> if none.
     *
     */
    public void setUpdateBase(Date updateBase) {
        _updateBase = updateBase;
    }

    public Class getInterface() {
        return SyModule.class;
    }

    public void copyFrom(Object obj) {
        COPY_FROM_HELPER.copy(this,obj);
    }

    private static final CopyFromHelper COPY_FROM_HELPER;

    static {
        Map basePropInterfaceMap = new HashMap();
        basePropInterfaceMap.put("updatePeriod",String.class);
        basePropInterfaceMap.put("updateFrequency",Integer.TYPE);
        basePropInterfaceMap.put("updateBase",Date.class);

        Map basePropClassImplMap = Collections.EMPTY_MAP;

        COPY_FROM_HELPER = new CopyFromHelper(SyModule.class,basePropInterfaceMap,basePropClassImplMap);
    }

}
