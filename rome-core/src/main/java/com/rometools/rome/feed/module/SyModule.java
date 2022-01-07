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

 *
 * @see <a href="http://web.resource.org/rss/1.0/modules/syndication/">Syndication module</a>
 */
public interface SyModule extends Module {

    /**
     * URI of the Syndication ModuleImpl (http://purl.org/rss/1.0/modules/syndication/).
     *
     */
    static final String URI = "http://purl.org/rss/1.0/modules/syndication/";

    /** the HOURLY constant */
    static final String HOURLY = "hourly";
    /** the DAILY constant */
    static final String DAILY = "daily";
    /** the WEEKLY constant */
    static final String WEEKLY = "weekly";
    /** the MONTHLY constant */
    static final String MONTHLY = "monthly";
    /** the YEARLY constant */
    static final String YEARLY = "yearly";

    /**
     * Returns the Syndication module update period.

     *
     * @return the Syndication module update period, <b>null</b> if none.
     *
     */
    String getUpdatePeriod();

    /**
     * Sets the Syndication module update period.

     *
     * @param updatePeriod the Syndication module update period to set, <b>null</b> if none.
     *
     */
    void setUpdatePeriod(String updatePeriod);

    /**
     * Returns the Syndication module update frequency.

     *
     * @return the Syndication module update frequency, <b>null</b> if none.
     *
     */
    int getUpdateFrequency();

    /**
     * Sets the Syndication module update frequency.

     *
     * @param updateFrequency the Syndication module update frequency to set, <b>null</b> if none.
     *
     */
    void setUpdateFrequency(int updateFrequency);

    /**
     * Returns the Syndication module update base date.

     *
     * @return the Syndication module update base date, <b>null</b> if none.
     *
     */
    Date getUpdateBase();

    /**
     * Sets the Syndication module update base date.

     *
     * @param updateBase the Syndication module update base date to set, <b>null</b> if none.
     *
     */
    void setUpdateBase(Date updateBase);

}
