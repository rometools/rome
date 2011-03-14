/*
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
package org.rometools.feed.module.yahooweather;

import org.rometools.feed.module.yahooweather.types.Condition;
import org.rometools.feed.module.yahooweather.types.Forecast;


/**
 * An interface describing the entry/item level data for Yahoo Weather.
 * @version $Id: YWeatherEntryModule.java,v 1.2 2008/01/22 14:50:06 kebernet Exp $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public interface YWeatherEntryModule extends YWeatherModule {
    /**
     * The current conditions.
     * @return The current conditions.
     */
    public Condition getCondition();

    /**
     * The current conditions.
     * @param condition The current conditions.
     */
    public void setCondition(Condition condition);

    /**
     * Forecasts for this location.
     * @return Forecasts for this location.
     */
    public Forecast[] getForecasts();

    /**
     * Forecasts for this location.
     * @param forecasts Forecasts for this location.
     */
    public void setForecasts(Forecast[] forecasts);
}
