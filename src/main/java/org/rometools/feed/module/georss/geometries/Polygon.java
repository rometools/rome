/*
 * Polygon.java
 *
 * Created on 8. februar 2007, 10:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.rometools.feed.module.georss.geometries;

import java.util.*;

/**
 * Polygon, a surface object bounded by one external ring and zero or more internal rings 
 * @author runaas
 */
public final class Polygon extends AbstractSurface implements Cloneable {
    private AbstractRing exterior;
    private List         interior;
    
    /** Creates a new instance of Polygon */
    public Polygon() {
        
    }
    
    public Object clone() throws CloneNotSupportedException {
        Polygon retval  = (Polygon)super.clone();
        if (exterior != null)
             retval.exterior = (AbstractRing)exterior.clone();
         if (interior != null) {
             retval.interior = new ArrayList();
             Iterator it = interior.iterator();
             while (it.hasNext()) {
                 AbstractRing r = (AbstractRing)it.next();
                 retval.interior.add(r.clone());
             }
         }
         return retval;
     }
    
     public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        Polygon pol = (Polygon)obj;
        if (exterior == null && pol.exterior == null)
            ;
        else if (exterior == null || pol.exterior == null)
            return false;
        else if (!exterior.equals(pol.exterior))
            return false;
       
        // Not efficient.... (but the number of internal ringr is usually small).
        Iterator it = interior.iterator();
        while (it.hasNext()) 
            if (!pol.interior.contains(it.next()))
                return false;
        it = pol.interior.iterator();
        while (it.hasNext()) 
            if (!interior.contains(it.next()))
                return false;
        return true;
    }
    
    /**
     * Retrieve the outer border
     *
     * @return the border ring
     */
    public AbstractRing getExterior() {
        return exterior;
    }
    
    /**
     * Retrieve the inner border
     *
     * @return the list of border rings
     */
    public List getInterior() {
        if (interior == null)
            interior = new ArrayList();
        return interior;
    }
    
    /**
     * Set the outer border
     *
     * @param exterior the outer ring
     */
    public void setExterior(AbstractRing exterior) {
        this.exterior = exterior;
    }
    
    /**
     * Set the list of inner borders (holes)
     *
     * @param interior the list of inner rings
     */
    public void setInterior(List interior) {
        this.interior = interior;
    }
}
