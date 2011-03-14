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
 * Forecast information about current atmospheric pressure, humidity,
 *       and visibility. Attributes:
 *       <ul class="topspace">
 *         <li>humidity: humidity, in percent (integer)</li>
 *         <li>visibility, in the units specified by the distance attribute of
 *           the yweather:units element (mi or km). Note that the visibility is
 *           specified as the actual value * 100. For example, a visibility of
 *           16.5 miles will be specified as 1650. A visibility of 14 kilometers
 *           will appear as 1400. (integer) [<em>A double here, and adjusted accordingly</em>]</li>
 *         <li>pressure: barometric pressure, in the units specified by the pressure
 *           attribute of the yweather:units element (in or mb). (float).</li>
 *         <li>rising: state of the barometric pressure: steady (0), rising (1),
 *           or falling (2). (integer: 0, 1, 2)</li>
 *       </ul>
 * @version $Id: Atmosphere.java,v 1.2 2008/01/22 14:50:05 kebernet Exp $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class Atmosphere implements Serializable, Cloneable {
    private EqualsBean equals = new EqualsBean(Atmosphere.class, this);
    private ToStringBean toString = new ToStringBean(Atmosphere.class, this);
    private int humidity;
    private double visibility;
    private double pressure;
    private PressureChange change;

    /**
     * Simple constructor.
     */
    public Atmosphere() {
        super();
    }

    /**
     * Constructs a new Atmosphere object
     * @param humidity humidity, in percent
     * @param visibility visibility distance (value beyond 1/100ths of a unit will be truncated)
     * @param pressure barometric pressure
     * @param change state of the barometric pressure
     */
    public Atmosphere(int humidity, double visibility, double pressure,
        PressureChange change) {
        super();
        this.humidity = humidity;
        this.visibility = visibility;
        this.pressure = pressure;
        this.change = change;
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

    /**
     * Relative humidity
     * @return humidity, in percent
     */
    public int getHumidity() {
        return humidity;
    }

    /**
     * Relative humidity
     * @param humidity humidity, in percent
     */
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    /**
     * Visibility distance
     * @return distance
     */
    public double getVisibility() {
        return visibility;
    }

    /**
     * Visibility distance
     * @param visibility distance (value beyond 1/100ths of a unit will be truncated)
     */
    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    /**
     * Barometric pressure
     * @return pressure
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * Barometric pressure
     *
     * @param pressure pressure
     */
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    /**
     * Change in pressure
     * @return Atmosphere.PressureChange object
     */
    public PressureChange getChange() {
        return change;
    }

    /**
     * Change in pressure
     * @param change PressureChange object
     */
    public void setChange(PressureChange change) {
        this.change = change;
    }

    public Object clone() {
        return new Atmosphere(this.humidity, this.visibility, this.pressure,
            this.change);
    }

    public static class PressureChange implements Serializable {
        public static final PressureChange RISING = new PressureChange(1,
                "rising");
        public static final PressureChange STEADY = new PressureChange(0,
                "steady");
        public static final PressureChange FALLING = new PressureChange(2,
                "falling");
        private int code;
        private String text;

        private PressureChange(int code, String text) {
            this.code = code;
            this.text = text;
        }

        public String toString() {
            return "[ code: " + this.code + " (" + this.text + ")]";
        }

        /**
         * The integer code for this chage state
         * @return int code
         */
        public int getCode() {
            return this.code;
        }

        /**
         * Gets a PressureChange instance for this int code.
         * @param code int code value
         * @return PressureChange instance
         * @throws RuntimeException if no 0, 1, or 2.
         */
        public static PressureChange fromCode(int code) {
            switch(code) {
            case 0:
                return STEADY;

            case 1:
                return RISING;

            case 2:
                return FALLING;

            default:
                throw new RuntimeException("Invalid pressure change code.");
            }
        }
    }
}
