/*
 * YearType.java
 *
 * Created on November 17, 2005, 11:42 PM
 *
 * This library is provided under dual licenses.
 * You may choose the terms of the Lesser General Public License or the Apache
 * License at your discretion.
 *
 *  Copyright (C) 2005  Robert Cooper, Temple of the Screaming Penguin
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
package org.rometools.feed.module.base.types;

import java.util.Calendar;
import java.util.Date;


/** This class represents a simple 4 digit year.
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @version $Revision: 1.1 $
 */
public class YearType implements CloneableType {
    /**
     * year value
     */
    int year;

    /**
     * Creates a new year from a string value.
     * @param year String to parse.
     */
    public YearType(String year) {
        this.year = Integer.parseInt(year.trim());
    }

    /**
     * Creates a new instance of YearType
     * @param date Date to get the year from.
     */
    public YearType(Date date) {
        Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	this.year = cal.get( Calendar.YEAR );
    }

    /**
     * Duplicates this object.
     * @return Cloned Year.
     */
    public Object clone() {
        return new YearType(Integer.toString(this.year));
    }

    /**
     * Returns a String representation of this object.
     * @return Returns a String representation of this object.
     */
    public String toString() {
        return Integer.toString(year);
    }
    
    public boolean equals( Object o ){
	if( !(o instanceof YearType))
	    return false;
	if( this.toString().equals( o.toString() ) )
	    return true;
	return false;
    }
}
