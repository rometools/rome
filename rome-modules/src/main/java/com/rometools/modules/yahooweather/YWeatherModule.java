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

import com.rometools.rome.feed.module.Module;

/**
 * A simple parent interface that defines the feed URI.
 */
public interface YWeatherModule extends Module {
    /**
     * "http://xml.weather.yahoo.com/ns/rss/1.0".
     */
    String URI = "http://xml.weather.yahoo.com/ns/rss/1.0";
}
