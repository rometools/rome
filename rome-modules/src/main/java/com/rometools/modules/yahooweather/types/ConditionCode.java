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
import java.util.HashMap;
import java.util.Map;

import com.rometools.rome.feed.impl.EqualsBean;

/**
 *
 * Condition codes are used in the yweather:forecast element to describe the current conditions.
 */
public class ConditionCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient static Map<Integer, ConditionCode> LOOKUP = new HashMap<Integer, ConditionCode>();
    public static final ConditionCode TORNADO = new ConditionCode(0, "tornado");
    public static final ConditionCode TROPICAL_STORM = new ConditionCode(1, "tropical storm");
    public static final ConditionCode HURRICANE = new ConditionCode(2, "hurricane");
    public static final ConditionCode SEVERE_THUNDERSTORMS = new ConditionCode(3, "severe thunderstorms");
    public static final ConditionCode THUNDERSTORMS = new ConditionCode(4, "thunderstorms");
    public static final ConditionCode MIXED_RAIN_AND_SNOW = new ConditionCode(5, "mixed rain and snow");
    public static final ConditionCode MIXED_RAIN_AND_SLEET = new ConditionCode(6, "mixed rain and sleet");
    public static final ConditionCode MIXED_SNOW_AND_SLEET = new ConditionCode(7, "mixed snow and sleet");
    public static final ConditionCode FREEZING_DRIZZLE = new ConditionCode(8, "freezing drizzle");
    public static final ConditionCode DRIZZLE = new ConditionCode(9, "drizzle");
    public static final ConditionCode FREEZING_RAIN = new ConditionCode(10, "freezing rain");
    public static final ConditionCode SHOWERS_LIGHT = new ConditionCode(11, "showers");
    public static final ConditionCode SHOWERS_HEAVY = new ConditionCode(12, "showers");
    public static final ConditionCode FLURRIES = new ConditionCode(13, "snow flurries");
    public static final ConditionCode LIGHT_SNOW_SHOWERS = new ConditionCode(14, "light snow showers");
    public static final ConditionCode BLOWING_SNOW = new ConditionCode(15, "blowing snow");
    public static final ConditionCode SNOW = new ConditionCode(16, "snow");
    public static final ConditionCode HAIL = new ConditionCode(17, "hail");
    public static final ConditionCode SLEET = new ConditionCode(18, "sleet");
    public static final ConditionCode DUST = new ConditionCode(19, "dust");
    public static final ConditionCode FOGGY = new ConditionCode(20, "foggy");
    public static final ConditionCode HAZE = new ConditionCode(21, "haze");
    public static final ConditionCode SMOKY = new ConditionCode(22, "smoky");
    public static final ConditionCode BLUSTERY = new ConditionCode(23, "blustery");
    public static final ConditionCode WINDY = new ConditionCode(24, "windy");
    public static final ConditionCode COLD = new ConditionCode(25, "cold");
    public static final ConditionCode CLOUDY = new ConditionCode(26, "cloudy");
    public static final ConditionCode MOSTLY_CLOUDY_NIGHT = new ConditionCode(27, "mostly cloudy (night)");
    public static final ConditionCode MOSTLY_CLOUDY_DAY = new ConditionCode(28, "mostly cloudy (day)");
    public static final ConditionCode PARTLY_CLOUDY_NIGHT = new ConditionCode(29, "partly cloudy (night)");
    public static final ConditionCode PARTLY_CLOUDY_DAY = new ConditionCode(30, "partly couldy (day)");
    public static final ConditionCode CLEAR_NIGHT = new ConditionCode(31, "clear (night)");
    public static final ConditionCode SUNNY = new ConditionCode(32, "sunny");
    public static final ConditionCode FAIR_NIGHT = new ConditionCode(33, "fair (night)");
    public static final ConditionCode FAIR_DAY = new ConditionCode(34, "fair (day)");
    public static final ConditionCode MIXED_RAIN_AND_HAIL = new ConditionCode(35, "mixed rain and hail");
    public static final ConditionCode HOT = new ConditionCode(36, "hot");
    public static final ConditionCode ISOLATED_THUNDERSTORMS = new ConditionCode(37, "isolated thunderstorms");
    public static final ConditionCode SCATTERED_THUNDERSTORMS_HEAVY = new ConditionCode(38, "scattered thunderstorms");
    public static final ConditionCode SCATTERED_THUNDERSTORMS_LIGHT = new ConditionCode(39, "scattered thunderstorms");
    public static final ConditionCode SCATTERED_SHOWERS = new ConditionCode(40, "scattered showers");
    public static final ConditionCode HEAVY_SNOW = new ConditionCode(41, "heavy snow");
    public static final ConditionCode SCATTERED_SNOW_SHOWERS = new ConditionCode(42, "scattered snow showers");
    public static final ConditionCode HEAVY_SNOW_WINDY = new ConditionCode(43, "heavy snow");
    public static final ConditionCode PARTLY_CLOUDY = new ConditionCode(44, "partly cloudy");
    public static final ConditionCode THUNDERSHOWERS = new ConditionCode(45, "thundershowers");
    public static final ConditionCode SHOW_SHOWERS = new ConditionCode(46, "snow showers");
    public static final ConditionCode ISLOATED_THUNDERSHOWERS = new ConditionCode(47, "isolated thundershowers");
    public static final ConditionCode NOT_AVAILABLE = new ConditionCode(3200, "not available");
    private final int code;
    private final String description;

    private ConditionCode(final int code, final String description) {
        this.code = code;
        this.description = description;

        final Object old = ConditionCode.LOOKUP.put(Integer.valueOf(code), this);

        if (old != null) {
            throw new RuntimeException("Duplicate condition code!");
        }
    }

    /**
     * The integer code for this condition
     *
     * @return int code value
     */
    public int getCode() {
        return code;
    }

    /**
     * Text description of condition (from the table at top of class)
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a condition code instance for a given integer
     *
     * @param code integer code to search for
     * @return a ConditionCode instance or null
     */
    public static ConditionCode fromCode(final int code) {
        return ConditionCode.LOOKUP.get(Integer.valueOf(code));
    }

    @Override
    public boolean equals(final Object o) {
        return EqualsBean.beanEquals(ConditionCode.class, this, o);
    }

    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }

    @Override
    public String toString() {
        return "[Condition code:" + code + " description:" + description + "]";
    }
}
