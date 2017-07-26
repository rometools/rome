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
 * A list of geographic positions, latitude, longitude decimal degrees WGS84
 */
public class PositionList implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;
    private double[] latitude;
    private double[] longitude;
    private int size;

    /** Creates a new empty instance of PositionList */
    public PositionList() {
        size = 0;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        final PositionList retval = (PositionList) super.clone();
        if (latitude != null) {
            retval.latitude = latitude.clone();
        }
        if (longitude != null) {
            retval.longitude = longitude.clone();
        }
        retval.size = size;
        return retval;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }

        final PositionList p = (PositionList) obj;
        if (p.size != size) {
            return false;
        }
        for (int i = 0; i < size; ++i) {
            if (p.latitude[i] != latitude[i] || p.longitude[i] != longitude[i]) {
                return false;
            }
        }
        return true;
    }

    private void ensureCapacity(int new_size) {
        if (longitude != null && longitude.length >= new_size) {
            return;
        }
        if (new_size < 4) {
            new_size = 4;
        } else {
            new_size = (int) Math.ceil(Math.pow(2, Math.ceil(Math.log(new_size) / Math.log(2))));
        }
        double[] tmp = new double[new_size];
        if (longitude != null) {
            System.arraycopy(longitude, 0, tmp, 0, size);
        }
        longitude = tmp;
        tmp = new double[new_size];
        if (latitude != null) {
            System.arraycopy(latitude, 0, tmp, 0, size);
        }
        latitude = tmp;
    }

    /**
     * @return the number of positions in the list
     */
    public int size() {
        return size;
    }

    /**
     * @param pos position index
     * @return longitude for position
     */
    public double getLongitude(final int pos) {
        return longitude[pos];
    }

    /**
     * @param pos position index
     * @return latitude for position
     */
    public double getLatitude(final int pos) {
        return latitude[pos];
    }

    /**
     * Add a position at the end of the list
     */
    public void add(final double latitude, final double longitude) {
        ensureCapacity(size + 1);
        this.longitude[size] = longitude;
        this.latitude[size] = latitude;
        ++size;
    }

    /**
     * Add a position at a given index in the list. The rest of the list is shifted one place to the
     * "right"
     *
     * @param pos position index
     */
    public void insert(final int pos, final double latitude, final double longitude) {
        ensureCapacity(size + 1);
        System.arraycopy(this.longitude, pos, this.longitude, pos + 1, size - pos);
        System.arraycopy(this.latitude, pos, this.latitude, pos + 1, size - pos);
        this.longitude[pos] = longitude;
        this.latitude[pos] = latitude;
        ++size;
    }

    /**
     * Replace the position at the index with new values
     *
     * @param pos position index
     */
    public void replace(final int pos, final double latitude, final double longitude) {
        this.longitude[pos] = longitude;
        this.latitude[pos] = latitude;
    }

    /**
     * Remove the position at the index, the rest of the list is shifted one place to the "left"
     *
     * @param pos position index
     */
    public void remove(final int pos) {
        System.arraycopy(longitude, pos + 1, longitude, pos, size - pos - 1);
        System.arraycopy(latitude, pos + 1, latitude, pos, size - pos - 1);
        --size;
    }
}
