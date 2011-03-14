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
package org.rometools.feed.module.georss;

import org.jdom.Element;

import com.sun.syndication.feed.module.Module;
import org.rometools.feed.module.georss.GMLParser;
import com.sun.syndication.io.ModuleParser;
import org.rometools.feed.module.georss.geometries.*;

/**
 * SimpleParser is a parser for the GeoRSS Simple format.
 *
 * @author Marc Wick
 * @version $Id: SimpleParser.java,v 1.4 2007/04/18 09:59:29 marcwick Exp $
 *
 */
public class SimpleParser implements ModuleParser {
    
    /*
     * (non-Javadoc)
     *
     * @see com.sun.syndication.io.ModuleParser#getNamespaceUri()
     */
    public String getNamespaceUri() {
        return GeoRSSModule.GEORSS_GEORSS_URI;
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
    /*
     * (non-Javadoc)
     *
     * @see com.sun.syndication.io.ModuleParser#parse(org.jdom.Element)
     */
    public Module parse(Element element) {
        Module geoRssModule = parseSimple(element);
        return geoRssModule;
    }
    
    static Module parseSimple(Element element) {
        GeoRSSModule geoRSSModule = null;
        
        Element pointElement = element.getChild("point",
                GeoRSSModule.SIMPLE_NS);
        Element lineElement = element.getChild("line",
                GeoRSSModule.SIMPLE_NS);
        Element polygonElement = element.getChild("polygon",
                GeoRSSModule.SIMPLE_NS);
        Element boxElement = element.getChild("box",
                GeoRSSModule.SIMPLE_NS);
         Element whereElement = element
                .getChild("where", GeoRSSModule.SIMPLE_NS);
        if (pointElement != null) {
            geoRSSModule = new SimpleModuleImpl();
            String coordinates = pointElement.getText();
            String[] coord = GeoRSSUtils.trimWhitespace(coordinates).split(" ");
            Position pos = new Position(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]));
            geoRSSModule.setGeometry(new Point(pos));
        } else if (lineElement != null) {
            geoRSSModule = new SimpleModuleImpl();
            PositionList posList = parsePosList(lineElement);
            geoRSSModule.setGeometry(new LineString(posList));
        } else if (polygonElement != null) {
            geoRSSModule = new SimpleModuleImpl();
            PositionList posList = parsePosList(polygonElement);
            Polygon poly = new Polygon();
            poly.setExterior(new LinearRing(posList));
            geoRSSModule.setGeometry(poly);
        } else if (boxElement != null) {
            geoRSSModule = new SimpleModuleImpl();
            String coordinates = boxElement.getText();
            String[] coord = GeoRSSUtils.trimWhitespace(coordinates).split(" ");
            Envelope envelope = new Envelope(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]), 
                    Double.parseDouble(coord[2]), Double.parseDouble(coord[3]));
            geoRSSModule.setGeometry(envelope);
        } else if (whereElement != null) {
             geoRSSModule = (GeoRSSModule)GMLParser.parseGML(whereElement);
        }
        
        return geoRSSModule;
    }
}
