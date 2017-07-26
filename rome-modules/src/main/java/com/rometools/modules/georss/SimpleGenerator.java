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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rometools.modules.georss.geometries.AbstractGeometry;
import com.rometools.modules.georss.geometries.AbstractRing;
import com.rometools.modules.georss.geometries.Envelope;
import com.rometools.modules.georss.geometries.LineString;
import com.rometools.modules.georss.geometries.LinearRing;
import com.rometools.modules.georss.geometries.Point;
import com.rometools.modules.georss.geometries.Polygon;
import com.rometools.modules.georss.geometries.Position;
import com.rometools.modules.georss.geometries.PositionList;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;

/**
 * SimpleGenerator produces georss elements in georss simple format.
 */
public class SimpleGenerator implements ModuleGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleGenerator.class);

    private static final Set<Namespace> NAMESPACES;
    static {
        final Set<Namespace> nss = new HashSet<Namespace>();
        nss.add(GeoRSSModule.SIMPLE_NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    /**
     * @param posList PositionList to convert
     * @return String representation
     */
    private String posListToString(final PositionList posList) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < posList.size(); ++i) {
            sb.append(posList.getLatitude(i)).append(" ").append(posList.getLongitude(i)).append(" ");
        }
        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * @see com.rometools.rome.io.ModuleGenerator#getNamespaceUri()
     */
    @Override
    public String getNamespaceUri() {
        return GeoRSSModule.GEORSS_GEORSS_URI;
    }

    /*
     * (non-Javadoc)
     * @see com.rometools.rome.io.ModuleGenerator#getNamespaces()
     */
    @Override
    public Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    /*
     * (non-Javadoc)
     * @see com.rometools.rome.io.ModuleGenerator#generate(com.rometools.rome.feed.module.Module,
     * org.jdom2.Element)
     */
    @Override
    public void generate(final Module module, final Element element) {
        // this is not necessary, it is done to avoid the namespace definition
        // in every item.
        Element root = element;
        while (root.getParent() != null && root.getParent() instanceof Element) {
            root = (Element) element.getParent();
        }
        root.addNamespaceDeclaration(GeoRSSModule.SIMPLE_NS);

        final GeoRSSModule geoRSSModule = (GeoRSSModule) module;

        final AbstractGeometry geometry = geoRSSModule.getGeometry();
        if (geometry instanceof Point) {
            final Position pos = ((Point) geometry).getPosition();

            final Element pointElement = new Element("point", GeoRSSModule.SIMPLE_NS);
            pointElement.addContent(pos.getLatitude() + " " + pos.getLongitude());
            element.addContent(pointElement);
        } else if (geometry instanceof LineString) {
            final PositionList posList = ((LineString) geometry).getPositionList();

            final Element lineElement = new Element("line", GeoRSSModule.SIMPLE_NS);

            lineElement.addContent(posListToString(posList));
            element.addContent(lineElement);
        } else if (geometry instanceof Polygon) {
            final AbstractRing ring = ((Polygon) geometry).getExterior();
            if (ring instanceof LinearRing) {
                final PositionList posList = ((LinearRing) ring).getPositionList();
                final Element polygonElement = new Element("polygon", GeoRSSModule.SIMPLE_NS);

                polygonElement.addContent(posListToString(posList));
                element.addContent(polygonElement);
            } else {
                LOG.error("GeoRSS simple format can't handle rings of type: " + ring.getClass().getName());
            }
            if (((Polygon) geometry).getInterior() != null && !((Polygon) geometry).getInterior().isEmpty()) {
                LOG.error("GeoRSS simple format can't handle interior rings (ignored)");
            }
        } else if (geometry instanceof Envelope) {
            final Envelope envelope = (Envelope) geometry;
            final Element boxElement = new Element("box", GeoRSSModule.SIMPLE_NS);
            boxElement.addContent(envelope.getMinLatitude() + " " + envelope.getMinLongitude() + " " + envelope.getMaxLatitude() + " "
                    + envelope.getMaxLongitude());
            element.addContent(boxElement);
        } else {
            LOG.error("GeoRSS simple format can't handle geometries of type: " + geometry.getClass().getName());
        }
    }

}
