/*
 * WeatherModuleParser.java
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
package com.sun.syndication.feed.module.yahooweather.io;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.yahooweather.YWeatherModule;
import com.sun.syndication.feed.module.yahooweather.YWeatherModuleImpl;
import com.sun.syndication.feed.module.yahooweather.types.Astronomy;
import com.sun.syndication.feed.module.yahooweather.types.Atmosphere;
import com.sun.syndication.feed.module.yahooweather.types.Condition;
import com.sun.syndication.feed.module.yahooweather.types.ConditionCode;
import com.sun.syndication.feed.module.yahooweather.types.Forecast;
import com.sun.syndication.feed.module.yahooweather.types.Location;
import com.sun.syndication.feed.module.yahooweather.types.Units;
import com.sun.syndication.feed.module.yahooweather.types.Wind;
import com.sun.syndication.io.ModuleParser;

import org.jdom.Element;
import org.jdom.Namespace;


/** ModuleParser implementation for Slash RSS.
 * @version $Revision: 1.2 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class WeatherModuleParser implements ModuleParser {
    private static final SimpleDateFormat TIME_ONLY = new SimpleDateFormat(
            "h:mm a");
    private static final SimpleDateFormat LONG_DATE = new SimpleDateFormat(
            "EEE, d MMM yyyy h:mm a zzz");
    private static final SimpleDateFormat SHORT_DATE = new SimpleDateFormat(
            "d MMM yyyy");
    private static final Namespace NS = Namespace.getNamespace(YWeatherModule.URI);

    /** Creates a new instance of SlashModuleParser */
    public WeatherModuleParser() {
        super();
    }

    public String getNamespaceUri() {
        return YWeatherModule.URI;
    }

    public Module parse(Element element) {
        YWeatherModuleImpl module = new YWeatherModuleImpl();
        Element location = element.getChild("location", WeatherModuleParser.NS);

        if(location != null) {
            Location l = new Location(location.getAttributeValue("city"),
                    location.getAttributeValue("region"),
                    location.getAttributeValue("country"));
            module.setLocation(l);
        }

        Element units = element.getChild("units", WeatherModuleParser.NS);

        if(units != null) {
            Units u = new Units(units.getAttributeValue("temperature"),
                    units.getAttributeValue("distance"),
                    units.getAttributeValue("pressure"),
                    units.getAttributeValue("speed"));
            module.setUnits(u);
        }

        Element wind = element.getChild("wind", WeatherModuleParser.NS);

        if(wind != null) {
            try {
                Wind w = new Wind(Integer.parseInt(wind.getAttributeValue(
                                "chill")),
                        Integer.parseInt(wind.getAttributeValue("direction")),
                        Integer.parseInt(wind.getAttributeValue("speed")));
                module.setWind(w);
            } catch(NumberFormatException nfe) {
                Logger.getAnonymousLogger()
                      .warning("NumberFormatException processing <wind> tag.");
            }
        }

        Element atmosphere = element.getChild("atmosphere",
                WeatherModuleParser.NS);

        if(atmosphere != null) {
            try {
                Atmosphere a = new Atmosphere(Integer.parseInt(
                            atmosphere.getAttributeValue("humidity")),
                        Double.parseDouble(atmosphere.getAttributeValue(
                                "visibility")) / 100,
                        Double.parseDouble(atmosphere.getAttributeValue(
                                "pressure")),
                        Atmosphere.PressureChange.fromCode(Integer.parseInt(
                                atmosphere.getAttributeValue("rising"))));
                module.setAtmosphere(a);
            } catch(NumberFormatException nfe) {
                Logger.getAnonymousLogger()
                      .warning("NumberFormatException processing <atmosphere> tag.");
            }
        }

        Element astronomy = element.getChild("astronomy", WeatherModuleParser.NS);

        if(astronomy != null) {
            try {
                Astronomy a = new Astronomy(TIME_ONLY.parse(
                            astronomy.getAttributeValue("sunrise")
                                     .replaceAll("am", "AM")
                                     .replaceAll("pm", "PM")),
                        TIME_ONLY.parse(astronomy.getAttributeValue("sunset")
                                                 .replaceAll("am", "AM")
                                                 .replaceAll("pm", "PM")));
                module.setAstronomy(a);
            } catch(ParseException pe) {
                Logger.getAnonymousLogger()
                      .warning("ParseException processing <astronomy> tag.");
            }
        }

        Element condition = element.getChild("condition", WeatherModuleParser.NS);

        if(condition != null) {
            try {
                Condition c = new Condition(condition.getAttributeValue("text"),
                        ConditionCode.fromCode(Integer.parseInt(
                                condition.getAttributeValue("code"))),
                        Integer.parseInt(condition.getAttributeValue("temp")),
                        LONG_DATE.parse(condition.getAttributeValue("date")
                                                 .replaceAll("pm", "PM")
                                                 .replaceAll("am", "AM")));
                module.setCondition(c);
            } catch(NumberFormatException nfe) {
                Logger.getAnonymousLogger()
                      .warning("NumberFormatException processing <condition> tag.");
            } catch(ParseException pe) {
                Logger.getAnonymousLogger()
                      .warning("ParseException processing <condition> tag.");
            }
        }

        List forecasts = element.getChildren("forecast", WeatherModuleParser.NS);

        if(forecasts != null) {
            Forecast[] f = new Forecast[forecasts.size()];
            int i = 0;

            for(Iterator it = forecasts.iterator(); it.hasNext(); i++) {
                Element forecast = (Element) it.next();

                try {
                    f[i] = new Forecast(forecast.getAttributeValue("day"),
                            SHORT_DATE.parse(forecast.getAttributeValue("date")),
                            Integer.parseInt(forecast.getAttributeValue("low")),
                            Integer.parseInt(forecast.getAttributeValue("high")),
                            forecast.getAttributeValue("text"),
                            ConditionCode.fromCode(Integer.parseInt(
                                    forecast.getAttributeValue("code"))));
                } catch(NumberFormatException nfe) {
                    Logger.getAnonymousLogger()
                          .warning("NumberFormatException processing <forecast> tag.");
                } catch(ParseException pe) {
                    Logger.getAnonymousLogger()
                          .warning("ParseException processing <forecast> tag.");
                }
            }

            module.setForecasts(f);
        }

        return module;
    }
}
