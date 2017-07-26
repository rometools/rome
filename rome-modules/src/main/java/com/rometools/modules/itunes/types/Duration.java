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
package com.rometools.modules.itunes.types;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.StringTokenizer;

import com.rometools.rome.io.impl.NumberParser;

/**
 * An encapsulation of the duration of a podcast. This will serialize (via .toString()) to HH:MM:SS
 * format, and can parse [H]*H:[M]*M:[S]*S or [M]*M:[S]*S.
 */
public class Duration implements Serializable {

    private static final long serialVersionUID = 1L;
    static final long SECOND = 1000;
    static final long MINUTE = SECOND * 60;
    static final long HOUR = MINUTE * 60;
    static final NumberFormat NUM_FORMAT = NumberFormat.getNumberInstance();
    static {
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
     *
     * @param milliseconds Creates a new instance of Duration specifying a length in milliseconds
     */
    public Duration(final long milliseconds) {
        setMilliseconds(milliseconds);
    }

    /**
     * Creates a new duration object with the given hours, minutes and seconds
     *
     * @param hours number of hours
     * @param minutes number of minutes
     * @param seconds number of seconds
     */
    public Duration(final int hours, final int minutes, final float seconds) {
        setMilliseconds(hours * HOUR + minutes * MINUTE + (long) (seconds * SECOND));
    }

    /**
     * Creates a new Duration parsing the String value.
     *
     * @param duration A String to parse
     */
    public Duration(final String duration) {
        final StringTokenizer tok = new StringTokenizer(duration, ":");
        switch (tok.countTokens()) {
            case 1:
                setMilliseconds((long) (NumberParser.parseFloat(tok.nextToken(), 0f) * SECOND));
                break;
            case 2:
                setMilliseconds(NumberParser.parseLong(tok.nextToken(), 0l) * MINUTE + (long) (NumberParser.parseFloat(tok.nextToken(), 0f) * SECOND));
                break;
            case 3:
                setMilliseconds(NumberParser.parseLong(tok.nextToken(), 0l) * HOUR + NumberParser.parseLong(tok.nextToken(), 0l) * MINUTE
                        + (long) (NumberParser.parseFloat(tok.nextToken(), 0f) * SECOND));
                break;
            default:
                throw new RuntimeException("Illegal time value: " + duration);
        }
    }

    /**
     * Returns a String representation in the formation HH:MM:SS
     *
     * @return Returns a String representation in the formation HH:MM:SS
     */
    @Override
    public String toString() {
        final Time time = new Time();

        return NUM_FORMAT.format(time.hours) + ":" + NUM_FORMAT.format(time.minutes) + ":" + NUM_FORMAT.format(Math.round(time.seconds));
    }

    /**
     * Returns the millisecond length
     *
     * @return the millisecond length
     */
    public long getMilliseconds() {
        return milliseconds;
    }

    /**
     * Sets the millisecond length
     *
     * @param milliseconds the millisecond length
     */
    public void setMilliseconds(final long milliseconds) {
        this.milliseconds = milliseconds;
    }

    private class Time {
        int hours;
        int minutes;
        float seconds;

        public Time() {
            long time = getMilliseconds();
            hours = (int) (time / HOUR);
            time = time - hours * HOUR;
            minutes = (int) (time / MINUTE);
            time = time - minutes * MINUTE;
            seconds = (float) time / (float) SECOND;
        }
    }
}
