/*
 * Restriction.java
 *
 * Created on April 19, 2006, 12:17 AM
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
package org.rometools.feed.module.mediarss.types;

import java.io.Serializable;

import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ToStringBean;


/**
 * <strong>&lt;media:restriction&gt; </strong></p>
 *
 * <p>Allows restrictions to be placed on the aggregator rendering the media in the feed. Currently, restrictions are based on distributor (uri) and country codes.  This element is purely informational and no obligation can be assumed or implied.
 * Only one &lt;media:restriction&gt; element of the same <em>type</em> can be applied to a media object - all others will be ignored.&nbsp;Entities in this element should be space separated. To allow the producer to explicitly declare his/her intentions, two literals are reserved: 'all', 'none'. These literals can only be used once. This element has 1 required attribute, and 1 optional attribute (with strict requirements for its exclusion).</p>
 *
 * <pre>        &lt;media:restriction relationship="allow" type="country"&gt;au us&lt;/media:restriction&gt;</pre>
 *
 * <p><em>relationship</em> indicates the type of relationship that the restriction represents (allow | deny). In the example above, the media object should only be syndicated in Australia and the United States. It is a required attribute.</p>
 *
 * <p><strong>Note:</strong> If the "allow" element is empty and the type is relationship is "allow", it is assumed that the empty list means "allow nobody" and the media should not be syndicated.</p>
 * <p>A more explicit method would be:</p>
 *
 * <pre>        &lt;media:restriction relationship="allow" type="country"&gt;au us&lt;/media:restriction&gt;</pre>
 *
 * <p><em>type</em> specifies the type of restriction (country | uri) that the media can be syndicated. It is an optional attribute; however can only be excluded when using one of the literal values "all" or "none". </p>
 *
 * <p>"country" allows restrictions to be placed based on country code. [<a href="http://www.iso.org/iso/en/prods-services/iso3166ma/index.html">ISO 3166</a>]</p>
 * <p>"uri" allows restrictions based on URI. Examples: urn:apple, http://images.google.com, urn:yahoo, etc.
 * @author cooper
 */
public class Restriction implements Serializable {
	private static final long serialVersionUID = 7944281267467298628L;

	private Relationship relationship;
    private String value;
    private Type type;

    /**
     * Creates a new instance of Restriction
     * @param relationship a Restriction.Relationship object
     * @param type A Restriction.Type object
     * @param value a value for the restriction.
     */
    public Restriction(Relationship relationship, Type type, String value) {
        if ((value == null) || (relationship == null)) {
            throw new NullPointerException(
                "Value and Relationship cannot be null.");
        }

        if ((type == null) && !(value.equals("all") || value.equals("none"))) {
            throw new NullPointerException(
                "Type is required if the value is other than 'all' or 'none'.");
        }

        this.relationship = relationship;
        this.type = type;
        this.value = value;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public boolean equals(Object obj) {
        EqualsBean eBean = new EqualsBean(this.getClass(), this);

        return eBean.beanEquals(obj);
    }

    public int hashCode() {
        EqualsBean equals = new EqualsBean(this.getClass(), this);

        return equals.beanHashCode();
    }

    public String toString() {
        ToStringBean tsBean = new ToStringBean(this.getClass(), this);

        return tsBean.toString();
    }

    /**
     * Indicates the action of the relationship
     */
    public static class Relationship {
        /**
         * An Allow relationship
         */
        public static final Relationship ALLOW = new Relationship("allow");

        /**
         * A deny relationship.
         */
        public static final Relationship DENY = new Relationship("deny");
        private String value;

        private Relationship(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    /**
     * Indicated the type of the relationship
     */
    public static class Type {
        /**
         * Indicates a Country type.
         */
        public static final Type COUNTRY = new Type("country");

        /**
         * Indicates a URI for a special restriction type.
         */
        public static final Type URI = new Type("uri");
        private String value;

        private Type(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }
}
