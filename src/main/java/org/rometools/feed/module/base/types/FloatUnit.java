/*
 * FloatUnit.java
 *
 * Created on November 16, 2005, 11:43 AM
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

import org.rometools.feed.module.base.io.GoogleBaseParser;


/** This class represents a quantity consisting of a float value and an optional
 * units specification.
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @version $Revision: 1.1 $
 */
public class FloatUnit implements CloneableType {
    /**
     * Units
     */
    private String units;
    /**
     * Float value
     */
    private float value;

    /**
     * Looks for a char in an array
     * @param find char to search for
     * @param array array to search
     * @return boolean indicating presence.
     */
    private boolean inCharArray( char find, char[] array ){
        for( int i=0; i < array.length; i++ ){
            if( find == array[i])
                return true;
        }
        return false;
    }
    
    /**
     * Creates a new float unit by parsing a String value
     * @param source String value to parse
     */
    public FloatUnit(String source) {
        String parse = source.trim();
        int space = -1;
        for( int i=0; i < parse.length(); i++ ){
            if( !inCharArray( parse.charAt(i), GoogleBaseParser.FLOAT_CHARS ) ){
                space = i;
                break;
            }
        }

        if (space == -1) {
            space = parse.length();
        }

        this.value = Float.parseFloat(
                GoogleBaseParser.stripNonValidCharacters(GoogleBaseParser.FLOAT_CHARS, parse.substring(0, space)));

         if (space != parse.length()) {
            this.units = parse.substring(space, parse.length()).trim();
        }       
    }

    /**
     * Creates a new instance of FloatUnit
     * @param value float value
     * @param units Units represented, or null.
     */
    public FloatUnit(float value, String units) {
        this.value = value;
        this.units = units;
    }

    /**
     * Returns the units.
     * @return Returns the units.
     */
    public String getUnits() {
        return units;
    }

    /**
     * Returns the float value.
     * @return Returns the float value.
     */
    public float getValue() {
        return value;
    }

    /**
     * Duplicates the object.
     * @return Duplicate FloatUnit
     */
    public Object clone() {
        return new FloatUnit(0 + this.value, this.units);
    }

    /**
     * Returns a String representation of the object.
     * @return Returns a String representation of the object.
     */
    public String toString() {
        if ((this.units != null) && (this.units.trim().length() > 0)) {
            return this.value + " " + this.units;
        } else {
            return Float.toString(value);
        }
    }
    
    public boolean equals( Object o ){
	if(!(o instanceof FloatUnit) )
	    return false;
	FloatUnit f = (FloatUnit) o;
	if( f.getValue() != this.value ){
	    return false;	    
	}
	if( this.units == f.getUnits() || ( this.units != null && this.units.equals( f.getUnits() )) ){
	    return true;
	}
	return false;
    }
}
