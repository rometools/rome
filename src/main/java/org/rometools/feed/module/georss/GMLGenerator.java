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

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jdom2.Element;
import org.rometools.feed.module.georss.geometries.AbstractGeometry;
import org.rometools.feed.module.georss.geometries.AbstractRing;
import org.rometools.feed.module.georss.geometries.Envelope;
import org.rometools.feed.module.georss.geometries.LineString;
import org.rometools.feed.module.georss.geometries.LinearRing;
import org.rometools.feed.module.georss.geometries.Point;
import org.rometools.feed.module.georss.geometries.Polygon;
import org.rometools.feed.module.georss.geometries.Position;
import org.rometools.feed.module.georss.geometries.PositionList;

import com.sun.syndication.feed.module.Module;
import com.sun.syndication.io.ModuleGenerator;

/**
 * GMLGenerator produces georss elements in georss GML format.
 * 
 * @author Marc Wick
 * @version $Id: GMLGenerator.java,v 1.1 2007/04/18 09:59:29 marcwick Exp $
 * 
 */
public class GMLGenerator implements ModuleGenerator {

    private static final Set NAMESPACES;

    static {
        final Set nss = new HashSet();
        nss.add(GeoRSSModule.GML_NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    private Element createPosListElement(final PositionList posList) {
        final Element posElement = new Element("posList", GeoRSSModule.GML_NS);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < posList.size(); ++i) {
            sb.append(posList.getLatitude(i)).append(" ").append(posList.getLongitude(i)).append(" ");
        }

        posElement.addContent(sb.toString());
        return posElement;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sun.syndication.io.ModuleGenerator#getNamespaceUri()
     */
    @Override
    public String getNamespaceUri() {
        return GeoRSSModule.GEORSS_GML_URI;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sun.syndication.io.ModuleGenerator#getNamespaces()
     */
    @Override
    public Set getNamespaces() {
        return NAMESPACES;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sun.syndication.io.ModuleGenerator#generate(com.sun.syndication.feed.module.Module, org.jdom2.Element)
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
        root.addNamespaceDeclaration(GeoRSSModule.GML_NS);

        final Element whereElement = new Element("where", GeoRSSModule.SIMPLE_NS);
        element.addContent(whereElement);

        final GeoRSSModule geoRSSModule = (GeoRSSModule) module;
        final AbstractGeometry geometry = geoRSSModule.getGeometry();

        if (geometry instanceof Point) {
            final Position pos = ((Point) geometry).getPosition();

            final Element pointElement = new Element("Point", GeoRSSModule.GML_NS);
            whereElement.addContent(pointElement);

            final Element posElement = new Element("pos", GeoRSSModule.GML_NS);
            posElement.addContent(String.valueOf(pos.getLatitude()) + " " + String.valueOf(pos.getLongitude()));
            pointElement.addContent(posElement);
        }

        else if (geometry instanceof LineString) {
            final PositionList posList = ((LineString) geometry).getPositionList();

            final Element lineElement = new Element("LineString", GeoRSSModule.GML_NS);
            lineElement.addContent(createPosListElement(posList));
            whereElement.addContent(lineElement);
        } else if (geometry instanceof Polygon) {
            final Element polygonElement = new Element("Polygon", GeoRSSModule.GML_NS);
            {
                final AbstractRing ring = ((Polygon) geometry).getExterior();
                if (ring instanceof LinearRing) {
                    final Element exteriorElement = new Element("exterior", GeoRSSModule.GML_NS);
                    polygonElement.addContent(exteriorElement);
                    final Element ringElement = new Element("LinearRing", GeoRSSModule.GML_NS);
                    exteriorElement.addContent(ringElement);
                    ringElement.addContent(createPosListElement(((LinearRing) ring).getPositionList()));

                } else {
                    System.err.println("GeoRSS GML format can't handle rings of type: " + ring.getClass().getName());
                }
            }
            final List interiorList = ((Polygon) geometry).getInterior();
            final Iterator it = interiorList.iterator();
            while (it.hasNext()) {
                final AbstractRing ring = (AbstractRing) it.next();
                if (ring instanceof LinearRing) {
                    final Element interiorElement = new Element("interior", GeoRSSModule.GML_NS);
                    polygonElement.addContent(interiorElement);
                    final Element ringElement = new Element("LinearRing", GeoRSSModule.GML_NS);
                    interiorElement.addContent(ringElement);
                    ringElement.addContent(createPosListElement(((LinearRing) ring).getPositionList()));

                } else {
                    System.err.println("GeoRSS GML format can't handle rings of type: " + ring.getClass().getName());
                }
            }
            whereElement.addContent(polygonElement);
        } else if (geometry instanceof Envelope) {
            final Envelope envelope = (Envelope) geometry;
            final Element envelopeElement = new Element("Envelope", GeoRSSModule.GML_NS);
            whereElement.addContent(envelopeElement);

            final Element lowerElement = new Element("lowerCorner", GeoRSSModule.GML_NS);
            lowerElement.addContent(String.valueOf(envelope.getMinLatitude()) + " " + String.valueOf(envelope.getMinLongitude()));
            envelopeElement.addContent(lowerElement);

            final Element upperElement = new Element("upperCorner", GeoRSSModule.GML_NS);
            upperElement.addContent(String.valueOf(envelope.getMaxLatitude()) + " " + String.valueOf(envelope.getMaxLongitude()));
            envelopeElement.addContent(upperElement);

        } else {
            System.err.println("GeoRSS GML format can't handle geometries of type: " + geometry.getClass().getName());
        }
    }
}
