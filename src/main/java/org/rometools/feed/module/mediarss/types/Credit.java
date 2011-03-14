/*
 * Credit.java
 *
 * Created on April 18, 2006, 7:34 PM
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
 *
 * <strong>&lt;media:credit&gt;</strong></p>
 *
 * <p>Notable entity and the contribution to the creation of the media object. Current entities can include people, companies, locations, etc. Specific entities can have multiple roles, and several entities can have the same role.
 * These should appear as distinct &lt;media:credit&gt; elements.
 * It has 2 optional attributes.</p>
 * <pre>        &lt;media:credit role="producer" scheme="urn:ebu"&gt;entity name&lt;/media:credit&gt;
 * </pre>
 * <p>role specifies the role the entity played. Must be lowercase. It is an optional attribute.</p>
 *
 * <p><em>scheme</em> is the URI that identifies the role scheme. It is an optional attribute. If this attribute is not included, the default scheme is 'urn:ebu'. See: European Broadcasting Union Role Codes.</p>
 *
 *
 * <p>Example roles:</p>
 * <pre>        actor
 *        anchor person
 *        author
 *        choreographer
 *        composer
 *        conductor
 *        director
 *        editor
 *        graphic designer
 *        grip
 *        illustrator
 *        lyricist
 *        music arranger
 *        music group
 *        musician
 *        orchestra
 *        performer
 *        photographer
 *        producer
 *        reporter
 *        vocalist
 * </pre>
 * <p>Additional roles: <a href="http://www.ebu.ch/en/technical/metadata/specifications/role_codes.php">European Broadcasting Union Role Codes</a>
 * @author cooper
 */
public class Credit implements Serializable {
	private static final long serialVersionUID = 7722721287224043428L;
	/**
     * Scheme value for the EBU credits.
     */
    public static final String SCHEME_EBU = "urn:ebu";
    private String name;
    private String role;
    private String scheme;

    /**
     * Creates a new instance of Credit
     * @param scheme schem used
     * @param role role name
     * @param name persons name
     */
    public Credit(String scheme, String role, String name) {
        if (name == null) {
            throw new NullPointerException("A credit name cannot be null.");
        }

        this.scheme = (scheme == null) ? SCHEME_EBU : scheme;
        this.role = (role == null) ? null : role.toLowerCase();
        this.name = name;
    }

    /**
     * Person/organizations name
     * @return Person/organizations name
     */
    public String getName() {
        return name;
    }

    /**
     * Role name
     * @return Role name
     */
    public String getRole() {
        return role;
    }

    /**
     * Scheme used.
     * @return Scheme used.
     */
    public String getScheme() {
        return scheme;
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
}
