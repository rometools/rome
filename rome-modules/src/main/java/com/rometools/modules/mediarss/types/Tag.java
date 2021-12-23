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
 * optionally weighted tag in media:tags.
 * 
 * @since MediaRSS 1.5.0
 */
public class Tag implements Serializable, Comparable<Tag> {
    private static final long serialVersionUID = 6410023938827034872L;
    private final String name;
    private Integer weight;

    /**
     * @param name the tag name
     */
    public Tag(final String name) {
        this(name, null);
    }

    /**
     * @param name the tag name
     * @param weight the weight of the tag
     */
    public Tag(final String name, final Integer weight) {
        super();
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(final Integer weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(final Tag o) {
        // weight is 1 when not set
        Integer thisWeight = 1;
        if (weight != null) {
            thisWeight = weight;
        }
        Integer otherWeight = 1;
        if (o != null && o.weight != null) {
            otherWeight = o.weight;
        }
        // revert comparision, highest values first
        return otherWeight.compareTo(thisWeight);
    }

    //CHECKSTYLE:OFF
    @Override
    public String toString() {
        return "Tag [name=" + name + ", weight=" + weight + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (weight == null ? 0 : weight.hashCode());
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
        final Tag other = (Tag) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (weight == null) {
            if (other.weight != null) {
                return false;
            }
        } else if (!weight.equals(other.weight)) {
            return false;
        }
        return true;
    }
    //CHECKSTYLE:ON
}
