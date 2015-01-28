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

import java.util.List;

import com.rometools.rome.feed.CopyFrom;

/**
 * Event of the Central Bank ModuleImpl.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Norbert Kiesel <nkiesel@metricstream.com>
 *
 */
public interface CBEvent extends Cloneable, CopyFrom {
    /**
     * Returns the CB event simpleTitle.
     * <p>
     *
     * @return the CB event simpleTitle, <b>null</b> if none.
     *
     */
    String getSimpleTitle();

    /**
     * Sets the CB event simpleTitle.
     * <p>
     *
     * @param simpleTitle the CB event simpleTitle to set, <b>null</b> if none.
     *
     */
    void setSimpleTitle(String simpleTitle);

	/**
     * Returns the CB event occurenceDate.
     * <p>
     *
     * @return the CB event occurenceDate, <b>null</b> if none.
     *
     */
    String getOccurenceDate();

    /**
     * Sets the CB event occurenceDate.
     * <p>
     *
     * @param occurenceDate the CB event occurenceDate to set, <b>null</b> if none.
     *
     */
    void setOccurenceDate(String occurenceDate);

	/**
     * Returns the CB event institutionAbbrev.
     * <p>
     *
     * @return the CB event institutionAbbrev, <b>null</b> if none.
     *
     */
    String getInstitutionAbbrev();

    /**
     * Sets the CB event institutionAbbrev.
     * <p>
     *
     * @param institutionAbbrev the CB event institutionAbbrev to set, <b>null</b> if none.
     *
     */
    void setInstitutionAbbrev(String institutionAbbrev);

	/**
     * Returns the CB event audience.
     * <p>
     *
     * @return the CB event audience, <b>null</b> if none.
     *
     */
    String getAudience();

    /**
     * Sets the CB event audience.
     * <p>
     *
     * @param audience the CB event audience to set, <b>null</b> if none.
     *
     */
    void setAudience(String audience);

	/**
     * Returns the CB event keyword.
     * <p>
     *
     * @return the CB event keyword, <b>null</b> if none.
     *
     */
    List<String> getKeywords();
    
    /**
     * Sets the CB event keyword.
     * <p>
     *
     * @param strkeyList the CB event keyword to set, <b>null</b> if none.
     *
     */
    void setKeywords(List<String> strkeyList);

	/**
     * Returns the CB event resource.
     * <p>
     *
     * @return the CB event resource, <b>null</b> if none.
     *
     */
    CBResource getResource();

    /**
     * Sets the CB event resource.
     * <p>
     *
     * @param resource the CB event resource to set, <b>null</b> if none.
     *
     */
    void setResource(CBResource resource);

	/**
     * Returns the CB event person.
     * <p>
     *
     * @return the CB event person, <b>null</b> if none.
     *
     */
    CBPerson getPerson();

    /**
     * Sets the CB event person.
     * <p>
     *
     * @param person the CB event person to set, <b>null</b> if none.
     *
     */
    void setPerson(CBPerson person);

	/**
     * Returns the CB event venue.
     * <p>
     *
     * @return the CB event venue, <b>null</b> if none.
     *
     */
    String getVenue();

    /**
     * Sets the CB event venue.
     * <p>
     *
     * @param venue the CB event venue to set, <b>null</b> if none.
     *
     */
    void setVenue(String venue);

	/**
     * Returns the CB event locationAsWritten.
     * <p>
     *
     * @return the CB event locationAsWritten, <b>null</b> if none.
     *
     */
    String getLocationAsWritten();

    /**
     * Sets the CB event locationAsWritten.
     * <p>
     *
     * @param locationAsWritten the CB event locationAsWritten to set, <b>null</b> if none.
     *
     */
    void setLocationAsWritten(String locationAsWritten);

	/**
     * Returns the CB event locationCountry.
     * <p>
     *
     * @return the CB event locationCountry, <b>null</b> if none.
     *
     */
    String getLocationCountry();

    /**
     * Sets the CB event locationCountry.
     * <p>
     *
     * @param locationCountry the CB event locationCountry to set, <b>null</b> if none.
     *
     */
    void setLocationCountry(String locationCountry);

	/**
     * Returns the CB event locationState.
     * <p>
     *
     * @return the CB event locationState, <b>null</b> if none.
     *
     */
    String getLocationState();

    /**
     * Sets the CB event locationState.
     * <p>
     *
     * @param locationState the CB event locationState to set, <b>null</b> if none.
     *
     */
    void setLocationState(String locationState);

	/**
     * Returns the CB event locationCity.
     * <p>
     *
     * @return the CB event locationCity, <b>null</b> if none.
     *
     */
    String getLocationCity();

    /**
     * Sets the CB event locationCity.
     * <p>
     *
     * @param locationCity the CB event locationCity to set, <b>null</b> if none.
     *
     */
    void setLocationCity(String locationCity);

	/**
     * Returns the CB event eventDateEnd.
     * <p>
     *
     * @return the CB event eventDateEnd, <b>null</b> if none.
     *
     */
    String getEventDateEnd();

    /**
     * Sets the CB event eventDateEnd.
     * <p>
     *
     * @param eventDateEnd the CB event eventDateEnd to set, <b>null</b> if none.
     *
     */
    void setEventDateEnd(String eventDateEnd);

}
