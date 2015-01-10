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

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.CopyFromHelper;
import com.rometools.rome.feed.impl.ObjectBean;

/**
 * Subject of the CB ModuleImpl, default implementation.
 * <p>
 *
 * @see <a href="http://www.cbwiki.net/wiki/index.php/RSS-CBMain">RSS CB module</a>.
 * @author Norbert Kiesel <nkiesel@metricstream.com>
 *
 */
public class CBEventImpl implements Cloneable, Serializable, CBEvent {

    private static final long serialVersionUID = 1L;
    private static final CopyFromHelper COPY_FROM_HELPER;

    private final ObjectBean objBean;
    private String simpleTitle;
    private String occurenceDate;
    private String institutionAbbrev;
    private String audience;
    private String keyword;
    private CBResource resource;
    private CBPerson person;
    private String venue;
    private String locationAsWritten;
    private String locationCountry;
    private String locationState;
    private String locationCity;
    private String eventDateEnd;

    static {
        final Map<String, Class<?>> basePropInterfaceMap = new HashMap<String, Class<?>>();
        basePropInterfaceMap.put("simpleTitle", String.class);
        basePropInterfaceMap.put("occurenceDate", String.class);
        basePropInterfaceMap.put("institutionAbbrev", String.class);
        basePropInterfaceMap.put("audience", String.class);
        basePropInterfaceMap.put("keyword", String.class);
        basePropInterfaceMap.put("venue", String.class);
        basePropInterfaceMap.put("locationAsWritten", String.class);
        basePropInterfaceMap.put("locationCountry", String.class);
        basePropInterfaceMap.put("locationState", String.class);
        basePropInterfaceMap.put("locationCity", String.class);
        basePropInterfaceMap.put("eventDateEnd", String.class);

        final Map<Class<? extends CopyFrom>, Class<?>> basePropClassImplMap = new HashMap<Class<? extends CopyFrom>, Class<?>>();
        basePropClassImplMap.put(CBResource.class, CBResourceImpl.class);
        basePropClassImplMap.put(CBPerson.class, CBPersonImpl.class);

        COPY_FROM_HELPER = new CopyFromHelper(CBEvent.class, basePropInterfaceMap, basePropClassImplMap);
    }

    /**
     * Default constructor. All properties are set to <b>null</b>.
     * <p>
     *
     */
    public CBEventImpl() {
        objBean = new ObjectBean(this.getClass(), this);
    }

    /**
     * Creates a deep 'bean' clone of the object.
     * <p>
     *
     * @return a clone of the object.
     * @throws CloneNotSupportedException thrown if an element of the object cannot be cloned.
     *
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return objBean.clone();
    }

    /**
     * Indicates whether some other object is "equal to" this one as defined by the Object equals()
     * method.
     * <p>
     *
     * @param other he reference object with which to compare.
     * @return <b>true</b> if 'this' object is equal to the 'other' object.
     *
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof CBEventImpl)) {
            return false;
        }
        return objBean.equals(other);
    }

    /**
     * Returns a hashcode value for the object.
     * <p>
     * It follows the contract defined by the Object hashCode() method.
     * <p>
     *
     * @return the hashcode of the bean object.
     *
     */
    @Override
    public int hashCode() {
        return objBean.hashCode();
    }

    /**
     * Returns the String representation for the object.
     * <p>
     *
     * @return String representation for the object.
     *
     */
    @Override
    public String toString() {
        return objBean.toString();
    }

    @Override
    public Class<CBEvent> getInterface() {
        return CBEvent.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        COPY_FROM_HELPER.copy(this, obj);
    }

    /**
     * Returns the CB event simpleTitle.
     * <p>
     *
     * @return the CB event simpleTitle, <b>null</b> if none.
     *
     */
	@Override
    public String getSimpleTitle() {
		return simpleTitle;
	}

    /**
     * Sets the CB event simpleTitle.
     * <p>
     *
     * @param simpleTitle the CB event simpleTitle to set, <b>null</b> if none.
     *
     */
	@Override
    public void setSimpleTitle(String simpleTitle) {
		this.simpleTitle = simpleTitle;
	}

	/**
     * Returns the CB event occurenceDate.
     * <p>
     *
     * @return the CB event occurenceDate, <b>null</b> if none.
     *
     */
	@Override
    public String getOccurenceDate() {
		return occurenceDate;
	}

    /**
     * Sets the CB event occurenceDate.
     * <p>
     *
     * @param occurenceDate the CB event occurenceDate to set, <b>null</b> if none.
     *
     */
	@Override
    public void setOccurenceDate(String occurenceDate) {
		this.occurenceDate = occurenceDate;
	}

	/**
     * Returns the CB event institutionAbbrev.
     * <p>
     *
     * @return the CB event institutionAbbrev, <b>null</b> if none.
     *
     */
	@Override
    public String getInstitutionAbbrev() {
		return institutionAbbrev;
	}

