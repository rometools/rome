/*
 * Rating.java
 *
 * Created on April 18, 2006, 7:58 PM
 *
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
 */
package com.rometools.modules.mediarss.types;

import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

/**
 * <strong>&lt;media:rating&gt;</strong></p>
 *
 *
 * <p>
 * This allows the permissible audience to be declared. If this element is not included, it assumes
 * that no restrictions are necessary. It has one optional attribute.
 * </p>
 *
 * <pre>
 * &lt;media:rating scheme="urn:simple"&gt;adult&lt;/media:rating&gt;
 *               &lt;media:rating scheme="urn:icra"&gt;r (cz 1 lz 1 nz 1 oz 1 vz 1)&lt;/media:rating&gt;
 *               &lt;media:rating scheme="urn:mpaa"&gt;pg&lt;/media:rating&gt;
 *
 *               &lt;media:rating scheme="urn:v-chip"&gt;tv-y7-fv&lt;/media:rating&gt;
 * </pre>
 *
 *
 * <p>
 * <em>scheme</em> is the URI that identifies the rating scheme. It is an optional attribute. If
 * this attribute is not included, the default scheme is urn:simple (adult | nonadult).
 * </p>
 *
 * For compatibility, a medai:adult tag will appear in the ratings as a urn:simple equiv.
 */
public class Rating extends AbstractSchemeValue {
    private static final long serialVersionUID = 1L;

    /**
     * urn:simple adult. This will be populated on the deprecated media:adult tag as well,
     */
    public static final Rating ADULT = new Rating("urn:simple", "adult");

    /**
     * urn:simple nonadult. This will be populated on the deprecated media:adult tag as well,
     */
    public static final Rating NONADULT = new Rating("urn:simple", "nonadult");

    /**
     * @param scheme scheme used for the rating
     * @param value value of the rating.
     */
    public Rating(final String scheme, final String value) {
        super(scheme, value);
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBean.beanEquals(this.getClass(), this, obj);
    }

    @Override
    public int hashCode() {
        return EqualsBean.beanHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBean.toString(this.getClass(), this);
    }
}
