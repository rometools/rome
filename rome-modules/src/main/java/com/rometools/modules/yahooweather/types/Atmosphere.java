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
 * Forecast information about current atmospheric pressure, humidity, and visibility. Attributes:
 * <ul class="topspace">
 * <li>humidity: humidity, in percent (integer)</li>
 * <li>visibility, in the units specified by the distance attribute of the yweather:units element
 * (mi or km). Note that the visibility is specified as the actual value * 100. For example, a
 * visibility of 16.5 miles will be specified as 1650. A visibility of 14 kilometers will appear as
 * 1400. (integer) [ <em>A double here, and adjusted accordingly</em>]</li>
 * <li>pressure: barometric pressure, in the units specified by the pressure attribute of the
 * yweather:units element (in or mb). (float).</li>
 * <li>rising: state of the barometric pressure: steady (0), rising (1), or falling (2). (integer:
 * 0, 1, 2)</li>
 * </ul>
 */
public class Atmosphere implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private final EqualsBean equals = new EqualsBean(Atmosphere.class, this);
    private final ToStringBean toString = new ToStringBean(Atmosphere.class, this);
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
     * @param humidity humidity, in percent
     * @param visibility visibility distance (value beyond 1/100ths of a unit will be truncated)
     * @param pressure barometric pressure
     * @param change state of the barometric pressure
     */
    public Atmosphere(final int humidity, final double visibility, final double pressure, final PressureChange change) {
        super();
        this.humidity = humidity;
        this.visibility = visibility;
        this.pressure = pressure;
        this.change = change;
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

    /**
     * Relative humidity
     *
     * @return humidity, in percent
     */
    public int getHumidity() {
        return humidity;
    }

    /**
     * Relative humidity
     *
     * @param humidity humidity, in percent
     */
    public void setHumidity(final int humidity) {
        this.humidity = humidity;
    }

    /**
     * Visibility distance
     *
     * @return distance
     */
    public double getVisibility() {
        return visibility;
    }

    /**
     * Visibility distance
     *
     * @param visibility distance (value beyond 1/100ths of a unit will be truncated)
     */
    public void setVisibility(final double visibility) {
        this.visibility = visibility;
    }

    /**
     * Barometric pressure
     *
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
    public void setPressure(final double pressure) {
        this.pressure = pressure;
    }

    /**
     * Change in pressure
     *
     * @return Atmosphere.PressureChange object
     */
    public PressureChange getChange() {
        return change;
    }

    /**
     * Change in pressure
     *
     * @param change PressureChange object
     */
    public void setChange(final PressureChange change) {
        this.change = change;
    }

    @Override
    public Object clone() {
        return new Atmosphere(humidity, visibility, pressure, change);
    }

    public static class PressureChange implements Serializable {

        private static final long serialVersionUID = 1L;
        public static final PressureChange RISING = new PressureChange(1, "rising");
        public static final PressureChange STEADY = new PressureChange(0, "steady");
        public static final PressureChange FALLING = new PressureChange(2, "falling");
        private final int code;
        private final String text;

        private PressureChange(final int code, final String text) {
            this.code = code;
            this.text = text;
        }

        @Override
        public String toString() {
            return "[ code: " + code + " (" + text + ")]";
        }

        /**
         * The integer code for this chage state
         *
         * @return int code
         */
        public int getCode() {
            return code;
        }

        /**
         * Gets a PressureChange instance for this int code.
         *
         * @param code int code value
         * @return PressureChange instance
         * @throws RuntimeException if no 0, 1, or 2.
         */
        public static PressureChange fromCode(final int code) {
            switch (code) {
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
