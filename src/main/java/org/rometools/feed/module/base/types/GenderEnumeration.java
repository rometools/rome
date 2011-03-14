/*
 * GenderEnumeration.java
 *
 * Created on November 16, 2005, 2:35 PM
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

/** Simple enumeration for Genders.
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @version $Revision: 1.1 $
 */
public class GenderEnumeration implements CloneableType {
    /** Men */
    public static final GenderEnumeration MALE = new GenderEnumeration("Male");
    /** Women */
    public static final GenderEnumeration FEMALE = new GenderEnumeration("Female");
    private String value;

    private GenderEnumeration(String value) {
        this.value = value;
    }

    /** Returns the proper instance based on the string value */
    public static GenderEnumeration findByValue(String value) {
        if (value == null) {
            return null;
        }

        String gender = value.toUpperCase();

        if (gender.charAt(0) == 'M') {
            return GenderEnumeration.MALE;
        } else if (gender.charAt(0) == 'F') {
            return GenderEnumeration.FEMALE;
        } else {
            return null;
        }
    }

    /** Returns the value of the instance */
    public String getValue() {
        return this.value;
    }
    
    /** Returns a reference to the same object. :P */
    public Object clone() {
        return this;
    }

    public String toString() {
        return value;
    }
}
