/*
 * LinearLing.java
 *
 * Created on 8. februar 2007, 11:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.rometools.feed.module.georss.geometries;

/**
 * Linear boundary object constructed by linear interpolation between points.
 * Start and end point should be identical.
 * @author runaas
 */
public final class LinearRing extends AbstractRing {
    private PositionList posList;
    
    /** Creates a new instance of LinearLing */
    public LinearRing() {
    }
    
    public LinearRing(PositionList posList) {
        this.posList = posList;
    }
    
    public Object clone() throws CloneNotSupportedException {
        LinearRing retval = (LinearRing)super.clone();
        if (posList != null)
            retval.posList = (PositionList)posList.clone();
        return retval;
    }
    
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        return getPositionList().equals(((LinearRing)obj).getPositionList());
    }
    
    /**
     * Get the position list
     *
     * @return the positionlist
     */
    public PositionList getPositionList() {
        if (posList == null)
            posList = new PositionList();
        return posList;
    }
    
    /**
     * Set the position list
     *
     * @param posList the new position list
     */
    public void setPositionList(PositionList posList) {
        this.posList = posList;
    }
}
