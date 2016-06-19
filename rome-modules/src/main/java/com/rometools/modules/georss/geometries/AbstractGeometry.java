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
 * Abstract base class for geometries.
 */
public abstract class AbstractGeometry implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    public AbstractGeometry() {
    }

    /**
     * Make a deep copy of the geometric object
     *
     * @return A copy of the object
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj != null && obj.getClass() == getClass();
    }
}
