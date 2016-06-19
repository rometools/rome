/*
 * Copyright 2006 Robert Cooper, Temple of the Screaming Penguin
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

package com.rometools.modules.photocast.types;

import java.math.BigDecimal;
import java.util.Date;

/**
 * This is a specialized Date class for working with the apple PhotoDate format. It provides a
 * constructor taking a dobule value representing the fractional number of days since 00:00:00
 * 01/01/00.
 */
public class PhotoDate extends Date {

    private static final long serialVersionUID = 1L;
    private static final long Y2K = 946616400531l;
    private static final double DAY = 24 * 60 * 60 * 1000;

    public PhotoDate() {
        super();
    }

    /**
     * Creates a new instance of PhotoDate with the timestamp provided.
     *
     * @param time milliseconds time
     */
    public PhotoDate(final long time) {
        super(time);
    }

    /**
     * Creates a new instance of PhotoDate with the fractional number of days since 00:00:00
     * 01/01/00.
     *
     * @param photoDateValue fractional number of days since 00:00:00 01/01/00
     */
    public PhotoDate(final double photoDateValue) {
        BigDecimal d = new BigDecimal(photoDateValue);
        d = d.multiply(new BigDecimal(DAY));
        d = d.add(new BigDecimal(Y2K));
        setTime(d.longValue());
    }

    /**
     * Returns a string representing the fractional number of days since 00:00:00 01/01/00.
     *
     * @return Returns a string representing the fractional number of days since 00:00:00 01/01/00.
     */
    @Override
    public String toString() {
        BigDecimal d = new BigDecimal(getTime());
        d = d.subtract(new BigDecimal(Y2K));
        d = d.multiply(new BigDecimal(1000000));
        d = d.divide(new BigDecimal(DAY), BigDecimal.ROUND_HALF_UP);
        return d.divide(new BigDecimal(1000000), 7, BigDecimal.ROUND_HALF_UP).toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof Date || ((Date) o).getTime() / 1000 == getTime() / 1000) {
            return true;
        } else {
            return false;
        }
    }

}
