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

import java.util.HashMap;
import java.util.Map;

import com.sun.syndication.feed.impl.EqualsBean;


/**
 * <h3>Condition Codes<a name="codes"></a></h3>
 *
 *<p>Condition codes are used in the yweather:forecast element to describe the
 *current conditions.</p>
 *<table style="width: 80%;" id="codetable" border="0">
 *   <tbody><tr>
 *     <th style="width: 10%;">Code</th>
 *     <th>Description</th>
 *   </tr>
 *   <tr>
 *
 *     <td style="background-color: rgb(255, 255, 255);">0</td>
 *     <td style="background-color: rgb(255, 255, 255);">tornado</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">1</td>
 *     <td style="background-color: rgb(236, 245, 250);">tropical storm</td>
 *   </tr>
 *
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">2</td>
 *     <td style="background-color: rgb(255, 255, 255);">hurricane</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">3</td>
 *     <td style="background-color: rgb(236, 245, 250);">severe thunderstorms</td>
 *
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">4</td>
 *     <td style="background-color: rgb(255, 255, 255);">thunderstorms</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">5</td>
 *
 *     <td style="background-color: rgb(236, 245, 250);">mixed rain and snow</td>
 *  </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">6</td>
 *     <td style="background-color: rgb(255, 255, 255);">mixed rain and sleet</td>
 *   </tr>
 *   <tr>
 *
 *     <td style="background-color: rgb(236, 245, 250);">7</td>
 *     <td style="background-color: rgb(236, 245, 250);">mixed snow and sleet</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">8</td>
 *     <td style="background-color: rgb(255, 255, 255);">freezing drizzle</td>
 *   </tr>
 *
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">9</td>
 *     <td style="background-color: rgb(236, 245, 250);">drizzle</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">10</td>
 *     <td style="background-color: rgb(255, 255, 255);">freezing rain</td>
 *
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">11</td>
 *     <td style="background-color: rgb(236, 245, 250);">showers</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">12</td>
 *
 *     <td style="background-color: rgb(255, 255, 255);">showers</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">13</td>
 *     <td style="background-color: rgb(236, 245, 250);">snow flurries</td>
 *   </tr>
 *   <tr>
 *
 *     <td style="background-color: rgb(255, 255, 255);">14</td>
 *     <td style="background-color: rgb(255, 255, 255);">light snow showers</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">15</td>
 *     <td style="background-color: rgb(236, 245, 250);">blowing snow</td>
 *   </tr>
 *
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">16</td>
 *     <td style="background-color: rgb(255, 255, 255);">snow</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">17</td>
 *     <td style="background-color: rgb(236, 245, 250);">hail</td>
 *
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">18</td>
 *     <td style="background-color: rgb(255, 255, 255);">sleet</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">19</td>
 *
 *     <td style="background-color: rgb(236, 245, 250);">dust</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">20</td>
 *     <td style="background-color: rgb(255, 255, 255);">foggy</td>
 *   </tr>
 *   <tr>
 *
 *     <td style="background-color: rgb(236, 245, 250);">21</td>
 *     <td style="background-color: rgb(236, 245, 250);">haze</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">22</td>
 *     <td style="background-color: rgb(255, 255, 255);">smoky</td>
 *   </tr>
 *
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">23</td>
 *     <td style="background-color: rgb(236, 245, 250);">blustery</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">24</td>
 *     <td style="background-color: rgb(255, 255, 255);">windy</td>
 *
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">25</td>
 *     <td style="background-color: rgb(236, 245, 250);">cold</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">26</td>
 *
 *     <td style="background-color: rgb(255, 255, 255);">cloudy</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">27</td>
 *     <td style="background-color: rgb(236, 245, 250);">mostly cloudy (night)</td>
 *   </tr>
 *   <tr>
 *
 *     <td style="background-color: rgb(255, 255, 255);">28</td>
 *     <td style="background-color: rgb(255, 255, 255);">mostly cloudy (day)</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">29</td>
 *     <td style="background-color: rgb(236, 245, 250);">partly cloudy (night)</td>
 *   </tr>
 *
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">30</td>
 *     <td style="background-color: rgb(255, 255, 255);">partly cloudy (day)</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">31</td>
 *     <td style="background-color: rgb(236, 245, 250);">clear (night)</td>
 *
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">32</td>
 *     <td style="background-color: rgb(255, 255, 255);">sunny</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">33</td>
 *
 *     <td style="background-color: rgb(236, 245, 250);">fair (night)</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">34</td>
 *     <td style="background-color: rgb(255, 255, 255);">fair (day)</td>
 *   </tr>
 *   <tr>
 *
 *     <td style="background-color: rgb(236, 245, 250);">35</td>
 *     <td style="background-color: rgb(236, 245, 250);">mixed rain and hail</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">36</td>
 *     <td style="background-color: rgb(255, 255, 255);">hot</td>
 *   </tr>
 *
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">37</td>
 *     <td style="background-color: rgb(236, 245, 250);">isolated thunderstorms</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">38</td>
 *     <td style="background-color: rgb(255, 255, 255);">scattered thunderstorms</td>
 *
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">39</td>
 *     <td style="background-color: rgb(236, 245, 250);">scattered thunderstorms</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">40</td>
 *
 *     <td style="background-color: rgb(255, 255, 255);">scattered showers</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">41</td>
 *     <td style="background-color: rgb(236, 245, 250);">heavy snow</td>
 *   </tr>
 *   <tr>
 *
 *     <td style="background-color: rgb(255, 255, 255);">42</td>
 *     <td style="background-color: rgb(255, 255, 255);">scattered snow showers</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">43</td>
 *     <td style="background-color: rgb(236, 245, 250);">heavy snow</td>
 *   </tr>
 *
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">44</td>
 *     <td style="background-color: rgb(255, 255, 255);">partly cloudy</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">45</td>
 *     <td style="background-color: rgb(236, 245, 250);">thundershowers</td>
 *
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">46</td>
 *     <td style="background-color: rgb(255, 255, 255);">snow showers</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(236, 245, 250);">47</td>
 *
 *     <td style="background-color: rgb(236, 245, 250);">isolated thundershowers</td>
 *   </tr>
 *   <tr>
 *     <td style="background-color: rgb(255, 255, 255);">3200</td>
 *     <td style="background-color: rgb(255, 255, 255);">not available</td>
 *   </tr>
 * </tbody></table>
 * @version $Id: ConditionCode.java,v 1.2 2008/01/22 14:50:05 kebernet Exp $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class ConditionCode implements Serializable {
    private transient static Map LOOKUP = new HashMap(); /*<Integer, Condition>*/
    public static final ConditionCode TORNADO = new ConditionCode(0, "tornado");
    public static final ConditionCode TROPICAL_STORM = new ConditionCode(1,
            "tropical storm");
    public static final ConditionCode HURRICANE = new ConditionCode(2,
            "hurricane");
    public static final ConditionCode SEVERE_THUNDERSTORMS = new ConditionCode(3,
            "severe thunderstorms");
    public static final ConditionCode THUNDERSTORMS = new ConditionCode(4,
            "thunderstorms");
    public static final ConditionCode MIXED_RAIN_AND_SNOW = new ConditionCode(5,
            "mixed rain and snow");
    public static final ConditionCode MIXED_RAIN_AND_SLEET = new ConditionCode(6,
            "mixed rain and sleet");
    public static final ConditionCode MIXED_SNOW_AND_SLEET = new ConditionCode(7,
            "mixed snow and sleet");
    public static final ConditionCode FREEZING_DRIZZLE = new ConditionCode(8,
            "freezing drizzle");
    public static final ConditionCode DRIZZLE = new ConditionCode(9, "drizzle");
    public static final ConditionCode FREEZING_RAIN = new ConditionCode(10,
            "freezing rain");
    public static final ConditionCode SHOWERS_LIGHT = new ConditionCode(11,
            "showers");
    public static final ConditionCode SHOWERS_HEAVY = new ConditionCode(12,
            "showers");
    public static final ConditionCode FLURRIES = new ConditionCode(13,
            "snow flurries");
    public static final ConditionCode LIGHT_SNOW_SHOWERS = new ConditionCode(14,
            "light snow showers");
    public static final ConditionCode BLOWING_SNOW = new ConditionCode(15,
            "blowing snow");
    public static final ConditionCode SNOW = new ConditionCode(16, "snow");
    public static final ConditionCode HAIL = new ConditionCode(17, "hail");
    public static final ConditionCode SLEET = new ConditionCode(18, "sleet");
    public static final ConditionCode DUST = new ConditionCode(19, "dust");
    public static final ConditionCode FOGGY = new ConditionCode(20, "foggy");
    public static final ConditionCode HAZE = new ConditionCode(21, "haze");
    public static final ConditionCode SMOKY = new ConditionCode(22, "smoky");
    public static final ConditionCode BLUSTERY = new ConditionCode(23,
            "blustery");
    public static final ConditionCode WINDY = new ConditionCode(24, "windy");
    public static final ConditionCode COLD = new ConditionCode(25, "cold");
    public static final ConditionCode CLOUDY = new ConditionCode(26, "cloudy");
    public static final ConditionCode MOSTLY_CLOUDY_NIGHT = new ConditionCode(27,
            "mostly cloudy (night)");
    public static final ConditionCode MOSTLY_CLOUDY_DAY = new ConditionCode(28,
            "mostly cloudy (day)");
    public static final ConditionCode PARTLY_CLOUDY_NIGHT = new ConditionCode(29,
            "partly cloudy (night)");
    public static final ConditionCode PARTLY_CLOUDY_DAY = new ConditionCode(30,
            "partly couldy (day)");
    public static final ConditionCode CLEAR_NIGHT = new ConditionCode(31,
            "clear (night)");
    public static final ConditionCode SUNNY = new ConditionCode(32, "sunny");
    public static final ConditionCode FAIR_NIGHT = new ConditionCode(33,
            "fair (night)");
    public static final ConditionCode FAIR_DAY = new ConditionCode(34,
            "fair (day)");
    public static final ConditionCode MIXED_RAIN_AND_HAIL = new ConditionCode(35,
            "mixed rain and hail");
    public static final ConditionCode HOT = new ConditionCode(36, "hot");
    public static final ConditionCode ISOLATED_THUNDERSTORMS = new ConditionCode(37,
            "isolated thunderstorms");
    public static final ConditionCode SCATTERED_THUNDERSTORMS_HEAVY = new ConditionCode(38,
            "scattered thunderstorms");
    public static final ConditionCode SCATTERED_THUNDERSTORMS_LIGHT = new ConditionCode(39,
            "scattered thunderstorms");
    public static final ConditionCode SCATTERED_SHOWERS = new ConditionCode(40,
            "scattered showers");
    public static final ConditionCode HEAVY_SNOW = new ConditionCode(41,
            "heavy snow");
    public static final ConditionCode SCATTERED_SNOW_SHOWERS = new ConditionCode(42,
            "scattered snow showers");
    public static final ConditionCode HEAVY_SNOW_WINDY = new ConditionCode(43,
            "heavy snow");
    public static final ConditionCode PARTLY_CLOUDY = new ConditionCode(44,
            "partly cloudy");
    public static final ConditionCode THUNDERSHOWERS = new ConditionCode(45,
            "thundershowers");
    public static final ConditionCode SHOW_SHOWERS = new ConditionCode(46,
            "snow showers");
    public static final ConditionCode ISLOATED_THUNDERSHOWERS = new ConditionCode(47,
            "isolated thundershowers");
    public static final ConditionCode NOT_AVAILABLE = new ConditionCode(3200,
            "not available");
    private int code;
    private String description;
    private transient EqualsBean equals = new EqualsBean(ConditionCode.class,
            this);

    private ConditionCode(int code, String description) {
        this.code = code;
        this.description = description;

        Object old = ConditionCode.LOOKUP.put(new Integer(code), this);

        if(old != null) {
            throw new RuntimeException("Duplicate condition code!");
        }
    }

    /**
     * The integer code for this condition
     * @return int code value
     */
    public int getCode() {
        return code;
    }

    /**
     * Text description of condition (from the table at top of class)
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a condition code instance for a given integer
     * @param code integer code to search for
     * @return a ConditionCode instance or null
     */
    public static ConditionCode fromCode(int code) {
        return (ConditionCode) ConditionCode.LOOKUP.get(new Integer(code));
    }

    public boolean equals(Object o) {
        return equals.equals(o);
    }

    public int hashCode() {
        return equals.hashCode();
    }

    public String toString() {
        return "[Condition code:" + this.code + " description:" +
        this.description + "]";
    }
}
