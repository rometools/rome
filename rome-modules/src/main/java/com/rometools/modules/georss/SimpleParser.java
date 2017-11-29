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

import com.rometools.modules.georss.geometries.Envelope;
import com.rometools.modules.georss.geometries.LineString;
import com.rometools.modules.georss.geometries.LinearRing;
import com.rometools.modules.georss.geometries.Point;
import com.rometools.modules.georss.geometries.Polygon;
import com.rometools.modules.georss.geometries.Position;
import com.rometools.modules.georss.geometries.PositionList;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;
import com.rometools.utils.Doubles;
import com.rometools.utils.Strings;

/**
 * SimpleParser is a parser for the GeoRSS Simple format.
 */
public class SimpleParser implements ModuleParser {

    @Override
    public String getNamespaceUri() {
        return GeoRSSModule.GEORSS_GEORSS_URI;
    }

    private static PositionList parsePosList(final Element element) {

        PositionList posList = null;

        final String coordinates = Strings.trimToNull(element.getText());
        if (coordinates != null) {

            posList = new PositionList();
            final String[] coord = coordinates.split("\\s+");
            for (int i = 0; i < coord.length; i += 2) {
                final double latitude, longitude;
                try {
                    latitude = Double.parseDouble(coord[i]);
                    longitude = Double.parseDouble(coord[i + 1]);
                } catch (final NumberFormatException e) {
                	return null;
                }
                posList.add(latitude, longitude);
            }

        }

        return posList;

    }

    @Override
    public Module parse(final Element element, final Locale locale) {
        return parseSimple(element);
    }

    static Module parseSimple(final Element element) {

        final Element pointElement = element.getChild("point", GeoRSSModule.SIMPLE_NS);
        final Element lineElement = element.getChild("line", GeoRSSModule.SIMPLE_NS);
        final Element polygonElement = element.getChild("polygon", GeoRSSModule.SIMPLE_NS);
        final Element boxElement = element.getChild("box", GeoRSSModule.SIMPLE_NS);
        final Element whereElement = element.getChild("where", GeoRSSModule.SIMPLE_NS);

        GeoRSSModule geoRSSModule = null;

        if (pointElement != null) {

            final String coordinates = Strings.trimToNull(pointElement.getText());
            if (coordinates != null) {

                final String[] coord = coordinates.split("\\s+");

                if (coord.length == 2) {

                    final Double latitude = Doubles.parse(coord[0]);
                    final Double longitude = Doubles.parse(coord[1]);

                    if (latitude != null && longitude != null) {

                        final Position pos = new Position(latitude, longitude);

                        final Point point = new Point(pos);

                        geoRSSModule = new SimpleModuleImpl();
                        geoRSSModule.setGeometry(point);

                    }

                }

            }

        } else if (lineElement != null) {

            final PositionList posList = parsePosList(lineElement);

            if (posList != null) {

                final LineString lineString = new LineString(posList);

                geoRSSModule = new SimpleModuleImpl();
                geoRSSModule.setGeometry(lineString);

            }

        } else if (polygonElement != null) {

            final PositionList posList = parsePosList(polygonElement);
            if (posList != null) {

                final LinearRing linearRing = new LinearRing(posList);

                final Polygon poly = new Polygon();
                poly.setExterior(linearRing);

                geoRSSModule = new SimpleModuleImpl();
                geoRSSModule.setGeometry(poly);

            }

        } else if (boxElement != null) {

            final String coordinates = Strings.trimToNull(boxElement.getText());
            if (coordinates != null) {

                final String[] coord = coordinates.split("\\s+");
                final double bottom, left, top, right;
                try {
                    bottom = Double.parseDouble(coord[0]);
                    left = Double.parseDouble(coord[1]);
                    top = Double.parseDouble(coord[2]);
                    right = Double.parseDouble(coord[3]);
                } catch (final NumberFormatException e) {
                	return null;
                }

                final Envelope envelope = new Envelope(bottom, left, top, right);

                geoRSSModule = new SimpleModuleImpl();
                geoRSSModule.setGeometry(envelope);
            }

        } else if (whereElement != null) {

            geoRSSModule = (GeoRSSModule) GMLParser.parseGML(whereElement);

        }

        return geoRSSModule;
    }

}
