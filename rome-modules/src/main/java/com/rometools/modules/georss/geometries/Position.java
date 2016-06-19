/*
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
 *
 */

package com.rometools.modules.georss.geometries;

import java.io.Serializable;

/**
 * A two dimensional position represented by latitude and longitude decimal degrees in WGS84
 */
public class Position implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;
    private double latitude;
    private double longitude;

    public Position() {
        latitude = Double.NaN;
        longitude = Double.NaN;
    }

    /**
     * Create Position from a pair of coordinate values
     */
    public Position(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }

        final Position p = (Position) obj;
        return p.latitude == latitude && p.longitude == longitude;
    }

    /**
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set the latitude
     *
     * @param latitude the new latitude
     */
    public void setLatitude(final double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set the longitude
     *
     * @param longitude the new longitude
     */
    public void setLongitude(final double longitude) {
        this.longitude = longitude;
    }
}
