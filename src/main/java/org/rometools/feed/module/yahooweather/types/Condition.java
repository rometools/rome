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
 * The current weather conditions. Attributes:
 *       <ul class="topspace">
 *         <li>text: a textual description of conditions, for example, "Partly
 *           Cloudy" (string)</li>
 *         <li>code: the condition code for this forecast. You could use this
 *           code to choose a text description or image for the forecast. The
 *           possible values for this element are described in <a href="#codes">Condition
 *           Codes</a> (integer)</li>
 *         <li>temp: the current temperature, in the units specified by the yweather:units
 *           element (integer)</li>
 *         <li>date: the current date and time for which this forecast applies.
 *           [<em>I believe this should be the time this condition information was captured</em>]
 *           The date is in <a href="http://www.rfc-editor.org/rfc/rfc822.txt">RFC822
 *           Section 5</a> format, for example "Wed, 30 Nov 2005 1:56 pm
 *           PST" (string)</li>
 *       </ul>
 * @version $Id: Condition.java,v 1.2 2008/01/22 14:50:05 kebernet Exp $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class Condition implements Serializable, Cloneable {
    private EqualsBean equals = new EqualsBean(Condition.class, this);
    private ToStringBean toString = new ToStringBean(Condition.class, this);
    private String text;
    private ConditionCode code;
    private int temperature;
    private Date date;

    /**
     * Simple constructor
     */
    public Condition() {
        super();
    }

    /**
     * Constructs a new Condition.
     * @param text a textual description of conditions, for example, "Partly
     *             Cloudy"
     * @param code the condition code for this forecast.
     * @param temperature the current temperature
     * @param date the current date and time
     */
    public Condition(String text, ConditionCode code, int temperature, Date date) {
        super();
        this.text = text;
        this.code = code;
        this.temperature = temperature;
        this.date = date;
    }

    /**
     * Description of condition
     * @return a textual description of conditions, for example, "Partly
     *             Cloudy"
     */
    public String getText() {
        return text;
    }

    /**
     * Description of condition
     * @param text a textual description of conditions, for example, "Partly
     *             Cloudy"
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Condition code
     * @return condition code
     */
    public ConditionCode getCode() {
        return code;
    }

    /**
     * Condition code
     * @param code Condition code
     */
    public void setCode(ConditionCode code) {
        this.code = code;
    }

    /**
     * Current Temperature
     * @return the current temperature
     * @see Units
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Current Temperature
     * @param temperature the current temperature
     * @see Units
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    /**
     * Date recorded
     * @return the current date and time
     */
    public Date getDate() {
        return date;
    }

    /**
     * Date recorded
     * @param date the current date and time
     */
    public void setDate(Date date) {
        this.date = date;
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
        return new Condition(this.text, this.code, this.temperature,
            (this.date != null) ? new Date(this.date.getTime()) : null);
    }
}
