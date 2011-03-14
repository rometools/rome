/*
 * Geometry.java
 *
 * Created on 8. februar 2007, 10:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.rometools.feed.module.georss.geometries;

import java.io.Serializable;

/**
 * Abstract base class for geometries.
 *
 * @author runaas
 */
public abstract class AbstractGeometry implements Cloneable, Serializable {
    
    /** Creates a new instance of Geometry */
    public AbstractGeometry() {
    }
    
    /**
     * Make a deep copy of the geometric object
     * @return A copy of the object
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
     public boolean equals(Object obj) {
         return obj != null && obj.getClass() == getClass();
     }
}
