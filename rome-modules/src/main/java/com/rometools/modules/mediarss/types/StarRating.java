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
 * This element specifies the rating-related information about a media object.
 * 
 * @since MediaRSS 1.5.0
 */
public class StarRating implements Serializable {
    private static final long serialVersionUID = -6807718323210492980L;
    private Double average;
    private Integer count;
    private Integer min;
    private Integer max;

    public Double getAverage() {
        return average;
    }

    public void setAverage(final Double average) {
        this.average = average;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(final Integer count) {
        this.count = count;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(final Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(final Integer max) {
        this.max = max;
    }

    //CHECKSTYLE:OFF
    @Override
    public String toString() {
        return "StarRating [average=" + average + ", count=" + count + ", min=" + min + ", max=" + max + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (average == null ? 0 : average.hashCode());
        result = prime * result + (count == null ? 0 : count.hashCode());
        result = prime * result + (max == null ? 0 : max.hashCode());
        result = prime * result + (min == null ? 0 : min.hashCode());
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
        final StarRating other = (StarRating) obj;
        if (average == null) {
            if (other.average != null) {
                return false;
            }
        } else if (!average.equals(other.average)) {
            return false;
        }
        if (count == null) {
            if (other.count != null) {
                return false;
            }
        } else if (!count.equals(other.count)) {
            return false;
        }
        if (max == null) {
            if (other.max != null) {
                return false;
            }
        } else if (!max.equals(other.max)) {
            return false;
        }
        if (min == null) {
            if (other.min != null) {
                return false;
            }
        } else if (!min.equals(other.min)) {
            return false;
        }
        return true;
    }
    //CHECKSTYLE:ON
}