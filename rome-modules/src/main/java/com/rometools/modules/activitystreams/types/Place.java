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
 * The "place" object type represents a location on Earth.
 * </p>
 * <p>
 * The "place" object type is identified by the URI
 * <tt>http://activitystrea.ms/schema/1.0/place</tt>.
 * </p>
 * <p>
 * A place has the following additional components:
 * </p>
 * <p>
 * </p>
 * <blockquote class="text">
 * <dl>
 * <dt>Latitude</dt>
 * <dd>The latitude of the place as a point on Earth. Represented in JSON as a property named
 * <tt>latitude</tt> whose value is a JSON number containing a decimal representation of the
 * latitude in degrees.</dd>
 * <dt>Longitude</dt>
 * <dd>The longitude of the place as a point on Earth. Represented in JSON as a property named
 * <tt>longitude</tt> whose value is a JSON number containing a decimal representation of the
 * longitude in degrees.</dd>
 * </dl>
 * </blockquote>
 */
public class Place extends ActivityObject {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/place";
    }

}
