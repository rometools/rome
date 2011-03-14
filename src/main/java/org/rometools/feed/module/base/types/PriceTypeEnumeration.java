/*
 * PriceTypeEnumeration.java
 *
 * Created on November 16, 2005, 11:45 AM
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



/**
 * Enumeration of values suitable for "price_type" or "salary_type".
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @version $Revision: 1.1 $
 */
public class PriceTypeEnumeration implements CloneableType {
    /**
     * Indicates the value is a starting value.
     */
    public static final PriceTypeEnumeration STARTING = new PriceTypeEnumeration("starting");
    /**
     * Indicates the value is negotiable
     */
    public static final PriceTypeEnumeration NEGOTIABLE = new PriceTypeEnumeration("negotiable");
    /**
     * String value encapsulated
     */
    private String value;

    /**
     * Creates a new instance of PriceTypeEnumeration
     * @param value Value to encapsulate
     */
    private PriceTypeEnumeration(String value) {
        this.value = value;
    }

    /**
     * Returns the string value of this instance.
     * @return Returns the string value of this instance.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns a PriceTypeEnumeration based on the String value or null.
     * @param value Value to search for.
     * @return PriceTypeEnumeration or null.
     */
    public static PriceTypeEnumeration findByValue(String value) {
        if (value.equalsIgnoreCase("negotiable")) {
            return PriceTypeEnumeration.NEGOTIABLE;
        } else {
            return PriceTypeEnumeration.STARTING;
        }
    }

    /**
     * Returns a duplicate of this instance
     * @return The same instance.
     */
    public Object clone() {
        return this;
    }

    /**
     * Returns the string value of this instance.
     * @return Returns the string value of this instance.
     */
    public String toString() {
        return value;
    }
}
