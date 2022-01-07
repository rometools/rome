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

import com.rometools.modules.base.types.GenderEnumeration;

/**
 * This is an interface for the GoogleBase plug in that exposes methods used for Person (personal
 * ad, resume, profile) entry types.
 */
public interface Person extends GlobalInterface {
    /**
     * Age of the individual.
     *
     * @param age Age of the individual.
     */
    public void setAge(Integer age);

    /**
     * Age of the individual.
     *
     * @return Age of the individual.
     */
    public Integer getAge();

    /**
     * Education of the individual.
     *
     * @param education Education of the individual.
     */
    public void setEducation(String education);

    /**
     * Education of the individual.
     *
     * @return Education of the individual.
     */
    public String getEducation();

    /**
     * Individuals employer.
     *
     * @param employer Individuals employer.
     */
    public void setEmployer(String employer);

    /**
     * Individuals employer.
     *
     *
     * @return Individuals employer.
     */
    public String getEmployer();

    /**
     * Ethnicity of the individual in the People profiles bulk upload entry.
     *
     *
     * @param ethnicities Ethnicity of the individual in the People profiles bulk upload entry.
     */
    public void setEthnicities(String[] ethnicities);

    /**
     * Ethnicity of the individual in the People profiles bulk upload entry.
     *
     *
     * @return Ethnicity of the individual in the People profiles bulk upload entry.
     */
    public String[] getEthnicities();

    /**
     * Gender of an individual in a People profiles bulk upload.
     *
     *
     * @param gender Gender of an individual in a People profiles bulk upload.
     */
    public void setGender(GenderEnumeration gender);

    /**
     * Gender of an individual in a People profiles bulk upload.
     *
     *
     * @return Gender of an individual in a People profiles bulk upload.
     */
    public GenderEnumeration getGender();

    /**
     * Interest of a person being profiled.
     *
     *
     * @param interestedIn Interest of a person being profiled.
     */
    public void setInterestedIn(String[] interestedIn);

    /**
     * Interest of a person being profiled.
     *
     *
     * @return Interest of a person being profiled.
     */
    public String[] getInterestedIn();

    /**
     * Location of a person.
     *
     * @param location Location of a person.
     */
    public void setLocation(String location);

    /**
     * Location of a person.
     *
     * @return Location of a person.
     */
    public String getLocation();

    /**
     * Marital status of an individual.
     *
     *
     * @param maritalStatus Marital status of an individual.
     */
    public void setMaritalStatus(String maritalStatus);

    /**
     * Marital status of an individual.
     *
     * @return Marital status of an individual.
     */
    public String getMaritalStatus();

    /**
     * Occupation of an individual.
     *
     * @param occupation Occupation of an individual.
     */
    public void setOccupation(String occupation);

    /**
     * Occupation of an individual.
     *
     * @return Occupation of an individual.
     */
    public String getOccupation();

    /**
     * Individual's sexual orientation.
     *
     * @param sexualOrientation Individual's sexual orientation.
     */
    public void setSexualOrientation(String sexualOrientation);

    /**
     * Individual's sexual orientation.
     *
     * @return Individual's sexual orientation.
     */
    public String getSexualOrientation();
}
