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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Polygon, a surface object bounded by one external ring and zero or more internal rings
 */
public final class Polygon extends AbstractSurface implements Cloneable {

    private static final long serialVersionUID = 1L;
    private AbstractRing exterior;
    private List<AbstractRing> interior;

    public Polygon() {

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        final Polygon retval = (Polygon) super.clone();
        if (exterior != null) {
            retval.exterior = (AbstractRing) exterior.clone();
        }
        if (interior != null) {
            retval.interior = new ArrayList<AbstractRing>();
            final Iterator<AbstractRing> it = interior.iterator();
            while (it.hasNext()) {
                final AbstractRing r = it.next();
                retval.interior.add((AbstractRing) r.clone());
            }
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
        final Polygon pol = (Polygon) obj;
        if (exterior == null && pol.exterior == null) {
            ;
        } else if (exterior == null || pol.exterior == null) {
            return false;
        } else if (!exterior.equals(pol.exterior)) {
            return false;
        }

        // Not efficient.... (but the number of internal ringr is usually small).
        Iterator<AbstractRing> it = interior.iterator();
        while (it.hasNext()) {
            if (!pol.interior.contains(it.next())) {
                return false;
            }
        }
        it = pol.interior.iterator();
        while (it.hasNext()) {
            if (!interior.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieve the outer border
     *
     * @return the border ring
     */
    public AbstractRing getExterior() {
        return exterior;
    }

    /**
     * Retrieve the inner border
     *
     * @return the list of border rings
     */
    public List<AbstractRing> getInterior() {
        if (interior == null) {
            interior = new ArrayList<AbstractRing>();
        }
        return interior;
    }

    /**
     * Set the outer border
     *
     * @param exterior the outer ring
     */
    public void setExterior(final AbstractRing exterior) {
        this.exterior = exterior;
    }

    /**
     * Set the list of inner borders (holes)
     *
     * @param interior the list of inner rings
     */
    public void setInterior(final List<AbstractRing> interior) {
        this.interior = interior;
    }
}
