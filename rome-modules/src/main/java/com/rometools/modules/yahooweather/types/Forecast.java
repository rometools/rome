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
import java.util.Date;

import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

/**
 * The weather forecast for a specific day. The item element contains multiple forecast elements for
 * today and tomorrow. Attributes:
 * <ul class="topspace">
 * <li>day: day of the week to which this forecast applies. Possible values are Mon Tue Wed Thu Fri
 * Sat Sun (string)</li>
 * <li>date: the date to which this forecast applies. The date is in "dd Mmm yyyy" format, for
 * example "30 Nov 2005" (string)</li>
 * <li>low: the forecasted low temperature for this day, in the units specified by the
 * yweather:units element (integer)</li>
 * <li>high: the forecasted high temperature for this day, in the units specified by the
 * yweather:units element (integer)</li>
 * <li>text: a textual description of conditions, for example, "Partly Cloudy" (string)</li>
 *
 * <li>code: the condition code for this forecast. You could use this code to choose a text
 * description or image for the forecast. The possible values for this element are described in
 * Condition Codes (integer)</li>
 * </ul>
 *
 * @see ConditionCode
 */
public class Forecast implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private transient ToStringBean toString = new ToStringBean(Forecast.class, this);
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
     * @param day day of the week to which this forecast applies. Possible values are Mon Tue Wed
     *            Thu Fri Sat Sun (string)
     * @param date the date to which this forecast applies
     * @param low the forecasted low temperature for this day
     * @param high the forecasted high temperature for this day
     * @param text a textual description of conditions, for example, "Partly Cloudy"
     * @param code ConditionCode instance for this forcast.
     */
    public Forecast(final String day, final Date date, final int low, final int high, final String text, final ConditionCode code) {
        super();
        this.day = day;
        this.date = date;
        this.low = low;
        this.high = high;
        this.text = text;
        this.code = code;
    }

    /**
     * Day of week
     *
     * @return day of the week to which this forecast applies. Possible values are Mon Tue Wed Thu
     *         Fri Sat Sun (string)
     */
    public String getDay() {
        return day;
    }

    /**
     * Day of week
     *
     * @param day day of the week to which this forecast applies. Possible values are Mon Tue Wed
     *            Thu Fri Sat Sun (string)
     */
    public void setDay(final String day) {
        this.day = day;
    }

    /**
     * For date.
     *
     * @return the date to which this forecast applies
     */
    public Date getDate() {
        return date;
    }

    /**
     * For date.
     *
     * @param date the date to which this forecast applies
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Low temperature.
     *
     * @return the forecasted low temperature for this day
     * @see Units
     */
    public int getLow() {
        return low;
    }

    /**
     * Low temperature
     *
     * @param low the forecasted low temperature for this day
     * @see Units
     */
    public void setLow(final int low) {
        this.low = low;
    }

    /**
     * High temperature
     *
     * @return the forecasted high temperature for this day
     * @see Units
     */
    public int getHigh() {
        return high;
    }

    /**
     * High temperature
     *
     * @param high the forecasted high temperature for this day
     * @see Units
     */
    public void setHigh(final int high) {
        this.high = high;
    }

    /**
     * Text summary
     *
     * @return a textual description of conditions, for example, "Partly Cloudy"
     */
    public String getText() {
        return text;
    }

    /**
     * Text summary
     *
     * @param text a textual description of conditions, for example, "Partly Cloudy"
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * Condition code
     *
     * @return the condition code for this forecast
     */
    public ConditionCode getCode() {
        return code;
    }

    /**
     * Condition code
     *
     * @param code the condition code for this forecast
     */
    public void setCode(final ConditionCode code) {
        this.code = code;
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
        return new Forecast(day, date != null ? new Date(date.getTime()) : null, low, high, text, code);
    }
}
