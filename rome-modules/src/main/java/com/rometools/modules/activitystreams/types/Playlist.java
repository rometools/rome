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
 *
 * <p>
 * The "playlist" object type represents an ordered list of time-based media items, such as video
 * and audio objects.
 * </p>
 * <p>
 * The "playlist" object type is identified by the URI
 * <tt>http://activitystrea.ms/schema/1.0/playlist</tt>.
 * </p>
 * <p>
 * A playlist has no additional components.
 * </p>
 */
public class Playlist extends ActivityObject {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTypeIRI() {
        return "http://activitystrea.ms/schema/1.0/playlist";
    }

}
