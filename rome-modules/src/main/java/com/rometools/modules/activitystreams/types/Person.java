/*
 *  Copyright 2011 robert.cooper.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package com.rometools.modules.activitystreams.types;

import com.rometools.rome.feed.atom.Link;

/**
 * <p>
 * The "person" Object type represents a user account. This is often represents a person, but might
 * also be a company or fictitious character that is being represented by a user account.
 * </p>
 * <p>
 * The "person" Object type is identified by the URI
 * <tt>http://activitystrea.ms/schema/1.0/person</tt>.
 * </p>
 * <p>
 * A person has the following additional components:
 * </p>
 * <p>
 * </p>
 * <blockquote class="text">
 * <dl>
 * <dt>Display Name</dt>
 * <dd>A name that can be used for the person in the user interface. This is often a name by which
 * the individual is known in a given context; no restriction is placed on what kind of name may be
 * used here. Represented by the Name component of the base Object Construct.</dd>
 * <dt>Avatar</dt>
 * <dd>A link to an "avatar" or "userpic" image for the user. Represented by the Representative
 * Image component of the base Object Construct.</dd>
 * </dl>
 * </blockquote>
 */
public class Person extends ActivityObject {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/person";
    }

    /**
     * Get the value of avatar
     *
     * @return the value of avatar
     */
    public Link getAvatar() {
        final Link result = findRelatedLink("avatar");
        if (result != null) {
            return result;
        }
        return findRelatedLink("userpic");
    }

    /**
     * Set the value of avatar
     *
     * @param newavatar new value of avatar
     */
    public void setAvatar(final Link newavatar) {
        final Link old = getAvatar();
        if (old != null) {
            getOtherLinks().remove(old);
        }
        newavatar.setRel("avatar");
        getOtherLinks().add(newavatar);
    }

}
