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

import java.util.List;

import com.rometools.rome.feed.CopyFrom;

/**
 * Subject of the Dublin Core ModuleImpl.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Norbert Kiesel <nkiesel@metricstream.com>
 *
 */
public interface CBNews extends Cloneable, CopyFrom {
    /**
     * Returns the CB news simpleTitle.
     * <p>
     *
     * @return the CB news simpleTitle, <b>null</b> if none.
     *
     */
    String getSimpleTitle();

    /**
     * Sets the CB news simpleTitle.
     * <p>
     *
     * @param simpleTitle the CB news simpleTitle to set, <b>null</b> if none.
     *
     */
    void setSimpleTitle(String simpleTitle);

	/**
     * Returns the CB news occurenceDate.
     * <p>
     *
     * @return the CB news occurenceDate, <b>null</b> if none.
     *
     */
    String getOccurenceDate();

    /**
     * Sets the CB news occurenceDate.
     * <p>
     *
     * @param occurenceDate the CB news occurenceDate to set, <b>null</b> if none.
     *
     */
    void setOccurenceDate(String occurenceDate);

	/**
     * Returns the CB news institutionAbbrev.
     * <p>
     *
     * @return the CB news institutionAbbrev, <b>null</b> if none.
     *
     */
    String getInstitutionAbbrev();

    /**
     * Sets the CB news institutionAbbrev.
     * <p>
     *
     * @param institutionAbbrev the CB news institutionAbbrev to set, <b>null</b> if none.
     *
     */
    void setInstitutionAbbrev(String institutionAbbrev);

	/**
     * Returns the CB news keyword.
     * <p>
     *
     * @return the CB news keyword, <b>null</b> if none.
     *
     */
    List<String> getKeyword();
    
    /**
     * Sets the CB news keyword.
     * <p>
     *
     * @param strkey the CB paper news to set, <b>null</b> if none.
     *
     */
    void setKeyword(List<String> strkey);

	/**
     * Returns the CB news resource.
     * <p>
     *
     * @return the CB news resource, <b>null</b> if none.
     *
     */
    CBResource getResource();

    /**
     * Sets the CB news resource.
     * <p>
     *
     * @param resource the CB news resource to set, <b>null</b> if none.
     *
     */
    void setResource(CBResource resource);

	/**
     * Returns the CB news person.
     * <p>
     *
     * @return the CB news person, <b>null</b> if none.
     *
     */
    CBPerson getPerson();

    /**
     * Sets the CB news person.
     * <p>
     *
     * @param person the CB news person to set, <b>null</b> if none.
     *
     */
    void setPerson(CBPerson person);
}
