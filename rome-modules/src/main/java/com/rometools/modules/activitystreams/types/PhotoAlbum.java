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

 * The "photo album" object type represents a collection of images.


 * The "photo album" object type is identified by the URI
 * "http://activitystrea.ms/schema/1.0/photo-album".


 * The photo album has the following additional components:



 * <blockquote class="text">
 * <dl>
 * <dt>Preview Image Link</dt>
 * <dd>A Media Link Construct describing an image file that can be used as a preview image for the
 * album. Represented by the Representative Image component of the base Object Construct. Processors
 * MAY ignore thumbnails that are of an inappropriate size for their user interface.</dd>
 * </dl>
 * </blockquote>
 */
public class PhotoAlbum extends ActivityObject {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/photo-album";
    }

}
