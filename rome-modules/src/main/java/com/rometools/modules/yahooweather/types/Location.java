/*
 * Copyright 2008 Robert Cooper, Temple of the Screaming Penguin
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
package com.rometools.modules.yahooweather.types;

import java.io.Serializable;

import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

/**
 * The location of this forecast. Attributes:
 * <ul class="topspace">
 * <li>city: city name (string)</li>
 * <li>region: state, territory, or region, if given (string)</li>
 * <li>country: two-character country code. (string)</li>
 * </ul>
 */
public class Location implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private transient ToStringBean toString = new ToStringBean(Location.class, this);
    private transient EqualsBean equals = new EqualsBean(Location.class, this);
    private String city;
    private String region;
    private String country;

    public Location() {
        super();
    }

    /**
     * @param city city name
     * @param region state, territory, or region, if given
     * @param country two-character country code.
     */
    public Location(final String city, final String region, final String country) {
        super();
        this.city = city;
        this.region = region;
        this.country = country;
    }

    /**
     * City name
     *
     * @return city name.
     */
    public String getCity() {
        return city;
    }

    /**
     * City name
     *
     * @param city city name
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * state, territory, or region, if given
     *
     * @return state, territory, or region, if given
     */
    public String getRegion() {
        return region;
    }

    /**
     * state, territory, or region, if given
     *
     * @param region state, territory, or region, if given
     */
    public void setRegion(final String region) {
        this.region = region;
    }

    /**
     * country two-character country code.
     *
     * @return country two-character country code.
     */
    public String getCountry() {
        return country;
    }

    /**
     * country two-character country code.
     *
     * @param country country two-character country code.
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    @Override
    public boolean equals(final Object o) {
        return equals.equals(o);
    }

    @Override
    public int hashCode() {
        return equals.hashCode();
    }

    @Override
    public String toString() {
        return toString.toString();
    }

    @Override
    public Object clone() {
        return new Location(city, region, country);
    }
}
