/*
 *
 *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2008  Robert Cooper, Temple of the Screaming Penguin
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rometools.feed.module.yahooweather.types;

import java.io.Serializable;

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;


/**
 * The location of this forecast. Attributes:
 *      <ul class="topspace">
 *        <li>city: city name (string)</li>
 *        <li>region: state, territory, or region, if given (string)</li>
 *        <li>country: two-character country code. (string)</li></ul>
 * @version $Id: Location.java,v 1.2 2008/01/22 14:50:05 kebernet Exp $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class Location implements Serializable, Cloneable {
    private transient ToStringBean toString = new ToStringBean(Location.class,
            this);
    private transient EqualsBean equals = new EqualsBean(Location.class, this);
    private String city;
    private String region;
    private String country;

    /**
     * Simple constructor
     */
    public Location() {
        super();
    }

    /**
     * Constructs a new Location object.
     * @param city city name
     * @param region state, territory, or region, if given
     * @param country two-character country code.
     */
    public Location(String city, String region, String country) {
        super();
        this.city = city;
        this.region = region;
        this.country = country;
    }

    /**
     * City name
     * @return city name.
     */
    public String getCity() {
        return city;
    }

    /**
     * City name
     * @param city city name
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * state, territory, or region, if given
     * @return state, territory, or region, if given
     */
    public String getRegion() {
        return region;
    }

    /**
     * state, territory, or region, if given
     * @param region state, territory, or region, if given
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * country two-character country code.
     * @return country two-character country code.
     */
    public String getCountry() {
        return country;
    }

    /**
     * country two-character country code.
     * @param country country two-character country code.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public boolean equals(Object o) {
        return this.equals.equals(o);
    }

    public int hashCode() {
        return this.equals.hashCode();
    }

    public String toString() {
        return this.toString.toString();
    }

    public Object clone() {
        return new Location(this.city, this.region, this.country);
    }
}
