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
 * The current weather conditions. Attributes:
 * <ul class="topspace">
 * <li>text: a textual description of conditions, for example, "Partly Cloudy" (string)</li>
 * <li>code: the condition code for this forecast. You could use this code to choose a text
 * description or image for the forecast. The possible values for this element are described in <a
 * href="#codes">Condition Codes</a> (integer)</li>
 * <li>temp: the current temperature, in the units specified by the yweather:units element (integer)
 * </li>
 * <li>date: the current date and time for which this forecast applies. [
 * <em>I believe this should be the time this condition information was captured</em>] The date is
 * in <a href="http://www.rfc-editor.org/rfc/rfc822.txt">RFC822 Section 5</a> format, for example
 * "Wed, 30 Nov 2005 1:56 pm PST" (string)</li>
 * </ul>
 */
public class Condition implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private final EqualsBean equals = new EqualsBean(Condition.class, this);
    private final ToStringBean toString = new ToStringBean(Condition.class, this);
    private String text;
    private ConditionCode code;
    private int temperature;
    private Date date;

    public Condition() {
        super();
    }

    /**
     * @param text a textual description of conditions, for example, "Partly Cloudy"
     * @param code the condition code for this forecast.
     * @param temperature the current temperature
     * @param date the current date and time
     */
    public Condition(final String text, final ConditionCode code, final int temperature, final Date date) {
        super();
        this.text = text;
        this.code = code;
        this.temperature = temperature;
        this.date = date;
    }

    /**
     * Description of condition
     *
     * @return a textual description of conditions, for example, "Partly Cloudy"
     */
    public String getText() {
        return text;
    }

    /**
     * Description of condition
     *
     * @param text a textual description of conditions, for example, "Partly Cloudy"
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * Condition code
     *
     * @return condition code
     */
    public ConditionCode getCode() {
        return code;
    }

    /**
     * Condition code
     *
     * @param code Condition code
     */
    public void setCode(final ConditionCode code) {
        this.code = code;
    }

    /**
     * Current Temperature
     *
     * @return the current temperature
     * @see Units
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Current Temperature
     *
     * @param temperature the current temperature
     * @see Units
     */
    public void setTemperature(final int temperature) {
        this.temperature = temperature;
    }

    /**
     * Date recorded
     *
     * @return the current date and time
     */
    public Date getDate() {
        return date;
    }

    /**
     * Date recorded
     *
     * @param date the current date and time
     */
    public void setDate(final Date date) {
        this.date = date;
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
        return new Condition(text, code, temperature, date != null ? new Date(date.getTime()) : null);
    }
}
