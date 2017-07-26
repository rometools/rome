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

import java.util.Locale;

import org.jdom2.Element;

import com.rometools.modules.georss.geometries.Point;
import com.rometools.modules.georss.geometries.Position;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;
import com.rometools.utils.Strings;

/**
 * W3CGeoParser is a parser for the W3C geo format.
 */
public class W3CGeoParser implements ModuleParser {

    @Override
    public String getNamespaceUri() {
        return GeoRSSModule.GEORSS_W3CGEO_URI;
    }

    static Module parseW3C(final Element element) {

        GeoRSSModule geoRSSModule = null;

        // do we have an optional "Point" element ?
        Element pointElement = element.getChild("Point", GeoRSSModule.W3CGEO_NS);

        // we don't have an optional "Point" element
        if (pointElement == null) {
            pointElement = element;
        }

        final Element lat = pointElement.getChild("lat", GeoRSSModule.W3CGEO_NS);
        Element lng = pointElement.getChild("long", GeoRSSModule.W3CGEO_NS);
        if (lng == null) {
            lng = pointElement.getChild("lon", GeoRSSModule.W3CGEO_NS);
        }

        if (lat != null && lng != null) {

            geoRSSModule = new W3CGeoModuleImpl();

            final String latTxt = Strings.trimToNull(lat.getText());
            final String lngTxt = Strings.trimToNull(lng.getText());

            if (latTxt != null && lngTxt != null) {

                final double latitude = Double.parseDouble(lat.getText());
                final double longitude = Double.parseDouble(lng.getText());

                final Position position = new Position(latitude, longitude);

                final Point point = new Point(position);

                geoRSSModule.setGeometry(point);
            }

        }

        return geoRSSModule;
    }

    @Override
    public Module parse(final Element element, final Locale locale) {
        return parseW3C(element);
    }

}
