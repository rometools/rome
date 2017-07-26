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

import java.util.Date;

/**
 * Syndication ModuleImpl.
 * <p>
 *
 * @see <a href="http://web.resource.org/rss/1.0/modules/syndication/">Syndication module</a>.
 */
public interface SyModule extends Module {

    /**
     * URI of the Syndication ModuleImpl (http://purl.org/rss/1.0/modules/syndication/).
     *
     */
    static final String URI = "http://purl.org/rss/1.0/modules/syndication/";

    static final String HOURLY = "hourly";
    static final String DAILY = "daily";
    static final String WEEKLY = "weekly";
    static final String MONTHLY = "monthly";
    static final String YEARLY = "yearly";

    /**
     * Returns the Syndication module update period.
     * <p>
     *
     * @return the Syndication module update period, <b>null</b> if none.
     *
     */
    String getUpdatePeriod();

    /**
     * Sets the Syndication module update period.
     * <p>
     *
     * @param updatePeriod the Syndication module update period to set, <b>null</b> if none.
     *
     */
    void setUpdatePeriod(String updatePeriod);

    /**
     * Returns the Syndication module update frequency.
     * <p>
     *
     * @return the Syndication module update frequency, <b>null</b> if none.
     *
     */
    int getUpdateFrequency();

    /**
     * Sets the Syndication module update frequency.
     * <p>
     *
     * @param updateFrequency the Syndication module update frequency to set, <b>null</b> if none.
     *
     */
    void setUpdateFrequency(int updateFrequency);

    /**
     * Returns the Syndication module update base date.
     * <p>
     *
     * @return the Syndication module update base date, <b>null</b> if none.
     *
     */
    Date getUpdateBase();

    /**
     * Sets the Syndication module update base date.
     * <p>
     *
     * @param updateBase the Syndication module update base date to set, <b>null</b> if none.
     *
     */
    void setUpdateBase(Date updateBase);

}
