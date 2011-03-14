/*
 * Time.java
 *
 * Created on April 18, 2006, 9:48 PM
 *
 * This code is currently released under the Mozilla Public License.
 * http://www.mozilla.org/MPL/
 *
 * Alternately you may apply the terms of the Apache Software License
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
package org.rometools.feed.module.mediarss.types;

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;

import java.io.Serializable;
import java.text.NumberFormat;


/**
 * Represents a <a href="http://www.ietf.org/rfc/rfc2326.txt">RFC 2326 3.6 Normal Play Time</a> timestamp.
 * @author cooper
 */
public class Time implements Serializable {
	private static final long serialVersionUID = 4088522049885593073L;

	private static final long SECOND = 1000;
    private static final long MINUTE = 60 * SECOND;
    private static final long HOUR = 60 * MINUTE * SECOND;
    private static final NumberFormat nf = NumberFormat.getInstance();

    static {
        nf.setMinimumIntegerDigits(2);
    }

    private long milliseconds = 0;

    /**
     * Creates a new instance of Time
     * @param milliseconds milliseconds in length or offset.
     */
    public Time(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    /**
     * Creates a new instance of Time
     * @param value <a href="http://www.ietf.org/rfc/rfc2326.txt">RFC 2326 3.6 Normal Play Time</a> value
     */
    public Time(String value) {
        String[] values = value.split(":");
        int count = values.length - 1;
        this.milliseconds = (long) (Double.parseDouble(values[count]) * (double) SECOND);
        count--;

        if (count >= 0) {
            milliseconds += (Long.parseLong(values[count]) * MINUTE);
            count--;
        }

        if (count >= 0) {
            milliseconds += (Long.parseLong(values[count]) * HOUR);
        }
    }

    public long getValue() {
        return milliseconds;
    }

    public boolean equals(Object obj) {
        EqualsBean eBean = new EqualsBean(this.getClass(), this);

        return eBean.beanEquals(obj);
    }

    public int hashCode() {
        EqualsBean equals = new EqualsBean(this.getClass(), this);

        return equals.beanHashCode();
    }

    public String toString() {
        long value = this.milliseconds;
        long hours = value / HOUR;
        value -= (hours * HOUR);

        long minutes = value / MINUTE;
        value -= (minutes * MINUTE);

        double seconds = (double) value / (double) SECOND;

        return nf.format(hours) + ":" + nf.format(minutes) + ":" + seconds;
    }
}
