/*
 * PositionList.java
 *
 * Created on 8. februar 2007, 11:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.rometools.feed.module.georss.geometries;

import java.io.Serializable;

/**
 * A list of geographic positions, latitude, longitude decimal degrees WGS84
 * @author runaas
 */
public class PositionList implements Cloneable, Serializable {
    private double [] latitude;
    private double [] longitude;
    private int size;
    
    /** Creates a new empty instance of PositionList */
    public PositionList() {
        size = 0;
    }
    
    public Object clone() throws CloneNotSupportedException {
        PositionList retval  = (PositionList)super.clone();
        if (latitude != null)
            retval.latitude = (double [])(latitude.clone());
        if (longitude != null)
            retval.longitude = (double [])(longitude.clone());
        retval.size = size;
        return retval;
    }
    
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        
        PositionList p = (PositionList)obj;
        if (p.size != size)
            return false;
        for (int i=0; i<size; ++i)
            if (p.latitude[i] != latitude[i] || p.longitude[i] != longitude[i])
                return false;
        return true;
    }
    
    private void ensureCapacity(int new_size) {
        if (longitude != null && longitude.length >= new_size)
            return;
        if (new_size < 4)
            new_size = 4;
        else
            new_size = (int)Math.ceil(Math.pow(2, Math.ceil(Math.log(new_size)/Math.log(2))));
        double [] tmp = new double[new_size];
        if (longitude != null)
            System.arraycopy(longitude, 0, tmp, 0, size);
        longitude = tmp;
        tmp = new double[new_size];
        if (latitude != null)
            System.arraycopy(latitude, 0, tmp, 0, size);
        latitude = tmp;
    }
    
    /**
     * @return the number of positions in the list
     */
    public int size() {
        return size;
    }
    
    /**
     * @param pos position index
     * @return longitude for position
     */
    public double getLongitude(int pos) {
        return longitude[pos];
    }
    
    /**
     * @param pos position index
     * @return latitude for position
     */
    public double getLatitude(int pos) {
        return latitude[pos];
    }
    
    /**
     * Add a position at the end of the list
     * @param latitude
     * @param longitude
     */
    public void add(double latitude, double longitude) {
        ensureCapacity(size+1);
        this.longitude[size] = longitude;
        this.latitude[size]  = latitude;
        ++size;
    }
    
    /**
     * Add a position at a given index in the list. The rest of the list is
     * shifted one place to the "right"
     *
     * @param pos position index
     * @param latitude
     * @param longitude
     */
    public void insert(int pos, double latitude, double longitude) {
        ensureCapacity(size+1);
        System.arraycopy(this.longitude, pos, this.longitude, pos+1, size-pos);
        System.arraycopy(this.latitude,  pos, this.latitude,  pos+1, size-pos);
        this.longitude[pos] = longitude;
        this.latitude[pos]  = latitude;
        ++size;
    }
    
    /**
     * Replace the position at the index with new values
     * 
     * @param pos position index
     * @param latitude
     * @param longitude
     */
    public void replace(int pos, double latitude, double longitude) {
        this.longitude[pos] = longitude;
        this.latitude[pos]  = latitude;
    }
    
    /**
     * Remove the position at the index, the rest of the list is shifted one place to the "left"
     * 
     * @param pos position index
     */
    public void remove(int pos) {
        System.arraycopy(longitude, pos+1, longitude, pos, size-pos-1);
        System.arraycopy(latitude,  pos+1, latitude,  pos, size-pos-1);
        --size;
    }
}
