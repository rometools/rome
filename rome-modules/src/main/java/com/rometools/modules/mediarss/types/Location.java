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

import com.rometools.modules.georss.GeoRSSModule;

/**
 * Optional tag to include location information about a media object.
 * 
 * @since MediaRSS 1.5.0
 */
public class Location implements Serializable {

    private static final long serialVersionUID = 2899286634307076735L;
    private String description;
    private Time start;
    private Time end;
    private GeoRSSModule geoRss;

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(final Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(final Time end) {
        this.end = end;
    }

    public GeoRSSModule getGeoRss() {
        return geoRss;
    }

    public void setGeoRss(final GeoRSSModule geoRss) {
        this.geoRss = geoRss;
    }

    // CHECKSTYLE:OFF
    @Override
    public String toString() {
        return "Location [description=" + description + ", start=" + start + ", end=" + end + ", geoRss=" + geoRss + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (end == null ? 0 : end.hashCode());
        result = prime * result + (geoRss == null ? 0 : geoRss.hashCode());
        result = prime * result + (start == null ? 0 : start.hashCode());
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
        final Location other = (Location) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (end == null) {
            if (other.end != null) {
                return false;
            }
        } else if (!end.equals(other.end)) {
            return false;
        }
        if (geoRss == null) {
            if (other.geoRss != null) {
                return false;
            }
        } else if (!geoRss.equals(other.geoRss)) {
            return false;
        }
        if (start == null) {
            if (other.start != null) {
                return false;
            }
        } else if (!start.equals(other.start)) {
            return false;
        }
        return true;
    }
    // CHECKSTYLE:ON

}
