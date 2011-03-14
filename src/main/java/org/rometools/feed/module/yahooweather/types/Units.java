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
 * Units for various aspects of the forecast. Attributes:
 *      <ul class="topspace">
 *        <li>temperature: degree units, f for Fahrenheit or c for Celsius (character)</li>
 *        <li>distance: units for distance, mi for miles or km for kilometers
 *          (string)</li>
 *         <li>pressure: units of barometric pressure, in for pounds per square
 *           inch or mb for millibars (string) [<em>I am pretty sure this is wrong.
 *           I believe this number of be inches of Mercury, not PSI.</em>]</li>
 *         <li>speed: units of speed, mph for miles per hour or kph for kilometers
 *           per hour (string)</li>
 *       </ul>
 *      Note that the default RSS feed uses Fahrenheit degree units and English
 *      units for all other attributes (miles, pounds per square inch, miles
 *      per hour). If Celsius has been specified as the degree units for the
 *      feed (using the u request parameter), all the units are in metric format
 *      (Celsius, kilometers, millibars, kilometers per hour).
 * @version $Id: Units.java,v 1.2 2008/01/22 14:50:05 kebernet Exp $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class Units implements Serializable, Cloneable {
    private transient ToStringBean toString = new ToStringBean(Units.class, this);
    private transient EqualsBean equals = new EqualsBean(Units.class, this);
    private String temperature;
    private String distance;
    private String pressure;
    private String speed;

    /**
     * Simple constructor.
     */
    public Units() {
        super();
    }

    /**
     * Constructs a new Units object
     * @param temperature units of temp ("f" or "c");
     * @param distance units of distance ("mi" or "km")
     * @param pressure units of pressure ("in" or "mb")
     * @param speed units of speed ("mph" or "kph")
     */
    public Units(String temperature, String distance, String pressure,
        String speed) {
        super();
        this.temperature = temperature;
        this.distance = distance;
        this.pressure = pressure;
        this.speed = speed;
    }

    /**
     * Units of Temperature.
     * @return temperature units of temp ("f" or "c");
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Units of Temperature.
     * @param temperature units of temp ("f" or "c");
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    /** Units of distance
     *
     * @return distance units of distance ("mi" or "km")
     */
    public String getDistance() {
        return distance;
    }

    /**
     * Units of distance
     * @param distance units of distance ("mi" or "km")
     */
    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * Units of pressure
     * @return units of pressure ("in" or "mb")
     */
    public String getPressure() {
        return pressure;
    }

    /**
     * Units of pressure
     * @param pressure units of pressure ("in" or "mb")
     */
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    /**
     * Units of speed
     * @return units of speed ("mph" or "kph")
     */
    public String getSpeed() {
        return speed;
    }

    /**
     * Units of speed
     * @param speed units of speed ("mph" or "kph")
     */
    public void setSpeed(String speed) {
        this.speed = speed;
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
        return new Units(this.temperature, this.distance, this.pressure,
            this.speed);
    }
}
