/*
 * PhotoDate.java
 *
 * Created on March 30, 2006, 5:53 PM
 *
  *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2006  Robert Cooper, Temple of the Screaming Penguin
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.rometools.feed.module.photocast.types;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This is a specialized Date class for working with the apple PhotoDate format.
 * It provides a constructor taking a dobule value representing the fractional 
 * number of days since 00:00:00 01/01/00.
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class PhotoDate extends Date{
    
    private static final long Y2K =  946616400531l;
    private static final double DAY = 24 * 60 * 60 * 1000;
    /** Creates a new instance of PhotoDate */
    public PhotoDate() {
        super();
    }
    
    /**
     * Creates a new instance of PhotoDate with the timestamp provided.
     * @param time milliseconds time
     */
    public PhotoDate( long time ){
        super( time );
    }
    
    /**
     * Creates a new instance of PhotoDate with the fractional 
     * number of days since 00:00:00 01/01/00.
     * @param photoDateValue fractional number of days since 00:00:00 01/01/00
     */
    public PhotoDate( double photoDateValue ){
        BigDecimal d = new BigDecimal(photoDateValue);
        d = d.multiply( new BigDecimal( DAY ) );
        d = d.add( new BigDecimal( Y2K) );
        this.setTime( d.longValue() );
    }
    
    /**
     * Returns a string representing the fractional 
     * number of days since 00:00:00 01/01/00.
     * @return Returns a string representing the fractional 
     * number of days since 00:00:00 01/01/00.
     */
    public String toString(){
        BigDecimal d = new BigDecimal( this.getTime() );  
        d = d.subtract( new BigDecimal( Y2K) );     
        d = d.multiply( new BigDecimal( 1000000 ) );
        d = d.divide( new BigDecimal(DAY) , BigDecimal.ROUND_HALF_UP);
        return d.divide( new BigDecimal(1000000), 7, BigDecimal.ROUND_HALF_UP).toString();
    }
    
    public boolean equals( Object o ){
        if( o instanceof Date || ((Date)o).getTime()/1000 == this.getTime()/1000 )
            return true;
        else
            return false;
    }
   
    
}
