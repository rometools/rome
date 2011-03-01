/*
 * TreeCategoryImpl.java
 *
 * Created on April 27, 2006, 3:44 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.sun.syndication.feed.synd.impl;

import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndCategoryImpl;

/**
 *
 * @author cooper
 */
public class TreeCategoryImpl extends SyndCategoryImpl {
    
    /** Creates a new instance of TreeCategoryImpl */
    public TreeCategoryImpl() {
        super();
    }
    
    public boolean equals(Object o) {
        SyndCategory c = (SyndCategory) o;
        if( c.getTaxonomyUri() != null && c.getTaxonomyUri().equals( this.getTaxonomyUri() ) )
            return true;
        else
            return false;
    }
    
}
