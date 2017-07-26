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
 * The "photo" object type represents a graphical still image.
 * </p>
 * <p>
 * The "photo" Object type is identified by the URI
 * <tt>http://activitystrea.ms/schema/1.0/photo</tt>.
 * </p>
 * <p>
 * A photo has the following additional components:
 * </p>
 * <p>
 * </p>
 * <blockquote class="text">
 * <dl>
 * <dt>Thumbnail</dt>
 * <dd>A link to a thumbnail version of the image. Represented by the Representative Image component
 * of the base Object Construct. Processors MAY ignore thumbnails that are of an inappropriate size
 * for their user interface.</dd>
 * <dt>Larger Image</dt>
 * <dd>A Media Link Construct linking to a larger -- ideally, full-size -- version of the photo
 * intended for standalone viewing. Represented in JSON as a property named <tt>fullImage</tt> whose
 * value is a JSON object with properties as defined in [TODO: xref the JSON serialization of a
 * Media Link Construct].</dd>
 * </dl>
 * </blockquote>
 */
public class Photo extends ActivityObject {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/photo";
    }

}
