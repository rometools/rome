/*
 * Duration.java
 *
 * Created on August 1, 2005, 7:45 PM
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
package org.rometools.feed.module.itunes.types;

import java.text.NumberFormat;
import java.util.StringTokenizer;
import java.io.Serializable;

import com.sun.syndication.io.impl.NumberParser;


/**
 * An encapsulation of the duration of a podcast. This will serialize (via .toString()) 
 * to HH:MM:SS format, and can parse [H]*H:[M]*M:[S]*S or [M]*M:[S]*S.
 * @version $Revision: 1.7 $
 * @author <a href="mailto:cooper@screaming-penguin.com">Robert "kebernet" Cooper</a>
 */
public class Duration implements Serializable {
    static final long SECOND = 1000;
    static final long MINUTE = SECOND * 60;
    static final long HOUR = MINUTE * 60;
    static final NumberFormat NUM_FORMAT = NumberFormat.getNumberInstance();
    static{
        NUM_FORMAT.setMinimumFractionDigits(0);
        NUM_FORMAT.setMaximumFractionDigits(0);
        NUM_FORMAT.setMinimumIntegerDigits(2);
        NUM_FORMAT.setGroupingUsed(false);
    }
    private long milliseconds;

    /**
     * Creates a new Duration object with 0 length.
     */
    public Duration() {
        super();
    }

    /**
     * Creates a new instance of Duration specifying a length in milliseconds
     * @param milliseconds Creates a new instance of Duration specifying a length in milliseconds 
     */
    public Duration(final long milliseconds) {
        this.setMilliseconds(milliseconds);
    }

    /**
     * Creates a new duration object with the given hours, minutes and seconds
     * @param hours number of hours
     * @param minutes number of minutes
     * @param seconds number of seconds
     */
    public Duration(final int hours, final int minutes, final float seconds) {
        this.setMilliseconds((hours * HOUR) + (minutes * MINUTE) +
            (long)(seconds * SECOND));
    }
    
    /**
     * Creates a new Duration parsing the String value.
     * @param duration A String to parse
     */
    public Duration( final String duration ){
        StringTokenizer tok = new StringTokenizer( duration, ":" );
        switch (tok.countTokens() ){
            case 1:
                this.setMilliseconds( (long) (NumberParser.parseFloat(tok.nextToken(), 0f) * SECOND) );
                break;
            case 2:
                this.setMilliseconds(NumberParser.parseLong( tok.nextToken(), 0l ) * MINUTE + (long) (NumberParser.parseFloat(tok.nextToken(), 0f) * SECOND) );
                break;
            case 3:
                this.setMilliseconds(NumberParser.parseLong(tok.nextToken(), 0l) * HOUR + NumberParser.parseLong(tok.nextToken(), 0l) * MINUTE + (long) (NumberParser.parseFloat(tok.nextToken(), 0f) * SECOND) );
                break;
            default:
                throw new RuntimeException("Illegal time value: "+ duration);
        }
    }

    /**
     * Returns a String representation in the formation HH:MM:SS
     * @return Returns a String representation in the formation HH:MM:SS
     */
    public String toString() {
        Time time = new Time();

        
        return NUM_FORMAT.format(time.hours) + ":" + NUM_FORMAT.format(time.minutes) + ":" +
        NUM_FORMAT.format( Math.round(time.seconds) );
    }

    /**
     * Returns the millisecond length
     * @return the millisecond length
     */
    public long getMilliseconds() {
        return milliseconds;
    }

    /**
     * Sets the millisecond length
     * @param milliseconds the millisecond length
     */
    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    private class Time {
        int hours;
        int minutes;
        float seconds;

        public Time() {
            long time = getMilliseconds();
            hours = (int) (time / HOUR);
            time = time - (long) hours * HOUR;
            minutes = (int) (time / MINUTE);
            time = time - (long) minutes * MINUTE;
            seconds = (float) ( (float) time / (float) SECOND);
        }
    }
}
