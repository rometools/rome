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
public interface CBRole extends Cloneable, CopyFrom {
    /**
     * Returns the CBRole jobTitle.
     * <p>
     *
     * @return the CBRole jobTitle, <b>null</b> if none.
     *
     */
    String getJobTitle();

    /**
     * Sets the CBRole jobTitle.
     * <p>
     *
     * @param jobTitle the CBRole jobTitle to set, <b>null</b> if none.
     *
     */
    void setJobTitle(String jobTitle);

    /**
     * Returns the CBRole affiliation.
     * <p>
     *
     * @return the CBRole affiliation, <b>null</b> if none.
     *
     */
    String getAffiliation();

    /**
     * Sets the CBRole affiliation.
     * <p>
     *
     * @param affiliation the CBRole affiliation to set, <b>null</b> if none.
     *
     */
    void setAffiliation(String affiliation);
}
