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

import java.util.Iterator;
import java.util.List;
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
import com.rometools.utils.Strings;

/**
 * GMLParser is a parser for the GML georss format.
 */
public class GMLParser implements ModuleParser {

    @Override
    public String getNamespaceUri() {
        return GeoRSSModule.GEORSS_GEORSS_URI;
    }

    @Override
    public Module parse(final Element element, final Locale locale) {
        final Module geoRssModule = parseGML(element);
        return geoRssModule;
    }

    private static PositionList parsePosList(final Element element) {
        final String coordinates = element.getText();
        final String[] coord = Strings.trimToEmpty(coordinates).split("\\s+");
        final PositionList posList = new PositionList();
        for (int i = 0; i < coord.length; i += 2) {
        	try {
        		posList.add(Double.parseDouble(coord[i]), Double.parseDouble(coord[i + 1]));
        	} catch (final NumberFormatException e) {
        		return null;
        	}
        }
        return posList;
    }

    static Module parseGML(final Element element) {
        GeoRSSModule geoRSSModule = null;

        final Element pointElement = element.getChild("Point", GeoRSSModule.GML_NS);
        final Element lineStringElement = element.getChild("LineString", GeoRSSModule.GML_NS);
        final Element polygonElement = element.getChild("Polygon", GeoRSSModule.GML_NS);
        final Element envelopeElement = element.getChild("Envelope", GeoRSSModule.GML_NS);
        if (pointElement != null) {
            final Element posElement = pointElement.getChild("pos", GeoRSSModule.GML_NS);
            if (posElement != null) {
                geoRSSModule = new GMLModuleImpl();
                final String coordinates = posElement.getText();
                final String[] coord = Strings.trimToEmpty(coordinates).split("\\s+");
                final Position pos;
                try {
                	pos = new Position(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]));
                } catch (final NumberFormatException e) {
                	return null;
                }
                geoRSSModule.setGeometry(new Point(pos));
            }
        } else if (lineStringElement != null) {
            final Element posListElement = lineStringElement.getChild("posList", GeoRSSModule.GML_NS);
            if (posListElement != null) {
                geoRSSModule = new GMLModuleImpl();
                PositionList positionList = parsePosList(posListElement);
                if (positionList == null) {
                	return null;
                }
                geoRSSModule.setGeometry(new LineString(positionList));
            }
        } else if (polygonElement != null) {
            Polygon poly = null;

            // The external ring
            final Element exteriorElement = polygonElement.getChild("exterior", GeoRSSModule.GML_NS);
            if (exteriorElement != null) {
                final Element linearRingElement = exteriorElement.getChild("LinearRing", GeoRSSModule.GML_NS);
                if (linearRingElement != null) {
                    final Element posListElement = linearRingElement.getChild("posList", GeoRSSModule.GML_NS);
                    if (posListElement != null) {
                        if (poly == null) {
                            poly = new Polygon();
                        }

                        PositionList positionList = parsePosList(posListElement);
                        if (positionList == null) {
                        	return null;
                        }
                        poly.setExterior(new LinearRing(positionList));
                    }
                }
            }

            // The internal rings (holes)
            final List<Element> interiorElementList = polygonElement.getChildren("interior", GeoRSSModule.GML_NS);
            final Iterator<Element> it = interiorElementList.iterator();
            while (it.hasNext()) {
                final Element interiorElement = it.next();
                if (interiorElement != null) {
                    final Element linearRingElement = interiorElement.getChild("LinearRing", GeoRSSModule.GML_NS);
                    if (linearRingElement != null) {
                        final Element posListElement = linearRingElement.getChild("posList", GeoRSSModule.GML_NS);
                        if (posListElement != null) {
                            if (poly == null) {
                                poly = new Polygon();
                            }

                            PositionList positionList = parsePosList(posListElement);
                            if (positionList == null) {
                            	return null;
                            }
                            poly.getInterior().add(new LinearRing(positionList));
                        }
                    }
                }

            }

            if (poly != null) {
                geoRSSModule = new GMLModuleImpl();
                geoRSSModule.setGeometry(poly);
            }
        } else if (envelopeElement != null) {
            final Element lowerElement = envelopeElement.getChild("lowerCorner", GeoRSSModule.GML_NS);
            final Element upperElement = envelopeElement.getChild("upperCorner", GeoRSSModule.GML_NS);
            if (lowerElement != null && upperElement != null) {
                geoRSSModule = new GMLModuleImpl();
                final String lowerCoordinates = lowerElement.getText();
                final String[] lowerCoord = Strings.trimToEmpty(lowerCoordinates).split("\\s+");
                final String upperCoordinates = upperElement.getText();
                final String[] upperCoord = Strings.trimToEmpty(upperCoordinates).split("\\s+");
                final Envelope envelope;
                try {
                	envelope = new Envelope(Double.parseDouble(lowerCoord[0]), Double.parseDouble(lowerCoord[1]), Double.parseDouble(upperCoord[0]),
                            Double.parseDouble(upperCoord[1]));
                } catch (final NumberFormatException e) {
                	return null;
                }
                geoRSSModule.setGeometry(envelope);
            }
        }

        return geoRSSModule;
    }

}
