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
package com.rometools.modules.yahooweather;

import com.rometools.modules.yahooweather.types.Astronomy;
import com.rometools.modules.yahooweather.types.Atmosphere;
import com.rometools.modules.yahooweather.types.Location;
import com.rometools.modules.yahooweather.types.Units;
import com.rometools.modules.yahooweather.types.Wind;

/**
 * An interface describing feed/channel level data for Yahoo Weather.
 */
public interface YWeatherFeedModule extends YWeatherModule {
    /**
     * The location the feed is for.
     *
     * @return The location the feed is for.
     */
    Location getLocation();

    /**
     * The location the feed is for.
     *
     * @param location The location the feed is for.
     */
    void setLocation(Location location);

    /**
     * Astronomical information for the location.
     *
     * @return Astronomical information for the location.
     */
    Astronomy getAstronomy();

    /**
     * Astronomical information for the location.
     *
     * @param astronomy Astronomical information for the location.
     */
    void setAstronomy(Astronomy astronomy);

    /**
     * Units that data in the feed is provided in.
     *
     * @return Units that data in the feed is provided in.
     */
    Units getUnits();

    /**
     * Units that data in the feed is provided in.
     *
     * @param units Units that data in the feed is provided in.
     */
    void setUnits(Units units);

    /**
     * Current wind conditions at the location.
     *
     * @return Current wind conditions at the location.
     */
    Wind getWind();

    /**
     * Current wind conditions at the location.
     *
     * @param wind Current wind conditions at the location.
     */
    void setWind(Wind wind);

    /**
     * The current atmospheric conditions.
     *
     * @return Atmosphere object.
     */
    Atmosphere getAtmosphere();

    /**
     * Sets the current atmopheric condictions.
     *
     * @param value Atmosphere object.
     */
    void setAtmosphere(Atmosphere value);
}
