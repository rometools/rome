/*
 * Copyright 2006 Marc Wick, geonames.org
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
 *
 */
package com.rometools.modules.georss;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

/**
 * static utility methods for georss.
 */
public class GeoRSSUtils {

    private GeoRSSUtils() {
    }

    /**
     * This convenience method checks whether there is any geoRss Element and will return it (georss
     * simple or W3GGeo).
     *
     * @param entry the element in the feed which might have a georss element
     * @return a georssmodule or null if none is present
     */
    public static GeoRSSModule getGeoRSS(final SyndEntry entry) {
        final GeoRSSModule simple = (GeoRSSModule) entry.getModule(GeoRSSModule.GEORSS_GEORSS_URI);
        final GeoRSSModule w3cGeo = (GeoRSSModule) entry.getModule(GeoRSSModule.GEORSS_W3CGEO_URI);
        final GeoRSSModule gml = (GeoRSSModule) entry.getModule(GeoRSSModule.GEORSS_GML_URI);
        if (gml != null) {
            return gml;
        }
        if (simple != null) {
            return simple;
        }
        if (w3cGeo != null) {
            return w3cGeo;
        }

        return null;
        /*
         * if (geoRSSModule == null && w3cGeo != null) { geoRSSModule = w3cGeo; } else if
         * (geoRSSModule == null && gml != null) { geoRSSModule = gml; } else if (geoRSSModule !=
         * null && w3cGeo != null) { // sanity check if
         * (!geoRSSModule.getGeometry().equals(w3cGeo.getGeometry())) { throw new
         * Error("geometry of simple and w3c do not match"); } } return geoRSSModule;
         */
    }

    /**
     * This convenience method checks whether there is any geoRss Element and will return it (georss
     * simple or W3GGeo).
     *
     * @param feed the element in the feed which might have a georss element
     * @return a georssmodule or null if none is present
     */
    public static GeoRSSModule getGeoRSS(final SyndFeed feed) {
        final GeoRSSModule simple = (GeoRSSModule) feed.getModule(GeoRSSModule.GEORSS_GEORSS_URI);
        final GeoRSSModule w3cGeo = (GeoRSSModule) feed.getModule(GeoRSSModule.GEORSS_W3CGEO_URI);
        final GeoRSSModule gml = (GeoRSSModule) feed.getModule(GeoRSSModule.GEORSS_GML_URI);

        if (gml != null) {
            return gml;
        }
        if (simple != null) {
            return simple;
        }
        if (w3cGeo != null) {
            return w3cGeo;
        }

        return null;
        /*
         * if (geoRSSModule == null && w3cGeo != null) { geoRSSModule = w3cGeo; } else if
         * (geoRSSModule == null && gml != null) { geoRSSModule = gml; } else if (geoRSSModule !=
         * null && w3cGeo != null) { // sanity check if
         * (!geoRSSModule.getGeometry().equals(w3cGeo.getGeometry())) { throw new
         * Error("geometry of simple and w3c do not match"); } } return geoRSSModule;
         */
    }
}
