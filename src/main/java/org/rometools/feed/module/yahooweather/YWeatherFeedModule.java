/*
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
package org.rometools.feed.module.yahooweather;

import org.rometools.feed.module.yahooweather.types.Astronomy;
import org.rometools.feed.module.yahooweather.types.Atmosphere;
import org.rometools.feed.module.yahooweather.types.Location;
import org.rometools.feed.module.yahooweather.types.Units;
import org.rometools.feed.module.yahooweather.types.Wind;


/**
 * An interface describing feed/channel level data for Yahoo Weather.
 * @version $Id: YWeatherFeedModule.java,v 1.3 2008/03/29 16:19:12 kebernet Exp $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public interface YWeatherFeedModule extends YWeatherModule {
    /**
     * The location the feed is for.
     * @return The location the feed is for.
     */
    public Location getLocation();

    /**
     * The location the feed is for.
     * @param location The location the feed is for.
     */
    public void setLocation(Location location);

    /**
     * Astronomical information for the location.
     * @return Astronomical information for the location.
     */
    public Astronomy getAstronomy();

    /**
     * Astronomical information for the location.
     * @param astronomy Astronomical information for the location.
     */
    public void setAstronomy(Astronomy astronomy);

    /**
     * Units that data in the feed is provided in.
     * @return Units that data in the feed is provided in.
     */
    public Units getUnits();

    /**
     * Units that data in the feed is provided in.
     * @param units Units that data in the feed is provided in.
     */
    public void setUnits(Units units);

    /**
     * Current wind conditions at the location.
     * @return Current wind conditions at the location.
     */
    public Wind getWind();

    /**
     * Current wind conditions at the location.
     * @param wind Current wind conditions at the location.
     */
    public void setWind(Wind wind);

    /**
     * The current atmospheric conditions.
     * @return Atmosphere object.
     */
    public Atmosphere getAtmosphere();

    /**
     * Sets the current atmopheric condictions
     * @param value Atmosphere object.
     */
    public void setAtmosphere(Atmosphere value);
}
