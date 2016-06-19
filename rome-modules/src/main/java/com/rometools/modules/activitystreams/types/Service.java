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

/**
 * <p>
 * The "service" Object type represents a website, personal website or blog, business, brand, or
 * other entity that performs some kind of work for other entities, people, or services, or acts as
 * kind of container for other objects.
 * </p>
 * <p>
 * The "service" Object type is identified by the URI
 * <tt>http://activitystrea.ms/schema/1.0/service</tt>.
 * </p>
 * <p>
 * A service has the following additional components:
 * </p>
 * <p>
 * </p>
 * <blockquote class="text">
 * <dl>
 * <dt>icon</dt>
 * <dd>A Media Link Construct representing a link to a small image representing the service.
 * Represented in JSON as a property named <tt>icon</tt> whose value is a JSON object with
 * properties as defined in [TODO: xref the JSON serialization of a Media Link Construct]. The
 * linked image MUST have an aspect ratio of one (horizontal) to one (vertical) and SHOULD be
 * suitable for presentation at a small size.</dd>
 * </dl>
 * </blockquote>
 */
public class Service extends ActivityObject {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/service";
    }

}
