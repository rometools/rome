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
 * Linear object constructed by linear interpolation between points
 */
public final class LineString extends AbstractCurve {

    private static final long serialVersionUID = 1L;
    private PositionList posList;

    public LineString() {

    }

    /**
     * Construct object from a position list
     */
    public LineString(final PositionList posList) {
        this.posList = posList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        final LineString retval = (LineString) super.clone();
        if (posList != null) {
            retval.posList = (PositionList) posList.clone();
        }
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
        return getPositionList().equals(((LineString) obj).getPositionList());
    }

    /**
     * Get the position list
     *
     * @return the positionlist
     */
    public PositionList getPositionList() {
        if (posList == null) {
            posList = new PositionList();
        }
        return posList;
    }

    /**
     * Set the position list
     *
     * @param posList the new position list
     */
    public void setPositionList(final PositionList posList) {
        this.posList = posList;
    }
}
