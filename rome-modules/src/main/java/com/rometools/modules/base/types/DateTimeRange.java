/*
 * Copyright 2005 Robert Cooper, Temple of the Screaming Penguin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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
}
