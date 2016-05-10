/*
 * DateTimeRange.java
 *
 * Created on November 16, 2005, 11:14 AM
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
package com.rometools.modules.base.types;

import java.util.Date;

/**
 * Represents a time range.
 *
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 * @version $Revision: 1.2 $
 */
public class DateTimeRange implements CloneableType {
    /**
     * end time
     */
    private Date end;
    /**
     * start time
     */
    private Date start;

    /**
     * Creates a new instance of DateTimeRange
     *
     * @param start Beginning of the timeframe.
     * @param end End of the Timeframe.
     */
    public DateTimeRange(final Date start, final Date end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The end of the timeframe.
     *
     * @return The end of the timeframe.
     */
    public Date getEnd() {
        return end;
    }

    /**
     * The beginning of the timeframe.
     *
     * @return The beginning of the timeframe.
     */
    public Date getStart() {
        return start;
    }

    /**
     * Clones the object
     *
     * @return Duplicate of this object.
     */
    @Override
    public Object clone() {
        final DateTimeRange retValue = new DateTimeRange(null, null);

        if (getStart() != null) {
            retValue.start = (Date) getStart().clone();
        }

        if (getEnd() != null) {
            retValue.end = (Date) getEnd().clone();
        }

        return retValue;
    }

    /**
     * String representation of the object.
     *
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        return "Start: " + start + " End: " + end;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof DateTimeRange) || o == null) {
            return false;
        }
        final DateTimeRange d = (DateTimeRange) o;
        if (start == d.getStart() && end == d.getEnd()) {
            return true;
        }
        if (start != null && !start.equals(d.getStart())) {
            return false;
        }
        if (end != null && !end.equals(d.getEnd())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = this.end != null ? this.end.hashCode() : 0;
        result = 31 * result + (this.start != null ? this.start.hashCode() : 0);
        return result;
    }
}
