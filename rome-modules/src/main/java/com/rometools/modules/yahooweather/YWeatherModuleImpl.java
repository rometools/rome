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
import com.rometools.modules.yahooweather.types.Condition;
import com.rometools.modules.yahooweather.types.Forecast;
import com.rometools.modules.yahooweather.types.Location;
import com.rometools.modules.yahooweather.types.Units;
import com.rometools.modules.yahooweather.types.Wind;
import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.feed.module.ModuleImpl;

/**
 * A Module implementation for entry or feed level information.
 */
public class YWeatherModuleImpl extends ModuleImpl implements YWeatherEntryModule, YWeatherFeedModule {
    private static final long serialVersionUID = 1L;
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

    @Override
    public Class<? extends Module> getInterface() {
        return CopyFromInterface.class;
    }

    @Override
    public void copyFrom(final CopyFrom o) {
        final YWeatherModuleImpl from = (YWeatherModuleImpl) o;
        setAstronomy(from.getAstronomy() != null ? (Astronomy) from.getAstronomy().clone() : null);
        setCondition(from.getCondition() != null ? (Condition) from.getCondition().clone() : null);
        setLocation(from.getLocation() != null ? (Location) from.getLocation().clone() : null);
        setUnits(from.getUnits() != null ? (Units) from.getUnits().clone() : null);
        setWind(from.getWind() != null ? (Wind) from.getWind().clone() : null);

        setAtmosphere(from.getAtmosphere() != null ? (Atmosphere) from.getAtmosphere().clone() : null);

        if (from.getForecasts() != null) {
            forecasts = new Forecast[from.forecasts.length];

            for (int i = 0; i < from.forecasts.length; i++) {
                forecasts[i] = from.forecasts[i] != null ? (Forecast) from.forecasts[i].clone() : null;
            }
        } else {
            forecasts = null;
        }
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(final Location location) {
        this.location = location;
    }

    @Override
    public Astronomy getAstronomy() {
        return astronomy;
    }

    @Override
    public void setAstronomy(final Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    @Override
    public Units getUnits() {
        return units;
    }

    @Override
    public void setUnits(final Units units) {
        this.units = units;
    }

    @Override
    public Condition getCondition() {
        return condition;
    }

    @Override
    public void setCondition(final Condition condition) {
        this.condition = condition;
    }

    @Override
    public Forecast[] getForecasts() {
        return forecasts;
    }

    @Override
    public void setForecasts(final Forecast[] forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public Wind getWind() {
        return wind;
    }

    @Override
    public void setWind(final Wind wind) {
        this.wind = wind;
    }

    @Override
    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    @Override
    public void setAtmosphere(final Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    /**
     * Inteface combining feed and entry module.
     */
    public interface CopyFromInterface extends YWeatherFeedModule, YWeatherEntryModule {
    }
}
