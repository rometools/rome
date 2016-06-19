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
 * The "folder" object type represents a collection of files or media objects. This is similar to
 * the "photo album" object type, but not specifically representing a collection of "photos."
 * </p>
 * <p>
 * The "folder" object type is identified by the URI
 * <tt>http://activitystrea.ms/schema/1.0/folder</tt>.
 * </p>
 * <p>
 * A folder has the following additional components:
 * </p>
 * <p>
 * </p>
 * <blockquote class="text">
 * <dl>
 * <dt>Preview Image Link</dt>
 * <dd>A Media Link Construct describing an image file that can be used as a preview image for the
 * folder. Represented by the Representative Image component of the base Object Construct.
 * Processors MAY ignore thumbnails that are of an inappropriate size for their user interface.</dd>
 * </dl>
 * </blockquote>
 */
public class Folder extends ActivityObject {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/folder";
    }

}
