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

/**
 * Envelope, a bounding box spanned by an upper right and lower left corner point. Note that if the
 * box spans the -180 180 degree meridian the numerical value of the minLongitude may be greater
 * than the maxLongitude.
 */
public class Envelope extends AbstractGeometry {

    private static final long serialVersionUID = 1L;
    protected double minLatitude, minLongitude, maxLatitude, maxLongitude;

    public Envelope() {
        minLatitude = minLongitude = maxLatitude = maxLongitude = Double.NaN;
    }

    /**
     * Construct object from coordinate values
     */
    public Envelope(final double minLatitude, final double minLongitude, final double maxLatitude, final double maxLongitude) {
        this.minLatitude = minLatitude;
        this.minLongitude = minLongitude;
        this.maxLatitude = maxLatitude;
        this.maxLongitude = maxLongitude;
    }

    /**
     * @return the minimum longitude
     */
    public double getMinLongitude() {
        return minLongitude;
    }

    /**
     * @return the minimum latitude
     */
    public double getMinLatitude() {
        return minLatitude;
    }

    /**
     * @return the maximum longitude
     */
    public double getMaxLongitude() {
        return maxLongitude;
    }

    /**
     * @return the maximum latitude
     */
    public double getMaxLatitude() {
        return maxLatitude;
    }

    /**
     * @param v minimum longitude
     */
    public void setMinLongitude(final double v) {
        minLongitude = v;
    }

    /**
     * @param v minimum latitude
     */
    public void setMinLatitude(final double v) {
        minLatitude = v;
    }

    /**
     * @param v maximum longitude
     */
    public void setMaxLongitude(final double v) {
        maxLongitude = v;
    }

    /**
     * @param v maximum latitude
     */
    public void setMaxLatitude(final double v) {
        maxLatitude = v;
    }
}