    /**
     * Sets the CB event institutionAbbrev.
     * <p>
     *
     * @param institutionAbbrev the CB event institutionAbbrev to set, <b>null</b> if none.
     *
     */
	@Override
    public void setInstitutionAbbrev(String institutionAbbrev) {
		this.institutionAbbrev = institutionAbbrev;
	}

	/**
     * Returns the CB event audience.
     * <p>
     *
     * @return the CB event audience, <b>null</b> if none.
     *
     */
	@Override
    public String getAudience() {
		return audience;
	}

    /**
     * Sets the CB event audience.
     * <p>
     *
     * @param audience the CB event audience to set, <b>null</b> if none.
     *
     */
	@Override
    public void setAudience(String audience) {
		this.audience = audience;
	}

	/**
     * Returns the CB event keyword.
     * <p>
     *
     * @return the CB event keyword, <b>null</b> if none.
     *
     */
	@Override
    public String getKeyword() {
		return keyword;
	}

    /**
     * Sets the CB event keyword.
     * <p>
     *
     * @param keyword the CB event keyword to set, <b>null</b> if none.
     *
     */
	@Override
    public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
     * Returns the CB event resource.
     * <p>
     *
     * @return the CB event resource, <b>null</b> if none.
     *
     */
	@Override
    public CBResource getResource() {
		return resource;
	}

    /**
     * Sets the CB event resource.
     * <p>
     *
     * @param resource the CB event resource to set, <b>null</b> if none.
     *
     */
	@Override
    public void setResource(CBResource resource) {
		this.resource = resource;
	}

	/**
     * Returns the CB event person.
     * <p>
     *
     * @return the CB event person, <b>null</b> if none.
     *
     */
	@Override
    public CBPerson getPerson() {
		return person;
	}

    /**
     * Sets the CB event person.
     * <p>
     *
     * @param person the CB event person to set, <b>null</b> if none.
     *
     */
	@Override
    public void setPerson(CBPerson person) {
		this.person = person;
	}

	/**
     * Returns the CB event venue.
     * <p>
     *
     * @return the CB event venue, <b>null</b> if none.
     *
     */
	@Override
    public String getVenue() {
		return venue;
	}

    /**
     * Sets the CB event venue.
     * <p>
     *
     * @param venue the CB event venue to set, <b>null</b> if none.
     *
     */
	@Override
    public void setVenue(String venue) {
		this.venue = venue;
	}

	/**
     * Returns the CB event locationAsWritten.
     * <p>
     *
     * @return the CB event locationAsWritten, <b>null</b> if none.
     *
     */
	@Override
    public String getLocationAsWritten() {
		return locationAsWritten;
	}

    /**
     * Sets the CB event locationAsWritten.
     * <p>
     *
     * @param locationAsWritten the CB event locationAsWritten to set, <b>null</b> if none.
     *
     */
	@Override
    public void setLocationAsWritten(String locationAsWritten) {
		this.locationAsWritten = locationAsWritten;
	}

	/**
     * Returns the CB event locationCountry.
     * <p>
     *
     * @return the CB event locationCountry, <b>null</b> if none.
     *
     */
	@Override
    public String getLocationCountry() {
		return locationCountry;
	}

    /**
     * Sets the CB event locationCountry.
     * <p>
     *
     * @param locationCountry the CB event locationCountry to set, <b>null</b> if none.
     *
     */
	@Override
    public void setLocationCountry(String locationCountry) {
		this.locationCountry = locationCountry;
	}

	/**
     * Returns the CB event locationState.
     * <p>
     *
     * @return the CB event locationState, <b>null</b> if none.
     *
     */
	@Override
    public String getLocationState() {
		return locationState;
	}

    /**
     * Sets the CB event locationState.
     * <p>
     *
     * @param locationState the CB event locationState to set, <b>null</b> if none.
     *
     */
	@Override
    public void setLocationState(String locationState) {
		this.locationState = locationState;
	}

	/**
     * Returns the CB event locationCity.
     * <p>
     *
     * @return the CB event locationCity, <b>null</b> if none.
     *
     */
	@Override
    public String getLocationCity() {
		return locationCity;
	}

    /**
     * Sets the CB event locationCity.
     * <p>
     *
     * @param locationCity the CB event locationCity to set, <b>null</b> if none.
     *
     */
	@Override
    public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	/**
     * Returns the CB event eventDateEnd.
     * <p>
     *
     * @return the CB event eventDateEnd, <b>null</b> if none.
     *
     */
	@Override
    public String getEventDateEnd() {
		return eventDateEnd;
	}

    /**
     * Sets the CB event eventDateEnd.
     * <p>
     *
     * @param eventDateEnd the CB event eventDateEnd to set, <b>null</b> if none.
     *
     */
	@Override
    public void setEventDateEnd(String eventDateEnd) {
		this.eventDateEnd = eventDateEnd;
	}
}
