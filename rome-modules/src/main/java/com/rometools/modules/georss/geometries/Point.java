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
 * Point object, contains a position
 */
public final class Point extends AbstractGeometricPrimitive {

    private static final long serialVersionUID = 1L;
    private Position pos;

    public Point() {

    }

    public Point(final Position pos) {
        this.pos = pos;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        final Point retval = (Point) super.clone();
        if (pos != null) {
            retval.pos = (Position) pos.clone();
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
        return getPosition().equals(((Point) obj).getPosition());
    }

    /**
     * Get the position
     *
     * @return the position
     */
    public Position getPosition() {
        if (pos == null) {
            pos = new Position();
        }
        return pos;
    }

    /**
     * Set the position
     *
     * @param pos the new position
     */
    public void setPosition(final Position pos) {
        this.pos = pos;
    }
}
