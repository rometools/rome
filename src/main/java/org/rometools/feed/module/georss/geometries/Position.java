/*
 * Position.java
 *
 * Created on 8. februar 2007, 11:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.rometools.feed.module.georss.geometries;

import java.io.Serializable;

/**
 * A two dimensional position represented by latitude and longitude decimal degrees in WGS84
 * @author runaas
 */
public class Position implements Cloneable, Serializable {
    private double latitude;
    private double longitude;
    
    /** Creates a new instance of Position */
    public Position() {
        latitude  = Double.NaN;
        longitude = Double.NaN;
    }
    
    /**
     * Create Position from a pair of coordinate values
     *
     * @param latitude
     * @param longitude
     */
    public Position(double latitude, double longitude) {
        this.latitude  = latitude;
        this.longitude = longitude;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        
        Position p = (Position)obj;
        return p.latitude == latitude && p.longitude == longitude;
    }
    
    /**
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }
    
    /**
     * Set the latitude
     *
     * @param latitude the new latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    /**
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }
    
    /**
     * Set the longitude
     *
     * @param longitude the new longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
