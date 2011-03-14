/*
 * ShortDate.java
 *
 * Created on November 17, 2005, 1:04 PM
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

import java.util.Date;


/**
 * This is a simple wrapper for java.util.Date that indicates it should be 
 * formatted without time of day for Google Base. It should be transparent to 
 * module developers. 
 * 
 * Move along. Nothing to see here.
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @version $Revision: 1.1 $
 */
public class ShortDate extends Date implements CloneableType {
    /** Creates a new instance of ShortDate */
    public ShortDate() {
        super();
    }

    /**
     * Creates a new Short Date based on a Date value.
     * @param date Date value to read from.
     */
    public ShortDate(Date date) {
        super(date.getTime());
    }

    /**
     * Creates a new ShortDate based on a millisecond time.
     * @param time Millisecond time to start with.
     */
    public ShortDate(long time) {
        super(time);
    }

    /**
     * Returns a duplicate of this object.
     * @return Duplicate of the object.
     */
    public Object clone() {
        return new ShortDate(this.getTime());
    }
}
