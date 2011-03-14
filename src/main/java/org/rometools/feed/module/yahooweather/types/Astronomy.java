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
 * Forecast information about current astronomical conditions. Attributes:
 *       <ul class="topspace">
 *         <li>sunrise: today's sunrise time. The time is a string in a local
 *           time format of "h:mm am/pm", for example "7:02 am" (string)</li>
 *         <li>sunset today's sunset time. The time is a string in a local time
 *           format of "h:mm am/pm", for example "4:51 pm" (string)</li>
 *       </ul>
 * @version $Id: Astronomy.java,v 1.2 2008/01/22 14:50:05 kebernet Exp $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class Astronomy implements Serializable, Cloneable {
    private EqualsBean equals = new EqualsBean(Astronomy.class, this);
    private ToStringBean toString = new ToStringBean(Astronomy.class, this);
    private Date sunrise;
    private Date sunset;

    /**
     * Simple constructor.
     */
    public Astronomy() {
        super();
    }

    /**
     * Constructs a new Astronomy object
     * @param sunrise time of sunrise (from 0ms)
     * @param sunset time of sunset (from 0ms)
     */
    public Astronomy(Date sunrise, Date sunset) {
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public Object clone() {
        return new Astronomy((this.getSunrise() != null)
            ? new Date(this.getSunrise().getTime()) : null,
            (this.getSunset() != null) ? new Date(this.getSunset().getTime())
                                       : null);
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
     * Time of sunrise
     * @return ime of sunrise (from 0ms)
     */
    public Date getSunrise() {
        return sunrise;
    }

    /**
     * Time of sunrise
     * @param sunrise ime of sunrise (from 0ms)
     */
    public void setSunrise(Date sunrise) {
        this.sunrise = sunrise;
    }

    /**
     * Time of sunset
     * @return time of sunset (from 0ms)
     */
    public Date getSunset() {
        return sunset;
    }

    /**
     * Time of sunset
     * @param sunset time of sunset (from 0ms)
     */
    public void setSunset(Date sunset) {
        this.sunset = sunset;
    }
}
