/*
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
 *
 */
package com.rometools.modules.mediarss.types;

import java.io.Serializable;

/**
 * 
 * media:statistics element.
 * 
 * @since MediaRSS 1.5.0
 */
public class Statistics implements Serializable {
    private static final long serialVersionUID = -2184017520632902691L;
    private Integer views;
    private Integer favorites;

    public Integer getViews() {
        return views;
    }

    public void setViews(final Integer views) {
        this.views = views;
    }

    public Integer getFavorites() {
        return favorites;
    }

    public void setFavorites(final Integer favorites) {
        this.favorites = favorites;
    }

    // CHECKSTYLE:OFF
    @Override
    public String toString() {
        return "Statistics [views=" + views + ", favorites=" + favorites + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (favorites == null ? 0 : favorites.hashCode());
        result = prime * result + (views == null ? 0 : views.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Statistics other = (Statistics) obj;
        if (favorites == null) {
            if (other.favorites != null) {
                return false;
            }
        } else if (!favorites.equals(other.favorites)) {
            return false;
        }
        if (views == null) {
            if (other.views != null) {
                return false;
            }
        } else if (!views.equals(other.views)) {
            return false;
        }
        return true;
    }
    // CHECKSTYLE:ON
}
