/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
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
 */
package com.rometools.modules.base;

import com.rometools.modules.base.types.CurrencyEnumeration;
import com.rometools.modules.base.types.PriceTypeEnumeration;

/**
 * This is an interface for the GoogleBase plug in that exposes methods used for Job listing entry
 * types.
 */
public interface Job extends GlobalInterface {
    /**
     * Level of education required for an employment position.
     *
     * @param education Level of education required for an employment position.
     */
    public void setEducation(String education);

    /**
     * Level of education required for an employment position.
     *
     * @return Level of education required for an employment position.
     */
    public String getEducation();

    /**
     * Company providing employment.
     *
     * @param employer Company providing employment.
     */
    public void setEmployer(String employer);

    /**
     * Company providing employment.
     *
     * @return Company providing employment.
     */
    public String getEmployer();

    /**
     * Legal residency requirements for an employment position.
     *
     *
     * @param immigrationStatus Legal residency requirements for an employment position.
     */
    public void setImmigrationStatus(String immigrationStatus);

    /**
     * Legal residency requirements for an employment position.
     *
     *
     * @return Legal residency requirements for an employment position.
     */
    public String getImmigrationStatus();

    /**
     * The functions of an employment position.
     *
     *
     * @param function The functions of an employment position.
     */
    public void setJobFunctions(String[] function);

    /**
     * The functions of an employment position.
     *
     * @return The functions of an employment position.
     */
    public String[] getJobFunctions();

    /**
     * The industry of an employment position.
     *
     * @param jobIndustries The industry of an employment position.
     */
    public void setJobIndustries(String[] jobIndustries);

    /**
     * The industry of an employment position.
     *
     * @return The industry of an employment position.
     */
    public String[] getJobIndustries();

    /**
     * Type of employment position. Example: Full-time, part-time, contractor, etc.
     *
     * @param jobTypes Type of employment position. Example: Full-time, part-time, contractor, etc.
     */
    public void setJobTypes(String[] jobTypes);

    /**
     * Type of employment position. Example: Full-time, part-time, contractor, etc.
     *
     * @return Type of employment position. Example: Full-time, part-time, contractor, etc.
     */
    public String[] getJobTypes();

    /**
     * Location of the position.
     *
     * @param location Location of the position.
     */
    public void setLocation(String location);

    /**
     * Location of the position.
     *
     * @return Location of the position.
     */
    public String getLocation();

    /**
     * Salary for this position. Non-numeric values such as "$" symbols are not acceptable.
     *
     * @param salary Salary for this position. Non-numeric values such as "$" symbols are not
     *            acceptable.
     */
    public void setSalary(Float salary);

    /**
     * Salary for this position. Non-numeric values such as "$" symbols are not acceptable.
     *
     * @return Salary for this position. Non-numeric values such as "$" symbols are not acceptable.
     */
    public Float getSalary();

    /**
     * The type of salary included.
     *
     * @param salaryType The type of salary included.
     */
    public void setSalaryType(PriceTypeEnumeration salaryType);

    /**
     * The type of salary included.
     *
     * @return The type of salary included.
     */
    public PriceTypeEnumeration getSalaryType();

    /**
     * Currency of the price amount for an item.
     *
     * @param value Currency of the price amount for an item.
     */
    public void setCurrency(CurrencyEnumeration value);

    /**
     * Currency of the price amount for an item.
     *
     * @return Currency of the price amount for an item.
     */
    public CurrencyEnumeration getCurrency();

}
