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

import java.util.Date;

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;


/**
 * The weather forecast for a specific day. The item element contains
 *       multiple forecast elements for today and tomorrow. Attributes:
 *       <ul class="topspace">
 *         <li>day: day of the week to which this forecast applies. Possible values
 *           are Mon Tue Wed Thu Fri Sat Sun (string)</li>
 *         <li>date: the date to which this forecast applies. The date is in "dd
 *           Mmm yyyy" format, for example "30 Nov 2005" (string)</li>
 *         <li>low: the forecasted low temperature for this day, in the units
 *           specified by the yweather:units element (integer)</li>
 *         <li>high: the forecasted high temperature for this day, in the units
 *           specified by the yweather:units element (integer)</li>
 *         <li>text: a textual description of conditions, for example, "Partly
 *           Cloudy" (string)</li>
 *
 *         <li>code: the condition code for this forecast. You could use this
 *           code to choose a text description or image for the forecast. The
 *           possible values for this element are described in Condition
 *           Codes (integer)</li>
 *       </ul>
 * @see ConditionCode
 * @version $Id: Forecast.java,v 1.2 2008/01/22 14:50:05 kebernet Exp $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class Forecast implements Serializable, Cloneable {
    private transient ToStringBean toString = new ToStringBean(Forecast.class,
            this);
    private transient EqualsBean equals = new EqualsBean(Forecast.class, this);
    private String day;
    private Date date;
    private int low;
    private int high;
    private String text;
    private ConditionCode code;

    /**
     * Simple constructor.
     */
    public Forecast() {
        super();
    }

    /**
     * Constructs a new Forecast object.
     * @param day  day of the week to which this forecast applies. Possible values
     *             are Mon Tue Wed Thu Fri Sat Sun (string)
     * @param date the date to which this forecast applies
     * @param low  the forecasted low temperature for this day
     * @param high the forecasted high temperature for this day
     * @param text a textual description of conditions, for example, "Partly
     *              Cloudy"
     * @param code ConditionCode instance for this forcast.
     */
    public Forecast(String day, Date date, int low, int high, String text,
        ConditionCode code) {
        super();
        this.day = day;
        this.date = date;
        this.low = low;
        this.high = high;
        this.text = text;
        this.code = code;
    }

    /**
     *  Day of week
     * @return day of the week to which this forecast applies. Possible values
     *             are Mon Tue Wed Thu Fri Sat Sun (string)
     */
    public String getDay() {
        return day;
    }

    /**
     *  Day of week
     * @param day day of the week to which this forecast applies. Possible values
     *             are Mon Tue Wed Thu Fri Sat Sun (string)
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * For date.
     * @return the date to which this forecast applies
     */
    public Date getDate() {
        return date;
    }

    /**
     * For date.
     * @param date the date to which this forecast applies
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Low temperature.
     * @return the forecasted low temperature for this day
     * @see Units
     */
    public int getLow() {
        return low;
    }

    /**
     * Low temperature
     * @param low the forecasted low temperature for this day
     * @see Units
     */
    public void setLow(int low) {
        this.low = low;
    }

    /**
     * High temperature
     * @return the forecasted high temperature for this day
     * @see Units
     */
    public int getHigh() {
        return high;
    }

    /**
     * High temperature
     * @param high the forecasted high temperature for this day
     * @see Units
     */
    public void setHigh(int high) {
        this.high = high;
    }

    /**
     * Text summary
     * @return a textual description of conditions, for example, "Partly
     *              Cloudy"
     */
    public String getText() {
        return text;
    }

    /**
     * Text summary
     * @param text a textual description of conditions, for example, "Partly
     *              Cloudy"
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Condition code
     * @return the condition code for this forecast
     */
    public ConditionCode getCode() {
        return code;
    }

    /**
     * Condition code
     * @param code the condition code for this forecast
     */
    public void setCode(ConditionCode code) {
        this.code = code;
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
        return new Forecast(this.day,
            (this.date != null) ? new Date(this.date.getTime()) : null,
            this.low, this.high, this.text, this.code);
    }
}
