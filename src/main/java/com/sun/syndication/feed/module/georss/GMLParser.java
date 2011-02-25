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
package com.sun.syndication.feed.module.georss;

import org.jdom.Element;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.georss.geometries.LineString;
import com.sun.syndication.feed.module.georss.geometries.LinearRing;
import com.sun.syndication.feed.module.georss.geometries.Point;
import com.sun.syndication.feed.module.georss.geometries.Polygon;
import com.sun.syndication.feed.module.georss.geometries.Envelope;
import com.sun.syndication.feed.module.georss.geometries.Position;
import com.sun.syndication.feed.module.georss.geometries.PositionList;
import com.sun.syndication.io.ModuleParser;

import java.util.*;

/**
 * GMLParser is a parser for the GML georss format.
 *
 * @author Marc Wick
 * @version $Id: GMLParser.java,v 1.2 2007/06/05 20:44:53 marcwick Exp $
 *
 */
public class GMLParser implements ModuleParser {
    
        /*
         * (non-Javadoc)
         *
         * @see com.sun.syndication.io.ModuleParser#getNamespaceUri()
         */
    public String getNamespaceUri() {
        return GeoRSSModule.GEORSS_GEORSS_URI;
    }
    
        /*
         * (non-Javadoc)
         *
         * @see com.sun.syndication.io.ModuleParser#parse(org.jdom.Element)
         */
    public Module parse(Element element) {
        Module geoRssModule = parseGML(element);
        return geoRssModule;
    }
    
    private static PositionList parsePosList(Element element) {
        String coordinates = element.getText();
        String[] coord = GeoRSSUtils.trimWhitespace(coordinates).split(" ");
        PositionList posList = new PositionList();
        for (int i=0; i<coord.length; i += 2) {
            posList.add(Double.parseDouble(coord[i]), Double.parseDouble(coord[i+1]));
        }
        return posList;
    }
    
    static Module parseGML(Element element) {
        GeoRSSModule geoRSSModule = null;
        
        Element pointElement = element.getChild("Point",
                GeoRSSModule.GML_NS);
        Element lineStringElement = element.getChild("LineString",
                GeoRSSModule.GML_NS);
        Element polygonElement = element.getChild("Polygon",
                GeoRSSModule.GML_NS);
        Element envelopeElement = element.getChild("Envelope",
                GeoRSSModule.GML_NS);
        if (pointElement != null) {
            Element posElement = pointElement.getChild("pos", GeoRSSModule.GML_NS);
            if (posElement != null) {
                geoRSSModule = new GMLModuleImpl();
                String coordinates = posElement.getText();
                String[] coord = GeoRSSUtils.trimWhitespace(coordinates).split(" ");
                Position pos = new Position(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]));
                geoRSSModule.setGeometry(new Point(pos));
            }
        } else if (lineStringElement != null) {
            Element posListElement = lineStringElement.getChild("posList", GeoRSSModule.GML_NS);
            if (posListElement != null) {
                geoRSSModule = new GMLModuleImpl();
                geoRSSModule.setGeometry(new LineString(parsePosList(posListElement)));
            }
        } else if (polygonElement != null) {
            Polygon poly = null;
            
            // The external ring
            Element exteriorElement = polygonElement.getChild("exterior", GeoRSSModule.GML_NS);
            if (exteriorElement != null) {
                Element linearRingElement = exteriorElement.getChild("LinearRing", GeoRSSModule.GML_NS);
                if (linearRingElement != null) {
                    Element posListElement = linearRingElement.getChild("posList", GeoRSSModule.GML_NS);
                    if (posListElement != null) {
                        if (poly == null)
                            poly = new Polygon();
                        poly.setExterior(new LinearRing(parsePosList(posListElement)));
                    }
                }
            }
            
            // The internal rings (holes)
            List interiorElementList = polygonElement.getChildren("interior", GeoRSSModule.GML_NS);
            Iterator it = interiorElementList.iterator();
            while (it.hasNext()) {
                Element interiorElement = (Element)it.next();
                if (interiorElement != null) {
                    Element linearRingElement = interiorElement.getChild("LinearRing", GeoRSSModule.GML_NS);
                    if (linearRingElement != null) {
                        Element posListElement = linearRingElement.getChild("posList", GeoRSSModule.GML_NS);
                        if (posListElement != null) {
                            if (poly == null)
                                poly = new Polygon();
                            poly.getInterior().add(new LinearRing(parsePosList(posListElement)));
                        }
                    }
                }
                
            }
            
            if (poly != null) {
                geoRSSModule = new GMLModuleImpl();
                geoRSSModule.setGeometry(poly);
            }
        } else if (envelopeElement != null) {
            Element lowerElement = envelopeElement.getChild("lowerCorner", GeoRSSModule.GML_NS);
            Element upperElement = envelopeElement.getChild("upperCorner", GeoRSSModule.GML_NS);
            if (lowerElement != null && upperElement != null) {
                geoRSSModule = new GMLModuleImpl();
                String lowerCoordinates = lowerElement.getText();
                String[] lowerCoord = GeoRSSUtils.trimWhitespace(lowerCoordinates).split(" ");
                String upperCoordinates = upperElement.getText();
                String[] upperCoord = GeoRSSUtils.trimWhitespace(upperCoordinates).split(" ");
                Envelope envelope = new Envelope(Double.parseDouble(lowerCoord[0]), Double.parseDouble(lowerCoord[1]),
                        Double.parseDouble(upperCoord[0]), Double.parseDouble(upperCoord[1]));
                geoRSSModule.setGeometry(envelope);
            }
        }
        
        return geoRSSModule;
    }
    
}
