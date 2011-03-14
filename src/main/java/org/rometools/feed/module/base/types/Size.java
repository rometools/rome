/*
 * Size.java
 *
 * Created on November 16, 2005, 1:51 PM
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

import java.util.StringTokenizer;


/** Represents the size on an item in 2 or 3 dimensions.
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @version $Revision: 1.2 $
 */
public class Size implements CloneableType {
    /**
     * height
     */
    private FloatUnit height;
    /**
     * length
     */
    private FloatUnit length;
    /**
     * width
     */
    private FloatUnit width;

    /**
     * Creates a new Size object parsing a string value.
     * @param source String value to parse
     */
    public Size(String source) {
        StringTokenizer tok = new StringTokenizer(source, "Xx");
        this.length = new FloatUnit( tok.nextToken());
        this.width = new FloatUnit( tok.nextToken());

        if (tok.hasMoreTokens()) {
            this.height = new FloatUnit( tok.nextToken());
        }
    }

    /**
     * Creates a new instance of Size
     * @param length lenght value
     * @param width width value
     */
    public Size(FloatUnit length, FloatUnit width) {
        this.length = length;
        this.width = width;
    }

    /**
     * Creates a new instance of Size.
     * @param length Length value.
     * @param width Width value.
     * @param height Height value.
     */
    public Size(FloatUnit length, FloatUnit width, FloatUnit height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    /**
     * Height value.
     * @return Height value.
     */
    public FloatUnit getHeight() {
        return height;
    }

    /**
     * Length value.
     * @return Length value.
     */
    public FloatUnit getLength() {
        return length;
    }

    /**
     * Width value.
     * @return Width value.
     */
    public FloatUnit getWidth() {
        return width;
    }

    /**
     * Duplicates this object.
     * @return A duplicate Size object.
     */
    public Object clone() {
        if (this.height != null) {
            return new Size(this.length, this.width, this.height);
        } else {
            return new Size(this.length, this.width);
        }
    }

    /**
     * Returns a string representation of this object.
     * @return A string representation of this object.
     */
    public String toString() {
        if (height != null) {
            return length + "x" + width + "x" + height;
        } else {
            return length + "x" + width;
        }
    }
    public boolean equals( Object o ){
	if( !(o instanceof Size))
	    return false;
	if( this.toString().equals( o.toString() ) )
	    return true;
	return false;
    }
}
