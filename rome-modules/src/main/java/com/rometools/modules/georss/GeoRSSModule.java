/*
 * Copyright 2006 Marc Wick, geonames.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.rometools.modules.georss;

import org.jdom2.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rometools.modules.georss.geometries.AbstractGeometry;
import com.rometools.modules.georss.geometries.Point;
import com.rometools.modules.georss.geometries.Position;
import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.module.ModuleImpl;

/**
 * GeoRSSModule is the main georss interface defining the methods to produce and consume georss
 * elements.
 */
public abstract class GeoRSSModule extends ModuleImpl implements Cloneable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(GeoRSSModule.class);

    protected AbstractGeometry geometry;

    private Double elev;
    private Integer floor;
    private Double radius;
    private String featureTypeTag;
    private String relationshipTag;
    private String featureNameTag;

    public static final String version = "0.9.8";

    /**
     * namespace URI for georss simple: <i>"http://www.georss.org/georss"</i>
     */
    public static final String GEORSS_GEORSS_URI = "http://www.georss.org/georss";

    /**
     * namespace URI for w3c georss : <i>"http://www.w3.org/2003/01/geo/wgs84_pos#"</i>
     */
    public static final String GEORSS_W3CGEO_URI = "http://www.w3.org/2003/01/geo/wgs84_pos#";

    /**
     * namespace URI for GML georss : <i>"http://www.opengis.net/gml"</i>
     */
    public static final String GEORSS_GML_URI = "http://www.opengis.net/gml";

    /**
     * Namespace for georss simple : <i>xmlns:georss="http://www.georss.org/georss"</i>
     */
    public static final Namespace SIMPLE_NS = Namespace.getNamespace("georss", GeoRSSModule.GEORSS_GEORSS_URI);

    /**
     *
     * Namespace for w3c georss : <i>xmlns:geo="http://www.w3.org/2003/01/geo/wgs84_pos#"</i>
     */
    public static final Namespace W3CGEO_NS = Namespace.getNamespace("geo", GeoRSSModule.GEORSS_W3CGEO_URI);

    /**
     *
     * Namespace for gml georss : <i>xmlns:gml="http://www.opengis.net/gml"</i>
     */
    public static final Namespace GML_NS = Namespace.getNamespace("gml", GeoRSSModule.GEORSS_GML_URI);

    protected GeoRSSModule(final Class<? extends GeoRSSModule> beanClass, final java.lang.String uri) {
        super(beanClass, uri);
    }

    /**
     * Set geometry of georss element
     *
     * @param geometry geometry
     */
    public void setGeometry(final AbstractGeometry geometry) {
        this.geometry = geometry;
    }

    /**
     * returns the geometry
     *
     * @return geometry
     */
    public AbstractGeometry getGeometry() {
        return geometry;
    }

    /**
     * The Feature Type tag of GeoRSS element
     *
     * @return Feature Type tag
     */
    public String getFeatureTypeTag() {
        return featureTypeTag;
    }

    /**
     * Set Feature Type tag of GeoRSS element
     *
     * @param featureTypeTag
     */
    public void setFeatureTypeTag(String featureTypeTag) {
        this.featureTypeTag = featureTypeTag;
    }

    /**
     * The Relationship tag of GeoRSS element
     *
     * @return Relationship tag
     */
    public String getRelationshipTag() {
        return relationshipTag;
    }

    /**
     * Set Relationship tag of GeoRSS element
     *
     * @param relationshipTag
     */
    public void setRelationshipTag(String relationshipTag) {
        this.relationshipTag = relationshipTag;
    }

    /**
     * The Feature Name tag of GeoRSS element
     *
     * @return Feature Name tag
     */
    public String getFeatureNameTag() {
        return featureNameTag;
    }

    /**
     * Set Feature Name tag of GeoRSS element
     *
     * @param featureNameTag
     */
    public void setFeatureNameTag(String featureNameTag) {
        this.featureNameTag = featureNameTag;
    }

    /**
     * Elevation, specified in GeoRSS elements, can be expressed as "elev".
     * "elev" is meant to contain "common" GPS elevation readings, i.e. height in meters
     * from the WGS84 ellipsoid, which is a reading that should be easy to get from any GPS device.
     *
     * Set elev of georss element
     *
     * @param elev elev
     */
    public void setElev(final Double elev) {
        this.elev = elev;
    }

    /**
     * returns the elev of georss element
     *
     * @return elev
     */
    public Double getElev() {
        return elev;
    }

    /**
     * Elevation, specified in GeoRSS elements, can be expressed as "floor".
     * "floor" is meant to contain the floor number of a building.
     *
     * Set floor of georss element
     *
     * @param floor floor
     */
    public void setFloor(final Integer floor) {
        this.floor = floor;
    }

    /**
     * returns the floor of georss element
     *
     * @return floor
     */
    public Integer getFloor() {
        return floor;
    }

    /**
     * "radius" indicates the size in meters of a radius or buffer around the geometry object,
     * for example, radius of circular area around a point geometry.
     *
     * Set radius of georss element
     *
     * @param radius radius
     */
    public void setRadius(final Double radius) {
        this.radius = radius;
    }

    /**
     * returns the radius of georss element
     *
     * @return radius
     */
    public Double getRadius() {
        return radius;
    }

    /**
     * Convenience method to return point geometry. Returns null if the geometry is non-point.
     *
     * @return geometry
     */
    public Position getPosition() {
        if (geometry instanceof Point) {
            return ((Point) geometry).getPosition();
        }
        return null;
    }

    /**
     * Convenience method to set point geometry.
     *
     * @return geometry
     */
    public void setPosition(final Position pos) {
        if (pos != null) {
            geometry = new Point(pos);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.rometools.rome.feed.CopyFrom#copyFrom(java.lang.Object)
     */
    @Override
    public void copyFrom(final CopyFrom obj) {
        final GeoRSSModule geoRSSModule = (GeoRSSModule) obj;
        geometry = geoRSSModule.getGeometry();
        try {
            geometry = (AbstractGeometry) geometry.clone();
        } catch (final CloneNotSupportedException ex) {
            LOG.error("Error", ex);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            final GeoRSSModule retval = (GeoRSSModule) super.clone();
            if (geometry != null) {
                retval.geometry = (AbstractGeometry) geometry.clone();
            }
            return retval;
        } catch (final Exception ex) {
            LOG.error("Error", ex);
        }
        throw new CloneNotSupportedException();
    }
}
