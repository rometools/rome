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
 * Person of the Central Bank ModuleImpl.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Norbert Kiesel <nkiesel@metricstream.com>
 * @author Manish SV Kumar <manish.svk@metricstream.com>
 *
 */
public interface CBPerson extends Cloneable, CopyFrom {
    /**
     * Returns the CB person givenName.
     * <p>
     *
     * @return the CB person givenName, <b>null</b> if none.
     *
     */
    String getGivenName();

    /**
     * Sets the CB person givenName.
     * <p>
     *
     * @param givenName the CB person givenName to set, <b>null</b> if none.
     *
     */
    void setGivenName(String givenName);
    
    /**
     * Returns the CB person surname.
     * <p>
     *
     * @return the CB person surname, <b>null</b> if none.
     *
     */
    String getSurname();

    /**
     * Sets the CB person surname.
     * <p>
     *
     * @param surname the CB person surname to set, <b>null</b> if none.
     *
     */
    void setSurname(String surname);
    
    /**
     * Returns the CB person personalTitle.
     * <p>
     *
     * @return the CB person personalTitle, <b>null</b> if none.
     *
     */
    String getPersonalTitle();

    /**
     * Sets the CB person personalTitle.
     * <p>
     *
     * @param personalTitle the CB person personalTitle to set, <b>null</b> if none.
     *
     */
    void setPersonalTitle(String personalTitle);

    /**
     * Returns the CB person nameAsWritten.
     * <p>
     *
     * @return the CB person nameAsWritten, <b>null</b> if none.
     *
     */
    String getNameAsWritten();

    /**
     * Sets the CB person nameAsWritten.
     * <p>
     *
     * @param nameAsWritten the CB person nameAsWritten to set, <b>null</b> if none.
     *
     */
    void setNameAsWritten(String nameAsWritten);
    
    /**
     * Returns the CB role.
     * <p>
     *
     * @return the CB role, <b>null</b> if none.
     *
     */
    CBRole getRole();

    /**
     * Sets the CB role.
     * <p>
     *
     * @param role the CB person role to set, <b>null</b> if none.
     *
     */
    void setRole(CBRole role);
}
