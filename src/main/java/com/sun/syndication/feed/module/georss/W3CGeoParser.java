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
import com.sun.syndication.io.ModuleParser;
import com.sun.syndication.feed.module.georss.geometries.*;

/**
 * W3CGeoParser is a parser for the W3C geo format.
 *
 * @author Marc Wick
 * @version $Id: W3CGeoParser.java,v 1.4 2007/04/18 09:59:29 marcwick Exp $
 *
 */
public class W3CGeoParser implements ModuleParser {
    
        /*
         * (non-Javadoc)
         *
         * @see com.sun.syndication.io.ModuleParser#getNamespaceUri()
         */
    public String getNamespaceUri() {
        return GeoRSSModule.GEORSS_W3CGEO_URI;
    }
    
    
    static Module parseW3C(Element element) {
        GeoRSSModule geoRSSModule = null;
        
        // do we have an optional "Point" element ?
        Element pointElement = element
                .getChild("Point", GeoRSSModule.W3CGEO_NS);
        
        // we don't have an optional "Point" element
        if (pointElement == null) {
            pointElement = element;
        }
        
        Element lat = pointElement.getChild("lat", GeoRSSModule.W3CGEO_NS);
        Element lng = pointElement.getChild("long", GeoRSSModule.W3CGEO_NS);
        if (lng == null)
            lng = pointElement.getChild("lon", GeoRSSModule.W3CGEO_NS);
        if (lat != null && lng != null) {
            geoRSSModule = new W3CGeoModuleImpl();
            Position pos = new Position(Double.parseDouble(lat.getText()), Double.parseDouble(lng.getText()));
            geoRSSModule.setGeometry(new Point(pos));
        }
        
        return geoRSSModule;
    }
    
        /*
         * (non-Javadoc)
         *
         * @see com.sun.syndication.io.ModuleParser#parse(org.jdom.Element)
         */
    public Module parse(Element element) {
        Module geoRssModule = parseW3C(element);
        return geoRssModule;
    }
    
}
