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

import com.sun.syndication.feed.CopyFrom;
import com.sun.syndication.feed.module.ModuleImpl;
import org.rometools.feed.module.yahooweather.types.Astronomy;
import org.rometools.feed.module.yahooweather.types.Atmosphere;
import org.rometools.feed.module.yahooweather.types.Condition;
import org.rometools.feed.module.yahooweather.types.Forecast;
import org.rometools.feed.module.yahooweather.types.Location;
import org.rometools.feed.module.yahooweather.types.Units;
import org.rometools.feed.module.yahooweather.types.Wind;


/**
 * A Module implementation for entry or feed level information.
 * @version $Id: YWeatherModuleImpl.java,v 1.2 2008/01/22 14:50:06 kebernet Exp $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class YWeatherModuleImpl extends ModuleImpl
    implements YWeatherEntryModule, YWeatherFeedModule {
    private Location location;
    private Astronomy astronomy;
    private Atmosphere atmosphere;
    private Units units;
    private Condition condition;
    private Wind wind;
    private Forecast[] forecasts;

    public YWeatherModuleImpl() {
        super(YWeatherModuleImpl.class, YWeatherModule.URI);
    }

    public Class getInterface() {
        return CopyFromInterface.class;
    }

    public void copyFrom(CopyFrom o) {
        YWeatherModuleImpl from = (YWeatherModuleImpl) o;
        this.setAstronomy((from.getAstronomy() != null)
            ? (Astronomy) from.getAstronomy().clone() : null);
        this.setCondition((from.getCondition() != null)
            ? (Condition) from.getCondition().clone() : null);
        this.setLocation((from.getLocation() != null)
            ? (Location) from.getLocation().clone() : null);
        this.setUnits((from.getUnits() != null)
            ? (Units) from.getUnits().clone() : null);
        this.setWind((from.getWind() != null) ? (Wind) from.getWind().clone()
                                              : null);

        this.setAtmosphere((from.getAtmosphere() != null)
            ? (Atmosphere) from.getAtmosphere().clone() : null);

        if(from.getForecasts() != null) {
            this.forecasts = new Forecast[from.forecasts.length];

            for(int i = 0; i < from.forecasts.length; i++) {
                this.forecasts[i] = (from.forecasts[i] != null)
                    ? (Forecast) from.forecasts[i].clone() : null;
            }
        } else {
            this.forecasts = null;
        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Forecast[] getForecasts() {
        return forecasts;
    }

    public void setForecasts(Forecast[] forecasts) {
        this.forecasts = forecasts;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public static interface CopyFromInterface extends YWeatherFeedModule,
        YWeatherEntryModule {
    }
}
