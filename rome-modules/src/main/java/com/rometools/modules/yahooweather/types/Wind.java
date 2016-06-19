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
 * Units for various aspects of the forecast. Attributes:
 * <ul class="topspace">
 * <li>temperature: degree units, f for Fahrenheit or c for Celsius (character)</li>
 * <li>distance: units for distance, mi for miles or km for kilometers (string)</li>
 * <li>pressure: units of barometric pressure, in for pounds per square inch or mb for millibars
 * (string)</li>
 * <li>speed: units of speed, mph for miles per hour or kph for kilometers per hour (string)</li>
 * </ul>
 * Note that the default RSS feed uses Fahrenheit degree units and English units for all other
 * attributes (miles, pounds per square inch, miles per hour). If Celsius has been specified as the
 * degree units for the feed (using the u request parameter), all the units are in metric format
 * (Celsius, kilometers, millibars, kilometers per hour).
 */
public class Wind implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private transient ToStringBean toString = new ToStringBean(Wind.class, this);
    private transient EqualsBean equals = new EqualsBean(Wind.class, this);
    private int chill;
    private int direction;
    private int speed;

    public Wind() {
        super();
    }

    /**
     * @param chill wind chill adjusted temperature
     * @param direction direction of wind in degrees
     * @param speed speed of wind
     */
    public Wind(final int chill, final int direction, final int speed) {
        super();
        this.chill = chill;
        this.direction = direction;
        this.speed = speed;
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
     * Wind chill adjusted temperature.
     *
     * @return int temperature value
     * @see Units
     */
    public int getChill() {
        return chill;
    }

    /**
     * Wind chill adjusted temperature.
     *
     * @param chill int temperature value
     * @see Units
     */
    public void setChill(final int chill) {
        this.chill = chill;
    }

    /**
     * Direction of wind in degrees
     *
     * @return int direction of wind.
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Direction of wind in degrees
     *
     * @param direction int direction of wind.
     */
    public void setDirection(final int direction) {
        this.direction = direction;
    }

    /**
     * Speed of wind
     *
     * @return int speed of wind
     * @see Units
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Speed of wind
     *
     * @param speed int speed of wind
     * @see Units
     */
    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    @Override
    public Object clone() {
        return new Wind(chill, direction, speed);
    }
}
